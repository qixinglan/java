<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	var api = frameElement.api, W = api.opener;
	$(function() {
		$(function() {
			$("#adjustType").text(
					$.dictionary.formatter('JZLB')($("#adjustType").text()));
			var status = $("#status").text();
			$("#status").text("");
			$.dictionary.radio("#status", "status", "DZJGZT", {
				allowBlank : false,
				border:false,
				defValue : status
			});
			var railType = $("#railType").text();
			changeSz(railType);
			$("#railType").text("");
			$.dictionary.radio("#railType", "railType", "DZWL", {
				allowBlank : false,
				border:false,
				defValue : railType
			});
			$("input[name=\"railType\"]").attr("onclick",
					"changeSz(this.value)");
			var lops = $("#lops").val();
			if (lops == 0) {
				changeLp(1);
			} else {
				changeLp(2);
			}
			var areas = $("#areas").val();
			if (areas) {
				areas = areas.split(",");
				for ( var area in areas) {
					var m = "#" + areas[area];
					$(m).attr("checked", "checked");
				}
			}
			//增加行
			$(".addImg").click(function() {
				var tbody = $("#userLine");
				var rowIndex = tbody.children().length;
				if (rowIndex < 5) {
					var tr = $("#add_template").val();
					tbody.append(tr);
					//order(); 
					addColor();
					//$.parser.parse();
					//给新增加行的第一个输入框获得焦点
					var newAdd = $("#userLine tr:last>td:eq(1)>input:eq(0)");
					newAdd.focus();
				} else {
					focError($("#dw"), "采集间隔不能设置过多!");
				}
			});
			//删除行
			$(".deleteImg").live("click", function() {
				var tr = $(this).parent().parent();
				tr.remove();
				//order();
				addColor();
			});
		});
	});
	//奇偶行变色
	function addColor() {
		var t = document.getElementById("userLine").getElementsByTagName("tr");
		var a = "#FFFFFF"; //奇数行背景
		var b = "#FBFBFB"; //偶数行背景
		for (var i = 0; i < t.length; i++) {
			t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a
					: b;
		}
	}
	function changeLp(value) {
		if (value == 1) {
			$("#hidden3").hide();
		}
		if (value == 2) {
			$("#hidden3").show();
		}
	}
	function changeSz(value) {
		if (value == 1) {
			$("#hidden1").hide();
			$("#hidden2").hide();
			$("#jzlk").attr("rowspan", "1");
		}
		if (value == 2) {
			$("#hidden1").show();
			$("#hidden2").hide();
			$("#jzlk").attr("rowspan", "2");
		}
		if (value == 3) {
			$("#hidden1").hide();
			$("#hidden2").show();
			$("#jzlk").attr("rowspan", "2");
		}
	}
	function drawMap(mapId, type) {
		var id = ("map" + new Date().getTime());
		W.$.dialog(
				{
					id : id,
					title : '地图标绘',
					lock : true,
					parent : api,
					content : 'url:${ctx}/data/xtsz/map.action?fxryid=' + mapId
							+ '&type=' + type + '&name='
							+ encodeURI(encodeURI(mapId)),
					cancelVal : '关闭',
					cancel : true
				}).max();
	}
