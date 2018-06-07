<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史报警信息详情查看</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">		
    var id='<%=request.getParameter("id")%>';
	$.ajax({
		url : '${ctx }/data/dzjg/lsbjxxgl/view.action',
		data : 'id=' + id,
		success : function(data) {
			$("#alarmTime").text(data.alarmTime);
			$("#alarmLevel").text($.dictionary.formatter('BJJB')(data.alarmLevel));
			$("#alarmType").text($.dictionary.formatter('BJLX')(data.alarmType));
			$("#name").text(data.name);
			$("#executeUnit").text($.organization.formatter()(data.executeUnit));
			$("#adjustType").text($.dictionary.formatter('JZLB')(data.adjustType));
			$("#isautoAlert").text($.dictionary.formatter('SFZDBJ')(data.isautoAlert));
			$("#alert").text(data.alert == null ? "" : data.alert);
			$("#alarm").text(data.alarm == null ? "" : data.alarm);
			$("#handleTime").text(data.handleTime == null ? "" : data.handleTime.replace("T"," "));
			$("#handler").text(data.handler == null ? "" : data.handler);
			$("#jlnr").text(data.record == null ? "" : data.record);
		}
	});
</script>
</head>
<body>
	<div>
		<table class="comm-table">
			<tr>
				<th>报警时间：</th>
				<td id="alarmTime"></td>
				<th>报警级别：</th>
				<td id="alarmLevel"></td>
			</tr>
			<tr>
				<th>报警类型：</th>
				<td id="alarmType"></td>
				<th>姓名：</th>
				<td id="name"></td>
			</tr>
			<tr>
				<th>执行机关：</th>
				<td id="executeUnit"></td>
				<th>矫正类别：</th>
				<td id="adjustType"></td>
			</tr>
			<tr>
				<th>是否自动提醒：</th>
				<td id="isautoAlert"></td>
				<th>提醒内容：</th>
				<td id="alert"></td>
			</tr>
			<tr>
				<th>报警内容：</th>
				<td colspan="3" id="alarm"></td>
			</tr>
			<tr>
				<th>处理时间：</th>
				<td id="handleTime"></td>
				<th>处理人/方式：</th>
				<td id="handler"></td>
			</tr>
			<tr>
				<th>记录内容：</th>
				<td colspan="3" id="record">
					<textarea id="jlnr" name="jlnr"
						style="height: 60px;resize:none;background:white;" 
						disabled="disabled"></textarea>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
