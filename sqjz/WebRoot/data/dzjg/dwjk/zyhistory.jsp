<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>历史轨迹</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/js/cmap/cmap.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/common3.css" />
<link rel="stylesheet" href="${ctx}/css/color.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<link href="${ctx}/css/jquery-ui.css" rel="stylesheet"/>
<style rel="stylesheet">
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}
.liW{
	width:80px !important;
}
#kd{
	width:760px;
	clear:both;
	padding:0px;
	marging:0px;
	position:realative;
	marging-top:40px;!important;
	background-color: #F4F3EF；
}
#kdContainer{
	float:bottom;
	width: 760px;
	height: 50px; 
 	z-index:1999;
 	display:'' ;
 	position:absolute;
 	margin-left:10%;
 	margin-top:-40px;
 	background-color: #F4F3EF；!important;
 
}
#kd div{
	padding:0px;
	margin:0px;
	margin-top:25px;
	float:left;
	width:30px;
	text-align:left;
	display:inline;
	background-color: #F4F3EF;
	font-weight: bold
}
input{
	vertical-align: middle;
}
</style>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/common2.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>

<script type="text/javascript">
	CONTEXT_PATH = '<%=contextPath%>';
	
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	$(function(){
		mapApp=document.getElementById("ezmap");
		var sd = new Date();
		$("input[name='min']").attr("value",sd.getHours()-2);
		$("input[name='max']").attr("value",sd.getHours());
		$("#bjsj2").attr("value",formatDate(sd));
		$("#bjsj1").attr("value",formatDate(new Date(sd.getTime()-1000*60*60*24)));
		showTimeChoose();
		searchData();
		initTimeChoose(sd.getFullYear(),sd.getMonth()+1,sd.getDate());
		var parms=getParameters(location.href);
		if(parms["x"]&&parms["x"]!="undefined"){
			ezmap.SetCenter(parms["x"],parms["y"],parms["level"]);	
		}
	});
	function formatDate(date){
		var str = date.getFullYear()+"-"+compare10(date.getMonth()+1)+"-"+compare10(date.getDate())
		+ " " +compare10(date.getHours())+":"+compare10(date.getMinutes());
		return str;
	}
	function compare10(num){
		return num<10?("0"+num):num;
	}
	
	function reset(){
		$("#bjsj1").attr("value","") ;
		$("#bjsj2").attr("value","") ;
	}
	function searchData(){
		var params = 'fxryid=<%=request.getParameter("fxryid")%>';
		if($("#bjsj1").val()!="" ||$("#bjsj2").val()!=""){
			if($("#bjsj1").val()=="" ||$("#bjsj2").val()==""){
				alert("开始时间和结束时间必须同时填写！");
				return ;
			}else{
				/*var sd = new Date($("#bjsj1").val());
				var ed = new Date($("#bjsj2").val());
				var iDays = parseInt(Math.abs(sd-ed)/1000/60/60/24);
				if(iDays>7){
					var api = frameElement.api, W = api.opener;
					W.$.dialog.alert("查询时间间隔必须小于7天！");
					$("#bjsj2").focus();
					return ;
				}*/
				params += "&startTime="+$("#bjsj1").val()+"&endTime="+$("#bjsj2").val();
			}
		}
		searchAction(params);
		
	}
    var markers=[];
    function ClearMap()
    {
    	if(ezmap.polyline){
    		 ezmap.mapApp.removeOverlay(ezmap.polyline);
    	}
    	if(markers.length>0){
  		  for(var i=0;i<markers.length;i++){
  			  ezmap.mapApp.removeOverlay(markers[i]);
  		  }
  		 }
    }
	function DrawLSGJPoint(points,times)
	{  
		 var strpoints=ezmap.convertpoints(points);
		 var pointArr = strpoints.split(",");
		 var cPoints=[];
		 for(var i=0;i<pointArr.length;i+=2){
			 var point=pointArr[i]+" "+pointArr[i+1];
			 cPoints.push(point);
		 }
		 var locate;
		 var lonLat;
		 var timeArr = times.split(",");
		 var param ;
		 var timeParam;
		 var mapAlarm = {};
		 var timeMap ={};
		 var tF,tS;
		 if(cPoints.length>0){
			 var tt =timeArr[0].split(";");
			tF=tS = (tt[0].slice(0,-2));
		 }else{
			 tF=tS = null; 
		 }
		 for(var i=0;i<cPoints.length;i++){
			 try{
			 param = timeArr[i].split(";");
			 //统计报警类型
			 if(!mapAlarm.hasOwnProperty(cPoints[i])){
				 if(param[1]!="null"){
					 var tempMap = {};
					 tempMap[param[1]] = 1;
					 mapAlarm[cPoints[i]] = tempMap;
				 }else{
					 mapAlarm[cPoints[i]] = {};
				 }
			 }else{
				 if(param[1]!="null"){
					 var tempMap = mapAlarm[cPoints[i]];
					 if(tempMap.hasOwnProperty(param[1])){
						 tempMap[param[1]] = tempMap[param[1]]+1;
					 }else{
						 tempMap[param[1]] = 1;
					 }
				 }
			 }
			 if(i==0){
			 	timeParam = param;
				 continue;
			 }
			 //统计时间段
			 if(cPoints[i]==cPoints[i-1]){
				 tS=(param[0].slice(0,-2));
			 }else{
				 var t_tempmap = timeMap[cPoints[i-1]];
				 if(t_tempmap==null){
					 t_tempmap={};
				 }
				 //只有一次定位情况
				 if(tF==tS){
					 for(var j=0;j<7;j++){
						 if(t_tempmap[j]!=null)
							 continue;
						 tF=tS=timeParam[0].slice(0,-2);
						 t_tempmap[j]=tF;
						 timeMap[cPoints[i-1]] =t_tempmap;
						 break;
					 }
				 }
				 //多次定位情况
				 else{
					 for(var j=0;j<7;j++){
						 if(t_tempmap[j]!=null)
							 continue;
						 t_tempmap[j]=tS+"至"+tF;
						 timeMap[cPoints[i-1]] =t_tempmap;
						 break;
					 }
					 tF=tS;
				 }
			 }
			 timeParam = param;
		 }
		 catch(e)
		 {
				
		  }
		 }
		 if(cPoints.length>1){
			 var t_tempmap = timeMap[cPoints[cPoints.length-1]];
			 if(t_tempmap==null){
				 t_tempmap={};
			 }
			 if(tF==tS){
				 for(var j=0;j<7;j++){
					 if(t_tempmap[j]!=null)
						 continue;
					 t_tempmap[j]=timeParam[0].slice(0,-2);
					 timeMap[cPoints[cPoints.length-1]] =t_tempmap;
					 break;
				 }
			 }
			 //多次定位情况
			 else{
				 for(var j=0;j<7;j++){
					 if(t_tempmap[j]!=null)
						 continue;
					 t_tempmap[j]=tS+"至"+tF;
					 timeMap[cPoints[cPoints.length-1]] =t_tempmap;
					 break;
				 }
				 tF=tS;
			 }
		 }
		 var drawmap = {};
		 for(var i=0;i<cPoints.length;i++){
			 try{
			 if(drawmap.hasOwnProperty(cPoints[i])){
				 continue;
			 }else{
				 drawmap[cPoints[i]]={};
			 }
			 var alarmFlag = false;
			 var innerHTML = "";
			 if(mapAlarm.hasOwnProperty(cPoints[i])){
				 param = timeArr[i].split(";");
				 var popupContentHTML = "";
				 var map = timeMap[cPoints[i]];
				 for(var j=0;j<7;j++){
					if(map[j]!=null){
						popupContentHTML+=(map[j]+"<br/>");
					}else{
						break;
					}
				 }
				 var tempMap = mapAlarm[cPoints[i]];
				 for(var j=0;j<7;j++){
					 if(tempMap.hasOwnProperty(j)){
						 if(!alarmFlag){
							 popupContentHTML +="报警情况：<br/>";
						 }
						 alarmFlag = true;
						 popupContentHTML+=("&nbsp;&nbsp;"+$.dictionary.formatter('BJLX')(j)+":"+tempMap[j]+"次；<br/>");
					 }
				 }
				 if(alarmFlag==true){
					 innerHTML+="<div class='point_r'></div>";
				 }else{
					 innerHTML+="<div class='point_g'></div>";
				 }
				 locate = cPoints[i].split(" ");
				 var marker=ezmap.addMarker(locate[0],locate[1],innerHTML,popupContentHTML,11,11,-4,16);
				 markers.push(marker);
			 }
			 }
			 catch(e)
			 {
				 alert(i);
			 }
		 }
		
	}
	function searchAction(params){
		$.ajax({
			url:'${ctx}/data/dwjk/history.action',
			data:params,
			async:false,
			success:function(data){
				ClearMap();			
				if(data.length>3){
					if($("input[name='line']").attr("checked")=="checked"){
						var popupContentHTMLS = data[6].replace("T"," ")+ " "+(data[7]==null?"":data[7]);
						var popupContentHTMLE = data[8].replace("T"," ")+ " "+(data[9]==null?"":data[9]);
						var CS="<div align='center' ><ul><li class='span-bd1' style='float:none'>终</li></ul></div>";
						var markerS = ezmap.addMarkerBylayer(data[1],data[2],CS,popupContentHTMLS);
						var CE="<div align='center' ><ul><li class='span-bd1' style='float:none'>起</li></ul></div>";
						var markerE = ezmap.addMarkerBylayer(data[3],data[4],CE,popupContentHTMLE);				
						ezmap.DrawLSGJ(data[0]);//轨迹
						markers.push(markerE);
						markers.push(markerS);
					}
					if($("input[name='point']").attr("checked")=="checked"){
						DrawLSGJPoint(data[0],data[5]);
					}
				}
			}
		});
	}
	function initTimeChoose(year,month,day){
		var str = "";
		for(var i=1;i<13;i++){
			str="<option value='"+i+"'";
			if(i==month){
				str+="selected='true'";
			}
			str+=">"+i+"</option>";
			$("#month").append(str);
		}
			
		var maxDay = getDay(year,month);
		for(var i=1;i<maxDay;i++){
			str="<option value='"+i+"'";
			if(i==day){
				str+="selected='true'";
			}
			str+=">"+i+"</option>";
			$("#day").append(str);
		}
	}
	function getDay(year,month){
		var temp = new Date(year,month,0);
		return temp.getDate()+1;
	}
	function changeDay(){
		var day = $("#day").val();
		$("#day option").remove();
		var maxDay = getDay(new Date().getFullYear(),$("#month").val());
		for(var i=1;i<maxDay;i++){
			str="<option value='"+i+"'";
			if(i==day){
				str+="selected='selected'";
			}
			str+=">"+i+"</option>";
			$("#day").append(str);
		}
	}
	function showTimeChoose(){
		$("#slider").slider('values',0,$("input[name='min']").attr("value")*30);
		$("#slider").slider('values',1,$("input[name='max']").attr("value")*30);
		if($("input:checked[name='time']").val()=="1"){
			$("#kdContainer").css("display","");
			$.each($(".cc #bjsj2"),function(i,n){
				$(n).attr("disabled","true");
				$(n).css("display","none");
			});
			$(".cc #bjsj1").css("display","none");
			$(".cc #text").css("display","none");
			$(".cc #dateChoose").css("display","");
		}else{
			$.each($(".cc #bjsj2"),function(i,n){
				$(n).removeAttr("disabled");
				$(n).css("display","");
			});
			$(".cc #bjsj1").css("display","");
			$(".cc #text").css("display","");
			$(".cc #dateChoose").css("display","none");
			
			$("#kdContainer").css("display","none");
		}
	}
	function showPointChoose(){
		ClearMap();
		if($("input:checked[name='time']").val()=="1"){
			sliderSearch();
		}else{
			searchData();
		}
	}
	function showLineChoose(){
		ClearMap();
		if($("input:checked[name='time']").val()=="1"){
			sliderSearch();
		}else{
			searchData();
		}
	}
	function drawGJ(){
		if($("input:checked[name='time']").val()=="1"){
			sliderSearch();
		}else{
			searchData();
		}
	}
