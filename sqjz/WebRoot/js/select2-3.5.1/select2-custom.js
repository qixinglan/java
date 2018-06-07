(function ($) {
"use strict";
var realSelect2 = $.fn.select2;
var copyToIdText = function(data, displayField, valueField, deepcopy){
	var result = {data: data};
	if (deepcopy){
		result = $.extend(true, result);//深拷贝
	}
	var copied = result.data;
	var handler = function(array){
		for (var i = 0; i < array.length; ++i){
			var item = array[i];
			item.id = item[valueField];
			item.text = item[displayField];
			if (item.children && item.children.length > 0){
				handler(item.children);
			}
		}
	}
	handler(copied || []);
	return copied;
};
//select2默认值字段为id,显示字段为text,支持显示字段重定义,但不支持值字段自定义
//本扩展允许使用valueField重定义值字段,displayField重定义显示字段
//allowPinYin允许拼音搜索
//如果没有重定义需求，请直接使用select2
//deepcopy是否拷贝数据
$.fn.select2Proxy = function(opts){
	opts = $.extend({
		maximumInputLength: 20,
		closeOnSelect: !(opts.multiple == true),
		searchInputPlaceholder: opts.multiple ? '' : '汉字/简拼',
		matcher: function(term, text, opt) {
			var term = term.toUpperCase();
			var text = text.toUpperCase();
			if (text.indexOf(term) >= 0){
				return true;
			}
			if (opts.allowPinYin !== false){
				return $.pinyin.isFirstMatch(term, text);
			}
			return false;
		}
	}, opts || {});
	
	if (opts.valueField && opts.data){//自定义值字段,如果使用本地数据
		if ($.isFunction(opts.data)){
			var getData = opts.data;
			opts.data = function(){
				var data = getData.apply(this, arguments);
				data = copyToIdText(data || [], opts.displayField || 'text', opts.valueField, opts.deepcopy);
				return { results: data, text: 'text' };
			}
		}
		else{
			if ($.isArray(opts.data)){
				opts.data = { results: opts.data, text: 'text' };
			}
			opts.data.results = copyToIdText(opts.data.results || [], opts.displayField || opts.data.text, opts.valueField, opts.deepcopy);
		}
	}
	if (opts.valueField && opts.ajax){//使用远程数据
		var userProcess = opts.ajax.results;
		opts.ajax.results = function(remoteData, pageNumber, query){
			var data = {results: remoteData, more: false};
			if ($.isFunction(userProcess)){
				data = userProcess(remoteData, pageNumber, query);
			}
			data.results = copyToIdText(data.results || [], opts.displayField || 'text', opts.valueField, opts.deepcopy);
			return data;
		};
	}
	realSelect2.call(this, opts);
	if(opts.selectAll&&opts.multiSelected){
		$("input[name='"+opts.name+"']").bind('change', function(){
			var data=$("input[name='"+opts.name+"']").select2('data');
			if(data.length==0){
				$("abbr[name='selectAll"+opts.name+"']").css("display","block");
				$("abbr[name='unSelectAll"+opts.name+"']").css("display","none");
			}else{
				if(opts.data.results){
					if(data.length==opts.data.results.length){
						$("abbr[name='selectAll"+opts.name+"']").css("display","none");
						$("abbr[name='unSelectAll"+opts.name+"']").css("display","block");
					}else{
						$("abbr[name='selectAll"+opts.name+"']").css("display","block");
						$("abbr[name='unSelectAll"+opts.name+"']").css("display","none");
					}
				}else{
					if(data.length==opts.data.length){
						$("abbr[name='selectAll"+opts.name+"']").css("display","none");
						$("abbr[name='unSelectAll"+opts.name+"']").css("display","block");
					}else{
						$("abbr[name='selectAll"+opts.name+"']").css("display","block");
						$("abbr[name='unSelectAll"+opts.name+"']").css("display","none");
					}
				}	
			}
		});
		$(".select2-container.select2-container-multi", this.parent()).append("<abbr name='selectAll"+opts.name+"' class='select2-abbr-button' style='color:#fff;background:#49A9E3'>全选</abbr><abbr name='unSelectAll"+opts.name+"' class='select2-abbr-button' style='display:none;color:#fff;background:#49A9E3'>全不选</abbr>");
		var  bind=function (func) {
            var self = this;
            return function () {
                func.apply(self, arguments);
            };
        }
		$(".select2-container.select2-container-multi", this.parent()).on("mousedown touchstart", "abbr",bind(function (e) {
    		$("input[name='"+opts.name+"']").select2('val','');
    		if("selectAll"+opts.name==$(e.target).attr("name")){
    			var data=[];
    			if ($.isFunction(opts.data)){
    				var getData = opts.data;
    				data = getData.apply(this, arguments);
    			}else{
    				data=opts.data;
    			}
    			$("input[name='"+opts.name+"']").select2('data',data.results);
        		$("abbr[name='unSelectAll"+opts.name+"']").css("display","block");
    		}else{
        		$("abbr[name='selectAll"+opts.name+"']").css("display","block");
    		}
    		$(e.target).css("display","none");
    	}));
	}
};
})(jQuery);