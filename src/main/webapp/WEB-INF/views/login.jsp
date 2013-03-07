<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="login"/></title>
    </head>
    <body>
        <c:if test="${not empty param.error}">
            <font color="red">
            Login error. <br />
            Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </font>
        </c:if>
        <div style="margin-left:500px;text-align:center">
            <form method="POST" action="<c:url value="/j_spring_security_check" />">
                <table>
                    <tr>
                        <td align="right"><spring:message code="login.username"/></td>
                        <td><input type="text" name="j_username" /></td>
                    </tr>
                    <tr>
                        <td align="right"><spring:message code="login.password"/></td>
                        <td><input type="password" name="j_password" /></td>
                    </tr>
                    <tr>
                        <td align="right"><spring:message code="login.rememberMe"/></td>
                        <td><input type="checkbox" name="_spring_security_remember_me" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="<spring:message code="login"/>" />
                            <input type="reset" value="<spring:message code="login.reset"/>" />
                        </td>
                    </tr>
                </table>
            </form></div>
        <script>
            (function($) {
                $("input[type=submit],input[type=reset]").button();
            })(jQuery);
        </script>
    </body>
</html>