<%@ page language="java" import="java.util.*,bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%User user=new User();
     user.setAge(25);
     user.setName("靓仔");
     user.setInterest(new String[]{"游泳","跑步"});
     request.setAttribute("users", user);%> 
     
     <%//User u=(User)request.getAttribute("users"); 
     //out.print(u.getName());%>
    
      <%User user1=new User();
     user1.setAge(26);
     user1.setName("哈啊哈");
     session.setAttribute("users", user1);%>
     interest:${users.interest[0]}
     name(request):${users.name} <br/>
     name(session):${sessionScope.users.name}<br/>
    name:${users["name"] }<br/>
    <%request.setAttribute("city", "name") ;%>
    name:${users[city]}
   
  </body>
</html>
