<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <title>空的购书记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" > </meta>
               <link href="" rel="shortcut icon" >
                       
               <link href="http://127.0.0.1:8081/bookWeb/css/default_common.css" rel="stylesheet" type="text/css" />


</head>
<!-- 分页算法 -->


%>
<!-- 顶部开始 -->
<div class="top">
    <div class="top_wrap">
         <div class="top_logo left"> <a href="http://127.0.0.1:8081/bookWeb/main.do" class="left" title="七星书城"><img width=200 height=50 alt="七星书城" src="http://127.0.0.1:8081/bookWeb/images/logo1.jpg" /></a>
            <div class="serchAll left">
                <div class="s_menu left"></div>
                <div class="s_input left">
                    <input name="keyword" id="keyword" type="text"/>
                </div>
                <div class="s_button right">
                    <input type="button" name="s_seek" class="s_seek" value="" id="searchBtn">
                </div>
                <div id="searchSelectDiv" class="search_box left"></div>
            </div>
        </div>
        <div class="top_login right util_bar">
            <%User user=(User)session.getAttribute("user"); %>
        <%if(user==null){%>
            <div class="top_login_links"><a href="http://127.0.0.1:8081/bookWeb/lookcart.do" class="gwc">购物车</a>| </div>
                    <script type="text/javascript">var isLogin= false;</script>
                    <a href="http://127.0.0.1:8081/bookWeb/login/login.html">登录 </a>|
                    <a href="http://127.0.0.1:8081/bookWeb/login/regist.jsp">注册 </a>
         <%}      
        else{%>
        <a href="http://127.0.0.1:8081/bookWeb/lookcart.do" class="gwc">购物车</a>|
          <a href="http://127.0.0.1:8081/bookWeb/user/fixuser.jsp"><%=user.getUname()%> </a>|
           <a href="logout.do?p=main">注销</a>
         <%} %> 
        </div>
    </div>
</div>
<!-- 集合结束 -->

<!--商品筛选-->
	
	<!-- 创建商品分类标签 -->
<!-- 调用商品分类信息 -->
	<div class="sc_navigation">
  <div class="sc_nav_wrap">
    <div class="selectdiv1 slideTxtBox3">
      <div id="divBig" class="selectdiv"> <span class="selectmz">作品大类</span>
      <div class="hd">
        <ul>
        <li ><a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=全部&currpage=1">全部</a></li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=小说&currpage=1" title="小说">小说</a>		
		</li>
		<li   >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=时尚生活&currpage=1" title="时尚生活">时尚生活</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=亲子教育&currpage=1" title="亲子教育">亲子教育</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=人物传记&currpage=1" title="人物传记">人物传记</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=经济管理&currpage=1" title="经济管理">经济管理</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=励志与成长&currpage=1" title="励志与成长">励志与成长</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=文学艺术&currpage=1" title="文学艺术">文学艺术</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=人文社科&currpage=1" title="人文社科">人文社科</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=原创文学&currpage=1" title="原创文学">原创文学</a>		
		</li>
		<li >
		<a href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=杂志周刊&currpage=1" title="杂志周刊">杂志周刊</a>		
		</li>
        </ul>
        </div>
      </div>    
      
    </div>
    

<!--商品筛选结束-->


<div class="sc_Main">
  <div class="index_content left" >
 <div class="layout" style=" position:relative;">
<div class="title" style="margin-top:0px;">
        <div class="left">
          <h2>您的图书为空                   <a href="http://127.0.0.1:8081/bookWeb/main/main.jsp">主页</a></h2>
        </div>
        <div class="right">   
        </div> 
      </div>

<!--商品列表-->
<div class="subcontent">

 

<div style="font-size:1.3em">

</div>

	
</div>
</div>
</div>

	<!--商品列表右侧推荐商品-->
	 <div class="index_side right">
     <div class="layout slideTxtBox1">
    
    <div class="title3 ">
        <div class="left hd"><ul><li class="on">热门榜</li></ul></div>
      </div>
      <div >
      <div class="content bd">     
        <ul>
					<li	class="first">
						<em>1</em>
						<a target="_blank" href="../introduce1.do?bid=34">
							<img alt="官场风云" style="width:62px;height:88px;" src="http://127.0.0.1:8081/bookWeb/images/CgoKDFVK2cKAIvciAAAHBbAUUXM799.jpg" />
						</a>
						<div class="info">
							<h2><a target="_blank" href="../introduce1.do?bid=34"  title="官场风云">官场风云</a></h2>
							<p> 
								作者：贺纯<br />
		              			分类：职场官场<br />
		            		</p>
						</div>
					</li> 
					<li> 
						<em>2</em> 
						<a target="_blank" href="../introduce1.do?bid=35" title="官场达人曾国藩">官场达人曾国藩</a> 
					</li>
					<li> 
						<em>3</em> 
						<a target="_blank" href="../introduce1.do?bid=36" title="故乡好">故乡好</a> 
					</li>
					<li> 
						<em>4</em> 
						<a target="_blank" href="../introduce1.do?bid=37" title="百年苏商">百年苏商</a> 
					</li>
					<li> 
						<em>5</em> 
						<a target="_blank" href="../introduce1.do?bid=38" title="王庸文存">王庸文存</a> 
					</li>
					<li> 
						<em>6</em> 
						<a target="_blank" href="../introduce1.do?bid=39" title="小高庄">小高庄</a> 
					</li>
					<li> 
						<em>7</em> 
						<a target="_blank" href="../introduce1.do?bid=40" title="生产（第九辑）">生产（第九辑）</a> 
					</li>
					<li> 
						<em>8</em> 
						<a target="_blank" href="../introduce1.do?bid=41" title="法界巨擘——倪徽奥传">法界巨擘——倪徽奥传</a> 
					</li>
					<li> 
						<em>9</em> 
						<a target="_blank" href="../introduce1.do?bid=43" title="读者新周刊（2015年第14期）">读者新周刊（2015年第...</a> 
					</li>
					<li> 
						<em>10</em> 
						<a target="_blank" href="../introduce1.do?bid=44" title="故事中的人生哲理">故事中的人生哲理</a> 
					</li>
		</ul>
      </div>      
      </div>     
    </div>
    </div>
  </div>
	<!--商品列表右侧推荐商品结束-->
	</div>

<!--商品列表结束-->
	
<br/>
<br/>
<br/>
<br/>
<br/>
<div style="text-align:center;"><span><em>客服电话</em>：0592-6300072</span> <span><em>客服邮箱</em>：service_xm@geeboo.cn</span>
<p>Copyright © 2010-2012 本公司版权所有  闽ICP备14013728号 </p></div>