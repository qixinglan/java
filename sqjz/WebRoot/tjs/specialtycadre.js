/**
 * SpecialtyCadre
 * Version 1.0 
 * 
 */
 //获取参数
		var url = window.location.href;
		var xl;
		var td;
		if(url.indexOf('xl=') > 0){
			xl=url.substring(url.indexOf('xl=') + 3,url.indexOf('xl=') + 5);
			if(xl=="00")
				choice(0);
			if(xl=="01")
				choice(1);
			if(xl=="02")
				choice(2);
			if(xl=="03")
				choice(3);
			if(xl=="04")
				choice(4);
			if(xl=="05")
				choice(5);
			if(xl=="06")
				choice(6);
			if(xl=="07")
				choice(7);
		}else{
			choice(0);
		}
		
		//如果查询方式为按专业查询则隐藏“所有系列”和“翻译系列”
		var cxfs = document.getElementById("cxfs").value;
		if(cxfs=="zc" && (xl=="00" || xl=="07")){
			document.getElementById("zytj").style.display = "none";
		}
		if(cxfs=="zy"){
			document.getElementById("0").style.display = "none";
			document.getElementById("7").style.display = "none";
		}
		function choice(type){
			var typeb=''+type;
			for(var i=0;i<=7;i++){
				var td = document.getElementById(i);
				if(i==type){
					td.style.background='url(../image/tabbgonclick.gif)';
					td.style.backgroundRepeat='no-repeat';
				}else{
					td.style.background='url(../image/tabbg.gif)';
					td.style.backgroundRepeat='no-repeat';
				}
			}
			
		}
		
		
		//导航
		function daohang(){
			window.location.href='/zhzs/index/list.action?tag=5';
		}
		
		