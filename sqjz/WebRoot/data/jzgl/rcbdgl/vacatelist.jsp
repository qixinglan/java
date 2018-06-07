<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	  <title>请假管理</title>
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8 "/>
	  <%@ include file="/common/commonjs.jsp" %>
	  <script language="javascript" type="text/javascript">
	  $(function(){
		        //根据登录人员获取对应司法所机构ID，通过司法所ID获取本机构下所有社区服刑人员请假信息
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/jzgl/rcbdgl/vacateSearch.action',
					colNames	: [
					    '','', '服刑人员', '性别','矫正状态','请假开始日期', '请假结束日期','请假原因','请假天数','外出目的地','销假日期','操作'
					],
					colModel	: [
							
							{
								name	: 'fxryid',
						        index	: 'fxryid',						        				    
						        hidden  : true
					        },
					        {
								name	: 'id',
						        index	: 'id',						        				    
						        hidden  : true
					        },
					        {
								name	:'fxryname',
								index	:'fxryname',
								width	: 20,
								align	:'center',
							    sortable: true
							}, {
						        name	: 'sex',
						        index	: 'sex',						      
						        width	: 15,						       
						        formatter : $.dictionary.formatter('XB'),
						        align	: 'center'
					        }, {
						        name	: 'fxrystate',
						        index	: 'fxrystate',						        				    
						        width	: 20,
								align	:'center',
								formatter :'dictionary',
								formatoptions:{code:'JZRYZT'},
							    sortable: true												        
					        }, {
						        name	: 'startDate',
						        index	: 'startDate',			        
						        width	: 25,
						        align	: 'center',
						        sortable: true
					        }, {
						        name	: 'endDate',
						        index	: 'endDate',
						        width	: 25,						    
						        align	: 'center',
						        sortable: true						        
					        }, {
						        name	: 'reason',
						        index	: 'reason',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'period',
						        index	: 'period',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'termini',
						        index	: 'termini',
						        width	: 25,
						        align	: 'center'
					        },{
						        name	: 'reportDate',
						        index	: 'reportDate',
						        width	: 20,
						        align	: 'center',
						        sortable: true
					        },{
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 150,
						        align	: 'center',
						        fixed : true,
						        sortable: false,
						        formatter : function(value, opts, rwdat) {
						        	var str = "<a title='' class='a-style gray'>销假</a>";
						        	if(rwdat.reportDate==null || rwdat.reportDate==''){
						        		str = "<a href='javascript:openReport(\""+rwdat.id+"\");' title='' class='a-style'>销假</a>";
						        		str += "<a href='javascript:openView(\""+rwdat.fxryid+"\");' title='' class='a-style'>历史信息</a>";
						        	}
						        	return str;
						        }
					        }
					],
					sortname:'reportDate',
					sortorder : "desc",
					multiselect : false,
					pager: '#mainGridPager'
				});				
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	          }			 		
		);
	    
		function openView(id) {
			$.dialog({
				id : id,
				width : '900px',
				height : '400px',
				title : '请假历史记录',
				content : 'url:${ctx }/data/jzgl/rcbdgl/vacate.jsp?id=' + id,
				cancelVal : '关闭',
				cancel : true
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
					title : '新建请假信息',
					content : 'url:${ctx }/data/jzgl/rcbdgl/addVacate.jsp',
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
		 function openReport(id){
			 $.dialog.confirm("确定销假吗？",function(){
					$.ajax({
						   type: "POST",
						   async:false,
						   url: "${ctx }/data/jzgl/rcbdgl/report.action",
						   data: "id="+id,
						 });
					 $("#mainGrid").trigger("reloadGrid");
				});
		 }
	  </script>
  </head>
  
  <body>
  <%@ include file="/data/top.jsp" %>
   <div class="main">
       <div class="breadcrumb-nav"><span>您当前的位置：矫正管理 ->请假管理 </span></div>
     	 <div class="container-top">
			<table class="search-table" id="searchDiv">
				<tbody>
					<tr>
						<th class="search-form-label">服刑人员</th>
		          		<td>
		          			<input name="fxryname" searchType="cn" type="text" class="search-form-field"/>
		         		</td>
						<th class="search-form-label">请假起止日：</th>
		     	  		<td><input id="startDate" name="startDate" type="text"
								class="Wdate inputStyle search-form-field"  searchType="ge"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'endDate\')}'})" />
								至
							<input id="endDate" name="endDate" type="text"
								class="Wdate inputStyle search-form-field" searchType="le"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'startDate\')}'})" />	
	                    </td>
						<td>
				          	<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
		          			<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
		         		 </td>
					</tr>
				</tbody>
			</table>
		</div>
		  <!--操作按钮-->
		  <div class="buttonArea operation">
		      <input type="button" id="btnExcel" class="bttn bicon-add" value="新建" onclick="javascript:openAdd()" />
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

