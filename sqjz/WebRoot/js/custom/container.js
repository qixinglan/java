(function ($) {
"use strict";
$.container = $.container || {};
$.extend($.container,{
	////selector 容器选择器，options 选项,参数包括: 
	//-----------容器类型type: form,panel,grid,其它为容器类型对应的生成器参数
	//-----------自容器items: 数组类型，包含1个或多个options相同方式的容器定义
	//-----------容器id:容器id必须绑定到可以容纳自容器的元素上
	//-----------容器标题title，panel作为显示，form作为提交的提示
	//-----------容器字段显示fields数组, fieldCols字段显示列数,fieldCls字段的样式定义:labelCls, valueCls
	//-----------fields数组包含为字段定义对象:输入字段,该参数中的name，value自动放入到attr属性传递给$.fields.text,dispaly等字段生成器.
	//---------------所有field属性都将作为options传递给$.fields中支持的字段生成器
	//---------------text字段中文标签(如果为空，则不显示整个th)，type字段输入框类型，必须是$.fields中支持的字段(text,display,dict_combobox等)
	//---------------colspan,rowspan字段占据行数和列数，默认均为1,tdCls,字段所在td的样式
	//-----------buttons 自定义按钮,text显示文本,cls显示样式,callback点击事件:非false时如果是函数直接回调，否则作为url跳转,
	generate: function(selector, options){
		var generator = $.container[(options.type || 'form')];
		var items = options.items || [];
		var fields = options.fields || [];
		var containerId = options.id || $.fields.random('container'); 
		options.id = containerId;
		var containerSelector = '#' + containerId;
		delete options.type;
		delete options.items;
		generator.call(this, selector, options);
		if (fields.length > 0){
			var tpl = $.container.getFieldsLayout(fields, options.fieldCols || 1, options.fieldCls || {}) || '这是一个不能发生的错误，请检查参数是否正确！';
			$(containerSelector).append(tpl);
			$.container.fillFields(containerSelector, fields);
			if (options.read){
				$.fields.ajaxLoadFormData({
					url: options.read, 
					formSelector: containerSelector, 
					title: options.title,
					callback: options.afterRead
				});
			}
			$(containerSelector).append('<div style="padding-bottom:10px;"></div>');
		}
		for (var i in items){
			$.container.generate(containerSelector, items[i]);
			$(containerSelector).append('<div style="padding-bottom:10px;"></div>');
		}
		if (options.buttons && options.buttons.length > 0){
			var btns = $('<div id="buttons-container" class="buttonArea operation alignRight"></div>');
			$(containerSelector).append(btns);
			for (var j in options.buttons){
				var btnModel = options.buttons[j];
				var btn = $('<input type="button"/>');
				btn.addClass(btnModel.cls || '');
				btn.attr('value', btnModel.text || '');
				btn.attr('id', btnModel.id || '');
				btns.append(btn);
				if (btnModel.callback){
					if ($.isFunction(btnModel.callback)){
						btn.click(btnModel.callback);
					}
					else{
						btn.click(function(){
							window.location = btnModel.callback;
						});
					}
				}
				if (btnModel.hidden == true){
					btn.hide();
				}
			}
		}
		if (options.padding){
			$(containerSelector).css('padding', options.padding);
		}
		if (options.margin){
			$(containerSelector).css('margin', options.margin);
		}
		return containerId;
	},
	row: function(selector, options){
		options = options || {};
		var container = $('<div class="row-layout"></div>');
		container.attr('id', options.id);
		$(selector).append(container);
	},
	//title:面板标题
	panel: function(selector, options){
		var container = $('<div class="container-form"></div>');
		$(selector).append(container);
		if (options.title){
			var title = $('<div class="container-form-title"></div>');
			var label = $('<div class="container-form-title-text"></div>');
			label.text(options.title);
			title.append(label);
			container.append(title);
			var btn = $('<input type="button" class="micon-expand container-form-title-collpase"/>');
			btn.css('height', '30px');
			btn.css('margin-top', '0px');
			btn.click(function(){
				var btn = this;
				var body = $('.container-form-body', $(btn).parent().parent());
				if (body.attr("collapse") == "true"){
					body.show();
					body.attr("collapse", "false");
					$(btn).removeClass("micon-collapsed");
					$(btn).addClass("micon-expand ");
				}
				else{
					body.hide();
					body.attr("collapse", "true");
					$(btn).removeClass("micon-expand");
					$(btn).addClass("micon-collapsed");
				}
			});
			title.append(btn);
		}
		var body = $('<div class="container-form-body"></div>');
		body.attr('id', options.id)
		container.append(body);
	},
	//selector 容器选择器
	//options 选项,参数包括:
	//-------title:表格标题
	//-------readonly:表格只读
	//-------add: 新增小按钮回调函数,add为空时，不显示按钮
	//-------del:删除小按钮回调函数
	//-------当del为空时,delUrl:删除URL，自动将ID列以‘,’分割进行默认删除
	//-------fieldClass,attr参照fields的字段定义，所有参数会放置table元素,如attr{ID:'table'},可以使用该id生成jggrid
	grid: function(selector, options){
		var container = $('<div class="grid-container"></div>');
		if (options.title){
			var title = $('<div class="grid-title"></div>');
			var actions = $('<div class="grid-actions"></div>');
			title.append(actions);
			title.append('<div class="grid-title-text">' + options.title + '</div>');
			container.append(title);
			if (options.readonly != true && options.add){
				var addBtn = $('<input type="button" class="bttn bicon-grid-add" title="新增"/>');
				addBtn.click(options.add);
				actions.append(addBtn);
			}
			if (options.readonly != true && options.modify){
				var modifyBtn = $('<input type="button" class="bttn bicon-grid-modify" title="修改"/>');
				modifyBtn.click(options.modify);
				actions.append(modifyBtn);
			}
			if (options.readonly != true && (options.delUrl || options.del)){
				var delBtn = $('<input type="button" class="bttn bicon-grid-del" title="删除"/>');
				actions.append(delBtn);
				var delfn = options.del;
				if (!delfn){
					delBtn.click(function(){
						var me = this;
						var ids = $(grid).getGridParam("selarrrow");
						if(ids==null||ids.length<1){
							$.dialog.alert("请选择删除数据！");
							return false;
						}
						$.dialog.confirm('确认要删除记录吗？', function(){
							$.ajax({
								   type: "GET",
								   async:false,
								   url: options.delUrl,
								   data: {
									   ids: '' + ids
								   },
								   dataType: "json",
								   success: function(data){
									   if (data.success){
										   $(grid).trigger("reloadGrid");
									   }
									   else{
										   $.dialog.error("无法删除！请稍候再试 .");
									   }
								   },
								   error: function(){
									   $.dialog.error("无法删除！请稍候再试 .");
								   }
							});
						});
					});
				}
			}
		}
		var grid = $('<table></table>');
		container.append(grid);
		grid.attr('id', options.id);
		if (options.pagerId){
			container.append('<div id="' + options.pagerId + '"></div>');
		}
		$(selector).append(container);
	},
	//生成表单布局模板，fields为字段定义,cols为表单布局列数
	getFieldsLayout: function(fields, cols, cls){
		cols = cols || 1;
		cls = $.extend({
			labelCls : 'form-td-label-120',
			valueCls : '',
			tableCls: 'form-table',
			trCls: 'form-table-tr',
			tdCls: 'form-table-td'
		}, cls || {});
		var getTdTpl = function(field){
			//字段显示,标签和输入放入1个table,只占layout的1个td
			var td = '<table class="form-table-cell"><tr>';
			if (field.text){
				td += '<th class="form-td-label ' + cls.labelCls || '';
				if (field.required){
					td += ' field-required-label';
				}
				td += '">' + field.text + ':</th>';
			}
			td += '<td class="form-td-input ' + (cls.valueCls || '') + '"/>';
			td += '</tr></table>';
			return td;
		};
		var getEmptyRow = function(){
			var row = [];
			for (var j = 0; j < cols; ++j){
				row[j] = 0;
			}
			return row;
		};
		var used = [];
		var setPosition = function(rowspan, colspan, curRow, curCol){
			//扩容
			for (var i = used.length; i < (curRow + rowspan + 1); ++i){
				used.push(getEmptyRow());
			}
			//查找可用位置
			for (; curRow < used.length; ++curRow){
				var index = $.fn.indexOf(used[curRow].slice(curCol), 0);
				if (index < 0){
					curCol = 0;
				}
				else{
					curCol += index;
					break;
				}
			}
			//标记跨行占用
			used[curRow][curCol] = rowspan;
			for (var i = 1; i < rowspan; ++i){
				used[curRow + i][curCol] = -1;
			}
			var needColspan = colspan;
			var realColspan = 1;
			var j = curCol + 1;
			while (needColspan > 1){
				//查找右侧空白td,尽可能的满足colspan
				if (used[curRow][j] == 0){
					used[curRow][j] = -1;
					++realColspan;
					++j;
					--needColspan;
				}
				else{
					break;
				}
			}
			return {row:curRow, col:curCol, colspan: realColspan, rowspan: rowspan};
		}
		var curCol = 0;
		var curRow = 0;
		var validFields = [];
		for (var i in fields){
			var field = fields[i];
			if (field.type != 'hidden'){
				var rowspan = field.rowspan || 1;
				var colspan = field.colspan || 1;
				var pos = setPosition(rowspan, colspan, curRow, curCol);
				curRow = pos.row;
				curCol = pos.col;
				field.rowspan = pos.rowspan;
				field.colspan = pos.colspan;
				validFields.push(field);
			}
		}
		var container = $('<div></div>');
		var table = $('<table></table>');
		table.addClass(cls.tableCls);
		container.append(table);
		var k = 0;
		//var tdWidth = Math.floor(1000 / cols) / 10;
		for (var i = 0; i < used.length; ++i){
			var tr = $('<tr></tr>');
			tr.addClass(cls.trCls);
			var validTd = 0;
			for (var j = 0; j < cols; ++j){
				if (used[i][j] == -1){
					continue;//-1表示占位符
				}
				var td = $('<td></td>');
				td.addClass(cls.tdCls);
				if(used[i][j] > 0){//放入表单td
					var field = validFields[k];
					++k;
					//td.css('width', tdWidth * field.colspan + '%');
					td.attr($.extend({
						rowspan: field.rowspan,
						colspan: field.colspan,
						id: field.id || ''
					}, field.tdAttr || {}));
					td.addClass(field.tdCls || '');
					if (field.type != null){
						td.append(getTdTpl(field));
					}
					else{
						td.append(field.text || '');
					}
					++validTd;
				}
				tr.append(td);
			}
			if (validTd > 0){
				table.append(tr);
			}
		}
		return container.html();
	},
	fillFields: function(selector, fields){
		var tds = $('td.form-td-input', $(selector));
		var j = 0;
		for (var i in fields){
			var field = fields[i];
			var container = null;
			if (!field.type){
				continue;
			}
			else if (field.type == 'hidden'){
				container = $(selector);
			}
			else{
				container = $(tds[j]);
				j += 1;
			}
			var fieldToBeAdd = [];
			
			//增加组合字段支持
			if (field.type == 'fieldset'){
				fieldToBeAdd = field.fields || [];
			}
			else{
				fieldToBeAdd.push(field);
			}
			var fWidth = 0;
			if (fieldToBeAdd.length > 1){
				//只有多个控件时，才固定大小
				fWidth = Math.floor((container.width() - 20 - 5 * fieldToBeAdd.length) * 10 / fieldToBeAdd.length) / 10;
			}			
			for (var i in fieldToBeAdd){
				var realField = fieldToBeAdd[i];
				var options = $.extend({}, realField);
				options.fieldClass = 'form-input ' + (realField.fieldClass || '');
				options.attr = $.extend({
					name:realField.name, 
					value:realField.value || '',
					required: realField.required,
					label: realField.text
				}, realField.attr || {});
				if (fWidth > 0){
					options.attr.style = 'width:' + fWidth + 'px;margin-right:5px;';
				}
				$.fields[realField.type](container, options);
			}

		}
	},
	//options.read表单初始化数据获取URL
	//options.cls 表单容器div样式
	//options.save options.yes, save为false不添加保存按钮，否则添加。saveVal为保存按钮的显示文字
	//-----------------save为函数时点击按钮直接回调save,否则会自动提交表单到save,成功时回调yes，saveBtnId保存按钮ID
	//options.cancel,cancelVal,cancel为false时，不显示按钮，cancal为函数时，点击按钮直接回调该函数，否则将cancel作为全局url进行跳转，
	//cancelVal为显示文字,cancelBtnId cancel按钮的ID
	//提交成功的回调函数 yes保存成功回调
	form: function(selector, options){
		var formId = options.id;
		var container = $('<div class="' + (options.cls || '') + '"></div>');
		var form = $('<form></form>');
		form.attr('id', formId);
		container.append(form);
		$(selector).append(container);
		options.buttons = options.buttons || [];
		if (options.save){
			var fn = function(){
				$.fields.checkForm('#' + formId,{
					yes : options.yes,
					title: options.title,
					validate: options.validate,
					url: options.save,
					mask: options.mask == false || true
				});
			}
			if ($.isFunction(options.save)){
				fn = options.save;
			}
			options.buttons.push({
				text: options.saveVal || '保存',
				cls: 'bttn bicon-save',
				id: options.saveBtnId,
				callback: fn
			});
		}
		if (options.cancel){
			var fn = $.isFunction(options.cancel) ? options.cancel : function(){ window.location = options.cancel; };
			var callback = fn;
			if (options.save && options.confirm != false){
				var callback = function(){
					$.dialog({
						title: '保存提示',
						id: 'confirm.png',
						icon: 'confirm.png',
						fixed: true,
						lock: true,
						width:250,
						content: '请确认本次-' + options.title + '-的修改是否已保存?',
						resize: false,
						parent: parent || null,
						okVal: '我已保存,直接退出',
						cancelVal: '返回修改',
						cancel: true,
						ok: function(here){
							return fn.call();
						}
					});
				};
			}
			options.buttons.push({
				text: options.cancelVal || '返回',
				cls: 'bttn bicon-return',
				id: options.cancelBtnId,
				callback: callback
			});
		}
	},
	findFirst: function(options, type, cmp){
		if (!options){
			return null;
		}
		type = type ? type : 'form';
		if ($.isArray(options)){
			for (var i in options){
				var option = options[i];
				var finded = $.container.findFirst(option, type, cmp);
				if (finded){
					return finded;
				}
			}
		}
		else {
			var realType = options.type ? options.type : 'form';
			if (realType == type && cmp.call(this, options)){
				return options;
			}	
			else{
				return $.container.findFirst(options.items, type, cmp);
			}
		}
	},
	//字典下拉列表默认弹出显示存在问题，需为field添加属性zIndex: 1990或更大值
	//options为container定义
	//popupOptions.formId,保存表单的Id,如果没有配置，则查找第一个save不为空的form
	//popupOptions。afterLoad 界面初始化后回调
	//popupOptions其它选项为$.dialog的参数配置
	//提交的表单配置必须包括
	//提交成功的回调函数 yes保存成功回调
	//其它参数为$.dialog的参数
	popup: function(options, popupOptions){
		var container = $('<div class="popup-content-container"></div>');
		var containerId = $.fields.random("popup-div");
		container.attr('id', containerId);
		popupOptions = popupOptions || {};
		if (!popupOptions.ok){
			var formId = popupOptions.formId;
			if (!formId){
				var submitForm = $.container.findFirst(options, 'form', function(item){
					return item.save;
				});
			}
			else{
				var submitForm = $.container.findFirst(options, 'form', function(item){
					return item.id == formId;
				});
			}
			var fn = false;
			var title = '';
			if (submitForm){
				title = submitForm.title || '';
				formId = submitForm.id ? submitForm.id : $.fields.random("popupForm");
				submitForm.id = formId;
				var save = submitForm.save;
				var fn = function(){
					return $.fields.checkForm('#' + formId,{
						yes : submitForm.yes,
						title: submitForm.title,
						validate: submitForm.validate,
						url: save,//复制save的值
						mask: submitForm.mask == true
					});
				}
				if ($.isFunction(save)){
					fn = save;
				}
				submitForm.save = false;//隐藏自带的保存按钮
			}
		}
		else{
			var fn = popupOptions.ok;
		}
		popupOptions = $.extend({
			width : '350px',
			height: '250px',
			fixed : true,
			lock : true,
			title: popupOptions.title || title,
			cover:true,
			content: container[0].outerHTML,
			cancelVal : '取消',
			cancel : true,
			okVal: '保存',
			ok: fn,
			init: function(){
				$.container.generate('#' + containerId, options);
				if (popupOptions.afterLoad){
					popupOptions.afterLoad.call(popupOptions.scope || this);
				}
			}
		}, popupOptions);
		$.dialog(popupOptions);
	},
	//弹出式iframe无边框，url链接,close,关闭函数，iframe需触发body的finished自定义事件才可以关闭
	popupPure: function(options){
		var $div = $('<div  class="popup-div-iframe"></div>');
		$div.css({
			width: $('body')[0].scrollWidth,
			height: $('body')[0].scrollHeight
		});
		$('body').append($div);
		var $iframe = $('<iframe class="popup-iframe" frameborder="0" allowTransparency="true" scrolling="no"></iframe>');
		$iframe.attr('src', options.url);
		$iframe.css({
			top: 0
		});
		$('body').append($iframe);
		var screenHeight = $(window).height();
		$iframe.one('load', function(){
			$iframe.get(0).contentWindow.$('body').one('finished', function(event, success){
				$iframe.remove();
				$div.remove();
				if ($.isFunction(options.close)){
					options.close.call(this, success);
				}
			});
		});
	}
});
})(jQuery);