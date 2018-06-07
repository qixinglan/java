<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">




<html>
<HEAD>
<TITLE>修改错误</TITLE>

<link href="../css/reg.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
var boole=false;
function checkUser(){

				var regexp=/^[a-z A-Z]{1}\w{3,15}$/;
				var value=$("#username").val();
				if(regexp.test(value)){
					$("#user_prompt").css("color","black");
					$("#user_prompt").html("用户名格式正确");
					return true;
					}
				else{
					$("#user_prompt").html("用户名由英文字母和数字组成的4-16位字符，以字母开头");
					$("#user_prompt").css("color","red");
					return false;
				}
				}
				
				var pwd;
				function checkPwd(){
				var regexp=/^\w{4,10}$/;
				var value=$("#pwd").val();
				if(regexp.test(value)){
					$("#pwd_prompt").css("color","black");
					$("#pwd_prompt").html("密码格式正确");
					pwd=value;
					return true;
					}
				else{
					$("#pwd_prompt").html("密码由英文字母和数字组成的4-10位字符");
					$("#pwd_prompt").css("color","red");
					return false;
				}
				}
				function checkRepwd(){
				var regexp=/^\w{4,10}$/;
				var value=$("#repwd").val();
				if(pwd==value){
					$("#repwd_prompt").css("color","black");
					$("#repwd_prompt").html("密码验证正确");
					return true;
					}
				else{
					$("#repwd_prompt").css("color","red");
					$("#repwd_prompt").html("密码验证错误");
					return false;
				}
				
				}
				function checkEmail(){
					var reg= /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
					var value=$("#email").val();
				if(reg.test(value)){
					$("#email_prompt").css("color","black");
					$("#email_prompt").html("邮箱格式正确");
					return true;
					}
				else{
					$("#email_prompt").css("color","red");
					$("#email_prompt").html("请填写正确邮箱");
					return false;
				}
				}
	
	
	function check(){
	if((checkEmail()&&checkUser()&&checkPwd()&&checkRepwd())){
	return true;
	}
	else{
	alert("请正确填写注册信息");
	return false;
	}
	
	}
	function codeRao(){
	var random=Math.random();
	document.getElementById('num').src = "../"+random+"/code.do";
	}
				
		
</script>
</HEAD>

<BODY>
	<div id="body">
		
			
			
		<h1 style="font-size:1.5em">用户信息更改</h1>
		
			
				<form method="post" action="http://127.0.0.1:8081/bookWeb/fixuser.do" onSubmit="return check();">			
					<table>						
						<tr>
							<td class="n_right"><a>邮箱</a></td>
							<td class="erroname"><input  id="email" value="" name="email" type="text" onblur="checkEmail()"/><br><span></span></td>
							<td id="email_prompt"></td>
						</tr>
						<tr>
							<td class="n_right"><a>用户名</a></td>
							<td class="erronick" ><input id="username"  name="username" type="text" onblur="checkUser()"/><br><span></span></td>
							<td id="user_prompt" style="color:red">用户名已被占用</td>
						</tr>
						<tr>
							<td class="n_right"><a>密码</a></td>
							<td class="erropass" ><input id="pwd" value="" name="pwd" type="password" onblur="checkPwd()"/><br><span></span></td>
							<td id="pwd_prompt">密码由英文字母和数字组成的4-10位字符</td>
						</tr>
						<tr>
							<td class="n_right"><a>确认密码</a></td>
							<td class="errorepass" ><input id="repwd" value="" type="password" onblur="checkRepwd()"/><br><span></span></td>
							<td id="repwd_prompt"></td>
						</tr>
						<tr>
							<td ><a>验证码</a></td>
							<td  ><input id="codema"  name="codema" type="text" /><br><span id="errorcodema"></span></td>
							<td><a href="javsscript:;" onclick="codeRao()">
							<img src="../code.do" id= "num"/>  <span style="font-size:15;color:red">看不清</span></a></td>
							
						</tr>						
					
					<tr>
					<td></td>
					<td><input type="submit" value="提交更改"/></td>
						
						</tr>
						</table>
						</form>			
				</div>	
									
		
	
</BODY>
</html>
