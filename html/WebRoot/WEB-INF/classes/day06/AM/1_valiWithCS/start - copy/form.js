//Step1:在script节点中增加方法inputFocus，该方法需要实现如下功能：
//Step1.1:修改文本框的className属性为txt_focus。
//Step1.2:修改文本框旁边的提示信息栏的display属性为block使其显示出来。
//Step1.3:将提示框的className属性设置为""，表示清除样式。

//该方法需要传入两个参数：
//1、当前获得焦点的文本框是谁？我们将这个参数起名为txtObj
//2、与当前文本框一起要显示出来的提示信息是谁？我们将这个参数起名为divID
//要实现上边三个功能，必须知道当前文本框旁边的提示框是谁，这就需要为所有的提示信息指定自己唯一的id，方便查找。我们顺便分别将其id定义为userName_msg ，password_msg

//Step2:为文本框指定onfocus事件，定义当文本框获得焦点时执行inputFocus方法。其中两个参数分别是当前文本框自己——this和要显示的提示框div的名字.
		
//Step3:修改验证姓名的方法：和inputFocus方法类似，验证方法同样需要两个参数，一个是当前文本框自身——this，另一个是要显示的提示框的id，我们需要用程序动态改变提示框的样式和内容。
function validateUserName(txtObj,divID){
	txtObj.className="";
	//这里验证表达式不变
	var reg = /^[a-zA-Z0-9_]{1,10}$/;
	//由于参数变化，test验证的内容，保存在了txtObj文本框对象的value属性中
	var isRight = reg.test(txtObj.value);
	var div=document.getElementById(divID);
	//如果验证通过
	if(!isRight){		
		div.className="vali_fail";
	}else{
		div.className="vali_success";
		div.innerHTML="&nbsp;";
	}
	return isRight;
}

//Step4:为submit按钮指定单击事件处理方法，名为validateDatas()。我们希望如果有一项验证没通过，则整个页面取消提交。这个必须由单击事件的返回值来确定。如果单击事件返回false，则整个表单不提交，会停留在错误页面。如果单击事件返回true，则表单继续提交。所以，我们这里还要在validateDatas方法前面加上return，以便让onclick事件可以根据验证方法的返回值决定取消提交，还是继续提交。
//Step5:新增方法validateDatas()，验证文本框的输入是否正确。
function inputFocus(txtObj,divID){
	txtObj.className ="txt_focus";
	//去掉隐身
	var div=document.getElementById(divID);
	div.className="";
	if(divID=="userName_msg"){
		div.innerHTML="10个字符以内的字母、数字和下划线的组合";	
		}
	else{
		div.innerHTML="6位数字";
	}
}
function validatePwd(txtObj,divID){
	txtObj.className="";
	var reg=/^\d{6}$/;
	var isRight=reg.test(txtObj.value);
	var div=document.getElementById(divID);
	//如果验证通过
	if(!isRight){
		div.className="vali_fail";
		
	}else{
		div.className="vali_success";
		div.innerHTML="&nbsp;";
	}
	return isRight;
}
function validate(){
	var nameobj=document.getElementById("userName");
	var pwdobj=document.getElementById("pwd");
	var rname=validateUserName(nameobj, "userName_msg");
	var rpwd=validatePwd(pwdobj, "pwd_msg");
	return rname && rpwd;
}