</script>
</head>
<body>
	<form id="fxryEdit" action="${ctx}/data/xtsz/fxrySave.action"
		method="post">
		<input type="hidden" id="areas" name="areas" value="${areas}" /><input
			type="hidden" id="periods" name="periods" value="${periods}"><input
			type="hidden" id="entity.id" name="entity.id" value="${entity.id}" /><input
			type="hidden" id="entity.org.orgId" name="entity.org.orgId"
			value="${entity.org.orgId}" /><input type="hidden" id="lops"
			value="${locSize}" />
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
								<td><input type="radio" id="lp" name="lp" value="1"
									onclick="changeLp(this.value)"
									<s:if test="#request.locP.size()==0">checked="checked"</s:if> />区县局默认设
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="lp" name="lp"
									value="2" onclick="changeLp(this.value)"
									<s:if test="#request.locP.size()>0">checked="checked"</s:if> />自定义</td>
							</tr>
							<tr id="hidden3">
								<td>
									<table class="comm-table">
										<thead>
											<tr>
												<th width="30%" style="text-align:center;font-weight:bold">开始时间</th>
												<th width="30%" style="text-align:center;font-weight:bold">结束时间</th>
												<th width="30%" style="text-align:center;font-weight:bold">间隔(分钟)</th>
												<td width="10%"
													style="text-align:center;background:#fbfbfb;" id="dw">
													<img class="addImg" src="${ctx}/images/add-row.gif"
													style="cursor:hand;width:16px;height:16px">
												</td>
											</tr>
										</thead>
										<tbody id="userLine" class="buttonArea">
											<s:iterator value="#request.locP" id="loc"
												status="st">
												<tr>
													<td style="text-align:left"><input type="text"
														required="required" label="开始时间" maxlength="5"
														class="startTime" validater="time"
														value='<s:property value="startTime" />' /></td>
													<td style="text-align:center"><input type="text"
														required="required" label="结束时间" maxlength="5"
														class="endTime" validater="time"
														value='<s:property value="endTiem" />' /></td>
													<td style="text-align:center"><input type="text"
														required="required" label="间隔" maxlength="3"
														validater="int" class="space"
														value='<s:property value="space" />' /></td>
													<td style="text-align:center;width:5%"><img
														class="deleteImg" src="${ctx}/images/delete-row.gif"
														style="cursor:hand;width:16px;height:16px"></td>
												</tr>
											</s:iterator>
										</tbody>
									</table> <textarea id="add_template" rows="" cols=""
										style="display:none">
										<tr>
											<td style="text-align:left">
												<input type="text" required="required" validater="time"
												label="开始时间" class="startTime" value="" maxlength="5" />
											</td>
											<td style="text-align:center">
												<input type="text" required="required" validater="time"
												label="结束时间" class="endTime" value="" maxlength="5" />
											</td>
											<td style="text-align:center">
												<input type="text" required="required" validater="int"
												label="间隔" maxlength="3" class="space" value="" />
											</td>
											<td style="text-align:center;width:5%">
												<img class="deleteImg" src="${ctx}/images/delete-row.gif"
												style="cursor:hand;width:16px;height:16px">
											
											</td>
										</tr>
									</textarea>
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
					<th>电子围栏：</th>
					<td colspan="3">
						<table class="comm-table" style="border:0">
							<tr>
								<th id="jzlk" style="width: 150px;">禁止离开区域：</th>
								<td colspan="3" id="railType">${entity.railType}</td>
							</tr>
							<tr id="hidden1">
								<td colspan="3">
									<table class="comm-table" style="border:0">
										<s:iterator value="#request.areaList" id="id" status="st">
											<s:if test="#st.index%4==0">
												<tr>
													<td colspan="3" style="border:0"><input
														type="checkbox" id="<s:property value="areaId" />"
														name="area" value='<s:property value="areaId" />' /><label
														for="<s:property value="areaId" />"><s:property
																value="areaName" />&nbsp;&nbsp;</label>
											</s:if>
											<s:elseif test="#st.index%4==3">
												<input type="checkbox" id="<s:property value="areaId" />"
													name="area" value='<s:property value="areaId" />' />
												<label for="<s:property value="areaId" />"><s:property
														value="areaName" /></label>
												</tr>
											</s:elseif>
											<s:else>
												<input type="checkbox" id="<s:property value="areaId" />"
													name="area" value='<s:property value="areaId" />' />
												<label for="<s:property value="areaId" />"><s:property
														value="areaName" />&nbsp;&nbsp;</label>
											</s:else>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr id="hidden2" style="display:none">
								<td colspan="3"><input type="button"
									class="bttn bicon-preview" id="dtbh01" value="地图标绘"
									onclick="javascript:drawMap('${entity.id}','01');" /></td>
							</tr>
							<tr>
								<th style="width: 150px;">禁止进入区域：</th>
								<td colspan="3"><input type="button"
									class="bttn bicon-preview" id="dtbh02" value="地图标绘"
									onclick="javascript:drawMap('${entity.id}','02');" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
