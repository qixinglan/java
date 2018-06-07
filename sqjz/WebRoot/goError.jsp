<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>系统提示</title>
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>

<style type="text/css">
.cuowu1{
        background:url(${ctx}/images/cuowu_mid.gif) repeat-y; 
		height:100%; 
		line-height:20px; 
		font-size:18px; 
		font-family:'新宋体'; 
		color:#666666; 
		padding:5px 25px 20px 25px;
		text-align:left
		}
.cuowu2{
        background:url(${ctx}/images/cuowu_bottom.gif) no-repeat top left; 
		height:66px; 
		width:100%; 
		text-align:center;
        }
</style>
</head>


<body style="text-align:center;width:100%">
<div style="width:514px; margin:auto; margin-top: 100px;">
<div><img src="${ctx}/images/fg_02.gif" width="514" height="80" /></div>
<div class="cuowu1">
 <p>您可能遇到以下问题：</p>
 <p>  ①输入的地址有误请重新输入！</p>
 <p>  ②登录已过期请重新登录！</p>
</div>
<div class="cuowu2"><br/>
  </div>
</div>
</body>
</html>

