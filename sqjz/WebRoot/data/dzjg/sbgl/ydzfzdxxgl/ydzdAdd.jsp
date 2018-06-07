<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移动执法终端设备管理</title>
<%@ include file="/common/commonjsTemp.jsp"%>
<script language="javascript" src="${ctx}/data/dzjg/sbgl/ydzfzdxxgl/findSim.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/data/dzjg/sbgl/js/org.js" type="text/javascript"></script>
<script type="text/javascript">
	$.org.load("${ctx}/data/jggl/json.action",'<%=orgId%>');
	$(function() {
		//sim卡下拉列表
		$.findSim.combobox("#phoneNumber","phoneNumber","NOUSEDSIM",{
			width:'68%',
			allowBlank : false,
			emptyText : '',
			defValue: '',
			attr:{searchType: "cn"}
		});
		$.dictionary.combobox("#status", "status", "ZFSBZT", {
			width:'68%',
			allowBlank : false, 
			attr:{searchType: "eq"},
			filter:'1,3,5'
		});
		//购买单位
		$.organization.combobox("#buyUnit", "buyUnit", ${user.wunit.bm}, {
			width:'68%',
			allowBlank : false,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '市局',
			defValue : '1',
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			//fieldClass:"search-form-field"
		});
		var changeIndex=0;
		//使用单位司法局
		$.org.combobox("#useUnit", "useUnit", ${user.wunit.bm}, {
			width:'68%',
			allowBlank : false,
			level : 10,
			showItself : true,
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			valuechange:function(value,elem){
				//发现value未改变，第一次也会执行，所以把第一次去掉
				changeIndex++;
				if(changeIndex>1){
					//联动使用单位司法所
					$("#useSfsUnit").empty();
					$.org.combobox("#useSfsUnit", "useSfsUnit", value, {
						width:'68%',
						allowBlank : false,
						level : 10,
						emptyText :'',
						defValue :'', 
						attr:{searchType: "eq",required:"required"},
						notShowType:"1,2,4,5,6,7,8,9",
						//fieldClass:"search-form-field"
					});
					//动态生成sim卡下拉列表
					$.findSim.load("findSim.action?useUnit="+value);
					$("#phoneNumber").empty();
					$.findSim.combobox("#phoneNumber","phoneNumber","NOUSEDSIM",{
						width:'68%',
						allowBlank : false, 
						emptyText : '',
						defValue: '',
						attr:{searchType: "cn"}
					});
					
				}
			}
		});
		//使用单位司法所
		$("#useSfsUnit").empty();
		$.org.combobox("#useSfsUnit", "useSfsUnit",'181', {
			width:'68%',
			allowBlank : false,
			level : 10,
			emptyText :'',
			defValue :'', 
			//showItself : true,
			attr:{searchType: "eq",required:"required"},
			notShowType:"1,2,4,5,6,7,8,9",
			//fieldClass:"search-form-field"
		});
		var date=new Date().format('yyyy-MM-dd');//设置默认购买日期
		$("#buyDate").val(date);
	});
</script>
</head>

<body>
		<form id="saveForm"
			action="${ctx }/data/sbgl/ydzf/addydzd.action" method="post">
			<table class="comm-table">
				<tbody>
					<tr>
						<th width='200px' class="red w180" >设备编号：</th>
						<td width='225px'><input type="text" id="deviceNumber" name="deviceNumber" required="required" validater="code" maxlength="32" style="width:60%"/></td>
						<th width='195px'>购买单位：</th>
						<td width='225px' id="buyUnit"></td>
					</tr>
					<tr>
						<th >使用单位（司法局）：</th>
						<td id="useUnit">
						</td>
						<th >使用单位（司法所）：</th>
						<td id="useSfsUnit">
						</td>
					</tr>
					<tr>
					<th >SIM卡号：</th>
					<td id="phoneNumber"></td>
					<th >设备状态：</th>
						<td id="status"></td>
					</tr>
					<tr>
						<th>购买日期：</th>
						<td><input id='buyDate' name="buyDate" class="Wdate" type="text"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16" style="width:60%"/></td>
					</tr>
				</tbody>
			</table>
		</form>
</body>
</html>
