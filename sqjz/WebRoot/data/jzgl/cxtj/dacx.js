(function ($) {
"use strict";
$.fxryfullinfo = $.fxryfullinfo || {};
$.extend($.fxryfullinfo,{
	defaultConfig:{
		view:{
			read:  CONTEXT_PATH + '/data/jzgl/cxtj/dacx/'+ "list.action",
			editable: false,
			showBaseinfo: true
		}
	},
	init: function(action,orgId,options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options);
		me.config.orgId = orgId;
		me[action].call(me);
	},
	viewDetail:function(id){
		$.dialog({ 
		id: 'detail',
	    width: '1200px', 
		height: '700px', 
		title: '详细信息查看',
	    content: 'url:'+CONTEXT_PATH+'/data/jzgl/dagl/zhcxview.jsp?id='+id,
		cancelVal: '关闭', 
		cancel: true 
		}).max();
	},
	view:function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url: me.config.read,
			colModel: [{
						name:'id',
						index:'id',
						label:'id',
						hidedlg:false,
						hidden:true
					},{
						name: 'responseOrg',
						index	: 'responseOrg',
						label	: '矫正负责单位',
	        			formatter: 'organization',
	        			width	: 150,
	        			align	: 'center',
	        			searchType : 'cn',
	        			searchOption: {
	        				useCOrg: true, 
        					notShowType: '1,4,5,6,7,8,9', 
        					level:2,
        					showRoot:false, 
        					allowSearch: true
        				},
        				searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo'
        				},
        				fixed :true,
	        			sortable: true
	        		},{
			        	name: 'name',
			        	index: 'name',
			        	label: '姓名',
			        	formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						},
				        width	: 130,
				        align	: 'center',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo'
        				},
				        searchType : 'cn',
				        fixed: true,
				        sortable: true
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'},
			        	width	: 35,
			        	align	: 'center',
			        	searchType : 'eq',
			        	searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo'
        				},
			        	fixed: true,
			        	sortable: true
			        },{
				        name	: 'code',
				        index	: 'code',
				        label	: '人员编号',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
				        sortable: true,
				        fixed :true
			        }, {
				        name	: 'adjustType',
				        index	: 'adjustType',
				        label	: '矫正类型',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZLB'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true,
        				},
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'state',
				        index	: 'state',
				        label	: '人员状况',
				        width	: 130,
				        formatter : 'dictionary',
				        formatoptions: {code:'JZRYZT',filter:'5,999'},
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'identityCard',
				        index	: 'identityCard',
				        label	: '身份证号',
				        width	: 130,
				        align	: 'center',
				        searchType: 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
        				fixed :true,
				        sortable: true
			        },{
				        name	: 'userdName',
				        index	: 'userdName',
				        label	: '曾用名',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'birthdate',
				        index	: 'birthdate',
				        label	: '出生日期',
				        width	: 70,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'isadult',
				        index	: 'isadult',
				        label	: '是否成年',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'isdeviceCode',
				        index	: 'isdeviceCode',
				        label	: '是否电子监管',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 90,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'deviceCode',
				        index	: 'deviceCode',
				        label	: '监管设备编号',
				        width	: 90,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },  {
				        name	: 'nation',
				        index	: 'nation',
				        label	: '民族',
				        formatter : 'dictionary',
				        formatoptions: {code:'MZ',allowSearch: true},
				        width	: 130,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true
			        }, {
				        name	: 'passport',
				        index	: 'passport',
				        label	: '护照号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'hrPermit',
				        index	: 'hrPermit',
				        label	: '回乡证号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'hkIdentity',
				        index	: 'hkIdentity',
				        label	: '香港身份证号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'amIdentity',
				        index	: 'amIdentity',
				        label	: '澳门身份证号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'tbIdentity',
				        index	: 'tbIdentity',
				        label	: '台胞证号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'gatPermit',
				        index	: 'gatPermit',
				        label	: '港澳台通行证号码',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'health',
				        index	: 'health',
				        label	: '健康状况',
				        formatter : 'dictionary',
				        formatoptions: {code:'JKZK'},
				        width	: 130,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'healthSpecific',
				        index	: 'healthSpecific',
				        label	: '具体健康状况',
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'healthContagion',
				        index	: 'healthContagion',
				        label	: '具体传染病史',
				        formatter : 'dictionary',
				        formatoptions: {code:'CRB'},
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'psychosis',
				        index	: 'psychosis',
				        label	: '是否有精神病',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'healthMental',
				        index	: 'healthMental',
				        label	: '心理是否健康',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'healthMentalSpecific',
				        index	: 'healthMentalSpecific',
				        label	: '具体心理健康状况',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'accreditingOrgan',
				        index	: 'accreditingOrgan',
				        label	: '心理鉴定机构',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'politicsStatusOriginal',
				        index	: 'politicsStatusOriginal',
				        label	: '原政治面貌',
				        formatter : 'dictionary',
				        formatoptions: {code:'ZZMM',allowSearch: true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
			        	name	: 'politicsStatus',
				        index	: 'politicsStatus',
				        label	: '政治面貌',
				        formatter : 'dictionary',
				        formatoptions: {code:'ZZMM',allowSearch: true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'phoneNumber',
				        index	: 'phoneNumber',
				        label	: '联系电话',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'natureHome',
				        index	: 'natureHome',
				        label	: '户籍性质',
				        formatter : 'dictionary',
				        formatoptions: {code:'HJXZ'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'iscapital',
				        index	: 'iscapital',
				        label	: '是否京籍',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'houseAddress',
				        index	: 'houseAddress',
				        label	: '居住地详细地址',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'homeAddress',
				        index	: 'homeAddress',
				        label	: '户籍详细地址',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'educationDegree',
				        index	: 'educationDegree',
				        label	: '文化程度',
				        formatter : 'dictionary',
				        formatoptions: {code:'WHCD',allowSearch: true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'maritalState',
				        index	: 'maritalState',
				        label	: '婚姻状况',
				        formatter : 'dictionary',
				        formatoptions: {code:'HYZK'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'workState',
				        index	: 'workState',
				        label	: '就业就学情况',
				        formatter : 'dictionary',
				        formatoptions: {code:'JYJX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        }, {
				        name	: 'workNuit',
				        index	: 'workNuit',
				        label	: '现工作单位',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'workNuitPhone',
				        index	: 'workNuitPhone',
				        label	: '单位联系电话',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'resumeInfo',
				        index	: 'resumeInfo',
				        label	: '个人简历',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: false,
				        hidden  : true
			        },{
				        name	: 'socialRelation',
				        index	: 'socialRelation',
				        label	: '社会关系',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'基本身份信息', 
        					content:'baseInfo',
        					hiddenResult: true
        				},
				        sortable: false,
				        hidden  : true
			        },{
				        name	: 'investigateUnit',
				        index	: 'investigateUnit',
				        label	: '侦查机关',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hiddenResult: true,
				        hidden  : true
			        },{
				        name	: 'dateDetention',
				        index	: 'dateDetention',
				        label	: '拘留日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateRecord',
				        index	: 'dateRecord',
				        label	: '立案日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'permitArrestUnit',
				        index	: 'permitArrestUnit',
				        label	: '决定/批准逮捕机关',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'dateArrest',
				        index	: 'dateArrest',
				        label	: '逮捕日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateEndInvestigate',
				        index	: 'dateEndInvestigate',
				        label	: '侦结日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'publicProsecution',
				        index	: 'publicProsecution',
				        label	: '公诉机关',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'indictmentNumber',
				        index	: 'indictmentNumber',
				        label	: '起诉书编号',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateIndictment',
				        index	: 'dateIndictment',
				        label	: '提起起诉日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'trialUnit',
				        index	: 'trialUnit',
				        label	: '审判机关',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'judgmentNumber',
				        index	: 'judgmentNumber',
				        label	: '判决书编号',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateJudgment',
				        index	: 'dateJudgment',
				        label	: '判决日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'informNumber',
				        index	: 'informNumber',
				        label	: '执行通知书文号',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'dateInform',
				        index	: 'dateInform',
				        label	: '执行通知书日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'orgdetentionAddress',
				        index	: 'orgdetentionAddress',
				        label	: '原羁押场所',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'dateExecute',
				        index	: 'dateExecute',
				        label	: '交付执行日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'decideUnit',
				        index	: 'decideUnit',
				        label	: '社区矫正决定机关',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'writType',
				        index	: 'writType',
				        label	: '文书类型',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZLB'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'writNumber',
				        index	: 'writNumber',
				        label	: '文书编号',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'writEffectiveDate',
				        index	: 'writEffectiveDate',
				        label	: '文书生效日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'majorCrime',
				        index	: 'majorCrime',
				        label	: '主要犯罪事实',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'administrativePenalty',
				        index	: 'administrativePenalty',
				        label	: '行政处罚/处分',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'criminalPunshment',
				        index	: 'criminalPunshment',
				        label	: '刑事处罚',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'法律文书信息', 
        					content:'legalInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        hidden  : true
			        },{
				        name	: 'adjustPeriod',
				        index	: 'adjustPeriod',
				        label	: '矫正期限',
				        width	: 140,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateSAdjust',
				        index	: 'dateSAdjust',
				        label	: '矫正起始日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateEAdjust',
				        index	: 'dateEAdjust',
				        label	: '矫正结束日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'crimeType',
				        index	: 'crimeType',
				        label	: '犯罪类型',
				        formatter : 'dictionary',
				        formatoptions: {code:'FZLX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'accusation',
				        index	: 'accusation',
				        label	: '具体罪名',
				        formatter : 'dictionary',
				        formatoptions: {
				        	code:'JTZM',
				        	allowSearch: true
				        },
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'oriPunishment',
				        index	: 'oriPunishment',
				        label	: '原判刑罚',
				        formatter : 'dictionary',
				        formatoptions: {code:'XFLX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateSOri',
				        index	: 'dateSOri',
				        label	: '原判刑起始日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateEOri',
				        index	: 'dateEOri',
				        label	: '原判刑结束日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'oriPeriod',
				        index	: 'oriPeriod',
				        label	: '原判刑期',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'imprisonmentPeriod',
				        index	: 'imprisonmentPeriod',
				        label	: '有期徒刑期限',
				        formatter : 'dictionary',
				        formatoptions: {code:'YQTXQX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'addpunishment',
				        index	: 'addpunishment',
				        label	: '附加刑',
				        formatter : 'dictionary',
				        formatoptions: {code:'FJX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'nonpoliticalPeriod',
				        index	: 'nonpoliticalPeriod',
				        label	: '剥夺政治权利期限',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateSNonpolitical',
				        index	: 'dateSNonpolitical',
				        label	: '剥夺政治权利起始日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateENonpolitical',
				        index	: 'dateENonpolitical',
				        label	: '剥夺政治权利结束日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'receiveUnit',
				        index	: 'receiveUnit',
				        label	: '接收剥夺政治权利人员公安机关名称',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'receivePerson',
				        index	: 'receivePerson',
				        label	: '接收人',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateTransfer',
				        index	: 'dateTransfer',
				        label	: '移交日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'sisType',
				        index	: 'sisType',
				        label	: '是否“四史”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SS',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'sansType',
				        index	: 'sansType',
				        label	: '是否“三涉”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SANS',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'isRecidivism',
				        index	: 'isRecidivism',
				        label	: '是否累犯',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'slfzType',
				        index	: 'slfzType',
				        label	: '是否“三类犯罪”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SLFZ',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'isalone',
				        index	: 'isalone',
				        label	: '是否共同犯罪',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'isforbidden',
				        index	: 'isforbidden',
				        label	: '是否被宣告禁令',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'forbidden',
				        index	: 'forbidden',
				        label	: '禁止令内容',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateSForbidden',
				        index	: 'dateSForbidden',
				        label	: '禁止令起始日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateEForbidden',
				        index	: 'dateEForbidden',
				        label	: '禁止令结束日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'dateReceive',
				        index	: 'dateReceive',
				        label	: '服刑人员接收日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },
			        {
				        name	: 'receiveType',
				        index	: 'receiveType',
				        label	: '接收方式',
				        formatter : 'dictionary',
				        formatoptions: {code:'JSFS'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },
			        {
				        name	: 'reportInfo',
				        index	: 'reportInfo',
				        label	: '报到情况',
				        formatter : 'dictionary',
				        formatoptions: {code:'BDQK'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },
			        {
				        name	: 'isgroupInfo',
				        index	: 'isgroupInfo',
				        label	: '是否建立矫正小组',
				        formatter : function(value, opts, rwdat) {
				        	var formatter=$.dictionary.formatter('SF');
							return formatter(value);
						},
				        width	: 140,
				        align	: 'center',
				        searchInput:'dictionary',
				        searchOption: {
				        	code:'SF'
        				},
				        searchType : 'eq',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'groupInfo',
				        index	: 'groupInfo',
				        label	: '矫正小组情况',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZXZRYQK',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'eremark',
				        index	: 'eremark',
				        label	: '刑罚执行备注',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'刑罚执行信息', 
        					content:'executeInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'removeReason',
				        index	: 'removeReason',
				        label	: '解除/终止矫正原因',
				        formatter : 'dictionary',
				        formatoptions: {code:'JJYY'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'removeDate',
				        index	: 'removeDate',
				        label	: '解除/终止矫正日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'jailType',
				        index	: 'jailType',
				        label	: '收监执行类型',
				        formatter : 'dictionary',
				        formatoptions: {code:'SJZXLX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'jailDate',
				        index	: 'jailDate',
				        label	: '收监执行日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'jailReason',
				        index	: 'jailReason',
				        label	: '收监执行原因',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'deathDate',
				        index	: 'deathDate',
				        label	: '死亡日期',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'deathReason',
				        index	: 'deathReason',
				        label	: '死亡原因',
				        width	: 140,
				        align	: 'center',
				        searchType : 'in',
				        searchInput : 'date',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'deathReasonDetail',
				        index	: 'deathReasonDetail',
				        label	: '具体死因',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'performance',
				        index	: 'performance',
				        label	: '矫正期间表现',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZBX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'manner',
				        index	: 'manner',
				        label	: '认罪态度',
				        formatter : 'dictionary',
				        formatoptions: {code:'RZTD'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'istrained',
				        index	: 'istrained',
				        label	: '是否参加职业技能培训',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'hascertificate',
				        index	: 'hascertificate',
				        label	: '是否获得职业技能证书',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'speciality',
				        index	: 'speciality',
				        label	: '技术特长及等级',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'issanwu',
				        index	: 'issanwu',
				        label	: '是否三无人员',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'risk',
				        index	: 'risk',
				        label	: '危险性评估',
				        formatter : 'dictionary',
				        formatoptions: {code:'WXXPG'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'familyContact',
				        index	: 'familyContact',
				        label	: '家庭联系情况',
				        formatter : 'dictionary',
				        formatoptions: {code:'JTLXQK'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'remark',
				        index	: 'remark',
				        label	: '特殊情况备注及帮教建议',
				        width	: 140,
				        align	: 'center',
				        searchPartition:{
        					text:'解除/终止矫正信息', 
        					content:'releaseInfo',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'isoutmanage',
				        index	: 'isoutmanage',
				        label	: '是否脱管',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'奖惩信息', 
        					content:'rewardPunish',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'isreward',
				        index	: 'isreward',
				        label	: '是否获得奖励',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
				        	text:'奖惩信息', 
        					content:'rewardPunish',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'ispunish',
				        index	: 'ispunish',
				        label	: '是否违纪 ',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
				        	text:'奖惩信息', 
        					content:'rewardPunish',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'isillegal',
				        index	: 'isillegal',
				        label	: '是否违法',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
				        	text:'奖惩信息', 
        					content:'rewardPunish',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        },{
				        name	: 'iscrime',
				        index	: 'iscrime',
				        label	: '是否涉嫌再犯罪',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 60,
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
				        	text:'奖惩信息', 
        					content:'rewardPunish',
        					hiddenResult: true
        				},
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'responseOrg,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		me.addSearchForm($("#mainGrid"),"#search");
	},
	buildTabPagesMenu: function(container,menu){
		var me = this;
		var collapsefn = function(submenu){
			return function(){
				var content=$(this).attr('content');
				if($("#div_"+content).position()){
					var t=$("#div_"+content).position().top;
					$(".content").scrollTop(t);
				}
				/*if ($(submenu).attr("collapse") == "true"){
					$(submenu).show();
					$(submenu).attr("collapse", "false");
				}
				else{
					$(submenu).hide();
					$(submenu).attr("collapse", "true");
				}*/
			}
		}
		var buildmenu = function(container, menus, cls){
			var $ul = $('<ul></ul>');
			$ul.addClass('tabpage-group-' + cls);
			$(container).append($ul);
			for (var i in menus){
				var menu = menus[i];
				var $li = $('<li></li>');
				$li.addClass('tabpage-items-' + cls);
				$li.append('<a>' + menu.text + '</a>');
				$ul.append($li);
				if (menu.submenus){
					$li.attr('content', menu.content);
					var $submenuli = $('<li></li>');
					$ul.append($submenuli);
					$li.click(collapsefn($submenuli));
					buildmenu($submenuli, menu.submenus, 'submenu');
				}
				else{
					$li.attr('content', menu.content);
					$li.attr('clickable', true);
					$li.click(function(){
						var content=$(this).attr('content');
						if($("#"+content).position()){
							var t=$("#"+content).position().top;
							$(".content").scrollTop(t);
						}
						$.each($('.tabpage-menu .tabpage-items-submenu'), function(i, item){
							if ($(item).attr('content') == content){
								$(item).addClass('tabpage-items-submenu-selected');
							}
							else{
								$(item).removeClass('tabpage-items-submenu-selected');
							}
						});
					});
				}
			}
		};
		buildmenu(container, menu, 'menu');
	},
	orgConAndResult:function(grid,conditions,results,menu){
		var celModel = grid.getGridParam('colModel');
		var colNames = grid.getGridParam('colNames');
		for (var i in celModel){
			var model = celModel[i];
			if (model.searchType){
				model.label = model.label ? model.label : colNames[i];
				var text="其他信息"; 
				var content="other";
				if(model.searchPartition){
					text=model.searchPartition.text;
					content=model.searchPartition.content;
				}
				if(conditions[content]){
					conditions[content].fields.push(model);
				}else{
					conditions[content]={};
					conditions[content].text=text;
					conditions[content].content="condition_"+content;
					conditions[content].fields=[];
					conditions[content].fields.push(model);
				}
			}
		}
		for (var i in celModel){
			var model = celModel[i];
			if (model.searchPartition&&model.searchPartition.hiddenResult){
				model.label = model.label ? model.label : colNames[i];
				var text=model.searchPartition.text; 
				var content=model.searchPartition.content;
				if(results[content]){
					results[content].fields.push(model);
				}else{
					results[content]={};
					results[content].text=text;
					results[content].content="result_"+content;
					results[content].fields=[];
					results[content].fields.push(model);
				}
			}
		}
		var condition={text:'查询条件定制',content:"condition",submenus:[]};
		var result={text:'查询结果定制',content:"result",submenus:[]};
		for(var i in conditions){
			var con={};
			con.text=conditions[i].text;
			con.content=conditions[i].content;
			condition.submenus.push(con);
		}
		for(var i in results){
			var con={};
			con.text=results[i].text;
			con.content=results[i].content;
			result.submenus.push(con);
		}
		menu.push(condition);
		menu.push(result);
	},
	addSearchForm : function(grid,elem){
		var me =this;
		var id = 'tabpages-container';
		var containerId=$.container.generate(".container-middle", {
			id: id,
			type: 'panel',
			padding: '0px'
		});
		var container_width = $(".container-top").css("width");
		var table_width = container_width.substr(0,container_width.indexOf("px"))-185;
		
		$('#' + containerId).css('min-width', table_width+"px");
		$('#' + id).css('borderTop', '1px solid #d8d6d6');
		$('#' + id).append('<table width="100%"><tr><td nowrap="nowrap" class="tabpage-menu"></td><td class="tabpage-content"></td></tr></table>');
		var conditionFields={};
		var resultFields={};
		var menu=[];
		me.orgConAndResult(grid, conditionFields, resultFields, menu);
		me.buildTabPagesMenu('.tabpage-menu',menu);
		$('li[clickable="true"]:first').click();
		var idPrefix = elem.replace('#', '');
		var formId = idPrefix + '_form';
		var opt={type:'form',id:formId,cls:"search-form-container"};
		$.container.generate('.tabpage-content',opt);
		
		var searchBtnId = idPrefix + '_searchBtn';
		var resetBtnId = idPrefix + '_resetBtn';
		var formContainer = $('<table class="search-table"></table>');
		$("#"+formId).append(formContainer);
		var table = $('#' + formId + ' table[class="search-table"]');
		var trs = [];
		var tr = $('<tr></tr>');
		var jgid=me.addSearchFileds(grid,conditionFields,resultFields,tr);
		trs.push(tr);
		table.append(tr);
		if (trs.length > 0){
			trs[0].append('<td class="search-btns-container" style="width:50px;vertical-align: top;">'+
					'<table><tr><td><input id="' + searchBtnId + '" type="button" class="bttn bicon-search"  value="查询"/></td></tr>'+
					'<tr><td><input id="' + resetBtnId + '" type="button" class="bttn bicon-reset"  value="重置"/></td></tr></table></td>');
			for (var i = 1; i < trs.length - 1;){
				trs[i].append('<td class="search-btns-container"></td>');
				i += 1;
			}
		}
		$("#"+searchBtnId).click(function(){
			//查询--Grid显示选定的查询列
			$.each(grid.searchFormFields, function(i, item){
				var value =  item.elem.val();
				var name=item.name;
				if (value){
					$("#"+name).attr("checked","true");
				}
			});
			$.each($("#"+jgid+" input"),function(i,n){
				if($(n).attr("checked")){
					grid.setGridParam().showCol($(n).attr("id"));
				}else{
					grid.setGridParam().hideCol($(n).attr("id"));
				}
			});
		});
		$("#"+resetBtnId).click(function(){
			//查询--Grid显示选定的查询列
			$.each($("#"+jgid+" input"),function(i,n){
				$(n).removeAttr("checked");
			});
		});
		grid.bindSearchForm('#' + formId, '#' + searchBtnId, '#' + resetBtnId);
	},
	insertField: function(tr, field){
		tr.append('<td class="search-form-label">' + field.label + '</td>');
		var td =$('<td class="search-form-field-container"></td>');
		var checkbox=$('<input type="checkbox" id="'+field.name+'"/>');
		if(!field.hidden){
			checkbox.attr("checked","checked");
		}
		td.append(checkbox);
		tr.append(td);
	},
	addOperationBox:function(elem,title,id,fields,maker){
		var nid=$.fields.random(id);
		var divid="div_"+id;
		var aid="a_"+nid;
		var container = $('<div class="operation-box container-main"><div class="title"><span></span> <label>'+title+'：</label>'
				+'<div id="'+divid+'" class="spanTurnOff" style="float: right;margin-right: 20px;"><a id="'+aid+'"></a></div></div><div class="comm-div" id="'+nid+'">'
				+'</div></div>');
		elem.find(".contentWrapper").append(container);
		/*elem.find("#"+aid).click(function() {
			if ($("#"+divid).attr("class") == "spanTurnOff") {
				$("#"+divid).removeClass("spanTurnOff");
				$("#"+divid).addClass("spanTurnOn");
				$("#"+aid).text("\u5c55\u5f00");
				$("#"+nid).hide();
			} else {
				$("#"+divid).removeClass("spanTurnOn");
				$("#"+divid).addClass("spanTurnOff");
				$("#"+aid).text("\u6536\u8d77");
				$("#"+nid).show();
			}
		});*/
		
		for(var i in fields){
			container.find("#"+nid).append('<div id="'+fields[i].content+'"><div class="container-search-title"><div class="container-form-title-text">'+fields[i].text+'</div></div><div><table class="comm-table"></table></div></div>');
			var table = container.find("#"+fields[i].content).find(".comm-table");
			var trs = [];
			for (var j in fields[i].fields){
				if (j % 2 == 0){
					var tr = $('<tr></tr>');
					trs.push(tr);
					table.append(tr);
				}
				if(maker){
					maker.insertField(tr, fields[i].fields[j]);
				}else{
					this.insertField(tr, fields[i].fields[j]);
				}
			}
			var j = fields[i].fields.length % 2;
			j = j ? (2 - j) : 0;//如果不是3的倍数，则需要补齐
			for (var k = 0; k < j;){
				tr.append('<td></td><td></td>');
				k += 1;
			}
			if(!maker){
				var selectAll = $('<input type="checkbox" content="'+fields[i].content+'"/><font>&nbsp;全选</font>');
				container.find("#"+fields[i].content).find(".container-form-title-text").append(selectAll);
				selectAll.click(function() {
					var chkedFlg = $(this).attr("checked");
					var content=$(this).attr("content")
					container.find("#"+content).find("input").each(function() {
						if (chkedFlg) {
							$(this).attr("checked","true");
						} else {
							$(this).removeAttr("checked");
						}
					});
				});
			}
		}
		if(!maker){
			var selectAll = $('<input type="checkbox"/><font>&nbsp;全选</font>');
			container.find("label").append(selectAll);
			selectAll.click(function() {
				var chkedFlg = $(this).attr("checked");
				container.find("input").each(function() {
					if (chkedFlg) {
						$(this).attr("checked","true");
					} else {
						$(this).removeAttr("checked");
					}
				});
			});
		}
		return nid;
	},
	addSearchFileds:function(grid,conditionFields,resultFields,parenttr){
		parenttr.append("<td><div class='content' style='height: 500px;min-width:770px;overflow-y: scroll;'><div class='contentWrapper'></div></div></td>");
		this.addOperationBox(parenttr, "查询条件定制", "condition", conditionFields,grid);
		return this.addOperationBox(parenttr, "查询结果定制", "result", resultFields,null);
	},
	inManage: function(id, grid){
		grid = grid || '#mainGrid';
		$.container.popup({
			type: 'row',
			items:[{
				read: CONTEXT_PATH + '/data/jzgl/dagl/'+'read.action?id=' + id,
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
				save: CONTEXT_PATH + '/data/jzgl/dagl/' + 'inManage.action?zfdc='+'zfdc',
				read: CONTEXT_PATH + '/data/jzgl/dagl/' + 'readOutManageInfo.action?fxryId=' + id,
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
	}
});
})(jQuery);