function getDateRange(days){
	//得到当前日期
    var end = new Date();
    var endString = end.toString();
    //修改日期
    end.setDate(end.getDate() - days + 1);
    //显示结果
    var s = "开始日期为：" + end.toLocaleDateString() + "\n";
    s += "结束日期为：" + endString;
    alert(s);
}