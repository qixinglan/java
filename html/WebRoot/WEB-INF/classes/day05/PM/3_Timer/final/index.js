//Step1：定义一个可以跨多个方法调用的变量timer
var timer;
//计算倒计时核心方法
var classOver = function(){
	var h1=document.getElementById("time");
	var now = new Date();
	var hour = 23-now.getHours();
	var minutes = 59-now.getMinutes();
	var seconds = 59-now.getSeconds();
	if(hour>=0)
		h1.innerHTML=hour+"小时"+minutes+"分"+seconds+"秒";
	else{
		timer = window.clearInterval(timer);
		h1.innerHTML="祝大家儿童节快乐";
	}
}
//Step2:定义timerStart方法
	//Step2.1:使用window.setInterval方法，每1000毫秒执行一次classOver方法，计算一次结果，显示在网页上。实现持续更改倒计时的方法
var timerStart = function(){
	timer = window.setInterval(classOver,1000);
}
//Step3:在页面加载后启动timerStart方法，定义同时启动定时器
window.addEventListener('load',timerStart,false);
//Step4:如果买到票，定义一个方法stopTimer。其中使用window.clearInterval函数将变量timer停止即可。该方法被倒计时右侧的按钮调用，用来停止倒计时。
var stopTimer = function(btnObj){
	if(timer === undefined){
		timer = window.setInterval(classOver,1000);
		btnObj.value="下课！";
	}
	else{
		timer = window.clearInterval(timer);
		btnObj.value="启动倒计时";
	}
}

//Step5:模仿周期性定时器，定义一个全局的变量，timeOut。
//Step6:和周期性定时器相似，需要一个闹钟要执行的方法，名为myAlert。其中定义闹钟要提示的内容，最后还原按钮上的文本
//Step7:如果timeOut为undefined，说明还没有定义定时器，此时可以创建新定时器。
	//Step7.1:如果timeOut为undefined，说明还没有定义计时器，此时可以建立新定时器。使用setTimeout方法，设置5秒后执行myAlert弹出提示。
	//Step7.2:如果点击按钮时，timeOut不等于undefined，说明有计时器正在运行。用户的意图是要停止当前计时器。此时，调用clearTimeout方法，取消当前计时器。
var timeOut;
var myAlert = function(){ 
	alert("该起床了！");
	timeOut=undefined;
	document.getElementById("btnTimeOut").value="再睡5秒！就5秒！";
}

function startAlert(btnObj){
	if(timeOut === undefined){
		btnObj.value = "我睡醒了！";	
		timeOut = window.setTimeout(myAlert	,5000);
	}
	else{
		timeOut = window.clearTimeout(timeOut);
		btnObj.value = "再睡5秒！就5秒！";
	}
}

