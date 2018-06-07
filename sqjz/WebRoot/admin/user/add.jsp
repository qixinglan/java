<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/admin/user/role.js" type="text/javascript"></script>
<script type="text/javascript">
	$.roleDictionary.load("${ctx}/sys/user/roleJson.action");
</script>
<script type="text/javascript">
	$(function() {
		$.roleDictionary.combobox("#role", "role", "ROLE",{multiSelected: false});
		
		var orgId = $("#orgId").val();
		$.organization.combobox("#org", "wunit.bm", orgId, {
			allowBlank : false,
			level : 10,
			showRoot : true,
			defValue : orgId,
			showItself : true
		});
		var id = $("#org").find('input[name="wunit.bm"]').attr("id");
		$("#" + id + "_mcdropdown").attr("onchange",
				"changeValue('" + id + "');");
		personChoose(orgId);
		$.dictionary.radio("#valid", "isvalid", "SF", {
			allowBlank : false,
		});
		$(".comm-table td input").attr("class", "organiz");
	});
	function personChoose(value) {
		$.person.personCbox("#user", "name", value, {
			allowBlank : false,
			pType : "",
			hasUser : "2",
			isUnion : false
		});
	}
	function changeValue(id) {
		var value = $("#" + id).val();
		$("#user").text("");
		personChoose(value);
		$(".comm-table td input").attr("class", "organiz");
	}
</script>
</head>
<body>
	<input type="hidden" value="${orgId}" id="orgId" />
	<form id="userAdd" action="${ctx}/sys/user/add.action" method="post">
		<input type="hidden" name="entity.id" id="entity.id"
			value="${entity.id}" />
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red">所属机构：</th>
					<td id="org"></td>
				</tr>
				<tr>
					<th class="red">姓名：</th>
					<td id="user"></td>
				</tr>
				<tr>
					<th class="red">用户名：</th>
					<td><input type="text" name="entity.userName"
						id="entity.userName" /></td>
				</tr>
				<tr>
					<th class="red">是否有效：</th>
					<td id="valid"></td>
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
