(function ($) {
"use strict";
$.supervision = $.supervision || {};
$.supervision.report = $.supervision.report || {};
$.extend($.supervision.report,{
	init: function(container,opts){
		var me = this;
		$(container).append( $('<tr><td id="report" style="width:100%" valign="top"></td></tr>'));
		if(opts.id){
			var data = me.ajaxGet({
				url:CONTEXT_PATH +'/data/homepage/reportDetail.action?id='+opts.id,
				cache:false
			});
			me.report("#report",3,30,data);
		}
		if(opts.orgId){
			var data = me.ajaxGet({
				url:CONTEXT_PATH +'/data/supervision/orgReport.action?orgId='+opts.orgId,
				cache:false
			});
			me.report("#report",3,30,data);
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
	report: function(container,colcount,percentage,data){
		var me=this;
		var tempTr=null;
		if ($.isArray(data)){
			var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
			$(container).append($toptable);
			var tempspan=2;
			$.each(data, function(i, item){
				$("#title").text("您当前的位置：执法督察 -> "+this.name);
				var tr=$('<tr></tr>'),td=$('<td></td>');
				if(i%colcount==0){
					$($toptable).append(tr);
					tempTr=tr;
				}else{
					tr=tempTr;
				}
				var containerId = $.fields.random('report'); 
				
				var $div = $("<td  style=\"width: "+percentage+"%;\" ></td>");
				var $table = $('<table cellspacing="0" cellpadding="0" class="content" style="table-layout:fixed"></table>');
				var $th=$('<tr><th width="5%"><img src="'+CONTEXT_PATH+'/images/index/biaoti_img.jpg" /></th><th width="95%">'+this.name+'</th></tr>');
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
				item.link=CONTEXT_PATH+item.link;
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
	}
});
})(jQuery);