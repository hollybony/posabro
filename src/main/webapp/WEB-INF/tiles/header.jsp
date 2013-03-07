<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
    <body>
        <sec:authentication property="principal" var="userLogged" />
        <table style="width: 100%" cellspacing="0" cellpadding="0">
            <tr>
                <td style="width: 60%" rowspan="3">
                    <div align="center" style=" height: 105px;  background-size:800px 105px; background-image: url('<%=request.getContextPath()%>/resources/images/ocsystem-banner.jpg');background-repeat:no-repeat;"></div>
                </td>
                <td style="background-color: #565656;color: white;float: right;padding:4px" class="ui-corner-all">
                    <sec:authorize var="loggedIn" access="isAuthenticated()" />
                    <c:if test="${loggedIn}">
                        <a href="<%=request.getContextPath()%>/">
                            <img src="<%=request.getContextPath()%>/resources/images/home.png"/>
                        </a>
                    </c:if>
                    <a href="<c:url value="/j_spring_security_logout" />">
                        <img src="<%=request.getContextPath()%>/resources/images/log-out.png" />
                    </a>
                    <img src="<%=request.getContextPath()%>/resources/images/<spring:message code="header.flag" />" </img>
                    <span>
                        <a href="?lang=en">en</a> 
                        | 
                        <a href="?lang=es">es</a>
                    </span>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
        </table>
    </div>
</body>
</html>