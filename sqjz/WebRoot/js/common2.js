/*菜单效果*/
var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;

function jsddm_open()
{
	
jsddm_canceltimer();
	jsddm_close();
	
	ddmenuitem = $(this).find('ul').eq(0).css('display', 'block');}

function jsddm_close()
{	if(ddmenuitem) ddmenuitem.css('display', 'none');}

function jsddm_timer()
{	closetimer = window.setTimeout(jsddm_close, timeout);}

function jsddm_canceltimer()
{	if(closetimer)
	{	window.clearTimeout(closetimer);
		closetimer = null;}}

$(document).ready(function()
{	$('#menu > li').bind('mouseover', jsddm_open);
	$('#menu > li').bind('mouseout',  jsddm_timer);
	$(".menu-submenu").mouseover(function(){
			$(this).find('ul').eq(0).css('display', 'block');
		})
	$(".menu-submenu").mouseout(function(){
			$(this).find('ul').eq(0).css('display', 'none');
		})
	});

document.onclick = jsddm_close;
/*详情页tab效果*/
$(function(){
	$(".ul-menu li").click(function(){
		$(".ul-menu li").each(function(index, element) {
			$(this).removeClass("li-selected");
		});
		$(this).addClass("li-selected");
	})	
})
/*获取当前页面传递的参数*/

function getLocationParam() {
	var search = "";
	if (arguments.length == 0) {
		search = location.search.substring(1);
	} else {
		search = arguments[0].search.substring(1);
	}
	var params = search.split("&");
	var paramObj = {};
	for (var i = 0, i_len = params.length; i < i_len; i++) {
		var strArr = params[i].split("=");
		paramObj[strArr[0]] = strArr[1];
	}
	return paramObj;
}


jQuery(document).ready(function() {
	/***************************************************************************
	 * 功能说明：手动初始化下拉码表方法
	 * 
	 * @author xiajb
	 * 
	 * @param target
	 *            需要初始化下拉码表的元素JQuery对象
	 * @param data
	 *            初始化数据
	 * @param type
	 *            数据类型，"local":data需要为JSON数组,"code":data为一码表类型,如果为其他值或为空：data为一URL请求地址
	 *            例子： type:code,$.initCodeComboCustom($("#xx"),"BXDM","code");
	 *            type:local,$.initCodeComboCustom($("#xx"),[{"code":"01","codeDesc":"XX1"},{"code":"02","codeDesc":"XX2"}],"local");
	 *            type:code,$.initCodeComboCustom($("#xx"),$.ctx +
	 *            "/system!listCode.action?codeClass=BXDM");
	 * 
	 **************************************************************************/
	$.initCodeComboCustom = function(target, data, options, type) {
		options = options || {};
		if (type == 'remote'){
			options.ajax = data;
		}
		else{
			options.data = data;
		}
		delete options.id;
		$(target).select2Proxy($.extend({
		    displayField: 'code',
		    valueField: 'codeDesc',
		    placeholder: options.emptyText,
		    allowClear: options.allowBlank,
		    multiple: options.multiSelected,
		    containerCssClass: options.fieldClass || '',
		    minimumResultsForSearch: options.minimumResultsForSearch || (options.allowSearch ? 0 : -1)
		}, options || {}));
		$(target).bind('valuechange', function(e,value,model){
			//用于ajaxload后的赋值
			$(this).trigger('change',[value]);
		});
		$(target).bind('select2-selecting select2-clearing change', function(e,tvalue){
			var value = e.val || '';
			if(tvalue){
				//用于ajaxload后的赋值
				value=tvalue;
			}
			if ($.isFunction(options.valuechange) && this.oldvalue != value){
				options.valuechange.apply(this, [value,e]);
			}
			this.oldvalue = e.val;
		});
		$(target).val(options.defValue);
		$(target).trigger('valuechange');
	};
});
/*页面全屏处理*/
$(function(){
	if($("body").find(".box5").html()!=null){
		$(".top-box").addClass("bottom-box");	
		$(".header-top").slideUp("fast",function(){
		   $(".header").css("height","50px");
		   $(".box5").css("top","100px");
			   
		 });
	}
	$(".top-box").click(function(){
		if($(this).hasClass("bottom-box")){
			$(".box5").css("top","240px");
			$(".header-top").slideDown("fast",function(){
				$(".header").css("height","150px");
				
				
			 });
			$(this).removeClass("bottom-box"); 
		}else{
			$(this).addClass("bottom-box");	
			$(".header-top").slideUp("fast",function(){
			   $(".header").css("height","50px");
			   $(".box5").css("top","100px");
			   
			 });
		}	
	})
});

