<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
	String contextPath = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
    <head>
        <title>社区矫正电子监管</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
         <style>
         v\:*{
		   BEHAVIOR: url(#default#VML);
		   postion:absolute;
		}   
        html,body
        {
         width:100%;
         height:100%;
         padding:0;
         margin:0
        }      
        </style>
        <link rel="stylesheet" href="${ctx}/css/common3.css" />
        <link rel="stylesheet" href="${ctx}/css/color.css" />
        <link rel="stylesheet" href="${ctx}/css/button.css" />
        <link rel="stylesheet" href="${ctx}/css/index.css" type="text/css"/>
        <script type="text/javascript">
			var _appServer='<%=contextPath%>'+'/';
		</script>	
        <SCRIPT type="text/javascript" src="${ctx}/BjFile?url=TileServer/js/EzMapAPI.js"></script>
        <script src="ezmap.js" type="text/javascript"></script>
    </head>
    <body onload="init()">
    <div id="map"  style=" width: 100%; height: 100%;border: 1px solid #ccc;"></div>
    </body>
</html>
