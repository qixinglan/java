<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作日志</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"xtgl",second:"log"});
		//表格初始化
		$("#mainGrid").jqGrid({
			url : '${ctx}/sys/xtrz/search.action',
			mtype : "POST",
			datatype : 'json',
			colModel : [ {
				name : 'id',
				index : 'id',
				label : 'id',
				hidedlg : false,
				hidden : true
			},{
				name : 'orgId',
				index : 'orgId',
				label : '操作单位',
				formatter: 'organization',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				name : 'yhm',
				index : 'yhm',
				label : '操作用户',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				name : 'mkm',
				index : 'mkm',
				label : '操作模块',
				width : 100,
				sortable : true,
				align : 'center'
			}, {
				name : 'rzlx',
				index : 'rzlx',
				label : '日志类型',
				width : 40,
				formatter : 'select',
				editoptions : {
					value : "0:登陆日志;1:操作日志"
				},
				sortable : true,
				align : 'center'
			}, {
				name : 'czsj',
				index : 'czsj',
				label : '操作时间',
				width : 100,
				sortable : true,
				align : 'center'
			} ],
			multiselect : false,
			rownumbers : true,
			sortname : 'czsj',
			sortorder : "desc",
			pager : '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：系统管理 -> 系统日志</span>
		</div>
		<div id="searchDiv" class="container-top">
			<table class="search-table">
				<tbody>
					<tr>
						<th>操作用户</th>
						<td><input type="text" name="yhm" searchType="cn" style="width: 340px;"/></td>
						<td><input type="button" id="searchBtn"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
						<th>操作时间：</th>
						<td><input class="Wdate" type="text" style="width: 150px;"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="16"
							name="czsj" searchType="ge" /> 至 <input class="Wdate"
							type="text" style="width: 150px;"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" maxlength="16"
							name="czsj" searchType="dle" /></td>
						<td><input type="button" id="resetBtn"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
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
