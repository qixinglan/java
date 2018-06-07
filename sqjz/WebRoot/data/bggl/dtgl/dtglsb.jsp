<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动态信息上报</title>
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
			$.menu.init("#menuDetail",{first:"bggl",second:"dtxx",third : "dtxxsb",});
			$.dictionary.combobox("#status", "status", "DTXXZT",{attr:{searchType:"eq","class":"search-form-field" }});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/dynamicreport/list.action',
					colNames	: [
					    '信息标题', '上报人', '上报时间', '信息状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'title',
						        index	: 'title',
						        width	: 100,
						        align	: 'left'
					        }, {
						        name	: 'creatername',
						        index	: 'creatername',
						        width	: 15,
						        align	: 'center'
					        }, {
						        name	: 'reporttime',
						        index	: 'reporttime',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'status',
						        index	: 'status',
						        width	: 10,
						        align	: 'center',
						        formatter : $.dictionary.formatter('DTXXZT')
					        }, {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 30,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
						        	var str  = "<a href='javascript:dtxxView(\""+rwdat.id+"\")' title='' class='a-style'>查看</a>";	
						        	if(rwdat.status=='2'){
						        		str+="<a href='javascript:openCreate(\"edit\",\""+rwdat.id+"\")' title='' class='a-style'>编辑</a>";
										str+="<a href='javascript:openDel(\""+rwdat.affixId+"\",\""+rwdat.id+"\")' title='' class='a-style'>删除</a>";
										str+="<a href='javascript:openSbxx(\""+rwdat.id+"\")' title='' class='a-style'>上报</a>";
						        	}else{
						        		str+="<a title='' class='a-style gray'>编辑</a>";
										str+="<a title='' class='a-style gray'>删除</a>";
										str+="<a title='' class='a-style gray'>上报</a>";
						        	}
									return str;
						        }
					        }
					],
					  pager: '#mainGridPager',
					  sortname : 'createdate',
					  sortorder : "desc",
					  multiselect: false
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});		
			function openDel(affixId,id){
				$.dialog.confirm("确定删除？",function(){
					$.ajax({
						url:"${ctx}/data/dynamicreport/delete.action?affixId="+affixId+"&id="+id,
						success:function(){
							$("#mainGrid").trigger("reloadGrid");
						}
					});
				});
			}
			//新建、编辑
			function openCreate(oper, id){
				var read = '';
				var zt = '新建';
				if(oper=="edit"){
					zt = '编辑';
					read = '${ctx }/data/dynamicreport/search.action?id='+id+'&oper=edit';
				}
				$.container.popup({
					title: '动态信息上报'+zt,
					read : read,
					save: '${ctx }/data/dynamicreport/report.action',
					fields:[
						{text:"id",
						 type:'hidden',
						 name:'id'
						},
						{text:"信息标题",
						 type:'text',
						 name:'title',
						 required: true,
						 maxlength:50,
						 allowBlank:false
						},
						{text:"内容",
						 name:'content',
						 type:'textarea',
						 colspan:4,
						 required: true,
						 allowBlank:false
						},
						{text:"附件",
						 type:'file',
						 name:'affixId',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 accept:"application/vnd.ms-excel",
						 spath:"dtgl/attach",
						 editable: true
						}						
					],
					yes : function(){
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{width: "650px",okVal:'保存'});
			}
			//查看
			function dtxxView(id){
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
						 allowBlank:false
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
			//上报
			function openSbxx(id){
				$.dialog.confirm("确定上报？",function(){
					$.ajax({
						url:"${ctx}/data/dynamicreport/commitSbxx.action",
						data:"id="+id,
						success:function(){
							$("#mainGrid").trigger("reloadGrid");
						}
					})
				})
			}
		</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 动态信息管理->动态信息上报</span>
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
		<div class="buttonArea operation">
			<input type="button" id="btnAdd" class="bttn bicon-add" value="新建"
				onclick="javascript:openCreate();" />
		</div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<iframe name="upload_frame" id="upload_frame" style="display:none"></iframe>
	<div style="margin:10px;display:none">
		<form name="myForm" id="myForm"
			action="${ctx }/data/dynamicreport/report.action" method="post"
			enctype="multipart/form-data" target="upload_frame">
			<table class="comm-table">
				<tr style="height:30px">
					<td style="border:none">信息标题：</td>
					<td style="border:none"><input type="text" name="title"
						id="title" class="in" style="margin:10px" /></td>
				</tr>
				<tr>
					<td style="border:none">内容：</td>
					<td style="border:none;"><textarea rows="15" cols="200"
							class="in" name="content" id="content"></textarea></td>
				</tr>
				<tr id="attach">
					<td style="border:none">附件︾</td>
					<td style="border:none"><input type="button"
						onclick="upload(this)" value="添加附件" /></td>
				</tr>
				<input type="hidden" name="status" id="status" />
			</table>
		</form>
		<table>
			<tr id="ftr">
				<td style="border:none"><span id="fname"></span></td>
				<td colspan="1"
					style="border:none;text-align: left;padding-left:5px;"><input
					type="hidden" class="filename" name="filename" /> <input
					style="display:none" type="file" class="file" name="file" value=""
					onchange="writeName(this)" style="width:212px" /> <input
					type="button" onclick="chooseFile(this)" value="选择文件.." /></td>
				<td style="border:none"><input type="button" class="delbtn"
					value="删除" onclick="delattch(this);" /></td>
			</tr>
		</table>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>
