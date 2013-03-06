<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><spring:message code="roles" /></title>
        <style>
            /*following 3 classes are for form dialog*/
            /*round input text elements*/
            input.text,select.text,div.text { margin-bottom:12px; width:95%; padding: .4em; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
            /*this class is used by some columns of datatable*/
            td.right{text-align: right}
            /*the current theme makes the pagination buttons color too dark, with this class we make these colors lighter*/
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
        <table id="rolesTable" class="display">
            <thead>
                <tr>
                    <th><spring:message code="role.name" /></th>
                    <th><spring:message code="role.description" /></th>
                    <th><spring:message code="auditor.createdDate" /></th>
                    <th><spring:message code="auditor.createdBy" /></th>
                    <th><spring:message code="auditor.modifiedDate" /></th>
                    <th><spring:message code="auditor.modifiedBy" /></th>
                </tr>
            </thead>
        </table>
        <div id="formDialog" title='<spring:message code="roles.data" />'>
            <form id="roleForm">
                <label for="nameInput"><spring:message code="role.name" /></label>
                <span class="validateTips"></span>
                <input id="nameInput" name="nameInput" type="text" class="text ui-widget-content ui-corner-all" />
                <label for="descriptionInput"><spring:message code="role.description" /></label>
                <span class="validateTips"></span>
                <input id="descriptionInput" name="descriptionInput" type="text" class="text ui-widget-content ui-corner-all" />
            </form>
        </div>
        <div id="deleteConfirmDialog" title="<spring:message code="roles.delete"/>">
            <span class="ui-icon ui-icon-alert"></span><p></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here and they are required by different functions*/
                var name = $('#nameInput'),
                description = $('#descriptionInput');
                var allFields = $([]).add(name).add(description);
                var oTable = null;
                var currentRole = null;
                var CrudHandler = {};
                CrudHandler.refreshTable = function(){
                    oTable = $('#rolesTable').dataTable({
                        'bJQueryUI': true,
                        /*'sScrollX': 500,
                    'sScrollXInner': '90%',
                    'bScrollCollapse': true,*/
                        'bProcessing': true,
                        'bServerSide': true,
                        'bDestroy' : true,
                        'bPaginate': true,
                        'sAjaxSource': 'roleController/filter',
                        'aoColumns': [
                            {'mData': 'name'},
                            {'mData': 'description'},
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
                    oTable.prev().find('input[type=text]').datepicker(
                        {
                            constrainInput: false,
                            dateFormat: '<spring:message code="jsShortFormatDate"/>',
                            onSelect:function(dateText){
                                oTable.fnFilter(dateText);
                            }
                        });
                    oTable.dblclick(function(){
                        CrudHandler.editRole();
                    });
                    /* Add a click handler to the rows - this could be used as a callback */
                    $('#rolesTable tbody').click(function(event) {
                        $(oTable.fnSettings().aoData).each(function (){
                            $(this.nTr).removeClass('row_selected');
                        });
                        $(event.target.parentNode).addClass('row_selected');
                    });
                };
                CrudHandler.newRole = function(){
                    currentRole = null;
                    name.prop('disabled',false);
                    $("#formDialog").dialog('open');
                };
                CrudHandler.editRole = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        currentRole = oTable.fnGetData(trSelected._DT_RowIndex);
                        name.val(currentRole.name);
                        description.val(currentRole.description);
                        name.prop('disabled',true);
                        $("#formDialog").dialog('open');
                        description.focus().select();
                    }
                };
                CrudHandler.updateRole = function(){
                    /*if previously was any error*/
                    allFields.removeClass('ui-state-error');
                    
                    var isNew = currentRole?false:true;
                    var webMethod = null;
                    if(isNew){
                        webMethod = 'store';
                        currentRole = {};
                    }else{
                        webMethod = 'update';
                    }
                    currentRole.name = name.val();
                    currentRole.description = description.val();
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
                        if(isNew){
                            currentRole = null;
                        }
                    };
                    //                        $.post('roleController/store',JSON.stringify(currentRole),callback,'json');
                    $.ajax(
                    { type: "POST",
                        url:'roleController/' + webMethod,
                        data:JSON.stringify(currentRole),
                        contentType: "application/json",
                        success:successCallback,
                        error:errorCallback
                    });
                };
                CrudHandler.askForDelete = function(){
                    var trSelected = CrudHandler.getTrSelected(oTable);
                    if(trSelected!==null){
                        var aElement = oTable.fnGetData(trSelected._DT_RowIndex);
                        var newMessage = '<spring:message code="role.delete.confirm" />'.replace('{0}', aElement.name);
                        $('#deleteConfirmDialog').text(newMessage).data('id',aElement.name).dialog('open');
                    }
                };
                CrudHandler.deleteRole = function(id){
                    $.ajax({
                        type: "POST",
                        url:'roleController/delete',
                        data:id,
                        contentType: "application/json",
                        success:function(){CrudHandler.refreshTable();},
                        error:function(xhr){
                            if(xhr.status===500){//bussiness exceptions
                                var errors = $.parseJSON(xhr.responseText);
                                $.each(errors,function(){
                                    alert('message : ' + this.defaultMessage);
                                });
                            }
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
                        CrudHandler.newRole();
                    });
                    $('#editButton').click(function(event){
                        CrudHandler.editRole();
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
                                CrudHandler.deleteRole($(this).data('id'));
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
                                CrudHandler.updateRole();
                            },
                            '<spring:message code="cancel" />': function() {
                                $( this ).dialog('close');
                            }
                        },
                        close: function() {
                            allFields.val('').removeClass('ui-state-error');
                        }
                    });
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