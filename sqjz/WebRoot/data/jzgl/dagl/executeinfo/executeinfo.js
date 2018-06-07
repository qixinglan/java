(function ($) {
"use strict";
$.executeinfo = $.executeinfo || {};
$.extend($.executeinfo,{
	defaultConfig:{
		edit:{
			read: "read.action",
			save: "save.action",
			yes: null,
			cancel: null,
			editable: false
		},
		view:{
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
		me.config.baseurl = me.config.baseurl || ($.fxryconfig.baseurl + 'executeinfo/');
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
			content : '修改刑罚执行信息成功！',
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
			type: 'row',
			items:[{
				title: '刑罚执行信息',
				read: me.config.baseurl + me.config.read,
				afterRead: function(){
					//根据原判刑罚判断原判刑期结束日期是否必录
					var oriPunishment = $('input[name="oriPunishment"]').val();
					$('input[name="dateEOri"]').setRequired(oriPunishment != '1' && oriPunishment != '2');
					var addpunishment = $('input[name="addpunishment"]').val();
					//根据附加刑判断剥夺政治权利期限、剥夺政治权利起止日、移交日期、接收剥夺政治权利人员的公安机关名称、接收人是否必录
					var required = addpunishment.indexOf('2')>=0;
					$('input[name="nonpoliticalPeriod"]').setRequired(required,$('input[name="nonpoliticalPeriod"]').val());
					$('input[name="dateSNonpolitical"]').setRequired(required);
					$('input[name="dateENonpolitical"]').setRequired(required);
					$('input[name="receiveUnit"]').setRequired(required,$('input[name="receiveUnit"]').val());
					$('input[name="receivePerson"]').setRequired(required,$('input[name="receivePerson"]').val());
					$('input[name="dateTransfer"]').setRequired(required);
				},
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
				        {text: '矫正类型', type:'dict_combobox', name: 'adjustType', code:'JZLB', required: true, emptyText:'不详'},
				        {text: '矫正期限', type:'text',name: 'adjustPeriod', required: true, maxlength:50},
				        //组合字段
				        {text: '矫正起止日', type:'fieldset', required: true, fields:[
				        	{text:'矫正起始日期', type:'datepicker', name: 'dateSAdjust', required: true},
					        {text:'矫正结束日期', type:'datepicker', name: 'dateEAdjust', required: true}
				        ]},
				        {text: '具体罪名', type:'dict_combobox',colspan:2,required: true,name: 'accusation', code:'JTZM', multiSelected:true, emptyText:'无',valuechange: function(e){
				        	//和犯罪类型的联动
				        	var jtzm=$.dictionary.getDict('JTZM');
				        	var fzlx="";
				        	var nowfzlx=$('input[name="crimeType"]', $(me.config.container)).val();
				        	var realChange=false;
				        	if(this.value==""){
				        		realChange=true;
				        		if(e&&!$.isArray(e)){
				        			var event=[];
				        			event[0]=e;
				        			var data=$.grep(jtzm, function(item){
				        				return $.inArray(item.code, event) >= 0;
				        			});
				        			if(data){
				        				fzlx=data[0].parent;
				        			}
				        		}
				        	}else{
				        		var jtzms=this.value.split(",");
				        		var code=[jtzms[0]];
			        			//从字典中获取选中的项，主要是用于获取其parent的值
			        			var data=$.grep(jtzm, function(item){
			        				return $.inArray(item.code, code) >= 0;
			        			});
			        			if(data[0]){
			        				//增加选项时会调用两次,排除其中一次无用的调用
					        		if($.isArray(e)&&jtzms.length==e.length){
					        			fzlx=data[0].parent;
					        			if(fzlx!=nowfzlx){
					        				realChange=true;
					        			}
					        		}
			        			}else{
			        				//兼容老数据的问题，清除老数据
			        				var temp="";
			        				for(var i=1;i<jtzms.length;i++){
			        					temp=jtzms[i]+",";
			        				}
			        				if(temp.length>1){
			        					temp=temp.substring(0,temp.length-1);
			        					$('input[name="accusation"]', $(me.config.container)).val(temp).trigger('valuechange');
			        				}
			        			}
				        	}
				        	if(realChange){
				        		//设置犯罪类型的值
				        		$('input[name="crimeType"]', $(me.config.container)).val(fzlx).trigger('valuechange');
				        	}
						}},
				        {text: '犯罪类型', type:'display',name: 'crimeType_show',required: true},
				        {text: '犯罪类型', type:'hidden', name: 'crimeType',required: true,valuechange: function(){
				        	if(this.value==""){
				        		$('span[name="crimeType_show"]').text("");
				        	}else{
				        		var fzlx=$.dictionary.getDict('FZLX');
					        	for(var i in fzlx){
					        		if (fzlx[i].code == this.value){
					        			$('span[name="crimeType_show"]').text(fzlx[i].name);
				    				}
					        	}
				        	}
				        	}},
						{text: '原判刑罚', type:'dict_combobox', name: 'oriPunishment', code:'XFLX', required: true, emptyText:'不详',
							valuechange:function(value){
				        		$('input[name="dateEOri"]').setRequired(value != '1' && value != '2');
				        	}},
						{text: '原判刑期起止日', type:'fieldset', required: true, fields:[
							{text:'原判刑期起始日期', type:'datepicker', name: 'dateSOri', required: true},
							{text:'原判刑期结束日期', type:'datepicker', name: 'dateEOri', required: true}
						]},
						{text: '原判刑期', type:'text',name: 'oriPeriod', required: true, maxlength:50},
						{text: '有期徒刑期限', type:'dict_combobox', name: 'imprisonmentPeriod', code:'YQTXQX', emptyText:'无'},
						
						{text: '附加刑', type:'dict_combobox',name: 'addpunishment', code:'FJX', emptyText:'无',multiSelected:true,
							valuechange:function(value){
								var addpunishment = $('input[name="addpunishment"]').val();
								var required = addpunishment.indexOf('2')>=0;
								$('input[name="nonpoliticalPeriod"]').setRequired(required,$('input[name="nonpoliticalPeriod"]').val());
								$('input[name="dateSNonpolitical"]').setRequired(required);
								$('input[name="dateENonpolitical"]').setRequired(required);
								$('input[name="receiveUnit"]').setRequired(required,$('input[name="receiveUnit"]').val());
								$('input[name="receivePerson"]').setRequired(required,$('input[name="receivePerson"]').val());
								$('input[name="dateTransfer"]').setRequired(required);
				        	}
						},
						{text: '剥夺政治权利期限', type:'text',name: 'nonpoliticalPeriod', maxlength:50, blankMsg:'附加刑为“剥夺政治权利”，请填写剥夺政治权利期限。',defValue:'无'},
						{text: '剥夺政治权利起止日', type:'fieldset', fields:[
							{text:'剥夺政治权利起始日期', type:'datepicker', name: 'dateSNonpolitical', blankMsg:'附加刑为“剥夺政治权利”，请填写剥夺政治权利起始日期。'},
							{text:'剥夺政治权利结束日期', type:'datepicker', name: 'dateENonpolitical', blankMsg:'附加刑为“剥夺政治权利”，请填写剥夺政治权利结束日期。'}
						]},
						
						{text: '接收剥夺政治权利人员的公安机关名称', type:'text',name: 'receiveUnit', maxlength:50, validater:'name', blankMsg:'附加刑为“剥夺政治权利”，请填写接收剥夺政治权利人员的公安机关名称。',defValue:'无'},
						{text: '接收人', type:'text',name: 'receivePerson', maxlength:20, validater:'name', blankMsg:'附加刑为“剥夺政治权利”，请填写接收人。',defValue:'无'},
						{text: '移交日期', type:'datepicker',name: 'dateTransfer', blankMsg:'附加刑为“剥夺政治权利”，请填写移交日期。'},
						
						{text: '是否有“四史”', type:'dict_combobox', code:'SS', name: 'sisType', emptyText:'无', multiSelected:true},
						{text: '是否有“三涉”', type:'dict_combobox',code:'SANS', name: 'sansType', emptyText:'无', multiSelected:true},
						{text: '是否累犯', type:'dict_radio', code:'SF', name: 'isRecidivism', allowBlank:false, defValue: '2'},
						{text: '是否"三类犯罪"', type:'dict_combobox',code:'SLFZ', name: 'slfzType', emptyText:'无', multiSelected:true},
						{text: '是否共同犯罪', type:'dict_radio', code:'SF', name: 'isalone', allowBlank:false, defValue: '2'},
						{text: '是否被宣告禁止令', type:'dict_radio', code:'SF', name: 'isforbidden', allowBlank:false, defValue: '2',
							valuechange:function(value){
							var required = value == '1';
							$('input[name="forbidden"]').setRequired(required,$('input[name="forbidden"]').val());
							$('input[name="dateSForbidden"]').setRequired(required);
							$('input[name="dateEForbidden"]').setRequired(required);
			        	}},
						{text: '禁止令内容', type:'text',colspan:2, name: 'forbidden', maxlength:100,defValue:'无'},
						
						{text: '禁止期限起止日', type:'fieldset', fields:[
							{text:'宣告禁止起始日期', type:'datepicker', name: 'dateSForbidden'},
							{text:'宣告禁止结束日期', type:'datepicker', name: 'dateEForbidden'}
						]},
						{text: '社区服刑人员<br/>接收日期', id: '',type:'datepicker',name: 'dateReceive'},
						{text: '社区服刑人员<br/>接收方式', type:'dict_combobox',name: 'receiveType', code:'JSFS', emptyText:'无'},
						{text: '报到情况', type:'dict_combobox',name: 'reportInfo', code:'BDQK', emptyText:'不详', required:true,
							valuechange:function(value){
				        		var required = value != '3';
				        		$('input[name="dateReceive"]').setRequired(required);
				        		$('input[name="receiveType"]').setRequired(required);
				        		$('input[name="groupInfo"]').setRequired(required);
				        	}
						},
						{text: '矫正小组人员<br/>组成情况', type:'dict_combobox',colspan:2,name: 'groupInfo', code:'JZXZRYQK', multiSelected:true, emptyText:'无'},
						{text: '备注', type:'textarea',name: 'remark', colspan:3, maxlength:200,defValue:'无'}
				]},{
				type: 'grid',
				id: 'grid-crime-container', 
				title:'共同犯罪情况' ,
				add: function(){ me.addCrime('#grid-crime-container');}, 
				delUrl: me.config.baseurl + 'deleteComplicity.action'
			},{
				type: 'grid',
				id: 'grid-imprisonment-container', 
				title:'暂予监外执行罪犯押送情况' ,
				add: function(){ me.addEscort('#grid-imprisonment-container');}, 
				delUrl: me.config.baseurl + 'deleteEscort.action'
			}]
		});
		me.addCrimeGrid('grid-crime-container', me.config.fxryId);
		me.addEscortGrid('grid-imprisonment-container', me.config.fxryId);
	},
	add: function(){
		
	},
	view: function(){
		var me = this;
		$.container.generate(me.config.container, {
			title: '刑罚执行信息',
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
			        {text: '矫正类型', type:'display', name: 'adjustType', formatter:$.dictionary.formatter('JZLB', '不详')},
			        {text: '矫正期限', type:'display',name: 'adjustPeriod'},
			        //组合字段
			        {text: '矫正起止日', type:'fieldset', fields:[
			        	{type:'display', name: 'dateSAdjust'},
				        {type:'display', name: 'dateEAdjust'}
			        ]},
			           
			        {text: '犯罪类型', type:'display', name: 'crimeType', formatter:$.dictionary.formatter('FZLX', '不详')},
					{text: '具体罪名', type:'display',name: 'accusation',colspan:2,formatter:$.dictionary.formatter('JTZM', '不详')},
					{text: '原判刑罚', type:'display', name: 'oriPunishment', formatter:$.dictionary.formatter('XFLX', '不详')},
					
					{text: '原判刑罚起止日', type:'fieldset', fields:[
						{type:'display', name: 'dateSOri'},
						{type:'display', name: 'dateEOri'}
					]},
					{text: '原判刑期', type:'display',name: 'oriPeriod'},
					{text: '有期徒刑期限', type:'display', name: 'imprisonmentPeriod', formatter:$.dictionary.formatter('YQTXQX', '无')},
					
					{text: '附加刑', type:'display',name: 'addpunishment', formatter:$.dictionary.formatter('FJX', '无')},
					{text: '剥夺政治权利期限', type:'display',name: 'nonpoliticalPeriod',defValue:'无'},
					{text: '剥夺政治权利起止日', type:'fieldset', fields:[
   						{type:'display', name: 'dateSNonpolitical',defValue:'无'},
   						{type:'display', name: 'dateENonpolitical'}
   					],defValue:'无'},
					
					{text: '接收剥夺政治权利人员的公安机关名称', type:'display',name: 'receiveUnit',defValue:'无'},
					{text: '接收人', type:'display',name: 'receivePerson',defValue:'无'},
					{text: '移交日期', type:'display',name: 'dateTransfer',defValue:'无'},
					
					{text: '是否有“四史”', type:'display', name: 'sisType', formatter:$.dictionary.formatter('SS', '无')},
					{text: '是否有“三涉”', type:'display',name: 'sansType', formatter:$.dictionary.formatter('SANS', '无')},
					{text: '是否累犯', type:'display', name: 'isRecidivism', formatter:$.dictionary.formatter('SF', '否')},
					{text: '是否"三类犯罪"', type:'display',name: 'slfzType', formatter:$.dictionary.formatter('SLFZ', '无')},
					{text: '是否共同犯罪', type:'display', name: 'isalone', formatter:$.dictionary.formatter('SF', '否')},
					{text: '是否被宣告禁止令', type:'display', name: 'isforbidden', formatter:$.dictionary.formatter('SF', '否')},
					{text: '宣告禁止令内容', type:'display',colspan:2, name: 'forbidden',defValue:'无'},
					{text: '禁止期限起止日', type:'fieldset', fields:[
						{type:'display', name: 'dateSForbidden',defValue:'无'},
						{type:'display', name: 'dateEForbidden'}
					]},
					
					{text: '社区服刑人员<br/>接收日期', type:'display',name: 'dateReceive'},
					{text: '社区服刑人员<br/>接收方式', type:'display',name: 'receiveType', formatter:$.dictionary.formatter('JSFS', '不详')},
					{text: '报到情况', type:'display',name: 'reportInfo', formatter:$.dictionary.formatter('BDQK', '不详')},
					
					{text: '矫正小组人员<br/>组成情况', type:'display',colspan:2,name: 'groupInfo', formatter:$.dictionary.formatter('JZXZRYQK', '不详')},
					{text: '备注', type:'textarea',name: 'remark', colspan:3, readonly:true, maxlength:200,defValue:'无'},
			],
			items:[{
				type: 'grid',
				id: 'grid-crime-container', 
				title:'共同犯罪情况' 
			},{
				type: 'grid',
				id: 'grid-imprisonment-container', 
				title:'暂予监外执行罪犯押送情况' 
			}]
		});
		
		me.addCrimeGrid('grid-crime-container', me.config.fxryId, true);
		me.addEscortGrid('grid-imprisonment-container', me.config.fxryId, true);
	},
	addCrime: function(elem){
		var me = this;
		$.container.popup({
			title: '添加共同犯罪情况',
			save: me.config.baseurl + 'saveComplicity.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{text: '姓名', type:'text',name: 'name', required: true, maxlength:20, validater:'name'},
					{text: '性别', type:'dict_radio', zIndex: 2000,name: 'sex', code:'XB', required: true,allowBlank:false},
					{text: '出生日期', type:'datepicker',name: 'birthday', required: true},
					{text: '罪名', type:'text',name: 'accusation', required: true, maxlength:50},
					{text: '被判处刑罚及所在监所', type:'text',name: 'punishmentAddress', required: true, maxlength:100}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "450px"});
	},
	addEscort: function(elem){
		var me = this;
		$.container.popup({
			title: '添加暂予监外执行罪犯押送情况',
			save: me.config.baseurl + 'saveEscort.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{text: '执行押送的警察姓名', type:'text',name: 'policeName', required: true, maxlength:20, validater:'name'},
					{text: '单位', type:'text',name: 'policeOffice', required: true, maxlength:50},
					{text: '职务', type:'text',name: 'job', required: true, maxlength:20},
					{text: '押送日期', type:'datepicker',name: 'escortDate', required: true}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "450px"});
	},
	addCrimeGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listComplicity.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'name',
				index:'name',
				label: '姓名',
				width: 20,
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
					code: 'XB'
				},
				align: 'center'
			},
			{
				name:'birthday',
				index:'birthday',
				label: '出生日期',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'accusation',
				index:'accusation',
				label: '罪名',
				width: 30,
				align: 'center'
			},
			{
				name:'punishmentAddress',
				index:'punishmentAddress',
				label: '被判处刑罚及所在监所',
				width: 60,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'createTime',
			sortorder: "asc"
		});
	},
	addEscortGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listEscort.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'policeName',
				index:'policeName',
				label: '执行押送的警察姓名',
				width: 20,
				align: 'center'
			},
			{
				name:'policeOffice',
				index:'policeOffice',
				label: '单位',
				width: 60,
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
				name:'escortDate',
				index:'escortDate',
				label: '押送日期',
				width: 150,
				fixed: true,
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