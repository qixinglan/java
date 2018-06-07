<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>管理导航区域</title>
</head>
<script  type="text/javascript">
var preClassName = "man_nav_5";
function list_sub_nav(Id,sortname){
   if(preClassName != ""){
      getObject(preClassName).className="bg_image";
   }
   if(getObject(Id).className == "bg_image"){
      getObject(Id).className="bg_image_onclick";
      preClassName = Id;
	  showInnerText(Id);
	  window.top.frames['leftFrame'].outlookbar.getbytitle(sortname);
	  window.top.frames['leftFrame'].outlookbar.getdefaultnav(sortname);
   }
}

function showInnerText(Id){
    var switchId = parseInt(Id.substring(8));
	var showText = "对不起没有信息！";
	switch(switchId){
	    case 1:
		   //showText =  "欢迎使用政府采购网后台管理系统!";
		   showText =  "用户权限管理";
		   break;
	    case 2:
		   showText =  "system setting!";
		   break;
	    case 3:
		   showText =  "User Manage";
		   break;		   
	    case 4:
		   showText =  "Chanage Manage";
		   break;	
	    case 5:
		   showText =  "用户权限管理";
		   break;		   		   
	}
	getObject('show_text').innerHTML = showText;
}
 //获取对象属性兼容方法
 function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
function retHome(){
	window.top.location.href='${ctx}/data/index.jsp';
}
</script>
<body>
<div id="nav">
    <li id="man_nav_1"  class="bg_image" onclick="retHome()"><span>首页</span></li>
</div>
<div id="sub_info">&nbsp;&nbsp;<img src="../images/hi.gif" />&nbsp;<span id="show_text"></span></div>
</body>
</html>