<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
        <script type="text/javascript">
        function login(){
        $("#admin_code_msg").text("");
        $("#password_msg").text("");
        $("#code_msg").text("");
        $("#loginmsg").text("");
        var admin_code=$("#admin_code").val();
        var password=$("#password").val();
        var code=$("#code").val();
        var reg=/^\w{1,30}$/;
        if(!reg.test(admin_code)){
        	$("#admin_code_msg").text("30长度的字母、数字和下划线");
        	return;
        }
        if(!reg.test(password)){
        	$("#password_msg").text("30长度的字母、数字和下划线");
        	return;
        }
        if(code==""){
	       	$("#code_msg").text("验证码不能为空");
	       	return;
        }
        $.post(
        "adminlogin.do",
        $("#admininfo").serialize(),
        function(date){
        	if(date=="1"){
        		$("#loginmsg").text("用户名错误");
        	}else if(date=="2"){
        		$("#loginmsg").text("密码错误");
        	}else if(date=="3"){
        		$("#loginmsg").text("验证码错误");
        	}else{
        	location.href="loginSuccess.do";
        	}
        }
        );
        }
        function changeCode(a){
        a.src="createImage.do?time="+new Date().getTime();
        }
		</script>
    </head>
    <body class="index">
        <div class="login_box">
        <form id="admininfo">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input  id="admin_code" type="text" class="width150" name="admin_code"/></td>
                    <td class="login_error_info"><span class="required" id="admin_code_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input  id="password" type="password" class="width150" name="password"/></td>
                    <td><span class="required" id="password_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input  id="code" type="text" class="width70" name="code"/></td>
                    <td><img src="createImage.do" alt="验证码" title="点击更换" onclick="changeCode(this)"/></td>  
                    <td><span class="required" id="code_msg"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:;login();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id="loginmsg"></span></td>                
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>
