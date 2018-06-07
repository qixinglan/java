<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史报警信息管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"lsbj"});
		//报警级别
	    $.dictionary.combobox("#alarmLevel", "alarmLevel", "BJJB", {searchType : "eq", attr:{searchType:"eq","class":"search-form-field"}});
		//报警类型
		$.dictionary.combobox("#alarmType", "alarmType", "BJLX",{attr:{searchType:"eq","class":"search-form-field"}});
		//矫正类别
		$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"eq","class":"search-form-field"}});
		//执行机关
		$.organization.combobox("#executeUnit", "executeUnit", ${user.wunit.bm}, {
	    	allowBlank : true,
			level : 10,
			showRoot : false,
			showItself : true,
			notShowType:"4,5,6,7,8,9",
			emptyText : '全部',
			attr:{searchType:"eq","class":"search-form-field"}});
		
		
		$.dictionary.combobox("#status", "status", "CLZT", {attr:{searchType:"eq","class":"search-form-field"}});
			

		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							autowidth : true,
							url : '${ctx}/data/dzjg/lsbjxxgl/search.action?orgId='+${user.wunit.bm},
							colNames : [ '', '报警级别', '报警类型', '报警时间', '姓名', '矫正类别','报警内容',
									'执行机关', '状态', '处理时间', '处理人/方式',
									'操作' ],
							colModel : [
									{
										name : 'alarmId',
										index : 'alarmId',
										hidedlg : false,
										hidden : true
									},
									{
										name : 'alarmLevel',
										index : 'alarmLevel',
										width : 40,
										shrinkToFit : false,
										align : 'center',
                                        formatter : $.dictionary.formatter('BJJB'),
                                        sortable: true
									},
									{
										name : 'alarmType',
										index : 'alarmType',
										width : 40,
										align : 'left',
										formatter : $.dictionary.formatter('BJLX'),
										sortable: true
									},
									{
										name : 'alarmTime',
										index : 'alarmTime',
										shrinkToFit : false,
										width : 60,
										align : 'center',
										sortable: true
									},
									{
										name : 'name',
										index : 'name',
										width : 40,
										shrinkToFit : false,
										align : 'left',
										sortable: true
									},
									{
										name : 'adjustType',
										index : 'adjustType',
										width : 80,
										align : 'center',
										formatter : $.dictionary.formatter('JZLB'),
										fixed:true,
										sortable: true,
									},
									{
										name : 'alarm',
										index : 'alarm',
										width : 140,
										align : 'left',
										fixed:true
									},
									{
										name : 'executeUnit',
										index : 'executeUnit',
										width : 40,
										align : 'left',
										sortable: true,
										formatter:function(value, opts, rwdat){
											return $.organization.formatter()(rwdat.executeUnit);
										}
									},
									{
										name : 'status',
										index : 'status',
										width : 30,
										align : 'center',
										formatter : $.dictionary.formatter('CLZT')
									},
									{
										name : 'handleTime',
										index : 'handleTime',
										width : 60,
										align : 'center'
									},
									{
										name : 'handler',
										index : 'handler',
										width : 40,
										align : 'center'
									},
									{
										name : 'cz',
										index : 'cz',
										width : 80,
										align : 'center',
										sortable : false,
										fixed : true,
										formatter : function(value, opts, rwdat) {
											if(rwdat.status == '2')
											{
											    return "<a href='javascript:Cl(\"" + rwdat.alarmId+"\");' title='' class='a-style'>处理</a>";
											}
											else{
											    return "<a href='javascript:openView(\""
													+ rwdat.alarmId
													+ "\");' title='' class='a-style'>详情</a>";											
											}
										}
									} ],
						    
							multiselect : false,
							pager : '#mainGridPager',
							sortname : 'alarmTime,alarmLevel,alarmType,status',
							sortorder : "desc,asc,desc,desc"
						});
		/* $("#mainGrid").addSearchForm("#search"); */
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});

	//导出
	function openExcel() {
		//var colNames = $("#mainGrid").getGridParam("colNames");
		//var sortName = $("#mainGrid").getGridParam("sortname");
		//window.open("${ctx }/data/dzjg/lsbjxxgl/excel.action?colNames=" + colNames
			//	+ "&sortName=" + sortName, "downloadFrame");
		var grid = $("#mainGrid").getGridParam('postData') || {};
		var data = {};
		for (var i in grid){
			data["jqgrid."+i] = grid[i];
		}
		var t = data["jqgrid.cols"].split(",").slice(1);
		t.pop();
		data["jqgrid.cols"]=t.join(",");
		var url = "${ctx }/data/dzjg/lsbjxxgl/excel.action?"+$.param(data);
		$("#downloadFrame").attr("src",url);
	}
	function openView(id) {
		var dataRow = $("#mainGrid").jqGrid("getRowData", id);

		$.dialog({
			id : id,
			width : '900px',
			height: '350px',
			title : '历史报警详情查看',
			content : 'url:bjxxView.jsp?id=' + id + '&t=' + new Date().getTime(),// view.action?id=' + id,
			cancelVal : '关闭',
			cancel : true,
			data : dataRow
		});
	}
	function Cl(id) {
		$.dialog({
			id : 'id',
			width: '500px', 
			height: '200px', 
			title:'处理信息',
			content : 'url:${ctx }/data/dzjg/lsbjxxgl/wclbjxxEdit.jsp?id='+id,
			okVal:'保存',
			ok: function(){
			    if($($(this).attr("iframe").contentDocument.getElementById("jlnr")).val().length<=100){
			    	$($(this).attr("iframe").contentDocument.getElementById("form1")).ajaxSubmit();
					$("#mainGrid").trigger("reloadGrid");
				}else{
					$($(this).attr("iframe").contentDocument.getElementById("msg")).text("字数不能超过100");
					return false;
				}
			}, 
			cancelVal : '关闭',
			cancel : true
		});
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 历史报警信息管理</span>
		</div>
		<div class="container-top" id="searchDiv">
			<table class="search-table" >
				<tbody>
					<tr>
						<th class="search-form-label">报警级别：</th>
						<td id="alarmLevel" />
						<th class="search-form-label">报警类型：</th>
						<td id="alarmType"/>
						<th class="search-form-label">报警时间：</th>
						<td nowrap="nowrap">
							<input id="bjsj1" style="width: 85px" name="alarmTime" type="text" searchType="ge" class="Wdate inputStyle" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />至<input 
							id="bjsj2"  style="width: 85px" name="alarmTime" type="text" searchType="le" class="Wdate inputStyle"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
						</td>
						<td><input type="button" id="searchBtn" class="bttn bicon-search"  value="查询" />
						</td>
					</tr>
					<tr>
						<th>矫正类别：</th>
						<td id="adjustType"></td>
						<th>执行机关：</th>
						<td id="executeUnit"></td>
						<th>处理时间：</th>
						<td nowrap="nowrap">
							<input id="qcsj1" style="width: 85px" name="handleTime" type="text" searchType="ge"
								class="Wdate inputStyle" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,maxDate:'#F{$dp.$D(\'qcsj2\')}'})" />至<input
								id="qcsj2" style="width: 85px" name="handleTime" type="text" searchType="le" class="Wdate inputStyle" 
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,minDate:'#F{$dp.$D(\'qcsj1\')}'})" />
						</td>
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" />
						</td>
					</tr>
					<tr>
						<th>状态：</th>
						<td id="status">
						<th>姓名：</th>
						<td><input type="text" id="name" name="name"class="search-form-field" searchType="cn"  />
						<th>处理人/方式：</th>
						<td><input name="handler" id="qcr" type="text"
							class="search-form-field" searchType="cn" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>

		<!--列表-->
		<!-- 
		<div class="buttonArea operation">
			<input type="button" id="btnExcel" class="bttn bicon-excel" onclick="openExcel()"
				value="导出" />
		</div>
		 -->
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
