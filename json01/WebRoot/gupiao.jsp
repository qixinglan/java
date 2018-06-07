<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% request.getAttribute("gupiao"); %>
<script type="text/javascript">
function f1(){
var obj=${gupiao};
alert(obj.name);
}
</script>
<% request.getAttribute("gupiao"); %>
    <title>My JSP 'gupiao.jsp' starting page</title>
  </head>
  <body onblur="f1();">

  </body>
</html>
