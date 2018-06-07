$(document).ready(function(){
	$(".btn_fanhui").click(function (){
		var url = window.parent.location.href;
		var arr = url.split("/");
		if(arr.length > 0){
			url = arr[0] + "//" + arr[2] + "/" + arr[3];
		}
		window.parent.location.href=url;
	})
	$("#shouye").click(function (){
		var url = window.parent.location.href;
		var arr = url.split("/");
		if(arr.length > 0){
			url = arr[0] + "//" + arr[2] + "/" + arr[3];
		}
		window.parent.location.href=url;
	})
	$("#shouye2").click(function (){
		var url = window.parent.location.href;
		var arr = url.split("/");
		if(arr.length > 0){
			url = arr[0] + "//" + arr[2] + "/" + arr[3];
		}
		window.parent.location.href=url;
	})
	$(".bg_jg").val(function(){
		if(this.value == null || TrimBoth(this.value) == ""){
			$(".bg_jg").addClass("gray");
			return "点击选择单位..";
		}else{
			if(this.value == "点击选择单位.."){
				$(".bg_jg").addClass("gray");
			}
			return this.value;
		}
	});
	$(".bg_aj").val(function(){
		if(this.value == null || TrimBoth(this.value) == ""){
			$(".bg_aj").addClass("gray");
			return "点击选择案件..";
		}else{
			if(this.value == "点击选择案件.."){
				$(".bg_aj").addClass("gray");
			}
			return this.value;
		}
	});
});