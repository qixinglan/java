<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员查看</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#ryType").text($.dictionary.formatter('RYLB')($("#ryType").text()));
		$("#xb").text($.dictionary.formatter('XB')($("#xb").text()));
		$("#zzmm").text($.dictionary.formatter('ZZMM')($("#zzmm").text()));
		$("#xl").text($.dictionary.formatter('WHCD')($("#xl").text()));
		$("#zwzj").text($.dictionary.formatter('ZWZJ')($("#zwzj").text()));
	});
</script>
</head>
<body>
	<form id="personAdd" action="${ctx}/data/jggl/perSave.action"
		method="post">
		<table class="comm-table" style="width:100%">
			<tbody>
				<tr>
					<th>姓名：</th>
					<td>${entity.name}</td>
					<th>性别：</th>
					<td id="xb">${entity.sex}</td>
				</tr>
				<tr>
					<th>出生日期：</th>
					<td>${birth}</td>
					<th>政治面貌：</th>
					<td id="zzmm">${entity.polityvisage}</td>
				</tr>
				<tr>
					<th>学历：</th>
					<td id="xl" colspan="3">${entity.degree}</td>
				</tr>
				<tr>
					<th>工作单位：</th>
					<td>${entity.org.cname}</td>
					<th>原工作单位：</th>
					<td>${entity.oldorg}</td>
				</tr>
				<tr>
					<th>人员类别：</th>
					<td id="ryType">${entity.persontype}</td>
					<th>职务职级：</th>
					<td id="zwzj">${entity.duty}</td>
				</tr>
				<tr>
					<th>办公电话：</th>
					<td>${entity.workPhone}</td>
					<th>手机：</th>
					<td>${entity.phone}</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td colspan="3"><textarea style="background:white;" disabled readonly="readonly">${entity.description}</textarea></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>