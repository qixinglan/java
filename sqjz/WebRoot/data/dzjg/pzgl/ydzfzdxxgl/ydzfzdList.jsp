<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>移动执法终端信息管理</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			$(function() {
				$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "ydzfzd",});
				//表格初始化
				$("#mainGrid").jqGrid({
					
					url		    : '${ctx}/data/ydzf/search.action',
					colNames	: [
					    '','设备编号',  '购进日期', '设备状态','领用单位','领用日期','操作'
					],
					colModel	: [
							{
								name	:'id',
								index	:'id',
								hidedlg	:false,
								hidden	:true
							},
					        {
						        name	: 'equnumber',
						        index	: 'equnumber',
						        searchType : 'cn',
						        sortable: true,
						        width	: 30,
						        align	: 'left'
					        }, 
					        /*{
						        name	: 'equtype',
						        index	: 'equtype',
						        searchType : 'eq',
						        width	: 30,
						        formatter :'dictionary',
								formatoptions:{code:'SBXH'},
						        align	: 'center'
					        },*/
					        {
						        name	: 'procureDate',
						        index	: 'procureDate',
						        width	: 30,
						        align	: 'center',
						        formatter : "date",
								formatoptions : {
									 newformat: 'Y-m-d'
								}
					        }, {
						        name	: 'equstatus',
						        index	: 'equstatus',
						        width	: 30,
						        searchType : 'eq',
						        formatter : 'dictionary',
						        formatoptions: {code:'SBZT'},
						        align	: 'center'
					        },  {
						        name	: 'orgId',
						        index	: 'orgId',
						        searchType : 'cn',
						        width	: 30,
						        align	: 'center'
					        },{
						        name	: 'useTime',
						        index	: 'useTime',
						        width	: 30,
						        align	: 'center',
						        formatter : "date",
								formatoptions : {
									newformat: 'Y-m-d'
								}
					        }, //{
						        //name	: 'MOrgId',
						        //index	: 'MOrgId',
						       //searchType : 'eq',
						       // width	: 30,
						       // align	: 'left'
					       // },
					        {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 100,
						        align	: 'center',
						        sortable : false,
						        fixed : true,
						        formatter : function(value, opts, rwdat) {
									return "<a href='javascript:openView(\""+rwdat.id+"\");' title='' class='a-style'>查看</a>&nbsp;&nbsp;<a href='javascript:openEdit(\""+rwdat.id+"\");' title='' class='a-style'>编辑</a>";	
						        }
					        }
					],
					sortname:'equnumber,procureDate,equstatus,orgId,useTime',
					sortorder:'desc,desc,desc,desc,desc',
						multiselect : true,
						rownumbers	: true,
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
				   url: "${ctx }/data/ydzf/del.action",
				   data: "id="+ids,
				 });
				 $("#mainGrid").trigger("reloadGrid");
			}
			function openAdd(){
				$.dialog({ 
					id:"add",
				    width: '850px', 
    				height: '350px', 
    				title:'移动执法终端信息新增/编辑',
				    content: 'url:${ctx }/data/ydzf/add.action', 
				    ok: function(){
				    	var form =$(this).attr("iframe").contentDocument.forms[0];
						form = $(form);
						if (!$(this).attr("iframe").contentWindow.$.fields.validateForm(form)){
							return false;
						}
						var equnumber = form.find("#equnumber").val();
						$.ajax({
							   type: "POST",
							   async:false,
							   url: "${ctx }/data/ydzf/check.action",
							   data: "equnumber="+equnumber,
							   success:function(data){
								   checkFlag = data;
							   }
							 });
						if(!checkFlag){
							$.dialog.alert("设备编号："+equnumber+"已存在！");
							return false;
						}
						var ajax_option = {
								url:form.attr("action"),
								success:function(data){
									$("#mainGrid").trigger("reloadGrid");
								}
						}
						form.ajaxSubmit(ajax_option);
    				}, 
    				okVal:'保存',
    				cancelVal: '取消', 
    				cancel: true 
    				
				});
				
			}
			//编辑
			function openEdit(id){
				var checkFlag = false;
				var dg = $.dialog({ 
				    id: 'eidt',
				    width: '850px', 
    				height: '350px', 
    				title:'移动执法终端信息新增/编辑',
				    content: 'url:${ctx }/data/ydzf/add.action?oper=edit&id='+id, 
				    ok: function(){
        			 	var form =$(this).attr("iframe").contentDocument.forms[0];
						form = $(form);
						if (!$(this).attr("iframe").contentWindow.$.fields.validateForm(form)){
							return false;
						}
						if(form.find("#useTime").val()==""&&form.find("#orgId").val()!=""){
							$(this).attr("iframe").contentWindow.focError(form.find("#useTime"),"填写使用时间");
							return false;
						}
						if(form.find("#useTime").val()!=""&&form.find("#orgId").val()==""){
							$(this).attr("iframe").contentWindow.focError(form.find("#orgId"),"填写领用单位");
							return false;
						}
						/*var equnumber = form.find("#equnumber").val();
						$.ajax({
							   type: "POST",
							   async:false,
							   url: "${ctx }/data/ydzf/check.action",
							   data: "id ="+form.find("#id").val()+"&equnumber="+equnumber,
							   success:function(data){
								   checkFlag = data;
							   }
							 });
						if(!checkFlag){
							$.dialog.alert("设备编号："+equnumber+"已存在！");
							return false;
						}*/
						var ajax_option = {
								url:form.attr("action"),
								success:function(data){
									$("#mainGrid").trigger("reloadGrid");
								}
						};
						form.ajaxSubmit(ajax_option);
    				}, 
    				okVal:'保存',
    				cancelVal: '取消', 
    				cancel: true 
				});
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
				var dataRow = $("#mainGrid").jqGrid("getRowData",id);
				
				$.dialog({ 
				    id: id,
				    width: '650px', 
    				height: '230px', 
    				title:'移动执法终端信息查看',
    				//oper : 'view',
				    content: 'url:${ctx }/data/ydzf/view.action?id='+id, 
    				cancelVal: '关闭', 
    				cancel: true ,
    				data: dataRow
				});
			}
		</script>
		</head>

