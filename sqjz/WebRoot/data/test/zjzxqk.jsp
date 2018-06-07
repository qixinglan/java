<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线设备</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.dictionary.combobox("#isLogin", "isLogin", "SF", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			fieldClass:"search-form-field",
			attr:{"searchType":"eq"}
		});
		//表格初始化
		$("#mainGrid").jqGrid({
			url : '${ctx}/data//test/xmpp/search.action',
			mtype : "POST",
			datatype : 'json',
			colModel : [ {
				name : 'type',
				index : 'type',
				label : '设备类型',
				formatter : function(value, opts, rwdat) {
					var actions ="主机";
					if(value!='1'){actions="移动终端";}
					return actions;
				},
				width : 100,
				align : 'center'
			}, {
				name : 'deviceNumber',
				index : 'deviceNumber',
				label : '用户名称',
				width : 100,
				align : 'center'
			},{
				name : 'isLogin',
				index : 'isLogin',
				label : '是否在线',
				width : 100,
				formatter : 'dictionary',
				formatoptions : {
					code : 'SF'
				},
				align : 'center'
			}, {
				name : 'loginTime',
				index : 'loginTime',
				label : '最近登录时间',
				width : 100,
				formatter:"time",
				align : 'center'
			} ,{
				name : 'state',
				index : 'state',
				label : '操作',
				width : 80,
				align : 'center',
				formatter : function(value, opts, rwdat) {
					var actions = "<a href='javascript:openView(\""
							+ rwdat.deviceNumber+"\",\""+ rwdat.type
							+ "\");' title='' class='a-style'>查看</a>";
					return actions;
				}
			}  ],
			multiselect : false,
			rownumbers : true,
			sortname : 'isLogin,loginTime',
			sortorder : "asc,desc",
			pager : '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	function openView(id,type) {
		var dataRow = $("#mainGrid").jqGrid("getRowData", id);
		$.dialog({
			id : id,
			title : '矫正机构信息查看',
			content : 'url:${ctx }/data/test/zjxxqk.jsp?id=' + id+'&type='+type,
			cancelVal : '关闭',
			cancel : true,
			data : dataRow
		}).max();
	}
</script>
<style rel="stylesheet">
.comm-table th {
	width: 201px;
}

.comm-table td {
	width: 201px;
}
</style>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 在线设备</span>
		</div>
		<ul class="tabpage-label-container">
			<a href="${ctx}/data/test/xmppTest.jsp"><li id="tab-zjry"
				class="tabpage-label">设备通讯测试</li></a>
			<a href="${ctx}/data/test/zjzxqk.jsp"><li id="tab-zjry"
				class="tabpage-label tabpage-label-selected">在线设备</li></a>
		</ul>
		<div id="searchDiv" class="container-top">
			<table class="search-table">
				<tbody>
					<tr>
						<th>用户名称</th>
						<td><input type="text" name="deviceNumber" searchType="cn"
							class="search-form-field" /></td>
						<th>是否在线</th>
						<td id="isLogin"></td>
						<td><input type="button" id="searchBtn"
							class="bttn bicon-search" value="查询" /> <input type="button"
							id="resetBtn" class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>