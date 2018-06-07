<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通知查看</title>
		<%@ include file="/common/commonjs.jsp"%>
		<script type="text/javascript">
			var notShowType='1,2,3,4,5,6,7,8,9';
		$(function() {
			$.menu.init("#menuDetail",{first:"bggl",second:"notice",third : "tzck",});
			if(${user.wunit.orgType == '1'}){
				notShowType = '1,3,4,5,6,7,8,9';
			}else if(${user.wunit.orgType == '2'}){
				notShowType = '1,2,4,5,6,7,8,9';
			}
			$.dictionary.combobox("#status", "status", "ZLLX",{attr:{searchType:"eq","class":"search-form-field"}});
				//表格初始化
				$("#mainGrid").jqGrid({
					url		    : '${ctx}/data/tzgl/getData.action?oper=tzView',
					colNames	: [
					    '','通知名称', '拟制人', '通知时间','要求','通知状态','操作'
					],
					
					colModel	: [
					        {
						        name	: 'id',
						        index	: 'id',
						        hidden : true
					        },{
						        name	: 'title',
						        index	: 'title',
						        width	: 40,
						        align	: 'left'
					        }, {
						        name	: 'creater',
						        index	: 'creater',
						        width	: 15,
						        align	: 'center',
					        }, {
						        name	: 'sendtime',
						        index	: 'sendtime',
						        width	: 20,
						        align	: 'center'
					        }, {
						        name	: 'required',
						        index	: 'required',
						        width	: 40,
						        align	: 'left'
					        }, 
					        {
						        name	: 'status',
						        index	: 'status',
						        width	: 10,
						        align	: 'center',
						        hidden : true,
						        formatter : function(value, opts, rwdat) {
						        	if(rwdat.status=="2"){
										return "拟制中";	
									}else{
										return "已下发";	
									}	 
						        }
					        }, {
						        name	: 'cz', 
						        index	: 'cz',
						        width	: 100,
						        align	: 'center',
						        fixed : true,
						        sortable : false,
						        formatter : function(value, opts, rwdat) {
						       		var str = "<a href='javascript:view(\""+rwdat.id+"\")' title='' class='a-style'>";
						       		var notice = rwdat.ccNoticereceives;
						       		for(var i=0;i<notice.length;i++){
						       			if(notice[i].orgId==${user.wunit.bm}){
								       		if(notice[i].status=='2'){
								       			str += "查看(未查看)</a>";
								       		}else{
								       			str += "查看(已查看)</a>";
								       		}
								       		break;
							       		}
						       		}
						       		
						       		
						        	return str;
						        }
					        }
					],
					  pager: '#mainGridPager',
					  sortname : 'createdate',
					  sortorder : "desc",
					  multiselect : false
				});
				 $("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
			});	
			//查看
			function view(id){
				$.container.popup({
					title: '通知拟制',
					read:'${ctx}/data/tzgl/search.action?id='+id+'&oper=tzView',
					fieldCols: 2,
					fieldCls:{
						labelCls : 'form-td-label-120'
					},
					fields:[
						{text:"通知接收单位",
						 type:'display',
						 name:'jgIds',
						 colspan:2,
						 allowBlank:false
						},
						{text:"通知名称",
						 type:'display',
						 name:'title',
						 colspan:2,
						 allowBlank:false
						},
						{text:"要求",
						 name:'required',
						 type:'textarea',
						 readonly:true,
						 colspan:2,
						 allowBlank:false
						},
						{text:"备注",
						 name:'description',
						 type:'textarea',
						 readonly:true,
						 colspan:2,
						 allowBlank:false
						},
						{text:"拟制人",
						 type:'display',
						 name:'creater',
						 colspan:1,
						 allowBlank:false
						},
						{text:"通知时间",
						 type:'display',
						 name:'sendtime',
						 colspan:1,
						 allowBlank:false
						},
						{text:"附件",
						 type:'file',
						 name:'affixId',
						 url:"${ctx}/data/common/upload.action",
						 multiple:true,
						 accept:"application/vnd.ms-excel",
						 spath:"tzgl/upload",
						 colspan:2,
						 readonly:true
						}						
					],
					yes : function(){}
				},{cancelVal: '关闭',width: "800px",title: '通知查看',cancel:function(){$("#mainGrid").trigger("reloadGrid");}});
			}
		</script>
		</head>

		<body>
<%@include file="/data/top.jsp" %>
<%@include file="/data/left.jsp"%>
<div class="main">
          <div class="breadcrumb-nav"> <span>您当前的位置：办公管理 -> 通知管理->通知查看</span> </div>
  <div class="container-top">
    <table class="search-table" id="searchDiv">
      <tbody>
        <tr>
          <th class="search-form-label">通知名称</th>
          <td>
          		<input name="title" searchType="cn" type="text" style="width: 200px"/>
          </td>
          <th class="search-form-label">拟制人</th>
          <td>
          	  <input name="creater" searchType="cn" type="text" style="width: 200px"/>
          </td>
	   <th class="search-form-label">通知时间</th>
          <td colspan="3">
          		<input id="xfsj1" name="sendtime" type="text" searchType="gt"
							class="Wdate inputStyle" style="width: 100px"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,maxDate:'#F{$dp.$D(\'xfsj2\')}'})" />
							至 <input id="xfsj2" name="sendtime" type="text"
							class="Wdate inputStyle" style="width: 100px" searchType="lt"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',enableInputMask:false,minDate:'#F{$dp.$D(\'xfsj1\')}'})" />
          </td>
          <td>
          		<input type="button" class="bttn bicon-search" id="searchBtn" value="查询"/>
          </td>
          
          <td>
          		<input type="button" class="bttn bicon-reset" id="resetBtn" value="重置"/>
          </td>
        </tr>
       </tbody>
    </table>
  </div>
  <!--列表-->
  <div class="buttonArea operation">
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
