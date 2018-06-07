<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/admin/user/role.js" type="text/javascript"></script>
<script type="text/javascript">
	$.roleDictionary.load("${ctx}/sys/user/roleJson.action");
</script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"xtgl",second:"user"});
		$.organization.combobox("#orgId", "searchOrg", null, {
			allowBlank : true,
			level : 10,
			useCOrg : true,
			showRoot : true,
			showItself : true,
			dropdownAutoWidth:false,
			emptyText : '全部'
		});
		$("#searchDiv").find('input[name="searchOrg"]').attr("searchType",
				"nci");
		$("#searchDiv").find('input[name="searchOrg"]').attr("class","search-form-field");
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/sys/user/search.action',
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
										name : 'wunit.mc',
										index : 'wunit.mc',
										label : '所属机构',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'name',
										index : 'name',
										label : '姓名',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'userName',
										index : 'userName',
										label : '用户名',
										width : 100,
										sortable : true,
										align : 'center'
									},
									{
										name : 'isvalid',
										index : 'isvalid',
										label : '是否有效',
										width : 40,
										formatter : 'dictionary',
										formatoptions : {
											code : 'SF'
										},
										sortable : true,
										align : 'center'
									},
									{
										name : 'roles',
										index : 'roles',
										label : '用户类型',
										width : 40,
										formatter : function(value, opts, rwdat) {
											var actions="";
											for ( var i in value) {
												if(i==0){
													actions = value[i].name;
												}else{
													actions = actions+","+value[i].name;
												}
											}
											return actions;
										},
										sortable : true,
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 80,
										sortable : false,
										align : 'center',
										formatter : function(value, opts, rwdat) {
											//alert(rwdat.isws);\""+ rwdat.id+ "\",\""+ rwdat.status+ "\"
											var actions = "<a href='javascript:openView(\""
													+ rwdat.id+"\"\,\""+rwdat.isws
													+ "\");' title='' class='a-style'>查看</a>";
											actions += "<a href='javascript:openEdit(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>编辑</a>";
											return actions;
										}
									} ],
							multiselect : false,
							rownumbers : true,
							sortname : 'wunit.id',
							sortorder : "asc",
							pager : '#mainGridPager'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	function openAdd() {
		var id = "user" + new Date().getTime();
		$.dialog({
			id : id,
			width : '500px',
			height : '250px',
			fixed : true,
			lock : true,
			cover : true,
			title : '系统用户新增',
			content : 'url:${ctx}/sys/user/initAdd.action',
			cancelVal : '关闭',
			okVal : '保存',
			ok : function() {
				var ajax_option = {
						success:function(success){
							$("#mainGrid").trigger("reloadGrid");
						}
				};
				$(
						$("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("userAdd")).ajaxSubmit(ajax_option);
			},
			cancel : true
		});
	}
	function openEdit(userId) {
		//var id = "editUser" + new Date().getTime();
		/*$.dialog({
			id : id,
			width : '500px',
			height : '250px',
			fixed : true,
			lock : true,
			cover : true,
			title : '系统用户编辑',
			content : 'url:${ctx}/sys/user/view.action?userId=' + userId,
			okVal : '保存',
			ok : function() {
				var ajax_option = {
						success:function(success){
							$("#mainGrid").trigger("reloadGrid");
						}
				};
				$(
						$("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("userUpdate")).ajaxSubmit(ajax_option);
			},
			cancelVal : '关闭',
			cancel : true
		});*/
		var fields=[
					{type:'hidden', name: 'id'},
					{text: "所属机构", type:"display",  name: "wunit.mc", required: true},
					{text: "姓名", type:"display",  name: "name", required: true},
					{text: "用户名", type:"display",  name: "userName", required: true},
					{text: "是否有效", type:"dict_radio",name: "isvalid", code: "SF", required: true},
					{text: "用户类型", type:"role_combobox",  name: "roleNames", code: "ROLE",required: true},
					
				];
		if(${user.isws}=='3'){
			var tgqx={text: "特管人员权限", type:"dict_radio",name:"isws", code: "SF", required:true,defValue:"2"};
			fields.push(tgqx);
		}
		$.container.popup({
			type: "row",
			items:[{
				title: "用户信息编辑 ",
				read: "${ctx}/sys/user/view.action?userId=" + userId,
				save: "${ctx}/sys/user/update.action",
				fieldCols: 1,
				fieldCls:{
					labelCls : "form-td-label-90"
				},
				fields:fields,
					yes: function(){
						$("#mainGrid").trigger("reloadGrid");
					}
			}]
		}, {
			width: "500px",
			height: "250px"
		});
	}
	function openView(userId,isws) {
		var fields=[
				    {text: "所属机构", type:"display",  name: "wunit.mc", required: false},
				    {text: "姓名", type:"display",  name: "name", required: false},
				    {text: "用户名", type:"display",  name: "userName", required: false},
				    {text: "是否有效", type:"display",  name: "isvalid", required: false,formatter:$.dictionary.formatter("SF", "否")},
				    {text: "用户类型", type:"display",  name: "roleNames", required: false},
				];
		if(${user.isws}=='3'){
			var tgqx= {text: "特管人员权限", type:"display",  name: "isws", required: false,formatter:function(){
				if(isws=='1'||isws=='2'){
					return $.dictionary.formatter("SF", "否")(isws);
				}
				else{
					return '是';
				}
			}
			}
			fields.push(tgqx);
		}
		$.container.popup({
			type: "row",
			items:[{
				read: "${ctx}/sys/user/show.action?userId=" + userId,
				save:"",
				fieldCols: 1,
				fieldCls:{
					labelCls : "form-td-label-90"
				},
				fields:fields
			}]
		}, {
			title: "用户信息查看",
			cancelVal : '关闭',
			width: "500px",
			height: "250px"
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
				url : "${ctx}/sys/user/del.action",
				data : "userId=" + str,
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
			<span>您当前的位置：系统管理 -> 用户管理</span>
		</div>
		<div id="searchDiv" class="container-top">
			<table class="search-table">
				<tbody>
					<tr>
						<th class="search-form-label">姓名</th>
						<td><input type="text" name="name" searchType="cn" class="search-form-field"/></td>
						<th class="search-form-label">用户名</th>
						<td><input type="text" name="userName" searchType="cn" class="search-form-field"/></td>
						<td><input type="button" id="searchBtn"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
						<th class="search-form-label">所属机构</th>
						<td id="orgId" colspan="3"></td>
						<td><input type="button" id="resetBtn"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<!-- <input type="button" id="btnAdd" class="bttn bicon-add" value="新增"
				onclick="javascript:openAdd();" /> <input type="button" id="btnDel"
				class="bttn bicon-reset" value="删除" onclick="javascript:openDel();" />
			-->
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
