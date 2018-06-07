<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息查看</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#valid").text($.dictionary.formatter('SF')($("#valid").text()));
	});
</script>
</head>
<body>
	<table class="comm-table">
		<tbody>
			<tr>
				<th class="red">所属机构：</th>
				<td>${entity.wunit.mc}</td>
			</tr>
			<tr>
				<th class="red">姓名：</th>
				<td id="user">${entity.name}</td>
			</tr>
			<tr>
				<th class="red">用户名：</th>
				<td>${entity.userName}</td>
			</tr>
			<tr>
				<th class="red">是否有效：</th>
				<td id="valid">${entity.isvalid}</td>
			</tr>
			<tr>
				<th class="red">用户类别：</th>
				<td><%=(String)request.getAttribute("roleNames")%></td>
			</tr>
		</tbody>
	</table>
</body>
</html>