<%@ page language="java" pageEncoding="UTF-8"%>
<div>
	<ul class="tabpage-label-container">
		<a href="jbsf.jsp"><li id="tab-jbsf" class="tabpage-label">基本身份信息</li></a>
		<a href="flws.jsp"><li id="tab-flws" class="tabpage-label">法律文书</li></a>		
		<a href="xfzx.jsp"><li id="tab-xfzx" class="tabpage-label">刑罚执行信息</li></a>
		<a href="jjxx.jsp"><li id="tab-jjxx" class="tabpage-label">解矫信息</li></a>
		<a href="dzjg.jsp"><li id="tab-dzjg" class="tabpage-label">电子监管信息</li></a>
		
		
		<%-- 
		<a href="zjry.jsp"><li id="tab-zjry" class="tabpage-label">在矫人员</li></a>
		<a href="tgry.jsp"><li id="tab-tgry" class="tabpage-label">脱管人员</li></a>		
		<c:if test="${user.wunit.orgType =='3' || user.wunit.orgType =='2'}">
		<a href="yzcry.jsp"><li id="tab-yzcry" class="tabpage-label">已转出人员</li></a>
		</c:if>
		<a href="jjry.jsp"><li id="tab-jjry" class="tabpage-label">解矫人员</li></a>
		<a href="jcqk.jsp"><li id="tab-jcqk" class="tabpage-label">奖惩信息</li></a>
		<a href="wfqk.jsp"><li id="tab-wfqk" class="tabpage-label">违法信息</li></a>
		<c:if test="${user.wunit.orgType =='3' || user.wunit.orgType =='2'}">
		<a href="qjxx.jsp"><li id="tab-qjxx" class="tabpage-label">请假信息</li></a>
		</c:if> 
		--%>
	</ul>
	<div style="clear:both;"></div>
</div>
