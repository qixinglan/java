<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>社区矫正业务统计简表</title>
	<%@ include file="/common/commonjs.jsp"%>
	<script type="text/javascript">
	  $(function(){
	     $.menu.init("#menuDetail",{first:"sqjz",second:"bbgl",third : "sqjzzb"});
	       //表格初始化
		$("#mainGrid").jqGrid({
			url : '${ctx}/data/jzgl/bbgl/statfxry/weeklist.action',			
			mtype : "POST",
			datatype : 'json',
			colModel: [
		       {
				name	: 'orgName',
				index	: 'orgName',
				label	: '区县名称',
				width	: 10,	
				
				sortable : false,
				align	: 'center'
			},{
				name	: 'currDate',
				index	: 'currDate',
				label	: '统计时限',
				width	: 10,
				
				 sortable : false,
				align	: 'center'
			},{
				name	: 'addData',
				index	: 'addData',
				label	: '累计接收',
				width	: 15,
				 sortable : false,
				align	: 'center',
			}, {
				name	: 'subData',
				index	: 'subData',
				width	: 15,
				label	: '累计解除',
				sortable:false,
				align	: 'center'
			},{
				name	: 'guanZhi',
				index	: 'guanZhi',
				width	: 10,
				label	: '在册管制',
				 sortable : false,
				align	: 'center'
			},{
				name	: 'jiaShi',
				index	: 'jiaShi',
				width	: 10,
				label	: '在册假释',
				 sortable : false,
				align	: 'center'
			},{
				name	: 'huanXing',
				index	: 'huanXing',
				width	: 10,
				label	: '在册缓刑',
				 sortable : false,
				align	: 'center'
			},{
				name	: 'zanJianWai',
				index	: 'zanJianWai',
				width	: 10,
				label	: '在册暂监外',
				 sortable : false,
				align	: 'center'
			},{
				name	: 'xiaoJi',
				index	: 'xiaoJi',
				width	: 10,
				label	: '在册小计',
				 sortable : false,
				align	: 'center'
			},{
				name	: 'weekAddNum',
				index	: 'weekAddNum',
				width	: 10,
				label	: '增加人员',
				
				 sortable : false,
				align	: 'center'
			},{
				name	: 'weekSubNum',
				index	: 'weekSubNum',
				width	: 10,
				
				label	: '减少人员',	
				 sortable : false,
				align	: 'center'
			}
			],	
			multiselect : false,
			pager: '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
		});
	  
	  function getData(){
	    var d=$("#myDate").val();
	    $("#mainGrid").setGridParam({url: '${ctx}/data/jzgl/bbgl/statfxry/weeklist.action?myDate='+d});
	   
	  };
	  
	  function openExcel() {
		var grid = $("#mainGrid").getGridParam('postData') || {};
		var data = {};
		for (var i in grid){
			data["jqgrid."+i] = grid[i];
		}
		var t = data["jqgrid.cols"].split(",").slice(1);
		t.pop();
		data["jqgrid.cols"]=t.join(",");
		data["jqgrid.cols"] = data["jqgrid.cols"].slice(0,-3);
		var url = '${ctx}/data/jzgl/bbgl/statfxry/excel.action?myDate='+$("#myDate").val();
		if ($("#downloadcsv").length <= 0)
			$("body")
					.append(
							"<iframe id=\"downloadcsv\" style=\"display:none\"></iframe>");
		$("#downloadcsv").attr("src", url);
		
	}
	</script>
  </head>  
  <body>
    <%@include file="/data/top.jsp" %>
	<%@include file="/data/left.jsp"%>
 <div class="main">
   <div class="breadcrumb-nav"> <span>您当前的位置：社区矫正 -> 报表管理->社区矫正业务统计简表</span> </div>
   <div class="container-top">
      <table class="search-table" id="searchDiv">
        <tbody>
          <tr>
            <th class="search-form-label">查询时间</th>
            <td colspan="3">
          		<input id="myDate" name="myDate" type="text" searchType="gt"
							class="Wdate inputStyle search-form-field" style="width: 85px;height:30px;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				
            </td>
            <td>          		          		
          		<input id="searchBtn" type="button"
							class="bttn bicon-search" value="查询" onclick="getData()"/>    	
            </td>
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表-->
  <div class="buttonArea operation">
  <span>
   	 <input type="button"
				name="btnExcel" class="bttn bicon-excel"
				onclick="openExcel()" value="导出" />
  </span>
  </div>
  
  <div class="container-bottom">
    <table id="mainGrid">
    </table>
    <div id="mainGridPager"></div>
  </div>
</div>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
</body>
</html>
