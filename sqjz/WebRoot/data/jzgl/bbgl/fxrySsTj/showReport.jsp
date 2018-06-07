<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>

<html>
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0>
<%
	String report = (String)request.getParameter("raq");
	StringBuffer param=new StringBuffer();
	String status = request.getParameter("status");
	String view = request.getParameter ("view");
	//保证报表名称的完整性
	int iTmp = 0;
	if( (iTmp = report.lastIndexOf(".raq")) <= 0 ){
		report = report + ".raq";
		iTmp = 0;
	}
	
	Enumeration paramNames = request.getParameterNames();
	if(paramNames!=null){
		while(paramNames.hasMoreElements()){
			String paramName = (String) paramNames.nextElement();
			String paramValue=request.getParameter(paramName);
			if(paramValue!=null){
				//把参数拼成name=value;name2=value2;.....的形式
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}
%>
<div><jsp:include page="../../toolbar.jsp" flush="false" /></div>
<div class="main" style="z-index:-10000">
	<div class="container-bottom">
	    <div id="mainGridPager">
			<table align="center" id="mainGrid" >
				<tr>
					<td>
					<report:html name="report1" reportFileName="<%=report%>"
						funcBarLocation="top"
						saveAsName="各区县矫正人员实时统计表"
						needPageMark="no"
						generateParamForm="no"
						params="<%=param.toString()%>"
						excelPageStyle="0"
						pdfExportStyle="text,0"
						exceptionPage="/reportJsp/myError2.jsp"
						/>
				</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
