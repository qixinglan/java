<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.nci.dcs.common.utils.LoginInfoUtils"%>
<%	
	if (LoginInfoUtils.isCAS(session)){
		response.sendRedirect(LoginInfoUtils.getLoginUrl(session));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请登录</title>
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script type="text/JavaScript">
	function debug_login(username, pwd){
		$("#username").attr("value", username);
		$("#password").attr("value", pwd);
		$("#login").submit();
	}
	function smt(isCA) {
		if ($.trim($("#username").val()) == "") {
			$("#error").text("用户账号不能为空");
			return false;
		}
		if ($.trim($("#password").val()) == "") {
			$("#error").text("密码不能为空");
			return false;
		}
		var frm = $('#login');
		if (isCA) {
			frm.attr('action', "${ctx}/");
		}
		frm.submit();
	}
	function check() {
		if ($.trim($("#username").val()) == "") {
			$("#error").text("用户账号不能为空");
			return false;
		}
		if ($.trim($("#password").val()) == "") {
			$("#error").text("密码不能为空");
			return false;
		}
	}
</script>
<style type="text/css">
<!--
debug使用 -->#wrap {
	width: 803px;
	height: 463px;
	background: no-repeat;
	margin-left: -395px;
	margin-top: -231px;
	position: absolute;
	top: 50%;
	left: 50%;
}

#wrap h2 {
	width: 799px;
	height: 96px;
	margin: 27px 2px 0;
}

#logoin {
	width: 266px;
	height: 228px;
	position: absolute;
	right: 50%;
}

</style>
</head>
<body>
	<div id="debug">
		<div>开发登陆</div>
		<ul style="list-style:none;line-height:20px;">
			<li onclick="debug_login('system', 'bjsfj@1234');" class="debug">市局</li>
			<li onclick="debug_login('wangjinkuan', '11111111');" class="debug">平谷区县司法局</li>
			<li><ul style="list-style:none;color:blue;">
					<li onclick="debug_login('chensi', '11111111');" class="debug">马坊司法所</li>
					<li onclick="debug_login('yangzheng', '11111111');" class="debug">金海湖司法所</li>
			</ul></li>
			<li onclick="debug_login('guojingming', '11111111');" class="debug">石景山区司法局</li>
			<li><ul style="list-style:none;color:blue;">
					<li onclick="debug_login('hongjie', '11111111');" class="debug">八宝山司法所</li>
					<li onclick="debug_login('liping', '11111111');" class="debug">直属司法所</li>
			</ul></li>
			<li onclick="debug_login('liyuepei', '11111111');" class="debug">朝阳区司法局</li>
			<li><ul style="list-style:none;color:blue;">
					<li onclick="debug_login('qicong', '11111111');" class="debug">东坝司法所</li>
					<li onclick="debug_login('linyamin', '11111111');" class="debug">黑庄户司法所</li>
			</ul></li>
		</ul>
	</div>
	<input type="hidden" name="message" id="message" value="${message }" />
	<input type="hidden" name="ctx" id="ctx" value="${ctx }" />
	<form action="${ctx }/sys/user/login.action" id="login" method="post" onsubmit="return check();">
		<div id="wrap">
			<h2></h2>
			<div id="logoin">
				<h3></h3>
				<table style="width:266px;height:138px" cellpadding="0"
					cellspacing="0">
					<tr>
						<td nowrap="nowrap" colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<th width="35%">用户账号：</th>
						<td nowrap="nowrap" colspan="2" width="65%"><input
							class="inp" name="username" id="username" type="text" value="" /></td>
					</tr>
					<tr>
						<th width="35%">密<span
							style="font-family:'宋体',white-space:pre;">&nbsp;&nbsp;&nbsp;&nbsp;</span>码：
						</th>
						<td nowrap="nowrap" colspan="2" width="65%"><input
							class="inp" name="password" id="password" type="password"
							value="" /></td>
					</tr>
					<tr>
						<td nowrap="nowrap" colspan="3"><label id="error" class="error" style="color: red;">${message}</label></td>
					</tr>
				</table>
				<p>
					<input type="submit" value="登 录" class="btn2_off"
						onmouseover="this.className='btn2_on';"
						onmouseout="this.className='btn2_off';" />
				</p>
			</div>
		</div>
	</form>
</body>
</html>

