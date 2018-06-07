<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子监管设备自检</title>
<!--[if IE 7]>
<script type='text/javascript' src='${ctx}/js/excanvas.js'></script>
<![endif]-->
<!--[if IE 8]>
<script type='text/javascript' src='${ctx}/js/excanvas.js'></script>
<![endif]--> 
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />

<script language="javascript" src="${ctx}/js/jquery.min.js"></script>
<script language="javascript" src="${ctx}/js/jquery.knob.min.js"></script>
<script language="javascript" src="${ctx}/js/dialog/lhgdialog.min.js?skin=blue" type="text/javascript"></script>

<style type="text/css">
body{
	margin: 0px;
	background-color:transparent !important;
}

.jindu{
		  position:absolute;
		  top:50%;
		  left: 50%;
		  margin-top: -325px;
		  margin-left: -372px;
	      height:650px; 
		  width:744px;
	  }
.top{
      width:744px;
      height:252px;
      background:url(${ctx}/images/check/top.png) left top no-repeat;
      }
.lianj{
      float:left;
      margin-top:43px; 
	  margin-left:90px; 
	  width:240px; 
	  height:170px;
	  }
.close{
      float:right;
      margin-top:43px; 
	  margin-right:55px; 
	  width:36px; 
	  height:36px;
	  text-shadow: 5px 5px 5px #FF0000;
	  opacity: .8;
	  filter: alpha(opacity=80);
}
.yuan{
	  background:url(${ctx}/images/check/yuan.png) left top no-repeat;
      float:right;
      margin-top:55px; 
	  margin-right:0px; 
	  width:158px; 
	  height:158px;
	  }
.mid{
	overflow:hidden;
     width:720px;
	 height:294px;
	 background:url(${ctx}/images/check/mid.png) top left repeat-y;
	 background-position-y: -10px;
	 }
.check-items{
	width:744px;
	 height:294px;
	 overflow-y:scroll;
}
.xiangm{
     width:630px;
	 height:67px;
	 margin-left:56px;
	 border:1px #cdccde solid;
	 background:url(${ctx}/images/check/jc_bg.png) left top repeat-x;
	 }
.xiangm_1{
     float:left; 
	 width:44px; 
	 height:67px;
	 }
.xiangm_2{
     float:left; 
	 margin-top:10px; 
	 width:49px; 
	 height:50px;
	 overflow: hidden;
	 }
.xiangm_3{
     float:left;
	 margin-left:18px;
	 width:510px; 
	 height:67px;
	 }
.xm1{
     float:left; 
	 margin-top:13px;
	 width:117px; 
	 height:19px; 
	 font-size:18px; 
	 color:#686866; 
	 font-weight:bolder;
	 }
.xm2{
     float:left; 
	 margin-top:14px; 
	 margin-left:10px; 
	 width:260px; 
	 height:16px; 
	 font-size:14px; 
	 color:#8d8d8d; 
	 }
.xm3{
     float:right; 
	 margin-top:13px; 
	 width:55px; 
	 height:23px;
	 }
.xm4{ 
     margin-top:12px; 
	 width:500px;
	 overflow:hidden;
	 background-color: #b8b8b8;
	 border: 1px solod #C2C2C2;
	 border-radius: 4px;
	 background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, 0.2), transparent 60%);
	background-image: -moz-linear-gradient(top, rgba(0, 0, 0, 0.2), transparent 60%);
	background-image: -o-linear-gradient(top, rgba(0, 0, 0, 0.2), transparent 60%);
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.2), transparent 60%);
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.2), 0 1px rgba(255, 255, 255, 0.6);
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.2), 0 1px rgba(255, 255, 255, 0.6);
	 }
