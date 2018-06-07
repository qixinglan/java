<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <%@ include file="/common/commonjsTemp.jsp"%>
    <script type="text/javascript">
        
        $(function(){    	 
        	var parms=getParameters(location.href);
        	var type=parms["type"];
        	var orgIds=parms["orgIds"];
        	var flag = orgIds instanceof Array;
        	var myurl="${ctx}/data/xtsz/getfxryInfo.action?type="+type;        	
        	$("#peaple option").remove();
        	
        	$("#peapleSelected option").remove();
            $.ajax({
    			url:myurl,		
    			type:"post",
    			async:false,    			
    			success:function(response){ 
    				$.each(response,function(i,n){
    					var item;
    					if(type!="3"){
    						item=$("<option value='"+response[i]["attr"]["id"]+"'>"+response[i]["attr"]["name"]+"</option>");
    						if(flag){
	    						for(var j=0;j<orgIds.length;j++){
	    							if(response[i]["attr"]["name"] == orgIds[j]){
		    						var obj = document.getElementById("peapleSelected");
		    						obj.options.add(new Option(response[i]["attr"]["name"], response[i]["attr"]["id"]));
	        						}
	    		    		    }
    							
    						}else{
	    						if(response[i]["attr"]["name"] == orgIds){
		    						var obj = document.getElementById("peapleSelected");
		    						obj.options.add(new Option(response[i]["attr"]["name"], response[i]["attr"]["id"]));
	        					}
    						}
    					}
    					else{
    						item=$("<option value='"+response[i][0]+"'>"+response[i][1]+"</option>");
    						if(flag){
	    						for(var j=0;j<orgIds.length;j++){
	    							if(response[i][1] == orgIds[j]){
			    						var obj = document.getElementById("peapleSelected");
			    						obj.options.add(new Option(response[i][1], response[i][0]));
	        						}
	    		    		    }
    						}else{
    							if(response[i][1] == orgIds){
    	    						var obj = document.getElementById("peapleSelected");
    	    						obj.options.add(new Option(response[i][1], response[i][0]));
        						}
    						}
    					}
    					$("#peaple").append(item);
    				});
    			}
            });
        });                
        function add2() {
            var objs = document.getElementById("peaple");
            if (objs.selectedIndex != -1) {
                for (var j = 0; j < objs.length; j++) {
                    if (objs.options[j].selected) {
                        var text = objs.options[j].text;
                        var value = objs.options[j].value;
                        var obj = document.getElementById("peapleSelected");
                        for (var i = 0; i < obj.length; i++) {
                            if (obj.options[i].text == text) {
                                return false;
                            }
                        }
                        obj.options.add(new Option(text, value));
                    }
                }
            }
        }

        function del() {
            var obj = document.getElementById("peapleSelected");
            if (obj.selectedIndex != -1) {
                obj.remove(obj.selectedIndex);
            }
        }
        
        function changeb(value,text) {
				    var obj=document.getElementById("peapleSelected");
				    for (var i=0; i<obj.length; i++){
					    if(obj.options[i].text==text){
						    return false;
						}
					}
				    obj.options.add(new Option(text,value));
				 }
        
    </script>

</head>
<body>
    <table width="350" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;margin-left:10px;">
        <tbody>
            <tr>
                <td>
                     <select name="peaple" id="peaple" multiple="true" style="height: 270px; width: 140px;"
	                      ondblclick="changeb(this.value,this.options[this.selectedIndex].text);">
	                      <option value="0">东城区</option>
	                      <option value="1">西城区</option>
	                      <option value="2">朝阳区</option>
	                      <option value="3">海淀区</option>
	                      <option value="4">丰台区</option>
	                      <option value="5">石景山区</option>
	                      <option value="6">门头沟区</option>
	                      <option value="7">房山区</option>
	                      <option value="8">大兴区</option>
	                      <option value="9">通州区</option>
	                      <option value="10">顺义区</option>
	                      <option value="11">昌平区</option>
	                      <option value="12">平谷区</option>
	                      <option value="13">怀柔区</option>
	                      <option value="14">密云区</option>
	                      <option value="15">延庆区</option>
	                  </select>
                </td>
                <td width="50">
                     <div class="select-btn container-center" style="padding-top:30px;">
							          <input type="button" class="btn-left" onclick="add2()"/>
							          <input type="button" class="btn-right" onclick="del()"/>
							       </div>
                </td>
                <td>
                    <select name="peapleSelected" id="peapleSelected" multiple="true" style="width: 140px;
                        height: 270px" ondblclick="del();">
                    </select>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
