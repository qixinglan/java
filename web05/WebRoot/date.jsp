<%@ page language="java"  import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.*"%>
<html>
<head>
</head>
<body>
	<%
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String str = time.format(date);
	%>
	当前系统日期是：<%=str%>
</body>
</html>
