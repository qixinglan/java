<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色授权</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		var roleId = $("#roleId").val();
		//执行机关
		$.organization.combobox("#executeUnit", "wunit.id", ${user.wunit.bm}, {
	    	allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType:"eq","class":"search-form-field"}});
		//表格初始化
		$("#userGrid")
				.jqGrid(
						{
							url : '${ctx}/sys/user/searchForRole.action?roleId='
									+ roleId,
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
										width : 90,
										sortable : true,
										align : 'center'
									},
									{
										name : 'userName',
										index : 'userName',
										label : '用户名',
										width : 90,
										sortable : true,
										align : 'center'
									},
									{
										name : 'isvalid',
										index : 'isvalid',
										label : '是否有效',
										width : 60,
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
										label : '是否授权',
										width : 60,
										formatter : function(value, opts, rwdat) {
											var actions = "否";
											for ( var i in value) {
												if (roleId == value[i].id) {
													actions = "是";
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
										width : 60,
										align : 'center',
										sortable : false,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:authoriz(\""
													+ roleId
													+ "\",\""
													+ rwdat.id
													+ "\",\"1\");' title='' class='a-style'>授权</a>";
											var roles=rwdat.roles;
											for ( var i in roles) {
												if (roleId == roles[i].id) {
													actions = "<a href='javascript:authoriz(\""
														+ roleId
														+ "\",\""
														+ rwdat.id
														+ "\",\"2\");' title='' class='a-style'>取消授权</a>";
												}
											}
											return actions;
										}
									} ],
							multiselect : false,
							rownumbers : true,
							sortname : 'wunit.id',
							sortorder : "asc",
							pager : '#userGridPager'
						});
		$("#userGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	function authoriz(roleId, userId,type) {
		$.ajax({
			type : "POST",
			async : true,
			url : "${ctx}/sys/role/upusers.action",
			data : "type="+type+"&roleId=" + roleId + "&userId=" + userId,
			success:function(data){
				$("#userGrid").trigger("reloadGrid");
			}
		});
	}
</script>
</head>
<body>
	<div class="container-top" id="searchDiv">
			<table class="search-table" >
				<tbody>
					<tr>
						<th class="search-form-label">所属机构：</th>
						<td id="executeUnit"></td>
						<td>
							<input type="button" id="searchBtn" class="bttn bicon-search"  value="查询" />
						</td>
					</tr><tr>
						<th class="search-form-label">姓名：</th>
						<td>
							<input type="text" id="name" name="name" class="search-form-field" searchType="cn"  />
						</td>
						<td>
							<input type="button" id="resetBtn" class="bttn bicon-reset" value="重置" />
						</td>
					</tr>
				</tbody>
			</table>
	</div>
	<input type="hidden" id="roleId" value="${roleId}" />
	<table id="userGrid">
	</table>
	<div id="userGridPager"></div>
</body>
</html>
