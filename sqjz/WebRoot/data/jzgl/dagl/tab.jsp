<%@ page language="java" pageEncoding="UTF-8"%>
<div>
	<ul class="tabpage-label-container">
		<!--<c:if test="${user.wunit.orgType =='3' || user.wunit.orgType =='2'}">
			<a href="rcgztx.jsp"><li id="tab-rcgztx" class="tabpage-label">日常工作提醒</li>
		</c:if>-->
		<a href="zjry.jsp"><li id="tab-zjry" class="tabpage-label">在矫人员</li></a>
		<a href="tgry.jsp"><li id="tab-tgry" class="tabpage-label">脱管人员</li></a>		
		<!--<a href="zjwxbry.jsp"><li id="tab-zjwxbry" class="tabpage-label">暂监外续保人员</li></a>
		<a href="yjjry.jsp"><li id="tab-yjjry" class="tabpage-label">预解矫人员</li></a>-->
		<c:if test="${user.wunit.orgType =='3' || user.wunit.orgType =='2'}">
	    <!--<a href="dzrry.jsp"><li id="tab-dzrry" class="tabpage-label">待转入人员</li></a>-->
		<a href="yzcry.jsp"><li id="tab-yzcry" class="tabpage-label">已转出人员</li></a>
		</c:if>
		<a href="jjry.jsp"><li id="tab-jjry" class="tabpage-label">解矫人员</li></a>
		<a href="jcqk.jsp"><li id="tab-jcqk" class="tabpage-label">奖惩信息</li></a>
		<a href="wfqk.jsp"><li id="tab-wfqk" class="tabpage-label">违法信息</li></a>
		<c:if test="${user.wunit.orgType =='3' || user.wunit.orgType =='2'}">
		<a href="qjxx.jsp"><li id="tab-qjxx" class="tabpage-label">请假信息</li></a>
		</c:if>
		<!--<c:if test="${user.wunit.orgType =='1'}">
		<a href="rysb.jsp"><li id="tab-rysb" class="tabpage-label">人员上报</li></a>
		</c:if>-->
	</ul>
	<div style="clear:both;"></div>
</div>
