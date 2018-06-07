(function ($) {
"use strict";
$.legalinstrument = $.legalinstrument || {};
$.extend($.legalinstrument,{
	defaultConfig:{
		edit:{
			read: "readLegal.action",
			save: "saveLegal.action",
			yes: null,
			cancel: null,
			editable: false
		},
		view:{
			read: "readLegal.action",
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
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl;
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
			content : '修改法律文书成功！',
			resize : false,
			ok : false,
			button : [{
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
			cancel : false
		});
	},
	edit: function(){
		var me = this;
		$.container.generate(me.config.container, {
			title: '法律文书信息',
			read: me.config.baseurl + me.config.read,
			save: me.config.baseurl + me.config.save,
			cancel: me.config.cancel != null ? me.config.cancel : me.config.listPage,
			yes: me.config.yes != null ? me.config.yes : function(){ me.saveSuccess(); },
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[        	
			        {type:'hidden',name: 'id'},
			        {type:'hidden',name: 'fxryId', value: me.config.fxryId},
			        
			        {text: '侦查机关', type:'text',name: 'investigateUnit', required: true, validater:'name', maxlength:50},
			        {text: '拘留日期', type:'datepicker',name: 'dateDetention'},
			        {text: '立案日期', type:'datepicker',name: 'dateRecord'},
			        
			        {text: '决定/批准逮捕机关', type:'text',name: 'permitArrestUnit', required: true, validater:'name', maxlength:50},
			        {text: '逮捕日期', type:'datepicker',name: 'dateArrest'},
			        {text: '侦结日期', type:'datepicker',name: 'dateEndInvestigate'},
			        
			        {text: '公诉机关（自诉人）', type:'text',name: 'publicProsecution', required: true, validater:'name', maxlength:50},
			        {text: '起诉书编号', type:'text',name: 'indictmentNumber', required: true,  maxlength:100},
			        {text: '提起起诉日期', type:'datepicker',name: 'dateIndictment', required: true},
			        
			        {text: '审判机关名称', type:'text',name: 'trialUnit', required: true, validater:'name', maxlength:50},
			        {text: '判决书编号', type:'text',name: 'judgmentNumber', required: true, maxlength:100},
			        {text: '判决日期', type:'datepicker',name: 'dateJudgment', required: true},
			        
			        {text: '执行通知书文号', type:'text',name: 'informNumber', required: true, maxlength:100},
			        {text: '执行通知书日期', type:'datepicker',name: 'dateInform'},
			        {text: '原羁押场所', type:'text',name: 'orgdetentionAddress', required: true, validater:'name', maxlength:50},
			        
			        {text: '交付执行日期', type:'datepicker',name: 'dateExecute'},
			        {text: '社区矫正决定机关', type:'text',name: 'decideUnit', required: true, validater:'name', maxlength:50},
			        {text: '文书生效日期', type:'datepicker',name: 'writEffectiveDate', required: true},
			        
			        {text: '文书类型', type:'dict_combobox',name: 'writType', required: true, code:'WSLX', emptyText:'不详'},
			        {text: '文书编号', type:'text',name: 'writNumber', required: true,  maxlength:100},
			        {},
			        
			        {text: '主要犯罪事实', type:'textarea',name: 'majorCrime', colspan:3, required: true, maxlength:500},
			        {text: '本次犯罪前的行政处罚/处分', type:'textarea',name: 'administrativePenalty', colspan:3, required: true, maxlength:200},
			        {text: '本次犯罪前的刑事处罚', type:'textarea',name: 'criminalPunshment', colspan:3, required: true, maxlength:200}
			]
		});
	},
	view: function(){
		var me = this;
		$.container.generate(me.config.container, {
			title: '法律文书信息',
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
			        
			        {text: '侦查机关', type:'display',name: 'investigateUnit'},
			        {text: '拘留日期', type:'display',name: 'dateDetention'},
			        {text: '立案日期', type:'display',name: 'dateRecord'},
			        
			        {text: '决定/批准逮捕机关', type:'display',name: 'permitArrestUnit'},
			        {text: '逮捕日期', type:'display',name: 'dateArrest'},
			        {text: '侦结日期', type:'display',name: 'dateEndInvestigate'},
			        
			        {text: '公诉机关（自诉人）', type:'display',name: 'publicProsecution'},
			        {text: '起诉书编号', type:'display',name: 'indictmentNumber'},
			        {text: '提起起诉日期', type:'display',name: 'dateIndictment'},
			        
			        {text: '审判机关名称', type:'display',name: 'trialUnit'},
			        {text: '判决书编号', type:'display',name: 'judgmentNumber'},
			        {text: '判决日期', type:'display',name: 'dateJudgment'},
			        
			        {text: '执行通知书文号', type:'display',name: 'informNumber'},
			        {text: '执行通知书日期', type:'display',name: 'dateInform'},
			        {text: '原羁押场所', type:'display',name: 'orgdetentionAddress'},
			        
			        {text: '交付执行日期', type:'display',name: 'dateExecute'},
			        {text: '社区矫正决定机关', type:'display',name: 'decideUnit'},
			        {text: '文书生效日期', type:'display',name: 'writEffectiveDate'},
			        
			        {text: '文书类型', type:'display',name: 'writType', formatter:$.dictionary.formatter('WSLX', '不详')},
			        {text: '文书编号', type:'display',name: 'writNumber'},
			        {},
			        
			        {text: '主要犯罪事实', type:'textarea',name: 'majorCrime', colspan:3, readonly:true},
			        {text: '本次犯罪前的行政处罚/处分', type:'textarea',name: 'administrativePenalty', colspan:3, readonly:true},
			        {text: '本次犯罪前的刑事处罚', type:'textarea',name: 'criminalPunshment', colspan:3, readonly:true}
			]
		});
	}
});
})(jQuery);