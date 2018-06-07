<%@ page language="java" pageEncoding="UTF-8"%>
<div>
	<ul class="tabpage-label-container">
		<a href="${ctx}/data/dwjk/locationDetail.action?orgId=<%=request.getParameter("orgId")%>"><li id="tab-menu" class="tabpage-label">人员监控</li></a>
		<a href="${ctx}/data/dzjg/dwjk/dwjk-gis.jsp?orgId=<%=request.getParameter("orgId")%>"><li id="tab-gis" class="tabpage-label">地图监控</li></a>		
	</ul>
	<div style="clear:both;"></div>
</div>
