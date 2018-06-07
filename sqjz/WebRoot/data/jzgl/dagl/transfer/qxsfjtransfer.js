(function ($) {
"use strict";
$.fxrytransfer = $.fxrytransfer || {};
$.extend($.fxrytransfer,{
	defaultConfig:{
		edit:{
			read: "read.action",
			save: "save.action",
			saveVal: '保存',
			yes: null,
			cancel: null,
			editable: false,
			showBaseinfo: true
		},
		view:{
			showBaseinfo: true,
			read: "read.action",
			editable: false
		}
	},
	init: function(action, fxryId, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config.listPage = $.fxryconfig.listPage;
		me.config = $.extend(me.config, options);
		me.config.fxryId = fxryId;
		me.config.read += '?fxryId=' + me.config.fxryId;
		me.config.baseurl = me.config.baseurl || ($.fxryconfig.baseurl);
		me[action].call(me);
	},
	edit: function(){
		var me = this;
		$.container.generate(me.config.container,{
			type: 'row',
			items:[{
				read: $.fxryconfig.baseurl + 'read.action?id=' + me.config.fxryId,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-150'
				},
				fields:[           
						{text: '姓名', type:'display',name: 'name'},
						{text: '人员编号', type:'display',name: 'code'},
						{type:'photo',name: 'picture', rowspan:4, readonly:true},
						{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
						{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
						{text: '身份证号', type:'display',name: 'identityCard'},
						{text: '出生日期', type:'display',name: 'birthdate'},
						{text: '原负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
						{text: '监管设备编号', type:'display',name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'}
					]
			},{
				title: '人员转出',
				save: false,
				read: $.fxryconfig.baseurl + 'readTransferInfo.action?fxryId=' + me.config.fxryId,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-150'
				},
				fields:[
					{text: '转出原因', type:'textarea', colspan:3, name: 'reason', readonly: true},
					{text: '接收单位', type:'display', name: 'receiveOrgId', formatter:$.fn.fmatter.organization},
					{text: '应报到时间', type:'display', name: 'reportTime'},
					{text: '附件', type:'file', name: 'affixId', spath:'transfer', readonly:true}
				]
			}],
			buttons:[{
				text: '接收',
				cls: 'bttn bicon-report',
				callback: function(){
					$.fields.ajaxRequest({
						url: $.fxryconfig.baseurl + 'sfjMoveIn.action?fxryId=' + me.config.fxryId,
						yes: $.fxryconfig.baseurl + 'baseinfo/add.jsp?fxryId=' + me.config.fxryId
					});
				}
			},{
				text: '返回',
				cls: 'bttn bicon-return',
				callback: $.fxryconfig.baseurl+me.config.returnurl
			}]});
	}
});
})(jQuery);