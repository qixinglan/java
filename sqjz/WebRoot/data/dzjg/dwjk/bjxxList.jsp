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
				//报警级别
			    $.dictionary.combobox("#alarmLevel", "alarmLevel", "BJJB", {attr:{searchType:"eq"}});
				//报警类型
				$.dictionary.combobox("#alarmType", "alarmType", "BJLX", {attr:{searchType:"eq"}});
				//矫正类别
				$.dictionary.combobox("#adjustType", "adjustType", "JZLB", {attr:{searchType:"eq"}});
				//处理状态
				$.dictionary.combobox("#status", "status", "CLZT", {attr:{searchType:"eq"}});
				
				//表格初始化
				$("#mainGrid").jqGrid({
					rowNum: 10,
					url		    : '${ctx}/data/alarm/getAlarmInfo.action?id=<%=request.getParameter("id")%>',
					colNames	: [
					    '报警级别', '报警类型','报警内容', '报警时间','状态','处理时间','处理人/方式','操作'
					],
					
					colModel	: [
			        	   {
						        name	: 'alarmLevel',
						        index	: 'alarmLevel',
						        width	: 80,
						        align	: 'center',
						        formatter : $.dictionary.formatter('BJJB')
					        }, {
						        name	: 'alarmType',
						        index	: 'alarmType',
						        width	: 80,
						        align	: 'left',
						        formatter : $.dictionary.formatter('BJLX')
					        }, {
						        name	: 'alarm',
						        index	: 'alarm',
						        width	: 250,
						        align	: 'left'
					        }, {
						        name	: 'alarmTime',
						        index	: 'alarmTime',
						        width	: 120,
						        align	: 'center',
						        formatter:function(value, opts, rwdat){
									return rwdat.alarmTime==null?"":rwdat.alarmTime.replace("T"," ");
								}						   
					        },{
						        name	: 'status',
						        index	: 'status',
						        width	: 60,
						        align	: 'center',
						        formatter: $.dictionary.formatter('CLZT')
					        },{
						        name	: 'handleTime',
						        index	: 'handleTime',
						        width	: 120,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return rwdat.handleTime==null?"":rwdat.handleTime.replace("T"," ");
								}
					        },{
						        name	: 'handler',
						        index	: 'handler',
						        width	: 100,
						        align	: 'center'
					        },  {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 60,
						        align	: 'center',
						        sortable : false,
						        fixed : true,
						        formatter : function(value, opts, rwdat) {
											var str="<a onClick='view(\""+rwdat.alarmId+"\")' title='' class='a-style'>详情</a>";
										 	return str;
						
						        }
					        }
					],
					multiselect : false,
					pager: '#mainGridPager',
					sortname : 'status,alarmTime,alarmLevel',
					sortorder : "desc,desc,asc"
				});
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});
			
			function view(id){
				window.parent.W.$.dialog({ 
				    id: 'testID',
				    width: '600px', 
    				height: '400px', 
    				title:'历史报警详情查看',
				    content: "url:${ctx}/data/dzjg/dwjk/bjxxView1.jsp?id="+id,
				    //ok: function(){ 
        		//	return false; 
    				//}, 
    				cancelVal: '关闭', 
    				cancel: true 
				});
			}
			
		</script>
		<!-- add by yang on 2016/09/29 -->
		<style type="text/css">
		.search-table {
			width: 100%;
			border: 1px solid #d8d6d6;
		}
		.search-table th, .search-table td {
			height: 50px;
			line-height: 50px;
		}
		.search-table th {
			font-weight: 100;
			text-align: right;
			vertical-align: middle;
			white-space: nowrap;
			padding-right:5px;
		}
		.search-table td {
			width: 200px\7;	/* ie6,7,8 */
			width: 200px\7;	/* ie6,7,8 */
			text-align: left;
			vertical-align: middle;
		}
		.search-table input[type="text"], .inputStyle {
			width: 130px;
			border: 1px solid #bdbdbd;
			vertical-align: central;
			margin: auto;
			line-height: 20px;
			height: 20px;
		}
		.search-table select{
			width: 145px;
			margin: auto;
		
		}
		</style>
		<!-- end -->
	</head>

	<body>
	<div class="container-top">
		</div>
		 	<div class="container-top" id="searchDiv">
			<table class="search-table" >
				<tbody>
					<tr>
						<th>报警级别：</th>
						<td id="alarmLevel"/>
						
						<th>报警类型：</th>
						<td id="alarmType"/>
						
						<th>报警时间：</th>
						<td><input id="bjsj1" name="alarmTime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
							至 <input id="bjsj2" name="alarmTime" type="text"
							class="Wdate inputStyle" style="width: 85px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
						</td>
						
						<td><input type="button" id="searchBtn" class="bttn bicon-search" value="查询" />
						</td>
					</tr>
					<tr>
						<th>状态：</th>
						<td id="status">
						</td>
						
						<th>处理人/方式：</th>
						<td><input name="handler" id="handler" type="text" searchType="cn" maxlength="100"/></td>
							
						<th>处理时间：</th>
						<td><input id="qcsj1" name="handleTime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'qcsj2\')}'})" />
							至 <input id="qcsj2" name="handleTime" type="text"
							class="Wdate inputStyle" style="width: 85px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'qcsj1\')}'})" />
						</td>
						
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		  <!--列表-->		
		  <div class="container-bottom">
		    <table id="mainGrid">
		    </table>
		    <div id="mainGridPager"></div>
		  </div>
	</body>
</html>
