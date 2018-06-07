<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员实力统计</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"bggl",second:"org"});
		$.dictionary.combobox("#rylb", "persontype", "RYLB", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			fieldClass:"search-form-field"
		});
		var seachOrg=$("#seachOrg").val();
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/jggl/perSearch.action?',
							mtype : "POST",
							datatype : 'json',
							colModel : [ {
								name : 'id',
								index : 'id',
								label : 'id',
								hidedlg : false,
								hidden : true
							}, {
								name : 'org.orgId',
								index : 'org.orgId',
								searchType : 'cn',
								label : '工作单位',
								width : 120,
								sortable:true,
								searchOption: {
						        	useCOrg: true, 
									showRoot:true,
									level:5,
									defValue:seachOrg||'',
									allowSearch: true
						        },
								formatter : 'organization',
								align : 'left',
							}, {
								name : 'name',
								index : 'name',
								searchType : 'cn',
								label : '姓名',
								width : 50,
								sortable:true,
								align : 'center'
							}, {
								name : 'oldorg',
								index : 'oldorg',
								searchType : 'cn',
								label : '原工作单位',
								width : 100,
								sortable:true,
								align : 'left'
							}, {
								name : 'sex',
								index : 'sex',
								label : '性别',
								searchType : 'eq',
								width : 20,
								sortable:true,
								formatter : 'dictionary',
								formatoptions : {
									code : 'XB'
								},
								align : 'center'
							}, {
								name : 'birthday',
								index : 'birthday',
								searchType : 'in',
								searchInput : 'date',
								label : '出生日期',
								width : 50,
								sortable:true,
								align : 'center'
							},

							{
								name : 'polityvisage',
								index : 'polityvisage',
								searchType : 'eq',
								label : '政治面貌',
								width : 40,
								sortable:true,
								formatter : 'dictionary',
								formatoptions : {
									code : 'ZZMM'
								},
								align : 'center',
							}, {
								name : 'degree',
								index : 'degree',
								searchType : 'eq',
								width : 30,
								label : '学历',
								sortable:true,
								formatter : 'dictionary',
								formatoptions : {
									code : 'WHCD'
								},
								align : 'center'
							},  {
								name : 'persontype',
								index : 'persontype',
								width : 90,
								searchType : 'eq',
								sortable:true,
								label : '人员类别',
								formatter : 'dictionary',
								formatoptions : {
									code : 'RYLB'
								},
								align : 'left'
							},  {
								name : 'duty',
								index : 'duty',
								width : 40,
								searchType : 'eq',
								sortable:true,
								label : '职务职级',
								formatter : 'dictionary',
								formatoptions : {
									code : 'ZWZJ'
								},
								align : 'left'
							},{
								name : 'phone',
								index : 'phone',
								width : 50,
								sortable:true,
								label : '手机',
								align : 'center'
							} ],
							sortNames : 'org.cname,name,oldorg,sex,birthday,polityvisage,degree,duty,persontype,phone',
							rownumbers : true,
							sortname : 'org.orgId,name',
							sortorder : "asc,asc",
							pager : '#mainGridPager',
							multiselect : false,
							gridComplete:function(){
								var seachOrg=$("#seachOrg").val();
								if(seachOrg){
									$("#searchDiv_searchBtn").click();
									$("#seachOrg").val("");
								}
							}
						});
		$("#mainGrid").addSearchForm("#searchDiv",{rows:"3"});
	});
	function download(url) {
		var colNames = $("#mainGrid").getGridParam("colNames");
		var sortNames = $("#mainGrid").getGridParam("sortNames");
		var filters = $("#mainGrid").getGridParam("postData").filters;
		url = "${ctx}/data/jggl/excel.action?colNames=" + colNames
				+ "&sortNames=" + sortNames+"&filters="+filters;
		if ($("#downloadcsv").length <= 0)
			$("body")
					.append(
							"<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
		$("#downloadcsv").attr("src", url);
	}
	function back() {
		window.location = "${ctx}/data/jggl/list.action";
	}
</script>
</head>
<body onload="">
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<input type="hidden" id="org" value="${org}" />
		<input type="hidden" id="seachOrg" value="${seachOrg}" />
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 矫正机构管理->人员实力查询</span>
		</div>
		<div id="searchDiv" class="container-top">
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" id="btnExcel" class="bttn bicon-excel" onclick="download('');"
				value="导出" />
			<input type="button" id="btnReturn" class="bttn bicon-return" value="返回"
				onclick="javascript:back();" />
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>