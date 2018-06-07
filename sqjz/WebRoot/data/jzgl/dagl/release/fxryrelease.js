(function ($) {
"use strict";
$.fxryrelease = $.fxryrelease || {};
$.extend($.fxryrelease,{
	defaultConfig:{
		edit:{
			read: "read.action",
			save: "save.action",
			saveVal: '保存',
			yes: null,
			cancel: null,
			editable: false,
			showBaseinfo: true
		},
		view:{
			showBaseinfo: true,
			read: "read.action",
			editable: false
		}
	},
	init: function(action, fxryId, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config.listPage = $.fxryconfig.listPage;
		me.config = $.extend(me.config, options);
		me.config.fxryId = fxryId;
		me.config.read += '?fxryId=' + me.config.fxryId;
		me.config.baseurl = me.config.baseurl || ($.fxryconfig.baseurl + 'release/');
		me[action].call(me);
	},
	saveSuccess: function(){
		var me = this;
		$.dialog({
			title : '保存成功，打印解矫宣告书',
			id: 'confirm.png',
//			icon : 'success.png',
			fixed : true,
			lock : true,
			width : 450,
			height: 400,
//			content : '人员解除矫正信息保存成功',
			content : 'url:' + reportUrl + 'data/jzgl/dagl/notice.jsp?raq=jcsqjzxgs.raq&fxry='+ me.config.fxryId,
			resize : false,
			ok : function(){
				$.fields.ajaxRequest({
					url: me.config.baseurl + '/removeAdjust.action?fxryId=' + me.config.fxryId,
					yes: function(){
						window.location = $.fxryconfig.baseurl + 'view.jsp?id=' + me.config.fxryId;
					}
				});
			},
			button : [{
				name : '打印宣告书',
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
				name : '继续编辑',
				callback : function() {
					window.location = me.config.baseurl + 'edit.jsp?fxryId=' + me.config.fxryId;
				}
			},{
				name : '返回列表',
				callback : function() {
					window.location = me.config.listPage;
				}
			}],
			okVal : '确认解矫',
			cancel : false
		});
	},
	common: function(){
		var me = this;
		if (me.config.showBaseinfo == true){
			$.fxrybaseinfo.init('view', me.config.fxryId, {
				container: me.config.container, 
				cancel: false, 
				yes: false
			});
		}
	},
	edit: function(){
		var me = this;
		me.common();
		$.container.generate(me.config.container, { 
			title: '人员解除矫正',
			read: me.config.baseurl + me.config.read,
			save: me.config.baseurl + me.config.save,
			cancel: me.config.cancel != null ? me.config.cancel : me.config.listPage,
			saveVal: me.config.saveVal,
			cancelVal: '取消',
			yes: me.config.yes != null ? me.config.yes : function(){ me.saveSuccess(); },
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[        	
			        {type:'hidden',name: 'id'},
			        {type:'hidden',name: 'fxryId', value: me.config.fxryId},
			        {text: '解除/终止矫正原因', type:'dict_combobox', name: 'removeReason', code:'JJYY', required: true, emptyText:'不详',
			        	valuechange:function(value){
			        		var required = value == '2';
			        		$('input[name="jailType"]').setRequired(required);
			        		$('input[name="jailDate"]').setRequired(required);
			        		$('input[name="jailReason"]').setRequired(required);
			        		var deathRequired = value == '3';
			        		$('input[name="deathDate"]').setRequired(deathRequired);
			        		$('input[name="deathReasonDetail"]').setRequired(deathRequired);
			        	}
			        },
			        {text: '解除/终止矫正日期', type:'datepicker', name: 'removeDate', required: true},
			        //收监执行类型 暂无该字典项说明
			        {text: '收监执行类型', type:'dict_combobox', name: 'jailType', code:'SJZXLX', emptyText:'不详'},
			        
			        {text: '收监执行日期', type:'datepicker', name: 'jailDate', blankMsg:'解除/终止矫正原因为“收监执行”,请填写收监执行日期。'},
			        {text: '收监执行原因', type:'dict_combobox', name: 'jailReason', code:'SJZXYY', emptyText:'不详'},
			        {text: '死亡日期', type:'datepicker', name: 'deathDate'},
			        
			        {text: '死亡原因', type:'dict_radio', name: 'deathReason', code:'SWYY', allowBlank:true, emptyText:'不详'},
			        {text: '具体死因', type:'text', colspan: 2, name: 'deathReasonDetail', maxlength:50},
			        
			        
			        {text: '矫正期间表现', type:'dict_combobox', name: 'performance', code:'JZBX', required: true, emptyText:'不详'},
			        {text: '认罪态度', type:'dict_combobox', name: 'manner', code:'RZTD', required: true, emptyText:'不详'},
			        {text: '矫正期间是否参加职业技能培训', type:'dict_radio', name: 'istrained', code:'SF', required: true,allowBlank:false,defValue:'2'},
			        
			        {text: '矫正期间是否获得职业技能证书', type:'dict_radio', name: 'hascertificate', code:'SF', required: true,allowBlank:false,defValue:'2'},
			        {text: '技术特长及等级', type:'text', colspan: 2, name: 'speciality', maxlength:100},
			        
			        {text: '是否三无人员', type:'dict_radio', name: 'issanwu', code:'SF', required: true,allowBlank:false,defValue:'2'},
			        {text: '危险性评估', type:'dict_radio', name: 'risk', code:'WXXPG', required: true,allowBlank:false},
			        {text: '家庭联系情况', type:'dict_radio', name: 'familyContact', code:'JTLXQK', required: true,allowBlank:false},
			        
			        {text: '矫正期间特殊情况备注及帮教建议', type:'textarea', colspan: 3, name: 'remark', required:true, maxlength:500},
			        {text: '矫正期间表现鉴定意见', type:'textarea', colspan: 3, name: 'expertOpinion', required:true, maxlength:500}
			],
			items:[{
				type: 'grid',
				id: 'grid-morecrime-container', 
				title:'余罪或再罪有关情况' ,
				add: function(){ me.addMoreCrime('#grid-morecrime-container');}, 
				delUrl:me.config.baseurl + 'deleteMoreCrime.action'
			},{
				type: 'grid',
				id: 'grid-imprisonment-container', 
				title:'刑期变动情况（包括监禁和社区矫正期间的刑期变动）' ,
				add: function(){ me.addImprisonment('#grid-imprisonment-container');}, 
				delUrl: me.config.baseurl + 'deleteImprisonment.action'
			}]
		});
		me.addMoreCrimeGrid('grid-morecrime-container', me.config.fxryId);
		me.addImprisonmentGrid('grid-imprisonment-container', me.config.fxryId);
	},
	view: function(){
		var me = this;
		me.common();
		$.container.generate(me.config.container, { 
			title: '人员解除矫正',
			read: me.config.baseurl + me.config.read,
			save: false,
			cancel: false,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[        	
			        {type:'hidden',name: 'id'},
			        {type:'hidden',name: 'fxryId', value: me.config.fxryId},
			        {text: '解除/终止矫正原因', type:'display', name: 'removeReason', formatter:$.dictionary.formatter('JJYY', '不详')},
			        {text: '解除/终止矫正日期', type:'display', name: 'removeDate'},
			        //收监执行类型 暂无该字典项说明
			        {text: '收监执行类型', type:'display', name: 'jailType', formatter:$.dictionary.formatter('SJZXLX', '不详')},
			        
			        {text: '收监执行日期', type:'display', name: 'jailDate'},
			        {text: '收监执行原因', type:'display', name: 'jailReason', formatter:$.dictionary.formatter('SJZXYY', '不详')},
			        {text: '死亡日期', type:'display', name: 'deathDate'},
			        
			        {text: '死亡原因', type:'display', name: 'deathReason', formatter:$.dictionary.formatter('SWYY', '不详')},
			        {text: '具体死因', type:'display', colspan: 2, name: 'deathReasonDetail'},
			        
			        
			        {text: '矫正期间表现', type:'display', name: 'performance', formatter:$.dictionary.formatter('JZBX', '不详')},
			        {text: '认罪态度', type:'display', name: 'manner', formatter:$.dictionary.formatter('RZTD', '不详')},
			        {text: '矫正期间是否参加职业技能培训', type:'display', name: 'istrained', formatter:$.dictionary.formatter('SF', '否')},
			        
			        {text: '矫正期间是否获得职业技能证书', type:'display', name: 'hascertificate', formatter:$.dictionary.formatter('SF', '否')},
			        {text: '技术特长及等级', type:'display', colspan: 2, name: 'speciality'},
			        
			        {text: '是否三无人员', type:'display', name: 'issanwu', formatter:$.dictionary.formatter('SF', '否')},
			        {text: '危险性评估', type:'display', name: 'risk', formatter:$.dictionary.formatter('WXXPG', '不详')},
			        {text: '家庭联系情况', type:'display', name: 'familyContact', formatter:$.dictionary.formatter('JTLXQK', '不详')},
			        
			        {text: '矫正期间特殊情况备注及帮教建议', type:'textarea', colspan: 3, name: 'remark', readonly:true},
			        {text: '矫正期间表现鉴定意见', type:'textarea', colspan: 3, name: 'expertOpinion', readonly:true}
			],
			items:[{
				type: 'grid',
				id: 'grid-morecrime-container', 
				title:'余罪或再罪有关情况' 
			},{
				type: 'grid',
				id: 'grid-imprisonment-container', 
				title:'刑期变动情况（包括监禁和社区矫正期间的刑期变动）' 
			}]
		});
		me.addMoreCrimeGrid('grid-morecrime-container', me.config.fxryId, true);
		me.addImprisonmentGrid('grid-imprisonment-container', me.config.fxryId, true);
	},
	addMoreCrime: function(elem){
		var me = this;
		$.container.popup({
			title: '添加余罪或再罪有关情况',
			save: me.config.baseurl + 'saveMoreCrime.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{text: '所涉罪名', type:'text',name: 'crimes', required: true},
					{text: '侦查机关', type:'text',name: 'investigateUnit'},
					{text: '被采取强制措施时间', type:'datepicker',name: 'time', required: true},
					{text: '审判机关', type:'text',name: 'judgeUnit'},
					{text: '罪名', type:'text',name: 'accusation', required: true},
					{text: '刑期', type:'text',name: 'prisonTerm', required: true}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "450px"});
	},
	addImprisonment: function(elem){
		var me = this;
		$.container.popup({
			title: '添加刑期变动情况',
			save: me.config.baseurl + 'saveImprisonment.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{text: '刑罚变动类型', type:'text',name: 'changeType', required: true},
					{text: '变动日期', type:'datepicker',name: 'changeDate', required: true},
					{text: '变动幅度', type:'text',name: 'range', required: true},
					{text: '变动原因', type:'text',name: 'reason', required: true}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "450px"});
	},
	addMoreCrimeGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		var me = this;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listMoreCrime.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'crimes',
				index:'crimes',
				label: '所涉罪名',
				width: 20,
				align: 'center'
			},
			{
				name:'investigateUnit',
				index:'investigateUnit',
				label: '侦查机关',
				width: 20,
				align: 'center'
			},
			{
				name:'time',
				index:'time',
				label: '被采取强制措施时间',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'judgeUnit',
				index:'judgeUnit',
				label: '审判机关',
				width: 20,
				align: 'center'
			},
			{
				name:'accusation',
				index:'accusation',
				label: '罪名',
				width: 20,
				align: 'center'
			},
			{
				name:'prisonTerm',
				index:'prisonTerm',
				label: '刑期',
				width: 20,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'createTime',
			sortorder: "asc"
		});
	},
	addImprisonmentGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		var me = this;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listImprisonment.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'changeType',
				index:'changeType',
				label: '刑罚变动类型',
				fixed: true,
				width: 150,
				align: 'center'
			},
			{
				name:'changeDate',
				index:'changeDate',
				label: '变动日期',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'range',
				index:'range',
				label: '变动幅度',
				width: 30,
				align: 'center'
			},
			{
				name:'reason',
				index:'reason',
				label: '变动原因',
				width: 60,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'createTime',
			sortorder: "asc"
		});
	}
});
})(jQuery);