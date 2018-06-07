<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聚集报警排除情况司法所设置</title>
<%@ include file="/common/commonjs.jsp"%>
<script type="text/javascript">
	$(function() {
		$.menu.init("#menuDetail",{first:"dzjg",second:"pzgl",third : "bjpcsz",});
		 loadData();
	     order();
		 addColor();			
		 jQuery(function(){
			  //数据库入库保存操作
			   $(".a-style").live("click",addDataHandler);
			  //增加行操作
			  $(".addImg").click(function(){  			      
			      var tr = $("#add_template").val();
			      $("#userLine").append(tr);
			      order(); 
			      addColor();
			      var newAdd = $("#userLine tr:last>td:eq(1)>input:eq(0)");
			      newAdd.focus();			    	     
			      loadPerson();
			  });			  
			  //删除行操作
			   $(".deleteImg").live("click",function(){ 
				 var tr =$(this).parent().parent();
				 if($(this).text()=="删除"){			  
			     $.ajax({
				    	url:"${ctx }/data/xtsz/deleteData.action",
					    type:"Post",
					    data:"ignoreId="+$(this).parent().data().ignoreId,
					    success:function(result)
					    {	
					       //alert(result);
					    }
			      });
			    }
			    tr.remove();
			    order();
			    addColor(); 
			   });	
		 });
		     //异步提交保存数据
			 function addDataHandler(){				    	 
				 var parms=[];		
				 if($(this).parent().data().ignoreId)
				 {
					parms.push("ignoreId="+$(this).parent().data().ignoreId);				 
				 }				 
				 var $this=$(this);
				 var elements=$(this).parent().parent().find("input");
				 for(var i=0;i<elements.length;i++){
					 if($(elements[i]).val()==""&&$(elements[i]).attr("name")!="personNames"&&$(elements[i]).attr("name")!="personIds"){
						   focError($(elements[i]),$(elements[i]).attr("msg"));
					          return;
					 }
				 }
				 elements.each(function(){
					   parms.push($(this).attr("name")+"="+$(this).val());				   
				      });
				 $.ajax(
					{
						url:"${ctx }/data/xtsz/addjjbjfxry.action",
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
					    		$this.parent().data().startTime= $this.parent().parent().find("input:eq(3)").val();
								$this.parent().data().endTime=$this.parent().parent().find("input:eq(4)").val();
								var datetime=$this.parent().data().startTime+"至"+$this.parent().data().endTime;
								$this.parent().parent().find("td:eq(1)").html($this.parent().parent().find(".add").val());
								$this.parent().parent().find("td:eq(2)").html(datetime);	
					        }
					    }
					}	 
				 );
			 }	
		     
	          function loadData()
	          {
		          $.ajax({
		     	  url:"${ctx }/data/xtsz/getData.action",
			      type:"Post",
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
	          $(data.ccIgnorePersons).each(function(){
	           orgname.push(this.personName);
	           });	
	          orgname.sort(); 
	          $("#userLine tr:last>td:eq(2)").html(data.startTime.substring(0,16)+"至"+data.endTime.substring(0,16));
	          $("#userLine tr:last>td:eq(1)").html(orgname.join(","));
	          $("#userLine tr:last>td:last").data(data);
	          order(); 
	          addColor();
	        }
			  //给表格添加序号   
			  function order(){
			     var uTable = document.getElementById("userLine");
			     for(var i=0;i<uTable.rows.length;i++){
			       uTable.rows[i].cells[0].innerHTML = (i+1);
			     }
			  } 			  
			  //奇偶行变色
			    function addColor(){				  
			      $("#userLine tr:even").css("background","red");
			      $("#userLine tr:odd").css("background","blue");
			    }
        });
	          
			   $(".a-edit").live("click",function(){
				  //$(this).removeAttr("a-edit");
				  $(this).attr("class","a-style");
				  $(this).text("保存");				 
				  var td1=$($("#add_template").val()).find("td:eq(1)").html();
				  var td2=$($("#add_template").val()).find("td:eq(2)").html();
				  $(this).parent().parent().find("td:eq(1)").html(td1);
				  $(this).parent().parent().find("td:eq(2)").html(td2);
				  $(this).parent().parent().find("input:eq(2)").val($(this).parent().data().startTime.substring(0,16));
				  $(this).parent().parent().find("input:eq(3)").val($(this).parent().data().endTime.substring(0,16));
				  loadPerson();
				  var orgname=[];
				  var orgIds=[];
			      $($(this).parent().data().ccIgnorePersons).each(function(){
			         orgname.push(this.personName);
			         orgIds.push(this.personId);
			      });	
			      orgname.sort();
			      orgIds.sort();
				  $(this).parent().parent().find("input:eq(0)").val(orgname.join(","));
				  $(this).parent().parent().find("input:eq(1)").val(orgname.join(","));
				  $(this).parent().parent().find("input:eq(2)").val(orgIds.join(","));
			  });
	  
			  //添加行的时候加载区县名称
			  function loadPerson(){				  								  
			      $('.add').triggerfield({
					 btnIcon : 'triggerIcon',
					 showClear : true,
					 open : function(){					
						$this=$(this);
						$.dialog({ 
						  id: 'testID',
						  width: '380px', 
			    		  height: '300px', 
			    		  title:'人员选择',
						  content: 'url:${ctx }/data/dzjg/pzgl/jjpcsz/qxxz.jsp?type=3'+'&orgIds='+this.value,
						  ok: function(){
						      var texts=[];
							  var ids=[];							
							  var orgs=[];
							  this.content.$("#peapleSelected option").each(function(){
								  var org={};
							       texts.push(this.text);
							       ids.push(this.value);
						    		org.personName=this.text;
						    		org.personId=this.value;
						    		orgs.push(org);
							   });		
							   texts.sort();
							   ids.sort();
							   $this.val(texts.join(","));								
					           $this.parent().find("[name='personNames']").val(texts.join(","));					           					          
					           $this.parent().parent().parent().find("[name='personIds']").val(ids.join(","));
					   	       var tr=$this.parent().parent().parent().parent();
							   tr.find("td:last").data().ccIgnorePersons=orgs;
		        		       cancel: true; 					          
			    	       }, 
			    	       cancelVal: '取消', 
			    	       cancel: true 
					   });
					  $(this).focus();
					 
					}
				});	
			 }		  
		</script>
