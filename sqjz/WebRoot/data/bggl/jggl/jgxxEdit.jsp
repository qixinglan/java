<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>矫正机构信息编辑</title>
<%@ include file="/common/commonjs.jsp"%>
<link rel="stylesheet" href="${ctx}/data/jzgl/dagl/style.css" />
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"bggl",second:"org"});
		var btn = $('<input type="button" class="micon-expand"/>');
		btn.click(triggerCollapse);
		$(".container-form-title").append(btn);
		$("#orgType")
				.text($.dictionary.formatter('JGLX')($("#orgType").text()));
		var status = $("#orgStatus").text();
		$("#orgStatus").text("");
		var orgId = document.getElementById("entity.orgId").value;
		$.dictionary.combobox("#orgStatus", "orgStatus", "JGZT", {
			allowBlank : false,
			defValue : status,
			fieldClass:"search-form-field"
		});
		$.dictionary.combobox("#zwzj", "duty", "ZWZJ", {
			allowBlank : true,
			emptyText : '全部',
			defValue : status,
			fieldClass:"search-form-field"
		});
		//表格初始化
		$("#mainGrid")
				.jqGrid(
						{
							url : '${ctx}/data/jggl/perSearch.action?orgId='
									+ orgId,
							mtype : "POST",
							datatype : 'json',
							colModel : [
									{
										name : 'id',
										index : 'id',
										label : 'id',
										hidedlg : false,
										hidden : true
									},
									{
										name : 'name',
										index : 'name',
										label : '姓名',
										width : 100,
										sortable:true,
										align : 'center'
									},
									{
										name : 'sex',
										index : 'sex',
										label : '性别',
										width : 30,
										sortable:true,
										formatter : 'dictionary',
										formatoptions : {
											code : 'XB'
										},
										align : 'center'
									},
									{
										name : 'org.cname',
										index : 'org.cname',
										label : '工作单位',
										width : 100,
										sortable:true,
										align : 'center'
									},
									{
										name : 'oldorg',
										index : 'oldorg',
										label : '原工作单位',
										width : 100,
										sortable:true,
										align : 'center'
									},{
										name : 'workPhone',
										index : 'workPhone',
										width : 100,
										sortable:true,
										label : '办公电话',
										 maxlength:11,
										align : 'center'
									},
									{
										name : 'phone',
										index : 'phone',
										width : 100,
										sortable:true,
										label : '手机',
										 maxlength:11,
										align : 'center'
									},
									{
										name : 'state',
										index : 'state',
										label : '操作',
										width : 80,
										align : 'center',
										sortable : false,
										formatter : function(value, opts, rwdat) {
											var actions = "<a href='javascript:personView(\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>查看</a>";
											actions += "<a href='javascript:openCreate(\"edit\",\""
													+ rwdat.id
													+ "\");' title='' class='a-style'>编辑</a>";
											return actions;
										}
									} ],
							multiselect : true,
							rownumbers : true,
							sortname : 'org.orgId,name',
							sortorder : "asc,asc",
							pager : '#mainGridPager'
						});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	});
	function triggerCollapse() {
		var btn = this;
		var body = $('.container-form-body', $(btn).parent().parent());
		if (body.attr("collapse") == "true") {
			body.show();
			body.attr("collapse", "false");
			$(btn).removeClass("micon-collapsed");
			$(btn).addClass("micon-expand ");
		} else {
			body.hide();
			body.attr("collapse", "true");
			$(btn).removeClass("micon-expand");
			$(btn).addClass("micon-collapsed");
		}
	}
	
	function save() {
		$.fields.checkForm("#jgxxForm", {
			yes : function(data) {
				back();
			},
			mask : true,
			title : '机构信息编辑',
			url : "${ctx}/data/jggl/save.action"
		});
	}
	function back() {
		window.location = "${ctx}/data/jggl/list.action";
	}
	function openAdd(orgId) {
		var id = arguments[0] || ("per" + new Date().getTime());
		$
				.dialog({
					id : id,
					width : '1000px',
					height : '410px',
					fixed : true,
					lock : true,
					cover : true,
					title : '机构人员新增',
					content : 'url:${ctx}/data/jggl/perAdd.action?orgId='
							+ orgId,
					cancelVal : '关闭',
					okVal : '保存',
					ok : function() {
						var doc = $("iframe[name='" + id + "']").get(0).contentDocument;
						return $("iframe[name='" + id + "']").get(0).contentWindow.$.fields.checkForm($(doc
								.getElementById("personAdd")), {
							yes : function(data) {
								$("#mainGrid").trigger("reloadGrid");
							},
							mask : true,
							title : '机构人员新增',
							url : $(doc.getElementById("personAdd")).attr(
									"action")
						});
					},
					cancel : true
				});
	}
	function openEdit(personId) {
		var id = arguments[0] || ("per" + new Date().getTime());
		$
				.dialog({
					id : id,
					width : '1000px',
					height : '410px',
					fixed : true,
					lock : true,
					cover : true,
					title : '机构人员编辑',
					content : 'url:${ctx}/data/jggl/perEdit.action?id='
							+ personId,
					cancelVal : '关闭',
					okVal : '保存',
					ok : function() {
						var doc = $("iframe[name='" + id + "']").get(0).contentDocument;
						return $("iframe[name='" + id + "']").get(0).contentWindow.$.fields.checkForm($(doc
								.getElementById("personAdd")), {
							yes : function(data) {
								$("#mainGrid").trigger("reloadGrid");
							},
							mask : true,
							title : '机构人员编辑',
							url : $(doc.getElementById("personAdd")).attr(
									"action")
						});
					},
					cancel : true
				});
	}
	function openView(personId) {
		var id = arguments[0] || ("pv" + new Date().getTime());
		$.dialog({
			id : id,
			width : '1000px',
			height : '410px',
			fixed : true,
			lock : true,
			cover : true,
			title : '机构人员查看',
			content : 'url:${ctx}/data/jggl/perView.action?id=' + personId,
			cancelVal : '关闭',
			cancel : true
		});
	}
	function openDel() {
		var grid = $("#mainGrid");
		var ids = grid.getGridParam("selarrrow");
		var str = '';
		for ( var id in ids) {
			str += ids[id] + ',';
		}
		str = str.substring(0, str.length - 1);
		if (ids == null || ids.length < 1) {
			$.dialog.alert("请选择需要删除的数据！");
			return false;
		}
		$.dialog.confirm("确认要删除记录吗？", function() {
			$.ajax({
				type : "POST",
				async : false,
				url : "${ctx}/data/rygl/perDel.action",
				data : "perId=" + str
			});
			$("#mainGrid").trigger("reloadGrid");
		});
	}
	function getPosition() {
		var x = $("#locX").attr("value");
		var y = $("#locY").attr("value");
		$.dialog({
			id : 'testID',
			title : '地图标绘',
			content : 'url:${ctx}/data/jggl/map.action?x='+x+'&y='+y,
			cancelVal : '关闭',
			cancel : true,
			ok:function(){
				var form =  $("iframe[name='testID']").get(0).contentDocument;
				var x = form.getElementById("x");
				var y = form.getElementById("y");
				$("#locX").attr("value",x.value);
				$("#locY").attr("value",y.value);
			}
		}).max();
	}
	
	//新建、编辑
	function openCreate(oper,id){
		var read = '${ctx}/data/jggl/perAdd.action?orgId='+ id;
		var title = '机构人员新增';
		if(oper=="edit"){
			read = '${ctx}/data/jggl/perView.action?id='+ id;
			title = '机构人员编辑';
		}
		var orgType="${orgType}";
		var filter="";
		if(orgType=="1"){
			filter="6,7,8,9,10,11";
		}else if(orgType=="2"){
			filter="6,7,9,10,11";
		}else if(orgType=="3"){
			filter="1,2,3,4,5,6,7,8";
		}else if(orgType=="10"){
			filter="1,2,3,4,5,8,9,10,11";
		}
		$.container.popup({
			title: title,
			read: read,
			save: '${ctx}/data/jggl/perSave.action',
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fieldCols: 2,
			fields:[{text:"orgId",type:'hidden',name:'org.orgId',},
					{text:"orgType",type:'hidden',name:'orgType',value:'${orgType}'},
					{text:"id",type:'hidden',name:'id',},
					{text:"姓名",type:'text',name:'name',maxlength:20,validater:'name',required: true,allowBlank:false},
					{text: "性别", type:'dict_radio',name: 'sex', code:'XB',defValue:'1', required: true},
					
					{text: "出生日期", type:'datepicker',name: 'birthday', required: true},
					{text: "政治面貌", type:'dict_combobox',name: 'polityvisage', code:'ZZMM',emptyText:'请选择',defValue:'12', required: true},
					
					{text: "学历", type:'dict_combobox',name: 'degree', code:'WHCD',emptyText:'请选择',defValue:'7', required: true},
					{},
					
					{text:"现工作单位及部门",type:'display',name:'org.cname'},
					{text:"原工作单位",type:'text',name:'oldorg',maxlength:80,validater:'name',required: true,allowBlank:false},
					
					{text: "人员类别", type:'dict_combobox',name: 'persontype', code:'RYLB',emptyText:'请选择',defValue:'1', required: true},
					{text: "职务职级", type:'dict_combobox',name: 'duty', code:'ZWZJ',emptyText:'请选择',defValue:'1',filter:filter, required: true},
					
					{text:"办公电话",type:'text',name:'workPhone',maxlength:11,validater:'phone',required: true,allowBlank:false},
					{text:"手机",type:'text',name:'phone',maxlength:11,validater:'telephone',required: true,allowBlank:false},
					
					{text:"备注",type:'textarea',name:'description',colspan:2,required: false,allowBlank:false}
					],
			yes : function(data){
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		},{width: "800px",height: "400px",okVal:'保存'});
	}
	//查看
	function personView(id){
		$.container.popup({
			title: '机构人员查看',
			read: '${ctx}/data/jggl/perView.action?id='+ id,
			fieldCls:{
				labelCls : 'form-td-label-120'
			},
			fieldCols: 2,
			fields:[
					{text:"姓名",type:'display',name:'name',maxlength:20,validater:'name'},
					{text: "性别", type:'display',name: 'sex', formatter:$.dictionary.formatter('XB', '不详')},
					
					{text: "出生日期", type:'display',name: 'birthday'},
					{text: "政治面貌", type:'display',name: 'polityvisage', formatter:$.dictionary.formatter('ZZMM', '不详')},
					
					{text: "学历", type:'display',name: 'degree', formatter:$.dictionary.formatter('WHCD', '不详')},
					{},
					
					{text:"现工作单位及部门",type:'display',name:'org.cname'},
					{text:"原工作单位",type:'display',name:'oldorg',maxlength:80,validater:'name'},
					
					{text: "人员类别", type:'display',name: 'persontype', formatter:$.dictionary.formatter('RYLB', '不详')},
					{text: "职务职级", type:'display',name: 'duty', formatter:$.dictionary.formatter('ZWZJ', '不详')},
					
					{text:"办公电话",type:'display',name:'workPhone',maxlength:11,validater:'phone'},
					{text:"手机",type:'display',name:'phone',maxlength:11,validater:'telephone'},
					
					{text:"备注",type:'textarea',name:'description',colspan:2,readonly:true}
					],
			yes : function(data){
				$("#mainGrid").trigger("reloadGrid");
				return true;
			}
		},{width: "800px",height: "400px",title: '机构人员查看',cancelVal : '关闭'});
	}
</script>
</head>
<body>
	<%@include file="/data/top.jsp"%>
	<%@include file="/data/left.jsp"%>
	<div class="main">
		<div class="breadcrumb-nav">
			<span>您当前的位置：办公管理 -> 矫正机构管理</span>
		</div>
		<div class="container-top">
			<div class="container-form">
				<div class="container-form-title">
					<label class="container-form-title-text">机构基本信息</label>
				</div>
				<div class="container-form-body">
					<form action="" id="jgxxForm">
						<input type="hidden" id="entity.orgId" name="entity.orgId"
							value="${entity.orgId}" /> <input type="hidden"
							id="entity.supOrg.orgId" name="entity.supOrg.orgId"
							value="${entity.supOrg.orgId}" />
						<table class="comm-table">
							<tbody>
								<tr>
									<th>机构名称：</th>
									<s:if
										test="#request.entity.orgType==1||request.entity.orgType==2">
										<td colspan="3">${entity.cname}</td>
									</s:if>
									<s:elseif test="#request.entity.orgType==3">
										<td colspan="3">${entity.cname}</td>
									</s:elseif>
									<s:else>
										<td>${entity.cname}</td>
										<th>机构类型：</th>
										<td id="orgType">${entity.orgType}</td>
									</s:else>
									<th>联系人：</th>
									<td><input type="text" class="search-form-field" maxlength="20"
										id="entity.contact" name="entity.contact"
										value="${entity.contact}" class="search-form-field"/></td>
								</tr>
								<tr>
									<th>地址：</th>
									<td><input type="text" class="search-form-field" maxlength="80"
										id="entity.address" name="entity.address"
										value="${entity.address}" class="search-form-field"/></td>
									<th>邮政编码：</th>
									<td><input type="text" class="search-form-field" maxlength="6"
										id="entity.postalCode" name="entity.postalCode"
										value="${entity.postalCode}" validater="int"/></td>
									<th>固定电话：</th>
									<td><input type="text" class="search-form-field" maxlength="11"
										id="entity.phone" name="entity.phone" value="${entity.phone}" /></td>
								</tr>
								<tr>
									<th>传真：</th>
									<td><input type="text" class="search-form-field" maxlength="20"
										id="entity.fax" name="entity.fax" value="${entity.fax}" /></td>
									<th>机构状态：</th>
									<td id="orgStatus">${entity.orgStatus}</td>
									<th>上级机构：</th>
									<td>${entity.supOrg.cname}</td>
								</tr>
								<tr>
									<th>位置坐标经度：</th>
									<td><input type="text" class="search-form-field"
										id="locX" name="entity.locX" value="${entity.locX}" /></td>
									<th>位置坐标纬度：</th>
									<td><input type="text" class="search-form-field"
										id="locY" name="entity.locY" value="${entity.locY}" /></td>
									<th>手动标绘坐标:</th>
									<td><input type="button"
										class="bttn bicon-preview" id="dtbh01" value="地图标绘"
										onclick="javascript:getPosition();" />
									</td>
								</tr>
								<tr>
									<th>机构设置情况：</th>
									<td colspan="5"><textarea id="entity.description"
											name="entity.description" style="resize:none;width:95%;padding-left: 6px !important;padding-right: 6px !important;">${entity.description}</textarea></td>
								</tr>
							</tbody>
						</table>
					</form>
					<div class="buttonArea operation">
						<input type="button" id="btnSave" class="bttn bicon-save"
							value="保存" onclick="javascript:save();" /> <input type="button"
							id="btnReturn" class="bttn bicon-return" value="返回"
							onclick="javascript:back();" />
					</div>
				</div>
			</div>
			<div class="container-form">
				<div class="container-form-title">
					<label class="container-form-title-text">机构成员信息</label>
				</div>
				<div id="searchDiv" class="container-form-body">
					<table class="search-table">
						<tbody>
							<tr>
								<th>姓名</th>
								<td><input type="text" name="name" searchType="cn" maxlength="20" class="search-form-field"/></td>
								<th>原工作单位</th>
								<td><input type="text" name="oldorg" searchType="cn" maxlength="80" class="search-form-field"/></td>
								<td><input type="button" id="searchBtn"
									class="bttn bicon-search" value="查询" /></td>
									<td><input type="button" id="resetBtn"
									class="bttn bicon-reset" value="重置" /></td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="container-form-body">
					<div class="grid-container">
						<div id="" class="buttonArea operation">
							<input type="button" id="btnAdd" class="bttn bicon-add"
								value="新增" onclick="javascript:openCreate('add','${entity.orgId}');" /> <input
								type="button" id="btnDel" class="bttn bicon-delete" value="删除"
								onclick="javascript:openDel();" />
						</div>
						<table id="mainGrid"></table>
						<div id="mainGridPager"></div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="/data/leftEnd.jsp"%>
	<%@include file="/data/bottom.jsp"%>
</body>
</html>