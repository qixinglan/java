//Step1:创建字符串对象strMario，内容为Hello Mario!：
var strMario = "Hello Mario!";//或者 var str2= new String("hello world!");
var funcs = ["strMario.length"
,"strMario.toLowerCase()"
,"strMario.toUpperCase()"
,""
,"strMario.charAt(4)"
,"strMario.indexOf(\"lo\")"
,"strMario.lastIndexOf(\"lo\")"
,"strMario.substring(3,7)"
,"strMario.replace(\"o\",\"O\")"
,""
,"strMario.split(\" \")"];
var step = 0;
var startTime;
var start = function(){
	startTime = new Date();
	run();
}
var page_load = function(){
	var mario = document.getElementById("mario");
	mario.addEventListener('click',start,false);
	scroll(0,document.body.scrollHeight) ;
}
window.addEventListener('load',page_load,false);

var closeDialog = function(){
	var dialogObj = document.getElementById("dialog");
	dialogObj.style.display = "none";
}

function print(){
	var dialogObj = document.getElementById("dialog");
	var txtInput = document.getElementById("txtInput");
	/*if(step < 11)
		txtInput.value = funcs[step];*/
	dialogObj.style.display = "block";
	document.getElementById("txtInput").focus();
	var msgObj = document.getElementById("msg");
	var btn = document.getElementById("btnOK");
	if(step != 0)
		msgObj.innerHTML += "<br/>"
	switch (step){
		case 0:
			msgObj.innerHTML += 
				"恭喜你，得到一个字符串对象：<br/>" + 
				"<strong>var strMario = \"Hello Mario!\";</strong><br/>" + 
				"请使用strMario对象的<span>.length</span>属性，取得字符串的长度：";				
			break;
		case 1:
			msgObj.innerHTML += 
				"请使用strMario对象的<span>.toLowerCase()</span>方法，查看字符串转成小写后的样子：";
			break;
		case 2:
			msgObj.innerHTML += 
				"请使用strMario对象的<span>.toUpperCase()</span>方法，查看字符串转成大写后的样子：";
			break;
		case 3:
			txtInput.setAttribute("readOnly",'true');
			txtInput.value = "不必输入内容，直接按回车继续！";
			msgObj.innerHTML += 
				"虽然使用了strMario对象的<span>.toUpperCase()</span>和<span>.toUpperCase()</span>方法，查看字符串转成大写/小写后的样子。但strMario的内容依然是：<span>" + strMario + "</span>没变。说明转换只是一种<span>临时行为</span>，并未改变字符串内容！";
			break;
		case 4:
			txtInput.removeAttribute("readOnly");
			msgObj.innerHTML += 
				"使用strMario对象的<span>.charAt(4)</span>方法，查看第五个（索引是4）位置的字符：";
			break;
		case 5:
			msgObj.innerHTML += 
				"使用strMario对象的<span>.indexOf(\"lo\")</span>方法，从开始(索引为0)查找字符串'lo'所在的位置：";
			break;
		case 6:
			msgObj.innerHTML += 
				"使用strMario对象的<span>.lastIndexOf(\"lo\")</span>方法，查找'lo'最后出现的位置：";
			break;
		case 7:
			msgObj.innerHTML += 
				"使用strMario对象的<span>.substring(3,7)</span>方法，获取从位置3至位置7之间的子字符串（前包含后不包含）：";
			break;
		case 8:
			msgObj.innerHTML += 
				"使用strMario对象的<span>.replace(\"o\",\"O\")</span>方法，替换字符串中的\"o\"为\"O\"：";
			break;
		case 9:
			txtInput.setAttribute("readOnly",'true');
			txtInput.value = "不必输入内容，直接按回车继续！";
			msgObj.innerHTML += 
				"虽然使用了strMario对象的<span>.replace()</span>方法得到了替换后的新字符串。但strMario的内容依然是：<span>" + strMario + "</span>没变。说明替换只是一种<span>临时行为</span>，并未改变字符串内容！";
			break;
		case 10:
			txtInput.removeAttribute("readOnly");
			msgObj.innerHTML += "使用strMario对象的<span>.split(\" \")</span>方法，把字符串以空格为界，分割成两个子字符串：";
			break;
		case 11:
			txtInput.setAttribute("readOnly",'true');
			msgObj.innerHTML += "<p>/*MISSION COMPLETE!*/</p><p>Congretulations!</p>";
			var end = document.getElementById("end");
			end.style.display = "block";
			var endTime = new Date();
			msgObj.innerHTML += "<h6>" + "用时" + dateDiff(endTime,startTime) + "秒！不错哦！</h6>";
			msgObj.innerHTML += "<p>向上滚动，可以复习刚才的内容！</p>";
			break;
		default:
			break;
	}
	if(step < 11)	{
		btn.addEventListener('click',vali,false);
		msgObj.innerHTML += "<br/>" + "press ENTER to continue..." + "<br/>";		
	}
	msgObj.scrollTop = msgObj.scrollHeight;
}

