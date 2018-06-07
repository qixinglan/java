(function ($) {
"use strict";
$.findSim = $.findSim||{};
$.extend($.findSim,{
	load: function(url){
		var me = this;
		me.dict = {};
		$.ajax({
            type: "GET",
            url:url||"findSim.action?useUnit=181",//默认第一个司法局为顺义司法局=181
            async: false,
            dataType: "json",
            success: function(data){
            	me.dict = data;
            }
        });
	},
	getDict: function(code, filter){
		filter = filter || false;
		var me = this;
		var data = [];
		if (me.dict[code]){
			data = me.dict[code].slice();
		}
		if (filter){
			var filterd = [];
			for (var i in data){
				if (data[i].using != false){
					filterd.push(data[i]);
				}
			}
			return filterd;
		}
		return data;
	},
	
	//containerSelector,输入框父容器，多个输入使用多个div分隔. fieldname, 表单字段名称. code, 数据字典代码
	//options选项,allowBlank允许为空,emptyText为空时显示,defValue默认值,zIndex显示层次，浮动DIV使用
	//filter数据字典过滤，可以为数组，字符串或函数，如果为字符串, 将使用','分割为数组；如果为函数，参数为原始值数组，返回值必须为数组
	combobox: function(containerSelector, fieldname, code, options){
		var me = this;
		options = $.extend({
			allowBlank: true,
			allowSearch: true,//允许搜索
			emptyText: '全部',
			defValue: '',
			multiSelected: false
		}, options || {});
		var id = fieldname + '-field';
		var fieldSelector = $('<input type="hidden" required="required" name="' + fieldname + '"/>');
		$(containerSelector).append(fieldSelector);
		var dict = me.getDict(code);
		var data = dict || [];
		if (!options.defValue && options.allowBlank == false && data.length > 0){
			options.defValue = data[0].code;
		}
		$(fieldSelector).addAttrAndCls(options);
		$.initCodeComboCustom($(fieldSelector), data,$.extend({
		    displayField: 'name',
		    valueField: 'code'
		}, options), 'local');
	}
});
$.findSim.load(CONTEXT_PATH + "/data/sbgl/jgsb/findSim.action?useUnit=181");//默认第一个司法局为顺义司法局=181
$.fn.fmatter = $.fn.fmatter || {};
$.fn.fmatter.dictionary = function(cellval, opts) {
	if (opts.colModel && opts.colModel.formatoptions && opts.colModel.formatoptions.code){
		var fn = $.dictionary.formatter(opts.colModel.formatoptions.code, opts.colModel.formatoptions.emptyText);
		return fn(cellval);
	}
	if (opts.code){
		var fn = $.dictionary.formatter(opts.code, opts.emptyText);
		return fn(cellval);
	}
	return cellval;
};
})(jQuery);