<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
    <head>
        <title>Email Address confirmation</title>
    </head>
    <body>
        <c:if test="${not empty userVerified}">
            <font color="green">
            Hello ${userVerified.name}!! <br />
            Your email address: ${userVerified.email} has been confirmed!!
            </font>
        </c:if>
    </body>
</html>