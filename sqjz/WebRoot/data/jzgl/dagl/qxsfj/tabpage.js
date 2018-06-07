(function ($) {
"use strict";
$.fxrytabpage = $.fxrytabpage || {};
$.extend($.fxrytabpage,{
	defaultConfig:{
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
		}
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
		me.config.menu=[{
			text:'提醒事项', 
			submenus:[
				{text:'待转入人员', content:'dzrry',count:'(0人)'},
				//{text:'暂监外续保人员',content:'zjwxbry',count:'(0人)'},
				{text:'预解矫人员', content:'yjjry',count:'(0人)'}]
		}];
		me.config.menuread='rctxCount.action';
		me[action].call(me);
	},
	sry: function(a){
		var me = this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="报到登记" onclick="javascript:$.fxryactions.add();"/>');
		//$('#buttons').append('<input type="button" class="bttn bicon-delete" id="btnDel" onclick="javascript:$.fxryactions.del();" value="删除" />');
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
							showRoot:true, 
							allowSearch: true
				        },
				       	label	: '矫正负责单位',
				        align	: 'center'
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 80,
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
				        width	: 260,
				        align	: 'right',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	if (rwdat.state =='1'){
				        		if (!rwdat.deviceCode){
				        			actions += "<a href='javascript:$.fxryactions.bindEquip(\""+rwdat.id+"\");' class='a-style' style='color:red'>佩戴设备</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.removeEquip(\""+rwdat.id+"\", \""+rwdat.name+"\",\""+rwdat.deviceCode+"\");' class='a-style' >解除设备</a>";
				        		}
				        	}
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' class='a-style'>查看</a>";
				        	//if (!rwdat.excuteId){
				        		actions += "<a href='javascript:$.fxryactions.editbase(\""+rwdat.id+"\");' class='a-style'>编辑</a>";
				        	//}
				        	//else{
				        		//actions += "<a href='javascript:$.fxryactions.edit(\""+rwdat.id+"\");' class='a-style'>编辑</a>";
				        	//}
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\");' class='a-style'>奖惩</a>";
				        	if (rwdat.state == '1'){
			        			actions += "<a href='javascript:$.fxryactions.moveOut(\""+rwdat.id+"\");' class='a-style'>转出</a>";
			        			actions += "<a href='javascript:$.fxryactions.moveOutBJ(\""+rwdat.id+"\");' class='a-style'>离京</a>";
				        	}
				        	else if(rwdat.state == '6'){
				        		actions += "<a href='javascript:$.fxryactions.cancelMoveOut(\""+rwdat.id+"\");' class='a-style'>取消转出</a>";
				        		actions += "<a title='' class='a-style gray'>离京</a>";
				        	}else{
				        		actions += "<a title='' class='a-style gray'>转出</a>";
				        		actions += "<a title='' class='a-style gray'>离京</a>";
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
	zjry: function(){
		var me = this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="报到登记" onclick="javascript:$.fxryactions.add();"/>');
		//$('#buttons').append('<input type="button" class="bttn bicon-delete" id="btnDel" onclick="javascript:$.fxryactions.del();" value="删除" />');
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
				        width	: 110,
				        align	: 'center'
			        }, {
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        sortable: true,
				        searchType: 'cn',
				        width	: 110,
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
							showRoot:true, 
							allowSearch: true
				        },
				       	label	: '矫正负责单位',
				        align	: 'center'
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        sortable: true,
				        width	: 80,
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
				        width	: 340,
				        align	: 'center',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, rwdat) {
				        	var actions = '';
				        	if (rwdat.state =='1'){
				        		if (!rwdat.deviceCode){
				        			actions += "<a href='javascript:$.fxryactions.bindEquip(\""+rwdat.id+"\");' class='a-style' style='color:red'>佩戴设备</a>";
				        		}
				        		else{
				        			actions += "<a href='javascript:$.fxryactions.removeEquip(\""+rwdat.id+"\", \""+rwdat.name+"\",\""+rwdat.deviceCode+"\");' class='a-style' >解除设备</a>";
				        		}
				        	}
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\");' class='a-style'>查看</a>";
				        	//if (!rwdat.excuteId){
				        		actions += "<a href='javascript:$.fxryactions.editbase(\""+rwdat.id+"\");' class='a-style'>编辑</a>";
				        	//}
				        	//else{
				        		//actions += "<a href='javascript:$.fxryactions.edit(\""+rwdat.id+"\");' class='a-style'>编辑</a>";
				        	//}
				        	actions += "<a href='javascript:$.fxryactions.reward(\""+rwdat.id+"\");' class='a-style'>奖惩</a>";
				        	actions += "<a href='javascript:$.fxryactions.export_doc(\""+rwdat.id+"\",\""+rwdat.name+"\");' class='a-style'>导出</a>";
				        	if (rwdat.state == '1'){
			        			actions += "<a href='javascript:$.fxryactions.moveOut(\""+rwdat.id+"\");' class='a-style'>转出</a>";
			        			actions += "<a href='javascript:$.fxryactions.moveOutBJ(\""+rwdat.id+"\");' class='a-style'>离京</a>";
				        	}
				        	else if(rwdat.state == '6'){
				        		actions += "<a href='javascript:$.fxryactions.cancelMoveOut(\""+rwdat.id+"\");' class='a-style'>取消转出</a>";
				        		actions += "<a title='' class='a-style gray'>离京</a>";
				        	}else{
				        		actions += "<a title='' class='a-style gray'>转出</a>";
				        		actions += "<a title='' class='a-style gray'>离京</a>";
				        	}
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
				        	var actions =""; 
				        	actions += "<a href='javascript:$.fxryactions.moveIn(\""+rwdat.fxryId+"\",\""+moveInreturn+"\");' class='a-style' style='color:red'>接收</a>";
				        	actions +="<a href='javascript:$.fxryactions.view(\""+rwdat.fxryId+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
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
							showRoot:true, 
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
				        	actions += "<a href='javascript:$.fxryactions.edit(\""+row.fxryId+"\",\"tgry\");'class='a-style'>编辑</a>";
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
		$("#mainGrid").addSearchForm("#search",{rows:"2"});
	},
	yjjry:function(){
		var me = this;
		var returnurl="rcgztx";
		if(me.config.returnurl){
			returnurl=me.config.returnurl;
		}
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
				        	var actions = "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
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
				        	var actions="";
				        	actions += "<a href='javascript:$.fxryactions.adjusttermmodify(\""+rwdat.id+"\");' class='a-style' style='color:red'>续保</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+rwdat.id+"\",\""+returnurl+"\");' title='' class='a-style'>查看</a>";
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
		$('#' + id).append('<table width="100%"><tr><td nowrap="nowrap" class="tabpage-menu" style="width:180px;"></td><td class="tabpage-content" width="500px"></td></tr></table>');
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
	form_error: function(container){
		$(container).append("<p>该功能还在开发中...</p>");
	},
	form_dzrry: function(container, content){
		var me = this;
		var header_top_width = $(".tabpage-label-container").css("width");
		var contain_width = header_top_width.substr(0,header_top_width.indexOf("px"))-185;
		var grid="<div class='tabpage' style='width:"+contain_width+"px'><div class='container-middle'><div id='search'></div></div>" +
				"<div class='container-bottom'><div id='buttons' class='buttonArea operation'></div>" +
				"<table id='mainGrid'></table><div id='mainGridPager'></div></div></div>";
		$(container).append(grid);
		me.dzrry();
	},
	form_zjwxbry: function(container, content){
		var me = this;
		var header_top_width = $(".tabpage-label-container").css("width");
		var contain_width = header_top_width.substr(0,header_top_width.indexOf("px"))-185;
		var grid="<div class='tabpage' style='width:"+contain_width+"px'><div class='container-middle'><div id='search'></div></div>" +
			"<div class='container-bottom'><div id='buttons' class='buttonArea operation'></div>" +
			"<table id='mainGrid'></table><div id='mainGridPager'></div></div></div>";
		$(container).append(grid);
		me.zjwxbry();
	},
	form_yjjry: function(container, content){
		var me = this;
		var header_top_width = $(".tabpage-label-container").css("width");
		var contain_width = header_top_width.substr(0,header_top_width.indexOf("px"))-185;
		var grid="<div class='tabpage' style='width:"+contain_width+"px'><div class='container-middle' ><div id='search'></div></div>" +
			"<div class='container-bottom'><div id='buttons' class='buttonArea operation'></div>" +
			"<table id='mainGrid'></table><div id='mainGridPager'></div></div></div>";
		$(container).append(grid);
		me.yjjry();
	},
	qjxx: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'vacateSearch.action',
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},{
				        name	: 'recordOrgId',
				        index	: 'recordOrgId',
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
				        label	: '人员编号',
				        sortable: true,
				        width	: 130,
				        align	: 'center'
			        }, {
				        name	: 'fxryidentirycard',
				        index	: 'fxryidentirycard',
				        label	: '身份证号',
				        sortable: true,
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
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'endDate,fxrycode',
			sortorder: "asc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search",{rows:"2"});
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