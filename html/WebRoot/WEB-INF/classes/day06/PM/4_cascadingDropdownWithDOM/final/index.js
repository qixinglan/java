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
	selObj.options.length = 0;
	for (var i = 0; i < data.length; i++){
		//Step2:cool！一步完成！
		selObj.add(new Option(data[i],i));
	}
}