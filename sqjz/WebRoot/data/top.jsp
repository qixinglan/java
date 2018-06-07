	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@ include file="/common/taglibs.jsp" %>
	<%@ page import="com.nci.dcs.common.utils.LoginInfoUtils"%>
	<style>
	ul,li {margin:0; padding:0; font-family:"微软雅黑"; font-size:14px; list-style-type:none;}
	</style>
	<script type="text/javascript">
	$(function() {
		setDate();
	});
	function logout(){
		window.top.location.href= '<%=LoginInfoUtils.getLogoutUrl(session)%>';
	}
	
	function setDate(){
		var date=new Date();
		var time=date.format('yyyy年MM月dd日');
		switch(date.getDay()){
		case 0:
			time=time+" 星期天";
			break;
		case 1:
			time=time+" 星期一";
			break;
		case 2:
			time=time+" 星期二";
			break;
		case 3:
			time=time+" 星期三";
			break;
		case 4:
			time=time+" 星期四";
			break;
		case 5:
			time=time+" 星期五";
			break;
		case 6:
			time=time+" 星期六";
			break;
		}
		$("#time").text(time);
	}
	</script>
<div class="header">
		<h1 class="logo">
			<a href="">
				<img src="${ctx}/images/index/logo.png" alt="">
			</a>
		</h1>
		<ul id="myMenuNew" class="navnew">
			<li id="mynav0" class="navnew-c">
			    <a href="${ctx}/data/index.jsp">
				<span class="navnew-icon navnew-i01"></span>
				<em>工作桌面</em>
				</a>
			</li>
			<li id="bggl">
			   <a href="${ctx}/data/jggl/list.action">
				<span class="navnew-icon navnew-i02"></span>
				<em>办公管理</em>
			   </a>
			</li>
			<li id="sqjz">
			    <c:if test="${user.wunit.orgType == '1'}">
					<a  href="${ctx}/data/jzgl/dagl/zjry.jsp">
					<span class="navnew-icon navnew-i03"></span>
				    <em>矫正管理</em>
				    </a>
				</c:if>
				<c:if test="${user.wunit.orgType == '2'||user.wunit.orgType == '3'}">
					<a  href="${ctx}/data/jzgl/dagl/zjry.jsp">
					<span class="navnew-icon navnew-i03"></span>
				    <em>矫正管理</em>
				    </a>
				</c:if>
			</li>
			<li id="zfdc">
				<c:if test="${user.wunit.orgType == '2'||user.wunit.orgType == '1'}">
					<a href="${ctx}/data/zfdc/supervision.jsp">				
					<span class="navnew-icon navnew-i04"></span>
				    <em>执法督察</em>
				    </a>
				</c:if>
			</li>
			<li id="dzjg">
				<c:if test="${user.wunit.orgType == '1'}">
				<a  href="${ctx}/data/dwjk/cityView.action">				
					<span class="navnew-icon navnew-i05"></span>
				    <em>电子监管</em>
				</a>
				</c:if>
				<c:if test="${user.wunit.orgType == '2'}">
					<a  href="${ctx}/data/dwjk/countryChart.action">					
					<span class="navnew-icon navnew-i05"></span>
				    <em>电子监管</em></a>
				</c:if>
				<c:if test="${user.wunit.orgType == '3'}">
					<a href="${ctx}/data/dwjk/locationDetail.action?orgId=${user.wunit.bm}">					
					<span class="navnew-icon navnew-i05"></span>
				    <em>电子监管</em></a>
				</c:if>
			</li>
			<li id="zhcx">
				<a  href="${ctx}/data/zhcxAll/cityMultipleQuery.action">
				<span class="navnew-icon navnew-i06"></span>
				<em>综合查询</em>
				</a>
			</li>
			<li  id="xtgl">				
			    <c:if test="${roleId!=null&&roleId=='1'}">
				<a href="${ctx}/sys/user/list.action" >
				<span class="navnew-icon navnew-i06"></span>
				<em>系统管理</em>
				</a>
			   </c:if>
			</li>
			<li>
			   <a href="javascript:logout()" >
				<span class="navnew-icon navnew-i07"></span>
				<em>退出管理</em>
				</a>
			</li>
		</ul>
	</div>
	<div class="tips">
		<p>今天是：<span id="time"></span>欢迎您<span>${user.wunit.mc}</span>${user.name}<span style="cursor: default;color: red;">运维电话：58575541</span></p>
	</div>