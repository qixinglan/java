//计算资产折旧
function calculateDepreciation() {
    //得到录入的各项数值
    var money = parseFloat(document.getElementById("txtPrice").value);
    var rate = parseFloat(document.getElementById("txtRate").value);
    var year = parseInt(document.getElementById("txtYear").value);

    //拼接结果字符串
    var s = "年限  剩余价值\n";
    //循环计算每年的折旧
    for (var i = 1; i <= year; i++) {
        var r = money * Math.pow((1 - rate), i);
        //每年的结果显示为一行
        s += i + "    " + r + "       " + r.toFixed(2) + "\n";
    }
    document.getElementById("txtResult").innerHTML = s;
}
