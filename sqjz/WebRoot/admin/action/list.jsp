<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>权限列表</title>
<link href="${ctx }/css/common2.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/page.js" type="text/javascript"></script>
<script src="${ctx}/js/validate.js" type="text/javascript"></script>
<script type="text/javascript">
function forward(uri){
	var url = "${ctx}/sys/action/" + uri;
	$("#searchtext").attr("action", url);
	$("#searchtext").submit();
}
</script>
</head>

<body>
<center>
<div class="man_zone2" >
  <form id="searchtext" action="${ctx }/sys/action/list.action" method="post">
    <input type="hidden" name="parent.id" value="${parentId }"/>
    <input type="hidden" name="parentId" value="${parentId}">
    <input type="hidden" id="mc" name="mc" value="<c:out value='${mc}'/>"/>
	<table width="99%" cellpadding="0" cellspacing="0" class="table_tj">
        <tr>        	
        	<td width="10%" style="padding-left:50px; text-align:left; font-size:14px; color:#394F4C;"><strong>模块名：</strong></td>	
          		<td width="30%"><input name="tmpMc" type="text" class="shuru" value="<c:out value='${mc }'/>"/></td>
            <td width="30%" style="padding-right:30px; text-align:right;">
	        	<a href="javascript:void(0);"><img src="${ctx }/images/chaxun.jpg" id="search" alt="查询" border="0" onclick="forward('list.action?isQuery=1');" /></a>&nbsp;&nbsp;
	        	<a href="javascript:void(0);"><img src="${ctx }/images/tianjia_mk.jpg" alt="添加模块" border="0" onclick="forward('beforeAdd.action?page.pageNo=${page.pageNo}');" /></a>&nbsp;&nbsp;
	        	<a href="javascript:void(0);"><img src="${ctx }/images/tianjia_dz.jpg" alt="添加动作" border="0" onclick="forward('addAction.action?page.pageNo=${page.pageNo}');" /></a>
	        </td>
        </tr>
    </table>
    </form>
	<table border="0" align="center" cellpadding="0" cellspacing="1" class="table_cx">
      <tr>
        <th>模块名称</th>
        <th>地址</th>
		<th>选择角色</th>
		<th>详细信息</th>		
        <th>操作</th>
      </tr>
      <s:set var="num" value="0"></s:set>
	  <s:iterator value="page.result" status="status">
      <s:set var="num" value="#num+1"></s:set>
		   	<tr class="<s:if test="#num%2==0">td-value-col</s:if><s:else>td-value-col2</s:else>">
		   	<td>${name }</td>
	        <td>${address }</td>
			<td><a href='#' onclick="openWin('${ctx}/sys/action/choice.action?id=${id}','选择角色',1000,600,1);">选择角色</a></td>
			<td><a href="javascript:void(0);" onclick="forward('show.action?page.pageNo=${page.pageNo}&id=${id }');">详细信息</a></td>
	        <td><a href="javascript:void(0);" onclick="forward('view.action?page.pageNo=${page.pageNo}&id=${id }');"><img src="${ctx }/images/xiugai.jpg" alt="修改" border="0" /></a>&nbsp;&nbsp;
	        <a href="javascript:void(0);"><img src="${ctx }/images/shanchu.jpg" onclick="if(confirm('你确认要删除记录吗？')){forward('del.action?page.pageNo=${page.pageNo}&id=${id }');}" alt="删除" border="0" /></a></td>
		   	</tr>		   	 
	  </s:iterator>
    </table>
    <%@ include file="../../controls/pageforadmin.jsp" %>
  </div>
</center>


</body>
</html>
