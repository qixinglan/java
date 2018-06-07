(function ($) {
"use strict";
$.fxryactions = $.fxryactions || {};
$.extend($.fxryactions,{
	view: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "view.jsp?id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	export_doc: function(id,name){
		var url=$.fxryconfig.baseurl + "exportRxryDoc.jsp?fxry_Id="+id+"&frxry_name="+name;
		window.location = url;
	},
	syncView: function(id,name,sex,identityCard,org,grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				fieldCols: 2,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[           
						{type:'hidden',name: 'id', value: id},//隐藏字段
						{text: '姓名', type:'display',defValue:name},
						{text: '性别', type:'display',defValue:sex, formatter:$.dictionary.formatter('XB', '不详')},
						{text: '身份证号', type:'display',defValue:identityCard},
						{text: '矫正机构', type:'display',defValue:org, formatter:$.fn.fmatter.organization},
				]
			},{
				type: 'grid',
				id: 'grid-synchis-container'
			},{
				type: 'grid',
				id: 'grid-synchis-container-pager'
			}]
		}, {
			width: '900px'
		});
		this.addSyncHistoryt('grid-synchis-container', id, 'grid-synchis-container-pager');
	},
	addSyncHistoryt:function(id,fxryId,pageId){
		$('#' + id).jqGrid({
			url: CONTEXT_PATH + '/data/jzgl/sync/history.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'saveTime',
				index:'saveTime',
				label: '上报日期',
				width: 100,
				fixed: true,
				align: 'center'
			},
			{
				name:'syncTime',
				index:'syncTime',
				label: '完成上报时间',
				width: 140,
				fixed: true,
				align: 'center'
			},
			{
				name:'syncState',
				index:'syncState',
				label: '人员上报状态',
				formatter : 'dictionary',
		        formatoptions: {code:'JZRYZT'},
				width: 100,
				fixed: true,
				align: 'center'
			},
			{
				name:'realState',
				index:'realState',
				label: '人员实际状态',
				formatter : 'dictionary',
		        formatoptions: {code:'JZRYZT'},
				width: 100,
				fixed: true,
				align: 'center'
			},{
				name:'syncOrg',
				index:'syncOrg',
				label: '上报时矫正机构',
				formatter: 'organization',
				width: 100,
				fixed: true,
				align: 'center'
			},{
				name:'state',
				index:'state',
				label: '上报状态',
				width: 80,
				fixed: true,
				align: 'center',
		        formatter : function(value, opts, rwdat) {
		        	var actions = '等待上报';
		        	if(value=='1'){
		        		actions = "成功";
		        	}
		        	if(value=='3'){
		        		actions = "失败";
		        	}
		        	return actions;	
		        }
			},
			{
				name:'description',
				index:'description',
				label: '描述',
				width: 500,
				align: 'center'
			}
			],
			rowNum : 5,
			rowList : false,
			multiselect: false,
			sortname: 'saveTime',
			sortorder: "desc",
			pager: '#'+pageId
		});
	},
	syncFxry: function(fxryId){
		var grid = '#mainGrid';
		$.dialog.confirm('确认要上报服刑人员信息吗？', function(){
			$.ajax({
				   type: "POST",
				   async:false,
				   url: CONTEXT_PATH + "/data/jzgl/sync/fxrySync.action?fxryId=" + fxryId,
				   dataType: "json",
				   success: function(data){
					   $.dialog.alert("服刑人员信息将于明天完成上报请耐心等待！");
					   $(grid).trigger("reloadGrid");
				   },
				   error: function(){
					   $.dialog.error("准备上报数据时发生异常！请稍候再试 。");
				   }
			});
		});
	},
	syncFxrys: function(){
		var grid = '#mainGrid';
		var ids = $(grid).getGridParam("selarrrow");
		if(ids==null||"" == ids){
			$.dialog.alert("请选择上报的服刑人员人员!");
			return false;
		}
		$.dialog.confirm('确认要上报服刑人员信息吗？', function(){
			$.ajax({
				   type: "POST",
				   async:false,
				   url: CONTEXT_PATH + "/data/jzgl/sync/fxrySync.action?fxryIds=" + ids,
				   dataType: "json",
				   success: function(data){
					   $.dialog.alert("服刑人员信息将于明天完成上报请耐心等待！");
					   $(grid).trigger("reloadGrid");
				   },
				   error: function(){
					   $.dialog.error("准备上报数据时发生异常！请稍候再试 。");
				   }
			});
		});
	},
	add: function(){
		var grid = '#mainGrid';
		$.dialog.confirm('确认要上报服刑人员信息吗？', function(){
			$.ajax({
				   type: "POST",
				   async:false,
				   url: CONTEXT_PATH + "/data/jzgl/sync/fxrysSync.action",
				   dataType: "json",
				   success: function(data){
					   $.dialog.alert("服刑人员信息将于明天完成上报请耐心等待！");
					   $(grid).trigger("reloadGrid");
				   },
				   error: function(){
					   $.dialog.error("准备上报数据时发生异常！请稍候再试 。");
				   }
			});
		});
	},
	edit: function(id){
		window.location = $.fxryconfig.baseurl + "edit.jsp?id=" + id;
	},
	accept: function(id){
		window.location = $.fxryconfig.baseurl + "edit.jsp?target=accept&id=" + id;
	},
	moveIn: function(id){
		window.location = $.fxryconfig.baseurl + "transfer/sfsaccept.jsp?fxryId=" + id;
	},
	reward:function(id,returnurl){
		var url=$.fxryconfig.baseurl + "rewardpunish/rpedit.jsp?id=" + id;
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
						{text: '负责矫正单位', type:'display',colspan:2,name: 'responseOrg', formatter:$.fn.fmatter.organization},
						{}
				]
			},{
				title: '人员转出',
				save: $.fxryconfig.baseurl + 'sfsMoveOut.action',
				read: $.fxryconfig.baseurl + 'readTransferInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryId', value: id},
					{text: '转出原因', type:'textarea', colspan:3, name: 'reason', required: true, maxlength:100},
					{text: '接收单位', type:'org_combobox', notShowType: '1,2,4,5,6,7,8,9',name: 'receiveOrgId', 
						allowSearch: true,showItself:false, required: true, useCOrg: true,showRoot:false, level:'0', emptyText:'请选择'},
					{text: '应到司法所报到时间', type:'datepicker',name: 'reportTime'},
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
	outManage: function(id, grid){
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
				title: '人员脱管',
				save: $.fxryconfig.baseurl + 'outManage.action',
				read: $.fxryconfig.baseurl + 'readOutManageInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				mask: true,
				fields:[    
					{type:'hidden', name: 'id'},
					{type:'hidden', name: 'fxryId', value: id},
					{text: '脱管日期', type:'datepicker', name: 'startDate'},
					{text: '脱管原因', type:'dict_combobox', name: 'reason', colspan:2,  code:'TGYY', required: true},
					{text: '备注', type:'textarea', colspan:3, name: 'description', maxlength:500}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '确认脱管',
			width: '900px',
			height: '300px'
		});
	},
	inManage: function(id, grid){
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
				title: '人员脱管',
				save: $.fxryconfig.baseurl + 'inManage.action',
				read: $.fxryconfig.baseurl + 'readOutManageInfo.action?fxryId=' + id,
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				mask: true,
				fields:[    
					{type:'hidden', name: 'id'},
					{type:'hidden', name: 'fxryId', value: id},
					{text: '脱管日期', type:'display', name: 'startDate'},
					{text: '恢复矫正日期', type:'datepicker', name: 'endDate'},
					{},
					{text: '脱管原因', type:'display', colspan:3, formatter:$.dictionary.formatter('TGYY', '不详'), name: 'reason'},
					{text: '备注', type:'textarea', colspan:3, name: 'description', readonly: true}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '恢复矫正',
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
	release: function(id){
		window.location = $.fxryconfig.baseurl + "release/edit.jsp?fxryId=" + id;
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
			width: '900px',
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
