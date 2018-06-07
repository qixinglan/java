<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 聚集报警排除情况市局设置</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
			$(function() {
				$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "bjpcsz",});
			  loadData();
			 jQuery(function(){
			   //增加行
			  $(".a-style").live("click",addDataHandler);
			  $(".addImg").click(function(){  
			      var tbody = $("#userLine");
			      var rowIndex = tbody.children().length;
			      var tr = $("#add_template").val();
			      tbody.append(tr);
			      order(); 
			      addColor();
			      //给新增加行的第一个输入框获得焦点
			      var newAdd = $("#userLine tr:last>td:eq(1)>input:eq(0)");
			      newAdd.focus();
			      loadQxmc();			    			      			     			     
			  });
			  //删除行
			   $(".deleteImg").live("click",function(){ 
				   var tr =$(this).parent().parent();
				   if($(this).text()=="删除"){
			  
			    $.ajax({
			    	url:"deleteData.action",
				    type:"Post",
				    data:"ignoreId="+$(this).parent().data().ignoreId,
				    success:function(result)
				    {	
				    }
			    });
			   }
			    tr.remove();
			    order();
			    addColor(); 
			   });
			 });
			 function addDataHandler(){
				 //alert($(this).text());
				 var parms=[];	
				 if($(this).parent().data().ignoreId)
				 {
					parms.push("ignoreId="+$(this).parent().data().ignoreId);				 
				 }				 
				 $this=$(this);
				 var elements=$(this).parent().parent().find("input");
				 for(var i=0;i<elements.length;i++){
					 if($(elements[i]).val()==""&&$(elements[i]).attr("name")!="JgIds"){
						   focError($(elements[i]),$(elements[i]).attr("msg"));
					          return;
					 }
				 }
				 $(elements).each(function(){  				   
					   parms.push($(this).attr("name")+"="+$(this).val());				   
				  }
				 );
				 $.ajax(
					{
						url:"postData.action",
					    type:"Post",
					    data:parms.join("&"),
					    success:function(result)
					    {			
					    	if(result.indexOf("success")>-1)
					    	{
					    		
					    		$this.parent().data().ignoreId=result.split(":")[1];
					    		$this.attr("class","a-edit");
					    		$this.text("编辑");
					    		$this.next().text("删除");
					    		$this.parent().data().startTime= $this.parent().parent().find("input:eq(2)").val();
								$this.parent().data().endTime=$this.parent().parent().find("input:eq(3)").val();
								var datetime=$this.parent().data().startTime+"至"+$this.parent().data().endTime;
								$this.parent().parent().find("td:eq(1)").html($this.parent().parent().find(".qxmc").val());
								$this.parent().parent().find("td:eq(2)").html(datetime);						       					          
					    		//alert("插入数据成功！");
					    	}
					    }
					}	 
				 );
			 }
			 
			 
			  //给表格添加序号   
			  function order(){
			     var uTable = document.getElementById("userLine");
			     for(var i=0;i<uTable.rows.length;i++){
			       uTable.rows[i].cells[0].innerHTML = (i+1);
			     }
			  } 
			  $(".a-edit").live("click",function(){
				  $(this).attr("class","a-style");
				  $(this).text("保存");				 
				  var td1=$($("#add_template").val()).find("td:eq(1)").html();
				  var td2=$($("#add_template").val()).find("td:eq(2)").html();
				  $(this).parent().parent().find("td:eq(1)").html(td1);
				  $(this).parent().parent().find("td:eq(2)").html(td2);
				  $(this).parent().parent().find("input:eq(1)").val($(this).parent().data().startTime.substring(0,16));
				  $(this).parent().parent().find("input:eq(2)").val($(this).parent().data().endTime.substring(0,16));
				  loadQxmc();
				  var orgname=[];
				  var orgIds=[];
			      $($(this).parent().data().ccIgnoreOrgs).each(function(){
			         orgname.push(this.orgName);
			         orgIds.push(this.orgId);
			      });
			      orgname.sort();
			      orgIds.sort();
				  $(this).parent().parent().find("input:eq(0)").val(orgname.join(","));
				  $(this).parent().parent().find("input:eq(1)").val(orgIds.join(","));
			  });
			  function loadData()
			  {
				  $.ajax({
					  url:"getData.action",
					    type:"Post",
					    //data:parms.join("&"),
					    success:function(datas){
					     $(datas).each(function(){
					    	 AddLine(this);
					     });
					      
					    }
					  
				  });
				  
			  }
			  function AddLine(data)
			  {
				  var tbody = $("#userLine");
			      var tr = $("#get_template").val();	
			      tbody.append(tr);
			      var orgname=[];
			      $(data.ccIgnoreOrgs).each(function(){
			         orgname.push(this.orgName);
			      });
			      orgname.sort();
			      //$("#userLine tr:last>td:eq(1)").html(data.ccIgnoreOrgs[0].id.orgId);
			      $("#userLine tr:last>td:eq(2)").html(data.startTime.substring(0,16)+"至"+data.endTime.substring(0,16));
			      $("#userLine tr:last>td:eq(1)").html(orgname.join(","));
			      $("#userLine tr:last>td:last").data(data);
			     
			      order(); 
			      addColor();
			  }
			  //奇偶行变色
			    function addColor(){
			      var t=document.getElementById("userLine").getElementsByTagName("tr");
			      var a = "#FFFFFF"; //奇数行背景
			      var b = "#FBFBFB"; //偶数行背景
			      for(var i=0;i<t.length;i++){
			        t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
			      }
			    }
			  });
			  //添加行的时候加载区县名称
			  function loadQxmc(){
				  var title="区县选择";
				  if($("#qxj").val()=="true"){
					  title="司法所选择";
				  }
					  $('.qxmc').triggerfield({
							btnIcon : 'triggerIcon',
							showClear : true,
							open : function(){
								$this=$(this);
								$.dialog({ 
							    id: 'testID',
							    width: '380px', 
			    				height: '300px', 
			    				title:title,
							    content: 'url:qxxz.action?type=1'+'&orgIds='+this.value,
							    ok: function(){
							    var texts=[];
							    var ids=[];							    
							    var orgs=[];
							    this.content.$("#peapleSelected option").each(
							     function(){
							    	     var org={};
							    		texts.push(this.text);
							    		ids.push(this.value);
							    		org.orgName=this.text;
							    		org.orgId=this.value;
							    		orgs.push(org);
							     }
							    );
							    texts.sort();
							    ids.sort();
							    $this.val(texts.join(","));
							    var tr=$this.parent().parent().parent().parent();
							    tr.find("td:last").data().ccIgnoreOrgs=orgs;
							    $this.parent().find("[name='JgIds']").val(ids.join(","));
							   
		        				cancel: true; 
			    				}, 
			    				cancelVal: '取消', 
			    				cancel: true 
								});
								$(this).focus();
							}
					});	
					$('.qxmc').attr("name","jgMc");
			  }			  
		</script>
