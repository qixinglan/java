(function($) {
	function init(target) {
		var $self = $(target);
		$self.attr("chooseType", "");
		var opts = $.data(target, 'triggerfield').options;
		var clearBtn = '';
		if(opts.showClear){
			clearBtn = '<a href="javascript:void(0);" tabindex="-1" class="clear_triggerIcon"></a>';
		}
		var randomId = new Date().getTime();
		var $divInput = $('<div style="display:inline-block;width:100%;">'
						+ '<div class="mcdropdown">'+clearBtn+'<a href="javascript:void(0);" tabindex="-1"></a><input type="hidden" name="'
				+ (target.name || target.id)
				+ '" id="'
				+ (target.id || randomId)
				+ '" /></div></div>');
		
		var _data = $.data(target);
		
		var $input = $self.replaceWith($divInput).attr({
					id		: (target.id || randomId)+'_mcdropdown',
					name	: opts.labelFieldName || (target.name || randomId)+'_mcdropdown'
				});
		$input.removeAttr("name");
		
		// get a reference to the hidden form field
		var $hidden = $divInput.find(":input");

		// put the input element back in the div.mcdropdown layer
		$divInput = $divInput.find(".mcdropdown").prepend($input);
		if(opts.readonly){
			$input.attr("readOnly", "readOnly");
		}
		if(!$.isNull(opts.emptyText)){
			$input[0].defaultValue = opts.emptyText;
			$input.addClass('emptyField');
		}
		if(!$.isEmptyObject(_data)){
			if(_data.events){
				$.each(_data.events, function(i){
					var type = this[0].type;
					if(this[0].namespace != ''){
						type += "."+this[0].namespace;
					}
					$input.bind(type, this[0].handler);
				});
			}
			$.data($input[0], _data);
		}
		
		var btnWidth = $('a:last', $divInput).width() + 2;//只计算下拉按钮的宽度  夏先智
//		$("a", $divInput).each(function(){
//			btnWidth += parseInt($(this).css('width'));
//		});
		
		var width = opts.width;
		if(width == "auto"){
			width = $self.outerWidth(true);
			if(opts.realContainer.length > 0){
				width = opts.realContainer.width();
			}
		}else{
			
		}
		var di = {
			width	: width - btnWidth,
			height	: $divInput.outerHeight()
		}

		// update the height of the outer relative div, this allows us to
		// correctly anchor the dropdown
		//$divInput.parent().height(di.height);

		// safari will not get the correct width until after everything has
		// rendered
//		if ($.browser.safari) {
//			setTimeout(function() {
//						$self.width($divInput.width() - btnWidth);
//					}, 100);
//		}

		// adjust the width of the new input element
		//$self.width(di.width)
//				.attr('readonly', 'readonly')
				// make sure we only attach the next events if we're in input
				// element
				$self.filter(":input")
				// prevent user from selecting text
				.bind("mousedown", function(e) {
							$self.trigger("focus");
						})
				// disable context menu
				.bind("contextmenu", function() {
							return false;
						});
				// when the user leaves the text field
		// attach a click event to the anchor
		if ($.browser.msie && $.browser.version == 7) {
			$("a", $divInput).css('margin-bottom', "1");
		}
		
		function setEmptyText(){
			var val = $input.val();
			if($.isNull(val) && !$.isNull(opts.emptyText)){
				$input.addClass('emptyField');
				$input.val(opts.emptyText);
			}else if(!$.isNull(val) && val != opts.emptyText){
				$input.removeClass('emptyField');
				//$input.val("");
			}
		}
		
		if(!opts.editable){
			$input.bind('click', function(e){
				if($.isFunction(opts.open)){
					if (!$divInput.is('.mcdropdownDisabled')){
						opts.open.apply(target, [e]);
					}
				}
			});
		}
		$input.bind('focus', function(e){
			setEmptyText();
		}).bind('blur', function(e){
			setEmptyText();
		}).bind('changeVal', function(e){
			setEmptyText();
		});
		
		$("a:not(.clear_triggerIcon)", $divInput).addClass(opts.btnIcon).bind("click", function(e) {
					// if disabled, skip processing
					if ($divInput.is('.mcdropdownDisabled'))
						return false;
					if($.isFunction(opts.open)){
						opts.open.apply(target, [e]);
					}
				});
		if(opts.showClear){
			$("a.clear_triggerIcon", $divInput).click(function(){
				if ($divInput.is('.mcdropdownDisabled'))
					return false;
				setValue(target, '');
				$(this).blur();
				$input.trigger('cleanVal');
			});
		}else{
			$("a:not(.clear_triggerIcon)", $divInput).css('margin-left', '-28px');
		}
		setEmptyText();
		
		if($input.attr('defValue')){
			$hidden.val($input.attr('defValue'));
			$hidden.attr('defaultValue', $input.attr('defValue'));
			$input.attr('defaultValue', $input.val());
			$input.trigger('validate');
		}

		$input.parents("form").bind('reset', function(){
			var defaultValue = $input.attr("defaultValue");
			if(defaultValue && defaultValue != opts.emptyText){
				$input.removeClass('emptyField');
			}else if(defaultValue == opts.emptyText){
				$input.addClass('emptyField');
			}
		});
	}
	
	function setValue(target, valObj){
		var opts = $.data(target, 'triggerfield').options;
		var text = "";
		var value = "";
		var pre_value = "";
		if(!$.isNull(valObj)){
			if(typeof(valObj) == 'string'){
				value = text = valObj;
			}else{
				value = valObj.value;
				text = valObj.text;
				if(text == ''){
					value = '';
				}
			}
		}

		$(target).parent().find("[type=hidden]").val(value);
		if(opts.showNameValue){
			$(target).val(text);
		}
		if(pre_value != value){
			$(target).trigger('changeComplete');
		}
		$(target).trigger('changeVal').trigger('validate');
	}
	
	function getValue(target){
		return [$('#'+target.id.replace('_mcdropdown', '')).val(), $(target).val()]
	}
	function reloadTree(target, url){
		if (target.id.indexOf("_mcdropdown") != -1) {
			return target.reloadTree();
		} else {
			return $("#" + target.id + '_mcdropdown')[0].reloadTree(url);
		}
	}
	
	function disable(target, status){
		var bDisabled = status;
		if(typeof(status) == 'undefined'){
			bDisabled = true;
		}
		$(target).parent()[bDisabled ? "addClass" : "removeClass"]("mcdropdownDisabled");
		if(bDisabled){
			$(target).attr("readOnly", "true");
		}else{
			$(target).removeAttr("disabled");
		}
	}
	
	$.fn.triggerfield = function(options, param) {
		if (typeof options == 'string') {
			switch (options) {
				case 'setValue' :
					return this.each(function() {
								setValue(this, param);
							});
				case 'getValue' : 
					return getValue(this[0]);
				case 'reloadTree' : 
					return reloadTree(this[0], param);
				case 'disable' : 
					return this.each(function(){
						disable(this, param);
					});
			}
		}

		options = options || {};

		return this.each(function() {
			var state = $.data(this, 'triggerfield');
			if (state) {
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'triggerfield', {
					options		: $.extend({}, $.fn.triggerfield.defaults, {
						width		: (parseInt($(this).css('width')) || 'auto'),
						editable	: ($(this).attr('editable') == 'true'
								? true
								: false)
					}, options)
				});
				init(this);
			}
		});
	};

	$.fn.triggerfield.defaults = {
		width			: 'auto',
		readonly        : true,
		open            : false,
		labelFieldName  : '',
		btnIcon         : 'triggerIcon',
		showClear       : true,
		emptyText       : '',
		realContainer   : [],//渲染的真实对象，主要用于获取宽度、高度及按钮显示位置
		showNameValue   : true//是否显示选择项的真实值
	};
})(jQuery);