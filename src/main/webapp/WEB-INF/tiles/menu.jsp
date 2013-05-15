<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <style>
            .ui-side-menu { width: 146px;z-index: 100;}
        </style>
    </head>
    <body>
        <sec:authorize var="loggedIn" access="isAuthenticated()" />
        <c:if test="${fn:contains(initParam['spring.profiles.active'],'secured') and loggedIn}">
            <ul id="menu" class="ui-side-menu">
                <li class="ui-state-disabled"><a href="#"><spring:message code="menu.catalogs"/></a></li>
                <li><a href="#"><spring:message code="menu.processes"/></a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="#"><spring:message code="menu.admin"/></a></li>
                    <li>
                        <a href="#"><spring:message code="menu.security"/></a>
                        <ul class="ui-side-menu">
                            <li><a href="<%=request.getContextPath()%>/users"><spring:message code="menu.security.users"/></a></li>
                            <li><a href="<%=request.getContextPath()%>/roles"><spring:message code="menu.security.roles"/></a></li>
                            <li><a href="<%=request.getContextPath()%>/groups"><spring:message code="menu.security.groups"/></a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><spring:message code="menu.boL.outBoundBoL"/></a>
                        <ul class="ui-side-menu">
                            <li><a href="<%=request.getContextPath()%>/outBoundBoL"><spring:message code="outBoundBoL"/></a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </c:if>
        <c:if test="${!fn:contains(initParam['spring.profiles.active'],'secured')}">
            <ul id="menu" class="ui-side-menu">
                <li>
                    <a href="#"><spring:message code="menu.boL.outBoundBoL"/></a>
                    <ul class="ui-side-menu">
                        <li><a href="<%=request.getContextPath()%>/outBoundBoL"><spring:message code="outBoundBoL"/></a></li>
                    </ul>
                </li>
            </ul>
        </c:if>
        <script>
            (function($) {
                $("#menu").menu();
            })(jQuery);
        </script>
    </body>
</html>