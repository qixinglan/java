<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统默认值设置</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		var areas = $("#areas").val().split(",");
		for ( var area in areas) {
			var m = "#" + areas[area];
			$(m).attr("checked", "checked");
		}
		var buttonArea = $(".buttonArea tr");
		if(buttonArea.length<1){
			$(".buttonArea").append("<tr><td style='text-align:center'>未设置</td>	<td style='text-align:center'>未设置</td><td style='text-align:center'>未设置</td></tr>");
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
	<div class="box13">
		<div class="title">${orgName}</div>
		<table class="comm-table">
			<input type="hidden" id="areas" name="areas" value="${areas}">
			<tr>
				<th>定位信息采集间隔：</th>
				<td colspan="3"><table class="comm-table">
						<thead>
							<tr>
								<th width="30%" style="text-align:center;font-weight:bold">开始时间</th>
								<th width="30%" style="text-align:center;font-weight:bold">结束时间</th>
								<th width="30%" style="text-align:center;font-weight:bold">间隔（分钟）</th>
							</tr>
						</thead>
						<tbody id="userLine" class="buttonArea">
							<s:iterator value="#request.entity.locPeriods" id="loc"
								status="st">
								<tr>
									<td style="text-align:center"><s:property value="startTime" />
									</td>
									<td style="text-align:center"><s:property value="endTiem" /></td>
									<td style="text-align:center"><s:property value="space" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table></td>
			</tr>
			<tr>
				<th>电子围栏：</th>
				<td colspan="3">
					<table class="comm-table" style="border:0">
						<tr>
							<th style="width: 150px;" <s:if test="#request.orgType==2&&(#request.entity.railType==2||#request.entity.railType==3)">rowspan="2"</s:if> >禁止离开区域：</th>
							<td colspan="3">
								<s:if test="#request.entity.railType==2">行政区划</s:if>
									<s:elseif test="#request.entity.railType==3">标绘设置 </s:elseif><s:else>北京市全境</s:else>
							</td>
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
									onclick="javascript:showMap('${orgName}','02');" /></td>
							</tr>
						</s:if>
						<s:if test="#request.orgType==2">
							<tr>
								<th style="width: 150px;">禁止进入区域：</th>
								<td colspan="3"><input type="button"
									class="bttn bicon-preview" id="dtbh" value="查看标绘"
									onclick="javascript:showMap('${orgName}','02');" /></td>
							</tr>
						</s:if>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>