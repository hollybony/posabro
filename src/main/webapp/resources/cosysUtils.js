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
    Validator.emailRegexp = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
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
            Validator.updateTip(o.prev(),message.replace('{0}',min).replace('{1}',max));
            return false;
        } else {
            return true;
        }
    };
    Validator.checkRegexp = function(o, regexp, msg) {
        if (!(regexp.test(o.val()))){
            o.addClass( "ui-state-error" );
            Validator.updateTip(o.prev(),msg);
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

