(function($) {
    $.widget("ui.collapsiblePanel", {
        options: {
        },
        _create: function() {
            this.element.addClass("ui-widget");
            this.element.children(":first").addClass("ui-widget-header ui-state-default ui-state-hover ui-corner-top ui-panel-header");
            this.element.children(":last").addClass("ui-widget-content ui-corner-bottom ui-panel-container");
            this.element.children(":first").bind({
                click: this._collapsePanel,
                mouseenter: this._addHoverState,
                mouseleave: this._removeHoverState
            });
        },
        destroy: function() {
            $.Widget.prototype.destroy.call(this, arguments);
            this.element.removeClass("ui-widget").children(":first").removeClass("ui-widget-header ui-state-default ui-state-hover ui-corner-top ui-panel-header");
            this.element.removeClass("ui-widget").children(":last").removeClass("ui-widget-content ui-corner-bottom ui-panel-container");
            this.element.children(":first").unbind();
        },
        disable: function() {
            $.Widget.prototype.disable.call(this, arguments);
        },
        enable: function() {
            $.Widget.prototype.enable.call(this, arguments);
        },
        _collapsePanel: function(){
            if($(this).parent().children(":last").is(":visible")){
                $(this).parent().children(":last").hide('fast');
            }else{
                $(this).parent().children(":last").show('fast');
            } 
        }
    });
})(jQuery);