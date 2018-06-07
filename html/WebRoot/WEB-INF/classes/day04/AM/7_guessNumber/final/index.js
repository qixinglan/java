//Step2:定义一个guessNumber方法，为guessNumber()方法定义一个参数str，用于自动将当前文本框的值传递到方法中。
//Step3：给定一个变量作为比较的对象，值为10。
//Step4：由于isNaN对空字符串不做判断直接通过，所以先使用length属性判断用户是否录入内容。
	//如果录入了，才继续执行。
		//Step5：使用isNaN判断录入的内容str是不是数字。
			//如果不是，提示“请录入数值”
			//如果是，开始比较
				//Step5.1：使用parseFloat将录入的内容转化为浮点数，保存在变量data中
				//Step5.2：用data和result进行比较
					//如果相等，提示“猜对了”
					//如果大于，提示“大了”
					//否则，提示“小了”
function guessNumber(str) {	
	var result = 10;
	if(str.trim().length > 0)
		if (isNaN(str)) alert("请录入数值");
		else {
			var data = parseFloat(str);
			data > result ? alert("大了") : data < result ? alert("小了") : alert("猜对了");
		}
}