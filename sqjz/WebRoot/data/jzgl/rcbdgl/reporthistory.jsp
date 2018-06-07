<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>    
    <title>报到历史记录</title>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8 "/>
     <%@ include file="/common/commonjs.jsp" %>
     <script language="javascript" type="text/javascript">
     $(function(){		         	  
     	var id='<%=request.getParameter("id")%>';      
			$("#mainGrid").jqGrid({
				rowNum: 10,
				url		    : '${ctx}/data/jzgl/rcbdgl/reporthistory.action?id='+id,				
				colNames	: [
							    '','服刑人员','性别','责任警官','报到时间'
							],
							colModel	: [
									{
								        name	: 'fxryid',
								        index	: 'fxryid',						        				    
								        hidden  : true,
								        
							        },{
										name	:'fxryname',
										index	:'fxryname',
										width	:10,
										align	:'center',
										searchType:'eq',
									    sortable: true
									}, {
								        name	: 'sex',
								        index	: 'sex',						      
								        width	: 10,						       
								        formatter : $.dictionary.formatter('XB'),
								        align	: 'center'
							        },{
								        name	: 'policename',
								        index	: 'policename',
								        width	: 20,
								        align	: 'center'						      
							        },{
								        name	: 'realreportdate',
								        index	: 'realreportdate',
								        width	: 20,						    
								        align	: 'center',
								        sortable: true						        
							        }
							],
				sortname:'realreportdate',
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

