<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	  <title>日常报到管理</title>
	  <%@ include file="/common/commonjs.jsp" %>
	  <script language="javascript" type="text/javascript">
	  $(function(){		
		  $.dictionary.combobox("#fxrystate", "fxrystate", "JZRYZT",{attr:{searchType:"eq","class":"search-form-field"}});
		        //根据登录人员获取对应司法所机构ID，通过司法所ID获取本机构下所有社区服刑人员报到信息
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/jzgl/rcbdgl/reportlist.action',
					colNames	: [
					    '服刑人员', '性别','矫正状态', '','下次报到月份', '上次报到时间','身份证号','人员编号','责任警官','操作'
					],
					colModel	: [
							{
								name	:'fxryname',
								index	:'fxryname',
								width	:10,
								align	:'center',
							    sortable: true
							}, {
						        name	: 'sex',
						        index	: 'sex',						      
						        width	: 10,						       
						        formatter : $.dictionary.formatter('XB'),
						        align	: 'center'
					        }, {
						        name	: 'fxrystate',
						        index	: 'fxrystate',						        				    
						        width	:10,
								align	:'center',
								formatter :'dictionary',
								formatoptions:{code:'JZRYZT'},
							    sortable: true												        
					        },{
						        name	: 'fxryid',
						        index	: 'fxryid',						        				    
						        hidden  : true,
						        
					        }, {
						        name	: 'reportdate',
						        index	: 'reportdate',			        
						        width	: 20,
						        align	: 'center',
						        sortable: true
					        }, {
						        name	: 'realreportdate',
						        index	: 'realreportdate',
						        width	: 20,						    
						        align	: 'center',
						        sortable: true						        
					        }, {
						        name	: 'fxryidentirycard',
						        index	: 'fxryidentirycard',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'fxrycode',
						        index	: 'fxrycode',
						        width	: 20,
						        align	: 'center'
					        },{
						        name	: 'policename',
						        index	: 'policename',
						        width	: 20,
						        align	: 'center'						      
					        },{
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 100,
						        align	: 'center',
						        fixed : true,
						        sortable: false,
						        formatter : function(value, opts, rwdat) {
												 return "<a href='javascript:openView(\""+rwdat.fxryid+"\");' title='' class='a-style'>详情</a>";	
						        }
					        }
					],
					sortname:'fxrycode',
					sortorder : "asc",
					multiselect : false,
					pager: '#mainGridPager'
				});				
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	          }			 		
		);
		
		function ToQJGL()
	    {		
		  window.location.href='${ctx}/data/jzgl/rcbdgl/qjgl.action';
	    };
	    var dialog;
	    function ToAddReportInfo()
	    {
	    	dialog=$.dialog({ 
				id:"add",
			    width: '900px', 
				height: '430px', 
				title:'手工报到',
			    content: 'url:${ctx }/data/jzgl/rcbdgl/addReportInfo.jsp'			    
			});	  	    		   
	    }
	    
		function openView(id) {
			var dataRow = $("#mainGrid").jqGrid("getRowData", id);
			$.dialog({
				id : id,
				width : '900px',
				height : '400px',
				title : '报到历史记录',
				content : 'url:${ctx }/data/jzgl/rcbdgl/reporthistory.jsp?id=' + id,
				cancelVal : '关闭',
				cancel : true,
				data : dataRow
			});
		}
	
	    function s(){
			$.container.popup({				
				items:[{
					save: "${ctx}/data/jzgl/rcbdgl/addReportInfoOperate.action",
					title: '手工报到录入',
					fieldCols: 3,
					fieldCls:{labelCls : 'form-td-label-90',},
					fields:[
					        {text: '社区服刑人员', type:'fxrys_combobox', name: 'fxryid', code: 'FXRYS',emptyText:'请选择',allowSearch:true},
					        {text: '本次报到时间', type:'datepicker', name: 'reportdate',dateFmt:'yyyy-MM-dd'},
						    {text: '下次报到时间', type:'datepicker', name: 'nextreportdate',dateFmt:'yyyy-MM'},
					],
					yes: function(){
						$("#mainGrid").trigger("reloadGrid");			
					},
				}]
			}, {
				okVal: '保存',
				width: '800px',
				height: '150px',
			});
		}	
	    function openAdd() {
			var id = "user" + new Date().getTime();
			$.dialog({
				id : id,
				width : '500px',
				height : '325px',
				fixed : true,
				lock : true,
				cover : true,
				title : '手工报到录入',
				content : 'url:${ctx }/data/jzgl/rcbdgl/add.jsp',
				cancelVal : '关闭',
				okVal : '保存',
				ok : function() {
					var doc = $("iframe[name='" + id + "']").get(0).contentDocument;
					return $("iframe[name='" + id + "']").get(0).contentWindow.$.fields.checkForm($(doc
							.getElementById("userAdd")), {
						yes : function(data) {
							$("#mainGrid").trigger("reloadGrid");
						},
						mask : true,
						title : '请假信息新建',
						url : $(doc.getElementById("userAdd")).attr(
								"action")
					});
				},
				cancel : true
			});
		}
	  </script>
  </head>
  
  <body>
  <%@ include file="/data/top.jsp" %>
   <div class="main">
       <div class="breadcrumb-nav"><span>您当前的位置：矫正管理 ->日常报到管理 </span></div>
        <div class="container-top">
		    <table class="search-table" id="searchDiv">
		      <tbody>
					<tr>
						<th class="search-form-label">服刑人员</th>
						<td><input name="fxryname" searchType="cn" type="text"
							class="search-form-field" /></td>
						<th class="search-form-label">矫正状态</th>
						<td id="fxrystate"></td>
						<th class="search-form-label">下次报到时间</th>
						<td><input id="reportdate" name="reportdate"
							class="Wdate inputStyle search-form-field"  searchType="eq"
							onclick="WdatePicker({dateFmt:'yyyy-MM',enableInputMask:false})" />
						</td>
						<td><input type="button" class="bttn bicon-search"
							id="searchBtn" value="查询" /> <input type="button"
							class="bttn bicon-reset" id="resetBtn" value="重置" />
						</td>
					</tr>
				</tbody>
    		</table>
  		</div>
		  <!--操作按钮-->
		  <div class="buttonArea operation">
		     <!--  <input type="button" id="btnExcel" class="bttn bicon-excel"  value="报到录入" onclick="javascript:ToAddReportInfo()"/> -->
		      <input type="button" id="btnExcel" class="bttn bicon-add"  value="报到" onclick="javascript:openAdd()"/>
		     <!-- 
		      <input type="button" id="btnExcel" class="bttn bicon-excel"  value="请假" onclick="javascript:ToQJGL()"/>
		      <input type="button" id="btnExcel" class="bttn bicon-excel"  value="销假"/>
		  	 -->
		  </div>
		 <!--数据列表区-->
		  <div class="">
		    <table id="mainGrid" >
		    </table>
		    <!-- 列表分页操作区 -->
		    <div id="mainGridPager"></div>
		  </div>
   </div>
   <%@include file="/data/bottom.jsp"%>
  </body>
</html>
