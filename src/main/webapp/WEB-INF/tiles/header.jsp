<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false" %>
<html>
    <head>
        <style>
            .ui-flag-menu { width: 50px; }
        </style>
    </head>
    <body>
        <sec:authentication property="principal" var="userLogged" />
        <table style="width: 100%" cellspacing="0" cellpadding="0" border="0">
            <tr>
                <td style="width: 60%">
                    <div class="ui-header-content"></div>
                </td>
                <td style="text-align: right; vertical-align: top;float: right;padding-top: 8px;">
                    <sec:authorize var="loggedIn" access="isAuthenticated()" />
                    <c:if test="${loggedIn}">
                        <label><strong><spring:message code="welcome" arguments="${userLogged.username}"/></strong></label>
                    </c:if>
                    <c:if test="${fn:contains(initParam['spring.profiles.active'],'secured') and not loggedIn}">
                        <a id="regAnchor" href="register"><spring:message code="register"/></a>
                    </c:if>
                    <a id="homeAnchor" href="<%=request.getContextPath()%>/"><spring:message code="home"/></a>
                    <c:if test="${loggedIn}">
                        <a id="exitAnchor" href="<c:url value="/j_spring_security_logout" />"><spring:message code="exit"/></a>
                    </c:if>
                    <a id="langAnchor"><spring:message code="language"/></a> 
                    <ul class="ui-flag-menu">
                        <li><a href="?lang=en"><img src="<%=request.getContextPath()%>/resources/images/uk-flag.png" </img></a></li>
                        <li><a href="?lang=es"><img src="<%=request.getContextPath()%>/resources/images/spain-flag.png" </img></a></li>
                    </ul>
                </td>
            </tr>
        </table>
        <script>
            (function($) {
                $('#regAnchor').button({
                    icons: {
                        primary: "ui-icon-pencil"
                    }
                });
                $('#homeAnchor').button({
                    icons: {
                        primary: "ui-icon-home"
                    }
                });
                $('#exitAnchor').button({
                    icons: {
                        primary: "ui-icon-circle-close"
                    }
                });
                $('#langAnchor').button({
                    icons: {
                        secondary: "ui-icon-triangle-1-s"
                    }
                }).click(function(){
                    var menu = $(this).next().show().position({
                        my: "left top",
                        at: "left bottom",
                        of: this
                    });
                    $(document).one( "click", function() {
                        menu.hide();
                    });
                    return false;
                }).next().hide().menu();
            })(jQuery);
        </script>
    </body>
</html>