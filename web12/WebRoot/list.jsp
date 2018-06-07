<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
  </head>
  <body>
<table width="60%" border="1" align="center">
<caption>员工信息表</caption>
<tr><th>姓名</th><th>薪水</th><th>下标</th><th>第几次迭代</th></tr>
<c:forEach var="e" items="${list}" varStatus="s">
<tr><td>${e.name}</td><td>${e.salary}</td><td>${s.index }</td><td>${s.count }</td></tr>
</c:forEach>
</table>
  </body>
</html>
