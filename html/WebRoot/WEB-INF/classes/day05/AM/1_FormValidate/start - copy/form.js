function validateUserName(name){
	//Step1：定义正则表达式对象匹配1-10个字母、数字或下划线的组合
	//Step2：使用正则表达式对象的test方法，判断传入的name参数是否匹配
	//Step3：如果不匹配，提示用户正确的规则
	var reg=/[a-z A-Z 0-9_]{1,10}/;
	if(!reg.test(name)){
		alert("10个字符以内的字母、数字和下划线的组合");
	}
	

}

function validatePwd(pwd) {
	//Step1：定义正则表达式对象匹配6个数字的要求
	//Step2：使用正则表达式对象的test方法，判断传入的pwd参数是否匹配。
	//Step3：如果不匹配，提示用户正确的规则
	var reg=/\d{6}/;
		if(!reg.test(name)){
			alert("6位数字");
		}

}