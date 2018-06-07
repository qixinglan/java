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
<script type="text/javascript">
function lastpage(){//上一页
var page=document.getElementById("page").innerText;
page--;
if(page<=0){
page=1;
}
window.location.href="http://127.0.0.1:8081/bookWeb/usermanage.do?page="+page; 

}
function nextpage(){//下一页
var page=document.getElementById("page").innerText;

if(document.getElementById("test")==null){//检查为空，页数不再提高
}
else{
page++;
}
window.location.href="http://127.0.0.1:8081/bookWeb/usermanage.do?page="+page; 

}
var  xhr=null;
var tr=null;
function fix(uid){
tr=document.getElementById(uid);
xhr=getXhr();
xhr.open('post','http://127.0.0.1:8081/bookWeb/userupstate.do','true');
xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.onreadystatechange=f2;
xhr.send("uid="+uid);
}
function f2(){

if(xhr.readyState==4){
if(xhr.status=200){
txt=xhr.responseText;
if(txt==""){

var array=tr.getElementsByTagName("td");
var status=array[5].innerText;
if(status==0){
array[5].innerText=1;
}
else{
array[5].innerText=0;
}
}else{
alert("修改失败");
}
}
}
}
// function fix(bid,bname,author,type,introduce,imgadd,bookadd,price,totalchapter){//修改函数，注意字母汉字传参el表达式需加‘’   双引号里的双引号再里面的双引号用转移字符，注意他们的配对是否正确，来用单双引号
// var tr=document.getElementById(bid);
// tr.innerHTML="<td id='test' ><a href='javascropt:;' onclick='submit("+bid+");return false'>提交</a>"+
//  " <a href='javascript:;' onclick='cancel("+bid+",&quot;"+bname+"&quot;,"+"&quot;"+author+"&quot;,"+"&quot;"+type+"&quot;,"+"&quot;"+introduce+"&quot;,"+"&quot;"+imgadd+"&quot;,"+"&quot;"+bookadd+"&quot;,"+price+","+totalchapter+")'>取消</a></td>"+
// "<td>"+bid+"</td>"+
//     "<td><input value="+bname+"></td>"+
//     "<td><input size=2 value="+author+"></td>"+
//     "<td><input size=4 value="+type+"></td>"+
//     "<td><input value="+introduce+"></td>"+
//     "<td><input value="+imgadd+"></td>"+
//     "<td><input value="+bookadd+"></td>"+
//     "<td><input size=2 value="+price+"></td>"+
//     "<td><input size=2 value="+totalchapter+"></td>";
// }

// function cancel(bid,bname,author,type,introduce,imgadd,bookadd,price,totalchapter){// 取消函数
// var tr=document.getElementById(bid);
// tr.innerHTML="<td id='test' ><a href='javascript:;' onclick='fix("+bid+",&quot;"+bname+"&quot;,&quot;"+author+"&quot;,&quot;"+type+"&quot;,&quot;"+introduce+"&quot;,&quot;"+imgadd+"&quot;,&quot;"+bookadd+"&quot;,"+price+","+totalchapter+")'>修改</a>"+
//     "  <a href='bookdelete.do?bid=${e.bid}&page=${param.page}'>删除</a></td>"+
//     "<td>"+bid+"</td>"+
//     "<td>"+bname+"</td>"+
//     "<td nowrap>"+author+"</td>"+
//     "<td width=30px>"+type+"</td>"+
//     "<td><input readonly='readonly' value='"+introduce+"'></td>"+
//     "<td><input readonly='readonly' value='"+imgadd+"'></td>"+
//     "<td><input readonly='readonly' value='"+bookadd+"'></td>"+
//     "<td>"+price+"</td>"+
//     "<td>"+totalchapter+"</td>";
    
