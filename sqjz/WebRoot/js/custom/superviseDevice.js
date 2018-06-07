/*
 * 使用方式
 * 1.先引入jQuery
 * 2.在需要的页面引入superviseDevice.js
 * 3.在jsp的<script>中加入 $.equip.load("${ctx}/data/dzjg/jgsb/equipableMachine.action");
 * 4.在初始化方法中加入$.equip.equipableDevice(selector,name,options);
 */
//本js用于获得可用设备
(function($) {
	"use strict";
	$.equip = $.equip || {};
	$.extend($.equip, {
		load: function(options){
			var result = [];
			$.ajax({
				type : "GET",
				cache: false,
				//url : options.url || CONTEXT_PATH + "/data/dzjg/jgsb/equipableMachine.action",
				url : options.url || CONTEXT_PATH + "/data/sbgl/jgsb/equipableMachine.action",
				async : false,
				dataType : "json",
				success : function(data) {
					result = data;
				}
			});
			return result;
		},
		// containerSelector,输入框父容器，多个输入使用多个div分隔. fieldname, 表单字段名称. 
		// options选项,allowBlank允许为空,emptyText为空时显示,defValue默认值,
		equipableDevice : function( containerSelector, fieldname, options) {
			$.equip.displaly(containerSelector, fieldname, options);
		},
		displaly : function(containerSelector, fieldname, options){
			var data = $.equip.load(options) || [];
			options = $.extend({
				allowBlank : true,
				emptyText : '无',
				defValue : '',
				zIndex : 2
			}, options || {},{multiSelected : false});
			var fieldSelector = $('<input type="hidden" name="' + fieldname
					+ '"/>');
			$(containerSelector).append(fieldSelector);
			var data = data || [];
			if (!options.defValue && options.allowBlank === false && data.length > 0) {
				options.defValue = data[0].code;
			}
			$(fieldSelector).addAttrAndCls(options);//拷贝样式和属性到显示字段 夏先智
			$.initCodeComboCustom($(fieldSelector), data,$.extend(
					{
						displayField : 'deviceNumber',
						valueField : 'deviceNumber',
					}, options), 'local');
			return true;
		}
	});
})(jQuery);
