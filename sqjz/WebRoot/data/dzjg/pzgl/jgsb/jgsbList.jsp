<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子监管设备管理</title>
<%@ include file="/common/commonjs.jsp"%>
<%-- <script language="javascript" src="${ctx}/js/custom/superviseDevice.js" type="text/javascript">
	$.equip.load("${ctx}/data/dzjg/jgsb/equipableMachine.action");
</script> --%>
<style>
 .searchStyle{
        	width:140px !important;
        	vertical-align: top !important;
        }
</style>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "dzjgsb",});
 		$.dictionary.combobox("#deviceVersion", "deviceVersion", "SBXH", {
			allowBlank : true,
			searchType : "eq",
			attr:{searchType: "eq"},
			fieldClass:"search-form-field"
		}); 
		
		$.dictionary.combobox("#deviceType", "deviceType", "SBLX", {
			allowBlank : true,
			attr:{searchType: "eq"},
			fieldClass:"search-form-field"
		});
		$.dictionary.combobox("#deviceStatus", "deviceStatus", "SBZT", {
			allowBlank : true,
			attr:{searchType: "eq"},
			fieldClass:"search-form-field"
		});
		//$.equip.equipableDevice("#deviceVersion","deviceVersion",{});

		//当前使用单位
		$.organization.combobox("#cname", "org", ${user.wunit.bm}, {
			allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"4,5,6,7,8,9",
			fieldClass:"search-form-field"
		});
		//购买单位
		$.organization.combobox("#procureUnit", "procureUnit", ${user.wunit.bm}, {
			allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			fieldClass:"search-form-field"
		});
		
		//表格初始化
		$("#mainGrid").jqGrid(
						{
							rowNum : 10,
							url : '${ctx}/data/dzjg/jgsb/search.action',
							colNames : [ '', '设备编号', '主机编号','腕表编号','主机电话号码', 
											'设备状态',  '购买单位', '当前使用单位', '购买日期',
											'操作','' ],

									colModel : [
											{
												name : 'deviceId',
												index : 'deviceId',
												hidedlg : false,
												hidden : true,
												key : true
											},
											{
												name : 'devicePair.pairDeviceNumber',
												index : 'devicePair.pairDeviceNumber',
												shrinkToFit : false,
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return rwdat.devicePair
													&& rwdat.devicePair.pairDeviceNumber
													|| '';
												}
											},
											{
												name : 'deviceNumber',
												index : 'deviceNumber',
												width : 30,
												align : 'left'
											},
											{
												name : 'devicePair.watch.deviceNumber',
												index : 'devicePair.watch.deviceNumber',
												shrinkToFit : false,
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return rwdat.devicePair
													&& rwdat.devicePair.watch.deviceNumber
													|| '';
												}
											},
											{
												name : 'connectPhone',
												index : 'connectPhone',
												width : 40,
												maxlength:11,
												align : 'left'
											},
											{
												name : 'deviceStatus',
												index : 'deviceStatus',
												width : 20,
												shrinkToFit : false,
												align : 'center',
												formatter : function(value, opts, rwdat){
													return $.dictionary
													.formatter('SBZT')(rwdat.deviceStatus);
												}
											},
											{
												name : 'procureUnit',
												index : 'procureUnit',
												width : 40,
												align : 'left',
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.procureUnit);
												}
											},
											{
												name : 'org',
												index : 'org',
												width : 40,
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.org);
												}
											},
											{
												name : 'procureDate',
												index : 'procureDate',
												width : 30,
												align : 'center',
												formatter : "date",
												formatoptions : {
													newformat : 'Y-m-d'
												}
											},
									{
										name : 'cz',
										index : 'cz',
										width : 80,
										align : 'center',
										sortable : false,
										fixed : true,
										formatter : function(value, opts, rwdat) {
											return "<a href='javascript:openView(\""
													+ rwdat.deviceId
													+ "\");' title='' class='a-style'>查看</a><a href='javascript:openEdit(\""
													+ rwdat.deviceId
													+ "\");' title='' class='a-style'>编辑</a>";
										}
									},{
										name : 'devicePair.beginDate',
										index : 'devicePair.beginDate',
										width : 0,
										hidden:true
									} ],
							multiselect : true,
							//rownumbers : true,
							pager : '#mainGridPager',
							sortname:'devicePair.beginDate',
							sortorder:'desc'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});

	function openEdit() {
		var id = arguments[0] || ("e" + new Date().getTime());
		$.dialog({
					id : id,
					width: '850px', 
					height: '350px', 
					title : arguments[0] && '电子监管设备信息编辑' || '电子监管设备信息新增',
					content : 'url:input.action'
							+ (arguments[0] ? '?deviceId=' + id : ''),
					ok : function() {
						var success = true;
						var form = $("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("saveForm");
							form = $(form);
							if (!$("iframe[name='" + id + "']").get(0).contentWindow.$.fields.validateForm(form)){
								return false;
							}
							var deviceId = form.find("#deviceId").val();
							if(!deviceId){
								var pairId = form.find("#pairDeviceNumber").val();
								var machineId = form.find("#machineDeviceNumber").val();
								var watchId = form.find("#watchDeviceNumber").val();
								var ajax_option = {
										url:form.attr("action"),
										success:function(data){
											$("#mainGrid").trigger("reloadGrid");
										}
								}
								if(pairId==machineId){
									$("iframe[name='" + id + "']").get(0).contentWindow.focError(form.find("#pairDeviceNumber"),"配对编号不能和主机编号相同");
									return false;
								}
								if(pairId==watchId){
									$("iframe[name='" + id + "']").get(0).contentWindow.focError(form.find("#pairDeviceNumber"),"配对编号不能和腕表编号相同");
									return false;
								}
								if(watchId==machineId){
									$("iframe[name='" + id + "']").get(0).contentWindow.focError(form.find("#watchDeviceNumber"),"腕表编号不能和主机编号相同");
									return false;
								}
							}
							$.ajax({
								   type: "POST",
								   async:false,
								   url: "${ctx }/data/dzjg/jgsb/checkDevicePair.action",
								   data: "pairId="+pairId+"&machineId="+machineId+"&watchId="+watchId,
								   success:function(data){
									   	if(data.msg!=null){
									   		$.dialog.alert(data.msg);
									   		success = false;
									   		return false;
									   	}
									    form.ajaxSubmit(ajax_option);
									}
								 });
							if(!success)
								return success;
							$("#mainGrid").trigger("reloadGrid");
					},
					okVal : '保存',
					cancelVal : '取消',
					cancel : true
				});
	}
	function openView() {
		var id = arguments[0] || ("v" + new Date().getTime());
		$.dialog({
			id : id,
			width: '1050px', 
			height: '350px', 
			title : '电子监管设备信息查看',
			content : 'url:view.action?deviceId=' + id,
			cancelVal : '关闭',
			cancel : true
		});
	}
	function openImport() {
		$.container.popup({
			title: '电子监管设备信息导入',
			save: 'import.action',
			fields:[
				{text:"电子监管设备信息",
				 type:'file',
				 name:'importFile',
				 url:"${ctx}/data/common/upload.action",
				 multiple:true,
				 accept:"application/vnd.ms-excel",
				 spath:"jgsb/import"
				}
			],
			yes : function(){
				$.dialog.alert("导入成功!",null,this);
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		},{width: "450px"});
	}
	function openExcel() {
		var grid = $("#mainGrid").getGridParam('postData') || {};
		var data = {};
		for (var i in grid){
			data["jqgrid."+i] = grid[i];
		}
		var t = data["jqgrid.cols"].split(",").slice(1);
		t.pop();
		data["jqgrid.cols"]=t.join(",");
		data["jqgrid.cols"] = data["jqgrid.cols"].slice(0,-3);
		var url = "${ctx }/data/dzjg/jgsb/export.action?" + $.param(data);
		$("#downloadFrame").attr("src",url);
		
	}
	function openDel() {
		var grid = $("#mainGrid");
		var ids = grid.getGridParam("selarrrow");
		var usedIds = [];
		for ( var id in ids) {
			if (grid.getRowData(ids[id]).deviceStatus === "已用")
				usedIds.push(grid.getRowData(ids[id]).deviceNumber);
		}
		if (usedIds.length != 0){
			$.dialog.alert("设备编号为" + usedIds.join(",") + "的状态为已用，不可删除。",false,this);
			return false;
		}
		if (ids == null || ids.length < 1) {
			$.dialog.alert("请选择删除数据！",false,this);
			return false;
		}
		$.dialog.confirm('你确认要删除记录吗？',function (){
					$.ajax({
						type : "POST",
						async : false,
						url : "${ctx }/data/dzjg/jgsb/del.action",
						data : {id:ids.join(",")},
					});
					$("#mainGrid").trigger("reloadGrid");
				}, function(){
					
				});
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管&nbsp;-&gt;&nbsp;配置管理&nbsp;-&gt;&nbsp;电子监管设备信息管理</span>
		</div>
		<div class="container-top">

			<table class="search-table" id="searchDiv">
				<tbody>
					<tr>
						<th>购买单位：</th>
						<td id="procureUnit"></td>
						<th>当前使用单位：</th>
						<td id="cname"></td>
						<th>设备状态：</th>
						<td id="deviceStatus"></td>
						<td><input id="searchBtn" type="button"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
						<th>设备编号：</th>
						<td><input type="text" name="devicePair.pairDeviceNumber" searchType="cn" class="search-form-field"/></td>
						<th>主机编号：</th>
						<td><input type="text" name="deviceNumber" searchType="cn" class="search-form-field"/></td>
						<th>主机电话号码：</th>
						<td><input type="text" name="connectPhone" searchType="cn" class="search-form-field"/></td>
						<td></td>
					</tr>
					<tr>
						<th>购买日期：</th>
						<td ><input id="bjsj1" class="Wdate" type="text" style="width: 85px"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" maxlength="16"
							name="procureDate" searchType="ge" />至<input class="Wdate" type="text" style="width: 85px" id="bjsj2"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" maxlength="16"
							name="procureDate" searchType="le" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" name="btnAdd" class="bttn bicon-add" value="新增"
				onclick="openEdit()" /> <input type="button" name="btnDel"
				class="bttn bicon-delete" onclick="javascript:openDel();" value="删除" />
			<input type="button" name="btnFile" class="bttn bicon-file"
				value="导入" onclick="javascript:openImport();" /> <input type="button"
				name="btnExcel" class="bttn bicon-excel"
				onclick="javascript:openExcel();" value="导出" />
		</div>
		<div id="search"></div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<iframe id="downloadFrame" style="display:none"/>
</body>
</html>