function getProjectName(){
	var curPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curPath.indexOf(pathName);
	//var localhostPath = curPath.substring(0,pos);
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return (projectName);
}
function changeMap(type){
	if(type==1){
	var strpoint=ezmap.getCenter();
	var level=12-ezmap.getZoomLevel();
	var url=location.href.split("&x")[0];
	var parms="&x="+strpoint.split(",")[0]+"&y="+strpoint.split(",")[1]+"&level="+level;
	location.href=url.replace("zy","")+parms;
	}
	else{
		var strpoint=getCenter();
		var level=12-GetCurrentZoom();
		if(level<0)
			level=0;
		var parms="&x="+strpoint.lon+"&y="+strpoint.lat+"&level="+level;
		var url=location.href.split("&x")[0];
	    if(url.indexOf("countyViewControl")>-1)
			url=url.replace("countyViewControl","zycountyViewControl");
		else if(url.indexOf("locationDetail")>-1)
			url=url.replace("locationDetail","zylocationDetail");
		else if(url.indexOf("history")>-1)
			url=url.replace("history","zyhistory");
		else
			url=url.replace("map","zymap");
	    if(url.indexOf("?")<0){
	    	parms="?"+parms;
	    }
		location.href=url+parms;
	}
}
function getParameters(url, options) {
    options = options || {};
    // if no url specified, take it from the location bar
    url = (url === null || url === undefined) ? window.location.href : url;
    //parse out parameters portion of url string
    var paramsString = "";
    if (url.indexOf('?')>-1) {
        var start = url.indexOf('?') + 1;
        var end = (url.indexOf("#")>-1) ?
                    url.indexOf('#') : url.length;
        paramsString = url.substring(start, end);
    }

    var parameters = {};
    var pairs = paramsString.split(/[&;]/);
    for(var i=0, len=pairs.length; i<len; ++i) {
        var keyValue = pairs[i].split('=');
        if (keyValue[0]) {

            var key = keyValue[0];
            try {
                key = decodeURIComponent(key);
            } catch (err) {
                key = unescape(key);
            }
            
            // being liberal by replacing "+" with " "
            var value = (keyValue[1] || '').replace(/\+/g, " ");

            try {
                value = decodeURIComponent(value);
            } catch (err) {
                value = unescape(value);
            }
            
            // follow OGC convention of comma delimited values
            if (options.splitArgs !== false) {
                value = value.split(",");
            }

            //if there's only one value, do not return as array                    
            if (value.length == 1) {
                value = value[0];
            }                
            
            parameters[key] = value;
         }
     }
    return parameters;
}; 
function focError(elem, error){
	if (!elem.is(':visible')){
		//如果控件不可见,从本机到Body，获取第一个可见的input
		elem = $($(':visible', elem.parent())[0]);
		//非input字段如div默认不能触发focus,blur事件
		elem.attr('tabIndex', elem.attr('tabIndex') || -1);
	}
	elem.addClass('error-field');
	if (!error){
		error = elem.attr('placeholder');
	}
	var tip = elem.qtip({
	    content: {text: '<p class="error-tip">'  + error + '</p>'},
	    show: {event: 'focus'},
	    hide: {event: 'noautohide'},
	    position: {at: 'bottom center', my: 'top center'},
	    //宽度减去border
	    style: {width: elem.outerWidth() - 2, classes:'qtip-red qtip-rounded'}
	});
	elem.one('blur input', function(){//只执行一次
		$(this).removeClass('error-field');
		var api = $(this).qtip('api');
		if (api && api.destroy){
			api.destroy(true);
		}
	});
	elem.focus();
};
$.fn.focusError = function(error){
	focError.apply(this, [$(this), error]);
}