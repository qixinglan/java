//Step1:定义calculate方法用于执行用户点击每个按钮时的功能。定义一个参数str接收用户点击的按钮的值
//Step2:<body>中，为每个按钮添加onclick事件，使其执行calculate方法。并且每个按钮都把自己的value，作为参数传递给calculate方法。
//Step3:使用switch，将按键分成三种情况：按c时，清空文本框内容；按等号时，计算文本框中的表达式结果并显示在文本框中；其余按键不进行任何计算，直接将所按内容追加到文本框内容的末尾。
		//第一种情况：按C时
			//Step3.1:清空文本框
		//第二种情况：按=时
			//Step3.2:获得文本框中的内容。如果内容不为空，则使用eval函数，计算当前文本框中的表达式的值。结果显示回文本框中。
			//如果内容不为空
				//Step3.2.1:由于输入内容错误，eval会抛出异常，所以这里需要引入异常处理，使用try catch(error)。其中try包裹住正常的计算
				//正常逻辑是使用eval函数，计算当前文本框中的表达式的结果，放在变量result中
				//将result显示到文本框中
				//如果捕获到异常，将error显示到文本框中
	    //默认情况下：
			//Step3.3:如果按的是普通按钮，则将所按内容使用+=的方式追加到文本框内容的结尾。
function calculate(str){	
	var txtObj = document.getElementById("txtData");
	var input=txtObj.value;
	switch(str){		
		case "C":			
			txtObj.value = "";
			break;		
		case "=":
			if(input.trim().length>0)
				try{
					var result = eval(input);
					txtObj.value = result;
				}catch(error){
					txtObj.value = error;
				}
			break;
		default:
			txtObj.value += str;
			break;						
	}	
}
