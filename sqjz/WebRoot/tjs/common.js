var isIe=(document.all)?true:false;
var video_type = "[avi,rm,rmvb,asf,asx,wmv,3gp,mp4,mov,flv]";
var doc_type = "[doc,docx,ppt,pptx,xls,xlsx,pdf,txt]";
var image_type = "[gif,jpg,jpeg,png,bmp]";

//tab1:标签1  显示 ； tab2:标签2 隐藏   ；  div1：层1显示；     div2:层2隐藏；
function showTab(tab1,tab2,div1,div2,classname){
	var tab_1 = document.getElementById(tab1);
	var tab_2 = document.getElementById(tab2);
	var div_1 = document.getElementById(div1);
	var div_2 = document.getElementById(div2);
	 
	tab_1.className =classname +"_cur";
	tab_2.className = classname;
	
	div_1.style.display="";	
	div_2.style.display="none";
}

/*
* 打开新窗口 f:链接地址 n:窗口的名称 w:窗口的宽度 h:窗口的高度 s:窗口是否有滚动条，1：有滚动条；0：没有滚动条
*/
function openWin(f,n,w,h,s){
	sb = s == "1" ? "1" : "0";
	l = (screen.width - w)/2;
	t = (screen.height - h)/2;
	sFeatures = "left="+ l +",top="+ t +",height="+ h +",width="+ w
			+ ",center=1,scrollbars=" + sb + ",status=1,directories=0,channelmode=0";
	openwin = window.open(f , n , sFeatures );
	if (!openwin.opener)
		openwin.opener = self;
	openwin.focus();
	return openwin;
}

/**
* 判断网址
*/

function funCheckURL(str_url){
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"   
     + "(([0-9]{1,3}\.){3}[0-9]{1,3})" // IP形式的URL- 199.194.52.184
     + "(:[0-9]{1,4})?" // 端口- :80
     + "((/?)|" // a slash isn't required if there is no file name
     + "(/[0-9A-Za-z_!~*'().;?:@&=+$,%#-]+)+/?)$";   
   
     var re=new RegExp(strRegex);   
     if (re.test(TrimBoth(str_url))){  
         return (false);   
     }else{   
         return (true);   
     }  
}
/*
* 打开删除窗口
*/
function openDeleteDialog(url, confirmString){
	var c = confirmString;
	var flag = false;
	if(c == null || c == ''){
		c = "你确认要删除记录吗？";
	}

	if(confirm(c)){
		flag = true;
		window.showModalDialog(url,"window123","dialogHeight:375px;dialogWidth:764px;resizable:no;help:yes;status:no;scroll:no");
	}
	
	return flag;
}

/*
* 删除记录
*/
function deleteEntity(url, confirmString){
	
	return openDeleteDialog(url, confirmString);
}


//检测控件是否为空
function checkControlNotNull(obj,errInfo){
	var o = getObj(obj);
	if(o.value == ""){
		alert(errInfo);
		o.forcus();
		return false;		
	}
	return true;
}

function getObj(id){
	return document.getElementById(id);
}

//弹出方法
function showMessageBox(wTitle,content,pos,wWidth)
{
	closeWindow();	
	var imagePath = "../js/dialog/images/";
	var bWidth=parseInt(document.documentElement.scrollWidth);
	var bHeight=parseInt(document.documentElement.scrollHeight);
	var back=document.createElement("div");
	back.id="back";
	var styleStr="top:0px;left:0px;position:absolute;background:#a29f9f;width:"+bWidth+"px;height:"+bHeight+"px;";
	styleStr+=(isIe)?"filter:alpha(opacity=40);":"opacity:0.40;";
	back.style.cssText=styleStr;
	document.body.appendChild(back);
	var mesW=document.createElement("div");
	mesW.id="mesWindow";
	mesW.className="mesWindow";
	var _left = pos.x - (wWidth / 2);
	var _top = pos.y;
	//alert(_left + "," + _top);
	var arr = [];
	arr.push('<table width="100%" border="0" cellspacing="0" cellpadding="0">');
	arr.push('<tr>');
	arr.push('<td width="13"><img src="'+imagePath+'dialog_lt.png" width="13" height="33"/></td>');
	arr.push('<td width="5%" valign="bottom" background="'+imagePath+'dialog_ct.png" style="padding-bottom:5px;"><img src="'+imagePath+'window.gif" width="15" height="15" /></td>');
	arr.push('<td width="90%" valign="bottom" background="'+imagePath+'dialog_ct.png" style="padding-bottom:5px;">&nbsp;'+wTitle+'</td>');
	arr.push('<td width="23" valign="top" background="'+imagePath+'dialog_ct.png" style="padding-top:7px;"><img src="'+imagePath+'dialog_closebtn.gif" width="28" height="17" onmousemove="this.src=\''+imagePath+'dialog_closebtn_over.gif\'" onmouseout="this.src=\''+imagePath+'dialog_closebtn.gif\'" class="close" onclick="closeWindow();" /></td>');
	arr.push('<td width="13"><img src="'+imagePath+'dialog_rt.png" width="13" height="33"/></td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td rowspan="2" background="'+imagePath+'dialog_mlt.png"></td>');
	arr.push('<td colspan="3">'+content+'</td>');
	arr.push('<td rowspan="2" background="'+imagePath+'dialog_mrt.png"></td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td height="42" colspan="3">&nbsp;</td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td><img src="'+imagePath+'dialog_lb.png" width="13" height="13" /></td>');
	arr.push('<td colspan="3"  valign="bottom" background="'+imagePath+'dialog_cb.png"></td>');
	arr.push('<td><img src="'+imagePath+'dialog_rb.png" width="13" height="13" /></td>');
	arr.push('</tr>');
	arr.push('</table>');
	
	mesW.innerHTML = arr.join('');
	
	//mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%'><tr><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' class='close' value='关闭' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
	styleStr="left:"+_left+"px;top:"+_top+"px;position:absolute;width:"+wWidth+"px;";
	mesW.style.cssText=styleStr;
	document.body.appendChild(mesW);
}

