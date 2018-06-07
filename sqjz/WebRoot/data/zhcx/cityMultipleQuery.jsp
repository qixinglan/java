﻿<%@page import="com.lowagie2.text.Document"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合查询</title>
<%@ include file="/common/commonjs.jsp"%>
<!-- <link rel="stylesheet" href="popup.css" /> -->
<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">

 function bindSearchForm2(grid,form, searchBtn, resetBtn){
	var grid = $(grid);
	grid.searchFormFields = [];
	$.each($('input', $(form)), function(i, n){
		var item = $(n);
		if (item.attr('name') && item.attr('searchType')&& 'true' != item.attr('nosearch')){
			grid.searchFormFields.push({
				elem: item,
				op: item.attr('searchType'),
				name: item.attr('name')
			});
		}
	});
	if (searchBtn){
		$(searchBtn).click(function(){
			var ruleGroup = "{\"groupOp\":\"AND\",\"rules\":[";
			var gi=0;
			var org = $("input[name='responseOrgqxj']").val();
			var orgsfs = $("input[name='testOrgsfs']").val();
			if(orgsfs!=null&&orgsfs!=""&&orgsfs!="undefined"){
				org = orgsfs;
			}
			$.each(grid.searchFormFields, function(i, item){
				var value =  item.elem.val();
				if (value){
					if (gi > 0) {
						ruleGroup += ",";
					}
					if(item.name=="responseOrgqxj"||item.name=="testOrgsfs"){
						ruleGroup += "{\"field\":\"responseOrg\",";
						ruleGroup += "\"op\":\"" + item.op + "\",";
						ruleGroup += "\"data\":\"" + value.replace(/\\/g,'\\\\').replace(/\"/g,'\\"') + "\"}";
					}else if(item.name=="state"&&value=="1"){
						ruleGroup += "{\"field\":\"" + item.name + "\",";
						ruleGroup += "\"op\":\"ne\",";
						ruleGroup += "\"data\":\"4\"}";
						ruleGroup += ",{\"field\":\"" + item.name + "\",";
						ruleGroup += "\"op\":\"ne\",";
						ruleGroup += "\"data\":\"7\"}";
					}else{
						ruleGroup += "{\"field\":\"" + item.name + "\",";
						ruleGroup += "\"op\":\"" + item.op + "\",";
						ruleGroup += "\"data\":\"" + value.replace(/\\/g,'\\\\').replace(/\"/g,'\\"') + "\"}";
						
					}
					gi++;
				}
			});
			var isws=${user.isws};
			if(isws!="1"&&isws!="3"){
				ruleGroup += ",{\"field\":\"istgry\",";
				ruleGroup += "\"op\":\"ne\",";
				ruleGroup += "\"data\":\"1\"}";
			}
			ruleGroup += "]}";
			var bo = ruleGroup.indexOf("\"field\":\"alarm");
			var seachurl = '${ctx}/data/zhcxAll/unAlarmSearch.action?orgId=${user.wunit.bm}';
			$("#isAlarm").val(0);
			if(bo>0){
				seachurl = '${ctx}/data/zhcxAll/search.action?orgId=${user.wunit.bm}';
				$("#isAlarm").val(1);
			}
			if(resetBtn=="#bbb"){
				seachurl = '${ctx}/data/zhcxAll/searchTongjiUnAlarm.action?orgId=${user.wunit.bm}';
				$("#isAlarm").val(0);
				if(bo>0){
					seachurl = '${ctx}/data/zhcxAll/searchTongji.action?orgId=${user.wunit.bm}';
					$("#isAlarm").val(1);
				}
			}
			grid.setGridParam({url:seachurl});
			grid.getGridParam('postData').filters = ruleGroup;
			grid.trigger('reloadGrid',[{page:1}]);
		});
	}
	if (resetBtn){
		$(resetBtn).click(function(){
			$.each($(form + ' input'), function(i, n){
				var item = $(n);
				if (item.attr('name') && item.attr('searchType') && 'true' != item.attr('noreset')){
					item.val('').trigger('valuechange');
				}
			});
			//所有下拉列表-已废弃
			//$("a.clear_triggerIcon", $(form)).click();
			grid.getGridParam('postData').filters = '';
			if (searchBtn){
				$(searchBtn).click();
			}
			else{
				grid.trigger('reloadGrid',[{page:1}]);
			}
		});
	}
} 
//统计导出
	function openExcelTj(){
		var data=$("#mainGrid1").jqGrid('getRowData');
		var rowData=[];
		for(var p in data){
			rowData.push([data[p].responseOrg,data[p].code]);
		}
		var url = "${ctx }/data/zhcxAll/exportTongJiExcel.action?rowData="+rowData;
		$("#downloadFrame").attr("src",url);
	}
			function searchByFilters(){
				var form = "#searchDiv";
				var grid = $("#mainGrid");
				grid.searchFormFields = [];
				$.each($('input', $(form)), function(i, n){
					var item = $(n);
					if (item.attr('name') && item.attr('searchType')&& 'true' != item.attr('nosearch')){
						grid.searchFormFields.push({
							elem: item,
							op: item.attr('searchType'),
							name: item.attr('name')
						});
					}
				});
				var ruleGroup = "{\"groupOp\":\"AND\",\"rules\":[";
				var gi=0;
				$.each(grid.searchFormFields, function(i, item){
					var value =  item.elem.val();
					if (value){
						if (gi > 0) {
							ruleGroup += ",";
						}
						if(item.name=="responseOrgqxj"||item.name=="testOrgsfs"||item.name=="responseOrgsfs"){
							ruleGroup += "{\"field\":\"responseOrg\",";
							ruleGroup += "\"op\":\"" + item.op + "\",";
							ruleGroup += "\"data\":\"" + value.replace(/\\/g,'\\\\').replace(/\"/g,'\\"') + "\"}";
						}else{
							ruleGroup += "{\"field\":\"" + item.name + "\",";
							ruleGroup += "\"op\":\"" + item.op + "\",";
							ruleGroup += "\"data\":\"" + value.replace(/\\/g,'\\\\').replace(/\"/g,'\\"') + "\"}";
							
						}
						gi++;
					}
				});
				var isws=${user.isws};
				if(isws!="1"&&isws!="3"){
					ruleGroup += ",{\"field\":\"istgry\",";
					ruleGroup += "\"op\":\"ne\",";
					ruleGroup += "\"data\":\"1\"}";
				}
				ruleGroup += "]}";
				var filters = ruleGroup;
				var bo = ruleGroup.indexOf("\"field\":\"alarm");
				var isunAlarm = "${ctx}/data/zhcxAll/searchTongjiByFiltersUnAlarm.action"
				if(bo>0){
					isunAlarm = "${ctx}/data/zhcxAll/searchTongjiByFilters.action";
				}
				var options = {
						type: 'POST',
						title:'统计数据',
						async:true,
						url: isunAlarm,
						data : "filters="+filters,
						cache: false,
						dataType: 'json',
						success: function(data, success, http){
							if (data){
								obj = [];
								if(data.length>0){
									for(var i in data){
										var name =data[i][1];
										var temp={
											name: name,
											value:data[i][0]
										};
										obj.push(temp);
									}
								}else{
									obj=[{}];
								}
								fusioncharts({type: 'pie3d',renderAt:'sjtjt',title:"查询统计结果饼状图",data:obj});
							}else{
								$.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
							}
						},
						error: function(){
							$.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
						},
						complete:function(){
								  
						}
					};
					$.ajax(options);
			}
			//选择定制条件
			function changeDztj(){
				//查询--Grid显示选定的查询列
				$.each($("#cxtjjg input"),function(i,n){
					var tt = $(n).attr('id');
					tt =tt.substring(0,tt.length-1);
					if($(n).attr("checked")){
						$("#mainGrid").setGridParam().showCol(tt);
					}else{
						$("#mainGrid").setGridParam().hideCol(tt);
					}
				});
				//$("#mainGrid").trigger("reloadGrid");
				$("#tou").attr("style","display: none;");
				$("#lb").attr("style","display: block;");
				
			}
			
			//重置定制条件
			/* function resetDztj(){
				//重置--表格重置显示前三列，定制条件选定前三个
				$("#chkAll").removeAttr("checked");
				$.each($("#cxtjjg input"),function(i,n){
					if(i<5){
						$(n).attr("checked","true");
						$("#mainGrid").setGridParam().showCol($(n).attr("id"));
					}else{
						$(n).removeAttr("checked");
					}
				});
			} */
			function selectValueChange(id,name,value,elem,type ){
				if(elem.choice !=null && elem.choice !='undefined' && value!=null && value !='undefined'&& value!=""){
					if(type=="org"){
						if(value!= null && value !='undefined' && value != ''){
							$("#responseOrgSpan").text(name+"="+elem.choice.text+";");
							$("#sfsOrg").children().remove();
							$("#aaa").val(value);
							$("#bbb").val(value);
							
							$.organization.combobox("#sfsOrg", "responseOrgsfs",value, {
						    	allowBlank : true,
								level : 10,
								showRoot : true,
								showItself : true,
								//ShowType:"3",
								notShowType:"4,5,6,7,8,9",
								emptyText : '全部',
								//defValue:${user.wunit.bm},
								dropdownAutoWidth:false,
								multiSelected: false,
								width:'80%',
								attr:{searchType:"eq"},valuechange:function(value,elem){
									selectOrgValueChange("responseOrgSpan","矫正单位",value,elem,"org");
								}});
						}
					}else{
					var dict = $.dictionary.getDict(type,true);
					var valueaa
					for (var j in dict){
						var item = dict[j];
						if(item.code == value){
							valueaa =item.name;
							break;
						}
					}
					var a = $("#"+id).text();
					var aa = a.split("=");
					if(aa[1]=="是;"||aa[1]=="否;"||aa[1]==";"||aa[1]=="男;"||aa[1]=="女;"){
						a="";
					}
					if(a==null || a =='undefined' || a==''){
						var namevalue = name+"="+valueaa+";";
						$("#"+id).text(namevalue);
					}else{
						a = a.replace(name+"=",'').replace(";",'');
						var namevalue = name+"="+a+","+valueaa+";";
						$("#"+id).text(namevalue);
					}
					}
				}else if(elem.removed !=null && elem.removed !='undefined' ){
					var d = elem.removed.name;
					var a = $("#"+id).text();
					var intd = a.indexOf(d) ;
					var e = a.substring(intd-1,intd) ;
					var inr = a.split(d);
					var r = inr[1].substring(0,1) ;
					if(e=="="){
						if(r==","){
							a = a.replace(d+',','');
							$("#"+id).text(a);
						}else{
							a = a.replace(d,'');
							$("#"+id).text("");
						}
					}else if(e==""||e=="是"){
						$("#"+id).text("");
					}else{
						a = a.replace(","+d,'');
						$("#"+id).text(a);
					}
				}else{
					if(type=="date"){
						var dateSpanNew = $("#"+elem+"Span").text();
						var datevalue3="";
						var datevalue4="";
						if(dateSpanNew != null && dateSpanNew !='undefined' && dateSpanNew != ''){
							if(id==elem+"Span1"){
								var datevalue1 = value;
								var num1 = dateSpanNew.indexOf("至");
								var num2 = dateSpanNew.indexOf(";");
								var datevalue2 = dateSpanNew.substring(num1+1,num2);
								datevalue3 = value;
								datevalue4 = dateSpanNew.substring(num1+1,num2);
							}
							if(id==elem+"Span2"){
								var datevalue2 = value;
								var num1 = dateSpanNew.indexOf("=");
								var num2 = dateSpanNew.indexOf("至");
								var datevalue1 = dateSpanNew.substring(num1+1,num2);
								datevalue3 = dateSpanNew.substring(num1+1,num2);
								datevalue4 = value;
							}
						}else{
							var datevalue1="";
							var datevalue2="";
							if(id==elem+"Span1"){
								datevalue1 = value;
								datevalue3 = value;
							}
							if(id==elem+"Span2"){
								datevalue2 = value;
								datevalue4 = value;
							}
						}
						if(datevalue1==""&&datevalue2==""){
							$("#"+elem+"Span").text("");
						}else{
							$("#"+elem+"Span").text(name+"="+datevalue1+"至"+datevalue2+";");
							
						}
						var birthdate = "";
						if(elem=="birthdate"){
							 var url = "${ctx}/data/zhcxAll/contrastDate.action?birthdate1="+datevalue3+"&birthdate2="+datevalue4;
							$.ajax({
					            type: "GET",
					            url:  url,
					            async: false,
					            dataType: "json",
					            success: function(data){
					            	var nl1= data.nl1;
					            	var nl2= data.nl2;
					            	if(nl1!=null && nl1!=""){
					            		if(nl1<0){
					            			nl1=0;
					            		}
					            		$("#nlfw2").val(nl1);
					            	}else{
					            		$("#nlfw2").val("");
					            	}
					            	if(nl2!=null && nl2!=""){
					            		if(nl2<0){
					            			nl2=0;
					            		}
					            		$("#nlfw1").val(nl2);
					            	}else{
					            		$("#nlfw1").val("");
					            	}
					            }
					        }); 
							
						}
					}
					if(type=="text"){
						if(value==""){
							$("#"+id).text("");
						}else{
							$("#"+id).text(name+"="+value+";");
						}
					}
					if(type=="nlfw"){
						var datevalue1="";
						var datevalue2="";
						var dateSpanNew = $("#birthdateSpan").text();
						if(dateSpanNew != null && dateSpanNew !='undefined' && dateSpanNew != ''){
							if(id==elem+"Span1"){
								var date=new Date().format("yyyy-MM-dd");
								var year=date.substring(0,4);
								var yearold=year-value;
								datevalue2 = yearold+date.substring(4,date.length);
								var num1 = dateSpanNew.indexOf("=");
								var num2 = dateSpanNew.indexOf("至");
								datevalue1 = dateSpanNew.substring(num1+1,num2);
							}
							if(id==elem+"Span2"){
								var date=new Date().format("yyyy-MM-dd");
								var year=date.substring(0,4);
								var yearold=year-value;
								datevalue1 = yearold+date.substring(4,date.length);
								var num1 = dateSpanNew.indexOf("至");
								var num2 = dateSpanNew.indexOf(";");
								datevalue2 = dateSpanNew.substring(num1+1,num2);
							}
						}else{
							if(id==elem+"Span1"){
								var date=new Date().format("yyyy-MM-dd");
								var year=date.substring(0,4);
								var yearold=year-value;
								datevalue2 = yearold+date.substring(4,date.length);
								
							}
							if(id==elem+"Span2"){
								var date=new Date().format("yyyy-MM-dd");
								var year=date.substring(0,4);
								var yearold=year-value;
								datevalue1 = yearold+date.substring(4,date.length);
							}
						}
						$("#birthdateSpan").text(name+"="+datevalue1+"至"+datevalue2+";");
						$("#birthdate1").val(datevalue1.trim());
						$("#birthdate2").val(datevalue2.trim());
					}
				}
			}
			
			function selectOrgValueChange(id,name,value,elem,type ){
				if(elem.choice !=null && elem.choice !='undefined' && value!=null && value !='undefined'&& value!=""){
					if(type=="org"){
						if(value!= null && value !='undefined' && value != ''){
							$("#responseOrgSpan").text(name+"="+elem.choice.text+";");
							$("#testOrgsfs").val(value);
						}
					}
				}
			}
			
			
			$(function() {
				var spanlist = ["responseOrgSpan","stateSpan","nameSpan","sexSpan","nationSpan","identityCardSpan","birthdateSpan","isadultSpan","politicsStatusSpan","politicsStatusOriginalSpan","educationDegreeSpan",
				                "workStateSpan","maritalStateSpan","natureHomeSpan","iscapitalSpan","healthSpan","healthMentalSpan","psychosisSpan","healthContagionSpan",
				                "adjustTypeSpan","dateSAdjustSpan","dateEAdjustSpan","crimeTypeSpan","accusationSpan","oriPunishmentSpan","oriPeriodSpan","addpunishmentSpan","nonpoliticalPeriodSpan","sisTypeSpan",
				                "sansTypeSpan","isRecidivismSpan","isaloneSpan","isforbiddenSpan","dateReceiveSpan","receiveTypeSpan","reportInfoSpan","isgroupInfoSpan","groupInfoSpan",
				                "removeReasonSpan","removeDateSpan","jailTypeSpan","jailReasonSpan","jailDateSpan","deathDateSpan","deathReasonSpan","familyContactSpan","performanceSpan","mannerSpan","istrainedSpan",
				                "hascertificateSpan","issanwuSpan","riskSpan","isdeviceCodeSpan","wearTimeSpan","releaseTimeSpan","alarmTypeSpan","alarmTimeSpan"];
				var tabtd;
				for(var i=0;i<spanlist.length;i++){
					tabtd = tabtd+"<span id='"+spanlist[i]+"' style='font-weight: bold; font-size: 14px;'></span>";
					tabtd = tabtd.replace("undefined",'');
				} 
				if(tabtd!=null && tabtd !='undefined' && tabtd!=''){
					$("#spantb").append("<tr><td>"+tabtd+"</td></tr>");
				}
				
				
				$.menu.init("#menuDetail",{first:"zhcx",second:"zhcxAll"});
				
				var orgType = ${user.wunit.orgType};
				if(orgType=='1'){
					$.organization.combobox("#qxjOrg", "responseOrgqxj", ${user.wunit.bm}, {
				    	allowBlank : true,
						level : 10,
						showRoot : true,
						showItself : true,
						notShowType:"3,4,5,6,7,8,9",
						emptyText : '全部',
						//defValue:${user.wunit.bm},
						dropdownAutoWidth:false,
						multiSelected: false,
						width:'80%',
						attr:{searchType:"eq"},valuechange:function(value,elem){
							selectValueChange("responseOrgSpan","矫正单位",value,elem,"org");
						}}); 
					$.organization.combobox("#sfsOrg", "responseOrgsfs", "", {
				    	allowBlank : true,
						level : 10,
						showRoot : true,
						showItself : true,
						notShowType:"1,2,3,4,5,6,7,8,9",
						emptyText : '全部',
						//defValue:${user.wunit.bm},
						dropdownAutoWidth:false,
						multiSelected: false,
						width:'80%',
						attr:{searchType:"eq"}
						});
				}else if(orgType=='2'){
					var bm = ${user.wunit.bm};
					var bmmc = $.organization.formatter()(bm);
					$("#qxjOrg").text(bmmc);
					$.organization.combobox("#sfsOrg", "responseOrgsfs",bm, {
							    	allowBlank : true,
									level : 10,
									showRoot : true,
									showItself : true,
									//ShowType:"3",
									notShowType:"4,5,6,7,8,9",
									emptyText : '全部',
									//defValue:${user.wunit.bm},
									dropdownAutoWidth:false,
									multiSelected: false,
									width:'80%',
									attr:{searchType:"eq"},valuechange:function(value,elem){
										selectOrgValueChange("responseOrgSpan","矫正单位",value,elem,"org");
									}});
				}else if(orgType=='3'){
					var bm = '${user.wunit.bm}';
					var supOrg = '${user.wunit.supOrg}';
					var supmc=	$.organization.formatter()(supOrg);
					var bmmc=	$.organization.formatter()(bm);
					$("#qxjOrg").text(supmc);
					$("#sfsOrg").text(bmmc);
				}
				
			 	    
				$.dictionary.combobox("#state", "state", "JZRYZT",{attr:{searchType:"eq"},width:'80%',height:'30px',valuechange:function(value,elem){
					selectValueChange("stateSpan","矫正状态",value,elem,"JZRYZT");
				}});
				//姓名
				$.dictionary.combobox("#sex", "sex", "XB",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("sexSpan","性别",value,elem,"XB");
				}});
				$.dictionary.combobox("#nation", "nation", "MZ",{attr:{searchType:"in"},multiSelected: true,width:'80%',height:'15px',valuechange:function(value,elem){
					selectValueChange("nationSpan","名族",value,elem,"MZ");
				}});
				//身份证
				//出生日期
				$.dictionary.combobox("#isadult", "isadult", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("isadultSpan","未成年犯",value,elem,"SF");
				}});
				$.dictionary.combobox("#politicsStatus", "politicsStatus", "ZZMM",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("politicsStatusSpan","政治面貌",value,elem,"ZZMM");
				}});
				$.dictionary.combobox("#politicsStatusOriginal", "politicsStatusOriginal", "ZZMM",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("politicsStatusOriginalSpan","原政治面貌",value,elem,"ZZMM");
				}});
				$.dictionary.combobox("#educationDegree", "educationDegree", "WHCD",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("educationDegreeSpan","文化程度",value,elem,"WHCD");
				}});
				$.dictionary.combobox("#workState", "workState", "JYJX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("workStateSpan","就业就学情况",value,elem,"JYJX");
				}});
				$.dictionary.combobox("#maritalState", "maritalState", "HYZK",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("maritalStateSpan","婚姻状况",value,elem,"HYZK");
				}});
				$.dictionary.combobox("#natureHome", "natureHome", "HJXZ",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("natureHomeSpan","户籍性质",value,elem,"HJXZ");
				}});
				$.dictionary.combobox("#iscapital", "iscapital", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("iscapitalSpan","是否京籍",value,elem,"SF");
				}});
				$.dictionary.combobox("#health", "health", "JKZK",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("healthSpan","健康状况",value,elem,"JKZK");
				}});
				$.dictionary.combobox("#healthMental", "healthMental", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("healthMentalSpan","心理是否健康",value,elem,"SF");
				}});
				$.dictionary.combobox("#psychosis", "psychosis", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("psychosisSpan","是否有精神病",value,elem,"SF");
				}});
				$.dictionary.combobox("#healthContagion", "healthContagion", "CRB",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("healthContagionSpan","具体传染病史",value,elem,"CRB");
				}});
				//----------------
				$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("adjustTypeSpan","矫正类别",value,elem,"JZLB");
				}});
				//矫正起始时间
				//矫正结束时间
				$.dictionary.combobox("#crimeType", "crimeType", "FZLX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("crimeTypeSpan","犯罪类型",value,elem,"FZLX");
				}});
				$.dictionary.combobox("#accusation", "accusation", "JTZM",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("accusationSpan","具体罪名",value,elem,"JTZM");
				}});
				$.dictionary.combobox("#oriPunishment", "oriPunishment", "XFLX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("oriPunishmentSpan","原判刑罚",value,elem,"XFLX");
				}});
				//原判刑期
				$.dictionary.combobox("#addpunishment", "addpunishment", "FJX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("addpunishmentSpan","附加刑",value,elem,"FJX");
				}});
				$.dictionary.combobox("#nonpoliticalPeriod", "nonpoliticalPeriod", "BDZZQLQX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("nonpoliticalPeriodSpan","剥夺政治权利期限",value,elem,"BDZZQLQX");
				}});
				$.dictionary.combobox("#sisType", "sisType", "SS",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("sisTypeSpan","是否“四史”",value,elem,"SS");
				}});
				$.dictionary.combobox("#sansType", "sansType", "SANS",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("sansTypeSpan","是否“三涉”",value,elem,"SANS");
				}});
				$.dictionary.combobox("#isRecidivism", "isRecidivism", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("isRecidivismSpan","是否累犯",value,elem,"SF");
				}});
				$.dictionary.combobox("#slfzType", "slfzType", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("slfzTypeSpan","是否三类犯罪",value,elem,"SF");
				}});
				$.dictionary.combobox("#isalone", "isalone", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("isaloneSpan","是否共同犯罪",value,elem,"SF");
				}});
				$.dictionary.combobox("#isforbidden", "isforbidden", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("isforbiddenSpan","是否被宣告禁令",value,elem,"SF");
				}});
				//社区服刑人员接收日期
				$.dictionary.combobox("#receiveType", "receiveType", "JSFS",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("receiveTypeSpan","社区服刑人员接收方式",value,elem,"JSFS");
				}});
				$.dictionary.combobox("#reportInfo", "reportInfo", "BDQK",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("reportInfoSpan","报到情况",value,elem,"BDQK");
				}});
				$.dictionary.combobox("#isgroupInfo", "isgroupInfo", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("isgroupInfoSpan","是否建立矫正小组",value,elem,"SF");
				}});
				$.dictionary.combobox("#groupInfo", "groupInfo", "JZXZRYQK",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("groupInfoSpan","矫正小组人员组成情况",value,elem,"JZXZRYQK");
				}});
				//-------------------------
				$.dictionary.combobox("#removeReason", "removeReason", "JJYY",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("removeReasonSpan","解除/终止矫正原因",value,elem,"JJYY");
				}});
				//解除/终止矫正日期范围
				$.dictionary.combobox("#jailType", "jailType", "SJZXLX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("jailTypeSpan","收监执行类型",value,elem,"SJZXLX");
				}});
				$.dictionary.combobox("#jailReason", "jailReason", "SJZXYY",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("jailReasonSpan","收监执行原因",value,elem,"SJZXYY");
				}});
				//收监执行日期范围
				//死亡日期范围
				$.dictionary.combobox("#deathReason", "deathReason", "SWYY",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("deathReasonSpan","死亡原因",value,elem,"SWYY");
				}});
				$.dictionary.combobox("#familyContact", "familyContact", "JTLXQK",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("familyContactSpan","家庭联系情况",value,elem,"JTLXQK");
				}});
				$.dictionary.combobox("#performance", "performance", "JZBX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("performanceSpan","矫正期间表现",value,elem,"JZBX");
				}});
				$.dictionary.combobox("#manner", "manner", "RZTD",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("mannerSpan","认真态度",value,elem,"RZTD");
				}});
				$.dictionary.combobox("#istrained", "istrained", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("istrainedSpan","矫正期间是否参加职业技能培训",value,elem,"SF");
				}});
				$.dictionary.combobox("#hascertificate", "hascertificate", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("hascertificateSpan","矫正期间是否获得职业技能证书",value,elem,"SF");
				}});
				$.dictionary.combobox("#issanwu", "issanwu", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
					selectValueChange("issanwuSpan","是否三无人员",value,elem,"SF");
				}});
				$.dictionary.combobox("#risk", "risk", "WXXPG",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("riskSpan","危险性评估",value,elem,"WXXPG");
				}});
				//---------------------------
				//$.dictionary.combobox("#ydDeviceNumber", "ydDeviceNumber", "SF",{attr:{searchType:"eq"},width:'80%',valuechange:function(value,elem){
				//	selectValueChange("ydDeviceNumberSpan","当前是否佩戴电子监管设备",value,elem,"SF");
				//}});
				//佩戴电子监管设备时间范围     
				//摘除电子监管设备时间范围
				$.dictionary.combobox("#alarmType", "alarmType", "BJLX",{attr:{searchType:"in"},multiSelected: true,width:'80%',valuechange:function(value,elem){
					selectValueChange("alarmTypeSpan","报警类型",value,elem,"BJLX");
				}});
				//报警时间范围 
				//--------------------------------------------
				$.dictionary.combobox("#sfsxxxhgl", "sfsxxxhgl", "SF",{attr:{searchType:"eq"},width:'80%'});
				$.dictionary.combobox("#isdeviceCode", "isdeviceCode", "SF",{attr:{searchType:"eq"},width:'80%'});
				$.dictionary.combobox("#bjjb", "bjjb", "BJJB",{attr:{searchType:"eq"},width:'80%'});
				$.dictionary.combobox("#bjlx", "bjlx", "BJLX",{attr:{searchType:"eq"},width:'80%'});
				//$.dictionary.combobox("#sfzdbj", "sfzdbj", "SFZDBJ",{attr:{searchType:"eq"}});
				$.dictionary.combobox("#jgzt", "dzjgzt", "DZJGZT",{attr:{searchType:"eq"},width:'80%'});
				$.dictionary.combobox("#sfzdtx", "sfzdtx", "SFZDBJ",{attr:{searchType:"eq"},width:'80%'});
				
				
				
				//表格初始化
				$("#mainGrid").jqGrid({
					autowidth : true,
					url		    : '',
					/* colNames	: [
							'查看详情','区局','司法所','矫正状态','姓名','性别','民族','身份证号','出身日期',
							'年龄范围','未成年犯','政治面貌','原政治面貌','文化程度','就业就学情况','婚姻状况','户籍性质',
							'是否京籍','健康状况','心理是否健康','是否有精神病','具有传染病史','矫正类别','矫正起始日期范围',
							'矫正终止日期范围','犯罪类型','具体罪名','原判刑罚','原判刑期','附加刑','剥夺政治权利期限',
							'是否“四史”','是否“三涉”','是否累犯','是否三类犯罪','是否共同犯罪','是否被宣告禁止令',
							'社区服刑人员接收日期','社区服刑人员接收方式','报到情况','是否建立矫正小组','矫正小组人员组成情况',
							'解除/终止矫正原因','解除/终止矫正日期范围','收监执行类型','收监执行原因','收监执行日期范围',
							'死亡日期范围','死亡原因','家庭联系情况','矫正期间表现','认真态度','矫正期间是否参加职业技能培训',
							'矫正期间是否获得职业技能证书','是否三无人员','危险性评估','当前是否佩戴电子监管设备',
							'佩戴电子监管设备时间范围','摘除电子监管设备时间范围','报警类型','报警时间范围 ','','',''
					], */
					cache:false,
					colModel	: [
							{//查看详情
								name : 'xq',
								index : 'xq',
								label	: '详情',
								width : 40,
								align : 'center',
								fixed : true,
								formatter : function(value, opts, rwdat) {
									return "<a href='javascript:viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>详情</a>";
								}
							},
							 {//矫正单位
						        name	: 'responseOrg',
						        index	: 'responseOrg',
						        label	: '矫正单位',
						        width	: 140,
						        align : 'center',
						        sortable: true,
						        fixed :true,
						        formatter:function(value, opts, rwdat){
									return $.organization.formatter()(rwdat.responseOrg);
								}
					        },  
					        {//矫正状态
						        name	: 'state',
						        index	: 'state',
						        label	: '矫正状态',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZRYZT'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//姓名
						        name	: 'name',
						        index	: 'name',
						        label	: '姓名',
						        width	: 140,
						        align	: 'center',
						        fixed :true
					        },
					        {
						        name	: 'userdName',
						        index	: 'userdName',
						        label	: '曾用名',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true,
		        				},
						        sortable: true,
						        fixed :true
					        },
					        {//性别
						        name	: 'sex',
						        index	: 'sex',
						        label	: '性别',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('XB'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//名族
						        name	: 'nation',
						        index	: 'nation',
						        label	: '名族',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('MZ'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//身份证号
						        name	: 'identityCard',
						        index	: 'identityCard',
						        label	: '身份证号',
						        width	: 140,
						        align	: 'center',
						        fixed :true
					        },
					        {//出生日期
						        name	: 'birthdate',
						        index	: 'birthdate',
						        label	: '出生日期',
						        width	: 160,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        //年龄范围
					        
					        {//未成年犯
						        name	: 'isadult',
						        index	: 'isadult',
						        label	: '未成年犯',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//政治面貌
						        name	: 'politicsStatus',
						        index	: 'politicsStatus',
						        label	: '政治面貌',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('ZZMM'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//原政治面貌
						        name	: 'politicsStatusOriginal',
						        index	: 'politicsStatusOriginal',
						        label	: '原政治面貌',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('ZZMM'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//文化程度
						        name	: 'educationDegree',
						        index	: 'educationDegree',
						        label	: '文化程度',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('WHCD'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//就业就学情况
						        name	: 'workState',
						        index	: 'workState',
						        label	: '就业就学情况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JYJX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//婚姻状况
						        name	: 'maritalState',
						        index	: 'maritalState',
						        label	: '婚姻状况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('HYZK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//户籍性质
						        name	: 'natureHome',
						        index	: 'natureHome',
						        label	: '户籍性质',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('HJXZ'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否京籍
						        name	: 'iscapital',
						        index	: 'iscapital',
						        label	: '是否京籍',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//'健康状况'
						        name	: 'health',
						        index	: 'health',
						        label	: '健康状况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JKZK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//心理是否健康
						        name	: 'healthMental',
						        index	: 'healthMental',
						        label	: '心理是否健康',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否有精神病
						        name	: 'psychosis',
						        index	: 'psychosis',
						        label	: '是否有精神病',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//具体传染病史
						        name	: 'healthContagion',
						        index	: 'healthContagion',
						        label	: '具体传染病史',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('CRB'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'passport',
						        index	: 'passport',
						        label	: '护照号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{
						        name	: 'hrPermit',
						        index	: 'hrPermit',
						        label	: '回乡证号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{
						        name	: 'hkIdentity',
						        index	: 'hkIdentity',
						        label	: '香港身份证号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{
						        name	: 'amIdentity',
						        index	: 'amIdentity',
						        label	: '澳门身份证号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{
						        name	: 'tbIdentity',
						        index	: 'tbIdentity',
						        label	: '台胞证号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{
						        name	: 'gatPermit',
						        index	: 'gatPermit',
						        label	: '港澳台通行证号码',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },
					        {
						        name	: 'workNuit',
						        index	: 'workNuit',
						        label	: '现工作单位',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        hidden  : true
					        },{
						        name	: 'workNuitPhone',
						        index	: 'workNuitPhone',
						        label	: '单位联系电话',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        hidden  : true
					        },
					        {
						        name	: 'houseAddress',
						        index	: 'houseAddress',
						        label	: '居住地详细地址',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        hidden  : true
					        },{
						        name	: 'homeAddress',
						        index	: 'homeAddress',
						        label	: '户籍详细地址',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'基本身份信息', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        hidden  : true
					        },
					        
					        
					        
					        
					        
					        
					        
					        
					        
					        {//矫正类别
						        name	: 'adjustType',
						        index	: 'adjustType',
						        label	: '矫正类别',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZLB'),
						        sortable: true,
						        fixed :true
					        },
					        {
						        name	: 'adjustPeriod',
						        index	: 'adjustPeriod',
						        label	: '矫正期限',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//矫正起始日期
						        name	: 'dateSAdjust',
						        index	: 'dateSAdjust',
						        label	: '矫正起始日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//矫正结束日期
						        name	: 'dateEAdjust',
						        index	: 'dateEAdjust',
						        label	: '矫正结束日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//犯罪类型
						        name	: 'crimeType',
						        index	: 'crimeType',
						        label	: '犯罪类型',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('FZLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//具体罪名
						        name	: 'accusation',
						        index	: 'accusation',
						        label	: '具体罪名',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JTZM'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//原判刑罚
						        name	: 'oriPunishment',
						        index	: 'oriPunishment',
						        label	: '原判刑罚',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('XFLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'dateSOri',
						        index	: 'dateSOri',
						        label	: '原判刑起始日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'dateEOri',
						        index	: 'dateEOri',
						        label	: '原判刑结束日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//原判刑期
						        name	: 'oriPeriod',
						        index	: 'oriPeriod',
						        label	: '原判刑期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'imprisonmentPeriod',
						        index	: 'imprisonmentPeriod',
						        label	: '有期徒刑期限',
						        formatter : 'dictionary',
						        formatoptions: {code:'YQTXQX'},
						        width	: 140,
						        align	: 'center',
						        searchType : 'eq',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//附加刑
						        name	: 'addpunishment',
						        index	: 'addpunishment',
						        label	: '附加刑',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('FJX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//'剥夺政治权利期限'
						        name	: 'nonpoliticalPeriod',
						        index	: 'nonpoliticalPeriod',
						        label	: '剥夺政治权利期限',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BDZZQLQX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'dateSNonpolitical',
						        index	: 'dateSNonpolitical',
						        label	: '剥夺政治权利起始日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'dateENonpolitical',
						        index	: 'dateENonpolitical',
						        label	: '剥夺政治权利结束日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'receiveUnit',
						        index	: 'receiveUnit',
						        label	: '接收剥夺政治权利人员公安机关名称',
						        width	: 140,
						        align	: 'center',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'receivePerson',
						        index	: 'receivePerson',
						        label	: '接收人',
						        width	: 140,
						        align	: 'center',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'dateTransfer',
						        index	: 'dateTransfer',
						        label	: '移交日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否“四史”
						        name	: 'sisType',
						        index	: 'sisType',
						        label	: '是否“四史”',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否“三涉”
						        name	: 'sansType',
						        index	: 'sansType',
						        label	: '是否“三涉”',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SANS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否累犯
						        name	: 'isRecidivism',
						        index	: 'isRecidivism',
						        label	: '是否累犯',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否“三类犯罪”
						        name	: 'slfzType',
						        index	: 'slfzType',
						        label	: '是否“三类犯罪”',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否共同犯罪
						        name	: 'isalone',
						        index	: 'isalone',
						        label	: '是否共同犯罪',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否被宣告禁令
						        name	: 'isforbidden',
						        index	: 'isforbidden',
						        label	: '是否被宣告禁令',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'forbidden',
						        index	: 'forbidden',
						        label	: '禁止令内容',
						        width	: 140,
						        align	: 'center',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'dateSForbidden',
						        index	: 'dateSForbidden',
						        label	: '禁止令起始日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'dateEForbidden',
						        index	: 'dateEForbidden',
						        label	: '禁止令结束日期',
						        width	: 140,
						        align	: 'center',
						        searchType : 'in',
						        searchInput : 'date',
						        searchPartition:{
		        					text:'刑罚执行信息', 
		        					content:'executeInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//服刑人员接收日期
						        name	: 'dateReceive',
						        index	: 'dateReceive',
						        label	: '服刑人员接收日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//接收方式
						        name	: 'receiveType',
						        index	: 'receiveType',
						        label	: '接收方式',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JSFS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//报到情况
						        name	: 'reportInfo',
						        index	: 'reportInfo',
						        label	: '报到情况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BDQK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//是否建立矫正小组
						        name	: 'isgroupInfo',
						        index	: 'isgroupInfo',
						        label	: '是否建立矫正小组',
						        width	: 160,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//矫正小组情况
						        name	: 'groupInfo',
						        index	: 'groupInfo',
						        label	: '矫正小组情况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZXZRYQK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        
							
					        
					        
					        
							{//解除/终止矫正原因
						        name	: 'removeReason',
						        index	: 'removeReason',
						        label	: '解除/终止矫正原因',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JJYY'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//解除/终止矫正日期
						        name	: 'removeDate',
						        index	: 'removeDate',
						        label	: '解除/终止矫正日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//收监执行类型
						        name	: 'jailType',
						        index	: 'jailType',
						        label	: '收监执行类型',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SJZXLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//收监执行原因
						        name	: 'jailReason',
						        index	: 'jailReason',
						        label	: '收监执行原因',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SJZXYY'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {//收监执行日期
						        name	: 'jailDate',
						        index	: 'jailDate',
						        label	: '收监执行日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//死亡日期
						        name	: 'deathDate',
						        index	: 'deathDate',
						        label	: '死亡日期',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//死亡原因
						        name	: 'deathReason',
						        index	: 'deathReason',
						        label	: '死亡原因',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SWYY'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//具体死因
						        name	: 'deathReasonDetail',
						        index	: 'deathReasonDetail',
						        label	: '具体死因',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JTSY'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//矫正表现
						        name	: 'performance',
						        index	: 'performance',
						        label	: '矫正表现',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZBX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//认罪态度
						        name	: 'manner',
						        index	: 'manner',
						        label	: '认罪态度',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('RZTD'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//是否参加职业技能培训
						        name	: 'istrained',
						        index	: 'istrained',
						        label	: '是否参加职业技能培训',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//是否获得职业技能证书
						        name	: 'hascertificate',
						        index	: 'hascertificate',
						        label	: '是否获得职业技能证书',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//技术特长及等级
						        name	: 'speciality',
						        index	: 'speciality',
						        label	: '技术特长及等级',
						        width	: 140,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//是否三无人员
						        name	: 'issanwu',
						        index	: 'issanwu',
						        label	: '是否三无人员',
						        formatter : $.dictionary.formatter('SF'),
						        width	: 140,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//危险性评估
						        name	: 'risk',
						        index	: 'risk',
						        label	: '危险性评估',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('WXXPG'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//家庭联系情况
						        name	: 'familyContact',
						        index	: 'familyContact',
						        label	: '家庭联系情况',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JTLXQK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        
					        
					        
					        
					        {//矫正期间表现鉴定意见
						        name	: 'opinion',
						        index	: 'opinion',
						        label	: '矫正期间表现鉴定意见',
						        width	: 140,
						        align	: 'center',
						        searchType : 'cn',
						        searchPartition:{
		        					text:'矫正期间表现鉴定意见', 
		        					content:'baseInfo',
		        					hiddenResult: true
		        				},
						        sortable: true,
						        fixed :true,
						        hidden: true
					        },{//佩戴电子监管设备时间范围
						        name	: 'wearTime',
						        index	: 'wearTime',
						        label	: '佩戴电子监管设备时间范围',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//摘除电子监管设备时间范围
						        name	: 'releaseTime',
						        index	: 'releaseTime',
						        label	: '摘除电子监管设备时间范围',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//报警类型
						        name	: 'alarmType',
						        index	: 'alarmType',
						        label	: '报警类型',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BJLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{//报警时间
						        name	: 'alarmTime',
						        index	: 'alarmTime',
						        label	: '报警时间',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        
					        
					        {//是否电子监管
						        name	: 'isdeviceCode',
						        index	: 'isdeviceCode',
						        label	: '是否电子监管',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
								name : 'fxryId',
								index : 'fxryId',
								label	: '',
								hidedlg : false,
								hidden : true
							}
					        
					        
					],
						multiselect : false,
						//sortname : "responseOrg,adjustType,name,sex,healthMental,natureHome,politicsStatus,politicsStatusOriginal,educationDegree,maritalState,workState,crimeType,oriPunishment,addpunishment,sisType,sansType,isRecidivism,isalone,isforbidden,dateReceive,receiveType,reportInfo,isgroupInfo,groupInfo",
						//sortorder : "desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc",
						sortname : "responseOrg,adjustType,name",
						sortorder : "asc,asc,asc",
						pager: '#mainGridPager',
				});
				//$("#mainGrid").bindSearchForm("#searchDiv","#searchBtn", "#aaa");
				bindSearchForm2("#mainGrid","#searchDiv","#searchBtn", "#aaa");
				
				 //初始化--Grid显示常用项
				$.each($("#cxtjjg input"),function(i,n){
					if(i<9){
						var tt = $(n).attr('id');
						tt =tt.substring(0,tt.length-1);
						$(n).attr("checked","true");
						$("#mainGrid").setGridParam().showCol(tt).trigger("reloadGrid");
					}
				});  
				
				 
				$("#mainGrid1").jqGrid({
					url		    : '',
					colModel: [ {//姓名
				        name	: 'responseOrg',
				        index	: 'responseOrg',
				        label	: '机构名称',
				        align	: 'center',
				        
				        formatter:function(value, opts, rwdat){
				        	if(value=="qb"){
				        		return "全部"
				        	}else{
								return $.organization.formatter()(rwdat.responseOrg);
				        	}
						}
			        },
			        {
				        name	: 'code',
				        index	: 'code',
				        label	: '统计数据',
				        align	: 'center',
				        searchType : 'cn',
				        searchPartition:{
        					text:'统计数据', 
        					content:'baseInfo',
        					hiddenResult: true,
        				},
				        sortable: true
			        }
					],
					multiselect : false,
					rownumbers	: true,
					sortname: 'responseOrg',
					sortorder: "asc",
					pager: '#mainGridPager1'
				});
				//$("#mainGrid1").bindSearchForm("#searchDiv","#resultBtn", "#bbb");
				bindSearchForm2("#mainGrid1","#searchDiv","#resultBtn", "#bbb");
				 
				 
				 
				 
				//初始化--checkbox状态
				$("#chkAlljb").click(function() {
					var chkedFlg = $(this).attr("checked");
					$("input:checkbox[class=jb]").each(function() {
						if(!$(this).attr("disabled")){
							if (chkedFlg) {
								$(this).attr("checked","true");
							} else {
								$(this).removeAttr("checked");
							}
						}
					});
				});
				$("#chkAllxf").click(function() {
					var chkedFlg = $(this).attr("checked");
					$("input:checkbox[class=xf]").each(function() {
						if(!$(this).attr("disabled")){
							if (chkedFlg) {
								$(this).attr("checked","true");
							} else {
								$(this).removeAttr("checked");
							}
						}
					});
				});
				$("#chkAlljj").click(function() {
					var chkedFlg = $(this).attr("checked");
					$("input:checkbox[class=jj]").each(function() {
						if(!$(this).attr("disabled")){
							if (chkedFlg) {
								$(this).attr("checked","true");
							} else {
								$(this).removeAttr("checked");
							}
						}
					});
				});
				$("#chkAlljg").click(function() {
					var chkedFlg = $(this).attr("checked");
					$("input:checkbox[class=jg]").each(function() {
						if(!$(this).attr("disabled")){
							if (chkedFlg) {
								$(this).attr("checked","true");
							} else {
								$(this).removeAttr("checked");
							}
						}
					});
				});
				
				
				$("#atj").click(function() {
					if ($("#divtj").attr("class") == "spanTurnOff") {
						$("#divtj").removeClass("spanTurnOff");
						$("#divtj").addClass("spanTurnOn");
						$("#atj").text("\u5c55\u5f00");
						$("#tj").hide();
						$("#tjTab").hide();
						$("#tjTj").hide();
					} else {
						$("#divtj").removeClass("spanTurnOn");
						$("#divtj").addClass("spanTurnOff");
						$("#atj").text("\u6536\u8d77");
						$("#tj").show();
						$("#tjTab").show();
						$("#tjTj").show();
					}
				});
				
				$("#ajg").click(function() {
					if ($("#divjg").attr("class") == "spanTurnOff") {
						$("#divjg").removeClass("spanTurnOff");
						$("#divjg").addClass("spanTurnOn");
						$("#ajg").text("\u5c55\u5f00");
						$("#jg").hide();
					} else {
						$("#divjg").removeClass("spanTurnOn");
						$("#divjg").addClass("spanTurnOff");
						$("#ajg").text("\u6536\u8d77");
						$("#jg").show();
					}
				});
			});	
			//查看未处理报警信息
			function wclbjxx(name,id){
				$.dialog({ 
					id: 'wclxxList',
				    width: '750px', 
    				height: '450px', 
    				title: name+'的未处理报警信息',
				    content: 'url:${ctx}/data/dzjg/zhcx/wclbjxxList.jsp?id='+id,
    				cancelVal: '关闭', 
    				cancel: true 
				});
			}
			function viewDetail(id){
				$.dialog({ 
					id: 'detail',
				    width: '1200px', 
    				height: '700px', 
    				title: '详细信息查看',
				    content: 'url:${ctx}/data/jzgl/dagl/zhcxview.jsp?id='+id,
    				cancelVal: '关闭', 
    				cancel: true 
				});
			}
			//导出
			function openExcel(){
				var colnames="";
				$.each($("#cxtjjg input"),function(i,n){
					var tt = $(n).attr('id');
					tt =tt.substring(0,tt.length-1);
					if($(n).attr("checked")){
						colnames=colnames+","+tt;
					}
				});
				var index=colnames.lastIndexOf('chkAllj');
				colnames=colnames.substring((index+8),colnames.length);
				var grid = $("#mainGrid").getGridParam('postData') || {};
				var records = $("#mainGrid").getGridParam('records');
				var data = {};
				for (var i in grid){
					data["jqgrid."+i] = grid[i];
				}
				var t = data["jqgrid.cols"].split(",").slice(2);
				t.pop();
				data["jqgrid.cols"]=t.join(",");
				var url = "${ctx }/data/zhcxAll/exportExcel.action?" + $.param(data)+"&colnames="+colnames+"&records="+records;
				$("#downloadFrame").attr("src",url);
			}
			function changetab(tab) {
				$("#tab-jbxx").removeClass("tabpage-label-selected");
				$("#tab-xfxx").removeClass("tabpage-label-selected");
				$("#tab-jjxx").removeClass("tabpage-label-selected");
				$("#tab-jgxx").removeClass("tabpage-label-selected");
				$("#jbxx").attr("style","display: none;");
				$("#xfxx").attr("style","display: none;");
				$("#jjxx").attr("style","display: none;");
				$("#jgxx").attr("style","display: none;");
				$("#jbxxjg").attr("style","display: none;");
				$("#xfxxjg").attr("style","display: none;");
				$("#jjxxjg").attr("style","display: none;");
				$("#jgxxjg").attr("style","display: none;");
				$("#jbxxchkAll").attr("style","display: none;");
				$("#xfxxchkAll").attr("style","display: none;");
				$("#jjxxchkAll").attr("style","display: none;");
				$("#jgxxchkAll").attr("style","display: none;");
				$("#tab-"+tab).addClass("tabpage-label-selected");
				$("#"+tab).attr("style","display: block;");
				$("#tab-"+tab+"jg").addClass("tabpage-label-selected");
				$("#"+tab+"jg").attr("style","display: block;");
				$("#tab-"+tab+"chkAll").addClass("tabpage-label-selected");
				$("#"+tab+"chkAll").attr("style","display: block;");
			}
			$(function() {
				$("#tou").attr("style","display: none;");
				$("#tj").attr("style","display: none;");
				$("#jbxx").attr("style","display: none;");
				$("#xfxx").attr("style","display: none;");
				$("#jjxx").attr("style","display: none;");
				$("#jgxx").attr("style","display: none;");
				
				$("#tou").attr("style","display: block;");
				$("#tj").attr("style","display: block;");
				$("#tjTab").attr("style","display: block;");
				$("#tjTj").attr("style","display: block;");
				$("#tab-jbxx").addClass("tabpage-label-selected");
				$("#jbxx").attr("style","display: block;");
				$("#jbxxjg").attr("style","display: block;");
				$("#jbxxchkAll").attr("style","display: block;");
			});
			function next() {
				$("#tj").attr("style","display: none;");
				$("#tjTab").attr("style","display: none;");
				$("#tjTj").attr("style","display: none;");
				$("#cxtjjg").attr("style","display: block;");
				
				
				$.each($("#tj input"),function(i,n){
					var tjid = $(n).attr('id');
					if(tjid=="qxjOrg"||tjid=="sfsOrg"||tjid=="testOrgsfs"){
						$("#responseOrgc").attr("checked","true");
						//$("#mainGrid").setGridParam().showCol("responseOrgc").trigger("reloadGrid");这是个坑
					}else{
						var tt = $(n).attr('name');
						var v = $("input[name='"+tt+"']").val();
						if(v!=""&&v!="null"&&v!=null&&v!="undefined"){
							$("#"+tt+"c").attr("checked","true");
							//$("#mainGrid").setGridParam().showCol(tt+"c").trigger("reloadGrid");
						}
					}
					
				});     
			}
			function up() {
				$("#cxtjjg").attr("style","display: none;");
				$("#tj").attr("style","display: block;");
				$("#tjTab").attr("style","display: block;");
				$("#tjTj").attr("style","display: block;");
			}
			
			function tongji(){
				$("#tjPag").attr("style","display: none;");
				$("#tjTab").attr("style","display: none;");
				$("#tjTj").attr("style","display: block;");
				$("#cxtjjg").attr("style","display: none;");
				$("#tjTjt").attr("style","display: block;");
				searchByFilters();
			}
			function fusioncharts(opts){
				var option = $.extend({
					width:'100%',
					height:'450',
					renderAt:'',
					dataFormat:'json',
					dataSource:{
						chart:{
			        		caption:opts.title||"",
			        		formatNumberScale:false,
			        	},
			        	data:opts.data,
			        	categories:opts.categories,
			        	dataset:opts.dataset
					}
				}, opts || {});
				$("#"+opts.renderAt).children().remove();
				FusionCharts.ready(function () {
				    var tempVsSalesChart = new FusionCharts(option);
				    tempVsSalesChart.render();
				});
			}
		</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="container-top" id="tou" style="display: block;">

			<div id="tjPag" style="display: block;">
				<ul class="tabpage-label-container">
					<span onclick="changetab('jbxx')"><li id="tab-jbxx"
						class="tabpage-label">基本身份信息</li></span>
					<span onclick="changetab('xfxx')"><li id="tab-xfxx"
						class="tabpage-label">刑罚执行信息</li></span>
					<span onclick="changetab('jjxx')"><li id="tab-jjxx"
						class="tabpage-label">解矫信息</li></span>
					<span onclick="changetab('jgxx')"><li id="tab-jgxx"
						class="tabpage-label">电子监管信息</li></span>
				</ul>
				<div style="clear:both;"></div>
			</div>
			<!-- 查询条件 -->
			<div >
				<div id="tj" style="display: block;" class="operation-box container-main">
				
					<table class="comm-table1" id="searchDiv">
						<tr id="jbxx" style="display: block;"><td width="100%">
						<table class="comm-table1" >
							<colgroup>
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
							</colgroup>
							<tbody>
								<tr style="display:none">
									<th></th>
									<td ><input type="text" name="testOrgsfs" searchType="eq" id="testOrgsfs"/></td>
									<th></th>
									<td><input type="text" name="rqrad" searchType="eq" id="rqrad"/></td>
									<th></th>
									<td></td>
									<th></th>
									<td></td>
								</tr>
								<tr>
									<th>区局：</th>
									<td id="qxjOrg"></td>
									<th>司法所：</th>
									<td id="sfsOrg"></td>
									<th>矫正状态：</th>
									<td id="state"></td>
									<th>姓名：</th>
									<td><input type="text" id="name" name="name" 
										onchange="selectValueChange('nameSpan','姓名',value,'name','text')"
										searchType="cn" style="width: 75%; background:white; text-align: center;margin: auto;" /></td>
								</tr>
								<tr>
									<th>性别：</th>
									<td id="sex"></td>
									<th>民族：</th>
									<td id="nation"></td>
									<th>身份证号：</th>
									<td><input type="text" id="identityCard" 
										onchange="selectValueChange('identityCardSpan','身份证号',value,'identityCard','text')"
										name="identityCard" searchType="cn"
										style="width: 75%; background:white;margin: auto;" /></td>
									<th>未成年犯：</th>
									<td id="isadult"></td>
								</tr>
								<tr>
								</tr>
								<tr>
									<th>出生日期：</th>
									<td><input id="birthdate1" name="birthdate" type="text" 
										onchange="selectValueChange('birthdateSpan1','出生日期',value,'birthdate','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.4%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="birthdate2" name="birthdate" type="text" 
										onchange="selectValueChange('birthdateSpan2','出生日期',value,'birthdate','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.4%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>年龄范围：</th>
									<td><input type="text" id="nlfw1" name="nlfw" 
										onchange="selectValueChange('nlfwSpan1','出生日期',value,'nlfw','nlfw')"
										style="width: 20%; background:white;margin: auto;" /> 岁 —— 
										<input type="text" id="nlfw2" name="nlfw" 
										onchange="selectValueChange('nlfwSpan2','出生日期',value,'nlfw','nlfw')"
										style="width: 20%; background:white;margin: auto;" /> 岁</td>
									<th>政治面貌：</th>
									<td id="politicsStatus"></td>
									<th>原政治面貌：</th>
									<td id="politicsStatusOriginal"></td>
								</tr>
								<tr>
									<th>文化程度：</th>
									<td id="educationDegree"></td>
									<th>婚姻状况：</th>
									<td id="maritalState"></td>
									<th>户籍性质：</th>
									<td id="natureHome"></td>
									<th>是否京籍：</th>
									<td id="iscapital"></td>
								</tr>
								<tr>
									<th>&emsp;&emsp;具有传染病史：</th>
									<td id="healthContagion"></td>
									<th>健康状况：</th>
									<td id="health"></td>
									<th>&emsp;&emsp;心理是否健康：</th>
									<td id="healthMental"></td>
									<th>&emsp;&emsp;是否有精神病：</th>
									<td id="psychosis"></td>
								</tr>
							</tbody>
						</table>
						</td></tr>
						<tr id="xfxx" style="display: block;"><td>
						<table class="comm-table1" >
							<colgroup>
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
							</colgroup>
							<tbody>
								<tr>
									<th>矫正类别：</th>
									<td id="adjustType"></td>
									<th>矫正起始日期范围：</th>
									<td><input id="dateSAdjust" name="dateSAdjust" type="text" 
										onchange="selectValueChange('dateSAdjustSpan1','矫正起始日期范围',value,'dateSAdjust','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;" 
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="dateSAdjust" name="dateSAdjust" type="text" 
										onchange="selectValueChange('dateSAdjustSpan2','矫正起始日期范围',value,'dateSAdjust','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>矫正终止日期范围：</th>
									<td><input id="dateEAdjust" name="dateEAdjust" type="text" 
										onchange="selectValueChange('dateEAdjustSpan1','矫正终止日期范围',value,'dateEAdjust','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="dateEAdjust" name="dateEAdjust" type="text" 
										onchange="selectValueChange('dateEAdjustSpan2','矫正终止日期范围',value,'dateEAdjust','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>犯罪类型：</th>
									<td id="crimeType"></td>
								</tr>
								<tr>
									<th>具体罪名：</th>
									<td id="accusation"></td>
									<th>原判刑罚：</th>
									<td id="oriPunishment"></td>
									<th>原判刑期：</th>
									<td><input id="oriPeriod" name="oriPeriod" type="text" 
										onchange="selectValueChange('oriPeriodSpan1','原判刑期',value,'oriPeriod','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="oriPeriod" name="oriPeriod" type="text" 
										onchange="selectValueChange('oriPeriodSpan2','原判刑期',value,'oriPeriod','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>附加刑：</th>
									<td id="addpunishment"></td>
								</tr>
								<tr>
									<th>剥夺政治权利期限：</th>
									<td id="nonpoliticalPeriod"></td>
									<th>是否“四史”：</th>
									<td id="sisType"></td>
									<th>是否“三涉”：</th>
									<td id="sansType"></td>
									<th>是否累犯：</th>
									<td id="isRecidivism"></td>
								</tr>
								<tr>
									<th>是否三类犯罪：</th>
									<td id="slfzType"></td>
									<th>是否共同犯罪：</th>
									<td id="isalone"></td>
									<th>是否被宣告禁止令：</th>
									<td id="isforbidden"></td>
									<th>社区服刑人员接收日期：</th>
									<td><input id="dateReceive" name="dateReceive" type="text" 
										onchange="selectValueChange('dateReceiveSpan1','社区服刑人员接收日期',value,'dateReceive','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										 至<input id="dateReceive" name="dateReceive" type="text" 
										onchange="selectValueChange('dateReceiveSpan2','社区服刑人员接收日期',value,'dateReceive','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" /> 
									</td>
								</tr>
								<tr>
									<th>社区服刑人员接收方式：</th>
									<td id="receiveType"></td>
									<th>报到情况：</th>
									<td id="reportInfo"></td>
									<th>是否建立矫正小组：</th>
									<td id="isgroupInfo"></td>
									<th>矫正小组人员组成情况：</th>
									<td id="groupInfo" colspan="3"></td>
								</tr>
							</tbody>
						</table>
						</td></tr>
						<tr id="jjxx" style="display: block;"><td>
						<table class="comm-table1" >
							<colgroup>
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
								<col style="width: 8%; white-space:normal;" />
								<col style="width: 17%; white-space:nowrap;" />
							</colgroup>
							<tbody>
								<tr>
									<th>解除/终止矫正原因：</th>
									<td id="removeReason"></td>
									<th>解除/终止矫正日期范围：</th>
									<td><input id="removeDate" name="removeDate" type="text"
										onchange="selectValueChange('removeDateSpan1','解除/终止矫正日期范围',value,'removeDate','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="removeDate" name="removeDate" type="text"
										onchange="selectValueChange('removeDateSpan2','解除/终止矫正日期范围',value,'removeDate','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>收监执行类型：</th>
									<td id="jailType"></td>
									<th>收监执行原因：</th>
									<td id="jailReason"></td>
								</tr>
								<tr>
									<th>收监执行日期范围：</th>
									<td><input id="jailDate" name="jailDate" type="text"
										onchange="selectValueChange('jailDateSpan1','收监执行日期范围',value,'jailDate','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="jailDate" name="jailDate" type="text"
										onchange="selectValueChange('jailDateSpan2','收监执行日期范围',value,'jailDate','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>死亡日期范围：</th>
									<td><input id="deathDate" name="deathDate" type="text"
										onchange="selectValueChange('deathDateSpan1','死亡日期范围',value,'deathDate','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="deathDate" name="deathDate" type="text"
										onchange="selectValueChange('deathDateSpan2','死亡日期范围',value,'deathDate','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>死亡原因：</th>
									<td id="deathReason"></td>
									<th>家庭联系情况：</th>
									<td id="familyContact"></td>
								</tr>
								<tr>
									<th>矫正期间表现：</th>
									<td id="performance"></td>
									<th>认真态度：</th>
									<td id="manner"></td>
									<th>矫正期间是否参加职业技能培训：</th>
									<td id="istrained"></td>
									<th>矫正期间是否获得职业技能证书：</th>
									<td id="hascertificate"></td>
								</tr>
								<tr>
									<th>是否三无人员：</th>
									<td id="issanwu"></td>
									<th>危险性评估：</th>
									<td id="risk"></td>
								</tr>
							</tbody>
						</table>
						</td></tr>
						<tr id="jgxx" style="display: block;"><td>
						<table class="comm-table1" >
							<colgroup>
								<col style="width: 13%; white-space:normal;" />
								<col style="width: 20%; white-space:nowrap;" />
								<col style="width: 13%; white-space:normal;" />
								<col style="width: 20%; white-space:nowrap;" />
								<col style="width: 13%; white-space:normal;" />
								<col style="width: 20%; white-space:nowrap;" />
							</colgroup>
							<tbody>
								<tr>
									<th>当前是否佩戴电子监管设备：</th>
									<td id="isdeviceCode"></td>
									<th>佩戴电子监管设备时间范围：</th>
									<td><input id="wearTime" name="wearTime" type="text" 
										onchange="selectValueChange('wearTimeSpan1','佩戴电子监管设备时间范围',value,'wearTime','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="wearTime" name="wearTime" type="text"
										onchange="selectValueChange('wearTimeSpan2','佩戴电子监管设备时间范围',value,'wearTime','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
									<th>摘除电子监管设备时间范围：</th>
									<td><input id="releaseTime" name="releaseTime" type="text"
										onchange="selectValueChange('releaseTimeSpan1','摘除电子监管设备时间范围',value,'releaseTime','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="releaseTime" name="releaseTime" type="text"
										onchange="selectValueChange('releaseTimeSpan2','摘除电子监管设备时间范围',value,'releaseTime','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
								</tr>
								<tr>
									<th>报警类型：</th>
									<td id="alarmType"></td>
									<th>报警时间范围：</th>
									<td><input id="alarmTime" name="alarmTime" type="text"
										onchange="selectValueChange('alarmTimeSpan1','报警时间范围',value,'alarmTime','date')"
										searchType="ge" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
										至 <input id="alarmTime" name="alarmTime" type="text"
										onchange="selectValueChange('alarmTimeSpan2','报警时间范围',value,'alarmTime','date')"
										searchType="le" class="Wdate inputStyle" style="width: 29.5%;margin: auto;"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
									</td>
								</tr>
							</tbody>
						</table>
						</td></tr>
					</table>

				</div>

				<div class="operation-box container-main" id="tjTj" style="display: none;">
					<div class="title">
						<label>已选的查询条件：</label>
					</div>
					<div class="comm-div">
						<table class="comm-table" id="spantb">
						</table>
					</div>
				</div>
				
				<div id="tjTjt" style="display: none;">
					<div class="title">
						<label>统计信息：</label>
						<div class="buttonArea operation">
      						<input type="button" id="btnExcel" class="bttn bicon-excel"  value="导出" onclick="openExcelTj()"/>
  						</div>
					</div>
					<div >
						<table width="100%">
						<tr>
						<td width="50%">
							<div >
										<div class="container-bottom" >
											<table id="mainGrid1">
											</table>
											<div id="mainGridPager1"></div>
										 </div>
						</td>
						<td id="sjtjt" width="50%">
						</td>
						</tr>
						</table>
					</div>
				</div>
				<table width="100%" id="tjTab" style="display: none;">
					<tbody>
						<tr>
							<td align="right" style="position: absolute;right: 10px;">
								<input type="button" class="bttn bicon-next" value="下一步" onclick="next()" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 查询结果 -->
			<div class="contentWrapper" id="cxtjjg" style="display: none;">

				<div class="operation-box container-main">
					<div class="title" id="jbxxchkAll" style="display: none;">
						<label>查询结果定制：<input type="checkbox"
							id="chkAlljb" name="chkAll" />&nbsp;全选
						</label>
					</div>
					<div class="title" id="xfxxchkAll" style="display: none;">
						<label>查询结果定制：<input type="checkbox"
							id="chkAllxf" name="chkAll" />&nbsp;全选
						</label>
					</div>
					<div class="title" id="jjxxchkAll" style="display: none;">
						<label>查询结果定制：<input type="checkbox"
							id="chkAlljj" name="chkAll" />&nbsp;全选
						</label>
					</div>
					<div class="title" id="jgxxchkAll" style="display: none;">
						<label>查询结果定制：<input type="checkbox"
							id="chkAlljg" name="chkAll" />&nbsp;全选
						</label>
					</div>

					<div class="comm-div" id="jbxxjg" style="display: none;">
						<table class="comm-table1">
							<colgroup>
								<col style="width: 12%; " />
								<col style="width: 2%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 2%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 2%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 2%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 2%; text-align:center;" />
							</colgroup>
					 		<tbody>
								<tr>
									<th width="180px">矫正单位：</th>
									<td><input class="jb" type="checkbox" id="responseOrgc" /></td>
									<th>姓名：</th>
									<td><input class="jb" type="checkbox" id="namec" /></td>
									<th>性别：</th>
									<td><input class="jb" type="checkbox" id="sexc" /></td>
									<th>民族:</th>
									<td><input class="jb" type="checkbox" id="nationc" /></td>
									<th>身份证号：</th>
									<td><input class="jb" type="checkbox" id="identityCardc" /></td>
								</tr>
								<tr>
									<th>曾用名：</th>
									<td><input class="jb" type="checkbox" id="userdNamec" /></td>
									<th>出生日期：</th>
									<td><input class="jb" type="checkbox" id="birthdatec" /></td>
									<th>矫正状态：</th>
									<td><input class="jb" type="checkbox" id="statec" /></td>
									<th>未成年犯：</th>
									<td><input class="jb" type="checkbox" id="isadultc" /></td>
									<th>是否电子监管：</th>
									<td><input class="jb" type="checkbox" id="isdeviceCodec" /></td>
								</tr>
								<tr>
									<th>到区县报到时间：</th>
									<td><input class="jb" type="checkbox" id="reportTimec" /></td>
									<th>健康状况：</th>
									<td><input class="jb" type="checkbox" id="healthc" /></td>
									<th>具体健康状况：</th>
									<td><input class="jb" type="checkbox" id="healthSpecificc" /></td>
									<th>具体传染病史：</th>
									<td><input class="jb" type="checkbox" id="healthContagionc" /></td>
									<th>是否有精神病：</th>
									<td><input class="jb" type="checkbox" id="psychosisc" /></td>
								</tr>
								<tr>
									<th>心理是否健康：</th>
									<td><input class="jb" type="checkbox" id="healthMentalc" /></td>
									<th>具体心理健康状况：</th>
									<td><input class="jb" type="checkbox" id="healthMentalSpecificc" /></td>
									<th>心理鉴定机构：</th>
									<td><input class="jb" type="checkbox" id="accreditingOrganc" /></td>
									<th>政治面貌：</th>
									<td><input class="jb" type="checkbox" id="politicsStatusc" /></td>
									<th>原政治面貌：</th>
									<td><input class="jb" type="checkbox" id="politicsStatusOriginalc" /></td>
								</tr>
								<tr>
									<th>联系电话：</th>
									<td><input class="jb" type="checkbox" id="phoneNumberc" /></td>
									<th>户籍性质：</th>
									<td><input class="jb" type="checkbox" id="natureHomec" /></td>
									<th>居住地详细地址：</th>
									<td><input class="jb" type="checkbox" id="houseAddressc" /></td>
									<th>户籍详细地址：</th>
									<td><input class="jb" type="checkbox" id="homeAddressc" /></td>
									<th>文化程度：</th>
									<td><input class="jb" type="checkbox" id="educationDegreec" /></td>
								</tr>
								<tr>
									<th>婚姻状况：</th>
									<td><input class="jb" type="checkbox" id="maritalStatec" /></td>
									<th>就业就学情况：</th>
									<td><input class="jb" type="checkbox" id="workStatec" /></td>
									<th>现工作单位：</th>
									<td><input class="jb" type="checkbox" id="workNuitc" /></td>
									<th>单位联系电话：</th>
									<td><input class="jb" type="checkbox" id="workNuitPhonec" /></td>
									<th>护照号码：</th>
									<td><input class="jb" type="checkbox" id="passportc" /></td>
								</tr>
								<tr>
									<th>回乡证号码：</th>
									<td><input class="jb" type="checkbox" id="hrPermitc" /></td>
									<th>香港身份证号码：</th>
									<td><input class="jb" type="checkbox" id="hkIdentityc" /></td>
									<th>澳门身份证号码：</th>
									<td><input class="jb" type="checkbox" id="amIdentityc" /></td>
									<th>台胞证号码：</th>
									<td><input class="jb" type="checkbox" id="tbIdentityc" /></td>
									<th>港澳台通行证号码：</th>
									<td><input class="jb" type="checkbox" id="gatPermitc" /></td>
								</tr>
							</tbody>
						</table>
					</div>
 
					<div class="comm-div" id="xfxxjg" style="display: none;">
						<table class="comm-table1">
							<colgroup>
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
							</colgroup>
							<tbody>
								<tr>
									<th width="180px">矫正类别：</th>
									<td><input class="xf" type="checkbox" id="adjustTypec" /></td>
									<th>矫正期限：</th>
									<td><input class="xf" type="checkbox" id="adjustPeriodc" /></td>
									<th>矫正起始日期：</th>
									<td><input class="xf" type="checkbox" id="dateSAdjustc" /></td>
									<th>矫正结束日期：</th>
									<td><input class="xf" type="checkbox" id="dateEAdjustc" /></td>
									<th>犯罪类型：</th>
									<td><input class="xf" type="checkbox" id="crimeTypec" /></td>
								</tr>
								<tr>
									<th>具体罪名：</th>
									<td><input class="xf" type="checkbox" id="accusationc" /></td>
									<th>原判刑罚：</th>
									<td><input class="xf" type="checkbox" id="oriPunishmentc" /></td>
									<th>原判刑起始日期：</th>
									<td><input class="xf" type="checkbox" id="dateSOric" /></td>
									<th>原判刑结束日期：</th>
									<td><input class="xf" type="checkbox" id="dateEOric" /></td>
									<th>原判刑期：</th>
									<td><input class="xf" type="checkbox" id="oriPeriodc" /></td>
								</tr>
								<tr>
									<th>有期徒刑期限：</th>
									<td><input class="xf" type="checkbox" id="imprisonmentPeriodc" /></td>
									<th>附加刑：</th>
									<td><input class="xf" type="checkbox" id="addpunishmentc" /></td>
									<th>剥夺政治权利期限：</th>
									<td><input class="xf" type="checkbox" id="nonpoliticalPeriodc" /></td>
									<th>剥夺政治权利起始日期：</th>
									<td><input class="xf" type="checkbox" id="dateSNonpoliticalc" /></td>
									<th>剥夺政治权利结束日期：</th>
									<td><input class="xf" type="checkbox" id="dateENonpoliticalc" /></td>
								</tr>
								<tr>
									<th>接收剥夺政治权利人员公安机关名称：</th>
									<td><input class="xf" type="checkbox" id="receiveUnitc" /></td>
									<th>接收人：</th>
									<td><input class="xf" type="checkbox" id="receivePersonc" /></td>
									<th>移交日期：</th>
									<td><input class="xf" type="checkbox" id="dateTransferc" /></td>
									<th>是否“四史”：</th>
									<td><input class="xf" type="checkbox" id="sisTypec" /></td>
									<th>是否“三涉”：</th>
									<td><input class="xf" type="checkbox" id="sansTypec" /></td>
								</tr>
								<tr>
									<th>是否累犯：</th>
									<td><input class="xf" type="checkbox" id="isRecidivismc" /></td>
									<th>是否“三类犯罪”：</th>
									<td><input class="xf" type="checkbox" id="slfzTypec" /></td>
									<th>是否共同犯罪：</th>
									<td><input class="xf" type="checkbox" id="isalonec" /></td>
									<th>是否被宣告禁令：</th>
									<td><input class="xf" type="checkbox" id="isforbiddenc" /></td>
									<th>禁止令内容：</th>
									<td><input class="xf" type="checkbox" id="forbiddenc" /></td>
								</tr>
								<tr>
									<th>禁止令起始日期：</th>
									<td><input class="xf" type="checkbox" id="dateSForbiddenc" /></td>
									<th>禁止令结束日期：</th>
									<td><input class="xf" type="checkbox" id="dateEForbiddenc" /></td>
									<th>社区服刑人员接收日期：</th>
									<td><input class="xf" type="checkbox" id="dateReceivec" /></td>
									<th>社区服刑人员接收方式：</th>
									<td><input class="xf" type="checkbox" id="receiveTypec" /></td>
									<th>报到情况：</th>
									<td><input class="xf" type="checkbox" id="reportInfoc" /></td>
								</tr>
								<tr>
									<th>矫正小组人员组成情况：</th>
									<td><input class="xf" type="checkbox" id="groupInfoc" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="comm-div" id="jjxxjg" style="display: none;">
						<table class="comm-table1">
							<colgroup>
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
							</colgroup>
							<tbody>
								<tr>
									<th width="180px">解除/终止矫正原因：</th>
									<td><input class="jj" type="checkbox" id="removeReasonc" /></td>
									<th>解除/终止矫正日期：</th>
									<td><input class="jj" type="checkbox" id="removeDatec" /></td>
									<th>收监执行类型：</th>
									<td><input class="jj" type="checkbox" id="jailTypec" /></td>
									<th>收监执行日期：</th>
									<td><input class="jj" type="checkbox" id="jailDatec" /></td>
									<th>收监执行原因：</th>
									<td><input class="jj" type="checkbox" id="jailReasonc" /></td>
								</tr>
								<tr>
									<th>死亡日期：</th>
									<td><input class="jj" type="checkbox" id="deathDatec" /></td>
									<th>死亡原因：</th>
									<td><input class="jj" type="checkbox" id="deathReasonc" /></td>
									<th>具体死因：</th>
									<td><input class="jj" type="checkbox" id="deathReasonDetailc" /></td>
									<th>矫正期间表现：</th>
									<td><input class="jj" type="checkbox" id="performancec" /></td>
									<th>认罪态度：</th>
									<td><input class="jj" type="checkbox" id="mannerc" /></td>
								</tr>
								<tr>
									<th>是否参加职业技能培训：</th>
									<td><input class="jj" type="checkbox" id="istrainedc" /></td>
									<th>是否获得职业技能证书：</th>
									<td><input class="jj" type="checkbox" id="hascertificatec" /></td>
									<th>是否三无人员：</th>
									<td><input class="jj" type="checkbox" id="issanwuc" /></td>
									<th>危险性评估：</th>
									<td><input class="jj" type="checkbox" id="riskc" /></td>
									<th>家庭联系情况：</th>
									<td><input class="jj" type="checkbox" id="familyContactc" /></td>
								</tr>
								<tr>
									<th>特殊情况备注及帮教建议：</th>
									<td><input class="jj" type="checkbox" id="remarkc" /></td>
									<th>矫正期间表现鉴定意见：</th>
									<td><input class="jj" type="checkbox" id="opinionc" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="comm-div" id="jgxxjg" style="display: none;">
						<table class="comm-table1">
							<colgroup>
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 8%; text-align:center;" />
							</colgroup>
							<tbody>
								<tr>
									<th width="180px">当前佩戴电子监管设备：</th>
									<td><input class="jg" type="checkbox" id="isdeviceCodec" /></td>
									<th>佩戴电子监管设备时间范围：</th>
									<td><input class="jg" type="checkbox" id="wearTimec" /></td>
									<th>摘除电子监管设备时间范围：</th>
									<td><input class="jg" type="checkbox" id="releaseTimec" /></td>
									<th>报警类型：</th>
									<td><input class="jg" type="checkbox" id="alarmTypec" /></td>
									<th>报警时间范围：</th>
									<td><input class="jg" type="checkbox" id="alarmTimec" /></td>
								</tr>
							</tbody>
						</table>
					</div>


				</div>

				<table width="100%">
					<tbody>
						<tr>
							<td align="right" style="padding-left:10px;">
								<input type="button" class="bttn bicon-step" id="upBtn" value="上一步" onclick="up()" />
								<input type="button" class="bttn bicon-search" id="searchBtn" value="查询" onclick="changeDztj()" /> 
								<input type="button" class="bttn bicon-reset" id="resultBtn" value="统计" onclick="tongji()" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!--列表-->
		<div id="lb" style="display: none;">
		<div class="buttonArea operation">
      <input type="button" id="btnExcel" class="bttn bicon-excel"  value="导出" onclick="openExcel()"/>
  </div>
  <div id="search"></div>
  <div class="container-bottom">
	<table id="mainGrid">
	</table>
	<div id="mainGridPager"></div>
  </div>
  </div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<iframe id="downloadFrame" style="display:none" />
	<input type="hidden" id="isAlarm" name="isAlarm"/>
	<input type="hidden" id="isws" name="isws" value="${user.isws}"/>
</body>
</html>
