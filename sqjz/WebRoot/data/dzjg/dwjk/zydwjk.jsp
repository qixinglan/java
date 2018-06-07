﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>定位监控</title>
		<%@ include file="/common/commonjs.jsp"%>
		 <link rel="stylesheet" href="${ctx }/js/cmap/openlayer/theme/default/style.css" type="text/css"/>
        <link rel="stylesheet" href="${ctx }/js/cmap/cmap.css" type="text/css"/>
        <style>
        html,body
        {
         width:100%;
         height:100%;
         padding:0px;
         margin:0px;
        }
        .searchStyle{
        	width:133px !important;
        	vertical-align: top !important;
        }
        </style>
      
		</head>

<body>
<%@include file="/data/top.jsp" %>
    <div style="margin:0px;position:relative;padding:0px">
    <div style="margin:0px;position:relative;">
    <div class="rightFixed" id="divRight">
	    <a href="javascript:void(0);" onclick="changeMap(1);" ><img src="../../images/map_change.png"/></a>
	</div>
    <table style="clear:both;top:0px;width:100%;heigth:100%;position:relative;">
     <tbody><tr style="clear:both">
     <td class="dwjk-box7-item" style="clear: both; width: 260px; display: table-cell;">
        <div class="dwjk-box8" style="width:260px">
            	<table >
            		 
                    <tr>
                    	<th class="orgName">执行机关:</th>
                        <td id="orgName" style="clear:both;width:142px;vertical-align: center"></td>
                    </tr>
                    <tr>
                    	<th>矫正类别:</th>
                        <td id="adjustType" name="adjustType" style="width:151px" searchType="eq"></td>
                    </tr>
                    <tr>
                    	<th>姓名:</th>
                        <td><input type="text" id="personName" name="personName" maxlength="20"  style="clear:both;width:125px;vertical-align: center"/></td>
                    </tr>
                    <tr>
                    	<th>报警状态:</th>
                        <td><input type="radio" name="alarmSta" value="" id="rad1" checked="checked"/>全部
                        	<input type="radio" name="alarmSta" value="0" id="rad2"/>正常
                            <input type="radio" name="alarmSta" value="1" id="rad3"/>报警
                        </td>
                    </tr>
                    <tr>
                    	<th colspan="2"><input type="button" class="btnnsearch" onclick="javascript:searchData(1);"/></th>
                        
                    </tr>
                </table>
        </div> 
         <div class="dwjk-box9" style="width:260px">
                <div class="dwjk-box10" style="width:260px">
                	<span class="page" style="display:none"></span>
                	<div id="page">
                	<label onclick="javascript:void(0)">首页</label>
                	<label onclick="javascript:void(0)">上一页</label>
                    <label onclick="javascript:void(0)">下一页</label>
                    <label onclick="javascript:void(0)">尾页</label>
                   </div>
                </div>
              <div style="height: 10px"></div>
              </div>     
     </td>
     <td id="showSearch" class="showDiv" style="padding-right:10px;width:9px;clear:both" ></td>
     <td style="clear:both;width:100%;height:100%;z-index:100">
     <div style="margin-left:10px;position:relative; width:100%; height:680px;border: 1px solid #ccc;z-index:10">
     <iframe id="ezmap" src="${ctx }/js/ezmap/ezmap.jsp" width="100%" height="100%"></iframe>
     </div>
     <!--<div id="map"  style="margin-left:10px;position:relative; width:100%; height:680px;border: 1px solid #ccc;z-index:10"></div>
      -->
      </td>
     </tr>
     <!-- <tr>
     	<td>
     		<div class="rightFixed" id="divRight">
			    <span onclick="backStaView()" id="jkxq"><div class="dwjk-jkxq" style="background-image: url(${ctx}/images/bt2.png)"></div></span>
			</div>
     	</td>
     </tr>-->
     <tr>
     <c:if test="${user.wunit.orgType == '1'}">
     <td colspan="3">
      <div class="dwjk-box5">
      <ul>
			<li id="1">北京市</li>
			<li id="181">顺义区</li>
			<li id="182">平谷区</li>
			<li id="183">门头沟区</li>
			<li id="184">通州区</li>
			<li id="185">大兴区</li>
			<li id="186">密云区</li>
			<li id="187">怀柔区</li>
			<li id="188">延庆区</li>
			<li id="190">丰台区</li>
			<li id="192">东城区</li>
			<li id="193">西城区</li>
			<li id="194">石景山区</li>
			<li id="195">海淀区</li>
			<li id="196">朝阳区</li>
			<li id="197">房山区</li>
			<li id="198">昌平区</li>
		</ul>
    </div> 
     </td>
     </c:if>
     </tr>
     </table> 
      
     </div>
    <%@include file="/data/bottom.jsp"%>
