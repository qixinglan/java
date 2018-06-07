<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

</script>
  </head>
  
  <body style="text-align:center;">
  <div style="margin:0px auto">
  <br/>
    <br/>
      <br/>
        <br/>
          <br/>
            <br/>
              <br/>
   <table align="center" width="900">
   <tr>
   <%if(((Administrator)request.getAttribute("ad")).getAtype()==1){ %><td><a href="admanage.do?page=1" >管理员用户管理</a></td><%} %>
   <td><a href="bookmanage.do?page=1">图书信息管理</a></td>
   <td><a href="usermanage.do?page=1">用户信息管理</a></td>

   </tr>
   </table>
   </div>
  </body>
</html>
