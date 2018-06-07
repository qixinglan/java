<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>资料上传</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"bggl",second:"xzyd",third : "upload",});
			$.dictionary.combobox("#filetype", "filetype", "ZLLX",{attr:{searchType:"eq","class":"search-form-field"}});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/fileoper/list.action',
					colNames	: [
					    '资料名称', '资料类型', '上传单位', '上传时间','下载次数','状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'filename',
						        index	: 'filename',
						        width	: 80,
						        align	: 'left'
					        }, {
						        name	: 'filetype',
						        index	: 'filetype',
						        width	: 15,
						        align	: 'center',
						        formatter : $.dictionary.formatter('ZLLX')
					        }, {
						        name	: 'uploadorg',
						        index	: 'uploadorg',
						        width	: 50,
						        align	: 'left',
						        formatter:function(value, opts, rwdat){
						        	return $.organization.formatter()(rwdat.uploadorg);
						        }
					        }, {
						        name	: 'uploaddate',
						        index	: 'uploaddate',
						        width	: 40,
						        align	: 'center',
						        formatter:function(value, opts, rwdat){
						        	return rwdat.uploaddate.replace("T"," ");
						        }
					        }, {
						        name	: 'downloadtime',
						        index	: 'downloadtime',
						        width	: 15,
						        align	: 'center'
					        },  {
						        name	: 'filestatus',
						        index	: 'filestatus',
						        width	: 10,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
						        	if(rwdat.filestatus=="1"){
										return "启用";	
									}else{
										return "停用";	
									}			 
						        }
					        }, {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 80,
						        align	: 'center',
						        fixed : true,
						        sortable : false,
						        formatter : function(value, opts, rwdat) {
						        	var url = "";
						        	if(rwdat.filestatus=="1"){
										url = "<a href='javascript:fileOper(\""+rwdat.id+"\",\"stop\")' title='' class='a-style'>停用</a>";	
									}else{
										url = "<a href='javascript:fileOper(\""+rwdat.id+"\",\"start\")' title='' class='a-style'>启用</a>";	
									}
										return url + "<a href='javascript:deleteFile(\""+rwdat.id+"\",\"stop\")' title='' class='a-style'>删除</a>";		 
						        }
					        }
					],
					  multiselect:false,
					  pager: '#mainGridPager',
					  sortname : 'uploaddate',
					  sortorder : "desc",
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});		
			function openEdit1(){
				var form = null;
				form = $("#myForm").clone();			
				$.dialog({ 
				    id: 'testID',
				    width: '650px', 
    				height: '150px', 
    				title:'上传资料',
				    content: form , 
				    ok: function(){ 
				    	if($("#filename").val()==""){
				    		alert("上传文件不能为空！");
				    		return false; 
				    	}
						$("#myForm").submit();
				    	$("#mainGrid").trigger("reloadGrid");
    				}, 
    				okVal:'上传',
    				cancelVal: '取消', 
    				cancel: true ,
    				init:function(){
    					$.dictionary.combobox("#filetype_form", "filetype", "ZLLX",{allowBlank:false,zIndex:2000});
    				},
    				drag:false
				});
			}
			function fileOper(id,oper){
				 $.ajax({
					   type: "POST",
					   async:false,
					   url: "${ctx }/data/fileoper/fileoper.action",
					   data: "id="+id+"&oper="+oper,
					 });
				 $("#mainGrid").trigger("reloadGrid");
			}
			
			function openEdit() {
				$.container.popup({
					title: '文件上传',
					save: "${ctx }/data/fileoper/upload.action",
					fields:[
					{text: '资料类型', 
						type:'dict_combobox',
						zIndex: 2000,
						name: 'filetype', 
						code:'ZLLX', 
						required: true,
						allowBlank:false
						},
						{text:"上传文件",
						 type:'file',
						 name:'importFile',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 accept:"application/vnd.ms-excel",
						 accepted:".doc,.docx,.xls,.ppt,.pdf,.txt,.exe",
						 spath:"xzyd/upload",
						 limitSize:"20",
						 required: true,
						 allowBlank:false
						}
					],
					yes : function(){
						$.dialog.alert("文件上传成功!",null,this);
						$("#mainGrid").trigger("reloadGrid");
						return true;
					}
				},{width: "450px"});
			}
			function deleteFile(id,realname){
				$.dialog.confirm("确定删除吗？",function(){
					$.ajax({
						   type: "POST",
						   async:false,
						   url: "${ctx }/data/fileoper/deletefile.action",
						   data: "ids="+id+"&realname="+realname,
						 });
					 $("#mainGrid").trigger("reloadGrid");
				});
			}
			function writeName(){
				var name = $("#file").val().substring(($("#file").val()).lastIndexOf("\\")+1);
				$("#filename").attr("value",name);
			}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：办公管理 -> 下载园地->资料上传</span> </div>
  <div class="container-top">
    <table class="search-table" id="searchDiv">
      <tbody>
        <tr>
          <th class="search-form-label">资料名称</th>
          <td>
          		<input name="filename" searchType="cn" type="text" style="width: 200px"/>
          </td>
          <th class="search-form-label">资料类型</th>
          <td id="filetype" >
          
          <th class="search-form-label">上传日期</th>
          <td colspan="3">
          		<input id="bjsj1" name="uploaddate" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 100px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
							至 <input id="bjsj2" name="uploaddate" type="text"
							class="Wdate inputStyle" style="width: 100px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
          </td>
          	  
          </td>
	  
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          </td>
           <td>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表-->
  <div class="buttonArea operation">
   <input type="button" id="btnAdd" class="bttn bicon-add"  value="上传" onclick="javascript:openEdit();"/>
  </div>
  
  <div class="container-bottom">
    <table id="mainGrid">
    </table>
    <div id="mainGridPager"></div>
  </div>
</div>
<iframe name="upload_frame" id="upload_frame" style="display:none"></iframe>
<!-- 
<div style="margin:10px;display:none">
		<form name="myForm" id="myForm" action="${ctx }/data/fileoper/upload.action" method="post" enctype="multipart/form-data" target="upload_frame">
		  	<table class="comm-table">
		  	 	<tr style="height:30px">
		  	 			<td style="border:1 solid;">
		  	 				资料类型：
		  	 			</td>
						<td style="border:1 solid;" id="filetype_form">
		  	 			</td>
		  	 		</tr>
		  	 		<tr>
		  	 			<td style="border:1 solid;">
		  	 				上传文件：
		  	 			</td>
						<td style="border:1 solid;text-align: left;padding-left:0px;">
								<input type="hidden" id="filename" name="filename"/>
								<input type="file" id="file" name="file" value=""  onchange="writeName()" style="width:212px"/>
		  	 			</td>
		  	  		</tr>
		    </table>	
		    </form>
		</div> -->
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
</body>
</html>
