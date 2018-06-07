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
		var parms=getParameters(location.href);
    	var supOrgName=parms["supOrgName"];
    	$("#supOrgName").val(supOrgName);
		$.dictionary.combobox("#jglx", "orgType", "JGLX", {
			allowBlank : false,
			defValue : '4',
			fieldClass:"search-form-field"
		});
		$.dictionary.combobox("#jgzt", "orgStatus", "JGZT", {
			allowBlank : false,
			defValue : '2',
			fieldClass:"search-form-field"
		});
	});
	function getPosition() {
		var x = $("#locX").attr("value");
		var y = $("#locY").attr("value");
		$.dialog({
			id : 'testID',
			title : '地图标绘',
			content : 'url:${ctx}/data/jggl/map.action?x='+x+'&y='+y,
			cancelVal : '关闭',
			cancel : true,
			ok:function(){
				var form =  $("iframe[name='testID']").get(0).contentDocument;
				var x = form.getElementById("x");
				var y = form.getElementById("y");
				$("#locX").attr("value",x.value);
				$("#locY").attr("value",y.value);
			}
		}).max();
	}
</script>
</head>
<body>
	<form id="jgAdd" action="${ctx}/data/jggl/save.action" method="post">
		<input type="hidden" id="entity.supOrg.orgId"
			name="entity.supOrg.orgId" value="${entity.supOrg.orgId}"/>
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="red">机构名称：</th>
					<td><input type="text" id="entity.cname" name="entity.cname" class="search-form-field"
						maxlength="25" required="required" label="机构名称"/></td>
					<th class="red">机构类型：</th>
					<td id="jglx"></td>
					<th class="red">联系人：</th>
					<td><input type="text" name="entity.contact" maxlength="20"
						id="entity.contact" class="search-form-field" required="required" label="联系人"/></td>
				</tr>
				<tr>
					<th class="red">地址：</th>
					<td><input type="text" name="entity.address" maxlength="80"
						id="entity.address" class="search-form-field" required="required" label="地址"/></td>
					<th class="red">邮政编码：</th>
					<td><input type="text" name="entity.postalCode" maxlength="6" validater="int" 
						id="entity.postalCode" class="search-form-field" required="required" label="邮政编码"/></td>
					<th class="red">固定电话：</th>
					<td><input type="text" name="entity.phone" id="entity.phone" class="search-form-field" 
						maxlength="11" required="required" label="固定电话"/></td>
				</tr>
				<tr>
					<th class="red">传真：</th>
					<td><input type="text" name="entity.fax" id="entity.fax" class="search-form-field" 
					maxlength="20" required="required" label="传真"/></td>
					<th class="red">机构状态：</th>
					<td id="jgzt"></td>
					<th class="red">上级机构：</th>
					<td><input type="text" name="supOrgName" id="supOrgName" class="search-form-field" disabled="disabled"/></td>
				</tr>
				<tr>
					<th>机构设置情况：</th>
					<td colspan="5"><textarea rows="" cols="" style="resize:none;width: 95%;"
							name="entity.description" id="entity.description"></textarea></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>