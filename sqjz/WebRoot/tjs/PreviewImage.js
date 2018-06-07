function previewImage(file,fileID){ 
  if(!file.value.match(/.jpg|.gif|.png|.bmp/i)){
	alert('图片类型必须为: .jpg, .gif, .bmp 或 .png !');
	//清空File内的内容
	if (file.files && file.files[0]){
		$("#"+fileID).val("");
	}else{
		file.outerHTML=file.outerHTML.replace(/(value=\").+\"/i,"$1\"");
	}
	return;
  }	
  var browser = getCurrentBrowser();
  if(browser.version != null && browser.version.indexOf(".")>0){
	browser.version = browser.version.substring(0, browser.version.indexOf(".")) + ".0";
  }
  var MAXWIDTH  = 88; 
  var MAXHEIGHT = 84; 
  var div = document.getElementById('imgDiv'); 
  if (file.files && file.files[0]){
    div.innerHTML = '<img id=zp>'; 
    var img = document.getElementById('zp'); 
    img.onload = function(){ 
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
      img.width = rect.width; 
      img.height = rect.height; 
      img.style.marginLeft = rect.left+'px'; 
      img.style.marginTop = rect.top+'px'; 
    } 
    if(browser.firefox && browser.version < 4.0){                                //firefox浏览器,版本4.0以下
		img.src = file.files.item(0).getAsDataURL();		
	}else if(browser.firefox && browser.version >= 4.0 && browser.version < 10.0){ //firefox浏览器,版本4.0至9.*
		img.src = window.URL.createObjectURL(file.files.item(0));
	}else if(browser.chrome && browser.version < 13.0){                        //chrome浏览器，版本13.0以下
		img.src = window.webkitURL.createObjectURL(file.files.item(0));
	}else{                                                               //其它浏览器, 支持html5
		var reader = new FileReader(); 
    	reader.onload = function(evt){img.src = evt.target.result;} 
    	reader.readAsDataURL(file.files[0]);
	}
     
  }else{
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod="scale",src="'; 
    file.select(); 
    if(browser.version >= 9.0){
    	$("#btnSubmit").focus(); 
    }
    var src = document.selection.createRange().text;
    document.selection.empty();
    div.innerHTML = "<div id=divhead style='width:"+MAXWIDTH+"px;height:"+MAXHEIGHT+"px;"+sFilter+src+"\");'></div>"; 
  } 
} 
function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
    var param = {top:0, left:0, width:width, height:height}; 
    if( width>maxWidth || height>maxHeight ){ 
        rateWidth = width / maxWidth; 
        rateHeight = height / maxHeight; 
        if( rateWidth > rateHeight ){ 
            param.width =  maxWidth; 
            param.height = Math.round(height / rateWidth); 
        }else{ 
            param.width = Math.round(width / rateHeight); 
            param.height = maxHeight; 
        } 
    } 
    param.left = Math.round((maxWidth - param.width) / 2); 
    param.top = Math.round((maxHeight - param.height) / 2); 
    return param; 
}