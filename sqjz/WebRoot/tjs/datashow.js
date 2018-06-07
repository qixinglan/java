function loadLeaderInfo(){
	var url = "";
	if(i < len){	//加载领导信息
		queryUrl =  "/zhsjfw/zhxx/tsDetail.action?id="+arrIds[i];
		i++;
	}else if(i == len){	//加载动态信息
		i=0;
		if(window.opener.document.getElementById("armyinfo").checked){
			queryUrl =  "/zhsjfw/army/showZfzq.action?jspName=showall";
		}else{
			return false;
		}
	}
	this.parent.frames[0].location = queryUrl;
	//$("#showdata").attr("src",queryUrl);
	/*  
	$.ajax({
		type:"GET",
		url:queryUrl,
		cache:false,
		success:function(html){

			$("body").html(html);				
		}
	});
	*/		
}