</head>
<body>
<%@include file="/data/top.jsp"%>
<%@include file="/data/left.jsp"%>
<div class="main">
  <div class="breadcrumb-nav"> <span>您当前的位置：电子监管 -> 配置管理 -> 聚集报警排除情况司法所设置</span> </div>
  <!--列表-->
  <h2 style="text-align:center;font-weight:bold;font-size:20px;">${JgMc}</h2>
  <div class="container-bottom" style="height:280px;overflow:auto">
      <table class="comm-table">
        <thead>
          <tr>
            <th width="5%" style="text-align:center;font-weight:bold">序号</th>
            <th width="40%" style="text-align:center;font-weight:bold">姓名</th>
            <th width="40%" style="text-align:center;font-weight:bold">时间</th>
            <th width="15%" style="text-align:center;font-weight:bold">
                 <img class="addImg" src="${ctx}/images/add-row.gif" style="cursor:hand;width:16px;height:16px">
            </th>
          </tr>
        </thead>
        <tbody id="userLine" class="buttonArea">
       </tbody>
      </table> 
      <textarea id="get_template" rows="" cols="" style="display:none">
       <tr>
            <td style="text-align:center;width:5%"></td>
            <td style="text-align:left">
            	
            </td>
            <td style="text-align:center">
            	
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
            <td style="text-align:left" id="ddd">
            	 <input type="text" style="width:400px" msg="请选择服刑人员！" name="personNames" class="add"></input>            	 
            	 <input type="text" style="display:none" name="personIds"></input>
            </td>
             <td style="text-align:center;width:10%">
            	<input class="Wdate" msg="开始时间不能为空！" name="startDate" id="startDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,maxDate:'#F{$dp.$D(\'endDate\')}'})" maxlength="16"/> 至
          	    <input class="Wdate" name="endDate" id="endDate" msg="结束时间不能为空！" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',enableInputMask:false,minDate:'#F{$dp.$D(\'startDate\')}'})" maxlength="16"/>
            </td>
            <td style="text-align:center;width:5%">
            	<a href='javascript:void(0);' class='a-style'>保存</a>&nbsp;&nbsp;
            	<a href='javascript:void(0);' class='deleteImg'>取消</a>
            </td>
         </tr>       
      </textarea>
    </div>
</div>
<%@include file="/data/leftEnd.jsp"%>
<%@include file="/data/bottom.jsp"%>
</body>
</html>