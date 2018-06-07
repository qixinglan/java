/**
 * 对jquery能力的扩展。
 * 分成两个部分：
 * 1. 对jquery本身能力的扩展，主要提供一些jquery的函数；使用 $.extend({...});
 * 2. 写一些小组件。使用$.fn.extend({...})扩展。
 */


// 第一部分。
$.extend({
	
	commAjax	: function(options, el) {
		options = $.extend({
			url			: '',
			isShowMask	: false,
			type		: 'POST',
			postData	: {},
			beforeSend	: false,
			onSuccess	: false,
			onFailure	: false,
			timeout		: 1800000,
			async		: true,
			checkSubmitted:false,
			maskMassage	: '数据加载中' // 等待提示信息
		}, options);
		
		if(options.checkSubmitted){
			//判断当前是否已经有提交动作，如果有则不提交
			if($.checkRun()){
				return;
			}
			//设置当前提交动作为true
			$.setRun(true);
		}
		
		if (!el) {
			el = $('html');
		}
		if (options.isShowMask && el.length > 0) {
			el.mask({
				top		: el.offset().top,
				left	: el.offset().left,
				width	: el.width(),
				height	: el.height(),
				message	: options.maskMassage
			});
		}
		
		$.ajax({
			url			: options.url,
			type		: options.type,
			data		: options.postData,
			beforeSend	: options.beforeSend,
			timeout		: options.timeout,
			async		: options.async,
			cache		: false,
			complete	: function(req, st) {
				if (options.isShowMask) {
					el.unmask();
				}
				
				// status：200为服务中成功的状态，0为本地打开时的成功状态
				if (st == "success"
				        && (req.status == 200 || req.status == 0)) {
					var obj;
					try {
						obj = jQuery.parseJSON(req.responseText);
					} catch (e) {
						obj = req.responseText;
					}
					
					if (obj && obj.success == false) {
						if ($.isFunction(options.onFailure)) {
							try {
								options.onFailure.call(this, obj);
							} catch (e) {
								$.alert("系统无法响应请求，请联系管理员");
							}
						} else if (obj.msg) {
							$.alert(obj.msg);
						}
					} else {
						if ($.isFunction(options.onSuccess))
							options.onSuccess.call(this, obj);
					}
				} else if (st == 'error' && req.status == "404") {
					$.alert('未找到对应请求。');
				} else if (st == 'timeout') {
					$.alert('连接超时，请刷新后重试。');
				} else {
					$.alert('连接失败，请检查网络。');
				}
				
				if(options.checkSubmitted){
					//设置当前提交动作标志为false
					$.setRun(false);
				}
			}
		});
	},
	
	strLen		: function(str) {// 判断字符长度(汉字算三个长度)
		str = $.trim(str);
		var len = 0;
		for (i = 0; i < str.length; i++) {
			if (str.charCodeAt(i) > 256) {
				len += 3;
			} else {
				len++;
			}
		}
		return len;
	},
	
	isNull	: function(obj) {
		if (obj == null || (typeof(obj) == 'string' && $.trim(obj) == '')
		        || (typeof(obj) == 'object' && $.isEmptyObject(obj))) {
			return true;
		}
		return false;
	},
	
	dateFormat : function(date, format){
		if(!format){
			format = 'yyyy-MM-dd';
		}
		
		var o = {   
	      "M+" : date.getMonth()+1, //month  
	      "d+" : date.getDate(),    //day  
	      "H+" : date.getHours(),   //hour  
	      "m+" : date.getMinutes(), //minute  
	      "s+" : date.getSeconds(), //second  ‘
		  //quarter  
	      "q+" : Math.floor((date.getMonth()+3)/3), 
	      "S" : date.getMilliseconds() //millisecond  
	    }   
	    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));      
	    for(var k in o){
	   	   if(new RegExp("("+ k +")").test(format)){   
	      	   format = format.replace(RegExp.$1,   
	        	 RegExp.$1.length==1 ? o[k] :    
	          	("00"+ o[k]).substr((""+ o[k]).length));  
	       }
	    } 
	    return format;  
	},
	
	realPosition : function() {
		var _ownerWin = window;
		var pos = {'left' : 0, 'top' : 0, 'bottom' : 9999999};
		while (_ownerWin != top) {
			var ifs = _ownerWin.parent.document.getElementsByTagName("iframe");
			for (i = 0; i < ifs.length; i++) {
				try{
					if (ifs[i].contentWindow == _ownerWin) {
						var _pos = ifs[i].getBoundingClientRect();
						pos.left += _pos.left;
						pos.top += _pos.top;
						if(_pos.bottom < pos.bottom){
							pos.bottom = _pos.bottom;
						}
						break;
					}
				}catch(e){}
			}
			_ownerWin = _ownerWin.parent;
		}
		return pos;
	}
});

