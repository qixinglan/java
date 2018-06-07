/* **** 单选树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * containerId ： 树容器id
 * rootId: 根结点id
 * childRange： 子节点范围
 * actionUrl: 链接地址
**/
//loadSingleCategoryTree('${ctx}', 'tree', '00', '','data/splb/list.action');
//loadSingleCategoryTree('${ctx}', 'tree', '00', '0001,0002,0003,0004','/data/spxx/list.action?gys.id=${gys.id}');
function loadSingleCategoryTree(context, containerId, rootId, childRange, actionUrl){
	var childDm = childRange;
	$("#" + containerId).jstree({ 			
		"json_data" : {		
			data: [{data : "商品类别", state:"closed", attr:{id:"00", nType:"root", name:"商品类别"}}],	
			"ajax" : {
				"url" : context + "/data/splb/tree.action",
				"cache" : false,
				"data" : function (n) {
					var parentDm = n.attr ? n.attr("id") : rootId;
					if(parentDm.length > 2)  //如果当前不是根目录吗
						childDm = "";
					else
						childDm = childRange;
					return { parentDm : parentDm, childDm : childDm};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "ui"]
	}).bind("select_node.jstree", function (event, data) {
		var bm = data.rslt.obj.attr("id");
		var dm = data.rslt.obj.attr("dm");
		var action = context + "/" + actionUrl ;
		if(actionUrl.indexOf('?') > 0){
			action += "&";
		}else{
			action += "?";
		}
		if(bm == null || bm == "undefined")
			bm = "00";
		action += "bm=" + bm;
		if(dm != null && dm != "undefined")
			action += "&dm=" + dm;
		/*if($("#main") != "undefined")
			$("#main").remove();
		$("#right").html('<iframe src="' + action + '" width="100%" height="550" id="main" name="main" frameborder="0" scrolling="auto"></iframe>');
		*/
		//alert("action22:"+action);
		$("#main").attr("src", action);
	});
}

/* **** 多选树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * rootId: 根结点id
 * positionControl: 位置参照控件id
 * keyControl： 保存结点代码的控件id
 * valueControl: 保存结点名称的控件id
**/
//selectMutipleCategoryTree('${ctx}','00','fl','lbdms','names',200,260)
function selectMutipleCategoryTree(context, rootId, positionControl, keyControl, valueControl, width, height){
	var tree = "deptTree";
	messContent="<div style='padding:6px 0 10px 0;text-align:left' id='" + tree + "'></div>";
	var objPos = inputPosition(positionControl);	
	closeWindow();	
	selectTree('选择商品类别', messContent, objPos, width, height);
	$("#"+tree).jstree({ 			
		"json_data" : {
			data: [{data : "商品类别" , state:"closed", attr:{id:"00", nType:"root", name:"商品类别"}}],					
			"ajax" : {
				"url" : context + "/data/splb/tree.action",
				"cache" : false,
				"data" : function (n) { 
					return { parentDm : n.attr ? n.attr("id") : rootId, childDm : ""};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "checkbox", "ui"]
	}).bind("check_node.jstree uncheck_node.jstree", function (event, data) {
		var checked_dms = new Array();
		var checked_names = new Array();
		$.jstree._focused().get_checked(-1,true).each(function(){
			if(this.id){
				checked_dms.push($(this).attr("dm"));
				checked_names.push($(this).attr("name"));
			}
		});
		$("#"+keyControl).val(checked_dms.join(","));
		$("#"+valueControl).val(checked_names.join(","));
	}).bind("loaded.jstree",function(e,data){
		if(window.location.href.indexOf("view.action") != -1){
			$("#"+tree).jstree("open_all");			
		}
		var check_dms = $("#"+keyControl).val();
		if(check_dms != ""){
			var check_dms = "," + check_dms + ",";
			$("#"+tree).find("> ul > li").each(function() {
				var nodeid = $(this).attr("dm");
				if(nodeid !=null && nodeid!=''){
					for(var i = 0; i < nodeid.length; i++){
						if(check_ids.indexOf("," + nodeid + ",") != -1){
							$(this).removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked"); 
						}
					}
				}else if($(this).attr("dm")=="-1"){
					$(this).removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-checked");
				}
							
			});
		}
	}).bind("open_node.jstree",function(e,data){
		var check_dms = $("#" + keyControl).val();	
		check_dms = "," + check_dms + ",";
		if(check_dms != ""){
			data.rslt.obj.find("> ul > li").each(function() {
				var nodeid = $(this).attr("dm");
				if(check_dms.indexOf(","+nodeid+",") != -1){			
					$(this).removeClass("jstree-unchecked").addClass("jstree-checked"); 
					opennode = true;
				}else{
					$(this).removeClass('jstree-undetermined jstree-checked').addClass('jstree-unchecked');
					$(this).children(":checkbox").removeClass("jstree-real-checkbox");			
				}			
			});
		}		
	});	
}
/* **** 单选树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * rootId: 根结点id
 * childRange： 子节点范围
 * positionControl: 位置参照控件id
 * keyControl： 保存结点代码的控件id
 * valueControl: 保存结点名称的控件id
**/
function selectSingleCategoryTree(context, rootId, childRange, positionControl, keyControl, valueControl, width, height){
	var tree = "deptTree";
	messContent="<div style='padding:6px 0 10px 0;text-align:left' id='" + tree + "'></div>";
	var objPos = inputPosition(positionControl);	
	closeWindow();	
	selectTree('选择商品类别', messContent, objPos, width, height);
	$("#"+tree).jstree({ 			
		"json_data" : {
			data: [{data : "商品类别" , state:"closed", attr:{id:"00", nType:"root", name:"商品类别"}}],					
			"ajax" : {
				"url" : context + "/data/splb/tree.action",
				"cache" : false,
				"data" : function (n) { 
					var parentDm = n.attr ? n.attr("id") : rootId;
					if(parentDm.length > 2)
						childDm = "";
					else
						childDm = childRange;
					return { parentDm : parentDm, childDm : childDm};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "ui"]
	}).bind("select_node.jstree", function (event, data) {
		$("#"+keyControl).val(data.rslt.obj.attr("dm"));
		$("#"+valueControl).val(data.rslt.obj.attr("name"));
		closeWindow();	
	});
}
function selectSingleBmbTree(context, rootId, childRange, positionControl, keyControl, valueControl, width, height){
	var tree = "deptTree";
	messContent="<div style='padding:6px 0 10px 0;text-align:left' id='" + tree + "'></div>";
	var objPos = inputPosition(positionControl);	
	closeWindow();	
	selectTree('选择部门', messContent, objPos, width, height);
	$("#"+tree).jstree({ 			
		"json_data" : {
			data: [{data : "司法局" , state:"closed", attr:{id:"2666", nType:"root", name:"司法局"}}],					
			"ajax" : {
				"url" : context + "/data/bm/tree.action",
				"cache" : false,
				"data" : function (n) { 
					var parentDm = n.attr ? n.attr("id") : rootId;
					if(parentDm.length > 2)
						childDm = "";
					else
						childDm = childRange;
					return { parentDm : parentDm, childDm : childDm};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "ui"]
	}).bind("select_node.jstree", function (event, data) {
		$("#"+keyControl).val(data.rslt.obj.attr("dm"));
		$("#"+valueControl).val(data.rslt.obj.attr("name"));
		closeWindow();	
	});
}

/* **** 部门单选树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * rootId: 根结点id
 * childRange： 子节点范围
 * positionControl: 位置参照控件id
 * keyControl： 保存结点代码的控件id
 * valueControl: 保存结点名称的控件id
**/
//	selectSingleBmbTree('${ctx}','11105','','bmmc','bmbm','bmmc',220,400)
/*function selectSingleBmbTree(context, rootId, childRange, positionControl, keyControl, valueControl, width, height){
	var tree = "deptTree";
	messContent="<div style='padding:6px 0 10px 0;text-align:left' id='" + tree + "'></div>";
	var objPos = inputPosition(positionControl);
	closeWindow();	
	select('选择部门', messContent, objPos, width, height);
	$("#"+tree).jstree({ 			
		"json_data" : {
			data: [{data : "司法局1" , state:"closed", attr:{id:"2666",dm:"2666", nType:"root", name:"司法局"}}],					
			"ajax" : {
				"url" : context + "/data/bm/tree.action",
				"cache" : false,
				"data" : function (n) { 
					var parentDm = n.attr ? n.attr("id") : rootId;
					return { parentDm : parentDm};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "ui"]
	}).bind("select_node.jstree", function (event, data) {
		$("#"+keyControl).val(data.rslt.obj.attr("dm"));
		$("#"+valueControl).val(data.rslt.obj.attr("name"));
		closeWindow();	
	});
}
*/
/* **** 单选权限树 ***********************
 * **** 参数    properties  ************
 * context: 上下文
 * containerId ： 树容器id
 * rootId: 根结点id
 * childRange： 子节点范围
 * actionUrl: 链接地址
**/
function loadSingleActionTree(context, containerId, rootId, childRange, actionUrl){
	$("#" + containerId).jstree({ 			
		"json_data" : {		
			data: [{data : "后台管理", state:"closed", attr:{id:"0", nType:"root", name:"后台管理"}}],	
			"ajax" : {
				"url" : context + "/sys/action/tree.action",   //tree每次从此链接获得数据
				"cache" : false,
				"data" : function (n) {			    
					var parentDm = n.attr ? n.attr("id") : rootId;
					return {parentDm : parentDm};
				}
			}
		},
		"themes": { "theme": "classic", "dots": false, "icons": false },
		"plugins" : ["themes", "json_data", "ui"]
	}).bind("select_node.jstree", function (event, data) {
		//获取到select_node的data中的id值，id为在java里写入的id值
		var parentId = data.rslt.obj.attr("id");  
		if (parentId == null || parentId == "undefined")
			parentId == "0";
		var action = context + "/" + actionUrl ;
		if(actionUrl.indexOf('?') > 0){
			action += "&";
		}else{
			action += "?";
		}
		action += "parentId="+parentId;
		if($("#main") != "undefined")
			$("#main").remove();
		$("#right").html('<iframe src="' + action + '" width="100%" height="550" id="main" name="main" frameborder="0" scrolling="auto"></iframe>');
		$("#main").attr("src", action);
	});
}