<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String num = request.getParameter("id");
	String type = request.getParameter("type");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线设备</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	var num = '<%=num%>';
	var type = '<%=type%>';
	$(function() {
		$.dictionary.combobox("#type", "type", "XXLX", {
			allowBlank : true,
			emptyText : '全部',
			defValue : '',
			fieldClass : "search-form-field",
			attr : {
				searchType : "eq"
			}
		});
		var actions = "主机";
		if (type != '1') {
			actions = "移动终端";
		}
		var text=actions+":"+num;
		$("#name").text(text);
		//表格初始化
		$("#mainGrid").jqGrid(
				{
					url : '${ctx}/data/test/xmpp/view.action?num=' + num
							+ '&type=' + type,
					mtype : "POST",
					datatype : 'json',
					colModel : [
							{
								name : 'type',
								index : 'type',
								label : '类型',
								formatter : 'dictionary',
								formatoptions : {
									code : 'XXLX'
								},
								width : 50,
								align : 'center'
							},
							{
								name : 'messageTime',
								index : 'messageTime',
								label : '消息时间',
								width : 150,
								align : 'center'
							},
							{
								name : 'messageId',
								index : 'messageId',
								label : '消息ID',
								width : 150,
								align : 'center'
							},
							{
								name : 'messageType',
								index : 'messageType',
								label : '消息类型',
								width : 70,
								align : 'center'
							},
							{
								name : 'message',
								index : 'message',
								label : '消息内容',
								width : 700,
								formatter : function(value, opts, rwdat) {
									var actions = value.replace(/</g, "&lt;")
											.replace(/>/g, "&gt;");
									return actions;
								},
								align : 'center'
							} ],
					multiselect : false,
					rownumbers : true,
					sortname : 'messageTime',
					sortorder : "desc",
					pager : '#mainGridPager'
				});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
</script>
</head>
<body>
	<div id="searchDiv" class="container-top">
		<table class="search-table">
			<tbody>
				<tr>
					<th>消息类型</th>
					<td id="type"></td>
					<th>消息内容</th>
					<td><input type="text" name="message" searchType="cn"
						maxlength="20" class="search-form-field" /></td>
					<td><input type="button" id="searchBtn"
						class="bttn bicon-search" value="查询" /></td>
				</tr>
				<tr>
					<th>消息时间</th>
					<td><input id="bjsj1" style="width: 185px" name="messageTime"
						type="text" searchType="gt" class="Wdate inputStyle"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />至<input
						id="bjsj2" style="width: 185px" name="messageTime" type="text"
						searchType="lt" class="Wdate inputStyle"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
					</td>
					<th id="name"></th>
					<td></td>
					<td><input type="button" id="resetBtn"
						class="bttn bicon-reset" value="重置" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container-bottom">
		<table id="mainGrid">
		</table>
		<div id="mainGridPager"></div>
	</div>
</body>
</html>