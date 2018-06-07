<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>市局定位监控</title>
		<%@ include file="/common/commonjs.jsp"%>
		<link rel="stylesheet" href="${ctx }/js/cmap/cmap.css" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-maphilight.min.js"></script>
		<script language="javascript" src="${ctx}/data/dzjg/dwjk/dwjkchart.js"></script>
		<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
	</head>
	<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
	<div class="main" >
		<table cellspacing="0" cellpadding="0" id="content" width="100%">
		</table>
	</div>
	<c:if test="${user.wunit.orgType != '1'}">
		<div style="display:none">
	</c:if>
			<div class="footerFixed">
				<ul class="dwjk-box5">
					<li id="1">北京市</li>
					<li id="181">顺义区</li>
					<li id="182">平谷区</li>
					<li id="183">门头沟区</li>
					<li id="184">通州区</li>
					<li id="185">大兴区</li>
					<li id="186">密云区</li>
					<li id="187">怀柔区</li>
					<li id="188">延庆区</li>
					<li id="190">丰台区</li>
					<li id="192">东城区</li>
					<li id="193">西城区</li>
					<li id="194">石景山区</li>
					<li id="195">海淀区</li>
					<li id="196">朝阳区</li>
					<li id="197">房山区</li>
					<li id="198">昌平区</li>
				</ul>
			</div>
	<c:if test="${user.wunit.orgType != '1'}">
		</div>
	</c:if>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<script language="javascript">
		$(function(){	
			$.menu.init("#menuDetail",{first:"dzjg",second:"dwjk"});
			var cCode = <%=request.getParameter("cCode")%>==null?"":"<%=request.getParameter("cCode")%>";
			$.dwjkchart.init("#content",{cCode:cCode});
			$(".dwjk-box5 li").click(function(){
				$(this).addClass("selected").siblings().removeClass("selected");					
				var countryCode = $(this).attr("id");
				$.dwjkchart.init("#content",{cCode:countryCode});
			});		
			var ORG_NAME= $("[id=\""+cCode+"\"]");	
			ORG_NAME.addClass("selected");
		});     
	</script>
	</body>
</html>
