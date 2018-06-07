/**
 * Filename:    selectDialog.js
 * Description: 报表查询条件设置页面用JavaScript脚本
 * Company:     ITC
 * author:      武继超
 * Create at:   2011-12-29
 *
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * Copyright (c) 2010-2011 .All rights reserved.
 */
var codeClass = "";
$(document).ready(function(){
	
	// 取得传递的参数
	//var obj = window.dialogArguments;
	// title
	//$("#tagTitle").text(obj.tagTitle);
	// value
	//$("#tagValue").val(obj.tagValue);
	//codeClass = obj.codeClass;
	// 按类型初始化右侧可选区域,
	// 类型-- 1、定长字符串（代码等）;
    //        2、不定长字符串（名称等）;
    //        3、数值;
    //        4、日期;
    //        5、下拉框(选项少);
    //        6、下拉框（选项多）;
    //        7、树形结构
	switch(obj.tagType){
		case "1":
			$("#trCon1").show();
			$("#trCon2").show();
			$("#trCon10").show();
			$("#trCon11").show();
			break;
		case "2":
			$("#trCon1").show();
			$("#trCon2").show();
			$("#trCon9").show();
			$("#trCon10").show();
			$("#trCon11").show();
			break;
		case "3":
		case "4":
			$("#trCon1").show();
			$("#trCon2").show();
			$("#trCon3").show();
			$("#trCon4").show();
			$("#trCon5").show();
			$("#trCon6").show();
			$("#trCon7").show();
			$("#trCon8").show();
			$("#trCon10").show();
			$("#trCon11").show();
			break;
		case "5":
		case "6":
			$("#trCon1").show();
			$("#trCon2").show();
			$("#trCon7").show();
			$("#trCon8").show();
			$("#trCon10").show();
			$("#trCon11").show();
			break;
		case "7":
		    loadTree(obj.codeClass,obj.ifAll);
			$("#trCon1").show();
			$("#trCon2").show();
			$("#trCon7").show();
			$("#trCon8").show();
			$("#trCon10").show();
			$("#trCon11").show();
			break;
		default :
			break;
	}

	/**
	 * 确定按钮事件
	 */
	$("#btnOk").click(function() {
		var rs = $("#tagValue").val();
		rs = format(rs);
		$("#tagValue").val(rs);
		var flg = check(rs);
		if (!flg) {
			return false;
		}
		window.returnValue = rs;
		window.close();
	});
	
	/**
	 * 取消按钮事件
	 */
	$("#btnCancel").click(function() {
		window.close();
	});
	

	/**
	 * 处理按钮事件
	 */
	$("#btnClear").click(function() {
		$("#tagValue").val("");
	});
});


///////////////////////////////////////////////////////////////
// 增加运算符
///////////////////////////////////////////////////////////////

/**
 * 选择运算符
 * @param flag
 * @return
 */
function setCon(flag){
	var tagValue = $("#tagValue").val();
	switch(flag){
		case "1":	
			strSign = addCon(tagValue,"AND")
			break;
		case "2":			
			strSign = addCon(tagValue,"OR")
			break;
		case "3":	
			strSign = addCon(tagValue,">")
			break;
		case "4":	
			strSign = addCon(tagValue,"<")
			break;
		case "5":	
			strSign = addCon(tagValue,">=")
			break;
		case "6":	
			strSign = addCon(tagValue,"<=")
			break;
		case "7":	
			strSign = addCon(tagValue,"=")
			break;
		case "8":	
			strSign = addCon(tagValue,"!=")
			break;
		case "9":	
			strSign = addCon(tagValue,"LIKE")
			break;
		case "10":	
			strSign = addCon(tagValue,"(")
			break;
		case "11":	
			strSign = addCon(tagValue,")")
			break;
	}
	$("#tagValue").val(strSign);
}

/**
 * 符号选择
 * @param lable
 * @param con
 * @return
 */
