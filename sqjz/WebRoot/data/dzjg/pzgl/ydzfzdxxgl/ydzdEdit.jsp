<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移动执法终端信息管理</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script language="javascript">
	$(function() {
		var parms=getParameters(location.href);
    	var oper=parms["oper"];
    	if(oper=="edit"){
    		$("#equnumber").attr("name","equnumberTemp");
    		$("#equnumberTemp").attr("name","equnumber");
    		$("#equnumber").attr("disabled",true);
    	}
		$.dictionary.combobox("#equtype", "equtype", "SBXH", {
			allowBlank : false,
			defValue : '${lawEn.equtype}'
		});
		$.dictionary.combobox("#equstatus", "equstatus", "SBZT", {
			width:'68%',
			allowBlank : false,
			defValue : '${lawEn.equstatus}'
		});
		var MOrg =$.organization.formatter()('${lawEn.MOrgId}');
		if(MOrg==null || MOrg==''){
			MOrg = $.organization.formatter()('${user.wunit.bm}');
		}
		$("#MOrgId").text(MOrg);
	});
</script>
</head>


<body>
	<form id="form1" action="${ctx }/data/ydzf/add.action?oper=add"
		method="post">
		<table class="comm-table">
			<c:if test="${lawEn.id != null}">
				<input type="hidden" name="id" value="${lawEn.id }" />
			</c:if>
			<tbody>
				<tr>
					<!-- <th class="red w180">设备型号：</th>
					<td id="equtype"></td> -->
					<th class="red w180">设备编号：</th>
					<td><input type="text" id="equnumber" name="equnumber"
						value="${lawEn.equnumber}" required="required" validater="code" maxlength="12"/></td>
					<th class="red w180">设备状态：</th>
					<td id="equstatus"></td>
					<input type="hidden" id="equnumberTemp" name="equnumberTemp" value="${lawEn.equnumber }" />
				</tr>
				<tr>
					<th>领用日期：</th>
					<td><input value="${lawEn.useTime}" class="Wdate"
						name="useTime" type="text" id="useTime"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16" /></td>
					<th class="red w180">购进日期：</th>
					<td><input class="Wdate"
						id="procureDate" value="${lawEn.procureDate}" name="procureDate"
						type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						maxlength="16" required="required" validater="date" /></td>
					
				</tr>
				<tr>
					<th>领用单位：</th>
					<td><input type="text" value="${lawEn.orgId}" name="orgId"
						id="orgId" /></td>
					<th>管理单位：</th>
					<td id="MOrgId"></td>
				</tr>
				
				<input type="hidden" name="MOrgId" value="${user.wunit.bm }" />
			</tbody>
		</table>
	</form>
</body>
</html>
