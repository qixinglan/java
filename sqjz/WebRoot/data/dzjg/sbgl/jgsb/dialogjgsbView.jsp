<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${ctx}/data/dzjg/sbgl/css/style.css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监管设备管理</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#buyUnit").text($.organization.formatter()($("#buyUnit").text() ));
		$("#status").text($.dictionary.formatter('DZJGSBZT')($("#status").text()));
		$.each($(".operateUnit,.useUnit,.useSfsUnit"),function(i,v){
			var span=$(v);
			span.text($.organization.formatter()(span.text()));
		});
	});
		/* //表格初始化
		$("#mainGrid").jqGrid(
			{
				rowNum : 10,
				url : '${ctx}/data/sbgl/jgsb/searchHistory.action?deviceNumber=${entity.deviceNumber }',
				colNames : [ '操作名称','操作时间','操作人',' 操作人单位','sim卡号','使用单位','佩戴人'],
	
				colModel : [
						{
							name : 'operateName',
							index : 'operateName',
							
						},
						{
							name : 'operateDate',
							index : 'operateDatee',
							formatter : "date",
							formatoptions : {
								newformat : 'Y-m-d'
							}
						},
						{
							name : 'operatePersionName',
							index : 'operatePersionName',
							
							
						},
						{
							name : 'operateUnit',
							index : 'operateUnit',
							formatter : function(value, opts, rwdat) {
								return $.organization.formatter()(value);
							}
							
						},
						{
							name : 'simNumber',
							index : 'simNumber',
							
						},
						{
							name : 'useUnit',
							index : 'useUnit',
						},
						{
							name : 'wearName',
							index : 'wearName',
						}],
				multiselect : false,
				sortname : 'operateDate',
				sortorder : "desc",
				//rownumbers : true,
				pager : '#mainGridPager',
			});
	}); */
</script>
</head>
<body>
	<table class="comm-table">
		<tbody>
			<tr>
				<%-- <th class="red w180">定位主机设备编号：</th>
				<td style="width: 200px;">${devicePair.machine.deviceNumber }</td> --%>
				<th class="red w180">设备编号：</th>
				<td>${entity.deviceNumber}</td>
				<th class="red w180">sim卡号：</th>
				<td id="connectPhone">${entity.phoneNumber}</td>
			</tr>
			<tr>
				<th>购买单位：</th>
				<td id="buyUnit"><s:property value="entity.buyUnit" /></td>
				<th>购买日期：</th>
				<td><s:date name="entity.buyDate" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<%-- <th class="red w180">主机联系电话：</th>
				<td id="connectPhone">${devicePair.machine.connectPhone }</td> --%>
				<th class="red w180">设备状态：</th>
				<td id="status">${entity.status}</td>
			</tr>
		<%-- 	<tr>
				<th class="red w180">配对编号：</th>
				<td id="pairDeviceNumber">${devicePair.pairDeviceNumber }</td>
			</tr> --%>
			
			
		</tbody>
	</table>
		
    <h3 class="main-h3"><span class="list"></span>历史记录  </h3>
    <div class="list-t">
        <div class="list-tbzoom">
			<div class="timeline" style="margin-left: 100px">
				<ul id="ulle">
					<li class="person"></li>
				<c:forEach items="${deviceRecords}" varStatus="i" var="obj">
				   <li class="go-to" style="width:200px;text-align:left; ">
				           操&nbsp;&nbsp;作&nbsp;&nbsp;人:${obj.operatePersionName}<br/>
			   		操作人单位:<span class='operateUnit'>${obj.operateUnit}</span><br/>
			   		sim&nbsp;&nbsp;卡&nbsp;号:${obj.simNumber}<br/>
	   				局使用单位:<span class='useUnit'>${obj.useUnit}</span><br/>
	   				所使用单位:<span class='useSfsUnit'>${obj.useSfsUnit}</span><br/>
	   				佩&nbsp;&nbsp;戴&nbsp;&nbsp;人:${obj.wearPersionName}<br/>
				   </li>
				   <li style="width:150px " name="tName" class="dital d<c:out value="${i.count +1}" />">
				   	<!-- 操作&nbsp;&nbsp;名称: -->${obj.operateName}<br/>
			   		<fmt:formatDate value="${obj.operateDate}" pattern="yyyy-MM-dd"/>
				   	</li>
				   <c:if test="${(i.count%3)==0 }"><br/><br/></c:if>
				</c:forEach>
				</ul>
			</div>
		 </div>
		
	<div class="container-bottom">
		<table id="mainGrid">
		</table>
		<div id="mainGridPager"></div>
	</div>
</body>
</html>
