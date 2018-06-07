//Step1:首先，既然是点击事件，肯定要为每个+按钮增加一个点击事件onclick="count()"。
//Step2:修改onclick方法为count(this),并定义方法function count(btnObj)
//Step3:基于当前的btnObj，先找到它的parent节点，再得到parent节点的所有childNodes。因为在td中，有三个子节点，而文本框在第二个，所以我们可以使用childNodes[1]，获得这个文本框。将文本框保存到变量txt中。
	//var txt = btnObj.parentNode.childNodes[1];
//Step4:注释掉刚才的第一条txt语句。定义一个nodes变量，获得btnObj对象的parentNode，然后使用按元素查找所有的input。再取第二个！
//Step5:获得文本框中的值，并转换为整数，保存在变量oldCount中
//其实每一行中的两个按钮+、-原理一样，只不过是+1 和 -1的不同而已。如果btnObj的value是"+"，说明需要+1，如果btnObj的value是"-"，说明需要-1。这里只有两种情况，刚好适合使用三元运算符；
//Step6:使用三元运算符，替换上一句。
//Step7:由于要求数量不能小于1，所以如果数量小于1了，就重置回1；
//Step8:既然我们使用一个方法统一实现了加减方法。那么-按钮的onclick事件只需要和+按钮的一样即可。为-按钮增加onclick事件为count(this)。



//Step9:定义计算方法calculate()
//Step10:定义变量total以存储总计,初始化为0
//Step11:通过表格的id，得到整个表格对象，再进一步得到所有行
//Step12:使用for循环，从第二行开始计算，到倒数第二行结束
//Step13:使用当前行trNodes[i]的getElementsByTagName("td")方法，得到当前行中的所有单元格，保存在变量tds中。
//Step14:从tds数组的第二个单元格中取出单价保存在变量price中。
//Step15:从tds数组的第三个单元格中，取出第二个input就是保存数量的文本框。得到它的value，保存在变量quantity中。
//Step16:price * quantity得到该行商品的小计。保存在变量sum中。
//Step17:tds中的第四个单元格中，放的是该行的小计，使用innerHTML替换旧的小计为sum。结果要求保留两位小树
//Step18:将当前行的小计sum累积到总计total中
//Step19:找到总计所在的位置，是trNodes数组的组后一行的第二个td，将total的最后结果替换到它的innerHTML中。保留两位小数。
//Step20:在count方法的结尾，增加对calculate方法的调用。
function count(obj){
	var input2=obj.parentNode.getElementsByTagName("INPUT")[1];
	var count=parseInt(input2.value);
	obj.value=="+"?count+=1:count-=1;
	if(count<1)   count=1;
	input2.value=count;
	var  tds=obj.parentNode.parentNode.getElementsByTagName("TD");
	var price=parseFloat(tds[1].innerHTML);
	var subTotal=price*count;
	tds[3].innerHTML=subTotal.toFixed(2);
	 calc();
}
function calc(){
	var base=document.getElementById("shoppingCart").getElementsByTagName("TR");
	var total=0;
	for(var i=1;i<base.length-1;i++){
		var sub1=parseFloat(base[i].getElementsByTagName("TD")[3].innerHTML);
		total+=sub1;
	}
	base[base.length-1].getElementsByTagName("TD")[1].innerHTML=total;
}
