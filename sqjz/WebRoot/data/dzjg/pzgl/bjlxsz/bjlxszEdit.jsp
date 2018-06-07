<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>  
    <title>报警类型设置</title>    
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 <%@ include file="/common/commonjsTemp.jsp" %> 	 
	 <script type="text/javascript">
        $(function(){    
        	var parms=getParameters(location.href);
        	var id=parms["id"];
            $.ajax({
    			url:"${ctx}/data/dzjg/bjlxsz/view.action",		
    			type:"post",
    			async:false,
    			data:'id='+id,
    			success:function(response){	
    				$("#id").val(response.id);
    				$("#alarmLevelName").val($.dictionary.formatter("BJJB")(response.alarmLevel));	
    				$("#alarmTypeName").val($.dictionary.formatter("BJLX")(response.alarmType));
    				$("#alarmType").val((response.alarmType));	
    				$("#alarmLevel").val((response.alarmLevel));	
    				$.dictionary.radio("#status", "status", "BJLXZT", {
    					allowBlank : false,
    					defValue : response.status==null?"2":response.status.toString()
    				});    		
    				$.dictionary.radio("#isautoAlarm", "isautoAlarm", "SFZDBJ", {
    					allowBlank : false,
    					defValue : response.isautoAlarm==null?"2":response.isautoAlarm.toString()
    				});  
    				$.dictionary.radio("#isautoAlert", "isautoAlert", "SFZDBJ", {
    					allowBlank : false,
    					defValue : response.isautoAlert==null?"2":response.isautoAlert.toString()
    				});  
    				$("#alertContent").val(response.alertContent);	
    				$("#alarmContent").val(response.alarmContent);	
    				$("#crossBorderRange").val(response.crossBorderRange);	
    				$("#crossBorderTime").val(response.crossBorderTime);	
    				$("#gatherTime").val(response.gatherTime);
    				$("#sparateRange").val(response.sparateRange);
    				$("#sparateTime").val(response.sparateTime);
    				$("#silentTime").val(response.silentTime);
    				$("#lostContact").val(response.lostContact);
    				$("#locationError").val(response.locationError);
    			}
            });
           var selectstr=".isDisplay[id="+parms['id']+"]";
           $(".isdisplay").hide();
           $(selectstr).show();
        });      
        function sava(){
        	
        }
	 </script>
  </head>
  
 
 <body> 
 <form id="form1" name="form1" action="${ctx }/data/dzjg/bjlxsz/update.action" method="post">  
 	 <div>
   		 <table class="comm-table">
	     	 <tbody>
	     	 	   <tr>
	     	 			<th>报警类型：</th>
	     	 			<td colspan="3">
	     	 			   <input type="text" name="alarmType" id="alarmType" style="height:30px;display:none" readOnly="true"/>
	     	 			   <input type="text" name="alarmTypeName" id="alarmTypeName" style="height:30px" readOnly="true"/>
	     	 			</td>	     	 			
		           </tr>
		           <tr>
	     	 			<th>状态：</th>
	     	 			<td colspan="3" id="status" >
	     	 			</td>  	 				     	 			
		           </tr>
		           <tr>
	     	 			<th>报警级别：</th>
	     	 			<td colspan="3" >
	     	 			    <input type="text" name="alarmLevel" style="height:30px;display:none" id="alarmLevel" readOnly="true" />
	     	 			    <input type="text" name="alarmLevelName" id="alarmLevelName" style="height:30px" readOnly="true"  />
	     	 			</td>  	 				     	 				     	 		
		           </tr>
		           <tr class="isDisplay" style="display:none"  id="1">
	     	 			<th>越界范围：</th>
	     	 			<td><input id="crossBorderRange" name="crossBorderRange" type="text" validater="int"  style="height:30px" maxlength="8" />公里</td>  	 				     	 			
	     	 			<th>越界时限：</th>
	     	 			<td><input id="crossBorderTime" name="crossBorderTime" type="text"  validater="int"  style="height:30px" maxlength="8" />分钟</td>
		           </tr>
		           <tr style="display:none" class="isDisplay" id="3">	     	 						     	 		
	     	 			<th>聚集时间：</th>
	     	 			<td colspan="3"><input id="gatherTime" name="gatherTime" type="text"   validater="int" style="height:30px" maxlength="8" />分钟</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay"  id="6">	     	 						     	 		
	     	 			<th>静默时间：</th>
	     	 			<td colspan="3"><input id="silentTime" name="silentTime" type="text"   validater="int" style="height:30px" maxlength="8"/>小时</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay" id="5">
	     	 			<!--  <th>分离距离：</th>
	     	 			<td><input id="sparateRange" name="sparateRange" type="text"  validater="int" style="height:30px"/>米</td>-->  	 				     	 			
	     	 			<th>分离时间：</th>
	     	 			<td><input id="sparateTime" name="sparateTime" type="text"  validater="int" style="height:30px" maxlength="8" />分钟</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay" id="7">
	     	 			<th>定位终端失联：</th>
	     	 			<td><input id="lostContact" name="lostContact" type="text"  validater="int" style="height:30px" maxlength="8"/>次</td>  	 				     	 			
	     	 			<!-- <th>定位信息异常：</th>
	     	 			<td><input id="locationError" name="locationError" type="text"  validater="int" style="height:30px"/>次</td>--> 
		           </tr>
		           <tr>
	     	 			<th>短信警告提醒：</th>
	     	 			<td colspan="3" id="isautoAlert"> 
		        		</td>  	
	     	 	   </tr>
	     	 	   <tr> 				     	 			
	     	 			<th></th>
	     	 			<td colspan="3"><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td style="width: 450px; border: 0;"><textarea id="alertContent" name="alertContent" style="width:400px;height:100px"></textarea></td><td style="border: 0;"><label id="alertMsg" style="border:none; height:10px; color: red; font-size: 12px;"></label></td></tr></table></td>
		           </tr>
		           <tr>
	     	 			<th>短信报警提醒：</th>
	     	 		    <td colspan="3" id="isautoAlarm"> 
		        		</td>  	
	     	 	   </tr>
	     	 	   <tr> 				     	 			
	     	 			<th></th>
	     	 			<td colspan="3"><table border="0" cellpadding="0" cellspacing="0" width="100%" ><tr><td style="width: 450px; border: 0;"><textarea id="alarmContent" name="alarmContent" style="width:400px;height:100px"></textarea></td><td style="border: 0;"><label id="alarmMsg" style="border:none; height:10px; color: red; font-size: 12px;"></label></td></tr></table></td>
		           </tr>		
		           <tr  style="display:none"> 				     	 			
	     	 			<th></th>
	     	 			<td colspan="3"><input type="text" name="id" id="id" /></td>
		           </tr>
	 	   </tbody>
    	</table>
  </div>
  </form>
</body>

</html>
