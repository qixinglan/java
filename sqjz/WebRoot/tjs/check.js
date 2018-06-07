function chkClassAttr(input,num){
	var str = $.trim($(input).val());
	if(str != "" && !regex_title.test(str)){
			$.ajax({
				type:"POST",
				url:"../class/check.action",
				data:$(input).attr("name")+"="+$(input).val(),
				cache:false,
				dataType:"json",
				success:function(html){
					if(parseInt($.trim(html)) > parseInt(num)){	//存在
						$("#formflag").val("0");
						return fun_clear(input);
					}else{
						$("#formflag").val("1");
						var $listItem = $(input).parents('li:first');
						$listItem.removeClass('warning')
						.find('span.red').remove();
						return true;
					}
				}
			});		
	}else{
		$("#formflag").val("1");
		var $listItem = $(input).parents('li:first');
		$listItem.removeClass('warning')
		.find('span.red').text("");
		return true;
	}
}

function chkDataItmeAttr(input,num){
	var str = $.trim($(input).val());
	if(str != "" && !regex_title.test(str)){
			$.ajax({
				type:"POST",
				url:"check.action",
				"cache" : false,
				data:$(input).attr("name")+"="+$(input).val(),
				cache:false,
				dataType:"json",
				success:function(html){
//					alert(html);
				if(parseInt($.trim(html)) > parseInt(num)){	//存在
						$("#formflag").val("0");
						return fun_clear(input);
					}else{
						$("#formflag").val("1");
						var $listItem = $(input).parents('li:first');
						$listItem.removeClass('warning')
						.find('span.red').remove();
						return true;
					}
				}
			});		
	}else{
		$("#formflag").val("1");
		var $listItem = $(input).parents('li:first');
		$listItem.removeClass('warning')
		.find('span.red').text("");
		return true;
	}
}
function fun_clear(input){
	var $listItem = $(input).parents('li:first');
	$listItem.removeClass('warning')
	.find('span.red').remove();
	
	$('<span></span>').addClass('red')
	.text("数据已存在")
	.appendTo($listItem);
	$listItem.addClass('warning');
	return false;
}