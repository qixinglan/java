<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'apply.jsp' starting page</title>
  </head>
  <body>
  <form action="apply.do" method="post">
  账号：<input name="username"/>${error1}<br>
  金额：<input name="money"/> ${error2}<br>
  <input type="submit" value="提交申请" />
  </form>
  </body>
</html>
