<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
</head>
<body>
	<div style="width:100%">
		<form action="${ctx }/data/alarm/writeContent.action" method="post"
			id="form1">
			<input type="hidden" name="id" value="<%=request.getParameter("id")%>" />
			<table class="comm-table" style="width:100%;padding:10px;">
				<tr style="height:30px">
	     	 			<td style="border:none">
	     	 				记录内容：
	     	 			</td>
	     	 		</tr>
				<tr>
					<td style="border:none">
						<textarea style="background:white;height: 120px;width:100%"
							id="jlnr" name="jlnr" ></textarea>
					</td>
				</tr>
				<tr>
					<td style="border:none; height:10px; color: red; font-size: 12px;">
						<label id="msg"></label>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>