<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		var railType = $("#railType").text();
		$("#adjustType").text(
				$.dictionary.formatter('JZLB')($("#adjustType").text()));
		$("#status")
				.text($.dictionary.formatter('DZJGZT')($("#status").text()));
		$("#railType").text(
				$.dictionary.formatter('DZWL')($("#railType").text()));
		var areas = $("#areas").val().split(",");
		for ( var area in areas) {
			var m = "#" + areas[area];
			$(m).attr("checked", "checked");
		}
		var lops = $("#lops").val();
		if (lops.length == 2) {
			$("#hidden3").hide();
		} else {
			$("#hidden3").show();
		}
	});
	var api = frameElement.api, W = api.opener;
	function showMap(mapId,type) {
		W.$.dialog({
			id : 'testID',
			title : '地图标绘',
			fixed : true,
			lock : true,
			parent:this,
			content : 'url:${ctx}/data/xtsz/map.action?view=1&fxryid=' + mapId+'&type='+type+'&name='+encodeURI(encodeURI(mapId)),
			cancelVal : '关闭',
			cancel : true
		}).max();
	}
</script>
</head>
<body>
	<input type="hidden" id="areas" name="areas" value="${areas}" />
	<input type="hidden" id="lops" value="${locP}" />
	<table class="comm-table">
		<tbody>
			<tr>
				<th width="20%">姓名：</th>
				<td width="30%">${entity.name}</td>
				<th width="20%">矫正类别：</th>
				<td width="30%" id="adjustType">${entity.adjustType}</td>
			</tr>
			<tr>
				<th width="20%">执行机关：</th>
				<td width="30%">${entity.org.cname}</td>
				<th width="20%">监管民警：</th>
				<td width="30%">${entity.pname}</td>
			</tr>
			<tr>
				<th>定位信息采集间隔：</th>
				<td colspan="3">
					<table class="comm-table">
						<tr>
							<td><s:if test="#request.locP.size()==0">区县局默认</s:if> <s:if
									test="#request.locP.size()>0">自定义</s:if></td>
						</tr>
						<tr id="hidden3">
							<td>
								<table class="comm-table">
									<thead>
										<tr>
											<th width="30%" style="text-align:center;font-weight:bold">开始时间</th>
											<th width="30%" style="text-align:center;font-weight:bold">结束时间</th>
											<th width="30%" style="text-align:center;font-weight:bold">间隔（分钟）</th>
										</tr>
									</thead>
									<tbody id="userLine" class="buttonArea">
										<s:iterator value="#request.locP" id="loc"
											status="st">
											<tr>
												<td style="text-align:left"><s:property
														value="startTime" /></td>
												<td style="text-align:center"><s:property
														value="endTiem" /></td>
												<td style="text-align:center"><s:property value="space" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th width="20%">电子监管状态：</th>
				<td colspan="3" width="80%" id="status">${entity.status}</td>
			</tr>
			<tr>
				<th width="20%">电子围栏：</th>
				<td colspan="3">
					<table class="comm-table" style="border:0">
						<tr>
							<th style="width: 150px;"
								<s:if test="#request.entity.railType==2||#request.entity.railType==3">rowspan="2"</s:if>>禁止离开区域：</th>
							<td colspan="3" width="80%" id="railType">${entity.railType}</td>
						</tr>
						<s:if test="#request.entity.railType==2">
							<tr>
								<td colspan="3">
									<table class="comm-table" style="border:0">
										<s:iterator value="#request.areaList" id="id" status="st">
											<s:if test="#st.index%4==0">
												<tr>
													<td colspan="3" style="border:0"><input
														type="checkbox" id="<s:property value="areaId" />"
														name="area" value='<s:property value="areaId" />'
														disabled="disabled" /><label
														for="<s:property value="areaId" />"><s:property
																value="areaName" />&nbsp;&nbsp;</label>
											</s:if>
											<s:elseif test="#st.index%4==3">
												<input type="checkbox" id="<s:property value="areaId" />"
													name="area" value='<s:property value="areaId" />'
													disabled="disabled" />
												<label for="<s:property value="areaId" />"><s:property
														value="areaName" /></label>
												</tr>
											</s:elseif>
											<s:else>
												<input type="checkbox" id="<s:property value="areaId" />"
													name="area" value='<s:property value="areaId" />'
													disabled="disabled" />
												<label for="<s:property value="areaId" />"><s:property
														value="areaName" />&nbsp;&nbsp;</label>
											</s:else>
										</s:iterator>
									</table>
								</td>
							</tr>
						</s:if>
						<s:if test="#request.entity.railType==3">
							<tr>
								<td colspan="3"><input type="button"
									class="bttn bicon-preview" id="dtbh" value="查看标绘"
									onclick="javascript:showMap('${entity.id}','02');" /></td>
							</tr>
						</s:if>
						<tr>
							<th style="width: 150px;">禁止进入区域：</th>
							<td colspan="3"><input type="button"
								class="bttn bicon-preview" id="dtbh" value="查看标绘"
								onclick="javascript:showMap('${entity.id}','02');" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
