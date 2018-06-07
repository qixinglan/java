<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>绑定角色</title>
    <link href="${ctx }/css/common2.css" rel="stylesheet" type="text/css" />
  	<script src="${ctx}/js/common.js" type="text/javascript"></script>	
    <script src="${ctx}/js/jquery-1.6.2.js" type="text/javascript"></script>   
    <script type="text/javascript">
    $(document).ready(function(){
        var oldId='${roleIds}';
    	var oldIds=oldId.split(',');
    	for(var i=0;i<oldIds.length;i++){
    	   $("input[value='"+oldIds[i]+"']").attr("checked",true);
    	}
    	$(".btn_submit").click(function(){
        	$("#choicerole").submit();
    		window.close();
    	});
    });	
    </script> 
  </head>  
  <body>  
   <center>
   <div>
   	 <form id="choicerole" action="${ctx}/sys/action/upactions.action" method="post">
       <input type="hidden" id="id" name="id" value="${id }"/>
	  	<table border="0" align="center" cellpadding="0" cellspacing="1" class="table_cx">
	     <tr>
	        <th>角色</th>
        	<th>角色描述</th>
        	<th>操作</th>        
      	 </tr>
  		 <s:set var="num" value="0"></s:set>
   	  	 <s:iterator value="roleList" status="status">   	
	  		<tr class="<s:if test="#num%2==0">td-value-col</s:if><s:else>td-value-col2</s:else>">
		   		<td>${name}</td>
		    	<td>${info}</td>
	  			<td><input type="checkbox" name="roleList[${num }].id" value="${id }"/></td>
	  		</tr>
	  	    <s:set var="num" value="#num+1"></s:set>	
	  	</s:iterator>
	  	</table>
	  	<br/>
  		<a href="javascript:void(0);"><img src="${ctx}/images/jieguo_queren.gif" border="0" class="btn_submit" /></a>	
  	</form>
  	</div>
  	</center>
  </body>
</html>