// 第二部分。
$.fn.extend({
	//左侧tab页
	leftTab : function(o){
		var self = this;
		var wordChange  =  o && o.wordLength == 2 ? '2w_' : '';
		$("div[class*=but_su_"+wordChange+"left]",this).hover(function(){
			$(this).removeClass("but_su_"+wordChange+"left");
			$(this).addClass("but_su_left_"+wordChange+"on");
		},function(){
			$(this).removeClass("but_su_left_"+wordChange+"on");
			$(this).addClass("but_su_"+wordChange+"left");
		}).bind('click',function(){
			$("div.but_su_left_"+wordChange+"click",self).removeClass("but_su_left_"+wordChange+"click");
			$(this).addClass("but_su_left_"+wordChange+"click");
		});
	},
	
	// 在当前组件上生成蒙板。
	mask : function(options) {
		var $self = $(this), $mask, $maskText;
		options = $.extend({
			autoShow	: true,
			id			: 'massk',
			left		: $self.offset().left
			        + parseInt(($self.css('padding-left') || 0).replace(
			                'px', ''
			        )),
			top			: $self.offset().top
			        + parseInt(($self.css('padding-top') || 0).replace(
			                'px', ''
			        )),
			width		: $self.width() + 2, // 宽度
			height		: $self.height() + 2, // 高度
			message		: '数据加载中', // 提示内容
			showMessage	: true
			// 提示内容
		}, options);
		
		this.init = function() {
			$mask = $('<div class="window-mask"></div>').attr('id',
			        options.id
			).appendTo(this);
			$mask.css({
				top				: options.top,
				left			: options.left,
				zIndex			: 99998,
				width			: options.width,
				height			: options.height,
				'line-height'	: options.height + 'px',
				display			: 'none'
			});
			if (options.showMessage) {
				$maskText = $('<div class="window-text"></div>').attr('id',
				        options.id + '_text'
				).appendTo(this);
				$maskText.css({
					top		: (options.top + options.height / 2 - 60 / 2),
					left	: options.left + options.width / 2 - 340 / 2,
					zIndex	: 99999,
					display	: 'none'
				});
			}
		};
		this.show = function() {
			$mask.show();
			if ($maskText)
				$maskText.show();
		};
		this.hide = function() {
			$mask.hide();
			if ($maskText)
				$maskText.hide();
		};
		this.remove = function() {
			$mask.remove();
			if ($maskText)
				$maskText.remove();
		};
		this.changeText = function(text) {
			if ($maskText)
				$maskText.html(text + '...');
		};
		$(top).bind('resize.mask', function() {
			$mask.css({
				width	: $self.width(),
				height	: $self.height()
			});
		});
		this.init();
		if (options.autoShow)
			this.show();
		this.changeText(options.message);
		
		if($(this).is("html")){
			var curWin = window;
			while(true){
				var tempWin = curWin.parent;
				if(!curWin.frameElement||!tempWin.frameElement){
					break;
				}else{
					curWin = tempWin;
				}
			}
			if(curWin.frameElement&&curWin.frameElement.lhgDG){
				$(curWin.frameElement).closest(".lhgdig").find("input[type='button']").attr("disabled",true);
			}
		}

		return this;
	},
	unmask	: function(id) {
		var unmaskId = id || 'massk';
		$('#' + unmaskId, this).remove();
		$('#' + unmaskId + '_text', this).remove();
		$(top).unbind('resize.mask');
		
		if($(this).is("html")){
			var curWin = window;
			while(true){
				var tempWin = curWin.parent;
				if(!curWin.frameElement||!tempWin.frameElement){
					break;
				}else{
					curWin = tempWin;
				}
			}
			if(curWin.frameElement&&curWin.frameElement.lhgDG){
				$(curWin.frameElement).closest(".lhgdig").find("input[type='button']").attr("disabled",false);
			}
		}
	},
	/**
	 * 初始化栏目区域 
	 * $('body').initColumnArea({
	 * 		title	: 'div.caption[mode]', 
	 *		body   	: 'div.caption_body',
	 *		state   : 'close',
	 *		speed   : 'slow'
	 * });
	 * @author zhougz
	 * @data 2011-11-04
	 */
	initColumnArea : function(opt){
		var self = this;
		self.opt = $.extend({
			title	: null, 
			body   	: null,
			state   : 'close',
			speed   : 'slow',
			closeCallback : false,
			openCallback : false
		}, opt);
		if($.isNull(opt.title) ||　$.isNull(opt.title)) return ;
		var $tA = typeof opt.titleArea === "string" ? $(opt.titleArea) : opt.title;
		var titleText = $tA.text();
		var $bA =  typeof opt.bodyArea === "string" ? $(opt.bodyArea) : opt.body;
		$tA.css({'cursor':'pointer'}).click(function(){
			if($bA.is(':visible')){
				$bA.slideUp(opt.speed,function(){
					if($.isFunction(opt.closeCallback))opt.closeCallback.call(this);
				});
				$tA.find("span").text("︾"+titleText);
			}else{
				$bA.slideDown(opt.speed,function(){
					if($.isFunction(opt.openCallback))opt.openCallback.call(this);
				});
				$tA.find("span").text("︽"+titleText);
			}
		});
		if(opt.state === 'close'){
			$tA.click();
		} else{
			$tA.find("span").text("︽"+titleText);
		}
	},
	getElFitHeight: function(exceptEl){
		var $self = $(this);
		var winPos = {'left' : 0, 'top' : 0, 'bottom' : 9999999};
		var ifs = parent.document.getElementsByTagName("iframe");
		for (i = 0; i < ifs.length; i++) {
			try{
				if (ifs[i].contentWindow == window) {
					winPos = ifs[i].getBoundingClientRect();
					break;
				}
			}catch(e){}
		}
		var elTop = $self.offset().top;
		var elMarginTop = parseInt($self.css('marginTop')) || parseInt($self.css('paddingTop'));
		if(elMarginTop<0){
			elTop += elMarginTop;
		}else{
			elTop -= elMarginTop;
		}
		
		var expElHeight = 0;
		if(exceptEl){
			var $exceptEl = $("#"+exceptEl);
			expElHeight = $exceptEl.outerHeight();
			var expElMarginTop = parseInt($exceptEl.css('marginTop')) || parseInt($exceptEl.css('paddingTop'));
			if(expElMarginTop<0){
				expElHeight += expElMarginTop;
			}else{
				expElHeight -= expElMarginTop;
			}
		}
		return winPos.bottom-winPos.top-elTop-expElHeight;
	}
});