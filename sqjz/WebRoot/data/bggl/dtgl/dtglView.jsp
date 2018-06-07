<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动态信息上报查看</title>
<%@ include file="/common/commonjs.jsp"%>
<style>
.in {
	text-align: left;
	padding: 0px;
	margin: 0px"
}
</style>
<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"bggl",second:"dtxx",third : "dtxxck",});
			$.dictionary.combobox("#status", "status", "DTXXZT",{filter: "2",attr:{searchType:"eq","class":"search-form-field"}});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/dynamicreport/list.action?oper=dtglView',
					colNames	: [
					    '信息标题', '上报单位', '上报时间', '信息状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'title',
						        index	: 'title',
						        width	: 100,
						        align	: 'left'
					        }, {
						        name	: 'recordOrgId',
						        index	: 'recordOrgId',
						        width	: 60,
						        formatter : 'organization',
						        align	: 'left'
					        }, {
						        name	: 'reporttime',
						        index	: 'reporttime',
						        width	: 30,
						        align	: 'center'
					        }, {
						        name	: 'status',
						        index	: 'status',
						        width	: 20,
						        align	: 'center',
						        formatter : $.dictionary.formatter('DTXXZT')
					        }, {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 10,
						        align	: 'center',
						        sortable: false,
						        formatter : function(value, opts, rwdat) {
						        	var replyId = '';
						        	if(rwdat.reply!=null){
						        		replyId = rwdat.reply.id;
						        	}
						        	var str="";
						        	if(rwdat.status=='3'){
						        		str+="<a href='javascript:dtxxQs(\""+strTrans(rwdat.title)+"\",\""+rwdat.id+"\")' title='' class='a-style'>签收</a>";
						        	}else{
						        		str+= "<a href='javascript:dtxxView(\""+strTrans(rwdat.title)+"\",\""+rwdat.id+"\")' title='' class='a-style'>查看</a>";
						        	}
									return str;
						        }
					        }
					],
					  pager: '#mainGridPager',
					  sortname : 'reporttime',
					  sortorder : "desc",
					  multiselect :false
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});
			function strTrans(title){
				if(title != null){
					return title.replace(/'/g,"‘").replace(/"/g,"“");
				}else{
					return title;
				}
			}
			//查看
			function dtxxView(title,id){
				$.container.popup({
					title: '动态信息上报查看',
					read: '${ctx }/data/dynamicreport/search.action?id='+id+'&oper=view',
					fieldCols: 2,
					fieldCls:{
						labelCls : 'form-td-label-120'
					},
					fields:[
						{text:"信息标题",
						 name:'title',
						 type:'display',
						 colspan:2,
						 allowBlank:false,
						 value:title
						},
						{text:"内容",
						 name:'content',
						 type:'textarea',
						 readonly:true,
						 colspan:2,
						 allowBlank:false
						},
						{text:"上报人",
						 name:'creatername',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"上报时间",
						 name:'reporttime',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"附件",
						 name:'affixId',
						 type:'file',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 colspan:2,
						 accept:"application/vnd.ms-excel",
						 spath:"dtgl/attach",
						 readonly:true
						},
						{text:"签收人",
						 name:'reply.replyPersonName',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"签收日期",
						 name:'reply.replydate',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						}
					],
					yes : function(){}
				},{cancelVal : '关闭',title: '动态信息上报查看', width: "800px"});
			}
			//签收
			function dtxxQs(title,id){
				$.container.popup({
					title: '动态信息上报查看',
					read: '${ctx }/data/dynamicreport/view.action?id='+id+'&oper=view',
					fieldCols: 2,
					fieldCls:{
						labelCls : 'form-td-label-120'
					},
					fields:[
						{text:"信息标题",
						 name:'title',
						 type:'display',
						 colspan:2,
						 allowBlank:false,
						 value:title
						},
						{text:"内容",
						 name:'content',
						 type:'textarea',
						 readonly:true,
						 colspan:2,
						 allowBlank:false
						},
						{text:"上报人",
						 name:'creatername',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"上报时间",
						 name:'reporttime',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"附件",
						 name:'affixId',
						 type:'file',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 colspan:2,
						 accept:"application/vnd.ms-excel",
						 spath:"dtgl/attach",
						 readonly:true
						},
						{text:"签收人",
						 name:'reply.replyPersonName',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						},
						{text:"签收日期",
						 name:'reply.replydate',
						 type:'display',
						 colspan:1,
						 allowBlank:false
						}
					],
					yes : function(){}
				},{cancelVal : '关闭', width: "800px",cancel:function(){$("#mainGrid").trigger("reloadGrid");}});
			}
			//批复--已废弃
			function openPf(replyId,title,reportId){
				var read = '';
				if(replyId!=null && replyId!=""){
					read = '${ctx }/data/dynamicreport/searchPfxx.action?id='+replyId;
				}
				$.container.popup({
					title: title+'的动态信息上报批复',
					read : read,
					save: '${ctx }/data/dynamicreport/savePfxx.action',
					fields:[
						{text:"id",
						 type:'hidden',
						 name:'id',
						 value:replyId
						},
						{text:"上报信息ID",
						 type:'hidden',
						 name:'report.id',
						 value:reportId
						},
						{text:"批复人",
						 type:'hidden',
						 name:'replyPersonId'
						},
						{text:"批复日期",
						 type:'hidden',
						 name:'replydate'
						},
						{text:"报告标题",
						 name:'report.title',
						 type:'hidden',
						 colspan:2,
						 allowBlank:false
						},
						{text:"批复内容",
						 name:'relyContent',
						 type:'textarea',
						 colspan:2,
						 required: true,
						 allowBlank:false
						}/*,
						{text:"附件",
						 name:'affixId',
						 type:'file',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 colspan:2,
						 accept:"application/vnd.ms-excel",
						 spath:"dtgl/attach",
						 readonly:true
						}*/
					],
					yes : function(){
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{okVal : '保存', width: "650px", height: '150px'});
			}
		</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 动态信息管理->动态信息上报查看</span>
		</div>
		<div class="container-top">
			<table class="search-table" id="searchDiv">
				<tbody>
					<tr>
						<th class="search-form-label">信息标题</th>
						<td><input name="title" searchType="cn" type="text" class="search-form-field" /></td>
						<th class="search-form-label">信息状态</th>
						<td id="status"></td>
						<th class="search-form-label">上报时间</th>
						<td colspan="3"><input id="bjsj1" name="reporttime"
							type="text" searchType="gt" class="Wdate inputStyle"
							style="width: 100px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
							至 <input id="bjsj2" name="reporttime" type="text"
							class="Wdate inputStyle" style="width: 100px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
						</td>
						<td><input type="button" class="bttn bicon-search"
							id="searchBtn" value="查询" /></td>
							<td><input type="button" class="bttn bicon-reset"
							id="resetBtn" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
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
