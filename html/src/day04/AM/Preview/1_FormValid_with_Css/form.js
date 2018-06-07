function inputFocus(txtObj, divID) {
	//修改当前文本框的样式
	txtObj.className = "txt_focus";
	//获得提示信息框的对象
	var divObj = document.getElementById(divID);
	//修改提示信息框的display属性和className属性。
	divObj.style.display = "block";
	//每次重新获得焦点时，都要讲提示信息框的内容还原回初始文本。
	switch(divID){
	
		case "userName_msg":
			divObj.innerHTML = "10个字符以内的字母、数字和下划线的组合";
			break;
		case "password_msg":
			divObj.innerHTML = "6位数字";
			break;
	}
	//以普通样式显示提示信息
	divObj.className = "";
}
function validateUserName(txtObj,divID){
	//这里验证表达式不变
	var reg = /^[a-zA-Z0-9_]{1,10}$/;
	//由于参数变化，test验证的内容，保存在了txtObj文本框对象的value属性中
	var isRight = reg.test(txtObj.value);
	//有要动态操作提示信息框的样式，所以，这里通过ID方式获得提示框对象
	var divObj = document.getElementById(divID);
	//如果验证通过
	if (isRight) {
		//修改提示框的display属性为block，使其显示出来。
		divObj.style.display = "block";
		//按照需求，修改正确，不再需要文字提示，只显示绿色对勾即可，所以这里去掉对象的innerHTML节点的内容。为了保证背景可以显示，这里使用一个空格占位
		divObj.innerHTML = "";
		//将提示信息框的样式更换为在CSS中专门定义的验证成功的背景图片。
		divObj.className = "vali_success";
	}
	//否则
	else{
		//修改提示框的display属性为block，使其显示出来。
		divObj.style.display = "block";
		//将提示信息框的样式更换为在CSS中专门定义的验证成功的背景图片。
		divObj.className = "vali_fail";
	}
	//验证完，说明焦点移开了，就要还原文本框的最初样式
	txtObj.className = "";
}
function validateUserPwd(txtObj,divID) {
	var reg = /^\d{6}$/;
	var isRight = reg.test(txtObj.value);
	//有要动态操作提示信息框的样式，所以，这里通过ID方式获得提示框对象
	var divObj = document.getElementById(divID);
	//如果验证通过
	if (isRight) {
		//修改提示框的display属性为block，使其显示出来。
		divObj.style.display = "block";
		//按照需求，修改正确，不再需要文字提示，只显示绿色对勾即可，所以这里去掉对象的innerHTML节点的内容。为了保证背景可以显示，这里使用一个空格占位
		divObj.innerHTML = "&nbsp;";
		//将提示信息框的样式更换为在CSS中专门定义的验证成功的背景图片。
		divObj.className = "vali_success";
	}
	//否则
	else{
		//修改提示框的display属性为block，使其显示出来。
		divObj.style.display = "block";
		//将提示信息框的样式更换为在CSS中专门定义的验证成功的背景图片。
		divObj.className = "vali_fail";
	}
	//验证完，说明焦点移开了，就要还原文本框的最初样式
	txtObj.className = "";
}