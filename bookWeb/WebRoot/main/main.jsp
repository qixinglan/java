<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
     <title>七星电子书</title>
     <meta charset="utf-8" />
    <script src="http://127.0.0.1:8081/bookWeb/js/common/common-min.js" type="text/javascript"></script>
    <link href="../css/default_common.css" rel="stylesheet" type="text/css" />
    <script  type="text/javascript">
    function f1(){
    var value=$("#keyword").val();
    window.location.href="http://127.0.0.1:8081/bookWeb/search.do?keyword="+value+"&currpage=1";
    }
    </script>

        

</head>
<!-- 顶部开始 -->
<div class="top">
    <div class="top_wrap">
        <div class="top_logo left"> <a href="http://127.0.0.1:8081/bookWeb/main.do" class="left" title="七星书城"><img width=200 height=50 alt="七星书城" src="../images/logo2.jpg" /></a>
            <div class="serchAll left">
                <div class="s_menu left"></div>
                <div class="s_input left">
                    <input name="keyword" id="keyword" type="text" />
                </div>
                <div class="s_button right" style="padding-top:3px">
                    <a  href="javascript:;" onclick="f1()" style="font-size:1.5em">搜索</a>
                </div>
                <div id="searchSelectDiv" class="search_box left"></div>
            </div>
        </div>
        <div class="top_login right util_bar">
        <%User user=(User)session.getAttribute("user"); %>
        <%if(user==null){%>
            <div class="top_login_links"><a href="http://127.0.0.1:8081/bookWeb/lookcart.do" class="gwc">购物车</a> | </div>
                    <script type="text/javascript">var isLogin= false;</script>
                    <a href="../login/login.html">登录 </a>|
                    <a href="../login/regist.jsp">注册 </a>
         <%}      
        else{%>
        <a href="http://127.0.0.1:8081/bookWeb/mybook.do?u_id=<%=user.getUid()%>&currpage=1">我的</a>|
        <a href="http://127.0.0.1:8081/bookWeb/lookcart.do">购物车</a>|
          <a href="http://127.0.0.1:8081/bookWeb/user/fixuser.jsp"><%=user.getUname()%> </a>|
         	<a href="../logout.do?p=main">注销</a>
         <%} %> 
        </div>
    </div>
</div>

<!-- 顶部结束 -->
    
     </script><link  href="../css/a_index.css"  rel="stylesheet" type="text/css" />



<div class="nav_S">
	<div class="sub_nav">
	    <a title="全部" href="http://127.0.0.1:8081/bookWeb/introduce2.do?type=全部&currpage=1">全部</a>
		<a title="小说" href="../introduce2.do?type=小说&currpage=1">小说</a>
		<a title="生活" href="../introduce2.do?type=时尚生活&currpage=1">时尚生活</a>
		<a title="教育" href="../introduce2.do?type=亲子教育&currpage=1">亲子教育</a>
		<a title="传记" href="../introduce2.do?type=人物传记&currpage=1">人物传记</a>
		<a title="经管" href="../introduce2.do?type=经济管理&currpage=1">经济管理</a>
		<a title="成功励志" href="../introduce2.do?type=励志与成长&currpage=1">励志与成长</a>
		<a title="文学艺术" href="../introduce2.do?type=文学艺术&currpage=1">文学艺术</a>
		<a title="社会科学" href="../introduce2.do?type=人文社科&currpage=1">人文社科</a>
        <a title="人在职场" href="../introduce2.do?type=原创文学&currpage=1">原创文学</a>
        <a title="青少年" href="../introduce2.do?type=杂志周刊&currpage=1">杂志周刊</a>
	</div>
</div>

<div class="a_index">
  <div class="a_main left">
 
   
 <link href="../picturesclick/css/reset.css" rel="stylesheet" type="text/css" />
