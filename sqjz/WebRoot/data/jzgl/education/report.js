(function ($) {
"use strict";
$.report = $.report || {};
$.extend($.report,{
	init: function(action, options){
		var me = this;
		me[action].call(me);
	},
	bindSearchForm: function(form, searchBtn, resetBtn,options){
		var grid = this;
		grid.searchFormFields = [];
		$.each($('input', $(form)), function(i, n){
			var item = $(n);
			if (item.attr('name') && item.attr('searchType')){
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
				options.data="filters="+ruleGroup;
				if($.fields.validateForm("#search")){
					$.ajax(options);
				}
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
				if (searchBtn){
					options.data="filters=";
					$(searchBtn).click();
				}
				else{
					options.data="filters=";
					$.ajax(options);
				}
			});
		}
	},
	insertField : function(tr, field){
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
				required:field.required
			},
			name : field.name,
			fieldClass: 'in' == field.searchType ? 'search-form-field-half' : 'search-form-field'
		}, field.formatoptions || {});
		fieldAttr = $.extend(fieldAttr, field.searchOption || {});
		if ('in' == field.searchType){
			fieldAttr.attr.searchType = 'ge';
			fieldAttr.attr.id="s"+field.name;
			fieldAttr.attr.lable="开始时间";
			fieldAttr.maxdate={type:'id',data:"e"+field.name};
			fieldAttr.defValue=field.miValue;
			maker(td, fieldAttr);
			td.append('至');
			fieldAttr.attr.searchType = 'le';
			fieldAttr.attr.id="e"+field.name;
			fieldAttr.attr.lable="结束时间";
			fieldAttr.defValue=field.mValue;
			fieldAttr.maxdate={type:'other',data:'$'};
			fieldAttr.mindate={type:'id',data:"s"+field.name};
			maker(td, fieldAttr);
		}
		else{
			maker(td, fieldAttr);
		}
	},
	addSearchForm : function(elem,celModel,options){
		var fields = [];
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
				if (j % 3 == 0){
					var tr = $('<tr></tr>');
					trs.push(tr);
					table.append(tr);
				}
				this.insertField(tr, fields[j]);
			}
			var j = fields.length % 3;
			j = j ? (3 - j) : 0;//如果不是3的倍数，则需要补齐
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
			this.bindSearchForm('#' + formId, '#' + searchBtnId, '#' + resetBtnId,options);
		}
	},
	fusioncharts:function(opts){
		var option = $.extend({
			width:'100%',
			height:'450',
			renderAt:'',
			dataFormat:'json',
			dataSource:{
				chart:{
	        		caption:opts.title||"",
	        		formatNumberScale:false,
	        	},
	        	data:opts.data,
	        	categories:opts.categories,
	        	dataset:opts.dataset
			}
		}, opts || {});
		$("#"+opts.renderAt).children().remove();
		FusionCharts.ready(function () {
		    var tempVsSalesChart = new FusionCharts(option);
		    tempVsSalesChart.render();
		});
	},
	education:function(){
		var me=this;
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 1,fields:[{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		var dataset = [];
		var categories=[];
		var options = {
			   type: 'POST',
			   title:"集中初始教育数据统计图",
			   async:true,
			   url: CONTEXT_PATH+'/data/jzgl/cxtj/sjfx/'+'educationReport.action',
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   if (data){
					   if(data.category){
						   categories=[{category:data.category}];
					   }
					   if(data.dataset){
						   dataset=data.dataset;
					   }
				   }
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   //stackedcolumn3d、mscolumn3d
				   me.fusioncharts({type: 'mscolumn3d',renderAt:'chart-container',title:"集中初始教育数据统计图",categories:categories,dataset:dataset});
			   }
		};
		var date=new Date();
		var month=date.getMonth();
		if(month<10){
			month="0"+month;
		}
		var temp = month-1;
		if(temp<10){
			temp="0"+temp;
		}
		var celModel = [ {
			name : 'time',
			label : '统计时间',
			searchType : 'in',
			searchInput : 'yearmonth',
			required:true,
			mValue:date.getFullYear()+"-"+month,
			miValue:(date.getFullYear()-1)+"-"+temp
		}];
		this.addSearchForm("#search", celModel,options);
		$("#search_searchBtn").click();
		$("#search_resetBtn").unbind("click");
		$("#search_resetBtn").click(function(){
			$("#stime").val(date.getFullYear()-1+"-"+temp);
			$("#etime").val(date.getFullYear()+"-"+month);
			$("#search_searchBtn").click();
		});
	}
});
})(jQuery);