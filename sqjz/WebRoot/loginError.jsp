<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>CA登录异常</title>
<style>
body{
	font-size:12px;
	font-color:black;
}
</style>
<%
	String message = (String)request.getAttribute("message");
	message = (message == null || message.isEmpty()) ? "CA登录失败，请联系各自机构的管理员" : message;
%>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript">
var leftTime = 10;
function go(){
   document.getElementById("num").innerText = leftTime;
   if(leftTime>0)leftTime--;
   if(leftTime<= 0){
     	window.opener=null;
	window.open('','_self','');
	window.close();
   }
}$(function(){
	$("#title").text('<%=message%>');
	document.getElementById("num").innerText = leftTime;
	setInterval(go,1000);
});
</script>
</head>
<body>
<span id="title"></span>，<span id="num"></span>秒后关闭窗口！
</body>
</html>
