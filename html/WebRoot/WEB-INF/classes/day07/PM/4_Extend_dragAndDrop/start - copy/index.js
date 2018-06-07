var racersList;
var volsList;
var racers=[];
var vols=[];
function loadDemo(){
	racersList=document.getElementById("racers");
	volsList=document.getElementById("volunteers");
	racersList.ondragover=handleDragOver;
	volsList.ondragover=handleDragOver;
	var members=document.getElementsByTagName("li");
	for(var i=0;i<members.length;i++){
		members[i].ondragstart=handleDragStart;
		members[i].ondragend=handleDragEnd;
	}
}
window.onload=loadDemo;//ҳ��һ���ؾ͡�����
function handleDragStart(evt){//�߰汾������Զ���ʶevtΪevent
	var src=evt.srcElement||evt.target;
	var name=src.innerHTML;
	var age=src.getAttribute("data-age");
	//alert(name+age);
	evt.dataTransfer.setData('text',name+","+age);
	//�����ʽ
	racersList.className="validtarget";
	volsList.className="validtarget";
	
	var racersSet=document.getElementById("racersField");
	var volsSet=document.getElementById("volunteersField");
	racersSet.ondragover=handlesetDragOver;
	volsSet.ondragover=handlesetDragOver;
	racersList.ondrop=handleDrop;
	volsList.ondrop=handleDrop;
}
function handleDragEnd(){
	//ȡ����ʽ
	racersList.className="";
	volsList.className="";
}
function handleDragOver(evt){
	evt.cancelBubble=true;
	evt.preventDefault();//ȡ��Ĭ�ϵ�over����
	var src=evt.srcElement||evt.target;
	if(src.id=="racers")
		racersList.className="highlighted";
	else
		volsList.className="highlighted";

}
function handlesetDragOver(evt){
	var src=evt.srcElement||evt.target;
	if(src.id=="racersField")
		racersList.className="validtarget";
	else
		volsList.className="validtarget";
}
function handlleDrop(evt){

	evt.cancelBubble=true;
	evt.preventDefault();//ȡ��Ĭ�ϵ�over����
	var src=evt.srcElement||evt.target;
	var txt=evt.dataTransfer.get("text");
	
	var name=txt.split(',')[0];
	var age=txt.split(',')[1];
	alert(age);
	if(src.id=="racers"){
		if(racers.indexOf(name)==-1)
			racers.push(name);
			racersList.innerHTML="";
		for(var i=0;i<races.length;i++){
			var li=document.createElement("li");
			li.innerHTML=name+":"+age;
			racersList.appendChild(li);
		}
	}
	else
		if(vols.indexof(name)==-1)
			vols.push(name);
	volsList.innerHTML="";
}









