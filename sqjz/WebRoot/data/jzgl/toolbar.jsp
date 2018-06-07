<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script language="JavaScript" type="text/javascript">
	function report1SaveFile(type){
		switch(type){
		 case "excel" : report1_saveAsExcel(); //调用润乾的生成文件代码
            			break;
         case "word" : report1_saveAsWord(); //调用润乾的生成文件代码
            		   break;
	     case "pdf" : report1_saveAsPdf(); //调用润乾的生成文件代码
	              	  break;
	 	 case "print" : report1_print(); //调用润乾的打印文件代码
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
				<a href="javascript:void(0);" onClick="report1SaveFile('print');return false;" title="打印"><img border="0" src="${pageContext.request.contextPath}/images/print.gif" /></a>
				<a href="javascript:void(0);" onClick="report1SaveFile('excel');return false;" title="导出EXCEL"><img border="0" src="${pageContext.request.contextPath}/images/excel.gif" /></a>
				<a href="javascript:void(0);" onClick="report1SaveFile('pdf');return false;" title="导出PDF"><img border="0" src="${pageContext.request.contextPath}/images/pdf.gif" /></a>
				<a href="javascript:void(0);" onClick="report1SaveFile('word');return false;" title="导出WORD"><img border="0" src="${pageContext.request.contextPath}/images/doc.gif" /></a>
			  </td>
			</tr>
	  </table>
	</td>
	</tr>
</table>