</script>
<script language="javascript" src="${ctx}/js/custom/dictionary.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/my97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-ui.js"></script>
</head>
<body>
	<input type="hidden" value="${history}" id="history"/>
	<div style="text-align: center;background-color: #F4F3EF">
		<table align="center" id="searchDiv">
	     	 <tbody>
	     	 		<tr>
	     	 			<td>
	     	 			按天查询<input type="radio"  name="time" value="1" onclick="showTimeChoose()"/>
	     	 			按时间段查询<input type="radio"  name="time" checked="checked" value="2" onclick="showTimeChoose()"/></td>
	     	 			<th style="text-align: right;vertical-align:middle;" >查询时间：</th>
	     	 			<td class="cc" width="480px" align="left" >
	     	 				<input id="dateChoose" name="dateChoose" type="text" 
										class="Wdate inputStyle" style="width: 150px;" 
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false})" />
							<input id="bjsj1" name="startTime" type="text" searchType="gt"
										class="Wdate inputStyle" style="width: 150px;" 
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
								<span id="text" >至</span>
								<input id="bjsj2" name="endTime" type="text" searchType="lt"
										class="Wdate inputStyle" style="width: 150px;" 
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />	
	     	 			轨迹点<input type="checkbox" checked="checked" name="point" onclick="showPointChoose()"/>
	     	 			轨迹线<input type="checkbox"  name="line" onclick="showLineChoose()"/></td>
		     	 		<td style="display:none"><input type="text" name="min" style="width:16px;height:10px" disabled="disabled"/>时 至
		     	 			<input type="text" name="max" style="width:16px;height:10px" disabled="disabled"/>时
		     	 		</td>
			          	<td class="cc" style="text-align: right;vertical-align:top;padding-left: 40px" >
				          	<input type="button" onclick="drawGJ()" class="bttn bicon-search" id="searchBtn" value="查询"/>
			     	 		<input type="button" onclick="reset()" class="bttn bicon-reset" id="resetBtn" value="重置"/>
		     	 		</td>
	     	 	  </tr>
	      </tbody>
    	</table>
	</div>
	<div id="map"
		style=" width: 997px; height: 500px;border: 1px solid #ccc;">
		<div class="rightFixed" id="divRight" style="margin-top:40px">
	    <a href="javascript:void(0);" onclick="changeMap(1);" ><img src="<%=contextPath %>/images/map_change.png"/></a>
	    </div>
		<iframe id="ezmap" src="${ctx}/js/ezmap/ezmap.jsp" width="100%" height="100%"></iframe>
	</div>
		
		<div id="kdContainer" >
	<div  id="kd" > 
	  <div  id="k0" style="margin-left:-2px">0</div>
	  <div  id="k1">1</div>
	  <div  id="k2">2</div>
	  <div  id="k3">3</div>
	  <div id="k4">4</div>
	  <div id="k5" >5</div>
	  <div id="k6">6</div>
	  <div id="k7" >7</div>
	  <div id="k8" >8</div>
	  <div id="k9" >9</div>
	  <div id="k10" >10</div>
	  <div id="k11" >11</div>
	  <div id="k12" >12</div>
	  <div id="k13" >13</div>
	  <div id="k14" >14</div>
	  <div id="k15" >15</div>
	  <div id="k16" >16</div>
	  <div id="k17" >17</div>
	  <div id="k18" >18</div>
	  <div id="k19" >19</div>
	  <div id="k20" >20</div>
	  <div id="k21" >21</div>
	  <div id="k22" >22</div>
	  <div id="k23" >23</div>
	  <div id="k24" >24</div>
	</div>

	<div id="slider" style="width:720px;margin:0px;padding:0px"></div>
	</div>
</body>
</html>

<script>
$( "#slider" ).slider({
	range: true,
	values: [ 300, 600 ],
	max:720,
	step:30,
	//orientation:'vertical',
	stop: function(e,ui){
		var min = $(this).slider('values',0)/30;
		var max = $(this).slider('values',1)/30;
		$("input[name='min']").attr("value",min);
		$("input[name='max']").attr("value",max);
		//查询数据
		var params = 'fxryid=<%=request.getParameter("fxryid")%>';
		var sd = new Date(new Date().getFullYear(),$("#month").val()-1,$("#day").val());
		sd.setHours($("input[name='min']").attr("value"), 0, 0, 0);
		var ed = new Date(new Date().getFullYear(),$("#month").val()-1,$("#day").val());
		ed.setHours($("input[name='max']").attr("value"), 0, 0, 0);
		params += "&startTime="+formatDate(sd)+"&endTime="+formatDate(ed);
		searchAction(params);
	},
	slide:function(e,ui){
		var min = $(this).slider('values',0)/30;
		var max = $(this).slider('values',1)/30;
		$("input[name='min']").attr("value",min);
		$("input[name='max']").attr("value",max);
	}
});
</script>