<link href="../picturesclick/css/common.css" rel="stylesheet" type="text/css" />
<link href="../picturesclick/css/facets3.css" rel="stylesheet" type="text/css" />
<link href="../picturesclick/css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../picturesclick/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../picturesclick/js/lanrenzhijia.js"></script>
<script type="text/javascript" src="../picturesclick/js/jquery.clip.js"></script>
<script type="text/javascript" src="../picturesclick/js/jquery.facets.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#main-image-box').children().removeClass('facets').end().facets({
		control: 'ul#mainlevel',
		clipSpacing: 1,
		animationSpeed: 400,
		beforeMax: function(index) {
			$('#main-image-box .clip:eq('+index+') .container').show();
		},
		beforeMin: function(index) {
			$('#main-image-box .clip:eq('+index+') .container').hide();
		}
	});
});
</script>
    <DIV id="imgPlay">
   
      
		<div id="container" style="width:900px">
  <div>
    
    <div id="navigation-panel">
      <div class="menu">
        <ul class="menu" bid="mainlevel">
          <li><a href="../introduce1.do?bid=30">江建强
            <!--[if gte IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
              <li><a  href="../introduce1.do?bid=30">用生命温暖艺术</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
          </li>
          <li><a href="../introduce1.do?bid=103">黄兴
            <!--[if gte IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
              <li><a  href="../introduce1.do?bid=103">没有预约的旅程</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
          </li>
          <li><a href="../introduce1.do?bid=32">王爷制霸
            <!--[if gte IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
              <li><a  href="../introduce1.do?bid=32">不愿让你一个人</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
          </li>
          <li><a href="../introduce1.do?bid=33">孙雄
            <!--[if gte IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
              <li><a  href="../introduce1.do?bid=33">名利场——偷商之嘴脸</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
          </li>
          <li class="last"><a href="../introduce1.do?bid=31">景素奇
            <!--[if gte IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
              <li><a  href="../introduce1.do?bid=31">猎眼看人：从打工者到老板的三级跳</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
          </li>
        </ul>
      </div>
<div id="main-image-box">
        <div class="clip facets" id="strand">
          <div class="container">
            
          </div>
        </div>
        <div class="clip facets" id="wintersport">
          <div class="container">
            
          </div>
        </div>
        <div class="clip facets" id="kreuzfahrt">
          <div class="container">
            
          </div>
        </div>
        <div class="clip facets" id="staedte">
          <div class="container">
            
          </div>
        </div>
        <div class="clip facets" id="abenteuer">
          <div class="container">
           
          </div>
        </div>      
      </div>
    </div>
  </div>

</div>	 
     
    </DIV>
  </div>
  <div class="a_sub right">
    <h4>读书名句</h4>
	
    	<div class="activity"> 
    	  <%@ include file="../mobile/MyHtml.jsp"%>
    	</div>
  </div>
</div>
<!--第一层结束-->
<div class="sc_Main">
  <!--左边排行榜开始-->
  <div class="index_side left"> 
   	<div style="margin-bottom:-35px;"></div>
  	
<div class="layout">
	<div class="title">
	    <div class="left"> <h4>畅销榜</h4></div>
	    <!-- <div class="right"><a href="#" class="more">更多</a></div> -->
	</div>
	<div class="content">
		<ul>
					<li	class="first">
						<em>1</em>
						<a title="风口"  href="../introduce1.do?bid=49">
<img  width="62px" height="88px" src="http://127.0.0.1:8081/bookWeb/images/201504271800281810_thumbnail.jpg"  /></a>
						<div class="info">
							<h2><a title="风口"  href="../introduce1.do?bid=49">风口</a></h2>
							<p> 
								作者：八八众筹<br/>
		              			分类：创业管理
		            		</p>
						</div>
					</li> 
					<li> 
						<em>2</em> 
						<h2><a title="萤火虫广场"  href="../introduce1.do?bid=50">萤火虫广场</a></h2>
					</li>
					<li> 
						<em>3</em> 
						<h2><a title="跟杜拉克学管理"  href="../introduce1.do?bid=51">跟杜拉克学管理</a></h2>
					</li>
					<li> 
						<em>4</em> 
						<h2><a title="用生命温暖艺术——秦怡传"  href="../introduce1.do?bid=30">用生命温暖艺术——秦怡传</a></h2>
					</li>
					<li> 
						<em>5</em> 
						<h2><a title="感动小学生的100个亲情故事"  href="../introduce1.do?bid=52">感动小学生的100个亲情故</a></h2>
					</li>
					<li> 
						<em>6</em> 
						<h2><a title="银海扬帆——于洋传"  href="../introduce1.do?bid=55">银海扬帆——于洋传</a></h2>
					</li>
					<li> 
						<em>7</em> 
						<h2><a title="话说金圣叹"  href="../introduce1.do?bid=56">话说金圣叹</a></h2>
					</li>
					<li> 
						<em>8</em> 
						<h2><a title="动物嘉年华：陆地好朋友"  href="../introduce1.do?bid=57">动物嘉年华：陆地好朋友</a></h2>
					</li>
					<li> 
						<em>9</em> 
						<h2><a title="永恒之阱"  href="../introduce1.do?bid=58">永恒之阱</a></h2>
					</li>
					<li> 
						<em>10</em> 
						<h2><a title="富人生意真经"  href="../introduce1.do?bid=59">富人生意真经</a></h2>
					</li>
		</ul>
	</div>
  </div>


<div class="layout">
	<div class="title">
	    <div class="left"> <h4>本周上升最快</h4></div>
	    <!-- <div class="right"><a href="#" class="more">更多</a></div> -->
	</div>
	<div class="content">
		<ul>
					<li	class="first">
						<em>1</em>
						<a title="每天给自己一个宽慰"  href="../introduce1.do?bid=82">
<img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTknmAVneYAAAIS8Upk9A056.jpg" />						</a>
						<div class="info">
							<h2><a title="每天给自己一个宽慰"  href="../introduce1.do?bid=82">每天给自己一个宽慰</a></h2>
							<p> 
								作者：邱光洪<br />
		              			分类：心灵读物
		            		</p>
						</div>
					</li> 
					<li> 
						<em>2</em> 
						<h2><a title="獠牙"  href="../introduce1.do?bid=83">獠牙</a></h2>
					</li>
					<li> 
						<em>3</em> 
						<h2><a title="口才大全"  href="../introduce1.do?bid=84">口才大全</a></h2>
					</li>
					<li> 
						<em>4</em> 
						<h2><a title="可爱宝贝理财经"  href="../introduce1.do?bid=85">可爱宝贝理财经</a></h2>
					</li>
					<li> 
						<em>5</em> 
						<h2><a title="能受委屈，才是大才"  href="../introduce1.do?bid=86">能受委屈，才是大才</a></h2>
					</li>
					<li> 
						<em>6</em> 
						<h2><a title="瞬间掌控人心"  href="../introduce1.do?bid=87">瞬间掌控人心</a></h2>
					</li>
					<li> 
						<em>7</em> 
						<h2><a title="推手"  href="../introduce1.do?bid=88">推手</a></h2>
					</li>
					<li> 
						<em>8</em> 
						<h2><a title="推销要懂心理学"  href="../introduce1.do?bid=89">推销要懂心理学</a></h2>
					</li>
					<li> 
						<em>9</em> 
						<h2><a title="玩转职场"  href="../introduce1.do?bid=90">玩转职场</a></h2>
					</li>
					<li> 
						<em>10</em> 
						<h2><a title="风口"  href="../introduce1.do?bid=49">风口</a></h2>
					</li>
		</ul>
	</div>
  </div>

<div class="layout">
	<div class="title">
	    <div class="left"> <h4>新书榜</h4></div>
	    
	</div>
	<div class="content">
		<ul>
					<li	class="first">
						<em>1</em>
						<a title="那些年，你我曾擦肩而过"  href="../introduce1.do?bid=60">
<img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkAKAOxT0AAAIZE0PEfI480.jpg" />						</a>
						<div class="info">
							<h2><a title="那些年，你我曾擦肩而过"  href="../introduce1.do?bid=60">那些年，你我曾擦肩而过</a></h2>
							<p> 
								作者：陈晓辉/一路开花<br />
		              			分类：青春文学
		            		</p>
						</div>
					</li> 
					<li> 
						<em>2</em> 
						<h2><a title="寻找春天的百合花"  href="../introduce1.do?bid=61">寻找春天的百合花</a></h2>
					</li>
					<li> 
						<em>3</em> 
						<h2><a title="稀奇的经验"  href="../introduce1.do?bid=62">稀奇的经验</a></h2>
					</li>
					<li> 
						<em>4</em> 
						<h2><a title="老师的承诺"  href="../introduce1.do?bid=63">老师的承诺</a></h2>
					</li>
					<li> 
						<em>5</em> 
						<h2><a title="千里黄金买马尾"  href="../introduce1.do?bid=64">千里黄金买马尾</a></h2>
					</li>
					<li> 
						<em>6</em> 
						<h2><a title="尘埃里的花"  href="../introduce1.do?bid=65">尘埃里的花</a></h2>
					</li>
					<li> 
						<em>7</em> 
						<h2><a title="越简单  越成功"  href="../introduce1.do?bid=66">越简单  越成功</a></h2>
					</li>
					<li> 
						<em>8</em> 
						<h2><a title="交往礼仪"  href="../introduce1.do?bid=67">交往礼仪</a></h2>
					</li>
					<li> 
						<em>9</em> 
						<h2><a title="会喝水，才健康"  href="../introduce1.do?bid=69">会喝水，才健康</a></h2>
					</li>
					<li> 
						<em>10</em> 
						<h2><a title="改变一生的健康习惯"  href="../introduce1.do?bid=70">改变一生的健康习惯</a></h2>
					</li>
		</ul>
	</div>
  </div>
  </div>
  <!--左边排行榜结束-->
  <div class="index_content right">
  	<!-- 名人书单开始 -->
    <div class="layout">
      <div class="title2" style="position:relative;" >
        <span style="font-size:1.5em;font-weight:bold">热门搜索</span>
      </div>
      <div class="content2">
        <ul class="tjsd">
	       
				<li class="mr_16">
				<div class="headInfo"> 
					<a title="改变！改变！自己为什么要去改变？" href="http://127.0.0.1:8081/bookWeb/search.do?keyword=改变自己&currpage=1" class="headImg">
					<img width="220"height="220" src="http://127.0.0.1:8081/bookWeb/images/201504271456432269_286.jpg"  alt="会员头像缩略图" />
		            	<div class="headName"><em class="name">改变自己</em>
		                	<p class="info">学着改变自己，因为自己还不完善</p>
		              	</div>
	              	</a>
	              	<div class="infoM">
		                <p>改变！改变！自己为什么要去</p>
		                <p>因为不满现状，因为有一颗雄心，有一个跟现在环境不能吻合的梦想！</p>
	              	</div>
		        </div>
		        </li>
				<li class="mr_16">
				<div class="headInfo"> 
					<a title="遇见美好" href="http://127.0.0.1:8081/bookWeb/search.do?keyword=遇见&currpage=1" class="headImg">
					<img width="220"height="220" src="http://127.0.0.1:8081/bookWeb/images/201504271604034227_286.jpg"  alt="会员头像缩略图" />
		            	<div class="headName"><em class="name">遇见</em>
		                	<p class="info">遇见，是一种缘分，也许是我们前</p>
		              	</div>
	              	</a>
	              	<div class="infoM">
		                <p>遇见美好</p>
		                <p>如果有一天，你的记忆中没有了我，不要忘记我们相遇的每分每秒。</p>
	              	</div>
		        </div>
		        </li>
				<li class="mr_16">
				<div class="headInfo"> 
					<a title="漫游这个世界" href="http://127.0.0.1:8081/bookWeb/search.do?keyword=漫游这个世界&currpage=1" class="headImg">
					<img width="220"height="220" src="http://127.0.0.1:8081/bookWeb/images/201504241735089387_286.jpg" />
		            	<div class="headName"><em class="name">漫游这个世界</em>
		                	<p class="info">我们一直在旅行,一直在等待某个</p>
		              	</div>
	              	</a>
	              	<div class="infoM">
		                <p>漫游这个世界</p>
		                <p>有人说：“人生至少要有两次冲动：一场奋不顾身的爱情和一段走就走的旅行。”我想在每个人的青春年岁里，都曾</p>
	              	</div>
		        </div>
		        </li>
        </ul>
      </div>
    </div>
    <!-- 名人书单结束 -->
    <!-- 推荐阅读、书单开始 -->
    <div class="layout mt_25 slideTxtBox">
    	<div class="title2" >
				<span style="font-size:1.5em;font-weight:bold">推荐阅读</span>
					
			
	    </div>
        <div class="bd">
        	<div class="content2">
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=105" title="青春弥撒"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkpSAfZQuAAALCnB63S4932.jpg"  /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="青春弥撒"  href="../introduce1.do?bid=105">青春弥撒</a></h2>
            <p class="bookAuthor">
            	<span>作者：顾溆赜</span>
            </p>
          </dd>
          <dd class="bookPrice">￥1.00
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=1" title="你为什么是穷人"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkomAE9ftAAAUSJV2ogA371.jpg" /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="你为什么是穷人"  href="../introduce1.do?bid=1">你为什么是穷人</a></h2>
            <p class="bookAuthor">
            	<span>作者：王阿贵</span>
            </p>
          </dd>
          <dd class="bookPrice">￥2.90
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=106" title="马口铁注"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkqGAA2igAAAOiQF7cII955.jpg"  /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="马口铁注"  href="../introduce1.do?bid=106">马口铁注</a></h2>
            <p class="bookAuthor">
            	<span>作者：恶鸟</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.50
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=107" title="葵花朵朵"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTknKAUbuLAAAQ74AT1Ng441.jpg"  /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="葵花朵朵"  href="../introduce1.do?bid=107">葵花朵朵</a></h2>
            <p class="bookAuthor">
            	<span>作者：吴建雄</span>
            </p>
          </dd>
          <dd class="bookPrice">￥2.20
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=108" title="动作会说话：不可思议的微反应心理学"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkqeAFK8vAAAY27Xone4514.jpg"/></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="动作会说话：不可思议的微反应心理学"  href="../introduce1.do?bid=108">动作会说话：不可思议的微反应心理学</a></h2>
            <p class="bookAuthor">
            	<span>作者：戈阳</span>
            </p>
          </dd>
          <dd class="bookPrice">￥1.80
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=109" title="决定男人一生的密码"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkpuANQnFAAAXZdMAgDA289.jpg" /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="决定男人一生的密码"  href="../introduce1.do?bid=109">决定男人一生的密码</a></h2>
            <p class="bookAuthor">
            	<span>作者：王丹</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.50
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=110" title="决定女人一生的密码"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTkpmAEdb8AAAVeZ4H4uU149.jpg" /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="决定女人一生的密码"  href="../introduce1.do?bid=110">决定女人一生的密码</a></h2>
            <p class="bookAuthor">
            	<span>作者：张玉辉</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.99
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=111" title="老板不说，却默默观察的25件事"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTknOAeIhCAAASKnsNT7c502.jpg" /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="老板不说，却默默观察的25件事"  href="../introduce1.do?bid=111">老板不说，却默默观察的25件事</a></h2>
            <p class="bookAuthor">
            	<span>作者：兰涛</span>
            </p>
          </dd>
          <dd class="bookPrice">￥3.20
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=112" title="没有如意的生活，只有看开的人生"><img src="http://127.0.0.1:8081/bookWeb/images/wKgKnFSTknWAOcDoAAAPCP2QUc4795.jpg"  /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="没有如意的生活，只有看开的人生"  href="../introduce1.do?bid=112">没有如意的生活，只有看开的人生</a></h2>
            <p class="bookAuthor">
            	<span>作者：陶玉立</span>
            </p>
          </dd>
          <dd class="bookPrice">￥2.90
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=113" title="中国房地产战争"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKM1UVJ1CAXkNaAAANlVhTVdk659.jpg"  /></a></dt>
          <dd class="bookTitle" >
          	<h2><a title="中国房地产战争"  href="../introduce1.do?bid=113">中国房地产战争</a></h2>
            <p class="bookAuthor">
            	<span>作者：叶檀</span>
            </p>
          </dd>
          <dd class="bookPrice">￥5.99
          </dd>
        </dl>
	      	</div>
     
      
      </div>
    </div>
    <!-- 推荐阅读、书单结束 -->
    <!-- 获取广告图片宽度 -->
	<!-- 获取广告图片高度 -->
	<!-- 获取专题广告列表 -->
    <div class="a_index_right mt_35">   
    		<a href="../introduce2.do?type=亲子教育&currpage=1" class="left" title="亲子教育"><img  src="http://127.0.0.1:8081/bookWeb/images/7ef4b7da4edd2cf.jpg" width="350px"height="102px"/></a>
    		<a href="../introduce2.do?type=励志与成长&currpage=1" class="right" title="成长励志"><img src="http://127.0.0.1:8081/bookWeb/images/88658PICPI9.jpg" width="350px"height="102px"/></a>
    </div>
    <!-- 最新上架开始 -->
    <div class="layout mt_25">
      <div class="title2" >
        <div class="left" style="font-size:1.5em;font-weight:bold">最新上架</div>
      </div>
      <div class="content2">
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=114" title="高效执行力"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKM1UrO26AO2Q_AAAJdgUoL7g911.jpg"  /></a></dt>
          <dd class="bookTitle" style="height:70px;">
          	<h2><a title="高效执行力"  href="../introduce1.do?bid=114">高效执行力</a></h2>
            <p class="bookAuthor">
            	<span>作者：倪江柏</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.01
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=115" title="富人思维"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKM1UrU6SAFOttAAAMMrxRlSo800.jpg" /></a></dt>
          <dd class="bookTitle" style="height:70px;">
          	<h2><a title="富人思维"  href="../introduce1.do?bid=115">富人思维</a></h2>
            <p class="bookAuthor">
            	<span>作者：白桦</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.01
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=116" title="偏离轨道100天"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKC1VLLuOAfRA_AAAPO69MGgI074.jpg"  /></a></dt>
          <dd class="bookTitle" style="height:70px;">
          	<h2><a title="偏离轨道100天"  href="../introduce1.do?bid=116">偏离轨道100天</a></h2>
            <p class="bookAuthor">
            	<span>作者：李诺萱</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.50
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=131" title="爱无法重来"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKCyAUc589.jpg"  /></a></dt>
          <dd class="bookTitle" style="height:70px;">
          	<h2><a title="爱无法重来"  href="../introduce1.do?bid=131">爱无法重来</a></h2>
            <p class="bookAuthor">
            	<span>作者：王春林</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.50
          </dd>
        </dl>
		<dl class="bookList mr_35">
          <dt><a  href="../introduce1.do?bid=117" title="格言学问类"><img src="http://127.0.0.1:8081/bookWeb/images/CgoKC1VLLruAJBp7AAAIt5pmW5U440.jpg"  /></a></dt>
          <dd class="bookTitle" style="height:70px;">
          	<h2><a title="格言学问类"  href="../introduce1.do?bid=117">格言学问类</a></h2>
            <p class="bookAuthor">
            	<span>作者：范文慧</span>
            </p>
          </dd>
          <dd class="bookPrice">￥0.20
          </dd>
        </dl>
      </div>
    </div>
    <!-- 最新上架结束 -->
    <div class="a_index_right mt_25">    
    		<a href="../introduce2.do?type=经济管理&currpage=1" class="left" title="掌握经济脉搏"><img   src="http://127.0.0.1:8081/bookWeb/images/20140701130826436.jpg" width="350px"height="102px"/></a>
    		<a href="../introduce2.do?type=人物传记&currpage=1" class="right" title="探访名人传记"><img src="http://127.0.0.1:8081/bookWeb/images/38650-382-200762314178.jpg" width="350px"height="102px"/></a>
    </div>
    <div class="layout mt_25">
        <div class="left" style="font-size:1.5em;font-weight:bold">每日作者风采</div>
        
      </div>
      
        
      
    </div>
   
    
  </div>
  <%@ include file="../picturebig/index.html"%>
</div>
  
<br/>
<br/>
<br/>
<br/>
<br/>
<div style="text-align:center;"><span><em>客服电话</em>：0592-6300072</span> <span><em>客服邮箱</em>：service_xm@geeboo.cn</span>
<p>Copyright © 2010-2012 本公司版权所有  闽ICP备14013728号 </p></div>




