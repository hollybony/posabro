<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><spring:message code="groups" /></title>
        <style>
            td.right{text-align: right}
        </style>
    </head>
    <body>
        <button id="newButton"><spring:message code="new" /></button>
        <button id="editButton"><spring:message code="edit" /></button>
        <button id="deleteButton"><spring:message code="delete" /></button>
        <button id="refreshButton"><spring:message code="refresh" /></button>
        <div style="height: 8px"></div>
        <table id="groupsTable" class="display">
            <thead>
                <tr>
                    <th><spring:message code="group.id" /></th>
                    <th><spring:message code="group.name" /></th>
                    <th><spring:message code="auditor.createdDate" /></th>
                    <th><spring:message code="auditor.createdBy" /></th>
                    <th><spring:message code="auditor.modifiedDate" /></th>
                    <th><spring:message code="auditor.modifiedBy" /></th>
                </tr>
            </thead>
        </table>
        <div id="formDialog" title='<spring:message code="groups.data" />'>
            <form id="groupForm">
                <label for="idInput"><spring:message code="group.id" /></label>
                <span class="validateTips"></span>
                <input id="idInput" name="idInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="nameInput"><spring:message code="group.name" /></label>
                <span class="validateTips"></span>
                <input id="nameInput" name="nameInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="rolesSelect"><spring:message code="group.roles" /></label>
                <span class="validateTips"></span>
                <div id="rolesSelect" class="text ui-widget-content ui-corner-all">
                </div>
                <label for="usersSelect"><spring:message code="group.members" /></label>
                <span class="validateTips"></span>
                <select id="usersSelect" name="usersSelect" multiple="multiple" class="text ui-widget-content ui-corner-all">
                </select>
            </form>
        </div>
        <div id="deleteConfirmDialog" title="<spring:message code="groups.delete"/>">
            <span class="ui-icon ui-icon-alert"></span><p></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here and they are required by different functions*/
                var id = $('#idInput'),
                name = $('#nameInput'),
                rolesSelect = $('#rolesSelect'),
                usersSelect = $('#usersSelect');
                var allFields = $([]).add(id).add(name).add(rolesSelect).add(usersSelect);
                var rolesAvailable = null;
                var usersAvailable = null;
                var oTable = null;
                var currentGroup = null;
                var CrudHandler = {};
                CrudHandler.refreshTable = function(){
                    var dateFormat = '<spring:message code="jsShortFormatDate"/>';
                    oTable = $('#groupsTable').dataTable({
                        'bJQueryUI': true,
                        'bProcessing': true,
                        'bServerSide': true,
                        'bDestroy' : true,
                        'bPaginate': true,
                        'sAjaxSource': 'groupController/filter',
                        'aoColumns': [
                            {'mData': 'id'},
                            {'mData': 'name'},
                            {'mData': 'auditData.createdDate', 'sClass':'right'},
                            {'mData': 'auditData.createdBy'},
                            {'mData': 'auditData.modifiedDate', 'sClass':'right'},
                            {'mData': 'auditData.modifiedBy'}
                        ],
                        'fnCreatedRow': function( nRow, aData, iDataIndex ) {
                            $('td:eq(2)', nRow).html(aData.auditData.createdDate!==null?$.datepicker.formatDate(dateFormat, new Date(aData.auditData.createdDate)):'-');
                            $('td:eq(4)', nRow).html(aData.auditData.modifiedDate!==null?$.datepicker.formatDate(dateFormat, new Date(aData.auditData.modifiedDate)):'-');
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
                        {constrainInput: false,
                        dateFormat: dateFormat,
                        onSelect:function(dateText){
                            oTable.fnFilter(dateText);
                        }
                    });
                    oTable.dblclick(function(){
                        CrudHandler.editGroup();
                    });
                    /* Add a click handler to the rows - this could be used as a callback */
                    $('#groupsTable tbody').click(function(event) {
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
                CrudHandler.refreshUsers = function(){
                    $.get('userController/getAll',function(data){
                        usersAvailable = data;
                        $.each(usersAvailable, function(index, value){
                            usersSelect.append($('<option></option>').attr('id',value.name).text(value.name));
                        });
                    });
                };
                CrudHandler.newGroup = function(){
                    currentGroup = null;
                    id.prop('disabled',true);
                    $("#formDialog").dialog('open');
                    name.focus();
                };
                CrudHandler.editGroup = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        currentGroup = oTable.fnGetData(trSelected._DT_RowIndex);
                        id.val(currentGroup.id);
                        id.prop('disabled',true);
                        name.val(currentGroup.name);
                        $.each(currentGroup.roles,function(){
                            rolesSelect.children('#' + this.name).prop('checked',true);
                        });
                        var userNames = [];
                        $.each(currentGroup.members,function(){
                            userNames.push(this.name);
                        });
                        usersSelect.val(userNames);
                        $("#formDialog").dialog('open');
                        name.focus().select();
                    }
                };
                CrudHandler.updateGroup = function(){
                    /*if previously was any error*/
                    allFields.removeClass('ui-state-error');
                    var roles = [];
                    var users = [];
                    var isNew = currentGroup?false:true;
                    var webMethod = null;
                    if(isNew){
                        webMethod = 'store';
                        currentGroup = {};
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
                    /*getting the users selected*/
                    $.each(usersSelect.val()===null?[]:usersSelect.val(),function(index, userName){
                        $.each(usersAvailable, function(){
                            if(userName==this.name){
                                users.push(this);
                            }
                        });
                    });
                    currentGroup.id =  id.val()===null?null:parseInt(id.val());
                    currentGroup.name =  name.val();
                    currentGroup.roles = roles;
                    currentGroup.members = users;
                    var successCallback = function(data){
                        $('#formDialog').dialog('close');
                        CrudHandler.refreshTable();
                    };
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                        if(isNew){
                            currentGroup = null;
                        }
                    };
                    //                        $.post('groupController/store',JSON.stringify(currentGroup),callback,'json');
                    $.ajax(
                    { type: "POST",
                        url:'groupController/' + webMethod,
                        data:JSON.stringify(currentGroup),
                        contentType: "application/json",
                        success:successCallback,
                        error:errorCallback
                    });
                };
                CrudHandler.askForDelete = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        var aElement = oTable.fnGetData(trSelected._DT_RowIndex);
                        var newMessage = '<spring:message code="group.delete.confirm" />'.replace('{0}', aElement.id);
                        $('#deleteConfirmDialog').text(newMessage).data('id',aElement.id).dialog('open');
                    }
                };
                CrudHandler.deleteGroup = function(id){
                    $.ajax({
                        type: "POST",
                        url:'groupController/delete',
                        data:id + '',
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
                    $("#refreshButton").click(function(event) {
                        CrudHandler.refreshTable();
                    });
                    $('#newButton').click(function(event){
                        CrudHandler.newGroup();
                    });
                    $('#editButton').click(function(event){
                        CrudHandler.editGroup();
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
                                CrudHandler.deleteGroup($(this).data('id'));
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
                                CrudHandler.updateGroup();
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
                    CrudHandler.refreshUsers();
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