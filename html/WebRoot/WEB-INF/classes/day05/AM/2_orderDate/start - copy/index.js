function getRange(days){
	var now=new Date();
	now.setDate(now.getDate()-days+1);
	alert("��"+now.toLocalDateString()+"��ʼ��������");
}