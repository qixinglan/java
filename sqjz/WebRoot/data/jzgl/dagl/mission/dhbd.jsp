﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员档案管理</title>
<%@ include file="/common/commonjs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<%@ include file="/data/jzgl/dagl/getjs.jsp"%>
<script language="javascript" src="${ctx}/data/jzgl/dagl/mission/mission.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.dailymission.init('dhbd');
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：工作桌面 -> 电话报告</span>
		</div>
		<div class="container-top"></div>
		<div class="tabpage">
			<div class="container-middle">
				<div id="search"></div>
			</div>
			<div class="container-bottom">
				<div id="buttons" class="buttonArea operation"></div>
				<table id="mainGrid">
				</table>
				<div id="mainGridPager"></div>
			</div>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
