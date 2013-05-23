

if (!this.Validator) {
    this.Validator = {};
}

if (!this.ExceptionHandler) {
    this.ExceptionHandler = {};
}

$(function() {
    Validator.emailRegexp = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
    
    Validator.updateTip = function(el, t) {
        el.text(t).addClass('ui-state-error');
        setTimeout(function() {
            el.removeClass('ui-state-error', 1500);
        }, 500 );
    };
    
    Validator.checkLength = function(o, message, min, max){
        if (o.val().length > max || o.val().length < min ) {
            o.addClass('ui-state-error');
            Validator.updateTip(Validator.resolveErrorMsgElement(o),message.replace('{0}',min).replace('{1}',max));
            return false;
        } else {
            return true;
        }
    };
    Validator.checkRegexp = function(o, regexp, msg) {
        if (!(regexp.test(o.val()))){
            o.addClass( "ui-state-error" );
            Validator.updateTip(Validator.resolveErrorMsgElement(o),msg);
            return false;
        } else {
            return true;
        }
    };
    Validator.updateError = function(error, msg) {
        var element;
        var msgError;
        if(!(error instanceof jQuery)){
            var errorTemp = error.field.replace('.', '_');
            element = $('[id^=' + errorTemp + ']').addClass('ui-state-error');
            msgError = error.defaultMessage;
        }else{
            element = error;
            element.addClass('ui-state-error');
            msgError = msg;
        }
        Validator.updateTip(Validator.resolveErrorMsgElement(element), msgError);
    };
    Validator.cleanErrors = function(el){
        var errorElement = null;
        el.removeClass('ui-state-error');
        if($.isArray(el)){
            $.each(el, function(){
                errorElement = Validator.resolveErrorMsgElement(this);
                errorElement.text('');
            });
        }else{			
            errorElement = Validator.resolveErrorMsgElement(el);
            errorElement.text('');
        }
    }
    
    Validator.resolveErrorMsgElement = function(el){
        if(el.prev().hasClass('validateTips')){
            return el.prev();
        }else{
            return el.next();
        }
    }
    
    ExceptionHandler.handleAjax = function(xhr){
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
    }
    
});

