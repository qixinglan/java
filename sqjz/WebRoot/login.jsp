<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nci.dcs.common.utils.LoginInfoUtils"%>
<%	
	
	response.sendRedirect(request.getContextPath() + LoginInfoUtils.getLoginUrl(session));
%>