<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>阅读</title>
<style type="text/css">
body {
	background-color: #E3E6E8;
	text-align: center;
}
</style>
<script type="text/javascript">
var xhr=null;
function lastchapter(){//上一章
var a=parseInt(document.getElementById("currchapter").innerText);
var b=parseInt(document.getElementById("totalchapter").innerText);
var c=document.getElementById("iframeid");
var src=c.getAttribute("src");


if((a-1)>=1){
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+(a-1)+".html");//修改内嵌网页的地址，达到电子书内容的改变
document.getElementById("currchapter").innerText=a-1;//修改当前章节信息
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+(a-1)+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);// 发送ajax请求，修改阅读记录
}else{
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+1+".html");
document.getElementById("currchapter").innerText=1;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+1+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);
}
}

function nextchapter(){
var a=parseInt(document.getElementById("currchapter").innerText);
var b=parseInt(document.getElementById("totalchapter").innerText);
var c=document.getElementById("iframeid");
var src=c.getAttribute("src");

if((a+1)<=b){
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+(a+1)+".html");
document.getElementById("currchapter").innerText=a+1;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+(a+1)+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);

}else{
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+b+".html");
document.getElementById("currchapter").innerText=b;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+b+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);
}
}
function jump(){
var a=parseInt(document.getElementById("currchapter").innerText);
var b=parseInt(document.getElementById("totalchapter").innerText);
var c=document.getElementById("iframeid");
var d=document.getElementById("jumpchapter");
var src=c.getAttribute("src");
if(d.value!=""){
if("NaN"==parseInt(d.value)){

}
else{
if(d.value<=0){
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+"1.html");
document.getElementById("currchapter").innerText=1;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+1+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);
}
if(d.value<=b&&d.value>=1){
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+d.value+".html");
document.getElementById("currchapter").innerText=d.value;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+d.value+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);
}
if(d.value>b){
c.src=((src.substring(0,(src.lastIndexOf(".html")-1)))+b+".html");
document.getElementById("currchapter").innerText=b;
xhr=getXhr();
xhr.open('get',encodeURI("savechapter.do?chapter="+b+"&u_id="+document.getElementById("u_id").innerText+"&bid="+document.getElementById("bid").innerText),true);
xhr.onreadystatechange=f2;
xhr.send(null);
}
}
}
}
function  f2(){
if(xhr.readyState==4){
if(xhr.status=200){
txt=xhr.responseText;
if(txt!=""){
window.location.href="http://127.0.0.1:8081/bookWeb/login/login.html";
}
}
}
}
//首次使用ajax，引出ajax对象
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
</script>
</head>


<body>
<%
int currchapter=Integer.parseInt(request.getParameter("currchapter"));//当前章/节
Book book =(Book)request.getAttribute("book");
int totaoChapter=book.getTotalchapter();
%>
<p>${book.bname}</p>
<table align="center">
<tr>
<td><a href="http://127.0.0.1:8081/bookWeb/main/main.jsp"  >主页</a></td>
<span id="u_id" style="display:none"><%=request.getParameter("u_id") %></span><span id="bid" style="display:none"><%=request.getParameter("bid")%></span>
<td>当前</td><td id="currchapter"><%=currchapter%></td><td>章/节</td>
<td><a href="javascript:;" onclick="lastchapter()">上一章/节</a></td>
<td><a href="javascript:;" onclick="nextchapter()">下一章/节</a></td>
<td>总共</td><td id="totalchapter">${book.totalchapter}</td><td>章/节</td><td><a href="javascript:;" onclick="jump()">跳转</a></td><td><input id="jumpchapter" size="2"/></td><td>章/节</td>
</tr>
</table>

  

</p> <iframe id="iframeid" src="${book.bookadd}<%=currchapter%>.html" style="height:1000;width:1000" scrolling="no" frameborder="0"></iframe>
<div ><a style="text-align:left" href="http://127.0.0.1:8081/bookWeb/main/main.jsp"  >主页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">顶部</a></div>
</body>
</html>
