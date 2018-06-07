function getCurrentBrowser(){ 
	var browser = { 
		msie: false, 
		firefox: false, 
		opera: false, 
		safari: false, 
		chrome: false, 
		netscape: false, 
		name: 'unknown', 
		version: 0 
	}, 
	userAgent = window.navigator.userAgent.toLowerCase(); 
	if ( /(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test( userAgent ) ){ 
		browser[RegExp.$1] = true; 
		browser.name = RegExp.$1; 
		browser.version = RegExp.$2; 
	} else if ( /version\D+(\d[\d.]*).*safari/.test( userAgent ) ){ // safari 
		browser.safari = true; 
		browser.name = 'safari'; 
		browser.version = RegExp.$2; 
	} 
	
	return browser; 
} 
