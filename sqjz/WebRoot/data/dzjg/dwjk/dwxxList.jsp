<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			$(function() {
				
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/dwjk/getAllXYById.action?id=<%=request.getParameter("id")%>',
					colNames	: [
					    '定位时间', '经度','纬度', '具体位置'
					],
					
					colModel	: [
					        {
						        name	: 'locTime',
						        index	: 'locTime',
						        width	: 200,
						        align	: 'center',
						        formatter : function(value, opts, rwdat) {
									return rwdat.locTime==null?"":rwdat.locTime.replace("T"," ");
								}
					        }, {
						        name	: 'longitude',
						        index	: 'longitude',
						        width	: 180,
						        align	: 'center'
					        }, {
						        name	: 'latitude',
						        index	: 'latitude',
						        width	: 180,
						        align	: 'center'
					        }, {
						        name	: 'address',
						        index	: 'address',
						        width	: 400,
						        align	: 'left'
					        }
					],
					multiselect : false,
					pager: '#mainGridPager',
					sortname : 'locTime',
					sortorder : "desc",
					rowNum : 10
				});
				$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});
			
			
		</script>
		</head>

	<body>
		 	 <div class="container-top">
		   		 <table class="search-table" id="searchDiv">
			     	 <tbody>
			     	 		<tr>
			     	 			<th  >定位时间：</th>
			     	 			<td >
				     	 			<input id="bjsj1" name="locTime" type="text" searchType="gt"
												class="Wdate inputStyle" style="width: 85px"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'bjsj2\')}'})" />
										至
										<input id="bjsj2" name="locTime" type="text" searchType="lt"
												class="Wdate inputStyle" style="width: 85px"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'bjsj1\')}'})" />	
			     	 			</td>
					          	<td style="text-align: right" ><input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
				     	 		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/></td>
			     	 	  </tr>
			      </tbody>
		    	</table>
		  </div>
  
		  <!--列表-->
		
		  <div class="container-bottom" style="width:980px">
		    <table id="mainGrid" style="width:980px" >
		    </table>
		    <div id="mainGridPager" style="width:980px"></div>
		  </div>
	</body>
</html>
