<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
#pg td{
	width: 60px;
}
</style>
<div style="background: url(${ctx}/images/table_bottom_bg.gif) left top repeat-x; height: 35px; width: 100%; text-align: center; font-size: 13px; line-height: 30px; color: #666666;">
	<table width="100%" height="36px" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="30%" style="vertical-align: middle;">
				共&nbsp;${page.totalCount}&nbsp;条记录，共&nbsp;${page.totalPages}&nbsp;页，当前第&nbsp;${page.pageNo}&nbsp;页
			</td>
			<td width="51%" style="vertical-align: middle;">
				<table id="pg" border="0" cellpadding="0" cellspacing="0" style="100%; margin:0 auto; text-align: left; height: 30px;">
					<tr>
						<td><a href="javascript:void(0);" onclick="goPage('1','${page.totalPages}');">首页</a></td>
						<td><a href="javascript:void(0);" onclick="goPage('${page.prePage}','${page.totalPages}');">上一页</a></td>
						<td><a href="javascript:void(0);" onclick="goPage('${page.nextPage }','${page.totalPages}');">下一页</a></td>
						<td><a href="javascript:void(0);" onclick="goPage('${page.totalPages}','${page.totalPages}');">尾页</a></td>
					</tr>
				</table>
			</td>
			<td width="12%" align="right" style="vertical-align: middle;">
				第&nbsp;<span style="text-decoration: underline;"><input type="text" size="2" id="pageNo" value="${page.pageNo }" style="border-left:none;border-right:none;border-top:none;border-bottom:solid 1px;width:25px;"/></span>&nbsp;页&nbsp;&nbsp;
			</td>
			<td width="7%" align="left" style="padding-top:10px; vertical-align: top;">
				<a href="javascript:void(0);" onclick="goPage('0','${page.totalPages}')" style="padding-top: 8px;"><img src="${ctx}/images/go.gif" width="40" height="20" border="0" /></a>
			</td>
		</tr>
	</table>
</div>

