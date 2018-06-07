<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监管设备管理</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		$.dictionary.radio("#deviceType", "deviceType", "SBLX", {
			allowBlank : false
		});
		$.dictionary.combobox("#deviceVersion", "deviceVersion", "SBXH", {
			allowBlank : false,
		});
		$.dictionary.combobox("#deviceStatus", "deviceStatus", "SBZT", {
			width:'68%',
			allowBlank : false,
			attr:{searchType: "eq"},
			filter:'03'
		});
		$("#procureUnit").text($.organization.formatter()(${user.wunit.bm} ));
	});
</script>
</head>

<body>
		<form id="saveForm"
			action="${ctx }/data/dzjg/jgsb/add.action" method="post">
			<table class="comm-table">
				<tbody>
					<tr>
						<th class="red w180">定位主机设备编号：</th>
						<td><input type="text" id="machineDeviceNumber" name="machineDeviceNumber" required="required" validater="code" maxlength="32" /></td>
						<th class="red w180">腕表设备编号：</th>
						<td><input type="text" id="watchDeviceNumber" name="watchDeviceNumber" required="required" validater="code" maxlength="32" /></td>
					</tr>
					<tr>
						<th class="red w180">主机联系电话：</th>
						<td id="connectPhone"><input type="text" name="connectPhone" required="required" validater="telephone" maxlength="11"/></td>
						<th class="red w180">设备状态：</th>
						<td id="deviceStatus"></td>
					</tr>
					<tr>
						<th class="red w180">配对编号：</th>
						<td><input type="text" id="pairDeviceNumber" name="pairDeviceNumber" required="required" validater="code" maxlength="32" /></td>
						<th>购买日期：</th>
						<td><input name="procureDate" class="Wdate" type="text"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16" /></td>
					</tr>
					<tr>
					<th>购买单位：</th>
						<td id="procureUnit" colspan="3"></td>
					</tr>
				</tbody>
			</table>
		</form>
</body>
</html>
