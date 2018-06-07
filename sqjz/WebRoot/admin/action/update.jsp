<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet" href="${ctx }/css/common2.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>
<script src="${ctx}/js/validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function forward(uri){
		$("#upform").attr("action", uri);
		$("#upform").attr("onsubmit", "");
		$("#upform").submit();
	}
	
	$(document).ready(function(){
		$(".save").click(function(){
			if(validateForm()){
				$("#upform").submit();
			}else{
				return false;
			}
		});	
	});	
</script>
<title>管理区域</title>
</head>

<body>
<center>
<div class="man_zone2">
  <table width="99%" align="center"  cellpadding="0" cellspacing="0" class="jieguo_bt">
    <tr><td><img src="${ctx }/images/jieguo_img.gif" /></td>
    </tr>
  </table>
   <form id="upform" action="${ctx}/sys/action/update.action" method="post" onsubmit="return validateForm()">
   <input type="hidden" name="id" id="id" value="${id }"/>
    <input type="hidden" name="parentId" value="${parentId}">
    <input type="hidden" name="parent.id" id="parent.id" value="${parent.id }"/>
    <input type="hidden" name="cjsj" id="cjsj" value="${cjsj }"/>
    <input type="hidden" name="type" id="type" value="${type }"/>
    <input type="hidden" id="mc" name="mc" value="<c:out value='${mc}'/>"/>
    <input type="hidden" id="pageNo" name="page.pageNo" value="${page.pageNo}"/>
  <table width="99%" border="0" align="center"  cellpadding="0" cellspacing="1" class="table_xz">
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">模块名称</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;"><input name="name" id="name" type="text" class="shuru required" value="<c:out value='${name }'/>" msg="模块名称" chklen="32"/><span class="red">*</span></td>
    </tr>
    <tr>
      <td width="15%" class="table_xz_title" style="background:#F3F8F7;">模块描述</td>
      <td width="85%" colspan="3" style="background-color:#F9FCFB;"><input name="info" type="text" class="shuru" value="<c:out value='${info }'/>" msg="模块描述" chklen="160"/></td>
    </tr>
  </table>
    
  <table width="99%" align="center"  cellpadding="0" cellspacing="0" class="jieguo_qr">
    <tr><td><a href="javascript:void(0);"><img src="${ctx }/images/jieguo_queren.gif" class="save" border="0"/></a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="forward('${ctx}/sys/action/list.action');"><img src="${ctx }/images/jieguo_fanhui.gif" border="0"/></a></td>
    </tr>
  </table>
  </form>
</div>
<center>
</body>
</html>
