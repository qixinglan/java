(function ($) {
"use strict";
$.fields = $.fields || {};
$.extend($.fields,{
	address: function(selector, options){
		var prefix = options.name || $.fields.random('address');
		var idName = prefix + '.id';
		var fulltextName = prefix + '.fulltext';
		var provinceName = prefix + '.province';
		var cityName = prefix + '.city';
		var countyName = prefix + '.county';
		var townName = prefix + '.town';
		var detailName = prefix + '.detail';
		var commonConfig = {
				formatSearching: null,
				type:'select',
				tdCls: 'address-td-value',
				displayField: 'name',
				valueField: 'code',	
				allowBlank: true,
				allowSearch: true,
				emptyText:'请选择',
				required: true
		};
		var url = CONTEXT_PATH + '/data/jzgl/dagl/moreinfo/selectAddress.action';
		var getData = function(pid, level){
			var result = [];
			if (pid){
				$.ajax({
		            type: "GET",
		            url: url + '?pid=' + pid + '&level=' + level,
		            async: false,
		            dataType: "json",
		            success: function(data){
		            	result = data;
		            }
		        });
			}
			return result;
		};
		$.container.generate(selector, {
			type: 'row',
			fieldCols:'8',
			fieldCls:{
				tableCls:'address-table',
				tdCls: 'address-td',
				trCls: 'address-tr',
				labelCls:'form-td-label form-td-label-80 address-label',
				valueCls: 'address-value'
			},
			fields:[
			    {type:'hidden', name: idName}, 
			    {type:'hidden', name: fulltextName, getValue: function(){
			    	//此处拼凑该字段值
			    	var provinceId = $('input[name="' + provinceName + '"]', $(selector)).val();
					var cityId = $('input[name="' + cityName + '"]', $(selector)).val();
					var countyId = $('input[name="' + countyName + '"]', $(selector)).val();
					var townId = $('input[name="' + townName + '"]', $(selector)).val();
					var fulltext = '';
					var provinceText;
					if (provinceId){
						provinceText = $('.select2-choice', $('input[name="' + provinceName + '"]', $(selector)).parent()).data('select2-data').name;
						fulltext += provinceText;
					}
					if (cityId){
						var cityText = $('.select2-choice', $('input[name="' + cityName + '"]', $(selector)).parent()).data('select2-data').name;
						if (provinceText != cityText){
							fulltext += cityText;
						}
					}
					if (countyId){
						fulltext += $('.select2-choice', $('input[name="' + countyName + '"]', $(selector)).parent()).data('select2-data').name;
					}
					if (townId){
						fulltext += $('.select2-choice', $('input[name="' + townName + '"]', $(selector)).parent()).data('select2-data').name;
					}
					fulltext += $.trim($('input[name="' + detailName + '"]', $(selector)).val());
					return fulltext || '不详';
			    }}, 
				$.extend({
					name: provinceName,
					data: function(){
						var data = getData(40, 1);
						return data;
					},
					valuechange: function(){
						$('input[name="' + cityName + '"]', $(selector)).val('').trigger('valuechange');
					}
				}, commonConfig),
				{text:'省', tdCls: 'address-td-label'},
				$.extend({
					name: cityName,
					data: function(){
						var pid = $('input[name="' + provinceName + '"]', $(selector)).val();
						var data = getData(pid, 2);
						return data;
					},
					valuechange: function(){
						$('input[name="' + countyName + '"]', $(selector)).val('').trigger('valuechange');
					}
				}, commonConfig),
		        {text:'市', tdCls: 'address-td-label'},
		        $.extend({
					name: countyName,
					data: function(){
						var pid = $('input[name="' + cityName + '"]', $(selector)).val();
						var data = getData(pid, 3);
						return data;
					},
					valuechange: function(){
						$('input[name="' + townName + '"]', $(selector)).val('').trigger('valuechange');
					}
				}, commonConfig),
		        {text:'县（市、区）', tdCls: 'address-td-label', tdAttr:{nowrap: true}},
		        $.extend({
					name: townName,
					data: function(){
						var pid = $('input[name="' + countyName + '"]', $(selector)).val();
						var data = getData(pid, 4);
						return data;
					}
				}, commonConfig),
		        {text:'乡镇（街道）', tdCls: 'address-td-label', tdAttr:{nowrap: true}},
		        {text:'详细门牌号', type:'text', colspan: 8, name: detailName}
	        ],
		});
	}
});
})(jQuery);