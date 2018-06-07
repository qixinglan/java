﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
$(function() {
	$("#orgType").text($.dictionary.formatter('JGLX')($("#orgType").text()));
	//$("#orgStatus").text($.dictionary.formatter('JGZT')($("#orgStatus").text()));
});
</script>
</head>
<body>
	<table class="comm-table" style="width:100%">
		<tbody>
			<tr>
				<th>机构名称：</th>
				<td>${entity.cname}</td>
				<th>机构类型：</th>
				<td id="orgType">${entity.orgType}</td>
				<th>联系人：</th>
				<td>${entity.contact}</td>
			</tr>
			<tr>
				<th>地址：</th>
				<td>${entity.address}</td>
				<th>邮政编码：</th>
				<td>${entity.postalCode}</td>
				<th>固定电话：</th>
				<td>${entity.phone}</td>
			</tr>
			<tr>
				<th>传真：</th>
				<td>${entity.fax}</td>
				<!-- <th>机构状态：</th>
				<td id="orgStatus">${entity.orgStatus}</td> -->
				<th>上级机构：</th>
				<td>${entity.supOrg.cname}</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<th>机构设置情况：</th>
				<td colspan="5"><textarea style="background:white;" disabled>${entity.description}</textarea></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
