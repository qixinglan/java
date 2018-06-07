<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'form.jsp' starting page</title>
  </head>
  
  <body>
    <form action="form" enctype="multipart/form-data" method="post">
    用户名:<input name="username"/><br>
    <input name="username2"/><br>
    文件：<input type="file" name="filename" /><br>
    <input type="submit" >
    </form>
  </body>
</html>
