//                    var bValid = true;
/*if previously was any error*/
//                    allFields.removeClass('ui-state-error');
//                
//                    bValid = bValid && Validator.checkLength(name, '<spring:message code="user.name.length" />', 3, 32 );
//                    bValid = bValid && Validator.checkLength( password, '<spring:message code="user.password.length" />', 5, 16 );
//                    bValid = bValid && Validator.checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, '<spring:message code="user.name.rules" />');
//                    bValid = bValid && Validator.checkRegexp( password, /^([0-9a-zA-Z])+$/, '<spring:message code="user.password.rules" />');
//                    if(bValid){
//                        if(!rolesSelect.children('input').is(':checked')){
//                            bValid = false;
//                            rolesSelect.addClass('ui-state-error');
//                            Validator.updateTips('<spring:message code="user.role.rules" />');
//                        }
//                    }




if (!this.Validator) {
    this.Validator = {};
}

$(function() {
    Validator.updateTips = function(t) {
        var tips = $('.validateTips');
        tips.text(t).addClass('ui-state-highlight');
        setTimeout(function() {
            tips.removeClass('ui-state-highlight', 1500 );
        }, 500 );
    };
    Validator.updateTip = function(el, t) {
        el.text(t).addClass('ui-state-highlight');
        setTimeout(function() {
            el.removeClass('ui-state-highlight', 1500);
        }, 500 );
    };
    Validator.checkLength = function(o, message, min, max){
        if (o.val().length > max || o.val().length < min ) {
            o.addClass('ui-state-error');
            Validator.updateTips(message.replace('{0}',min).replace('{1}',max));
            return false;
        } else {
            return true;
        }
    };
    Validator.checkRegexp = function(o, regexp, n) {
        if (!(regexp.test(o.val()))){
            o.addClass( "ui-state-error" );
            Validator.updateTips(n);
            return false;
        } else {
            return true;
        }
    };
    Validator.updateError = function(error, msg) {
        var element;
        var msgError;
        if(!(error instanceof jQuery)){
            //                    alert('error in field : ' + error.field + ' from object : ' + error.objectName + ' message : ' + error.defaultMessage);
            element = $('#' + error.objectName + 'Form [id^=' + error.field + ']').addClass('ui-state-error');
            msgError = error.defaultMessage;
        }else{
            element = error;
            element.addClass('ui-state-error');
            msgError = msg;
        }
        Validator.updateTip(element.prev(), msgError);
    };
});

