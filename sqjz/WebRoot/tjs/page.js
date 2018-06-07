
function tz(url,pageNo){  	
	if(!validateInt(pageNo)){
		alert("页数格式不正确，请重新输入！");
		return false;
	}
	if(url.indexOf("?") != -1){
		strUrl = url+"&page.pageNo=" + pageNo;
	}else{
		strUrl = url+"?page.pageNo=" + pageNo;
	}
	window.location.href=strUrl;
}

function newTz(form,pageNo){
	$("#pageNo").val(pageNo);
	$("#"+form).submit();
}

function tiaozhuan(url,total){  
	var pageNo = 0 ;
	pageNo = parseInt($("#tiaozhuanpages").val());
	if(url.indexOf("?") != -1){
		strUrl = url+"&page.pageNo=" + pageNo;
	}else{
		strUrl = url+"?page.pageNo=" + pageNo;
	}
	var max = 0 ;
	max = parseInt(total);
	if(pageNo <1 ||isNaN(pageNo)){
	    alert("跳转页输入格式错误！");
   }else if(pageNo > max){
   		alert("超出最大页！");   
   }else{
   
	   window.location.href=strUrl;
	} 
}

function a_load_user(url,pageNo){  	
	if(url.indexOf("?") != -1){
		strUrl = url+"&page.pageNo=" + pageNo;
	}else{
		strUrl = url+"?page.pageNo=" + pageNo;
	}
	ajax_load(strUrl,"userlist");
}

function a_load_user_count(url,total){  
	var pageNo = 0 ;
	pageNo = parseInt($("#tiaozhuanpages").val());
	if(url.indexOf("?") != -1){
		strUrl = url+"&page.pageNo=" + pageNo;
	}else{
		strUrl = url+"?page.pageNo=" + pageNo;
	}
	var max = 0 ;
	max = parseInt(total);
	if(pageNo <1 ||isNaN(pageNo)){
	    alert("跳转页输入格式错误！");
   }else if(pageNo > max){
   		alert("超出最大页！");   
   }else{
	   ajax_load(strUrl,"userlist");
	} 
}

function goPage(pageNo,totalPages){
	
	if(pageNo==0){//page为0的时候为点击GO按钮触发的该事件
		var flag = true;
		pageNo = $("#pageNo").val();
		if(!validateInt(pageNo)){
			alert("页数格式不正确，请重新输入！");	
			flag=false;
		}else if(parseInt(pageNo)>parseInt(totalPages)){
			alert("页数大于总页数，请重新输入！");
			flag=false;
		}else if(parseInt(pageNo)<1){
			alert("页数小于1，请重新输入！");
			flag=false;
		}
			
		if(!flag){
			$("#pageNo").val("");
			$("#pageNo").focus();
			return;
		}
	}
	
	var input = document.createElement("input");
	input.name="page.pageNo";
	input.id="pageNoForFormSubmit";
	input.value=pageNo;
	input.type="hidden";
	document.forms[0].appendChild(input);
	document.forms[0].submit();
}