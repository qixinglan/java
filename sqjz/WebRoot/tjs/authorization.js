$(document).ready(function(){
	$(".siderbar_1").find("div").each(function() {
		var divId = $(this).attr("id");
		if(authis.indexOf(divId) == -1){
			$(this).hide();
		}else{
			$(this).show();
		}
		
	});
});
