<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>报表查询</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	function search(){
		var raq = $("#raq").val();
		if(raq!=""){
			var src = "<%=reportUrl%>data/jzgl/showReport.jsp?raq="+raq+"&orgId=${user.wunit.bm }";
			$("#report").attr("src",src);
		}
	}
	function reset(){
		$("#raq").attr("value","");
		$("#bjsj1").attr("value","");
		$("#bjsj2").attr("value","");
	}
</script>
  </head>
  
  <body>
   <%@ include file="/data/top.jsp" %>
    <div class="main" style="height:480px">
       <div class="breadcrumb-nav"><span>您当前的位置: 社区矫正 ->查询统计  ->社区服刑人员统计</span></div>
       <div class="container-top" style="clear:both;padding-left:200px">
       		
       </div>
     	 <div style="width:100%;height:100%">
     	 <c:if test="${user.wunit.bm !=1 }">
     	 	<iframe frameborder="0" scrolling="yes" id="report" src='<%=reportUrl%>data/jzgl/showReport.jsp?raq=qxsfj_tjdata&orgId=${user.wunit.bm }'></iframe>
     	 </c:if>
     	 <c:if test="${user.wunit.bm==1 }">
     	 	<iframe frameborder="0" scrolling="yes" id="report" src='<%=reportUrl%>data/jzgl/showReport.jsp?raq=sfj_tjdata&orgId=${user.wunit.bm }'></iframe>
     	 </c:if>
     	 </div>
   </div>
   <%@include file="/data/bottom.jsp"%>
  </body>
</html>