</div>
</body>
	<script type="text/javascript">
		var SEARCH_FLAG = false;
		var INIT_FLAG =true;
		var _layer_person;
		var _layer_note;
		var FxryList={};
		var ORG_ID = <%=request.getParameter("orgId")%>
		var fxryList={};
		var mapApp;
			$(function(){
				mapApp=document.frames["ezmap"];
				//执行机关
				$.organization.combobox("#orgName", "orgId", ${user.wunit.bm}, {
					allowBlank : true,
					level : 10,
					showRoot : true,
					showItself : true,
					notShowType:"4,5,6,7,8,9",
					emptyText : '全部',
					dropdownAutoWidth:true,
					defValue:<%=request.getParameter("orgId")%>,
					fieldClass:"searchStyle"
				});
				//矫正类别
				$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"eq"},fieldClass:"searchStyle"});
				//初始化地图标点
				setTimeout(function(){
					searchData(1);
					var ORG_NAME= $("[id="+ORG_ID+"]");	
					if(ORG_NAME&&ORG_NAME.length>0)
						mapApp.GetFxryDZWL(ORG_NAME.text());
				},500);
				
				var param = getLocationParam();
				if(param.flag=="view"){
					$("#showSearch").click();
				}
				$("#showSearch").click(function(){
					if($(this).hasClass("showDiv")){
						$(".dwjk-box7-item").hide();
						$(this).addClass("hideDiv");	
						$(this).removeClass("showDiv");
					}else{
						$(".dwjk-box7-item").show();
						$(this).addClass("showDiv");	
						$(this).removeClass("hideDiv");
					}
				});			
				//切换所选择区
				$(".dwjk-box5 li").each(function(){						
					if($(this).attr("id")==<%=request.getParameter("orgId")%>)
						$(this).addClass("selected");
				});
				
				$(".dwjk-box5 li").click(function(){
					var id = $(this).attr("id");
					var orgClassId = $("input[name='orgId']").attr("id")+"_mcdropdown";
					$(("input[id='"+orgClassId+"']")).attr("value",$.organization.formatter()(id));
					$(("input[name='orgId']")).attr("value",id);
					$("#orgName").text("");
					
					$.organization.combobox("#orgName", "orgId", id, {
						allowBlank : true,
						level : 10,
						showRoot : false,
						showItself : true,
						emptyText : '全部',
						defValue:id
					});
					mapApp.GetFxryDZWL($(this).text());
					searchData(1);
					INIT_FLAG = true;
					SEARCH_FLAG = false;
					ORG_ID = id;				
					$("li").each(function(){						
						if($(this).hasClass("selected"))
							$(this).removeClass("selected");
					});
					$(this).addClass("selected");
					
				});
				//定时刷新地图
				timer();
				var parms=getParameters(location.href);
				if(parms["x"]&&parms["x"]!="undefined"){
					ezmap.SetCenter(parms["x"],parms["y"],parms["level"]);	
				}
			});
			
			function bjxx(name,id,pageNo){
						$.dialog({ 
						    id: 'wclxxList',
						    width: '750px', 
		    				height: '250px', 
		    				title: name+'的未处理报警信息',
						    content: 'url:${ctx}/data/dzjg/dwjk/wclbjxxList.jsp?id='+id,
						    okVal:'关闭',
							ok: function(){ 
								searchData(pageNo);
							}
						});
					}	
			function xqxx(id,name){
						$.dialog({ 
						    id: 'testID2',
						    width: '1020px', 
		    				height: '600px', 
		    				title:name+'的详细信息查看',
						    content: 'url:${ctx}/data/dzjg/dwjk/dwjkView.jsp?id='+id,
		    				cancelVal: '关闭', 
		    				cancel: true 
						});
					}
			 
				function searchData(pageNo){
					var personName = $($("input[id='personName']")).val();
					var alarmSta = "";
					$.each($("input[type='radio']"),function(i,n){
						if(n.checked){
							alarmSta=n.value;
							return false;
						}
					});
					var corrType = $($("input[name='adjustType']")).val();
					var orgId = $($("input[name='orgId']")).val();
					$.ajax({
					   type: "POST",
					   async:true,
					   url: "${ctx }/data/dwjk/searchGIS.action",
					   dataType:"json",
					   data:"pageNo="+pageNo+"&personName="+personName+"&orgId="+orgId+"&corrType="+corrType+"&alarmSta="+alarmSta,
					   success:function(data){
						   var datas = data.result;
						   $(".dwjk-box9-jg").remove();
						   $(".dwjk-box9-item").remove();
						   $("#page").remove();
						   for(var item in fxryList)
						   {
							   mapApp.mapApp.removeOverlay(fxryList[item]); 
						   }
						   fxryList=null;
						   fxryList={};
						  
						   var str1 = "<span class='dwjk-box9-jg'>共有<span style='color:red'>"+datas.length+"</span>条结果</span>";
						   $(".dwjk-box10").before(str1);
						   $.each( datas, function(i, n)
						   {   					
							    var str = "<div class='dwjk-box9-item'><table><tr><td rowspan='2'>";
								if(datas[i].ac>0){
									str+="<span class='span-bd' style='width:35px;' onclick='locate(\""+datas[i].id+"\","+datas[i].gisX+","+datas[i].gisY+");'>";
								}else{
									str+="<span class='span-bd1' style='width:35px;' onclick='locate(\""+datas[i].id+"\","+datas[i].gisX+","+datas[i].gisY+");'>";
								} 
								if(datas[i].gisX&&datas[i].gisX>0){
									var content="";
									if(datas[i].ac>0){
										content= "<div align='center'><ul><li class='span-bd' style='float:none;width:35px;'></li><li><b  style='color:chocolate;font:menu'>"+datas[i].name+"</b></li></ul></div> ";
									}else{
										content="<div align='center'><ul><li class='span-bd1' style='float:none;width:35px;' ></li><li><b  style='color:chocolate;font:menu'>"+datas[i].name+"</b></li></ul></div> ";
									} 
									var detail=getdetail(datas[i].id,datas[i].name,datas[i].phoneNumber,datas[i].orgId,datas[i].adjustType,datas[i].gisX,datas[i].gisY,datas[i].personName,datas[i].personPhone,datas[i].address,datas[i].locType,datas[i].locTime);
									var marker=mapApp.addMarkerBylayer(datas[i].gisX,datas[i].gisY,content,detail);
									fxryList[datas[i].id]=marker;
							 	}
								if(i<data.pageSize*(data.pageNo-1)||i>(data.pageSize*data.pageNo-1))
									return true;
								str+=((i+1)+"</span></td>");
								str+="<td><span>"+getValue(datas[i].name)+"  "+getValue(datas[i].phoneNumber)+"  "; 
								str+=$.dictionary.formatter("JZLB")(datas[i].adjustType);
								str+="   "+$.organization.formatter()(datas[i].orgId)+"</span></td>";
								str+="</tr><tr><td><a onClick='bjxx(\""+datas[i].name+"\",\""+datas[i].id+"\",\""+data.pageNo+"\")'><span class='dwjk-box9-item-bj'><label  id=\""+datas[i].id+"\">";
								str+=(datas[i].ac==null?"0":datas[i].ac)+"条报警</label></span></a><a onClick='history(\""+datas[i].id+"\")'><span class='dwjk-box9-item-gj'>历史轨迹</span></a><a onClick='xqxx(\""+datas[i].id+"\",\""+datas[i].name+"\")'><span class='dwjk-box9-item-xx'>详细</span></a></td></tr></table></div>";
								$(".dwjk-box10").before(str);	
						   });
						   str = "<div id='page' style='height:10px;>";
						   str+= "<label onclick='searchData(1)'></label>";
						   str+= "<label onclick='searchData(1)'>首页</label>";
		                   str+= "<label onclick='searchData("+(data.prePage)+")'>上一页</label>";
		                   str+= "<label onclick='searchData("+(data.nextPage)+")'>下一页</label>";
		                   str+= "<label onclick='searchData("+data.totalPages+")'>尾页</label></div>";
		                   $(".page").after(str);
					   }
					});
					INIT_FLAG = false;
					SEARCH_FLAG = true;
				}
				
				function locate(id,x,y){
					if(x&&x>0){
					mapApp.Location(x,y);	
					fxryList[id].openInfoWindowHtml(fxryList[id].detail);
					}
				}

				function getdetail(id,name,phone,orgId,corrtype,x,y,personName,personPhone,address,locType,locTime){				
					var str = "<div style='width:236px;height:236px;'><ul>";
                   	if(true){
						$.ajax({
							   type: "post",
							   async:false,
							   url: "${ctx }/data/dwjk/getAlarmDetailOnGis.action",
							   dataType:"json",
							   data:"id="+id,
							   success:function(datas){
								   str +="<li>矫正机关："+$.organization.formatter()(orgId)+"<a onClick='parent.xqxx(\""+id+"\",\""+name+"\")'><span class='btnxx'></span></a></li>";
								   str +="<li>责任人："+getValue(personName)+"</li>";
								   str +="<li>责任人电话："+getValue(personPhone)+"</li>";
								   str +="<li>服刑人员姓名："+getValue(name)+"</li>";
								   str +="<li>服刑人员电话："+getValue(phone)+"</li>";
								   str +="<li>服刑人员家庭住址："+getValue(address)+"</li>";
								   str +="<li>定位类型："+getValue(locType)+"</li>";
								   str +="<li>定位时间："+getValue(locTime).replace("T"," ")+"</li>";
								   str += "</ul></div>";
							   }
						});
                   	}
				  	return str;
				}
				//地图上初始化服刑人员
				function initMap(orgId){
					//执行机关
					searchData(1);
				}				
				function timer(){
					setInterval("time()",100*1000);
				}
			    
				function time(){
					//if(SEARCH_FLAG){
						var personName = $($("input[id='personName']")).val();
						var alarmSta = "";
						$.each($("input[type='radio']"),function(i,n){
							if(n.checked){
								alarmSta=n.value;
								return false;
							}
						});
						var corrType = $($("input[name='adjustType']")).val();
						var orgId = $($("input[name='orgId']")).val();
						$.ajax({
						   type: "POST",
						   async:true,
						   url: "${ctx }/data/dwjk/searchGIS.action",
						   dataType:"json",
						   data:"personName="+personName+"&orgId="+orgId+"&corrType="+corrType+"&alarmSta="+alarmSta,
						   success:function(data){
							   var datas = data.result;
							   for(var i=0;i<datas.length;i++)
							   {				
								    var content;
								    if(fxryList[datas[i].id])
								    {
								    	var p=mapApp.convertpoint(datas[i].gisX,datas[i].gisY);
								    	fxryList[datas[i].id].setPoint(new mapApp.Point(p.split(",")[0],p.split(",")[1]));
								    }else{
									if(datas[i].gisX&&datas[i].gisX>0){
									if(datas[i].ac>0){
										content= "<div align='center'><ul><li class='span-bd' style='float:none;width:35px;'></li><li><b style='color:chocolate;font:menu'>"+datas[i].name+"</b></li></ul></div> ";
									}
									else{
										content="<div align='center'><ul><li class='span-bd1' style='float:none;width:35px;'></li><li><b style='color:chocolate;font:menu'>"+datas[i].name+"</b></li></ul></div> ";
									}	
									
								    var detail=getdetail(datas[i].id,datas[i].name,datas[i].phoneNumber,datas[i].orgId,+datas[i].adjustType,datas[i].gisX,datas[i].gisY,datas[i].personName,datas[i].personPhone,datas[i].address,datas[i].locType,datas[i].locTime);
								    var marker=mapApp.addMarkerBylayer(datas[i].gisX,datas[i].gisY,content,detail);
								    marker.detail=detail;
									fxryList[datas[i].id]=marker;
									}
								   }
								}
						   }
						});
						
					}
				function backStaView(){
					var action = "${ctx}/data/dwjk/countyViewControl.action?orgId=";
					$(".dwjk-box5 li").each(function(){						
						if($(this).hasClass("selected")){
							action = action +$(this).attr("id")+"&orgName="+$(this).text();
						}
					});
					window.location = action;
				}
				function history(mapId) {
					$.dialog({
						id : 'testID',
						width: '1020px', 
		    			height: '550px',
						title : '历史轨迹',
						content : 'url:${ctx}/data/dzjg/dwjk/zyhistory.jsp?fxryid=' + mapId,
						cancelVal : '关闭',
						cancel : true
					});
				}
				function getValue(value){

					return value==null?" ":value=="null"?" ":value;
				}
		</script>
</html>
