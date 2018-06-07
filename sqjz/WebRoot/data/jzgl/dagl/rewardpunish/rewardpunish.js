(function ($) {
"use strict";
$.rewardpunish = $.rewardpunish || {};
$.extend($.rewardpunish,{
	defaultConfig:{
		edit:{
			read: "read.action",
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
		me.config.read += '?id=' + me.config.fxryId;
		me.config.baseurl = me.config.baseurl || ($.fxryconfig.baseurl + 'rewardpunish/');
		me[action].call(me);
	},
	edit: function(){
		var me = this;
		var id = 'tabpages-container1';
		var container = $(".container-middle");
		$.container.generate(container, {
			id: id,
			type: 'panel',
			title: '基本身份信息'
		});
		var item=[{
			title: '基本身份信息',
			read: me.config.baseurl + me.config.read,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			},
			fields:[ 
			        {type:'hidden',name: 'id'},
			        {type:'hidden',name: 'fxryId', value: me.config.fxryId},
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
					{text: '指纹采集情况', type:'display',name: ''},
					
					{text: '监管设备编号', type:'display',name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'},
					{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
					{},
			        
			        {text: '护照号码', type:'display', name: 'passport'},
			        {text: '回乡证号码', type:'display', name: 'hrPermit'},
			        {text: '香港身份证号码', type:'display', name: 'hkIdentity'},
			        
			        {text: '澳门身份证号码', type:'display', name: 'amIdentity'},
			        {text: '台胞证号码', type:'display', name: 'tbIdentity'},
			        {text: '港澳台通行证号码', type:'display', name: 'gatPermit'},
			        
			        {text: '健康状况', type:'display', name: 'health', formatter:$.dictionary.formatter('JKZK', '不详')},
			        {text: '具体健康状况', type:'display', name: 'healthSpecific'},
			        {text: '具体传染病史', type:'display', name: 'healthContagion', formatter:$.dictionary.formatter('CRB', '不详')},
			        {text: '是否有精神病', type:'display', name: 'psychosis', formatter:$.dictionary.formatter('SF', '是')},
			        {text: '心理是否健康', type:'display', name: 'healthMental', formatter:$.dictionary.formatter('SF', '是')},
			        {text: '具体心理健康状况', type:'display', name: 'healthMentalSpecific'},
			        {text: '鉴定机构(精神病)', type:'display', name: 'accreditingOrgan'},
			        
			        {text: '原政治面貌', type:'display', name: 'politicsStatusOriginal', formatter:$.dictionary.formatter('ZZMM', '不详')},
			        {text: '政治面貌', type:'display', name: 'politicsStatus', formatter:$.dictionary.formatter('ZZMM', '不详')},
			        {text: '联系电话', type:'display', name: 'phoneNumber'},
			        
			        {text: '户籍性质', type:'display', name: 'natureHome', formatter:$.dictionary.formatter('HJXZ', '不详')},
			        {text: '户籍地是否<br/>与居住地相同', type:'display', name: 'issameHome', formatter:$.dictionary.formatter('SF', '是')},
			        
			        {text: '居住地详细地址', type:'display', colspan:3, name: 'houseAddress.fulltext'},
			        
			        {text: '户籍详细地址', type:'display', colspan:3, name: 'homeAddress.fulltext'},
			        
			        {text: '文化程度', type:'display', name: 'educationDegree', formatter:$.dictionary.formatter('WHCD', '不详')},
			        {text: '婚姻状况', type:'display', name: 'maritalState', formatter:$.dictionary.formatter('HYZK', '不详')},
			        {text: '就业就学情况', type:'display', name: 'workState', formatter:$.dictionary.formatter('JYJX', '不详')},
			        
			        {text: '现工作单位', type:'display', colspan:2, name: 'workNuit'},
			        {text: '单位联系电话', type:'display', name: 'workNuitPhone'}
			        ]}];
		$.container.generate($("#"+id), {
			type: 'row',
			items:item
		});
		var id2 = 'tabpages-container2';
		$.container.generate(container, {
			id: id2,
			type: 'panel',
			title: '奖惩信息'
		});
		var rp=[{
			title: '奖惩信息',
			read: me.config.baseurl + me.config.read,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			}
			}];
		var jlflag="sj"==me.config.location||"qxsfj"==me.config.location;
		var jlxx={type: 'grid',id: 'grid-reward-container',title:'奖励信息'};
		if(jlflag){
			jlxx.add=function(){ me.addReward('#grid-reward-container');};
			jlxx.delUrl=me.config.baseurl + 'deleteReward.action';
		}
		var wjflag="sfs"==me.config.location||"qxsfj"==me.config.location;
		var wjxx={type: 'grid',id: 'grid-punish-container',title:'违纪信息'};
		if(wjflag){
			wjxx.add=function(){ me.addPunish('#grid-punish-container');}; 
			wjxx.delUrl=me.config.baseurl + 'deletePunish.action';
		}
		rp.push(jlxx);
		rp.push(wjxx);
		$.container.generate($("#"+id2), {
			type: 'row',
			items:rp
		});
		var id3 = 'tabpages-container3';
		$.container.generate(container, {
			id: id3,
			type: 'panel',
			title: '违法信息'
		});
		var rp=[{
			title: '违法信息',
			read: me.config.baseurl + me.config.read,
			fieldCols : 3, 
			fieldCls: {
				labelCls: 'form-td-label-120'
			}
			}];
		
		var wfxx={type: 'grid',id: 'grid-illegal-container',title:'违法信息'	};
		if(wjflag){
			wfxx.add=function(){ me.addIllegal('#grid-illegal-container');};
			wfxx.delUrl=me.config.baseurl + 'deleteIllegal.action';
		}
		var fzxx={type: 'grid',id: 'grid-acrime-container', title:'涉嫌再犯罪信息'};
		if(jlflag){
			fzxx.add=function(){ me.addACrime('#grid-acrime-container');};
			fzxx.modify=function(){ me.modifyACrime('#grid-acrime-container');};
			fzxx.delUrl=me.config.baseurl + 'deleteCrime.action';
		}
		rp.push(wfxx);
		rp.push(fzxx);
		$.container.generate($("#"+id3), {
			type: 'row',
			items:rp
		});
		me.addRewardGrid('grid-reward-container', me.config.fxryId,!jlflag);
		me.addPunishGrid('grid-punish-container', me.config.fxryId,!wjflag);
		me.addIllegalGrid('grid-illegal-container', me.config.fxryId,!wjflag);
		me.addACrimeGrid('grid-acrime-container', me.config.fxryId,!jlflag);
		me.addBtns();
	},
	addBtns: function(){
		var me = this;
		var container = $(".container-bottom");
		var btns = $('<div id="buttons-container" class="buttonArea operation alignRight"></div>');
		container.append(btns);
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
	view: function(){
		var me = this;
		$.container.generate(me.config.container, {
			title: '奖惩情况',
			read: me.config.baseurl + me.config.read,
			save: false,
			cancel: false,
			items:[{
				type: 'grid',
				id: 'grid-reward-container', 
				title:'奖励信息' 
			},{
				type: 'grid',
				id: 'grid-punish-container', 
				title:'违纪信息' 
			},{
				type: 'grid',
				id: 'grid-illegal-container', 
				title:'违法信息' 
			},{
				type: 'grid',
				id: 'grid-acrime-container', 
				title:'涉嫌再犯罪信息' 
			}]
		});
		me.addRewardGrid('grid-reward-container', me.config.fxryId, true);
		me.addPunishGrid('grid-punish-container', me.config.fxryId, true);
		me.addIllegalGrid('grid-illegal-container', me.config.fxryId, true);
		me.addACrimeGrid('grid-acrime-container', me.config.fxryId, true);
	},
	addRewardGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listReward.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'rewarddate',
				index:'rewarddate',
				label: '奖励日期',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'rewardorg',
				index:'rewardorg',
				label: '奖励单位',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'reward',
				index:'reward',
				label: '奖项',
				width: 200,
				align: 'center'
			},
			{
				name:'reason',
				index:'reason',
				label: '具体原因',
				width: 275,
				align: 'center'
			},
			{
				name:'info',
				index:'info',
				label: '受奖情况',
				width: 275,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'rewarddate',
			sortorder: "desc"
		});
	},
	addPunishGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listPunish.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'punishdate',
				index:'punishdate',
				label: '违纪处理日期',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'punishorg',
				index:'punishorg',
				label: '处理单位',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'result',
				index:'result',
				label: '违纪处理结果',
				width: 200,
				formatter: 'dictionary',
				formatoptions:{
					code:'WJCLJG'
				},
				align: 'center'
			},
			{
				name:'detail',
				index:'detail',
				label: '主要违纪事实',
				width: 550,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'punishdate',
			sortorder: "desc"
		});
	},
	addIllegalGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listIllegal.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'dealdate',
				index:'dealdate',
				label: '违法处理日期',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'dealorg',
				index:'dealorg',
				label: '处理单位',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'illegaltype',
				index:'illegaltype',
				label: '违法类型',
				width: 200,
				formatter: 'dictionary',
				formatoptions:{
					code:'WFLX'
				},
				align: 'center'
			},
			{
				name:'detail',
				index:'detail',
				label: '主要违法事实',
				width: 550,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'dealdate',
			sortorder: "desc"
		});
	},
	addACrimeGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listCrime.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'dealdate',
				index:'dealdate',
				label: '被采取强制措施时间',
				width: 130,
				fixed: true,
				align: 'center'
			},
			{
				name:'enddate',
				index:'enddate',
				label: '强制措施结束时间',
				width: 130,
				fixed: true,
				align: 'center'
			},
			{
				name:'dealorg',
				index:'dealorg',
				label: '侦查机关',
				width: 150,
				fixed: true,
				align: 'center'
			},
			{
				name:'crimeType',
				index:'crimeType',
				label: '涉嫌再犯罪类型',
				width: 150,
				formatter: 'dictionary',
				formatoptions:{
					code:'FZLX'
				},
				fixed: true,
				align: 'center'
			},
			{
				name:'accusation',
				index:'accusation',
				label: '所涉罪名',
				formatter: 'dictionary',
				formatoptions:{
					code:'JTZM'
				},
				width: 150,
				align: 'center'
			},
			{
				name:'detail',
				index:'detail',
				label: '主要涉嫌再犯罪事实',
				width: 200,
				align: 'center'
			},
			{
				name:'explain',
				index:'explain',
				label: '情况说明',
				width: 200,
				align: 'center'
			}
			],
			multiselect: !readonly,
			rownumbers : !readonly,
			sortname: 'dealdate',
			sortorder: "desc"
		});
	},
	addReward: function(elem){
		var me = this;
		$.container.popup({
			title: '添加奖励信息',
			save: me.config.baseurl + 'saveReward.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[          
			        
					{type:'hidden',name: 'fxryid', value: me.config.fxryId},
					{text: '奖励日期', type:'datepicker',name: 'rewarddate', required: true},
			        {text: '奖励单位', type:'text',name: 'rewardorg', required: true,maxlength:100},
			        {text: '奖项', type:'text',name: 'reward', required: true, maxlength:50},
					{text: '具体原因', type:'textarea',name: 'reason', required: true,maxlength:200},
					{text: '受奖情况', type:'textarea',name: 'info', required: true,maxlength:200}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "550px"});
	},
	addPunish: function(elem){
		var me = this;
		$.container.popup({
			title: '添加违纪信息',
			save: me.config.baseurl + 'savePunish.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryid', value: me.config.fxryId},
					{text: '违纪处理时间', type:'datepicker',name: 'punishdate', required: true},
					{text: '处理单位', type:'text',name: 'punishorg', required: true, maxlength:100},
					{text: '违纪处理结果', type:'dict_radio', zIndex: 2000,name: 'result', code:'WJCLJG', required: true,allowBlank:false},
					{text: '主要违纪事实', type:'textarea',name: 'detail', required: true, maxlength:200}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "550px"});
	},
	addIllegal: function(elem){
		var me = this;
		$.container.popup({
			title: '添加违法信息',
			save: me.config.baseurl + 'saveIllegal.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryid', value: me.config.fxryId},
					{text: '违法处理时间', type:'datepicker',name: 'dealdate', required: true},
					{text: '处理单位', type:'text',name: 'dealorg', required: true, maxlength:100},
					{text: '违法类型', type:'dict_combobox', name: 'illegaltype', code:'WFLX', required: true, emptyText:'不详', allowSearch: true},
					{text: '主要违法事实', type:'textarea',name: 'detail', required: true, maxlength:200}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "550px"});
	},
	addACrime: function(elem){
		var me = this;
		$.container.popup({
			title: '添加涉嫌再犯罪信息',
			save: me.config.baseurl + 'saveCrime.action',
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[           
					{type:'hidden',name: 'fxryid', value: me.config.fxryId},
					{text: '被采取强制措施时间', type:'datepicker',name: 'dealdate', required: true},
					{text: '强制措施结束时间', type:'datepicker',name: 'enddate'},
					{text: '侦查机关', type:'text',name: 'dealorg', required: true, maxlength:100},
					{text: '所涉罪名', type:'dict_combobox',name: 'accusation', code:'JTZM',required: true, multiSelected:true, emptyText:'无',valuechange: function(e){
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
			        		//增加选项时会调用两次,排除其中一次无用的调用
			        		if($.isArray(e)&&jtzms.length==e.length){
			        			var code=[jtzms[0]];
			        			//从字典中获取选中的项，主要是用于获取其parent的值
			        			var data=$.grep(jtzm, function(item){
			        				return $.inArray(item.code, code) >= 0;
			        			});
			        			fzlx=data[0].parent;
			        			if(fzlx!=nowfzlx){
			        				realChange=true;
			        			}
			        		}
			        	}
			        	if(realChange){
			        		//设置犯罪类型的值
			        		$('input[name="crimeType"]').val(fzlx).trigger('valuechange');
			        	}
					}},
					{text: '涉嫌再犯罪类型', type:'display',name: 'crimeType_show',required: true},
			        {text: '涉嫌再犯罪类型', type:'hidden', name: 'crimeType',required: true,valuechange: function(){
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
					{text: '主要涉嫌犯罪事实', type:'textarea',name: 'detail', required: true, maxlength:200},
					{text: '情况说明', type:'textarea',name: 'explain', maxlength:200}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "550px"});
	},
	modifyACrime: function(elem){
		var me = this;
		var ids = $(elem).getGridParam("selarrrow");
		if(ids==null||ids.length<1){
			$.dialog.alert("请选择需要修改的数据！");
			return false;
		}
		if(ids.length>1){
			$.dialog.alert("请只选择一条需要修改的数据！");
			return false;
		}
		$.container.popup({
			title: '修改涉嫌再犯罪信息',
			read: me.config.baseurl + 'getCrime.action?id='+ids,
			save: me.config.baseurl + 'saveCrime.action',
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:[
			        {type:'hidden',name: 'id'},
					{type:'hidden',name: 'fxryid', value: me.config.fxryId},
					{text: '被采取强制措施时间', type:'datepicker',name: 'dealdate', required: true},
					{text: '强制措施结束时间', type:'datepicker',name: 'enddate'},
					{text: '侦查机关', type:'text',name: 'dealorg', required: true, maxlength:100},
					{text: '所涉罪名', type:'dict_combobox',name: 'accusation', code:'JTZM',required: true, multiSelected:true, emptyText:'无',valuechange: function(e){
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
			        		//增加选项时会调用两次,排除其中一次无用的调用
			        		if($.isArray(e)&&jtzms.length==e.length){
			        			var code=[jtzms[0]];
			        			//从字典中获取选中的项，主要是用于获取其parent的值
			        			var data=$.grep(jtzm, function(item){
			        				return $.inArray(item.code, code) >= 0;
			        			});
			        			fzlx=data[0].parent;
			        			if(fzlx!=nowfzlx){
			        				realChange=true;
			        			}
			        		}
			        	}
			        	if(realChange){
			        		//设置犯罪类型的值
			        		$('input[name="crimeType"]').val(fzlx).trigger('valuechange');
			        	}
					}},
					{text: '涉嫌再犯罪类型', type:'display',name: 'crimeType_show',required: true},
			        {text: '涉嫌再犯罪类型', type:'hidden', name: 'crimeType',required: true,valuechange: function(){
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
					{text: '主要涉嫌犯罪事实', type:'textarea',name: 'detail', required: true, maxlength:200},
					{text: '情况说明', type:'textarea',name: 'explain', maxlength:200}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		},{width: "550px"});
	}
});
})(jQuery);