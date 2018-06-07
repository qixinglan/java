<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String contextPath = request.getContextPath();
	String x = request.getParameter("x");
	String y = request.getParameter("y");
	if("".equals(x)){
		x="0";
		y="0";
	}
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>社区矫正电子监管</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="${ctx}/js/cmap/openlayer/theme/default/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/cmap/cmap.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/common3.css" />
<link rel="stylesheet" href="${ctx}/css/color.css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<link href="${ctx}/js/grid/ui.jqgrid.css" rel="stylesheet"
	type="text/css" />
<style rel="stylesheet">
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0
}
*{margin:0px;padding:0px}
#cc{
	position:absolute;top:60px;left:60px;z-index:2000
}
</style>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/cmap/openlayer/OpenLayers.js" type="text/javascript"></script>
<script src="${ctx}/js/cmap/openlayer/lib/OpenLayers/Lang/zh-CN.js"	type="text/javascript"></script> 
<script src="${ctx}/js/cmap/CMapIcon.js" type="text/javascript"></script>
<script src="${ctx}/js/cmap/cmap.js" type="text/javascript"></script>

<script type="text/javascript">
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	CONTEXT_PATH = '<%=contextPath%>';
	var x = '<%=x%>';
	var y = '<%=y%>';
	$(function(){
		var view=$("#view").val();
		if(view!=""){
			$("#searchDiv").attr("style","display:none");
		}else{
			$("#map").attr("style","width: 100%;height:90%;border: 1px solid #ccc;");
		}
		init();
		if(x!='0'){
			Location(x,y);
		}
		var layer_point = CreateMarkerLayers("point");
		var size = new OpenLayers.Size(50,50);
		var icon_point = new CMapIcon(null, size,{x:-(size.w*0.49),y:-(size.h*0.69)});
		icon_point.imageDiv.innerHTML="<div align='center' ><ul><li class='span-bd' style='float:none'>E</li></ul></div>";
		addMarkerBylayer(<%=x%>,<%=y%>,icon_point,layer_point,null,true,true);
		
		MapClick(function collect(evt){
		    var lonLat = GetPostionHandler1(evt);
		    $("#x").attr("value",lonLat.lon);
		    $("#y").attr("value",lonLat.lat);
			addMarkerBylayer(lonLat.lon,lonLat.lat,icon_point,layer_point,null,true,true);
		});
	});
		
</script>

</head>
<body  style="height: 100%;">
	<input type="hidden" id="x" value="<%=x%>"/>
	<input type="hidden" id="y" value="<%=y%>"/>
<div id="map" 
		style="width: 100%;height:100%;border: 1px solid #ccc;"></div>
</body>
</html>