<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员档案管理</title>
<%@ include file="/common/commonjs.jsp"%>
<%
	String opt = request.getParameter("opt");
	String y = request.getParameter("y");
	/* if(opt==null||"".equals(opt)&&){
		opt="jgsbsfj";
	} */
	request.setAttribute("opt",opt);
	request.setAttribute("orgType",orgType);
%>
<script language="javascript" src="tjreport.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<script language="javascript" src="${ctx}/js/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript">
	var opt;
	var useUnit="";
	var useSfsUnit="";
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"sbgl",third : "tjinfo"});
		opt = '<%=opt%>';
		if(opt=='null'&&ORG_TYPE=='sj'){
			opt="jgsbsfj";
		}
		if(opt=='null'&&ORG_TYPE=='qxsfj'){
			opt="jgsbsfs";
		}
		$("#tab-"+opt).addClass("tabpage-label-selected");
		$.report.init(opt);
		 //设置默认日期
		var date2=new Date().format('yyyy-MM-dd ');
		$("#bjsj2").val(date2);
		var date1=new Date('1980/01/01').format('yyyy-MM-dd ');
		$("#bjsj1").val(date1);
		//区县司法局用户司法所下拉列表
		if(ORG_TYPE=="qxsfj"&&opt=='jgsbfxry'){
			$.organization.combobox("#useSfsUnit", "useSfsUnit", ${user.wunit.bm}, {
				width:'68%',
				allowBlank : true,
				level : 10,
				showItself : true,
				emptyText : '全部',
				defValue : '',
				attr:{searchType: "eq"},
				notShowType:"1,2,4,5,6,7,8,9",
				fieldClass:"search-form-field",
				valuechange:function(value,eleme){
					useSfsUnit=value;
				}
			});
		};
		//生成司法局下拉列表 
		if(ORG_TYPE=='sj'){
			$.organization.combobox("#useUnit", "useUnit", ${user.wunit.bm}, {
				width:'68%',
				allowBlank : true,
				level : 10,
				showItself : true,
				emptyText : '全部',
				defValue : '',
				attr:{searchType: "eq"},
				notShowType:"1,3,4,5,6,7,8,9",
				fieldClass:"search-form-field",
				valuechange:function(value,elem){
					useUnit=value;
					$("#useSfsUnit").empty();
					$.organization.combobox("#useSfsUnit", "useSfsUnit", value, {
						width:'68%',
						allowBlank : true,
						level : 10,
						showItself : true,
						emptyText : '全部',
						defValue : '',
						attr:{searchType: "eq"},
						notShowType:"1,2,4,5,6,7,8,9",
						fieldClass:"search-form-field",
						valuechange:function(value,elem){
							useSfsUnit=value;
						}
					});
					unitSelect(value);
				}
			});
		};
	});
	function findChildren(useUnit){
		var children=$.organization.getData(useUnit,{
			allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"4,5,6,7,8,9",
			fieldClass:"search-form-field"
		})[0].children;
		return children;
	};
	function unitSelect(useUnit){
		if(opt=="jgsbzt"){
			$.report.orgFusionCharts("监管设备状态统计","jgsbzt.action?useUnit="+useUnit,$.dictionary.formatter("DZJGSBZT", '不详'),"jg"); 
		}
		if(opt=="zfsbzt"){
			$.report.orgFusionCharts("执法终端状态统计","zfsbzt.action?useUnit="+useUnit,$.dictionary.formatter("ZFSBZT", '不详'),"zf"); 
		}
		if(opt=="simsbzt"){
			$.report.orgFusionCharts("SIM卡状态统计","simsbzt.action?useUnit="+useUnit,$.dictionary.formatter("SIMSBZT", '不详'),"sim"); 
		}
		if(opt=="jgsbsfj"){
			var children;
			if(useUnit==""){
				children=findChildren(ORG_ID);
			}else{
				children=findChildren(useUnit);
			};
			$.report.unittj("监管设备各单位统计","jgsbsfs.action?useUnit="+useUnit,"jg",children);
		}
		if(opt=="zfsbsfj"){
			var children;
			if(useUnit==""){
				children=findChildren(ORG_ID);
			}else{
				children=findChildren(useUnit);
			};
			$.report.unittj("执法终端各单位统计","zfsbsfs.action?useUnit="+useUnit,"zf",children);
		}
		if(opt=="simsbsfj"){
			var children;
			if(useUnit==""){
				children=findChildren(ORG_ID);
			}else{
				children=findChildren(useUnit);
			};
			$.report.unittj("SIM卡各单位统计","simsbsfs.action?useUnit="+useUnit,"sim",children);
		}
		if(opt=="simsbty"){
			$.report.simtytj("SIM卡类型统计","simsbty.action?useUnit="+useUnit,$.dictionary.formatter("SIMTYPE", '不详'));
		}
	};
	function dzjgfxrySearch(){
		var startTime=$("#bjsj1").val();
		var overTime=$("#bjsj2").val();
		$.report.jgsbfxrytj("监管设备佩戴人统计","jgsbfxrytj.action?useUnit="+useUnit+"&startTime="+startTime+"&overTime="+overTime+"&useSfsUnit="+useSfsUnit);
	}
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管 -> 设备管理 ->统计信息</span>
		</div>
		<div class="container-top">
			<div>
				<ul class="tabpage-label-container">
					<c:if test="${orgType=='sj'}">
					<a href="tjreport.jsp?opt=jgsbsfj"><li id="tab-jgsbsfj" class="tabpage-label">监管设备统计</li></a>
					</c:if>
					<c:if test="${orgType=='qxsfj'}">
					<a href="tjreport.jsp?opt=jgsbsfs"><li id="tab-jgsbsfs" class="tabpage-label">监管设备统计</li></a>
					</c:if>
					<a href="tjreport.jsp?opt=jgsbzt"><li id="tab-jgsbzt" class="tabpage-label">监管设备状态统计</li></a>
					<a href="tjreport.jsp?opt=jgsbfxry"><li id="tab-jgsbfxry" class="tabpage-label">佩戴人统计    </li></a>
					<li class="tabpage-label-group"></li>
					<c:if test="${orgType=='sj'}">
					<a href="tjreport.jsp?opt=zfsbsfj"><li id="tab-zfsbsfj" class="tabpage-label">执法终端统计</li></a>
					</c:if>
					<c:if test="${orgType=='qxsfj'}">
					<a href="tjreport.jsp?opt=zfsbsfs"><li id="tab-zfsbsfs" class="tabpage-label">执法终端统计</li></a>
					</c:if>
					<a href="tjreport.jsp?opt=zfsbzt"><li id="tab-zfsbzt" class="tabpage-label">执法终端状态统计</li></a>
					<li class="tabpage-label-group"></li>
					<c:if test="${orgType=='sj'}">
					<a href="tjreport.jsp?opt=simsbsfj"><li id="tab-simsbsfj" class="tabpage-label">SIM卡统计</li></a>
					</c:if>
					<c:if test="${orgType=='qxsfj'}">
					<a href="tjreport.jsp?opt=simsbsfs"><li id="tab-simsbsfs" class="tabpage-label">SIM卡统计</li></a>
					</c:if>
					<a href="tjreport.jsp?opt=simsbzt"><li id="tab-simsbzt" class="tabpage-label">SIM卡状态统计</li></a>
					<a href="tjreport.jsp?opt=simsbty"><li id="tab-simsbty" class="tabpage-label">SIM卡类型统计</li></a>
				</ul>
				<div style="clear:both;"></div>
			</div>
			<div class="container-top"  >
			
			<table class="search-table" id="searchDiv"  >
				<tbody>
				<tr style="float:left;padding-left:5px">
				<c:if test="${orgType=='sj'}">
						<th >司法局使用单位：</th >
						<td style="text-align:left;" id="useUnit">
						</td>
					</c:if>
					<c:if test="${opt=='jgsbfxry'}">
						<th  style="margin-left:0px;padding-left:5px">司法所使用单位：</th>
						<td id="useSfsUnit">
						</td>
						<th>&nbsp;查询日期：</th>
						<td >
						<input id="bjsj1" class="Wdate" type="text" style="width: 85px"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" maxlength="16"
							searchType="ge" />至<input class="Wdate" type="text" style="width: 85px" id="bjsj2"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" maxlength="16"
							 searchType="le" />
						</td>
						<td >&nbsp;<input id="searchBtn" type="button"
							 class="bttn bicon-search" onclick="dzjgfxrySearch()" value="查询" />
						</td>
				</c:if>
				</tr>
				</tbody>
			</table>
			
		</div>
			
			
		</div>
		
		<div style=" height:31px; width:100%;"><div id="totalNum" style="float:left; height:20px; font-size:14px;
					color:#46474C; line-height:20px; margin-top:8px; width:80%; min-width:300px;">合计数量：0</div></div>
		<div class="container-bottom">
			<div id="buttons" class="buttonArea operation"></div>
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
		 <div class="chart"></div>
		<div id="chart1"></div>
		<div id="chart2"></div> 
	</div>
	
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>

