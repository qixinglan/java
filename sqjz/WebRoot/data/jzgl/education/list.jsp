<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集中教育</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"sqjz",second:"jzjy"});
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/jzgl/education/getData.action',
							colModel : [
									{
										name : 'id',
										index : 'id',
										label : 'id',
										hidedlg : false,
										hidden : true
									},
									{
										name : 'month',
										index : 'month',
										label : '月份',
										sortable : true,
										searchType : 'eq',
										width : 150,
										align : 'center'
									},
									{
										name : 'stage',
										index : 'stage',
										searchType : 'eq',
										label : '期',
										sortable : true,
										width : 100,
										align : 'center'
									},
									{
										name : 'persons',
										index : 'persons',
										searchType : 'eq',
										label : '人数',
										sortable : true,
										width : 130,
										align : 'center'
									},
									{
										name : 'id',
										index : 'id',
										label : '操作',
										width : 120,
										align : 'center',
										fixed : true,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:view(\""+ rwdat.id+ "\");' class='a-style'>查看</a>"+
											"<a href='javascript:openCreate(\"edit\",\""+rwdat.id+"\",\""+rwdat.month+"\")' title='' class='a-style'>编辑</a>"+
											"<a href='javascript:del(\""+rwdat.id+"\")' title='' class='a-style'>删除</a>";
											return actions;
										}
									} ],
							pager : '#mainGridPager',
							sortname : 'month',
							sortorder : "desc",
							multiselect : false
						});
		$("#mainGrid").addSearchForm("#search",{rows:"2"});
	});
	//新建、编辑
	function openCreate(oper,id,month){
		var read = '';
		if(oper=="edit"){
			read = '${ctx}/data/jzgl/education/view.action?id='+id;
		}
		var fields=[{text:"创建日期",type:'hidden',name:'createdate'},
					{text:"拟制人",type:'hidden',name:'creater'},
					{text:"所属机构",type:'hidden',name:'orgId'},
					{text:"id",type:'hidden',name:'id',},
					{text:"月份",type:'datepicker',name:'date',dateFmt:'yyyy-MM',validater:'yearmonth',defValue:oper=="edit"?month:'',required: true},
					{text:"期",type:'text',name:'stage',maxlength:15,validater:'int',required: true},
					{text:"人数",type:'text',name:'persons',maxlength:10,validater:'int',required: true}];
		$.container.popup({
			title: '集中教育',
			read: read,
			save: function(){
				var date = $("input[name='date']").val();
				var url = '${ctx }/data/jzgl/education/add.action?date='+ date;
				var formId =  $("form").attr("id");
				return $.fields.checkForm("#"+formId, {
					yes : function(data) {
						$("#mainGrid").trigger("reloadGrid");
					},
					mask : false,
					title : '保存集中教育',
					url : url
				});
			},
			fieldCols: 1,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:fields,
			yes : function(data){}
		},{width: "450px",height: "250px",okVal:'保存'});
	}
	//查看
	function view(id){
		var fields=[{text:"月份",type:'display',name:'month',maxlength:50,required: true},
					{text:"期",type:'display',name:'stage',maxlength:50,dateFmt:'yyyy-MM',required: true},
					{text:"人数",type:'display',name:'persons',maxlength:50,required: true}];
		$.container.popup({
			title: '集中教育',
			read:'${ctx}/data/jzgl/education/view.action?id='+id,
			fieldCols: 1,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fields:fields,
			yes : function(){}
		},{cancelVal : '关闭',width: "450px",height: "250px",});
	}
	//删除
	function del(id){
		$.dialog.confirm("确定删除吗？",function(){
			$.ajax({
				   type: "POST",
				   async:false,
				   url: "${ctx }/data/jzgl/education/delete.action",
				   data: "id="+id,
				   success : function(data){
					   $("#mainGrid").trigger("reloadGrid");
				   }
				 });
		});
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：社区矫正 -> 集中教育</span>
		</div>
		<div class="container-top">
		</div>
		<div class="container-middle">
			<div id="search"></div>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<span>
				<input type="button" id="btnAdd" class="bttn bicon-add"  value="新建" onclick="javascript:openCreate();"/>
			</span>
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	 <%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
