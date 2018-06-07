<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据展示</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/homepage.js"></script>
<script language="javascript"
	src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script language="javascript">
$(function() {
	$.homepage.showReport('.main');
	});
</script>
</head>
<body
	style="background:url(${ctx}/images/index/content_bg.jpg) top repeat-x #DDEBF4;">
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：工作桌面 ->数据展示</span>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
