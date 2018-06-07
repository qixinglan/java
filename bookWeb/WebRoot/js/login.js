$(function(){
	if(parent!=null && parent.index!=null){
		parent.location.href="login.jsp";
	}
	if($("#flag").val() <=0) {
		$('.erro span').html("平台参数有误，暂不能登录！");
		$('#username').attr("disabled","disabled");
	    $('#password').attr("disabled","disabled");
	}
	
	
	
	$('#code').val("");
	
	//点击登陆按钮
	$('#login').click(function(){
		login();
	})
	
	//回车事件
//	document.onkeydown=function(e){
//		var ev=document.all?window.event:e;
//		if(ev.keyCode==13){
//			login();
//		}
//	}
//	
})

// 更换验证码图片
function changeImage() {
	$("#codeImage").attr("src", "jcaptcha?temp=" + (new Date().getTime().toString(36)));
	return false;
}

/**
 * 登录
 */
function login(){
	//验证用户名,密码和验证码是否为空
	if($('#username').val()==""||$('#password').val()==""||$('#code').val()==""){
		$('.erro span').html("<a color='red'>请输入完整信息！</a>");
		return false;
	}
	
//	 if(!checkUsername()||!checkPassword()){
//		 $('.erro span').html("用户名或密码有误");
//		 return false;
//	 }	 
	var type = 0;
	if($("#all1").attr("checked")=="checked"||$("#all1").attr("checked")==true) {
		type = 1;
	}
	if($("#all2").attr("checked")=="checked"||$("#all2").attr("checked")==true) {
		if(type == 1) {
			type = 2;
		} else {
			type = 3;
		}
	}
	$.ajax({
		   type: "POST",
		   url: "login.go",
		   data: {
				username: window.encodeURI($('#username').val()),
				password: $('#password').val(),
				nickname: window.encodeURI($('#nickname').val()),
				platToken: $('#platToken').val(),
				accountId: $('#accountId').val(),
				power:type
		   },
		   success: function(data){
			   if(data!=null&&data!=""){
				   data = eval("("+ data +")");
					 if(data.flag != null) {
						 if(data.flag==0){
							 $('.erro span').html("用户名或密码错误，请重新输入！");
						 }
						 if(data.flag==1){
							 $('.erro span').html("登录成功，页面正在跳转，请稍候。");
							 var accessSecret = data.accessSecret;
							 var msg = "";
							 if(type == 1) {
								 
							 } else if(type == 2) {
								 msg+= "&nickname="+window.encodeURI(window.encodeURI(data.nickname));
							 } else if(type ==3) {
								 msg+= "&nickname="+window.encodeURI(window.encodeURI(data.nickname));
							 }
							 location.href=$("#callbackUrl").val()+"?params="+window.encodeURI(window.encodeURI($("#params").val()))+"&accessSecret="+accessSecret+msg;
						 }else{							
							 $('.erro span').html("用户名或密码错误，请重新输入！");
						 }
					 }
				 }
		   },
		   error: function(){
			   $('.erro span').html("网络异常，请稍候重试。");
		   }
		});
}

function reggo() {
	location.href = 'reg.jsp?accountId='+$("#accountId").val()+"&callbackUrl="+$("#callbackUrl").val()+"&nickname="+window.encodeURI(window.encodeURI($("#nickname").val()))+"&platToken="+$("#platToken").val()+"&params="+window.encodeURI(window.encodeURI($("#params").val()));
}




/**
 * 验证账户名字输入是否合法
 * @returns {Boolean}
 */
function checkUsername(){
	var textReg = new RegExp(new RegExp("^[A-Za-z0-9]{2,20}$"));
	return textReg.test($('#username').val());
}

/**
 * 验证密码输入是否合法
 * @returns {Boolean}
 */
function checkPassword(){
	var textReg = new RegExp(new RegExp("^[A-Za-z0-9]{6,14}$"));
	return textReg.test($('#password').val());
}