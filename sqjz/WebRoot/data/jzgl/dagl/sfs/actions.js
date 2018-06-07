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
	edit: function(id,returnurl){
		//window.location = $.fxryconfig.baseurl + "edit.jsp?id=" + id;
		var url = $.fxryconfig.baseurl + "edit.jsp?id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	accept: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "edit.jsp?target=accept&id=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
	},
	moveIn: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "transfer/sfsaccept.jsp?fxryId=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
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
					{text: '脱管日期', type:'datepicker', name: 'startDate',maxdate:{type:'other',data:'$'}},
					{text: '脱管原因', type:'dict_combobox', name: 'reason', colspan:2,  code:'TGYY', required: true},
					{text: '通知派出所时间', type:'timepicker', name: 'noticeTime',maxdate:{type:'other',data:'$'}},
					{text: '通知人', type:'text', name: 'notifier',maxlength:20},
					{text: '派出所名称', type:'text', name: 'noticePolice',maxlength:50},
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
					{text: '通知派出所时间', type:'display', name: 'noticeTime'},
					{text: '通知人', type:'display', name: 'notifier'},
					{text: '派出所名称', type:'display', name: 'noticePolice'},
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
	release: function(id,returnurl){
		var url=$.fxryconfig.baseurl + "release/edit.jsp?fxryId=" + id;
		if(returnurl){
			url+="&returnurl="+returnurl;
		}
		window.location = url;
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
				    {type:'hidden', name: 'dateEAdjust',id:'dateEAdjust'},
				    {text: '原矫正开始时间', type:'display', name: 'dateSAdjust'},
				    {text: '原矫正结束时间', type:'display',  name: 'dateEAdjust'},
				    {text: '矫正类型', type:'display', name: 'adjustType', formatter:$.dictionary.formatter('JZLB', '不详')},
				    {text: '新矫正开始时间', type:'datepicker', name: 'newStartDate',required: true,mindate:{type:'id',data:'dateEAdjust'},maxdate:{type:'id',data:'newEndDate'}},
				    {text: '新矫正结束时间', type:'datepicker', name: 'newEndDate',required: true,mindate:{type:'id',data:'newStartDate'}},
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
	reportDateChange: function(value){
		var time="";
		if(value){
			var val=value.split("-");
			if(val.length==3){
				var date =new Date(val[0],val[1]-1,val[2]);
				date.setMonth(date.getMonth()+1, 1);
				time=date.format('yyyy-MM');
			}
		}
		$('span[name="nextreportdate"]').text(time);
		$('input[name="nextreportdate"]').val(time);
	},
	report: function(id, grid){
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
				]
			},{
				title: '人员报到',
				save: $.fxryconfig.baseurl + 'addReportInfoOperate.action',
				read: '',
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'id'},
				    {type:'hidden', name: 'fxryid', value: id},
				    {type:'hidden', name: 'nextreportdate',required: true},
					{text: '本次报到时间', type:'timepicker',name: 'reportdate',required: true,maxdate:{type:'other',data:'$'},
				    	valuechange:"$.fxryactions.reportDateChange(this.value);"},
					{text: '下次报到时间', type:'hidden', name: 'nextreportdate',required: true}
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '报到',
			width: '900px',
			height: '300px'
		});
	},
	vacate: function(id, grid){
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
				]
			},{
				title: '人员报道',
				save: $.fxryconfig.baseurl + 'qjgl.action',
				read: '',
				fieldCols: 3,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:[
				    {type:'hidden', name: 'personId', value: id},
					{text: '请假开始日期', type:'datepicker',name: 'startDate',required: true,maxdate:{type:'id',data:'endDate'}},
					{text: '请假结束日期', type:'datepicker', name: 'endDate', required: true,mindate:{type:'id',data:'startDate'}},
					{text: '外出目的地', type:'text', name: 'termini', maxlength:100},
					{text: '请假原因', type:'textarea', colspan:3,name: 'reason', required: true, maxlength:100},
				],
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			}]
		}, {
			okVal: '请假',
			width: '900px',
			height: '300px'
		});
	},
	vacateReport: function(id, name, grid){
		grid = grid || '#mainGrid';
		$.dialog.confirm('服刑人员：' + name + '<br>确定销假吗？', function(){
			$.fields.ajaxRequest({
				url: $.fxryconfig.baseurl + 'report.action?fxryid=' + id,
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			});
		});
	},
	vacateReportById: function(id, name, grid){
		grid = grid || '#mainGrid';
		$.dialog.confirm('服刑人员：' + name + '<br>确定销假吗？', function(){
			$.fields.ajaxRequest({
				url: $.fxryconfig.baseurl + 'report.action?id=' + id,
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			});
		});
	},
	educationsInit : function(id,flag){
		var type = '1';
		var tempId = '';
		var url = '../educations/addMore.action';
		var fields=[{text:"创建日期",type:'hidden',name:'createdate'},
					{text:"拟制人",type:'hidden',name:'creater'},
					{text:"所属机构",type:'hidden',name:'orgId'},
					{text:"服刑人员",type:'hidden',name:'fxryId',value:tempId},
					{text:"集中教育人员",type:'hidden',name:'ids',value:id},
					{text:"集中教育类型",type:'hidden',name:'type',value:type},//集中分类教育
					{text:"id",type:'hidden',name:'id'}
					];
		fields.push({type:'hidden', name: 'createTime', id:'reportDateCom'},
				{text:"集中教育时间",type:'datepicker',name:'time',dateFmt:'yyyy-MM-dd',validater:'date',required: true,
			valuechange:'changeDate(value,"bz","90")'},
			{text: '是否参与', type:'dict_radio',name: 'sfcy', code:'SF', required: true,
				valuechange:function(value,e){
					//未参加集中教育的在备注中填写原因
					if(value==2){
						$("textarea[name='bz']").setRequired(true,$("textarea[name='bz']").val());
					}else{
						var ydate = $("#reportDateCom").val();
						var value = $("input[name='time']").val();
						if(!commpareDate(value,ydate)){
							$("textarea[name='bz']").setRequired(false,$("textarea[name='bz']").val());
						}
					}
				}
			},
			{text: '备注', type:'textarea',name: 'bz', colspan:2, maxlength:200,defValue:'',required: false},
			{text: '记录人', type:'display',name: 'personName'},
			{text: '记录日期', type:'display',name: 'createTime'}
		);
		$.container.popup({
			title: '集中教育',
			read: '../educations/getEducationsInitData.action',
			save: url,
			fieldCols: 2,
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:fields,
			yes : function(data){
				$("#mainGrid").trigger("reloadGrid");
			}
		},{width: "600px",height: "250px",okVal:'保存'});
	},
	educations : function(id,flag){
		var tempId = id;
		var type = '';
		var url = '../educations/addMore.action';
		if(flag=='jzcsjy'){//集中初始教育
			type = '1';
			tempId = '';
		}else if(flag=='jzjjqjy'){//集中解矫前教育
			type = '3';
			tempId = '';
		}else if(flag=='zjry'){//集中解矫前教育
			type = '2';
			tempId = '';
		}else{
			type = '2';
			url = '../educations/add.action';
		}
		var fields=[{text:"创建日期",type:'hidden',name:'createdate'},
					{text:"拟制人",type:'hidden',name:'creater'},
					{text:"所属机构",type:'hidden',name:'orgId'},
					{text:"服刑人员",type:'hidden',name:'fxryId',value:tempId},
					{text:"集中教育人员",type:'hidden',name:'ids',value:id},
					{text:"集中教育类型",type:'hidden',name:'type',value:type},//集中分类教育
					{text:"id",type:'hidden',name:'id'},
					{text:"集中教育时间",type:'datepicker',name:'time',dateFmt:'yyyy-MM-dd',validater:'date',required: true}
					];
		fields.push({text: '是否参与', type:'dict_radio',name: 'sfcy', code:'SF', required: true,
			valuechange:function(value,e){
				//未参加集中教育的在备注中填写原因
				if(value==2){
					$("textarea[name='bz']").setRequired(true,$("textarea[name='bz']").val());
				}else{
					$("textarea[name='bz']").setRequired(false,$("textarea[name='bz']").val());
				}
			}
		});
		fields.push({text: '备注', type:'textarea',name: 'bz', colspan:1, maxlength:200,defValue:'',required: false});
		$.container.popup({
			title: '集中教育',
			read: false,
			save: url,
			fieldCols: 1,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:fields,
			yes : function(data){
				$("#mainGrid").trigger("reloadGrid");
			}
		},{width: "450px",height: "250px",okVal:'保存'});
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

function changeDate(value,id,classNum){
	var ydate = $("#reportDateCom").val();
	if(commpareDate(value,ydate)){
		//备注说明是否必录
		$("textarea[name='"+id+"']").setRequired(true,$("textarea[name='"+id+"']").val());
		$("textarea[name='"+id+"']").attr('label', '补录说明');
		//循环替换名称
		$(".form-td-label").each(function(i){
			var text = $(this).text();
			if(text == '备注:'){
				$(this).text('补录说明:');
				$(this).attr("class", 'form-td-label form-td-label-'+classNum+' field-required-label');
			}else if(text == '记录人:'){
				$(this).text('审批人:');
			}else if(text == '记录日期:'){
				$(this).text('审批日期:');
			}
		 });
	}else{
		var sfcy = $("input[name='sfcy']").val();
		var sfcyValue = "";
		if(sfcy!=null && sfcy!='undefined' && sfcy!=''){
			$('.dict-field-radio').each(function(i){
				if($(this)[0].checked){
					sfcyValue = $(this)[0].value
				}
			});
		}
		//备注说明是否必录
		if(sfcyValue=='2'){
			$("textarea[name='"+id+"']").setRequired(true,$("textarea[name='"+id+"']").val());
		}else{
			$("textarea[name='"+id+"']").setRequired(false,$("textarea[name='"+id+"']").val());
		}
		$("textarea[name='"+id+"']").attr('label', '备注');
		//循环替换名称
		$(".form-td-label").each(function(i){
			var text = $(this).text();
			if(text == '补录说明:'){
				$(this).text('备注:');
				if(sfcyValue=='2'){
					$(this).attr("class", 'form-td-label form-td-label-'+classNum+' field-required-label');
				}else{
					$(this).attr("class",'form-td-label form-td-label-'+classNum+' ')
				}
			}else if(text == '审批人:'){
				$(this).text('记录人:');
			}else if(text == '审批日期:'){
				$(this).text('记录日期:');
			}
		 });
	}
}

function commpareDate(value,ydate){
	ydate = ydate.substring(0,10);
	ydate = ydate.replace('-','');
	ydate = ydate.replace('-','');
	value = value.substring(0,10);
	value = value.replace('-','');
	value = value.replace('-','');
	if(parseInt(value)<parseInt(ydate)){
		return true;
	}else{
		return false;
	}
}