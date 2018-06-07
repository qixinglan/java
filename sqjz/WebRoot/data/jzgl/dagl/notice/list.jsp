<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>接收提醒</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/jzgl/dagl/tzgl/getData.action',
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
										searchType : 'cn',
										label : '姓名',
										sortable : true,
										width : 100,
										align : 'center'
									},
									{
										name : 'code',
										index : 'code',
										searchType : 'cn',
										label : '人员编号',
										sortable : true,
										width : 130,
										align : 'center'
									},
									{
										name : 'identityCard',
										index : 'identityCard',
										label : '身份证号',
										sortable : true,
										searchType : 'cn',
										width : 150,
										align : 'center'
									},
									{
										name : 'sex',
										index : 'sex',
										label : '性别',
										fixed : true,
										width : 50,
										sortable : true,
										searchType : 'eq',
										align : 'center',
										formatter : 'dictionary',
										formatoptions : {
											code : 'XB'
										}
									},
									{
										name : 'responseOrg',
										index : 'responseOrg',
										width : 130,
										searchType : 'cn',
										sortable : true,
										formatter : 'organization',
										searchOption : {
											useCOrg : true,
											notShowType : '1,4,5,6,7,8,9',
											showRoot : false,
											level : 2,
											allowSearch : true
										},
										label : '矫正负责单位',
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '当前状态',
										fixed : true,
										width : 80,
										sortable : true,
										align : 'center',
										formatter : 'dictionary',
										formatoptions : {
											code : 'JZRYZT'
										}
									},
									{
										name : 'deviceCode',
										index : 'deviceCode',
										label : '是否电子监管',
										fixed : true,
										width : 90,
										sortable : true,
										align : 'center',
										searchType : 'eq',
										searchInput : 'dictionary',
										searchOption : {
											code : 'SF'
										},
										formatter : function(value, opts, rwdat) {
											if (!rwdat.deviceCode) {
												return "否";
											} else {
												return "是";
											}
										}
									},
									{
										name : 'status',
										index : 'status',
										label : '通知状态',
										fixed : true,
										width : 90,
										sortable : true,
										align : 'center',
										formatter : function(value, opts, rwdat) {
											if (value=='2') {
												return "待签收";
											} else {
												return "已签收";
											}
										}
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 80,
										align : 'center',
										fixed : true,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:view(\""+ rwdat.status+ "\",\""+ rwdat.id+ "\",\""+rwdat.fxryId+"\");' class='a-style'>查看</a>";
											return actions;
										}
									} ],
							pager : '#mainGridPager',
							sortname : 'createdate',
							sortorder : "desc",
							multiselect : false
						});
		$("#mainGrid").addSearchForm("#search");
	});
	
	function view(status,id,fxryId) {
		var url = "${ctx}/data/jzgl/dagl/view.jsp?id=" + fxryId+"&returnurl=notice/list";
		if(status=='2'){
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx }/data/jzgl/dagl/tzgl/view.action",
				data : "id=" + id,
				success: function(data){
					window.location = url;
	            }
			});
		}else{
			window.location = url;
		}
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：工作桌面 -> 人员已接收</span>
		</div>
		<div class="container-top">
		</div>
		<div class="container-middle">
			<div id="search"></div>
		</div>
		<!--列表-->
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
