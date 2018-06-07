<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <%@taglib url='http://java.sun.com/jstl/core' prefix='c'%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sim卡设备管理</title>
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
	var status='<%=request.getParameter("status")%>';
	var useUnit='<%=request.getParameter("useUnit")%>';
	var useDeviceType=<%=request.getParameter("useDeviceType")%>;
	var emptyTextStatus="全部";
	var emptyTextUseUnit="全部";
	var emptyuseDeviceType="全部";
	if(useUnit!='null'&&useUnit!=''){
		emptyTextUseUnit=$.organization.formatter()(useUnit);
	}
	if(status!='null'&&status!=''){
		emptyTextStatus=$.dictionary.formatter('DZJGSBZT')(status);
	}
	var re=/^[0-9]+.?[0-9]*$/;//判断是否为数字
	if(re.test(useDeviceType)){
		emptyuseDeviceType=$.dictionary
		.formatter('SIMTYPE')(useDeviceType);
	}
	$(function() {
		$("#mynav0").removeClass("navnew-c");
		$("#dzjg").addClass("navnew-c");
		if(ORG_TYPE=="qxsfj"){
			$("#btnAdd").hide();
			$("#btnDel").hide();
		}
		if(emptyuseDeviceType=='全部'){
			$.dictionary.combobox("#useDeviceType", "useDeviceType", "SIMTYPE", {
				width:'68%',
				allowBlank : true, 
				attr:{searchType: "eq"},
				fieldClass:"search-form-field"
			});
		}else{
			$("#useDeviceType").append("<input value='"+emptyuseDeviceType+"' readonly='readonly'></input>");
		}
		if(emptyTextStatus=='全部'){
			$.dictionary.combobox("#status", "status", "SIMSBZT", {
				allowBlank : true,
				emptyText :emptyTextStatus,
				attr:{searchType: "eq"},
				fieldClass:"search-form-field"
			});
		}else{
			$("#status").append("<input value='"+emptyTextStatus+"' readonly='readonly'></input>");
		}
		if(emptyTextUseUnit=='全部'){
			//当前使用单位
			$.organization.combobox("#useUnit", "useUnit", ${user.wunit.bm}, {
				allowBlank : true,
				level : 10,
				//showRoot : true,
				showItself : true,
				emptyText : emptyTextUseUnit,
				attr:{searchType: "eq"},
				notShowType:"4,5,6,7,8,9",
				fieldClass:"search-form-field"
			});
		}else{
			$("#useUnit").append("<input value='"+emptyTextUseUnit+"' readonly='readonly'></input>");
		}
		//购买单位
		$.organization.combobox("#buyUnit", "buyUnit", ${user.wunit.bm}, {
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
							url : '${ctx}/data/sbgl/sim/search.action?status='+status+'&useUnit='+useUnit+'&useDeviceType='+useDeviceType,
							colNames : [ '','SIM卡号', '购买单位', '购买日期','使用单位（司法局）','使用单位（司法所）','状态',
											'操作'],
											
								colModel : [
											
											{
												name : 'deviceNumber',
												index : 'deviceNumber',
												hidedlg : false,
												hidden : true,
												key : true
											},
											{
												name : 'phoneNumber',
												index : 'phoneNumber',
												width : 40,
												maxlength:11,
												align : 'center'
											},
											{
												name : 'buyUnit',
												index : 'buyUnit',
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {//value 本单元数据    rwat  本行对象
													return $.organization.formatter()(rwdat.buyUnit);
												}
											},
											{
												name:'buyDate',
												index:'buyDate',
												width : 60,
												formatter : "date",
												align : 'center',
												formatoptions : {
													newformat : 'Y-m-d'
												}
											},
											{
												name : 'useUnit',
												index : 'org',
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.useUnit);
												}
											},
											{
												name : 'useSfsUnit',
												index : 'sfsOrg',
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.useSfsUnit);
												}
											},
											{
											
												name : 'status',
												index : 'status',
												width : 20,
												shrinkToFit : false,
												align : 'center',
												formatter : function(value, opts, rwdat){
													return $.dictionary
													.formatter('SIMSBZT')(rwdat.status);
													/* status=rwdat.status;
													if(status=='4'){
														return '注销';
													}if(status=='1'){
														return '未用';
													}
													if(status=='2'){
														return '已用';
													}
													if(status=='3'){
														return '未开通';
													}
													return 'null'; */
												}
												},
											{
												name : 'cz',
												index : 'cz',
												width : 200,
												align : 'center',
												sortable : false,
												fixed : true,
												formatter : function(value, opts, rwdat) {
													var status=$.dictionary.formatter('SIMSBZT')(rwdat.status);
													if(ORG_TYPE=='qxsfj'){//区县司法局
														 return  "<a href='${ctx}/data/sbgl/sim/view.action?deviceNumber="+
															rwdat.deviceNumber+
															"' title='' target='_blank' class='a-style'>查看</a>";
													}
													if(status=="未用"){
														return "<a href='${ctx}/data/sbgl/sim/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>"+
														"<a href='javascript:statusLogout(\""+ rwdat.deviceNumber+ "\",\""+ rwdat.status+ "\");' class='a-style'>注销</a>";
													}
													if(status=="已用"){
														return "<a href='${ctx}/data/sbgl/sim/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>";
													}
													if(status=="未开通"){
														return "<a href='${ctx}/data/sbgl/sim/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>"+
														"<a href='javascript:statusLogout(\""+ rwdat.deviceNumber+ "\",\""+ rwdat.status+ "\");' class='a-style'>注销</a>"+
														"<a href='javascript:statusStart(\""+ rwdat.deviceNumber+ "\",\""+ rwdat.status+ "\");' class='a-style'>开通</a>";
													}	
													if(status=="注销"){
														return "<a href='${ctx}/data/sbgl/sim/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>";
													}	
												}
											}
										],
							multiselect : true,
							//rownumbers : true,
							pager : '#mainGridPager',
							sortname:'buyDate',
							sortorder:'desc'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		
	});
	function statusLogout(){
		var id=arguments[0];
		$.ajax({
			type:"post",
			url:"${ctx}/data/sbgl/sim/changeStatus.action",
			data:"id="+id+"&status=4",
			success:function(){
				$("#mainGrid").trigger("reloadGrid");
			}
		})
	}
	function statusStart(){
		var id=arguments[0];
		$.ajax({
			type:"post",
			url:"${ctx}/data/sbgl/sim/changeStatus.action",
			data:"id="+id+"&status=1",
			success:function(){
				$("#mainGrid").trigger("reloadGrid");
			}
		})
	}
	//新增sim卡
	function openEdit() {
		var id = arguments[0] || ("e" + new Date().getTime());
		$.dialog({
					id : id,
					width: '850px', 
					height: '350px', 
					title : '新增sim卡',
					content : 'url:${ctx }/data/sbgl/sim/input.action',
					ok : function() {
						var success = true;
						var form = $("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("saveForm");
							form = $(form);
							//做验证检查是否为空
							if (!$("iframe[name='" + id + "']").get(0).contentWindow.$.fields.validateForm(form)){
								return false;
							}
							var ajax_option = {
									url:form.attr("action"),
									success:function(data){
										$.dialog.alert(data.msg);
										$("#mainGrid").trigger("reloadGrid");
									}
							}
							form.ajaxSubmit(ajax_option);//发送ajax请求
							
					},
					okVal : '保存',
					cancelVal : '取消',
					cancel : true
				});
	}
	function openView() {
		//arguments为参数数组，【0】设备id
		var deviceNumber = arguments[0] ;
		$.dialog({
			
			width: '1300px', 
			height: 'auto', 
			title : 'sim卡信息查看',
			content : 'url:view.action?deviceNumber='+ deviceNumber,
			cancelVal : '关闭',
			cancel : true
		});
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
		data["jqgrid.cols"] = data["jqgrid.cols"].slice(0);
		var url = "${ctx }/data/sbgl/sim/export.action?" + $.param(data);
		$("#downloadFrame").attr("src",url);
	}
	function openDel() {
		var grid = $("#mainGrid");
		var grid = $("#mainGrid");
		var ids = grid.getGridParam("selarrrow");
		var usedIds = [];
		for ( var id in ids) {
			if (grid.getRowData(ids[id]).status === "已用")
				usedIds.push(grid.getRowData(ids[id]).phoneNumber);
		} 
		if (usedIds.length != 0){
			$.dialog.alert("sim卡号为" + usedIds.join(",") + "的状态为已用，不可删除。",false,this);
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
						url : "${ctx }/data/sbgl/sim/del.action",
						data : {deviceNumber:ids.join(",")},
					});
					$("#mainGrid").trigger("reloadGrid");
				}, function(){
					
				});
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管&nbsp;-&gt;&nbsp;设备管理&nbsp;-&gt;&nbsp;sim卡设备信息管理</span>
		</div>
		<div class="container-top">

			<table class="search-table" id="searchDiv">
				<tbody>
					<tr>
						<th>购买单位：</th>
						<td id="buyUnit"></td>
						<th>当前使用单位：</th>
						<td id="useUnit"></td>
						<th>设备状态：</th>
						<td id="status"></td>
						<td><input id="searchBtn" type="button"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
					<!-- searchType  sql：eq =  cn  like   ge >  le  <-->
						<th>SIM卡号：</th>
						<td><input type="text" name="phoneNumber" searchType="cn" class="search-form-field"/></td>
						<th>SIM卡类型：</th>
						<td id="useDeviceType"></td>
						<th>购买日期：</th>
						<td ><input id="bjsj1" class="Wdate" type="text" style="width: 85px"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" maxlength="16"
							name="buyDate" searchType="ge" />至<input class="Wdate" type="text" style="width: 85px" id="bjsj2"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" maxlength="16"
							name="buyDate" searchType="le" /></td>
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" name="btnAdd" id="btnAdd" class="bttn bicon-add" value="新增"
				onclick="openEdit()" /> <input type="button" name="btnDel" id="btnDel"
				class="bttn bicon-delete" onclick="javascript:openDel();" value="删除" />
			 <input type="button"
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