function addCon(lable,con) {

	if(lable == null){
		lable = "";
	}
	// 格式化输入内容
	lable = format(lable);
	// 去掉所有首末空格
	lable = trim(lable);
	// 在首位置增加一个空格
	lable = " " + lable;
	// 替换全部空格的正则表达式
	var regb = new RegExp(" ","g");
	// 替换全部☭的正则表达式
	var rege = new RegExp("☭","g");
	
	// 处理前将空格全部替换成☭
	lable = lable.replace(regb,"☭");
	
	// lable为空时
	if (lable == "☭") {
		// 第一次con不能选择 AND、OR、)三种选项，选择其他直接返回选择的con
		if (con == "AND" || con == "OR" || con == ")") {
			return "";
		} else {
			return " " + con + " ";
		}
	} else {
		// 取得最后一个☭后面的内容
		var strLast = lable.substring(lable.lastIndexOf("☭") + 1, lable.length);
		// 最后一个☭(包含本身)之前部分
		var strOther = lable.substring(0, lable.lastIndexOf("☭") + 1);
		// 如果最后一个☭后面的内容是con的一种
		if (strLast == "(") {
			// “(”后面不能是AND、OR、)
			if (con == "AND" || con == "OR" || con == ")") {
				return lable.replace(rege," ") + " ";
			} else {
				return lable.replace(rege," ") + " " + con + " ";
			}
		} else if (strLast == ")") {
			// “)”后面不能是>、<、>=、<=、=、!=、LIKE
			if (con == "(" || con == "AND" || con == "OR") {
				return lable.replace(rege," ") + " " + con + " ";
			} else if (con == ")") {
				// “)”后面是“)”时，判断前面是否有未成对的“(”
				var left = lable.split("(");
				var right = lable.split(")");
				if (left.length > right.length) {
					return lable.replace(rege," ") + " " + con + " ";
				} else {
					return lable.replace(rege," ") + " ";
				}
			} else {
				return lable.replace(rege," ") + " ";
			}
			
		} else if (strLast == "AND"	|| strLast == "OR") {
			// 如果最后部分与con 相等，并且是AND、OR 则用con替换最后部分
			if (con == "AND" || con == "OR") {
				return strOther.replace(rege," ") + con + " ";
			} else {
				// 如果con不是AND 、OR，并且不是),则在最后部分之后增加con
				if (con != ")") {
				    return lable.replace(rege," ") + " " + con + " ";
				} else {
					// 如果是), 直接返回（AND、OR 后面不能是")"）
					return lable.replace(rege," ") + " ";
				}
			}
		} else if (strLast == ">"
					|| strLast == "<"
					|| strLast == ">="
					|| strLast == "<="
					|| strLast == "="
					|| strLast == "!="
					|| strLast == "LIKE") {
			// 如果con是运算符，则用con替换最后部分
			if (con == ">" || con == "<"
					|| con == ">=" || con == "<="
					|| con == "=" || con == "!="
					|| con == "LIKE") {
				return strOther.replace(rege," ") + con + " ";
			} else {
				// 如果con不是运算符，直接返回
				return lable.replace(rege," ") + " ";
			}
			
		} else {
			// 如果最后一个☭后面的内容不是con的一种
			// 后面只能是AND、OR、)
			if (con == "AND" || con == "OR") {
				return lable.replace(rege," ") + " " + con + " ";
			} else if (con == ")") {
				// 判断前面是否有未成对的“(”
				var left = lable.split("(");
				var right = lable.split(")");
				if (left.length > right.length) {
					return lable.replace(rege," ") + " " + con + " ";
				} else {
					return lable.replace(rege," ") + " ";
				}
			} else {
				return lable.replace(rege," ") + " ";
			}
		}
		
	}
	// 返回前☭替换成空格
	//return lable.replace(rege," ") + " ";
}

function format(lable) {
	
	if(lable == null || lable == ""){
		return "";
	}

	// 遍历每个运算符，为每个运算符前后各增加一个空格
	
	var tmp = lable;
	tmp = tmp.replace(/>/g," > ");
	tmp = tmp.replace(/</g," < ");
	tmp = tmp.replace(/=/g," = ");
	tmp = tmp.replace(/>  =/g," >= ");
	tmp = tmp.replace(/<  =/g," <= ");
	tmp = tmp.replace(/! =/g," != ");
	// and or like 全部替换为大写格式
	tmp = tmp.replace(/ and/ig," AND ");
	tmp = tmp.replace(/ or/ig," OR ");
	tmp = tmp.replace(/ like/ig," LIKE ");
	// 去掉重复的空格
	tmp = tmp.replace(/\s{2,}/g," ");
	return " " + trim(tmp) + " ";
}

