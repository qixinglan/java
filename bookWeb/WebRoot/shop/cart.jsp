<%@ page language="java" import="java.util.*,dao.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
    <title>购物车</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8" > </meta>
                    <link href="" rel="shortcut icon" >
                        <script src="http://127.0.0.1:8081/bookWeb/js/common/common-min.js" type="text/javascript"></script>
                        
                        <link href="http://127.0.0.1:8081/bookWeb/css/default_common.css" rel="stylesheet" type="text/css" />

 

</head>


<!-- 获取会员基本信息 -->

      <div class="gwc_Main">
          <div class="gwc_title">
              <h2>我的购物车</h2>
          </div>
          <form class="validate" id="checkoutform">
              <div id="new_cart_wrapper" class="gwc_box">
                  <table border="0" cellpadding="0" cellspacing="0" class="g_table">
                      <tr class="tbg">
                          <td class="cl01"><div class="g_chkbx">
                              
                          </div></td>
                          <td>购买信息</td>
                          <td class="cl03">单价</td>
                         
                         
                          <td class="cl05">操作</td>
                      </tr>
                      <%if(session.getAttribute("cart")!=null){ %>
                      <c:forEach var="e" items="${cart.items}">
                      
                          <tr >
                          <td class="cl01">
                              
                          </td>
                          <td>
                              <div class="bif">
                                  <a href="#" class="img"  ><img src="${e.imgadd }"/></a>
                                  <div class="info">
                                      <h4><a href="#"  >${e.bname }</a></h4>
                                      <p class="author"><span></span></p>
                                  </div>
                              </div>
                          </td>
                          <td class="cl03">${e.price}</td>
                          
                          <td class="cart_last cl05"><a href="http://127.0.0.1:8081/bookWeb/delete.do?bid=${e.bid}" >删除</a></td>
                          </tr>
                          
                          
                         </c:forEach> 
                         
                                                         </table>
                  <div class="g_tbox">
                      <div class="g_chkbx left">
                          
                          <a href="http://127.0.0.1:8081/bookWeb/deleteall.do">清空</a>
                      </div>
                      <div class="right total">
                         	 共<label id="orderNumTotal">${cart.items.size()}</label>本，总计
                          <i class="price"><label id="orderPriceTotal"><%=((Cart)session.getAttribute("cart")).jijia()%></label></i>
                      </div>
                  </div>
                  <%} %>
                  <div class="nextStep">
                      <a href="http://127.0.0.1:8081/bookWeb/main/main.jsp" class="left">< 再逛逛</a>
                      <div class="js right">
                          <a href="http://127.0.0.1:8081/bookWeb/buy.do"  >去结算</a>
                      </div>
                  </div>
              </div>
          </form>
      </div>

              


