(function ($) {
"use strict";
$.validater = $.validater || {};
$.extend($.validater,{
	//验证器,支持正则和自定义函数验证,其它通用或标准型验证器可以放置到此处
	//返回假值表示验证通过，否则返回验证错误消息
	regex: function(expect, value){//正则表达式匹配,expect为js正则
		if (expect){
			return expect.test(value) ? '' : '格式错误';
		}
		return false;
	},
	self: function(expect, value){//自定义回调函数匹配,
		return expect(value);
	},
	name: function(expect, value){//任务姓名，地名，单位名称等其它实体名称,只允许输入中文,英文，空格，英文圆点
		var regex = /^[\u4E00-\u9FA5a-zA-Z .0-9]+$/;
		return regex.test(value) ? '' : '只能输入中文、字母 、数字、空格或英文圆点 (.)';
	},
	code: function(expect, value){
		var regex = /^[a-zA-Z._\-0-9]+$/;
		return regex.test(value) ? '' : '只能输入字母 、数字或符号 (_.-)';
	},
	phone: function(expect, value){
		var regex = /^[0-9-]+$/;
		return regex.test(value) ? '' : '电话号码只能输入数字和横线(-)';
	},
	idcard: function(expect, value){
		var regex = /^((\d{15})|(^\d{17}(\d|[Xx])))$/;
		return regex.test(value) ? '' : '必须输入15位或18位身份证号码';
	},
	date: function(expect, value){
		var regex = /^\d{4}-\d{2}-\d{2}$/;
		return regex.test(value) ? '' : '日期必须为2000-01-01的格式';
	},
	yearmonth: function(expect, value){
		var regex = /^\d{4}-\d{2}$/;
		return regex.test(value) ? '' : '日期必须为2000-01的格式';
	},
	datetime: function(expect, value){
		var regex = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/;
		return regex.test(value) ? '' : '时间必须为2000-01-01 12:00的格式';
	},
	fulldate: function(expect, value){
		var regex = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:([0-5][0-9])$/;
		return regex.test(value) ? '' : '时间必须为2000-01-01 12:00的格式';
	},
	int: function(expect, value){
		var re = /^\d+$/g;
		return re.test(value)?'':'只能输入数字';
	},
	time: function(expect, value){
		var regex = /^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$/;
		return regex.test(value) ? '' : '时间必须为12:00的格式，范围从0:00-23:59';
	},
	telephone: function(expect, value){
		var regex = /^((\d{11}))$/;
		return regex.test(value) ? '' : '必须输入11位移动电话号码';
	},
	ip:function(expect,value){
		var regex = /^((25[0-5]|2[0-4]\d|1?\d?\d)\.){3}(25[0-5]|2[0-4]\d|1?\d?\d)$/;
		return regex.test(value)?'':'必须正确填写IP';
	}
});
})(jQuery);