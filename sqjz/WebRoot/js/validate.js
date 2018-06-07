var regex_title = /[\~\!\@\#\$\%\^\&\*\\\=\+\<\>\{\}\,\.\?\;\:\"\'\`\/\|]/;
var regex_key = /[\~\!\@\#\$\%\^\&\*\\\=\+\<\>\{\}\.\?\;\:\"\'\`\/\|]/;
var isMobile=/^(((1[0-9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;
var isPhone=/^\d{3,4}-?\d{7,9}$/;
var isMail=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var isPostCode=/^[0-9][0-9]{5}$/;
/*
* 去掉左边空格
*/
function LTrim(str) {
 var whitespace = new String(" \t\n\r");
 var s = new String(str);
 if (whitespace.indexOf(s.charAt(0)) != -1) {
     var j=0, i = s.length;
     while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
        j++;
     }
     s = s.substring(j, i);
 }
 return s;
}

/*
* 去掉右边空格
*/
function RTrim(str) {
 var whitespace = new String(" \t\n\r");
 var s = new String(str);
 if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
     var i = s.length - 1;
     while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
         i--;
     }
     s = s.substring(0, i+1);
 }
 return s;
}

/*
* 去掉两边空格
*/
function TrimBoth(str) {
 return RTrim(LTrim(str));
}
function validateInt(objValue){
	var re = /^\d+$/g;
	return re.test(objValue);
}
function validateDecimal(objValue){
	var re = /^\d+(\.\d+)?$/g;
	return re.test(objValue);
}
function validateCharacter(objValue){
	var re = /^[a-zA-Z]+$/g;
	return re.test(objValue);
}
function validateChinese(objValue){
	var re = /^[\u4E00-\u9FA5]+$/g;
	return re.test(objValue);
}
function validateImgSuffix(imageFile){
	var re = /.jpg|.gif|.png|.bmp/i;
	return imageFile.value.match(re);
}
function checkChinese(input){
	var str = $("#"+input).val();
	for(var i=0;i<str.length;i++)
	{
		if (validateChinese(str.charAt(i))==true){
			alert("密码只能是英文字符，请重新输入");
			$("#"+input).select();
			$("#"+input).focus();
			return false;
		}
	}
	return true;
}

function strLen(str){
	var strlength=0;	
	for (var i=0;i<str.length;i++)
	{
		if (validateChinese(str.charAt(i))==true||str.match(/[^\x00-\xff]/ig)!=null)
		    strlength=strlength + 2;
		else
			strlength=strlength + 1;
	}
	return strlength;
}
function chkLen(str){
	return str.length;
}
function validateForm(){
	var flag=true;
	var msg="";	
	$('form :input').each(function(index){	
			var objValue = $.trim(this.value);
			var $listItem = $(this).parents('td:first');
			$listItem.removeClass('warning')
			.find('span.attached').remove();
			if ($(this).hasClass('required')){	//必填字段合法性检测
				if(objValue == ''){
					msg = this.msg;
					$('<span></span>').addClass('red').addClass('attached')
					.text("不能为空！")
					.appendTo($listItem);
					$listItem.addClass('warning');	
					flag = false;
				}
			}
			if ($(this).hasClass('validityKey')){			
				if(regex_key.test(objValue)){	//存在非法字符
					msg = "存在非法字符，请重新输入!";
					$('<span></span>').addClass('red').addClass('attached')
					.text(msg)
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}
			if(chkLen(objValue) > $(this).attr("chklen")){
					msg = "超过"+$(this).attr("chklen")+"个字符，请重新输入!";
					$('<span></span>').addClass('red').addClass('attached')
					.text(msg)
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
			}
			if ($(this).hasClass('mobile')){	//验证手机号码
				if(objValue!=""&&!isMobile.test(objValue)){
					$('<span></span>').addClass('red').addClass('attached')
					.text("请输入正确的手机号码！")
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}
			if ($(this).hasClass('phone')){	//验证电话号码
				if(objValue!=""&&!isPhone.test(objValue)){
					$('<span></span>').addClass('red').addClass('attached')
					.text("请输入正确的电话号码！")
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}
			if ($(this).hasClass('mail')){	//验证邮箱
				if(objValue!=""&&!isMail.test(objValue)){
					$('<span></span>').addClass('red').addClass('attached')
					.text("请输入正确的邮箱地址！")
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}
			if ($(this).hasClass('pc')){	//验证邮箱
				if(objValue!=""&&!isPostCode.test(objValue)){
					$('<span></span>').addClass('red').addClass('attached')
					.text("请输入正确的邮政编码！")
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}		
			
			if($(this).attr("dataType")=="int"){	//整数内容检测
					if(objValue!=""&&!validateInt(objValue)){
						$('<span></span>').addClass('red').addClass('attached')
						.text("内容非法，请重新输入!")
						.appendTo($listItem);
						$listItem.addClass('warning');
						flag = false;
					}else{
						var v = parseInt(objValue,10);
	                        var max = $.trim($(this).attr("maxValue"));
	                        var min = $.trim($(this).attr("minValue"));
							if(validateInt(max)&&max!=0){
								var maxx = parseInt(max,10);
								if(v > maxx){
									$('<span></span>').addClass('red').addClass('attached')
									.text($(this).attr("msg") + "的数值超过最大范围" + maxx + "！请重新输入")
									.appendTo($listItem);
									$listItem.addClass('warning');
									flag = false;
								}
							}
							if(validateInt(min)){
								var minn = parseInt(min,10);
								if(v < minn){
									$('<span></span>').addClass('red').addClass('attached')
									.text($(this).attr("msg") + "的数值低于最小范围" + minn + "！请重新输入")
									.appendTo($listItem);
									$listItem.addClass('warning');
									flag = false;
								}
							}							
					}
			}
			if($(this).attr("dataType")=="decimal"){	//浮点数内容检测
					if(objValue!=""&&!validateDecimal(objValue)){
						$('<span></span>').addClass('red').addClass('attached')
						.text("内容非法，请重新输入!")
						.appendTo($listItem);
						$listItem.addClass('warning');
						flag = false;
					}else{
						var v = parseFloat(objValue);
	                    var max = $.trim($(this).attr("maxValue"));
	                    var min = $.trim($(this).attr("minValue"));
						if(validateDecimal(max)&&max!=0){
							var maxx = parseFloat(max);
							if(v - maxx > 0.0){
								$('<span></span>').addClass('red')
								.text($(this).attr("msg") + "的数值超过最大范围" + max + "！请重新输入")
								.appendTo($listItem);
								$listItem.addClass('warning');
								flag = false;
							}
						}
						if(validateDecimal(min)){
							var minn = parseFloat(min);
							if(v - minn < 0.0){
								$('<span></span>').addClass('red').addClass('attached')
								.text($(this).attr("msg") + "的数值低于最小范围" + min + "！请重新输入")
								.appendTo($listItem);
								$listItem.addClass('warning');
								flag = false;
							}
						}
						if(flag){
							var dNum=$.trim($(this).attr("decimalNum"));
							if(validateInt(dNum)){
								var num = parseInt(dNum,10);
								var dIndex=objValue.indexOf('.');
								if(dIndex!=-1){
									var decimalStr=objValue.substring(dIndex+1,objValue.length);
									if(decimalStr.length>num){
										$('<span></span>').addClass('red').addClass('attached')
										.text("小数点后超出"+num+"位！")
										.appendTo($listItem);
										$listItem.addClass('warning');
										flag = false;
									}
								}
							}
						}						
						
					}
			}
			
			//打分页面分数验证  begin
			if($(this).attr("fsType")==0&&objValue!=""){
				var typeValue=$.trim($(this).attr("gdValue"));
				var vs=typeValue.split(",");
				var f=false;
				for(i=0;i<vs.length;i++){
					if(objValue==vs[i]){
						f=true;
						break;
					}
				}
				if(!f){
					$('<span></span>').addClass('red').addClass('attached')
					.text("无效分数!")
					.appendTo($listItem);
					$listItem.addClass('warning');
					flag = false;
				}
			}
			if($(this).attr("fsType")==1&&objValue!=""){
				if(objValue!=""&&!validateInt(objValue)){
						$('<span></span>').addClass('red').addClass('attached')
						.text("内容非法!")
						.appendTo($listItem);
						$listItem.addClass('warning');
						flag = false;
				}else{
					var zxz=$.trim($(this).attr("zxz"));
					var zdz=$.trim($(this).attr("zdz"));										
					if(parseInt(objValue,10)<parseInt(zxz,10)||parseInt(objValue,10)>parseInt(zdz,10)){
						$('<span></span>').addClass('red').addClass('attached')
						.text("无效分数!")
						.appendTo($listItem);
						$listItem.addClass('warning');
						flag = false;
					}
				}
				
			}
			//end
			
			if(flag==false){
				return flag;
			}
			
		});				
	return flag;
}
/*
*全选或全不选
*chkAllName：全选框的id
*chkName：复选框的name
*/
function checkAll(chkAllName,chkName){
	if($("#"+chkAllName).is(":checked")){
		$("input[name='"+chkName+"']").attr("checked",true);
	}else{
		$("input[name='"+chkName+"']").attr("checked",false);
	}
}
/*
* 判断checkbox是否有选中项   
* 参数 checkbox的name属性
* 有选中项 返回 true  没有返回false
*/
function chksIsChecked(name){
	var flag=false;
	$("input[name='"+name+"']").each(function(index){
		if(this.checked){
			flag=true;
		}
	})
	return flag;
}
/*
* 修改时 判断checkbox 是否被多选   
* 参数 checkbox的name属性
* 有选中项 返回 true 正确选择    false 选择不正确
*/
function chkIscheckeds(name){
	var i=0;
	$("input[name='"+name+"']").each(function(index){
		if(this.checked){
			i++;
		}
	})
	if(i>1||i==0)
		return false;
	else
		return true;
				
}