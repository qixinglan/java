(function ($) {
"use strict";
$.homepage = $.homepage || {};
$.extend($.homepage, {
	init:function(container){
		var me =this;
		if("other"!=ORG_TYPE){
			if("sj"==ORG_TYPE){
				$(container).append( $('<tr><td id="report" style="width:100%" valign="top"></td></tr>'));
				me.report("#report",2,48,{org:'sj',height:'400px'});
			}else{
				$(container).append( $('<tr><td id="todo" valign="top" style="width: 276px;"></td><td id="report" valign="top"></td></tr>'));
				me.todo($("#todo"));
				me.report("#report",2,48);
			}
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
	todo: function(container){
		var me = this;
		var data = me.ajaxGet({
			url:CONTEXT_PATH +'/data/homepage/mineData.action',
			cache:false
		});
		if($.isArray(data)&&data.length>0){
			var table = $('<table cellspacing="0" cellpadding="0" class="content"></table>');
			$(container).append(table);
			var body=$('<tbody valign="top"></tbody>');
			$(table).append(body);
			var total = 0;
			var $todoCount = $('<tr><th width="15px;"></th><th id="todoCount">待办事项</th></tr>');
			$(body).append($todoCount);
			var $todoContent = $('<tr><td colspan="2"></td></tr>');
			$(body).append($todoContent);
			var $list = $('<div class="mokuai"></div>');
			$('td',$todoContent).append($list);
			$.each(data, function(i, item){
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
				$('th:last', $todoCount).text("待办事项("+total+")");
			}
		}
	},
	drawReport:function(data,containerId,options){
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
		options = options||{};
		var option = $.extend({
			width:'95%',
			height: options.height || '500px',
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
		    var tempVsSalesChart = new FusionCharts(option);
		    tempVsSalesChart.render();
	},
	report: function(container,colcount,percentage,options){
		var me=this;
		options = options || {};
		var data = me.ajaxGet({
			url:CONTEXT_PATH +'/data/homepage/reportData.action',
			cache:false
		});
		var tempTr=null;
		if ($.isArray(data)){
			var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
			$(container).append($toptable);
			var  more=false;
			var tempspan=2;
			if(data.length==1){
				more=true;
				tempspan=3;
			}
			var org = options.org;
			var length = data.length;
			if(options.org=='sj'){
				var tempDate = new Array;
				tempDate[0] = {detail: "全市电子监管人数:<span id='zjryCount'></span>人",name: "全市电子监管人员统计"};
				for(var i=0;i<length;i++){
					tempDate[i+1]=data[i];
				}
				data = tempDate;
			}
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
				var $table = $('<table cellspacing="4px" cellpadding="0" class="content"></table>');
				if(more){
					var $th=$('<tr><th width="2%" style="padding-left:3px"></th><th width="93%">'+this.name+'</th><th width="5%" style="text-align:right;padding-right:10px"><a href="'+CONTEXT_PATH+'/data/homepage/report.jsp" style="color:white;">更多</a></th></tr>');
				}else{
					var $th=$('<tr><th width="2%" style="padding-left:3px"></th><th width="93%">'+this.name+'</th><th width="5%"  style="text-align:right;padding-right:10px"><a class="ui_max" href="javascript:$.homepage.max('+item.id+',\''+item.name+'\');"></a></th></tr>');
					if(org=='sj' && i==0){
						$th=$('<tr><th width="2%" style="padding-left:3px"></th><th width="93%">'+this.name+'</th><th width="5%" style="text-align:right;padding-right:10px"><a class="ui_max" href="javascript:$.homepage.max('+item.id+',\''+item.name+'\',\'sjmap\');"></a></th></tr>');
					}else if(org=='sj' && i==3){
						$th=$('<tr ><th width="2%" style="padding-left:3px"></th><th width="88%">'+this.name+'</th><th  width="10%" style="text-align:right;padding-right:10px"><a style="vertical-align:middle;color:white;margin-right:5px" href="'+CONTEXT_PATH+'/data/homepage/report.jsp" >更多</a><a class="ui_max"  style="vertical-align:middle" href="javascript:$.homepage.max('+item.id+',\''+item.name+'\');"></a></th></tr>');
					}
				}
				tr.append($div);
				$div.append($table);
				$table.append($th);
				if(org=='sj'){
					if(i==3){
						tempspan=4;	
					}else{
						tempspan=3;	
					}
				}
				var $report=$('<tr><td colspan="'+tempspan+'" id="'+containerId+'"></td></tr>');
				$table.append($report);
				var counttr='<tr><td colspan="'+tempspan+'"><div style="background-color:#e5ebef; height:35px; width:100%;">'+
					'<div style="float:left; height:35px; width:15px;"></div><div style="float:left; height:20px; font-size:14px;'+
					'color:#46474C; line-height:20px; margin-top:8px; width:80%; min-width:300px;">'+this.detail+'</div>'+
				'</div></td></tr>';				
				$table.append($(counttr));
				if(org=='sj' && i==0){
					me.drawMap(item,containerId);
				}else{
					me.drawReport(item,containerId,options);
				}
			});
		}
	},
	showReport:function(container){
		var me = this;
		var data = me.ajaxGet({
			url:CONTEXT_PATH +'/data/homepage/reportTitle.action',
			cache:false
		});
		if ($.isArray(data)){
			var $div=$('<div class="container-top"></div>');
			var $ul=$('<ul class="tabpage-label-container"></ul>');
			$(container).append($div);
			$(container).append($('<div class="container-middle"></div>'));
			$div.append($ul);
			$div.append($('<div style="clear:both;"></div>'));
			$.each(data, function(i, item){
				var $li=$('<li  class="tabpage-label">'+item.shortName+'</li>');
				$ul.append($li);
				$li.bind("click",function(){
					$('li', $div).removeClass("tabpage-label-selected");
					$(this).addClass("tabpage-label-selected");
					var data = me.ajaxGet({
						url:CONTEXT_PATH +'/data/homepage/reportDetail.action',
						data:{id:item.id},
						cache:false,
						type:"POST"
					});
					$(".container-middle").empty();
					var tempTr=null;
					if ($.isArray(data)){
						var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
						$(".container-middle").append($toptable);
						var tempspan=2;
						$.each(data, function(i, item){
							var tr=$('<tr></tr>'),td=$('<td></td>');
							if(i%2==0){
								$($toptable).append(tr);
								tempTr=tr;
							}else{
								tr=tempTr;
							}
							var containerId = $.fields.random('report'); 
							
							var $div = $("<td  style=\"width: "+50+"%;\" ></td>");
							var $table = $('<table cellspacing="0" cellpadding="0" class="content" style="height:100%;width:100%;margin:0;"></table>');
							//<th width="5%"><a href="javascript:$.homepage.max('+item.id+',\''+item.name+'\');" style="color:white;">最大化</a></th>
							var $th=$('<tr><th width="2%" style="padding-left:3px"><th width="98%">'+this.name+'</th></tr>');
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
				});
			});
			$('li:first', $div).trigger("click");
		}
	},
	max:function(id,title,flag){
		var me = this;
		flag = flag||'';
		var container = $('<div class="popup-content-container"></div>');
		var containerId = $.fields.random("popup-div");
		container.attr('id', containerId);
		var popupOptions = {};
		popupOptions = $.extend({
			width : '1280px',
			height: '768px',
			fix : true,
			lock : false,
			max:false,
			title: popupOptions.title||title ,
			cover:true,
			content: container[0].outerHTML,
			cancelVal : '关闭',
			cancel : true,
			okVal: '保存',
			ok: false,
			init: function(){
				var $toptable = $('<table cellspacing="0" cellpadding="0" class="reportcontent"></table>');
				$("#"+containerId).append($toptable);
				var data = new Array;
				if(flag=='sjmap'){
					data[0] = {detail: "全市电子监管人数:<span id='zjryCount'></span>人",name: "全市电子监管人数"};
				}else{
					data = me.ajaxGet({
						url:CONTEXT_PATH +'/data/homepage/reportDetail.action',
						data:{id:id},
						cache:false,
						type:"POST"
					});
				}
				if ($.isArray(data)){
					$.each(data, function(i, item){
						var tr=$('<tr></tr>');
						$($toptable).append(tr);
						var reportId = $.fields.random('report'); 
						var $div = $("<td  style=\"width: "+50+"%;\" ></td>");
						var $table = $('<table cellspacing="0" cellpadding="0" class="content"></table>');
						var $th=$('<tr><th width="5%"><img src="'+CONTEXT_PATH+'/images/index/biaoti_img.jpg" /></th><th width="95%"></th></tr>');
						tr.append($div);
						$div.append($table);
						$table.append($th);
						var $report=$('<tr><td colspan="2" id="'+reportId+'"></td></tr>');
						$table.append($report);
						var counttr='<tr><td colspan="2"><div style="background:url('+CONTEXT_PATH+'/images/index/shuj_bg.jpg) top repeat-x; height:31px; width:100%;">'+
							'<div style="float:left; height:31px; width:40px;"><img src="'+CONTEXT_PATH+'/images/index/ren.jpg" /></div><div style="float:left; height:20px; font-size:14px;'+
							'color:#46474C; line-height:20px; margin-top:8px; width:80%; min-width:300px;">'+item.detail+'</div>'+
						'</div></td></tr>';				
						$table.append($(counttr));
						if(flag=='sjmap'){
							me.drawMaxMap(item,reportId);
						}else{
							me.drawReport(item,reportId);
						}
					});
				}
				if(flag=='sjmap'){
					$.ajax({
					    url:"dwjk/cityViewResult.action",
						type:"post",
						async:false,
						success:function(response){	
							var zjryCount=0;
							$(response).each(function(){									
							    var orgid=this[0];
							    if(orgid)
							    {		
							      var select= $(".dwjk-box1[id="+orgid+"]");
							      if(select.length>0)
							      {
							        zjryCount+=(this[3]?this[3]:0);
							      }
							      var c=$("<span>"+(this[3]?this[3]:0)+"</span>");
							      select.append(c);			
							    }								    
							});
							$("#zjryCount").append(zjryCount);
							$('.dwjk-box1').attr('style','width:35px');
						}						    
				  });
				}
			}
		}, popupOptions);
		$.dialog(popupOptions).max();
	},
	drawMap:function(data,containerId){
		$("#"+containerId).children().remove();
//			var counttr='<img src="../images/earth.jpg" usemap="#Map" border="0" style="width:670px;height:400px"/>';
		var counttr='<div class="dwjk-sybox"><canvas style="width: 550px; height: 400px; position: absolute; left: 0px; top: 0px; padding: 0px; border: 0px;" height="400" width="550"/>'
			+'<img src="../images/earth.jpg" usemap="#Map" border="0" style="width:670px;height:400px"/>';
		counttr+='<div class="dwjk-boxsy dwjksy-hr" id="187" ></div>'//怀柔 -->        
        +'<div class="dwjk-boxsy dwjksy-yq" id="188"></div>'//延庆 -->        	
        +'<div class="dwjk-boxsy dwjksy-cp" id="198"></div>'//昌平 -->        	
        +'<div class="dwjk-boxsy dwjksy-mtg" id="183"></div>'//门头沟 -->        
        +'<div class="dwjk-boxsy dwjksy-hd" id="195"></div>'//海淀 -->
        +'<div class="dwjk-boxsy dwjksy-sjs" id="194"></div>'//石景山 -->
        +'<div class="dwjk-boxsy dwjksy-dc" id="192"></div>'//东城 -->
        +'<div class="dwjk-boxsy dwjksy-xc" id="193"></div>'//西城 -->
        +'<div class="dwjk-boxsy dwjksy-cy" id="196"></div>'//朝阳 -->     
        +'<div class="dwjk-boxsy dwjksy-fs" id="197" ></div>'// 房山-->        
        +'<div class="dwjk-boxsy dwjksy-dx" id="185"></div>'//大兴 -->        	
        +'<div class="dwjk-boxsy dwjksy-my" id="186"></div>'//密云 -->        	
        +'<div class="dwjk-boxsy dwjksy-sy" id="181"></div>'//顺义 -->        	
        +'<div class="dwjk-boxsy dwjksy-pg" id="182"></div>'//平谷 -->        	
        +'<div class="dwjk-boxsy dwjksy-tz" id="184" ></div>'//通州 -->        	
        +'<div class="dwjk-boxsy dwjksy-ft" id="190"></div>';//丰台 -->  
        counttr+='</div>';      
		$("#"+containerId).append($(counttr));
	},
	drawMaxMap:function(data,containerId){
		$("#"+containerId).children().remove();
		//var counttr='<img src="../images/earth.jpg" usemap="#Map" border="0" style=""/>';
		var counttr='<div class="dwjk-box"><canvas style="width: 1102px; height: 850px; position: absolute; left: 0px; top: 0px; padding: 0px; border: 0px;" height="850" width="1102"/>'
			+'<img src="../images/earth.jpg" usemap="#Map" border="0" style="width:1102px;height:850px"/>';
		counttr+='<div class="dwjk-box1 dwjkmax-hr" id="187" ></div>'//怀柔 -->        
        +'<div class="dwjk-box1 dwjkmax-yq" id="188"></div>'//延庆 -->        	
        +'<div class="dwjk-box1 dwjkmax-cp" id="198"></div>'//昌平 -->        	
        +'<div class="dwjk-box1 dwjkmax-mtg" id="183"></div>'//门头沟 -->        
        +'<div class="dwjk-box1 dwjkmax-hd" id="195"></div>'//海淀 -->
        +'<div class="dwjk-box1 dwjkmax-sjs" id="194"></div>'//石景山 -->
        +'<div class="dwjk-box1 dwjkmax-dc" id="192"></div>'//东城 -->
        +'<div class="dwjk-box1 dwjkmax-xc" id="193"></div>'//西城 -->
        +'<div class="dwjk-box1 dwjkmax-cy" id="196"></div>'//朝阳 -->     
        +'<div class="dwjk-box1 dwjkmax-fs" id="197"></div>'// 房山-->        
        +'<div class="dwjk-box1 dwjkmax-dx" id="185"></div>'//大兴 -->        	
        +'<div class="dwjk-box1 dwjkmax-my" id="186"></div>'//密云 -->        	
        +'<div class="dwjk-box1 dwjkmax-sy" id="181"></div>'//顺义 -->        	
        +'<div class="dwjk-box1 dwjkmax-pg" id="182"></div>'//平谷 -->        	
        +'<div class="dwjk-box1 dwjkmax-tz" id="184" ></div>'//通州 -->        	
        +'<div class="dwjk-box1 dwjkmax-ft" id="190"></div>';//丰台 -->  
		counttr+='</div>';
		$("#"+containerId).append($(counttr));
	}
});
})(jQuery);