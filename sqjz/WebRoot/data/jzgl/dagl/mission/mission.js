(function ($) {
"use strict";
$.dailymission = $.dailymission || {};
$.extend($.dailymission,{
	defaultConfig:{
		dhbd:{
		},
		report:{
		},
		gyld:{
		},
		zf:{
		},
		jy:{
		},
		xbdry:{
		},
		xjtxry:{
		},
		tzck:{
		},
		dtxx:{
		},
		zjwbj:{
		}
	},
	accomplish:function(id, name,title,action){
		var me =this;
		var grid = '#mainGrid';
		$.dialog.confirm("服刑人员：" + name + "<br>确定"+title+"吗？", function(){
			$.fields.ajaxRequest({
				url: me.config.baseurl + action+'?id=' + id,
				yes: function(){
					$(grid).trigger("reloadGrid");
				}
			});
		});
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || CONTEXT_PATH + "/data/jzgl/dagl/mission/";
		me[action].call(me);
	},
	missionGrid:function(action,saveAction,title,hrefTitle,returnurl){
		var me=this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="'+title+'"/>');
		var colModel=[{
			name	:'id',
			index	:'id',
			label	: 'id',
			hidedlg	:false,
			hidden	:true},{
		        name	: 'name',
		        index	: 'name',
		        label	: '姓名',
		        searchType: 'cn',
		        sortable: true,
		        width	: 150,
		        align	: 'center'
	        }];
		if(ORG_TYPE=='qxsfj'){
			colModel.push({name	:'orgId',
				index	:'orgId',
				label	: '矫正负责单位',
				align	: 'center',
				sortable: true,
		        formatter: 'organization'});
		}
		colModel.push({
				name	: 'code',
				index	: 'code',
				label	: '人员编号',
				fixed: true,
				width	: 150,
				sortable: true,
				align	: 'center'
			}, {
				name	: 'idCard',
				index	: 'idCard',
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
				name	: 'policeName',
				index	: 'policeName',
				sortable: true,
				width	: 100,
				label	: '责任干警',
				align	: 'center'
			},{
				name	: 'vacate',
				index	: 'vacate',
				label	: '是否有未销请假',
				fixed: true,
				width	: 120,
				sortable: true,
				align	: 'center',
				formatter : 'dictionary',
				formatoptions: {code:'SF'}
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
					actions += "<a href='javascript:$.dailymission.accomplish(\""+row.id+"\",\""+row.name+"\",\""+title+"\",\""+saveAction+"\");'class='a-style'>"+hrefTitle+"</a>";
					actions += "<a href='javascript:$.fxryactions.view(\""+row.id+"\",\""+returnurl+"\");' class='a-style'>查看</a>";
					return actions;	
				}
			});
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + action,
			colModel: colModel,
			multiselect : true,
			rownumbers	: true,
			sortname: "name,orgId",
			sortorder: "asc,asc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
		/*$("#btnAdd").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择"+title+"的人员!");
				return false;
			}
			$.dialog.confirm("确认所选人员"+title+"吗？", function(){
				$.fields.ajaxRequest({
					url: me.config.baseurl + saveAction+"?ids="+ids,
					yes: function(){
						 $("#mainGrid").trigger("reloadGrid");
					}
				});
			});
		});*/
		$("#btnAdd").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择"+title+"的人员!");
				return false;
			}
			if(saveAction == 'phoneReport.action' || saveAction == 'illExamination.action'){
				$.container.popup({
					type: 'row',
					items:[{
						title: title,
						save:me.config.baseurl + saveAction,
						read: me.config.baseurl + 'getMissionInitData.action',
						fieldCols: 2,
						fieldCls:{
							labelCls : 'form-td-label-90'
						},
						fields:[
						        {type:'hidden', name: 'ids',value:ids},
						        {type:'hidden', name: 'createTime', id:'reportDateCom'},
						        {text: '报告类型', type:'display', defValue:title},
						        {text: '报告时间', type:'datepicker',name: 'reportDateTime',dateFmt:'yyyy-MM-dd HH:mm:ss',validater:'fulldate', required: true,
						        	valuechange:'changeDate(value,"bz","90")'},
						        {text: '备注', type:'textarea', name: 'bz',required: false,maxlength:200,colspan:2},
						        {text: '记录人', type:'display',name: 'personName'},
						        {text: '记录日期', type:'display',name: 'createTime'}
						        ],
						        yes: function(){
						        	$("#mainGrid").trigger("reloadGrid");
						        }
					}]
				}, {
					okVal: title,
					width: '600px',
					height: '150px'
				});
			}else{
				$.dialog.confirm("确认所选人员"+title+"吗？", function(){
					$.fields.ajaxRequest({
						url: me.config.baseurl + saveAction+"?ids="+ids,
						yes: function(){
							 $("#mainGrid").trigger("reloadGrid");
						}
					});
				});
			}
		});
	},
	dhbd: function(){
		var me = this;
		me.missionGrid("phoneReportList.action","phoneReport.action","电话报告","报告","mission/dhbd");
	},
	gyld:function(){

		var me=this;
		var title="社区服务";
		var action="publicWorkList.action";
		var saveAction="publicWork.action";
		var returnurl="mission/gyld";
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="社区服务"/>');
		var colModel=[{
			name	:'id',
			index	:'id',
			label	: 'id',
			hidedlg	:false,
			hidden	:true},{
		        name	: 'name',
		        index	: 'name',
		        label	: '姓名',
		        searchType: 'cn',
		        sortable: true,
		        width	: 150,
		        align	: 'center'
	        }];
		if(ORG_TYPE=='qxsfj'){
			colModel.push({name	:'orgId',
				index	:'orgId',
				label	: '矫正负责单位',
				align	: 'center',
				sortable: true,
		        formatter: 'organization'});
		}
		colModel.push({
				name	: 'code',
				index	: 'code',
				label	: '人员编号',
				fixed: true,
				width	: 150,
				sortable: true,
				align	: 'center'
			}, {
				name	: 'idCard',
				index	: 'idCard',
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
				name	: 'policeName',
				index	: 'policeName',
				sortable: true,
				width	: 100,
				label	: '责任干警',
				align	: 'center'
			},{
				name	: 'vacate',
				index	: 'vacate',
				label	: '是否有未销请假',
				fixed: true,
				width	: 120,
				sortable: true,
				align	: 'center',
				formatter : 'dictionary',
				formatoptions: {code:'SF'}
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
					actions += "<a href='javascript:$.dailymission.savegyld(\""+row.id+"\",\""+row.name+"\",\""+title+"\",\""+saveAction+"\");'class='a-style'>"+"服务"+"</a>";
					actions += "<a href='javascript:$.fxryactions.view(\""+row.id+"\",\""+returnurl+"\");' class='a-style'>查看</a>";
					return actions;	
				}
			});
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + action,
			colModel: colModel,
			multiselect : true,
			rownumbers	: true,
			sortname: "name,orgId",
			sortorder: "asc,asc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
		$("#btnAdd").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择"+title+"的人员!");
				return false;
			}
			/*$.dialog.confirm("确认所选人员"+title+"吗？", function(){
				$.fields.ajaxRequest({
					url: me.config.baseurl + saveAction+"?ids="+ids,
					yes: function(){
						 $("#mainGrid").trigger("reloadGrid");
					}
				});
			});*/
			var fields=[{text:"任务开始日期",type:'hidden',name:'missionStart'},
						{text:"任务结束日期",type:'hidden',name:'missionEnd'},
						{text:"服刑人员id",type:'hidden',name:'ids',value:ids},
						{type:'hidden', name: 'createTime', id:'reportDateCom'},
						{text:"任务类型",type:'hidden',name:'missionType',value:"4"},//集中分类教育
						{text:"完成日期",type:'datepicker',name:'reportDateTime',dateFmt:'yyyy-MM-dd HH:mm:ss',validater:'fulldate',required: true,
							valuechange:'changeDate(value,"bz","90")'}
						];
			fields.push({text: '是否参与', type:'dict_radio',name: 'sfcy', code:'SF', required: true,
				valuechange:function(value,e){
					//未参加社区服务的在备注中填写原因
					if(value==2){
						$("textarea[name='bz']").setRequired(true,$("textarea[name='bz']").val());
					}else{
						var ydate = $("#reportDateCom").val();
						var value = $("input[name='reportDateTime']").val();
						if(!commpareDate(value,ydate)){
							$("textarea[name='bz']").setRequired(false,$("textarea[name='bz']").val());
						}
					}
				}
			});
			fields.push({text: '备注', type:'textarea',name: 'bz', colspan:2, maxlength:200,defValue:'',required: false},
					{text: '记录人', type:'display',name: 'personName'},
			        {text: '记录日期', type:'display',name: 'createTime'});
			$.container.popup({
				title: '社区服务',
				read: me.config.baseurl + 'getMissionInitData.action',
				save: me.config.baseurl + saveAction,
				fieldCols: 2,
				fieldCls:{
					labelCls : 'form-td-label-90'
				},
				fields:fields,
				yes : function(data){
					$("#mainGrid").trigger("reloadGrid");
				}
			},{width: "600px",height: "150px",okVal:'保存'});
		});
	},
	savegyld:function(id, name,title,action){
		var me=this;
		var url = me.config.baseurl + action+'?id=' + id;
		
		var fields=[{text:"任务开始日期",type:'hidden',name:'missionStart'},
					{text:"任务结束日期",type:'hidden',name:'missionEnd'},
					{text:"服刑人员id",type:'hidden',name:'id',value:id},
					{type:'hidden', name: 'createTime', id:'reportDateCom'},
					{text:"任务类型",type:'hidden',name:'missionType',value:"4"},//集中分类教育
					{text:"完成日期",type:'datepicker',name:'reportDateTime',dateFmt:'yyyy-MM-dd HH:mm:ss',validater:'fulldate',required: true,
						valuechange:'changeDate(value,"bz","90")'}
					];
		fields.push({text: '是否参与', type:'dict_radio',name: 'sfcy', code:'SF', required: true,
			valuechange:function(value,e){
				//未参加社区服务的在备注中填写原因
				if(value==2){
					$("textarea[name='bz']").setRequired(true,$("textarea[name='bz']").val());
				}else{
					var ydate = $("#reportDateCom").val();
					var value = $("input[name='reportDateTime']").val();
					if(!commpareDate(value,ydate)){
						$("textarea[name='bz']").setRequired(false,$("textarea[name='bz']").val());
					}
				}
			}
		});
		fields.push({text: '备注', type:'textarea',name: 'bz', colspan:2, maxlength:200,defValue:'',required: false},
				{text: '记录人', type:'display',name: 'personName'},
		        {text: '记录日期', type:'display',name: 'createTime'});
		$.container.popup({
			title: '社区服务',
			read: me.config.baseurl + 'getMissionInitData.action',
			save: url,
			fieldCols: 2,
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:fields,
			yes : function(data){
				$("#mainGrid").trigger("reloadGrid");
			}
		},{width: "600px",height: "150px",okVal:'保存'});
	},
	zf:function(){
		var me = this;
		me.missionGrid("interviewList.action","interview.action","走访","走访","mission/zf");
	},
	jy:function(){
		var me = this;
		me.missionGrid("educationList.action","education.action","教育","教育","mission/jy");
	},
	zjwbj: function(){
		var me = this;
		me.missionGrid("illExaminationList.action","illExamination.action","暂监外病检","病检","mission/zjwbj");
	},
	report:function(){
		var me = this;
		$('#buttons').append('<input type="button" id="btnAdd" class="bttn bicon-add"  value="人工报告"/>');
		$("#buttons").append("<div style='display:none;'><OBJECT id='sfzhyCtl'  border='0' classid='clsid:439B8C29-D4E5-4B78-A389-88FDFCD7770B' height='30'></OBJECT></div>")
		$("#mainGrid").jqGrid({
			url: me.config.baseurl + 'needReportList.action',
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
			        },{
				        name	: 'vacate',
				        index	: 'vacate',
				        label	: '是否有未销请假',
				        fixed: true,
				        width	: 120,
				        sortable: true,
				        align	: 'center',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'}
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 110,
				        align	: 'right',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.dailymission.zwbd(\""+row.fxryid+"\",\""+row.fxryname+"\",\"当月报告\",\"report.action\");'class='a-style'>指纹报告</a>";
				        	actions += "<a href='javascript:$.fxryactions.view(\""+row.fxryid+"\",\"mission/report\");' class='a-style'>查看</a>";
				        	return actions;	
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname: 'realreportdate,fxrycode,fxryname',
			sortorder: "asc,asc,asc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
		$("#btnAdd").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择当月报告的人员!");
				return false;
			}
			$.container.popup({
				type: 'row',
				items:[{
					title: '当月报告',
					save:me.config.baseurl + "report.action",
					read: me.config.baseurl + 'getReportInitData.action',
					fieldCols: 2,
					fieldCls:{
						labelCls : 'form-td-label-90'
					},
					fields:[
					    {type:'hidden', name: 'ids',value:ids},
					    {type:'hidden', name: 'reportType', value: '1'},
					    {type:'hidden', name: 'createTime', id:'reportDateCom'},
						{text: '报告类型', type:'display',defValue:'1',
					    	formatter:$.dictionary.formatter('DYBGLX', '不详')},
					    {text: '报告时间', type:'datepicker',name: 'reportDateTime',dateFmt:'yyyy-MM-dd HH:mm:ss',validater:'fulldate', required: true,
					    		valuechange:'changeDate(value,"description","90")'},
						{text: '备注', type:'textarea', name: 'description', required: false,maxlength:200,colspan:2},
					    {text: '记录人', type:'display',name: 'creater'},
					    {text: '记录日期', type:'display',name: 'createTime'}
					],
					yes: function(){
						$("#mainGrid").trigger("reloadGrid");
					}
				}]
			}, {
				okVal: '报告',
				width: '600px',
				height: '150px'
			});
		});
	},
	zwbd:function(fxryId, name,title,action){
		var me = this;
    	$.ajax({
    		type: "POST",
    	    async:false,
    	    url: CONTEXT_PATH + "/data/zwy/getMFingers.action",
    	    data: "fxryId="+fxryId,
    	    
    		success:function(data){
    			if(data==null){
    				$.dialog.alert("没有注册登记指纹，请先登记指纹！");
    				return;
    			}
    			var fpo =$("#sfzhyCtl")[0];
    			try{
	                fpo.P_inzwtz1 = data[0];
	                fpo.P_inzwtz2 = data[1];
	                fpo.P_inzwtz3 = data[2];
	                fpo.P_inzwtz4 = data[3];
	                fpo.P_inzwtz5 = data[4];
	                fpo.P_inzwtz6 = data[5];
	                fpo.P_inzwtz7 = data[6];
	                fpo.P_inzwtz8 = data[7];
	                fpo.P_inzwtz9 = data[8];
	                fpo.P_inzwtz10 = data[9];
	                
	                var res = fpo.GetFPic('1');
	                if(res==1){
	                	$.fields.ajaxRequest({
	                		url: me.config.baseurl + "report.action?reportType='2'&ids="+fxryId,
	                		yes: function(){
	                			$("#mainGrid").trigger("reloadGrid");
	                			$.dialog.alert("报告成功！");
	                		}
	                	});
	                }else{
	                	$.dialog.alert("报告失败！");
	                }
    			}catch(e){
    				$.dialog.alert("请到下载园地下载指纹控件，并使用兼容模式！");
    			}
    		}
        });
		/*$.dialog({
			width : '0px',
			height : '0px',
			fixed : true,
			lock : true,
			cover : true,
			data:{"fxryId":id},
			title : false,
			content : 'url:/sqjz/fingerreport.htm',
			okVal : '关闭',
			ok : false,
			cancelVal : '取消',
			cancel : false,
			zIndex:-999,
		});
		return;
		
		$.dialog.confirm("确认所选人员当月报告吗？", function(){
			$.fields.ajaxRequest({
				url: me.config.baseurl + "report.action?ids="+ids,
				yes: function(){
					 $("#mainGrid").trigger("reloadGrid");
				}
			});
		});*/
	},
	xbdry:function(){
		var me = this;
		$.fxrytabpage.init();
		$.fxrytabpage.xbdry('/mission/jsbd');
	},
	xjtxry:function(){
		var me = this;
		$.fxrytabpage.init();
		$.fxrytabpage.qjlb('vacateList.action');
	},
	tzck:function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH + "/data/tzgl/getData.action?oper=tzView&status=2",
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'title',
				        index	: 'title',
				        label	: '通知名称',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'creater',
				        index	: 'creater',
				        label	: '拟制人',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'sendtime',
				        index	: 'sendtime',
				        label	: '通知时间',
				        sortable: true,
				        searchInput : 'time',
				        searchType: 'in',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'required',
				        index	: 'required',
				        label	: '要求',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'status',
				        index	: 'status',
				        label	: '是否未读',
				        width	: 150,
				        searchType: 'eq',
				        nosearch:true ,
				        align	: 'center',
						hidden	:true,
				        formatter : 'dictionary',
				        formatoptions: {
				        	code:'SF',
				        	defValue:"1",
				        	valuechange:function(e){
				        		var value;
				        		if(e){
				        			if("2"==e){
				        				value="1";
				        			}else{
				        				value="2";
				        			}
				        		}else if(this.value){
				        			if("2"==this.value){
				        				value="1";
				        			}else{
				        				value="2";
				        			}
				        		}
				        		var searchUrl=CONTEXT_PATH + "/data/tzgl/getData.action?oper=tzView";
				        		if(value){
				        			searchUrl=searchUrl+"&status="+value;
				        		}
				        		$("#mainGrid").setGridParam({url: searchUrl});
				        	}
				        }
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	actions += "<a href='javascript:$.dailymission.tzview(\""+row.id+"\");'class='a-style'>查看</a>";
					 	    return actions;	
				        }
			        }
			],
			rownumbers	: true,
			sortname: "sendtime",
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	tzview:function(id){
		$.container.popup({
			title: '通知拟制',
			read:CONTEXT_PATH + '/data/tzgl/search.action?id='+id+'&oper=tzView',
			fieldCols: 2,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[
				{text:"通知接收单位",
				 type:'display',
				 name:'jgIds',
				 colspan:2,
				 allowBlank:false
				},
				{text:"通知名称",
				 type:'display',
				 name:'title',
				 colspan:2,
				 allowBlank:false
				},
				{text:"要求",
				 name:'required',
				 type:'textarea',
				 readonly:true,
				 colspan:2,
				 allowBlank:false
				},
				{text:"备注",
				 name:'description',
				 type:'textarea',
				 readonly:true,
				 colspan:2,
				 allowBlank:false
				},
				{text:"拟制人",
				 type:'display',
				 name:'creater',
				 colspan:1,
				 allowBlank:false
				},
				{text:"通知时间",
				 type:'display',
				 name:'sendtime',
				 colspan:1,
				 allowBlank:false
				},
				{text:"附件",
				 type:'file',
				 name:'affixId',
				 url:"${ctx}/data/common/upload.action",
				 multiple:true,
				 accept:"application/vnd.ms-excel",
				 spath:"tzgl/upload",
				 colspan:2,
				 readonly:true
				}						
			],
			yes : function(){}
		},{cancelVal: '关闭',width: "800px",cancel:function(){$("#mainGrid").trigger("reloadGrid");}});
	},
	dtxx:function(){
		var me = this;
		var url=CONTEXT_PATH + "/data/dynamicreport/list.action?oper=dtglView";
		$("#mainGrid").jqGrid({
			url:url+"&status=3",
			colModel: [
					{
						name	:'id',
						index	:'id',
						label	: 'id',
						hidedlg	:false,
						hidden	:true
					},
			        {
				        name	: 'title',
				        index	: 'title',
				        label	: '信息标题',
				        searchType: 'cn',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'recordOrgId',
				        index	: 'recordOrgId',
				        label	: '上报单位',
				        formatter : 'organization',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'reporttime',
				        index	: 'reporttime',
				        label	: '上报时间',
				        sortable: true,
				        searchInput : 'time',
				        searchType: 'in',
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'status',
				        index	: 'status',
				        label	: '信息状态',
				        searchType: 'eq',
				        nosearch:true ,
				        formatter : 'dictionary',
				        formatoptions: {
				        	code:'DTXXZT',
				        	defValue:"3",
				        	filter: "2",
				        	valuechange:function(e){
				        		var value;
				        		if(e){
				        			value=e;
				        		}else if(this.value){
				        			value=this.value;
				        		}
				        		var searchUrl=url;
				        		if(value){
				        			searchUrl=searchUrl+"&status="+value;
				        		}
				        		$("#mainGrid").setGridParam({url: searchUrl});
				        	}
				        },
				        width	: 150,
				        align	: 'center'
			        },{
				        name	: 'state', 
				        index	: 'state',
				        label	: '操作',
				        width	: 80,
				        align	: 'center',
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
				        	var actions = "";
				        	if(row.status=='3'){
				        		actions += "<a href='javascript:$.dailymission.dtxxView(\""+row.id+"\",\"view\");'class='a-style'>签收</a>";
				        	}else{
				        		actions += "<a href='javascript:$.dailymission.dtxxView(\""+row.id+"\",\"search\");'class='a-style'>查看</a>";
				        	}
					 	    return actions;	
				        }
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: "reporttime",
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	},
	dtxxView:function(id,action){
		$.container.popup({
			title: '动态信息上报查看',
			read: CONTEXT_PATH+"/data/dynamicreport/"+action+".action?id="+id+"&oper=view",
			fieldCols: 2,
			fieldCls:{
				labelCls : "form-td-label-120"
			},
			fields:[
				{text:"信息标题",
				 name:'title',
				 type:'display',
				 colspan:2,
				 allowBlank:false
				},
				{text:"内容",
				 name:'content',
				 type:'textarea',
				 readonly:true,
				 colspan:2,
				 allowBlank:false
				},
				{text:"上报人",
				 name:'creatername',
				 type:'display',
				 colspan:1,
				 allowBlank:false
				},
				{text:"上报时间",
				 name:'reporttime',
				 type:'display',
				 colspan:1,
				 allowBlank:false
				},
				{text:"附件",
				 name:'affixId',
				 type:'file',
				 url:"${ctx}/data/common/upload.action",
				 multiple:true,
				 colspan:2,
				 accept:"application/vnd.ms-excel",
				 spath:"dtgl/attach",
				 readonly:true
				},
				{text:"签收人",
				 name:'reply.replyPersonName',
				 type:'display',
				 colspan:1,
				 allowBlank:false
				},
				{text:"签收日期",
				 name:'reply.replydate',
				 type:'display',
				 colspan:1,
				 allowBlank:false
				}
			],
			yes : function(){}
		},{cancelVal : '关闭', width: "800px",cancel:function(){$("#mainGrid").trigger("reloadGrid");}});
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