.process-bar{
	width: 0px;
	height: 6px;
	border-radius: 4px;
	background-color: #0EA0F1;
	background: #1997e6;
	background-image: -webkit-linear-gradient(top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0) 60%, rgba(0, 0, 0, 0) 61%, rgba(0, 0, 0, 0.2)), -webkit-linear-gradient(left, #147cd6, #24c1fc);
	background-image: -moz-linear-gradient(top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0) 60%, rgba(0, 0, 0, 0) 61%, rgba(0, 0, 0, 0.2)), -moz-linear-gradient(left, #147cd6, #24c1fc);
	background-image: -o-linear-gradient(top, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0) 60%, rgba(0, 0, 0, 0) 61%, rgba(0, 0, 0, 0.2)), -o-linear-gradient(left, #147cd6, #24c1fc);
	background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.3), rgba(255, 255, 255, 0) 60%, rgba(0, 0, 0, 0) 61%, rgba(0, 0, 0, 0.2)), linear-gradient(to right, #147cd6, #24c1fc);
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px rgba(0, 0, 0, 0.2), inset 0 0 0 1px rgba(0, 0, 0, 0.2);
	box-shadow: inset 0 1px rgba(0, 0, 0, 0.2), inset 0 0 0 1px rgba(0, 0, 0, 0.2);
}
.total-process{
	top: 0px;
	left: 0px;
	width: 142px;
	height: 142px;
	margin-top: -150px;
	margin-left: auto;
	margin-right: auto;
}

