<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
	String id = request.getParameter("id");
	String returnurl=request.getParameter("returnurl");
	if (id == null || id.isEmpty()){
		response.sendError(404);
	}
	if (returnurl == null || returnurl.isEmpty()){
		returnurl = "zjry";
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>服刑人员信息管理-社区服刑人员档案</title>
	<%@ include file="/common/commonjsTemp.jsp"%>
	<link rel="stylesheet" href="style.css" />
	<script language="javascript" src="${ctx}/data/jzgl/dagl/config.js"></script>
	<script language="javascript" src="moreinfo/address.js" type="text/javascript"></script>
	<script language="javascript" src="moreinfo/moreinfo.js" type="text/javascript"></script>
	<script language="javascript" src="legal/instrument.js" type="text/javascript"></script>
	<script language="javascript" src="executeinfo/executeinfo.js" type="text/javascript"></script>
	<script language="javascript" src="release/fxryrelease.js" type="text/javascript"></script>
	<script language="javascript" src="rewardpunish/rewardpunish.js" type="text/javascript"></script>
	<script language="javascript" src="zhcxfxry.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$.fxry.init('view', '<%=id%>', {returnurl:'<%=returnurl%>'});
		});
	</script>
</head>
<body>
	<div class="main">
		<div class="container-top">
		</div>
	   <div class="container-middle">
	   </div>
	   <div class="container-bottom">
	  </div>
	</div>
</body>
</html>