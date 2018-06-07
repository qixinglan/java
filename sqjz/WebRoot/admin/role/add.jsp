<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增角色</title>
<%@ include file="/common/commonjsTemp.jsp"%>
</head>
<body>
	<form id="roleAdd" action="${ctx}/sys/role/add.action" method="post">
		<input type="hidden" name="entity.id" id="entity.id"
			value="${entity.id}" />
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red">角色名：</th>
					<td><input type="text" name="entity.name" maxlength="20" id="entity.name" /></td>
				</tr>
				<tr>
					<th>角色说明：</th>
					<td><input type="text" name="entity.info" maxlength="300" id="entity.info" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
