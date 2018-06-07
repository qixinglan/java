<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>社区服刑人员档案管理</title>
	<%@ include file="/common/commonjs.jsp"%>
	
	<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
	<%@ include file="getjs.jsp"%>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"sqjz",second:"jzgl"});
			$.fxrytabpage.init('tgry');
		});
	</script>
</head>
 
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：矫正管理 -> 社区服刑人员档案管理</span> </div>
  <div class="container-top">
  	<%@include file="tab.jsp" %>
  	<script type="text/javascript">
		$("#tab-tgry").addClass("tabpage-label-selected");
	</script>
  </div>
    <div class="tabpage">
	  <div class="container-middle">
	  	<div id="search"></div>
	  </div>
	  <div class="container-bottom">
	  	<div id="buttons" class="buttonArea operation">
	  	</div>
	    <table id="mainGrid">
	    </table>
	    <div id="mainGridPager"></div>
	  </div>
  </div>
</div>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp" %>
</body>
</html>
