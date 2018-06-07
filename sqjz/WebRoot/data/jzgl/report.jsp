<%@page import="com.nci.dcs.common.runqian.RemoteReportUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>报表查询</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function(){
		 reset();
	});
	function addMonth(m){
		return m<10?"0"+m:m;
	}
	function search(){
		var raq = $("#raq").val();
		var st = $("#st").val();
		var et = $("#et").val();
		var src = "<%=reportUrl%>data/jzgl/showReport.jsp?raq="+raq+"&orgId=${user.wunit.bm }&orgType=${user.wunit.orgType}";
		
		if(raq=="statistic_count"){
			if(st!=""&&et!=""){
				var yearArryS =st.split("-");
				var yearArryE =et.split("-");
				src += "&year="+yearArryS[0]+"&month1="+yearArryS[1]+"&month2="+yearArryE[1]
					+ "&st="+st+"&et="+et;
				if(yearArryS[0]!=yearArryE[0]){
					$.dialog.alert("只可查询同一年度数据，请重新选择！");
					return false;
				}
			}else{
				$.dialog.alert("请重新选择查询时间！");
					return false;
			}
		}
		$("#report").attr("src",src);
	}
	function reset(){
		var d = new Date();
		var t = d.getFullYear()+"-"+addMonth(d.getMonth()+1);
		$("#st").attr("value",t);
		$("#et").attr("value",t);
	}
	function searchCondition(){
		var con = $("#raq").val();
		if(con=="statistic_count"){
			$("#searchCondition").css("display","");
		}else{
			$("#searchCondition").css("display","none");
		}
	}
</script>
  </head>
  
  <body>
   <%@ include file="/data/top.jsp" %>
    <div class="main">
       <div class="breadcrumb-nav"><span>您当前的位置: 社区矫正 ->查询统计  ->数据分析 </span></div>
       <div class="container-top" style="clear:both;padding-left:100px">
       		<div id="search" >
       			<table>
       				<tr>
       					<td valign="middle">分析类型：</td>
       					<td>
	       					<select id="raq" onchange="searchCondition()">
	       					<option value="sex">社区服刑人员性别比例分析</option>
	       					<option value="crimeType">社区服刑人员犯罪类型比例分析</option>
	       					<option value="workState">社区服刑人员就业就学比例分析</option>
	       					<option value="education">社区服刑人员文化比例分析</option>
	       					<option value="age">社区服刑人员年龄比例分析</option>
	       					<option value="natrueHome">社区服刑人员户籍比例分析</option>
	       					<option value="personType">社区服刑人员类别比例分析</option>
	       					<option value="statistic_count">社区服刑人员数量趋势图</option> 
	       					</select>
	       				</td>
	       				<td>
	       				<div id="searchCondition" style="display:none">
	       				<input id="st" name="startTime" type="text" 
							class="Wdate inputStyle" style="width: 85px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM',enableInputMask:false,maxDate:'#F{$dp.$D(\'et\')}'})"/>
	       				 至 <input id="et" name="endTime" type="text"
							class="Wdate inputStyle" style="width: 85px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM',enableInputMask:false,minDate:'#F{$dp.$D(\'st\')}'})" />
						</div>
						</td>
	       				<td valign="top"><input type="button" class="bttn bicon-search" value="查询" onclick="search()"/></td>
	       				<td valign="top"><input type="reset" class="bttn bicon-search" value="重置" onclick="reset()"/></td>
       				</tr>
       			</table>
       		</div>
       </div>
     	 <div style="width:1200px;height:600px">
     		 <iframe frameborder="0" scrolling="no" id="report" src='<%=reportUrl%>data/jzgl/showReport.jsp?raq=sex&orgId=${user.wunit.bm }'></iframe>
     	 </div>
   </div>
   <%@include file="/data/bottom.jsp"%>
  </body>
</html>
