<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"xtgl",second:"role"});
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/sys/role/search.action',
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
										name : 'name',
										index : 'name',
										label : '角色名',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'info',
										index : 'info',
										label : '角色说明',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 80,
										align : 'center',
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:openChose(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>选择用户</a>";
											actions += "<a href='javascript:openView(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>查看</a>";
											actions += "<a href='javascript:openEdit(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>编辑</a>";
											return actions;
										}
									} ],
							multiselect : true,
							rownumbers : true,
							sortname : 'id',
							sortorder : "desc",
							pager : '#mainGridPager'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	var api = window.top;
	function openAdd() {
		var id = "role" + new Date().getTime();
		$.dialog({
			id : id,
			width : '500px',
			height : '200px',
			fixed : true,
			lock : true,
			cover : true,
			parent : api,
			title : '新增角色',
			content : 'url:${ctx}/sys/role/initAdd.action',
			okVal : '保存',
			ok : function() {
				var ajax_option = {
						success:function(success){
							$("#mainGrid").trigger("reloadGrid");
						}
				}
				$(
						$("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("roleAdd")).ajaxSubmit(ajax_option);
			},
			cancelVal : '取消',
			cancel : true
		});
	}
	function openView(roleId) {
		var id = "viewRole" + new Date().getTime();
		$.dialog({
			id : id,
			width : '500px',
			height : '200px',
			fixed : true,
			lock : true,
			cover : true,
			title : '角色信息查看',
			content : 'url:${ctx}/sys/role/show.action?roleId=' + roleId,
			cancelVal : '关闭',
			cancel : true
		});
	}
	function openEdit(roleId) {
		var id = "editRole" + new Date().getTime();
		$.dialog({
			id : id,
			width : '500px',
			height : '200px',
			fixed : true,
			lock : true,
			cover : true,
			title : '角色信息编辑',
			content : 'url:${ctx}/sys/role/view.action?roleId=' + roleId,
			okVal : '保存',
			ok : function() {
				var ajax_option = {
						success:function(success){
							$("#mainGrid").trigger("reloadGrid");
						}
				}
				$(
						$("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("roleEdit")).ajaxSubmit(ajax_option);
			},
			cancelVal : '取消',
			cancel : true
		});
	}
	function openChose(roleId) {
		var id = "roleUser" + new Date().getTime();
		$.dialog({
			id : id,
			width : '800px',
			height : '450px',
			fixed : true,
			lock : true,
			cover : true,
			title : '角色授权',
			content : 'url:${ctx}/sys/role/choice.action?roleId=' + roleId,
			cancelVal : '关闭',
			cancel : true
		});
	}
	function openDel() {
		var grid = $("#mainGrid");
		var ids = grid.getGridParam("selarrrow");
		var str = '';
		for ( var id in ids) {
			str += ids[id] + ',';
		}
		str = str.substring(0, str.length - 1);
		if (ids == null || ids.length < 1) {
			$.dialog.alert("请选择需要删除的数据！");
			return false;
		}
		$.dialog.confirm("确认要删除记录吗？", function() {
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx}/sys/role/del.action",
				data : "roleId=" + str,
				success:function(data){
					$("#mainGrid").trigger("reloadGrid");
				}
			});
		});
	}
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：系统管理 -> 角色管理</span>
		</div>
		<div id="searchDiv" class="container-top"></div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" id="btnAdd" class="bttn bicon-add" value="新增"
				onclick="javascript:openAdd();" /><input type="button" id="btnDel"
				class="bttn bicon-reset" value="删除" onclick="javascript:openDel();" />
		</div>
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