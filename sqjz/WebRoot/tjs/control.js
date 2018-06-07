
/* **** 多选树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * actionName:action访问地址
 * title:弹出层上方提示信息
 * rootId: 根结点id
 * positionControl: 位置参照控件id
 * keyControl： 保存结点代码的控件id
 * valueControl: 保存结点名称的控件id
**/
function selectMutipleList(context,actionName,title, rootId, positionControl, keyControl, valueControl, width, height){
	var list = "list";
	messContent="<div style='padding:6px 0 10px 0;text-align:left' id='" + list + "'></div>";
	var objPos = inputPosition(positionControl);
	closeWindow();	
	select(title, messContent, objPos, width, height);
	$("#"+list).jstree({ 			
		"json_data" : {
			"ajax" : {
				"url" : actionName,
				"cache" : false				
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "checkbox", "ui"]
	}).bind("check_node.jstree uncheck_node.jstree", function (event, data) {
		var checked_dms = new Array();
		var checked_names = new Array();
		$.jstree._focused().get_checked(-1,true).each(function(){
			if(this.id){
				checked_dms.push($(this).attr("id"));
				checked_names.push($(this).attr("name"));
			}
		});
		$("#"+keyControl).val(checked_dms.join(","));
		$("#"+valueControl).val(checked_names.join(","));
	}).bind("loaded.jstree",function(e,data){
		if(window.location.href.indexOf("view.action") != -1){
			$("#"+list).jstree("open_all");			
		}
		var check_dms = $("#"+keyControl).val();	
		if(check_dms != ""){
			var checkNodeDms = check_dms.split(",");
			var check_dms = "," + check_dms + ",";
			$("#"+list).find("> ul > li").each(function() {
				var nodeid = $(this).attr("id");
				if(nodeid !=null && nodeid!=''){
					for(var i = 0; i < nodeid.length; i++){
						if(check_ids.indexOf("," + nodeid + ",") != -1){
							$(this).removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked"); 
						}
					}
				}else if($(this).attr("id")=="-1"){
					$(this).removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked");
				}
							
			});
		}
	});	
}
function asynchronismLoadBrandOption(selectName,actionUrl,brandDm){
	$.post(actionUrl,function(data){
			var result=JSON.parse(data);
			$('#'+selectName).empty();
			for(var i=0;i<result.length;i++){
				if(result[i].dm==brandDm){
					$('#'+selectName).append("<option value='"+result[i].dm+"' selected='selected'>"+result[i].mc+"</option>");
				}else{
					$('#'+selectName).append("<option value='"+result[i].dm+"'>"+result[i].mc+"</option>");
				}
			}
	});
}
