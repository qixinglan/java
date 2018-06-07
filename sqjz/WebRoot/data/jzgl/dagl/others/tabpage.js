(function ($) {
"use strict";
$.fxrytabpage = $.fxrytabpage || {};
$.extend($.fxrytabpage,{
	defaultConfig:{
		zjry:{
		},
		dzrry:{
		},
		yzcry:{
		},
		tgry:{
		},
		jjry:{
		}
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
		me[action].call(me);
	},
	zjry: function(){
	},
	dzrry: function(){
	},
	yzcry: function(){
	},
	tgry: function(){
	},
	jjry: function(){
	}
});
})(jQuery);