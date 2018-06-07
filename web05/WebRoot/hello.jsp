<%@page import="java.util.*"  contentType="text/html;charset=utf-8"  pageEncoding="utf-8"%>
<html>
<head></head>
<body style="font-size:30px;color:red">
current time:
<%
Date date=new Date();
out.write(date.toString());
out.println(request.getRequestURI());
 %>
当前系统时间:<%=new Date() %>
<%
	for(int i=0;i<100;i++){
%>
	hello world<br>
<% 
	}
%>
</body>
</html>