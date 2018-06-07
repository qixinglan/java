(function ($) {
"use strict";
$.fxrymoreinfo = $.fxrymoreinfo || {};
$.extend($.fxrymoreinfo,{
	defaultConfig:{
		edit:{
			read: $.fxryconfig.baseurl + "read.action",
			save: $.fxryconfig.baseurl + "updateJBSFXX.action",
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
		me.config.baseurl = me.config.baseurl || $.fxryconfig.baseurl + 'moreinfo/';
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
	addressRequired:function(select,required, emptyValue){
		$(select).attr('required', required);
		if (!required){
			//如果非必须，清空值
			$(select).val(emptyValue || '');
			$(select).trigger('valuechange', emptyValue || '');
		}
	},
	edit: function(){
		var me = this;
		$.container.generate(me.config.container, {
			type: 'row',
			items:[{ 
				read: me.config.read + '?id=' + me.config.fxryId,
				save: me.config.save,
				afterRead: function(model){
					//是否已采集指纹判断
					var fingerPrintNo = $('input[name="fingerPrintNo"]').attr("value");
					if(fingerPrintNo!=""){
						$.fields.initPic();
						//$("#collectPrint").attr("disabled",true);
					}
				},
				cancel: me.config.cancel != null ? me.config.cancel : me.config.listPage,
				saveVal: '保存',
				cancelVal: '取消',
				yes: me.config.yes != null ? me.config.yes : function(data){ me.saveSuccess(data); },
				fieldCols : 3, 
				fieldCls: {
					labelCls: 'form-td-label-120'
				},
				fields:[     
				        {type:'hidden',name: 'id', value: me.config.fxryId},
				        {type:'hidden',name: 'fingerPrintNo'},
				        {text: '人员编号', type:'display',name: 'code'},
						{text: '人员当前状态', type:'display',name: 'state', formatter:$.dictionary.formatter('JZRYZT', '不详')},
						{type:'bdPhoto',name: 'picture', rowspan:4},
						
						{text: '姓名', type:'text',name: 'name', required: true, validater:'name', maxlength:20},
						{text: '曾用名', type:'text',name: 'userdName', validater:'name', maxlength:20,defValue:'无'},
						
						{text: '身份证号', type:'text',name: 'identityCard', required: true, maxlength:18, validater:'idcard'
							,valuechange: '$.fxrymoreinfo.idcard(this.value,"input[name=\'birthdate\']")'},
						{text: '出生日期', type:'datepicker',name: 'birthdate', required: true},
						
						{text: '性别', type:'dict_radio',name: 'sex', code:'XB', required: true},
						{text: '是否成年', type:'dict_radio',name: 'isadult', code: 'SF', required: true},
						
						{text: '民族', type:'dict_combobox',name: 'nation', code: 'MZ', required: true, allowSearch:true, emptyText:'不详'},
						{text: '到区县局报到时间', type:'display', name: 'reportTime'},
						//{text: '指纹编号', type:'display',name: 'fingerPrintNo_show'},
						{text: '', type:'pictureprint', name: 'pictureprint'},
						{text: '监管设备编号', type:'display',name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'},
						{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
						{text: '是否特管人员', type:'dict_radio',name: 'isTgry', code: 'SF', required: true},
				        
				        {text: '护照号码', type:'text', name: 'passport', maxlength:60, validater:'code',placeholder:'无'},
				        {text: '回乡证号码', type:'text', name: 'hrPermit', validater:'regex', maxlength:7, expect:/^\d{7}$/,placeholder:'无', errorMsg:'回乡证号码必须为7位数字。'},
				        {text: '香港身份证号码', type:'text', name: 'hkIdentity', validater:'regex', maxlength:11, expect:/^[HM]\d{10}$/, placeholder:'H/M+10位数字', errorMsg:'香港身份证号码必须为H/M+10位数字'},
				        
				        {text: '澳门身份证号码', type:'text', name: 'amIdentity', maxlength:60, validater:'code',placeholder:'无'},
				        {text: '台胞证号码', type:'text', name: 'tbIdentity', maxlength:60, validater:'code',placeholder:'无'},
				        {text: '港澳台通行证号码', type:'text', name: 'gatPermit', validater:'regex', maxlength:11, expect:/^[W]\d{8}$/, placeholder:'W+8位数字',errorMsg:'港澳台通行证号码必须为W+8位数字'},
				        
				        {text: '健康状况', type:'dict_radio', name: 'health', code:'JKZK', allowBlank: false, required: true,
				        	valuechange:function(value){
				        		$('input[name="healthSpecific"]').setRequired(value == '3');
				        	}
				        },
				        {text: '具体健康状况', type:'text', name: 'healthSpecific', maxlength:100, blankMsg:'健康状况为“较差”时，请填写具体健康状况。'},
				        {text: '具体传染病史', type:'dict_combobox', name: 'healthContagion', code:'CRB', emptyText:'无'},
				        
				        {text: '是否有精神病', type:'dict_radio', name: 'psychosis', code:'SF', allowBlank: false, required: true,defValue:'2',
				        },
				        {text: '心理是否健康', type:'dict_radio', name: 'healthMental', code:'SF', allowBlank: false, required: true,
				        	valuechange:function(value){
				        		$('input[name="healthMentalSpecific"]').setRequired(value == '2');
				        		$('input[name="accreditingOrgan"]').setRequired(value == '2');
				        	}
				        },
				        {text: '具体心理健康状况', type:'text', name: 'healthMentalSpecific', maxlength:100, blankMsg:'心理是否健康选择“否”时，请填写具体心理健康状况。'},
				        {text: '鉴定机构(精神病)', type:'text', name: 'accreditingOrgan', maxlength:100, blankMsg:'心理是否健康选择“否”时，请填写鉴定机构。'},
				        
				        {text: '原政治面貌', type:'dict_combobox', name: 'politicsStatusOriginal', code:'ZZMM', emptyText:'不详', required: true, allowSearch:true},
				        {text: '政治面貌', type:'dict_combobox', name: 'politicsStatus', code:'ZZMM', emptyText:'不详', allowSearch:true},
				        {text: '联系电话', type:'text', name: 'phoneNumber', validater:'phone', maxlength:11,required: true},
				        
				        {text: '户籍性质', type:'dict_combobox', name: 'natureHome', code:'HJXZ', emptyText:'不详',
				        	required: true,valuechange: function(value){
				        		me.addressRequired('input[name="homeAddress.detail"]', value != '1' && value != '2');
				        		me.addressRequired('input[name="homeAddress.province"]', value == '1' || value == '2');
				        		me.addressRequired('input[name="homeAddress.city"]', value == '1' || value == '2');
				        		me.addressRequired('input[name="homeAddress.county"]', value == '1' || value == '2');
				        		me.addressRequired('input[name="homeAddress.town"]', value == '1' || value == '2');
						}},
				        {text: '户籍地是否<br/>与居住地相同', type:'dict_radio', name: 'issameHome', code:'SF',  defValue:'2',
				        	allowBlank: false, required: true,valuechange:function(value){
				        		var container = $('#homeAddress-td .form-td-input');
				        		if (value == '1'){
				        			container.empty();
				        			container.append('<p>同居住地详细地址</p>');
				        		}
				        		else{
				        			if ($('input[name="homeAddress.id"]', container).length == 0){
				        				container.empty();
				        				$.fields.address(container, {name: 'homeAddress', required: true});
					        			$('input[name="homeAddress.id"]', container).trigger('valuechange');
				        			}
				        		}
				        	}},
				        
				        {text: '居住地详细地址', type:'address', colspan:3, name: 'houseAddress', required: true},
				        
				        {text: '户籍详细地址', id:'homeAddress-td', type:'address', colspan:3, name: 'homeAddress', required: true},
				        
				        {text: '文化程度', type:'dict_combobox', name: 'educationDegree', code:'WHCD', emptyText:'不详', required: true, allowSearch:true},
				        {text: '婚姻状况', type:'dict_combobox', name: 'maritalState', code:'HYZK', emptyText:'不详', required: true},
				        {text: '就业就学情况', type:'dict_combobox', name: 'workState', code:'JYJX', emptyText:'不详', required: true,
				        	valuechange:function(value){
				        		$('input[name="workNuit"]').setRequired(value == '1');
				        	}
				        },
				        
				        {text: '现工作单位', type:'text', colspan:2, name: 'workNuit', maxlength:100, blankMsg:'就业就学情况为“就业”时，请填写现工作单位。',defValue:'无'},
				        {text: '单位联系电话', type:'text', name: 'workNuitPhone', validater:'phone', maxlength:11},
				        
				        {text: '指纹采集', type:'fingerprint', colspan:3, name: ''}
				]
			},{
				type: 'grid',
				id: 'grid-resume-container', 
				title:'个人简历' ,
				add: function(){ me.addResume('#grid-resume-container');}, 
				delUrl:me.config.baseurl + 'deleteResumes.action'
			},{
				type: 'grid',
				id: 'grid-relations-container', 
				title:'家庭成员及主要社会关系' ,
				add: function(){ me.addRelations('#grid-relations-container');}, 
				delUrl: me.config.baseurl + 'deleteRelations.action'
			}]
		});
		me.addResumeGrid('grid-resume-container', me.config.fxryId);
		me.addRelationsGrid('grid-relations-container', me.config.fxryId);
	},
	view: function(){
		var me = this;
		$.container.generate(me.config.container, { 
			read: me.config.read + '?id=' + me.config.fxryId,
			afterRead:function(){
				//是否已采集指纹判断
				var fingerPrintNo = $('input[name="fingerPrintNo"]').attr("value");
				if(fingerPrintNo!=""){
					$.fields.initPic();
					//$("#collectPrint").attr("disabled",true);
				}
			},
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
					{type:'bdPhoto',name: 'picture', rowspan:4, readonly:true},
					
					{text: '姓名', type:'display',name: 'name'},
					{text: '曾用名', type:'display',name: 'userdName'},
					
					{text: '身份证号', type:'display',name: 'identityCard'},
					{text: '出生日期', type:'display',name: 'birthdate'},
					
					{text: '性别', type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
					{text: '是否成年', type:'display',name: 'isadult', formatter:$.dictionary.formatter('SF', '不详')},
					
					{text: '民族', type:'display',name: 'nation', formatter:$.dictionary.formatter('MZ', '不详')},
					{text: '到区县局报到时间', type:'display', name: 'reportTime'},
					{text: '指纹编号', type:'hidden',name: 'fingerPrintNo'},
					
					{text: '监管设备编号', type:'display',name: 'deviceCode', formatter: 'empty', emptyText:'未佩戴'},
					{text: '负责矫正单位', type:'display',name: 'responseOrg', formatter:$.fn.fmatter.organization},
					{text: '是否特管人员', type:'display',name: 'isTgry', formatter:$.dictionary.formatter('SF', '不详')},
			        
			        {text: '护照号码', type:'display', name: 'passport',defValue:'无'},
			        {text: '回乡证号码', type:'display', name: 'hrPermit',defValue:'无'},
			        {text: '香港身份证号码', type:'display', name: 'hkIdentity',defValue:'无'},
			        
			        {text: '澳门身份证号码', type:'display', name: 'amIdentity',defValue:'无'},
			        {text: '台胞证号码', type:'display', name: 'tbIdentity',defValue:'无'},
			        {text: '港澳台通行证号码', type:'display', name: 'gatPermit',defValue:'无'},
			        
			        {text: '健康状况', type:'display', name: 'health', formatter:$.dictionary.formatter('JKZK', '不详')},
			        {text: '具体健康状况', type:'display', name: 'healthSpecific'},
			        {text: '具体传染病史', type:'display', name: 'healthContagion', formatter:$.dictionary.formatter('CRB', '不详')},
			        {text: '是否有精神病', type:'display', name: 'psychosis', formatter:$.dictionary.formatter('SF', '是')},
			        {text: '心理是否健康', type:'display', name: 'healthMental', formatter:$.dictionary.formatter('SF', '是')},
			        {text: '具体心理健康状况', type:'display', name: 'healthMentalSpecific'},
			        {text: '鉴定机构', type:'display', name: 'accreditingOrgan'},
			        
			        {text: '原政治面貌', type:'display', name: 'politicsStatusOriginal', formatter:$.dictionary.formatter('ZZMM', '不详')},
			        {text: '政治面貌', type:'display', name: 'politicsStatus', formatter:$.dictionary.formatter('ZZMM', '不详')},
			        {text: '联系电话', type:'display', name: 'phoneNumber',maxlength:11},
			        
			        {text: '户籍性质', type:'display', name: 'natureHome', formatter:$.dictionary.formatter('HJXZ', '不详')},
			        {text: '户籍地是否<br/>与居住地相同', type:'display', name: 'issameHome', formatter:$.dictionary.formatter('SF', '是')},
			        
			        {text: '居住地详细地址', type:'display', colspan:3, name: 'houseAddress.fulltext'},
			        
			        {text: '户籍详细地址', type:'display', colspan:3, name: 'homeAddress.fulltext'},
			        
			        {text: '文化程度', type:'display', name: 'educationDegree', formatter:$.dictionary.formatter('WHCD', '不详')},
			        {text: '婚姻状况', type:'display', name: 'maritalState', formatter:$.dictionary.formatter('HYZK', '不详')},
			        {text: '就业就学情况', type:'display', name: 'workState', formatter:$.dictionary.formatter('JYJX', '不详')},
			        
			        {text: '现工作单位', type:'display', colspan:2, name: 'workNuit',defValue:'无'},
			        {text: '单位联系电话', type:'display', name: 'workNuitPhone',defValue:'无',maxlength:11},
			        
			        {text: '指纹采集', type:'fingerprint', colspan:3, name: '',readonly:true}
			],
			items:[{
				type: 'grid',
				id: 'grid-resume-container', 
				title:'个人简历' 
			},{
				type: 'grid',
				id: 'grid-relations-container', 
				title:'家庭成员及主要社会关系' 
			}]
		});
		me.addResumeGrid('grid-resume-container', me.config.fxryId, true);
		me.addRelationsGrid('grid-relations-container', me.config.fxryId, true);
	},
	addResumeGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listResumes.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'id',
				index:'id',
				label: 'id',
				hidden: true,
				align: 'center'
			},
			{
				name:'startTime',
				index:'startTime',
				label: '开始日期',
				width: 30,
				align: 'center'
			},
			{
				name:'endTime',
				index:'endTime',
				label: '结束日期',
				width: 30,
				align: 'center',
				formatter : function(value, opts, rwdat) {
					if(!value){
						return "至今";	
					}else{
						return value;
					}
		        }
			},
			{
				name:'workUnit',
				index:'workUnit',
				label: '所在单位',
				width: 70,
				align: 'center'
			},
			{
				name:'job',
				index:'job',
				label: '职务',
				width: 20,
				align: 'center'
			}
			],
			multiselect: !readonly,
			sortname: 'startTime',
			sortorder: "asc"
		});
	},
	resumeDateChange:function(value,elem){
		if(value){
			$('input[name="'+elem+'"]').val(value+"-01");
		}else{
			$('input[name="'+elem+'"]').val("");
		}
	},
	addResume: function(elem){
		var me = this;
		$.container.popup({
			title: '添加服刑人员个人简历',
			save: me.config.baseurl + 'saveResume.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{type:'hidden',name: 'startTime'},
					{type:'hidden',name: 'endTime'},
					{text: '开始日期', type:'yearmonthpicker',name: 'startTimeS', required: true,maxdate:{type:'id',data:'endTimeS'},
						valuechange:"$.fxrymoreinfo.resumeDateChange(this.value,'startTime');"},
					{text: '结束日期', type:'yearmonthpicker',name: 'endTimeS',mindate:{type:'id',data:'startTimeS'},maxdate:{type:'other',data:'$'},
							valuechange:"$.fxrymoreinfo.resumeDateChange(this.value,'endTime');"},
					{text: '职务', type:'text',name: 'job', maxlenght: 50,required: true,},
					{text: '所在单位', type:'text',name: 'workUnit', required: true, maxlenght: 100}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		});
	},
	addRelationsGrid: function (id, fxryId, readonly){
		var me = this;
		readonly = readonly || false;
		$('#' + id).jqGrid({
			url: me.config.baseurl + 'listRelations.action?fxryId=' + fxryId,
			colModel: [
			{
				name:'name',
				index:'name',
				label: '姓名',
				width: 40,
				align: 'center'
			},
			{
				name:'relation',
				index:'relation',
				label: '关系',
				width: 20,
				align: 'center'
			},
			{
				name:'address',
				index:'address',
				label: '工作单位或家庭住址',
				width: 70,
				align: 'center'
			},
			{
				name:'phoneNumber',
				index:'phoneNumber',
				label: '联系电话',
				width: 20,
				align: 'center'
				,maxlength:11
			}
			],
			multiselect: !readonly,
			sortname: 'createTime',
			sortorder: "asc"
		});
	},
	addRelations: function(elem){
		var me = this;
		$.container.popup({
			title: '添加服刑人员家庭成员及主要社会关系',
			save: me.config.baseurl + 'saveRelation.action',
			fieldCls:{
				labelCls : 'form-td-label-90'
			},
			fields:[           
					{type:'hidden',name: 'fxryId', value: me.config.fxryId},
					{text: '姓名', type:'text',name: 'name', required: true, validater:'name', maxlength:20},
					{text: '关系', type:'text',name: 'relation', required: true, validater:'name', maxlength:20},
					{text: '联系电话', type:'text',name: 'phoneNumber', required: true, validater:'phone', maxlength:11},
					{text: '所在单位或家庭住址', type:'text',name: 'address', required: true, maxlength:100}
			],
			yes: function(){
				$(elem).trigger("reloadGrid");
			}
		});
	}
});
})(jQuery);