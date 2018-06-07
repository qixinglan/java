<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>矫正机构新增</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		var orgType=$("#orgType").val();
		var filter="";
		if(orgType=="1"){
			filter="6,7,8,9,10,11";
		}else if(orgType=="2"){
			filter="6,7,9,10,11";
		}else if(orgType=="3"){
			filter="1,2,3,4,5,6,7,8";
		}else if(orgType=="10"){
			filter="1,2,3,4,5,8,9,10,11";
		}
		var temp = $("#ryType").text();
		$("#ryType").text("");
		$.dictionary.combobox("#ryType", "entity.persontype", "RYLB", {
			allowBlank : false,
			defValue : temp || '1',
			fieldClass:"search-form-field",
			attr:{required:"required"}
		});
		temp = $("#zzmm").text();
		$("#zzmm").text("");
		$.dictionary.combobox("#zzmm", "entity.polityvisage", "ZZMM", {
			allowBlank : false,
			defValue : temp || '12',
			fieldClass:"search-form-field",
			attr:{required:"required"}
		});
		temp = $("#xl").text();
		$("#xl").text("");
		$.dictionary.combobox("#xl", "entity.degree", "WHCD", {
			allowBlank : false,
			defValue : temp || '7',
			fieldClass:"search-form-field",
			attr:{required:"required"}
		});
		temp = $("#zwzj").text();
		$("#zwzj").text("");
		$.dictionary.combobox("#zwzj", "entity.duty", "ZWZJ", {
			allowBlank : false,
			defValue : temp || '1',
			fieldClass:"search-form-field",
			attr:{required:"required"},
			filter:filter
		});
		temp = $("#xb").text();
		$("#xb").text("");
		$.dictionary.radio("#xb", "entity.sex", "XB", {
			allowBlank : false,
			defValue : temp || '1',
			border:false,
			fieldClass:"search-form-field",
			attr:{required:"required"}
		});
	});
</script>
</head>
<body>
	<form id="personAdd" action="${ctx}/data/jggl/perSave.action"
		method="post">
		<input type="hidden" name="entity.id" id="entity.id"
			value="${entity.id}" /> <input type="hidden" name="entity.org.orgId"
			id="entity.org.orgId" value="${entity.org.orgId}" />
			<input type="hidden" id="orgType" value="${orgType}" />
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red ">姓名：</th>
					<td><input type="text" name="entity.name" id="entity.name" maxlength="20"
						value="${entity.name}" class="search-form-field" required="required" label="姓名"/></td>
					<th class="red ">性别：</th>
					<td id="xb">${entity.sex}</td>
				</tr>
				<tr>
					<th class="red ">出生日期：</th>
					<td><input type="text" name="entity.birthday" style="width: 200px;"
						id="entity.birthday" class="Wdate date-field form-input"
						value="${entity.birthday}" required="required" label="出生日期"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					<th class="red ">政治面貌：</th>
					<td id="zzmm">${entity.polityvisage}</td>
				</tr>
				<tr>
					<th class="red ">学历：</th>
					<td id="xl" colspan="3">${entity.degree}</td>
				</tr>
				<tr>
					<th class="red ">现工作单位及部门：</th>
					<td >${entity.org.cname}</td>
					<th class="red ">原工作单位：</th>
					<td ><input type="text" name="entity.oldorg" id="entity.oldorg" maxlength="80"
						value="${entity.oldorg}" class="search-form-field" required="required" label="原工作单位"/></td>
				</tr>
				<tr>
					<th class="red ">人员类别：</th>
					<td id="ryType">${entity.persontype}</td>
					<th class="red ">职务职级：</th>
					<td id="zwzj">${entity.duty}</td>
				</tr>
				<tr>
					<th class="red ">办公电话：</th>
					<td><input type="text" name="entity.workPhone" id="entity.workPhone" maxlength="11"
						value="${entity.workPhone}" class="search-form-field"  required="required" label="办公电话" validater="int"/></td>
					<th class="red ">手机：</th>
					<td><input type="text" name="entity.phone" id="entity.phone" maxlength="11"
						value="${entity.phone}" class="search-form-field"  required="required" label="手机" validater="telephone"/></td>
				</tr>
				<tr>
					<th>备注：</th>
					<td colspan="3"><textarea name="entity.description" style="resize:none; width: 95%;margin-left: 5px;margin-right: 0px;"
							id="entity.description">${entity.description}</textarea></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>