<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
    <head>
        <title><spring:message code="retrievePassword"/></title>
        <style>
            /*following 3 classes are for form dialog*/
            /*round input text elements*/
            input.text,select.text,div.text { margin-bottom:12px; width:95%; padding: .4em; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>
    </head>
    <body>
        <div style="width: 300px">
            <label for="userInput"><spring:message code="login.username"/></label>
            <span class="validateTips"></span>
            <input id="userInput" name="userInput" type="text" class="text ui-widget-content ui-corner-all"/>
            <label for="emailInput"><spring:message code="user.email"/></label>
            <span class="validateTips"></span>
            <input id="emailInput" name="emailInput" type="text" class="text ui-widget-content ui-corner-all"/>
            <input id="sendButton" name="sendButton" type="button" value="<spring:message code="accept"/>" />
            <div id="answerDiv" style="display: none"><spring:message code="tempPasswordSent" /></div>
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
                        if(xhr.status===500){//bussiness exceptions
                            var errors = $.parseJSON(xhr.responseText);
                            $.each(errors,function(){
                                alert('message : ' + this.defaultMessage);
                            });
                        }else if (xhr.status===400){//validation errors
                            var errors = $.parseJSON(xhr.responseText);
                            $.each(errors, function(){
                                Validator.updateError(this);
                            });
                        }else{
                            alert('unknown error contact, your webmaster');
                        }
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
                    $("#sendButton").button();
                    sendButton.click(function(event){
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