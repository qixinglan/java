(function ($) {
"use strict";


//修复IE下数组无indexOf方法的问题
$.fn.indexOf = function(array, rt, start){
	var start = Number(start) || 0;
	start = (start < 0) ? Math.ceil(start) : Math.floor(start);
	start += (start < 0) ? array.length : 0;
	for (; start < array.length; ++start){
		if (array[start] == rt){
			return start;
		}
	}
	return -1;
};

$.fn.addAttrAndCls = function(options){
	this.addClass(options.fieldClass);
	for (var name in options.attr){
		this.attr(name, options.attr[name]);
	}
	for (var name in options){
		this.data(name, options[name]);
	}
};

$.fn.setRequired = function(required, emptyValue){
	$(this).attr('required', required);
	var $td = $(this).parents('.form-table-td');
	var $label = $('.form-td-label', $td);
	if (!required){
		//如果非必须，清空值
		$(this).val(emptyValue || '');
		$(this).trigger('valuechange', emptyValue || '');
		$label.removeClass('field-required-label');
	}
	else{
		$label.addClass('field-required-label');
	}
}

$.fn.readonly = function(options){
	var inputs = $('input', $(this));
	$.each(inputs, function(i, elem){
		$(elem).attr('readonly', true);
	});
};
$.fn.fmatter.days = function(cellval, opts) {
	if (cellval == null){
		return '-';
	}
	if (cellval == 0){
		return '<font color="red">今天</font>';
	}
	var cls = cellval > 0 ? 'positive-date' : 'negative-date';
	var prefix = cellval > 0 ? '' : '<span class="negative-sign">逾期</span>';
	cellval = cellval > 0 ? cellval : -cellval;
	var day = cellval % 30;
	var remain = (cellval - day) / 30;
	var month = remain % 12;
	var year = (remain - month) / 12;
	var str = prefix;
	if (year > 0){
		str += year + "年";
	}
	if (month > 0){
		str += month + "月";
	}
	if (day > 0){
		str += day + "天";
	}
	return  '<span class="' + cls + '">' + str + '</span>';
};

$.fn.fmatter.empty = function(cellval, opts) {
	opts = opts || {};
	return cellval ? cellval : (opts.emptyText || '');
}

$.dialog.loadmask = function(content, parent)
{
	return $.dialog({
		title: '提示',
		id: 'Alert',
		zIndex: $.dialog.setting.zIndex,
		icon: 'alert.png',
		fixed: true,
		lock: true,
		cancel: false,
		content: content,
		esc: false,
		width:250,
		resize: false,
		parent: parent || null
	});
};

Date.prototype.format = function(format){
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"S": this.getMilliseconds()
	};
	if (/(y+)/.test(format)){
		format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	}
	for (var k in o){
		if (new RegExp('(' + k + ')').test(format)){
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
		}
	}
	return format;
}

$.date = {};

$.date.today = function(){
	return new Date().format('yyyy-MM-dd');
}

$.date.now = function(){
	return new Date().format('yyyy-MM-dd hh:mm:ss');
}

$.date.timestamp = function(){
	return new Date().format('yyyy-MM-dd hh:mm:ss.S');
}

})(jQuery);