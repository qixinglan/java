(function ($) {
"use strict";
$.authorise = $.authorise || {};
$.extend($.authorise, {
	init:function(container){
		var me=this;
		$.container.generate(container, { 
			items:[{ 
			read: CONTEXT_PATH + '/sys/authorise/sysInfo.action',
			save: false,
			cancel: false,
			afterRead:function(){
				var lic=$("input[name='hasLicense']").val();
				var timeOut=$("input[name='timeOut']").val();
				var detail="";
				if('true'!=lic){
					detail="您的系统未授权，请将下面系统信息发至开发商以获取授权码！";
				}else if('true'==timeOut){
					var dbTimeOut=$("input[name='dbTimeOut']").val();
					if('true'==dbTimeOut){
						detail="您的系统数据库和应用时间差距大于1天，请尽快矫正时间！";
					}else{
						detail="您的系统授权已经过期，请将下面系统信息发至开发商以重新获取授权码！";
					}
				}
				$("span[name='detail']").text(detail);
			},
			fieldCols : 1, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[
			        {type:'hidden',name: 'hasLicense'},
			        {type:'hidden',name: 'timeOut'},
			        {type:'hidden',name: 'dbTimeOut'},
			        {type:'display',name: 'detail'},
					{text: '操作系统', type:'display',name: 'os'},
					{text: '特征码1', type:'display',name: 'board'},
					{text: '特征码2', type:'display',name: 'cpu'},
					{text: '应用版本', type:'display',name: 'version'}
			]
		},{
			title:"应用授权",
			save: CONTEXT_PATH + '/sys/authorise/authorise.action',
			saveVal: '授权',
			read: '',
			fieldCols:1,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[
				{text: '授权码', type:'textarea',name: 'authoriseCode', required: true, maxlength:10000}
			],
			yes: function(data){
				me.authoriseSuccess(data);
			}
		}]});
	},
	authoriseSuccess: function(data){
		var me = this;
		$.dialog({
			title : '授权成功',
			id: 'confirm.png',
			icon : 'success.png',
			fixed : true,
			lock : true,
			width : 250,
			content : data.msg,
			resize : false,
			ok : function(){
				window.location = CONTEXT_PATH+LOGIN;
			},
			okVal : '登录系统',
			cancel : false
		});
	},
});
})(jQuery);