(function ($) {
"use strict";
$.fxry = $.fxry || {};
$.extend($.fxry,{
	defaultConfig:{
		edit:{
			editable: true,
			showType: 'panels',
			showReadonly: false,
			menu: [
				{text:'基本身份信息', content:'moreinfo'},
				{text:'法律文书信息', content:'legal'},
				{text:'刑罚执行信息', content:'executeinfo'},
				{text:'矫正实施信息', content:'team'}
           ]
		},
		view:{
			editable: false,
			showType: 'tabpages',
			showReadonly: true,
			menu: [{
				text:'基本档案信息管理', 
				submenus:[
					{text:'基本身份信息', content:'moreinfo'},
					{text:'法律文书信息', content:'legal'},
					{text:'刑罚执行信息', content:'executeinfo'}
            ]},
           {
            	text:'矫正实施档案管理', 
            	submenus:[
					{text:'矫正小组成员信息', content:'team'},
					{text:'当月报到记录', content:'reportinfo'},
					{text:'电话报到记录', content:'phonereport'},
					{text:'暂监外病检记录', content:'illExamination'},
					{text:'社区服务记录', content:'publicwork'},
					{text:'每月走访记录', content:'interview'},
					{text:'每月教育记录', content:'education'},
					{text:'集中教育信息', content:'educations'},
					{text:'请假记录信息', content:'vacte'},
					{text:'矫正转移信息', content:'transfer'},
					{text:'矫正脱管信息', content:'outmanage'},
					{text:'解除矫正信息', content:'release'},
					{text:'奖惩信息', content:'rewardpunish'}
				]}
           ]
		}
	},
	init: function(action, fxryId, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options);
		me.config.fxryId = fxryId;
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
		me.config.action = action;
		me[action].call(me);
		me.addBtns(action);
	},
	view: function(){
		var me = this;
		if (me.config.showType == 'panels'){
			me.panels(".container-middle");
		}
		else{
			me.tabpages(".container-middle");
		}
	},
	edit: function(){
		var me = this;
		me.config.target = me.config.target || 'edit';
		me.config.editConfig = $.fields.ajaxGet({
			url: $.fxryconfig.baseurl + 'getEditConfig.action',
			data: {
				id: me.config.fxryId,
				target: me.config.target
			}
		});
		me.config.editConfig = me.config.editConfig || {};
		if (me.config.showType == 'panels'){
			me.panels(".container-middle");
		}
		else{
			me.tabpages(".container-middle");
		}
	},
	addBtns: function(action){
		var me = this;
		var container = $(".container-bottom");
		var btns = $('<div id="buttons-container" class="buttonArea operation alignRight"></div>');
		container.append(btns);
		if("edit"===action){
		var xgsBtn = $('<input type="button" class="bttn bicon-save" value="社区矫正宣告书"/>').click(
				function (){
					$.dialog({
						title : '社区矫正宣告书',
						id: 'xgs',
						fixed : true,
						lock : true,
						width : 450,
						height: 400,
						content : 'url:' + reportUrl + 'data/jzgl/dagl/notice.jsp?raq=sqjzxgs.raq&fxry='+ me.config.fxryId,
						resize : false,
						cancel : true,
						button : [{
							name : '打印',
							callback : function() {
								try {
									if ($("iframe[name=xgs]").size() > 0){
										$("iframe[name=xgs]").get(0).contentWindow.report1_print();
										return false;
									} else {
										$("#xgs").get(0).contentWindow.report1_print();
										return false;
									}
								} catch (e){
									
								}
							}
						}],
						cancelVal : "返回"
					});
				});
		btns.append(xgsBtn);
		}
		var cancelBtn = $('<input type="button" class="bttn bicon-return" value="返回列表"/>');
		var cancelFn = function(){
			if(!me.config.returnurl){
				me.config.returnurl="zjry";
			}
			window.location = $.fxryconfig.baseurl + me.config.returnurl+".jsp";
		};
		btns.append(cancelBtn);
		cancelBtn.click(cancelFn);
	},
	getAction: function(content){
		var me = this;
		if (me.config.editConfig && me.config.editConfig[content] == true){
			return "edit";
		}
		return 'view';
	},
	setAction: function(content, action){
		var me = this;
		 me.config.editConfig[content] = action;
		 me.loadContent(content);
	},
	panels: function(container){
		var me = this;
		var seq = 0;
		var showPanels = [];
		var travelfn = function(submenus){
			if (!submenus || submenus.lenght < 1){
				return;
			}
			for (var i in submenus){
				var item = submenus[i];
				if (item.content && (me.config.showReadonly || me.getAction(item.content) != "view")) {
					showPanels.push({
						title: item.text,
						id: 'panel-form-' + seq,
						content: item.content,
					 	type: item.type || 'panel'
					 });
					++seq;
				 }
				 travelfn(item.submenus);
			 }
		 }
		 travelfn(me.config.menu);
		 $.container.generate(container,{
			 type: 'row',
			 items: showPanels
		 })
		 for (var j in showPanels){
			 var panel = showPanels[j];
			 me.loadContent('#' + panel.id, panel.content);
		 }
	},
	tabpages: function(container){
		var me = this;
		var id = 'tabpages-container';
		$.container.generate(container, {
			id: id,
			type: 'panel',
			title: '社区服刑人员信息管理',
			padding: '0px'
		});
		$('#' + id).append('<table width="100%"><tr><td nowrap="nowrap" class="tabpage-menu"></td><td class="tabpage-content"></td></tr></table>');
		me.buildTabPagesMenu('.tabpage-menu');
		if (me.config.content){
			$('li[content="' + me.config.content + '"]').click();
		}
		else{
			$('li[clickable="true"]:first').click();
		}
	},
	buildTabPagesMenu: function(container){
		var me = this;
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
					var $submenuli = $('<li></li>');
					$ul.append($submenuli);
					$li.click(collapsefn($submenuli));
					buildmenu($submenuli, menu.submenus, 'submenu');
				}
				else{
					$li.attr('content', menu.content);
					$li.attr('clickable', true);
					$li.click(function(){
						me.loadContent('.tabpage-content', $(this).attr('content'));
					});
				}
			}
		};
		buildmenu(container, me.config.menu, 'menu');
	},
	
	loadContent: function(container, content){
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
	afterSave: function(content){
		var me = this;
		if (me.config.showType == 'panels'){
			$.dialog({
				title : '保存成功',
				id: 'confirm.png',
				icon : 'success.png',
				content: '保存成功！',
				okVal: '确定',
				ok: true
			});
			return;
		}
		var title = $('li[content="' + content + '"]').text() || '';
		$.dialog({
			title : '保存成功',
			id: 'confirm.png',
			icon : 'success.png',
			fixed : true,
			lock : true,
			width : 250,
			content : '保存' + title + '成功！',
			resize : false,
			ok : function(){
				me.setAction(content, 'view');
				me.loadNextEditable();
			},
			okVal: '编辑下一项',
			button : [{
				name : '查看',
				callback : function() {
					me.setAction(content, 'view');
				}
			},{
				name : '返回修改',
				callback : function() {}
			}],
			cancel : false
		});
	},
	loadNextEditable: function(){
		var me = this;
		var contents = $('li[clickable="true"]');
		var i = 0;
		for (; i < contents.length; ++i){
			var content = $(contents[i]).attr('content');
			if (me.getAction(content) == 'edit'){
				me.loadContent(content);
				return;
			}
		};
		me.editFinished();
	},
	editFinished: function(){
		$('li[clickable="true"]:first').click();
		$.dialog({
			title : '填写完成',
			id: 'success.png',
			icon : 'success.png',
			fixed : true,
			lock : true,
			width : 250,
			content : '本次需要填写的内容已全部完成.',
			resize : false,
			ok : function(){
				window.location = $.fxryconfig.baseurl + "view.jsp?id=" + me.config.fxryId;
			},
			okVal: '查看',
			cancelVal: '返回列表',
			cancel : function(){
				window.location = $.fxryconfig.listPage;
			}
		});
	},
	form_error: function(container){
		$(container).append("<p>该功能还在开发中...</p>");
	},
	form_moreinfo: function(container, content){
		var me = this;
		$.fxrymoreinfo.init(me.getAction(content), me.config.fxryId, {
			container: container, 
			cancel: false, 
			yes: function(){ 
				me.afterSave(content);
			}
		});
	},
	form_executeinfo: function(container, content){
		var me = this;
		$.executeinfo.init(me.getAction(content), me.config.fxryId, {
			container: container, 
			cancel: false, 
			yes: function(){ 
				me.afterSave(content);
			}
		});
	},
	form_release: function(container, content){
		var me = this;
		$.fxryrelease.init(me.getAction(content), me.config.fxryId, {
			container: container, 
			showBaseinfo: false,
			cancel: false, 
			saveVal: '保存',
			yes: function(){ 
				me.afterSave(content);
			}
		});
	},
	form_legal: function(container, content){
		var me = this;
		$.legalinstrument.init(me.getAction(content), me.config.fxryId, {
			container: container, 
			showBaseinfo: false,
			cancel: false, 
			saveVal: '保存',
			yes: function(){ 
				me.afterSave(content);
			}
		})
	},
	form_team: function(container, content){
		var me = this;
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-team-container', 
			title: me.config.editable ? '矫正小组成员列表' : '',
			add: me.config.editable ? function(){
				$.container.popup({
					title: '添加矫正小组成员',
					save: me.config.baseurl + 'moreinfo/saveAdjustGroup.action',
					fieldCls:{
						labelCls : 'form-td-label-90'
					},
					fieldCols: 2,
					fields:[           
							{type:'hidden',name: 'fxryId', value: me.config.fxryId},
							{text: '姓名', type:'text',name: 'name', required: true, maxlength:20, validater:'name'},
							{text: '职务', type:'text',name: 'job', maxlength:20},
							{text: '是否责任人', type:'dict_radio', code: 'SF', name: 'isduty', required: true},
							{text: '电话', type:'text',name: 'phone', required: true, validater:'phone',maxlength:11},
							{text: '工作单位', type:'text',name: 'workUnit', colspan:2, maxlength:50},
							{text: '家庭住址', type:'text',name: 'homeAddress', colspan:2, maxlength:50}
					],
					yes: function(){
						$('#grid-team-container').trigger("reloadGrid");
					}
				},{width: "600px", height:"250px"});
			} : false, 
			delUrl: me.config.editable ? (me.config.baseurl + 'moreinfo/deleteAdjustGroup.action') : false
		});
		$('#grid-team-container').jqGrid({
			url: me.config.baseurl + 'moreinfo/listAdjustGroup.action?fxryId=' + me.config.fxryId,
			colModel: [
			{
				name:'name',
				index:'name',
				label: '姓名',
				width: 20,
				align: 'center'
			},
			{
				name:'job',
				index:'job',
				label: '职务',
				width: 20,
				align: 'center'
			},
			{
				name:'workUnit',
				index:'workUnit',
				label: '工作单位',
				width: 60,
				align: 'center'
			},
			{
				name:'isduty',
				index:'isduty',
				label: '是否责任人',
				width: 80,
				fixed: true,
				formatter: 'dictionary',
				formatoptions:{
					code:'SF'
				},
				align: 'center'
			},
			{
				name:'phone',
				index:'phone',
				label: '电话',
				width: 20,
				align: 'center'
			},{
				name:'homeAddress',
				index:'homeAddress',
				label: '家庭住址',
				width: 60,
				align: 'center'
			}
			],
			rownumbers	: true,
			multiselect: me.config.editable
		});
	},
	form_transfer: function(container, content){
		var me = this;
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-transfer-container', 
			pagerId: 'grid-transfer-pager'
		});
		$('#grid-transfer-container').jqGrid({
			url: me.config.baseurl + 'listTransfer.action?fxryId=' + me.config.fxryId,
			colModel: [
			{
				name:'outOrgId',
				index:'outOrgId',
				label: '转出单位',
				width: 60,
				formatter: 'organization',
				align: 'center'
			},
			{
				name:'outTime',
				index:'outTime',
				label: '转出时间',
				width: 130,
				fixed: true,
				align: 'center'
			},
			{
				name:'receiveTime',
				index:'receiveTime',
				label: '接收时间',
				width: 130,
				fixed: true,
				align: 'center'
			},
			{
				name:'receiveOrgId',
				index:'receiveOrgId',
				label: '接收单位',
				width: 60,
				align: 'center',
				formatter: function(value, opts, row){
					if (value){
						return $.fn.fmatter.organization(value);
					}
					else{
						return row.receiveOrgName + '(联系人:' + row.name + ',电话:' + row.phone + ')';
					}
				}
			},
			{
				name:'affixId',
				index:'affixId',
				label: '附件',
				width: 60,
				fixed: true,
				align: 'center',
				formatter: function(value){
					if (value){
						return '<a href="' + CONTEXT_PATH + '/data/common/download.action'
						+ '?spath=transfer&id=' + value + '">下载</a>'
					}
					else{
						return '无';
					}
				}
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'outTime',
			sortorder: "asc",
			pager: '#grid-transfer-pager'
		});
	},
	form_reportinfo: function(container, content){
		var me = this;
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-reportinfo-container', 
			pagerId: 'grid-reportinfo-pager'
		});
		$('#grid-reportinfo-container').jqGrid({
			url: me.config.baseurl + 'reporthistory.action?id=' + me.config.fxryId,
			colModel: [
			{
				name:'fxryname',
				index:'fxryname',
				label: '服刑人员',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'sex',
				index:'sex',
				label: '性别',
				width: 50,
				fixed: true,
				formatter: 'dictionary',
				formatoptions:{
					code:'XB'
				},
				align: 'center'
			},
			{
				name:'policename',
				index:'policename',
				label: '责任干警',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'realreportdate',
				index:'realreportdate',
				label: '报到时间',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'reportType',
				index:'reportType',
				label: '报到类型',
				width: 100,
				fixed: true,
				formatter: 'dictionary',
				formatoptions:{
					code:'DYBGLX'
				},
				align: 'center'
			},
			{
				name:'description',
				index:'description',
				label: '备注',
				width: 200,
				align: 'center'
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'realreportdate',
			sortorder: "desc",
			pager: '#grid-reportinfo-pager'
		});
	},
	view_reportHistory:function(fingerPrintNo,reportTime){
		
		if(reportTime==null ){
			$.dialog.confirm("未采集报到头像");
			return false;
		}else if(reportTime.length<12){
			reportTime = reportTime+" 16:0:0";
		}
		reportTime = reportTime.replace(/-/g,"/");
		var time = new Date(reportTime);
		var picPath = $.fxry.getPicPath(fingerPrintNo,time);
		
		$.dialog({ 
			id: 'detail',
		    width: '200px', 
			height: '200px', 
			title: '报到头像查看',
		    content: "<img src='"+CONTEXT_PATH+"/upload/fingerprint/"+picPath+".jpg'/>",
			cancelVal: '返回列表', 
			cancel: true 
		});
	}
	,
	getPicPath:function(fingerPrintNo,time){
		var year =time.getFullYear();
		var month=time.getMonth()<9?"0"+(time.getMonth()+1):(time.getMonth()+1);
		var date=time.getDate()<10?"0"+time.getDate():time.getDate();
		var hours=time.getHours()<10?"0"+time.getHours():time.getHours();
		var minutes=time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes();
		var seconds=time.getSeconds()<10?"0"+time.getSeconds():time.getSeconds();
		var picPath = fingerPrintNo.substr(10)+"/"+fingerPrintNo+"/AttPhoto/"+year+''+month+''
		+date+''+hours+''+minutes+''+seconds;
		return picPath;
	},
	form_vacte: function(container, content){
		var me = this;
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-vacte-container', 
			pagerId: 'grid-vacte-pager'
		});
		$('#grid-vacte-container').jqGrid({
			url: me.config.baseurl + 'vacateSearch.action?id=' + me.config.fxryId,
			colModel: [
			{
				name:'startDate',
				index:'startDate',
				label: '请假开始日期',
				width: 100,
				fixed: true,
				align: 'center'
			},
			{
				name:'endDate',
				index:'endDate',
				label: '请假结束日期',
				width: 100,
				fixed: true,
				align: 'center'
			},{
				name:'reportDate',
				index:'reportDate',
				label: '销假日期',
				width: 100,
				fixed: true,
				align: 'center'
			},{
				name:'period',
				index:'period',
				label: '请假天数',
				width: 50,
				align: 'center'
			},
			{
				name:'reason',
				index:'reason',
				label: '请假原因',
				width: 200,
				align: 'center'
			},
			{
				name:'termini',
				index:'termini',
				label: '外出目的地',
				width: 50,
				align: 'center'
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'reportDate',
			sortorder: "desc",
			pager: '#grid-vacte-pager'
		});
	},
	form_outmanage: function(container, content){
		var me = this;
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-outmanage-container', 
			pagerId: 'grid-outmanage-pager'
		});
		$('#grid-outmanage-container').jqGrid({
			url: me.config.baseurl + 'listOutManage.action?fxryId=' + me.config.fxryId,
			colModel: [
			{
				name:'startDate',
				index:'startDate',
				label: '脱管日期',
				width: 80,
				fixed: true,
				align: 'center'
			},
			{
				name:'endDate',
				index:'endDate',
				label: '恢复矫正日期',
				width: 90,
				fixed: true,
				align: 'center'
			},
			{
				name:'reason',
				index:'reason',
				label: '脱管原因',
				width: 550,
				formatter: 'dictionary',
				formatoptions:{
					code:'TGYY'
				},
				align: 'center'
			},
			{
				name:'noticeTime',
				index:'noticeTime',
				label: '通知派出所日期',
				width: 120,
				fixed: true,
				align: 'center'
			},{
				name:'notifier',
				index:'notifier',
				label: '通知人',
				width: 100,
				align: 'center'
			},{
				name:'noticePolice',
				index:'noticePolice',
				label: '派出所名称',
				width: 150,
				align: 'center'
			},{
				name:'description',
				index:'description',
				label: '描述',
				width: 200,
				align: 'center'
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'startDate',
			sortorder: "asc",
			pager: '#grid-outmanage-pager'
		});
	},
	form_rewardpunish: function(container, content){
		var me = this;
		$.rewardpunish.init(me.getAction(content), me.config.fxryId, {
			container: container, 
			showBaseinfo: false,
			cancel: false
		});
	},
	//20150327新增功能
	mission_grid:function(container,action,formatter){
		var me = this;
		var periodformatter=formatter||function(value){
			return value;
		};
		var me = this;
		$.container.generate(container, { 
			read: me.config.baseurl + 'read.action?id=' + me.config.fxryId,
			save: false,
			cancel: false,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[       
					{type:'hidden',name: 'id', value: me.config.fxryId},//隐藏字段
					{type:'hidden',name: 'fingerPrintNo'},
					
					{text: '人员编号', type:'display',name: 'code'},
					{text: '人员当前状态', type:'display',name: 'state', formatter:$.dictionary.formatter('JZRYZT', '不详')},
					{type:'photo',name: 'picture', rowspan:4, readonly:true},
					
					{text: '姓名', type:'display',name: 'name'},
					{text: '曾用名', type:'display',name: 'userdName'},
					
					{text: '身份证号', type:'display',name: 'identityCard'},
					{text: '出生日期', type:'display',name: 'birthdate'},
					
					{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
					{text: '是否成年', type:'display',name: 'isadult', formatter:$.dictionary.formatter('SF', '不详')}
			]
		});
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-reportinfo-container', 
			pagerId: 'grid-reportinfo-pager'
		});
		$('#grid-reportinfo-container').jqGrid({
			url: me.config.baseurl + action+'?id=' + me.config.fxryId,
			colModel: [{
				name:'accomplishDate',
				index:'accomplishDate',
				label: '记录周期',
				width: 200,
				align: 'center',
				formatter:periodformatter
			},{
				name:'accomplishDate',
				index:'accomplishDate',
				label: '记录日期',
				width: 200,
				align: 'center'
			}
			,
			{
				name:'personName',
				index:'personName',
				label: '记录人',
				width: 200,
				align: 'center'
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'missionStart',
			sortorder: "desc",
			pager: '#grid-reportinfo-pager'
		});
	},
	form_phonereport: function(container, content){
		var  formatter=function(value,opts,rwdat){
			return rwdat.missionStart+"至"+rwdat.missionEnd;
		};
		this.mission_grid(container,"mission/fxryPhoneReport.action",formatter);
	},
	form_illExamination: function(container, content){
		var  formatter=function(value,opts,rwdat){
			return rwdat.missionStart+"至"+rwdat.missionEnd;
		};
		this.mission_grid(container,"mission/fxryIllExamination.action",formatter);
	},
	form_publicwork: function(container, content){
		var  formatter=function(value,opts,rwdat){
			if(rwdat.missionStart){
				return rwdat.missionStart.substring(0,7);
			}
			return "获取周期错误";
		};
		this.mission_grid(container,"mission/fxryPublicWork.action",formatter);
	},
	form_interview: function(container, content){
		var  formatter=function(value,opts,rwdat){
			if(rwdat.missionStart){
				return rwdat.missionStart.substring(0,7);
			}
			return "获取周期错误";
		};
		this.mission_grid(container,"mission/fxryInterview.action",formatter);
	},
	form_education: function(container, content){
		var  formatter=function(value,opts,rwdat){
			if(rwdat.missionStart){
				return rwdat.missionStart.substring(0,7);
			}
			return "获取周期错误";
		};
		this.mission_grid(container,"mission/fxryEducation.action",formatter);
	},
	form_educations: function(container, content){
		var me = this;
		$.container.generate(container, { 
			read: me.config.baseurl + 'read.action?id=' + me.config.fxryId,
			save: false,
			cancel: false,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[       
					{type:'hidden',name: 'id', value: me.config.fxryId},//隐藏字段
					{type:'hidden',name: 'fingerPrintNo'},
					
					{text: '人员编号', type:'display',name: 'code'},
					{text: '人员当前状态', type:'display',name: 'state', formatter:$.dictionary.formatter('JZRYZT', '不详')},
					{type:'photo',name: 'picture', rowspan:4, readonly:true},
					
					{text: '姓名', type:'display',name: 'name'},
					{text: '曾用名', type:'display',name: 'userdName'},
					
					{text: '身份证号', type:'display',name: 'identityCard'},
					{text: '出生日期', type:'display',name: 'birthdate'},
					
					{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
					{text: '是否成年', type:'display',name: 'isadult', formatter:$.dictionary.formatter('SF', '不详')}
			]
		});
		$.container.generate(container, {
			type: 'grid',
			id: 'grid-educations-container', 
			pagerId: 'grid-educations-pager'
		});
		$('#grid-educations-container').jqGrid({
			url: '../educations/getData.action?fxryId=' + me.config.fxryId,
			colModel: [{
				name:'time',
				index:'time',
				label: '集中教育时间',
				width: 20,
				align: 'center'
			},{
				name:'sfcy',
				index:'sfcy',
				label: '是否参与',
				width: 20,
				formatter: 'dictionary',
				formatoptions:{
					code:'SF'
				},
				align: 'center'
			},
			{
				name:'type',
				index:'type',
				label: '集中教育类型',
				width: 20,
				formatter: 'dictionary',
				formatoptions:{
					code:'JZJYLX'
				},
				align: 'center'
			},
			{
				name:'bz',
				index:'bz',
				label: '备注',
				width: 40,
				align: 'left'
			}
			],
			rownumbers	: true,
			multiselect: false,
			sortname: 'time,createdate',
			sortorder: "desc,desc",
			pager: '#grid-educations-pager'
		});
	}
});
})(jQuery);