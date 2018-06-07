<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="top">
<!--左侧列表开始-->
<table cellpadding="0" cellspacing="0" width="100%" background="${ctx}/images/top_mid.png">
    <tr><td width="28%"><img src="${ctx}/images/top_left.png" width="457" height="79" /></td> 
	    <td width="40%" style="background:url(${ctx}/images/top_mid.png) repeat-x;"></td> 
	    <td width="32%" align="right" style="padding-right:30px;"><a href="http://10.5.36.28:8680/wtsj/yth/wt/login.action?hlhusername=${user.yhm}&hlhpwd=${user.mm}" target="_bank"><img src="${ctx}/images/fankui.gif" border="0"/></a></td>
    </tr></table>
<!--左侧列表结束-->

</div>
</body>
</html>
