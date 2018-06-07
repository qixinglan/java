(function ($) {
"use strict";
$.fxryconfig = $.fxryconfig || {};
$.extend($.fxryconfig,{
	init:function(options){
		$(options.container).append("<p>当前登录用户无修改权限</p>");
	}
});
})(jQuery);