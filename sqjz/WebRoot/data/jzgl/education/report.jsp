<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集中初始教育数据统计</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="report.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"jzjy"});
		$.report.init("education");
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：集中教育 -> 集中初始教育数据统计</span>
		</div>
		<div class="container-top">
			<div id="search"></div>
		</div>
		<div class="container-bottom">
			<div id="buttons" class="buttonArea operation"></div>
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
		<div class="chart"></div>
		<div id="chart1"></div>
		<div id="chart2"></div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>