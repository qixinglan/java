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
		rysb:{			
		}
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
		me[action].call(me);
	},
	sry: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listZJRY.action?showTgry=true',
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
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\");' class='a-style'>奖惩</a>";
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
		$("#mainGrid").addSearchForm("#search");
	},
	zjry: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'listZJRY.action',
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
				        width	: 120,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 120,
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
				        width	: 180,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' class='a-style'>查看</a>";
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\");' class='a-style'>奖惩</a>";
				        	actions += "<a href='javascript:$.fxryactions.export_doc(\""+rwdat.id+"\",\""+rwdat.name+"\");' class='a-style'>导出</a>";
				        	if (rwdat.state =='1' && rwdat.deviceCode){
				        		actions += "<a title='' class='a-style gray' href='javascript:$.fxryactions.locate(\""+rwdat.id+"\");'>定位</a>";
				        	}
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
		$("#mainGrid").addSearchForm("#search");
	},
	dzrry: function(){
		var me = this;
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
				        	notShowType: '1,3,4,5,6,7,8,9'
				        }
			        },{
				        name	: 'outTime',
				        index	: 'outTime',
				        searchInput : 'time',
				        sortable: true,
				        width	: 140,
				        searchType : 'in',
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
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\");' title='' class='a-style'>查看</a>";
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
				        hidden:true,
				        sortable: true,
				       	label	: '转出单位',
				        align	: 'center',
				        formatter : 'organization'
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
				        name	: 'receiveOrgId',
				        index	: 'receiveOrgId',
				        width	: 200,
				        sortable: true,
				       	label	: '接收单位',
				        align	: 'center',
				        formatter: function(value, opts, row){
							if (value){
								return $.fn.fmatter.organization(value);
							}
							else{
								return row.receiveOrgName + '(联系人:' + row.connectName + ',电话:' + row.connectPhone + ')';
							}
						}
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
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\");' title='' class='a-style'>查看</a>";
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
				        searchType: 'eq',
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'}
			        }, {
				        name	: 'responseOrg',
				        index	: 'responseOrg',
				        width	: 100,
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
				        width	: 100,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.fxryId+"\",\"tgry\");' class='a-style'>查看</a>";
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
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\",\"jjry\");' class='a-style'>查看</a>";
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
		$("#mainGrid").addSearchForm("#search",{rows:"2"});
	},
	yjjry:function(){
		var me = this;
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
			        } ,{
				        name	: 'orgName',
				        index	: 'orgName',
				        label	: '负责矫正单位',				       
				        width	: 50,
				        sortable: true,
				        align	: 'center'
			        },
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
				        width	: 100,
				        fixed: true,
				        align	: 'center',				     
				        formatter : function(value, opts, rwdat) {
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' title='' class='a-style'>查看</a>";
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
		$("#mainGrid").addSearchForm("#search");
	},
	zjwxbry:function(){
		var me = this;
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
			        } ,{
				        name	: 'orgName',
				        index	: 'orgName',
				        label	: '负责矫正单位',				       
				        width	: 50,
				        sortable: true,
				        align	: 'center'
			        },
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
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' title='' class='a-style'>查看</a>";
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
		$("#mainGrid").addSearchForm("#search");
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
	rysb:function(){	
		var me = this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="批量上报" onclick="javascript:$.fxryactions.syncFxrys();"/>');
		$("#mainGrid").jqGrid({
			url		    : CONTEXT_PATH + '/data/jzgl/sync/list.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
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
				       	label	: '矫正机构',
				        align	: 'center'
			        },{
				        name	: 'name',
				        index	: 'name',
				        searchType : 'cn',
				        label	: '姓名',
				        sortable: true,
				        width	: 100,
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
				        name	: 'state',
				        index	: 'state',
				        label	: '人员状态',
				        fixed: true,
				        width	: 80,
				        sortable: true,
						searchType : 'eq',
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'isok',
				        index	: 'isok',
				        label	: '数据性',
				        fixed: true,
				        width	: 150,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'SBSJYXX'}
				        	
			        },{
				        name	: 'saveTime',
				        index	: 'saveTime',
				        label	: '上次发起上报日期',
				        fixed: true,
				        width	: 120,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'realState',
				        index	: 'realState',
				        label	: '上次人员上报状态',
				        fixed: true,
				        width	: 120,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT'}
			        },{
				        name	: 'syncTime',
				        index	: 'syncTime',
				        label	: '上次完成上报时间',
				        fixed: true,
				        width	: 140,
				        sortable: true,
				        align	: 'center'
			        },{
				        name	: 'syncState',
				        index	: 'syncState',
				        label	: '上次上报状态',
				        fixed: true,
				        width	: 120,
				        sortable: true,
				        align	: 'center',
						searchType : 'eq',
						searchInput:'select',
						searchOption:{
							data:[{name:'成功',code:"1"},{name:'等待上报',code:"2"},{name:'失败',code:"3"}],
							displayField: 'name',
							valueField: 'code'
						},
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	if(value=='1'){
				        		actions = "成功";
				        	}
				        	if(value=='2'){
				        		actions = "等待上报";
				        	}
				        	if(value=='3'){
				        		actions = "失败";
				        	}
				        	return actions;	
				        }
			        },{
				        name	: 'cz', 
				        index	: 'cz',
				        label	: '操作',
				        width	: 150,
				        align	: 'center',
				        fixed : true,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	if(null==rwdat.syncState||"3"==rwdat.syncState){
				        		actions += "<a href='javascript:$.fxryactions.syncFxry(\""+rwdat.id+"\");' title='' class='a-style'>上报</a>";
				        	}
				        	actions += "<a href='javascript:$.fxryactions.syncView(\""+rwdat.id+"\",\""+rwdat.name+"\",\""+rwdat.sex+"\",\""+rwdat.identityCard+"\",\""+rwdat.responseOrg+"\");' title='' class='a-style'>上报情况</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\"rysb\");' title='' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'syncState,isok',
			sortorder: "desc,asc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:"3"});
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