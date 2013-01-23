<%@ page session="false" %>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>
        Access denied page
    </title>
</head>
<html>
    <body>
        <img src="<%=request.getContextPath()%>/resources/images/access-denied.jpg"/> 
        <a href="<c:url value="/spring_security_login" />">Login</a>
    </body>
</html>