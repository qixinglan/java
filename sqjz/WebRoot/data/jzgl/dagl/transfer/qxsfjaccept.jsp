<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
	String fxryId = request.getParameter("fxryId");
	String returnurl=request.getParameter("returnurl");
	if (returnurl == null || returnurl.isEmpty()){
		returnurl = "rcgztx.jsp";
	}
	if (fxryId == null || fxryId.isEmpty()){
		response.sendError(404);
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>服刑人员信息管理-人员接收</title>
	<%@ include file="/common/commonjs.jsp"%>
	
	<link rel="stylesheet" href="../style.css" />
	<script language="javascript" src="../config.js"></script>
	<script language="javascript" src="qxsfjtransfer.js" type="text/javascript"></script>
	<script language="javascript" src="../baseinfo/baseinfo.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav"> <span>您当前的位置：矫正管理 -> 社区服刑人员档案管理 -> 人员接收</span> </div>
		<div class="container-top">
  		</div>
  		<div class="container-middle">
  			
  		</div>
 		 <div class="container-bottom"></div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp" %>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"sqjz",second:"jzgl"});
			$.fxrytransfer.init("edit", "<%=fxryId%>", {container:'.container-middle','returnurl':'<%=returnurl%>'});
		});
	</script>
</body>
</html>
