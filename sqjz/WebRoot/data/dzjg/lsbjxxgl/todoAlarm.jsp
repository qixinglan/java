<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史报警信息管理</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		//表格初始化
		$("#mainGrid").jqGrid({
			url: "${ctx}/data/dzjg/lsbjxxgl/todo.action",
			colModel: [
					{
						name	:"alarmId",
						index	:"alarmId",
						label	: "alarmId",
						key : true,
						hidedlg	:false,
						hidden	:true
					},{
						name	:"fxryId",
						index	:"fxryId",
						label	: "fxryId",
						hidedlg	:false,
						hidden	:true
					},{
				        name	: "alarmLevel",
				        index	: "alarmLevel",
				        label	: "报警级别",
				        searchType: "eq",
				        sortable: true,
				        formatter : "dictionary",
				        formatoptions: {code:"BJJB"},
				        width	: 70,
				        align	: "center"
			        },{
				        name	: "alarmType",
				        index	: "alarmType",
				        label	: "报警类型",
				        width	: 100,
				        searchType: "eq",
				        formatter : "dictionary",
				        formatoptions: {code:"BJLX"},
				        sortable: true,
				        align	: "center"
			        }, {
				        name	: "alarmTime",
				        index	: "alarmTime",
				        label	: "报警时间",
				        sortable: true,
				        searchType: "in",
				        searchInput: "date",
				        width	: 150,
				        align	: "center"
			        },{
				        name	: "name",
				        index	: "name",
				        label	: "姓名",
				        width	: 150,
				        sortable: true,
				        searchType : "cn",
				        align	: "center"
			        },{
				        name	: "adjustType",
				        index	: "adjustType",
				        sortable: true,
				        width	: 100,
				       	label	: "矫正类别",
				       	searchType: "eq",
				       	formatter : "dictionary",
				        formatoptions: {code:"JZLB"},
				        align	: "center"
			        },{
				        name	: "alarm",
				        index	: "alarm",
				        sortable: true,
				        width	: 200,
				       	label	: "报警内容",
				        align	: "center"
			        }, {
				        name	: "status",
				        index	: "status",
				        sortable: true,
				        width	: 50,
				       	label	: "状态",
				       	formatter : "dictionary",
				        formatoptions: {code:"CLZT"},
				        align	: "center"
			        }, {
				        name	: "cz", 
				        index	: "cz",
				        label	: "操作",
				        width	: 40,
				        align	: "center",
				        fixed : true,
				        sortable: false,
				        formatter : function(value, opts, row) {
							return "<a href='javascript:deal(\"" + row.alarmId+"\");' title='' class='a-style'>处理</a>";
				        }
			        }
			],
			multiselect : true,
			rownumbers	: true,
			sortname : "alarmTime,alarmLevel,alarmType,status",
			sortorder : "desc,asc,desc,desc",
			pager: "#mainGridPager"
		});
		$("#mainGrid").addSearchForm("#search");
		$("#buttons").append("<input type=\"button\" id=\"btnCon\" class=\"bttn bicon-confirm\"  value=\"批量处理\"/>");
		$("#btnCon").click(function(){
			var ids = $("#mainGrid").getGridParam("selarrrow");
			if(ids==null||"" == ids){
				$.dialog.alert("请选择需要处理的报警!");
				return false;
			}
			$.dialog.confirm("确定处理所选的报警信息吗？", function(){
				$.fields.ajaxRequest({
					type:"POST",
					url:"${ctx}/data/alarm/todoDeal.action?ids="+ids,
					yes:function(){
						 $("#mainGrid").trigger("reloadGrid");
					}
				});
			});
		});
	});
	function deal(id) {
		var dataRow = $("#mainGrid").jqGrid("getRowData", id);
		$.container.popup({
			type: "row",
			items:[{
				read: "${ctx}/data/jzgl/dagl/read.action?id=" + dataRow.fxryId,
				fieldCols: 3,
				fieldCls:{
					labelCls : "form-td-label-90"
				},
				fields:[           
						{text: "姓名", type:"display",name: "name"},
						{text: "人员编号", type:"display",name: "code"},
						{type:"photo",name: "picture", rowspan:4, readonly:true},
						{text: "性别", type:"display",name: "sex", formatter:$.dictionary.formatter("XB", "不详")},
						{text: "民族", type:"display",name: "nation", formatter:$.dictionary.formatter("MZ", "不详")},
						{text: "身份证号", type:"display",name: "identityCard"},
						{text: "出生日期", type:"display",name: "birthdate"},
						{text: "负责矫正单位", type:"display",colspan:2,name: "responseOrg", formatter:$.fn.fmatter.organization},
				]
			},{
				title: "报警处理",
				save: "${ctx}/data/alarm/writeContent.action",
				read: "",
				fieldCols: 3,
				fieldCls:{
					labelCls : "form-td-label-90"
				},
				fields:[
					{type:"hidden",name: "id", value: id},//隐藏字段
					{text: "报警时间", type:"display", defValue:dataRow.alarmTime},
					{text: "报警级别", type:"display", name: "l", defValue:dataRow.alarmLevel},
					{text: "报警类型", type:"display", name: "t", defValue:dataRow.alarmType},
					{text: "记录内容", type:"textarea", colspan:3,name: "jlnr", required: true, maxlength:100}
				],
				yes: function(){
					$("#mainGrid").trigger("reloadGrid");
				}
			}]
		}, {
			okVal: "保存",
			width: "900px",
			height: "200px"
		});
	}
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：工作桌面 -> 报警信息</span>
		</div>
		<div class="container-top"></div>
		<div class="tabpage">
			<div class="container-middle">
				<div id="search"></div>
			</div>
			<div class="container-bottom">
				<div id="buttons" class="buttonArea operation"></div>
				<table id="mainGrid">
				</table>
				<div id="mainGridPager"></div>
			</div>
		</div>
	</div>
	<%@include file="/data/bottom.jsp"%>
<iframe id="downloadFrame" style="display:none"/>
</body>
</html>
