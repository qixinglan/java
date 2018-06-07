<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>显示列设置页面</title>
<%
	String contextPath = request.getContextPath();
%>
<script type="text/javascript">
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	CONTEXT_PATH = '<%=contextPath%>';
</script>
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/common3.css" />
<link rel="stylesheet" href="${ctx}/css/color.css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<link href="${ctx}/js/grid/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery.extends.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/jquery.form.min.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/grid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/grid/grid.locale-cn.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/grid/jqgrid.custom.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/grid/jquery.jqGrid.src.js" type="text/javascript"></script>
<!-- lhgdialog -->
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>

<link href="${ctx}/js/combobox/css/jquery.simple.tree.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/combobox/js/jquery.simple.tree.js" type="text/javascript"></script>
<link href="${ctx}/js/triggerfield/css/jquery.triggerfield.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/triggerfield/js/jquery.triggerfield.js" type="text/javascript"></script>
<script src="${ctx}/js/combobox/js/jquery.combobox.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript" src="${ctx}/js/my97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="${ctx}/js/common2.js"></script>
<link rel="stylesheet" href="${ctx}/css/dictionary.css" type="text/css"></link>
<script language="javascript" src="${ctx}/js/custom/utils.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/dictionary.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/organization.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/fields.css" type="text/css"></link>
<script language="javascript" src="${ctx}/js/custom/fields.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/container.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/jqgrid.searchform.js" type="text/javascript"></script>
		<script type="text/javascript">		
		$(function() {
			$.dictionary.combobox("#filetype", "filetype", "ZLLX",{attr:{searchType:"eq"}});
										});
		function writeName(){
			var name = $("#file").val().substring(($("#file").val()).lastIndexOf("\\")+1);
			$("#filename").attr("value",name);
		}
		var api = frameElement.api;
		var data = api.data;
		function submit(){
			//非ie方法
			$("#myForm").ajaxSubmit();
			data.trigger("reloadGrid");
			//ie下提交还是有问题
			//var formData = $("#form1").serialize();
			//document.getElementById("submit").click();
			
		}
		api.button({
			id:'ok',
			name:'确定',
			callback:submit
		})
		</script>
		</head>

		<body>
		<div style="margin:10px;">
		<form name="myForm" id="myForm" action="${ctx }/data/fileoper/upload.action" method="post" enctype="multipart/form-data">
			<span style="display:none"><input type="submit" id="submit"/></span>
		  	<table class="comm-table">
		  	 	<tr style="height:30px">
		  	 			<td style="border:none">
		  	 				资料类型：
		  	 			</td>
						<td style="border:none" id="filetype">
		  	 			</td>
		  	 		</tr>
		  	 		<tr>
		  	 			<td style="border:none">
		  	 				上传文件：
		  	 			</td>
						<td style="border:none;text-align: left;padding-left:5px;">
								<input type="hidden" id="filename" name="filename"/>
								<input type="file" id="file" name="file" value=""  onchange="writeName()" style="width:212px"/>
		  	 			</td>
		  	  		</tr>
		    </table>	
		    </form>
		</div>
    </body>
</html>
