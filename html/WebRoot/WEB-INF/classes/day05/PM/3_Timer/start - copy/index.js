//Step1：定义一个可以跨多个方法调用的变量timer

//计算倒计时核心方法

//Step2:定义timerStart方法
	//Step2.1:使用window.setInterval方法，每1000毫秒执行一次classOver方法，计算一次结果，显示在网页上。实现持续更改倒计时的方法

//Step3:在页面加载后启动timerStart方法，定义同时启动定时器

//Step4:如果下课，定义一个方法stopTimer。其中使用window.clearInterval函数将变量timer停止即可。该方法被倒计时右侧的按钮调用，用来停止倒计时。


//Step5:模仿周期性定时器，定义一个全局的变量，timeOut。
//Step6:和周期性定时器相似，需要一个闹钟要执行的方法，名为myAlert。其中定义闹钟要提示的内容，最后还原按钮上的文本
//Step7:如果timeOut为undefined，说明还没有定义定时器，此时可以创建新定时器。
	//Step7.1:如果timeOut为undefined，说明还没有定义计时器，此时可以建立新定时器。使用setTimeout方法，设置5秒后执行myAlert弹出提示。
	//Step7.2:如果点击按钮时，timeOut不等于undefined，说明有计时器正在运行。用户的意图是要停止当前计时器。此时，调用clearTimeout方法，取消当前计时器。
var timer;
function classOver(){
	var now=new Date();
	var hour=17-now.getHours()-1;
	var minute=59-now.getMinutes();
	var second=59-now.getSeconds();
	var h1=document.getElementById("time");
	if(hour>=0)
		h1.innerHTML=hour+"小时"+minute+"分"+second+"秒";
	else{
		timer=clearInterval(timer);//停止周期性函数而且把timer置为undefined
	}
}
function timerStart(){
	timer=window.setInterval(classOver,1000);//周期性函数,返回该线程的线程号(是个数字）window.可省略
}
function stopTimer(btnobj){
	if(timer===undefined){
		timerStart();
		btnobj.value="停止";
	}else{
		timer=clearInterval(timer);//停止周期性函数而且把timer置为undefined
		btnobj.value="启动";
	}
}
var timeout;
function myAlert(){
	alert("该起床了");
	timeout=undefined;
}
function startAlert(btnobj){
	if(timeout===undefined){
		timeout=setTimeout(myAlert,5000);//一次性计时器，到时自动关闭,调函数时不带（）！！！！
		btnobj.value="5秒后响铃";
	}else{
			timeout=clearTimeout(timeout);
			btnobj.value="再睡会";
		}
}

window.addEventListener("load",timerStart(),false);