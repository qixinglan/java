<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>矫正机构管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script src="${ctx}/js/jquery/jquery.jstree.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$.dictionary.combobox("#jgzt", "orgStatus", "JGZT", {allowBlank: true, emptyText: '全部',defValue:''});
	$("#jgzt").find('input[name="orgStatus"]').attr("searchType","eq");
	
	//表格初始化
	$("#mainGrid").jqGrid({
		url : '${ctx}/data/jggl/search.action',
		mtype : "POST",
		datatype : 'json',
		colModel: [
		{
			name	:'orgId',
			index	:'orgId',
			label	: 'orgId',
			key:true,
			hidedlg	:false,
			hidden	:true
		},{
			name	: 'cname',
			index	: 'cname',
			label	: '机构名称',
			width	: 100,
			sortable:true,
			align	: 'center'
		},{
			name	: 'address',
			index	: 'address',
			label	: '地址',
			width	: 100,
			sortable:true,
			align	: 'center'
		}, {
			name	: 'contact',
			index	: 'contact',
			label	: '联系人',
			width	: 100,
			sortable:true,
			align	: 'center'
		}, {
			name	: 'phone',
			index	: 'phone',
			label	: '固定电话',
			width	: 100,
			 maxlength:11,
			sortable:true,
			align	: 'center',
		}, {
			name	: 'fax',
			index	: 'fax',
			width	: 100,
			label	: '传真',
			sortable:true,
			align	: 'center'
		},{
			name	: 'orgStatus',
			index	: 'orgStatus',
			width	: 70,
			label	: '机构状态',
			sortable:true,
			formatter : 'dictionary',
			formatoptions: {code:'JGZT'},
			align	: 'center'
		},{
			name	: 'state', 
			index	: 'state',
			label	: '操作',
			width	: 80,
			align	: 'center',
			formatter : function(value, opts, rwdat) {
				var actions = "<a href='javascript:openView(\""+rwdat.orgId+"\");' title='' class='a-style'>查看</a>";
				actions += "<a href='javascript:openEdit(\""+rwdat.orgId+"\");' title='' class='a-style'>编辑</a>";
				return actions;	
			}
		}
		],
		multiselect : true,
		rownumbers	: true,
		sortname: 'orgType,orgId',
		sortorder: "asc,asc",
		pager: '#mainGridPager'
	});
	$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	$("#jgtree").jstree({
		"json_data" : {
			data : [ {
				data : "北京市司法局",
				state : "closed",
				attr : {
					id : "1",
					dm : "0001",
					nType : "root",
					name : "北京市司法局"
				}
			} ],
			"ajax" : {
				"url" : "${ctx}/data/jggl/jgtree.action",
				"cache" : true,
				"data" : function(n) {
					var parentId = n.attr ? n.attr("id") : rootId;
					return {
						parentId : parentId
					};
				}
			}
		},
		"themes" : {
			"theme" : "classic",
			"dots" : false,
			"icons" : false
		},
		"plugins" : [ "themes", "json_data", "ui" ]
	});
});
</script>
</head>

<body onload="">
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 矫正机构管理</span>
		</div>
		<div id="jgtree" style="width: 20%;float:left;">
		</div>
		<div style="width: 80%;float:right;">
		<div id="searchDiv" class="container-top">
			<table class="search-table">
				<tbody>
					<tr>
						<th>地址</th>
						<td><input type="text" name="address" searchType="cn" /></td>
						<th>联系人</th>
						<td><input type="text" name="contact" searchType="cn" /></td>
						<td><input type="button" id="searchBtn"
							class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
						<th>机构状态</th>
						<td id="jgzt" colspan="3"></td>
						<td><input type="button" id="resetBtn"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" id="btnAdd" class="bttn bicon-add" value="新增"
				onclick="javascript:openAdd();" /> <input type="button" id="btnDel"
				class="bttn bicon-reset" value="删除" onclick="javascript:openDel();" />

			<input type="button" id="btnExport" class="bttn bicon-report"
				value="人员实力查询" onclick="javascript:openPerson();" />
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
		</div>
	</div>
	<div style="clear:both;"></div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
