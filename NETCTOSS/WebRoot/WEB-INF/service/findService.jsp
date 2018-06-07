<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
//             删除
//             function deleteAccount() {
//                 var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
//                 document.getElementById("operate_result_info").style.display = "block";
//             }
 //        	   开通或暂停
//             function setState() {
//                 var r = window.confirm("确定要开通此业务账号吗？");
//                 document.getElementById("operate_result_info").style.display = "block";
//             }
			//开通
			function open_Service(id){
				var r = window.confirm("确定要开通此业务账号吗？");	
				if(r){
					$.post(
					"updateToOpen.do",
					{"service_id":id},
					function(date){
						$("#showinf").text(date.message);
						$("#operate_result_info").show("slow");
						
						setTimeout(function(){
						if(date.boolean){
						location.href="findService.do";}
						},1000);
						
					}
					);
				}
			}
			//暂停
			function pause_Service(id){
				var r = window.confirm("确定要暂停此业务账号吗？");	
				if(r){
					$.post(
					"updateToPause.do",
					{"service_id":id},
					function(date){
						if(date.boolean){
						$("#showinf").text(date.message);
						$("#operate_result_info").show("slow");
						setTimeout(function(){
						location.href="findService.do";
						},1000);
						}
					}
					);
				}
			}
			//删除
			function delete_Service(id){
				var r = window.confirm("确定要删除此业务账号吗？");	
				if(r){
					$.post(
					"updateToDelete.do",
					{"service_id":id},
					function(date){
						if(date.boolean){
						$("#showinf").text(date.message);
						$("#operate_result_info").show("slow");
						setTimeout(function(){
						location.href="findService.do";
						},1000);
						}
					}
					);
				}
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
            <form action="findService.do" method="post">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<input type="text"  class="width100 text_search" name="os_username" value="${servicePage.os_username}"/></div>                            
                    <div>服务器 IP：<input type="text"  class="width100 text_search" name="unix_host" value="${servicePage.unix_host}"/></div>
                    <div>身份证：<input type="text"   class="text_search" name="idcard_no" value="${servicePage.idcard_no}"/></div>
                    <div>状态：
                        <select class="select_search" name="status" >
                            <option value=""  <c:if test="${empty servicePage.status}">selected</c:if>>全部</option>
                            <option value="0" <c:if test="${servicePage.status=='0'}">selected</c:if>>开通</option>
                            <option value="1" <c:if test="${servicePage.status==1}">selected</c:if>>暂停</option>
                            <option value="2" <c:if test="${servicePage.status==2}">selected</c:if>>删除</option>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='addService.do';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                                                                      <span id="showinf"></span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                    <c:forEach var="map" items="${list}">
                    <tr>
                        <td><a href="service_detail.html" title="查看明细">${map.SERVICE_ID}</a></td>
                        <td>${map.ACCOUNT_ID}</td>
                        <td>${map.IDCARD_NO}</td>
                        <td>${map.REAL_NAME}</td>
                        <td>${map.OS_USERNAME}</td>
                        <td>
                        <c:if test="${map.STATUS==0}">开通</c:if>
                        <c:if test="${map.STATUS==1}">暂停</c:if>
                        <c:if test="${map.STATUS==2}">删除</c:if>
                        </td>
                        <td>${map.UNIX_HOST}</td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${map.NAME}</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                ${map.DESCR}
                            </div>
                        </td>                            
                        <td class="td_modi">
                     			<c:choose>
	                        	<c:when test="${map.STATUS==0}">
		                        	<input type="button" value="暂停" class="btn_pause" onclick="pause_Service(${map.SERVICE_ID})" />
		                            <input type="button" value="修改" class="btn_modify" onclick="location.href='updateService.do?service_id=${map.SERVICE_ID}'" /> 
		                            <input type="button" value="删除" class="btn_delete" onclick="delete_Service(${map.SERVICE_ID})" />
	                        	</c:when>
	                        	<c:when test="${map.STATUS==1}">
	                        		<input type="button" value="开通" class="btn_start" onclick="open_Service(${map.SERVICE_ID});" />
		                            <input type="button" value="修改" class="btn_modify" onclick="location.href='updateService.do?service_id=${map.SERVICE_ID}'" />
	                            	<input type="button" value="删除" class="btn_delete" onclick="delete_Service(${map.SERVICE_ID})" />
	                        	</c:when>
	                        	</c:choose>
                        </td>
                    </tr>
                     </c:forEach>                                                            
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
                    <a href="findService.do?currentPage=1">首页</a>
                 <c:choose>
                <c:when test="${servicePage.currentPage==1}">
                <a href="#">上一页</a>
                </c:when>
                <c:otherwise>
                <a href="findService.do?currentPage=${servicePage.currentPage-1}">上一页</a>
                </c:otherwise>
                </c:choose>
               	<c:forEach begin="1" var="i" end="${servicePage.totalPages }">
               	<c:choose>
               	<c:when test="${i==servicePage.currentPage}">
               	<a href="findService.do?currentPage=${i}" class="current_page">${i}</a></c:when>
               	<c:otherwise>
               	<a href="findService.do?currentPage=${i}" >${i}</a></c:otherwise>
               	</c:choose>
               	</c:forEach>		
                 <c:choose>
                <c:when test="${servicePage.currentPage==servicePage.totalPages}">
                <a href="#">下一页</a>
                </c:when>
                <c:otherwise>
                <a href="findService.do?currentPage=${servicePage.currentPage+1}">下一页</a>
                </c:otherwise>
                </c:choose>    
                    
                    <a href="findService.do?currentPage=${servicePage.totalPages}">末页</a>
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
