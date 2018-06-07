<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>请假信息</title>
<%@ include file="/common/commonjs.jsp"%>
<script language="javascript" src="${ctx}/data/jzgl/rcbdgl/fxrys.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.fxrys.fxrysCbox("#personId", "personId", "FXRYS",{
			multiSelected: false,
			padding:'6px',
			width:'95%',
			attr:{required:"true",label:"服刑人员"}
		});
	});
</script>
</head>
<body>
	<form id="userAdd" action="${ctx}/data/jzgl/rcbdgl/qjgl.action" method="post">
		<table class="comm-table">
			<tbody>
				<tr>
					<th class="form-td-label form-td-label-120 field-required-label">服刑人员：</th>
					<td id="personId"></td>
				</tr>
	     	  	<tr>
	     	  		<th class="form-td-label form-td-label-120 field-required-label">请假开始日期：</th>
	     	  		<td>
	     	  			<input id="startDate" name="startDate" type="text" required="true" 
							class="Wdate inputStyle" style="width: 90%" label="请假开始日期"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'endDate\')}'})" />
					</td>
				</tr>
	     	  	<tr>
					<th class="form-td-label form-td-label-120 field-required-label">请假结束日期：</th>
					<td>
						<input id="endDate" name="endDate" type="text" required="true"
							class="Wdate inputStyle" style="width: 90%" label="请假结束日期"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'startDate\')}'})" />	
                    </td>	     	  		
	     	  	</tr>
	     	  	<tr>
	     	  		<th>外出目的地：</th>
	     	  		<td>
	     	 			<input id="termini" maxlength="100" name="termini" type="text" style="width: 90%"/>
	     	 		</td>
	     	 	</tr>
	     	  	<tr>
	     	  		<th class="form-td-label form-td-label-120 field-required-label">请假原因：</th>
	     	  		<td>
	     	 			<textarea id="reason" name="reason" required="true" label="请假原因" maxlength="100"
	     	 				style="resize:none; width: 93%;margin-left: 5px;margin-right: 0px;"></textarea>
	     	 		</td>	     	  			     	  
	     	  	</tr>	  
     	  	</tbody>
		</table>
	</form>
</body>
</html>
