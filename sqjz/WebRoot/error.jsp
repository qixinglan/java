<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统提示</title>
<script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>

<style type="text/css">
.cuowu1{
        background:url(${ctx}/images/cuowu_mid.gif) repeat-y; 
		height:100%; 
		line-height:20px; 
		font-size:14px; 
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
<div style="width:514px; margin:auto">
<div><img src="${ctx}/images/fg_01.gif" width="514" height="111" /></div>
<div class="cuowu1">
 <p><s:property value="exception.message"/></p> 
 <p style="display:none"><s:property value="exceptionStack"/></p>
</div>
<div class="cuowu2"><br/>
  </div>
</div>
</body>
</html>

