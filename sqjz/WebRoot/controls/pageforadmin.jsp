<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
#pg td{
	width: 60px;
}
</style>
<div style="height: 38px; width: 100%; text-align: center; font-size: 13px; line-height: 30px; color: #666666;">
	<table width="99%" height="36px" border="0" cellspacing="0" cellpadding="0">
		<tr style=" background:#E5EFEE; font-size:14px; height:30px; line-height:30px;">
			 <td width="15%" align="right">
			 	 <div style="padding-left:29px; float:left;">共${page.totalCount}条记录</div>		
			 </td>
			 <td width="15%" align="left">
			  <div style="float:left;">第${page.pageNo}/${page.totalPages}页</div>
			   </td>
			 <td width="40%" valign="middle" align="center">
			 	<a href="javascript:void(0);" onclick="goPage('1','${page.totalPages}');">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="goPage('${page.prePage}','${page.totalPages}');">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="goPage('${page.nextPage }','${page.totalPages}');">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="goPage('${page.totalPages}','${page.totalPages}');">尾页</a>
			 </td>
			 <td width="23%" align="right" style="vertical-align:middle;">
			 	第<input type="text" id="pageNo" value="${page.pageNo }" value="1" style="width:42px; text-align:center;" />页&nbsp;&nbsp;
			 </td>
			 <td width="7%" align="left" style="vertical-align:middle;">
			 	&nbsp;&nbsp;<a href="javascript:void(0);" onclick="goPage('0','${page.totalPages}')"><img src="${ctx}/images/go.jpg" style="padding-top:4px;" border="0"/></a>
			 </td>
		</tr>
	</table>
</div>