(function ($) {
"use strict";
$.fxrybaseinfo = $.fxrybaseinfo || {};
$.extend($.fxrybaseinfo,{
	defaultConfig:{
		add:{
			read: $.fxryconfig.baseurl + "read.action",
			save: $.fxryconfig.baseurl + "saveJBSFXX.action",
			editable: true,
			printnotice : false
		},
		edit:{
			read: $.fxryconfig.baseurl + "read.action",
			save: $.fxryconfig.baseurl + "saveJBSFXX.action",
			editable: true,
			showBaseinfo: true,
			yes: null,
			cancel: null
		},
		view:{
			read: $.fxryconfig.baseurl + "read.action",
			editable: false,
			showBaseinfo: true
		}
	},
	idcard:function(value,select){
		if(value){
			if(!$.validater.idcard("",value)){
				if(value.length==15){
					var year="",month="",day="";
					year="19"+value.substring(6,8);
					month=value.substring(8,10);
					day=value.substring(10,12);
					$(select).val(year+"-"+month+"-"+day);
					$(select).trigger('valuechange', year+"-"+month+"-"+day);
				}else if(value.length==18){
					var year="",month="",day="";
					year=value.substring(6,10);
					month=value.substring(10,12);
					day=value.substring(12,14);
					$(select).val(year+"-"+month+"-"+day);
					$(select).trigger('valuechange', year+"-"+month+"-"+day);
				}
			}
		}
	},
	init: function(action, fxryId, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config.listPage = $.fxryconfig.listPage;
		me.config = $.extend(me.config, options);
		me.config.fxryId = fxryId;
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl + 'baseinfo/';
		me.config.deviceCheck=false;
		me[action].call(me);
	},
	saveSuccess: function(){
		var me = this;
		$.dialog({
			title : '保存成功',
			id: 'confirm.png',
			icon : 'success.png',
			fixed : true,
			lock : true,
			width : 250,
			content : '修改服刑人员基本信息成功！',
			resize : false,
			ok : function(){
				window.location = $.fxryconfig.baseurl + 'view.jsp?id=' + me.config.fxryId;
			},
			button : [{
				name : '返回列表',
				callback : function() {
					window.location = me.config.listPage;
				}
			}],
			okVal : '查看',
			cancel : false
		});
	},
	//新增或编辑基本信息，可以更换设备
	add:function(){
		var me = this;
		var saveBtnId = $.fields.random('save-btn');
		var checkBtnId = $.fields.random('check-btn');
		var parma = me.config.fxryId ? ('?id=' + me.config.fxryId) : '';
		$.container.generate(me.config.container, {
			read: me.config.read + parma,
			save: me.config.save,
			cancel: me.config.cancel != null ? me.config.cancel : function(){
				var fingerNo = $("input[name='fingerPrintNo']").val(); 
				if(fingerNo!=''){
					$.ajax({
						type : "POST",
						async : true,
						url : CONTEXT_PATH + "/data/zwy/deletePrint.action?fingerPrintNo="+fingerNo,
						success : function(data) {
						}
					});
				}
				window.location = me.config.listPage;
			},
			saveVal: '保存',
			saveBtnId: saveBtnId,
			cancelVal: '取消',
			yes: me.config.yes != null ? me.config.yes : function(data){ 
				me.config.fxryId = data.msg;
				if (me.config.printnotice){
					me.addSuccess(data); 
				} else {
					me.saveSuccess();
				}
			},
			title: '社区服刑人员基本信息登记',
			fieldCols: 3,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[      
			        {type:'hidden',name: 'id', value: me.config.fxryId},
			        {type:'hidden',name: 'fingerPrintNo'},
					{text: '姓名', type:'text',name: 'name', required: true, validater:'name', maxlength:20},
					{text: '曾用名', type:'text',name: 'userdName', validater:'name', maxlength:20},
					{type:'bdPhoto',name: 'picture', rowspan:4},
					{text: '身份证号', type:'text',name: 'identityCard', required: true, maxlength:18, validater:'idcard'
						,valuechange: '$.fxrybaseinfo.idcard(this.value,"input[name=\'birthdate\']")'},
					{text: '出生日期', type:'datepicker',name: 'birthdate', required: true},
					{text: '性别', type:'dict_radio',name: 'sex', code:'XB', required: true},
					{text: '是否成年', type:'dict_radio',name: 'isadult', code: 'SF', required: true},
					{text: '民族', type:'dict_combobox',name: 'nation', code: 'MZ', required: true, emptyText:'不详',allowSearch:true},
					{text: '到区县局报到时间', type:'datepicker', name: 'reportTime', required: true},
					{text: '到司法所报到时间', type:'datepicker', name: 'report.reportTime', required: true},
					{text: '监管设备编号', type:'equips', name: 'deviceCode', emptyText:'请选择', allowSearch:true,
						valuechange: function(value){
							if (value){//如果选择了监管设备，必须自检
								/*var checked = $('#' + checkBtnId).data('checked') || {};
								var tip = '<p>监管设备<a style="color:red;font-weight:bold;">[' + value + ']</a>需要自检，自检成功后才可以使用</p>';
								if (checked[value] == true){
									//本次页面刷新内，该设备已经自检成功
									$('#' + saveBtnId).show();
									tip = '<p>监管设备<a style="color:green;font-weight:bold;">[' + value + ']</a>已经自检成功，可以使用</p>';
								}
								else{
									//设备未检查
									$('#' + saveBtnId).hide();
								}
								$('#' + checkBtnId).qtip('option', 'content.text', tip).show().trigger('selectequip');
								*/
								if(value=="在用主机数量达到性能上限"){
									$('#' + saveBtnId).hide();
								}
							}
							else{
								$('#' + checkBtnId).hide().trigger('unfocus');
								$('#' + saveBtnId).show();
							}
						}
					},
					{text: '', type:'pictureprint', name: 'pictureprint'},
					{text: '指纹获取', type:'fingerprint', name: 'fingerprint', colspan:8},
					//{text: '指纹编号',type:'hidden',name: 'fingerPrintNo_show'},
					{text: '接收下属单位', type:'org_combobox', name: 'report.orgId', required: true, useCOrg: true, 
						notShowType: '1,2,4,5,6,7,8,9', showRoot:false, level:'1', emptyText:'请选择', allowSearch: true
						,valuechange:function(value,e){
							$("input[name='report.personId']").val("");
							$("input[name='report.personId']").trigger('valuechange', "");
					}},
					
					{text: '责任人', type:'person_combobox', name: 'report.personId', required: true, pType: '', emptyText:'请选择',
						orgId: function(){
							return $('[name="report.orgId"]').val() || '';
						},valuechange:function(value,e){
							if(e.choice){
								$("input[name='report.phone']").val(e.choice.phone);
							}else{
								$("input[name='report.phone']").val("");
							}
						}},
					{text: '责任人联系电话', type:'text', name: 'report.phone', required: true, maxlength:11, validater:'phone'},
					{text: '是否特管人员', type:'dict_radio',name: 'isTgry', code: 'SF', required: true,defValue:"2"}
			],
			buttons:[{
				text: '监管设备自检',
				cls: 'bttn bicon-report',
				id: checkBtnId,
				hidden: true,
				callback: function(){
					var deviceCode = $('[name="deviceCode"]', me.config.container).val();
					var url = me.config.baseurl + 'check.jsp?deviceCode=' + deviceCode;// + '&_t=' + (new Date()).valueOf();
					$.container.popupPure({
						url: url,
						close: function(success){
							$('#' + checkBtnId).hide().data('checked')[deviceCode] = success;
							if (success){
								$('#' + saveBtnId).show();
								$('[name="deviceCode"]').select2('readonly', true);
							}
							else{
								$('[name="deviceCode"]', me.config.container).val('').trigger('change').focusError('设备自检未通过，请重新选择!');
							}
						}
					});
				}
			}]
		});
		$('#' + checkBtnId).data('checked', {});
		$('#' + checkBtnId).qtip({
		    content: {text: ''},
		    show: {event: 'selectequip'},
		    hide: {event:'click unfocus', inactive: 6000},
		    position: {at: 'left center', my: 'right center'},
		    style: {classes:'qtip-rounded', width: 300},
		    effect: function(offset) {
	            $(this).slideDown(300);
	        }
		});
	},
	addSuccess:function(){
		var me = this;
		$.dialog({
			title : '保存成功，打印报道通知书',
			id: 'confirm.png',
			fixed : true,
			lock : true,
			width : 600,
			height: 400,
			content : 'url:' + reportUrl + 'data/jzgl/dagl/notice.jsp?raq=bdtzs.raq&fxry='+ me.config.fxryId,
			resize : false,
			ok : function(){
				window.location = $.fxryconfig.baseurl + 'view.jsp?id=' + me.config.fxryId;
			},
			button : [{
				name : '打印通知书',
				callback : function() {
					try {
						if ($("iframe[name='confirm.png']").size() > 0){
							$("iframe[name='confirm.png']").get(0).contentWindow.report1_print();
							return false;
						} else {
							$("#confirm.png").get(0).contentWindow.report1_print();
							return false;
						}
					} catch (e){
						
					}
				}
			},{
				name : '返回列表',
				callback : function() {
					window.location = me.config.listPage;
				}
			}],
			okVal : '查看人员信息',
			cancel : false
		});
	},
	//编辑基本信息，不可编辑设备
	edit: function(){
		var me = this;
		var parma = me.config.fxryId ? ('?id=' + me.config.fxryId) : '';
		$.container.generate(me.config.container, {
			read: me.config.read + parma,
			afterRead:function(){
				//是否已采集指纹判断
				var fingerPrintNo = $('input[name="fingerPrintNo"]').attr("value");
				if(fingerPrintNo!=""){
					fingerPrintNo = fingerPrintNo.substr(10)+"/"+fingerPrintNo;
					$.fields.initPic();
					//$("#collectPrint").attr("disabled",true);
				}
			},
			save: me.config.save,
			cancel: me.config.cancel != null ? me.config.cancel : function(){
				var fingerNo = $("input[name='fingerPrintNo']").val(); 
				if(fingerNo!=''){
					$.ajax({
						type : "POST",
						async : true,
						url : CONTEXT_PATH + "/data/zwy/deletePrint.action?fingerPrintNo="+fingerNo,
						success : function(data) {
						}
					});
				}
				window.location = me.config.listPage;
			},
			saveVal: '保存',
			cancelVal: '取消',
			yes: me.config.yes != null ? me.config.yes : function(data){ 
				me.config.fxryId = data.msg;
				me.saveSuccess(data); 
			},
			title: '社区服刑人员基本信息登记',
			fieldCols: 3,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[      
			        {type:'hidden',name: 'id', value: me.config.fxryId},
			        {type:'hidden',name: 'fingerPrintNo'},
					{text: '姓名', type:'text',name: 'name', required: true, validater:'name', maxlength:20},
					{text: '曾用名', type:'text',name: 'userdName', validater:'name', maxlength:20},
					{type:'bdPhoto',name: 'picture', rowspan:4},
					{text: '身份证号', type:'text',name: 'identityCard', required: true, maxlength:18, validater:'idcard'},
					{text: '出生日期', type:'datepicker',name: 'birthdate', required: true},
					{text: '性别', type:'dict_radio',name: 'sex', code:'XB', required: true},
					{text: '是否成年', type:'dict_radio',name: 'isadult', code: 'SF', required: true},
					{text: '民族', type:'dict_combobox',name: 'nation', code: 'MZ', required: true, emptyText:'不详',allowSearch:true},
					{text: '到区县局报到时间', type:'datepicker', name: 'reportTime', required: true},
					{text: '到司法所报到时间', type:'datepicker', name: 'report.reportTime', required: true},
					{text: '监管设备编号', type:'display', name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'},
					{text: '', type:'pictureprint', name: 'pictureprint'},
					{text: '指纹采集', type:'fingerprint', name: '', colspan:3},
					//{text: '指纹编号',type:'hidden',name: 'fingerPrintNo_show'},
					{text: '接收下属单位', type:'org_combobox', name: 'report.orgId', required: true, useCOrg: true, 
						notShowType: '1,2,4,5,6,7,8,9', showRoot:false, level:'1', emptyText:'请选择', allowSearch: true
						,valuechange:function(value,e){
							$("input[name='report.personId']").val("");
							$("input[name='report.personId']").trigger('valuechange', "");
					}},
					
					{text: '责任人', type:'person_combobox', name: 'report.personId', required: true, pType: '', emptyText:'请选择',
						orgId: function(){
							return $('[name="report.orgId"]').val() || '';
						},valuechange:function(value,e){
							if(e.choice){
								$("input[name='report.phone']").val(e.choice.phone);
							}else{
								$("input[name='report.phone']").val("");
							}
						}},
					{text: '责任人联系电话', type:'text', name: 'report.phone', required: true, maxlength:11, validater:'phone'},
					{text: '是否特管人员', type:'dict_radio',name: 'isTgry', code: 'SF', required: true}
			]
		})
	},
	view:function(){
		var me = this;
		$.container.generate(me.config.container, {
			read: me.config.read + '?id=' + me.config.fxryId,
			afterRead:function(){
				//是否已采集指纹判断
				var fingerPrintNo = $('input[name="fingerPrintNo"]').attr("value");
				if(fingerPrintNo!=""){
					fingerPrintNo = fingerPrintNo.substr(10)+"/"+fingerPrintNo;
					$.fields.initPic();
					//$("#collectPrint").attr("disabled",true);
				}
			},
			//type: 'panel',
			title: '社区服刑人员基本信息',
			//padding: '0px',
			fieldCols: 3,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[           
				{text: '人员编号', type:'display',name: 'code'},
				{text: '人员当前状态', type:'display',name: 'state', formatter:$.dictionary.formatter('JZRYZT', '不详')},
				{type:'photo',name: 'picture', rowspan:4, readonly:true},
				
				{text: '姓名', type:'display',name: 'name'},
				{text: '曾用名', type:'display',name: 'userdName'},
				
				{text: '身份证号', type:'display',name: 'identityCard'},
				{text: '出生日期', type:'display',name: 'birthdate'},
				
				{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
				{text: '是否成年', type:'display',name: 'isadult', formatter:$.dictionary.formatter('SF', '不详')},
				
				{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
				{text: '到区县局报到时间', type:'display', name: 'reportTime'},
				//{text: '指纹采集情况', type:'display',name: ''},
				{text: '指纹编号',type:'display',name: 'fingerPrintNo'},
				
				{text: '监管设备编号', type:'display',name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'},
				{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
				{text: '应到司法所报到时间', type:'display',name: 'report.reportTime'}
			]
		})
	}
});
})(jQuery);