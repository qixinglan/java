<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAJAX.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
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
  </head>
  
  <body>
 <a href="javascript:;" onclick="alert(getXhr())">点击这里就显示</a><br>
  </body>
</html>
