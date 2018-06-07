<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sim卡管理</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script language="javascript" src="${ctx}/data/dzjg/sbgl/js/org.js" type="text/javascript"></script>
<script type="text/javascript">
$.org.load("${ctx}/data/jggl/json.action",'<%=orgId%>');
	$(function() {
		$.dictionary.combobox("#useDeviceType", "useDeviceType", "SIMTYPE", {
			width:'68%',
			allowBlank : false, 
			attr:{searchType: "eq"},
		});
		$.dictionary.combobox("#status", "status", "SIMSBZT", {
			width:'68%',
			allowBlank : false, 
			attr:{searchType: "eq"},
			emptyText : '未用',
			defValue : '1',
			filter:'2,4'
		});
		//购买单位
		$.organization.combobox("#buyUnit", "buyUnit", ${user.wunit.bm}, {
			width:'68%',
			allowBlank : false,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '市局',
			defValue : '1',
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			//fieldClass:"search-form-field"
		});
		//使用单位
		$.org.combobox("#useUnit", "useUnit", ${user.wunit.bm}, {
			width:'68%',
			allowBlank : false,
			level : 10,
			showItself : true,
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			//fieldClass:"search-form-field"
		});
		var date=new Date().format('yyyy-MM-dd');//设置默认购买日期
		$("#buyDate").val(date);
	});
</script>
</head>

<body>
		<form id="saveForm"
			action="${ctx }/data/sbgl/sim/add.action" method="post">
			<table class="comm-table">
				<tbody>
					<tr>
						<th class="red w180">SIM卡号：</th>
						<td id="phoneNumber">
						<input type="text" name="phoneNumber" required="required" validater="telephone" maxlength="11" style="width:60%"/> </td>
						<th>购买单位：</th>
						<td id="buyUnit"></td>
					</tr>
					<tr>
						 <th >使用单位：</th>
						<td id="useUnit">
						</td>
						<th >设备状态：</th>
						<td id="status"></td>
					</tr>
					<tr>
						<th >SIM卡类型：</th>
						<td id="useDeviceType"></td>
						<th>购买日期：</th>
						<td><input id='buyDate' name="buyDate" class="Wdate" type="text"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16" style="width:62%"/></td>
						<!-- <td id="buyUnitName" colspan="3"></td><input id="buyUnit" name='buyUnit' type="hidden"/> -->
					</tr>
				</tbody>
			</table>
		</form>
</body>
</html>
