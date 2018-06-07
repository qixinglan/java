<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="prototype-1.6.0.3.js"></script>
  <script type="text/javascript">
  function check(){
  	var xhr=getXhr();
  	xhr.open('get','check.do?username='+$F('username'),true);
  	xhr.onreadystatechange=function(){
  		if(xhr.readyState==4){
	  		if(xhr.status==200){
	  			var txt=xhr.responseText;
  				$('warn').innerHTML=txt;
	  		}
  			else{
  				$('warn').innerHTML='检查出错';
  			}
  		}
  	}
  	$('warn').innerHTML='正在查询';
  	xhr.send(null);
  }
  function getXhr(){
		var xhr=null;
		if(window.XMLHttpRequest){
			xhr=new XMLHttpRequest;
		}else{
			xhr=new ActiveXObject('Microsoft.XMLHttp');
		}
		return xhr;
	}
  </script>
    <base href="<%=basePath%>">
    <title>My JSP 'regist.jsp' starting page</title>
    
  </head>
  <body>
    <form action="regist.do" method="post"> 
    用户名:<input name="username" onblur="check()" id="username"/><span style="color:red" id="warn"></span><br>
    真实姓名：<input/><br>
    <input type="submit" value="提交">
    </form>. <br>
  </body>
</html>
