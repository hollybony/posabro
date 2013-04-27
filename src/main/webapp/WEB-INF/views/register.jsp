<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title><spring:message code="register" /></title>
    </head>
    <body>
        <div id="formDiv" style="width: 300px;" class="ui-accordion ui-widget ui-helper-reset">
            <h3 class="ui-state-active ui-corner-top ui-accordion-content ui-helper-reset"><spring:message code="register"/></h3>
            <div class="ui-state-highlight ui-corner-bottom ui-helper-reset form-frame">
                <form id="userForm" method="POST" action="<c:url value="/j_spring_security_check" />">
                    <label for="nameInput"><spring:message code="user.name" /></label>
                    <span class="validateTips"></span>
                    <input id="nameInput" name="j_username" type="text" class="text ui-widget-content ui-corner-all" />
                    <label for="emailInput"><spring:message code="user.email" /></label>
                    <span class="validateTips"></span>
                    <input id="emailInput" name="emailInput" type="text" class="text ui-widget-content ui-corner-all" />
                    <label for="passwordInput"><spring:message code="user.password" /></label>
                    <span class="validateTips"></span>
                    <input id="passwordInput" name="j_password" type="password" value="" class="text ui-widget-content ui-corner-all" />
                    <label for="confirmPasswordInput"><spring:message code="user.confirmPassword" /></label>
                    <span class="validateTips"></span>
                    <input id="confirmPasswordInput" name="confirmPasswordInput" type="password" value="" class="text ui-widget-content ui-corner-all" />
                    <input id="registerButton" name="registerButton" type="button" value="<spring:message code="register" />"/>
                </form>
            </div>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here and they are required by different functions*/
                var name = $('#nameInput'),
                email = $('#emailInput'),
                password = $('#passwordInput'),
                confirmPass = $('#confirmPasswordInput');
                var allFields = $([]).add(name).add(email).add(password).add(confirmPass);
                var CrudHandler = {};
                CrudHandler.registerUser = function(){
                    /*if previously was any error*/
                    allFields.removeClass('ui-state-error');
                    if(password.val()!==confirmPass.val()){
                        Validator.updateError(password,'<spring:message code="user.passNotEqual"/>');
                        Validator.updateError(confirmPass,'<spring:message code="user.passNotEqual"/>');
                        return;
                    }
                    var currentUser = {};
                    currentUser.name = name.val();
                    currentUser.email = email.val();
                    currentUser.password =  password.val();
                    currentUser.roles=[{name:'fake',description:'double fake'}];
                    var successCallback = function(data){
                        $('#userForm').submit();
                    };
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
                    //                        $.post('userController/store',JSON.stringify(currentUser),callback,'json');
                    $.ajax(
                    { type: "POST",
                        url:'userController/registerGuest',
                        data:JSON.stringify(currentUser),
                        contentType: "application/json",
                        success:successCallback,
                        error:errorCallback
                    });
                };
                CrudHandler.init = function(){
                    $("#registerButton").button();
                    $('#registerButton').click(function(){
                        CrudHandler.registerUser();
                    });
                    $('#formDiv').draggable();
                };
                CrudHandler.init();
            });
        </script>
    </body>
</html>