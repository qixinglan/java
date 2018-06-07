<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区服刑人员档案管理</title>
<%@ include file="/common/commonjs.jsp"%>
<link rel="stylesheet" href="${ctx}/data/jzgl/dagl/style.css" />
<script language="javascript" src="dacx.js"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css" />
<link rel="stylesheet" href="${ctx}/data/dzjg/zhcx/popup.css" />
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"cxtj",third : "dacx"});
		$("#mainGrid").jqGrid({
			url: CONTEXT_PATH + "/data/jzgl/cxtj/dacx/list.action",
			colModel: [{
						name:'id',
						index:'id',
						label:'id',
						hidedlg:false,
						hidden:true
					},{
						name: 'responseOrg',
						index	: 'responseOrg',
						label	: '矫正机构',
	        			formatter: 'organization',
	        			width	: 150,
	        			align	: 'center',
	        			searchType : 'cn',
	        			searchOption: {
	        				useCOrg: true, 
        					notShowType: '1,4,5,6,7,8,9', 
        					level:2,
        					showRoot:false, 
        					allowSearch: true
        				},
        				fixed :true,
	        			sortable: true
	        		},{
			        	name: 'name',
			        	index: 'name',
			        	label: '姓名',
			        	formatter : function(value, opts, rwdat) {
							return "<a href='javascript:$.fxryfullinfo.viewDetail(\""+rwdat.id+"\");' title='' class='a-style'>"+value+"</a>";
						},
				        width	: 130,
				        align	: 'center',
				        searchType : 'cn',
				        sortable: true
			        }, {
				        name	: 'sex',
				        index	: 'sex',
				        label	: '性别',
				        formatter : 'dictionary',
				        formatoptions: {code:'XB'},
			        	width	: 35,
			        	align	: 'center',
			        	searchType : 'eq',
			        	fixed: true,
			        	sortable: true
			        },{
				        name	: 'birthdate',
				        index	: 'birthdate',
				        label	: '出生日期',
				        width	: 70,
				        align	: 'center',
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'dateAdjust',
				        index	: 'dateAdjust',
				        label	: '矫正起止日期',
				        width	: 140,
				        align	: 'center',
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'politicsStatusOriginal',
				        index	: 'politicsStatusOriginal',
				        label	: '原政治面貌',
				        formatter : 'dictionary',
				        formatoptions: {code:'ZZMM',allowSearch: true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true
			        },{
				        name	: 'educationDegree',
				        index	: 'educationDegree',
				        label	: '文化程度',
				        formatter : 'dictionary',
				        formatoptions: {code:'WHCD',allowSearch: true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true
			        },{
				        name	: 'workState',
				        index	: 'workState',
				        label	: '就业就学情况',
				        formatter : 'dictionary',
				        formatoptions: {code:'JYJX'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true
			        },{
				        name	: 'adjustType',
				        index	: 'adjustType',
				        label	: '矫正类型',
				        formatter : 'dictionary',
				        formatoptions: {code:'JZLB'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true
			        },{
				        name	: 'hasoutmanage',
				        index	: 'hasoutmanage',
				        label	: '是否曾脱管',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'removeReason',
				        index	: 'removeReason',
				        label	: '解除终止矫正原因',
				        formatter : 'dictionary',
				        formatoptions: {code:'JJYY'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'iscontagion',
				        index	: 'iscontagion',
				        label	: '是否有传染病史',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 130,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden: true
			        }, {
				        name	: 'psychosis',
				        index	: 'psychosis',
				        label	: '是否有精神病',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        hidden  : true
			        }, {
				        name	: 'slfzType',
				        index	: 'slfzType',
				        label	: '是否“三类犯罪”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SLFZ',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'issanwu',
				        index	: 'issanwu',
				        label	: '是否三无人员',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'sisType',
				        index	: 'sisType',
				        label	: '是否“四史”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SS',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'sansType',
				        index	: 'sansType',
				        label	: '是否“三涉”',
				        formatter : 'dictionary',
				        formatoptions: {code:'SANS',multiSelected:true},
				        width	: 140,
				        align	: 'center',
				        searchType : 'mdircn',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }, {
				        name	: 'isRecidivism',
				        index	: 'isRecidivism',
				        label	: '是否累犯',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'isforbidden',
				        index	: 'isforbidden',
				        label	: '是否被宣告禁令',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 140,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        },{
				        name	: 'isdeviceCode',
				        index	: 'isdeviceCode',
				        label	: '是否电子监管',
				        formatter : 'dictionary',
				        formatoptions: {code:'SF'},
				        width	: 90,
				        align	: 'center',
				        searchType : 'eq',
				        sortable: true,
				        fixed :true,
				        hidden  : true
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'responseOrg,code',
			sortorder: "desc,desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").addSearchForm("#search");
	});
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：矫正管理 -> 查询统计 ->社区服刑人员档案查询</span>
		</div>
		<div class="container-top">
			<%@include file="tab.jsp"%>
			<script type="text/javascript">
			$("#tab-fxry").addClass("tabpage-label-selected");
			</script>
		</div>
		<div class="tabpage">
			<div class="container-middle">
				<div id="search"></div>
			</div>
			<div class="container-bottom">
				<div id="buttons" class="buttonArea operation"></div>
				<table id="mainGrid">
				</table>
				<div id="mainGridPager"></div>
			</div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
