<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'main.jsp' starting page</title>
  </head>
  <body>
  <%
  Object o=session.getAttribute("user") ;
  if(o==null){
  response.sendRedirect("login.jsp");
  }
  %>
    This is my <span style="color:red">main</span> page. <br>
  </body>
</html>
