function validateUserName(name){
	//定义正则表达式对象匹配1-10个字母、数字或下划线的组合
	var reg = /^[a-zA-Z0-9_]{1,10}$/;
	//使用正则表达式对象的test方法，判断传入的name参数是否匹配。
	var isRight = reg.test(name);
	//如果不匹配，提示用户正确的规则
	if (!isRight) alert("姓名要求是10个字符以内的字母、数字和下划线的组合");
}

function validatePwd(pwd) {
	//定义正则表达式对象匹配6个数字的要求
	var reg = /^\d{6}$/;
	//使用正则表达式对象的test方法，判断传入的pwd参数是否匹配。
	var isRight = reg.test(pwd);
	//如果不匹配，提示用户正确的规则
	if (!isRight) alert("密码要求6位数字！");
}