function check(lable) {
	
	// 判断括号是否都是成对
	var left = lable.split("(");
	var right = lable.split(")");
	if (left.length != right.length) {
		alert("\u5b58\u5728\u672a\u95ed\u5408\u62ec\u53f7,\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
		return false;
	}
	
	var regb = new RegExp(" ","g");
	// 去掉全部空格
	var tmp = lable.replace(regb,"");
	var tmp1="";
	var aflg=false;
	for (i=0; i<tmp.length; i++) {
		var str = tmp.substring(i,i+1);
		var flg = false;
		if (str == "(" || str == ")" || str == "=") {
			flg = true;
		} else if (str == "!" || str == ">" || str == "<") {
			var next = tmp.substring(i+1,i+2);
			if (next == "=") {
				str = str + next;
				i++;
			} else {
				atr = str;
			}
			flg = true;
		} else if (str == "A") {
			var next = tmp.substring(i+1,i+3);
			if (next == "ND") {
				str = str + next;
				i = i+2;
				flg = true;
			}
		} else if (str == "O") {
			var next = tmp.substring(i+1,i+2);
			if (next == "R") {
				str = str + next;
				i++;
				flg = true;
			}
		} else if (str == "L") {
			var next = tmp.substring(i+1,i+4);
			if (next == "IKE") {
				str = str + next;
				i = i+3;
				flg = true;
			}
		} else {
			tmp1="";
		}
		if (flg) {
			if (tmp1 == "") {
				tmp1 = str;
			} else {
				// 排除合理的连续
				if ((tmp1 == "(" || tmp1 == ")") && tmp1 == str) {
					tmp1 = str;
				} else if (tmp1 == ")" && (str == "AND" || str == "OR" || str == ")")) {
					tmp1 = str;
				} else if ((tmp1 == "AND" || tmp1 == "OR") && (str != "AND" && str != "OR" && str !=")")) {
					tmp1 = str;
				} else if (tmp1 == "(" && (str != "AND" || str != "OR" || str != ")")) {
					tmp1 = str;
				} else {
					aflg=true;
					break;
				}
			}
		}
	}
	
	if (aflg || (tmp1 != "" && tmp1 != ")")) {
		alert("\u5b58\u5728\u65e0\u610f\u4e49\u7684\u8fd0\u7b97\u7b26\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff01");
		return false;
	}
	
	
	return true;
}
////////////////////////////////////////////////////////////////
// 格式化tagValue
///////////////////////////////////////////////////////////////

/**
 * 日期选择
 */
function setCal(obj){
	
	var lableValue = format($("#tagValue").val());
	if(!parseLable(lableValue)){
		if(trim(lableValue)==""){
			$("#tagValue").val(" " + obj + " ");
		}else{
			$("#tagValue").val(lableValue + " " +obj + " ");
		}
	}else{
		$("#tagValue").val(lableValue.substring(0, rtrim(lableValue).lastIndexOf(" ")) + " " + obj + " ");
	}
}

/**
 * 下拉框选择
 */
function setSelect(id){

	var lableValue = format($("#tagValue").val());
	var text =  $("#"+id).find("option:selected").text();
	if (text.indexOf(":") > -1) {
		text = text.substring(text.lastIndexOf(":")+1);
	}
	if(!parseLable(lableValue)){
		if(trim(lableValue)==""){
			$("#tagValue").val(" " + text + " ");
		}else{
			$("#tagValue").val(lableValue + " " + text + " ");
		}
	}else{
		$("#tagValue").val(lableValue.substring(0, rtrim(lableValue).lastIndexOf(" ")) + " " + text + " ");
	}
}

/**
 * 格式化
 */
function parseLable(lableValue){
	
	if(lableValue == ""){
			return false;
	}else{
		var lableArray = trim(format(lableValue)).split(" ");
		if (lableArray[lableArray.length-1]!="AND" && 
		    lableArray[lableArray.length-1]!="OR" && 
		    lableArray[lableArray.length-1]!=">" && 
			lableArray[lableArray.length-1]!="<" && 
			lableArray[lableArray.length-1]!=">=" && 
			lableArray[lableArray.length-1]!="<=" && 
			lableArray[lableArray.length-1]!="=" && 
			lableArray[lableArray.length-1]!="!=" && 
			lableArray[lableArray.length-1]!="LIKE" &&
			lableArray[lableArray.length-1]!="(" &&
			//lableArray[lableArray.length-1]!=")" &&
			lableArray[lableArray.length-1]!="") {
			return true;
		}
	}
	return false;
}

function trim(lable) 
{ return lable.replace(/(^\s*)|(\s*$)/g, ""); } 
function ltrim (lable)
{ return lable.replace(/(^\s*)/g, ""); }  
function rtrim (lable)
{ return lable.replace(/(\s*$)/g, ""); } 

//加载树 ,通过type区分不同的树
function loadTree(type,ifAll){
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		async: {
			enable: true,
			type: "post",
			url: jQuery.ctx+"/system/anj136!getSelectByReport.action?cacheType=" + type + "&ifAll=" + ifAll
		},
		callback: {
			onAsyncSuccess:onAsyncSuccess,
			onClick: zTreeOnClick
		}
	};
	//zTreeObj = $("#tree").zTree(setting, null);

	$.fn.zTree.init($("#tree"), setting, null);
}

function onAsyncSuccess(){
	$("#divWait").hide();
}

//点击树事件
function zTreeOnClick(event, treeId, treeNode, clickFlag){
	//selectedNode = treeNode.getSelectedNode();
	if (codeClass != "SAFE_SAFE") {
		var name = treeNode.name;
		if (name.indexOf(":") > -1) {
			name = name.substring(name.lastIndexOf(":")+1);
		}
	    var lableValue = format($("#tagValue").val());
	    if(!parseLable(lableValue)){
	        if(trim(lableValue)==""){
	            $("#tagValue").val(" " + name + " ");
	        }else{
	            $("#tagValue").val(lableValue + " " + name + " ");
	        }
	    }else{
	        $("#tagValue").val(lableValue.substring(0, rtrim(lableValue).lastIndexOf(" ")) + " " + name + " ");
	    }
	} else {
		var name = treeNode.id;
	    var lableValue = format($("#tagValue").val());
	    if(!parseLable(lableValue)){
	        if(trim(lableValue)==""){
	            $("#tagValue").val(" " + name + " ");
	        }else{
	            $("#tagValue").val(lableValue + " " + name + " ");
	        }
	    }else{
	        $("#tagValue").val(lableValue.substring(0, rtrim(lableValue).lastIndexOf(" ")) + " " + name + " ");
	    }
	}
}