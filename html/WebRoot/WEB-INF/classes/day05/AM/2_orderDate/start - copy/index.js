function getRange(days){
	var now=new Date();
	now.setDate(now.getDate()-days+1);
	alert("从"+now.toLocalDateString()+"开始，到今天");
}