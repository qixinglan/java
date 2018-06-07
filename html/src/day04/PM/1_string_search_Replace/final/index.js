function searchAndReplace(){
	//Step1:获得页面上的文本框，再使用文本框的value属性获得用户在文本框中输入的是什么？
	//Step2:从开始位置查找“js”首次出现的索引位置！
	//Step3:如果搜索结果为-1，说明根本没找到“js”，直接提示“安全”
	var str = document.getElementById("txtString").value;	
	var index = str.indexOf("js");	
	if(index == -1) alert("Clear!");
	//Step4:如果搜索结果不等于-1，说明至少有一个“js”。则继续向下执行
	//由于replace方法一次只能替换一个查找到的"js"，如果想都替换，只能借助循环。继续循环的判断依据应该是，在当前位置之后是否还找到了新的"js"。每次循环内都应该有两个操作：
			//Step4.1:首先替换当前位置找到的一个"js"为"**"
			//Step4.2:然后从当前位置继续向后查找，看下一次出现"js"的位置在哪里。
			//程序会回到while循环判断是否继续循环
	//Step5:最终，经过循环替换后的新字符串，再保存回网页上的文本框中，替换旧的内容。
	else{		
		while(index > -1){			
			str = str.replace("js","**");			
			index = str.indexOf("js",index + 1);
		}
		document.getElementById("txtString").value = str;
	}
}