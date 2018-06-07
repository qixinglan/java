<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<ul id="menu">
            	
                <li><a href="${pageContext.request.contextPath}/login/loginSuccess.do" 
                <c:if test="${currentModule==0 }">class="index_on"</c:if> 
                <c:if test="${currentModule!=0 }">class="index_off"</c:if> 
                 ></a></li>
                <c:forEach items="${adminModules}" var="am">
                <c:if test="${am.module_id==1}">
                <li><a href="${pageContext.request.contextPath}/role/findRole.do?currentPage=1" 
                 <c:if test="${currentModule==1 }">class="role_on"</c:if> 
                <c:if test="${currentModule!=1 }">class="role_off"</c:if>></a></li>
                </c:if>
                 <c:if test="${am.module_id==2}">
                <li><a href="${pageContext.request.contextPath}/admin/findAdmin.do?currentPage=1" 
                 <c:if test="${currentModule==2}">class="admin_on"</c:if> 
                <c:if test="${currentModule!=2}">class="admin_off"</c:if>></a></li>
                 </c:if>
                 <c:if test="${am.module_id==3}">
                <li><a href="${pageContext.request.contextPath}/cost/findAllCost.do?currentPage=1" 
                 <c:if test="${currentModule==3}">class="fee_on"</c:if> 
                <c:if test="${currentModule!=3}">class="fee_off"</c:if>></a></li>
                 </c:if>
                 <c:if test="${am.module_id==4}">
                <li><a href="${pageContext.request.contextPath}/account/findAccount.do?currentPage=1"
                  <c:if test="${currentModule==4}">class="account_on"</c:if> 
                <c:if test="${currentModule!=4}">class="account_off"</c:if>></a></li>
                 </c:if>
                 <c:if test="${am.module_id==5}">
                <li><a href="${pageContext.request.contextPath}/service/findService.do?currentPage=1"
                  <c:if test="${currentModule==5}">class="service_on"</c:if> 
                <c:if test="${currentModule!=5}">class="service_off"</c:if>></a></li>
                 </c:if>
                 <c:if test="${am.module_id==6}">
                <li><a href="${pageContext.request.contextPath}" class="bill_off"></a></li>
                 </c:if>
                 <c:if test="${am.module_id==7}">
                <li><a href="${pageContext.request.contextPath}" class="report_off"></a></li>
                 </c:if>
                 </c:forEach>
                <li><a href="user/user_info.html" class="information_off"></a></li>
                <li><a href="user/user_modi_pwd.html" class="password_off"></a></li>
               
</ul>