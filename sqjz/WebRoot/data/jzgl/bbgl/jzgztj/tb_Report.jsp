<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<%@ page import="java.io.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>

<html>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/js/dialog/skins/blue.css" />
<script type="text/javascript">
	function reCreateTable(){
		
		window.parent.$.dialog.confirm("确认重新统计数据码？点击确定将丢失修改数据！", function() {
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx}/data/jzgl/bbgl/workstat/reCreateTable.action",
				data : "id=<%=request.getParameter("id")%>" ,
				success:function(){
					window.location.reload();
				}
			});
		});
	}
	function checkInt(cell){
		cell =cell +"";
		if(cell.indexOf(".")>0||cell.length>8||cell.indexOf("-")!=-1){
			return false;
		}
		return true;
	}

</script>
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0>
<%
	request.setCharacterEncoding( "GBK" );
	//String report = (String)request.getAttribute( "raq" );
	String report = (String)request.getParameter("raq");
	String reportFileHome=Context.getInitCtx().getMainDir();
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

	//以下代码是检测这个报表是否有相应的参数模板
	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));
	String submitImage = " <input type='button'  value='生成报表' class='ui_state_highlight'/>";
%>
<jsp:include page="../../toolbar.jsp" flush="false" />
<table id=rpt align=center><tr><td>
<%	//如果参数模板存在，则显示参数模板
	if( f.exists() ) {
	%>
	<table id=param_tbl><tr><td>
		<report:param name="form1" paramFileName="<%=paramFile%>"
			needSubmit="no"
			params="<%=param.toString()%>"
			
		/>
	</td>
	<td><a href="javascript:_submit( form1 )"><img src="../images/query.jpg" border=no style="vertical-align:middle"></a></td>
	</tr></table>
	<% }
%>

<table align=center>
	<tr>
		<td>
		<%if("3".equals(status)||"5".equals(status)||view != null){ %>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation=""
			needPageMark="yes"
			saveAsName="社区矫正工作统计表"
			generateParamForm="no"
			params="<%=param.toString()%>"
			excelPageStyle="0"
			pdfExportStyle="text,0"
			width="-1"
			exceptionPage="/reportJsp/myError2.jsp"
		/>
		<%}else{ %>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation="top"
			submit="<%=submitImage%>"
			saveAsName="社区矫正工作统计表"
			needPageMark="yes"
			generateParamForm="no"
			params="<%=param.toString()%>"
			excelPageStyle="0"
			pdfExportStyle="text,0"
			width="-1"
			exceptionPage="/reportJsp/myError2.jsp"
			
			userFuncBarElements="<input type='button' onclick='reCreateTable()' value='重新生成' class='ui_state_highlight' />"
		/>
		<%} %>
	</td>
	</tr>
</table>
</body>
</html>