function showBackground(obj,endInt)
{
	obj.filters.alpha.opacity+=1;
	if(obj.filters.alpha.opacity<endInt)
	{
		setTimeout(function(){showBackground(obj,endInt)},8);
	}
}

function inputPosition(input)
{
	var obj = document.getElementById(input);
	var _left = obj.offsetLeft;
	var _top = obj.offsetTop + 25;
	
	while(obj = obj.offsetParent){
		_left += obj.offsetLeft;
		_top += obj.offsetTop;
	}	
	
	return {x:_left, y:_top};
}

function validateImgSuffix(imageFile){
	var re = /.jpg|.gif|.png|.bmp/i;
	return imageFile.value.match(re);
}

function checkImgSuffix(imageFile){
	if(!validateImgSuffix(imageFile)){
		alert('图片类型必须为: .jpg, .gif, .bmp 或 .png !');
		imageFile.outerHTML=imageFile.outerHTML.replace(/(value=\").+\"/i,"$1\"");
	}
}

function endWith(fileName, suffix){
	if(!(fileName == "" || suffix == "")){
		return fileName.substring(fileName.length-suffix.length) == suffix;
	}
	return false;
}

function filterStr(str){
	var result="";	
	for (var i=0;i<str.length;i++)
	{
		if (!regex_title.test(str.charAt(i)))
			result+=str.charAt(i);
	}
	return TrimBoth(result);
}

function clearData(){
	$(':input[type=text]').each(function(index){
			if ($(this).hasClass('rst')){	//清空text
				$(this).val("");
			}			   
		});		
	$(':input[type=hidden]').each(function(index){
			if ($(this).hasClass('rst')){	//清空hidden
				$(this).val("");
			}			   
		});		
	$('select').each(function(index){
			if ($(this).hasClass('rst')){	//重置select
				$(this).get(0).selectedIndex=0;
			}else if($(this).hasClass('rsts')){	
				$(this).empty();
			}
		});	
	$(':radio').each(function(index){
			if ($(this).hasClass('rst')){	//重置radio
				$(this).get(0).checked=true;
			}			
		});	
}

function select(wTitle,content,pos,wWidth,wHeight){	
	var bWidth=parseInt(document.documentElement.scrollWidth);
	var bHeight=parseInt(document.documentElement.scrollHeight);
    
	var back=document.createElement("div");
	back.id="back";
	var styleStr="top:0px;left:0px;position:absolute;width:"+bWidth+"px;";
	back.style.cssText=styleStr;
	document.body.appendChild(back);
	var mesW=document.createElement("div");
	mesW.id="mesWindow";
	var wHeight1;
	if(wHeight != null && wHeight != "undefined"){
		mesW.style.height = wHeight + "px";
		wHeight1 = (wHeight - 30) + "px";
	}else{
		mesW.style.height = "300px";
		wHeight1 = 270 + "px";
	}
	mesW.className="mesWindow1";
	mesW.innerHTML="<div class='mesWindowTop'><table width='100%'><tr><td>"+wTitle+"</td><td style='width:40px;'><a href='#' class='close' onclick='closeWindow();'>关闭</a></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent' style='height:" + wHeight1 + "'>"+content+"</div><div class='mesWindowBottom'></div>";
	styleStr="left:"+pos.x+"px;top:"+pos.y+"px;position:absolute;width:"+wWidth+"px;";
	mesW.style.cssText=styleStr;
	document.body.appendChild(mesW);
}

//关闭窗口
function closeWindow()
{
	if(document.getElementById('mesWindow')!=null)
	{
		document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
	}
	if(document.getElementById('back')!=null)
	{
		document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
	}

	if(window.frames[0] != null && window.frames[0] != "undefined" && window.frames[0].document != null && window.frames[0].document != "undefined" && window.frames[0].document.getElementById('chartdiv') != "undefined" && window.frames[0].document.getElementById('chartdiv') != null){
		if(window.frames[0].document.getElementById("chartdiv").style.display=="none"){
			window.frames[0].document.getElementById("chartdiv").style.display="block";
		}
	}
	
}

//显示等待窗口
function showWaitBox(wTitle,content,pos,wWidth)
{
	closeWindow();	
	var imagePath = "../../js/dialog/images/";
	var bWidth=parseInt(document.documentElement.scrollWidth);
	var bHeight=parseInt(document.documentElement.scrollHeight);
	var back=document.createElement("div");
	back.id="back";
	var styleStr="top:0px;left:0px;position:absolute;background:#a29f9f;width:"+bWidth+"px;height:"+bHeight+"px;";
	styleStr+=(isIe)?"filter:alpha(opacity=40);":"opacity:0.40;";
	back.style.cssText=styleStr;
	document.body.appendChild(back);
	var mesW=document.createElement("div");
	mesW.id="mesWindow";
	mesW.className="mesWindow";
	var _left = pos.x - (wWidth * 1.5);
	var _top = pos.y - 160;
	//alert(_left + "," + _top);
	var arr = [];
	arr.push('<table width="100%" border="0" cellspacing="0" cellpadding="0">');
	arr.push('<tr>');
	arr.push('<td width="13"><img src="'+imagePath+'dialog_lt.png" width="13" height="33"/></td>');
	arr.push('<td width="5%" valign="bottom" background="'+imagePath+'dialog_ct.png" style="padding-bottom:5px;"><img src="'+imagePath+'window.gif" width="15" height="15" /></td>');
	arr.push('<td width="90%" valign="bottom" background="'+imagePath+'dialog_ct.png" style="padding-bottom:5px;">&nbsp;'+wTitle+'</td>');
	arr.push('<td width="23" valign="top" background="'+imagePath+'dialog_ct.png" style="padding-top:7px;"></td>');
	arr.push('<td width="13"><img src="'+imagePath+'dialog_rt.png" width="13" height="33"/></td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td rowspan="2" background="'+imagePath+'dialog_mlt.png"></td>');
	arr.push('<td colspan="3">'+content+'</td>');
	arr.push('<td rowspan="2" background="'+imagePath+'dialog_mrt.png"></td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td height="42" colspan="3">&nbsp;</td>');
	arr.push('</tr>');
	arr.push('<tr>');
	arr.push('<td><img src="'+imagePath+'dialog_lb.png" width="13" height="13" /></td>');
	arr.push('<td colspan="3"  valign="bottom" background="'+imagePath+'dialog_cb.png"></td>');
	arr.push('<td><img src="'+imagePath+'dialog_rb.png" width="13" height="13" /></td>');
	arr.push('</tr>');
	arr.push('</table>');
	
	mesW.innerHTML = arr.join('');
	
	//mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%'><tr><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' class='close' value='关闭' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
	styleStr="left:"+_left+"px;top:"+_top+"px;position:absolute;width:"+wWidth+"px;";
	mesW.style.cssText=styleStr;
	document.body.appendChild(mesW);
}

function loadPage(url,framename){
	$("#"+framename).attr("src",url);
}

/*
* 清除已经选择的分类：name 分类名称	id input输入框ID
*/
function clearclass(input1){
	$("#"+input1).val("");
}

/*
 * 关闭当前窗口
 */
function closeWin(){
	window.opener=null;
	window.open('','_self');
	window.close();
}

/**
 * 异步加载页面
 * 杨珍琪 2013-6-18
 * @param url
 * @param div
 * @return
 */
function ajax_load(url,div){
	$.ajax({
		type:"GET",
		url:url,
		cache:false,
		success:function(html){
			$("#"+div).html(html);
		}
	});	
}

/**
 * 异步加载页面
 * 杨珍琪 2013-11-28
 * @param url
 * @param div
 * @return
 */
function ajax_fire(url,fn,paras){
	$.ajax({type:"POST",async:false,url:url,data:paras,success:function(info){
   	   fn(info);
    }});
}

/**
 * 遮罩层
 * 2013-11-13
 * 
 * 遮罩层引入：
 * <link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
 * <div id="bgdiv"></div><div id="ctdiv" class="tc_dingwei">  此处是显示内容  </div>	
 * 
 * url：异步访问url
 * paras：异步传递参数，json格式{key1:value1, key2:value2}
 * fn: 异步访问成功后的回调函数
 * position:显示位置，left左坐标，top上坐标
 */

//获取数据，弹出
function showDiv(url,paras,fn,position) {
	   $.ajax({type:"POST",async:false,url:url,data:paras,success:function(info){
	   	   fn(info);
	   }});		 
	   showOutDiv(position);
}

//弹出
function showOutDiv(position){
	   if(position != null){
		   if(position.top != null || position.top != "undefined")
		   		$("#ctdiv").css("top", position.top);
		   if(position.left != null || position.left != "undefined")
			   	$("#ctdiv").css("marginLeft", position.left);
	   }
	   $("#bgdiv").css({ display: "block", height: $(document).height(),width:$(document).width()});
	   var yscroll = document.documentElement.scrollTop;
	   $("#ctdiv").css("display", "block");
	   document.documentElement.scrollTop = 0;	
}

//关闭
function closeDiv() {
       $("#bgdiv").css("display", "none");
       $("#ctdiv").css("display", "none");
}

//切换图片
function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

