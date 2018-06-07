<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">

function updateTable(){
	$.ajax({
		url:"${ctx}/data/jzgl/bbgl/workstat/updateTable.action",
		success:function(data){
			alert("本月报表生成成功");
		}
	});
};
//alert($.showText.add({a:3,b:3}));
//-->
</script>
</head>
<body>
<input type="button" onclick="updateTable()" value="生成报表"/>
</body>
</html>