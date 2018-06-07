<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			$(function() {
				//报警级别
			    $.dictionary.combobox("#alarmLevel", "alarmLevel", "BJJB", {searchType : "eq", attr:{searchType:"eq"}});
				//报警类型
				$.dictionary.combobox("#alarmType", "alarmType", "BJLX",{attr:{searchType:"eq"}});
				//矫正类别
				$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"eq"}});
				//执行机关
				$.organization.combobox("#executeUnit", "executeUnit", ${user.wunit.bm}, {
			    	allowBlank : true,
					level : 10,
					showRoot : true,
					showItself : true,
					notShowType:"4,5,6,7,8,9",
					emptyText : '全部',
					attr:{searchType:"eq"}});
				
				
				$.dictionary.combobox("#status", "status", "CLZT", {attr:{searchType:"eq"}});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/dzjg/lsbjxxgl/getOrgAlarm.action?personId=${user.person.id}',
					colNames	: [
					 'id','报警级别', '报警类型','姓名','矫正类别','报警内容','报警时间','执行机关','是否自动提醒','操作'
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
						        width	: 40,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BJJB')
					        }, {
						        name	: 'alarmType',
						        index	: 'alarmType',
						        width	: 50,
						        align	: 'left',
						        formatter : $.dictionary.formatter('BJLX')
					        }, 
					        {
						        name	: 'name',
						        index	: 'name',
						        width	: 40,
						        align	: 'left'
					        }, 
					        {
						        name	: 'adjustType',
						        index	: 'adjustType',
						        width	: 50,
						        align	: 'left',
						        formatter : $.dictionary.formatter('JZLB')
					        }, {
						        name	: 'alarm',
						        index	: 'alarm',
						        width	: 70,
						        align	: 'left'
					        }, {
						        name	: 'alarmTime',
						        index	: 'alarmTime',
						        width	: 100,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return rwdat.alarmTime==null?"":rwdat.alarmTime.replace("T"," ");
								}
					        },
					        {
						        name	: 'executeUnit',
						        index	: 'executeUnit',
						        width	: 80,
						        align	: 'left',
						        formatter:function(value, opts, rwdat){
									return $.organization.formatter()(rwdat.executeUnit);
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
					sortname : 'alarmLevel,alarmType,alarmTime,name,executeUnit,adjustType',
					sortorder : "asc,desc,desc,desc,desc,asc"
				});
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
				});
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
			//导出
			function openExcel() {
				var colNames = $("#mainGrid").getGridParam("colNames");
				var sortName = $("#mainGrid").getGridParam("sortname");
				window.open("${ctx }/data/dzjg/lsbjxxgl/excel.action?type=wcl&colNames=" + colNames
						+ "&sortName=" + sortName, "downloadFrame");
			}
		</script>
		</head>

<body>
<div style="margin:10px">
  <div class="container-top" id="searchDiv">
			<table class="search-table" >
				<tbody>
					<tr>
						<th>报警时间：</th>
						<td><input id="bjsj1" name="alarmTime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
							至 <input id="bjsj2" name="alarmTime" type="text" searchType="lt"
							class="Wdate inputStyle" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
						</td>
						<th>报警级别：</th>
						<td id="alarmLevel"/>
						<th>报警类型：</th>
						<td id="alarmType"/>
						
						<td><input type="button" id="searchBtn" class="bttn bicon-search"  value="查询" />
						</td>
					</tr>
					<tr>
						<th>姓名：</th>
						<td><input type="text" id="name" name="name" style="width:215px" searchType="cn"  /></td>
						<th>执行机关：</th>
						<td id="executeUnit"></td>
						<th>矫正类别：</th>
						<td id="adjustType" style="width:151px" ></td>
						
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
  <!--列表-->
  <div class="buttonArea operation">
      <input type="button" class="bttn bicon-delete" id="btnDel" value="批量处理" />
      <input type="button" id="btnExcel" class="bttn bicon-excel" onclick="openExcel()"
				value="导出" />
  </div>
  <br/>
  <div class="container-bottom">
    <table id="mainGrid" >
    </table>
    <div id="mainGridPager"></div>
  </div>
</div>
</body>
</html>
