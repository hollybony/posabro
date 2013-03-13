<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="login"/></title>
        <style>
            /*following 3 classes are for form dialog*/
            /*round input text elements*/
            input.text,select.text,div.text { margin-bottom:12px; width:95%; padding: .1em; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>
    </head>
    <body>
        <c:if test="${not empty param.error}">
            <c:if test="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message=='Bad credentials'}">
                <a href="retrievePassword">Forgot your password?</a>
            </c:if>
        </c:if>
        <div id="formDiv" style="width: 300px;padding:20px" class="ui-corner-all ui-state-highlight">
            <form id="loginForm" method="POST" action="<c:url value="/j_spring_security_check" />">
                <label for="j_username"><spring:message code="login.username"/></label>
                <span class="validateTips"></span>
                <input id="j_username" name="j_username" type="text" class="text ui-widget-content ui-corner-all"/>
                <label for="j_password"><spring:message code="login.password"/></label>
                <span class="validateTips"></span>
                <input id="j_password" name="j_password" type="password" class="text ui-widget-content ui-corner-all"/>
                <label for="_spring_security_remember_me"><spring:message code="login.rememberMe"/></label>
                <span class="validateTips"></span>
                <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" class="ui-widget-content ui-corner-all"/>
                <br>
                <input id="loginButton" type="button" value="<spring:message code="login"/>" />
                <input type="reset" value="<spring:message code="login.reset"/>" />
            </form>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                var userName = $('#j_username');
                var password = $('#j_password');
                var allFields = $([]).add(userName).add(password);
                var Handler = {};
                Handler.login = function(){
                    allFields.removeClass('ui-state-error');
                    var bValid = true;
                    bValid = bValid && Validator.checkLength(userName, '<spring:message code="user.name.length"/>', 3, 32);
                    bValid = bValid && Validator.checkLength(password, '<spring:message code="user.password.length"/>', 3, 32);
                    if(bValid){
                        $('#loginForm').submit();
                    }
                };
                Handler.init = function(){
                    $('input[type=button],input[type=reset]').button();
                    $('#formDiv').draggable();
                    $("#loginButton").click(function(){
                        Handler.login();
                    });
                };
                Handler.init();
            });
        </script>
    </body>
</html>