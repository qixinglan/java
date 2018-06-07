<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>签收情况</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		//表格初始化
		$("#mainGrid").jqGrid(
				{
					rowNum : 10,
					url : '${ctx}/data/tzgl/noticeReceive.action?id=<%=(String) request.getAttribute("id")%>',
					colNames : [ '', '通知名称', '通知接收单位', '签收状态','签收日期','签收人'  ],

					colModel : [ {
						name : 'id',
						index : 'id',
						hidden : true
					}, {
						name : 'name',
						index : 'name',
						width : 100,
						align : 'center'
					}, {
						name : 'orgId',
						index : 'orgId',
						width : 100,
						align : 'center',
						formatter : function(value, opts, rwdat) {
							return $.organization.formatter()(rwdat.orgId);
						}
					}, {
						name : 'status',
						index : 'status',
						width : 100,
						align : 'center',
						formatter : function(value, opts, rwdat) {
							if (rwdat.status == "2") {
								return "待签收";
							} else {
								return "已签收";
							}
						}
					}, {
						name : 'receivetime',
						index : 'receivetime',
						width : 100,
						align : 'center'
					}, {
						name : 'receivername',
						index : 'receivername',
						width : 50,
						align : 'center'
					} ],
					multiselect : false,
					pager : '#mainGridPager',
					sortname : '',
					sortorder : "",
				});
	});
</script>
</head>

<body>
	<!--列表-->
	<div class="container-bottom">
		<table id="mainGrid">
		</table>
		<div id="mainGridPager"></div>
	</div>
</body>
</html>
