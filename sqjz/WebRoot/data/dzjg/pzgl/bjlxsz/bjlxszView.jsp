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
    				$("#alarmLevel").text($.dictionary.formatter("BJJB")(response.alarmLevel));	
    				$("#alarmType").text($.dictionary.formatter("BJLX")(response.alarmType));
    				$.dictionary.radio("#status", "status", "BJLXZT", {
    					allowBlank : false,
    					defValue : response.status==null?"2":response.status.toString(),
    				    readonly:true
    				});    		
    				$.dictionary.radio("#isautoAlarm", "isautoAlarm", "SFZDBJ", {
    					allowBlank : false,
    					defValue : response.isautoAlarm==null?"2":response.isautoAlarm.toString(),
    				    readonly:true
    				});  
    				$.dictionary.radio("#isautoAlert", "isautoAlert", "SFZDBJ", {
    					allowBlank : false,
    					defValue : response.isautoAlert==null?"2":response.isautoAlert.toString(),
    				    readonly:true
    				});  
    				$("#alertContent").val(response.alertContent);	
    				$("#alarmContent").val(response.alarmContent);	
    				$("#crossBorderRange").prepend(response.crossBorderRange);	
    				$("#crossBorderTime").prepend(response.crossBorderTime);	
    				$("#gatherTime").prepend(response.gatherTime);
    				$("#sparateRange").prepend(response.sparateRange);
    				$("#sparateTime").prepend(response.sparateTime);
    				$("#silentTime").prepend(response.silentTime);
    				$("#lostContact").prepend(response.lostContact);
    				$("#locationError").prepend(response.locationError);
    			}
            });
           var selectstr=".isDisplay[id="+parms['id']+"]";
           $(".isdisplay").hide();
           $(selectstr).show();
        });       
        function getParameters(url, options) {
            options = options || {};
            // if no url specified, take it from the location bar
            url = (url === null || url === undefined) ? window.location.href : url;
            //parse out parameters portion of url string
            var paramsString = "";
            if (url.indexOf('?')>-1) {
                var start = url.indexOf('?') + 1;
                var end = (url.indexOf("#")>-1) ?
                            url.indexOf('#') : url.length;
                paramsString = url.substring(start, end);
            }

            var parameters = {};
            var pairs = paramsString.split(/[&;]/);
            for(var i=0, len=pairs.length; i<len; ++i) {
                var keyValue = pairs[i].split('=');
                if (keyValue[0]) {

                    var key = keyValue[0];
                    try {
                        key = decodeURIComponent(key);
                    } catch (err) {
                        key = unescape(key);
                    }
                    
                    // being liberal by replacing "+" with " "
                    var value = (keyValue[1] || '').replace(/\+/g, " ");

                    try {
                        value = decodeURIComponent(value);
                    } catch (err) {
                        value = unescape(value);
                    }
                    
                    // follow OGC convention of comma delimited values
                    if (options.splitArgs !== false) {
                        value = value.split(",");
                    }

                    //if there's only one value, do not return as array                    
                    if (value.length == 1) {
                        value = value[0];
                    }                
                    
                    parameters[key] = value;
                 }
             }
            return parameters;
        };        
	 </script>
  </head>
  
 
 <body> 
 <form id="form1" name="form1" action="${ctx }/data/dzjg/bjlxsz/update.action" method="post">  
 	 <div>
   		 <table class="comm-table">
	     	 <tbody>
	     	 	   <tr>
	     	 			<th>报警类型：</th>
	     	 			<td colspan="3" id="alarmType">	     	 		
	     	 			</td>	     	 			
		           </tr>
		           <tr>
	     	 			<th>状态：</th>
	     	 			<td colspan="3" id="status" >
	     	 			</td>  	 				     	 			
		           </tr>
		           <tr>
	     	 			<th>报警级别：</th>
	     	 			<td colspan="3" id="alarmLevel">
	     	 			</td>  	 				     	 				     	 		
		           </tr>
		           <tr class="isDisplay" style="display:none"  id="1">
	     	 			<th>越界范围：</th>
	     	 			<td id="crossBorderRange">公里</td>  	 				     	 			
	     	 			<th>越界时限：</th>
	     	 			<td id="crossBorderTime">分钟</td>
		           </tr>
		           <tr style="display:none" class="isDisplay" id="3">	     	 						     	 		
	     	 			<th>聚集时间：</th>
	     	 			<td colspan="3" id="gatherTime">分钟</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay"  id="6">	     	 						     	 		
	     	 			<th>静默时间：</th>
	     	 			<td colspan="3" id="silentTime">小时</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay" id="5">
	     	 			<th>分离距离：</th>
	     	 			<td id="sparateRange">米</td>  	 				     	 			
	     	 			<th>分离时间：</th>
	     	 			<td id="sparateTime">分钟</td>
		           </tr>
		           <tr style="display:none"  class="isDisplay" id="7">
	     	 			<th>定位终端失联：</th>
	     	 			<td id="lostContact">次</td>  	 				     	 			
	     	 			<th>定位信息异常：</th>
	     	 			<td  id="locationError">次</td>
		           </tr>
		           <tr>
	     	 			<th>短信警告提醒：</th>
	     	 			<td colspan="3" id="isautoAlert"> 
		        		</td>  	
	     	 	   </tr>
	     	 	   <tr> 				     	 			
	     	 			<th></th>
	     	 			<td colspan="3"><textarea id="alertContent" name="alertContent" style="width:400px;height:100px;"disabled="true"></textarea></td>
		           </tr>
		           <tr>
	     	 			<th>短信报警提醒：</th>
	     	 		    <td colspan="3" id="isautoAlarm"> 
		        		</td>  	
	     	 	   </tr>
	     	 	   <tr> 				     	 			
	     	 			<th></th>
	     	 			<td colspan="3"><textarea id="alarmContent" name="alarmContent" style="width:400px;height:100px;" disabled="true"></textarea></td>
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