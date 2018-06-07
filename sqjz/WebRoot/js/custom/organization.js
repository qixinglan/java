(function($) {
	"use strict";
	$.organization = $.organization || {};
	$.extend($.organization, {
		load : function(url,orgId) {
			var me = this;
			me.dict = {};
			me.map = {};
			me.rootId=orgId;
			me.fn = null;
			$.ajax({
				type : "GET",
				url : url || "/data/jggl/json.action",
				async : false,
				dataType : "json",
				success : function(data) {
					me.dict = data;
					var root = me.dict[0][0];
					me.formap(root);
				}
			});
		},
		formap : function(dic) {
			var me = this;
			me.map[dic.id] = dic.codeDesc;
			for ( var i in dic.children) {
				me.formap(dic.children[i]);
			}
		},
		clearChildren : function(tree, level, orgId,notShowType) {
			var me = this;
			if (level <= 0 || tree.children == null) {
				tree.children = null;
			} else {
				level = level - 1;
				var flag=true;
				while(flag){
					flag=false;
					for (var i in tree.children) {
						if (tree.children[i].id == orgId||
								notShowType.indexOf(tree.children[i].type)!=-1) {
							tree.children.splice(i, 1);
							flag=true;
							break;
						}
						me.clearChildren(tree.children[i], level, orgId,notShowType);
					}
				}
			}
		},
		searchRootId : function(orgId, tree, level, templevel) {
			var me = this;
			for (var i = 0; i < tree.children.length; i++) {
				var lef = tree.children[i];
				if (lef.id == orgId) {
					return tree;
				} else if (lef.children != null && lef.children.length > 0) {
					var tlevel = 0;
					var result = me.searchRootId(orgId, lef, level, tlevel);
					if (result != null) {
						templevel = tlevel + 1;
						if (tlevel < level) {
							return tree;
						} else {
							return result;
						}
					}
				}
			}
			return null;
		},
		searchRoot : function(orgId, tree) {
			var me = this;
			for (var i = 0; i < tree.children.length; i++) {
				var lef = tree.children[i];
				if (lef.id == orgId) {
					return lef;
				} else if (lef.children != null && lef.children.length > 0) {
					var result = me.searchRoot(orgId, lef);
					if (result != null) {
						return result;
					}
				}
			}
			return null;
		},
		getData : function(orgId, options) {
			var me = this;
			var level = options.level;
			var tree =new Array();
			tree=$.extend(true,tree, me.dict[0] ||{});
			if(options.useCOrg){
				orgId=me.rootId;
			}
			if (orgId == null || tree[0].id == orgId) {
				me.clearChildren(tree[0], level, null,options.notShowType);
				var root = tree[0];
				if (!options.showRoot && root.children != null) {
					for (var j = 0; j < root.children.length; j++) {
						tree[j] = root.children[j];
					}
				}
			} else {
				var tempLevel = 0 - level;
				if (level > 0) {
					var root = me.searchRoot(orgId, tree[0]);
					if (root != null) {
						me.clearChildren(root, level, null,options.notShowType);
						if (!options.showRoot) {
							if(root.children.length){
								for (var j = 0; j < root.children.length; j++) {
									tree[j] = root.children[j];
								}
							}else{
								tree[0] = root;
							}
						} else {
							tree[0] = root;
						}
					} else {
						tree = null;
					}
				} else {
					var root = me.searchRootId(orgId, tree[0], tempLevel, 0);
					if (root != null) {
						if (!options.showItself) {
							me.clearChildren(root, tempLevel + 1, orgId,options.notShowType);
						} else {
							me.clearChildren(root, tempLevel + 1, null,options.notShowType);
						}
						if (!options.showRoot) {
							if(root.children.length){
								for (var j = 0; j < root.children.length; j++) {
									tree[j] = root.children[j];
								}
							}else{
								tree[0] = root;
							}
						} else {
							tree[0] = root;
						}
					} else {
						tree = null;
					}
				}
			}
			return tree;
		},
		// containerSelector,输入框父容器，多个输入使用多个div分隔. fieldname, 表单字段名称. orgId,
		// 需要查询的机构的id,为空时查询所有机构
		// options选项,allowBlank允许为空,emptyText为空时显示,defValue默认值,
		// level查询几级机构:0为orgId的同级机构，大于0下级机构，小于0上级机构
		// showRoot为是否显示根节点,showItself为是否显示orgId节点
		//notShowType:不显示在树形节点中的机构类型'4,5,67,8,'
		combobox : function(containerSelector, fieldname, orgId, options) {
			var me = this;
			options = $.extend({
				allowBlank : true,
				allowSearch: true,
				emptyText : '全部',
				defValue : '',
				multiSelected : false,
				level : '1',
				useCOrg:false,
				showRoot : false,
				notShowType :'',
				showItself : true,
				zIndex : 2
			}, options || {});
			var id = fieldname + '-field';
			var fieldSelector = $('<input type="hidden" name="' + fieldname
					+ '"/>');
			$(containerSelector).append(fieldSelector);
			$(fieldSelector).addAttrAndCls(options);//设置占位控件的样式 夏先智
			var dict = me.getData(orgId, options);
			var data = dict || [];
			if (!options.defValue && options.allowBlank === false && data.length > 0) {
				options.defValue = data[0].codeDesc;
			}
			var combo = $.initCodeComboCustom($(fieldSelector), data,
					$.extend({
						displayField : 'codeDesc',
						valueAttr : 'id',
						showClear : options.allowBlank,
						valueField : 'id',
						emptyText : options.emptyText,
						multiSelected : options.multiSelected
					}, options), 'local');
		},
		formatter : function(emptyText) {
			var me = this;
			var fn = function(cellvalue) {
				if (me.map[cellvalue]) {
					return me.map[cellvalue];
				}
				return emptyText || '';
			};
			me.formatters = fn;
			return fn;
		}
	});
	$.person = $.person || {};
	$.extend($.person, {
		personCbox : function(containerSelector, fieldname, orgId, options) {
			var me = this;
			options = $.extend({
				allowBlank : true,
				allowSearch : true,
				emptyText : '全部',
				defValue : '',
				multiSelected : false,
				pType : '',
				useCOrg:false,
				hasUser : '',
				isUnion : false
			}, options || {});
			
			if(options.useCOrg){
				orgId = $.organization.rootId || orgId;
			}
			var getData = function(){
				var result = [];
				var id = orgId;
				if ($.isFunction(orgId)){
					id = orgId.call();
				}
				if (!id){
					return result;
				}
				$.ajax({
					type : "GET",
					url : CONTEXT_PATH+"/data/jggl/perJson.action?orgId=" + id + "&pType="
							+ options.pType + "&isUnion=" + options.isUnion+"&hasUser="+options.hasUser,
					async : false,
					dataType : "json",
					success : function(data) {
						result = data[0];
					}
				});
				return result;
			};
			var id = fieldname + '-field';
			var fieldSelector = $('<input type="hidden" name="' + fieldname
					+ '"/>');
			$(containerSelector).append(fieldSelector);
			$(fieldSelector).addAttrAndCls(options);//设置占位控件的样式 夏先智
			//如果orgId为函数，则data为获取数据的函数,否则直接获取数据
			var data = $.isFunction(orgId) ? getData : getData();
			$.initCodeComboCustom($(fieldSelector), data, $.extend({
				displayField : 'name',
				valueField : 'code'
			}, options), 'local');
		}
	});
	
	
	$.fxrys = $.fxrys || {};
	$.extend($.fxrys, {
		fxrysCbox : function(containerSelector, fieldname, orgId, options) {
			var me = this;
			options = $.extend({
				allowBlank : true,
				emptyText : '全部',
				defValue : '',
				multiSelected : false,
				pType : 1,
				useCOrg:false,
				hasUser : '',
				isUnion : false
			}, options || {});
			var dict = [];
			if(options.useCOrg){
				orgId = $.organization.rootId || orgId;
			}
			var myurl=CONTEXT_PATH+"/data/xtsz/fxrylist.action";
			$.ajax({
				type : "GET",
				url : myurl,
				async : false,
				dataType : "json",
				success : function(data) {
					dict = data;
				}
			});
			var id = fieldname + '-field';
			var fieldSelector = $('<input type="hidden" name="' + fieldname
					+ '"/>');
			$(containerSelector).append(fieldSelector);
			$(fieldSelector).addAttrAndCls(options);//设置占位控件的样式 夏先智
			var data = dict[0] || [];
			if (!options.defValue && options.allowBlank === false && data.length > 0) {
				options.defValue = data[0].code;
			}
			$.initCodeComboCustom($(fieldSelector), data , $.extend({
				displayField : 'name',
				valueField : 'code'
			}, options), 'local');
		}
	});	
	$.fn.fmatter.organization = function(cellval, opts) {
		var fn = $.organization.formatter(cellval);
		return fn(cellval);
	};
})(jQuery);