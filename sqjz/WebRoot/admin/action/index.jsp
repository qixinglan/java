<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>无标题文档</title>

		<link href="${ctx}/css/common2.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>
		<script src="${ctx}/js/jquery.jstree.js" type="text/javascript"></script>
		<script src="${ctx}/js/tree.js" type="text/javascript"></script>
		<script src="${ctx}/js/fold.js" type="text/javascript"></script>		
		<script type="text/javascript">
			$(function(){
			    //loadSingleCategoryTree('${ctx}', 'tree', '00', '0001,0002,0003,0004','/data/spxx/list.action?gys.id=${gys.id}');
				//loadSingleActionTree('${ctx}', 'tree', '1', '','sys/action/list.action');
				//loadSingleActionTree('${ctx}', 'tree', '0', '1,2,3','sys/action/list.action');  this can work
				loadSingleActionTree('${ctx}', 'tree', '0', '','sys/action/listInit.action');
			});
		</script>
	</head>

	<body>
		<div style="width:100%;">			
			<!--div id="left" style="width:184px; float:left; height:503px; margin-top:5px;"-->
			<div id="left" style="width:18%;float:left; height:408px; margin-top:5px;">
			   		<div width="100%" height="42px" style="text-align: left; padding-left:7px; background:url(${ctx}/images/tree_top_extra.jpg) repeat-x top left;"><img src="${ctx}/images/tree_top.jpg"/></div>
			   		<div width="100%" class="tree" style="height:100%;">
			   			<div id="tree" style="margin-top: 0; margin-left: 0; width: 100%; height: 100%; overflow: auto; "></div>
			   		</div>
			   	   <!--  <div width="100%"><img src="${ctx}/images/shu_bottom.gif" /></div> -->
		    </div>
	        <div id="right" style="width:82%; height:490px; float:left; margin-top:5px;">
			    <iframe src="" width="100%" height="550" id="main" name="main" frameborder="0" scrolling="auto">
			    </iframe>
			</div>		
		   
		</div>
	</body>
</html>
