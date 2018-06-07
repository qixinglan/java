(function ($) {
"use strict";
$.fields = $.fields || {};
$.fn.setValue = function(value){
	if ($(this).is('input') || $(this).is('textarea')){
		$(this).val(value);
	}
	else{
		$(this).text(value);
	}
};
$.fn.getValue = function(){
	if ($(this).is('input') || $(this).is('textarea')){
		return $(this).val();
	}
	else{
		return $(this).text();
	}
};
$.extend($.fields,{
	display: function(selector, options){
		options = $.extend({
			fieldClass: '',
			defValue: '',
			formatter: null,
			formatoptions: null,
			emptyText: '不详'
		}, options || {});
		var elem = $('<span class="display-field"/>');
		$(selector).append(elem);
		elem.addAttrAndCls(options);
		elem.attr('readonly', true);
		var fmt = options.formatter;
		if (fmt){
			elem.hide();
			var display = $('<span class="display-field"/>');
			display.addAttrAndCls(options);
			display.removeAttr('name');
			display.attr('readonly', true);
			$(selector).append(display);
			var fmtfn = $.isFunction(fmt) ? fmt : $.fn.fmatter[fmt];
			elem.bind('valuechange', function() {
				options.formatoptions = options.formatoptions || {};
				options.formatoptions.emptyText = options.emptyText;
				display.setValue(fmtfn(elem.getValue(), options.formatoptions));
			});
			//显示空值
			elem.trigger('valuechange');
		}
		if (options.defValue){
			elem.setValue(options.defValue || options.emptyText);
			if(fmt){
				elem.trigger("valuechange");
			}
		}
	},
	hidden : function(selector, options){
		options = $.extend({
			fieldClass: '',
			defValue: '',
			getValue: null
		}, options || {});
		var elem = $('<input type="hidden"/>');
		elem.addAttrAndCls(options);
		if(options.id){
			elem.attr('id',options.id);
		}
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		if (options.valuechange&&$.isFunction(options.valuechange)){
			elem.bind('valuechange', options.valuechange);
		}
		$(selector).append(elem);
	},
	datepicker: function(selector, options){
		options = $.extend({
			fieldClass: '',
			dateFmt: 'yyyy-MM-dd',
			allowBlank: true,
			defValue: '',
			attr: {},
			validater: 'date',
			expect: null,
			errorMsg:null,
			blankMsg:null,
			placeholder: null
		}, options || {});
		var elem = $('<input class="Wdate date-field" type="text"/>');
		elem.attr('maxlength', options.dateFmt.length);
		var initstr="WdatePicker({dateFmt:'" + options.dateFmt + "'";
		if(options.mindate||options.maxdate){
			if(!elem.attr('id')){
				elem.attr('id', options.name);
			}
			if(options.mindate){
				if(options.mindate.type=='id'){
					initstr+=",minDate:'#F{$dp.$D(\\\'"+options.mindate.data+"\\\')}'";
				}else{
					initstr+=",minDate:'"+options.mindate.data+"'";
				}
			}
			if(options.maxdate){
				if(options.maxdate.type=='id'){
					initstr+=",maxDate:'#F{$dp.$D(\\\'"+options.maxdate.data+"\\\')}'";
				}else{
					initstr+=",maxDate:'"+options.maxdate.data+"'";
				}
			}
		}
		initstr+="})";
		elem.attr('onClick', initstr);
		elem.addClass(options.validater);
		elem.attr('validater', options.validater);
		elem.data('expect', options.expect);
		elem.data('blankMsg', options.blankMsg);
		elem.data('errorMsg', options.errorMsg);
		elem.attr('placeholder', options.placeholder);
		elem.addAttrAndCls(options);
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		if(options.valuechange){
			elem.attr('onchange', options.valuechange);
		}
		$(selector).append(elem);
	},
	timepicker: function(selector, options){
		options = options || {};
		options.dateFmt = 'yyyy-MM-dd HH:mm';
		options.validater = 'datetime';
		$.fields.datepicker(selector, options);
	},
	yearmonthpicker: function(selector, options){
		options = options || {};
		options.dateFmt = 'yyyy-MM';
		options.validater = 'yearmonth';
		$.fields.datepicker(selector, options);
	},
	//支持字段验证validater 验证器,expect参数
	text : function(selector, options){
		options = $.extend({
			fieldClass: '',
			defValue: '',
			maxlength: 100,
			validater: null,
			expect: null,
			errorMsg:null,
			blankMsg: null,
			placeholder: null
		}, options || {});
		var elem = $('<input type="text"/>');
		elem.addAttrAndCls(options);
		elem.addClass(options.validater);
		elem.attr('validater', options.validater);
		elem.data('expect', options.expect);
		elem.data('errorMsg', options.errorMsg);
		elem.data('blankMsg', options.blankMsg);
		elem.attr('maxlength', options.maxlength);
		elem.attr('placeholder', options.placeholder);
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		if (options.valuechange){
			elem.attr('onchange', options.valuechange);
		}
		$(selector).append(elem);
	},
	textarea : function(selector, options){
		options = $.extend({
			fieldClass: '',
			defValue: '',
			readonly: false,
			maxlength: 1000,
			validater: null,
			expect: null,
			errorMsg: null,
			blankMsg: null,
			placeholder: null
		}, options || {});
		var elem = $('<textarea/>');
		elem.attr('readonly', options.readonly == true);
		elem.addClass(options.validater);
		elem.attr('validater', options.validater);
		elem.data('expect', options.expect);
		elem.data('errorMsg', options.errorMsg);
		elem.data('blankMsg', options.errorMsg);
		elem.attr('maxlength', options.maxlength);
		elem.attr('placeholder', options.placeholder);
		elem.addAttrAndCls(options);
		
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		$(selector).append(elem);
	},
	integer : function(selector, options){
		options = $.extend({
			fieldClass: '',
			defValue: '',
			placeholder: null,
			min:0,
			max:99999
		}, options || {});
		var elem = $('<input type="text"/>');
		elem.addAttrAndCls(options);
		elem.attr('placeholder', options.placeholder);
		elem.attr('maxlength', ('' + options.max).length);
		elem.data('max', options.max);
		elem.data('min', options.min);
		if (options.defValue){
			elem.val(options.defValue);
		}
		elem.data('oldvalue', options.defValue || '');
		$(selector).append(elem);
		elem.css('ime-mode', 'disabled');
		elem.bind('keypress', function(e){
			var code = e.keyCode ? e.keyCode : e.which;
			if (!$.browser.msie && (e.keyCode == 0x8)){
				return;
			}
			if (!$(this).val() && code == 45){
				return true;
			}
			var value = parseInt($(this).val());
			if (code == 48){
				return isNaN(value) || value > 0;
			}
			return code >= 49 && code <= 57;
		});
		elem.bind('input propertychange', function(e){
			var value = parseInt($(this).val());
			if (($(this).val() && $(this).val() != ('' + value)) 
					|| (value < $(this).data('min') 
					|| value > $(this).data('max'))){
				$(this).val($(this).data('oldvalue'))
			}
			else{
				$(this).data('oldvalue', isNaN(value) ? '' : value);
			}
		});
		elem.bind('dragenter', function(e){
			return false;
		});
		if ($.browser.msie){
			elem.bind('paste', function(e){
				var value = parseInt(clipboardData.getData('text'));
				if (!isNaN(value)){
					$(this).val(value);
					$(this).trigger('input');
				}
				return false;
			});
		}
	},
	number : function(selector, options){
		$.fields.text(selector, options)
	},
	dict_combobox : function(selector, options){
		options = options || {};
		options.attr = $.extend({
			required: options.required == true || false
		}, options.attr || {});
		$.dictionary.combobox(selector, options.name, options.code, options);
	},
	//不支持搜索
	dict_checkbox : function(selector, options){
		$.dictionary.checkbox(selector, options.name, options.code, options);
	},
	//不支持搜索
	dict_radio : function(selector, options){
		$.dictionary.radio(selector, options.name, options.code, options);
	},
	org_combobox : function(selector, options){
		$.organization.combobox(selector, options.name, options.orgId, options);
	},
	role_combobox : function(selector, options){
		$.roleDictionary.combobox(selector, options.name, options.code,options);
	},
	person_combobox : function(selector, options){
		options = options || {};
		options.attr = $.extend({
			required: options.required == true || false
		}, options.attr || {});
		$.person.personCbox(selector, options.name, options.orgId, options);
	},
	fxrys_combobox : function(selector, options){
		$.fxrys.fxrysCbox(selector, options.name, options.orgId, options);
	},
	equips: function(selector, options){
		$.equip.equipableDevice(selector, options.name, options);
	},
	select: function(selector, options){
		var id = options.name + '-field';
		var fieldSelector = $('<input type="hidden" name="' + options.name + '"/>');
		$(selector).append(fieldSelector);
		$(fieldSelector).addAttrAndCls(options);//设置占位控件的样式 夏先智
		$.initCodeComboCustom($(fieldSelector), options.ajax || options.data || {} 
		, options, options.ajax ? "remote" : "local");
	},
	photo: function(selector, options){
		options = $.extend({
			defValue: '',
			view: CONTEXT_PATH + '/data/photos/view.action',
			upload: CONTEXT_PATH + '/data/photos/upload.action',
			readonly: false,
			title: '照片',
			accept: 'image/*'
		}, options || {});
		var elem = $('<input type="hidden"/>');
		elem.addAttrAndCls({attr: options.attr || {}});
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		$(selector).append(elem);
		$(selector).css('text-align', 'center');
		var container = $('<div/>');
		$(selector).append(container);
		var image = $('<img name="head"/>');
		image.addClass('photo');
		container.append(image);
		elem.bind('valuechange', function() {
			var value = elem.val();
			var url = '';
			if (value){
				url =  options.view + '?id=' + value;
			}
			else{
				url = options.alt || (CONTEXT_PATH + '/images/head.jpg');
			}
			image.attr('src', url);
		});
		elem.attr('value', options.defValue);
		elem.trigger('valuechange', options.defValue);
		if (!options.readonly){
			//container.append('<div class="red" name ="getPhoto" style="margin:0 auto;position: relative;top:-20px;height:0px">获取头像</div>');
			$.fields.upload(image, {
				title: options.title,
				accept: options.accept,
			    mask: true,
			    filename: 'photo',
			    upload: options.upload,
			    yes: function(data){
			    	elem.val(data.msg).trigger('valuechange');
			    }
			});
			$("div[name='getPhoto']").click(function(){
				var fingerNo = $("input[name='fingerPrintNo']").val();
				fingerNo = fingerNo.substr(10)+"/"+fingerNo;
				$.ajax({
					type : "POST",
					async : true,
					url : options.url || CONTEXT_PATH + "/data/zwy/pickPrint.action?fingerPrintNo="+fingerNo,
					success : function(datas) {
						if(datas.success){
							var data = datas.msg.split(",");
							var src = "";
							for(var i=0;i<data.length-1;i++){
								if(data[i].substr(0,data[i].indexOf("."))=="11")
									src = CONTEXT_PATH+"/upload/fingerprint/"+fingerNo+"/11.jpeg";
								 else
									continue;
							}
							if(src.length<1){
								$.dialog.alert("请先上传头像，稍后再试！");
							}else{
								$("img[name='head']").attr("src",src);
								//$("div[name='getPhoto']").empty();
							}
							
						}else{
							$.dialog.alert("头像信息未获取，请稍后再试！");
						}
					}
				});
			});
		}
	},
	bdPhoto: function(selector, options){
		options = $.extend({
			defValue: '',
			view: CONTEXT_PATH + '/data/photos/view.action',
			upload: CONTEXT_PATH + '/data/photos/upload.action',
			readonly: false,
			title: '照片',
			accept: 'image/*'
		}, options || {});
		var elem = $('<input type="hidden"/>');
		elem.addAttrAndCls({attr: options.attr || {}});
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		$(selector).append(elem);
		$(selector).css('text-align', 'center');
		var container = $('<div/>');
		$(selector).append(container);
		var image = $('<img name="head"/>');
		image.addClass('photo');
		container.append(image);
		elem.bind('valuechange', function() {
			var value = elem.val();
			var url = '';
			if (value){
				var upFID=$("input[name='id']").val();
				url =  options.view + '?id=' + value+'&upFID='+upFID;
			}
			else{
				url = options.alt || (CONTEXT_PATH + '/images/head.jpg');
			}
			image.attr('src', url);
		});
		elem.attr('value', options.defValue);
		elem.trigger('valuechange', options.defValue);
		if (!options.readonly){
			//container.append('<div class="red" name ="getPhoto" style="margin:0 auto;position: relative;top:-20px;height:0px">获取头像</div>');
			$.fields.upload(image, {
				title: options.title,
				accept: options.accept,
			    mask: true,
			    filename: 'photo',
			    upload: options.upload,
			    data:function(){
			    	var no=$("input[name='fingerPrintNo']").val();
					var pic=$("input[name='picture']").val();
					var id=$("input[name='id']").val();
					if(!$("input[name='upFPN']",this.parent())[0]){
						this.parent().append("<input type='hidden' name ='upFPN'/>");
					}
					if(!$("input[name='upPIC']",this.parent())[0]){
						this.parent().append("<input type='hidden' name ='upPIC'/>");
					}
					if(!$("input[name='upFID']",this.parent())[0]){
						this.parent().append("<input type='hidden' name ='upFID'/>");
					}
					$("input[name='upFPN']",this.parent()).val(no);
					$("input[name='upPIC']",this.parent()).val(pic);
					$("input[name='upFID']",this.parent()).val(id);
					return null;
			    },
			    yes: function(data){
			    	var ids=data.msg.split(";");
			    	$("input[name='fingerPrintNo']").val(ids[0]);
			    	elem.val(ids[1]).trigger('valuechange');
			    }
			});
		}
	},
	pictureprint:function(selector, options){
		$(selector).css("text-align","center");
		var elem='<input type="button" class="bicon-preview" id="printIdcard" value="身份证采集"/>&nbsp&nbsp&nbsp&nbsp<input type="button" class="bicon-preview" id="printPicture" value="人像采集"/>';
		var div="<div style='display:none;'><OBJECT id='sfzhyCtl'  border='0' classid='clsid:3A21B528-549C-446A-A3D9-FD15F9EFEBAA'></OBJECT>" +
				"<OBJECT id='takePhotoCtl' border='0' classid='clsid:0703B4E1-987A-45CF-9E25-0A7A0B0447AC'></OBJECT></div>";
		$(selector).append(elem);
		$(selector).append(div);
		$("#printPicture").click(function(){
			var fpo =$("#takePhotoCtl");
			try{
				var pr = fpo[0].FormTag(1);
				if (pr == 1){
					if(fpo[0].P_Jpgphoto){
						var fxryId= $("input[name='fxryId']").val();
			        	if(fxryId==null || fxryId==''|| fxryId=='undefined'){
			        		fxryId= $("input[name='id']").val();
			        	}
						$.ajax({
							type : "POST",
							async : true,
							url : CONTEXT_PATH + "/data/zwy/collectFace.action",
							data : {
								fingerNo:$("input[name='fingerPrintNo']").val(),
								facePic:fpo[0].P_Jpgphoto,
								faceId:$("input[name='picture']").val(),
								fxryId:fxryId
							},
							success : function(datas) {
								$("input[name='fingerPrintNo']").val(datas[0]);
								$("input[name='picture']").val(datas[1]).trigger('valuechange');
							}
						});
					}
	            }
			}catch(e){
				$.dialog.alert("请到下载园地下载指纹控件，并使用兼容模式！");
			}
		});
		$("#printIdcard").click(function(){
			/*从以下属性获取身份证信息
		P_errormsg错误描述
		p_outxp  base64照片jpg
		P_outsfzhm 身份证号码
		P_outqsrq 有效期起始
		P_outjsrq 有效期截止
		P_outcsrq 出生日期
		P_outfzjg 发证机关
		P_outmz 民族
		P_outname 姓名
		P_outsex 性别
		P_outzz 住址
		P_outmzcode 民资代码
		P_outsexcode 性别代码*/
			var fpo =$("#sfzhyCtl")[0];
			try{
				var idCardInfo = fpo.sf_readxx();
				if(idCardInfo == '1'){
					var mzMap = {};
					mzMap['汉族']='1';
					mzMap[ '蒙古族']='2';
					mzMap['回族']='3';
					mzMap['藏族']='4';
					mzMap['维吾尔族']='5';
					mzMap['苗族']='6';
					mzMap['彝族']='7';
					mzMap['壮族']='8';
					mzMap['布依族']='9';
					mzMap['朝鲜族']='10';
					mzMap['满族']='11';
					mzMap['侗族']='12';
					mzMap['瑶族']='13';
					mzMap['白族']='14';
					mzMap['土家族']='15';
					mzMap['哈尼族']='16';
					mzMap['哈萨克族']='17';
					mzMap['傣族']='18';
					mzMap['黎族']='19';
					mzMap['傈僳族']='20';
					mzMap['佤族']='21';
					mzMap['畲族']='22';
					mzMap['高山族']='23';
					mzMap['拉祜族']='24';
					mzMap['水族']='25';
					mzMap['东乡族']='26';
					mzMap['纳西族']='27';
					mzMap['景颇族']='28';
					mzMap['柯尔克孜族']='29';
					mzMap['土族']='30';
					mzMap['达翰尔族']='31';
					mzMap['仫佬族']='32';
					mzMap['羌族']='33';
					mzMap['布朗族']='34';
					mzMap['撒拉族']='35';
					mzMap['毛南族']='36';
					mzMap['仡佬族']='37';
					mzMap['锡伯族']='38';
					mzMap['阿昌族']='39';
					mzMap['普米族']='40';
					mzMap['塔吉克族']='41';
					mzMap['怒族']='41';
					mzMap['乌孜别克族']='43';
					mzMap['俄罗斯族']='44';
					mzMap['鄂温克族']='45';
					mzMap['德昂族']='46';
					mzMap['保安族']='47';
					mzMap['裕固族']='48';
					mzMap['京族']='49';
					mzMap['塔塔尔族']='50';
					mzMap['独龙族']='51';
					mzMap['鄂伦春族']='52';
					mzMap['赫哲族']='53';
					mzMap['门巴族']='54';
					mzMap['珞巴族']='55';
					mzMap['基诺族']='56';
					mzMap['其它']='57';
					mzMap['外国人入籍']='57';
					var facePic = fpo.p_outxp;
					$("input[name='name']").val(fpo.P_outname);//姓名
					$("input[name='sex'][value="+fpo.P_outsexcode+"]").attr('checked', true);//性别
					$("input[name='identityCard']").val(fpo.P_outsfzhm);//身份证号
					
					var birthdate = fpo.P_outcsrq;
					birthdate=birthdate.substring(0,4)+'-'+birthdate.substring(4, 6)+'-'+birthdate.substring(6, 8);
					$("input[name='birthdate']").val(birthdate);//出生日期
					
					$("input[name='nation']").val(mzMap[fpo.P_outmz]).trigger('valuechange');//民族
					var fxryId= $("input[name='fxryId']").val();
					if(fxryId==null || fxryId==''|| fxryId=='undefined'){
						fxryId= $("input[name='id']").val();
					}
					$.ajax({
						type : "POST",
						async : true,
						url : CONTEXT_PATH + "/data/zwy/collectFace.action",
						data : {
							fingerNo:$("input[name='fingerPrintNo']").val(),
							facePic:facePic,
							faceId:$("input[name='picture']").val(),
							fxryId:fxryId
						},
						error: function(){
							$.dialog.alert("身份证采集出错！请刷新页面后再试！");
						},
						success : function(datas) {
							$("input[name='fingerPrintNo']").val(datas[0]);
							$("input[name='picture']").val(datas[1]).trigger('valuechange');
						}
					});
				}else{
					$.dialog.alert("请放置身份证件！");
				}
			}catch(e){
				$.dialog.alert("请到下载园地下载身份证采集控件，并使用兼容模式！");
			}
		});
	},
	fingerprint: function(selector, options){
		var elem1 = '<img class="fingerprint" name="right1" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg" />';
		var elem2 = '<img class="fingerprint" name="right2" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem3 = '<img class="fingerprint" name="right3" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem4 = '<img class="fingerprint" name="right4" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem5 = '<img class="fingerprint" name="right5" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem6 = '<img class="fingerprint" name="left1" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem7 = '<img class="fingerprint" name="left2" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem8 = '<img class="fingerprint" name="left3" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem9 = '<img class="fingerprint" name="left4" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var elem10 = '<img class="fingerprint" name="left5" src="'+CONTEXT_PATH+'/upload/fingerprint/weicaiji.jpg"  />';
		var b_caiji = '<span class="red"><input type="button" class="bicon-confirm" id="collectPrint" value="采集指纹"/></span>';
		//var b_tiqu = '<span class ="red"><input type="button" class="bicon-preview" onclick ="$.fields.initPic2()" value="提取指纹"/></span>';
		
		var table = "<table width='100%' style='text-align:center;'>" +"<tr><td colspan='5' style='border-right:1px solid #d9d9d9;'align='center'>左手</td><td  colspan='5' style='border-right:1px solid #d9d9d9 ' align='center'>右手</td></tr>"+
				"<tr style='border-top:1px solid #d9d9d9'><td>拇指</td><td>食指</td><td>中指</td><td>无名指</td><td style='border-right:1px solid #d9d9d9'>小指</td><td>拇指</td><td>食指</td>"
			+"<td>中指</td><td>无名指</td><td style='border-right:1px solid #d9d9d9'>小指</td>";
		if (!options.readonly){
			table += "<td width='150px'>"+b_caiji+"</td>";
			//table += "<td width='150px'>"+b_tiqu+"</td>";
		}
		table +="</tr>"
		+"<tr><td>"+elem6+"</td>"
		+"<td>"+elem7+"</td>"
		+"<td>"+elem8+"</td>"
		+"<td>"+elem9+"</td>"
		+"<td style='border-right:1px solid #d9d9d9'>"+elem10+"</td>"
		+"<td>"+elem1+"</td>"
		+"<td>"+elem2+"</td>"
		+"<td>"+elem3+"</td>"
		+"<td>"+elem4+"</td>"
		+"<td style='border-right:1px solid #d9d9d9'>"+elem5+"</td>";
		table += "</tr></table>";
		$(selector).append(table);
		//初始化指纹设备
		var div="<div style='display:none;'> <OBJECT id=fp_obj border=0 classid=clsid:3BE765B4-14B4-40A6-B207-39DEEBEA6634 width=800 height=250></OBJECT></div>";
		$(selector).append(div);
		//采集指纹
		$("#collectPrint").click(function(){
			var zwcj_cap = "5";
	        var zwcj_qam = "55";
	        var zwcj_score = "0.97";
	        var zwcj_verify = "0.68";
			var fp = $("#fp_obj");
			var fpo = fp[0];
			try{
		        fpo.AxBorderStyle = 0;
		        fpo.P_compresstype = 0;
		        fpo.P_capset = zwcj_cap;
		        fpo.P_qamset = zwcj_qam;
		        fpo.P_scoreset = zwcj_score;
		        fpo.P_verifyset = zwcj_verify;
		        fpo.Cap_init();
		        if (fpo.P_leftzcjg == 0 || fpo.P_rightzcjg == 0){
		        	$.dialog.alert("没有采集任何指纹！");
       				return; 
		        }else{
		        	var mask = $.dialog.loadmask("指纹正在上传中...");
		        	$.fields.initPic2(fpo);
		        	var fxryId= $("input[name='fxryId']").val();
		        	var fingerNo= $("input[name='fingerPrintNo']").val();
		        	if(fxryId==null || fxryId==''|| fxryId=='undefined'){
		        		fxryId= $("input[name='id']").val();
		        	}
		        	$.ajax({
		        		type: "POST",
		        	    async:true,
		        	    url: CONTEXT_PATH + "/data/zwy/collectPrint2.action",
//		        	    data: "fxryId="+fxryId+"&fingerNo="+fingerNo+"&left1="+encodeURIComponent(fpo.P_leftcompresszwm)+"&left2="+encodeURIComponent(fpo.P_leftcompresszws)+"&left3="+encodeURIComponent(fpo.P_leftcompresszwz)+"&left4="+encodeURIComponent(fpo.P_leftcompresszwh)+"&left5="+encodeURIComponent(fpo.P_leftcompresszwx)
//		        	    +"&right1="+encodeURIComponent(fpo.P_rightcompresszwm)+"&right2="+encodeURIComponent(fpo.P_rightcompresszws)+"&right3="+encodeURIComponent(fpo.P_rightcompresszwz)+"&right4="+encodeURIComponent(fpo.P_rightcompresszwh)+"&right5="+encodeURIComponent(fpo.P_rightcompresszwx)
//		        	    +"&mleft1="+encodeURIComponent(fpo.P_leftzwm)+"&mleft2="+encodeURIComponent(fpo.P_leftzws)+"&mleft3="+encodeURIComponent(+fpo.P_leftzwz)+"&mleft4="+encodeURIComponent(fpo.P_leftzwh)+"&mleft5="+encodeURIComponent(fpo.P_leftzwx)
//		        	    +"&mright1="+encodeURIComponent(fpo.P_rightzwm)+"&mright2="+encodeURIComponent(fpo.P_rightzws)+"&mright3="+encodeURIComponent(fpo.P_rightzwz)+"&mright4="+encodeURIComponent(fpo.P_rightzwh)+"&mright5="+encodeURIComponent(fpo.P_rightzwx),
		        	    data: "fxryId="+fxryId+"&fingerNo="+fingerNo+"&fingerFlag=left"+
        				"&left1="+encodeURIComponent(fpo.P_leftcompresszwm)+
        				"&left2="+encodeURIComponent(fpo.P_leftcompresszws)+
        				"&left3="+encodeURIComponent(fpo.P_leftcompresszwz)+
        				"&left4="+encodeURIComponent(fpo.P_leftcompresszwh)+
        				"&left5="+encodeURIComponent(fpo.P_leftcompresszwx)+
        				"&mleft1="+encodeURIComponent(fpo.P_leftzwm)+
        				"&mleft2="+encodeURIComponent(fpo.P_leftzws)+
        				"&mleft3="+encodeURIComponent(+fpo.P_leftzwz)+
        				"&mleft4="+encodeURIComponent(fpo.P_leftzwh)+
        				"&mleft5="+encodeURIComponent(fpo.P_leftzwx),
        				error: function(){
        					if (mask){
        						mask.close();
        					}
        					$.dialog.alert("指纹采集出错！请刷新页面后再试！");
        				},
		        		success:function(data){
		        			fingerNo = data;
		        			$.ajax({
		        				type: "POST",
		        				async:true,
		        				url: CONTEXT_PATH + "/data/zwy/collectPrint2.action",
		        				data: "fxryId="+fxryId+"&fingerNo="+fingerNo+"&fingerFlag=right"+
		        				"&right1="+encodeURIComponent(fpo.P_rightcompresszwm)+
		        				"&right2="+encodeURIComponent(fpo.P_rightcompresszws)+
		        				"&right3="+encodeURIComponent(fpo.P_rightcompresszwz)+
		        				"&right4="+encodeURIComponent(fpo.P_rightcompresszwh)+
		        				"&right5="+encodeURIComponent(fpo.P_rightcompresszwx)+
		        				"&mright1="+encodeURIComponent(fpo.P_rightzwm)+
		        				"&mright2="+encodeURIComponent(fpo.P_rightzws)+
		        				"&mright3="+encodeURIComponent(fpo.P_rightzwz)+
		        				"&mright4="+encodeURIComponent(fpo.P_rightzwh)+
		        				"&mright5="+encodeURIComponent(fpo.P_rightzwx),
		        				error: function(){
		        					if (mask){
		        						mask.close();
		        					}
		        					$.dialog.alert("指纹采集出错！请刷新页面后再试！");
		        				},
		        				success:function(data){
		        					$("input[name='fingerPrintNo']").val(data);
		        					$("span[name='fingerPrintNo_show']").empty().append(data);
		        					//$.fields.initPic();
		        					if(mask){
		        						mask.close();
		        					}
		        				}
		        			});
		        		}
		            });
		        	
		        }
			}catch(e){
				if(mask){
    				mask.close();
    			}
				$.dialog.alert("请到下载园地下载指纹控件，并使用兼容模式！");
			}
		});
	},
	getFingerPrint:function(options){
		$.ajax({
			type : "POST",
			async : false,
			url : CONTEXT_PATH + "/data/zwy/pickPrint.action",
			data : "fingerPrintNo="+$("input[name='fingerPrintNo']").val(),
			success : function(datas) {
				initPic();
			}
		});
	},
	initPic2:function(fpo){
		$("img[name='right1']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='right2']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='right3']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='right4']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='right5']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='left1']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='left2']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='left3']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='left4']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		$("img[name='left5']").attr("src","C:/WINDOWS/takehdfinger/f-nullzw.bmp");
		
		$("img[name='right1']").attr("src","C:/WINDOWS/takehdfinger/f-11zw.bmp");
		$("img[name='right2']").attr("src","C:/WINDOWS/takehdfinger/f-12zw.bmp");
		$("img[name='right3']").attr("src","C:/WINDOWS/takehdfinger/f-13zw.bmp");
		$("img[name='right4']").attr("src","C:/WINDOWS/takehdfinger/f-14zw.bmp");
		$("img[name='right5']").attr("src","C:/WINDOWS/takehdfinger/f-15zw.bmp");
		$("img[name='left1']").attr("src","C:/WINDOWS/takehdfinger/f-16zw.bmp");
		$("img[name='left2']").attr("src","C:/WINDOWS/takehdfinger/f-17zw.bmp");
		$("img[name='left3']").attr("src","C:/WINDOWS/takehdfinger/f-18zw.bmp");
		$("img[name='left4']").attr("src","C:/WINDOWS/takehdfinger/f-19zw.bmp");
		$("img[name='left5']").attr("src","C:/WINDOWS/takehdfinger/f-20zw.bmp");
	},
	initFilterImage:function(img,imagepath){
		img.style.filter='progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)';
		
        img.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imagepath;
        img.style.width='80px';
        img.style.width='100px';
	}
	,
	initPic:function(selector,options){
		var fingerNo = $("input[name='fingerPrintNo']").val();
		var doc = fingerNo.substring(0,6)+"/"+fingerNo.substring(6,12)+"/"+fingerNo.substring(6,14)+"/"+fingerNo;
		$("span[name='fingerPrintNo_show']").empty().append(fingerNo);
		$.ajax({
			type : "POST",
			async : true,
			url :  CONTEXT_PATH + "/data/zwy/pickPrint.action?fingerPrintNo="+$("input[name='fingerPrintNo']").val(),
			success : function(datas) {
				var exitPic=datas.msg;				
				(exitPic.indexOf("6")>-1)? 
					$("img[name='right1']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/6.jpeg"):	
				    $("img[name='right1']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("7")>-1)? 
					$("img[name='right2']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/7.jpeg"):	
				    $("img[name='right2']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("8")>-1)? 
					$("img[name='right3']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/8.jpeg"):	
				    $("img[name='right3']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("9")>-1)? 
					$("img[name='right4']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/9.jpeg"):	
				    $("img[name='right4']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("0")>-1)? 
					$("img[name='right5']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/10.jpeg"):	
				    $("img[name='right5']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
					
				(exitPic.indexOf("1")>-1)? 
					$("img[name='left1']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/1.jpeg"):
					$("img[name='left1']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("2")>-1)? 
					$("img[name='left2']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/2.jpeg"):
					$("img[name='left2']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("3")>-1)? 
					$("img[name='left3']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/3.jpeg"):
					$("img[name='left3']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("4")>-1)? 
					$("img[name='left4']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/4.jpeg"):
					$("img[name='left4']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
				(exitPic.indexOf("5")>-1)? 
					$("img[name='left5']").attr("src",CONTEXT_PATH+"/upload/fingerprint/"+doc+"/5.jpeg"):
					$("img[name='left5']").attr("src",CONTEXT_PATH+"/upload/fingerprint/weicaiji.jpg");
			}
		});
	},
	//title提示标题,selected选择文件后回调,false不上传,upload,上传URL，yes上传成功回调
	//data附加的数据，为对象或函数,accept,接受的文件类型部分浏览器不兼容
	upload: function(selector, options){
		options = options || {};
		var wrapped = $(selector);
		wrapped.bind('mouseover', function(){
			var formId = $.fields.random('uploadForm');
			var form = $('<form ENCTYPE="multipart/form-data"></form>');
			form.attr('id', formId);
			var div = $('<div class="ajax-upload-container"/>');
			div.hide();
			$('body').append(div);
			div.append(form);
			var upload = $('<input name="' + options.filename + '" type="file">');
			upload.attr('title', options.title);
			upload.attr('accept', options.accept || '');
			upload.addClass('ajax-upload-input');
			upload.css('height', '100%');
			form.append(upload);
			upload.change(function(){
				var value = $(this).val();
				if (value){
					if ($.isFunction(options.selected) && options.selected(value) != true){
						return;
					}
					var data = options.data || {};
					if ($.isFunction(data)){
						data = data.call($(this));
					};
					$.fields.checkForm(form,{
						prefix: false,
						async: true,
						clearForm: true,
						yes: options.yes,
						mask: false,
						title: options.title,
						data: data,
						uploadProgress: options.uploadProgress,
						url: options.upload
					});
				}
			})
			div.css({
				height: wrapped.outerHeight(),
				width: wrapped.outerWidth(),
				'top': wrapped.offset().top,
				'left': wrapped.offset().left
			});
			div.show();
			div.bind('mouseleave', function(){
				div.hide();
			});
		});
	},
	random: function(prefix){
		return prefix + '_' + (new Date()).valueOf() + '_' + Math.floor(Math.random() * 1000000000);
	},
	//options.title 提示标题
	//options.validate 提交前自定义验证函数
	//options.yes 保存或后台验证成功的回调函数,yes为字符串时，默认为跳转页面直接跳转
	//options.mask 是否使用遮罩层防止用户修改或重复点击
	checkForm : function(formSelector, options){
		var title = (options.prefix == false ? '' : '保存') + (options.title || '');
		var mask = null;
		if (!$.fields.validateForm(formSelector)){
			return false;
		}
		//用户自定义校验
		if (options.validate){
			if (!options.validate.call(this, formSelector,options)){
				return false;
			}
		}
		if (options.mask){
			mask = $.dialog.loadmask(title);
		}
		var returnVal = false;
		var options = $.extend({    //提交后的回调函数
			   type: 'POST',               //默认是form的method（get or post），如果申明，则会覆盖
			   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型
			   clearForm: false,          //成功提交后，清除所有表单元素的值
			   resetForm: false,          //成功提交后，重置所有表单元素的值
			   async: options.async == true,//async为true时，返回值无效，默认为false
			   timeout: 60000,               //限制请求的时间，当请求大于300秒后，跳出请求
			   success: function(response){
				   if (mask){
					   mask.close();
				   }
				   if (!$.isPlainObject(response)){
					   response = $.parseJSON(response);
				   }
				   if (response && response.success){
					   if (options.yes){
						   if ($.isFunction(options.yes)){
							   options.yes.call(this, response);
						   }
						   else{
							   window.location = options.yes;
						   }
					   }
					   returnVal = true;
					   return;
				   }
				   $.dialog.error(title + "出错！错误原因:" + (response ? response.msg : '未知'));
			   },
			   error: function(){
				   if (mask){
					   mask.close();
				   }
				   if ($.isFunction(options.no)){
					   options.yes.call(this, response);
				   }
				   $.dialog.error(title + "出错！请稍候再试");
			   }
			}, options || {});
		$(formSelector).ajaxSubmit(options);
		return returnVal;
	},
	validateForm: function(formSelector){
		//表单验证只检查text和textarea字段,下拉列表或radio已经不需要验证
		//错误提示优先级:errorMsg->验证器返回错误消息->placeholder
		var editable = function(elem){
			return elem.attr('name') && !elem.attr('readonly') && !elem.attr('disabled');
		};
		
		var inputs = $('[name]', $(formSelector));
		for (var i = 0; i < inputs.length; ++i){
			var $elem = $(inputs[i]);
			if (editable($elem)){
				var getValue = $elem.data('getValue');
				if ($.isFunction(getValue)){
					$elem.val(getValue.apply($elem));
				}
				if ($elem.is('[type="text"]') || $elem.is('textarea')){
					$elem.val($.trim($elem.val()));
				}
				if ($elem.is('[required]') && !$elem.val()){
					$elem.focusError($elem.data('blankMsg') || (($elem.attr('label') || '') + '不能为空'));
					return false; 
				}
				if ($elem.val() && $elem.is('[validater]')){
					var validater = $elem.attr('validater');
					var expect = $elem.data('expect');
					if (!$.validater[validater]){
						$elem.focusError('验证器不存在,此错误为开发人员错误!');
						return false;
					}
					var msg = $.validater[validater](expect, $elem.val());
					if (msg){
						var errorMsg = $elem.data('errorMsg') || msg;
						$elem.focusError(errorMsg);
						return false;
					}
				}
			}
		}
		return true;
	},
	ajaxGet: function(options){
		var obj = null;
		options = options || {};
		options = $.extend({
			   type: 'GET',
			   async: false,
			   dataType: 'json',
			   cache: false,
			   success: function(data){
				  obj = data;
			   }
		}, options);
		$.ajax(options);
		return obj;
	},
	ajaxRequest: function(options){
		var title = options.title || '处理中';
		var mask = '';
		if (options.mask != false){
			mask = $.dialog.loadmask(title + '...');
		}
		options = options || {};
		options = $.extend({
			   type: 'GET',
			   dataType: 'json',
			   cache: false,
			   async: true,
			   success: function(data){
				   if (options.mask != false){ mask.close();}
				   if (data){
					   if (data.success && options.yes){
						   if ($.isFunction(options.yes)){
							   options.yes.call(this, data);
						   }
						   else{
							   window.location = options.yes;
						   }
					   }
					   else{
						   $.dialog.error('失败:' + data.msg);
					   }
				   }
				   else{
					   $.dialog.error('操作失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   if (options.mask != false){ mask.close();}
				   $.dialog.error('操作失败！请稍候再试 .');
			   }
		}, options);
		$.ajax(options);
	},
	//初始化表单数据,formId表单ID,title为出错信息标题
	//其它参数为$.ajax的参数
	ajaxLoadFormData: function(options){
		options = options || {};
		options = $.extend({
			   type: 'GET',
			   async:true,
			   url: '',
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   if (data){
					   $.fields.loadFormData(options.formSelector, data, {clear: false, ignore: true, callback:options.callback});
				   }
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   }
		}, options);
		$.ajax(options);
	},
	loadFormData : function(form, data, options){
		options = $.extend({
			clear: true,//加载前，清空表单
			ignore: false//忽略data中的null
		}, options || {});
		var inputs = $('[name]', $(form));
		if (options.clear){
			$(form).clearForm();
		}
		$(form).data('model', data);
		$.each(inputs, function(i, elem){
			var name = $(elem).attr('name');
			if (name){
				var value = '';
				var names = name.split('.');
				
				if (names[1]){
					if (data[names[0]]){
						var model = data[names[0]];
						value = model[names[1]];
					}
				}
				else{
					value = data[name];
					var model = data;
				}
				if (value != null || !options.ignore){
					var type = $(elem)[0].type;
					if ('radio' == type){
						$(elem).attr('checked', value == $(elem).val());
					}
					else if ('checkbox' == type){
						var pieces = value.split(',');
						for (var i in pieces){
							if ($(elem).val() == pieces[i]){
								$(elem).attr('checked', true);
								break;
							}
						}
					}
					else{
						$(elem).setValue(value);
					}
					$(elem).trigger('valuechange', [value, model]);
				}
			}
		});
		if (options.callback){
			options.callback.call(this, data);
		}
	},
	file : function(container,options){
		var options = $.extend({
			name:"file",
			fileset : false,
			title: '文件上传',
			accept : false,
			multiple : false,
			info: CONTEXT_PATH + '/data/common/fileinfo.action',
			download: CONTEXT_PATH + '/data/common/download.action',
			url: CONTEXT_PATH + '/data/common/upload.action',
			del: CONTEXT_PATH + '/data/common/delFile.action',
			delClass : 'upload-display-delete',
			addVal : false,
			procBarClass : 'upload-display-processbar',
			fileNameclass : 'upload-display-filename',
			yes:false,				//上传成功事件
			no:false,				//上传失败事件
			spath:'anon'
		}, options||{});
		container = $(container);
		//值存储
		var elem = $('<input type="hidden"/>');
		elem.addAttrAndCls({attr: options.attr || {}});
		if (options.defValue){
			elem.attr('value', options.defValue);
		}
		container.append(elem);
		var filenames = $('<div/>');
		container.append(filenames);
		var editDel = options.del ? $('<a>删除</a>') : $('<a></a>');
		editDel.addClass(options.delClass || '');
		editDel.hide();
		if(options.editable){
			editDel.bind("click", function(){
				$.fields.ajaxRequest({
					url: options.del,
					data : {spath: options.spath, id:elem.val()},
					yes: function(){
							filenames.hide();
							addDiv.show();
							elem.val('');
					}
				});
				
			});
		}
		
		elem.bind('valuechange', function() {
			var id = elem.val();
			if (id){
				var info = $.fields.ajaxGet({
					url: options.info,
					data : {spath: options.spath, id:id}
				});
				if (info.success){
					filenames.empty();
					filenames.append('<a href="' + options.download 
							+ '?spath=' + options.spath + '&id=' + id + '">' + info.msg + '</a>');
					if(options.editable){
						filenames.append(editDel);
						editDel.show();
						addDiv.hide();
					}
				}
			}
		});
		elem.attr('value', options.defValue);
		elem.trigger('valuechange', options.defValue);
		if (options.readonly){
			return;
		}
		var add = $('<a href="javascript:void(0);"></a>').text(options.addVal|| '添加文件');
		var addDiv =$('<div/>');
		addDiv.append(add);
		container.append(addDiv);
    	var uploadinfo = $('<div style="clear:both"/>');
    	var fileName = $('<span></span>');
    	fileName.addClass(options.fileNameClass || '');
    	var procBar = $('<span></span>');
    	procBar.addClass(options.procBarClass || '');
		var del = options.del ? $('<a>删除</a>') : $('<a></a>');
		del.addClass(options.delClass || '');
		del.hide();
		uploadinfo.append(fileName).append(procBar).append(del);
		container.append(uploadinfo);
		uploadinfo.hide();
		del.bind("click", function(){
			$.fields.ajaxRequest({
				url: options.del,
				data : {spath: options.spath, id:elem.val()},
				yes: function(){
					uploadinfo.hide();
					addDiv.show();
					elem.val('');
				}
			});
			
		});
		$.fields.upload(add, {
			title: options.title,
			accept: options.accept,
		    mask: true,
		    filename: 'file',
		    upload: options.url,
		    data : {spath: options.spath,limitSize: options.limitSize||''},
		    selected: function(value){
		    	var filetype = value.substr(value.lastIndexOf(".")+1);
		    	if(options.accepted!=null&&options.accepted.indexOf(filetype)<0){
					$.dialog.alert("只能上传以"+options.accepted+"结尾的文件");
					return false;
				}
		    	addDiv.hide();
		    	del.hide();
		    	uploadinfo.show();
		    	fileName.text(value.substring(value.lastIndexOf("\\")+1));
		    	return true;
		    },
		    uploadProgress:function(event, position, total, percentComplete){
				procBar.text(percentComplete + "%");
			},
			yes:function(data){
				procBar.text("");
				del.show();
				if (data.success){
					data.msg = $.parseJSON(data.msg);
					elem.val(data.msg[0]);
				} 
				else{
					procBar.text("失败");
				}
			},
			error:function(){
				procBar.text("失败");
			}
		});
	}
});
})(jQuery);