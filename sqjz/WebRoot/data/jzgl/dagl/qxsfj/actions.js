(function ($) {
"use strict";
$.fxryactions = $.fxryactions || {};
$.extend($.fxryactions,{
	add: function(){
		window.location = $.fxryconfig.baseurl + "baseinfo/add.jsp";
	},
	export_doc: function(id,name){
		var url=$.fxryconfig.baseurl + "exportRxryDoc.jsp?fxry_Id="+id+"&frxry_name="+name;
		window.location = url;
	},
	reward:function(id,returnurl){
		var url=$.fxryconfig.baseurl + "rewardpunish/rpedit.jsp?id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location =url;
	},
	del: function(grid){
		grid = grid || "#mainGrid";
		var ids = $(grid).getGridParam("selarrrow");
		if(ids==null||ids.length<1){
			$.dialog.alert("请选择删除数据！");
			return false;
		}
		$.dialog.confirm('确认要删除记录吗？', function(){
			$.ajax({
				   type: "GET",
				   async:false,
				   url: $.fxryconfig.baseurl + "deleteZJRY.action",
				   data: "ids="+ids,
				   dataType: "json",
				   success: function(data){
					   if (data.success){
						   $(grid).trigger("reloadGrid");
					   }
					   else{
						   var row = $(grid).getRowData(data.msg);
						   $.dialog.error("【" + row.name + ":" + row.identityCard + "】已经报到，档案不能被删除。");
					   }
				   },
				   error: function(){
					   $.dialog.error("无法删除！请稍候再试 .");
				   }
			});
		});
	},
	bindEquip: function(id){
		window.location = $.fxryconfig.baseurl + "baseinfo/add.jsp?fxryId=" + id;
	},
	removeEquip: function(id, name, deviceCode, grid){
		grid = grid || '#mainGrid';
		$.dialog.confirm('使用人：' + name + '<br>设备编号：' + deviceCode +  '<br>确认解除设备？', function(){
			$.fields.ajaxRequest({
				url: $.fxryconfig.baseurl + 'removeEquip.action?fxryId=' + id + '&deviceCode=' + deviceCode,
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			});
		});
	},
	view: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "view.jsp?id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	editbase: function(id){
		window.location = $.fxryconfig.baseurl + "baseinfo/edit.jsp?fxryId=" + id;
	},
	edit: function(id,returnurl){
		//window.location = $.fxryconfig.baseurl + "edit.jsp?id=" + id;
		var url=$.fxryconfig.baseurl + "edit.jsp?id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	moveIn: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "transfer/qxsfjaccept.jsp?fxryId=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	moveOut: function(id, grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				read: $.fxryconfig.baseurl + 'read.action?id=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[           
						{type:'hidden',name: 'id', value: id},//隐藏字段
						{text: '姓名', type:'display',name: 'name'},
						{text: '人员编号', type:'display',name: 'code'},
						{type:'photo',name: 'picture', rowspan:4, readonly:true},
						{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
						{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
						{text: '身份证号', type:'display',name: 'identityCard'},
						{text: '出生日期', type:'display',name: 'birthdate'},
						{text: '负责矫正单位', type:'display', colspan:2, name: 'responseOrg', formatter:$.fn.fmatter.organization}
				]
			},{
				title: '人员转出',
				save: $.fxryconfig.baseurl + 'sfjMoveOut.action',
				read: $.fxryconfig.baseurl + 'readTransferInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryId', value: id},
				    {text: '转出原因', type:'textarea', colspan:3, name: 'reason', required: true},
					{text: '接收单位', type:'org_combobox', name: 'receiveOrgId', notShowType: '1,3,4,5,6,7,8,9', 
				    	allowSearch: true,showItself:false, required: true, useCOrg: true,showRoot:false, level:'0', emptyText:'请选择'},
					{text: '应到司法局报到时间', type:'datepicker',name: 'reportTime'},
					{text: '附件', type:'file', name: 'affixId', spath:'transfer'}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '确认转出',
			width: '900px',
			height: '300px'
		});
	},
	cancelMoveOut: function(id, grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				read: $.fxryconfig.baseurl + 'read.action?id=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[           
						{type:'hidden',name: 'id', value: id},//隐藏字段
						{text: '姓名', type:'display',name: 'name'},
						{text: '人员编号', type:'display',name: 'code'},
						{type:'photo',name: 'picture', rowspan:4, readonly:true},
						{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
						{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
						{text: '身份证号', type:'display',name: 'identityCard'},
						{text: '出生日期', type:'display',name: 'birthdate'},
						{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
						{text: '应到司法所报到时间', type:'display',name: 'report.reportTime'}
				]
			},{
				title: '人员转出',
				read: $.fxryconfig.baseurl + 'readTransferInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryId', value: id},
					{text: '转出原因', type:'textarea', colspan:3, name: 'reason', readonly: true},
					{text: '接收单位', type:'display', name: 'receiveOrgId', formatter:$.fn.fmatter.organization},
					{text: '附件', type:'file', readonly:true, colspan:2, name: 'affixId', spath:'transfer'}
				]
			}]
		}, {
			title: '取消人员转出',
			okVal: '取消转出',
			ok: function(){
				$.fields.ajaxRequest({
					url: $.fxryconfig.baseurl + '/cancelSfsMoveOut.action?fxryId=' + id,
					yes: function(){
						$(grid).trigger("reloadGrid");
					}
				});
			},
			width: '900px',
			height: '300px'
		});
	},
	moveOutBJ: function(id, grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				read: $.fxryconfig.baseurl + 'read.action?id=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[           
						{type:'hidden',name: 'id', value: id},//隐藏字段
						{text: '姓名', type:'display',name: 'name'},
						{text: '人员编号', type:'display',name: 'code'},
						{type:'photo',name: 'picture', rowspan:4, readonly:true},
						{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
						{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
						{text: '身份证号', type:'display',name: 'identityCard'},
						{text: '出生日期', type:'display',name: 'birthdate'},
						{text: '负责矫正单位', type:'display',colspan:2,name: 'responseOrg', formatter:$.fn.fmatter.organization}
				]
			},{
				title: '人员外省转出',
				save: $.fxryconfig.baseurl + 'sfjMoveOutBJ.action',
				read: $.fxryconfig.baseurl + 'readTransferInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryId', value: id},
				    {text: '转出原因', type:'textarea', colspan:3, name: 'reason', required: true},
					{text: '接收单位', type:'text', name: 'receiveOrgName', required: true},
					{text: '联系人', type:'text', name: 'name', required: true},
					{text: '联系人电话', type:'text', name: 'phone', required: true,maxlength:11}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '确认转出',
			width: '900px',
			height: '300px'
		});
	},
	adjusttermmodify: function(id,grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				read: $.fxryconfig.baseurl + 'read.action?id=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[           
						{type:'hidden',name: 'id', value: id},//隐藏字段
						{text: '姓名', type:'display',name: 'name'},
						{text: '人员编号', type:'display',name: 'code'},
						{type:'photo',name: 'picture', rowspan:4, readonly:true},
						{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
						{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
						{text: '身份证号', type:'display',name: 'identityCard'},
						{text: '出生日期', type:'display',name: 'birthdate'},
						{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization}					
				]
			},{
				title: '变更矫正期限',
				save: $.fxryconfig.baseurl + 'executeinfo/updateAdjustTerm.action',
				read: $.fxryconfig.baseurl + 'executeinfo/read.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryId', value: id},
				    {text: '原矫正开始时间', type:'display', name: 'dateSAdjust'},
				    {text: '原矫正结束时间', type:'display',  name: 'dateEAdjust'},
				    {text: '矫正类型', type:'display', name: 'adjustType', formatter:$.dictionary.formatter('JZLB', '不详')},
				    {text: '新矫正开始时间', type:'datepicker', name: 'newStartDate'},
				    {text: '新矫正结束时间', type:'datepicker', name: 'newEndDate'},
				    {}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");			
				}
			}]
		}, {
			okVal: '保存',
			width: '800px',
			height: '300px'
		});
	},
	locate: function(id){
		$.dialog({
			title : '在矫人员定位信息',
			id: 'locate',
			fixed : true,
			lock : true,
			width : 1024,
			height: 768,
			content : 'url:'+CONTEXT_PATH+'/data/dzjg/dwjk/history.jsp?fxryid=' + id,
			resize : false,
			cancel : true,
			button : [],
			cancelVal : "返回"
		}).max();
	}
});
})(jQuery);
