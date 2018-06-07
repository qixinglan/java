<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script language="JavaScript" type="text/javascript">
	function <%=request.getParameter("reportName") %>SaveFile(type){
		switch(type){
		 case "excel" : <%=request.getParameter("reportName") %>_saveAsExcel(); //调用润乾的生成文件代码
            			break;
         case "word" : <%=request.getParameter("reportName") %>_saveAsWord(); //调用润乾的生成文件代码
            		   break;
	     case "pdf" : <%=request.getParameter("reportName") %>_saveAsPdf(); //调用润乾的生成文件代码
	              	  break;
	 	 case "print" : <%=request.getParameter("reportName") %>_print(); //调用润乾的打印文件代码
	              		break;
		}	
	}
</script>

<table id="titleTable" width="100%" cellspacing="0" cellpadding="0" border="0" >
	<tr>
	<td nowrap="nowrap"  height="22" width="100%" valign="middle"  style="font-size:13px" background="../images/ta51top.jpg">
		<table width="100%">
			<tr >
				<td nowrap="nowrap"  width="86%" align="left"  style="font-size:13px" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td nowrap="nowrap"  width="14%" align="center" valign="middle"   style="font-size:12px" > <!-- 共<span id="t_page_span"></span>页/第<span id="c_page_span"></span>页&nbsp;&nbsp; -->
				<a href="javascript:void(0);" onClick="<%=request.getParameter("reportName") %>SaveFile('print');return false;" title="打印"><img border="0" src="${ctx }/images/print.gif" /></a>
				<a href="javascript:void(0);" onClick="<%=request.getParameter("reportName") %>SaveFile('excel');return false;" title="导出EXCEL"><img border="0" src="${ctx }/images/excel.gif" /></a>
				<a href="javascript:void(0);" onClick="<%=request.getParameter("reportName") %>SaveFile('pdf');return false;" title="导出PDF"><img border="0" src="${ctx }/images/pdf.gif" /></a>
				<a href="javascript:void(0);" onClick="<%=request.getParameter("reportName") %>SaveFile('word');return false;" title="导出WORD"><img border="0" src="${ctx }/images/doc.gif" /></a>
			  </td>
			</tr>
	  </table>
	</td>
	</tr>
</table>