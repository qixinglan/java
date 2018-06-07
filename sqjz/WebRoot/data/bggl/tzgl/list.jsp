<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通知拟制</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			var notShowType='1,2,3,4,5,6,7,8,9';
		$(function() {
			$.menu.init("#menuDetail",{first:"bggl",second:"notice",third : "tznz"});
			if(${user.wunit.orgType == '1'}){
				notShowType = '1,3,4,5,6,7,8,9';
			}else if(${user.wunit.orgType == '2'}){
				notShowType = '1,2,4,5,6,7,8,9';
			}
			$.dictionary.combobox("#status", "status", "TZZT",{attr:{searchType:"eq","class":"search-form-field"}});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/tzgl/getData.action',
					colNames	: [
					    '','通知名称', '拟制人', '通知时间','要求','通知状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'id',
						        index	: 'id',
						        hidden : true
					        },{
						        name	: 'title',
						        index	: 'title',
						        width	: 40,
						        align	: 'left'
					        }, {
						        name	: 'creater',
						        index	: 'creater',
						        width	: 15,
						        align	: 'center'
					        }, {
						        name	: 'sendtime',
						        index	: 'sendtime',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'required',
						        index	: 'required',
						        width	: 40,
						        align	: 'left'
					        }, 
					        {
						        name	: 'status',
						        index	: 'status',
						        width	: 10,
						        align	: 'center',
						        formatter : $.dictionary.formatter('TZZT'),
					        }, {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 250,
						        align	: 'center',
						        fixed : true,
						        sortable : false,
						        formatter : function(value, opts, rwdat) {
						        	if(rwdat.status=='2'){
										return "<a href='javascript:view(\""+rwdat.id+"\")' title='' class='a-style'>查看</a>"+
										"<a href='javascript:openCreate(\"edit\",\""+rwdat.id+"\")' title='' class='a-style'>编辑</a>"+
										"<a href='javascript:delNotice(\""+rwdat.affixId+"\",\""+rwdat.id+"\")' title='' class='a-style'>删除</a>"+
										"<a href='javascript:sendNotice(\""+rwdat.id+"\")' title='' class='a-style'>下发</a>"+
										"<a title='' class='a-style gray'>签收情况</a>";	
						        		
									}else{
										return "<a href='javascript:view(\""+rwdat.id+"\")' title='' class='a-style'>查看</a>"+
										"<a title='' class='a-style gray'>编辑</a>"+
										"<a title='' class='a-style gray'>删除</a>"+
										"<a title='' class='a-style gray'>下发</a>"+
										"<a href='javascript:qsqkView(\""+rwdat.id+"\")' title='' class='a-style'>签收情况</a>";	
									}			 
						        }
					        }
					],
					  pager: '#mainGridPager',
					  sortname : 'createdate',
					  sortorder : "desc",
					  multiselect : false
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});	
			//新建、编辑
			function openCreate(oper,id){
				var read = '';
				var zt = '新建';
				if(oper=="edit"){
					zt = '编辑';
					read = '${ctx }/data/tzgl/search.action?id='+id+'&oper=edit';
				}
				var fields=[{text:"创建日期",type:'hidden',name:'createdate',},
							{text:"拟制人",type:'hidden',name:'creater',},
							{text:"id",type:'hidden',name:'id',},
							{text:"状态",type:'hidden',name:'status',value:'2'},
							{text:"通知接收单位",type:'org_combobox',name:'jgIds',required: true,
								useCOrg: true,notShowType: notShowType,showRoot:false,emptyText:'请选择',
								allowSearch: true,multiSelected:true,selectAll:true},
							{text:"通知名称",type:'text',name:'title',maxlength:50,required: true,allowBlank:false}];
 				if(${user.wunit.orgType == '1'}){
 					fields.push({text:"接收方类型",type:'dict_radio',name:'receiveType',code:'ZWZJ',required: true,
 						filter:'1,2,4,5,6,7,9,10,11,12,13,14'});
				}
 				fields.push({text:"要求",name:'required',type:'textarea',required: false,allowBlank:false});
 				fields.push({text:"备注",name:'description',type:'textarea',required: false,allowBlank:false});
 				fields.push({text:"附件",type:'file',name:'affixId',url:"${ctx}/data/common/upload.action",
					multiple:true,accept:"application/vnd.ms-excel",spath:"tzgl/upload",editable: true});
				$.container.popup({
					title: '通知拟制'+zt,
					read: read,
					save: '${ctx }/data/tzgl/postData.action',
					fields:fields,
					yes : function(data){
						id = data.msg;
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{width: "650px",okVal:'保存'});
			}
			//查看
			function view(id){
				var fields=[{text:"通知接收单位",type:'display',name:'jgIds',colspan:2,allowBlank:false},
							{text:"通知名称",type:'display',name:'title',colspan:2,allowBlank:false}];
				if(${user.wunit.orgType == '1'}){
 					fields.push({text:"接收方类型",type:'display',name:'receiveType',colspan:2,formatter : 'dictionary',
				        formatoptions: {code:'ZWZJ'}});
				}
				fields.push({text:"要求",name:'required',type:'textarea',readonly:true,colspan:2,allowBlank:false});
				fields.push({text:"备注",name:'description', type:'textarea',readonly:true,colspan:2,allowBlank:false});
				fields.push({text:"拟制人",type:'display',name:'creater',colspan:1,allowBlank:false});
				fields.push({text:"通知时间",type:'display',name:'sendtime',colspan:1,allowBlank:false});
				fields.push({text:"附件",type:'file',name:'affixId',url:"${ctx}/data/common/upload.action",
					multiple:true,accept:"application/vnd.ms-excel",spath:"tzgl/upload",colspan:2,
					readonly:true});
				$.container.popup({
					title: '通知拟制',
					read:'${ctx}/data/tzgl/search.action?id='+id+'&oper=view',
					fieldCols: 2,
					fieldCls:{
						labelCls : 'form-td-label-120'
					},
					fields:fields,
					yes : function(){}
				},{cancelVal : '关闭',title: '通知拟制查看',width: "800px"});
			}
			//删除
			function delNotice(affixId,id){
				$.dialog.confirm("确定删除吗？",function(){
					$.ajax({
						   type: "POST",
						   async:false,
						   url: "${ctx }/data/tzgl/delNotice.action",
						   data: "id="+id+"&affixId="+affixId,
						 });
					 $("#mainGrid").trigger("reloadGrid");
				});
			}
			//下发
			function sendNotice(id){
				$.dialog.confirm("确定下发吗？",function(){
					$.ajax({
						   type: "POST",
						   async:false,
						   url: "${ctx }/data/tzgl/xfNotice.action",
						   data: "id="+id,
						 });
					 $("#mainGrid").trigger("reloadGrid");
				});
			}
			function openAdd() {
				var id = arguments[0] || ("per" + new Date().getTime());
				$.dialog({
							id : id,
							width : '650px',
							height : '400px',
							fixed : true,
							lock : true,
							cover : true,
							title : '新增通知',
							content : 'url:${ctx}/data/tzgl/addNotice.action',
							oklVal : '保存',
							ok : function() {
								var doc = $("iframe[name='" + id + "']").get(0).contentDocument;
								return $("iframe[name='" + id + "']").get(0).contentWindow.$.fields.checkForm(
										$(doc.getElementById("noticeAdd")), {
											yes : function(data) {
												$("#mainGrid").trigger("reloadGrid");
											},
											mask : true,
											title : '新增通知',
											url : $(doc.getElementById("noticeAdd")).attr(
													"action")
										});
							},
							cancelVal : '关闭',
							cancel : true
						});
			}
			
			//签收情况查看
			function qsqkView(id) {
				/*
				$.container.popup({
					title : '签收情况查看',
					read : '',
					fields : [],
					items : [ {
						type : 'grid',
						id : 'grid-qsqk-container'
					} ],
					yes : function(data) {
					}
				}, {
					width : "650px",
					height:"200px",
					title : '签收情况查看',
					cancelVal : '关闭'
				});
				$('#grid-qsqk-container').jqGrid({
					rowNum : 10,
					url : '${ctx}/data/tzgl/noticeReceive.action?id=' + id,
					colNames : [ '', '通知名称', '通知接收单位', '签收状态', '签收日期', '签收人' ],
					colModel : [ {
						name : 'id',
						index : 'id',
						hidden : true
					}, {
						name : 'name',
						index : 'name',
						width : 100,
						align : 'center'
					}, {
						name : 'orgId',
						index : 'orgId',
						width : 100,
						align : 'center',
						formatter : function(value, opts, rwdat) {
							return $.organization.formatter()(rwdat.orgId);
						}
					}, {
						name : 'status',
						index : 'status',
						width : 100,
						align : 'center',
						formatter : function(value, opts, rwdat) {
							if (rwdat.status == "2") {
								return "待签收";
							} else {
								return "已签收";
							}
						}
					}, {
						name : 'receivetime',
						index : 'receivetime',
						width : 100,
						align : 'center'
					}, {
						name : 'receivername',
						index : 'receivername',
						width : 50,
						align : 'center'
					} ],
					multiselect : false,
					sortname : '',
					sortorder : "",
					pager: '#grid-qsqk-container'
					
				});
				
				 */
				var id = arguments[0] || ("per" + new Date().getTime());
				$.dialog({ 
				    id: id,
				    width: '700px', 
					height: '400px', 
					title:'签收情况',
				    content: 'url:${ctx }/data/tzgl/qsqkView.action?id='+id, 
					cancelVal: '关闭', 
					cancel: true 
				});
			}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：办公管理 -> 通知管理->通知拟制</span> </div>
  <div class="container-top">
    <table class="search-table" id="searchDiv">
      <tbody>
        <tr>
          <th class="search-form-label">通知名称</th>
          <td>
          		<input name="title" searchType="cn" type="text" style="width: 200px"/>
          </td>
          <th class="search-form-label">通知状态</th>
          <td id="status" > 
          </td>
          <th class="search-form-label">通知时间</th>
          <td colspan="3">
          		<input id="xfsj1" name="sendtime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 100px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'xfsj2\')}'})" />
							至 <input id="xfsj2" name="sendtime" type="text"
							class="Wdate inputStyle" style="width: 100px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'xfsj1\')}'})" />
          </td>	  
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          </td>
          <td>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
         <tr>
         
          
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表-->
  <div class="buttonArea operation">
  <span>
	<c:if test="${user.wunit.orgType != '3'}">
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
