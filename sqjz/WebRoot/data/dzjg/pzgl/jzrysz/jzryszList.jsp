<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员设置</title>
<%@ include file="/common/commonjs.jsp"%>
<script src="${ctx}/js/cmap/openlayer/OpenLayers.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/openlayer/lib/OpenLayers/Lang/zh-CN.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/cmap.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "fxrysz",});
		$.dictionary.combobox("#jzlb", "adjustType", "JZLB", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			attr:{searchType:"eq","class":"search-form-field"}
		});
		$.dictionary.combobox("#dzwl", "railType", "DZWL", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			attr:{searchType:"eq","class":"search-form-field"}
		});
		$.dictionary.combobox("#jgzt", "status", "DZJGZT", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			attr:{searchType:"eq","class":"search-form-field"}
		});
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/xtsz/search.action',
							mtype : "POST",
							datatype : 'json',
							colModel : [
									{
										name : 'id',
										index : 'id',
										label : 'id',
										hidedlg : false,
										hidden : true
									},
									{
										name : 'org.cname',
										index : 'org.cname',
										label : '矫正机构',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'name',
										index : 'name',
										label : '姓名',
										width : 100,
										align : 'left'
									},
									{
										name : 'adjustType',
										index : 'adjustType',
										label : '矫正类别',
										formatter : 'dictionary',
										formatoptions : {
											code : 'JZLB'
										},
										width : 30,
										align : 'left'
									},
									{
										name : 'pname',
										index : 'pname',
										label : '监管民警',
										width : 30,
										align : 'center'
									},
									{
										name : 'status',
										index : 'status',
										label : '电子监管状态',
										formatter : 'dictionary',
										formatoptions : {
											code : 'DZJGZT'
										},
										width : 30,
										align : 'center'
									},
									{
										name : 'railType',
										index : 'railType',
										label : '电子围栏',
										formatter : 'dictionary',
										formatoptions : {
											code : 'DZWL'
										},
										width : 30,
										align : 'center'
									},
									{
										name : 'cz',
										index : 'cz',
										label : '操作',
										width : 100,
										align : 'center',
										fixed : true,
										sortable : false,
										formatter : function(value, opts, rwdat) {
											return "<a href='javascript:openView(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>查看</a>&nbsp;&nbsp;<a href='javascript:openEdit(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>设置</a>";
										}
									} ],
							multiselect : false,
							rownumbers : true,
							pager : '#mainGridPager'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	function checkForm(doc,fxryId){
		var railType=doc.getElementsByName("railType");
		var railValue=1;
		for( var i in railType){
			if(railType[i].checked){
				railValue=railType[i].value;
			}
		}
		var lp=doc.getElementsByName("lp");
		var lpValue=1;
		for( var i in lp){
			if(lp[i].checked){
				lpValue=lp[i].value;
			}
		}
		if(railValue==2){
			var j=0;
			var areas=doc.getElementsByName("area");
			for(var i in areas){
				if(areas[i].checked){
					j++;
				}
			}
			if(j==0){
				focError($(doc.getElementById("17")),"请至少选择一个行政区划");
				return false;
			}
		}else if(railValue==3){
			var dzwl=GetDzwl(fxryId);
			if(dzwl=="null"){
				focError($(doc.getElementById("dtbh01")),"地图标绘服务有误!");
				return false;
			}else if(/0(0|1)/.test(dzwl)){
				focError($(doc.getElementById("dtbh01")),"请标绘禁止离开区域!");
				return false;
			}
		}
		var tbody = $(doc.getElementById("userLine"));
		var rowIndex = tbody.children().length;
		if(lpValue==1){
			$(doc.getElementById("fxryEdit")).find(".deleteImg").each(function() {
				var tr = $(this).parent().parent();
				tr.remove();
			});
		}else if(rowIndex!=0){
			$(doc.getElementById("periods")).attr("value", rowIndex);
			var i = 0;
			$(doc.getElementById("fxryEdit")).find(".startTime").each(function() {
				$(this).attr("id", "startTime" + i);
				$(this).attr("name", "startTime" + i);
				i++;
			});
			i = 0;
			$(doc.getElementById("fxryEdit")).find(".endTime").each(function() {
				$(this).attr("id", "endTime" + i);
				$(this).attr("name", "endTime" + i);
				i++;
			});
			i = 0;
			$(doc.getElementById("fxryEdit")).find(".space").each(function() {
				$(this).attr("id", "space" + i);
				$(this).attr("name", "space" + i);
				i++;
			});
		}else{
			focError($(doc.getElementById("dw")),"请设定采集间隔");
			return false;
		}
		if (!$.fields.validateForm($(doc.getElementById("fxryEdit")))){
			return false;
		}
		return true;
	}
	function openEdit(id) {
		var wid = arguments[0] || ("edit" + new Date().getTime());
		$.dialog({
			id : wid,
			width : '800px',
			height : '600px',
			title : '社区服刑人员设置',
			content : 'url:${ctx}/data/xtsz/fxryEdit.action?id=' + id,
			ok : function() {
				var doc=$("iframe[name='" + wid + "']").get(0).contentDocument;
				if(!checkForm(doc,id)){
					return false;
				}
				$(doc.getElementById("fxryEdit")).ajaxSubmit();
				$("#mainGrid").trigger("reloadGrid");
			},
			okVal : '保存',
			cancelVal : '取消',
			cancel : true
		});
	}
	function openView(id) {
		var id = arguments[0] || ("view" + new Date().getTime());
		$.dialog({
			id : id,
			width : '800px',
			height : '600px',
			title : '社区服刑人员设置查看',
			content : 'url:${ctx}/data/xtsz/fxryView.action?id=' + id,
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
			<span>您当前的位置：电子监管 -> 配置管理 -> 社区服刑人员设置</span>
		</div>
		<div id="searchDiv" class="container-top">
			<table class="search-table">
				<tbody>
					<tr>
						<th class="search-form-label">姓名：</th>
						<td><input type="text" name="name" searchType="cn"
							maxlength="20" class="search-form-field" /></td>
						<th class="search-form-label">矫正类别：</th>
						<td id="jzlb"></td>
						<th class="search-form-label">监管民警：</th>
						<td><input type="text" name="pname" searchType="cn"
							maxlength="20" class="search-form-field" /></td>
						<td><input type="button" id="searchBtn"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
						<th>电子监管状态：</th>
						<td id="jgzt"></td>
						<th>电子围栏：</th>
						<td id="dzwl" colspan="3"></td>
						<td><input type="button" id="resetBtn"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
