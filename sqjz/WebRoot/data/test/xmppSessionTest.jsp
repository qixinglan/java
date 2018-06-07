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
			url : '${ctx}/data/test/xmpp/sessionSearch.action',
			mtype : "POST",
			datatype : 'json',
			colModel : [ {
				name : 'id',
				index : 'id',
				label : 'SessionID',
				width : 50,
				align : 'center'
			}, {
				name : 'keyCount',
				index : 'keyCount',
				label : '连接数',
				width : 50,
				align : 'center'
			},{
				name : 'users',
				index : 'users',
				label : '登录者',
				width : 150,
				align : 'center'
			},{
				name : 'content',
				index : 'content',
				label : '描述',
				width : 300,
				align : 'center'
			} ,{
				name : 'connected',
				index : 'connected',
				label : '是否在线',
				width : 50,
				align : 'center'
			},{
				name : 'closed',
				index : 'closed',
				label : '是否关闭',
				width : 50,
				align : 'center'
			}],
			multiselect : false,
			rownumbers : true,
			pager : '#mainGridPager'
		});
	});
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
			<span>您当前的位置：XMPP测试 -> session管理</span>
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