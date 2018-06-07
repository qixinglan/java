(function ($) {
"use strict";
$.dwjkchart = $.dwjkchart || {};
$.extend($.dwjkchart, {
	init:function(container,opts){
		var me =this;
		if("other"!=ORG_TYPE&&"sfs"!=ORG_TYPE){
			$(container).empty();
			$(container).append( $('<tr><td id="report" valign="top"></td></tr>'));
			me.listDate($("#report"),opts);
		}
	},
	ajaxGet: function(options){
		var obj = null;
		options = options || {};
		options = $.extend({
			   type: 'GET',
			   async: false,
			   dataType: 'json',
			   cache: false,
			   success: function(data){
				  obj = data;
			   }
		}, options);
		$.ajax(options);
		return obj;
	},
	listDate: function(right,opts){
		var me = this;
		var title="各区县司法局督察事项";
		if("qxsfj"==ORG_TYPE){
			title="各司法所督察事项";
		}
		var data = me.ajaxGet({
			url:CONTEXT_PATH +'/data/dwjk/chartData.action?cCode='+opts.cCode,
			cache:false
		});
		me.report("#report",3,30,data,opts);
	},
	drawReport:function(data,containerId){
		var type="";
		if(1==data.type){
			type="column3d";
		}
		if (2==data.type){
			type="pie3d";
		}
		if (3==data.type){
			type="msline";
		}
		$.each(data.listData,function(i, item){
			if(item.link){
				item.link=CONTEXT_PATH+"/"+item.link;
			}
		});
		var option = $.extend({
			width:'95%',
			height:'500px',
			renderAt:containerId,
			dataFormat:'json',
			type:type,
			dataSource:{
				chart:{
					caption:"",
	        		formatNumberScale:false,
	        		bgcolor:"#FFFFFF",
	        		showborder:"0",
	        		useroundedges:"1",
	        		canvasbgcolor:"#FFFFFF",
	        		canvasbasecolor:"#CCCCCC",
	        		showcanvasbg:"0"
	        	},
	        	data:data.listData,
	        	categories:[{category:data.category}],
	        	dataset:data.dataset
			}
		},{});
		$("#"+containerId).children().remove();
		FusionCharts.ready(function () {
		    var tempVsSalesChart = new FusionCharts(option);
		    tempVsSalesChart.render();
		});
	},
	report: function(container,colcount,percentage,data,opts){
		var me=this;
		var tempTr=null;
		if ($.isArray(data)){
			var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
			$(container).append($toptable);
			var  more=false;
			var tempspan=3;
			$.each(data, function(i, item){
				var tr=$('<tr></tr>'),td=$('<td></td>');
				if(i%colcount==0){
					$($toptable).append(tr);
					tempTr=tr;
				}else{
					tr=tempTr;
				}
				var containerId = $.fields.random('report'); 
				var $div = $("<td  style=\"width: "+percentage+"%;\" ></td>");
				var $table = $('<table cellspacing="0" cellpadding="0" class="content"></table>');
				if(more){
					var $th=$('<tr><th width="2%" ></th><th width="95%" style="text-align:center;">'+this.name+'</th><th width="3%" style="text-align:center;"><a href="'+CONTEXT_PATH+'/data/homepage/report.jsp" style="color:white;">更多</a></th></tr>');
				}else{
					var params = "";
					if(opts.cCode!=""){
						params += "?orgId=" + opts.cCode;
					}
					var $th=$('<tr><th width="2%"></th><th width="90%" style="text-align:left;">'+this.name+'</th><th width="8%" style="text-align:right; padding-right:10px;"><a href="'+CONTEXT_PATH+'/data/dwjk/locationDetail.action'+params+'" style="color:white;">定位监控详情</a></th></tr>');
				}
				tr.append($div);
				$div.append($table);
				$table.append($th);
				var $report=$('<tr><td colspan="'+tempspan+'" id="'+containerId+'"></td></tr>');
				$table.append($report);
				var counttr='<tr><td colspan="'+tempspan+'"><div style="background:url('+CONTEXT_PATH+'/images/index/shuj_bg.jpg) top repeat-x; height:31px; width:100%;">'+
					'<div style="float:left; height:31px; width:40px;"><img src="'+CONTEXT_PATH+'/images/index/ren.jpg" /></div><div style="float:left; height:20px; font-size:14px;'+
					'color:#46474C; line-height:20px; margin-top:8px; width:80%; min-width:300px;">'+this.detail+'</div>'+
				'</div></td></tr>';				
				$table.append($(counttr));
				me.drawReport(item,containerId);
			});
		}
	}
});
})(jQuery);