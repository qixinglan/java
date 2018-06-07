<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit"/>
<title>首页</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/homepage.js"></script>
<script language="javascript"
	src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<%
	String location = "";
	try {
		location = LoginInfoUtils.getOrgType(request.getSession());
	} catch (Exception e) {
		response.sendRedirect("/");
	}
%>
<script language="javascript">
$(function() {
	$.homepage.init('#homepage','<%=location%>');
		//$.homepage.report('#report');
		//$.homepage.fit('#todo','#report');
	if('${user.wunit.bm}' == '1'){
		$.ajax({
		    url:"${ctx}/data/dwjk/cityViewResult.action",
			type:"post",
			async:false,
			success:function(response){	
				var zjryCount=0;
				$(response).each(function(){									
				    var orgid=this[0];
				    if(orgid)
				    {		
				      var select= $(".dwjk-boxsy[id="+orgid+"]");
				      if(select.length>0)
				      {
				        zjryCount+=(this[3]?this[3]:0);
				      }
				      var c=$("<span>"+(this[3]?this[3]:0)+"</span>");
				      select.append(c);			
				    }								    
				});
				$("#zjryCount").append(zjryCount);
			}						    
	  });
	}
	});
</script>
</head>
<body
	style="background:url(${ctx}/images/index/content_bg.jpg) top repeat-x #DDEBF4;">
	<%@include file="top.jsp"%>
	<table cellspacing="0" cellpadding="0" class="homecontent" id="homepage">
	</table>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
