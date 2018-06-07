(function ($) {
"use strict";
$.dictionary = $.dictionary || {};
$.extend($.dictionary,{
	load: function(url){
		var me = this;
		me.dict = {};
		$.ajax({
            type: "GET",
            url: url || "/data/dictionary/json.action",
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
	getCode: function(code,name){
		var me = this;
		var dict = me.getDict(code);
		for(var i in dict){
			 if(dict[i].name==name){
				 return dict[i].code;
			 }
		};
		return "undefined";
	},
	formatter: function(code, emptyText){
		var me = this;
		me.formatters = me.formatters || {};
		if (me.formatters[code]){
			return me.formatters[code];
		}
		else{
			var dict = me.getDict(code);
			if (dict){
				var map = {};
				for (var j in dict){
					var item = dict[j];
					map[item.code] = item.name;
				}
				var fn = function(cellvalue){
					if(cellvalue==null){
						return emptyText || '';
					}
					cellvalue = cellvalue+"";
					var values = cellvalue.split(',');
					if (values.length > 1){
						var str = [];
						for (var i in values){
							var val = map[values[i]];
							if (val){
								str.push(val);
							}
						}
						return str.join(',');
					}
					else{
						if (map[cellvalue]){
							return map[cellvalue];
						}
					}
					return emptyText || '';
				};
				me.formatters[code] = fn;
				return fn;
			}
			else{
				return function(cellvalue){
					return cellvalue;
				};
			}
		}
	},
	filter: function(data, filter){
		if (!$.isArray(filter)){
			filter = filter.split(',');
		}
		return $.grep(data, function(item){
			return $.inArray(item.code, filter) < 0;
		});
	},
	//containerSelector,输入框父容器，多个输入使用多个div分隔. fieldname, 表单字段名称. code, 数据字典代码
	//options选项,allowBlank允许为空,emptyText为空时显示,defValue默认值,zIndex显示层次，浮动DIV使用
	//filter数据字典过滤，可以为数组，字符串或函数，如果为字符串, 将使用','分割为数组；如果为函数，参数为原始值数组，返回值必须为数组
	combobox: function(containerSelector, fieldname, code, options){
		var me = this;
		options = $.extend({
			allowBlank: true,
			allowSearch: false,//允许搜索
			emptyText: '全部',
			defValue: '',
			multiSelected: false
		}, options || {});
		var id = fieldname + '-field';
		var fieldSelector = $('<input type="hidden" name="' + fieldname + '"/>');
		$(containerSelector).append(fieldSelector);
		var dict = me.getDict(code, true);
		var data = dict || [];
		if (options.filter){
			if ($.isFunction(options.filter)){
				data = options.filter.call(data);
			}
			else{
				data = me.filter(data, options.filter);
			}
		}
		if (!options.defValue && options.allowBlank == false && data.length > 0){
			options.defValue = data[0].code;
		}
		$(fieldSelector).addAttrAndCls(options);
		$.initCodeComboCustom($(fieldSelector), data,$.extend({
		    displayField: 'name',
		    valueField: 'code'
		}, options), 'local');
	},
	
	//eg: $.dictionary.radio("#sbxh", "sbxh", "sbxh", {allowBlank: true,emptyText:'全部',defValue:'04'});
	//valuechange
	radio: function(containerSelector, fieldname, code, options){
		var me = this;
		options = $.extend({
			allowBlank: false,
			emptyText: '未知',
			defValue: '',
			fieldClass: '',
			border: true,
			valuechange: null
		}, options || {});
		var divContainer = $('<div></div');
		$(containerSelector).append(divContainer);
		divContainer.addClass('dict-field');
		divContainer.addClass(options.fieldClass);
		if (options.border){
			divContainer.addClass('dict-field-bordered');
		}
		var dict = me.getDict(code, true);
		var data = dict || [];
		if (options.filter){
			if ($.isFunction(options.filter)){
				data = options.filter.call(data);
			}
			else{
				data = me.filter(data, options.filter);
			}
		}
		if (options.allowBlank){
			data.push({code: '', name: options.emptyText});
		}
		for (var i in data){
			var item = data[i];
			var elem = $('<input name="' + fieldname + '" class="dict-field-radio" type="radio" value="' + item.code + '">' + item.name + '</input>');
			if (options.readonly || options.disabled){
				elem.attr('disabled', true);
			}
			if (options.valuechange){
				var callback = function(value){
					options.valuechange.apply(this, [value]);
				};
				elem.click(function(){
					callback.apply(this, [$(this).val()]);
				});
				elem.bind('valuechange', function(){
					if ($(this).attr('checked') == 'checked'){
						callback.apply(this, [$(this).val()]);
					}
				});
			}
			divContainer.append(elem);
		}
		
		var value = '';
		if (options.defValue){
			value = options.defValue;
		}
		else if (options.allowBlank === false && data.length > 0){
			value = data[0].code;
		}
		var radios = $('input[name="' + fieldname + '"][value="' + value + '"]', $(divContainer)); 
		$(radios).attr('checked', true);
	},
	checkbox: function(containerSelector, fieldname, code, options){
		var me = this;
		options = $.extend({
			allowBlank: true,
			defValue: '',
			fieldClass: ''
		}, options || {});
		var cls = 'dict-field-bordered ' +  options.fieldClass;
		var divContainer = $('<div class="dict-field-bordered ' + options.fieldClass + '"></div');
		$(containerSelector).append(divContainer);
		var dict = me.getDict(code, true);
		var data = dict || [];
		var hiddenInput = $('<input type="text" name="' + fieldname + '"/>');
		divContainer.append(hiddenInput);
		hiddenInput.css('display', 'none');
		var checkboxs = divSelector + ' input[type="checkbox"]'; 
		for (var i in data){
			var item = data[i];
			var elem = $('<input class="dict-field-checkbox" type="checkbox" value="' + item.code + '">' + item.name + '</input>');
			if (options.readonly || options.disabled){
				elem.attr('disabled', true);
			}
			divContainer.append(elem);
			elem.click(function(){
				var newValue = '';
				$.each($(checkboxs), function(){
					var item = this;
					if (item.checked == true){
						if (newValue != ''){
							newValue += ',';
						}
						newValue += item.value;
					}
				});
				hiddenInput.val(newValue);
			});
		}
		if (options.defValue){
			var values = options.defValue.split(',');
			for (var j in values){
				var value = values[j].trim();
				$('input[type="checkbox"][value="' + value +'"]', divContainer).attr('checked', true);
			}
		}
	}
});
$.dictionary.load(CONTEXT_PATH + "/data/dictionary/json.action");

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