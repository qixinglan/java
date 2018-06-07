<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>    
    <title>请假历史记录</title>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8 "/>
     <%@ include file="/common/commonjs.jsp" %>
     <script language="javascript" type="text/javascript">
     $(function(){		         	  
     	var id='<%=request.getParameter("id")%>';
     	$("#mainGrid").jqGrid({
     		rowNum: 10,
			url		    : '${ctx}/data/jzgl/rcbdgl/vacateSearch.action?id='+id,
			colNames	: [
			    '','', '服刑人员', '性别','矫正状态','请假开始日期', '请假结束日期','请假原因','请假天数','外出目的地','销假日期'
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
						width	:20,
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
				        width	:20,
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
			        }
			],
			sortname:'reportDate',
			sortorder : "desc",
			multiselect : false,
			pager: '#mainGridPager'
		});						
       }			 		
	);
     </script>
  </head>  
  <body>
   		 <!--数据列表区-->
		  <div class="">
		    <table id="mainGrid" >
		    </table>
		    <!-- 列表分页操作区 -->
		    <div id="mainGridPager"></div>
		  </div>
  </body>
</html>

