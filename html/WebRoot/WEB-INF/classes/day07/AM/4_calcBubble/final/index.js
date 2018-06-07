//Step4: 去掉第一个参数str,删除所有button的onclick方法。
//Step5: 修改div的onclick方法为calculate(event)
function calculate(e){
	//Step6：使用e.srcElement || e.target同时兼容IE和Firefox系列浏览器，获得事件源
	var obj = e.srcElement || e.target;
	//Step7：如果用户点击的是input，而且type是button，说明点在了按钮上，则获取obj的value，保存在变量str中，原来的switch方法可以继续使用。	
	if(obj.nodeName == "INPUT" && obj.type=="button"){
		var str = obj.value;	
		var txtObj = document.getElementById("txtData");
		switch(str){
			case "C":
				txtObj.value = "";
				break;
			case "=":
				var input = txtObj.value;
				if(input.length > 0)
					try{
						var result = eval(input);
						txtObj.value = result;
					}catch(error){
						txtObj.value = error;
					}
				break;
			default:
				txtObj.value += str;
				break;	
		}
	}
}