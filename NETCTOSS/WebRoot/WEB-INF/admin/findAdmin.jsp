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
            //重置密码
            function resetPwd() {
                var a=$(":checkbox[name='checkbox123']:checked");
                var b=new Array();
                if(a.length>0){
                    var r=window.confirm("确认要重置密码吗？")
                    if(r){
		                for(var i=0;i<a.length;i++){
		                b.push(a.eq(i).parent().next().text());
		                }
		                $.post(
			                "resettingPassword.do",
			                {"str":b.toString()},
			                function(date){
				                if(date){
				                	window.alert("重置成功");
				                }else{
				                	window.alert("重置失败，未选择管理员");
				                }
				            }
		                )
	                }
	            }
                
            }
            //删除
            function deleteAdmin() {
                var r = window.confirm("确定要删除此管理员吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
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
            <form action="findAdmin.do" method="post">
                <!--查询-->
                <!-- 每次搜索回到第一页 -->
                <input type="hidden" name="currentPage" value="1"/>
                <div class="search_add">
                    <div>
                               模块：
                        <select id="selModules" class="select_search" name="moduleId">
                        <option  value="" >全部</option>
                        <c:forEach items="${modules}" var="m">
                        <option  value="${m.module_id}" <c:if test="${m.module_id==adminPage.moduleId}">selected</c:if>    > ${m.name}</option>
                        </c:forEach>
                        </select>
                    </div>
                    <div>角色：<input type="text" value="${adminPage.roleName}" class="text_search width200" name="roleName"/></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='addAdmin.do';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>   
                        <c:forEach items="${admin}" var="a">                
                        <tr>
                            <td><input type="checkbox" name="checkbox123"/></td>
                            <td>${a.admin_id}</td>
                            <td>${a.name}</td>
                            <td>${a.admin_code}</td>
                            <td>${a.telephone }</td>
                            <td>${a.email}</td>
                            <td>${a.enrolldate}</td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
                              <c:choose>
                              <c:when test="${a.roles.size()>1}">${a.roles.get(0).name}...</c:when>
                              <c:when test="${a.roles.size()==1}">${a.roles.get(0).name}</c:when>
                              <c:otherwise></c:otherwise>
                              </c:choose>
                                </a>
                                <!--浮动的详细信息-->
                                <div class="detail_info">
                                   <c:forEach items="${a.roles}" varStatus="s" var="ar">
                              <c:choose>
                              <c:when test="${s.last}">${ar.name}</c:when>
                              <c:otherwise>${ar.name}、</c:otherwise>
                              </c:choose>
                              </c:forEach>
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='updateAdmin.do?admin_id=${a.admin_id}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="location.href='deleteAdmin.do?admin_id=${a.admin_id}';" />
                            </td>
                        </tr>
                       </c:forEach>   
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
        	          <c:choose>
                <c:when test="${adminPage.currentPage==1}">
                <a href="#">上一页</a>
                </c:when>
                <c:otherwise>
                <a href="findAdmin.do?currentPage=${adminPage.currentPage-1}">上一页</a>
                </c:otherwise>
                </c:choose>
               	<c:forEach begin="1" var="i" end="${adminPage.totalPages }">
               	<c:choose>
               	<c:when test="${i==adminPage.currentPage}">
               	<a href="findAdmin.do?currentPage=${i}" class="current_page">${i}</a></c:when>
               	<c:otherwise>
               	<a href="findAdmin.do?currentPage=${i}" >${i}</a></c:otherwise>
               	</c:choose>
               	</c:forEach>		
                 <c:choose>
                <c:when test="${adminPage.currentPage==adminPage.totalPages}">
                <a href="#">下一页</a>
                </c:when>
                <c:otherwise>
                <a href="findAdmin.do?currentPage=${adminPage.currentPage+1}">下一页</a>
                </c:otherwise>
                </c:choose>  
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
