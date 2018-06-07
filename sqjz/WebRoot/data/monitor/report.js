(function ($) {
"use strict";
$.report = $.report || {};
$.extend($.report,{
	init: function(action, options){
		var me = this;
		me.config = $.extend(me.config, options);
		me[action].call(me);
	},
	fusioncharts:function(opts){
		var option = $.extend({
			width:'100%',
			height:'250',
			renderAt:'',
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
	sysReport:function(){
		var me=this;
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 2,fields:[{},{},{},{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		$($(".chart").find("td")[1]).attr("id","chart-container1")
		$($(".chart").find("td")[2]).attr("id","chart-container2")
		$($(".chart").find("td")[3]).attr("id","chart-container3")
		var serverDataset = [];
		var serverCategory=[];
		var lbsDataset = [];
		var lbsCategory=[];
		var netDataset = [];
		var netCategory=[];
		var dbDataset = [];
		var dbCategory=[];
		var options = {
			   type: 'POST',
			   title:"服务器状态趋势分析",
			   async:true,
			   url: CONTEXT_PATH+'/data/monitor/'+'report.action',
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   if (data){
					   if(data.serverCategory){
						   serverCategory=[{category:data.serverCategory}];
					   }
					   if(data.serverDataset){
						   serverDataset=data.serverDataset;
					   }
					   if(data.lbsCategory){
						   lbsCategory=[{category:data.lbsCategory}];
					   }
					   if(data.lbsDataset){
						   lbsDataset=data.lbsDataset;
					   }
					   if(data.netCategory){
						   netCategory=[{category:data.netCategory}];
					   }
					   if(data.netDataset){
						   netDataset=data.netDataset;
					   }
					   if(data.dbCategory){
						   dbCategory=[{category:data.dbCategory}];
					   }
					   if(data.dbDataset){
						   dbDataset=data.dbDataset;
					   }
				   }
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'msline',renderAt:'chart-container',title:"服务器状态趋势图（%）",categories:serverCategory,dataset:serverDataset});
				   me.fusioncharts({type: 'msline',renderAt:'chart-container1',title:"系统网络状态趋势图（%）",categories:netCategory,dataset:netDataset});
				   me.fusioncharts({type: 'msline',renderAt:'chart-container2',title:"数据库趋势图（%）",categories:dbCategory,dataset:dbDataset});
				   me.fusioncharts({type: 'msline',renderAt:'chart-container3',title:"LBS服务趋势图（%）",categories:lbsCategory,dataset:lbsDataset});
			   }
		};
		var dateStr = me.config.endDate;
		var dateStrt = me.config.startDate;
		$("#stime").val(dateStrt);
		$("#etime").val(dateStr);
		$("#searchBtn").click(function(){
			var stime = $("#stime").val();
			var etime = $("#etime").val();
			options.data="stime="+stime+"&etime="+etime;
			if($.fields.validateForm("#searchForm")){
				$.ajax(options);
			}
		});
		$("#resetBtn").click(function(){
			$("#stime").val(dateStrt);
			$("#etime").val(dateStr);
			$("#searchBtn").click();
		});
		$("#searchBtn").click();
	},
	locationReport:function(){
		var me=this;
		$.container.generate(".chart",{
			type: 'row',
			items:[{fieldCols: 1,fields:[{}]}]
		});
		$(".chart").find("td").first().attr("id","chart-container");
		var locationCategory = [];
		var locationDataset = [];
		var options = {
			   type: 'POST',
			   title:"服务器状态趋势分析",
			   async:true,
			   url: CONTEXT_PATH+'/data/monitor/'+'report.action?flag=location',
			   cache: false,
			   dataType: 'json',
			   success: function(data, success, http){
				   if (data){
					   if(data.locationCategory){
						   locationCategory=[{category:data.locationCategory}];
					   }
					   if(data.locationDataset){
						   locationDataset=data.locationDataset;
					   }
				   }
				   else{
					   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
				   }
			   },
			   error: function(){
				   $.dialog.error('获取' + (options.title || '') + '信息失败！请稍候再试 .');
			   },
			   complete:function(){
				   me.fusioncharts({type: 'msline',height:'450',renderAt:'chart-container',title:"定位数量统计趋势图",categories:locationCategory,dataset:locationDataset});
			   }
		};
		var dateStr = me.config.endDate;
		var dateStrt = me.config.startDate;
		$("#stime").val(dateStrt);
		$("#etime").val(dateStr);
		$("#searchBtn").click(function(){
			var stime = $("#stime").val();
			var etime = $("#etime").val();
			options.data="stime="+stime+"&etime="+etime;
			if($.fields.validateForm("#searchForm")){
				$.ajax(options);
			}
		});
		$("#resetBtn").click(function(){
			$("#stime").val(dateStrt);
			$("#etime").val(dateStr);
			$("#searchBtn").click();
		});
		$("#searchBtn").click();
	}
});
})(jQuery);