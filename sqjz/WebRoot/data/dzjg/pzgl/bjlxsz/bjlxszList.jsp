<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报警类型设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="报警类型设置">
<%@include file="/common/commonjs.jsp"%>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 配置管理 -> 报警类型设置</span>
		</div>
		<!--列表-->
        <div id="search"></div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
    <%@include file="/data/bottom.jsp"%>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "bjlxsz",});
			//表格初始化
			$("#mainGrid").jqGrid(
							{
								url : '${ctx}/data/dzjg/bjlxsz/search.action',
								colNames : ['', '报警级别', '报警类型', '状态', '操作' ],
								colModel : [										
										{
											name : 'id',
											index : 'id',
											hidden  : true                                 
										},{
											name : 'alarmLevel',
											index : 'alarmLevel',
											width : 40,
											align : 'center',
											searchType:  'eq',
	                                        formatoptions :{code:'BJJB'},
	                                        formatter:'dictionary',
	                                        sortable: true	                                       
										},{
											name : 'alarmType',
											index : 'alarmType',
											width : 40,
											align : 'left',
											searchType:'eq',
											formatoptions:{code:'BJLX'},
	                                        formatter : 'dictionary',
	                                        sortable: false
										},{									
											name : 'status',
											index : 'status',
											width : 40,
											formatter :'dictionary',
											formatoptions:{code:'BJLXZT'},
											searchType:'eq',
											align : 'center'
										},{										
											name : 'cz',
											index : 'cz',
											width : 120,
											align : 'center',
											fixed : true,
											sortable : false,
											formatter : function(value, opts,rwdat) {
												var id = rwdat.id;											
												var str = "<a onClick='view("+ id+")' title='' class='a-style'>查看</a>";
												str += "<a onClick='edit("+id+ ")' title='' class='a-style'>设置</a>";
												return str;
											}
										} ],
								multiselect : false,
								pager : '#mainGridPager',
								sortorder : "asc",
								sortname:'alarmLevel'
							});
			                    $("#mainGrid").addSearchForm("#search",{rows:"2"});
			                    $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		});
		function edit(id) {				
			$.dialog({
				id : 'testID',
				width : '800px',
				title : '报警类型设置',
				content : 'url:${ctx }/data/dzjg/pzgl/bjlxsz/bjlxszEdit.jsp?id='+id,
				ok : function() {
					
			    	var form =$(this).attr("iframe").contentDocument.forms[0];
					form = $(form);
					$($(this).attr("iframe").contentDocument.getElementById("alertMsg")).text("");
					$($(this).attr("iframe").contentDocument.getElementById("alarmMsg")).text("");
					if (!$(this).attr("iframe").contentWindow.$.fields.validateForm(form)){
						return false;
					}
					if($($(this).attr("iframe").contentDocument.getElementById("alertContent")).val().length > 100){
						$($(this).attr("iframe").contentDocument.getElementById("alertMsg")).text("字数不能超过100");
						return false;
					}
					if($($(this).attr("iframe").contentDocument.getElementById("alarmContent")).val().length > 100){
						$($(this).attr("iframe").contentDocument.getElementById("alarmMsg")).text("字数不能超过100");
						return false;
					}
					var ajax_option = {
							url:form.attr("action"),
							success:function(success){
								$("#mainGrid").trigger("reloadGrid");
							}
					}
					form.ajaxSubmit(ajax_option);
				},
				okVal : '保存',
				cancelVal : '取消',
				cancel : true
			});
		}
		function view(id) {
			$.dialog({
				id : 'testIDView',
				width : '530px',
				title : '报警类型查看',
				content : 'url:${ctx }/data/dzjg/pzgl/bjlxsz/bjlxszView.jsp?id='+id,
				cancelVal : '关闭',
				cancel : true
			});
		}
	</script>
</body>
</html>
