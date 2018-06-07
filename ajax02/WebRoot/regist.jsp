<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.tip{color:red}
	</style>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript">
function check_uname(){
//先获得ajax对象
var xhr=getXhr();
//使用ajax发送请求
xhr.open('get',encodeURI('check.do?username='+$F('username')),true);

xhr.onreadystatechange=function(){
	if(xhr.readyState==4){
	alert(xhr.status);
	if(xhr.status==200){
	
		var txt=xhr.responseText;
		$('user_m').innerHTML=txt;
	}else{
		$('user_m').innerHTML="检查用户名出错";
	}
	}
};
xhr.send(null);
}
</script>
  </head>
  
  <body>
   <form action="regist.do" method="post">
   <fieldset><legend>注册</legend>
   用户名：<input id="username" name="username" onblur="check_uname()"/><span class="tip" id="user_m"></span><br>
  密码：<input type="password" name="password"/><br>
  <input type="submit" value="提交"/>
  </fieldset>
   </form>
  </body>
</html>
