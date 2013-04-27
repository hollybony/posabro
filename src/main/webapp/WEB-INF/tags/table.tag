<%@ tag import="java.util.Date" import="java.text.DateFormat" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="sAjaxSource" required="true" %>
<%@ attribute name="aoColumns" required="false" %>
<%@ attribute name="fnCreatedRow" required="false" %>
<style>
    td.right{text-align: right}
</style>
<script>
    if (!this.TableHandler) {
        this.TableHandler = {};
    }
    $(function() {
        var o${id} = null;
        var TableHandler = {};
        TableHandler.refreshTable = function(oTable){
            var dateFormat = '<spring:message code="jsShortFormatDate"/>';
            oTable = $('#${id}').dataTable({
                'bJQueryUI': true,
                'bProcessing': true,
                'bServerSide': true,
                'bDestroy' : true,
                'bPaginate': true,
                'sAjaxSource': '${sAjaxSource}',
                'aoColumns': ${aoColumns},
                'fnCreatedRow': ${fnCreatedRow},
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
                    o${id}.fnFilter(dateText);
                }
            });
            oTable.dblclick(function(){
                TableHandler.editUser();
            });
            /* Add a click handler to the rows - this could be used as a callback */
            $('#usersTable tbody').click(function(event) {
                $(oTable.fnSettings().aoData).each(function (){
                    $(this.nTr).removeClass('row_selected');
                });
                $(event.target.parentNode).addClass('row_selected');
            });
        };
        TableHandler.getTrSelected = function(oTable){
            var aReturn = null;
            var aTrs = oTable.fnGetNodes();
            for(var i=0 ; i<aTrs.length ; i++ ){
                if($(aTrs[i]).hasClass('row_selected')){
                    aReturn = aTrs[i];
                    break;
                }
            }
            return aReturn;
        };
        TableHandler.refreshTable(o${id});
    });
</script>

<%
    DateFormat dateFormat =
            DateFormat.getDateInstance(DateFormat.LONG);
    Date now = new Date(System.currentTimeMillis());
    out.println(dateFormat.format(now));
%>
