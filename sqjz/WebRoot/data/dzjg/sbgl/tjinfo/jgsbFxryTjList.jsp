<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>督察项详细信息</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/jzgl/cxtj/dacx.js"></script>
<script language="javascript" src="${ctx}/data/zfdc/js/supervision.list.js"></script>
<script type="text/javascript">
	$(function() {
		<%
		String startTime = request.getParameter("startTime");
		String overTime = request.getParameter("overTime");
		String useUnit= request.getParameter("useUnit");
		String useSfsUnit= request.getParameter("useSfsUnit");
		String indexNumber= request.getParameter("indexNumber");
		%>
		$("#mynav0").removeClass("navnew-c");
		$("#dzjg").addClass("navnew-c");
		var emptyText="全部";
		$("#mainGrid").jqGrid(
				{
					rowNum : 10,   
					url : '${ctx}/data/sbgl/jgsb/fxrytj.action?useUnit=<%=useUnit%>&startTime=<%=startTime%>&overTime=<%=overTime%>&indexNumber=<%=indexNumber%>&useSfsUnit=<%=useSfsUnit%>',
					colNames : [ '佩戴人','设备编号','矫正负责单位','佩戴时间','解除时间'],

					colModel : [
							{
								name:'fxryName',
								index:'fxryName',
								 searchType: 'cn',
								align : 'center',
								formatter : function(value, opts, rwdat) {
									return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.fxryId+"\");' title='' class='a-style'>"+value+"</a>";
								}
							},
							{
								name : 'deviceNumber',
								index : 'deviceNumber',
								align : 'center',
								searchType: 'cn',
								formatter : function(value, opts, rwdat) {
									return "<a href='${ctx}/data/sbgl/jgsb/view.action?deviceNumber="+
									rwdat.deviceNumber+
									"' title='' target='_blank' class='a-style'>"+value+"</a>";
								}
							},
							{
								name : 'useSfsUnit',
								index : 'useSfsUnit',
								sortable: true,
								formatter: 'organization',
								align : 'center'
							},
							{
								name : 'wearTime',
								index : 'wearTime',
								align : 'center',
								formatter : "date",
								formatoptions : {
									newformat : 'Y-m-d'
								}
							},
							{
								name : 'releaseTime',
								index : 'releaseTime',
								align : 'center',
								formatter : "date",
								formatoptions : {
									newformat : 'Y-m-d'
								}
							}
							],
					multiselect : false,
					sortname : 'wearTime',
					sortorder : "asc",
					//rownumbers : true,
					pager : '#mainGridPager',
				});
		$("#mainGrid").addSearchForm("#search");
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 佩戴人员统计 
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