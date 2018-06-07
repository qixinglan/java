<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String sfsId = request.getParameter("sfsId");
	String qxsfjId = request.getParameter("qxsfjId");
	String OrgId = request.getParameter("orgId");
	String type = request.getParameter("type");
	String reportId = request.getParameter("reportId");
	if(type == null || type.isEmpty()){
		response.sendError(404);
	}
	if(reportId == null || reportId.isEmpty()){
		response.sendError(404);
	}
	if ((sfsId == null || sfsId.isEmpty())
			&&(qxsfjId == null || qxsfjId.isEmpty())
			&&(OrgId == null || OrgId.isEmpty())){
		response.sendError(404);
	}
	if (qxsfjId == null || qxsfjId.isEmpty()){
		qxsfjId="";
	}
	if (sfsId == null || sfsId.isEmpty()){
		sfsId="";
	}
	if (OrgId == null || OrgId.isEmpty()){
		OrgId="";
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>督察项详细信息</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/jzgl/cxtj/dacx.js"></script>
<script language="javascript" src="${ctx}/data/zfdc/js/supervision.list.js"></script>
<script type="text/javascript">
	$(function() {
		$.supervision.list.init("<%=type%>",{
			sfsId:"<%=sfsId%>",
			qxsfjId:"<%=qxsfjId%>",
			orgId:"<%=OrgId%>",
			reportId:"<%=reportId%>"}
		);
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：执法督察 -> <a id="dcx"></a> -> <span id="title" style="padding-left:0"></span></span>
		</div>
		<div class="container-top">
		</div>
		<div class="container-middle">
			<div id="search"></div>
		</div>
		<!--列表-->
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>