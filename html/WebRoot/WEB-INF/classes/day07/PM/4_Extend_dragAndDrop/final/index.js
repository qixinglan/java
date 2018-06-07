//两个数组分别用来保存参赛选手和志愿者
var racers = [];
var volunteers = [];
//分别用来保存放置参赛选手和志愿者的ul
var racersList;
var volunteersList;
//页面刚加载时
function loadDemo() {
	racersList = document.getElementById("racers");
	volunteersList = document.getElementById("volunteers");
	//Step1：每个可拖动的成员都有拖动开始和结束事件处理程序
	var members = document.getElementsByTagName("li");
	for(var i=0;i<members.length;i++){
		members[i].addEventListener("dragstart", handleDragStart, false);
		members[i].addEventListener("dragend", handleDragEnd, false);
	}
	//Step2：每个目标列表都有一个特定的dragover事件处理程序
	racersList.addEventListener("dragover", handleDragOver, false);
	volunteersList.addEventListener("dragover", handleDragOver, false);
	//Step3：目标列表外围的fieldset起到边界的作用。用来重置dragover的样式
	var racersField = document.getElementById("racersField");
	var volunteersField = document.getElementById("volunteersField");
	racersField.addEventListener("dragover", handleDragOverOuter, false);
	volunteersField.addEventListener("dragover", handleDragOverOuter, false);
	//Step4：为两个目标列表注册Enter、Leave、drop事件
	racersList.addEventListener("drop", handleDrop, false);
	volunteersList.addEventListener("drop", handleDrop, false);
}
window.addEventListener("load", loadDemo, false);

//开始拖动时触发
function handleDragStart(event) {
	var evt=event||window.event;
	//设置从源到目标的传输内容：
	//第一个参数记住，分别表示文本内容和html标签属性
	//第二个参数就是对应的内容和属性名称
	var src=evt.target || evt.srcElement;
	evt.dataTransfer.setData("name", src.innerHTML);
	evt.dataTransfer.setData("age", src.dataset.age);
	//显示可放置的目标区域
	racersList.className = "validtarget";
	volunteersList.className = "validtarget";
}

// 如果在racers列表上晃动，则区域显示为高亮的黄色，表示可以放置
function handleDragOver(event) {
	var evt=event||window.event;
	evt.cancelBubble=true;
	//这里必须使用下面一句话取消原有dragover事件的默认方法：
	//dragover事件的默认方法是移动可以，但不允许放置！
	//移动完，哪儿来的，还要回哪儿去！
	//preventDefault()是在告诉浏览器：你管不着我！我就愿意在这儿
	evt.preventDefault();
	var src=evt.srcElement||evt.target;
	if(src.id=='racers')
		racersList.className = "highlighted";
	else
		volunteersList.className = "highlighted";
}

//fieldset到蓝色区域之间的白色部分，都是fieldset的区域
//如果在这个区域中晃动，说明已经离开目标区
//此时根据触发over事件的field，决定将哪个list区域还原回蓝色
function handleDragOverOuter(event) {
	var evt=event||window.event;
	var src=evt.target || evt.srcElement;
	if (src.id == "racersField")
		racersList.className = "validtarget";
	else if (src.id == "volunteersField")
		volunteersList.className = "validtarget";
}

// 当用户释放鼠标按键时，表示要放置元素了
// 此时需要传递数据到目标位置。添加新元素，排序。
function handleDrop(event) {
	var evt=event||window.event;
	evt.cancelBubble=true;
	evt.preventDefault();		
	var src=evt.target || evt.srcElement;
	var dropTarget = src;
	var group; var list; var text;
	// 使用text类型，从通道中获得成员的名字
	text = evt.dataTransfer.getData("name");
	// 如果当前放置的是racers区域中，则需要根据需求改变显示内容
	if(dropTarget.id == "racers")
		//从通到中获得html数据
		text = evt.dataTransfer.getData("age") + ": " + text;
	if(dropTarget.id == "volunteers"){
		//获得现在的志愿者列表中的内容
		group = volunteers;
		//获得现在的志愿者列表区域
		list = volunteersList;
	}
	else{	
		//重新获得
		group = racers;
		list = racersList;
	}
	// 向对应数组中添加新元素，然后排序
	if (group.indexOf(text) == -1) {
		group.push(text);
		group.sort();
		// 移除显示列表中的所有项，重新添加！
		while (list.hasChildNodes())
			list.removeChild(list.lastChild);
		// 重新遍历数组，添加新元素
		group.forEach(function(person) {
			var newChild = document.createElement("li");
			newChild.innerHTML = person;
			list.appendChild(newChild);
		});
	}
}

// 无论拖放是否成功，都会执行这个方法，让一切归于平静
function handleDragEnd(evt) {
	racersList.className = null;
	volunteersList.className = null;
}
//与拖拽目标相关的事件方法中最好加上取消冒泡和取消默认事件
//否则天知道浏览器自己添加了什么拖拽的支持，也一并触发。
//比如firefox中拖拽就会自动打开新页面，在百度中查询关键字。