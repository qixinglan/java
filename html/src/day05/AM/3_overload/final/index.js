/*正确重载方法*/
//Step1：定义myMethod方法，不要参数列表
//Step2：在方法中，首先判断arguments数组的length属性，确定传入参数的个数。
	//如果length==1，则说明点的是一个参数的按钮。
		//使用arguments[0]的方式取出传入的参数，使用parseInt函数，将参数转为整数类型，保存在变量n中。
		//计算n的平方
	//如果length==2，则说明点的是两个参数的按钮
		//使用arguments[index]的方式，依次取出传入的两个参数，分别保存在n和m中。
		//求和
		//打印结果
function myMethod() {	
	if (arguments.length == 1) {	
		var n = parseInt(arguments[0]); 		
		alert(n + " 的平方为：" + n * n);
	}
	else if (arguments.length == 2) {
		var n = parseInt(arguments[0]); 
		var m = parseInt(arguments[1]);
		var result = n + m;
		alert(n + " 与 " + m + " 的和为：" + result);
	}
}