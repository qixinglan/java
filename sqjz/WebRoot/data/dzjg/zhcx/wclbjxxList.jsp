﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
					rowNum: 10,
					url		    : '${ctx}/data/alarm/getUntreatedAlarmInfos.action?id=<%=request.getParameter("id")%>',
					colNames	: [
					    'id','报警级别', '报警类型','报警内容','报警时间','是否自动提醒','操作'
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
						        width	: 60,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return rwdat.alarmTime==null?"":rwdat.alarmTime.replace("T"," ");
								}
					        }, {
						        name	: 'isautoAlert',
						        index	: 'isautoAlert',
						        width	: 60,
						        align	: 'center',
						        formatter : $.dictionary.formatter('SFZDBJ')
					        },  {
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
							 });
							 $("#mainGrid").trigger("reloadGrid");
					 };	 
				})
			});
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
			var api = frameElement.api, W = api.opener;
			function edit(id){
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
		    			},  
	    				cancelVal: '取消', 
	    				cancel: true ,
	    				zindex:2000
					});
			}
			function reload(){
				 $("#mainGrid").trigger("reloadGrid");
			}
		</script>
		</head>

<body>
<div style="margin:10px">
  
  <!--列表-->
  <div class="buttonArea operation">
      <input type="button" class="bttn bicon-delete" id="btnDel" value="批量处理" />
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
