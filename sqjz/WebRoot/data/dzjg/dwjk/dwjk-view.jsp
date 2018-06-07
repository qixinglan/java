<%@page import="com.nci.dcs.system.model.User,com.nci.dcs.common.utils.LoginInfoUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定位监控</title>
<%@ include file="/common/commonjs.jsp"%>
<link rel="stylesheet"
	href="${ctx }/js/cmap/openlayer/theme/default/style.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx }/js/cmap/cmap.css" type="text/css" />
<script src="${ctx }/js/cmap/openlayer/OpenLayers.js"
	type="text/javascript"></script>
<script src="${ctx}/js/cmap/openlayer/lib/OpenLayers/Lang/zh-CN.js"
	type="text/javascript"></script>
	<script src="${ctx}/js/cmap/CMapIcon.js" type="text/javascript"></script>
<script src="${ctx}/js/cmap/cmap.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/js/jquery-maphilight.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"dwjk"});
	});
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div id="map" style="margin:0px; position:relative;width: 100%; height: 720px;">
	<div class="centerFixed" id="divCenter">
		<div class="maptitle" style="font-size: 30px;text-align: center;background-color: #F4F3EF" id="maptitle"></div>
	</div>
	<div class="rightFixed" id="divRight">
	    <a href="javascript:void(0);" onclick="changeMap(2);" ><img style="position:absolute;right:130px;top:7px" src="../../images/map_change_s.png"/></a>
	    <a href="${ctx}/data/dwjk/locationDetail.action" id="jkxq"><div class="dwjk-jkxq"></div></a>
	    <div id="qxsfjtj" class="divtj"></div>
	</div>
	</div>
	<div id="hint" class="dwjk-box4"></div>	
	<c:if test="${user.wunit.orgType == '1'||user.wunit.orgType == '2'}">
	<c:if test="${user.wunit.orgType == '2'}">
	<div style="display:none">
	</c:if>
		<div class="footerFixed">
			<ul class="dwjk-box5">
				<li id="1">北京市</li>
				<li id="181">顺义区</li>
				<li id="182">平谷区</li>
				<li id="183">门头沟区</li>
				<li id="184">通州区</li>
				<li id="185">大兴区</li>
				<li id="186">密云区</li>
				<li id="187">怀柔区</li>
				<li id="188">延庆区</li>
				<li id="190">丰台区</li>
				<li id="192">东城区</li>
				<li id="193">西城区</li>
				<li id="194">石景山区</li>
				<li id="195">海淀区</li>
				<li id="196">朝阳区</li>
				<li id="197">房山区</li>
				<li id="198">昌平区</li>
			</ul>
		</div>
		<c:if test="${user.wunit.orgType == '2'}">
		</div>
		</c:if>
	</c:if>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>

