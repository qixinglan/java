//Step1：为添加按钮定义单击事件，并定义处理方法为onclick="addRow();" 。然后在Script中定义addRow()方法。
function addRow() {
	//Step2：根据表格的id，获得table1对象。保存在变量table中
	//Step3：调用table的insertRow(index)，创建新的空行。其中index为-1，表示在最后一行追加。新行保存在row变量中。
	//Step4：调用row的insertCell(index)方法，将index设置为0，说明在新行的开始位置插入一个单元格。将新插入的单元格保存在变量idCell中。
	//Step5：将文本框txtID中录入的内容，放入单元格idCell的innerHTML中。
	//Step6：模仿步骤4、5，插入第二个单元格，保存在变量nameCell中。并将文本框txtName中的内容，放入单元格的innerHTML中
	//Step7：模仿步骤4，插入第三个单元格，保存在变量buttonCell中。
	//Step8：使用createElement方法创建一个Input节点，保存在变量button中。设置其type为button。这实际上就是创建了一个按钮控件。
	//Step9：设置按钮的文本为“删除”
	//Step10：将按钮节点加入到buttonCell节点对象中。使用appendChild()方法。
	var table = document.getElementById("table1");	
	var row = table.insertRow(-1); 	
	var idCell = row.insertCell(0);	
	idCell.innerHTML = document.getElementById("txtID").value; 	
	var nameCell = row.insertCell(1); //为行创建 name 单元格
	nameCell.innerHTML = document.getElementById("txtName").value;	
	var buttonCell = row.insertCell(2); 	
	var button = document.createElement("input");
	button.type = "button";	
	button.value = "删除";	
	buttonCell.appendChild(button);		
	//button.onclick = function () { delFunc(this); };
	button.addEventListener('click',delFunc,false);
}
//Step11：为删除按钮定义删除方法delFunc，要求传入当前点击的按钮对象作为参数btnObj。
	//Step12：定义删除确认框，返回isDel。
	//Step13：如果isDel为true，也就是用户点了确认，则执行删除方法。
		//Step14：按钮的直接父级节点是td，td的父级才是要删除的tr。所以，这里连用btnObj对象的两个parentNode，向上两级，找到当前按钮所在的行对象，保存在rowOjb变量中。
		//Step15：要删除行，就需要使用table对象的deleteRow(index)方法。所以，首先要使用表格的id找到表格对象，保存在table变量中。
		//Step16：调用table对象的deleteRow(index)方法。其中index为当前行rowObj的rowIndex属性
//Step17：在addRow() 方法的结尾为button的onclick事件制定匿名方法function(){delFunc(this);};
var delFunc = function() {
	var rowObj = this.parentNode.parentNode;
	var isDel = confirm("继续删除产品："+rowObj.cells[0].innerHTML.trim()+"吗?");	
	if (isDel){			 		
		var table = document.getElementById("table1");		
		table.deleteRow(rowObj.rowIndex);
	}
}
