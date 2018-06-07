<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作成功</title>
<link href="${ctx }/css/link.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var sec = 1;
var timer = setInterval("count()", 1000);	
function count() {
	sec--;
	if(sec == 0){
		clearInterval(timer);
		//重新加载数据
		//window.opener.location.reload();
		//window.opener.document.forms['selall'].submit();
		window.opener.document.forms[0].submit();
		window.opener=null;
		window.open('','_self');
		window.close();
	}
}
window.resizeTo(800,530);
</script>
<style type="text/css">
<!--
body {
	background-color: #E7F2FF;margin:0px; padding:0; width:100%; height:100%;
}
-->
</style>
</head>
<body>
<img src="${ctx }/images/liangmiao_fanhui.gif" alt="" width="764" height="375" />
</body>
</html>
