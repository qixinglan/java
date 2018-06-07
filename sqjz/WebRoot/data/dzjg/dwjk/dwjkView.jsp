<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定位监控详细资料</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<!--Tabbed组件start -->
<style rel="stylesheet">
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0
}
</style>
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/SpryTabbedPanels/SpryTabbedPanels.css" />
<script type="text/javascript" language="javascript"
	src="${ctx}/js/SpryTabbedPanels/SpryTabbedPanels.js"></script>
<!--Tabbed组件start -->

</head>
<body>
	<table class="comm-table">
		<tbody>
			<tr>
				<td>
					<div id="tabbedPanels1"
						class="TabbedPanels tabbed extraMarginY extraMarginX">
						<ul class="TabbedPanelsTabGroup">
							<li class="TabbedPanelsTab" tabindex="0">基本信息</li>
							<li class="TabbedPanelsTab" tabindex="1">报警信息</li>
							<li class="TabbedPanelsTab" tabindex="2">历史轨迹</li>
						</ul>
						<div class="TabbedPanelsContentGroup">
							<div id="tabbedPanel1" class="TabbedPanelsContent"
								style="height:550px ;width:1000px">
								<iframe id="Iframe1"
									src="jbxx.jsp?id=<%=request.getParameter("id") %>"
									style="height:100%" frameborder="0"> </iframe>
							</div>
							<div id="tabbedPanel3" class="TabbedPanelsContent"
								style="height:550px ;width:1000px">
								<iframe id="Iframe3"
									src="bjxxList.jsp?id=<%=request.getParameter("id")%>"
									style="height:100%" frameborder="0"> </iframe>
							</div>
							<div id="tabbedPanel2" class="TabbedPanelsContent"
								style="height:550px; width:1000px">
								<iframe id="Iframe2"
									src="history.jsp?fxryid=<%=request.getParameter("id")%>"
									style="height:100%" frameborder="0"> </iframe>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript">
		var api = frameElement.api, W = api.opener;
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("tabbedPanels1");
	</script>
</body>
</html>