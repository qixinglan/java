<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'font.jsp' starting page</title>

  </head>
  
  <body>
    <form action="comment" method="post">
    评论内容：<input name="content"/><br>
    <input type="submit">
    </form>
  </body>
</html>