var vali = function(){
	var txt = document.getElementById("txtInput").value;
	var btn = document.getElementById("btnOK");
	var msgObj = document.getElementById("msg");
	if(step != 3 && step != 9)
	msgObj.innerHTML += "<br/><span>" + txt + "</span><br/>";
	try{
		if(step != 3 && step !=9){
			var r = eval(txt);
			if(r == undefined)
				throw new Error(txt + " is undefined");
			var func = funcs[step].toString();
			if(txt != func)
				throw new Error("输入有误！");
			switch (step){
				case 0:
					msgObj.innerHTML += "strMario的长度是：<span>" + r
					break;
				case 1:
					msgObj.innerHTML += "strMario转成小写后是：<span>" + r;
					break;
				case 2:
					msgObj.innerHTML += "strMario转成大写后是：<span>" + r;
					break;
				case 4:
					msgObj.innerHTML += "第五个（索引是4）位置的字符是：<span>" + r;
					break;
				case 5:
					msgObj.innerHTML += "字符串'lo'所在的位置是：<span>" + r;
					break;
				case 6:
					msgObj.innerHTML += "'lo'最后出现的位置是：<span>" + r;
					break;
				case 7:
					msgObj.innerHTML += "位置3至位置7之间的子字符串是：<span>" + r + "<br/>注意：前包含后不包含！";
					break;
				case 8:
					msgObj.innerHTML += "替换字符串中的\"o\"为\"O\",得到新字符串是：<span>" + r + "<br/>注意：仅第一个o被替换！";
					break;
				case 10:
					msgObj.innerHTML += "分割后得到的数组是：<span>" + r.join(" | ") + "<br/>可使用[index]访问各子字符串！";
					break;
				default:
					break;
			}
			msgObj.innerHTML += "</span></br>";
		}
		msgObj.innerHTML += "</br>3秒后继续！";
			var i = 0;
			var timer = setInterval(function(){
				msgObj.innerHTML += "... "; 
				i++;
				if(i>2) {
					msgObj.innerHTML += "<br/>"; 
					clearInterval(timer);
				}
			},900);
		step++;
		setTimeout(closeDialog,3000);
		setTimeout(run,3500);
	}catch(ex){
		var fail = document.getElementById("fail");
		fail.style.display = "block";
		var txtInput = document.getElementById("txtInput");
		txtInput.setAttribute("readOnly",'true');
		msgObj.innerHTML += "<span>" + ex.message + "</span>";
		msgObj.innerHTML += "<p>" + "/*GAME OVER!*/" + "</p>";
		var endTime = new Date();
		msgObj.innerHTML += "<h6>" + "你只坚持了" + dateDiff(endTime,startTime) + "秒</h6>";
		msgObj.innerHTML += "<p>向上滚动查看错在哪儿？继续努力！</p>";
	}finally{
		document.getElementById("txtInput").value = "";
		btn.removeEventListener('click',vali,false);
		msgObj.scrollTop = msgObj.scrollHeight;
	}
}

