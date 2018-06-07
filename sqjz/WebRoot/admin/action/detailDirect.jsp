<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" href="${ctx }/css/common2.css" type="text/css" />
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>
<script type="text/javascript">
function forward(uri){
	$("#detailform").attr("action", uri);
	$("#detailform").attr("onsubmit", "");
	$("#detailform").submit();
}
$(document).ready(function(){
	var type = "${type}";
	var typeNames = ["模块","增加","删除","修改","查询"];
	var typeName = typeNames[type];
	$("#neirong").html(" "+typeName);
	
	$(".btn_back").click(function(){
		forward('${ctx}/sys/action/list.action');
	});
});
</script>
<title>详细信息</title>
</head>

<body>
<center>
<div class="man_zone2">
<form id="detailform" action="" method="post">
  <input type="hidden" id="mc" name="mc" value="<c:out value='${mc}'/>"/>
  <input type="hidden" id="pageNo" name="page.pageNo" value="${page.pageNo}"/>
  <input type="hidden" name="parentId" value="${parentId}">
  <table width="99%" align="center"  cellpadding="0" cellspacing="0" class="jieguo_bt">
    <tr><td><img src="${ctx }/images/jieguo_img.gif" /></td>
    </tr>
  </table>
  <table width="99%" border="0" align="center"  cellpadding="0" cellspacing="1" class="table_xz">
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">名称</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;">${name }</td>
    </tr>
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">地址</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;">${address }</td>
    </tr>
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">类型</td>
      <td width="85%" id="neirong" colspan="3" style="background-color:#F9FCFB;"></td>
    </tr>
     <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">描述信息</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;">${info }</td>
    </tr>
     <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">创建时间</td>
      <td width="35%" style="background-color:#F9FCFB;"><s:date name="cjsj" format="yyyy-MM-dd HH:mm:ss" /></td>
	  <td width="15%" class="table_xz_title" style="background:#F3F8F7;">修改时间</td>
      <td width="35%" style="background-color:#F9FCFB;"><s:date name="xgsj" format="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">上次修改人</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;">${xgr }</td>
    </tr>
     <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">拥有该权限的角色</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;">
      <s:iterator value="roles" status="status">
  			${name }&nbsp;&nbsp;&nbsp;&nbsp;
  		</s:iterator>
		</td>
    </tr>
  </table>
    
  <table width="99%" align="center"  cellpadding="0" cellspacing="0" class="jieguo_qr">
    <tr><td>
    </td>
    </tr>
  </table>
</form>
</div>
<center>
</body>
</html>
