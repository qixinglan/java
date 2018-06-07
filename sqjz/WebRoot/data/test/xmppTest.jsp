<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备通讯测试</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		var btn = $('<input type="button" class="micon-expand"/>');
		btn.click(triggerCollapse);
		$(".container-form-title").append(btn);
	});
	function triggerCollapse() {
		var btn = this;
		var body = $('.container-form-body', $(btn).parent().parent());
		if (body.attr("collapse") == "true") {
			body.show();
			body.attr("collapse", "false");
			$(btn).removeClass("micon-collapsed");
			$(btn).addClass("micon-expand ");
		} else {
			body.hide();
			body.attr("collapse", "true");
			$(btn).removeClass("micon-expand");
			$(btn).addClass("micon-collapsed");
		}
	}
	function test(type) {
		document.getElementById("xmpp").action = "${ctx}/data/test/xmpp/input.action?type="
				+ type;
		$("#xmpp").ajaxSubmit();
	}
</script>
<style rel="stylesheet">
.comm-table th{
	width: 201px;
}
.comm-table td{
	width: 201px;
}
</style>
</head>
<body onload="">
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 设备通讯测试</span>
		</div>
		<ul class="tabpage-label-container">
		<a href="${ctx}/data/test/xmppTest.jsp"><li id="tab-zjry" class="tabpage-label tabpage-label-selected">设备通讯测试</li></a>
		<a href="${ctx}/data/test/zjzxqk.jsp"><li id="tab-zjry" class="tabpage-label">在线设备</li></a>
		</ul>
		<form id="xmpp" action="${ctx}/data/test/xmpp/input.action" method="post">
			<div class="container-top">
				<div class="container-form">
					<div class="container-form-title">
						<label class="container-form-title-text">设置联系电话</label>
					</div>
					<div class="container-form-body">
						<table class="comm-table">
							<tbody>
								<tr>
									<th>联系电话：</th>
									<td colspan="4"><input type="text" id="entity.telPhones"
										name="entity.telPhones" value="${entity.telPhones}" /></td>
									<td><input type="button" id="btnSave"
										class="bttn bicon-test" value="测试"
										onclick="javascript:test('1');" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container-form">
					<div class="container-form-title">
						<label class="container-form-title-text">主机相关测试</label>
					</div>
					<div class="container-form-body">
						<table class="comm-table">
							<tbody>
								<tr>
									<th>主机编号：</th>
									<td><input type="text" id="entity.zjnum"
										name="entity.zjnum" value="${entity.zjnum}" /></td>
									<th>腕表编号：</th>
									<td colspan="2"><input type="text" id="entity.wbnum"
										name="entity.wbnum" value="${entity.wbnum}" /></td>
									<td><input type="button" id="btnSave"
										class="bttn bicon-test" value="配对测试"
										onclick="javascript:test('2');" /></td>
								</tr>
								<tr>
									<td colspan=1" align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机关机"
										onclick="javascript:test('3');" /></td>
									<td colspan="1" align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机重启"
										onclick="javascript:test('19');" /></td>
									<td colspan="2" align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机停止工作"
										onclick="javascript:test('4');" /></td>
									<td colspan="2" align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机开始工作"
										onclick="javascript:test('5');" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container-form">
					<div class="container-form-title">
						<label class="container-form-title-text">自检测试</label>
					</div>
					<div class="container-form-body">
						<table class="comm-table">
							<tbody>
								<tr>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机容量测试"
										onclick="javascript:test('8');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机电量测试"
										onclick="javascript:test('9');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="腕表电量测试"
										onclick="javascript:test('10');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="CPU信息测试"
										onclick="javascript:test('11');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机内存测试"
										onclick="javascript:test('12');" /></td>
								</tr>
								<tr>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="震动测试"
										onclick="javascript:test('13');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="手机号测试"
										onclick="javascript:test('14');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="闭合状态测试"
										onclick="javascript:test('15');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主机工作情况"
										onclick="javascript:test('16');" /></td>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="腕表工作情况"
										onclick="javascript:test('17');" /></td>
								</tr>
								<tr>
									<td align="center"><input type="button" id="btnSave"
										class="bttn bicon-test" value="主动定位测试"
										onclick="javascript:test('18');" /></td>
									<td align="center" colspan="4"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container-form">
					<div class="container-form-title">
						<label class="container-form-title-text">静默时间测试</label>
					</div>
					<div class="container-form-body">
						<table class="comm-table">
							<tbody>
								<tr>
									<th>静默时间(分钟)：</th>
									<td colspan="4"><input type="text" id="entity.silent"
										name="entity.silent" value="${entity.silent}" /></td>
									<td><input type="button" id="btnSave"
										class="bttn bicon-test" value="测试"
										onclick="javascript:test('6');" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container-form">
					<div class="container-form-title">
						<label class="container-form-title-text">低电测试</label>
					</div>
					<div class="container-form-body">
						<table class="comm-table">
							<tbody>
								<tr>
									<th>电压阀值(%)：</th>
									<td colspan="4"><input type="text" id="entity.voltage"
										name="entity.voltage" value="${entity.voltage}" /></td>
									<td><input type="button" id="btnSave"
										class="bttn bicon-test" value="测试"
										onclick="javascript:test('7');" /></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>