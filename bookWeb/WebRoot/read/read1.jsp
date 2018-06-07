<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>试读</title>
<style type="text/css">
body {
	background-color: #E3E6E8;
	text-align: center;
}
</style>

</head>

<body>
<%Book book=(Book)session.getAttribute("book");
%>
<p><%=book.getBname() %></p>
<p></p>
<div style="text-align: left";><a href="http://127.0.0.1:8081/bookWeb/main/main.jsp"  >主页</a></div>
  

</p> <iframe src="<%=book.getBookadd()%>1.html" style="height:1000;width:1000" scrolling="no" frameborder="0"></iframe>
<div ><a style="text-align:left" href="http://127.0.0.1:8081/bookWeb/main/main.jsp"  >返回 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">顶部</a></div>
</body>
</html>
