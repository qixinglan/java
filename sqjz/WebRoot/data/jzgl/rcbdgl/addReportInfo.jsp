<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>       
    <title>手工报到</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="/common/commonjs.jsp" %>
	<script type="text/javascript">
	 $(function(){
		$.ajax({
			url:"${ctx}/data/jzgl/rcbdgl/addReportInfoView.action",
			type:"post",
			async:false,
			data:'id='+"1",
			success:function(response){	
				$("#fxryid").val(response.fxryid);
				$("#fxrycode").val(response.fxrycode);
				$("#fxrystate").val($.dictionary.formatter('JZRYZT')(response.fxrystate));
				$("#fxrydevicecode").val(response.fxrydevicecode);
				$("#fxryorgname").val(response.orgname);
				$("#fxryname").val(response.fxryname);
				$("#fxryisadult").val($.dictionary.formatter('SF')(response.fxryisadult));
				$("#sex").val($.dictionary.formatter('XB')(response.sex));
				$("#nation").val($.dictionary.formatter('MZ')(response.nation));
				$("#fxryidentity").val(response.fxryidentity);
				$("#fxrybirthday").val(response.fxrybirthday);
				$("#reportdate").val(response.reportdate).css("background","red");
				$("#nextreportdate").val(response.nextreportdate).css("background","red");							
			}
		});	
		$("#submitbtn").click(function(){			
			$("#form").submit();
			parent.location.href=parent.location.href;            
            $.close();
		});
		$("#resetbtn").click(function(){
			$.close();
		});
	 });	
	</script>
  </head>    
<form id="form" name="form" action="${ctx }/data/jzgl/rcbdgl/addReportInfoOperate.action" method="post">
<body>
<div style="margin:10px">  
 	 <div class="container-top">
   		 <table class="search-table">
	     	 <tbody>
	     	 		<tr>
	     	 			<th>人员编码：</th>
	     	 			<td><input id="fxrycode"  name="fxrycode" type="text"  readOnly="true"/></td>  	 				     	 			
	     	 			<th>人员当前状态：</th>
	     	 			<td><input id="fxrystate" name="fxrystate" type="text" readOnly="true"/></td>
		           </tr>
		           <tr>
	     	 			<th>监控设备编号：</th>
	     	 			<td><input id="fxrydevicecode" name="fxrydevicecode" type="text"  readOnly="true"/></td>  	 				     	 			
	     	 			<th>负责矫正单位：</th>
	     	 			<td><input id="fxryorgname" name="fxryorgname" type="text"  readOnly="true"/></td>
		           </tr>
		           <tr>
	     	 			<th>姓名：</th>
	     	 			<td><input id="fxryname" name="fxryname" type="text"  readOnly="true"/></td>  	 				     	 			
	     	 			<th>是否成年：</th>
	     	 			<td><input id="fxryismanhood" name="fxryismanhood" type="text" readOnly="true"/></td>
		           </tr>
		           <tr>
	     	 			<th>性别：</th>
	     	 			<td><input id="sex" name="sex" type="text" readOnly="true"/></td>  	 				     	 			
	     	 			<th>民族：</th>
	     	 			<td><input id="nation" name=""nation"" type="text" readOnly="true"/></td>
		           </tr>
		           <tr>
	     	 			<th>身份证号：</th>
	     	 			<td><input id="fxryidentity" name="fxryidentity" type="text" readOnly="true"/></td>  	 				     	 			
	     	 			<th>出生日期：</th>
	     	 			<td><input id="fxrybirthday" name="fxrybirthday" type="text" readOnly="true"/></td>
		           </tr>
		           <tr>
	     	 			<th>本次报到时间：</th>
	     	 			<td><input id="reportdate" name="reportdate" type="text"/></td>  	 				     	 			
	     	 			<th>下次报到时间：</th>
	     	 			<td><input id="nextreportdate" name="nextreportdate" type="text"/></td>
		           </tr>
		           <tr style="display:none;">	     	 			
	     	 			<td><input id="fxryid" name="fxryid" type="text" /></td>  	 				     	 				     	 			
		           </tr>
	 	   </tbody>
    	</table>
  </div>
  <!--列表-->
  <div class="buttonArea operation">
     <input type="button" id="submitbtn" class="bttn bicon-confirm" value="确定" />
	 <input type="button" id="resetbtn"   class="bttn bicon-reset" value="取消" />
  </div>  
</div>
</body>
</form>
</html>
