<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<html>
    <head>
        <title><spring:message code="emailVerification.title" /></title>
        <link href="<c:url value="/resources/jquery-ui-1.9.2/css/dark-hive/jquery-ui-1.9.2.custom.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/jquery-ui-1.9.2/development-bundle/demos/demos.css" />" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${not empty userVerified}">
            <div class="ui-widget">
                <div class="ui-state-highlight ui-corner-all" style="padding: 0px 0.7em;">
                    <p>
                        <span class="ui-icon ui-icon-info" style="float: left; margin-right: 0.3em;"></span>
                        <strong>Info!</strong>
                        <spring:message code="greeting.message" arguments="${userVerified.name}" /><br />
                        <spring:message code="emailVerification.verified" arguments="${userVerified.email}"/>
                    </p>
                </div>
            </div>
        </c:if>
    </body>
</html>