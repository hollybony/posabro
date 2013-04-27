<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="createNewPassword"/></title>
    </head>
    <body>
        <div id="genNewPassDiv" style="width: 300px;" class="ui-accordion ui-widget ui-helper-reset">
            <h3 class="ui-state-active ui-corner-top ui-accordion-content ui-helper-reset"><spring:message code="createNewPassword"/></h3>
            <div class="ui-state-highlight ui-corner-bottom ui-helper-reset form-frame">
                <label for="tempPasswordInput"><spring:message code="temporaryPassword"/></label>
                <span class="validateTips"></span>
                <input id="tempPasswordInput" name="tempPasswordInput" type="password" class="text ui-widget-content ui-corner-all"/>
                <label for="newPasswordInput"><spring:message code="newPassword"/></label>
                <span class="validateTips"></span>
                <input id="newPasswordInput" name="newPasswordInput" type="password" class="text ui-widget-content ui-corner-all"/>
                <label for="confirmNewPasswordInput"><spring:message code="confirmNewPassword"/></label>
                <span class="validateTips"></span>
                <input id="confirmNewPasswordInput" name="confirmNewPasswordInput" type="password" class="text ui-widget-content ui-corner-all"/>
                <input id="okButton" type="button" value="<spring:message code="accept"/>" />
                <input id="userNameHidden" type="hidden" value="${userName}"/>
                <input id="tokenHidden" type="hidden" value="${token}"/>
            </div>
        </div>
        <div id="returnDialog" title="<spring:message code="retrievePassword"/>">
            <span class="ui-icon ui-icon-alert"></span><p><spring:message code="newPasswordMsg" /></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                var okButton = $('#okButton');
                var tempPassword = $('#tempPasswordInput');
                var newPassword = $('#newPasswordInput');
                var confirmNewPassword = $('#confirmNewPasswordInput');
                var allFields = $([]).add(tempPassword).add(newPassword).add(confirmNewPassword);
                var Handler = {};
                Handler.generateNewPassword = function(){
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                    };
                    var successCallback = function(){
                        $('#returnDialog').dialog('open');
                        setTimeout(function(){
                            window.location='<%=request.getContextPath()%>';
                        },1300);
                    };
                    allFields.removeClass('ui-state-error');
                    var bValid = true;
                    if(newPassword.val()!==confirmNewPassword.val()){
                        Validator.updateError(newPassword,'<spring:message code="user.passNotEqual"/>');
                        Validator.updateError(confirmNewPassword,'<spring:message code="user.passNotEqual"/>');
                        bValid = bValid && false;
                    }
                    bValid = bValid && Validator.checkLength(tempPassword, '<spring:message code="user.password.length"/>', 3, 32);
                    bValid = bValid && Validator.checkLength(newPassword, '<spring:message code="user.password.length"/>', 3, 32);
                    bValid = bValid && Validator.checkLength(confirmNewPassword, '<spring:message code="user.password.length"/>', 3, 32);
                    if(bValid){
                        var userName = $('#userNameHidden').val();
                        var token = $('#tokenHidden').val();
                        $.ajax(
                        { type: "POST",
                            url:'<%=request.getContextPath()%>/userController/getNewPassword',
                            data:JSON.stringify({userName:userName, tempPassword:tempPassword.val(), newPassword:newPassword.val(), token:token}),
                            contentType: "application/json",
                            success:successCallback,
                            error:errorCallback
                        });
                    }
                };
                Handler.init = function(){
                    okButton.button();
                    okButton.click(function(){
                        Handler.generateNewPassword();
                    });
                    $('#returnDialog').dialog({
                        autoOpen: false,
                        position: 'center',
                        resizable: false,
                        modal: false,
                        close: function() {
                        }
                    });
                };
                Handler.init();
            });
        </script>
    </body>
</html>