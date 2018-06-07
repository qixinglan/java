<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admanage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="http://127.0.0.1:8081/bookWeb/js/md5.js"></script>
<script type="text/javascript">
function lastpage(){//上一页
var page=document.getElementById("page").innerText;
page--;
if(page<=0){
page=1;
}
window.location.href="http://127.0.0.1:8081/bookWeb/admanage.do?page="+page; 

}
function nextpage(){//下一页
var page=document.getElementById("page").innerText;

if(document.getElementById("test")==null){//检查为空，页数不再提高

}
else{
page++;
}
window.location.href="http://127.0.0.1:8081/bookWeb/admanage.do?page="+page; 

}
function fix(aname,apwd,atype){//修改函数，注意字母汉字传参el表达式需加‘’   双引号里的双引号再里面的双引号用转移字符，注意他们的配对是否正确，来用单双引号
var tr=document.getElementById(aname);
tr.innerHTML="<td id='test' ><a href='javascropt:;' onclick='submit(&quot;"+aname+"&quot;);return false'>提交</a> <a href='javascript:;' onclick='cancel(&quot;"+aname+"&quot;,"+apwd+","+atype+")'>取消</a></td>"+
"<td><input  value="+aname+"></td> <td><input  value="+apwd+"></td><td ><input size=1  value="+atype+"></td>";
}
function cancel(aname,apwd,atype){// 取消函数
var tr=document.getElementById(aname);
tr.innerHTML="<td id='test' ><a href='javascript:;' onclick='fix(&quot;"+aname+"&quot;,"+apwd+","+atype+")'>修改</a> <a href='addelete.do?aname="+aname+"&page="+document.getElementById("page").innerText+"'>删除</a></td>"+
"<td>"+aname+"</td> <td>"+apwd+"</td><td >"+atype+"</td>";
}
function submit(yaname){//提交函数

var tr=document.getElementById(yaname);
var array=(tr.getElementsByTagName("input"));
var page=document.getElementById("page").innerText;
var re=/[\u4E00-\u9FA5]/;

if(array[0].value==""||array[1].value==""||array[2].value==""||re.test(array[0].value)||re.test(array[1].value)){//判断中文且不能为空
alert("输入错误");
}
else if(array[2].value==0||array[2].value==1){
xhr=getXhr();
xhr.open('post','http://127.0.0.1:8081/bookWeb/adupdate.do','true');
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.onreadystatechange=f3;
xhr.send("aname="+array[0].value+"&apwd="+array[1].value+"&atype="+array[2].value+"&yaname="+yaname);
//window.location.href="http://127.0.0.1:8081/bookWeb/adupdate.do?aname="+array[0].value+"&apwd="+array[1].value+"&atype="+array[2].value+"&page="+page+"&yaname="+yaname;
}
else{
alert("输入错误");
}
}
function  f3(){
if(xhr.readyState==4){
if(xhr.status=200){
txt=xhr.responseText;
if(txt==""){
location.reload();
alert("修改成功");
}else{
alert("修改失败");
}
}
}
}
var xhr=null;
function add(){//添加 函数
var aname=document.getElementById("aname").value;
var apwd=document.getElementById("apwd").value;
var atype=document.getElementById("atype").value;
var page=document.getElementById("page").innerText;
var re=/[\u4E00-\u9FA5]/;

if(aname==""||atype==""||apwd==""||re.test(aname)||re.test(apwd)){//判断中文且不能为空
alert("输入错误");
}

else if(atype==0||atype==1){
xhr=getXhr();
xhr.open('post','http://127.0.0.1:8081/bookWeb/adadd.do','true');
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.onreadystatechange=f2;
xhr.send("aname="+aname+"&apwd="+apwd+"&atype="+atype);

}
else{
alert("输入错误");
}
}
function  f2(){
if(xhr.readyState==4){
if(xhr.status=200){
txt=xhr.responseText;
if(txt==""){
location.reload();
alert("添加成功");
}else{
alert("添加失败");
}
}
}
}


function getXhr(){
	if(window.XMLHttpRequest){
		Xhr=new XMLHttpRequest;
		}
	else{
		Xhr=new ActiveXObject('Microsoft.XMLHttp')
		}
		return Xhr;
}
function addelete(aname,page){
xhr=getXhr();
xhr.open('post','http://127.0.0.1:8081/bookWeb/addelete.do','true');
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.onreadystatechange=f4;
xhr.send("aname="+aname);
}
function  f4(){
if(xhr.readyState==4){
if(xhr.status=200){
txt=xhr.responseText;
if(txt==""){
location.reload();

}else{
alert("删除失败");
}
}
}
}
</script>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
  </head>
  
  <body>
    <table class="gridtable" align="center" width=700  >
    <tr><th>操作<th>用户名(不可以重复且不能包含汉字)</th><th>密码(不可以是汉字)</th><th>类别(只能为0或1)</th></tr>
    <c:forEach  var='e' items='${list}'>
    <tr id="${e.aname}" align="center">
    <td id="test"><a href="javascript:;" onclick="fix('${e.aname}',${e.apwd},${e.atype})">修改</a>
    <a href="javascript:;" onclick="addelete('${e.aname}',${param.page})">删除</a></td>
    <td>${e.aname}</td>
    <td>${e.apwd}</td>
    <td>${e.atype}</td>
    </tr>
    </c:forEach>
    
    <tr align="center">
    <td><a href="javascript:;" onclick="add();return false">添加</a></td>
    <td><input id="aname"></td>
    <td><input id="apwd"></td>
    <td><input size=1 id="atype"></td>
    </tr>
    <tr align="center">
     <td></td>
    <td><a href="javascript:;" onclick="lastpage()">上一页</a></td>
    <td><a href="javascript:;" onclick="nextpage()">下一页</a></td>
    <td>当前第<span id="page">${param.page}</span>页</td>
    </tr>
    </table>
    <div style="text-align:center">
    <a href="bookmanage.do?page=1">图书信息管理</a>
   <a href="usermanage.do?page=1">用户信息管理</a>
   </div>
  </body>
</html>
