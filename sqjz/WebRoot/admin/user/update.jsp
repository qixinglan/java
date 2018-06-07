<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息编辑</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/admin/user/role.js" type="text/javascript"></script>
<script type="text/javascript">
	$.roleDictionary.load("${ctx}/sys/user/roleJson.action");
</script>
<script type="text/javascript">
	$(function() {
		var sf = $("#valid").text();
		$("#valid").text("");
		$.dictionary.radio("#valid", "isvalid", "SF", {
			allowBlank : false,
			defValue : sf
		});
		var roles = "<%=request.getAttribute("roleId")%>";
		$.roleDictionary.combobox("#role", "role", "ROLE",{multiSelected: false,defValue:roles=="null"?"":roles});
	});
</script>
</head>
<body>
	<form id="userUpdate" action="${ctx}/sys/user/update.action"
		method="post">
		<input type="hidden" name="entity.id" id="entity.id"
			value="${entity.id}" />
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red w180">所属机构：</th>
					<td>${entity.wunit.mc}</td>
				</tr>
				<tr>
					<th class="red w180">姓名：</th>
					<td id="user">${entity.name}</td>
				</tr>
				<tr>
					<th class="red w180">用户名：</th>
					<td>${entity.userName}</td>
				</tr>
				<tr>
					<th class="red w180">是否有效：</th>
					<td id="valid">${entity.isvalid}</td>
				</tr>
				<tr>
					<th class="red">用户类别：</th>
					<td id="role"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>