var run = function(){
	var mario = document.getElementById("mario");
	mario.removeEventListener('click',start,false);
	switch (step){
		case 0:
			move(mario,540,0,540,90,10);
			setTimeout(function(){move(mario,540,90,530,90,10);},900);
			setTimeout(function(){move(mario,530,90,540,90,10);},1000);
			setTimeout(function(){print();},1200);
			break;
		case 1:
			move(mario,540,90,540,165,10);
			setTimeout(function(){move(mario,540,165,530,165,10);},750);
			setTimeout(function(){move(mario,530,165,540,165,10);},850);
			setTimeout(function(){print();},950);
			break;
		case 2:
			move(mario,540,165,525,190,30);
			setTimeout(function(){move(mario,525,190,540,210,30);},450);
			setTimeout(function(){move(mario,540,210,530,210,20);},900);
			setTimeout(function(){move(mario,530,210,540,210,20);},1000);
			setTimeout(function(){print();},1100);
			break;
		case 3:
			move(mario,540,210,540,260,10);
			setTimeout(function(){move(mario,540,260,475,235,5);},500);
			setTimeout(function(){move(mario,475,235,475,190,10);},1150);
			setTimeout(function(){
				bg_move(document.body,0,-150,10);
				move(mario,475,190,475,40,10);
			},1600);
			setTimeout(function(){move(mario,475,40,465,40,10);},3100);
			setTimeout(function(){move(mario,465,40,475,40,10);},3200);
			setTimeout(function(){print();},3300);
			break;
		//background-position:-150px bottom;
		case 4:
			move(mario,475,40,475,85,10);
			setTimeout(function(){move(mario,475,85,450,110,10);},450);
			setTimeout(function(){move(mario,450,110,510,140,10);},700);
			setTimeout(function(){move(mario,510,140,510,175,10);},1300);
			setTimeout(function(){move(mario,510,175,540,175,10);},1650);
			setTimeout(function(){move(mario,540,175,540,225,10);},1950);
			setTimeout(function(){move(mario,540,225,530,225,10);},2450);
			setTimeout(function(){move(mario,530,225,540,225,10);},2550);
			setTimeout(function(){print();},2650);
			break;
		case 5:
			move(mario,540,225,540,290,10);
			setTimeout(function(){move(mario,540,290,490,325,10);},650);
			setTimeout(function(){move(mario,490,320,540,360,10);},1150);
			setTimeout(function(){move(mario,540,360,540,410,10);},1650);
			setTimeout(function(){move(mario,540,410,475,430,10);},2150);
			setTimeout(function(){move(mario,475,430,475,440,10);},2900);
			setTimeout(function(){move(mario,475,440,465,440,10);},3000);
			setTimeout(function(){move(mario,465,440,475,440,10);},3100);
			setTimeout(function(){print();},3200);
			break;
		case 6:
			move(mario,475,440,475,425,20);
			setTimeout(function(){move(mario,475,425,475,445,10);},300);
			setTimeout(function(){move(mario,475,445,450,495,10);},450);
			setTimeout(function(){move(mario,450,495,475,545,10);},700);
			setTimeout(function(){
				bg_move(document.body,-150,-350,10);
				move(mario,475,545,475,345,10);
			},950);
			setTimeout(function(){move(mario,475,345,475,360,10);},3050);
			setTimeout(function(){move(mario,475,355,540,405,10);},3350);
			setTimeout(function(){move(mario,540,405,530,405,10);},4000);
			setTimeout(function(){move(mario,530,405,540,405,10);},4100);
			setTimeout(function(){print();},4200);
			break;
		//background-position:-350px bottom;
		case 7:
			move(mario,540,405,540,385,10);
			setTimeout(function(){move(mario,540,385,475,405,10);},300);
			setTimeout(function(){move(mario,475,405,475,430,10);},950);
			setTimeout(function(){move(mario,475,430,540,455,10);},1200);
			setTimeout(function(){move(mario,540,455,530,455,10);},1850);
			setTimeout(function(){move(mario,530,455,540,455,10);},1950);
			setTimeout(function(){print();},2050);
			break;
		case 8:
			move(mario,540,455,540,505,10);
			setTimeout(function(){move(mario,540,505,530,505,10);},500);
			setTimeout(function(){move(mario,530,505,540,505,10);},600);
			setTimeout(function(){print();},700);
			break;
		case 9:
			move(mario,540,505,540,515,10);
			setTimeout(function(){move(mario,540,515,485,550,10);},100);
			setTimeout(function(){move(mario,485,550,540,585,10);},650);
			setTimeout(function(){move(mario,540,585,475,585,10);},1200);
			setTimeout(function(){move(mario,475,585,475,605,10);},1850);
			setTimeout(function(){move(mario,475,605,410,635,10);},2050);
			setTimeout(function(){move(mario,410,635,410,685,10);},2700);
			setTimeout(function(){move(mario,410,685,370,720,10);},3200);
			setTimeout(function(){move(mario,370,720,410,755,10);},3550);
			setTimeout(function(){
				bg_move(document.body,-350,-550,10);
				move(mario,410,755,410,555,10);
			},3950);	
			setTimeout(function(){move(mario,410,555,410,635,10);},6000);
			setTimeout(function(){move(mario,410,635,540,635,10);},6800);
			setTimeout(function(){move(mario,540,635,475,615,10);},8100);
			setTimeout(function(){move(mario,475,615,475,580,10);},8750);
			setTimeout(function(){move(mario,475,580,465,580,10);},9100);
			setTimeout(function(){move(mario,465,580,475,580,10);},9200);
			setTimeout(function(){print();},9300);
			break;
		//background-position:-550px bottom;
		case 10:
			move(mario,475,580,475,600,10);
			setTimeout(function(){move(mario,475,600,465,600,10);},200);
			setTimeout(function(){move(mario,465,600,475,600,10);},300);
			setTimeout(function(){print();},400);
			break;
		case 11:
			move(mario,475,600,475,580,10);
			setTimeout(function(){move(mario,475,580,475,610,10);},200);
			setTimeout(function(){move(mario,475,610,510,670,10);},500);
			setTimeout(function(){move(mario,510,670,475,700,10);},850);
			setTimeout(function(){move(mario,475,700,475,715,10);},1200);
			setTimeout(function(){move(mario,475,715,440,785,10);},1350);
			setTimeout(function(){move(mario,440,785,540,845,10);},1700);
			setTimeout(function(){move(mario,540,845,475,925,10);},2700);
			setTimeout(function(){move(mario,475,925,410,1005,10);},3350);
			setTimeout(function(){move(mario,410,1005,350,1105,10);},4000);
			setTimeout(function(){move(mario,350,1105,395,1150,10);},4600);
			setTimeout(function(){move(mario,395,1150,525,1150,10);},5050);
			setTimeout(function(){move(mario,525,1150,525,1170,10);},6350);
			setTimeout(function(){move(mario,525,1170,540,1190,10);},6550);
			setTimeout(function(){move(mario,540,1190,540,1250,10);},6700);
			setTimeout(function(){
				mario.style.display = "none";
				print();
			},7300);
			break;
		default:
			break;
	}
	
}

function dateDiff(date1, date2){ 
    if(date1.getTime) 
    date1 = date1.getTime(); 

    if(date2.getTime) 
    date2 = date2.getTime(); 
    return (date1 - date2) / 1000;//结果是秒 
}