</head>
<body>
<%@include file="/data/top.jsp"%>
<%@include file="/data/left.jsp"%>
<div class="main">
<input type="hidden" id="qxj" value="${qxj}"/>
  <div class="breadcrumb-nav"> 
  	<span>您当前的位置：电子监管 -> 配置管理 -> <s:if test="#request.qxj">聚集报警排除情况区县局设置</s:if><s:else>聚集报警排除情况市局设置</s:else></span> 
  	</div>
  <!--列表-->
  <h2 style="text-align:center;font-weight:bold;font-size:20px;">
	${JgMc}
	</h2>
  <div class="container-bottom" style="height:280px;overflow:auto">
      <table class="comm-table">
        <thead>
          <tr>
            <th width="5%" style="text-align:center;font-weight:bold">序号</th>
            <th width="40%" style="text-align:center;font-weight:bold"><s:if test="#request.qxj">司法所名称</s:if><s:else>区县名称</s:else></th>
            <th width="40%" style="text-align:center;font-weight:bold">时间</th>
            <th width="15%" style="text-align:center;font-weight:bold"><img class="addImg" src="${ctx}/images/add-row.gif" style="cursor:hand;width:16px;height:16px"></th>
          </tr>
        </thead>
        <tbody id="userLine" class="buttonArea">
       </tbody>
      </table> 
      <textarea id="get_template" rows="" cols="" style="display:none">
       <tr>
            <td style="text-align:center;width:5%"></td>
            <td style="text-align:left">
            	东城区、西城区
            </td>
            <td style="text-align:center">
            	2014-01-01至2014-01-02 00:00-24:00
            </td>
            <td style="text-align:center;width:5%">
            	<a href='javascript:void(0);' title='' class='a-edit'>编辑</a>&nbsp;&nbsp;
            	<a href='javascript:void(0);' title='' class='deleteImg'>删除</a>
            </td>
          </tr>
      </textarea>
      <textarea id="add_template" rows="" cols="" style="display:none">
        <tr>
            <td style="text-align:center;width:5%"></td>
            <td style="text-align:left">
            	 <input type="text" style="width:400px" name="JgIds" msg="请选择机构"  class="qxmc"/>
            </td>
            <td style="text-align:center;width:10%">
            	<input class="Wdate" msg="开始时间不能为空！" name="startDate" id="startDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,maxDate:'#F{$dp.$D(\'endDate\')}'})" maxlength="16"/> 至
          	    <input class="Wdate" name="endDate" id="endDate" msg="结束时间不能为空！" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,minDate:'#F{$dp.$D(\'startDate\')}'})" maxlength="16"/>
            </td>
            <td style="text-align:center;width:5%">
            	<a href='javascript:void(0);' title='' class='a-style'>保存</a>&nbsp;&nbsp;
            	<a href='javascript:void(0);' title='' class='deleteImg'>取消</a>
            </td>
          </tr>
      </textarea>
  </div>
</div>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
</body>
</html>