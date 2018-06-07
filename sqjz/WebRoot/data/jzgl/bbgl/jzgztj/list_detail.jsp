<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区矫正工作统计表</title>
<%@ include file="/common/commonjs.jsp"%>
	<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"sqjz",second:"bbgl",third : "workReport"});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/jzgl/bbgl/workstat/list.action?opt=view&id=<%=request.getParameter("id")%>',
					colNames	: [
					    '','年份', '月份','机构','状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'id',
						        index	: 'id',
						        hidden : true
					        },{
						        name	: 'year',
						        index	: 'year',
						        width	: 40,
						        align	: 'center'
					        }, {
						        name	: 'month',
						        index	: 'month',
						        width	: 30,
						        align	: 'center'
					        }, {
						        name	: 'orgid',
						        index	: 'orgid',
						        width	: 30,
						        align	: 'center',
						        formatter:function(value, opts, rwdat){
									return $.organization.formatter()(rwdat.orgid);
								}
					        }, {
						        name	: 'status',
						        index	: 'status',
						        width	: 30,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
						        	return rwdat.status=='1'?'新建':rwdat.status=='2'?'未上报':rwdat.status=='3'?'待审核':rwdat.status=='4'?'被退回':rwdat.status=='5'?'审核成功':'--';
						        }
					        },{
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 250,
						        align	: 'center',
						        fixed : true,
						        sortable : false,
						        formatter : function(value, opts, rwdat) {
						        	var str = '';
						        	var status = '<%=request.getParameter("status")%>';
						        	//if(rwdat.status!='4'){
									str= "<a href='javascript:view(\""+rwdat.id+"\",\""+rwdat.orgid+"\")' title='' class='a-style'>查看详细</a>";
									if(rwdat.status!='5'&&rwdat.status!='4'){
										str +="<a href='javascript:sendBack(\""+rwdat.id+"\")' title='' class='a-style'>退回</a>";
										str +="<a href='javascript:sendSuccess(\""+rwdat.id+"\")' title='' class='a-style'>审核通过</a>";
									}
									//}
									if(rwdat.orgid==${user.wunit.bm}){
										str= "<a href='javascript:viewCollect(\""+rwdat.id+"\",\""+rwdat.orgid+"\",\""+rwdat.year+"\",\""+rwdat.month+"\",\""+${user.wunit.orgType}+"\",\""+rwdat.status+"\")' title='' class='a-style'>查看汇总</a>";
									}
									return str;			 
						        }
					        }],
					  pager: '#mainGridPager',
					  multiselect : false
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});	
		
		function view(id,orgId,year,month){
			$.dialog({ 
			    id: 'testID',
			    width: '1800px', 
				height: '700px', 
				title:'社区矫正工作统计表查看',
			    content: 'url:<%=reportUrl%>data/jzgl/bbgl/jzgztj/tb_Report.jsp?status=3&raq=sfs_jzgztjb&id='+id+'&orgId='+orgId,
				cancelVal: '关闭', 
				cancel: function(){
					$("#mainGrid").trigger("reloadGrid");
				} 
			});
		}
		function sendBack(id){
			$.container.popup({				
				items:[{
					save: "${ctx}/data/jzgl/bbgl/workstat/sendBack.action",
					title: '退回原因填写',
					fieldCols: 1,
					fieldCls:{labelCls : 'form-td-label-90',},
					fields:[
							{text: 'id', type:'hidden', name: 'id',defValue:id},
					        {text: '退回原因', type:'textarea', name: 'reason',maxlength:300},
					],
					yes: function(){
						$("#mainGrid").trigger("reloadGrid");			
					},
				}]
			}, {
				okVal: '保存',
				width: '300px',
				height: '50px',
			});
		}
		function sendSuccess(id){
			$.ajax({
				   type: "POST",
				   async:true,
				   url: "${ctx}/data/jzgl/bbgl/workstat/sendSuccess.action",
				   data:"id="+id,
				   success:function(data){
					   $("#mainGrid").trigger("reloadGrid");
				   }
			});
		}
		function viewCollect(id,orgId,year,month,orgType,status){
			var raq = '';
			if(orgType=='1')
				raq = 'sfj_jzgztjb';
			if(orgType=='2')
				raq = 'sfj_jzgztjb';
			if(orgType=='3')
				raq = 'sfs_jzgztjb';
			$.dialog({ 
			    id: 'testID',
			    width: '1800px', 
				height: '700px', 
				title:'社区矫正工作统计表查看',
			    content: 'url:<%=reportUrl%>data/jzgl/bbgl/jzgztj/tb_Report.jsp?raq='+raq+'&id='+id+'&orgId='+orgId+'&year='+year+'&month='+month+'&status='+status,
				cancelVal: '关闭', 
				cancel: function(){
					$("#mainGrid").trigger("reloadGrid");
				} 
			});
		}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：社区矫正 -> 报表管理->社区矫正工作统计表->详细</span> </div>
  <div class="container-top">
  <!--列表-->
  <div class="buttonArea operation">
  <span>
	<c:if test="${user.wunit.orgType == '3'}">
   <input type="button" id="btnAdd" class="bttn bicon-add"  value="新建" onclick="javascript:openCreate();"/>
  </c:if>
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
