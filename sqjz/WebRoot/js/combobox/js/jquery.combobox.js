(function($) {
	/*
	 * ! treecombo jQuery Plug-in
	 * 
	 * @zhaomeidan
	 * depend on triggerfield
	 * 
	 * Date: 2010-06-10
	 */
	$.fn.treecombo = function(options) {
		// track the dropdown object
		var dd;
		
		// create a dropdown for each match
		this.each(function() {
					dd = $.data(this, "treecombo");

					// we're already a dropdown, return a reference to myself
					if (dd){
						if(options.url){
							dd.reloadTree(options.url);
						}
						return false;
					}
					new $.treecomboMenu(this, options);
				});

		// return either the dropdown object or the jQuery object reference
		return dd || this;
	};

	// set default options
	$.treecombo = {
		version		: "1.0",
		setDefaults	: function(options) {
			$.extend(defaults, options);
		}
	};

	// set the defaults
	var defaults = {
		url				: '',
		treeHtml        : false,
		matchable       : false,
		maxColumnHeight	: 150,
		multiSelected	: false,
		showIcon        : false,
		autoclose		: true,
		selectWidth     :'auto',
		selectHeight    :'auto',
		animate			: false,
		showClear       : true,
		showNameValue   : true,//是否显示选择项的真实值
		realContainer   : [],//渲染的真实对象，主要用于获取宽度、高度及按钮显示位置
		emptyText       : '',
		zIndex          : 2,
		openFx			: "slideDown" // the fx to use for showing the root
										// menu
		,
		openSpeed		: 250 // the speed of the openFx
		,
		closeFx			: "slideUp" // the fx to use for hiding the root menu
		,
		closeSpeed		: 250 // the speed of the closeFx
		,
		autoHeight		: true // always uses the lineHeight options (much
								// faster than calculating height)
		,
		lineHeight		: 19 // the base height of each list item (li) this
								// is normally calculated automatically, but in
								// some cases the value can't be determined and
								// you'll need to set it manually
		,
		screenPadding	: 2 // the padding to use around the border of the
								// screen -- this is used to make sure items
								// stay on the screen
		,
		valueAttr		: "id" // the attribute that contains the value to use
								// in the hidden field
		,
		select			: null // callback that occurs when a value is selected
		,
		init			: null
		,labelFieldName : ''
		,ignoreValues : false//忽略不显示的选项,字符串，逗号分隔
		,onlyValues:false
		// callback that occurs when the control is fully initialized
	};

	// check to see if the browser is IE6
	var isIE6 = ($.browser.version && $.browser.version <= 6);

	$.treecomboMenu = function(el, options) {
		
		var $self, thismenu = this, $list, $divInput, settings, typedText = "", matchesCache, oldCache, $keylist, $keylistiframe, bInput, bDisabled = false,preValue='';

		// create a reference to the dropdown
		$self = $(el);

		// is the field and input element
		bInput = $self.is(":input");

		// get the settings for this instance
		settings = $.extend({}, defaults, options);

		$self.parents().bind('scroll', function(){
			thismenu.closeMenu();
		});
		
		// attach window behaviors
		$(document)
				// Bind a click event to hide all visible menus when the
				// document is clicked
				.bind("click", function(e) {
			// get the target that was clicked
					var $target = $(e.target);
					if($target.is('ul.simpleTree') || $target.attr('id') == el.id || $target.next(":input[id="+el.id+']').length == 1){
						clearTimeout(iBlurTimeout);
						e.stopPropagation();
						return false;
					}
					var $ul = $target.parents().filter(function() {
						return this === $list[0]
								|| (!!$keylist && $keylist[0] === this);
					});
					// check to make sure the clicked element was inside the
					// list
					if ($ul.length) {
						if ($target.is("li[className^=folder]")) {
							clearTimeout(iBlurTimeout);
							e.stopPropagation();
							return false;
						}else if($target.is(".showValue")){
							clearTimeout(iBlurTimeout);
							e.stopPropagation();
							return false;
						}
					}
					// close the menu
					thismenu.closeMenu();
				}).bind('keydown', function(e){
					if(e.keyCode == 9){
						thismenu.closeMenu();
					}
				});

		// store a reference to the list, if it's not already a jQuery object
		// make it one
		if(!$.isNull(options.url)){
			
			$list = $('<ul class="simpleTree"><span></span><li class="root"><ul class="ajax"><li>{url:'
					+ options.url + '}</li></ul></li></ul>');
		}else{
			if(options.treeHtml==""){
				$list = $('<ul class="simpleTree"><span></span><li class="root" style="margin-top:-16px;"><span>&nbsp;</span><ul class="">暂无数据</ul></li></ul>');
		}else{
				$list = $('<ul class="simpleTree"><span></span><li class="root" style="margin-top:-16px;"><span>&nbsp;</span><ul class="">'+ options.treeHtml + '</ul></li></ul>');
		}
		}
		var width = $self.outerWidth()-3;
		if(settings.selectWidth!="auto"){
			width = parseInt(settings.selectWidth)+2;
		}
		var divHeight=settings.selectHeight;

		if(settings.realContainer.length > 0){
			width = settings.realContainer.ou-rWidth(true);
		}
		// we need to calculate the visual width for each nested list
		$list
				// move list to body -- this allows us to always calculate the
				// correct position & width of the elements
				.appendTo('body')
				// move the list way off screen
				.css({
							position	 : "absolute",
							border	     : '1px solid #7f9db9',
							backgroundColor : '#fff',
							//'border-top' : 'none',
							overflow	 : 'auto',
							width		 : width,
							height       :divHeight,
							'zIndex'     : settings.zIndex,
							top		: 0,
							left	: 0,
							display	: "none"
						});
		 if (bInput){
			var defValue = $self.attr("defValue");
			if(!$.isNull(defValue)){
				$self.val(displayString(defValue, true));
			}
		}
		// create the div to wrap everything in
		var $input = $self.triggerfield({
			btnIcon : '',
			emptyText : settings.emptyText,
			showClear : settings.showClear,
			selectWidth:settings.selectWidth,
			selectHeight:settings.selectHeight,
			labelFieldName : settings.labelFieldName,
			open : function(args){
				thismenu.openMenu(args[0]);
			}
		}).attr('autocomplete', 'off');
		
		
		$self.keydown(function(e){
			switch (e.keyCode) {
				case 37 : //left
				case 39 : //right
					return false;
				default : 
					return true;
			}
		})
		.keyup(function(e) {
			//当文本框为readOnly时不进行自动匹配
			if($self.attr("readOnly")){
				return false;;
			}
			var selected;
			try{
				selected = $list[0].getSelected();
			}catch(e){
				return;
			}
			if(selected.length == 0){
				selected = $list.find('li[selectable!=false]:visible:not([className^=line]):not(li.root):first');
			}
			switch (e.keyCode) {
				case 38 : // up
					thismenu._upKey(selected);
				break;
				case 40 : // down
					thismenu._downKey(selected);
				break;
				case 13 : // enter
					if (!$list.is(":visible")){
						return;
		            }
		            
					if(!settings.multiSelected){
						if(settings.matchable || selected.length>0){
							thismenu.setValue(selected.attr(settings.valueAttr));
						}else{
							$hidden.val($self.val());
						}
						thismenu.closeMenu();
					}else{
						var text = $self.val();
						if(selected.length){
							text = text.substr(0, text.lastIndexOf(',')+1)+getNodeText(selected);
						}
						var value = getMatchedValue(text);
						thismenu.setValue(value);
					}
				break;
				case 27 : // esc
					thismenu.closeMenu();
					$input.blur();
				break;
				default :
					if(e.keyCode == 37 || e.keyCode==39){
						return;
					}
					thismenu.openMenu(e);
					if(e.keyCode != 9){//非tab键
						thismenu._filter.apply(thismenu);
					}
			}
		})
		.mousedown(function(e){
			return false;
		})
		.click(function(e){
			if($.browser.msie){
				var obj = e.srcElement;
				if(obj.value.length>0){
					var r =obj.createTextRange();    
					r.moveStart('character',obj.value.length);      
					r.collapse();      
					r.select(); 
				}
			}  
		})
		.bind('blur', onBlur);
		
		$divInput = $self.parent("div.mcdropdown");
		var $hidden = $divInput.find(":input:hidden");
		
		$.data($hidden[0], "treecombo", thismenu);

		this._upKey = function(selected){
			var prev = selected.prev().prev();
			if (prev.length == 0) {
				prev = selected.parent().parent();
			}
			
			if (prev.length) {
				var select = false;
				if(prev.is('li.root')){
					return;
				}
				if(prev.is(':visible')){
					select = $list[0].select(prev);
				}
				if(!select){
					thismenu._upKey(prev);
				}
			}
		}
		
		this._downKey = function(selected){
			var next = selected.next().next();
			if(next.length == 0){
				next = selected.parent().parent();
				if(next.is('li.root')){
					return;
				}
				if(next.length){
					next = next.next().next();
				}
			}
			
			if (next.length) {
				var select = false;
				if(next.is(':visible')){
					select = $list[0].select(next);
				}
				if(!select){
					thismenu._downKey(next);
				}
			}
		}
		this.reloadTree = function(url){
			$list = $list.empty().append('<span></span><li class="root"><ul class="ajax"><li>{url:'
					+ url + '}</li></ul></li>');
			this.renderList();		
			
		}
		
		el.reloadTree = function(options){
			if(options.url!=""&& options.url!=null){
				$list = $list.empty().append('<span></span><li class="root"><ul class="ajax"><li>{url:'
					+ options.url + '}</li></ul></li>');
			}else{
				
				$list =  $list.empty().append('<span></span><li class="root" style="margin-top:-16px;"><span>&nbsp;</span><ul class="">'+ options.treeHtml + '</ul></li></ul>');
			}
			
			$list.simpleTree({
				nodeCheckBox			: settings.multiSelected,
				cascadeParentChecked	: settings.cascadeParentChecked,
				cascadeChildrenChecked	: settings.cascadeChildrenChecked,
				autoclose				: settings.autoclose,
				animate					: settings.animate,
				showIcon                : settings.showIcon,
				showRoot                : false,
				valueAttr		        : settings.valueAttr,
				ignoreValues            : settings.ignoreValues,
				onlyValues              : settings.onlyValues,
				afterClick				: function(node) {
					if (!settings.multiSelected && node.attr('selectable') != "false") {
						thismenu.setValue(node.attr(settings.valueAttr));
						thismenu.closeMenu();
					}
				},
				checkBoxClick			: function(input) {
					thismenu.setValue(this.getSelectionsValue().value);
				},
				afterAjax : function(){
					// set the default value (but don't run callback)
				    if (bInput){
						var defValue = $self.attr("defValue");
						if(!$.isNull(defValue)){
							thismenu.setValue(defValue, true);
							$hidden.attr("defaultValue", defValue);
							$self.attr("defaultValue", $self.val());
						}
					}
				}
			});
			thismenu.renderTree = true;
					
					//
		}
		// set the value of the field
		this.setValue = function(value, skipCallback, append) {
			// get the display name
			var name = '';
			
			if(append){
				var hiddenVal = $hidden.val();
				if((','+hiddenVal+',').indexOf(','+value+',') == -1){
					value = hiddenVal+($.isNull(value)?'':($.isNull(hiddenVal)?value:','+value));
				}
			}else if(settings.multiSelected){
				var vals = value.split(",");
				var val = "";
				for(var i=0; i<vals.length; i++){
					if(!$.isNull(vals[i]) && (','+val+',').indexOf(','+vals[i]+',') == -1){
						val += ($.isNull(val)?'':',')+vals[i];
					}
				}
				value = val;
				delete val, i;
			}
			if(!$.isNull(value)){
				name = displayString(value);
			}else{
				value = '';
			}
			// update the hidden value
			$hidden.val(value);

			// run the select callback (some keyboard entry methods will manage
			// this callback manually)
			if (settings.select != null && skipCallback != true)
				settings.select.apply(thismenu, [value, name]);

			if($self.is(':visible') && value != null && $.trim(value) != ''){
				$self.trigger('validate');
			}
			
			// update the display value and return the jQuery object
			if(settings.showNameValue){
				$self[bInput ? "val" : "text"](name);
			}
			
			$self.trigger('changeVal').trigger('validate');
			
			return $self;
		};

		// get the value of the field (returns array)
		this.getValue = function(value) {
			return [$hidden.val(), $self[bInput ? "val" : "text"]()];
		};
		
		this._filter = function() {
			$('.active', $list).removeClass('active');
			var rows = $('li:not([className^="line"])', $list), cache = rows.map(function() {
						return $('>div>span.text', this).text().toLowerCase();
					});
			
			var val = settings.multiSelected?$input.val().split(','):[$input.val()];
			var term = $.trim(val[val.length-1].toLowerCase()), scores = [];

			if (!term) {
				thismenu._clearFilter();
			} else {
				rows.hide();

				cache.each(function(i) {
							if (this.indexOf(term) > -1) {
								scores.push(i);
							}
						});

				var selected = false;
				$.each(scores, function() {
							$(rows[this]).parents().show().each(function(){
								this.className = this.className.replace('close', 'open');
							});
							$(rows[this]).show();
							if(!selected){
								selected = $list[0].select(rows[this]);
							}
						});
			}
		};
		
		this._clearFilter = function(){
			var rows = $('li:not([className^="line"])', $list);
			$('.active', $list).removeClass('active');
			rows.find('>div>span.text>input').attr('checked', false);
			rows.show();
			var value = $hidden.val();
			if($.isNull(value)){
				return;
			}
			if(settings.multiSelected){
				value = value.split(',');
				for(var i=0; i<value.length; i++){
					$('#'+value[i]+'>div>span.text>input', $list).attr('checked', true);
				}
				delete i;
			}else{
				$('#'+value+'>div', $list).addClass('active');
			}
		}
		
		this.renderList = function(){
			$list.simpleTree({
				nodeCheckBox			: settings.multiSelected,
				cascadeParentChecked	: settings.cascadeParentChecked,
				cascadeChildrenChecked	: settings.cascadeChildrenChecked,
				autoclose				: settings.autoclose,
				animate					: settings.animate,
				showIcon                : settings.showIcon,
				showRoot                : false,
				valueAttr		        : settings.valueAttr,
				ignoreValues            : settings.ignoreValues,
				onlyValues              :settings.onlyValues,
				afterClick				: function(node) {
					if (!settings.multiSelected && node.attr('selectable') != "false") {
						thismenu.setValue(node.attr(settings.valueAttr));
						thismenu.closeMenu();
					}
				},
				checkBoxClick			: function(input) {
					thismenu.setValue(this.getSelectionsValue().value);
				},
				afterAjax : function(){
					// set the default value (but don't run callback)
				    if (bInput){
						var defValue = $self.attr("defValue");
						//如果控件通过其它方式setValue时，打开下拉列表时应该保留其值
						var value = $hidden.val();
						if(!$.isNull(value)){
							defValue = value;
						}
						if(!$.isNull(defValue)){
							thismenu.setValue(defValue, true);
							$hidden.attr("defaultValue", defValue);
							$self.attr("defaultValue", $self.val());
						}
					}
				}
			});
			thismenu.renderTree = true;
		}

		// open the menu programmatically
		this.openMenu = function(e) {
			// if the menu is open, kill processing
			if ($list.is(":visible")) {
				// on a mouse click, close the menu, otherwise just cancel
				return;
			}

			function open() {
				if(!thismenu.renderTree){
					thismenu.renderList();
					// columnize the root list
					columnizeList($list).hide();
				}
			
				preValue = $hidden.val();

				// anchor the menu relative parent
				if(settings.realContainer.length > 0){
					anchorTo(settings.realContainer, $list, true);
				}else{
					anchorTo($input, $list, true);
				}
				
				//下拉选择时（非树状）样式修改为正行背景色 add by zhaomd
				$list.show(0, function(){
					if(!settings.showIcon){
						var listWidth = $list[0].scrollWidth;
						$list.find("li .showValue").each(function(){
							//padding不能为负数，当数据为空时会报错
							if((listWidth-$(this).parents("div:first")[0].scrollWidth)-4>1){
								$(this).css("paddingRight", (listWidth-$(this).parents("div:first")[0].scrollWidth)-4+"px");
							}else{
								$(this).css("paddingRight", (listWidth-$(this).parents("div:first")[0].scrollWidth)+3+"px");
							}
							
						});
					}
				}).hide();
				//add end
				thismenu._clearFilter();
				
				// show the menu
				$list[settings.openFx](settings.openSpeed, function() {
							// scroll the list into view
							scrollToView($list);
						});
				
				// if the bgIframe exists, use the plug-in
				if (isIE6 && !!$.fn.bgIframe)
					$list.bgIframe();
				$self.focus();
			}

			// if this is triggered via an event, just open the menu
			if (e)
				open();
			// otherwise we need to open the menu asynchronously to avoid
			// collision with $(document).click event
			else
				setTimeout(open, 1);
		};

		// close the menu programmatically
		this.closeMenu = function(e) {
			if($list.is(":visible")){
				// hide any open menus
				$list.find("div.active").removeClass('active');
	
				// close the menu
				$list[settings.closeFx](settings.closeSpeed);
				if($hidden.val() != preValue){
					$self.trigger('changeComplete');
					$self.trigger('change');
				}
			}
		};

		// place focus in the input box
		this.focus = function() {
			$self.focus();
		};

		// disable the element
		this.disable = function(status) {
			$input.triggerfield('disable', status);
		};
		
		if(options.url){
			this.renderList();
			columnizeList($list).hide();
		}
					
		function getNodeText($el, beforeRender) {
			// return either an empty string or the node's value
			var nodeText = "";
			if(beforeRender){
				nodeText = $el.text();
			}else{
				$el.contents().children().each(function() {
					if ($(this).is('span') && !$(this).is('.trigger')) {
						nodeText = $(this).find('.showValue').text();
						return false;
					}
				});
				if($el.find(".showValue").text()==""&&$el.find(".text").text()!=null){
					nodeText = $el.find('.text').text();
				}
			}
			return nodeText;
		};

		function displayValue(value, beforeRender) {
			// return the path as an array
			return getNodeText(getListItem(value), beforeRender);
		};

		function displayString(value, beforeRender) {
			// return the display name
			var displayStr = "";
			var values = settings.multiSelected?value.split(","):[value];
			for (var i = 0; i < values.length; i++) {
				displayStr += displayValue(values[i], beforeRender) + ",";
			}
			return displayStr.substring(0, displayStr.length - 1);
		};
		
		// scroll the current element into view
		function scrollToView($el) {
			// get the current position
			var p = position($el, true);
			// get the screen dimensions
			var sd = getScreenDimensions();

			// if we're hidden off the bottom of the page, move up
			if (p.bottom > sd.y) {
				$("html,body").animate({
					"scrollTop"	: "+="
							+ ((p.bottom - sd.y) + settings.screenPadding)
							+ "px"
				})
			}
		};

		function position($el, bUseOffset) {
			var bHidden = false;
			// if the element is hidden we must make it visible to the DOM to
			// get
		
			if ($el.is(":hidden")) {
				bHidden = !!$el.css("visibility", "hidden").show();
			}

			var pos = $.extend(
					$el[bUseOffset === true ? "offset" : "position"](), {
						width			: $el.outerWidth(),
						height			: $el.outerHeight(),
						marginLeft		: parseInt($.curCSS($el[0],
										"marginLeft", true), 10)
								|| 0,
						marginRight		: parseInt($.curCSS($el[0],
										"marginRight", true), 10)
								|| 0,
						marginTop		: parseInt($.curCSS($el[0],
										"marginTop", true), 10)
								|| 0,
						marginBottom	: parseInt($.curCSS($el[0],
										"marginBottom", true), 10)
								|| 0
					});
			
			if (pos.marginTop < 0)
				pos.top += pos.marginTop;
			if (pos.marginLeft < 0)
				pos.left += pos.marginLeft;
			pos["bottom"] = pos.top + pos.height;
			pos["right"] = pos.left + pos.width;

			// hide the element again
			if (bHidden)
				$el.hide().css("visibility", "visible");

			return pos;
		};

		function anchorTo($anchor, $target, bUseOffset) {
			var pos = position($anchor, bUseOffset);
			
			var bottom = pos.bottom-1;
			
			if((pos.bottom+$target.height() + settings.screenPadding) >= getScreenDimensions().y){
				bottom = pos.bottom - $anchor.height()-$target.height()-3;
				if(bottom < 0){
					if(getScreenDimensions().height-pos.bottom > ($target.height()+bottom)){
						$target.height(getScreenDimensions().height-pos.bottom-3);
						bottom = pos.bottom-1;
					}else{
						$target.height($target.height()+bottom-2);
						bottom = 3;
					}
				}
			}
			
			$target.css({
						position	: "absolute",
						top			: bottom,
						left		: pos.left
					});

			/*
			 * we need to return the top edge of the core drop down menu,
			 * because the top:0 starts at this point when repositioning items
			 * absolutely this means we have to offset everything by the offset
			 * of the top menu
			 */

			return pos.bottom;
		};

		function getScreenDimensions() {
			var d = {
				scrollLeft	: $(window).scrollLeft(),
				scrollTop	: $(window).scrollTop(),
				width		: $(window).width() // changed from innerWidth
				,
				height		: $(window).height()
				// changed from innerHeight
			};

			// calculate the correct x/y positions
			d.x = d.scrollLeft + d.width;
			d.y = d.scrollTop + d.height;

			return d;
		};

		function getPadding(el, name) {
			var torl = name == 'height' ? 'Top' : 'Left', // top or left
			borr = name == 'height' ? 'Bottom' : 'Right'; // bottom or right

			return (
			// we add "0" to each string to make sure parseInt() returns a
			// number
			parseInt("0" + $.curCSS(el, "border" + torl + "Width", true), 10)
					+ parseInt("0"
									+ $.curCSS(el, "border" + borr + "Width",
											true), 10)
					+ parseInt("0" + $.curCSS(el, "padding" + torl, true), 10)
					+ parseInt("0" + $.curCSS(el, "padding" + borr, true), 10)
					+ parseInt("0" + $.curCSS(el, "margin" + torl, true), 10) + parseInt(
					"0" + $.curCSS(el, "margin" + borr, true), 10));
		};

		function getListDimensions($el, cols) {
			if (!$el.data("dimensions")) {
				// get the width of the dropdown menu
				var ddWidth = $divInput.outerWidth();
				// if showing the root item, then try to make sure the width of
				// the menu is sized to the drop down menu
				var width = (($el === $list) && ($el.data("width") * cols < ddWidth))
						? Math.floor(ddWidth / cols)
						: $el.data("width");

				$el.data("dimensions", {
							// get the original width of the list item
							column	: width
							// subtract the padding from the first list item
							// from the width to get the width of the items
							,
							item	: width
									- getPadding($el.children().eq(0)[0],
											"width")
							// get the original height
							,
							height	: $el.height()
						});
			}

			return $el.data("dimensions");
		};

		function getHeight($el) {
			// skip height calculation and use lineHeight
			if (settings.autoHeight === false)
				return settings.lineHeight;
			// if we haven't cached our height, do so now
			if (!$el.data("height"))
				$el.data("height", $el.outerHeight());

			// return the cached value
			return $el.data("height");
		};

		function columnizeList($el) {
			// we need to draw the list element, but hide it so we can correctly
			// calculate it's information
			$el.css({
						"visibility"	: "hidden",
						"display"		: "block"
					});

			// if the menu is too tall to fit on the screen, try adding another
			// column
			if (($el !== $list)
					&& (settings.maxColumnHeight + (settings.screenPadding * 2) >= getScreenDimensions().height)) {
				return columnizeList($el, cols + 1);
			}

			/*
			 * set the height of the list to the max column height. this fixes
			 * display problems in FF when the last column is not full.
			 * 
			 * we also need to set the visiblity to "visible" to make sure that
			 * the element will show up
			 */
			 
			 var height = $el.height();
			 if(settings.showIcon || height >= settings.maxColumnHeight){
			 	height = settings.maxColumnHeight;
			 }else if($el.attr('clientHeight') < height){
			 	height = $el.attr('scrollHeight')+18;
			 }
			$el.css("visibility", "visible").height(height);
			
			return $el;
		};

		function getListItem(value) {
			return $list.find("li:not('.line'):not('.line-last')["
					+ settings.valueAttr + "='" + value + "']");
		};
		
		function getListItemByDisplayStr(displayStr){
			return $list.find("")
		}
		
		function getMatchedValue(displayStr){
			
			var name = settings.multiSelected?displayStr.split(','):[displayStr];
			var value = [];
			var rows = $list.find("li:not([className^='line'])");
			for(var i=0; i<name.length; i++){
				var matchRows = rows.filter(function(){
					if($(this).attr('selectable') ==  'false' || getNodeText($(this)) != name[i]){
						return false;
					}
					return true;
				});
				if(matchRows.length != 0){
					value.push(matchRows.first().attr(settings.valueAttr));
				}
			}
			return value.join(',');
		}

		var iBlurTimeout;
		function onBlur(e) {
			// we may need to cancel this blur event, so we run it
			// asynchronously
			
			iBlurTimeout = setTimeout(function() {
						var value = getMatchedValue($self.val());
						if(settings.matchable || !$.isNull(value)){
							thismenu.setValue(value);
						}else if($self.val() != settings.emptyText){
							$hidden.val($self.val());
							$self.trigger("changeVal");
							$self.trigger('change');
						}
						
					}, 200);
		};
		
		// run the init callback (some keyboard entry methods will manage this
		// callback manually)
		if (settings.init != null)
			settings.init.apply(thismenu, [$input, $hidden, $list]);
	};

})(jQuery);