<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色信息查看</title>
<%@ include file="/common/commonjsTemp.jsp"%>
</head>
<body>
	<table class="comm-table">
		<tbody>
			<tr>
				<th class="red" width="20%">角色名：</th>
				<td>${entity.name}</td>
			</tr>
			<tr>
				<th width="20%">角色说明：</th>
				<td>${entity.info}</td>
			</tr>
			<tr>
				<th width="20%">拥有该角色用户：</th>
				<td><%=(String)request.getAttribute("users")%></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
