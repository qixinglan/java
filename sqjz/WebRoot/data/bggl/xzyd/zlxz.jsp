<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>资料下载</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
		$(function() {
			$.menu.init("#menuDetail",{first:"bggl",second:"xzyd",third : "download",});
			$.dictionary.combobox("#filetype", "filetype", "ZLLX",{attr:{searchType:"eq","class":"search-form-field"}});
				
			//表格初始化
			$("#mainGrid").jqGrid({
				url		    : '${ctx}/data/fileoper/list.action?oper=download',
				colNames	: [
				    '资料名称', '资料类型', '上传单位', '上传时间','下载次数','操作'
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
				        }, {
					        name	: 'cz', 
					        index	: 'cz',
					        width	: 80,
					        align	: 'center',
					        fixed : true,
					        sortable : false,
					        formatter : function(value, opts, rwdat) {
								return "<a href='javascript:download(\""+rwdat.id+"\",\""+rwdat.realname+"\")' title='' class='a-style'>下载</a>";
					        }
				        }
				],
				multiselect:false,
				sortname : 'uploaddate',
				sortorder : "desc",
				pager: '#mainGridPager'
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});	
		
		function download(id,realname){
			 $.ajax({
				   type: "POST",
				   async:false,
				   url: "${ctx}/data/fileoper/checkFile.action?id="+id+"&filename="+realname,
				   success:function(data){
					   if(data==null){
						   window.location = "${ctx}/data/fileoper/download.action?id="+id+"&filename="+realname;
					   }else{
							$.dialog.alert(data);
					   }
				   }
				  // data: "id="+id+"&oper="+oper,
				 });
			
		}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：办公管理 -> 下载园地->资料下载</span> </div>
  <div class="container-top">
    <table class="search-table" id="searchDiv">
      <tbody>
        <tr>
          <th class="search-form-label">资料名称</th>
          <td>
          		<input name="filename" searchType="cn" type="text" style="width: 200px"/>
          </td>
          <th class="search-form-label">资料类型</th>
          <td id="filetype">
          </td>
	  
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
        <!-- 
         <tr>
          <th class="search-form-label">上传日期</th>
          <td colspan="3">
          		<input id="bjsj1" name="uploaddate" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 85px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
							至 <input id="bjsj2" name="uploaddate" type="text"
							class="Wdate inputStyle" style="width: 85px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />
          </td>
          <td>
          		
          </td>
        </tr>
         -->
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
