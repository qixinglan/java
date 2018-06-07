function getInt(){
	//Step1:获得id为txtData文本框：使用网页的getElementById("txtData")
	//Step2:文本框的.value属性可以拿到文本框中录入的内容。
	/*Step3:执行逻辑：
		3.1使用isNaN判断用户录入的是不是数字
			如果不是数字，提示用户“请录入数值类型”
			如果是数字，使用parseInt，取录入内容的整数部分*/
	var txtObj = document.getElementById("txtData");
	var str = txtObj.value;
	if(isNaN(str)) alert("请录入数值类型");
	else{ var data = parseInt(str); alert("整数部分为：" + data);}
}
function getSquare() {
	//Step1：找元素	
	//Step2：拿内容	
	/*Step3：做处理
		3.1使用isNaN判断用户录入的是不是数字
			如果不是数字，提示用户“请录入数值类型”
			如果是数字，使用parseFloat转换成浮点数，然后计算平方，显示结果*/
	var txtObj = document.getElementById("txtData");
	var str = txtObj.value;
	if (isNaN(str))  alert("请录入数值类型");
	else { 
		var data = parseFloat(str);  
		var result = data * data;  
		alert(result); 
	}
}