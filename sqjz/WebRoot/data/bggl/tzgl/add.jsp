<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通知拟制</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.organization.combobox("#jgIds", "jgIds", null, {
	    	allowBlank : true,
			level : 10,
			showRoot : false,
			showItself : true,
			notShowType:"1,3,4,5,6,7,8,9",
			emptyText : '请选择',
			multiSelected: true,
			width:'95%',
			attr:{searchType:"eq"}});
	});
</script>
</head>
<body>
	<form id="noticeAdd" action="${ctx }/data/tzgl/postData.action"
		method="post">
		<input type="hidden" name="id" id="id" value="${entity.id}" />
		 <input type="hidden" name="status" id="status" value="${entity.status}" />
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red">通知接收单位：</th>
					<td id="jgIds"></td>
				</tr>
				<tr>
					<th class="red">通知名称：</th>
					<td>
						<input type="text" name="title" id="title" maxlength="50" style="width: 93%"
							value="${entity.title}" required="required" label="通知名称"/>
					</td>
				</tr>
				<tr>
					<th>要求：</th>
					<td><textarea name="required" style="resize:none; width: 95%;margin-left: 5px;margin-right: 0px;"
							id="required">${entity.required}</textarea></td>
				</tr>
				<tr>
					<th>备注：</th>
					<td><textarea name="description" style="resize:none; width: 95%;margin-left: 5px;margin-right: 0px;"
							id="description">${entity.description}</textarea></td>
				</tr>
				<tr>
					<th class="red">附件：</th>
					<td id="affixId">${entity.affixId}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>