<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
    <title>介绍</title>
   
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                   
                        
     <link href="http://127.0.0.1:8081/bookWeb/css/default_common.css" rel="stylesheet" type="text/css" />
     <script src="../js/jquery.js" type="text/javascript"></script>
    
<script src="http://127.0.0.1:8081/bookWeb/js/common/common-min.js" type="text/javascript"></script>
 <script  type="text/javascript">
    function f1(){
    var value=$("#keyword").val();
    window.location.href="http://127.0.0.1:8081/bookWeb/search.do?keyword="+value+"&currpage=1";
    }
    </script>                     
<script>
function add(){
var a=window.location.href;
a=a.substring(a.lastIndexOf("bid=")+4);//从链接地址中获取bid
window.location.href="http://127.0.0.1:8081/bookWeb/add.do?bid="+a;

}
</script> 
</head>
<!-- 顶部开始 -->
<div class="top">
    <div class="top_wrap">
        <div class="top_logo left"> <a href="http://127.0.0.1:8081/bookWeb/main.do" class="left" title="七星书城"><img width=200 height=50 alt="七星书城" src="http://127.0.0.1:8081/bookWeb/images/logo1.jpg" /></a>
            <div class="serchAll left">
                <div class="s_menu left"></div>
                <div class="s_input left">
                    <input name="keyword" id="keyword" type="text" />
                </div>
               <div class="s_button right" style="padding-top:5px">
                    <a  href="javascript:;" onclick="f1()" style="font-size:1.2em">搜索</a>
                </div>
                <div id="searchSelectDiv" class="search_box left"></div>
            </div>
        </div>
        <div class="top_login right util_bar">
        <%User user=(User)session.getAttribute("user"); %>
        <%if(user==null){%>
            <div class="top_login_links"><a href="http://127.0.0.1:8081/bookWeb/lookcart.do" class="gwc">购物车</a> | </div>
                    <script type="text/javascript">var isLogin= false;</script>
                    <a href="http://127.0.0.1:8081/bookWeb/login/login.html">登录 </a>|
                    <a href="http://127.0.0.1:8081/bookWeb/login/regist.jsp">注册 </a>
         <%}      
        else{%>
        <a href="http://127.0.0.1:8081/bookWeb/mybook.do?u_id=<%=user.getUid()%>&currpage=1">我的</a>|
        <a href="http://127.0.0.1:8081/bookWeb/lookcart.do">购物车</a>|
          <a href="http://127.0.0.1:8081/bookWeb/user/fixuser.jsp"><%=user.getUname()%> </a>|
         <a href="logout.do?p=introduce1&bid=${book.bid}">注销</a>
         <%} %> 
        </div>
    </div>
</div>
<div class="navigation">

<div class="nav_L">
	
</div></div>
<%Book book=(Book)request.getAttribute("book"); %>
<%session.setAttribute("book", book);%>
<div class="titleNav">
  <p>电子书介绍</p>
</div>
<div class="sc_Main">
  <div class="index_content left" >
    <div class="bookDetail">
      <div class="bookMainInfo">
            <div class="left">
<img src="${book.imgadd }" width=201px height=284px /></div>
            <div class="right info">
                <dl>
                    <dt><h1>${book.bname}</h1></dt>
                    <dd class="mt_8 s_value">
                            <div id="discuss_list_wrapper" class="clr">
                                <div class="grade">
                                    <div class="left star">
					
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>
                    </dd>
                    <dd class=""><span>分类： ${book.type}</span></dd>
                    <dd class=""><span>作者：  ${book.author }</span></dd>
                    
                </dl>

                <div class="oinfo">
                    <ul class="oinfoul">
                                <li><em class="price_L">￥${book.price}</em> </li>
                    </ul>
                </div>

                <div class="btn_link">
                                <form action="cart.html" id="goodsform" method="post">
                        <a href="read/read1.jsp"  class="btn_try bpx1">试读</a>
                       
                        <div class="left linkBar">
                            <div class="left linkBar_sc l_rs">
                                <a href="javascript:;" onclick="add()" class="addtocart_btn"  >加入购物车</a> |
                            </div>
                            <div class="left share2">
                            	
								
                            </div>
                        </div>
                    </form>
                </div>
            </div>

                 </div>
      <div class="b_div bookTntro">
        <h2><em>${book.bname}</em> 简介</h2>
        <div class="binfo">
          <h3><p>
	${book.introduce}</p>
</h3>
        </div>
   	 </div>
    </div>
  </div>
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
						<a target="_blank" href="introduce1.do?bid=34">
							<img alt="官场风云" style="width:62px;height:88px;" src="http://127.0.0.1:8081/bookWeb/images/CgoKDFVK2cKAIvciAAAHBbAUUXM799.jpg" />
						</a>
						<div class="info">
							<h2><a target="_blank" href="introduce1.do?bid=34"  title="官场风云">官场风云</a></h2>
							<p> 
								作者：贺纯<br />
		              			分类：职场官场<br />
		            		</p>
						</div>
					</li> 
					<li> 
						<em>2</em> 
						<a target="_blank" href="introduce1.do?bid=35" title="官场达人曾国藩">官场达人曾国藩</a> 
					</li>
					<li> 
						<em>3</em> 
						<a target="_blank" href="introduce1.do?bid=36" title="故乡好">故乡好</a> 
					</li>
					<li> 
						<em>4</em> 
						<a target="_blank" href="introduce1.do?bid=37" title="百年苏商">百年苏商</a> 
					</li>
					<li> 
						<em>5</em> 
						<a target="_blank" href="introduce1.do?bid=38" title="王庸文存">王庸文存</a> 
					</li>
					<li> 
						<em>6</em> 
						<a target="_blank" href="introduce1.do?bid=39" title="小高庄">小高庄</a> 
					</li>
					<li> 
						<em>7</em> 
						<a target="_blank" href="introduce1.do?bid=40" title="生产（第九辑）">生产（第九辑）</a> 
					</li>
					<li> 
						<em>8</em> 
						<a target="_blank" href="introduce1.do?bid=41" title="法界巨擘——倪徽奥传">法界巨擘——倪徽奥传</a> 
					</li>
					<li> 
						<em>9</em> 
						<a target="_blank" href="introduce1.do?bid=43" title="读者新周刊（2015年第14期）">读者新周刊（2015年第...</a> 
					</li>
					<li> 
						<em>10</em> 
						<a target="_blank" href="introduce1.do?bid=44" title="故事中的人生哲理">故事中的人生哲理</a> 
					</li>
		</ul>
      </div>      
      </div>     
    </div>
  </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<div style="text-align:center;"><span><em>客服电话</em>：0592-6300072</span> <span><em>客服邮箱</em>：service_xm@geeboo.cn</span>
<p>Copyright © 2010-2012 本公司版权所有  闽ICP备14013728号 </p></div>
