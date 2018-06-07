<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<%@ include file="/common/commonjsTemp.jsp"%>
		<script type="text/javascript">
			$(function() {
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/alarm/getUntreatedAlarmInfos.action?id=<%=request.getParameter("id")%>',
					colNames	: [
					    'id','报警级别', '报警类型','报警内容','报警时间','定位','操作'
					],
					
					colModel	: [{
								name : 'alarmId',
								index : 'alarmId',
								label : 'alarmId',
								key : true,
								hidedlg : false,
								hidden : true
							},
					        {
						        name	: 'alarmLevel',
						        index	: 'alarmLevel',
						        width	: 30,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BJJB')
					        }, {
						        name	: 'alarmType',
						        index	: 'alarmType',
						        width	: 40,
						        align	: 'left',
						        formatter : $.dictionary.formatter('BJLX')
					        }, {
						        name	: 'alarm',
						        index	: 'alarm',
						        width	: 80,
						        align	: 'left'
					        }, {
						        name	: 'alarmTime',
						        index	: 'alarmTime',
						        width	: 80,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return rwdat.alarmTime==null?"":rwdat.alarmTime.replace("T"," ");
								}
					        }, 
					        {
						        name	: 'X',
						        index	: 'X',
						        width	: 20,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									  var  str="<a onClick='locAlarm(\""+rwdat.alarmId+"\",\""+rwdat.x+"\",\""+rwdat.y+"\")' title='' class='a-style'>定位</a>";
								 return str;
		        				}
					        },
					        {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 40,
						        align	: 'center',
						        fixed : true,
						        formatter : function(value, opts, rwdat) {
													//var str="<a onClick='del(\""+rwdat.alarmId+"\")' title='' class='a-style'>处理</a>";
													  var  str="<a onClick='edit(\""+rwdat.alarmId+"\")' title='' class='a-style'>处理</a>";
												 return str;
						
						        }
					        }
					],
					multiselect : true,
					pager: '#mainGridPager',
					sortname : 'status,alarmTime,alarmLevel',
					sortorder : "desc,desc,asc"
				});
				$("#mainGrid").addSearchForm("#search");
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
				$("#btnDel").click(function(){
					var ids = $("#mainGrid").getGridParam("selarrrow");
					if(ids==null||"" == ids){
						alert("请选择处理数据!");
						return false;
					}
					 if(confirm("确定处理所选的报警信息吗？")){
						 $.ajax({
							   type: "POST",
							   async:false,
							   url: "${ctx }/data/alarm/dealAlarm.action",
							   data: "ids="+ids,
							   success:function(){
								   $("#mainGrid").trigger("reloadGrid");
							   }
							 });
							 
					 };	 
				});
				initStatic('<%=request.getParameter("id")%>');
			});
			var api = frameElement.api, W = api.opener;
			function locAlarm(alarmId,x,y)
			{
				window.parent.SetCenter(x,y,7);
				window.parent.ClearMarkersbylayer(window.parent._layer_loc_alarm);
				var size = new window.parent.OpenLayers.Size(50,20);
				var icon = new window.parent.CMapIcon(null, size,{
						x : -(size.w*0.39),
						y : -(size.h*1.15)
				});
				var str="<div align='center' onclick='showAlarmDetail(\""+alarmId+"\",\""+x+"\",\""+y+"\")'><ul><li class='span-bd' style='float:none;width:35px;'>A</li></ul></div>";
				icon.imageDiv.innerHTML = str;
				window.parent.addMarkerBylayer(x,y,icon,window.parent._layer_loc_alarm,null,true,true);
				window.parent.showAlarmDetail(alarmId,x,y);
				api.close();
			}
			function del(obj){
				var ids = obj;
				if(confirm("确定处理所选的报警信息吗？")){
					 $.ajax({
						   type: "POST",
						   async:false,
						   url: "${ctx }/data/alarm/dealAlarm.action",
						   data: "ids="+ids,
						   success:function(){
							   $("#mainGrid").trigger("reloadGrid");
						   }
						 });
						 
				};	
			}
			
			function edit(id){
				var row = $("#mainGrid").getGridParam("selarrrow");
					W.$.dialog({ 
					    id: 'wclID',
					    width: '500px', 
	    				height: '200px', 
	    				title:'处理信息',
					    content: 'url:${ctx}/data/dzjg/dwjk/wclbjxxEdit.jsp?id='+id,
					    okVal:'保存',
						ok: function(){ 
							$(W.$(this).attr("iframe").contentDocument.getElementById("form1")).ajaxSubmit();
							$("#mainGrid").trigger("reloadGrid");
							initStatic('<%=request.getParameter("id")%>');
		    			},  
	    				cancelVal: '取消', 
	    				cancel: true ,
	    				zindex:2000
					});
			}
			function reload(){
				 $("#mainGrid").trigger("reloadGrid");
			}
			function initStatic(id){
				$.ajax({
					   type: "POST",
					   async:false,
					   url: "${ctx }/data/alarm/untreatedInfoStatictis.action",
					   data: "id="+id,
					   success:function(data){
							$("#yuejie").empty().append(data[1]+"次");
							$("#pochai").empty().append(data[2]+"次");
							$("#juji").empty().append(data[3]+"次");
							$("#didian").empty().append(data[4]+"次");
							$("#renji").empty().append(data[5]+"次");
							$("#jingmo").empty().append(data[6]+"次");
							$("#buzaixian").empty().append(data[7]+"次");
							$("#heji").empty().append(data[1]+data[2]+data[3]+data[4]+data[5]+data[6]+data[7]).append("次")
					   }
					 });
			}
		</script>
		</head>

<body>
<div style="margin:10px">
  
  <!--列表-->
  <div class="buttonArea operation">
      <input type="button" class="bttn bicon-delete" id="btnDel" value="批量处理" />
      <table class="search-table" style="text-align: center !important">
				<tbody>
					<tr align="center">
						<td>越界报警</td>
						<td>破拆报警</td>
						<td>聚集报警</td>
						<td>低电报警</td>
						<td>人机分离报警</td>
						<td>静默报警</td>
						<td>设备不在线</td>
						<td>合计</td>
					</tr>
					<tr align="center">
						<td id="yuejie">0次</td>
						<td id="pochai">0次</td>
						<td id="juji">0次</td>
						<td id="didian">0次</td>
						<td id="renji">0次</td>
						<td id="jingmo">0次</td>
						<td id="buzaixian">0次</td>
						<td id="heji">0次</td>
					</tr>
				</tbody>
			</table>
  </div>
  <br/>
  <div class="container-bottom">
    <table id="mainGrid" >
    </table>
    <div id="mainGridPager"></div>
  </div>
</div>
<div id="search"></div>
</body>
</html>
