<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区矫正工作统计表</title>
<%@ include file="/common/commonjs.jsp"%>
	<script type="text/javascript">
	function jqGridMergerCell(gridName,CellName){
		var mya=$("#"+gridName).getDataIDs();
		var length=mya.length;
		for(var i=0;i<=length;i++){
			var before=$("#"+gridName).jqGrid("getRowData",mya[i]);
			var rowSpanTaxCount=1;
			for(var j=i+1;j<=length;j++){
				var end=$("#"+gridName).jqGrid("getRowData",mya[j]);
				if(before[CellName]==end[CellName]){
					rowSpanTaxCount++;
					$("#"+gridName).setCell(mya[j],CellName,"",{display:"none"});
				}else{
					rowSpanTaxCount=1;
					break;
				}
				$("#"+CellName+mya[i]).attr("rowspan",rowSpanTaxCount);
			}
		}
	}
		$(function() {
			$.menu.init("#menuDetail",{first:"sqjz",second:"bbgl",third : "workReport"});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/jzgl/bbgl/workstat/list.action',
					colNames	: [
					    '','年份','季度', '月份','状态','退回原因','操作'
					],
					
					colModel	: [
					        {
						        name	: 'id',
						        index	: 'id',
						        hidden : true
					        },{
						        name	: 'year',
						        index	: 'year',
						        width	: 150,
						        align	: 'center',
						        sortable : false,
						        cellattr:function(rowId,tv,rawObject,cm,radta){
						        	  return "id=\'year"+rowId+"\'";
						        }
					        },  {
						        name	: 'quarter',
						        index	: 'quarter',
						        width	: 100,
						        align	: 'center',
						        formatter:function(value, opts, rwdat){
						        	var quarter = rwdat.month<4&&rwdat.month>0?1:rwdat.month<7&&rwdat.month>3?'2':rwdat.month<10&&rwdat.month>6?'3':'4';
						        	var str = "<a onclick='javascript:viewQuarter(\""+rwdat.orgid+"\",\""+rwdat.year+"\",\""+quarter+"\",\"3\")' title='' class='a-style'>";
						        		str += quarter;
						        		str+="</a>";
						        	return str;
						        },
						        cellattr:function(rowId,tv,rawObject,cm,radta){
						        	  return "id=\'quarter"+rowId+"\'";
						        }
					        },{
						        name	: 'month',
						        index	: 'month',
						        width	: 100,
						        align	: 'center',
						        sortable : false
					        }, {
						        name	: 'status',
						        index	: 'status',
						        width	: 120,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
						        	if(${user.wunit.orgType=='1'}){
						        		return rwdat.status=='1'?'新建':'已生成';
						        	}
						        	return rwdat.status=='1'?'新建':rwdat.status=='2'?'未上报':rwdat.status=='3'?'待审核':rwdat.status=='4'?'被退回':rwdat.status=='5'?'审核成功':'--';
						        }
					        },{
						        name	: 'reason',
						        index	: 'reason',
						        width	: 250,
						        align	: 'center',
						        formatter : function(value, opts, rwdat){
						        	var reason = rwdat.reason;
						        	if(reason!=null){
						        		return "<a href='#' onclick=\"viewReason(\'"+reason+"\')\">"+reason+"</a>";
						        	}else{
						        		return "";
						        	}
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
									if(${user.wunit.orgType!='3'}&&rwdat.status!='3'){
										str= "<a href='javascript:view(\""+rwdat.id+"\",\""+rwdat.status+"\")' title='' class='a-style'>审核报表</a>";
									}
									if(rwdat.status=='1'){
										str+= "<a href='javascript:viewCollect(\""+rwdat.id+"\",\""+rwdat.orgid+"\",\""+rwdat.year+"\",\""+rwdat.month+"\",\""+${user.wunit.orgType}+"\",\""+rwdat.status+"\")' title='' class='a-style'>生成报表</a>";	
									}else{
										str+= "<a href='javascript:viewCollect(\""+rwdat.id+"\",\""+rwdat.orgid+"\",\""+rwdat.year+"\",\""+rwdat.month+"\",\""+${user.wunit.orgType}+"\",\""+rwdat.status+"\")' title='' class='a-style'>查看报表</a>";
									}
									
						        	if(rwdat.orgid==${user.wunit.bm}){
						        		if(${user.wunit.orgType!='1'}){
								        	if(rwdat.status=='2'||rwdat.status=='4'){
								        			str+="<a href='javascript:openReport(\""+rwdat.id+"\")' title='' class='a-style'>上报</a>";
								        	}
						        		}
							        	if(rwdat.status!='3'&&rwdat.status!='5'){
							        		str+="<a href='javascript:openDelete(\""+rwdat.id+"\")' title='' class='a-style'>删除</a>";
							        	}
						        	}
									return str;			 
						        }
					        }],
					  pager: '#mainGridPager',
					  sortname : 'year,month',
					  sortorder : "desc,desc",
					  multiselect : false,
					  gridComplete:function(){
						  jqGridMergerCell("mainGrid","year");
						  jqGridMergerCell("mainGrid","quarter");
						}
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
				 $("#resetBtn").click(function(){
					 $("#yearmonth").val('').trigger('valuechange');
				 });
			});	
			function view(id,status){
				window.location = '${ctx}/data/jzgl/bbgl/jzgztj/list_detail.jsp?id='+id+"&status="+status;
			}
			function viewReason(text){
				$.container.popup({				
					items:[{
						title: '退回原因查看',
						fieldCols: 1,
						fieldCls:{labelCls : 'form-td-label-90',},
						fields:[
						        {text: '退回原因', type:'display', defValue:text,editable: false},
						]
					}]
				}, {
					width: '300px',
					height: '90px',
				});
			}
			//新建、编辑
			function openCreate(){
				$.container.popup({				
					items:[{
						save: "${ctx}/data/jzgl/bbgl/workstat/add.action",
						title: '报表创建',
						fieldCols: 1,
						fieldCls:{labelCls : 'form-td-label-90',},
						fields:[
						        {text: '报表时间', type:'yearmonthpicker', name: 'time',require:true},
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
			function viewCollect(id,orgId,year,month,orgType,status){
				var raq = '';
				if(orgType=='1'||orgType=='2')
					raq = 'sfj_jzgztjb';
				if(orgType=='3')
					raq = 'sfs_jzgztjb';
				$.dialog({ 
				    id: 'testID',
				    width: '1800px', 
					height: '700px', 
					title:'社区矫正工作统计表查看',
				    content: 'url:${ctx}/data/jzgl/bbgl/jzgztj/tb_Report.jsp?raq='+raq+'&id='+id+'&orgId='+orgId+'&year='+year+'&month='+month+'&status='+status,
					cancelVal: '关闭', 
					cancel: function(){
						$("#mainGrid").trigger("reloadGrid");
					} 
				});
			}
			function viewQuarter(orgId,year,quarter,status){
				var raq = 'jzgztjg_jd';
				var status = '3';
				if(${user.wunit.orgType=='1'}){
					status='2';
				}
				$.dialog({ 
				    id: 'testID',
				    width: '1800px', 
					height: '700px', 
					title:'社区矫正工作季度统计表查看',
				    content: 'url:${ctx}/data/jzgl/bbgl/jzgztj/tb_Report.jsp?view=1&raq='+raq+'&orgId='+orgId+'&year='+year+'&quarter='+quarter+'&status='+status,
					cancelVal: '关闭', 
					cancel: true
				});
			}
			function openDelete(id){
				$.dialog.confirm("确认要删除该条报表吗？", function() {
					$.ajax({
						type : "POST",
						async : false,
						url : "${ctx}/data/jzgl/bbgl/workstat/delete.action",
						data : "id=" + id,
						success:function(data){
							$("#mainGrid").trigger("reloadGrid");
						}
					});
				});
			}
			function openReport(id){
				$.dialog.confirm("确认要上报该条数据吗？", function() {
					$.ajax({
						type : "POST",
						async : false,
						url : "${ctx}/data/jzgl/bbgl/workstat/reportTable.action",
						data : "id=" + id,
						success:function(data){
							$("#mainGrid").trigger("reloadGrid");
						}
					});
				});
			}
			function search(){
				var time = $("#yearmonth").val();
				if(""!=time){
					var times = time.split("-");
					$(("input[name='year']")).attr("value",times[0]);
					$(("input[name='month']")).attr("value",times[1]);
				}
				$("#searchBtn").click();
			}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：社区矫正 -> 报表管理->社区矫正工作统计表</span> </div>
  <div class="container-top">
    <table class="search-table" >
      <tbody>
         <tr>
          <th class="search-form-label">查询时间</th>
          <td colspan="3">
          		<input id="yearmonth" name="yearmonth" type="text" searchType="gt"
							class="Wdate inputStyle search-form-field" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM',enableInputMask:false})" />
				
          </td>
          <td>
          		<input type="button"  value="查询" class="bttn bicon-search" onclick="search()"/>
          		<div style="display:none" id="searchDiv">
          		<input id="year" name="year" type="text" searchType="eq">
				<input id="month" name="month" type="text" searchType="eq">
         		<input type="button" class="bttn bicon-search"
							id="searchBtn" value="查询" />
				</div>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
      </tbody>
    </table>
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
