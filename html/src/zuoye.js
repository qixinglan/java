var array=["rose","daffodil","apple", "daisy"];
 var index=1;
function rotate(){
	var img=document.getElementById("img");
	img.src="images/"+array[index]+".jpg";
	if(index==array.length-1)
		index=0;
	else
		index++;
}
var timer;
function start(){
	timer=setInterval(rotate, 2000);
}

function stop(){
	timer=clearInterval(timer);
}

//打开页面直接加载

//window.onload=function(){start()};
window.addEventListener("load",start,false);
function picture(index){
	stop();
	var img=document.getElementById("img");
	img.src="images/"+array[index-1]+".jpg";
	
}
