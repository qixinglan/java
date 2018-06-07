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
		$("#deviceType").text($.dictionary.formatter('SBLX')($("#deviceType").text()));
		$("#deviceVersion").text($.dictionary.formatter('SBXH')($("#deviceVersion").text()));
		$("#procureUnit").text($.organization.formatter()($("#procureUnit").text() ));
		$("#deviceStatus").text($.dictionary.formatter('SBZT')($("#deviceStatus").text() ));
		

		//表格初始化
		$("#mainGrid").jqGrid(
			{
				rowNum : 10,
				url : '${ctx}/data/dzjg/jgsb/searchHistory.action?pairNumber=${devicePair.pairDeviceNumber }',
				colNames : [ '使用单位','社区服刑人员姓名','使用状态',' 开始使用日期',' 结束使用日期 ','操作人','操作机构'],
	
				colModel : [
						{
							name : 'orgId',
							index : 'orgId',
							formatter : function(value, opts, rwdat) {
								return $.organization.formatter()(rwdat.orgId);
							}
						},
						{
							name : 'userdName',
							index : 'userdName',
						},
						{
							name : 'status',
							index : 'status',
							formatter : $.dictionary.formatter('SBZT')
						},
						{
							name : 'beginDate',
							index : 'beginDate',
							formatter : "date",
							formatoptions : {
								newformat : 'Y-m-d'
							}
						},
						{
							name : 'endDate',
							index : 'endDate',
							formatter : "date",
							formatoptions : {
								newformat : 'Y-m-d'
							}
						},
						{
							name : 'crtperson',
							index : 'crtperson',
						},
						{
							name : 'crtorg',
							index : 'crtorg',
							formatter : function(value, opts, rwdat) {
								return $.organization.formatter()(rwdat.crtorg);
							}
						}],
				multiselect : false,
				sortname : 'endDate',
				sortorder : "desc",
				//rownumbers : true,
				pager : '#mainGridPager',
			});
	});
</script>
</head>
<body>
	<table class="comm-table">
		<tbody>
			<tr>
				<th class="red w180">定位主机设备编号：</th>
				<td style="width: 200px;">${devicePair.machine.deviceNumber }</td>
				<th class="red w180">腕表设备编号：</th>
				<td>${devicePair.watch.deviceNumber }</td>
			</tr>
			<tr>
				<th class="red w180">主机联系电话：</th>
				<td id="connectPhone">${devicePair.machine.connectPhone }</td>
				<th class="red w180">设备状态：</th>
				<td id="deviceStatus">${devicePair.status}</td>
			</tr>
			<tr>
				<th class="red w180">配对编号：</th>
				<td id="pairDeviceNumber">${devicePair.pairDeviceNumber }</td>
			</tr>
			<tr>
				<th>购买单位：</th>
				<td id="procureUnit"><s:property value="procureUnit" /></td>
				<th>购买日期：</th>
				<td><s:date name="procureDate" format="yyyy-MM-dd" /></td>
			</tr>
			<c:if test="${deviceType=='1'&&deviceStatus=='03'}">
				<tr>
					<th>佩戴人员：</th>
					<td><s:property value="#request.fxry" /></td>
					<th>佩戴日期：</th>
					<td><s:date name="#request.date" format="yyyy-MM-dd" /></td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div class="container-bottom">
		<table id="mainGrid">
		</table>
		<div id="mainGridPager"></div>
	</div>
</body>
</html>
