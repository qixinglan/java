(function ($) {
"use strict";
$.supervision = $.supervision || {};
$.extend($.supervision, {
	init:function(container,opts){
		var me =this;
		if("other"!=ORG_TYPE&&"sfs"!=ORG_TYPE){
			$(container).append( $('<tr><td id="todo" valign="top" style="width: 276px;"></td><td id="report" valign="top"></td></tr>'));
			me.listDate($("#todo"),$("#report"),opts);
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
	listDate: function(left,right,opts){
		var me = this;
		var title="各区县司法局督察事项";
		if("qxsfj"==ORG_TYPE){
			title="各司法所督察事项";
		}
		var data = me.ajaxGet({
			url:CONTEXT_PATH +'/data/supervision/mineData.action',
			cache:false
		});
		if(data.left&&data.right){
			if($.isArray(data.left)&&data.left.length>0){
				var table = $('<table cellspacing="0" cellpadding="0" class="content"></table>');
				$(left).append(table);
				var body=$('<tbody valign="top"></tbody>');
				$(table).append(body);
				var total = 0;
				var $todoCount = $('<tr><th width="15px;"></th><th id="todoCount">执法督察事项</th></tr>');
				$(body).append($todoCount);
				var $todoContent = $('<tr><td colspan="2"></td></tr>');
				$(body).append($todoContent);
				var $list = $('<div class="mokuai"></div>');
				$('td',$todoContent).append($list);
				$.each(data.left, function(i, item){
					var $div = $('<div class="mokuai_in"><a><span class="icon"><label class="icon-dq"/></span></a></div>');
					$list.append($div);
					if (item.url){
						$('a', $div).attr('href', CONTEXT_PATH + item.url);
					}
					$('.icon', $div).css('background-image', 'url(' + CONTEXT_PATH + item.icon + ')');
					$('.text', $div).text(item.name || '');
					if (item.total > 0){
						total += item.total;
						$('label', $div).text(item.total);
					}
					else{
						$('label', $div).hide();
					}
				});
				$list.append($('<div style="clear: both"></div>'));
				if(total>0){
					$('th:last', $todoCount).text("执法督察事项("+total+")");
				}
			}
			if(!opts||!opts.report){
				if($.isArray(data.right)&&data.right.length>0){
					var table = $('<table cellspacing="0" cellpadding="0" class="content" ></table>');
					$(right).append(table);
					var body=$('<tbody valign="top"></tbody>');
					$(table).append(body);
					var total = 0;
					var $todoCount = $('<tr><th width="15px;"></th><th id="todoCount">'+title+'</th></tr>');
					$(body).append($todoCount);
					var $todoContent = $('<tr><td colspan="2" ></td></tr>');
					$(body).append($todoContent);
					var $list = $('<div class="mokuai" style="width:98%"></div>');
					$('td',$todoContent).append($list);
					$.each(data.right, function(i, item){
						var $div = $('<div class="mokuai_in" style="width:12.5%;min-width:116px;text-align: center;"><a><span class="icon"><div style="  position: relative;bottom: -90px;">'+item.name+'</div><label class="icon-dq"/></span></a></div>');
						$list.append($div);
						if (item.url){
							$('a', $div).attr('href', CONTEXT_PATH + item.url);
						}
						$('.icon', $div).css('background-image', 'url(' + CONTEXT_PATH + item.icon + ')');
						$('.text', $div).text(item.name || '');
						if (item.total > 0){
							total += item.total;
							$('label', $div).text(item.total);
						}
						else{
							$('label', $div).hide();
						}
					});
					$list.append($('<div style="clear: both"></div>'));
					if(total>0){
						$('th:last', $todoCount).text(title+"("+total+")");
					}
				}
			}else if(data.report){
				me.report("#report",3,30,data.report);
			}
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
	},
	report: function(container,colcount,percentage,data){
		var me=this;
		var tempTr=null;
		if ($.isArray(data)){
			var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
			$(container).append($toptable);
			var  more=false;
			var tempspan=2;
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
					var $th=$('<tr><th width="2%" ></th><th width="93%">'+this.name+'</th><th width="5%" style="text-align:center;"><a href="'+CONTEXT_PATH+'/data/homepage/report.jsp" style="color:white;">更多</a></th></tr>');
				}else{
					var $th=$('<tr><th width="2%"></th><th width="98%">'+this.name+'</th></tr>');
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