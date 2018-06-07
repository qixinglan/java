<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<%
	String fxryId = request.getParameter("fxryId");
	if (fxryId == null){
		fxryId = "";
	}
	String refreshValue = request.getParameter("refresh");
	if (refreshValue == null){
		refreshValue = "";
	}
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="ie-comp"/>
	<title>服刑人员信息管理-服刑人员基本信息登记</title>
	<%@ include file="/common/commonjs.jsp"%>
	
	<link rel="stylesheet" href="../style.css" />
	<%@ include file="../getjs.jsp"%>
	<script language="javascript" src="baseinfo.js" type="text/javascript"></script>
	<script language="javascript" src="${ctx}/js/custom/superviseDevice.js" type="text/javascript"></script>
	<script language="javascript" src="${ctx}/js/custom/fingerPrint.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav"> <span>您当前的位置：矫正管理 -> 社区服刑人员档案管理 -> 司法局服刑人员基本信息登记</span> </div>
		<div class="container-top">
  		</div>
  		<div class="container-middle">
  			
  		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp" %>
	<script type="text/javascript">
		$(function() {
			refresh();
			$.menu.init("#menuDetail",{first:"sqjz",second:"jzgl"});
			var refreshValue = '<%=refreshValue%>';
			if(refreshValue=='1'){
			$.fxrybaseinfo.init("add", '<%=fxryId%>', {container:'.container-middle',printnotice:true});
			}
		});
		function refresh(){
			var url = window.location.href;
			if(url.indexOf("refresh")==-1){
				if(url.indexOf("?")==-1)
					window.location.href=url+"?refresh=1";
				else
					window.location.href=url+"&refresh=1";
			}
		}
	</script>
</body>
</html>
