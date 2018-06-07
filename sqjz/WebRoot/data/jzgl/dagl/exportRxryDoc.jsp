<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
	String fxry_Id = request.getParameter("fxry_Id");
	String frxry_name = request.getParameter("frxry_name");
	if (fxry_Id == null || fxry_Id.isEmpty()) {
		response.sendError(404);
	}
	if (frxry_name == null || frxry_name.isEmpty()) {
		frxry_name = "";
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服刑人员信息管理-社区服刑人员档案</title>
<%@ include file="/common/commonjs.jsp"%>
<link rel="stylesheet" href="style.css" />
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"jzgl"});
		//checkbox实现单选效果
		$(":checkbox[name=docExport]").each(function() {
		$(this).click(function(){
		if($(this).attr('checked')){
		$(":checkbox[name=docExport]").removeAttr('checked');
		$(this).attr('checked','checked');
		}
		});
		});
		//全选
		$("#chkAll").click(function() {
			var chkedFlg = $(this).attr("checked");
			$("input:checkbox[name!=chkAll]").each(function() {
				if (!$(this).attr("disabled")) {
					if (chkedFlg) {
						$(this).attr("checked", "true");
					} else {
						$(this).removeAttr("checked");
					}
				}
			});
		});
		//收起和展开								
		$("#ajg").click(function() {
			if ($("#divjg").attr("class") == "spanTurnOff") {
				$("#divjg").removeClass("spanTurnOff");
				$("#divjg").addClass("spanTurnOn");
				$("#ajg").text("\u5c55\u5f00");
				$("#jg").hide();
			} else {
				$("#divjg").removeClass("spanTurnOn");
				$("#divjg").addClass("spanTurnOff");
				$("#ajg").text("\u6536\u8d77");
				$("#jg").show();
			}
		});
	});
	//导出
	function openDoc() {
		var checkedResult = "";
		var fxry_Id="<%=fxry_Id%>";
		var frxry_name="<%=frxry_name%>";
		$('input[name="docExport"]:checked').each(function() {
			var result = $(this).val();
			checkedResult = result;
		});
		checkedResult = $.trim(checkedResult);
		checkedResult = checkedResult.split(",");
		if (checkedResult == "" || checkedResult.length < 1) {
			$.dialog.alert("请选择需要导出的数据！");
			return false;
		}
		//服刑人员档案编号
		var fxry_dabh = checkedResult[0];
		var url = "${ctx}/data/jzgl/dagl/doc.action?fxry_Id=" + fxry_Id
				+ "&fxry_dabh=" + fxry_dabh;
		$("#downloadFrame").attr("src", url);

	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：矫正管理 -> 社区服刑人员档案管理 -> 档案导出
			</span>
		</div>
		<div class="container-top">
			<div class="contentWrapper">
				<div class="operation-box container-main">
					<!-- 
					<div class="title">
						<span></span> <label><input type="checkbox" id="chkAll"
							name="chkAll" />&nbsp;全选</label>
						<div id="divjg" class="spanTurnOff"
							style="float: right;margin-right: 20px;">
							<a id="ajg">收起</a>
						</div>
					</div>
					 -->
					<div class="comm-div" id="jg">

						<table class="comm-doc">
							<tbody>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="3" />3居住地核实的复函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="6" />6年度社区矫正人员报到情况登记表</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="7" />7社区矫正人员基本信息表</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="8" />8社区矫正人员报到告知书</label></th>
								</tr>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="9" />9社区矫正人员未按时报到通报函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="11" />11社区矫正宣告书</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="12" />12人户分离社区矫正人员通报函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="13" />13社区矫正责任书</label></th>
								</tr>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="14" />14社区矫正工作记录</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="15" />15社区矫正人员矫正方案</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="16" />16社区矫正人员外出审批表</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="17" />17区矫正人员外出通报函</label></th>
								</tr>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="18" />18外出社区矫正人员监管委托书</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="19" />19暂住地司法所对社区矫正人员矫正情况通报函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="20" />20社区矫正人员居住地变更审批表</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="21" />21社区矫正人员居住地变更征求意见函</label></th>
								</tr>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="22" />22社区矫正人员居住地变更的复函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="23" />23社区矫正人员居住地变更通知书</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="24" />24社区矫正人员进入特定区域（场所）审批表</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="25" />25询问（谈话）笔录</label></th>
								</tr>
								<tr>
									<th><label><input type="checkbox" name="docExport"
											value="26" />26社区矫正人员脱管通报函</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="29" />29暂时中止社区矫正通知书</label></th>
									<th><label><input type="checkbox" name="docExport"
											value="30" />30社区矫正警告审批表</label></th>
									<th><label></label></th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" id="btnExcel" class="bttn bicon-excel"
				value="导出" onclick="openDoc()" />
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<iframe id="downloadFrame" style="display:none" />
</body>
</html>
