<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.nci.dcs.common.utils.LoginInfoUtils" %>
<%
	String contextPath = request.getContextPath();
	//使用现有的模块来获取当前登录信息 夏先智
	String orgId = LoginInfoUtils.getCurOrgId(request.getSession());
	orgId = (orgId == null || orgId.isEmpty()) ? "1" : orgId;
	
	String orgType= LoginInfoUtils.getOrgType(request.getSession());
	Integer roleId = LoginInfoUtils.getRoleId(request.getSession());
	String role=(roleId == null )? "" : Integer.toString(roleId);
%>
<meta name="renderer" content="webkit"/> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<script type="text/javascript">
	//应用部署名,跨模块使用URL时,引用images时使用CONTEXT_PATH + 其在webroot下的子路径
	CONTEXT_PATH = '<%=contextPath%>';
	ORG_ID='<%=orgId%>'
	ORG_TYPE = '<%=orgType%>';
	ROLE_ID = '<%=role%>';
</script>
<link rel="stylesheet" href="${ctx}/css/indexcss.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/common3.css" />
<link rel="stylesheet" href="${ctx}/css/color.css" />
<link rel="stylesheet" href="${ctx}/css/button.css" />
<link rel="stylesheet" href="${ctx}/css/index.css" />
<link rel="stylesheet" href="${ctx}/css/overcast/jquery-ui.css" />
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
<link href="${ctx}/js/triggerfield/css/jquery.triggerfield.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/triggerfield/js/jquery.triggerfield.js" type="text/javascript"></script>
<link href="${ctx}/js/combobox/css/jquery.simple.tree.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/combobox/js/jquery.simple.tree.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript" src="${ctx}/js/my97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="${ctx}/js/common2.js"></script>
<link rel="stylesheet" href="${ctx}/css/dictionary.css" type="text/css"></link>
<script language="javascript" src="${ctx}/js/custom/utils.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/dictionary.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/organization.js" type="text/javascript"></script>
<script type="text/javascript">
	$.organization.load("${ctx}/data/jggl/json.action",'<%=orgId%>');
</script>
<link rel="stylesheet" href="${ctx}/css/fields.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/js/qtip/jquery.qtip.min.css" />
<script language="javascript" src="${ctx}/js/qtip/jquery.qtip.min.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/validater.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/fields.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/container.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/jqgrid.searchform.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/custom/pinyin.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/tabpage.css"/>
<link rel="stylesheet" href="${ctx}/js/select2-3.5.1/select2.css" />
<link rel="stylesheet" href="${ctx}/js/select2-3.5.1/select2-custom.css" />
<script language="javascript" src="${ctx}/js/select2-3.5.1/select2.min.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/select2-3.5.1/select2_locale_zh-CN.js" type="text/javascript"></script>
<script language="javascript" src="${ctx}/js/select2-3.5.1/select2-custom.js" type="text/javascript"></script>
<!--新增左侧菜单-->
<%
	String reportUrl = com.nci.dcs.common.runqian.RemoteReportUtil.getReportUrl(request);
%>
<script>
	var reportUrl = "<%=reportUrl%>";
</script>
<script language="javascript" src="${ctx}/data/menu.js" type="text/javascript"></script>