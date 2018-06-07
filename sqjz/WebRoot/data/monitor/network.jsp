<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>系统网络状态</title>
	<%@ include file="/common/commonjs.jsp"%>
	
	<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
	<script language="javascript" src="tabpage.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"xtgl",second:"monitor"});
			$.monitortabpage.init('network');
		});
	</script>
</head>
 
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：系统监控 -> 系统网络状态</span> </div>
  <div class="container-top">
  	<%@include file="tab.jsp" %>
  	<script type="text/javascript">
		$("#tab-network").addClass("tabpage-label-selected");
	</script>
  </div>
  <div class="tabpage">
	  <div class="container-middle">
	  	<table class="search-table" id="searchDiv">
      <tbody>
         <tr>
          <th class="search-form-label">监控时间</th>
          <td>
          		<input id="jksj1" name="createTime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 160px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,maxDate:'#F{$dp.$D(\'jksj2\')}'})" />
							至 <input id="jksj2" name="createTime" type="text"
							class="Wdate inputStyle" style="width: 160px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,minDate:'#F{$dp.$D(\'jksj1\')}'})" />
          </td>
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
      </tbody>
    </table>
	  </div>
	  <div class="container-bottom">
	  	<div id="buttons" class="buttonArea operation">
	  	</div>
	    <table id="mainGrid">
	    </table>
	    <div id="mainGridPager"></div>
	  </div>
  </div>
</div>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp" %>
</body>
</html>
