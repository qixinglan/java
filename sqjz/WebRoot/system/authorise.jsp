<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统授权</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="authorisejs.jsp"%>
<script type="text/JavaScript">
	$(function() {
		$.authorise.init('#search');
	});
</script>
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="0" class="logo">
		<tr>
			<td align="left"><img src="${ctx}/images/index/top_left.jpg" /></td>
			<td align="right"><img src="${ctx}/images/index/top_right.jpg" /></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" class="yiji_cl">
		<tr>
			<td></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr class="wel">
			<td><div class="erji_div" style="cursor: default;"></div></td>
		</tr>
	</table>
	<div class="main">
  <div class="breadcrumb-nav"><span>您当前的位置：系统授权</span> </div>
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
	<%@include file="/data/bottom.jsp"%>
</body>
</html>