<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：电子监管 -> 配置管理 -> 移动执法终端信息管理</span> </div>
  <div class="container-top">
    <!-- <table class="search-table" id="searchDiv">
      <tbody>
        <tr>
          <th>设备编号</th>
          <td>
          		<input type="text" name="equnumber" searchType="cn"/>
          </td>
          <th>设备型号</th>
          <td id="sbxh">
				<input type="text" name="orgId" searchType="cn"/>
          </td>
	  <th>购进日期</th>
          <td>
          		<input class="Wdate" name="gjdateS" type="text" style="width:85px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16"/> 至
          		<input class="Wdate" name="gjdataE" type="text" style="width:85px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16"/>
          </td>
          <td>
          		<input id="searchBtn" type="button" class="bttn bicon-search"  value="查询"/>
          </td>
        </tr>
        <tr>
          <th>设备状态</th>
          <td>
          		<input type="text" id="sbzt" name="sbzt" />
          </td>
          <th>领用日期</th>
          <td>
          		<input class="Wdate" name="lydateS" type="text" style="width:85px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16"/> 至
          		<input class="Wdate" name="lydateE" type="text" style="width:85px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="16"/>
          </td>
          <th>领用单位</th>
          <td>
          		<input type="text" name="orgId" searchType="cn" style="width:212px"/>
          </td>
         
        </tr>
         <tr>
          <th>管理单位</th>
          <td colspan="5">
          		<input type="text" id="sbzt" name="sbzt" value="全部" >
          </td>

          <td>
          		<input id="resetBtn" type="button" class="bttn bicon-reset"  value="重置"/>
          </td>
        </tr>
      </tbody>
    </table> -->
  </div>
  <!--列表-->
  <div id="search"></div>
  <div class="buttonArea operation">
      <input type="button" id="btnAdd" class="bttn bicon-add"  value="新增" onclick="javascript:openAdd();"/>
      <input type="button" class="bttn bicon-delete" id="btnDel" onclick="javascript:openDel();" value="删除" />
      <input type="button" id="btnExcel" class="bttn bicon-excel"  onclick="javascript:openExcel();" value="导出"/>
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
