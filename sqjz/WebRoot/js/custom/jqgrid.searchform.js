/**
 * 
 */
(function ($) {
"use strict";
$.jgrid.extend({
	bindSearchForm: function(form, searchBtn, resetBtn){
		var grid = this;
		grid.searchFormFields = [];
		$.each($('input', $(form)), function(i, n){
			var item = $(n);
			if (item.attr('name') && item.attr('searchType')&& 'true' != item.attr('nosearch')){
				grid.searchFormFields.push({
					elem: item,
					op: item.attr('searchType'),
					name: item.attr('name')
				});
			}
		});
		if (searchBtn){
			$(searchBtn).click(function(){
				var ruleGroup = "{\"groupOp\":\"AND\",\"rules\":[";
				var gi=0;
				$.each(grid.searchFormFields, function(i, item){
					var value =  item.elem.val();
					if (value){
						if (gi > 0) {
							ruleGroup += ",";
						}
						ruleGroup += "{\"field\":\"" + item.name + "\",";
						ruleGroup += "\"op\":\"" + item.op + "\",";
						ruleGroup += "\"data\":\"" + value.replace(/\\/g,'\\\\').replace(/\"/g,'\\"') + "\"}";
						gi++;
					}
				});
				ruleGroup += "]}";
				grid.getGridParam('postData').filters = ruleGroup;
				grid.trigger('reloadGrid',[{page:1}]);
			});
		}
		if (resetBtn){
			$(resetBtn).click(function(){
				$.each($(form + ' input'), function(i, n){
					var item = $(n);
					if (item.attr('name') && item.attr('searchType') && 'true' != item.attr('noreset')){
						item.val('').trigger('valuechange');
					}
				});
				//所有下拉列表-已废弃
				//$("a.clear_triggerIcon", $(form)).click();
				grid.getGridParam('postData').filters = '';
				if (searchBtn){
					$(searchBtn).click();
				}
				else{
					grid.trigger('reloadGrid',[{page:1}]);
				}
			});
		}
	},
	//name, label, searchType, formatter, searchOption附加搜索参数（也可配置到formatoptions）,
	//searchInput指定输入框类型（如果为空，则根据formatter决定）
	addSearchForm : function(elem,options){
		var grid = this;
		var celModel = grid.getGridParam('colModel');
		var colNames = grid.getGridParam('colNames');
		var fields = [];
		options = options||{};
		var rows = options.rows || 3;
		for (var i in celModel){
			var model = celModel[i];
			if (model.searchType){
				model.label = model.label ? model.label : colNames[i];
				fields.push(model);
			}
		}
		if (fields.length > 0){
			var idPrefix = elem.replace('#', '');
			var formId = idPrefix + '_form';
			var searchBtnId = idPrefix + '_searchBtn';
			var resetBtnId = idPrefix + '_resetBtn';
			var formContainer = $('<div><form id="' + formId + '"><table class="search-table"></table></form></div>');
			$(elem).addClass("search-container");
			$(elem).append(formContainer);
			formContainer.addClass("search-form-container");
			var table = $('#' + formId + ' table[class="search-table"]');
			var line = 0;
			var trs = [];
			for (var j in fields){
				if (j % rows == 0){
					var tr = $('<tr></tr>');
					trs.push(tr);
					table.append(tr);
				}
				grid.insertField(tr, fields[j]);
			}
			var j = fields.length % rows;
			j = j ? (rows - j) : 0;//如果不是3的倍数，则需要补齐
			for (var k = 0; k < j;){
				tr.append('<td></td><td></td>');
				k += 1;
			}
			if (trs.length > 0){
				trs[0].append('<td class="search-btns-container"><input id="' + searchBtnId + '" type="button" class="bttn bicon-search"  value="查询"/></td>');
				trs[trs.length - 1].append('<td class="search-btns-container"><input id="' + resetBtnId + '" type="button" class="bttn bicon-reset"  value="重置"/></td>');
				for (var i = 1; i < trs.length - 1;){
					trs[i].append('<td class="search-btns-container"></td>');
					i += 1;
				}
			}
			grid.bindSearchForm('#' + formId, '#' + searchBtnId, '#' + resetBtnId);
		}
		return grid;
	},
	insertField : function(tr, field){
		var grid = this;
		var makers = {//简单输入，与formatter兼容
			integer : $.fields.integer,
			number : $.fields.number,
			date : $.fields.datepicker,
			time : $.fields.timepicker,
			yearmonth : $.fields.yearmonthpicker,
			dictionary : $.fields.dict_combobox,
			organization: $.fields.org_combobox,
			select:$.fields.select
		};
		var maker = makers[field.searchInput || field.formatter];
		if (!maker){//匹配searchInput指定的输入框类型
			maker = $.fields[field.searchInput];
		}
		if (!maker){
			maker = $.fields.text;
		}
		tr.append('<td class="search-form-label" nowrap="nowrap">' + field.label + '</td>');
		var td = $('<td class="search-form-field-container" nowrap="nowrap"/>');
		tr.append(td);
		var fieldAttr = $.extend({
			attr:{
				maxLength: 30,
				name : field.name,
				searchType : field.searchType,
				nosearch:field.nosearch
			},
			name : field.name,
			fieldClass: 'in' == field.searchType ? 'search-form-field-half' : 'search-form-field'
		}, field.formatoptions || {});
		fieldAttr = $.extend(fieldAttr, field.searchOption || {});
		if ('in' == field.searchType){
			fieldAttr.attr.searchType = 'ge';
			maker(td, fieldAttr);
			td.append('至');
			fieldAttr.attr.searchType = 'le';
			maker(td, fieldAttr);
		}
		else{
			maker(td, fieldAttr);
		}
	}
});
})(jQuery);