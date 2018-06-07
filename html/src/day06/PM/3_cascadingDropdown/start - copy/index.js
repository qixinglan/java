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
			
//Step1:select作为列表选择控件，有一个事件onchange，它在选中项被改变时触发。所以首先为“方向”下拉列表定义onchange事件处理方法为classChanged()。然后在script中创建对应方法classChanged()。
//Step2:根据“方向”下拉列表的id，快速查找到"方向"下拉列表对象，并获得当前选中的索引。使用selectedIndex属性获得当前选中项的索引。将索引保存在变量i中。
//Step3:根据索引值，从二维数组中找到对应方向的子数组，保存在变量data中。
//Step4:使用"课程"下拉列表的Id，找到“课程”下拉列表对象，保存到selObj变量中//Step5:使用while循环依次删除旧的列表项，循环结束条件是“课程”下拉列表对象的length=0。
	//Step6:每次都使用removeChild方法，移除"课程"下拉列表对象的最后一个子节点lastChild。
//Step7:清空课程列表后，需要将对应数组data中的内容，利用循环依次创建新节点，并将新节点追加到课程列表对象的子节点的最后位置。
	//Step8:创建一个新节点，名为option。这个节点就是select节点的选项子节点
	//Step9:设置新节点的innerHTML属性，将对应的课程名称作为节点的文本。
	//Step10:创建好节点后，要将它加入到selObj下拉列表的子节点中，使用appendChild方法。
function classChanged(selected){
	var data=projectArray[selected];
	var sel=document.getElementById("selProject");
	//每次还必须删除原先的，这种删除方法需牢记
	sel.innerHTML="";
	for(var i=0;i<data.length;i++){
		sel.add(new Option(data[i],data[i]));
	}
//	for(var i=0;i<data.length;i++){
//		var option=document.createElement("option");
//		option.innerHTML=data[i];
//		option.value=data[i];
//		sel.appendChild(option);
//	}
}
