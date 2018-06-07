var isExpanded = true;
var offsetWidth = 0;
var offsetHeight = 0;
	
function fold(context){
	if(isExpanded){
		isExpanded = false;
		$("#left").hide();
		if(offsetWidth == 0)
			offsetWidth = $("#left").width();
		$("#right").width($("#right").width()+offsetWidth);
		$("#arrow").attr("src",context+"/images/shensuo_2.gif");
	}else{
		isExpanded = true;
		$("#left").show();
		$("#right").width($("#right").width()-offsetWidth);
		$("#arrow").attr("src",context+"/images/shensuo_1.gif");
	}
}