<script language="javascript">

	window.scrollTo(0,105);
    $(function(){					  
		  //地图初始化
		  init();
		  var href = "${ctx}/data/dwjk/locationDetail.action?orgId=";
		  var orgId = <%=request.getParameter("orgId")==null?LoginInfoUtils.getCurOrgId(session):request.getParameter("orgId")%>
		//  var parms=OpenLayers.Util.getParameters(location.href);		  		  		  
		  $(".dwjk-box5 li").click(function(){
			        $(this).addClass("selected")
			               .siblings().removeClass("selected");					
					GetFeatureInfo($(this).text(), "blue");
					markers.clearMarkers();
					var id = $(this).attr("id");
					searchSta(id);
					$.ajax({
						url:"${ctx}/data/jggl/get.action",
						data:"id="+id,
						success:function(data){
							$("#jkxq").attr("href",href + id+"&x="+(data.locX==null?"":data.locX)+"&y="+(data.locY==null?"":data.locY)+"&level=10");
							SetCenter(data.locX, data.locY, 8);
						}
					});
				});		  
		 //地图设置中心
	    //SetCenter(117.09438, 40.15000, 8);
		 var ORG_NAME= $("[id="+orgId+"]");				
		GetFeatureInfo(ORG_NAME.text(), "blue");
		searchSta(orgId);
		//设置初始选中项样式
        $(".dwjk-box5 li:[id='" + orgId + "']").addClass("selected");
        //初始化详情链接
		  $.ajax({
				url:"${ctx}/data/jggl/get.action",
				data:"id="+orgId,
				success:function(data){
					$("#jkxq").attr("href",href + orgId+"&x="+(data.locX==null?"":data.locX)+"&y="+(data.locY==null?"":data.locY)+"&level=10");
					SetCenter(data.locX, data.locY, 8);
				}
			});
	});
	function searchSta(orgId) {
		var count = 0;  //在册人数
		var epCount = 0;//监管人数
		var alarmCount = 0;//未处理报警总数
		$.ajax({
			type : "POST",
			async : true,
			url : "${ctx }/data/dwjk/countyView.action",
			data : "cCode=" + orgId,
			dataType : "json",
			success : function(datas) {
			$.each(
					datas,
					function(i, n){
						if (datas[i][5] != null && datas[i][6] != null) {
							var c1 = (datas[i][2] == null ? 0 : datas[i][2]);
							var c2 = (datas[i][3] == null ? 0 : datas[i][3]);
							var c3 = (datas[i][4] == null ? 0 : datas[i][4]);
							count = count + c1;
							epCount = epCount + c2;
							alarmCount = alarmCount + c3;	
							
							var s = new OpenLayers.Size(120, 66);
							
							
							var str = "<div name='"+i+"' class='dwjk-box1'" + 
										" id='div" + datas[i][0]+ "'"							           
										+ ")'"
										+ "><div style='position:absolute;bottom:-15px;'><b><a href='${ctx }/data/dwjk/locationDetail.action?x="
										+ datas[i][5]
										+ "&y="
										+ datas[i][6]
										+ "&level=11&orgId="
										+ datas[i][0]
										+ "'>"
										+ datas[i][1]
										+ "</a></b></div><span>"
										+ c1
										+ "</span><span>"
										+ c2
										+ "</span><span>"
										+ c3
										+ "</span>"
										+ "</div>";
										//var s = new OpenLayers.Size(120, 66);
										var icon = new CMapIcon(null, s, {
															x : -(s.w),
															y : -(s.h)
														});
										icon.imageDiv.innerHTML = str;
										var marker = addMarker(datas[i][5],
														datas[i][6], icon,
														null, false, true);
									   var name=datas[i][1].replace("司法所","");
									   var s = new OpenLayers.Size(16, 16);
									   var ic_show = "<div id='ic_show' name='"+i+"'><div style='width: 120px;position: absolute;"
									   +"bottom: -15px;left: -52px;text-align: center;'><b><a style='font-size:12px;color:black;'"
									   +" href='${ctx }/data/dwjk/locationDetail.action?x="
										+ datas[i][5]
										+ "&y="
										+ datas[i][6]
										+ "&level=11&orgId="
										+ datas[i][0]
										+ "'>"
										+ name
										+ "</a></b></div><img src='${ctx}/images/index/home_16_2.png'/></div>";
									   var icon_show = new CMapIcon(null, s, {
											x : -(s.w),
											y : -(s.h)
										});
										
										icon_show.imageDiv.innerHTML = ic_show;
										addMarker(datas[i][5],
														datas[i][6], icon_show,
														null, false, true);
										
										 marker.icon.rid = datas[i][0];
										 //marker.events.register("click", marker, searchSfs);
										 marker.events.register("mouseout", marker, onMouseout);
							}
						});
					var boxs = $('.dwjk-box1');
					$.each(boxs,function(i,n){
						$(boxs[i]).hide();
					});
					
					var boxs_show = $('div[id="ic_show"]');
					$.each(boxs_show,function(i,n){
						$(boxs_show[i]).mouseover(function(){
							var idx = $(this).attr("name");
							$(".dwjk-box1[name='"+idx+"']").show();
						}).mouseout(function(){
							var idx = $(this).attr("name");
							$(".dwjk-box1[name='"+idx+"']").hide();
						});;
					});
					
					
					var title = "<div class='divZJTJ'>在矫人员&nbsp;:" + count + "人</div><div class='divJGTJ'>电子监管人员&nbsp;:" + epCount + "人</div><div class='divBJTJ'>未处理报警&nbsp;:" + alarmCount + "条</div>";
					$("#qxsfjtj").html(title);
					var ORG_NAME= $("[id="+orgId+"]");				
					var maptitle = "<div class='divtitle'><b>"+ORG_NAME.text()+"各司法所社区服刑人员统计</b></div>";
					$("#maptitle").html(maptitle);
				}
			});
	}
	function onMouseout(evt)
	{
		$("#hint").hide();
	}
	function searchSfs(evt) {	
		var str = "";		
				$.ajax({
					type : "POST",
					async : true,
					url : "${ctx }/data/alarm/getSfsSta.action",
					data : "cCode=" + this.icon.rid,
					dataType : "json",
					success : function(datas) {
						if (datas != null) {
							var str = "<table><tr><th></th><th><img src='${ctx}/images/t4.png' width='20' height='20'/></th><th><img src='${ctx}/images/t5.png' width='20' height='20'/></th><th><img src='${ctx}/images/t6.png' width='20' height='20'/></th></tr>";
							$.each(datas, function(i, n) {
								var dictvalue="";
								if(datas[i][0]!=null)										 										
								 {
									dictvalue+=$.dictionary.formatter("JZLB")(datas[i][0]);								 							
								    str += "<tr><td>"									 
										+ (dictvalue)+":</td><td>"
										+ (datas[i][2]?datas[i][2]:0) + "</td><td>"
										+ (datas[i][3]?datas[i][3]:0) + "</td><td>"
										+ (datas[i][4]?datas[i][4]:0) + "</td></tr>";		
								 }
							});
							str += "</table>";
						}			
						$("#hint").html(str);
						$("#hint").show();        						       
					}
		});
        $("#hint").css("top", evt.y);
        $("#hint").css("left", evt.x);
	}

</script>
</body>
</html>
