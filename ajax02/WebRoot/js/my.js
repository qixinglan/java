function getXhr(){
	var Xhr=null;
	if(window.XMLHttpRequest){
		Xhr=new XMLHttpRequest;
		}
	else{
		Xhr=new ActiveXObject('Microsoft.XMLHttp')
		}
		return Xhr;
}
function $(id){
	return document.getElementById(id);
}
function $F(id){
	return $(id).value;
}