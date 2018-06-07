<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- <%@taglib url='http://java.sun.com/jstl/core' prefix='c'%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子监管设备管理</title>
<%@ include file="/common/commonjs.jsp"%>
<style>
 .searchStyle{
        	width:140px !important;
        	vertical-align: top !important;
        }
</style>
<script language="javascript" src="${ctx}/data/dzjg/sbgl/jgsb/findSim.js" type="text/javascript"></script>
<script type="text/javascript">
		var status='<%=request.getParameter("status")%>';
		var useUnit='<%=request.getParameter("useUnit")%>';
		var emptyTextStatus="全部";
		var emptyTextUseUnit="全部";
		if(useUnit!='null'&&useUnit!=''){
			emptyTextUseUnit=$.organization.formatter()(useUnit);
		}
		if(status!='null'&&status!=''){
			emptyTextStatus=$.dictionary.formatter('DZJGSBZT')(status);
		}
	$(function() {
		$("#mynav0").removeClass("navnew-c");
		$("#dzjg").addClass("navnew-c");
		//alert(${ctx});
		//alert(ORG_TYPE);
		//区县司法局不能新增删除
		if(ORG_TYPE=="qxsfj"){
			$("#btnAdd").hide();
			$("#btnDel").hide();
			$("#btnFile").hide();
		}
		if(emptyTextStatus=='全部'){
			$.dictionary.combobox("#status", "status", "DZJGSBZT", {
				allowBlank : true,
				emptyText : emptyTextStatus,
				attr:{searchType: "eq"},
				fieldClass:"search-form-field"
			});
		}else{
			$("#status").append("<input value='"+emptyTextStatus+"' readonly='readonly'></input>");
		}
		if(emptyTextUseUnit=='全部'){
			//使用单位
			$.organization.combobox("#useUnit", "useUnit", ${user.wunit.bm}, {
				allowBlank : true,
				level : 10,
				//showRoot : true,
				showItself : true,
				emptyText : emptyTextUseUnit,
				attr:{searchType: "eq"},
				notShowType:"4,5,6,7,8,9",
				fieldClass:"search-form-field"
			});
		}else{
			$("#useUnit").append("<input value='"+emptyTextUseUnit+"' readonly='readonly'></input>");
		}
		//购买单位
		$.organization.combobox("#buyUnit", "buyUnit", ${user.wunit.bm}, {
			allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"3,4,5,6,7,8,9",
			fieldClass:"search-form-field"
		});
		//表格初始化
		$("#mainGrid").jqGrid(
						{	
							rowNum : 10,
							url : '${ctx}/data/sbgl/jgsb/search.action?status='+status+'&useUnit='+useUnit,
							colNames : [ '', '设备编号','购买单位', '购买日期','使用单位（司法局）','使用单位（司法所）','SIM卡号','状态',
											'操作'],
											
								colModel : [
											{
												name : 'id',
												index : 'id',
												hidedlg : false,
												hidden : true,
												key : true
											},
											{
												name : 'deviceNumber',
												index : 'deviceNumber',
												width : 30,
												align : 'center'
											},
											{
												name : 'buyUnit',
												index : 'buyUnit',
												width : 40,
												align : 'center',
												formatter : function(value, opts, rwdat) {//value 本单元数据    rwat  本行对象
													return $.organization.formatter()(rwdat.buyUnit);
												}
											},
											{
												name:'buyDate',
												index:'buyDate',
												formatter : "date",
												width : 40,
												align : 'center',
												formatoptions : {
													newformat : 'Y-m-d'
												}
											},
											{
												name : 'useUnit',
												index : 'org',
												width : 80,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.useUnit);
												}
											},
											{
												name : 'useSfsUnit',
												index : 'sfsOrg',
												width : 80,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													return $.organization.formatter()(rwdat.useSfsUnit);
												}
											},
											{
												name : 'phoneNumber',
												index : 'phoneNumber',
												width : 200,
												maxlength:11,
												align : 'center',
												formatter : function(value, opts, rwdat) {
													var status=$.dictionary.formatter('DZJGSBZT')(rwdat.status);
													if(status=="作废"){
														return"";
													}
													if(ORG_TYPE=='qxsfj'){//区县司法局
														return value;
													}
													return "<div class='a-style' id='pn"+rwdat.id+"'>"+value+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='javascript:changeSimNum(\""
													+ rwdat.id+ "\",\""+rwdat.useUnit
													+ "\");' title='' class='a-style'>更改</a></div>";
													
												}
												
											},
											{
											
												name : 'status',
												index : 'status',
												width : 60,
												shrinkToFit : false,
												fixed : true,
												align : 'center',
												formatter : function(value, opts, rwdat){
													return $.dictionary
													.formatter('DZJGSBZT')(rwdat.status);
													/* status=rwdat.status;
													if(status=='0'){
														return '作废';
													}if(status=='1'){
														return '未用';
													}
													if(status=='2'){
														return '已用';
													}
													if(status=='3'){
														return '维修';
													}if(status=='4'){
														return '已解除';
													} */
												}
												},
											{
												name : 'cz',
												index : 'cz',
												width : 200,
												align : 'center',
												sortable : false,
												fixed : true,
												formatter : function(value, opts, rwdat) {
													if(ORG_TYPE=='qxsfj'){//区县司法局
														 return  "<a href='${ctx}/data/sbgl/jgsb/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>";
													}
													var status=$.dictionary.formatter('DZJGSBZT')(rwdat.status)
													if(status=='作废'){
														return  "<a href='${ctx}/data/sbgl/jgsb/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>";
													}
													if(status=='未用'||status=='已用'||status=='已解除'){
														return "<a href='${ctx}/data/sbgl/jgsb/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>"+
														"<a  href='javascript:statusFix(\""+ rwdat.id+ "\",\""+ rwdat.status+ "\");'class='a-style'>维修</a>"+
														"<a href='javascript:statusBad(\""+ rwdat.id+ "\",\""+ rwdat.status+ "\");' class='a-style'>作废</a>";
													}
													if(status=='维修'){
														return  "<a href='${ctx}/data/sbgl/jgsb/view.action?deviceNumber="+
														rwdat.deviceNumber+
														"' title='' target='_blank' class='a-style'>查看</a>"+
														"<a href='javascript:statusBad(\""+ rwdat.id+ "\",\""+ rwdat.status+ "\");' class='a-style'>作废</a>"+
														"<a href='javascript:statusReturn(\""+ rwdat.id+ "\");'class='a-style'>返回</a>";
													}
											}
										}],
							
							multiselect : true,
							//rownumbers : true,
							pager : '#mainGridPager',
							sortname:'buyDate',
							sortorder:'desc'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		
	});
	//修改卡号
	 var oselect;
	function changeSimNum(){
		var id=arguments[0];
		var useUnit=arguments[1];
		oselect=$("#pn"+id+"").html();
		$.findSim.load("${ctx }/data/sbgl/jgsb/findSim.action?useUnit="+useUnit);
		if($.findSim.getDict('NOUSEDSIM').length==0){
			$.dialog.alert("无可用sim卡");
			return false;
		}
		$("#phoneNumber").empty();
		$("#pn"+id+"").html("<form id ='changePN' action='${ctx }/data/sbgl/jgsb/changeSimNum.action' method='post'><span id='phoneNumber'></span>"+
				"&nbsp;&nbsp;"+"<input type='hidden' name='id' value='"+id+"'/>"+
				"<input style='margin-bottom:2px' type='button' value='提交' onclick='sub()'>&nbsp;&nbsp;"+
				"<input type='button' style='margin-bottom:2px' value='取消' onclick='nosub(\""+id+"\")'></form>");
		$.findSim.combobox("#phoneNumber","phoneNumber","NOUSEDSIM",{
			width:'30%',
			allowBlank : false, 
			emptyText : '',
			defValue: '',
			attr:{searchType: "cn"}
		});
		/* $.ajax({
			type:"post",
			url:"${ctx}/data/sbgl/jgsb/findSim.action",
			data:"useUnit="+useUnit,
			success:function(data){
				if(data.length==0){
					$.dialog.alert("无可用sim卡");
					return false;
				}
				var select="<select id='phoneNumer' style='width:150px;'  name='phoneNumber' required='required'>"
				for(var i=0;i<data.length;i++){
					select+="<option value="+data[i].phoneNumber+">"+data[i].phoneNumber+"</option>"
				}
				select+="</select>";
				oselect=$("#pn"+id+"").html();
				$("#pn"+id+"").html("<form id ='changePN' action='${ctx }/data/sbgl/jgsb/changeSimNum.action' method='post'>"+select+
						"&nbsp;&nbsp;"+"<input type='hidden' name='id' value='"+id+"'/>"+
						"<input style='margin-bottom:10px' type='button' value='提交' onclick='sub()'>&nbsp;&nbsp;"+
						"<input type='button' style='margin-bottom:10px' value='取消' onclick='nosub(\""+id+"\")'></form>");
			}
		}) */
	}
	function nosub(id){
		$("#pn"+id+"").html(oselect);
	}
	function sub(){
		var form=$("#changePN");
		var ajax_option = {
				url:form.attr("action"),
				success:function(data){
					//$.dialog.alert(data.msg);
					$("#mainGrid").trigger("reloadGrid");
				}
		}
		form.ajaxSubmit(ajax_option);//发送ajax请求
	}
	function statusFix(){
		var id=arguments[0];
		var status=arguments[1];
		var fix=$.dictionary.getCode('DZJGSBZT','维修');
		var used=$.dictionary.getCode('DZJGSBZT','已用');
		if(status==used){
			$.dialog.alert('请先解绑设备');
			return ;
		}
		
		$.ajax({
			type:"post",
			url:"${ctx}/data/sbgl/jgsb/changeStatus.action",
			data:"id="+id+"&status="+fix,
			success:function(){
				$("#mainGrid").trigger("reloadGrid");
			}
		})
	}
	function statusBad(){
		var id=arguments[0];
		var status=arguments[1];
		var bad=$.dictionary.getCode('DZJGSBZT','作废');
		var used=$.dictionary.getCode('DZJGSBZT','已用');
		if(status==used){
			$.dialog.alert('请先解绑设备');
			return ;
			
		}
		$.dialog.confirm('确认要作废该设备吗?',function(){
			$.ajax({
				type:"post",
				url:"${ctx}/data/sbgl/jgsb/changeStatus.action",
				data:"id="+id+"&status="+bad,
				success:function(){
					$("#mainGrid").trigger("reloadGrid");
				}
			})
		});
		
	}
	function statusReturn(){
		var id=arguments[0];
		var notUsed=$.dictionary.getCode('DZJGSBZT','未用');
		$.ajax({
			type:"post",
			url:"${ctx}/data/sbgl/jgsb/changeStatus.action",
			data:"id="+id+"&status="+notUsed,
			success:function(){
				$("#mainGrid").trigger("reloadGrid");
			}
		})
	}
	//新增电子监管设备
	function openEdit() {
		var id = arguments[0] || ("e" + new Date().getTime());
		$.dialog({
					id : id,
					width: '850px', 
					height: '350px', 
					title : '电子监管设备信息新增',
					content : 'url:${ctx }/data/sbgl/jgsb/input.action',
					ok : function() {
						var success = true;
						var form = $("iframe[name='" + id + "']").get(0).contentDocument
								.getElementById("saveForm");
							form = $(form);
							//做验证检查是否为空
							if (!$("iframe[name='" + id + "']").get(0).contentWindow.$.fields.validateForm(form)){
								return false;
							}
							var ajax_option = {
									url:form.attr("action"),
									success:function(data){
										$.dialog.alert(data.msg);
										$("#mainGrid").trigger("reloadGrid");
									}
							}
							form.ajaxSubmit(ajax_option);//发送ajax请求
							
					},
					okVal : '保存',
					cancelVal : '取消',
					cancel : true
				});
	}
	//查看——弹出框样式
	function openView() {
		//arguments为参数数组，【0】设备id
		var id = arguments[0] || ("v" + new Date().getTime());
		$.dialog({
			id : id,
			width: '1300px', 
			height:'auto', 
			title : '电子监管设备信息查看',
			content : 'url:view.action?id='+ id,
			cancelVal : '关闭',
			cancel : true
		});
	}
	function openImport() {
		$.container.popup({
			title: '电子监管设备信息导入',
			save: 'import.action',
			fields:[
				{text:"电子监管设备信息",
				 type:'file',
				 name:'importFile',
				 url:"${ctx}/data/common/upload.action",
				 multiple:true,
				 accept:"application/vnd.ms-excel",
				 spath:"jgsb/import"
				}
			],
			yes : function(){
				$.dialog.alert("导入成功!",null,this);
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		},{width: "450px"});
	}
	function openExcel() {
		var grid = $("#mainGrid").getGridParam('postData') || {};
		var data = {};
		for (var i in grid){
			data["jqgrid."+i] = grid[i];
		}
		var t = data["jqgrid.cols"].split(",").slice(1);
		t.pop();
		data["jqgrid.cols"]=t.join(",");
		data["jqgrid.cols"] = data["jqgrid.cols"].slice(0);
		var url = "${ctx }/data/sbgl/jgsb/export.action?" + $.param(data);
		$("#downloadFrame").attr("src",url);
	}
	function openDel() {
		var grid = $("#mainGrid");
		var ids = grid.getGridParam("selarrrow");
		var usedIds = [];
		for ( var id in ids) {
			if (grid.getRowData(ids[id]).status === "已用"){
				usedIds.push(grid.getRowData(ids[id]).deviceNumber);
			}
		} 
		if (usedIds.length != 0){
			$.dialog.alert("设备编号为" + usedIds.join(",") + "的状态为已用，不可删除。",false,this);
			return false;
		}
		
		if (ids == null || ids.length < 1) {
			$.dialog.alert("请选择删除数据！",false,this);
			return false;
		}
		$.dialog.confirm('你确认要删除记录吗？',function (){
					$.ajax({
						type : "POST",
						async : false,
						url : "${ctx }/data/sbgl/jgsb/del.action",
						data : {id:ids.join(",")},
					});
					$("#mainGrid").trigger("reloadGrid");
				}, function(){
					
				});
	}
</script>
</head>

<body>
	<%@include file="/data/top.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：电子监管&nbsp;-&gt;&nbsp;设备管理&nbsp;-&gt;&nbsp;电子监管设备信息管理</span>
		</div>
		<div class="container-top">

			<table class="search-table" id="searchDiv">
				<tbody>
					<tr>
						<th>购买单位：</th>
						<td id="buyUnit"></td>
						<th>当前使用单位：</th>
						<td id="useUnit"></td>
						<th>设备状态：</th>
						<td id="status"></td>
						<td><input id="searchBtn" type="button" 
							 class="bttn bicon-search" value="查询" /></td>
					</tr>
					<tr>
					<!-- searchType  sql：eq =  cn  like   ge >  le  <-->
						<th>设备编号：</th>
						<td><input type="text" name="deviceNumber" searchType="eq" class="search-form-field"/></td>
						<!-- <th>主机编号：</th>
						<td><input type="text" name="deviceNumber" searchType="cn" class="search-form-field"/></td> -->
						<th>主机电话号码：</th>
						<td><input type="text" name="phoneNumber" searchType="cn" class="search-form-field"/></td>
						<th>购买日期：</th>
						<td ><input id="bjsj1" class="Wdate" type="text" style="width: 85px"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" maxlength="16"
							name="buyDate" searchType="ge" />至<input class="Wdate" type="text" style="width: 85px" id="bjsj2" 
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" maxlength="16"
							name="buyDate" searchType="le" /></td>
						<td><input id="resetBtn" type="button"
							class="bttn bicon-reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--列表-->
		<div class="buttonArea operation">
			<input type="button" name="btnAdd" id="btnAdd" class="bttn bicon-add" value="新增"
				onclick="openEdit()" /> <input type="button" name="btnDel" id="btnDel" 
				class="bttn bicon-delete" onclick="javascript:openDel();" value="删除" />
			<input type="button" name="btnFile" class="bttn bicon-file" id="btnFile"
				value="导入" onclick="javascript:openImport();" /> <input type="button"
				name="btnExcel" class="bttn bicon-excel"
				onclick="javascript:openExcel();" value="导出" />
		</div>
		<div id="search"></div>
		<div class="container-bottom">
			<table id="mainGrid">
			</table>
			<div id="mainGridPager"></div>
		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
	<iframe id="downloadFrame" style="display:none"/>
</body>
</html>
