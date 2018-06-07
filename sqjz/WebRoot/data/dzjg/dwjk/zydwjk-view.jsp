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
<script type="text/javascript" src="${ctx }/js/jquery-maphilight.min.js"></script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	 
	<div class="centerFixed" id="divCenter">
		<div class="maptitle" style="font-size: 30px;text-align: center;background-color: #F4F3EF" id="maptitle"></div>
	</div>
	<div style="margin-left:0px;position:relative; width:100%; height:720px;">
	<div class="rightFixed" id="divRight">
	    <a href="javascript:void(0);" onclick="changeMap(1);" ><img style="position:absolute;right:123px;top:7px" src="../../images/map_change.png"/></a>
	    <a href="${ctx}/data/dwjk/zylocationDetail.action" id="jkxq"><div class="dwjk-jkxq"></div></a>
	    <div id="qxsfjtj" class="divtj"></div>
	</div>
     <iframe id="ezmap" src="${ctx }/js/ezmap/ezmap.jsp"  scrolling=“no” width="100%" height="100%"></iframe>
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
	<%@include file="/data/bottom.jsp"%>

<script language="javascript">
	window.scrollTo(0,105);
	var mapApp;
    $(function(){
    	  mapApp=document.frames["ezmap"];
		  var href = "${ctx}/data/dwjk/zylocationDetail.action?orgId=";
		  var orgId = <%=request.getParameter("orgId")==null?LoginInfoUtils.getCurOrgId(session):request.getParameter("orgId")%>
		//  var parms=OpenLayers.Util.getParameters(location.href);		  		  		  
		  $(".dwjk-box5 li").click(function(){
			        $(this).addClass("selected")
			               .siblings().removeClass("selected");						        
			        //mapApp.clearMap();
			        mapApp.GetFxryDZWL($(this).text());
					var id = $(this).attr("id");
					searchSta(id);
					$.ajax({
						url:"${ctx}/data/jggl/get.action",
						data:"id="+id,
						success:function(data){
							$("#jkxq").attr("href",href + id+"&x="+(data.locX==null?"":data.locX)+"&y="+(data.locY==null?"":data.locY)+"&level=10");
						}
					});
				});		  
		 //地图设置中心
	    //SetCenter(117.09438, 40.15000, 8);
		 setTimeout(function(){
		 var ORG_NAME= $("[id="+orgId+"]");
		 //mapApp.clearMap();
	     mapApp.GetFxryDZWL(ORG_NAME.text());
		 searchSta(orgId);
		 },500);
		//设置初始选中项样式
        $(".dwjk-box5 li:[id='" + orgId + "']").addClass("selected");
        //初始化详情链接
		  $.ajax({
				url:"${ctx}/data/jggl/get.action",
				data:"id="+orgId,
				success:function(data){
					$("#jkxq").attr("href",href + orgId+"&x="+(data.locX==null?"":data.locX)+"&y="+(data.locY==null?"":data.locY)+"&level=10");
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
							var url="${ctx }/data/dwjk/locationDetail.action?x="
							+ datas[i][5]
							+ "&y="
							+ datas[i][6]
							+ "&level=11&orgId="
							+ datas[i][0];
							//alert(url);
							var str = "<div class='dwjk-box1'" + 
										" id='div" + datas[i][0]+ "'"							           
										+ ")'"
										+ "><span>"
										+ c1
										+ "</span><span>"
										+ c2
										+ "</span><span>"
										+ c3
										+ "</span>"
										+ "</div><div style='position:relative;bottom:-55px;'><b><a onclick='parent.change(\""+url+"\");' href='javascript:void(0);'>"
										+ datas[i][1]
										+ "</a></b></div>";
										var detail=null;
										//var detail=searchSfs(datas[i][0]);
										var marker=mapApp.addMarkerBylayer(datas[i][5],datas[i][6],str,detail);
										//fxryList[datas[i][0]]=marker;
										//icon.imageDiv.innerHTML = str;
										//var marker = addMarker(datas[i][5],
										//				datas[i][6], icon,
										//				null, false, true);
									   //marker.icon.rid = datas[i][0];
									   //marker.events.register("click", marker, searchSfs);
									   //marker.events.register("mouseout", marker, onMouseout);									   
							}
						});
					var title = "<div class='divZJTJ'>在矫人员&nbsp;:" + count + "人</div><div class='divJGTJ'>电子监管人员&nbsp;:" + epCount + "人</div><div class='divBJTJ'>未处理报警&nbsp;:" + alarmCount + "条</div>";
					$("#qxsfjtj").html(title);
					var ORG_NAME= $("[id="+orgId+"]");				
					var maptitle = "<div class='divtitle'><b>"+ORG_NAME.text()+"各司法所社区服刑人员统计</b></div>";
					$("#maptitle").html(maptitle);
				}
			});
	}
	function change(url)
	{
		parent.location.href=url;
	}
	function onMouseout(evt)
	{
		$("#hint").hide();
	}
	function searchSfs(rid) {	
		var str = "";		
		$.ajax({
					type : "POST",
					async : false,
					url : "${ctx }/data/alarm/getSfsSta.action",
					data : "cCode=" +rid,
					dataType : "json",
					success : function(datas) {
						if (datas != null) {
						    str = "<table><tr><th></th><th><img src='${ctx}/images/t4.png' width='20' height='20'/></th><th><img src='${ctx}/images/t5.png' width='20' height='20'/></th><th><img src='${ctx}/images/t6.png' width='20' height='20'/></th></tr>";
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
					}
		});
		return str;
	}

</script>
</body>
</html>
