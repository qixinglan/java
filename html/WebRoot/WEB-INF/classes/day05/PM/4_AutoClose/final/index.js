var timer;
var timera;

var closeFunc = function(){
	window.open('','_parent','');
	window.close();
}
var aFunc=function(){
	var a=document.getElementById("h1");
	var n=parseInt(a.innerHTML);
	a.innerHTML=(n-1)+"s后自动关闭";
}
function closeWait() {
	timera = window.setInterval(aFunc,1000);
	timer = window.setTimeout(closeFunc,5000); 
}

window.addEventListener('load',closeWait,false);

//取消定时跳转
function cancel() {
    window.clearTimeout(timer);
	window.clearInterval(timera);
	document.getElementById("h1").innerHTML="留在此页";
}
