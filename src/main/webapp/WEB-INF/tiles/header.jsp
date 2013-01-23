<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
    <body>
        <div align="center" style=" height: 109px; width: 800px; background-size:800px 105px; background-image: url('<%=request.getContextPath()%>/resources/images/ocsystem-banner_1.jpg');background-repeat:no-repeat;"></div>
        <security:authentication property="principal" var="userLogged" />
        <div align="left" style="position: absolute; top: 21px; right: 22px;width:440px;  background-color: #565656"/>
        <table>
            <tr>
                <td style="color:white">
                    <spring:message code="welcome" arguments="${userLogged.username}"/>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/">
                        <img src="<%=request.getContextPath()%>/resources/images/home.png"></img>
                    </a>
                </td>
                <td align="right">
                    <a href="<c:url value="/j_spring_security_logout" />">
                        <img src="<%=request.getContextPath()%>/resources/images/log-out.png" />
                    </a>
                </td>
                <td>
                    <img src="<%=request.getContextPath()%>/resources/images/<spring:message code="header.flag" />" </img>
                </td>
                <td>
                    <span style="float: right">
                        <a href="?lang=en">en</a> 
                        | 
                        <a href="?lang=es">es</a>
                    </span>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>