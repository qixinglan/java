(function ($) {
"use strict";
$.fxrys = $.fxrys || {};
$.extend($.fxrys,{
	fxrysCbox: function(containerSelector, fieldname, orgId, options) {
		var me = this;
		options = $.extend({
			allowBlank : true,
			allowSearch : true,
			emptyText : '请选择',
			defValue : '',
			multiSelected : false
		}, options || {});
		var getData = function(){
			var result = [];
			$.ajax({
				type : "GET",
				url : CONTEXT_PATH+"/data/jzgl/rcbdgl/fxrysJson.action",
				async : false,
				dataType : "json",
				success : function(data) {
					result = data[orgId];
				}
			});
			return result;
		};
		var id = fieldname + '-field';
		var fieldSelector = $('<input type="hidden" name="' + fieldname
				+ '"/>');
		$(containerSelector).append(fieldSelector);
		$(fieldSelector).addAttrAndCls(options);//设置占位控件的样式 夏先智
		var data = getData();
		$.initCodeComboCustom($(fieldSelector), data, $.extend({
			displayField : 'name',
			valueField : 'code'
		}, options), 'local');
	}
});
})(jQuery);