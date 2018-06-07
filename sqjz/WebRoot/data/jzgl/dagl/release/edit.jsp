<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
	String fxryId = request.getParameter("fxryId");
	String returnurl=request.getParameter("returnurl");
	if (returnurl == null || returnurl.isEmpty()){
		returnurl="";
	}
	if (fxryId == null || fxryId.isEmpty()){
		response.sendError(404);
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>服刑人员信息管理-人员解除矫正</title>
	<%@ include file="/common/commonjs.jsp"%>
	
	<link rel="stylesheet" href="../style.css" />
	<script language="javascript" src="../config.js"></script>
	<script language="javascript" src="../baseinfo/baseinfo.js" type="text/javascript"></script>
	<script language="javascript" src="fxryrelease.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav"> <span>您当前的位置：矫正管理 -> 社区服刑人员档案管理 -> 人员解除矫正</span> </div>
		<div class="container-top">
  		</div>
  		<div class="container-middle">
  			
  		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp" %>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"sqjz",second:"jzgl"});
			var opt={container:'.container-middle'};
			var fxryId='<%=fxryId%>';
			if('<%=returnurl%>'){
				opt.listPage='<%=returnurl%>';
			}
			$.fxryrelease.init("edit", fxryId, opt);
		});
	</script>
</body>
</html>
