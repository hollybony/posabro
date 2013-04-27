<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title><spring:message code="users" /></title>
        <style>
            td.right{text-align: right}
        </style>
    </head>
    <body>
        <sec:authentication property="principal" var="userLogged" />
        <button id="newButton"><spring:message code="new" /></button>
        <button id="editButton"><spring:message code="edit" /></button>
        <button id="deleteButton"><spring:message code="delete" /></button>
        <button id="refreshButton"><spring:message code="refresh" /></button>
        <div style="height: 8px"></div>
        <table id="usersTable" class="display">
            <thead>
                <tr>
                    <th><spring:message code="user.name" /></th>
                    <th><spring:message code="user.email" /></th>
                    <th><spring:message code="user.verifiedEmail" /></th>
                    <th><spring:message code="user.status" /></th>
                    <th><spring:message code="auditor.createdDate" /></th>
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
                <label for="emailInput"><spring:message code="user.email" /></label>
                <span class="validateTips"></span>
                <input id="emailInput" name="emailInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="enabledInput"><spring:message code="user.status" /></label>
                <span class="validateTips"></span>
                <div id="statusSelect" class="text ui-widget-content ui-corner-all">
                    <input name="status" type="radio" value="ENABLED"/><spring:message code="user.status.enabled" />
                    <input name="status" type="radio" value="DISABLED"/><spring:message code="user.status.disabled" />
                    <input name="status" type="radio" value="TEMP_PASSWORD"/><spring:message code="user.status.tempPsw" />
                </div>
                <label for="passwordInput"><spring:message code="user.password" /></label>
                <span class="validateTips"></span>
                <input id="passwordInput" name="passwordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                <label for="confirmPasswordInput"><spring:message code="user.confirmPassword" /></label>
                <span class="validateTips"></span>
                <input id="confirmPasswordInput" name="confirmPasswordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                <label for="rolesSelect"><spring:message code="user.rolesAssigned" /></label>
                <span class="validateTips"></span>
                <select id="rolesSelect" name="rolesSelect" multiple="multiple" class="text ui-widget-content ui-corner-all">
                </select>
            </form>
        </div>
        <div id="deleteConfirmDialog" title="<spring:message code="users.delete"/>">
            <span class="ui-icon ui-icon-alert"></span><p></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here as they are required by different functions*/
                var name = $('#nameInput'),
                email = $('#emailInput'),
                statusSelect = $('#statusSelect'),
                password = $('#passwordInput'),
                rolesSelect = $('#rolesSelect'),
                confirmPass = $('#confirmPasswordInput');
                var allFields = $([]).add(name).add(email).add(statusSelect).add(password).add(rolesSelect).add(confirmPass);
                var rolesAvailable = null;
                var oTable = null;
                var currentUser = null;
                var CrudHandler = {};
                CrudHandler.refreshTable = function(){
                    var dateFormat = '<spring:message code="jsShortFormatDate"/>';
                    oTable = $('#usersTable').dataTable({
                        'bJQueryUI': true,
                        'bProcessing': true,
                        'bServerSide': true,
                        'bDestroy' : true,
                        'bPaginate': true,
                        'sAjaxSource': 'userController/filter',
                        'aoColumns': [
                            {'mData': 'name'},
                            {'mData': 'email'},
                            {'mData': 'verifiedEmail'},
                            {'mData': 'status'},
                            {'mData': 'auditData.createdDate', 'sClass':'right'},
                            {'mData': 'auditData.createdBy'},
                            {'mData': 'auditData.modifiedDate', 'sClass':'right'},
                            {'mData': 'auditData.modifiedBy'}
                        ],
                        'fnCreatedRow': function( nRow, aData, iDataIndex ) {
                            $('td:eq(4)', nRow).html(aData.auditData.createdDate!==null?$.datepicker.formatDate(dateFormat, new Date(aData.auditData.createdDate)):'-');
                            $('td:eq(6)', nRow).html(aData.auditData.modifiedDate!==null?$.datepicker.formatDate(dateFormat, new Date(aData.auditData.modifiedDate)):'-');
                        },
                        'sPaginationType': 'full_numbers',
                        'oLanguage': {
                            'sProcessing': '<spring:message code="dataTable.processing"/>',
                            'sSearch' : '<spring:message code="dataTable.search"/>',
                            'sLengthMenu' : '<spring:message code="dataTable.pageSizes"/>',
                            'sInfo' : '<spring:message code="dataTable.recordsInfo"/>'
                        }
                    });
                    oTable.prev().find('input[type=text]').datepicker(
                    {
                        constrainInput: false,
                        dateFormat: dateFormat,
                        onSelect:function(dateText){
                            oTable.fnFilter(dateText);
                        }
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
                            rolesSelect.append($('<option></option>').attr('id',value.name).text(value.name));
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
                        email.val(currentUser.email);
                        statusSelect.children('input[value="' + currentUser.status + '"]').prop('checked',true);
                        name.prop('disabled',true);
                        var roleNames = [];
                        $.each(currentUser.roles,function(){
                            roleNames.push(this.name);
                        });
                        rolesSelect.val(roleNames);
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
                    $.each(rolesSelect.val()===null?[]:rolesSelect.val(),function(index, roleName){
                        $.each(rolesAvailable, function(){
                            if(roleName==this.name){
                                roles.push(this);
                            }
                        });
                    });
                    currentUser.name = name.val();
                    currentUser.email = email.val();
                    currentUser.status = statusSelect.children(':checked').val();
                    currentUser.password =  password.val();
                    currentUser.roles = roles;
                    if('${userLogged.username}'===currentUser.name){
                        Validator.updateError(name,'<spring:message code="user.userLoggedConstraint"/>');
                        return;
                    }
                    var successCallback = function(){
                        $('#formDialog').dialog('close');
                        CrudHandler.refreshTable();
                    };
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                        if(isNew){
                            currentUser = null;
                        }
                    };
                    $.ajax({type: "POST",
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
                        if('${userLogged.username}'!==aElement.name){
                            var newMessage = '<spring:message code="user.delete.confirm" />'.replace('{0}', aElement.name);
                            $('#deleteConfirmDialog').text(newMessage).data('id',aElement.name).dialog('open');
                        }
                    }
                };
                CrudHandler.deleteUser = function(id){
                    $.ajax({
                        type: "POST",
                        url:'userController/delete',
                        data:id,
                        contentType: "application/json",
                        success:function(){CrudHandler.refreshTable();},
                        error:function(xhr){
                            ExceptionHandler.handleAjax(xhr);
                        }
                    });
                };
                CrudHandler.init = function(){
                    CrudHandler.refreshTable();
                    /*to turn into jquery buttons al the button tags */            
                    $("button").button();
                    /*set refreshTable method in click event*/
                    $("#refreshButton").click(function(){
                        CrudHandler.refreshTable();
                    });
                    $('#newButton').click(function(){
                        CrudHandler.newUser();
                    });
                    $('#editButton').click(function(){
                        CrudHandler.editUser();
                    });
                    /*set askForDelete method in click event*/
                    $('#deleteButton').click(function(){
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
                            statusSelect.prop('checked',false);
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