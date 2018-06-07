<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 <!--    <script type="text/javascript">
  /*   window.onload(test()); */
    
    function testo(){
    alert("1");
    }
    function test(){
    alert("1");
    var personO=new Object();
    personO.name='John';
    personO.age=50;
    personO.say=new function(){alert('hello');};
    personO.say();
    alert(personO.age);
    }
     function introduceT(){
    alert(this.name+" ;  "+this.age)
    }
    function persion(n,a){
    this.age=n;
    this.name=a;
    this.showName=function(){(alert(this.name))};
    this.introduce=introduceT();
    }
   
    function test1(){
    var o1=new persion(26,"marry");
    //alert(o1.age);
    //o1.showName();
    //o1.introduceT();
    }
    function a(){
    alert("a");
    }
   var b=function(){
    alert("b");
    }
    var c=new Function("a","b","alert(a-b)");
      
    </script> -->
    <style type="text/css">
    .heightlight{
    	background-color: #aaa;
    }
    </style>
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    var a=12;
    var b="34"
    var d=56;
    alert(parseInt(a+b+d));
    $("button").click(function(){$("p").show("slow").css("color","red").html("asd").append("<p>123</p>");
     alert($("p").val())});
    $("h1").mouseover(function(){
    $(this).addClass("heightlight")});
        $("h1").mouseout(function(){
    $(this).removeClass("heightlight")});
    })
  
   
    </script>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <button  >112</button>
 <p style="display:none">12345678</p>
 <h1>h1</h1>
  </body>
</html>
