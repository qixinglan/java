/*
 * jQuery SimpleTree Drag&Drop plugin Update on 22th May 2008 Version 0.3
 * 
 * Licensed under BSD <http://en.wikipedia.org/wiki/BSD_License> Copyright (c)
 * 2008, Peter Panov <panov@elcat.kg>, IKEEN Group http://www.ikeen.com All
 * rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Peter Panov, IKEEN Group nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY Peter Panov, IKEEN Group ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Peter Panov, IKEEN Group BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

// 将json数据转成html。
$.extend({
	//允许设置下拉列表的显示字段和值字段  夏先智 2014-06-09
	parseHTMLForTree : function(jsonArr, options){
		var opts = $.extend({
			valueAttr: 'id',
			valueField: 'code',
			displayField: 'codeDesc',
			selectableField: 'selectable',
			childrenField: 'children'
		}, options || {});
		var html = '';
		$.each(jsonArr, function(){
			html +='<li ' + opts.valueAttr + '="'+this[opts.valueField]+'" selectable="'+(this[opts.selectableField] || "true")+'"><span class="text">'+this[opts.displayField]+'</span>';
			if(this[opts.childrenField] && this[opts.childrenField].length>0){
				html += "<ul>";
				html += $.parseHTMLForTree(this[opts.childrenField], options);
				html += "</ul>";
			}
			html += '</li>';
		});
		return html;
	}
});


$.fn.simpleTree = function(opt) {
	return this.each(function() {
		$(this).siblings('.root-loading:first').show();
		
		var TREE = this;
		var ROOT = $('.root', this);
		var mousePressed = false;
		var mouseMoved = false;
		var dragMoveType = false;
		var dragNode_destination = false;
		var dragNode_source = false;
		var dragDropTimer = false;
		var ajaxCache = Array();

		/**
		 * added by zhaomeidan 2010-04-19 beforeAjax : 展开AJAX之前的事件接口
		 */
		var beforeAjax = false;

		TREE.option = {
			drag : false,
			animate : false,
			autoclose : true,
			speed : 'fast',
			showIcon : true,
			showRoot : true,
			pageSize : false,
			docClass : 'doc',
			docLastClass : 'doc-last',
			lineClass : 'line',
			afterAjax : false,
			afterMove : false,
			beforeClick : false,
			afterClick  : false,
			afterRootClick : false,
			afterDblClick : false,
			beforeContextMenu : false,
			afterContextMenu : false,
			docToFolderConvert : true,
			beforeAjax : false, // beforeAjax : 展开AJAX之前事件
			selectEabled : false ,//单选框树节点是否可以选中判断事件
			openable : false,//是否展开该节点,function(){}
			beforeDelete: false,//删除节点前的function，若返回false，则不能删除
			afterDelete:false,//删除节点后的function

			/**
			 * 复选框属性及状态标志 默认属性，支持重新定义 by zhaomeidan 2010-04-19 例： <span
			 * ck='a'>表示全选</span>
			 */
			nodeCheckBox : false,//多选框启用与否
			ckFlag : 'ck', // 标签中的元素
			allCheckFlag : 'a', // 全选
			halfCheckFlag : 'h', // 半选
			noCheckFlag : 'n', // 没选
			checkBoxClick : false, // 多选框事件
			cascadeParentChecked : true,//级联选中上级节点
			cascadeChildrenChecked : true,//级联选中下级节点
			cascadeAllShowParent : false,
			ignoreIndeterminate : true,//是否忽略半选，为true时获取选择节点时不包含半选节点
			valueAttr : "id",
			codeAttr:"code",//shenlj add 2012-08-22 用于获取某一节点的code值。
			codeDescAttr:"codedesc",
			ignoreValues : false,//忽略不显示的选项,字符串，逗号分隔
			onlyValues   :false,
			selectValues :""   //在多选情况下，传递文本框中的数据
		};

		TREE.option = $.extend(TREE.option, opt);
		if(!TREE.option.showIcon){
			TREE.option.docClass = 'doc-noIcon';
			TREE.option.docLastClass = 'doc-noIcon-last';
			TREE.option.lineClass = 'line-noIcon';
		}
		
		if(TREE.option.showRoot){
			ROOT.removeClass('root').addClass('root_show');
			if(TREE.option.afterRootClick){
				var $rootSpan = ROOT.find(">span");
				$rootSpan.hover(function(){
					if(!$rootSpan.hasClass('activeRoot')){
						$rootSpan.addClass('hoverRoot');
					}
				}, function(){
					$rootSpan.removeClass('hoverRoot');
				});
				$rootSpan.click(function(){
					$('.active', TREE).removeClass('active');
					$rootSpan.removeClass('hoverRoot').addClass('activeRoot');
					TREE.option.afterRootClick();
				});
			}
		}
		$.extend(this, {
          getSelecteCodeValue : function() {// add by shenlj 2012-08-22 为了取某一节点的code值与codedesc值 
            var sels = this.getSelections();
            var valObj = {
              text : "",//显示值
              value : "",//code值
              codedesc : ""//对应的codedesc值
            };
            for(var i=0; i<sels.length; i++){
              var qc = $(sels[i]).attr("qc");
              if (qc) {
                valObj.text += qc+",";
              } else {
                valObj.text += $(">div span.showValue", sels[i]).text()+",";
              }
              
              valObj.value += $(sels[i]).attr(this.option.codeAttr)+",";
              valObj.codedesc += $(sels[i]).attr(this.option.codeDescAttr)+",";
            }
            if(sels.length>0){
              valObj.text = valObj.text.substr(0, valObj.text.length-1);
              valObj.value = valObj.value.substr(0, valObj.value.length-1);
              valObj.codedesc = valObj.codedesc.substr(0, valObj.codedesc.length-1);
            }
            return valObj;
          },
					getSelected : function() {
						return $('div.active', this).parent();
					},
					getSelectedParent : function() {
						var $val =$('li.folder-open span .showValue:first', this);
						var valObj = {
							text : "",
							value : ""
						};
						if($val.text()!=null){
							valObj.text = $val.text();
							valObj.value = ($('li.folder-open', this).attr(this.option.valueAttr));
						}
						return valObj;
					},
					getSelectionsValue : function() {
						var sels = this.getSelections();
						var valObj = {
							text : "",
							value : ""
						};
						for(var i=0; i<sels.length; i++){
							var qc = $(sels[i]).attr("qc");
							if (qc) {
								valObj.text += qc+",";
							} else {
								valObj.text += $(">div span.showValue", sels[i]).text()+",";
							}
							
							valObj.value += $(sels[i]).attr(this.option.valueAttr)+",";
						}
						if(sels.length>0){
							valObj.text = valObj.text.substr(0, valObj.text.length-1);
							valObj.value = valObj.value.substr(0, valObj.value.length-1);
						}
						return valObj;
					},
					getSelections : function() {//该功能查询出所有选中节点的li集合，同时包括其下的ul，但ul中未过滤未选中的节点，故使用时需自行过滤
						if(this.option.nodeCheckBox){
							//修复多选无值BUG
							var $sels = $("input[type=checkbox][checked=checked][indeterminate!=true]", this).parent().parent().parent().filter("[selectable!= 'false']");
							if(this.option.cascadeAllShowParent && this.option.cascadeChildrenChecked){
								$sels = $sels.filter(function(){
									var $self = $(this);
									if($self.parents("ul").siblings('div').find(":checkbox:checked[indeterminate!=true]").length > 0){
										return false;
									}
									return true;
								});
							}else if(!this.option.ignoreIndeterminate){
								//修复多选无值BUG
								$sels = $("input[type=checkbox][checked=checked]", this).parent().parent().parent().filter("[selectable!= 'false']");
							}
							return $sels;
						}
						return $('div.active', this).parent();
					},
					clearSelections : function() {
						if(this.option.nodeCheckBox){
							$("input[type=checkbox][checked=true]", this).attr('checked', false).attr("indeterminate", false);
						}else{
							$('div.active', this).removeClass('active');	
						}
					}
				});

		TREE.select = function(node){
			var $node = $(node);
			if($node.attr('selectable') == 'false'){
				return false;
			}
			
			$('.active', TREE).removeClass('active');
			ROOT.find(">span").removeClass('activeRoot');
			var $divChild = $node.children('div');
			$divChild.removeClass('hover').addClass('active');
			return true;
		}
		
		TREE.closeNearby = function(obj) {
			$(obj).siblings().filter('.folder-open, .folder-open-last').each(
					function() {
						var childUl = $('>ul', this);
						var className = this.className;
						this.className = className.replace('open', 'close');
						if (TREE.option.animate) {
							childUl.animate({
										height : "toggle"
									}, TREE.option.speed);
						} else {
							childUl.hide();
						}
					});
		};

		TREE.nodeToggle = function(obj) {
			var childUl = $('>ul', obj);
			if (childUl.is(':visible')) {
				obj.className = obj.className.replace('open', 'close');

				if (TREE.option.animate) {
					childUl.animate({
								height : "toggle"
							}, TREE.option.speed);
				} else {
					childUl.hide();
				}
			} else {
				obj.className = obj.className.replace('close', 'open');
				if (TREE.option.animate) {
					childUl.animate({
								height : "toggle"
							}, TREE.option.speed, function() {
								if (TREE.option.autoclose)
									TREE.closeNearby(obj);
								if (childUl.is('.ajax'))
									TREE.setAjaxNodes(childUl, obj.id);
							});
				} else {
					childUl.show();
					if (TREE.option.autoclose)
						TREE.closeNearby(obj);
					if (childUl.is('.ajax'))
						TREE.setAjaxNodes(childUl, obj.id);
				}
			}
		};

		/**
		 * 执行AJAX的节点，并将结果长出在树上
		 */
		TREE.setAjaxNodes = function(node, parentId, callback) {
			if ($.inArray(parentId, ajaxCache) == -1) {// 判断parentId是否属于字符串ajaxCache中的一员
				ajaxCache[ajaxCache.length] = parentId;
				var url = $.trim($('>li', node).text());

				if (url && url.indexOf('url:')) {
					url = $.trim(url.replace(/.*\{url:(.*)\}/i, '$1'));
					$.commAjax({
								type : "GET",
								url : url,
								contentType : 'html',
								cache : false,
								onSuccess : function(responce) {
									if (typeof TREE.option.beforeAjax == 'function') {
										// AJAX节点展开前动作
										TREE.option.beforeAjax(node);
									}
									
									node.removeAttr('class');
									node.html(responce);
									$.extend(node, {
												url : url
											});
									TREE.setTreeNodes(node, true);
									if (typeof TREE.option.afterAjax == 'function') {
										TREE.option.afterAjax(node);
									}
									if (typeof callback == 'function') {
										callback(node);
									}
									$('.root-loading').hide();
								}
							});
				}
			}
		};

		/**
		 * 添加节点
		 */
		TREE.setTreeNodes = function(obj, useParent) {
			obj = useParent ? obj.parent() : obj;
			
			// 针对span进行操作
			var nodes_ = [];
			nodes_ = $('li>span', obj);
			nodes_.addClass('text');
			nodes_.wrapInner('<span class="showValue"></span>');
			nodes_.wrap('<div></div>');
			
			nodes_ = $('li>div', obj);
			
			nodes_.bind('selectstart', function() {
						return false;
					});
			nodes_.click(function(e) {
						var $this = $(this);
						if($(e.target).is('.trigger')){
							return;
						}
						
						if(typeof TREE.option.selectEabled == 'function'){
							if(!TREE.option.selectEabled($(this).parent())){
								return;
							}
						}
						
						if (typeof TREE.option.beforeClick == 'function' && TREE.option.beforeClick($(this).parent()) == false) {
							return;
						}
						var selectable = TREE.select($this.parent());
						if(!selectable) return false;
						if (typeof TREE.option.afterClick == 'function') {
							TREE.option.afterClick($(this).parent());
						}
						if (TREE.option.nodeCheckBox) {
							var $input = $("input[type=checkbox]", $(this))
									.eq(0);
								
							$input.attr("checked", !$input.attr("checked"));
							TREE.checkBoxClickDefault($input);
							if (typeof TREE.option.checkBoxClick == 'function') {// 如果事件被重写，则利用重写的事件规则
								TREE.option.checkBoxClick.call(TREE, $input);
							}
						}
					});
			nodes_.dblclick(function() {
						mousePressed = false;
						TREE.nodeToggle($(this).parent()[0]);
						if (typeof TREE.option.afterDblClick == 'function') {
							TREE.option.afterDblClick($(this).parent());
						}
					});
			nodes_.bind("contextmenu", function(e) {
						var $this = $(this);
						if (typeof TREE.option.beforeContextMenu == 'function' && TREE.option.beforeContextMenu($(this).parent(), e) == false) {
							return;
						}
						TREE.select($this.parent());
						if (typeof TREE.option.afterContextMenu == 'function') {
							TREE.option.afterContextMenu($(this).parent(), e);
						}
						return false;
					});
			nodes_.mousedown(function(event) {
				mousePressed = true;
				cloneNode = $(this).parent().clone();
				var LI = $(this).parent();
				if (TREE.option.drag) {
					$('>ul', cloneNode).hide();
					$('body')
							.append('<div id="drag_container"><ul></ul></div>');
					$('#drag_container').hide().css({
								opacity : '0.8'
							});
					$('#drag_container >ul').append(cloneNode);
					$("<img>").attr({
								id : "tree_plus",
								src : "images/plus.gif"
							}).css({
								width : "7px",
								display : "block",
								position : "absolute",
								left : "5px",
								top : "5px",
								display : 'none'
							}).appendTo("body");
					$(document).bind("mousemove", {
								LI : LI
							}, TREE.dragStart).bind("mouseup", TREE.dragEnd);
				}
				return false;
			});
			nodes_.mouseup(function() {
						if (mousePressed && mouseMoved && dragNode_source) {
							TREE.moveNodeToFolder($(this).parent());
						} 
						TREE.eventDestroy();
					});
			nodes_.mouseover(function() {
				var $parent = $(this);
				if(!$parent.hasClass('active')){
					$parent.addClass('hover');
				}	
			});
			nodes_.mouseout(function() {
				var $parent = $(this);
				if($parent.hasClass('hover')){
					$parent.removeClass('hover');
				}	
			});

			/**
			 * 生长出多选框
			 * 
			 * @author zhaomeidan
			 */
			if (TREE.option.nodeCheckBox) {
				
				var parentChecked_ = 0; // 全选框默认 undefined状态
				var parentIndeterminate_ = 0; // 半选框默认 undefined状态
				
				if ($("input:first", obj).attr("checked") == true) {
					parentChecked_ = 2; // 全选
				} else if ($("input:first", obj).attr("checked") == false) {
					parentChecked_ = 1; // 没选
				}

				if ($("input:first", obj).attr("indeterminate") == true) {
					parentIndeterminate_ = 2; // 半选
				} else if ($("input:first", obj).attr("indeterminate") == false) {
					parentIndeterminate_ = 1; // 没选
				}

				nodes_.children('span').each(function() {
					
					// 纯净标签
					var cls = '';
					if(!$.browser.msie || $.browser.version != '7.0'){
						cls = " class='checkbox'";
					}
					var nodeHtml = "&nbsp;<input type='checkbox' onclick='event.cancelBubble=true;'"+cls+"/>";
					
					if($(this).parent().parent().attr('selectable') == 'false'){
						nodeHtml="";
					}
					nodeHtml += $(this).html();
				    $(this).html(nodeHtml);
				   
				    if (!TREE.option.cascadeChildrenChecked) {
				    	return;
				    }
					var input_ = $("input[type=checkbox]", $(this)).eq(0);
					
					// zhougz 2011.9.7打开注释 
					if($(this).parent().parent().attr('selectable') == 'false'){
						$("input[type=checkbox]", $(this)).attr('disabled', 'disabled');
					}
					// 如果父节点全选，则将所有下级子节点全选
					if (parentChecked_ == 2 && parentIndeterminate_ != 2)
						$(this).attr(TREE.option.ckFlag,
								TREE.option.allCheckFlag);
					if (parentChecked_ == 1 && parentIndeterminate_ == 1)
						$(this).attr(TREE.option.ckFlag,
								TREE.option.noCheckFlag);
					// 状态构造
					if ($(this).attr(TREE.option.ckFlag) == undefined || // 没有设置标志，默认认为没选
							$(this).attr(TREE.option.ckFlag) == TREE.option.noCheckFlag) {// 没选
						$(this).attr(TREE.option.ckFlag,
								TREE.option.noCheckFlag);// 修正 undefined 设置
						TREE.checkBoxStatus(input_, false, false);
					} else if ($(this).attr(TREE.option.ckFlag) == TREE.option.halfCheckFlag) {// 半选
						TREE.checkBoxStatus(input_, true, false);
					} else if ($(this).attr(TREE.option.ckFlag) == TREE.option.allCheckFlag) {// 全选
						TREE.checkBoxStatus(input_, false, true);
					}
		
					input_.click(function(e) {
								TREE.checkBoxClickDefault(input_);
								if (typeof TREE.option.checkBoxClick == 'function') {// 如果事件被重写，则利用重写的事件规则
									TREE.option.checkBoxClick.call(TREE, input_);
								}
							});
					/* ========add by qiujie 新增反显功能========== */		
					var vals = TREE.option.selectValues;
					
					if(!$.isNull(vals)){
					
						var input1 = $(this).parent().parent().parent().parent();
							var parentInput = $("input", input1).eq(0);// 找到父节点的选择框
							var parentSel = $(parentInput).parent().parent().parent();  //获取到父节点的li对象
							var len =(parentSel.attr("id")).length;  //获取父节点id的长度
							var parentId = parentSel.attr("id"); //获取父节点li的id
							var inputId = ($(this).parent().parent().attr('id')).substr(0,len);  //获取当前check的li的id
						if(vals.indexOf(",")>0){
							
						var val = vals.split(",");
						for(var i=0;i<val.length;i++){
							
							if(val[i]==$.trim($(this).text())){
								input_.attr('checked', 'checked');
								if(!parentInput.attr('checked')&&parentId==inputId){
									$(parentInput).attr("indeterminate", true);
								}
								
								return;
							}
						}
					}else{
						
						if(vals==$.trim($(this).text())){
							input_.attr('checked', 'checked');	
							if(parentId==inputId){
								$(parentInput).attr("indeterminate", true);
							}
								
	
						}
					}
						
					}
				});
				$(this).parents('form').bind('reset', function(){
					$(":checkbox[indeterminate=true]").attr("indeterminate", false);
				});
				
			}

			// 针对li进行操作
			var li_ = [];
			li_ = $('li', obj);
			if(!TREE.option.showIcon){
				li_ .css("lineHeight", "14px");
			}

			li_.each(function(i) {
						var $li_ = $(this);
						if(TREE.option.onlyValues && (","+TREE.option.onlyValues+",").indexOf(","+$li_.attr(TREE.option.valueAttr)+",")<=0){
							$li_.remove();
							return;
						}
						if(TREE.option.ignoreValues && (","+TREE.option.ignoreValues+",").indexOf(","+$li_.attr(TREE.option.valueAttr)+",")>=0){
							$li_.remove();
							return;
						}
						if(TREE.option.openable && TREE.option.openable($li_) != false){
							this.className = 'open';
						}
						if($li_.attr("newTip") == "true"){
							$li_.find(">div").prepend("<span class='new'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
						}
						if($li_.attr("showDelete") == "true"){
							var $delete = $("<span class='delete' title='删除'>&nbsp;&nbsp;&nbsp;</span>");
							$delete.insertAfter($li_.find(">div span.showValue"));
							$delete.click(function(){
								TREE.delNode($li_);
							})
						}
						var className = this.className;
						var open = false;
						var cloneNode = false;
						var LI = this;
						var childNode = $('>ul', this); // 当前li的子集
						if (childNode.size() > 0) {// 如果存在子集
							var setClassName = 'folder-';
							if (className && className.indexOf('open') >= 0) { // 判断子集状态
								setClassName = setClassName + 'open';
								open = true;
							} else {
								setClassName = setClassName + 'close';
							}
							// 判断是否是末节点，给予 -last样式
							this.className = setClassName
									+ ($(this).is(':last-child') ? '-last' : '');
							// 子集未打开或者为AJAX节点，隐藏子集
							if (!open || className.indexOf('ajax') >= 0){
								childNode.show();
							}

							TREE.setTrigger(this);
						} else {
							var setClassName = TREE.option.docClass;
							this.className = setClassName
									+ ($li_.is(':last-child') ? '-last' : '');
						}
					});
			li_.before('<li class="'+TREE.option.lineClass+'"></li>');
			li_.filter(':last-child').after('<li class="line-last"></li>');

			TREE.setEventLine($('.'+TREE.option.lineClass+', .line-last', obj));
			/* ========add by zhaomeidan 修改为可从根节点进行远程数据加载========== */
			if (!useParent) {
				var childUl = $('>ul', obj);
				if (childUl.is('.ajax')){
					TREE.setAjaxNodes(childUl, obj.attr('id'));
				}else{
					$('.root-loading').hide();
					if (typeof TREE.option.afterAjax == 'function') {
						TREE.option.afterAjax(obj);
					}
				}
			}
			/* ============================================================== */
		};

		// 复选框状态控制 by zhaomeidan
		TREE.checkBoxStatus = function(inputCheck, indeterminate, checked) {
			var $inputCheck = $(inputCheck);
			if($inputCheck.parent().parent().parent().attr('selectable') == 'false'){
				return;
			}
			// 判断类型，这里必须是BOOLEAN类型
			if (typeof(indeterminate) == "boolean"
					&& typeof(checked) == "boolean") {
				$inputCheck.attr("indeterminate", indeterminate);
				if ($.browser.msie) {
					$inputCheck.attr("checked", checked || indeterminate);
				} else {
					$inputCheck.attr("checked", checked);
				}
			} else {
				alert("出现参数类型异常jquery.simple.tree.js==> 318行");
			}
		};

		// 复选框默认事件 by zhaomeidan
		TREE.checkBoxClickDefault = function(input) {
			if (TREE.option.cascadeChildrenChecked) {
				if ($(input).attr("checked")) {// 如果选中
					$("input[type=checkbox]", $(input).parent().parent().parent()).each(
							function(index) {
								TREE.checkBoxStatus(this, false, true);
							});
				} else {// 没选中
					$("input[type=checkbox]", $(input).parent().parent().parent()).each(
							function(index) {
								TREE.checkBoxStatus(this, false, false);
							});
				}
			}
			if (TREE.option.cascadeParentChecked) {
				TREE.changeParentNodes($(input));
			}
		};

		/**
		 * 改变父节点状态(递归)
		 * 
		 * @param {}
		 *            node by zhaomeidan
		 */
		TREE.changeParentNodes = function(input) {
			var input_ = $(input).parent().parent().parent().parent().parent();
			// 根据根节点没有复选框的要求，来跳出当前递归
			var isRoot = true;
			
			$(">div>span", $(input_)).each(function(){
				if ($(this).html().indexOf("input") != -1 || $(this).html().indexOf("INPUT") != -1){
					isRoot = false;
				}
			});
			if(isRoot)
				return;
			var parentInput = $("input:first", $(input_)).eq(0);// 找到父节点的选择框
			var callbackValue = TREE.decideBrotherChecked(input); // 搜捕同级兄弟节点状态
			// 父节点状态
			if (callbackValue == 2) {
				TREE.checkBoxStatus(parentInput, false, true);
			} else if (callbackValue == 1) {
				
				TREE.checkBoxStatus(parentInput, true, false);
			} else if (callbackValue == 0) {
				TREE.checkBoxStatus(parentInput, false, false);
			}
			TREE.changeParentNodes($(parentInput));
		}

		/**
		 * 判断同级节点状态 return ： 全选上了： 2 半选上了： 1 都没选上： 0
		 * 
		 * @author zhaomeidan
		 */
		TREE.decideBrotherChecked = function(input) {
			var nowThis = $(input).parent().parent().parent().parent();
			var flagInteger = 0; // 返回值变量
			var temp_ = 0; // 临时变量
			var countBrother = $("input[type=checkbox]", nowThis).size();// 同级兄弟节点数
			$("input[type=checkbox]", nowThis).each(function(index) {
						if (this.checked)
							temp_++;// 选中
						if (this.indeterminate) {// 发现半选，直接判定结果，并跳出循环
							flagInteger = 1;
							return false;
						}
					});
			if (flagInteger != 1) {
				if (temp_ == 0)
					return 0;
				if (temp_ != countBrother)
					return 1;
				if (temp_ == countBrother)
					return 2;
			}
			return flagInteger;
		}

		TREE.setTrigger = function(node) {
			$(node).click(function(event) {
				var target = $(event.srcElement || event.target);
				if(target.is('li') && event.currentTarget == target[0]){
					TREE.nodeToggle(node);
				}
			});
		};

		TREE.dragStart = function(event) {
			var LI = $(event.data.LI);
			if (mousePressed) {
				mouseMoved = true;
				if (dragDropTimer)
					clearTimeout(dragDropTimer);
				if ($('#drag_container:not(:visible)')) {
					$('#drag_container').show();
					LI.prev('.'+TREE.option.lineClass).hide();
					dragNode_source = LI;
				}
				$('#drag_container').css({
							position : 'absolute',
							"left" : (event.pageX + 5),
							"top" : (event.pageY + 15)
						});
				if (LI.is(':visible'))
					LI.hide();
				var temp_move = false;
				if (event.target.tagName.toLowerCase() == 'span'
						&& $.inArray(event.target.className, Array('text',
										'active', 'trigger')) != -1) {

					var parent = event.target.parentNode;
					var offs = $(parent).offset({
								scroll : false
							});
					var screenScroll = {
						x : (offs.left - 3),
						y : event.pageY - offs.top
					};
					var isrc = $("#tree_plus").attr('src');
					var ajaxChildSize = $('>ul.ajax', parent).size();
					var ajaxChild = $('>ul.ajax', parent);
					screenScroll.x += 19;
					screenScroll.y = event.pageY - screenScroll.y + 5;

					if (parent.className.indexOf('folder-close') >= 0
							&& ajaxChildSize == 0) {
						if (isrc.indexOf('minus') != -1)
							$("#tree_plus").attr('src', 'images/plus.gif');
						$("#tree_plus").css({
									"left" : screenScroll.x,
									"top" : screenScroll.y
								}).show();
						dragDropTimer = setTimeout(function() {
									parent.className = parent.className
											.replace('close', 'open');
									$('>ul', parent).show();
								}, 700);
					} else if (parent.className.indexOf('folder') >= 0
							&& ajaxChildSize == 0) {
						if (isrc.indexOf('minus') != -1)
							$("#tree_plus").attr('src', 'images/plus.gif');
						$("#tree_plus").css({
									"left" : screenScroll.x,
									"top" : screenScroll.y
								}).show();
					} else if (parent.className.indexOf('folder-close') >= 0
							&& ajaxChildSize > 0) {
						mouseMoved = false;
						$("#tree_plus").attr('src', 'images/minus.gif');
						$("#tree_plus").css({
									"left" : screenScroll.x,
									"top" : screenScroll.y
								}).show();

						$('>ul', parent).show();
						/*
						 * Thanks for the idea of Erik Dohmen
						 */
						TREE.setAjaxNodes(ajaxChild, parent.id, function() {
									parent.className = parent.className
											.replace('close', 'open');
									mouseMoved = true;
									$("#tree_plus").attr('src',
											'images/plus.gif');
									$("#tree_plus").css({
												"left" : screenScroll.x,
												"top" : screenScroll.y
											}).show();
								});

					} else {
						if (TREE.option.docToFolderConvert) {
							$("#tree_plus").css({
										"left" : screenScroll.x,
										"top" : screenScroll.y
									}).show();
						} else {
							$("#tree_plus").hide();
						}
					}
				} else {
					$("#tree_plus").hide();
				}
				return false;
			}
			return true;
		};

		TREE.dragEnd = function() {
			if (dragDropTimer)
				clearTimeout(dragDropTimer);
			TREE.eventDestroy();
		};

		TREE.setEventLine = function(obj) {
			obj.mouseover(function() {
				if (this.className.indexOf('over') < 0 && mousePressed
						&& mouseMoved) {
					this.className = this.className
							.replace(TREE.option.lineClass, 'line-over');
				}
			}).mouseout(function() {
						if (this.className.indexOf('over') >= 0) {
							this.className = this.className
									.replace('-over', '');
						}
					}).mouseup(function() {
						if (mousePressed && dragNode_source && mouseMoved) {
							dragNode_destination = $(this).parents('li:first');
							TREE.moveNodeToLine(this);
							TREE.eventDestroy();
						}
					});
		};

		TREE.checkNodeIsLast = function(node) {
			if (node.className.indexOf('last') >= 0) {
				var prev_source = $(node).prev().prev();
				if (prev_source.size() > 0) {
					prev_source[0].className += '-last';
				}
				node.className = node.className.replace('-last', '');
			}
		};

		TREE.checkLineIsLast = function(line) {
			if (line.className.indexOf('last') >= 0) {
				var prev = $(line).prev();
				if (prev.size() > 0) {
					prev[0].className = prev[0].className.replace('-last', '');
				}
				dragNode_source[0].className += '-last';
			}
		};

		TREE.eventDestroy = function() {
			// added by Erik Dohmen (2BinBusiness.nl), the unbind mousemove
			// TREE.dragStart action
			// like this other mousemove actions binded through other actions
			// ain't removed (use it myself
			// to determine location for context menu)
			$(document).unbind('mousemove', TREE.dragStart).unbind('mouseup')
					.unbind('mousedown');
			$('#drag_container, #tree_plus').remove();
			if (dragNode_source) {
				$(dragNode_source).show().prev('.'+TREE.option.lineClass).show();
			}
			dragNode_destination = dragNode_source = mousePressed = mouseMoved = false;
			// ajaxCache = Array();
		};

		TREE.convertToFolder = function(node) {
			node[0].className = node[0].className.replace(TREE.option.docClass, 'folder-open');
			node.append('<ul><li class="line-last"></li></ul>');
			TREE.setTrigger(node[0]);
			TREE.setEventLine($('.'+TREE.option.lineClass+', .line-last', node));
		};

		TREE.convertToDoc = function(node) {
			$('>ul', node).remove();
			$('img', node).remove();
			node[0].className = node[0].className.replace(
					/folder-(open|close)/gi, TREE.option.docClass);
		};

		TREE.moveNodeToFolder = function(node) {
			if (!TREE.option.docToFolderConvert
					&& node[0].className.indexOf(TREE.option.docClass) != -1) {
				return true;
			} else if (TREE.option.docToFolderConvert
					&& node[0].className.indexOf(TREE.option.docClass) != -1) {
				TREE.convertToFolder(node);
			}
			TREE.checkNodeIsLast(dragNode_source[0]);
			var lastLine = $('>ul >.line-last', node);
			if (lastLine.size() > 0) {
				TREE.moveNodeToLine(lastLine[0]);
			}
		};

		TREE.moveNodeToLine = function(node) {
			TREE.checkNodeIsLast(dragNode_source[0]);
			TREE.checkLineIsLast(node);
			var parent = $(dragNode_source).parents('li:first');
			var line = $(dragNode_source).prev('.'+TREE.option.lineClass);
			$(node).before(dragNode_source);
			$(dragNode_source).before(line);
			node.className = node.className.replace('-over', '');
			var nodeSize = $('>ul >li', parent).not('.'+TREE.option.lineClass+', .line-last')
					.filter(':visible').size();
			if (TREE.option.docToFolderConvert && nodeSize == 0) {
				TREE.convertToDoc(parent);
			} else if (nodeSize == 0) {
				parent[0].className = parent[0].className.replace('open',
						'close');
				$('>ul', parent).hide();
			}

			// added by Erik Dohmen (2BinBusiness.nl) select node
			if (!$('span:first', dragNode_source).parent().hasClass('active')) {
				TREE.select($('span:first', dragNode_source).parent().parent());
			}

			if (typeof(TREE.option.afterMove) == 'function') {
				var pos = $(dragNode_source).prevAll(':not(.'+TREE.option.lineClass+')').size();
				TREE.option.afterMove($(node).parents('li:first'),
						$(dragNode_source), pos);
			}
		};

		TREE.addNode = function(id, text,opts, callback) {
			if(!$.isNull(opts.parentId)){
				dragNode_destination = $("#"+opts.parentId, ROOT);
			}else{
				dragNode_destination = TREE.getSelected();
			}
			if(dragNode_destination.length == 0){
				alert('请选择一个节点');
				return;
			}
			var showDelStr = '';
			if(opts.showDelete){
				showDelStr = ' showDelete="true' 
			}
			var temp_node = $('<li><ul><li id="' + id + '"'+showDelStr+'"><span class="text">' + text
					+ '</span></li></ul></li>');
			TREE.setTreeNodes(temp_node);
			dragNode_source = $('.'+TREE.option.docLastClass, temp_node);
			TREE.moveNodeToFolder(dragNode_destination);
			temp_node.remove();
			if (typeof(callback) == 'function') {
				callback(dragNode_destination, dragNode_source);
			}
		};

		TREE.delNode = function(node) {
			var selNode = TREE.getSelected();
			TREE.select(node);
			dragNode_source = node;
			if(TREE.option.beforeDelete && !TREE.option.beforeDelete.call(TREE, node)){
				TREE.select(selNode);
				return;
			}
			
			$.confirm('是否确认删除？', function(){
				TREE.checkNodeIsLast(node[0]);
			
				var parent = node.parent();
				node.prev().remove();
				node.remove();
				
				if(TREE.option.docToFolderConvert && parent.find("li:not([className ^="+TREE.option.lineClass+"])").length == 0){
					TREE.convertToDoc(parent.parent());
				}
				
				if(selNode != node){
					TREE.select(selNode);
				}
				if (TREE.option.afterDelete) {
					TREE.option.afterDelete.call(TREE);
				}
			});
		};

		TREE.init = function(obj) {
			TREE.setTreeNodes(obj, false);
		};
		TREE.init(ROOT);
	});
};

