<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员档案管理</title>
<%@ include file="/common/commonjs.jsp"%>
<%
	String id = request.getParameter("orgId");
	if (id == null || id.isEmpty()) {
		response.sendError(404);
	}
%>
<script language="javascript" src="${ctx}/data/zfdc/js/supervision.report.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<script language="javascript"
	src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">
	$(function() {
		$("#mynav0").removeClass("navnew-c");
		$("#zfdc").addClass("navnew-c");
		$.supervision.report.init("#homepage",{orgId:"<%=id%>"});
	});
</script>
</head>
<body
	style="background:url(${ctx}/images/index/content_bg.jpg) top repeat-x #DDEBF4;">
	<%@include file="/data/top.jsp"%>
	<div style="width: 95%;margin: 0 auto;">
		<div class="breadcrumb-nav">
			<span id="title">您当前的位置：执法督察 -></span>
		</div>
	</div>
	<table cellspacing="0" cellpadding="0" class="homecontent"
		id="homepage">
	</table>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
