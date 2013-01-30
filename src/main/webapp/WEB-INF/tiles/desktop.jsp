<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
    <head>
        <!--for production sake-->
        <link href="<c:url value="/resources/jquery-ui-1.9.2/css/dark-hive/jquery-ui-1.9.2.custom.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/DataTables-1.9.4/media/css/demo_table_jui.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/jquery-ui-1.9.2/development-bundle/demos/demos.css" />" rel="stylesheet" type="text/css"/>

        <!--for development sale-->
        <!--<link href="<c:url value="/resources/jquery-ui-1.9.2/development-bundle/themes/dark-hive/jquery.ui.all.css" />" rel="stylesheet" type="text/css"/>-->


        <!--js-->
        <!--It must be before any ui js-->
        <script src="<c:url value="/resources/jquery-ui-1.9.2/development-bundle/jquery-1.8.3.js"/>"></script>

        <!--This includes all the js ui files use it instead of resources/jquery-ui-1.9.1/development-bundle/ui/*-->
        <script src="<c:url value="/resources/jquery-ui-1.9.2/js/jquery-ui-1.9.2.custom.js"/>"></script>



<!--        <script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.core.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.widget.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.tabs.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.datepicker.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.tooltip.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.button.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.menu.js"/>"></script>
<script src="<c:url value="/resources/jquery-ui-1.9.1/development-bundle/ui/jquery.ui.dialog.js"/>"></script>
        -->

        <!--A dependency of dialog component-->
        <script src="<c:url value="/resources/jquery-ui-1.9.2/development-bundle/external/jquery.bgiframe-2.1.2.js"/>"></script>

        <script src="<c:url value="/resources/DataTables-1.9.4/media/js/jquery.dataTables.js"/>"></script>

        <script src="<c:url value="/resources/json2.js"/>"></script>
    </head>
</head>
<body>
    <table width="1250px" align="center">
        <tr>
            <td colspan="2">
                <tiles:insertAttribute name="header" />
            </td>
        </tr>
        <tr>
            <td style="vertical-align: top;width: 159px">
                <tiles:insertAttribute name="menu" />
            </td>
            <td style="height:520px;vertical-align: top;">
                <tiles:insertAttribute name="body" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <tiles:insertAttribute name="footer" />
            </td>
        </tr>
    </table>
    <div id="expiredSessionDialog" title="<spring:message code="session.expired"/>">
        <span class="ui-icon ui-icon-alert"></span><p><spring:message code="session.expired.redirect"/></p>
    </div>
    <script>
        (function($) {
            var expiredSessionDialog =  $('#expiredSessionDialog').dialog({
                autoOpen: false,
                position: 'center',
                resizable: false,
                modal: true
            });
            
            $(document).ajaxError(function(event, xhr) {
                var response = null;
                /*it's a session timeout error*/
                if(xhr.responseText.substring(0, '<html>'.length),'<html>'){
                    if(xhr.responseText.indexOf('Session has expired')!==-1){
                        expiredSessionDialog.dialog('open');
                        expiredSessionDialog.effect('shake',{}, 500, function(){
                            document.location = ''; 
                        });
                    }/*else{
                        response = $.parseJSON(xhr.responseText);
                        alert(response.message);
                    }*/
                }
            });      
        })(jQuery);
    </script>
</body>
</html>