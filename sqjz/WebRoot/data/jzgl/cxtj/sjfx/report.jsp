<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员档案管理</title>
<%@ include file="/common/commonjs.jsp"%>
<%
	String opt = request.getParameter("opt");
	String y = request.getParameter("y");
	if(opt==null||"".equals(opt)){
		opt="fzlx";
	}
%>
<script language="javascript" src="report.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"cxtj",third : "sjfx"});
		var opt = '<%=opt%>';
		$("#tab-"+opt).addClass("tabpage-label-selected");
		$.report.init(opt);
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：矫正管理 -> 查询统计 ->数据分析</span>
		</div>
		<div class="container-top">
			<div>
				<ul class="tabpage-label-container">
					<a href="report.jsp?opt=fzlx"><li id="tab-fzlx" class="tabpage-label">犯罪类型分析</li>
					<a href="report.jsp?opt=jyjx"><li id="tab-jyjx" class="tabpage-label">就业就学分析</li></a>
					<a href="report.jsp?opt=whcd"><li id="tab-whcd" class="tabpage-label">文化程度分析</li></a>
					<a href="report.jsp?opt=nld"><li id="tab-nld" class="tabpage-label">年龄段分析</li></a>
					<a href="report.jsp?opt=hjxz"><li id="tab-hjxz" class="tabpage-label">户籍性质分析</li></a>
					<a href="report.jsp?opt=jzlx"><li id="tab-jzlx" class="tabpage-label">矫正类型分析</li></a>
					<a href="report.jsp?opt=rysl"><li id="tab-rysl" class="tabpage-label">人员数量趋势</li></a>
				</ul>
				<div style="clear:both;"></div>
			</div>
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