.process-value{
	width: 100%;
	height: 100%;
	text-align: center;
	vertical-align: middle;
}
.process-value span{
	height:38px;
	line-height: 38px;
	margin-top: 60px;
	display: inline-block;
	font-size: 40px;
}
.process-done-text{
	margin-top: -28px;
	margin-right: 10px;
	font-size: 24px;
	color: white;
	text-align: right;
}
.process-total{
	height:37px;
	margin-top: 215px;
	width: 0%;
	background: -webkit-linear-gradient(#8E97DA,#988BAF);
	background: -moz-linear-gradient(#8E97DA,#988BAF);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#8E97DA, endColorstr=#988BAF);
}

.process-total-container{
	margin-bottom: -252px;
	height:252px;
	border: none;
	width: 649px;
	margin-left: 47px;
	overflow: hidden;
}
.gap{
	background:url(${ctx}/images/check/mid.png) top left repeat-y;
}
.actions{
	text-align: left;
 	background:url(${ctx}/images/check/mid.png) top left repeat-y;
 	background-position-y: -10px;
	height: 40px;
}
.btn{
	margin-left: 5px;
	margin-right: 5px;
}
.buttons{
	height: 100%;
	display: inline-block;
	float:right;
	float: right;
	margin-right: 52px;
}

.buttons input{
	margin-top: 12px;
}
.check-items-nav{
	float:left;
	height: 25px;
	width: 25px;
	margin-top: 10px;
	width:26px;
}
.check-items-navbar{
	height: 100%;
	margin-left: 58px;
	display: inline-block;
}
.bottom{
     width:744px;
	 height:71px;
	 }
.image-thumb{
	height: 25px;
	width: 25px;
	zoom: 1;
}
.image-thumb-result{
	height: 10px;
	width: 10px;
	zoom:1;
	margin-top: -36px;
	margin-left: 16px;
}
</style>
</head>

<body>
<%
	String deviceCode = request.getParameter("deviceCode");
	if (deviceCode == null){
		deviceCode = "";
	}
%>

<script type="text/javascript">
	//设备编号,div的ID前缀,每一项的超时时间，div的起始索引和最大边界,迭达范围[start, end)
	function playItemFinished(success, msg, $div, callback){
		var $image = $('.xiangm_2 img', $div);
		var $copied = $image.clone();
		$('body').append($copied);
		$copied.css({
			position: 'absolute',
			height: $image.outerHeight(),
			width: $image.outerWidth(),
			'top': $image.offset().top,
			'left': $image.offset().left
		});
		var $resultThumb = $('#result-thumb');
		var $li = $('<li class="check-items-nav"></li>');
		$li.appendTo($resultThumb);
		var $a = $('<a href="#' + $div.attr('id') +'"></a>');
		$a.appendTo($li);
		$a.attr('title', msg);
		$copied.animate({
			height: $li.outerHeight(),
			width: $li.outerWidth(),
			'top': $li.offset().top,
			'left': $li.offset().left
			}, 500, 'linear', function(){
				$copied.attr('style', null);
				$copied.addClass('image-thumb');
				$a.append($copied);
				var image = success ? 'yes' : 'no';
				$a.append('<img class="image-thumb-result" src="${ctx}/images/' + image + '.png"/>');
				callback();
		});
	};
	function setTotalProcess(start, total, successedResult){
		var value = Math.floor(100 * successedResult / total);
		if (value > 0){
			$("#circle").val(value).trigger('change');
		}
		else{
			$("#circle").val('0.01').trigger('change');
		}
		$('.process-value span').text(value + '%');
		$('.process-done-text').text(start + '/' + total);
		if (start == 1 && successedResult != 1){
			//第一检测项失败
			//start = total;
			//$('body').data('canceled', true);
		}
		if (start == total){
			$('#total-title').attr('src', '${ctx}/images/check/yiwc.png');
			$('#cancelBtn').val('放弃使用');
			if (successedResult == total){
				$('#enableBtn').show();
				$('#retryBtn').hide();
			}
			else{
				$('#enableBtn').hide();
				$('#retryBtn').show();
			}
		}
		if (start < total && start > 2){
			window.location.hash = 'check_' + (start - 1);
		}
	};
	function check(){
		var deviceCode = '<%=deviceCode%>';
		var idprefix = 'check_';
		var timeout = 3000;
		var total = 15;
		var successedResult = 0;
		setTotalProcess(0, total, successedResult);
		var checkNext = function(start, end){
			var result = false;
			var $div = $('#' + idprefix + start);
			if ($div.length == 0 || $('body').data('canceled') == true){
				return;
			}; 
			$div.focus();
			var reachedProcess = 100 * start / total + '%';
			var title = $('.xm2', $div).text().substring(1);//。xm2中的文本将作为提示标题
			$('.xm2', $div).data('title', title);
			$('.xm2', $div).html(title + '中...');
			$.ajax({
				type : 'get',
				url :  '${ctx}/data/jzgl/dagl/deviceCheck.action',
				async : true,
				cache: false,
				dataType: 'json',
				data: {type: start, deviceCode:deviceCode},
				timeout: timeout,
				beforeSend: function(){
					$('.process-bar', $div).animate({width: '100%'}, timeout, 'linear');
					$('.process-total').animate({width: reachedProcess}, timeout, 'linear');
				},
				complete: function(){
					$('.process-bar', $div).stop();
					$('.process-total').stop();
					//检测完成后，进度条走满并标记结果,result如果类型改变，此处修改提示
					var msg = '';
					var status = '';
					var success = false;
					if (result && result.success){
						successedResult += 1;
						success = true;
						msg = title + '成功 ' + result.msg;
						status = '<img src="${ctx}/images/check/zhuangt_2.png"/>';
					}
					else{
						var error = (result && result.success == false) ? result.msg : '服务器响应超时';
						msg = title + '失败 ' + error;
						status = '<img src="${ctx}/images/check/zhuangt_3.png"/>';
					}
					$('.process-total').animate({width: reachedProcess}, 100, 'linear');
					$('.process-bar', $div).animate({width: '100%'}, 100, 'linear', function(){
						$span = success ? $('<span style="color:green"></span>') : $('<span style="color:red"></span>');
						$span.text(msg);
						$('.xm2', $div).empty();
						$('.xm2', $div).append($span);
						$('.xm3', $div).html(status);
						playItemFinished(success, msg, $div, function(){
							setTotalProcess(start, total, successedResult);
							start += 1;
							if (start < end){
								checkNext(start, end);
							}
						})
					});
				},
				success : function(response) {
					result = response;
				},
			});
		};
		checkNext(1, total + 1);
	};
	$(function() {
		$('.close').click(function(){
			$('body').data('canceled', true);
			$.dialog({
				title: '关机确认',
				icon: 'confirm.png',
				fixed: true,
				lock: true,
				width:250,
				content: '设备[<%=deviceCode%>]自检未完成(通过),如果该设备不再使用,请选择<关机>。是否关机？',
				resize: false,
				okVal: '关机',
				cancelVal: '不关机',
				parent: parent || null,
				ok: function(here){
					//TODO: 此处发送关机的AJAX请求
					$.ajax({
						type : 'get',
						url :  '${ctx}/data/jzgl/dagl/deviceClose.action',
						async : true,
						cache: false,
						dataType: 'json',
						data: {deviceCode:'<%=deviceCode%>'},
						complete: function(){
							$('body').trigger('finished', false);
						}
					});
				},
				cancel: function(here){
					$('body').trigger('finished', false);
				}
			});
		});
		$('#cancelBtn').click(function(){
			$('.close').trigger('click');
		});
		$('#retryBtn').click(function(){
			$('#total-title').attr('src', '${ctx}/images/check/jinxz.png');
			$('#result-thumb a img:first').trigger('click');//显示第一个检测项
			$('body').data('canceled', false);//重置取消标记
			$('#result-thumb').empty();//清空检测结果缩略菜单
			$('.process-bar').each(function(){//进度条全部清空
				$(this).css('width', 0);
			});
			$('.xm2').each(function(){//检测状态文本
				$(this).text('待' + ($(this).data('title') ||  $(this).text().substring(1)));
			})
			$('.process-total').animate({width: 0}, 200, 'linear', function(){
				$('#cancelBtn').val('取消自检');
				check();
			});
		});
		$('#enableBtn').click(function(){
			$('body').trigger('finished', true);
		});
		$('#enableBtn').hide();
		$('#retryBtn').hide();
		$("#circle").knob({
			width: 142,
			height: 142,
			thickness:0.23,
			displayInput: false,
			fgColor: '#4775C2',
			bgColor:'#CEC6C0',
			readOnly: true,
			min: 0,
			max: 100,
			inputColor: 'red',
			angleArc:360, //进度条的弧线角度
			angleOffset:180,//起始位置的角度
		})
		check();
	});
	
</script>
<div class="jindu">

  <div class="top">
  		  <div class="process-total-container"><div class="process-total"></div><div class='process-done-text'></div></div>
	      <div class="lianj"><img src="${ctx}/images/check/lianjie.png" /></div>
		  <div class="close"><a title="取消"><img src="${ctx}/images/check/close.png" /></a></div>
		  <div class="yuan">
		  	<div class="process-value"><span>0%</span></div>
		  	<div class="total-process"><input id="circle" value="0.001"></input></div>
		  </div>
		  <div style="margin-left:255px;height:252px;">
		  	<img id="total-title" src="${ctx}/images/check/jinxz.png"/>
		  </div>
  </div>
  <div style="height:14px" class="gap"></div>
  <div class="mid">
        <div class="check-items">
        <div class="xiangm" id='check_1'>
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_connect.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi1.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		
		<div class="xiangm" id='check_2' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_time.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi2.png" /></div>
				  <div class="xm2">待设置</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		
		<div class="xiangm" id='check_3' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_time.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi3.png" /></div>
				  <div class="xm2">待设置</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		
		<div class="xiangm" id='check_4' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_report.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi4.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_5' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_silent.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi5.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_6' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_flash.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi6.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_7' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_battary.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi7.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_8' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_battary.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi8.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_9' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_cpu.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi9.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_10' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_memory.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi10.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_11' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_phone.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi11.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_12' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_close.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi12.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_13' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_work.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi13.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_14' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_work.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi14.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_15' style="margin-top:6px;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/zj_zd.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi15.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		<div class="xiangm" id='check_16' style="margin-top:6px;display: none;">
		     <div class="xiangm_1"><img src="${ctx}/images/check/jc_left.png" /></div>
			 <div class="xiangm_2"><img src="${ctx}/images/check/w_location.png" /></div>
			 <div class="xiangm_3"><div style="height:36px; width:500px;">
			      <div class="xm1"><img src="${ctx}/images/check/zi16.png" /></div>
				  <div class="xm2">待检测</div>
                  <div class="xm3"></div></div>
				  <div class="xm4"><div class="process-bar"></div></div>
             </div>
		</div>
		</div>
  </div>
  <div class="actions">
  <div  class="check-items-navbar">
  	<ul id='result-thumb' style="list-style:none;margin:0px">
  	</ul>
  	</div>
  	<div class="buttons">
  	<input id= 'enableBtn'type="button" title="启用设备后,设备将被激活并开始工作" class="btn btn-small btn-success" value="使用设备"/>
  	<input id= 'retryBtn'type="button" class="btn btn-small btn-warning" value="重新检测"/>
  	<input id='cancelBtn' type="button" title="退后自检后,该设备将自动关机" class="btn btn-small" value="取消自检"/>
  	</div>
  </div>
  <div class="bottom"><img src="${ctx}/images/check/bottom.png" /></div>


</div>

</body>
</html>