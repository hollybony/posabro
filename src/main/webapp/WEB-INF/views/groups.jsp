<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><spring:message code="users" /></title>
        <style>
            /*following 4 classes are for form dialog*/
            /*round input text elements*/
            input.text,select.text,div.text { margin-bottom:12px; width:95%; padding: .4em; }
            /*this is for the checkboxes div*/
            div.text { margin-bottom:12px; width:92%; padding: .4em; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
            /*this class is used by some columns of datatable*/
            td.right{text-align: right}
            /*the curreny team makes the pagination buttons color too dark, with this class we make these colors lighter*/
            .paging_full_numbers .ui-button {
                color: #336699 !important;
            }
        </style>
    </head>
    <body>
        <button id="newButton"><spring:message code="new" /></button>
        <button id="editButton"><spring:message code="edit" /></button>
        <button id="deleteButton"><spring:message code="delete" /></button>
        <button id="refreshButton"><spring:message code="refresh" /></button>
        <div style="height: 8px"></div>
        <table id="usersTable" class="display">
            <thead>
                <tr>
                    <th><spring:message code="user.name" /></th>
                    <th><spring:message code="user.creationDate" /></th>
                    <th><spring:message code="auditor.createdBy" /></th>
                    <th><spring:message code="auditor.modifiedDate" /></th>
                    <th><spring:message code="auditor.modifiedBy" /></th>
                </tr>
            </thead>
        </table>
        <div id="formDialog" title='<spring:message code="users.data" />'>
            <form id="userForm">
                <label for="nameInput"><spring:message code="user.name" /></label>
                <span class="validateTips"></span>
                <input id="nameInput" name="nameInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="passwordInput"><spring:message code="user.password" /></label>
                <span class="validateTips"></span>
                <input id="passwordInput" name="passwordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                <label for="confirmPasswordInput"><spring:message code="user.confirmPassword" /></label>
                <span class="validateTips"></span>
                <input id="confirmPasswordInput" name="confirmPasswordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                <label for="rolesSelect"><spring:message code="user.rolesAssigned" /></label>
                <span class="validateTips"></span>
                <div id="rolesSelect" class="text ui-widget-content ui-corner-all">
                </div>
            </form>
        </div>
        <div id="deleteConfirmDialog" title="<spring:message code="users.delete"/>">
            <span class="ui-icon ui-icon-alert"></span><p><spring:message code="user.delete.confirm" /></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here and they are required by different functions*/
                var name = $('#nameInput'),
                password = $('#passwordInput'),
                rolesSelect = $('#rolesSelect'),
                confirmPass = $('#confirmPasswordInput');
                var allFields = $([]).add(name).add(password).add(rolesSelect).add(confirmPass);
                var rolesAvailable = null;
                var oTable = null;
                var currentUser = null;
                var CrudHandler = {};
                CrudHandler.refreshTable = function(){
                    oTable = $('#usersTable').dataTable({
                        'bJQueryUI': true,
                        /*'sScrollX': 500,
                    'sScrollXInner': '90%',
                    'bScrollCollapse': true,*/
                        'bProcessing': true,
                        'bServerSide': true,
                        'bDestroy' : true,
                        'bPaginate': true,
                        'sAjaxSource': 'userController/filter',
                        'aoColumns': [
                            {'mData': 'name'},
                            {'mData': 'auditData.createdDate', 'sClass':'right'},
                            {'mData': 'auditData.createdBy'},
                            {'mData': 'auditData.modifiedDate', 'sClass':'right'},
                            {'mData': 'auditData.modifiedBy'}
                        ],
                        'sPaginationType': 'full_numbers',
                        'oLanguage': {
                            'sProcessing': '<spring:message code="dataTable.processing"/>',
                            'sSearch' : '<spring:message code="dataTable.search"/>',
                            'sLengthMenu' : '<spring:message code="dataTable.pageSizes"/>',
                            'sInfo' : '<spring:message code="dataTable.recordsInfo"/>'
                        }/*,
                        'fnServerParams': function ( aoData ) {
                            aoData.push( { "name": "more_data", "value": "my_value" } );
                        }*/
                    });
                    oTable.dblclick(function(){
                        CrudHandler.editUser();
                    });
                    /* Add a click handler to the rows - this could be used as a callback */
                    $('#usersTable tbody').click(function(event) {
                        $(oTable.fnSettings().aoData).each(function (){
                            $(this.nTr).removeClass('row_selected');
                        });
                        $(event.target.parentNode).addClass('row_selected');
                    });
                };
                CrudHandler.refreshRoles = function(){
                    $.get('roleController/getAll',function(data){
                        rolesAvailable = data;
                        $.each(rolesAvailable, function(index, value){
                            rolesSelect.append('<input type="checkbox" id="' + value.name + '"/><label for="' + value.name + '">' + value.name + '</label>');
                        });
                    });                
                };
                CrudHandler.newUser = function(){
                    currentUser = null;
                    name.prop('disabled',false);
                    $("#formDialog").dialog('open');
                };
                CrudHandler.editUser = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        currentUser = oTable.fnGetData(trSelected._DT_RowIndex);
                        name.val(currentUser.name);
                        name.prop('disabled',true);
                        $.each(currentUser.roles,function(){
                            rolesSelect.children('#' + this.name).prop('checked',true);
                        });
                        $("#formDialog").dialog('open');
                        password.focus().select();
                    }
                };
                CrudHandler.updateUser = function(){
                    /*if previously was any error*/
                    allFields.removeClass('ui-state-error');
                    
                    if(password.val()!==confirmPass.val()){
                        Validator.updateError(password,'<spring:message code="user.passNotEqual"/>');
                        Validator.updateError(confirmPass,'<spring:message code="user.passNotEqual"/>');
                        return;
                    }
                    var roles = [];
                    var isNew = currentUser?false:true;
                    var webMethod = null;
                    if(isNew){
                        webMethod = 'store';
                        currentUser = {};
                    }else{
                        webMethod = 'update';
                    }
                    /*getting the selected roles*/
                    rolesSelect.children('input:checked').each(function(useless, value){
                        $.each(rolesAvailable, function(){
                            if($(value).prop('id')==this.name){
                                roles.push(this);
                            }
                        });
                    });
                    currentUser.name =  name.val();
                    currentUser.password =  password.val();
                    currentUser.roles = roles;
                    var successCallback = function(data){
                        $('#formDialog').dialog('close');
                        CrudHandler.refreshTable();
                    };
                    var errorCallback = function(xhr){
                        if(xhr.status===500){//bussiness exceptions
                            var errors = $.parseJSON(xhr.responseText);
                            $.each(errors,function(){
                                alert('message : ' + this.defaultMessage);
                            });
                        }else if (xhr.status===400){//validation errors
                            var errors = $.parseJSON(xhr.responseText);
                            $.each(errors, function(){
                                Validator.updateError(this);
                            });
                        }else{
                            alert('unknown error contact, your webmaster');
                        }
                    };
                    //                        $.post('userController/store',JSON.stringify(currentUser),callback,'json');
                    $.ajax(
                    { type: "POST",
                        url:'userController/' + webMethod,
                        data:JSON.stringify(currentUser),
                        contentType: "application/json",
                        success:successCallback,
                        error:errorCallback
                    });
                };
                CrudHandler.askForDelete = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        var aElement = oTable.fnGetData(trSelected._DT_RowIndex);
                        var newMessage = $('#deleteConfirmDialog').text().replace('{0}', aElement.name);
                        $('#deleteConfirmDialog').text(newMessage).data('id',aElement.name).dialog('open');
                    }
                };
                CrudHandler.deleteUser = function(id){
                    $.ajax({
                        type: "POST",
                        url:'userController/delete',
                        data:id,
                        contentType: "application/json",
                        success:function(){CrudHandler.refreshTable();}
                    });
                };
                CrudHandler.init = function(){
                    CrudHandler.refreshTable();
                    /*to turn into jquery buttons al the button tags */            
                    $("button").button();
                    /*set refreshTable method in click event*/
                    $("#refreshButton").click(function(event) {
                        CrudHandler.refreshTable();
                    });
                    $('#newButton').click(function(event){
                        CrudHandler.newUser();
                    });
                    $('#editButton').click(function(event){
                        CrudHandler.editUser();
                    });
                    /*set askForDelete method in click event*/
                    $('#deleteButton').click(function(event){
                        CrudHandler.askForDelete();
                        return false;
                    });
                    $('#deleteConfirmDialog').dialog({
                        autoOpen: false,
                        position: 'center',
                        resizable: false,
                        modal: false,
                        buttons: {
                            '<spring:message code="ok" />': function() {
                                $(this).dialog('close');
                                CrudHandler.deleteUser($(this).data('id'));
                            },
                            '<spring:message code="cancel" />': function() {
                                $(this).dialog('close');
                            }
                        }
                    });
                    $('#formDialog').dialog({
                        autoOpen: false,
                        width: 400,
                        modal: true,
                        buttons: {
                            '<spring:message code="ok" />': function() {
                                CrudHandler.updateUser();
                            },
                            '<spring:message code="cancel" />': function() {
                                $( this ).dialog('close');
                            }
                        },
                        close: function() {
                            allFields.val('').removeClass('ui-state-error');
                            rolesSelect.children('input').prop('checked',false);
                        }
                    });
                    CrudHandler.refreshRoles();
                };
                CrudHandler.getTrSelected = function(oTableLocal){
                    var aReturn = null;
                    var aTrs = oTableLocal.fnGetNodes();
                    for(var i=0 ; i<aTrs.length ; i++ ){
                        if($(aTrs[i]).hasClass('row_selected')){
                            aReturn = aTrs[i];
                            break;
                        }
                    }
                    return aReturn;
                };
                CrudHandler.init();
            });
        </script>
    </body>
</html>