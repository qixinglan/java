function searchAndReplace(){
	//Step1:鑾峰緱椤甸潰涓婄殑鏂囨湰妗嗭紝鍐嶄娇鐢ㄦ枃鏈鐨剉alue灞炴�鑾峰緱鐢ㄦ埛鍦ㄦ枃鏈涓緭鍏ョ殑鏄粈涔堬紵
	//Step2:浠庡紑濮嬩綅缃煡鎵锯�js鈥濋娆″嚭鐜扮殑绱㈠紩浣嶇疆锛�
	//Step3:濡悜鍚庢煡鎵傛灉鎼滅储缁撴灉涓�1锛岃鏄庢牴鏈病鎵惧埌鈥渏s鈥濓紝鐩存帴鎻愮ず鈥滃畨鍏ㄢ�

	
	//Step4:濡傛灉鎼滅储缁撴灉涓嶇瓑浜�1锛岃鏄庤嚦灏戞湁涓�釜鈥渏s鈥濄�鍒欑户缁悜涓嬫墽琛�
	//鐢变簬replace鏂规硶涓�鍙兘鏇挎崲涓�釜鏌ユ壘鍒扮殑"js"锛屽鏋滄兂閮芥浛鎹紝鍙兘鍊熷姪寰幆銆傜户缁惊鐜殑鍒ゆ柇渚濇嵁搴旇鏄紝鍦ㄥ綋鍓嶄綅缃箣鍚庢槸鍚﹁繕鎵惧埌浜嗘柊鐨�js"銆傛瘡娆″惊鐜唴閮藉簲璇ユ湁涓や釜鎿嶄綔锛�
			//Step4.1:棣栧厛鏇挎崲褰撳墠浣嶇疆鎵惧埌鐨勪竴涓�js"涓�**"
			//Step4.2:鐒跺悗浠庡綋鍓嶄綅缃户缁撅紝鐪嬩笅涓�鍑虹幇"js"鐨勪綅缃湪鍝噷銆�
			//绋嬪簭浼氬洖鍒皐hile寰幆鍒ゆ柇鏄惁缁х画寰幆
	//Step5:鏈�粓锛岀粡杩囧惊鐜浛鎹㈠悗鐨勬柊瀛楃涓诧紝鍐嶄繚瀛樺洖缃戦〉涓婄殑鏂囨湰妗嗕腑锛屾浛鎹㈡棫鐨勫唴瀹广�
	//在MyEclipse无法执行，用浏览器
	var txtobj=document.getElementById("txtString");
	var str=txtobj.value;
	
	if(str.trim().length>0){
		var reg=/js/gi;
//		var index=0;;
//		while((index=str.indexOf("js",index))>-1){
			var strs=str.match(reg);
			str=str.replace(reg,"**");
			txtobj.value=str;
			alert("共替换了"+strs.length+"次");
//		}
	}
	
}