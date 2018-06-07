//added by Jennie on 12-7-18

var isIe=(document.all)?true:false;

function boxPosition(type, w, h)
{
	if(type=="middle"){
		var _left = (parseInt(document.documentElement.scrollWidth) - document.body.scrollLeft)/2 - w/2;
		var _top = (parseInt(document.documentElement.scrollHeight) - document.body.scrollTop)/2 - h/2;
	}else
		if(type=="bottom"){
			var _left = (parseInt(document.documentElement.scrollWidth) - document.body.scrollLeft)/2 - w/2;
			var _top = (parseInt(document.documentElement.scrollHeight) - document.body.scrollTop)/2 - h/2 + 40;
		}
	
	return {x:_left, y:_top};
}

//弹出方法
function showPreviewBox(wTitle,xmlStr,xslPath,ctx,pos,wWidth,wHeight)
{
    var imagePath = ctx + "/js/dialog/images/";
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
	var _left = pos.x;
	var _top = pos.y;
	
	var xml = new ActiveXObject("Microsoft.XMLDOM");
	xml.async = false;
	xml.loadXML("<?xml version='1.0' encoding='utf-8'?>" + xmlStr);
	var xsl = new ActiveXObject("Microsoft.XMLDOM");
	xsl.async = false;
	xsl.load(xslPath);
	var htmlStr = xml.transformNode(xsl);
	
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
	arr.push('<td colspan="3">');
	arr.push('<div id="showDiv" style="border: 1px; border-color: black;">');
	//content below
	arr.push(htmlStr);
	arr.push('</div>');
	arr.push('</td>');
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
	if(document.getElementById('srcList') != "undefined"){
		document.getElementById('srcList').style.visibility="visible";
	}
	if(document.getElementById('destList') != "undefined"){
		document.getElementById('destList').style.visibility="visible";
	}
	
	return true;
}