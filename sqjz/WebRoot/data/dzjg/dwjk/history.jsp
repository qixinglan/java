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
<link rel="stylesheet"
	href="${ctx}/js/cmap/openlayer/theme/default/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/cmap/cmap.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/common3.css" />
<link rel="stylesheet" href="${ctx}/css/color.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<link href="${ctx}/css/jquery-ui.css" rel="stylesheet" />
<style rel="stylesheet"><!--
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

.liW {
	width: 80px !important;
}

#kd {
	width: 760px;
	clear: both;
	padding: 0px;
	marging: 0px;
	position: realative;
	marging-top: 40px; ! important;
	background-color: #F4F3EF；
}

#kdContainer {
	float: bottom;
	width: 760px;
	height: 50px;
	z-index: 1999;
	display: '';
	position: absolute;
	margin-left: 10%;
	margin-top: -42px;
}

#kd div {
	padding: 0px;
	margin: 0px;
	margin-top: 25px;
	float: left;
	width: 30px;
	text-align: left;
	display: inline;
	background-color: #F4F3EF;
	font-weight: bold
}

input {
	vertical-align: middle;
}
/*add by yang on 2016/09/01 */
.docker{ height:30px;position:absolute;top:70px;left:20px;z-index:999;width:300px;padding-bottom:0px;margin-bottom:0px;}
.dwjk-box5 li {border:1px solid silver; font-size: 12px;}
.dwjk-box5 li.image{ width:200px; height:102px; vertical-align: bottom; text-align: left;}
.dwjk-box5 li.list{ background:url(../../../images/chaxun_tj_bg.gif) repeat-x; width:200px; height:27px; line-height:27px; text-align:center; color: #666666; margin-left:0px;padding:0px; font-weight:bold; cursor:pointer;}
.dwjk-box5 li.list span{ width:150px;}
.bttn {
	border:0;
	display:inline-block;
	font-size:12px;
	line-height:22px;
	color:#333;
	text-align:center;
	letter-spacing:2px;
	text-shadow:0 1px 1px rgba(255, 255, 255, 0.75);
	vertical-align:middle;
	cursor:pointer;
	margin-right:2px;
	background:url(../../../images/btn1.jpg) repeat-x; 
	padding-left: 4px;
	padding-right: 4px;
}
/*end*/
-->
</style>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/cmap/openlayer/OpenLayers.js"></script>
<script language="javascript" src="${ctx}/js/heatmap/heatmap_openlayer.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>

<script type="text/javascript">
	CONTEXT_PATH = '<%=contextPath%>';
	var mapApp;
	//update by yang on 2016/09/07
	var api = null;
	if(frameElement != null){
		api = frameElement.api;
	}
	//end
	var heat = null;
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	$(function(){
		init();
		$(".docker").mousedown(function(e){
			$(this).css("cursor","move");
			var offset=$(this).offset();
			var x=e.pageX-offset.left;
			var y=e.pageY-offset.top;
			$(document).bind("mouseover",function(ev){
				$(".docker").stop();
				var _x=ev.pageX-x;
				var _y=ev.pageY-y;
				$(".docker").animate({left:_x+"px",top:_y+"px"},10);
			}); 
		});
		$(".docker").mouseup(function(){
			$(this).css("cursor","default");
			$(document).unbind("mouseover");
		});
		var sd = new Date();
		$("input[name='min']").attr("value",sd.getHours()-2);
		$("input[name='max']").attr("value",sd.getHours());
		$("#bjsj2").attr("value",formatDate(sd));
		$("#bjsj1").attr("value",formatDate(new Date(sd.getTime()-1000*60*60*24)));
		$(".cc #dateChoose").attr("value",formatDate2(sd));
		showTimeChoose();
		drawGJ();
		
	});
	function drawHeatMap(data){
		heat = new Heatmap.Layer("Heatmap");
		var pointArr = data.split(",");
		for(var i=0;i<pointArr.length;i++){
			var ll = pointArr[i].split(" ");
			heat.addSource(new Heatmap.Source(new OpenLayers.LonLat(ll[0],ll[1])));
		}
		//heat.addSource(new Heatmap.Source(new OpenLayers.LonLat(117.150, 40.39)));
		
		map.addLayer(heat);
		map.zoomToExtent(heat.getDataExtent());
	}
	function formatDate(date){
		var str = date.getFullYear()+"-"+compare10(date.getMonth()+1)+"-"+compare10(date.getDate())
		+ " " +compare10(date.getHours())+":"+compare10(date.getMinutes());
		return str;
	}
	function formatDate2(date){
		var str = date.getFullYear()+"-"+compare10(date.getMonth()+1)+"-"+compare10(date.getDate());
		return str;
	}
	function compare10(num){
		return num<10?("0"+num):num;
	}
	function compare10_2(str){
		var s = str<10?str.slice(1,2):str; 
		return s;
	}
	function reset(){
		$("#bjsj2").attr("value",formatDate(sd));
		$("#bjsj1").attr("value",formatDate(new Date(sd.getTime()-1000*60*60*24)));
	}
	function searchData(){
		var params = 'fxryid=<%=request.getParameter("fxryid")%>';
		if($("#bjsj1").val()!="" ||$("#bjsj2").val()!=""){
			if($("#bjsj1").val()=="" ||$("#bjsj2").val()==""){
			    /*update by yang on 2016/09/07*/
				if(api!=null){
					var W = api.opener;
					W.$.dialog.alert("开始时间和结束时间必须同时填写！");
				}else{
					frames.parent.parent.$.dialog.alert("开始时间和结束时间必须同时填写！");
				}
				/*end*/
				return ;
			}else{
				
				var startTime = $("#bjsj1").val();
				var endTime = $("#bjsj2").val();
				var sd = new Date(startTime.slice(0,4),compare10_2(startTime.slice(5,7))-1,startTime.slice(8,10));
				var ed = new Date(endTime.slice(0,4),compare10_2(endTime.slice(5,7))-1,endTime.slice(8,10));
				var iDays = parseInt(Math.abs(sd-ed)/1000/60/60/24);
				if(iDays>7){
					/*update by yang on 2016/09/07*/
					if(api!=null){
						var W = api.opener;
						W.$.dialog.alert("查询时间间隔必须小于7天！");
					}else{
						frames.parent.parent.$.dialog.alert("查询时间间隔必须小于7天！");
					}
					//$("#bjsj2").focus();
					/*end*/
					return ;
				}
				params += "&startTime="+$("#bjsj1").val()+"&endTime="+$("#bjsj2").val();
			}
		}
		searchAction(params);
		
	}
	
	function searchAction(params){
		$.ajax({
			url:'${ctx}/data/dwjk/history.action',
			data:params,
			async:false,
			success:function(data){
				//if($("input:checkbox[name='animate']").attr("checked")!="checked")
					ClearLSGJ();
					
				//add by yang on 2016/08/26
				var start = 0;
				if(data.length > 18){
					start += 11;
				}
				$("#name").text(getValue(data[start+1])==" "?"无此项信息":data[start+1]);
				$("#sex").text(getValue($.dictionary.formatter('XB')(data[start+2]))==" "?"无此项信息":$.dictionary.formatter('XB')(data[start+2]));
				$("#idcard").text(getValue(data[start+3])==" "?"无此项信息":data[start+3]);
				$("#phoneNumber").text(getValue(data[start+4])==" "?"无此项信息":data[start+4]);				
				$("#houseAddress").text(getValue(data[start+5])==" "?"无此项信息":data[start+5]);
				$("#adjustType").text(getValue($.dictionary.formatter('JZLB')(data[start+6]))==" "?"无此项信息":$.dictionary.formatter('JZLB')(data[start+6]));
				var imghref = '../../images/head.jpg';
		   		if(getValue(data[start+7])!=" "){
		   			imghref='${ctx }/data/photos/view.action?id='+data[start]+'&upFID='+data[start];
		   			$("#photo").attr("src",imghref);
		   		}
				//end
				
				if(data.length>3){
					var size = new OpenLayers.Size(50,20);
					var iconS = new OpenLayers.Icon(null, size,{
						x : -(size.w*0.49),
						y : -(size.h*1.59)
					});
					var iconE = new OpenLayers.Icon(null, size,{
						x : -(size.w*0.49),
						y : -(size.h*1.59)
					});
					  if($("input[name='heatpoint']").attr("checked")=="checked"){
						  if(heat !=null){
						 	 return;
						  }
						  drawHeatMap(data[0]);
						  return;
					}else if(heat !=null){
						heat.clearPoints();
						map.removeLayer(heat);
						heat = null;
					}  
					SetCenter(data[1],data[2],GetCurrentZoom());
					if($("input:checkbox[name='animate']").attr("checked")=="checked"){
						 var popupContentHTMLS = data[6].replace("T"," ")+ " "+(data[7]==null?"":data[7]);
						var popupContentHTMLE = data[8].replace("T"," ")+ " "+(data[9]==null?"":data[9]);
						iconS.imageDiv.innerHTML="<div align='center' ><ul><li class='span-bd3' style='float:none'></li></ul></div>";
						addMarker(data[1],data[2],iconS,popupContentHTMLS,true,true);
						iconE.imageDiv.innerHTML="<div align='center' ><ul><li class='span-bd2' style='float:none'></li></ul></div>";
						addMarker(data[3],data[4],iconE,popupContentHTMLE,true,true); 
						DrawLSGJPoint(data[0],data[5],[10]);
						SetCenter(data[3],data[4],GetCurrentZoom());
						return;
					}
					if($("input[name='line']").attr("checked")=="checked"){
						var popupContentHTMLS = data[6].replace("T"," ")+ " "+(data[7]==null?"":data[7]);
						var popupContentHTMLE = data[8].replace("T"," ")+ " "+(data[9]==null?"":data[9]);
						iconS.imageDiv.innerHTML="<div align='center' ><ul><li class='span-bd3' style='float:none'></li></ul></div>";
						addMarker(data[1],data[2],iconS,popupContentHTMLS,true,true);
						iconE.imageDiv.innerHTML="<div align='center' ><ul><li class='span-bd2' style='float:none'></li></ul></div>";
						addMarker(data[3],data[4],iconE,popupContentHTMLE,true,true);
						DrawLSGJ(data[0]);//轨迹
					}
					if($("input[name='point']").attr("checked")=="checked"){
						DrawLSGJPoint(data[0],data[5],data[10]);
					}	
				}
			}
		});
	}
	function initToolDiv(){}
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
	
	function drawGJ(){
		if($("input:checked[name='time']").val()=="1"){
			sliderSearch();
		}else{
			searchData();
		}
	}
	function animateTools(){
		$("#_tool").toggle(
				function () {
					$(this).empty().append("》");
				    $(".c").each(function(i,n){
				    	$(n).show();
				    });
				  },
		  function () {
			  	$(this).empty().append("《");
			  	$(".c").each(function(i,n){
			    	$(n).hide();
			    });
			  }
			  
			);
	}
	function gjd(){
		if($("input[name='animate']").attr("checked")=="1"||$("input[name='animate']").attr("checked")=="checked")
			return;
		if($("input[name='point']").attr("checked")=="1"||$("input[name='point']").attr("checked")=="checked")
			$("input[name='point']").attr("checked",false);
		else
			$("input[name='point']").attr("checked","checked");
		drawGJ();
	}
	function gjx(){
		if($("input[name='animate']").attr("checked")=="1"||$("input[name='animate']").attr("checked")=="checked")
			return;
		if($("input[name='line']").attr("checked")=="1"||$("input[name='line']").attr("checked")=="checked")
			$("input[name='line']").attr("checked",false);
		else
			$("input[name='line']").attr("checked","checked");
		drawGJ();
	}
	function hdrd(){
		if($("input[name='animate']").attr("checked")=="1"||$("input[name='animate']").attr("checked")=="checked")
			return;
		if($("input[name='heatpoint']").attr("checked")=="1"||$("input[name='heatpoint']").attr("checked")=="checked")
			$("input[name='heatpoint']").attr("checked",false);
		else
			$("input[name='heatpoint']").attr("checked","checked");
		drawGJ();
	}
	function gjhf(){
		if($("input[name='animate']").attr("checked")=="1"||$("input[name='animate']").attr("checked")=="checked")
			return;
		$("input[name='animate']").attr("checked","checked");
		drawGJ();
	}
	/*add by yang on 2016/09/02*/
	function getValue(value){
		return value==null?" ":value=="null"?" ":value==""?" ":value;
	}
	/*end*/
</script>
<script language="javascript" src="${ctx}/js/custom/dictionary.js"></script>
<script src="${ctx}/js/cmap/openlayer/OpenLayers.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/openlayer/lib/OpenLayers/Lang/zh-CN.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/CMapIcon.js" type="text/javascript"></script>
<script src="${ctx}/js/cmap/cmap.js" type="text/javascript"></script>
<script src="${ctx}/js/common2.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/js/my97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-ui.js"></script>
</head>
<body>
	<input type="hidden" value="${history}" id="history" />
	<div style="text-align: center;background-color: #F4F3EF">
		<table align="center" id="searchDiv" style="width: 920px;">
			<tbody>
				<tr>
					<td style="width:175px;vertical-align:middle;">按天查询<input type="radio" name="time" checked="checked"
						value="1" onclick="showTimeChoose();" style="margin-bottom: 7px;"/> 按时间段查询<input type="radio"
						name="time" value="2" onclick="showTimeChoose()" style="margin-bottom: 7px;"/></td>
					<th style="text-align: right;vertical-align:middle;width:82px;">查询时间：</th>
					<td class="cc" width="340px" align="left" style="vertical-align:middle;">
						<input id="dateChoose" name="dateChoose" type="text"
						class="Wdate inputStyle" style="width: 150px; margin-bottom: 2px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#'})" />
						<input id="bjsj1" name="startTime" 
						type="text" searchType="gt"
						class="Wdate inputStyle" style="width: 140px; margin-bottom: 2px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
						<span id="text">至</span> 
						<input id="bjsj2" name="endTime"
						type="text" searchType="lt" 
						class="Wdate inputStyle" style="width: 140px; margin-bottom: 2px;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
					</td>
					<td style="display:none;vertical-align:middle;"><input type="text" name="min"
						style="width:16px;height:10px" disabled="disabled" />时 至 <input
						type="text" name="max" style="width:16px;height:10px"
						disabled="disabled" />时
					</td>
					<td class="cc" style="text-align:left; padding-left:4px; margin-bottom:2px; width:50px;">
						<input type="button" onclick="drawGJ()" class="bttn" id="searchBtn" value="查询" /> 
					</td>
					<td class="cc" style="text-align:left; padding-left:1px; margin-bottom:2px;">
						<input type="button" onclick="gjd()" class="bttn" id="pointBtn" value="轨迹点" />
						<input type="button" onclick="gjx()" class="bttn" id="lineBtn" value="轨迹线" />
						<input type="button" onclick="gjhf()" class="bttn" id="traceBtn" value="轨迹回放" />
						<input type="button" onclick="hdrd()" class="bttn" id="hotBtn" value="活动热点" />
					</td>
				</tr>
			</tbody>
		</table>
	</div><!-- 
	<div id="tools" style="">
		<table>
		 
						 轨迹线<input type="checkbox"  checked="checked"
						name="line" onclick="drawGJ()" style="margin-bottom: 7px;"/>
						轨迹回放<input type="checkbox"
						name="animate" onclick="drawGJ()" style="margin-bottom: 7px;"/>
						活动热点<input type="checkbox"
						name="heatpoint" onclick="drawGJ()" style="margin-bottom: 7px;"/>
			<tr><td rowspan="2" style='color:red'><span onclick="animateTools()" id="_tool">》</span></td></tr>
			<tr class="c">
			<td>轨迹回放</td><td><input type="checkbox"
						name="animate" onclick="drawGJ()" style="margin-bottom: 7px;"/></td></tr>
			<tr class="c"><td>活动热点</td><td><input type="checkbox"
						name="heatpoint" onclick="drawGJ()" style="margin-bottom: 7px;"/></td></tr>
			
		</table>
	</div>-->
	<div id="map"
		style="width: 99%; height: 90%;border: 1px solid #ccc;"></div>
	<div id="kdContainer">
		<div id="kd">
			<div id="k0" style="margin-left:-2px">0</div>
			<div id="k1">1</div>
			<div id="k2">2</div>
			<div id="k3">3</div>
			<div id="k4">4</div>
			<div id="k5">5</div>
			<div id="k6">6</div>
			<div id="k7">7</div>
			<div id="k8">8</div>
			<div id="k9">9</div>
			<div id="k10">10</div>
			<div id="k11">11</div>
			<div id="k12">12</div>
			<div id="k13">13</div>
			<div id="k14">14</div>
			<div id="k15">15</div>
			<div id="k16">16</div>
			<div id="k17">17</div>
			<div id="k18">18</div>
			<div id="k19">19</div>
			<div id="k20">20</div>
			<div id="k21">21</div>
			<div id="k22">22</div>
			<div id="k23">23</div>
			<div id="k24">24</div>
		</div>
		<div id="slider" style="width:720px;margin:0px;padding:0px"></div>
	</div>
	
	<div class="rightFixed" id="divRight" style="margin-top:40px">
		<a href="javascript:void(0);" onclick="changeMap(2);"><img src="<%=contextPath %>/images/map_change.png"/></a>
	</div>
	
	<!-- add by yang on 2016/08/26 -->
	<!--
	<div class="docker">
		<ul class="dwjk-box5">
			<li class="image"><img width="100px" id="photo" height="100px" src="../../../images/head.jpg" /></li>
	        <li class="list">姓名:<span id="name"></span></li>
	        <li class="list">性别:<span id="sex"></span></li>
	        <li class="list">身份证号:<span id="idcard"></span></li>
	        <li class="list">电话号码:<span id="phoneNumber"></span></li>
	        <li class="list">家庭住址:<span id="houseAddress"></span></li>
	        <li class="list">矫正类型:<span id="adjustType"></span></li>
	    </ul>
    </div>
    -->
	<!-- end -->
	
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
		sliderSearch();
	},
	slide:function(e,ui){
		var min = $(this).slider('values',0)/30;
		var max = $(this).slider('values',1)/30;
		$("input[name='min']").attr("value",min);
		$("input[name='max']").attr("value",max);
	}
});

function sliderSearch(){
	var time = $("#dateChoose").val();
	if(time==""){
		if(api!=null){
			var W = api.opener;
			W.$.dialog.alert("请选择日期！");
		}else{
			frames.parent.parent.$.dialog.alert("请选择日期！");
		}
		/*update by yang on 2016/09/07
		$("#dateChoose").focus();
		end*/
		return ;
	}
	//查询数据
	var params = 'fxryid=<%=request.getParameter("fxryid")%>';
	var sd = new Date(time.slice(0,4),compare10_2(time.slice(5,7))-1,time.slice(8,10));
	sd.setHours($("input[name='min']").attr("value"), 0, 0, 0);
	var ed = new Date(time.slice(0,4),compare10_2(time.slice(5,7))-1,time.slice(8,10));
	ed.setHours($("input[name='max']").attr("value"), 0, 0, 0);
	params += "&startTime="+formatDate(sd)+"&endTime="+formatDate(ed);
	searchAction(params);
	var parms=getParameters(location.href);
	SetCenter(parms["x"],parms["y"],parms["level"]);
}
</script>