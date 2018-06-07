<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>矫正机构管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script src="${ctx}/js/jquery/jquery.jstree.js" type="text/javascript"></script>
<script type="text/javascript">
	var supOrgId = '${user.wunit.bm}';
	var supOrgName = '${user.wunit.mc}';
	$(function() {
		$.menu.init("#menuDetail",{first:"bggl",second:"org"});
		/*$.dictionary.combobox("#jgzt", "orgStatus", "JGZT", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			fieldClass:"search-form-field"
		});
		$("#jgzt").find('input[name="orgStatus"]').attr("searchType", "eq");*/

		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/jggl/search.action',
							mtype : "POST",
							datatype : 'json',
							colModel : [
									{
										name : 'postalCode',
										index : 'postalCode',
										label : 'postalCode',
										key : true,
										hidedlg : false,
										hidden : true
									},{
										name : 'orgId',
										index : 'orgId',
										label : 'orgId',
										key : true,
										hidedlg : false,
										hidden : true
									},
									{
										name : 'cname',
										index : 'cname',
										label : '机构名称',
										width : 50,
										sortable : true,
										align : 'left'
									},
									{
										name : 'address',
										index : 'address',
										label : '地址',
										width : 100,
										sortable : true,
										align : 'left'
									},
									{
										name : 'contact',
										index : 'contact',
										label : '联系人',
										width : 30,
										sortable : true,
										align : 'center'
									},
									{
										name : 'phone',
										index : 'phone',
										label : '固定电话',
										width : 30,
										 maxlength:11,
										sortable : true,
										align : 'center'
									},
									{
										name : 'fax',
										index : 'fax',
										width : 50,
										label : '传真',
										sortable : true,
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 25,
										align : 'center',
										sortable : false,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:openView(\""
													+ rwdat.orgId
													+ "\");' title='' class='a-style'>查看</a>";
											actions += "<a href='javascript:openEdit(\""
													+ rwdat.orgId
													+ "\");' title='' class='a-style'>编辑</a>";
											return actions;
										}
									} ],
							multiselect : false,
							rownumbers : true,
							sortname : 'orgType,postalCode,orgId',
							sortorder : "asc,asc,asc",
							pager : '#mainGridPager'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		var mc = $("#mc").val();
		var bm = $("#bm").val();
		var jgRoot=[ {
			data : mc,
			state : "closed",
			attr : {
				id : bm,
				dm : postalCode,
				nType : "root",
				name : mc
			}
		} ];
		var cm;
		if(bm=="1"){
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
		}).bind('select_node.jstree', function(e, node) {
			//清空之前的查询条件
			$.each($('input'), function(i, n){
				var item = $(n);
				if (item.attr('name') && item.attr('searchType') && 'true' != item.attr('noreset')){
					item.val('').trigger('valuechange');
				}
			});
			$("#mainGrid").getGridParam('postData').filters = '';
			//查询选中树节点的机构信息
			var selectId = node.inst.get_selected().attr('id');
			var urll = '${ctx}/data/jggl/search.action?orgId=' + selectId;
			$("#searchOrg").val(selectId);
			$("#mainGrid").setGridParam({
				'url' : urll
			}).trigger("reloadGrid",[{page:1}]);
			//重置列表查询url
			/*$("#mainGrid").setGridParam({
				'url' : '${ctx}/data/jggl/search.action'
			});*/
			supOrgId = selectId;
			supOrgName = node.inst.get_selected().attr('name');
			//控制新增按钮
			var orgType = node.inst.get_selected().attr('dm');
			if(orgType>3){
				$("#btnAdd").attr("disabled","disabled");
			}else{
				var btnAdd = document.getElementById("btnAdd");
				if(btnAdd){
					btnAdd.removeAttribute("disabled");
				}
			}
		}).bind('loaded.jstree',function(e,node){
			node.inst.open_node($("#jgtree").find('li[id='+bm+']'));
			node.inst.open_node($("#jgtree").find('li[id='+cm+']'));
		}).bind('refresh.jstree',function(e,node){
			//jstree刷新后展开节点，避免无子节点时添加机关后父节点不展开
			node.inst.open_node($("#jgtree").find('li[id='+supOrgId+']'));
		});
	});
	function openAdd() {
		var id = arguments[0] || ("per" + new Date().getTime());
		$
				.dialog({
					id : id,
					width : '960px',
					height : '300px',
					fixed : true,
					lock : true,
					cover : true,
					title : '新增矫正机构',
					content : 'url:${ctx}/data/jggl/add.action?supOrgName='+supOrgName+'&supOrgId='+supOrgId,
					oklVal : '保存',
					ok : function() {
						var doc = $("iframe[name='" + id + "']").get(0).contentDocument;
						return $("iframe[name='" + id + "']").get(0).contentWindow.$.fields.checkForm(
								$(doc.getElementById("jgAdd")), {
									yes : function(data) {
										refresh(supOrgId);//刷新jstree	
										$("#mainGrid").trigger("reloadGrid");
										reloadOrg();//重载机构数据字典
									},
									mask : true,
									title : '新增机构',
									url : $(doc.getElementById("jgAdd")).attr(
											"action")
								});
					},
					cancelVal : '关闭',
					cancel : true
				});
	}
	//刷新jstree	
	function refresh(id){
		$jstree = $.jstree._focused();
		$jstree.refresh("#"+id);
	}
	//重载机构数据字典
	function reloadOrg(){
		//$.organization.load("${ctx}/data/jggl/reload.action");
	}
	function openView(id) {
		$.container.popup({
			title : '机构信息查看',
			read : '${ctx }/data/jggl/view.action?id=' + id,
			fieldCls : {
				labelCls : 'form-td-label-120'
			},
			fieldCols : 3,
			fields : [ 
			{
				text : "机构名称",
				type : 'display',
				name : 'cname'
			},
			{
				text : "机构类型",
				type : 'display',
				name : 'orgType',
				formatter : $.dictionary.formatter('JGLX', '')
			},
			{
				text : "联系人",
				type : 'display',
				name : 'contact'
			},
			{
				text : "地址",
				type : 'display',
				name : 'address'
			},{
				text : "邮政编码",
				type : 'display',
				name : 'postalCode'
			},{
				text : "固定电话",
				type : 'display',
				name : 'phone'
			},{
				text : "传真",
				type : 'display',
				name : 'fax'
			},{
				text : "上级机构",
				type : 'display',
				name : 'supOrgCname'
			},{},{
				text : "机构设置情况",
				type : 'textarea',
				name : 'description',
				colspan : 3,
				readonly : true
			}
			],
			yes : function(data) {
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		}, {
			width : "700px",
			height : "200px",
			title : '机构信息查看',
			cancelVal : '关闭'
		});
		
	}
	function openEdit(id) {
		window.location = '${ctx }/data/jggl/edit.action?id=' + id;
	}
	function openPerson(id) {
		document.getElementById("personForm").action="${ctx }/data/jggl/perList.action";
		document.getElementById("personForm").submit();
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
			$.dialog.alert("请选择需要删除的机构！");
			return false;
		}
		$.dialog.confirm("确认要删除机构吗？", function() {
			$.ajax({
				type : "POST",
				async : false,
				url : "del.action",
				data : "orgIds=" + str,
				success : function(data) {
					if ((data[0] & "100") == "100") {
						$.dialog.alert("删除机构失败,不能删除非本系统添加的机构!");
					} else if ((data[0] & "010") == "010") {
						$.dialog.alert("删除机构失败,请确保其无下属人员!");
					} else if ((data[0] & "001") == "001") {
						$.dialog.alert("删除机构失败,请确保其无下属机构!");
					} else {
						refresh(id);//刷新jstree	
						$.dialog.alert("删除机构成功！");
						//删除成功后新增按钮enable
						var btnAdd = document.getElementById("btnAdd");
						btnAdd.removeAttribute("disabled");
						supOrgId = $("#bm").val();
						supOrgName = $("#mc").val();
						var urll = '${ctx}/data/jggl/search.action?orgId=' + supOrgId;
						$("#mainGrid").setGridParam({
							'url' : urll
						}).trigger("reloadGrid");
						reloadOrg();//重载机构数据字典
					}
				}
			});
		});
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
		<input type="hidden" id="searchOrg" name="searchOrg"/>
	</form>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 矫正机构管理</span>
		</div>
		<table style="width: 100%;">
		<tbody>
			<tr>
				<td style="padding-top:13px;" valign="top" >
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
								<tr>
									<th>机构名称</th>
									<td><input type="text" name="cname" searchType="cn" id="cname"
										class="search-form-field" /></td>
									<th>地址</th>
									<td><input type="text" name="address" searchType="cn" id="address"
										class="search-form-field" /></td>
									<td nowrap="nowrap"><input type="button" id="searchBtn"
										class="bttn bicon-search" value="查询" /></td>
								</tr>
								<tr>
									<th>联系人</th>
									<td colspan="3"><input type="text" name="contact" searchType="cn" id="contact"
										class="search-form-field" /></td>
									<td nowrap="nowrap"><input type="button" id="resetBtn"
										class="bttn bicon-reset" value="重置" /></td>
								</tr>
							</tbody>
						</table>
					</div> <!--列表-->
					<div class="buttonArea operation">
						<!--<input type="button" id="btnAdd" class="bttn bicon-add" value="新增"
							onclick="javascript:openAdd();" /> <input type="button"
							id="btnDel" class="bttn bicon-reset" value="删除"
							onclick="javascript:openDel();" /> 
							--><input type="button"
							id="btnExport" class="bttn bicon-report" value="人员实力查询"
							onclick="javascript:openPerson();" />
					</div>
					<div class="container-bottom">
						<table id="mainGrid">
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