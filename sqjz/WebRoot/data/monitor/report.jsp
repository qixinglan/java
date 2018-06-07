<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.HOUR_OF_DAY, -1);
	Date startTime = calendar.getTime();
	Date endTime = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String startDate =sdf.format(startTime);
	String endDate = sdf.format(endTime);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统监控</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="report.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"xtgl",second:"monitor"});
		$.report.init('sysReport',{"startDate":"<%=startDate%>","endDate":"<%=endDate%>"});
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：系统监控</span>
		</div>
		<div class="container-top">
			<%@include file="tab.jsp" %>
		  	<script type="text/javascript">
				$("#tab-report").addClass("tabpage-label-selected");
			</script>
	<form id="searchForm">
	<table class="search-table" id="searchDiv">
      <tbody>
         <tr>
          <th class="search-form-label">监控时间</th>
          <td>
          		<input id="stime" name="createTime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 160px" required='required'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,maxDate:'#F{$dp.$D(\'etime\')}'})" />
							至 <input id="etime" name="createTime" type="text"
							class="Wdate inputStyle" style="width: 160px" searchType="lt" required='required'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,minDate:'#F{$dp.$D(\'stime\')}'})" />
          </td>
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
      </tbody>
    </table>
    </form>
		</div>
		<div class="container-bottom">
			<div id="buttons" class="buttonArea operation"></div>
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
		<div class="chart"></div>
		<div id="chart1"></div>
		<div id="chart2"></div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
