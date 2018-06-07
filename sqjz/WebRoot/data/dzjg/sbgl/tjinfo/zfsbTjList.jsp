<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>移动执法终端信息管理</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script language="javascript" src="${ctx}/data/dzjg/sbgl/ydzfzdxxgl/findSim.js" type="text/javascript"></script>
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
				//区县司法局不能新增删除
				if(ORG_TYPE=="qxsfj"){
					$("#btnAdd").hide();
					$("#btnDel").hide();
				}
				if(emptyTextStatus=='全部'){
					$.dictionary.combobox("#status", "status", "ZFSBZT", {
						allowBlank : true,
						emptyText :  emptyTextStatus,
						attr:{searchType: "eq"},
						fieldClass:"search-form-field"
					});
				}else{
					$("#status").append("<input value='"+emptyTextStatus+"' readonly='readonly'></input>");
				}
				if(emptyTextUseUnit=='全部'){
					//当前使用单位
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
				$("#mainGrid").jqGrid({
					
					url		    : '${ctx}/data/sbgl/ydzf/search.action?status='+status+'&useUnit='+useUnit,
					colNames	: [
					    '','设备编号',  '购买单位', '购买日期','使用单位（司法局）','使用单位（司法所）','SIM卡号','状态','操作'
					],
					colModel	: [
							{
								name	:'id',
								index	:'id',
								hidedlg	:false,
								hidden	:true,
								key : true
							},
					        {
						        name	: 'deviceNumber',
						        index	: 'deviceNumber',
						        searchType : 'cn',
						        sortable: true,
						        width	: 50,
						        align	: 'center'
					        }, 
					        /*{
						        name	: 'equtype',
						        index	: 'equtype',
						        searchType : 'eq',
						        width	: 30,
						        formatter :'dictionary',
								formatoptions:{code:'SBXH'},
						        align	: 'center'
					        },*/
					        {
						        name	: 'buyUnit',
						        index	: 'buyUnit',
						        width	: 50,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return $.organization.formatter()(rwdat.buyUnit);
								}
					        },
					        {
						        name	: 'buyDate',
						        index	: 'buyDate',
						        width	: 50,
						        align	: 'center',
						        formatter : "date",
								formatoptions : {
									 newformat: 'Y-m-d'
								}
					        }, 
					        {
						        name	: 'useUnit',
						        index	: 'useUnit',
						        searchType : 'cn',
						        width	: 70,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return $.organization.formatter()(rwdat.useUnit);
								}
					        },
					        {
								name : 'useSfsUnit',
								index : 'sfsOrg',
								width : 70,
								align : 'center',
								formatter : function(value, opts, rwdat) {
									return $.organization.formatter()(rwdat.useSfsUnit);
								}
							},
					        {
					        	name:'phoneNumber',
					        	index:'phoneNumber',
					        	align:'center',
					        	width : 200,
				        		formatter : function(value, opts, rwdat) {
				        			var status=$.dictionary.formatter('ZFSBZT')(rwdat.status);
				        			if(status=="作废"){
										return"";
									}
				        			if(ORG_TYPE=='qxsfj'){//区县司法局
										return value;
									}
									return "<div  id='pn"+rwdat.id+"'>"+value+"&nbsp&nbsp&nbsp<a href='javascript:changeSimNum(\""
									+ rwdat.id+ "\",\""+rwdat.useUnit
									+ "\");' title='' class='a-style'>更改</a></div>";
									
								}
					        },
					        {
						        name	: 'status',
						        index	: 'status',
						        width	: 30,
						        searchType : 'eq',
						        formatter : 'dictionary',
						        formatoptions: {code:'ZFSBZT'},
						        align	: 'center'
					        }, 
					        {
								name : 'cz',
								index : 'cz',
								width : 200,
								align : 'center',
								sortable : false,
								fixed : true,
								formatter : function(value, opts, rwdat) {
									var status=$.dictionary.formatter('ZFSBZT')(rwdat.status);
									if(ORG_TYPE=='qxsfj'){//区县司法局
										return "<a href='${ctx}/data/sbgl/ydzf/view.action?id="+
										rwdat.id+
										"' title='' target='_blank' class='a-style'>查看</a>";
									}
									if(status=='作废'){
										return "<a href='${ctx}/data/sbgl/ydzf/view.action?id="+
										rwdat.id+
										"' title='' target='_blank' class='a-style'>查看</a>";
									}
									if(status=='未用'||status=='已用'){
										return "<a href='${ctx}/data/sbgl/ydzf/view.action?id="+
											rwdat.id+
											"' title='' target='_blank' class='a-style'>查看</a>"+
										"<a  href='javascript:statusFix(\""+ rwdat.id+ "\");'class='a-style'>维修</a>"+
										"<a href='javascript:statusBad(\""+ rwdat.id+ "\");' class='a-style'>作废</a>";
									}
									if(status=='维修'){
										return "<a href='${ctx}/data/sbgl/ydzf/view.action?id="+
										rwdat.id+
										"' title='' target='_blank' class='a-style'>查看</a>"+
										"<a href='javascript:statusBad(\""+ rwdat.id+ "\");' class='a-style'>作废</a>"+
										"<a href='javascript:statusReturn(\""+ rwdat.id+ "\");'class='a-style'>返回</a>";
									}
							}
						}
					],
					sortname:'buyDate',
					sortorder:'desc',
						multiselect : true,
						rownumbers	: true,
					  pager: '#mainGridPager'
				});
			/* 	$("#mainGrid").addSearchForm("#search",{rows:"2"}); */
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});
			//修改卡号
			 var oselect; 
			function changeSimNum(){
				var id=arguments[0];
				var useUnit=arguments[1];
				oselect=$("#pn"+id+"").html();
				$.findSim.load("${ctx }/data/sbgl/ydzf/findSim.action?useUnit="+useUnit);
				if($.findSim.getDict('NOUSEDSIM').length==0){
					$.dialog.alert("无可用sim卡");
					return false;
				};
				$("#phoneNumber").empty();
				$("#pn"+id+"").html("<form id ='changePN' action='${ctx }/data/sbgl/ydzf/changeSimNum.action' method='post'><span id='phoneNumber'></span>"+
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
					url:"${ctx}/data/sbgl/ydzf/findSim.action",
					data:"useUnit="+useUnit,
					success:function(data){
						if(data.length==0){
							$.dialog.alert("无可用sim卡");
							return false;
						}
						var select="<select id='phoneNumer' style='width:150px;'  name='phoneNumber' required='required'>";
						for(var i=0;i<data.length;i++){
							select+="<option value="+data[i].phoneNumber+">"+data[i].phoneNumber+"</option>";
						}
						select+="</select>";
						oselect=$("#pn"+id+"").html();
						$("#pn"+id+"").html("<form id ='changePN' action='${ctx }/data/sbgl/ydzf/changeSimNum.action' method='post'>"+select+
								"&nbsp&nbsp&nbsp"+"<input type='hidden' name='id' value='"+id+"'/>"+
								"<input type='button' style='margin-bottom:10px' value='提交' onclick='sub()'>&nbsp&nbsp"+
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
			function openDel(){
				var grid = $("#mainGrid");
				var ids = grid.getGridParam("selarrrow");
				var usedIds = [];
				for ( var id in ids) {
					if (grid.getRowData(ids[id]).status === "已用")
						usedIds.push(grid.getRowData(ids[id]).deviceNumber);
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
								url : "${ctx }/data/sbgl/ydzf/del.action",
								data : {id:ids.join(",")},
							});
							$("#mainGrid").trigger("reloadGrid");
						}, function(){
							
						});
			}
			//新增
			function openAdd(){
				$.dialog({ 
					id:"add",
				    width: '850px', 
    				height: '350px', 
    				title:'移动执法终端信息新增',
				    content: 'url:${ctx }/data/sbgl/ydzf/add.action', 
				    ok: function(){
				    	var form =$(this).attr("iframe").contentDocument.forms[0];
						form = $(form);
						if (!$(this).attr("iframe").contentWindow.$.fields.validateForm(form)){
							return false;
						}
						var equnumber = form.find("#equnumber").val();
						
						var ajax_option = {
								url:form.attr("action"),
								success:function(data){
									$.dialog.alert(data.msg);
									$("#mainGrid").trigger("reloadGrid");
								}
						}
						form.ajaxSubmit(ajax_option);
    				}, 
    				okVal:'保存',
    				cancelVal: '取消', 
    				cancel: true 
    				
				});
				
			}
			//编辑
			function openEdit(id){
				var checkFlag = false;
				var dg = $.dialog({ 
				    id: 'eidt',
				    width: '850px', 
    				height: '350px', 
    				title:'移动执法终端信息新增/编辑',
				    content: 'url:${ctx }/data/ydzf/add.action?oper=edit&id='+id, 
				    ok: function(){
        			 	var form =$(this).attr("iframe").contentDocument.forms[0];
						form = $(form);
						if (!$(this).attr("iframe").contentWindow.$.fields.validateForm(form)){
							return false;
						}
						if(form.find("#useTime").val()==""&&form.find("#orgId").val()!=""){
							$(this).attr("iframe").contentWindow.focError(form.find("#useTime"),"填写使用时间");
							return false;
						}
						if(form.find("#useTime").val()!=""&&form.find("#orgId").val()==""){
							$(this).attr("iframe").contentWindow.focError(form.find("#orgId"),"填写领用单位");
							return false;
						}
						/*var equnumber = form.find("#equnumber").val();
						$.ajax({
							   type: "POST",
							   async:false,
							   url: "${ctx }/data/ydzf/check.action",
							   data: "id ="+form.find("#id").val()+"&equnumber="+equnumber,
							   success:function(data){
								   checkFlag = data;
							   }
							 });
						if(!checkFlag){
							$.dialog.alert("设备编号："+equnumber+"已存在！");
							return false;
						}*/
						var ajax_option = {
								url:form.attr("action"),
								success:function(data){
									$("#mainGrid").trigger("reloadGrid");
								}
						};
						form.ajaxSubmit(ajax_option);
    				}, 
    				okVal:'保存',
    				cancelVal: '取消', 
    				cancel: true 
				});
			}
			//导出
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
				var url = "${ctx }/data/sbgl/ydzf/export.action?" + $.param(data);
				$("#downloadFrame").attr("src",url);
			}
			//导出
			/* function openExcel(){
				var colNames = $("#mainGrid").getGridParam("colNames");
				var sortName = $("#mainGrid").getGridParam("sortName");
				var url="${ctx }/data/ydzf/excel.action?colNames="+colNames+"&sortName="+sortName;
				if ($("#downloadcsv").length <= 0)
					$("body")
							.append(
									"<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
				$("#downloadcsv").attr("src", url);
			} */
			//查看
			function openView(id){
				$.dialog({ 
				    id: id,
				    width: '1300px', 
    				height: 'auto', 
    				title:'移动执法终端信息查看',
    				//oper : 'view',
				    content: 'url:${ctx }/data/sbgl/ydzf/view.action?id='+id, 
    				cancelVal: '关闭', 
    				cancel: true 
				});
			}
			function statusFix(){
				var id=arguments[0];
				var fix=$.dictionary.getCode('ZFSBZT','维修');
				$.ajax({
					type:"post",
					url:"${ctx}/data/sbgl/ydzf/changeStatus.action",
					data:"id="+id+"&status="+fix,
					success:function(){
						$("#mainGrid").trigger("reloadGrid");
					}
				})
			}
			function statusBad(){
				var id=arguments[0];
				var bad=$.dictionary.getCode('ZFSBZT','作废');
				$.dialog.confirm('确认要作废该设备吗?',function(){
					$.ajax({
						type:"post",
						url:"${ctx}/data/sbgl/ydzf/changeStatus.action",
						data:"id="+id+"&status="+bad,
						success:function(){
							$("#mainGrid").trigger("reloadGrid");
						}
					})
				});
			}
			function statusReturn(){
				var id=arguments[0];
				var notUsed=$.dictionary.getCode('ZFSBZT','未用');
				$.ajax({
					type:"post",
					url:"${ctx}/data/sbgl/ydzf/changeStatus.action",
					data:"id="+id+"&status="+notUsed,
					success:function(){
						$("#mainGrid").trigger("reloadGrid");
					}
				})
			}
		</script>
		</head>

<body>
<%@include file="/data/top.jsp" %>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：电子监管 -> 设备管理 -> 移动执法终端信息管理</span> </div>
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
  <div id="search"></div>
  <div class="buttonArea operation">
      <input type="button" id="btnAdd" class="bttn bicon-add" id="btnAdd" value="新增" onclick="javascript:openAdd();"/>
      <input type="button" class="bttn bicon-delete" id="btnDel" onclick="javascript:openDel();" value="删除" />
      <input type="button" id="btnExcel" class="bttn bicon-excel"  onclick="javascript:openExcel();" value="导出"/>
  </div>
 
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
