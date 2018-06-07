//Step1:瀹氫箟calculate鏂规硶鐢ㄤ簬鎵ц鐢ㄦ埛鐐瑰嚮姣忎釜鎸夐挳鏃剁殑鍔熻兘銆傚畾涔変竴涓弬鏁皊tr鎺ユ敹鐢ㄦ埛鐐瑰嚮鐨勬寜閽殑鍊�
//Step2:<body>涓紝涓烘瘡涓寜閽坊鍔爋nclick浜嬩欢锛屼娇鍏舵墽琛宑alculate鏂规硶銆傚苟涓旀瘡涓寜閽兘鎶婅嚜宸辩殑value锛屼綔涓哄弬鏁颁紶閫掔粰calculate鏂规硶銆�
//Step3:浣跨敤switch锛屽皢鎸夐敭鍒嗘垚涓夌鎯呭喌锛氭寜c鏃讹紝娓呯┖鏂囨湰妗嗗唴瀹癸紱鎸夌瓑鍙锋椂锛岃绠楁枃鏈涓殑琛ㄨ揪寮忕粨鏋滃苟鏄剧ず鍦ㄦ枃鏈涓紱鍏朵綑鎸夐敭涓嶈繘琛屼换浣曡绠楋紝鐩存帴灏嗘墍鎸夊唴瀹硅拷鍔犲埌鏂囨湰妗嗗唴瀹圭殑鏈熬銆�
		//绗竴绉嶆儏鍐碉細鎸塁鏃�
			//Step3.1:娓呯┖鏂囨湰妗�
		//绗簩绉嶆儏鍐碉細鎸�鏃�
			//Step3.2:鑾峰緱鏂囨湰妗嗕腑鐨勫唴瀹广�濡傛灉鍐呭涓嶄负绌猴紝鍒欎娇鐢╡val鍑芥暟锛岃绠楀綋鍓嶆枃鏈涓殑琛ㄨ揪寮忕殑鍊笺�缁撴灉鏄剧ず鍥炴枃鏈涓�
			//濡傛灉鍐呭涓嶄负绌�
				//Step3.2.1:鐢变簬杈撳叆鍐呭閿欒锛宔val浼氭姏鍑哄紓甯革紝鎵�互杩欓噷闇�寮曞叆寮傚父澶勭悊锛屼娇鐢╰ry catch(error)銆傚叾涓璽ry鍖呰９浣忔甯哥殑璁＄畻
				//姝ｅ父閫昏緫鏄娇鐢╡val鍑芥暟锛岃绠楀綋鍓嶆枃鏈涓殑琛ㄨ揪寮忕殑缁撴灉锛屾斁鍦ㄥ彉閲弐esult涓�
				//灏唕esult鏄剧ず鍒版枃鏈涓�
				//濡傛灉鎹曡幏鍒板紓甯革紝灏唀rror鏄剧ず鍒版枃鏈涓�
	    //榛樿鎯呭喌涓嬶細
			//Step3.3:濡傛灉鎸夌殑鏄櫘閫氭寜閽紝鍒欏皢鎵�寜鍐呭浣跨敤+=鐨勬柟寮忚拷鍔犲埌鏂囨湰妗嗗唴瀹圭殑缁撳熬銆�
function calc(val){
	//查找文本框
	//根据value的值判断操作
	//写回文本框
	var txtobj=document.getElementById("txtData");
	switch(val){
	case "C":
		txtobj.value="";
		break;
	case "=":
		var input=txtobj.value;
		if(input.length>0){
			try{
				txtobj.value=eval(input);
			}catch(error){
				txtobj.value=error;
			}
		}
		break;
	default:
		txtobj.value+=val;
		
	}
}
