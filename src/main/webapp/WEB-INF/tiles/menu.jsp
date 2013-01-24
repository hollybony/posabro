<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <style>
            .ui-menu { width: 150px;
            z-index: 100;}
        </style>
    </head>
    <body>
        <ul id="menu">
            <li class="ui-state-disabled"><a href="#"><spring:message code="menu.catalogs"/></a></li>
            <li><a href="#"><spring:message code="menu.processes"/></a></li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="#"><spring:message code="menu.admin"/></a></li>
                <li>
                    <a href="#"><spring:message code="menu.security"/></a>
                    <ul>
                        <li><a href="<%=request.getContextPath()%>/users"><spring:message code="menu.security.users"/></a></li>
                        <li><a href="<%=request.getContextPath()%>/roles"><spring:message code="menu.security.roles"/></a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>
        <script>
            (function($) {
                $("#menu").menu();
            })(jQuery);
        </script>
    </body>
</html>