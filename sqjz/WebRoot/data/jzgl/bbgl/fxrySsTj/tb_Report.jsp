<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<%@ page import="java.io.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>


<html>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"bbgl",third : "fxryRealTimeReport"});
	});
	

</script>
<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<%
	reportUrl += "data/jzgl/bbgl/fxrySsTj/showReport.jsp?";
	reportUrl += request.getQueryString();
%>
<iframe src="<%=reportUrl%>"></iframe>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
</body>
</html>
