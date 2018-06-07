//计算资产折旧
//得到录入的各项数值
//拼接结果字符串
//循环计算每年的折旧
	//每年的结果显示为一行
//计算资产折旧
function calculateDepreciation() {
	var price=parseFloat(document.getElementById("txtPrice").value);
	var rate=parseFloat(document.getElementById("txtRate").value);
	var year=parseInt(document.getElementById("txtYear").value);
	var r=price*Math.pow((1-rate),year);
	alert(r.toFixed(2));
}
