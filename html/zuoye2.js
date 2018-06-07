var changeyear;
var changemonth;

function yearChanged(year){
	changeyear=2005+year;
	alert(year);
}
function monthChanged(month){

	var day=document.getElementById("day");
	//day.removeChild(day.childNodes);
	day.innerHTML="";
	changemonth=month+1;
	if(changemonth==1||changemonth==3||changemonth==5||changemonth==7||changemonth==8||changemonth==10||changemonth==12){
		for(var i=1;i<32;i++){
			day.add(new Option(i,i));
		}	
	}
	else if(changemonth==2){
		if(changeyear=2008){
			for(var i=1;i<30;i++){
				day.add(new Option(i,i));
			}	
		}
		else{
			for(var i=1;i<29;i++){
				day.add(new Option(i,i));
			}	
		}
		
	}
	else{
		for(var i=1;i<31;i++){
			day.add(new Option(i,i));
		}
	}
	
}
function jiazai(){
	var year=document.getElementById("year");
	for(var i=2005;i<2015;i++)
		year.add(new Option(i,i));
	var month=document.getElementById("month");
	for(var i=1;i<13;i++){
		month.add(new Option(i,i));
	}

//	if(month.value==2){
//		if(year.value%4==0&&year.value%100!=0)
//				for(var i=1;i<30;i++){
//					day.add(new Option(i,i));
//				}else {
//					for(var i=1;i<29;i++){
//						day.add(new Option(i,i));
//						}
//					}
//	}
	
	
}
window.addEventListener('load',jiazai,false);
