$(function(){
	if(parent!=null && parent.index!=null){
		parent.location.href="login.jsp";
	}
	if($("#flag").val() <=0) {
		$('.erro span').html("ƽ̨���������ݲ��ܵ�¼��");
		$('#username').attr("disabled","disabled");
	    $('#password').attr("disabled","disabled");
	}
	
	
	
	$('#code').val("");
	
	//�����½��ť
	$('#login').click(function(){
		login();
	})
	
	//�س��¼�
//	document.onkeydown=function(e){
//		var ev=document.all?window.event:e;
//		if(ev.keyCode==13){
//			login();
//		}
//	}
//	
})

// ������֤��ͼƬ
function changeImage() {
	$("#codeImage").attr("src", "jcaptcha?temp=" + (new Date().getTime().toString(36)));
	return false;
}

/**
 * ��¼
 */
function login(){
	//��֤�û���,�������֤���Ƿ�Ϊ��
	if($('#username').val()==""||$('#password').val()==""||$('#code').val()==""){
		$('.erro span').html("<a color='red'>������������Ϣ��</a>");
		return false;
	}
	
//	 if(!checkUsername()||!checkPassword()){
//		 $('.erro span').html("�û�������������");
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
							 $('.erro span').html("�û���������������������룡");
						 }
						 if(data.flag==1){
							 $('.erro span').html("��¼�ɹ���ҳ��������ת�����Ժ�");
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
							 $('.erro span').html("�û���������������������룡");
						 }
					 }
				 }
		   },
		   error: function(){
			   $('.erro span').html("�����쳣�����Ժ����ԡ�");
		   }
		});
}

function reggo() {
	location.href = 'reg.jsp?accountId='+$("#accountId").val()+"&callbackUrl="+$("#callbackUrl").val()+"&nickname="+window.encodeURI(window.encodeURI($("#nickname").val()))+"&platToken="+$("#platToken").val()+"&params="+window.encodeURI(window.encodeURI($("#params").val()));
}




/**
 * ��֤�˻����������Ƿ�Ϸ�
 * @returns {Boolean}
 */
function checkUsername(){
	var textReg = new RegExp(new RegExp("^[A-Za-z0-9]{2,20}$"));
	return textReg.test($('#username').val());
}

/**
 * ��֤���������Ƿ�Ϸ�
 * @returns {Boolean}
 */
function checkPassword(){
	var textReg = new RegExp(new RegExp("^[A-Za-z0-9]{6,14}$"));
	return textReg.test($('#password').val());
}