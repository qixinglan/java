//使用二维数组保存方向与课程之间的包含关系。第一项是默认项“请选择”。
//这里为了演示效果，我们特意将二维数组的顺序和"方向"下拉列表中的列表项顺序对应
var projectArray = new Array();
//对应"方向"下拉列表中的第一项
projectArray[0] = ["请选择"];  
//对应"方向"下拉列表中的第二项
projectArray[1] = ["请选择","CoreJava", "Oracle", "JSP", "JDBC"]; 
//对应"方向"下拉列表中的第三项
projectArray[2] = ["请选择","PS", "CSS"]; 
//对应"方向"下拉列表中的第四项
projectArray[3] = ["请选择","PHP", "MySql", "Jquery"];
			
function classChanged() {	
	var i = document.getElementById("selClass").selectedIndex;	
	var data = projectArray[i];	
	var selObj = document.getElementById("selProject");	
	//Step1:一句话删除原有选项，直接将options数组的length属性设置为0.
	while (selObj.childNodes.length > 0) {		
		selObj.removeChild(selObj.lastChild);
	}	
	for (var i = 0; i < data.length; i++) {	
		//Step2:清空课程列表后，需要将对应数组data中的内容，利用循环依次创建新节点，并将新节点追加到课程列表对象的子节点的最后位置。
		var optionObj = document.createElement("option");		
		optionObj.innerHTML = data[i];		
		selObj.appendChild(optionObj);
	}
}