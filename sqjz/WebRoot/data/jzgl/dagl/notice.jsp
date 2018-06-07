<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<%
	String fxryId = request.getParameter("fxry");
	String raq = request.getParameter("raq");
	String param = fxryId == null ? "fxry=" : "fxry=" + fxryId;
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通知书</title>
	<link rel="stylesheet" href="../style.css" />
</head>
<body>
		<report:html name="report1" reportFileName="<%=raq%>"
			funcBarLocation=""
			needPageMark="yes"
			generateParamForm="no"
			params="<%=param%>"
			width="-1"
			exceptionPage="../../../../error.jsp"
		/>
</body>
</html>
