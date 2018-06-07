(function ($) {
"use strict";
$.report = $.report || {};
$.extend($.report,{
	init: function(action, options){
		var me = this;
		me[action].call(me);
	},
	
	fusioncharts:function(opts){
		var option = $.extend({
			width:'100%',
			height:'450',
			renderAt:'',
			url:'',
			dataFormat:'json',
			dataSource:{
				chart:{
	        		caption:opts.title||"",
	        		formatNumberScale:false,
	        	},
	        	data:opts.data,
	        	categories:opts.categories,
	        	dataset:opts.dataset
			}
		}, opts || {});
		$("#"+opts.renderAt).children().remove();
		FusionCharts.ready(function () {
		    var tempVsSalesChart = new FusionCharts(option);
		    tempVsSalesChart.render();
		});
	},
	orgFusionCharts:function(title,url,formatter,sb){
		var link;
		var zt;
		if(sb=="jg"){
			zt=$.dictionary.getDict("DZJGSBZT");
			url=CONTEXT_PATH+'/data/sbgl/jgsb/'+url;
			link="jgsbTjList.jsp";
		}
		if(sb=="zf"){
			zt=$.dictionary.getDict("ZFSBZT");
			url=CONTEXT_PATH+'/data/sbgl/ydzf/'+url;
			link="zfsbTjList.jsp";
		}if(sb=="sim"){
			zt=$.dictionary.getDict("SIMSBZT");
			url=CONTEXT_PATH+'/data/sbgl/sim/'+url;
			link="simsbTjList.jsp";
		};
		var me=this;
		$(".chart").empty();//删除多余的
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 2,fields:[{},{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		$(".chart").find("td").last().attr("id","chart-container1");
		var obj = [];
		var options = {
			   type: 'POST',
			   title:title,
			   async:true,
			   url: url,
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				  var sum=0;
				  if (data){
					  obj = [];
				  for(var i in zt){
					  var code=zt[i].code;
					  var name =zt[i].name;
					  var value=0;
					   if(data.length>0){
						   for(var i in data){
							   if(name==formatter(data[i][1])){
								   value=data[i][0];
								   sum+=data[i][0];
							   }
						   }
					   };
					 
					   var temp={
							   name: name,
							   value:value,
						   	   link:link+"?status="+code+"&"+url.substring((url.lastIndexOf('?')+1))
						   };
					   obj.push(temp);
				  }
				  $("#totalNum").text("合计数量:"+sum);
				  }
				  /* if (data){
					   var sum=0;
					   obj = [];
					   if(data.length>0){
						   for(var i in data){
							   sum+=data[i][0];
							   var name =data[i][1];
							   if(formatter){
								   name=formatter(data[i][1]);
							   }
							   var temp={
								   name: name,
								   value:data[i][0],
							   	   link:link+"?status="+data[i][1]+"&"+url.substring((url.lastIndexOf('?')+1))
							   };
							   obj.push(temp);
						   }
					   }else{
						   obj=[{}];
					   }
					   $("#totalNum").text("合计数量:"+sum);
				   }*/
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'column3d',renderAt:'chart-container',title:title+"柱状图",data:obj});
				   me.fusioncharts({type: 'pie3d',renderAt:'chart-container1',title:title+"饼图",data:obj});
			   }
		};
		$.ajax(options);
		return options;
	},
	jgsbzt:function(){
		var me=this;
		var options=me.orgFusionCharts("监管设备状态统计","jgsbzt.action?",$.dictionary.formatter("DZJGSBZT", '不详'),"jg");
	},
	zfsbzt:function(){
		var me=this;
		var options=me.orgFusionCharts("执法终端状态统计","zfsbzt.action?",$.dictionary.formatter("ZFSBZT", '不详'),"zf");
	},
	simsbzt:function(){
		var me=this;
		var options=me.orgFusionCharts("SIM卡状态统计","simsbzt.action?",$.dictionary.formatter("SIMSBZT", '不详'),"sim");
	},
	unittj:function(title,url,sb,children){
		var link;
		if(sb=="jg"){
			url=CONTEXT_PATH+'/data/sbgl/jgsb/'+url;
			link="jgsbTjList.jsp";
		}
		if(sb=="zf"){
			url=CONTEXT_PATH+'/data/sbgl/ydzf/'+url;
			link="zfsbTjList.jsp";
		}if(sb=="sim"){
			url=CONTEXT_PATH+'/data/sbgl/sim/'+url;
			link="simsbTjList.jsp";
		};
		var me=this;
		$(".chart").empty();
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 2,fields:[{},{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		$(".chart").find("td").last().attr("id","chart-container1");
		var obj = [];
		var options = {
			   type: 'POST',
			   title:title,
			   async:true,
			   url:url,
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   var sum=0;
					  if (data){
						  obj = [];
					  for(var i in children){
						  var code=children[i].id;
						  var name=children[i].codeDesc;
						  var value=0;
						  var type="";
						   if(data.length>0){
							   for(var i in data){
								   if(name==data[i].name){
									   value=data[i].num;
									   sum+=data[i].num;
									   type=data[i].type;
								   }
							   }
						   };
						   var temp={
								   name: name,
								   value:value,
								   link:link+'?useUnit='+code+'&useDeviceType='+type
							   };
						   obj.push(temp);
					  }
					  $("#totalNum").text("合计数量:"+sum);
					  }
				   /*if (data){
					   var sum=0;
					   obj = [];
					   if(data.length>0){
						   for(var i in data){
							   sum+=data[i].num;
							   var temp={
								   name:data[i].name,
								   value:data[i].num,
								   link:link+'?useUnit='+data[i].useUnit+'&useDeviceType='+data[i].type
							   };
							   obj.push(temp);
						   }
					   }else{
						   obj=[{}];
					   }
					   $("#totalNum").text("合计数量:"+sum);
				   }*/
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'column3d',renderAt:'chart-container',title:title+"柱状图",data:obj});
				   me.fusioncharts({type: 'pie3d',renderAt:'chart-container1',title:title+"饼图",data:obj});
			   }
		};
		$.ajax(options);
		return options;
	},
	unitsfs:function(){
		var children=$.organization.getData(ORG_ID,{
			allowBlank : true,
			level : 10,
			showRoot : true,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"4,5,6,7,8,9",
			fieldClass:"search-form-field"
		})[0].children;
		return children;
	},
	unitsfj:function(){
		var children=$.organization.getData(ORG_ID,{
			allowBlank : true,
			level : 10,
			showRoot : false,
			showItself : true,
			emptyText : '全部',
			attr:{searchType: "eq"},
			notShowType:"4,5,6,7,8,9",
			fieldClass:"search-form-field"
		});
		return children;
	},
	jgsbsfs:function(){
		var me=this;
		var options=me.unittj("监管设备司法所统计","jgsbsfs.action","jg",me.unitsfs());
	},
	jgsbsfj:function(){
		var me=this;
		var options=me.unittj("监管设备司法局统计","jgsbsfj.action","jg",me.unitsfj());
		
	},
	zfsbsfs:function(){
		var me=this;
		var options=me.unittj("执法终端司法所统计","zfsbsfs.action","zf",me.unitsfs());
	},
	zfsbsfj:function(){
		var me=this;
		var options=me.unittj("执法终端司法局统计","zfsbsfj.action","zf",me.unitsfj());
		
	},
	simsbsfs:function(){
		var me=this;
		var options=me.unittj("SIM卡司法所统计","simsbsfs.action","sim",me.unitsfs());
	},
	simsbsfj:function(){
		var me=this;
		var options=me.unittj("SIM卡司法局统计","simsbsfj.action","sim",me.unitsfj());
	},
	simsbty:function(){
		var me=this;
		var options=me.simtytj("SIM卡类型统计","simsbty.action?",$.dictionary.formatter("SIMTYPE", '不详'));
	},
	jgsbfxry:function(){
		var me=this;
		var options=me.jgsbfxrytj("监管设备佩戴人统计","jgsbfxrytj.action?");
	},
	jgsbfxrytj:function(title,url){
		var link;
		url=CONTEXT_PATH+'/data/sbgl/jgsb/'+url;
		link="jgsbFxryTjList.jsp"+url.substring(url.lastIndexOf("?"));
		var me=this;
		$(".chart").empty();
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 2,fields:[{},{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		$(".chart").find("td").last().attr("id","chart-container1");
		var obj = [];
		var options = {
			   type: 'POST',
			   title:title,
			   async:true,
			   url:url,
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   if (data){
					   var sum=0;
					   obj = [];
					   if(data.length>0){
						   for(var i in data){
							   sum+=data[i].num;
							   var temp={
								   name:data[i].name,
								   value:data[i].num,
								   link:link+"&indexNumber="+i
								  // '?useUnit='+data[i].useUnit+'&useDeviceType='+data[i].type
							   };
							   obj.push(temp);
						   }
					   }else{
						   obj=[{}];
					   }
					   $("#totalNum").text("合计数量:"+sum);
				   }
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'column3d',renderAt:'chart-container',title:title+"柱状图",data:obj});
				   me.fusioncharts({type: 'pie3d',renderAt:'chart-container1',title:title+"饼图",data:obj});
			   }
		};
		$.ajax(options);
		return options;
	},
	
	
	simtytj:function(title,url,formatter){
		var link;
		url=CONTEXT_PATH+'/data/sbgl/sim/'+url;
		link="simsbTjList.jsp";
		var zt=$.dictionary.getDict("SIMTYPE");
		var me=this;
		$(".chart").empty();
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 2,fields:[{},{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		$(".chart").find("td").last().attr("id","chart-container1");
		var obj = [];
		var options = {
			   type: 'POST',
			   title:title,
			   async:true,
			   url:url,
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   var sum=0;
					  if (data){
						  obj = [];
					  for(var i in zt){
						  var code=zt[i].code;
						  var name =zt[i].name;
						  var value=0;
						   if(data.length>0){
							   for(var i in data){
								   if(name==formatter(data[i].type)){
									   value=data[i].num;
									   sum+=data[i].num;
								   }
							   }
						   };
						 
						   var temp={
								   name: name,
								   value:value,
								   link:link+url.substring((url.lastIndexOf('?')))+'&useDeviceType='+code
							   };
						   obj.push(temp);
					  }
					  $("#totalNum").text("合计数量:"+sum);
					  }
				  /* if (data){
					   var sum=0;
					   obj = [];
					   if(data.length>0){
						   for(var i in data){
							   sum+=data[i].num;
							   var temp={
								   name:data[i].name,
								   value:data[i].num,
								   //link:link+'?useUnit='+data[i].useUnit+'&useDeviceType='+data[i].type
								   link:link+url.substring((url.lastIndexOf('?')))+'&useDeviceType='+data[i].type
							   };
							   obj.push(temp);
						   }
					   }else{
						   obj=[{}];
					   }
					   $("#totalNum").text("合计数量:"+sum);
				   }*/
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'column3d',renderAt:'chart-container',title:title+"柱状图",data:obj});
				   me.fusioncharts({type: 'pie3d',renderAt:'chart-container1',title:title+"饼图",data:obj});
			   }
		};
		$.ajax(options);
		return options;
	},
	
	
});
})(jQuery);