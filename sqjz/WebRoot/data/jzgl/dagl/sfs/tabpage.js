(function ($) {
"use strict";
$.fxrytabpage = $.fxrytabpage || {};
$.extend($.fxrytabpage,{
	defaultConfig:{
		sry:{
		},
		zjry:{
		},
		dzrry:{
		},
		yzcry:{
		},
		tgry:{
		},
		jjry:{
		},
		yjjry:{			
		},
		//zjwxbry:{			
		//},
		jcqk:{			
		},
		wfqk:{			
		},
		rcgztx:{			
		},
		qjxx:{			
		},
		jzjjqjy:{			
		},
		jzcsjy:{
		}
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
		me.config.menu=[{
			text:'提醒事项',
			submenus:[{text:'接收报到', content:'xbdry',count:'(0人)'},
			          {text:'待转入人员', content:'dzrry',count:'(0人)'},
			         // {text:'暂监外续保人员',content:'zjwxbry',count:'(0人)'},
			          {text:'预解矫人员',content:'yjjry',count:'(0人)'},
			          {text:'报到提醒', content:'bdtxry',count:'(0人)'},
			          {text:'销假提醒', content:'xjtxry',count:'(0人)'}]
		}];
		me.config.menuread='rctxCount.action';
		if(action){
			me[action].call(me);
		}
	},
	sry: function(){
		var me = this;
		me.educationButton("zjry");
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + 'listZJRY.action?showTgry=true',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 40,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 100,
				        fixed : true,
				       	label	: '矫正期限',
				        align	: 'center'
			        }, {
				        name	: 'remainDays',
				        index	: 'remainDays',
				        width	: 100,
				        sortable: true,
				        formatter : 'days',
				        fixed : true,
				       	label	: '解矫期限',
				        align	: 'center'
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '当前状态',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'deviceCode',
				        index	: 'deviceCode',
				        label	: '是否电子监管',
				        fixed: true,
				        width	: 90,
				        sortable: true,
				        align	: 'center',
				        searchType : 'eq',
				        searchInput:'dictionary',
				        searchOption: {code:'SF'},
				        formatter : function(value, opts, rwdat) {
				        	if (!rwdat.deviceCode){
				        		return "否";
			        		}
			        		else{
			        			return "是";
			        		}
				        }
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 370,
				        align	: 'right',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	if (!row.excuteId){
				        		actions += "<a href='javascript:$.fxryactions.accept(\""+row.id+"\");' class='a-style' style='color:red'>接收</a>";
				        	}
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.id+"\");' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.edit(\""+row.id+"\");'class='a-style'>编辑</a>";
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+row.id+"\");' class='a-style'>奖惩</a>";
				        	if (!row.excuteId){
					        	//actions += "<a class='a-style gray'>报到</a>";
					        	actions += "<a class='a-style gray'>请假</a>";
				        	}else{
					        	//actions += "<a href='javascript:$.fxryactions.report(\""+row.id+"\");'class='a-style'>报到</a>";
					        	if(row.vacated==0){
					        		actions += "<a href='javascript:$.fxryactions.vacate(\""+row.id+"\");'class='a-style'>请假</a>";
					        	}else{
					        		actions += "<a href='javascript:$.fxryactions.vacateReport(\""+row.id+"\",\""+row.name+"\");'class='a-style'>销假</a>";
					        	}
				        	}
				        	if (row.excuteId){
				        		if (row.state == '6'){
				        			actions += "<a href='javascript:$.fxryactions.cancelMoveOut(\""+row.id+"\");' class='a-style'>取消转出</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.moveOut(\""+row.id+"\");' class='a-style'>转出</a>";
				        		}
				        		if (row.state == '3'){
				        			actions += "<a href='javascript:$.fxryactions.inManage(\""+row.id+"\");' class='a-style'>恢复矫正</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.outManage(\""+row.id+"\");' class='a-style'>脱管</a>";
				        		}
					        	
					        	var releaseStyle =  "style='color:red'";
					        	if (row.remainDays == null || row.remainDays > 0){
					        		releaseStyle = '';
					        	}
					        	actions += "<a href='javascript:$.fxryactions.release(\""+row.id+"\");' class='a-style' " + releaseStyle + ">解矫</a>";
				        	}
				        	else{
				        		actions += "<a class='a-style gray'>转出</a>";
					        	actions += "<a class='a-style gray'>脱管</a>";
					        	actions += "<a class='a-style gray' " + releaseStyle + ">解矫</a>";
				        	}
				        	actions += "<a href='javascript:$.fxryactions.educations(\""+row.id+"\",2);' class='a-style'>教育</a>";//集中分类教育
				        	return actions;	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'reported,remainDays,code',
			sortorder: "asc,asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	zjry: function(){
		var me = this;
		me.educationButton("zjry");
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + 'listZJRY.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 120,
				        align	: 'center'
			        },{
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 40,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 100,
				        fixed : true,
				       	label	: '矫正期限',
				        align	: 'center'
			        }, {
				        name	: 'remainDays',
				        index	: 'remainDays',
				        width	: 100,
				        sortable: true,
				        formatter : 'days',
				        fixed : true,
				       	label	: '解矫期限',
				        align	: 'center'
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '当前状态',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'deviceCode',
				        index	: 'deviceCode',
				        label	: '是否电子监管',
				        fixed: true,
				        width	: 90,
				        sortable: true,
				        align	: 'center',
				        searchType : 'eq',
				        searchInput:'dictionary',
				        searchOption: {code:'SF'},
				        formatter : function(value, opts, rwdat) {
				        	if (!rwdat.deviceCode){
				        		return "否";
			        		}
			        		else{
			        			return "是";
			        		}
				        }
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 400,
				        align	: 'center',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	if (!row.excuteId){
				        		actions += "<a href='javascript:$.fxryactions.accept(\""+row.id+"\");' class='a-style' style='color:red'>接收</a>";
				        	}
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.id+"\");' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.edit(\""+row.id+"\");'class='a-style'>编辑</a>";
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+row.id+"\");' class='a-style'>奖惩</a>";
				        	actions += "<a href='javascript:$.fxryactions.export_doc(\""+row.id+"\",\""+row.name+"\");' class='a-style'>导出</a>";
				        	if (!row.excuteId){
					        	//actions += "<a class='a-style gray'>报到</a>";
					        	actions += "<a class='a-style gray'>请假</a>";
				        	}else{
					        	//actions += "<a href='javascript:$.fxryactions.report(\""+row.id+"\");'class='a-style'>报到</a>";
					        	if(row.vacated==0){
					        		actions += "<a href='javascript:$.fxryactions.vacate(\""+row.id+"\");'class='a-style'>请假</a>";
					        	}else{
					        		actions += "<a href='javascript:$.fxryactions.vacateReport(\""+row.id+"\",\""+row.name+"\");'class='a-style'>销假</a>";
					        	}
				        	}
				        	if (row.excuteId){
				        		if (row.state == '6'){
				        			actions += "<a href='javascript:$.fxryactions.cancelMoveOut(\""+row.id+"\");' class='a-style'>取消转出</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.moveOut(\""+row.id+"\");' class='a-style'>转出</a>";
				        		}
				        		if (row.state == '3'){
				        			actions += "<a href='javascript:$.fxryactions.inManage(\""+row.id+"\");' class='a-style'>恢复矫正</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.outManage(\""+row.id+"\");' class='a-style'>脱管</a>";
				        		}
					        	
					        	var releaseStyle =  "style='color:red'";
					        	if (row.remainDays == null || row.remainDays > 0){
					        		releaseStyle = '';
					        	}
					        	actions += "<a href='javascript:$.fxryactions.release(\""+row.id+"\");' class='a-style' " + releaseStyle + ">解矫</a>";
				        	}
				        	else{
				        		actions += "<a class='a-style gray'>转出</a>";
					        	actions += "<a class='a-style gray'>脱管</a>";
					        	actions += "<a class='a-style gray' " + releaseStyle + ">解矫</a>";
				        	}
				        	actions += "<a href='javascript:$.fxryactions.educations(\""+row.id+"\",2);' class='a-style'>教育</a>";//集中分类教育
				        	if (row.state =='1' && row.deviceCode){
				        		actions += "<a title='' class='a-style gray' href='javascript:$.fxryactions.locate(\""+row.id+"\");'>定位</a>";
				        	}
				        	return actions;	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'reported,remainDays,code',
			sortorder: "asc,asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	dzrry: function(){
		var me = this;
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
		var moveInreturn=returnurl+".jsp";
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listTransferUntrat.action?oper=untreat',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'outOrgId',
				        index	: 'outOrgId',
				        width	: 200,
				        searchType : 'eq',
				        sortable: true,
				       	label	: '转出单位',
				        align	: 'center',
				        formatter : 'organization',
				        formatoptions:{
				        	showItself:false, 
				        	useCOrg: true,
				        	showRoot:false, 
				        	level:'0', 
				        	notShowType: '1,2,4,5,6,7,8,9'
				        }
			        },{
				        name	: 'outTime',
				        index	: 'outTime',
				        sortable: true,
				        width	: 140,
				        searchType : 'in',
				        searchInput : 'time',
				        fixed : true,
				       	label	: '转出时间',
				        align	: 'center'
			        },{
				        name	: 'reportTime',
				        index	: 'reportTime',
				        sortable: true,
				        width	: 140,
				        searchType : 'in',
				        searchInput : 'time',
				        fixed : true,
				       	label	: '应报到时间',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 100,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.moveIn(\""+rwdat.fxryId+"\",\""+moveInreturn+"\");' title='' class='a-style' style='color:red'>接收</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'outTime,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	yzcry: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listTransferUntrat.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'outOrgId',
				        index	: 'outOrgId',
				        width	: 200,
				        searchType : 'cn',
				        sortable: true,
				        hidden:true,
				       	label	: '转出单位',
				        align	: 'center',
				        formatter : 'organization'
			        },{
				        name	: 'outTime',
				        index	: 'outTime',
				        sortable: true,
				        width	: 140,
				        searchType : 'in',
				        fixed : true,
				        searchInput : 'time',
				       	label	: '转出时间',
				        align	: 'center'
			        },{
				        name	: 'receiveOrgId',
				        index	: 'receiveOrgId',
				        width	: 200,
				        sortable: true,
				       	label	: '接收单位',
				        align	: 'center',
				        formatter : 'organization'
			        },{
				        name	: 'receiveTime',
				        index	: 'receiveTime',
				        sortable: true,
				        width	: 140,
				        fixed : true,
				       	label	: '接收时间',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 100,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\",\"yzcry\");' title='' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'outTime,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	tgry: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listUnManage.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        searchType: 'cn',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 90,
				        fixed : true,
				       	label	: '矫正期限',
				        align	: 'center'
			        }, {
				        name	: 'remainDays',
				        index	: 'remainDays',
				        width	: 90,
				        sortable: true,
				        formatter : 'days',
				        fixed : true,
				       	label	: '解矫期限',
				        align	: 'center'
			        },
			        {
				        name	: 'startDate',
				        index	: 'startDate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '脱管时间',
				        align	: 'center'
			        },
			        {
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 145,
				        align	: 'left',
				        fixed : true,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.fxryId+"\",\"tgry\");' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.edit(\""+row.fxryId+"\",\"tgry\");'class='a-style'>编辑</a>";
				        	actions += "<a href='javascript:$.fxryactions.inManage(\""+row.fxryId+"\");'class='a-style'>恢复矫正</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'startDate,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	jjry: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listUnControl.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },
			        {
				        name	: 'removeDate',
				        index	: 'removeDate',
				        sortable: true,
				        width	: 140,
				        fixed : true,
				       	label	: '解矫日期',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 100,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\",\"jjry\");' title='' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'removeDate,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	yjjry:function(){
		var me = this;
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
		var moveInreturn=me.config.baseurl+returnurl+".jsp";
		$("#mainGrid").jqGrid({
			url		   : me.config.baseurl + 'listReadyRelease.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 30,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 50,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',				      
				        width	: 20,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        searchType : 'cn',
				        label	: '矫正类别',
				        sortable: true,
				        width	: 30,
				        align	: 'center',
					    formatter : 'dictionary',
					    formatoptions: {code:'JZLB'}
			        } ,
			        {
				        name	: 'startdate',
				        index	: 'startdate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正开始日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.startdate!=null){
				        		return rwdat.startdate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        },{
				        name	: 'enddate',
				        index	: 'enddate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正结束日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.enddate!=null){
				        		return rwdat.enddate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        }, 
			        {
				        name	: 'days',
				        index	: 'days',
				        label	: '解矫期限',				      
				        width	: 30,
				        sortable: true,
				        formatter : 'days',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 120,
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.release(\""+rwdat.id+"\",\""+moveInreturn+"\");' class='a-style' style='color:red'>解矫</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
				        	return actions;					        	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'orgName,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	zjwxbry:function(){
		var me = this;
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
		$("#mainGrid").jqGrid({
			url		   : me.config.baseurl + 'listOutPrionExtend.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 30,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 50,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',				      
				        width	: 20,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        searchType : 'cn',
				        label	: '矫正类别',
				        sortable: true,
				        width	: 30,
				        align	: 'center',
					    formatter : 'dictionary',
					    formatoptions: {code:'JZLB'}
			        } ,
			        {
				        name	: 'startdate',
				        index	: 'startdate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正开始日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.startdate!=null){
				        		return rwdat.startdate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        },{
				        name	: 'enddate',
				        index	: 'enddate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正结束日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.enddate!=null){
				        		return rwdat.enddate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        }, 
			        {
				        name	: 'days',
				        index	: 'days',
				        label	: '续保期限',				      
				        width	: 30,
				        sortable: true,
				        formatter : 'days',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 100,
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.adjusttermmodify(\""+rwdat.id+"\");' class='a-style' style='color:red'>续保</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' class='a-style'>查看</a>";
				        	return actions;					        	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'orgName',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	jcqk: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listJCQK.action?type=jc',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'responseOrg',
				        index	: 'responseOrg',
				        width	: 130,
				        searchType : 'cn',
				        sortable: true,
				        formatter: 'organization',
				        searchOption: {
				        	useCOrg: true, 
							notShowType: '1,4,5,6,7,8,9', 
							showRoot:false,
							level:2,
							allowSearch: true
				        },
				       	label	: '矫正负责单位',
				        align	: 'center'
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '当前状态',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'rnum',
				        index	: 'rnum',
				        label	: '奖励次数',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'pnum',
				        index	: 'pnum',
				        label	: '违纪次数',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\",\"jcqk\");' title='' class='a-style'>奖惩</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'rnum,pnum',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	buildmenu:function(container, menus, cls){
		var me=this;
		var collapsefn = function(submenu){
			return function(){
				if ($(submenu).attr("collapse") == "true"){
					$(submenu).show();
					$(submenu).attr("collapse", "false");
				}
				else{
					$(submenu).hide();
					$(submenu).attr("collapse", "true");
				}
			}
		};
		var $ul = $('<ul></ul>');
		$ul.addClass('tabpage-group-' + cls);
		$(container).append($ul);
		for (var i in menus){
			var menu = menus[i];
			var $li = $('<li></li>');
			$li.addClass('tabpage-items-' + cls);
			$li.append('<a>' + menu.text)
			if(menu.count){
				$li.append(menu.count);
			}
			$li.append('</a>');
			$ul.append($li);
			if (menu.submenus){
				var $submenuli = $('<li></li>');
				$ul.append($submenuli);
				$li.click(collapsefn($submenuli));
				me.buildmenu($submenuli, menu.submenus, 'submenu');
			}
			else{
				$li.attr('content', menu.content);
				$li.attr('clickable', true);
				$li.click(function(){
					me.loadContent('.tabpage-content', $(this).attr('content'));
				});
			}
		}
	},
	rcgztx: function(){	
		var me = this;
		var id = 'tabpages-container';
		$.container.generate(".container-middle", {
			id: id,
			type: 'panel',
			padding: '0px'
		});
		$('#' + id).css('borderTop', '1px solid #d8d6d6');
		$('#' + id).append('<table width="100%"><tr><td nowrap="nowrap" class="tabpage-menu" style="width:180px;"></td><td class="tabpage-content"></td></tr></table>');
		var menu=me.config.menu;
		if(me.config.menuread){
			var data="";
			for(var i in menu){
				if(menu[i].submenus){
					for(var j in menu[i].submenus){
						data+=menu[i].submenus[j].content+",";
					}
				}
			}
			var options ={};
			options = $.extend({
				   type: 'POST',
				   async:false,
				   url: me.config.baseurl+me.config.menuread,
				   data:'type='+data,
				   title:'提醒事项数量',
				   cache: false,
				   dataType: 'json',
				   success: function(data, success, http){
					   if (data){
						   for(var i in menu){
								if(menu[i].submenus){
									for(var j in menu[i].submenus){
										var content=menu[i].submenus[j].content;
										if(data[content]){
											if(!me.config.content){
												   me.config.content=menu[i].submenus[j].content;
											}
											menu[i].submenus[j].count="<font color='red'>("+data[content]+")人</font>";
										}else{
											menu[i].submenus[j].count="(0)人";
										}
									}
								}
							}
					   }
					   else{
						   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
					   }
				   },
				   error: function(){
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			}, options);
			$.ajax(options);
		}
		me.buildmenu('.tabpage-menu', me.config.menu, 'menu');
		if (me.config.content){
			$('li[content="' + me.config.content + '"]').click();
		}
		else{
			$('li[clickable="true"]:first').click();
		}
		
	},
	loadContent:function(container, content){
		var me = this;
		var form = 'form_' + content;
		$(container).empty();
		if (me[form]){
			me[form]($(container), content);
		}
		else{
			me['form_error']($(container), content);
		}
		$.each($('.tabpage-menu .tabpage-items-submenu'), function(i, item){
			if ($(item).attr('content') == content){
				$(item).addClass('tabpage-items-submenu-selected');
			}
			else{
				$(item).removeClass('tabpage-items-submenu-selected');
			}
		});
	},
	addGrid:function(container){
		var grid="<div class='tabpage'><div class='container-middle'><div id='search'></div></div>" +
			"<div class='container-bottom'><div id='buttons' class='buttonArea operation'></div>" +
			"<table id='mainGrid'></table><div id='mainGridPager'></div></div></div>";
		$(container).append(grid);
	},
	form_error: function(container){
		$(container).append("<p>该功能还在开发中...</p>");
	},
	xbdry:function(url){
		var me = this;
		var returnurl="rcgztx";
		if(url){
			returnurl=url;
		}
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + 'listXBDRY.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 100,
				        fixed : true,
				       	label	: '矫正期限',
				        align	: 'center'
			        }, {
				        name	: 'remainDays',
				        index	: 'remainDays',
				        width	: 100,
				        sortable: true,
				        formatter : 'days',
				        fixed : true,
				       	label	: '解矫期限',
				        align	: 'center'
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '当前状态',
				        fixed: true,
				        width	: 100,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'right',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.fxryactions.accept(\""+row.id+"\",\""+returnurl+"\");' class='a-style' style='color:red'>接收</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.id+"\",\""+returnurl+"\");' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'reported,remainDays,code',
			sortorder: "asc,asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	form_xbdry: function(container){
		var me = this;
		me.addGrid(container);
		me.xbdry();
	},
	form_dzrry: function(container, content){
		var me = this;
		me.addGrid(container);
		me.dzrry();
	},
	form_zjwxbry: function(container, content){
		var me = this;
		me.addGrid(container);
		me.zjwxbry();
	},
	form_yjjry: function(container, content){
		var me = this;
		me.addGrid(container);
		me.yjjry();
	},
	form_bdtxry: function(container, content){
		var me = this;
		me.addGrid(container);
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + 'listXYBDRY.action',
			colModel: [
					{
						name	:'fxryid',
						index	:'fxryid',
						label	: 'fxryid',
						key : true,
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'fxryname',
				        index	: 'fxryname',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'fxrycode',
				        index	: 'fxrycode',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
				        align	: 'center'
			        }, {
				        name	: 'fxryidentirycard',
				        index	: 'fxryidentirycard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'policename',
				        index	: 'policename',
				        sortable: true,
				        width	: 100,
				       	label	: '责任干警',
				        align	: 'center'
			        },{
				        name	: 'realreportdate',
				        index	: 'realreportdate',
				        sortable: true,
				        width	: 100,
				        fixed : true,
				       	label	: '上次报到时间',
				        align	: 'center'
			        }, /*{
				        name	: 'reportdate',
				        index	: 'reportdate',
				        width	: 100,
				        sortable: true,
				        fixed : true,
				       	label	: '下次报到月份',
				        align	: 'center'
			        },*/{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'right',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.fxryactions.report(\""+row.fxryid+"\");' class='a-style' style='color:red'>报到</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.fxryid+"\",\"rcgztx\");' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'reportdate',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	form_xjtxry: function(container, content){
		var me = this;
		me.addGrid(container);
		me.qjlb('vacateList.action','tx');
	},
	qjxx: function(){
		var me = this;
		me.qjlb('vacateSearch.action');
	},
	qjlb:function(url,type){
		var me = this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="销假"/>');
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + url,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'fxryname',
				        index	: 'fxryname',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        },{
				        name	: 'fxrycode',
				        index	: 'fxrycode',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'fxryidentirycard',
				        index	: 'fxryidentirycard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        },{
				        name	: 'startDate',
				        index	: 'startDate',
				        label	: '请假开始日期',
				        fixed: true,
				        width	: 100,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'endDate',
				        index	: 'endDate',
				        label	: '请假结束日期',
				        fixed: true,
				        width	: 100,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'period',
				        index	: 'period',
				        label	: '请假天数',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center'
			        },
			        {
				        name	: 'reason',
				        index	: 'reason',
				        label	: '请假原因',
				        width	: 200,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'termini',
				        index	: 'termini',
				        label	: '外出目的地',
				        width	: 100,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	actions += "<a href='javascript:$.fxryactions.vacateReportById(\""+rwdat.id+"\",\""+rwdat.fxryname+"\");'class='a-style'>销假</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'endDate,fxrycode',
			sortorder: "asc,desc",
			pager: '#mainGridPager'
		});
		if(type==null){
			$("#mainGrid").addSearchForm("#search");
			$("#btnAdd").click(function(){
				var ids = $("#mainGrid").getGridParam("selarrrow");
				if(ids==null||"" == ids){
					$.dialog.alert("请选择销假的人员!");
					return false;
				}
				var fields=[{text:"服刑人员id",type:'hidden',name:'ids',value:ids},
				            {type:'hidden', name: 'createTime', id:'reportDateCom'},
							{text:"销假日期",type:'datepicker',name:'reportDateTime',dateFmt:'yyyy-MM-dd HH:mm:ss',validater:'fulldate',required: true,
								valuechange:'changeDate(value,"bz","120")'},
							{text: '备注', type:'textarea',name: 'bz', colspan:2, maxlength:200,defValue:'',required: false},
							{text: '记录人', type:'display',name: 'creater'},
					        {text: '记录日期', type:'display',name: 'createTime'}
							];
				$.container.popup({
					title: '销假提醒',
					read: me.config.baseurl + 'getReportInitData.action',
					save: me.config.baseurl + 'report.action',
					fieldCols: 1,
					fieldCls:{
						labelCls : 'form-td-label-120'
					},
					fields:fields,
					yes : function(data){
						$("#mainGrid").trigger("reloadGrid");
					}
				},{width: "450px",height: "150px",okVal:'销假'});
			});
		}else{
			$("#mainGrid").addSearchForm("#search",{rows:2});
		}
	},
	jzcsjy:function(){
		var me = this;
		me.educationButton("jzcsjy");
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
		$("#mainGrid").jqGrid({
			url		   : me.config.baseurl + 'listReadyJzcsjy.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 30,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 50,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',				      
				        width	: 20,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        searchType : 'cn',
				        label	: '矫正类别',
				        sortable: true,
				        width	: 30,
				        align	: 'center',
					    formatter : 'dictionary',
					    formatoptions: {code:'JZLB'}
			        } ,
			        {
				        name	: 'startdate',
				        index	: 'startdate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正开始日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.startdate!=null){
				        		return rwdat.startdate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        },{
				        name	: 'enddate',
				        index	: 'enddate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正结束日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.enddate!=null){
				        		return rwdat.enddate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        }, 
			        {
				        name	: 'days',
				        index	: 'days',
				        label	: '解矫期限',				      
				        width	: 30,
				        sortable: true,
				        formatter : 'days',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 120,
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.educationsInit(\""+rwdat.id+"\",\"jzcsjy\");' class='a-style'>教育</a>";//集中初始教育
				        	return actions;					        	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'orgName,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	jzjjqjy:function(){
		var me = this;
		me.educationButton("jzjjqjy");
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
		$("#mainGrid").jqGrid({
			url		   : me.config.baseurl + 'listReadyJzjjqjy.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 30,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 50,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',				      
				        width	: 20,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        searchType : 'cn',
				        label	: '矫正类别',
				        sortable: true,
				        width	: 30,
				        align	: 'center',
					    formatter : 'dictionary',
					    formatoptions: {code:'JZLB'}
			        } ,
			        {
				        name	: 'startdate',
				        index	: 'startdate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正开始日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.startdate!=null){
				        		return rwdat.startdate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        },{
				        name	: 'enddate',
				        index	: 'enddate',
				        sortable: true,
				        width	: 50,				       
				       	label	: '矫正结束日期',
				       	formatter : function(value, opts, rwdat) {
				       		if(rwdat.enddate!=null){
				        		return rwdat.enddate.replace("T", " ");
				       		}else{
				       			return "";
				       		}
				        },
				        align	: 'center'
			        }, 
			        {
				        name	: 'days',
				        index	: 'days',
				        label	: '解矫期限',				      
				        width	: 30,
				        sortable: true,
				        formatter : 'days',
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 120,
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.educations(\""+rwdat.id+"\",\"jzjjqjy\");' class='a-style'>教育</a>";//集中解矫前教育
				        	return actions;					        	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'orgName,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:2});
	},
	educationButton : function(flag){
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="集中教育"/>');
		$("#btnAdd").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择需要进行集中教育人员!");
				return false;
			}
			if(flag=='jzcsjy'){//集中初始教育
				$.fxryactions.educationsInit(ids,flag);
			}else{
				$.fxryactions.educations(ids,flag);
			}
		});
	},
	wfqk: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listJCQK.action?type=wf',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        },{
				        name	: 'code',
				        index	: 'code',
				        searchType : 'cn',
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        fixed: true,
				        width	: 50,
				        sortable: true,
				        searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'responseOrg',
				        index	: 'responseOrg',
				        width	: 130,
				        searchType : 'cn',
				        sortable: true,
				        formatter: 'organization',
				        searchOption: {
				        	useCOrg: true, 
							notShowType: '1,4,5,6,7,8,9', 
							showRoot:false,
							level:2,
							allowSearch: true
				        },
				       	label	: '矫正负责单位',
				        align	: 'center'
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '当前状态',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'inum',
				        index	: 'inum',
				        label	: '违法次数',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center'
			        },
			        {
				        name	: 'cnum',
				        index	: 'cnum',
				        label	: '再犯罪次数',
				        fixed: true,
				        width	: 80,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\",\"wfqk\");' title='' class='a-style'>奖惩</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'inum,cnum',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	}
});
})(jQuery);