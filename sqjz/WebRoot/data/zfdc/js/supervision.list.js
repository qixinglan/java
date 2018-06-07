(function ($) {
"use strict";
$.supervision = $.supervision || {};
$.supervision.list = $.supervision.list|| {};
$.extend($.supervision.list,{
	defaultConfig:{
		WJS:{},
		PR:{},
		PW:{},
		MR:{},
		UM:{},
		XJ:{},
		JJ:{},
		FE:{},
		CRIME:{},
		ILL:{}
	},
	init:function(action, options){
		var me = this;
		$("#mynav0").removeClass("navnew-c");
		$("#zfdc").addClass("navnew-c");
		var param="";
		if(options.sfsId){
			param="sfsId="+options.sfsId;
			$("#title").text($.organization.formatter()(options.sfsId));
		}else if(options.qxsfjId){
			param="qxsfjId="+options.qxsfjId;
			$("#title").text($.organization.formatter()(options.qxsfjId));
		}else if(options.orgId){
			param="orgId="+options.orgId;
		}
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.param=param;
		if(me[action]){
			me[action].call(me);
		}
	},
	setTitle:function(title){
		var me=this;
		var url="";
		if(me.config.orgId){
			$("#dcx").text($.organization.formatter()(me.config.orgId)+"督察项");
			$("#title").text(title);
			url=CONTEXT_PATH+"/data/zfdc/orgReport.jsp?orgId="+me.config.orgId;
		}else if(me.config.reportId){
			url=CONTEXT_PATH+"/data/zfdc/report.jsp?id="+me.config.reportId;
			$("#dcx").text(title);
		}
		$("#dcx").bind("click",function(){
			window.location.href=url;
		});
	},
	WJS:function(){
		var me=this;
		me.setTitle("人员报到");
		$("#mainGrid").jqGrid({
			url : CONTEXT_PATH +'/data/supervision/getWjsData.action?'+me.config.param,
			colModel : [{
				name : 'id',
				index : 'id',
				label : 'id',
				hidedlg : false,
				hidden : true
			},{
				name : 'responseOrg',
				index : 'responseOrg',
				width : 130,
				sortable : true,
				formatter : 'organization',
				label : '矫正负责单位',
				align : 'center'
			},{
				name : 'name',
				index : 'name',
				searchType : 'cn',
				label : '姓名',
				sortable : true,
				width : 100,
				align : 'center',
				formatter : function(value, opts, rwdat) {
					return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
				}
			},{
				name : 'code',
				index : 'code',
				label : '人员编号',
				sortable : true,
				width : 130,
				align : 'center'
			},{
				name : 'identityCard',
				index : 'identityCard',
				label : '身份证号',
				sortable : true,
				searchType : 'cn',
				width : 150,
				align : 'center'
			},{
				name : 'sex',
				index : 'sex',
				label : '性别',
				fixed : true,
				width : 50,
				sortable : true,
				searchType : 'eq',
				align : 'center',
				formatter : 'dictionary',
				formatoptions : {
					code : 'XB'
				}
			},{
				name : 'deviceCode',
				index : 'deviceCode',
				label : '是否电子监管',
				fixed : true,
				width : 90,
				sortable : true,
				align : 'center',
				formatter : function(value, opts, rwdat) {
					if (!rwdat.deviceCode) {
						return "否";
					} else {
						return "是";
					}
				}
			}],
			pager : '#mainGridPager',
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			multiselect : false
		});
		$("#mainGrid").addSearchForm("#search");
	},
	PR:function(){
		var me=this;
		me.setTitle("电话报告");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/phoneReport.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
				        name	: 'policename',
				        index	: 'policename',
				        sortable: true,
				        width	: 100,
				       	label	: '责任干警',
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	PW:function(){
		var me=this;
		me.setTitle("社区服务");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/publicWork.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
				        name	: 'policename',
				        index	: 'policename',
				        sortable: true,
				        width	: 100,
				       	label	: '责任干警',
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	MR:function(){
		var me=this;
		me.setTitle("当月报告");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/monthReport.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
				        name	: 'policename',
				        index	: 'policename',
				        sortable: true,
				        width	: 100,
				       	label	: '责任干警',
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	UM:function(){
		var me=this;
		me.setTitle("脱管人员");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/unManage.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 100,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 50,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.fxryId+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 100,
				        sortable: true,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 100,
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
				        width	: 200,
				        align	: 'center',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        		if (row.state == '3'){
				        			actions += "<a href='javascript:$.fxryfullinfo.inManage(\""+row.fxryId+"\");' class='a-style'>恢复矫正</a>";
				        		}		
				        		actions += "<a href='javascript:$.fxryfullinfo.viewDetail(\""+row.fxryId+"\");' class='a-style'>详情</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	XJ:function(){
		var me=this;
		me.setTitle("销假逾期");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/vacateList.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.fxryId+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
				        name	: 'startDate',
				        index	: 'startDate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '请假开始时间',
				        align	: 'center'
			        },{
				        name	: 'endDate',
				        index	: 'endDate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '请假结束时间',
				        align	: 'center'
			        },{
				        name	: 'reportDate',
				        index	: 'reportDate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '销假时间',
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	JJ:function(){
		var me=this;
		me.setTitle("解除矫正");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/release.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        label	: '矫正类别',
				        sortable: true,
				        width	: 80,
				        align	: 'center',
					    formatter : 'dictionary',
					    formatoptions: {code:'JZLB'}
			        },{
				        name	: 'startdate',
				        index	: 'startdate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '矫正开始时间',
				        align	: 'center'
			        },{
				        name	: 'enddate',
				        index	: 'enddate',
				        width	: 140,
				        sortable: true,
				        fixed : true,
				       	label	: '矫正结束时间',
				        align	: 'center'
			        },{
			        	name	: 'days',
				        index	: 'days',
				        label	: '解矫期限',				      
				        width	: 60,
				        sortable: true,
				        formatter : 'days',
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	FE:function(){
		var me=this;
		me.setTitle("集中初始教育");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/firstEducation.action?'+me.config.param,
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
						name : 'responseOrg',
						index : 'responseOrg',
						width : 130,
						sortable : true,
						formatter : 'organization',
						label : '矫正负责单位',
						align : 'center'
					},{
				        name	: 'name',
				        index	: 'name',
				        label	: '姓名',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center',
						formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						}
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        fixed: true,
				        width	: 150,
				        sortable: true,
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
				        name	: 'policename',
				        index	: 'policename',
				        sortable: true,
				        width	: 100,
				       	label	: '责任干警',
				        align	: 'center'
			        },{
			        	name	: 'receiveDate',
				        index	: 'receiveDate',
				        label	: '接收时间',				      
				        width	: 140,
				        sortable: true,
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	CRIME:function(){
		var me=this;
		me.setTitle("涉嫌再犯罪");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/crime.action?'+me.config.param,
			colModel: [
				{
					name	:'id',
					index	:'id',
					label	: 'id',
					hidedlg	:false,
					hidden	:true
				},{
					name : 'responseOrg',
					index : 'responseOrg',
					width : 130,
					sortable : true,
					formatter : 'organization',
					label : '矫正负责单位',
					align : 'center'
				},{
					name	: 'name',
				    index	: 'name',
				    label	: '姓名',
				    searchType: 'cn',
				    sortable: true,
				    width	: 150,
				    align	: 'center',
					formatter : function(value, opts, rwdat) {
						return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
					}
			    },{
				    name	: 'code',
				    index	: 'code',
				    label	: '人员编号',
				    fixed: true,
				    width	: 150,
				    sortable: true,
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
				    name	: 'policename',
				    index	: 'policename',
				    sortable: true,
				    width	: 100,
				  	label	: '责任干警',
				    align	: 'center'
			    }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	ILL:function(){
		var me=this;
		me.setTitle("暂监外病检");
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH +'/data/supervision/ill.action?'+me.config.param,
			colModel: [
				{
					name	:'id',
					index	:'id',
					label	: 'id',
					hidedlg	:false,
					hidden	:true
				},{
					name : 'responseOrg',
					index : 'responseOrg',
					width : 130,
					sortable : true,
					formatter : 'organization',
					label : '矫正负责单位',
					align : 'center'
				},{
					name	: 'name',
				    index	: 'name',
				    label	: '姓名',
				    searchType: 'cn',
				    sortable: true,
				    width	: 150,
				    align	: 'center',
					formatter : function(value, opts, rwdat) {
						return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
					}
			    },{
				    name	: 'code',
				    index	: 'code',
				    label	: '人员编号',
				    fixed: true,
				    width	: 150,
				    sortable: true,
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
				    name	: 'policename',
				    index	: 'policename',
				    sortable: true,
				    width	: 100,
				  	label	: '责任干警',
				    align	: 'center'
			    }
			],
			multiselect : false,
			rownumbers	: true,
			sortname : "responseOrg,name",
			sortorder : "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	}
});
})(jQuery);