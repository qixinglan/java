﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>综合查询</title>
    	<%@ include file="/common/commonjs.jsp"%>
		<link rel="stylesheet" href="popup.css" />
    
		<script type="text/javascript">
			//选择定制条件
			function changeDztj(){
				//查询--Grid显示选定的查询列
				$.each($("#jg input"),function(i,n){
					if($(n).attr("checked")){
						$("#mainGrid").setGridParam().showCol($(n).attr("id"));
					}else{
						$("#mainGrid").setGridParam().hideCol($(n).attr("id"));
					}
				});
			}
			//重置定制条件
			function resetDztj(){
				//重置--表格重置显示前三列，定制条件选定前三个
				$("#chkAll").removeAttr("checked");
				$.each($("#jg input"),function(i,n){
					if(i<7){
						$(n).attr("checked","true");
						$("#mainGrid").setGridParam().showCol($(n).attr("id"));
					}else{
						$(n).removeAttr("checked");
					}
				});
			}
			$(function() {
				$.menu.init("#menuDetail",{first:"dzjg",second:"zhcx"});
				$.organization.combobox("#responseOrg", "responseOrg", ${user.wunit.bm}, {
			    	allowBlank : true,
					level : 10,
					showRoot : true,
					showItself : true,
					notShowType:"4,5,6,7,8,9",
					emptyText : '全部',
					//defValue:${user.wunit.bm},
					dropdownAutoWidth:false,
					multiSelected: false,
					width:'70%',
					attr:{searchType:"eq"}});
				$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#sex", "sex", "XB",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#healthMental", "healthMental", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#natureHome", "natureHome", "HJXZ",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#politicsStatus", "politicsStatus", "ZZMM",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#politicsStatusOriginal", "politicsStatusOriginal", "ZZMM",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#educationDegree", "educationDegree", "WHCD",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#maritalState", "maritalState", "HYZK",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#workState", "workState", "JYJX",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#crimeType", "crimeType", "FZLX",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#oriPunishment", "oriPunishment", "XFLX",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#addpunishment", "addpunishment", "FJX",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#sisType", "sisType", "SS",{attr:{searchType:"cn"},multiSelected: true,width:'70%'});
				$.dictionary.combobox("#sansType", "sansType", "SANS",{attr:{searchType:"cn"},multiSelected: true,width:'70%'});
				$.dictionary.combobox("#isRecidivism", "isRecidivism", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#isalone", "isalone", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#isforbidden", "isforbidden", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#receiveType", "receiveType", "JSFS",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#reportInfo", "reportInfo", "BDQK",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#isgroupInfo", "isgroupInfo", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#groupInfo", "groupInfo", "JZXZRYQK",{attr:{searchType:"cn"},multiSelected: true,width:'89%'});
				$.dictionary.combobox("#sfsxxxhgl", "sfsxxxhgl", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#isdeviceCode", "isdeviceCode", "SF",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#bjjb", "bjjb", "BJJB",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#bjlx", "bjlx", "BJLX",{attr:{searchType:"eq"},width:'70%'});
				//$.dictionary.combobox("#sfzdbj", "sfzdbj", "SFZDBJ",{attr:{searchType:"eq"}});
				$.dictionary.combobox("#jgzt", "dzjgzt", "DZJGZT",{attr:{searchType:"eq"},width:'70%'});
				$.dictionary.combobox("#sfzdtx", "sfzdtx", "SFZDBJ",{attr:{searchType:"eq"},width:'70%'});
				
				//表格初始化
				$("#mainGrid").jqGrid({
					autowidth : true,
					url		    : '${ctx}/data/dzjg/zhcx/search.action?orgId=${user.wunit.bm}',
					colNames	: [
							'查看详情','报警信息','执行机关','矫正类别','姓名','性别',
							'心理是否健康','户籍性质','政治面貌','原政治面貌',
							'文化程度','婚姻状况','就业就学情况','犯罪类型',
							'原判刑罚','附加刑','是否“四史”','是否“三涉”',
							'是否累犯','是否共同犯罪','是否被宣告禁止令','社区服刑人员接收日期',
							'社区服刑人员接收方式','报到情况','是否建立矫正小组','矫正小组人员组成情况','是否定位监控',''
					],
					
					colModel	: [
							{
								name : 'xq',
								index : 'xq',
								width : 80,
								align : 'center',
								fixed : true,
								formatter : function(value, opts, rwdat) {
									return "<a href='javascript:viewDetail(\""+rwdat.fxryId+"\");' title='' class='a-style'>查看详情</a>";
								}
							},
							{
								name : 'cz',
								index : 'cz',
								width : 80,
								align : 'center',
								fixed : true,
								formatter : function(value, opts, rwdat) {
									return "<a href='javascript:wclbjxx(\""+rwdat.name+"\",\""+rwdat.fxryId+"\");' title='' class='a-style'>查看</a>";
								}
							},{
						        name	: 'responseOrg',
						        index	: 'responseOrg',
						        width	: 140,
						        align : 'center',
						        sortable: true,
						        fixed :true,
						        formatter:function(value, opts, rwdat){
									return $.organization.formatter()(rwdat.responseOrg);
								}
					        }, 
					        {
						        name	: 'adjustType',
						        index	: 'adjustType',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZLB'),
						        sortable: true,
						        fixed :true
					        },
					        {
						        name	: 'name',
						        index	: 'name',
						        width	: 140,
						        align	: 'center',
						        fixed :true
					        },
					        {
						        name	: 'sex',
						        index	: 'sex',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('XB'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'healthMental',
						        index	: 'healthMental',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'natureHome',
						        index	: 'natureHome',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('HJXZ'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'politicsStatus',
						        index	: 'politicsStatus',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('ZZMM'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'politicsStatusOriginal',
						        index	: 'politicsStatusOriginal',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('ZZMM'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'educationDegree',
						        index	: 'educationDegree',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('WHCD'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'maritalState',
						        index	: 'maritalState',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('HYZK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'workState',
						        index	: 'workState',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JYJX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'crimeType',
						        index	: 'crimeType',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('FZLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'oriPunishment',
						        index	: 'oriPunishment',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('XFLX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'addpunishment',
						        index	: 'addpunishment',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('FJX'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'sisType',
						        index	: 'sisType',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'sansType',
						        index	: 'sansType',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SANS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'isRecidivism',
						        index	: 'isRecidivism',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'isalone',
						        index	: 'isalone',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'isforbidden',
						        index	: 'isforbidden',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'dateReceive',
						        index	: 'dateReceive',
						        width	: 160,
						        align	: 'center',
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'receiveType',
						        index	: 'receiveType',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JSFS'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'reportInfo',
						        index	: 'reportInfo',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BDQK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'isgroupInfo',
						        index	: 'isgroupInfo',
						        width	: 160,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },
					        {
						        name	: 'groupInfo',
						        index	: 'groupInfo',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('JZXZRYQK'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
						        name	: 'isdeviceCode',
						        index	: 'isdeviceCode',
						        width	: 140,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SF'),
						        sortable: true,
						        fixed :true,
						        hidden  : true
					        },{
								name : 'fxryId',
								index : 'fxryId',
								hidedlg : false,
								hidden : true
							}
					],
						multiselect : false,
						//sortname : "responseOrg,adjustType,name,sex,healthMental,natureHome,politicsStatus,politicsStatusOriginal,educationDegree,maritalState,workState,crimeType,oriPunishment,addpunishment,sisType,sansType,isRecidivism,isalone,isforbidden,dateReceive,receiveType,reportInfo,isgroupInfo,groupInfo",
						//sortorder : "desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc,desc",
						sortname : "responseOrg,adjustType,name",
						sortorder : "asc,asc,asc",
						pager: '#mainGridPager'
				});
				$("#mainGrid").bindSearchForm("#searchDiv","#searchBtn", "#resetBtn");
				//初始化--Grid显示前三列
				$.each($("#jg input"),function(i,n){
					if(i<7){
						$(n).attr("checked","true");
						$("#mainGrid").setGridParam().showCol($(n).attr("id")).trigger("reloadGrid");
					}
				});
				//初始化--checkbox状态
				$("#chkAll").click(function() {
					var chkedFlg = $(this).attr("checked");
					$("input:checkbox[name!=chkAll]").each(function() {
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
					} else {
						$("#divtj").removeClass("spanTurnOn");
						$("#divtj").addClass("spanTurnOff");
						$("#atj").text("\u6536\u8d77");
						$("#tj").show();
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
    				height: '500px', 
    				title: '详细信息查看',
				    content: 'url:${ctx}/data/jzgl/dagl/zhcxview.jsp?id='+id,
    				cancelVal: '关闭', 
    				cancel: true 
				});
			}
			//导出
			function openExcel(){
				//var colNames = $("#mainGrid").getGridParam("colNames");
				//var sortName = $("#mainGrid").getGridParam("sortname");
				//window.open("${ctx }/data/dzjg/zhcx/excel.action?colNames=" + colNames
				//		+ "&sortName=" + sortName, "downloadFrame");
				
				var grid = $("#mainGrid").getGridParam('postData') || {};
				var data = {};
				for (var i in grid){
					data["jqgrid."+i] = grid[i];
				}
				var t = data["jqgrid.cols"].split(",").slice(2);
				t.pop();
				data["jqgrid.cols"]=t.join(",");
				var url = "${ctx }/data/dzjg/zhcx/excel.action?" + $.param(data);
				$("#downloadFrame").attr("src",url);
				
			}
		</script>
		</head>

<body>
<%@include file="/data/top.jsp"%>
<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：电子监管 -> 综合查询</span> </div>
  <div class="container-top" >

    <div class="contentWrapper">
      
        <div class="operation-box container-main">
          <div class="title"> <span></span>
            <label>查询条件定制：</label>
            <div id="divtj" class="spanTurnOff" style="float: right;margin-right: 20px;"><a id="atj">收起</a></div>
          </div>
          <div class="comm-div" id="tj">
				    <table class="comm-table" id="searchDiv">
							<colgroup>
								<col style="width: 20%; white-space:normal;" />
								<col style="width: 30%; white-space:nowrap;" />
								<col style="width: 20%; white-space:normal;" />
								<col style="width: 30%; white-space:nowrap;" />
							</colgroup>
				      <tbody>
				        <tr>
				          <th colspan="4" style="text-align:left;">&nbsp;&nbsp;社区服刑人员信息：</th>
				        </tr>
				        <tr>
				          <th>执行机关：</th>
				          <td id="responseOrg">
				          		
				          </td>
				          <th>矫正类别：</th>
				          <td id="adjustType">
				          </td>
				        </tr>
				        <tr>
				          <th>姓名：</th>
				          <td>
				          		<input type="text" id="name" name="name" searchType="cn"  style="width: 65%; background:white"/>
				          		
				          </td>
				          <th>性别：</th>
				          <td id="sex">
				          		
				          </td>
				        </tr>
				        <tr>
				          <th>心理是否健康：</th>
				          <td id="healthMental">
				          </td>
				          <th>户籍性质：</th>
				          <td id="natureHome">
				          </td>
				        </tr>
				        <tr>
				          <th>政治面貌：</th>
				          <td id="politicsStatus">
				          		
				          </td>
				          <th>原政治面貌：</th>
				          <td id="politicsStatusOriginal">
				          		
				          </td>
				        </tr>
				        <tr>
				          <th>文化程度：</th>
				          <td id="educationDegree">
				          		
				          </td>
				          <th>婚姻状况：</th>
				          <td id="maritalState">
				          </td>
				        </tr>
				        <tr>
				          <th>就业就学情况：</th>
				          <td id="workState">
				          		
				          </td>
				          <th>犯罪类型：</th>
				          <td id="crimeType">
				          </td>
				        </tr>
				        <tr>
				          <th>原判刑罚：</th>
				          <td id="oriPunishment">
				          </td>
				          <th>附加刑：</th>
				          <td id="addpunishment">
				          </td>
				        </tr>
				        <tr>
				          <th>是否“四史”：</th>
				          <td id="sisType">
				          </td>
				          <th>是否“三涉”：</th>
				          <td id="sansType">
				          </td>
				        </tr>
				        <tr>
				          <th>是否累犯：</th>
				          <td id="isRecidivism">
				          </td>
				          <th>是否共同犯罪：</th>
				          <td id="isalone">
				          </td>
				        </tr>
				        <tr>
				          <th>是否被宣告禁止令：</th>
				          <td id="isforbidden">
				          </td>
				          <th>社区服刑人员接收日期：</th>
				          <td>
				          		<input id="dateReceive" name="dateReceive" type="text" searchType="eq"
							class="Wdate inputStyle" style="width: 65%"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
				          </td>
				        </tr>
				        <tr>
				          <th>社区服刑人员接收方式：</th>
				          <td id="receiveType">
				          </td>
				          <th>报到情况：</th>
				          <td id="reportInfo">
				          </td>
				        </tr>
				        <tr>
				          <th>是否建立矫正小组：</th>
				          <td id="isgroupInfo">
				          </td>
				          <th>是否定位监控：</th>
				          <td id="isdeviceCode">
				          </td>
				        </tr>
				        <tr>
				          <th>矫正小组人员组成情况：</th>
				          <td id="groupInfo" colspan="3">
				          </td>
				        </tr>
				      </tbody>
				    </table>
          </div>
        </div>


        <div class="operation-box container-main">
          <div class="title"> <span></span>
            <label>查询结果定制：<input type="checkbox" id="chkAll" name="chkAll"/>&nbsp;全选</label>
            <div id="divjg" class="spanTurnOff" style="float: right;margin-right: 20px;"><a id="ajg">收起</a></div>
          </div>
          <div class="comm-div"  id="jg">

				    <table class="comm-table">
							<colgroup>
								<col style="width: 12%; " />
								<col style="width: 13%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 13%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 13%; text-align:center;" />
								<col style="width: 12%; " />
								<col style="width: 13%; text-align:center;" />
							</colgroup>
				      <tbody>
				        <tr>
				          <th>执行机关：</th>
				          <td>
				          		<input type="checkbox" id="responseOrg"/>
				          </td>
				          <th>矫正类别：</th>
				          <td>
				          		<input type="checkbox" id="adjustType"/>
				          </td>
				          <th>姓名：</th>
				          <td>
				          		<input type="checkbox" id="name"/>
				          </td>
				          <th>性别：</th>
				          <td>
				          		<input type="checkbox" id="sex"/>
				          </td>
				        </tr>
				        <tr>
				          <th>心理是否健康：</th>
				          <td>
				          		<input type="checkbox" id="healthMental"/>
				          </td>
				          <th>户籍性质：</th>
				          <td>
				          		<input type="checkbox" id="natureHome"/>
				          </td>
				          <th>政治面貌：</th>
				          <td>
				          		<input type="checkbox" id="politicsStatus"/>
				          </td>
				          <th>原政治面貌：</th>
				          <td>
				          		<input type="checkbox" id="politicsStatusOriginal"/>
				          </td>
				        </tr>
				        <tr>
				          <th>文化程度：</th>
				          <td>
				          		<input type="checkbox" id="educationDegree"/>
				          </td>
				          <th>婚姻状况：</th>
				          <td>
				          		<input type="checkbox" id="maritalState"/>
				          </td>
				          <th>就业就学情况：</th>
				          <td>
				          		<input type="checkbox" id="workState"/>
				          </td>
				          <th>犯罪类型：</th>
				          <td>
				          		<input type="checkbox" id="crimeType"/>
				          </td>
				        </tr>
				        <tr>
				          <th>原判刑罚：</th>
				          <td>
				          		<input type="checkbox" id="oriPunishment"/>
				          </td>
				          <th>附加刑：</th>
				          <td>
				          		<input type="checkbox" id="addpunishment"/>
				          </td>
				          <th>是否“四史”：</th>
				          <td>
				          		<input type="checkbox" id="sisTypec"/>
				          </td>
				          <th>是否“三涉”：</th>
				          <td>
				          		<input type="checkbox" id="sansType"/>
				          </td>
				        </tr>
				        <tr>
				          <th>是否累犯：</th>
				          <td>
				          		<input type="checkbox" id="isRecidivism"/>
				          </td>
				          <th>是否共同犯罪：</th>
				          <td>
				          		<input type="checkbox" id="isalone"/>
				          </td>
				          <th>是否被宣告禁止令：</th>
				          <td>
				          		<input type="checkbox" id="isforbidden"/>
				          </td>
				          <th>社区服刑人员接收日期：</th>
				          <td>
				          		<input type="checkbox" id="dateReceive"/>
				          </td>
				        </tr>
				        <tr>
				          <th>社区服刑人员接收方式：</th>
				          <td>
				          		<input type="checkbox" id="receiveType"/>
				          </td>
				          <th>报到情况：</th>
				          <td>
				          		<input type="checkbox" id="reportInfo"/>
				          </td>
				          <th>是否建立矫正小组：</th>
				          <td>
				          		<input type="checkbox" id="isgroupInfo"/>
				          </td>
				          <th>是否定位监控：</th>
				          <td>
								<input type="checkbox" id="isdeviceCode"/>
				          </td>
				        </tr>
				        <tr>
				          <th>矫正小组人员组成情况：</th>
				          <td>
				          		<input type="checkbox" id="groupInfo"/>
				          </td>
				          <th colspan="6"></th>
				        </tr>
				        <tr>
				        </tr>
				      </tbody>
				    </table>
          </div>
        </div>
    </div>
    <table width="100%">
      <tbody>
        <tr>
          <td align="right">
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询" onclick="changeDztj()"/>
          </td>
          <td align="left" style="padding-left:10px;">
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置" onclick="resetDztj()"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表-->
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
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
<iframe id="downloadFrame" style="display:none"/>
</body>
</html>
