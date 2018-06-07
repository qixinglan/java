//得到随机数
function getRandomNumber() {
	var strmin=document.getElementById("txtMin").value;
	var strmax=document.getElementById("txtMax").value;
	if(strmin.trim().length>0&&strmax.trim().length>0)
		if(!isNaN(strmin) && !isNaN(strmax)){
			var min=parseInt(strmin);
			var max=parseInt(strmax);
			if(max>min){
				//var r=min+(max-min)*Math.random();
				//alert(Math.round(r));
				alert(Math.round(min+(max-min)*Math.random()));
			}
			
		}
}