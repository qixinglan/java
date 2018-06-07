<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%-- <%@taglib url='http://java.sun.com/jstl/core' prefix='c'%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${ctx}/data/dzjg/sbgl/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子监管设备管理</title>
<%@ include file="/common/commonjs.jsp"%>
<%-- <script language="javascript" src="${ctx}/js/custom/superviseDevice.js" type="text/javascript">
	$.equip.load("${ctx}/data/dzjg/jgsb/equipableMachine.action");
</script> --%>
<style>
 .searchStyle{
        	width:140px !important;
        	vertical-align: top !important;
        }
</style>
<script type="text/javascript">
$(function() {
	$.menu.init("#menuDetail",{first:"dzjg",second:"sbgl",third : "dzjgsb",});
	$("#buyUnit").text($.organization.formatter()($("#buyUnit").text() ));
	$("#useUnit").text($.organization.formatter()($("#useUnit").text() ));
	$("#status").text($.dictionary.formatter('DZJGSBZT')($("#status").text()));
	$.each($(".operateUnit,.useUnit,.useSfsUnit"),function(i,v){
		var span=$(v);
		span.text($.organization.formatter()(span.text()));
	});
	$.each($(".operateName"),function(i,v){
		var span=$(v);
		span.text($.dictionary.formatter('SBCZNAME')(span.text()));
	});
	 //佩戴人员统计表格
	$("#mainGrid").jqGrid(
		{
			rowNum : 10,   
			url : '${ctx}/data/sbgl/jgsb/pdrytj.action?deviceNumber=${entity.deviceNumber }',
			colNames : [ '佩戴人','佩戴时间','解除时间'],

			colModel : [
					{
						name:'fxryName',
						index:'fxryName',
						align : 'center',
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
	});
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管&nbsp;-&gt;&nbsp;设备管理&nbsp;-&gt;&nbsp;电子监管设备信息管理</span>
		</div>
	<table class="comm-table" style="width:100%">
		<tbody>
			<tr>
				<%-- <th class="red w180">定位主机设备编号：</th>
				<td style="width: 200px;">${devicePair.machine.deviceNumber }</td> --%>
				<th class="red w180">设备编号：</th>
				<td>${entity.deviceNumber}</td>
				<th >SIM卡号：</th>
				<td id="connectPhone">${entity.phoneNumber}</td>
			</tr>
			<tr>
				<th>购买单位：</th>
				<td id="buyUnit"><s:property value="entity.buyUnit" /></td>
				<th>购买日期：</th>
				<td><s:date name="entity.buyDate" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>使用单位：</th>
				<td id="useUnit"><s:property value="entity.useUnit" /></td>
				<%-- <th class="red w180">主机联系电话：</th>
				<td id="connectPhone">${devicePair.machine.connectPhone }</td> --%>
				<th >设备状态：</th>
				<td id="status">${entity.status}</td>
			</tr>
		<%-- 	<tr>
				<th class="red w180">配对编号：</th>
				<td id="pairDeviceNumber">${devicePair.pairDeviceNumber }</td>
			</tr> --%>
			
			
		</tbody>
	</table>
		
    <h3 class="main-h3"><span class="list"></span>生命周期详情 </h3>
    <div class="list-t" id="listt">
        <div class="list-tbzoom">
			<div class="timeline" style="margin-left: 100px">
				<ul id="ulle">
					<li class="person"></li>
				<c:forEach items="${deviceRecords}" varStatus="i" var="obj">
				   <li class="go-to" style="width:200px;text-align:left; ">
				           操&nbsp;&nbsp;作&nbsp;&nbsp;人:${obj.operatePersionName}<br/>
			   		操作人单位:<span class='operateUnit'>${obj.operateUnit}</span><br/>
			   		SIM&nbsp;&nbsp;卡&nbsp;号:${obj.simNumber}<br/>
	   				<%-- 局使用单位:<span class='useUnit'>${obj.useUnit}</span><br/> --%>
	   				所使用单位:<span class='useSfsUnit'>${obj.useSfsUnit}</span><br/>
	   				佩&nbsp;&nbsp;戴&nbsp;&nbsp;人:${obj.wearPersionName}<br/>
	   				   <br/>
				   </li>
				   <li style="width:150px " name="tName" class="dital d<c:out value="${i.count +1}" />">
				   	<!-- 操作&nbsp;&nbsp;名称: --><span class="operateName">${obj.operateName}</span><br/>
			   		<fmt:formatDate value="${obj.operateDate}" pattern="yyyy-MM-dd"/>
				   	</li>
				   <c:if test="${(i.count%4)==0 }"><br/><br/></c:if>
				</c:forEach>
				</ul>
			</div>
		 </div>
		</div>
		<h3 class="main-h3"><span class="list"></span>佩戴人员统计</h3>
		<div class="list-t" >
		<div style="background:url('${ctx}/images/index/shuj_bg.jpg') top repeat-x; height:31px; width:100%;"><div style="float:left; height:20px; font-size:14px;
					color:#46474C; line-height:20px; margin-top:8px; width:80%; min-width:300px;">历史佩戴人员总数:${totalFxryNum}</div></div>
	<div class="container-bottom" id="container">
		<table id="mainGrid">
		</table>
		<div id="mainGridPager"></div>
		</div>
	</div>
	
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<iframe id="downloadFrame" style="display:none"/>
</body>
</html>
