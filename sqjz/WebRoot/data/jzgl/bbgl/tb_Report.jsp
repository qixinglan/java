<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>

<html>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/js/dialog/skins/blue.css" />
<script type="text/javascript">
	function reCreateTable(){
		
		window.parent.$.dialog.confirm("ȷ������ͳ�������룿���ȷ������ʧ�޸����ݣ�", function() {
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx}/data/jzgl/bbgl/statfxry/reCreateTable.action",
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
	String view = request.getParameter("view");
	//��֤�������Ƶ�������
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
				//�Ѳ���ƴ��name=value;name2=value2;.....����ʽ
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}

	//���´����Ǽ����������Ƿ�����Ӧ�Ĳ���ģ��
	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));
	String submitImage = " <input type='button'  value='���ɱ���' class='ui_state_highlight'/>";
%>
<jsp:include page="../toolbar.jsp" flush="false" />
<table id=rpt align=center><tr><td>
<%	//�������ģ����ڣ�����ʾ����ģ��
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
		<%if("3".equals(status)||"5".equals(status)||view!=null){ %>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation=""
			saveAsName="����������Ա���ͳ�Ʊ�"
			needPageMark="yes"
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
			userFuncBarElements="<input type='button' onclick='reCreateTable()' value='��������' class='ui_state_highlight'/>"
			submit="<%=submitImage%>"
			saveAsName="����������Ա���ͳ�Ʊ�"
			needPageMark="yes"
			generateParamForm="no"
			params="<%=param.toString()%>"
			excelPageStyle="0"
			pdfExportStyle="text,0"
			width="-1"
			exceptionPage="/reportJsp/myError2.jsp"
			
		/>
		<%} %>
	</td>
	</tr>
</table>
</body>
</html>
