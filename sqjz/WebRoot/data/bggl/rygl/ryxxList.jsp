<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script src="${ctx}/js/jquery/jquery.jstree.js" type="text/javascript"></script>
<script type="text/javascript">
	var supOrgId = '${user.wunit.bm}';
	var supOrgName = '${user.wunit.mc}';
	var gzdwID = "";
	$(function() {
		$.menu.init("#menuDetail", {
			first : "bggl",
			second : "people"
		});
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx }/data/rygl/perSearch.action?orgId='
									+ supOrgId,
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
										name : 'org.orgId',
										index : 'org.orgId',
										label : '工作单位',
										width : 80,
										sortable : true,
										formatter : 'organization',
										align : 'left',
									},
									{
										name : 'name',
										index : 'name',
										searchType : 'cn',
										label : '姓名',
										width : 40,
										sortable : true,
										align : 'center'
									},
									{
										name : 'oldorg',
										index : 'oldorg',
										searchType : 'cn',
										label : '原工作单位',
										width : 80,
										sortable : true,
										align : 'left'
									},
									{
										name : 'sex',
										index : 'sex',
										label : '性别',
										searchType : 'eq',
										width : 20,
										sortable : true,
										formatter : 'dictionary',
										formatoptions : {
											code : 'XB'
										},
										align : 'center'
									},
									{
										name : 'birthday',
										index : 'birthday',
										searchType : 'in',
										searchInput : 'date',
										label : '出生日期',
										width : 40,
										sortable : true,
										align : 'center'
									},

									{
										name : 'polityvisage',
										index : 'polityvisage',
										searchType : 'eq',
										label : '政治面貌',
										width : 40,
										sortable : true,
										formatter : 'dictionary',
										formatoptions : {
											code : 'ZZMM'
										},
										align : 'center',
									},
									{
										name : 'degree',
										index : 'degree',
										searchType : 'eq',
										width : 30,
										label : '学历',
										sortable : true,
										formatter : 'dictionary',
										formatoptions : {
											code : 'WHCD'
										},
										align : 'center'
									},
									{
										name : 'persontype',
										index : 'persontype',
										width : 80,
										searchType : 'eq',
										sortable : true,
										label : '人员类别',
										formatter : 'dictionary',
										formatoptions : {
											code : 'RYLB'
										},
										align : 'left'
									},
									{
										name : 'duty',
										index : 'duty',
										width : 40,
										searchType : 'eq',
										sortable : true,
										label : '职务职级',
										formatter : 'dictionary',
										formatoptions : {
											code : 'ZWZJ'
										},
										align : 'left'
									},
									{
										name : 'phone',
										index : 'phone',
										width : 50,
										sortable : true,
										label : '手机',
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 40,
										align : 'center',
										sortable : false,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:personView(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>查看</a>";
											actions += "<a href='javascript:openCreate(\"edit\",\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>编辑</a>";
											return actions;
										}
									} ],
							sortNames : 'org.cname,name,oldorg,sex,birthday,polityvisage,degree,duty,persontype,phone',
							rownumbers : true,
							sortname : 'org.orgId,name',
							sortorder : "asc,asc",
							pager : '#mainGridPager',
							multiselect : true,
						});
		//$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		$("#mainGrid").addSearchForm("#searchDiv", {
			rows : "3"
		});
		var mc = $("#mc").val();
		var bm = $("#bm").val();
		var jgRoot = [ {
			data : mc,
			state : "closed",
			attr : {
				id : bm,
				dm : postalCode,
				nType : "root",
				name : mc,
			}
		} ];
		var cm;
		if (bm == "1") {
			cm="FFFFFFFF";
			jgRoot.push({
				data : "区县司法局",
				state : "closed",
				attr : {
					id : "FFFFFFFF",
					dm : "FFFFFFFF",
					nType : "root",
					name : "区县司法局"
				}
			});
		}
		var postalCode = $("#postalCode").val();
		$("#jgtree").jstree({
			"json_data" : {
				data : jgRoot,
				"ajax" : {
					"url" : "${ctx}/data/jggl/jgtree.action",
					"cache" : false,
					"data" : function(n) {
						var parentId = n.attr ? n.attr("id") : rootId;
						return {
							parentId : parentId
						};
					}
				}
			},
			"themes" : {
				"theme" : "apple",
				"dots" : false,
				"icons" : false
			},
			"plugins" : [ "themes", "json_data", "ui" ]
		}).bind(
				'select_node.jstree',
				function(e, node) {
					//清空之前的查询条件
					$.each($('input'), function(i, n) {
						var item = $(n);
						if (item.attr('name') && item.attr('searchType')
								&& 'true' != item.attr('noreset')) {
							item.val('').trigger('valuechange');
						}
					});
					$("#mainGrid").getGridParam('postData').filters = '';
					//查询选中树节点的机构信息
					var selectId = node.inst.get_selected().attr('id');
					gzdwID = selectId;
					if ("FFFFFFFF" == selectId) {
						$("#btnAdd").attr("disabled", true);
					} else {
						$("#btnAdd").removeAttr("disabled");
					}
					var urll = '${ctx}/data/rygl/perSearch.action?orgId='
							+ selectId;
					$("#searchOrg").val(selectId);
					$("#mainGrid").setGridParam({
						'url' : urll
					}).trigger("reloadGrid", [ {
						page : 1
					} ]);
					//重置列表查询url
					/*$("#mainGrid").setGridParam({
						'url' : '${ctx}/data/jggl/search.action'
					});*/
					supOrgId = selectId;
					supOrgName = node.inst.get_selected().attr('name');
				}).bind('loaded.jstree', function(e, node) {
			node.inst.open_node($("#jgtree").find('li[id=' + bm + ']'));
			node.inst.open_node($("#jgtree").find('li[id=' + cm + ']'));
		}).bind('refresh.jstree', function(e, node) {
			//jstree刷新后展开节点，避免无子节点时添加机关后父节点不展开
			node.inst.open_node($("#jgtree").find('li[id=' + supOrgId + ']'));
		});
	});

	//刷新jstree	
	function refresh(id) {
		$jstree = $.jstree._focused();
		$jstree.refresh("#" + id);
	}

	//新建、编辑
	function openCreate(oper, id) {
		if (oper == "add") {
			if (gzdwID == "") {
				id = supOrgId;
			} else {
				id = gzdwID;
			}
			var read = '${ctx}/data/jggl/perAdd.action?orgId=' + id;
			var title = '机构人员新增';
		}

		if (oper == "edit") {
			read = '${ctx}/data/jggl/perView.action?id=' + id;
			title = '机构人员编辑';
		}
		var orgType = "${orgType}";
		var filter = "";
		if (orgType == "1") {
			filter = "6,7,8,9,10,11";
		} else if (orgType == "2") {
			filter = "6,7,9,10,11";
		} else if (orgType == "3") {
			filter = "1,2,3,4,5,6,7,8";
		} else if (orgType == "10") {
			filter = "1,2,3,4,5,8,9,10,11";
		}
		$.container.popup({
			title : title,
			read : read,
			save : '${ctx}/data/jggl/perSave.action',
			fieldCls : {
				labelCls : 'form-td-label-120'
			},
			fieldCols : 2,
			fields : [ {
				text : "orgId",
				type : 'hidden',
				name : 'org.orgId',
			}, {
				text : "orgType",
				type : 'hidden',
				name : 'orgType',
				value : '${orgType}'
			}, {
				text : "id",
				type : 'hidden',
				name : 'id',
			}, {
				text : "姓名",
				type : 'text',
				name : 'name',
				maxlength : 20,
				validater : 'name',
				required : true,
				allowBlank : false
			}, {
				text : "性别",
				type : 'dict_radio',
				name : 'sex',
				code : 'XB',
				defValue : '1',
				required : true
			},

			{
				text : "出生日期",
				type : 'datepicker',
				name : 'birthday',
				required : true
			}, {
				text : "政治面貌",
				type : 'dict_combobox',
				name : 'polityvisage',
				code : 'ZZMM',
				emptyText : '请选择',
				defValue : '12',
				required : true
			},

			{
				text : "学历",
				type : 'dict_combobox',
				name : 'degree',
				code : 'WHCD',
				emptyText : '请选择',
				defValue : '7',
				required : true
			}, {},

			{
				text : "现工作单位及部门",
				type : 'display',
				name : 'org.cname'
			}, {
				text : "原工作单位",
				type : 'text',
				name : 'oldorg',
				maxlength : 80,
				validater : 'name',
				required : true,
				allowBlank : false
			},

			{
				text : "人员类别",
				type : 'dict_combobox',
				name : 'persontype',
				code : 'RYLB',
				emptyText : '请选择',
				defValue : '1',
				required : true
			}, {
				text : "职务职级",
				type : 'dict_combobox',
				name : 'duty',
				code : 'ZWZJ',
				emptyText : '请选择',
				defValue : '1',
				filter : filter,
				required : true
			},

			{
				text : "办公电话",
				type : 'text',
				name : 'workPhone',
				maxlength : 11,
				validater : 'phone',
				required : true,
				allowBlank : false
			}, {
				text : "手机",
				type : 'text',
				name : 'phone',
				maxlength : 11,
				validater : 'telephone',
				required : true,
				allowBlank : false
			},

			{
				text : "备注",
				type : 'textarea',
				name : 'description',
				colspan : 2,
				required : false,
				allowBlank : false
			} ],
			yes : function(data) {
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		}, {
			width : "800px",
			height : "400px",
			okVal : '保存'
		});
	}
	//查看
	function personView(id) {
		$.container.popup({
			title : '机构人员查看',
			read : '${ctx}/data/jggl/perView.action?id=' + id,
			fieldCls : {
				labelCls : 'form-td-label-120'
			},
			fieldCols : 2,
			fields : [ {
				text : "姓名",
				type : 'display',
				name : 'name',
				maxlength : 20,
				validater : 'name'
			}, {
				text : "性别",
				type : 'display',
				name : 'sex',
				formatter : $.dictionary.formatter('XB', '不详')
			},

			{
				text : "出生日期",
				type : 'display',
				name : 'birthday'
			}, {
				text : "政治面貌",
				type : 'display',
				name : 'polityvisage',
				formatter : $.dictionary.formatter('ZZMM', '不详')
			},

			{
				text : "学历",
				type : 'display',
				name : 'degree',
				formatter : $.dictionary.formatter('WHCD', '不详')
			}, {},

			{
				text : "现工作单位及部门",
				type : 'display',
				name : 'org.cname'
			}, {
				text : "原工作单位",
				type : 'display',
				name : 'oldorg',
				maxlength : 80,
				validater : 'name'
			},

			{
				text : "人员类别",
				type : 'display',
				name : 'persontype',
				formatter : $.dictionary.formatter('RYLB', '不详')
			}, {
				text : "职务职级",
				type : 'display',
				name : 'duty',
				formatter : $.dictionary.formatter('ZWZJ', '不详')
			},

			{
				text : "办公电话",
				type : 'display',
				name : 'workPhone',
				maxlength : 11,
				validater : 'phone'
			}, {
				text : "手机",
				type : 'display',
				name : 'phone',
				maxlength : 11,
				validater : 'telephone'
			},

			{
				text : "备注",
				type : 'textarea',
				name : 'description',
				colspan : 2,
				readonly : true
			} ],
			yes : function(data) {
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		}, {
			width : "800px",
			height : "400px",
			title : '机构人员查看',
			cancelVal : '关闭'
		});
	}
	//删除
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
				url : "${ctx}/data/rygl/perDel.action",
				data : "perId=" + str
			});
			$("#mainGrid").trigger("reloadGrid");
		});
	}
	function download(url) {
		var colNames = $("#mainGrid").getGridParam("colNames");
		var sortNames = $("#mainGrid").getGridParam("sortNames");
		var filters = $("#mainGrid").getGridParam("postData").filters;
		var OrgId = "";
		if (gzdwID == "") {
			OrgId = supOrgId;
		} else {
			OrgId = gzdwID;
		}
		url = "${ctx}/data/jggl/excel.action?colNames=" + colNames
				+ "&sortNames=" + sortNames + "&filters=" + filters + "&OrgId="
				+ OrgId;
		if ($("#downloadcsv").length <= 0)
			$("body")
					.append(
							"<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
		$("#downloadcsv").attr("src", url);
	}
</script>
</head>
<body onload="">
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<input type="hidden" id="bm" value="${orgId}" />
	<input type="hidden" id="mc" value="${orgName}" />
	<input type="hidden" id="postalCode" value="${postalCode}" />
	<form method="post" id="personForm">
		<input type="hidden" id="searchOrg" name="searchOrg" />
	</form>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 人员管理</span>
		</div>
		<table style="width: 100%;">
			<tbody>
				<tr>
					<td style="padding-top:13px;" valign="top">
						<table>
							<tr>
								<td>
									<div id="jgtree" style="width:200px;"></div>
								</td>
							</tr>
						</table>
					</td>
					<td style="border: 2px solid #d8d6d6;" valign="top">
						<div id="searchDiv" class="container-top">
							<table class="search-table">
								<input type="hidden" id="orgId" name="orgId" , searchType="cn" />
								<tbody>

								</tbody>
							</table>
						</div> <!--列表-->
						<div class="buttonArea operation">
							<input type="button" id="btnAdd" class="bttn bicon-add"
								value="新增"
								onclick="javascript:openCreate('add','${entity.orgId}');" /> <input
								type="button" id="btnDel" class="bttn bicon-delete" value="删除"
								onclick="javascript:openDel();" /> <input type="button"
								id="btnExcel" class="bttn bicon-excel" onclick="download('');"
								value="导出" />
						</div>
						<div class="container-bottom">
							<table id="mainGrid" style="padding-right:100px;">
							</table>
							<div id="mainGridPager"></div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>