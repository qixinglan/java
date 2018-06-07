<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>默认值设置</title>
<%@ include file="/common/commonjs.jsp"%>
<script src="${ctx}/js/cmap/openlayer/OpenLayers.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/openlayer/lib/OpenLayers/Lang/zh-CN.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/cmap.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "mrsz",});
		var areas = $("#areas").val().split(",");
		for ( var area in areas) {
			var m = "#" + areas[area];
			$(m).attr("checked", "checked");
		}
		$(".dwjk-qxli").click(function() {
			var orgId = this.id;
			$.dialog({
				id : 'box12',
				width : '850px',
				height : '450px',
				title : '默认值设置查看页面',
				content : 'url:${ctx}/data/xtsz/view.action?orgId=' + orgId,
				cancelVal : '关闭',
				cancel : true
			});
		});
		addColor();
		//增加行
		$(".addImg").click(function() {
			var tbody = $("#userLine");
			var rowIndex = tbody.children().length;
			if(rowIndex<5){
				var tr = $("#add_template").val();
				tbody.append(tr);
				//order(); 
				addColor();
				//$.parser.parse();
				//给新增加行的第一个输入框获得焦点
				var newAdd = $("#userLine tr:last>td:eq(1)>input:eq(0)");
				newAdd.focus();
			}else{
				focError($("#dw"),"采集间隔不能设置过多!");
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
	//给表格添加序号   
	function order() {
		var uTable = document.getElementById("userLine");
		for (var i = 0; i < uTable.rows.length; i++) {
			uTable.rows[i].cells[0].innerHTML = (i + 1);
		}
	}
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
	function changeSz(value) {
		if (value == 1||value==null) {
			$("#hidden1").hide();
			$("#hidden2").hide();
			$("#jzlk").attr("rowspan","1");
		}
		if (value == 2) {
			$("#hidden1").show();
			$("#hidden2").hide();
			$("#jzlk").attr("rowspan","2");
		}
		if (value == 3) {
			$("#hidden1").hide();
			$("#hidden2").show();
			$("#jzlk").attr("rowspan","2");
		}
	}
	function submitForm() {
		var tbody = $("#userLine");
		var rowIndex = tbody.children().length;
		$("#periods").attr("value", rowIndex);
		var i = 0;
		$(".startTime").each(function() {
			$(this).attr("id", "startTime" + i);
			$(this).attr("name", "startTime" + i);
			i++;
		});
		i = 0;
		$(".endTime").each(function() {
			$(this).attr("id", "endTime" + i);
			$(this).attr("name", "endTime" + i);
			i++;
		});
		i = 0;
		$(".space").each(function() {
			$(this).attr("id", "space" + i);
			$(this).attr("name", "space" + i);
			i++;
		});
		if (!$.fields.validateForm($("#form"))){
			return false;
		}
		var railType=document.getElementsByName("railType");
		var railValue=1;
		for( var i in railType){
			if(railType[i].checked){
				railValue=railType[i].value;
			}
		}
		if(railValue==2){
			var j=0;
			var areas=document.getElementsByName("area");
			for(var i in areas){
				if(areas[i].checked){
					j++;
				}
			}
			if(j==0){
				focError($("#17"),"请至少选择一个行政区划");
				return false;
			}
		}else if(railValue==3){
			var dzwl=GetDzwl($("#orgName").val());
			if(dzwl=="null"){
				focError($("#dtbh01"),"地图标绘服务有误!");
				return false;
			}else if(/0(0|1)/.test(dzwl)){
				focError($("#dtbh01"),"请标绘禁止离开区域!");
				return false;
			}
		}
		if(!checkTime()){
			return false;	
		}
		$("#form").ajaxSubmit();
		$.dialog.alert("保存成功");
	}
	function drawMap(mapId,type) {
		$.dialog({
			id : 'testID',
			title : '地图标绘',
			content : 'url:${ctx}/data/xtsz/map.action?fxryid=' + mapId+'&type='+type+'&name='+encodeURI(encodeURI(mapId)),
			cancelVal : '关闭',
			cancel : true
		}).max();
	}
	//校验时间是否有重复
	function checkTime(){
		var userLine = $("#userLine");
		var rowIndex = userLine.children().length;
		var tbody = userLine.children();
		var flage = 0;
		for(var i=0;i<rowIndex;i++){
			var childrenA = tbody.get(i);
			var startTime = $(childrenA).find("#startTime"+i).attr("value");
			var endTime = $(childrenA).find("#endTime"+i).attr("value");
			var starti = sjjs(startTime);
			var endi = sjjs(endTime);
			for(var j=0;j<rowIndex;j++){
				var childrenB = tbody.get(j);
				var startTimej = $(childrenB).find("#startTime"+j).attr("value");
				var endTimej = $(childrenB).find("#endTime"+j).attr("value");
				var startj = sjjs(startTimej);
				var endj = sjjs(endTimej);
				if(starti<=endi && startj<endj){
					if( (starti>=startj && starti<=endj) || (endi >=startj && endi <=endj)){
						flage++;
					}
				}else if(starti<endi && startj>endj){
					if( (starti>=0 && starti<=endj) || (endi >=startj && endi <=2400)){
						flage++;
					}
				}else if(starti>=endi && startj>endj){
					if( (starti>=startj && starti<=2400) || (endi >=0 && endi <=endj)){
						flage++;
					}
				}else if(starti==endi && startj==endj){
					if(starti==startj){
						flage++;
					}
				}
			}
		}
		if(flage>rowIndex){
			$.dialog.alert("时间设置重复，请确认！");
			return false;
		}else{
			return true;
		}
	}
	//计算时间
	function sjjs(time){
		var array = time.split(":");
		var newTime="";
		for(var i=0;i<array.length;i++){
			newTime += array[i];
		}
		return parseFloat(newTime);
	}
</script>
</head>
<body onload="changeSz(${entity.railType})">
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 配置管理 -> 系统默认值设置</span>
		</div>
		<div class="container-top"></div>
		<!--列表-->
		<div class="fl_l" style="text-align:center;font-size: 18px;font-weight:bold">
			<span>
			<c:if test="${user.wunit.orgType == '1'}">市局系统设置</c:if>
			<c:if test="${user.wunit.orgType == '2'}">区县系统设置</c:if>
			</span>
		</div>
		<div class="fl-r">
			<span style="margin-right:-10px"> <input
				style="align:right;" type="button"
				class="bttn bicon-save" id="btnSave" value="保存"
				onclick="submitForm();" />
			</span>
		</div>
		<form action="${ctx}/data/xtsz/update.action" method="post" id="form"
			name="form">
			<input type="hidden" id="orgName" value="${orgName}"/>
			<input type="hidden" id="periods" name="periods" value="${periods}">
			<input type="hidden" id="areas" name="areas" value="${areas}">
			<table class="comm-table">
				<tr>
					<th >定位信息采集间隔：</th>
					<td colspan="3">
						<table class="comm-table">
							<thead>
								<tr>
									<th width="30%" style="text-align:center;font-weight:bold">开始时间</th>
									<th width="30%" style="text-align:center;font-weight:bold">结束时间</th>
									<th width="30%" style="text-align:center;font-weight:bold">间隔(分钟)</th>
									<td width="10%" style="text-align:center;background:#fbfbfb;" id="dw">
										<img class="addImg" src="${ctx}/images/add-row.gif"
										style="cursor:hand;width:16px;height:16px">
									</td>
								</tr>
							</thead>
							<tbody id="userLine" class="buttonArea">
								<s:iterator value="#request.entity.locPeriods" id="loc"
									status="st">
									<tr>
										<td style="text-align:left"><input type="text" required="required" label="开始时间"maxlength="5"
											class="startTime" validater="time"   value='<s:property value="startTime" />' />
										</td>
										<td style="text-align:center"><input type="text" required="required" label="结束时间"maxlength="5"
											class="endTime"  validater="time"  value='<s:property value="endTiem" />' /></td>
										<td style="text-align:center"><input type="text" required="required" label="间隔" maxlength="3" 
											validater="int" 
											class="space" value='<s:property value="space" />' /></td>
										<td style="text-align:center;width:5%"><img
											class="deleteImg" src="${ctx}/images/delete-row.gif"
											style="cursor:hand;width:16px;height:16px"></td>
									</tr>
								</s:iterator>
							</tbody>
						</table> 
						<textarea id="add_template" rows="" cols="" style="display:none">
							<tr>
								<td style="text-align:left">
									<input type="text" required="required" validater="time" label="开始时间" class="startTime" value="" maxlength="5"/>
								</td>
								<td style="text-align:center">
									<input type="text" required="required" validater="time" label="结束时间" class="endTime" value="" maxlength="5"/>
								</td>
								<td style="text-align:center">
									<input type="text" required="required" validater="int" label="间隔" maxlength="3"class="space" value="" />
								</td>
								<td style="text-align:center;width:5%">
									<img class="deleteImg" src="${ctx}/images/delete-row.gif"
										style="cursor:hand;width:16px;height:16px">
								</td>
							</tr>
						</textarea>
					</td>
				</tr>
				<tr>
					<th>电子围栏：</th>
					<td colspan="3">
						<table class="comm-table" style="border:0">
							<tr>
								<th id="jzlk" style="width: 150px;">禁止离开区域：</th>
								<td colspan="3">
									<input type="radio" id="railType"
										name="railType" onclick="changeSz(1)" value="1"
										<s:if test="#request.entity.railType==1||#request.entity.railType==null">checked="checked"</s:if> />北京市全境 &nbsp;&nbsp;&nbsp;&nbsp;
									<s:if test="#request.orgType==2">
										<input type="radio" id="railType" name="railType" onclick="changeSz(2)" value="2"
											<s:if test="#request.entity.railType==2">checked="checked"</s:if> />行政区划 &nbsp;&nbsp;&nbsp;&nbsp; 
										<input type="radio" id="railType" name="railType" onclick="changeSz(3)" value="3"
											<s:if test="#request.entity.railType==3">checked="checked"</s:if> />标绘设置
									</s:if>
								</td>
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
												<input type="checkbox"
													id="<s:property value="areaId" />" name="area"
													value='<s:property value="areaId" />' />
													<label for="<s:property value="areaId" />"><s:property
														value="areaName" /></label>
												</tr>
											</s:elseif>
											<s:else>
												<input type="checkbox"
													id="<s:property value="areaId" />" name="area"
													value='<s:property value="areaId" />' />
													<label for="<s:property value="areaId" />"><s:property
													value="areaName" />&nbsp;&nbsp;</label>
											</s:else>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr id="hidden2" style="display:none">
								<td colspan="3">
									<input type="button"
										class="bttn bicon-preview" id="dtbh01" value="地图标绘"
										onclick="javascript:drawMap('${orgName}','01');" />
								</td>
							</tr>
							<s:if test="#request.orgType==2">
								<tr>
									<th style="width: 150px;">禁止进入区域：</th>
									<td colspan="3">
										<input type="button"
											class="bttn bicon-preview" id="dtbh02" value="地图标绘"
											onclick="javascript:drawMap('${orgName}','02');" />
									</td>
								</tr>
							</s:if>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<br/>
		<div class="fl_l">
				<span>
					<c:if test="${user.wunit.orgType == '1'}">区县系统设置情况查看：</c:if>
					<c:if test="${user.wunit.orgType == '2'}">市局系统设置情况查看：</c:if>
				</span>
		</div>
		<br/>
		<ul class="dwjk-box5">
			<s:iterator value="#request.orgList" id="id">
				<li class="dwjk-qxli" id='<s:property value="orgId" />'><s:property
					value="orgName" /></li>
			</s:iterator>
		</ul>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
