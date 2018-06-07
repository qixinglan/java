<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script type="text/javascript" language="javascript" src="../js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
            //删除
            function deleteAccount(id) {
                var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
                if(r){
                	location.href="deleteAccount.do?account_id="+id;
                }
            }
            //开通或暂停
            function setState() {
                var r = window.confirm("确定要开通此账务账号吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //开通
             function openAccount(id) {
                var r = window.confirm("确定要开通此账务账号吗？");
                if(r){
                location.href="updateToOpen.do?account_id="+id;
                }
            }
            //暂停
              function pauseAccount(id) {
                var r = window.confirm("确定要暂停此账务账号吗？");
                if(r){
                location.href="updateToPause.do?account_id="+id;
                }
            }
            // 跳转到目标页面
            function toPage(targetPage) {
            	document.getElementById("targetPage").value=targetPage;
            	document.forms[0].submit();
            }
            //开通
            function startAccount(id) {
            	var r = window.confirm("确定要开通此账务账号吗？");
            	if(!r) return;
            	$.post(
            		"startAccount",
            		{"id":id},
            		function(data){
            			var pass = data;
            			if(pass) {
            				// 如果更新成功，刷新当前页面
            				var targetPage = $("#targetPage").val();
            				toPage(targetPage);
            			} else {
            				alert("开通失败！");
            			}
            		}
            	);
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <jsp:include page="../main/menu.jsp"></jsp:include>    
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="findAccount.do" method="post">
            <!-- 每次查询回到第一页 -->
            	<input type="hidden" name="currentPage" id="targetPage" value="1"/>
                <!--查询-->
                <div class="search_add">                        
                    <div>
                    身份证：
                    
                    	<input type="text" name="idcardno" value="${accountPage.idcardno}" class="text_search" />
                    	
                    </div>                            
                    <div>
                    	姓名：
                    	<input type="text" class="width70 text_search" value="${accountPage.realname}" name="realname" />
                    </div>
                    <div>
                    登录名：
                    	<input type="text" name="loginname" class="text_search" value="${accountPage.loginname}" />
                  </div>
                    <div>
                   状态：
                        <select name="status" class="select_search">
                            <option value="" <c:if test="${empty accountPage.status}" >selected</c:if>>全部</option>
                            <option value="0" <c:if test="${accountPage.status eq '0'}" >selected</c:if>>开通</option>
                            <option value="1" <c:if test="${accountPage.status==1}" >selected</c:if>>暂停</option>
                            <option value="2" <c:if test="${accountPage.status==2}" >selected</c:if>>删除</option>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='addAccount.do';" />
                </div>  
                <!--删除等的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功，且已删除其下属的业务账号！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th>账号ID</th>
                        <th>姓名</th>
                        <th class="width150">身份证</th>
                        <th>登录名</th>
                        <th>状态</th>
                        <th class="width100">创建日期</th>
                        <th class="width150">上次登录时间</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <c:forEach items="${account}" var="a">
	                    <tr>
	                        <td>${a.account_id}</td>
	                        <td><a href="account_detail.html">${a.real_name}</a></td>
	                        <td>${a.idcard_no}</td>
	                        <td>${a.login_name}</td>
	                        <td>
	                        <c:choose>
	                        <c:when test="${a.status==0}">开通</c:when>
	                        <c:when test="${a.status==1}">暂停</c:when>
	                        <c:otherwise>删除</c:otherwise>
	                        </c:choose>
	                        </td>
	                        <td><fmt:formatDate value="${a.create_date}" pattern="yyyy/MM/dd" /></td>
	                        <td>${a.last_login_time}</td>                            
	                        <td class="td_modi">
	                        	<c:choose>
	                        	<c:when test="${a.status==0}">
		                        	<input type="button" value="暂停" class="btn_pause" onclick="pauseAccount(${a.account_id})" />
		                            <input type="button" value="修改" class="btn_modify" onclick="location.href='updateAccount.do?account_id=${a.account_id}'" />
		                            <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${a.account_id})" />
	                        	</c:when>
	                        	<c:when test="${a.status==1}">
	                        		<input type="button" value="开通" class="btn_start" onclick="openAccount(${a.account_id})" />
		                            <input type="button" value="修改" class="btn_modify" onclick="location.href='updateAccount.do?account_id=${a.account_id}'" />
		                            <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(${a.account_id})" />
	                        	</c:when>
	                        	</c:choose>
	                        </td>
	                    </tr>
                       </c:forEach>                
                </table>
                <p>业务说明：<br />
                1、创建则开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、暂停账务账号，同时暂停下属的所有业务账号；<br />                
                6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br />
                7、删除账务账号，同时删除下属的所有业务账号。</p>
                </div>                   
                <!--分页-->
                <div id="pages">
                    <a href="findAccount.do?currentPage=1">首页</a>
                 <c:choose>
                <c:when test="${accountPage.currentPage==1}">
                <a href="#">上一页</a>
                </c:when>
                <c:otherwise>
                <a href="findAccount.do?currentPage=${accountPage.currentPage-1}">上一页</a>
                </c:otherwise>
                </c:choose>
               	<c:forEach begin="1" var="i" end="${accountPage.totalPages }">
               	<c:choose>
               	<c:when test="${i==accountPage.currentPage}">
               	<a href="findAccount.do?currentPage=${i}" class="current_page">${i}</a></c:when>
               	<c:otherwise>
               	<a href="findAccount.do?currentPage=${i}" >${i}</a></c:otherwise>
               	</c:choose>
               	</c:forEach>		
                 <c:choose>
                <c:when test="${accountPage.currentPage==accountPage.totalPages}">
                <a href="#">下一页</a>
                </c:when>
                <c:otherwise>
                <a href="findAccount.do?currentPage=${accountPage.currentPage+1}">下一页</a>
                </c:otherwise>
                </c:choose>    
                    
                    <a href="findAccount.do?currentPage=${accountPage.totalPages}">末页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>