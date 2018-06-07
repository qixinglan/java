function move(elem,topStart,leftStart,topEnd,leftEnd,interval) {
	var left = leftStart;
	var top = topStart;
	if(topStart != topEnd || leftStart != leftEnd)	{
		var timer = window.setInterval(function(){frame();}, interval); // draw every 5ms 
		function frame() {
			if(topStart == topEnd){
				leftEnd > leftStart ? left++ : left--;
				elem.style['left'] = left + 'px';
				if (left == leftEnd)  // check finish condition 
					window.clearInterval(timer);
			}
			else if(leftStart == leftEnd){
				topEnd > topStart ? top++ : top--;
				elem.style['top'] = top + 'px';
				if (top == topEnd)  // check finish condition 
					window.clearInterval(timer);
			}
			else{
				topEnd > topStart ? top++ : top--;
				var rate = Math.abs((leftEnd-leftStart)/(topEnd-topStart));
				leftEnd > leftStart ? left += rate : left -= rate
		
				elem.style['top'] = top + 'px'; // show frame  
				elem.style['left'] = left + 'px';
				if (top == topEnd)  // check finish condition 
					window.clearInterval(timer);
			}			
		} 	
	}
} 
function bg_move(elem,leftStart,leftEnd,interval) {
	var left = leftStart;
	if(leftStart != leftEnd)	{
		var timer = window.setInterval(function(){frame();}, interval); // draw every 5ms 
		function frame() {
			leftEnd > leftStart ? left++ : left--;
 
			elem.style.backgroundPosition = left + 'px ' + "bottom";
			if (left == leftEnd)  // check finish condition 
				window.clearInterval(timer);			
		} 	
	}
} 
