<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="retrievePassword"/></title>
    </head>
    <body>
        <div id="retrievePassDiv" style="width: 300px;" class="ui-accordion ui-widget ui-helper-reset">
            <h3 class="ui-state-active ui-corner-top ui-accordion-content ui-helper-reset"><spring:message code="retrievePassword"/></h3>
            <div class="ui-state-highlight ui-corner-bottom ui-helper-reset form-frame">
                <label for="userInput"><spring:message code="login.username"/></label>
                <span class="validateTips"></span>
                <input id="userInput" name="userInput" type="text" class="text ui-widget-content ui-corner-all"/>
                <label for="emailInput"><spring:message code="user.email"/></label>
                <span class="validateTips"></span>
                <input id="emailInput" name="emailInput" type="text" class="text ui-widget-content ui-corner-all"/>
                <input id="sendButton" name="sendButton" type="button" value="<spring:message code="accept"/>" />
                <div id="answerDiv" style="display: none"><spring:message code="tempPasswordSent" /></div>
            </div>
        </div>
        <div id="returnDialog" title="<spring:message code="retrievePassword"/>">
            <span class="ui-icon ui-icon-alert"></span><p><spring:message code="tempPasswordSent" /></p>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                var sendButton = $('#sendButton');
                var userName = $('#userInput');
                var email = $('#emailInput');
                var allFields = $([]).add(userName).add(email).add(email);
                var Handler = {};
                Handler.requestPassword = function(){
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                    };
                    var successCallback = function(){
                        $('#answerDiv').show('fast');
                    };
                    allFields.removeClass('ui-state-error');
                    var bValid = true;
                    bValid = bValid && Validator.checkLength(userName, '<spring:message code="user.name.length"/>', 3, 32);
                    bValid = bValid && Validator.checkLength(email, '<spring:message code="user.email.length"/>', 3, 64);
                    bValid = bValid && Validator.checkRegexp(email, Validator.emailRegexp, '<spring:message code="user.email.rules"/>');
                    if(bValid){
                        $.ajax(
                        { type: "POST",
                            url:'userController/getTempPassword',
                            data:JSON.stringify({userName:$('#userInput').val(),email:$('#emailInput').val()}),
                            contentType: "application/json",
                            success:successCallback,
                            error:errorCallback
                        });
                    }
                };
                Handler.init = function(){
                    $('#retrievePassDiv').draggable();
                    $("#sendButton").button();
                    sendButton.click(function(){
                        Handler.requestPassword();
                    });
                    $('#returnDialog').dialog({
                        autoOpen: false,
                        position: 'center',
                        resizable: false,
                        modal: false,
                        buttons: {
                            '<spring:message code="ok" />': function() {
                                $(this).dialog('close');
                            }
                        },
                        close: function() {
                            sendButton.prop('enabled',false);
                        }
                    });
                };
                Handler.init();
            });
        </script>
    </body>
</html>