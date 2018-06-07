<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  <style type="text/css">
	h1,h2,h3,h4,h5,h6,p{padding:0px;margin:0px}
	div{width:190px;margin:0 autu;border:1px solid gray;padding:6px;}
	div h2{background:black;color:white;padding:2px;text-align:center}
	div p{background:gray}
	div h3,div h4{background:gray}
	div span{color:red}
	p input{width:80px}
	</style>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
  <div>
   <c:url var="action" value="/login/login-action1.form"></c:url>
   <form action="${action}" method="post">
   <h2>登录</h2>
   <h3><span></span></h3>
   <p>用户:<input type="text" name="name"><span></span></p>
   <p>密码:<input type="password" name="pwd"><span></span></p>
   <h4><input type="submit" value="登录"></h4>
   </form>
   </div>
   <div>
   <c:url var="action" value="/login/login-action2.form"></c:url>
   <form action="${action}" method="post">
   <h2>登录</h2>
   <h3><span></span></h3>
   <p>用户:<input type="text" name="name"><span></span></p>
   <p>密码:<input type="password" name="pwd"><span></span></p>
   <h4><input type="submit" value="登录"></h4>
   </form>
   </div>
   <div>
   <c:url var="action" value="/login/login-action3.form"></c:url>
   <form action="${action}" method="post">
   <h2>登录</h2>
   <h3><span></span></h3>
   <p>用户:<input type="text" name="name"><span></span></p>
   <p>密码:<input type="password" name="pwd"><span></span></p>
   <h4><input type="submit" value="登录"></h4>
   </form>
   </div>
   <div>
   <c:url var="action" value="/login/login-action4.form"></c:url>
   <form action="${action}" method="post">
   <h2>登录</h2>
   <h3><span></span></h3>
   <p>用户:<input type="text" name="name"><span></span></p>
   <p>密码:<input type="password" name="pwd"><span></span></p>
   <h4><input type="submit" value="登录"></h4>
   </form>
   </div>
   <div>
   <c:url var="action" value="/login/login-action5.form"></c:url>
   <form action="${action}" method="post">
   <h2>登录</h2>
   <h3><span></span></h3>
   <p>用户:<input type="text" name="name"><span></span></p>
   <p>密码:<input type="password" name="pwd"><span></span></p>
   <h4><input type="submit" value="登录"></h4>
   </form>
   </div>
  </body>
</html>