// }
// var xhr=null;
// function submit(bid){//提交函数   改用ajax 因为get信息太长
// var tr=document.getElementById(bid);
// var array=(tr.getElementsByTagName("input"));
//window.location.href="http://127.0.0.1:8081/bookWeb/bookupdate.do?bid="+bid+"&bname="+array[0].value+"&author="+array[1].value+"&type="+array[2].value+"&introduce="+array[3].value+"&imgadd="+array[4].value+"&bookadd="+array[5].value+"&price="+array[6].value+"&totalchapter="+array[7].value+"&page="+page;
// xhr=getXhr();
// xhr.open('post','http://127.0.0.1:8081/bookWeb/bookupdate.do','true');
// xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// xhr.onreadystatechange=f2;
// xhr.send("bid="+bid+"&bname="+array[0].value+"&author="+array[1].value+"&type="+array[2].value+"&introduce="+array[3].value+"&imgadd="+array[4].value+"&bookadd="+array[5].value+"&price="+array[6].value+"&totalchapter="+array[7].value);

// }
// function f2(){
// var page=document.getElementById("page").innerText;
// if(xhr.readyState==4){
// if(xhr.status=200){
// txt=xhr.responseText;
// if(txt==""){
// window.location.href="http://127.0.0.1:8081/bookWeb/bookmanage.do?page="+page;
// alert("修改成功");
// }else{
// alert("提交失败");
// }
// }
// }
// }
function getXhr(){
	if(window.XMLHttpRequest){
		Xhr=new XMLHttpRequest;
		}
	else{
		Xhr=new ActiveXObject('Microsoft.XMLHttp')
		}
		return Xhr;
}
// function add(){//添加 函数   ajax post  请求
// var bname=document.getElementById("bname").value;
// var author=document.getElementById("author").value;
// var introduce=document.getElementById("introduce").value;
// var imgadd=document.getElementById("imgadd").value;
// var bookadd=document.getElementById("bookadd").value;
// var price=document.getElementById("price").value;
// var totalchapter=document.getElementById("totalchapter").value;
// var type=document.getElementById("type").value;
// if(bname==""||type==""||author==""||introduce==""||imgadd==""||bookadd==""||price==""||totalchapter==""){//判断中文且不能为空
// alert("请检查，是否存在空值");
// }
// else{
// xhr=getXhr();
// xhr.open('post','http://127.0.0.1:8081/bookWeb/bookadd.do','true');
// xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// xhr.onreadystatechange=f3;
//window.location.href="http://127.0.0.1:8081/bookWeb/adadd.do?aname="+aname+"&apwd="+apwd+"&atype="+atype+"&page="+page;
// xhr.send("bname="+bname+"&author="+author+"&type="+type+"&introduce="+introduce+"&imgadd="+imgadd+"&bookadd="+bookadd+"&price="+price+"&totalchapter="+totalchapter);
// }
// }
// function f3(){
// var page=document.getElementById("page").innerText;
// if(xhr.readyState==4){
// if(xhr.status=200){
// txt=xhr.responseText;
// if(txt==""){
// window.location.href="http://127.0.0.1:8081/bookWeb/bookmanage.do?page="+page;
// alert("插入成功");
// }else{
// alert("插入失败");
// }
// }
// }
// }

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
  
  <body >
    <table class="gridtable" align="center"    >
    <tr><th nowrap>操作<th>UID</th><th>用户名</th><th>密码</th><th>邮箱</th><th>状态(0代表冻结，1代表可用)</th><th>购物车</th><th>购买记录</th></tr>
    <c:forEach  var='e' items='${list}'>
    <tr id="${e.uid}" align="center" >
    <td id="test" ><a href="javascript:;" onclick="fix(${e.uid})">状态修改</a>
    <a href="userdelete.do?uid=${e.uid}&page=${param.page}">删除</a></td>
     <td>${e.uid}</td>
    <td>${e.uname}</td>
    <td>${e.password}</td>
    <td nowrap>${e.email}</td>
    <td >${e.status}</td>
    <td><a href="usercartmanage.do?uid=${e.uid}&page=1">点击查看</a></td>
    <td><a href="usershoprecordmanage.do?uid=${e.uid}&page=1"">点击查看</a></td>
    </tr>
    </c:forEach>
    
   
    <tr align="center">
     <td></td>
    <td><a href="javascript:;" onclick="lastpage()">上一页</a></td>
    <td><a href="javascript:;" onclick="nextpage()">下一页</a></td>
    <td colspan="5">当前第<span id="page">${param.page}</span>页</td>
    
    </tr>
    </table>
    <div style="text-align:center">
    <a href="bookmanage.do?page=1">图书信息管理</a>
    </div>
  </body>
</html>
