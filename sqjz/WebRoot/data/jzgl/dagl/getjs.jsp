<%@ page language="java" import="java.lang.System,com.nci.dcs.common.utils.LoginInfoUtils" pageEncoding="UTF-8"%>
<%
	String location = "";
	try{
		location = LoginInfoUtils.getOrgType(request.getSession());
		location += "/";
	}
	catch (Exception e){
		response.sendRedirect("/");
	}
%>
<script language="javascript" src="${ctx}/data/jzgl/dagl/config.js"></script>
<script language="javascript" src="${ctx}/data/jzgl/dagl/<%=location%>config.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/data/jzgl/dagl/<%=location%>tabpage.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/data/jzgl/dagl/<%=location%>actions.js" type="text/javascript"></script>

