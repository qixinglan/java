<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>指纹仪信息管理</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			var notShowType='1,2,3,4,5,6,7,8,9';
			$(function() {
				$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "zwygl",});
				if(${user.wunit.orgType == '1'}){
					notShowType = '1,3,4,5,6,7,8,9';
				}else if(${user.wunit.orgType == '2'}){
					notShowType = '1,2,4,5,6,7,8,9';
				}
				//表格初始化
				$("#mainGrid").jqGrid({
					
					url		    : '${ctx}/data/zwy/search.action',
					colNames	: [
					    '','设备编号',  '设备IP', '设备状态','使用单位','操作'
					],
					colModel	: [
							{
								name	:'id',
								index	:'id',
								hidedlg	:false,
								hidden	:true
							},
					        {
						        name	: 'machineId',
						        index	: 'machineId',
						        searchType : 'cn',
						        sortable: true,
						        width	: 30,
						        align	: 'left'
					        }, {
						        name	: 'machineIp',
						        index	: 'machineIp',
						        searchType : 'cn',
						        sortable: true,
						        width	: 30,
						        align	: 'left'
					        },
					        {
						        name	: 'status',
						        index	: 'status',
						        width	: 30,
						        searchType : 'eq',
						        formatter : 'dictionary',
						        formatoptions: {code:'ZWYZT'},
						        align	: 'center'
					        },  {
						        name	: 'orgId',
						        index	: 'orgId',
						        searchType : 'cn',
						        sortable: true,
						        formatter: 'organization',
						        searchOption: {
						        	useCOrg: true, 
									notShowType: '1,4,5,6,7,8,9', 
									showRoot:true, 
									allowSearch: true
						        },
						       	label	: '使用单位',
						        align	: 'center'
					        }, 
					        {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 100,
						        align	: 'center',
						        sortable : false,
						        fixed : true,
						        formatter : function(value, opts, rwdat) {
									return "<a href='javascript:openView(\""+rwdat.id+"\");' title='' class='a-style'>查看</a>&nbsp;&nbsp;<a href='javascript:openCreate(\"edit\",\""+rwdat.id+"\");' title='' class='a-style'>编辑</a>";	
						        }
					        }
					],
						multiselect : true,
						rownumbers	: true,
						sortname:'machineId',
						sortorder:'desc',
					  pager: '#mainGridPager'
				});
				$("#mainGrid").addSearchForm("#search",{rows:"2"});
			});
			
			function openDel(){
				var ids = $("#mainGrid").getGridParam("selarrrow");
				if(ids==null||ids.length<1){
					alert("请选择删除数据！");
					return false;
				}
				if(!confirm('你确认要删除记录吗？'))
					return false;
				$.ajax({
				   type: "POST",
				   async:false,
				   url: "${ctx }/data/zwy/del.action",
				   data: "id="+ids,
				 });
				 $("#mainGrid").trigger("reloadGrid");
			}
			//新建、编辑
			function openCreate(oper,id){
				var read = '';
				if(oper=="edit"){
					read = '${ctx }/data/zwy/view.action?id='+id;
				}
				var fields=[{text:"机器编号",type:'text',name:'machineId',required: true,validater:'code', maxlength:'32'},
							{text:"机器IP",type:'text',name:'machineIp',validater:'ip',validater:'ip', maxlength:'15'},
							{text:"id",type:'hidden',name:'id',},
							{text:"状态",type:'dict_combobox',name:'status',code:'ZWYZT',allowBlank:false,required: true,},
							{text:"使用单位",type:'org_combobox',name:'orgId',required: true,
								useCOrg: true,notShowType: notShowType,showRoot:true,emptyText:'请选择',
								allowSearch: true,multiSelected:false,selectAll:false},
							{type:'hidden',name:'oper',value:'add'}];
				$.container.popup({
					title: '指纹仪信息维护',
					read: read,
					save: '${ctx }/data/zwy/add.action',
					fields:fields,
					yes : function(data){
						id = data.msg;
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{width: "650px",okVal:'保存'});
			}
			//导出
			function openExcel(){
				var colNames = $("#mainGrid").getGridParam("colNames");
				var sortName = $("#mainGrid").getGridParam("sortName");
				var url="${ctx }/data/ydzf/excel.action?colNames="+colNames+"&sortName="+sortName;
				if ($("#downloadcsv").length <= 0)
					$("body")
							.append(
									"<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
				$("#downloadcsv").attr("src", url);
			}
			function openView(id){
				var fields=[{text:"机器编号",type:'display',name:'machineId',required: true,},
							{text:"机器IP",type:'display',name:'machineIp',},
							{text:"状态",type:'display',name:'status',  formatter : $.dictionary.formatter('ZWYZT'),required: true},
							{text:"使用单位",type:'display',name:'orgId',required: true,
								formatter: 'organization',}];
				$.container.popup({
					title: '添加指纹仪',
					read: '${ctx }/data/zwy/view.action?id='+id,
					fields:fields,
					yes : function(data){
						id = data.msg;
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{width: "650px",okVal:'保存'});
			}
		</script>
		</head>

<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：电子监管 -> 配置管理 -> 指纹仪管理</span> </div>
  <div class="container-top">
    
  </div>
  <!--列表-->
  <div id="search"></div>
  <div class="buttonArea operation">
      <input type="button" id="btnAdd" class="bttn bicon-add"  value="新增" onclick="javascript:openCreate('add',null);"/>
      <input type="button" class="bttn bicon-delete" id="btnDel" onclick="javascript:openDel();" value="删除" />
    <!--  <input type="button" id="btnExcel" class="bttn bicon-excel"  onclick="javascript:openExcel();" value="导出"/> --> 
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
