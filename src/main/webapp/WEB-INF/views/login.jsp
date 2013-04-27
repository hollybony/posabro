<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="login"/></title>
    </head>
    <body>
        <c:if test="${not empty param.error}">
            <c:if test="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message=='Bad credentials'}">
                <div class="ui-widget">
                    <div class="ui-state-error ui-corner-all" style="padding: 0px 0.7em;">
                        <p>
                            <span class="ui-icon ui-icon-alert" style="float: left; margin-right: 0.3em;"></span>
                            <strong><spring:message code="alert"/></strong>
                            <spring:message code="login.badCredentials"/>
                            <a href="generateTempPassword"><spring:message code="login.forgotPassword"/></a></p>
                    </div>
                </div><br/>
            </c:if>
        </c:if>
        <div id="formDiv" style="width: 300px;" class="ui-accordion ui-widget ui-helper-reset">
            <h3 class="ui-state-active ui-corner-top ui-accordion-content ui-helper-reset"><spring:message code="login"/></h3>
            <div class="ui-state-highlight ui-corner-bottom ui-helper-reset form-frame">
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
                    <br/><br/>
                    <input id="loginButton" type="submit" value="<spring:message code="login"/>" />
                    <input type="reset" value="<spring:message code="login.reset"/>" />
                </form>
            </div>
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
                    $('input[type=button],input[type=reset],input[type=submit]').button();
                    $('#formDiv').draggable();
                    $("#loginButton").click(function(){
                        Handler.login();
                        return false;
                    });
                };
                Handler.init();
            });
        </script>
    </body>
</html>