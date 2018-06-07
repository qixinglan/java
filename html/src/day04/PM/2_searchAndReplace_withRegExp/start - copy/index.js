function searchAndReplace(){
	//Step1:获得页面上的文本框，再使用文本框的value属性获得用户在文本框中输入的是什么？
	var str = document.getElementById("txtString").value;
	//Step2:使用match方法获得所有出现的js，放到数组中。
	//Step3:判断数组的元素个数（length），就等于js出现次数，也就是总共需要替换的次数。
	//Step4:使用replace方法替换找到的js为"**"。
	//Step5:将替换后的新字符串，写回页面上的文本框的value中。
	//Step6:显示共替换了多少处


	//Step7:修改步骤2、4中的正则表达式，后加g。看结果
    //Step8:修改步骤2、4中的正则表达式，后加gi。看结果
}