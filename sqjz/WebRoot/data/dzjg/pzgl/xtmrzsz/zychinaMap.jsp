<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String contextPath = request.getContextPath();
	String name = request.getParameter("fxryid");
	String type = request.getParameter("type");
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
</style>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/common2.js" type="text/javascript"></script>
<script type="text/javascript">
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	CONTEXT_PATH = '<%=contextPath%>';
	var mapApp;
	$(function(){
		mapApp=document.frames["ezmap"];
		var na=$("#name").val();
		var ty=$("#type").val();
		var view=$("#view").val();
		if(view!=""){
			$("#searchDiv").attr("style","display:none");
		}else{
			$("#map").attr("style","width: 100%;height:90%;border: 1px solid #ccc;");
		}
		mapApp.GetFxryDZWLAll(na);
		var parms=getParameters(location.href);
		if(parms["x"]&&parms["x"]!="undefined"){
			ezmap.SetCenter(parms["x"],parms["y"],parms["level"]);	
		}
	});
</script>
</head>
<body onload="" style="height: 100%">
	<input type="hidden" id="name" value="${name}"/>
	<input type="hidden" id="type" value="${type}"/>
	<input type="hidden" id="view" value="${view}"/>
	<div id="searchDiv" class="container-top">
		<table class="search-table" style="margin-top: 5px;margin-bottom: 5px;">
			<tbody>
				<tr>
					<td><input type='button' class="bttn bicon-save" value="新增"
						onclick="mapApp.DrawDZWL();" />
						<input type="button" id="btnDel" class="bttn bicon-delete"
						value="删除" onclick="javascript:mapApp.DeleteDZWL();" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="map"
		style="width: 100%;height:100%;border: 1px solid #ccc;">
		<div class="rightFixed" id="divRight">
	    <a href="javascript:void(0);" onclick="changeMap(2);" ><img src="<%=contextPath%>/images/map_change.png"/></a>
	    <a href="${ctx}/data/dwjk/zylocationDetail.action" id="jkxq"><div class="dwjk-jkxq"></div></a>
	    <div id="qxsfjtj" class="divtj"></div>
	</div>
		<iframe id="ezmap" src="${ctx}/js/ezmap/ezmap.html" width="100%" height="100%"></iframe>
		</div>
</body>
</html>