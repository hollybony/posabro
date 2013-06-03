<%-- 
    Document   : billOfLadingNotFound
    Created on : Jun 3, 2013, 11:20:37 AM
    Author     : Carlos Juarez
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title><spring:message code="outBoundBoL.billOfLadingDoesNotExist"/></title>
    </head>
    <body>
        <h3><spring:message code="outBoundBoL.billOfLadingNotFound" arguments="${bolId}"/></h3>
    </body>
</html>
