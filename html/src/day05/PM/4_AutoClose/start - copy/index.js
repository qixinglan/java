function aFunc(){
	var h1=document.getElementById("h1");
	var n=parseInt(h1.innerHTML);//获得5
	h1.innerHTML=(n-1)+"s后自动关机";

	
}
function closeFun(){
	window.close();
}
var time;
var timeout;
function closeWait(){
	time=setInterval(aFunc, 1000);
	timeout=setTimeout(closeFun,5000);
}
window.addEventListener("load",closeWait,false);
function cancel(){
	clearInterval(time);
	clearTimeout(timeout);
}