<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function switchSysBar() {
		var img = $("#flex").attr("src");
		if (img.indexOf("flex_left.jpg") >= 0) {
			$("#leftMenu").css("display", "none");
			$("#flex").attr("src", "${ctx}/images/index/flex_right.jpg");
		} else {
			$("#leftMenu").css("display", "table-cell");
			$("#flex").attr("src", "${ctx}/images/index/flex_left.jpg");
		}
	}
</script>
<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0" style="table-layout:fixed;">
		<tr>
			<td width="180" id="leftMenu" height="100%" id=frmTitle noWrap name="fmTitle" align="center" style="background:#1fa8f7"
				valign="top">
				<table  width="180" height="100%" border="0" cellpadding="0"
					cellspacing="0" style="table-layout:fixed;">
					<tr>
						<td width="180" valign="top" id="menuDetail">
						</td>
					</tr>
				</table>
			</td>
			<td
				style="width:6px; background:url(${ctx}/images/index/flex_bg.jpg) left repeat-y;"
				valign="middle" onclick="switchSysBar()"><SPAN class=navPoint
				id=switchPoint title=关闭/打开左栏><img id="flex"src="${ctx}/images/index/flex_left.jpg"
					name="img1" width=6 height=39 id=img1></SPAN></td>
			<td width="100%" valign="top" style="background-color:#e5ebef">