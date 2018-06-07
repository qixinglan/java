<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>社区服刑人员档案管理</title>
	<%@ include file="/common/commonjs.jsp"%>
	<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
	<style type="text/css">
		.container-top {
			margin-bottom: 0px;
			width: 100%;
			height: auto;
			margin-top:10px;
		}
		.dwjk-box7{ width:100%; margin:0; position: relative; border:1px solid #E3E3E3; margin-bottom:15px; min-width:972px; min-height:70px; border-left:0px; border-right:0px;}
		.dwjk-box7-item{align:left; vertical-align:top; padding:0; position:relative; margin:0; top:0px; left:0px; width:100%;}
		.dwjk-box8{ width:100%; background:#FAFAFA; border:1px solid #D3D4D4; top:0px; left:0px;}
		.dwjk-box8 table{ width:100%; height:90px;}
		.dwjk-box8 th,.dwjk-box8 td{ color:#616161; vertical-align: middle;}
		.dwjk-box8 td input[type=text]{ border:1px solid #bdbdbd; background:#fff; margin-top:10px; height:18px;}
		.dwjk-box8 th{text-align:left; padding-left:20px; min-width:80px; width: 100px;}
		.dwjk-box8 th input.btnnsearch{ background:url(../../images/btn5.jpg) no-repeat; width:80px; height:28px; margin:0 70px; border:0px;}
		.main{
			height:auto; 
			padding:0 15px 15px 15px;
			z-index:0; 
		}
		.main-h3{
		/*	border-bottom:1px solid #cdcdcd;
			border-top:1px solid #ffffff;*/
			border:1px solid #83A6CB;
			color:#3c3c3c;
			display:block;
			font-size:13px;
			height:26px;
			line-height:26px;
			text-indent: 0.5em;
			*position:relative;
			background-color: #aed9f2;
		    background: -webkit-linear-gradient(top,#ddeef8 0,#b8d8eb 100%);
		    background: linear-gradient(to bottom,#c6e5f7 0,#b8d8eb 100%);
		    background-repeat: repeat-x;
		    -webkit-box-shadow:inset 1px 1px 0px #ffffff; 
		    box-shadow:inset 1px 1px 0px #ffffff; 
		    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#c6e5f7,endColorstr=#b8d8eb,GradientType=0);
		}
		.main-h3 span {
			background-image: url('../../images/tico.png');
			background-repeat: no-repeat;
			display: inline-block;
			border-right: 1px solid #83A6CB;
			float: left;
			height: 16px;
			margin-right: 3px;
			padding: 5px;
			width: 16px;
		}
		.main-h3 a {
			/*background-image: url('../image/icons/tico.png');
			background-repeat: no-repeat;*/
			display: inline-block;
			float: right;
			font-weight:normal;
			font-size:12px;
			height: 16px;
			line-height: 16px;
			margin-right: 3px;
			margin-top:4px;
			padding:0 3px;
			*position:absolute;
			*right:3px;
			*top:3px
		}
		.list-t{
			border-left:1px solid #83A6CB;
			border-right:1px solid #83A6CB;
			border-bottom:1px solid #83A6CB;
			overflow-x:hidden;
			/*overflow-y:auto;*/
			position:relative;
			width:100%;
		}
		.list-tbzoom{
			overflow-y:auto;
			overflow-x:auto;
		}
		.pictd{
			background-color:#f2f2f2;
			border:1px solid #92c1e9;
			color:#173058;
			cursor:pointer;
			padding:5px;
			margin:2px;
			border-radius: 3px;
			background: linear-gradient(top,#ffffff 0,#f2f2f2 100%);
			background: -webkit-linear-gradient(top,#ffffff 0,#f2f2f2 100%);
			background: -moz-linear-gradient(top,#ffffff 0,#f2f2f2 100%);
			filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,endColorstr=#f2f2f2,GradientType=0);
			box-shadow:0 1px 2px rgba(0,0,0,.3);
			-webkit-box-shadow:0 1px 2px rgba(0,0,0,.3);
			-moz-box-shadow:0 1px 2px rgba(0,0,0,.3);
			filter: progid:DXImageTransform.Microsoft.Shadow(Direction=0,Strength=2,Color=#d9d9d9);
			font-size: 13px;
		}
		.pic_hover{
			background-color:#a1dbff;
			border:1px solid #7db5de;
			color:#173058;
			background: linear-gradient(top,#f0f9ff 0,#a1dbff 100%);
			background: -webkit-linear-gradient(top,#f0f9ff 0,#a1dbff 100%);
			background: -moz-linear-gradient(top,#f0f9ff 0,#a1dbff 100%);
			filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#f0f9ff,endColorstr=#a1dbff,GradientType=0);
			cursor:pointer;
		}
		.dwjk-box9-jg{ color:#5B5858; font-size:12px; display:inline; height:20px; line-height:20px; margin-top:5px; width:200px; text-align:right;}
		#page span,#page label{
			font-size: 12px;
			margin-top: 15px;
			border: none;
			background: none;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"dzjg",second:"dwjk"});
			//执行机关
			$.organization.combobox("#orgName", "orgId", ${user.wunit.bm}, {
				allowBlank : true,
				level : 10,
				showRoot : true,
				showItself : true,
				notShowType:"4,5,6,7,8,9",
				emptyText : '全部',
				dropdownAutoWidth:true,
				defValue:<%=request.getParameter("orgId")%>,
				fieldClass:"searchStyle",
				width:"184px"
			});
			//矫正类别
			$.dictionary.combobox("#adjustType", "adjustType", "JZLB",{attr:{searchType:"eq"},fieldClass:"searchStyle",width:"184px"});
			//是否绑定设备
			$.dictionary.combobox("#hasDevice", "hasDevice", "SF",{attr:{searchType:"eq"},fieldClass:"searchStyle",defValue:"2",width:"184px"});
			//初始化地图标点
			searchData(1);
		});
		
		function searchData(pageNo){
			var personName = $($("input[id='personName']")).val();
			var alarmSta = "";
			$.each($("input[type='radio']"),function(i,n){
				if(n.checked){
					alarmSta=n.value;
					return false;
				}
			});
			var corrType = $($("input[name='adjustType']")).val();
			var orgId = $($("input[name='orgId']")).val();
			var hasDevice = $($("input[name='hasDevice']")).val();
			$.ajax({
			   type: "POST",
			   async:true,
			   url: "${ctx }/data/dwjk/searchFxryList.action",
			   dataType:"json",
			   data:"pageNo="+pageNo+"&personName="+personName+"&orgId="+orgId+"&corrType="+corrType+"&alarmSta="+alarmSta+"&hasDevice="+hasDevice,
			   success:function(data){
				   var datas = data.result;
				   $(".dwjk-box9-jg").remove();
				   $(".dwjk-box9-item").remove();
				   $("#page").remove();
				   var str='<div class="list-tbzoom"><table id="pictable" width="100%" border="0">';
				   $.each(datas, function(i, n)
				   {
				   		var imghref = '${ctx}/images/head.jpg';
				   		if(getValue(datas[i].picture)!=" "){
				   			imghref='${ctx }/data/photos/view.action?id='+datas[i].id+'&upFID='+datas[i].id;
				   		}
				   		if(i%4==0){
				   			str+='<tr>';
				   		}
				   		str+='<td width="25%" style="">';
		        		str+='	<div class="pictd" pkid="'+datas[i].id+'">';
			        	str+='		<table border="0">';
			        	str+='			<tr height="30px">';
			        	str+='				<td align="center" rowspan="4" style="padding-right:10px;">';
			        	str+='					<img width="100px"  name="photo" height="100px" src="'+imghref+'" onclick="toGisDetail(\''+datas[i].id+'\');"/>';
			        	str+='				</td>';
			        	str+='				<td width="60%">';
			        	str+='					<b>姓名</b>:'+getValue(datas[i].name);
			        	str+='				</td>';
			        	str+='			</tr>';
			        	str+='			<tr height="30px">';
			        	str+='				<td style="white-space: nowrap;">';
			        	str+='					<b>身份证号</b>:'+getValue(datas[i].idcard);
			        	str+='				</td>';
			        	str+='			</tr>';
			        	str+='			<tr height="30px">';
			        	str+='				<td style="white-space: nowrap;">';
			        	str+='					<b>矫正类别</b>:'+getValue($.dictionary.formatter('JZLB')(datas[i].adjustType));
			        	str+='				</td>';
			        	str+='			</tr>';
			        	str+='			<tr height="30px">';
			        	str+='				<td style="white-space: nowrap;">';
			        	str+='					<b>联系电话</b>:'+getValue(datas[i].phoneNumber);
			        	str+='				</td>';
			        	str+='			</tr>';
			        	str+='		</table>';
		        		str+='	</div>';
		        		str+='</td>';
				   		if(i%4==3){
				   			str+='</tr>';
				   		}
				   });
				   var remain=4-datas.length%4;
				   if(remain>0){
					   for(;remain>0;remain--){
						   str+='<td width="25%" style="">';
						   str+='</td>';
					   }
					   str+='</tr>';
				   }
				   str+='</table></div>';
				   $(".list-t").html(str);	
				   str="<div id='page' style='height:25px; border:1px solid; margin:0 auto; clear:both;'>";
				   str+="<span style='width:400px; text-align:right;'>当前第<label style='color:red;'>"+data.pageNo+"</label>页</span>";
				   str+="<span style='width:90px; text-align:right;' onclick='searchData(1)'>首页</span>";
                   str+="<span style='width:60px; text-align:right;' onclick='searchData("+data.prePage+")'>上一页</span>";
                   str+="<span style='width:60px; text-align:right;' onclick='searchData("+data.nextPage+")'>下一页</span>";
                   str+="<span style='width:90px; text-align:right;' onclick='searchData("+data.totalPages+")'>尾页</span>";
                   str+="<span style='width:600px; text-align:left;'>共有<label style='color:red;'>"+data.totalPages+"</label>页</div></span>"
                   $(".page").after(str);
			   }
			});
			INIT_FLAG = false;
			SEARCH_FLAG = true;
		}
		function toGisDetail(id){
			$.dialog({
				title : '在矫人员定位信息',
				id: 'locate',
				fixed : true,
				lock : true,
				width : 1024,
				height: 768,
				content : 'url:'+CONTEXT_PATH+'/data/dzjg/dwjk/history.jsp?fxryid=' + id,
				resize : false,
				cancel : true,
				button : [],
				cancelVal : "返回"
			}).max();
			//window.location = CONTEXT_PATH+'/data/dzjg/dwjk/dwjk-history.jsp?fxryid='+id+'&orgId=<%=request.getParameter("orgId")%>';
		}
		function getValue(value){
			return value==null?" ":value=="null"?" ":value;
		}
	</script>
</head>
 
<body>
	<%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
	<div class="main">
	  <div class="container-top">
	  	<%@include file="tab.jsp" %>
	  	<script type="text/javascript">
			$("#tab-menu").addClass("tabpage-label-selected");
		</script>
	  </div>
	  <table style="clear:both;top:0px;width:100%;heigth:50px;position:relative;">
      	<tr style="clear:both">
        	<td class="dwjk-box7-item" style="clear: both; width: 100%; display: table-cell;">
        		<div class="dwjk-box8" style="width:100%">
            		<table border="0" cellpadding="0" cellspacing="0">		 
                    	<tr style="height:50px;">
                    		<th class="orgName">执行机关:</th>
                        	<td id="orgName" style="vertical-align: center"></td>
                    		<th>矫正类别:</th>
                        	<td id="adjustType" name="adjustType" searchType="eq"></td>
                    		<th>是否解绑设备:</th>
                    		<td id="hasDevice" name="hasDevice" searchType="eq"></td>
                   		</tr>
                    	<tr style="height:40px;">
                    		<th>姓名:</th>
                        	<td><input type="text" id="personName" name="personName" maxlength="20"  style="width:170px;"/></td>
                    		<th>报警状态:</th>
                        	<td>
                        		<input type="radio" name="alarmSta" value="" id="rad1" checked="checked"/>全部
                        		<input type="radio" name="alarmSta" value="0" id="rad2"/>正常
                            	<input type="radio" name="alarmSta" value="1" id="rad3"/>报警
                        	</td>
                    		<th colspan="2" style="padding-left:154px;"><input type="button" class="btnnsearch" onclick="javascript:searchData(1);"/></th>
                    	</tr>
                	</table>
         		</div> 
         		<h3 class="main-h3"><span class="list"></span>在矫人员列表</h3>
			    <div class="list-t" >
			    </div>
         		<div class="dwjk-box9" style="width:100%">
                	<div class="dwjk-box10" style="width:100%">
                		<span class="page" style="display:none"></span>
                		<div id="page">
	                		<label onclick="javascript:void(0)" style="width:60px">首页</label>
	                		<label onclick="javascript:void(0)" style="width:60px">上一页</label>
	                    	<label onclick="javascript:void(0)" style="width:60px">下一页</label>
	                    	<label onclick="javascript:void(0)" style="width:60px">尾页</label>
                   		</div>
                   </div>
              	   <div style="height: 10px"></div>
               </div>     
     		</td>
     	 </tr>
	  </table> 
    </div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp" %>
</body>
</html>
