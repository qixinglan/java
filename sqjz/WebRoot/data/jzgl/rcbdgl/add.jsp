<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>手工报到录入</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/jzgl/rcbdgl/fxrys.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.fxrys.fxrysCbox("#fxryid", "fxryid", "FXRYS",{multiSelected: false,width:'95%',attr:{required:"true",label:"服刑人员"}});
	});
</script>
</head>
<body>
	<form id="userAdd" action="${ctx}/data/jzgl/rcbdgl/addReportInfoOperate.action" method="post">
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="form-td-label form-td-label-120 field-required-label">服刑人员：</th>
					<td id="fxryid"></td>
				</tr>
				<tr>
					<th class="form-td-label form-td-label-120 field-required-label">本次报到时间：</th>
					<td><input id="reportdate" name="reportdate" required="true" label="本次报到时间"
						class="Wdate inputStyle" style="width: 95%"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
					</td>
				</tr>
				<tr>
					<th class="form-td-label form-td-label-120 field-required-label">下次报到时间：</th>
					<td><input id="nextreportdate" name="nextreportdate"
						class="Wdate inputStyle" style="width: 95%" required="true" label="下次报到时间"
						onclick="WdatePicker({dateFmt:'yyyy-MM',enableInputMask:false})" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>