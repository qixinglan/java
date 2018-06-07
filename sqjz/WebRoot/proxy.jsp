<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.httpclient.methods.GetMethod"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.apache.commons.httpclient.HttpStatus"%>
<%@page import="org.apache.commons.httpclient.HttpState"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@page import="org.apache.commons.httpclient.params.HttpParams"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.httpclient.methods.PostMethod"%>
<%@page import="org.apache.commons.httpclient.HttpMethod"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>

<%@page import="org.apache.commons.httpclient.HttpClient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String url=request.getParameter("url");
HttpClient client=new HttpClient();
HttpMethod method;
if(request.getMethod()=="POST"){
	method=new PostMethod(url);
	HttpMethodParams parms=new HttpMethodParams();
	Map<String,String[]> sendParms=request.getParameterMap();
	for(Map.Entry<String,String[]> entry:sendParms.entrySet()){
		parms.setParameter(entry.getKey(), entry.getValue());
		
	}
	method.setParams(parms);
	int length=request.getContentLength();
	//((PostMethod)method).setRequestHeader("Content-Type", "text/xml; charset=UTF-8");
	((PostMethod)method).setRequestContentLength(length);
	((PostMethod)method).setRequestBody(request.getInputStream());
}
else
{
   method=new GetMethod(url);	
}

try{
	client.executeMethod(method);
	if(method.getStatusCode()==HttpStatus.SC_OK){
	 //byte[] body=method.getResponseBody();
	 //String b=method.getResponseBodyAsString();	 
	 InputStream stream=method.getResponseBodyAsStream();
	 // ServletOutputStream stream=response();
	 //stream.write(body);
	 //stream.close();
	 //response.setContentType("text/xml; charset=GBK");
	 int b;
	 
	 while((b=stream.read())!=-1)
	 {
		out.print((char)b);
	 	//response.getOutputStream().write((char)b);
	 }
	 stream.close();
	
	}
}
catch(Exception ex)
{
	
}
%>