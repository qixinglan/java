<%@ page language="java" import="java.util.*,web.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>

    这是首页 <br>
    当前系统在线人数<%=application.getAttribute("count") %>
  </body>
</html>
