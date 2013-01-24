<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><spring:message code="users" /></title>
        <style>
            /*following 3 classes are for form dialog*/
            /*round input text elements*/
            input.text,select.text,div.text { margin-bottom:12px; width:95%; padding: .4em; }
            /*this is for the checkboxes div*/
            div.text { margin-bottom:12px; width:92%; padding: .4em; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
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
                </tr>
            </thead>
        </table>
        <div id="formDialog" title='<spring:message code="users.data" />'>
            <p class="validateTips"></p>
            <form>
                <label for="nameInput"><spring:message code="user.name" /></label>
                <input id="nameInput" name="nameInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="passwordInput"><spring:message code="user.password" /></label>
                <input id="passwordInput" name="passwordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                <label for="rolesSelect"><spring:message code="user.rolesAssigned" /></label>
                <div id="rolesSelect" class="text ui-widget-content ui-corner-all">
                </div>
            </form>
        </div>
        <div id="deleteConfirmDialog" title="<spring:message code="users.delete"/>">
            <span class="ui-icon ui-icon-alert"></span><p><spring:message code="user.delete.confirm" /></p>
        </div>
        <script>
            $(function() {
                /*fields are declares here and they are required by different functions*/
                var name = $('#nameInput'),
                password = $('#passwordInput'),
                rolesSelect = $('#rolesSelect');
                var allFields = $([]).add(name).add(password).add(rolesSelect);
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
                        'sAjaxSource': 'userController/queryUsers',
                        'aoColumns': [
                            { 'mData': 'name'},
                            { 'mData': 'creationDate'},
                        ],
                        'sPaginationType': 'full_numbers',
                        'oLanguage': {
                            'sProcessing': '<spring:message code="dataTable.processing"/>',
                            'sSearch' : '<spring:message code="dataTable.search"/>',
                            'sLengthMenu' : '<spring:message code="dataTable.pageSizes"/>',
                            'sInfo' : '<spring:message code="dataTable.recordsInfo"/>'
                        }
                    });
                    oTable.dblclick(function(){
                        CrudHandler.editElement();
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
                CrudHandler.newElement = function(){
                    currentUser = null;
                    $("#formDialog").dialog('open');
                };
                CrudHandler.editElement = function(){
                    isNew = false;
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        currentUser = oTable.fnGetData(trSelected._DT_RowIndex);
                        name.val(currentUser.name);
                        $.each(currentUser.roles,function(){
                            rolesSelect.children('#' +  this.name).attr('checked',true);
                        });
                        $("#formDialog").dialog('open');
                        name.focus().select();
                    }
                };
                CrudHandler.updateElement = function(){
                    var bValid = true;
                    /*if previously was any error*/
                    allFields.removeClass('ui-state-error');
                
                    bValid = bValid && Validator.checkLength(name, '<spring:message code="user.name.length" />', 3, 32 );
                    //                        bValid = bValid && Validator.checkLength( email, "email", 6, 80 );
                    bValid = bValid && Validator.checkLength( password, '<spring:message code="user.password.length" />', 5, 16 );
                    bValid = bValid && Validator.checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, '<spring:message code="user.name.rules" />');
                    // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                    //bValid = bValid && CrudHandler.checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
                    bValid = bValid && Validator.checkRegexp( password, /^([0-9a-zA-Z])+$/, '<spring:message code="user.password.rules" />');
                    if(bValid){
                        if(!rolesSelect.children('input').is(':checked')){
                            bValid = false;
                            rolesSelect.addClass('ui-state-error');
                            Validator.updateTips('<spring:message code="user.role.rules" />');
                        }
                    }
                    if (bValid) {
                        var roles = [];
                        var isNew = currentUser?false:true;
                        var method = null;
                        if(isNew){
                            method = 'store';
                            currentUser = {};
                        }else{
                            method = 'update';
                        }
                        rolesSelect.children('input:checked').each(function(useless, value){
                            $.each(rolesAvailable, function(){
                                if($(value).attr('id')==this.name){
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
                        //                        $.post('userController/store',JSON.stringify(currentUser),callback,'json');
                        $.ajax(
                        { type: "POST",
                            url:'userController/' + method,
                            data:JSON.stringify(currentUser),
                            contentType: "application/json",
                            success:successCallback
                        });
                    }
                };
                CrudHandler.askForDelete = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        var aElement = oTable.fnGetData(trSelected._DT_RowIndex);
                        var newMessage = $('#deleteConfirmDialog').text().replace('{0}', aElement.name);
                        $('#deleteConfirmDialog').text(newMessage).data('id',aElement.name).dialog('open');
                    }
                };
                CrudHandler.deleteElement = function(id){
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
                        CrudHandler.newElement();
                    });
                    $('#editButton').click(function(event){
                        CrudHandler.editElement();
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
                                CrudHandler.deleteElement($(this).data('id'));
                            },
                            '<spring:message code="cancel" />': function() {
                                $(this).dialog('close');
                            }
                        }
                    });
                    $('#formDialog').dialog({
                        autoOpen: false,
                        height: 300,
                        width: 350,
                        modal: true,
                        buttons: {
                            '<spring:message code="ok" />': function() {
                                CrudHandler.updateElement();
                            },
                            '<spring:message code="cancel" />': function() {
                                $( this ).dialog('close');
                            }
                        },
                        close: function() {
                            allFields.val('').removeClass('ui-state-error');
                            rolesSelect.children('input').attr('checked',false);
                        }
                    });
                    CrudHandler.refreshRoles();
                };
                CrudHandler.formatDate = '<spring:message code="jsShortFormatDate" />';
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
  
                Validator = {};
                Validator.updateTips = function(t) {
                    var tips = $('.validateTips');
                    tips
                    .text(t)
                    .addClass('ui-state-highlight');
                    setTimeout(function() {
                        tips.removeClass('ui-state-highlight', 1500 );
                    }, 500 );
                };
                Validator.checkLength = function(o, message, min, max){
                    if (o.val().length > max || o.val().length < min ) {
                        o.addClass('ui-state-error');
                        Validator.updateTips(message.replace('{0}',min).replace('{1}',max));
                        return false;
                    } else {
                        return true;
                    }
                };
                Validator.checkRegexp = function(o, regexp, n) {
                    if (!(regexp.test(o.val()))){
                        o.addClass('ui-state-error');
                        Validator.updateTips(n);
                        return false;
                    } else {
                        return true;
                    }
                };
                CrudHandler.init();
            });
        </script>
    </body>
</html>