<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jqGrid Demos</title>
  
<link href="css/ui-lightness/jquery-ui-1.8.12.custom.css" rel="stylesheet" type="text/css" />
<link href="css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.5.1.min.js" type="text/javascript"></script> 

<script src="js/jquery-ui-1.8.12.custom.min.js" type="text/javascript"></script>
<script src="js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min-3.8.js" type="text/javascript"></script>


<script type="text/javascript">  


jQuery(document).ready(function(){   
jQuery("#list2").jqGrid({   
         datatype: "json", //将这里改为使用JSON数据   
         url:'DataServlet', //这是Action的请求地址   
         mtype: 'POST',   
         height: 250,   
         width: 550,   

         colNames:['编号1','姓名', '电话'],   
         colModel:[   

             {name:'id',index:'id', width:60, sorttype:"int"},   
             {name:'name',index:'name', width:90, editoptions:{value:"0:无效;1:正常;2:未知"}},   
             {name:'phone',index:'phone', width:120}        
         ],   

         pager: '#pager2', //分页工具栏,指定了jqGrid页脚显示位置
		 sortname: 'id', //排序
		 sortorder: "desc",//降序
		 viewrecords: true,//是否在Pager Bar显示所有记录的总数
         //imgpath: 'css/ui-lightness/images', //图片存放路径  
         rowNum:10, //每页显示记录数               
         rowList:[10,20,30], //可调整每页显示的记录数   
         multiselect: true, //是否支持多选   
         caption: "信息显示" ,  
		 repeatitems: true,//cell中不需要各列的name，但是需要与colModel一一对应
		 loadComplete: function(){
		 var records=$("#list2").jqGrid('getGridParam','records');
		 alert("加载完成"+records);
		 }
     });   
	 jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
 });  
var txtdd = '{ "employees" : [' +
'{ "firstName":"Bill" , "lastName":"Gates" },' +
'{ "firstName":"George" , "lastName":"Bush" },' +
'{ "firstName":"Thomas" , "lastName":"Carter" } ]}'; 

</script> 
<script type="text/javascript">

function f1(){
//此处可以添加对查询数据的合法验证  
   // var orderId = $("#orderId").val();  
    $("#list2").jqGrid('setGridParam',{  
        datatype:'json',  
       	//postData:{'orderId':orderId}, //发送数据  
        page:2 
    }).trigger("reloadGrid"); //重新载
}
</script>
</head>  
<body>  
		<table id="list2"></table>
		<div id="pager2"></div>
		<%--将jason的数据通过tf在页面上显示出来 --%>
		<button id='search_btn'  onclick="f1()">搜索</button>
		<%=session.getAttribute("tf") %>
        
</body>  
</html> 