(function($) {
	"use strict";
	$.menu = $.menu || {};
	$.extend($.menu, {
		defaultConfig : {
			first : "",
			second : "",
			third : ""
		},
		menuList : {
			sj:{
				bggl : {
					title : "办公管理",
					img : CONTEXT_PATH + "/images/index/bg_gl_s.jpg",
					subMenu : [ {
						id : "org",
						title : "机构管理",
						url : CONTEXT_PATH + "/data/jggl/list.action",
						img : CONTEXT_PATH + "/images/index/left_jzjggl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzjg.jpg"*/
					}, {
						id : "people",
						title : "人员管理",
						url : CONTEXT_PATH + "/data/rygl/list.action",
						img : CONTEXT_PATH + "/images/index/left_rygl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_rygl.jpg"*/
					},{
						id : "notice",
						title : "通知管理",
						img : CONTEXT_PATH + "/images/index/left_tzgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_tzgl.jpg",*/
						subMenu : [ {
							id : "tznz",
							title : "通知拟制",
							url : CONTEXT_PATH + "/data/tzgl/list.action",
							permission : {
								orgType : "sj|qxsfj"
							}
						}]
					}, {
						id : "dtxx",
						title : "动态信息管理",
						img : CONTEXT_PATH + "/images/index/left_dtxxgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_dtxx.jpg",*/
						subMenu : [ {
							id : "dtxxck",
							title : "动态信息查看",
							url : CONTEXT_PATH + "/data/bggl/dtgl/dtglView.jsp"
						} ]
					}, {
						id : "xzyd",
						title : "下载园地",
						img : CONTEXT_PATH + "/images/index/icon4.png",
						/*img : CONTEXT_PATH + "/images/index/erj_xzyd.jpg",*/
						subMenu : [ {
							id : "upload",
							title : "资料上传",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlsc.jsp"
						}, {
							id : "download",
							title : "资料下载",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlxz.jsp"
						} ]
					} ]
				},
				sqjz : {
					title : "矫正管理",
					img : CONTEXT_PATH + "/images/index/sq_jz_s.jpg",
					subMenu : [ {
						id : "jzgl",
						title : "日常管理",
						img : CONTEXT_PATH + "/images/index/left_rcgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzgl.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/dagl/zjry.jsp"
					}, {
						id : "bbgl",
						title : "报表管理",
						img : CONTEXT_PATH + "/images/index/left_bbgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_bbgl.jpg",*/
						subMenu : [ {
							id : "workReport",
							title : "工作统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/jzgztj/list.jsp"
						}, {
							id : "fxryReport",
							title : "人员情况统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/list.jsp"
						},{
							id : "ygztzjReport",
							title : "中途之家工作统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/ygztzj/list.jsp"
						}
						/*, 
						{
							id : "fxryRealTimeReport",
							title : "人员情况实时统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/fxrySsTj/tb_Report.jsp?raq=qxsfj_tjdata&orgId=1&orgType=2"
						}*/
						,{
							id:"sqjzzb",
							title:"社区矫正业务统计简表",
							url:CONTEXT_PATH + "/data/jzgl/bbgl/list_sqjzzb.jsp"
						}
						]
					}, {
						id : "cxtj",
						title : "数据统计",
						img : CONTEXT_PATH + "/images/index/left_cxtj.png",
						/*img : CONTEXT_PATH + "/images/index/erj_cxtj.jpg",*/
						subMenu : [ /*{
							id : "dacx",
							title : "人员档案查询",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/fxrycx.jsp"
						},*/ {
							id : "sjfx",
							title : "数据统计",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/sjfx/report.jsp"
						} ]
					} , {
						id : "jzjy",
						title : "集中教育",
						img : CONTEXT_PATH + "/images/index/left_jzjy.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzjy.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/education/report.jsp"
					}, 
					{
						id : "tgry",
						title : "特管人员",
						img : CONTEXT_PATH + "/images/index/left_tgry.png",
					/*	img : CONTEXT_PATH + "/images/index/erj_tgry.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/dagl/sry.jsp?"
					}]
				},
				dzjg : {
					title : "电子监管",
					img : CONTEXT_PATH + "/images/index/dz_jg_s.jpg",
					subMenu : [ {
						id : "dwjk",
						title : "定位监控",
						//img : CONTEXT_PATH + "/images/index/erj_dwjk.jpg",
						img : CONTEXT_PATH + "/images/index/left_dwjk.png",
						//url : CONTEXT_PATH + "/data/dwjk/countyViewControl.action?orgId=181&orgName=顺义区"
						url : CONTEXT_PATH + "/data/dwjk/cityView.action"
					}, {
						id : "lsbj",
						title : "历史报警信息",
						//img : CONTEXT_PATH + "/images/index/erj_lsbj.jpg",
						img : CONTEXT_PATH + "/images/index/left_lsbjxxgl.png",
						url : CONTEXT_PATH + "/data/dzjg/lsbjxxgl/list.action"
					}, 
					/*{
						id : "zhcx",
						title : "综合查询",
						//img : CONTEXT_PATH + "/images/index/erj_zhcx.jpg",
						img : CONTEXT_PATH + "/images/index/left_zhcx.png",
						url : CONTEXT_PATH + "/data/dzjg/zhcx/list.action"
					}
					,*/
					{
						id : "pzgl",
						title : "配置管理",
						img : CONTEXT_PATH + "/images/index/left_pzgl.png",
						//img : CONTEXT_PATH + "/images/index/erj_pzgl.jpg",
						subMenu : [{
							id : "mrsz",
							title : "系统默认设置",
							roleId:"1",
							url : CONTEXT_PATH + "/data/xtsz/input.action"
						} ,{
							id : "bjlxsz",
							title : "报警类型设置",
							roleId:"1",
							url : CONTEXT_PATH + "/data/dzjg/bjlxsz/list.action"
						} 
						/*,{
							id : "bjpcsz",
							title : "聚集报警排除",
							roleId:"1",
							url : CONTEXT_PATH + "/data/xtsz/jjbjsz.action"
						},{
							id : "dzjgsb",
							title : "电子监管设备",
							roleId:"1",
							url : CONTEXT_PATH + "/data/dzjg/jgsb/list.action"
						} ,{
							id : "ydzfzd",
							title : "移动执法终端",
							roleId:"1",
							url : CONTEXT_PATH + "/data/ydzf/list.action"
						}, {
							id : "zwygl",
							title : "指纹仪管理",
							roleId:"1",
							url : CONTEXT_PATH + "/data/zwy/list.action"
						}*/ ]
					}
					, {
							id : "sbgl",
							title : "设备管理",
							img : CONTEXT_PATH + "/images/index/left_sbgl.png",
							//img : CONTEXT_PATH + "/images/index/erj_sbgl.jpg",
							subMenu : [{
								id : "dzjgsb",
								title : "电子监管设备",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/jgsb/list.action"
							} ,{
								id : "simk",
								title : "SIM卡",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/sim/list.action"
							},{
								id : "ydzfzd",
								title : "移动执法终端",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/ydzf/list.action"
							},{
								id : "tjinfo",
								title : "统计信息",
								roleId:"1",
								url : CONTEXT_PATH + "/data/dzjg/sbgl/tjinfo/tjreport.jsp"
							}]
					} ]
				},
				xtgl : {
					title : "系统管理",
					img : CONTEXT_PATH + "/images/index/xt_gl_s.jpg",
					subMenu : [ {
						id : "user",
						title : "用户管理",
						img : CONTEXT_PATH + "/images/index/left_yhgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_yhgl.jpg",*/
						url : CONTEXT_PATH + "/sys/user/list.action"
					}, {
						id : "role",
						title : "角色管理",
						img : CONTEXT_PATH + "/images/index/left_jsgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jsgl.jpg",*/
						url : CONTEXT_PATH + "/sys/role/list.action"
					}, {
						id : "log",
						title : "操作日志",
						img : CONTEXT_PATH + "/images/index/left_czrz.png",
						/*img : CONTEXT_PATH + "/images/index/erj_czrz.jpg",*/
						url : CONTEXT_PATH + "/sys/xtrz/list.action?rzlx=1"
					},{
						id : "monitor",
						title : "监控管理",
						img : CONTEXT_PATH + "/images/index/left_xtjk.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzgl.jpg",*/
						url : CONTEXT_PATH + "/data/monitor/reportServer.action"
					} ]
				}
			},
			qxsfj:{
				bggl : {
					title : "办公管理",
					img : CONTEXT_PATH + "/images/index/bg_gl_s.jpg",
					subMenu : [ {
						id : "org",
						title : "机构管理",
						url : CONTEXT_PATH + "/data/jggl/list.action",
						img : CONTEXT_PATH + "/images/index/left_jzjggl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzjg.jpg"*/
					}, {
						id : "people",
						title : "人员管理",
						url : CONTEXT_PATH + "/data/rygl/list.action",
						img : CONTEXT_PATH + "/images/index/left_rygl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_rygl.jpg"*/
					},{
						id : "notice",
						title : "通知管理",
						img : CONTEXT_PATH + "/images/index/left_tzgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_tzgl.jpg",*/
						subMenu : [ {
							id : "tznz",
							title : "通知拟制",
							url : CONTEXT_PATH + "/data/tzgl/list.action"
						}, {
							id : "tzck",
							title : "通知查看",
							url : CONTEXT_PATH + "/data/bggl/tzgl/tzView.jsp"
						} ]
					}, {
						id : "dtxx",
						title : "动态信息管理",
						img : CONTEXT_PATH + "/images/index/left_dtxxgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_dtxx.jpg",*/
						subMenu : [ {
							id : "dtxxsb",
							title : "动态信息上报",
							url : CONTEXT_PATH + "/data/bggl/dtgl/dtglsb.jsp"
						}, {
							id : "dtxxck",
							title : "动态信息查看",
							url : CONTEXT_PATH + "/data/bggl/dtgl/dtglView.jsp"
						} ]
					}, {
						id : "xzyd",
						title : "下载园地",
						img : CONTEXT_PATH + "/images/index/icon5.png",
						/*img : CONTEXT_PATH + "/images/index/erj_xzyd.jpg",*/
						subMenu : [ {
							id : "upload",
							title : "资料上传",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlsc.jsp"
						}, {
							id : "download",
							title : "资料下载",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlxz.jsp"
						} ]
					} ]
				},
				sqjz : {
					title : "矫正管理",
					img : CONTEXT_PATH + "/images/index/sq_jz_s.jpg",
					subMenu : [ {
						id : "jzgl",
						title : "日常管理",
						img : CONTEXT_PATH + "/images/index/left_rcgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzgl.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/dagl/zjry.jsp"
					}, {
						id : "bbgl",
						title : "报表管理",
						img : CONTEXT_PATH + "/images/index/left_bbgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_bbgl.jpg",*/
						subMenu : [ {
							id : "workReport",
							title : "工作统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/jzgztj/list.jsp"
						}, {
							id : "fxryReport",
							title : "人员情况统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/list.jsp"
						},{
							id : "ygztzjReport",
							title : "中途之家工作统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/ygztzj/list.jsp"
						} ]
					}, {
						id : "cxtj",
						title : "数据统计",
						img : CONTEXT_PATH + "/images/index/left_cxtj.png",
						/*img : CONTEXT_PATH + "/images/index/erj_cxtj.jpg",*/
						subMenu : [ /*{
							id : "dacx",
							title : "人员档案查询",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/fxrycx.jsp"
						},*/ {
							id : "sjfx",
							title : "数据统计",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/sjfx/report.jsp"
						} ]
					} , {
						id : "jzjy",
						title : "集中教育",
						img : CONTEXT_PATH + "/images/index/left_jzjy.png",
					/*	img : CONTEXT_PATH + "/images/index/erj_jzjy.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/education/report.jsp"
					},{
						id : "tgry",
						title : "特管人员",
						img : CONTEXT_PATH + "/images/index/left_tgry.png",
						/*img : CONTEXT_PATH + "/images/index/erj_tgry.jpg",*/
						url : CONTEXT_PATH + "/data/jzgl/dagl/sry.jsp?"
					}]
				},
				dzjg : {
					title : "电子监管",
					img : CONTEXT_PATH + "/images/index/dz_jg_s.jpg",
					subMenu : [ {
						id : "dwjk",
						title : "定位监控",
						img : CONTEXT_PATH + "/images/index/left_dwjk.png",
						/*img : CONTEXT_PATH + "/images/index/erj_dwjk.jpg",*/
						url : CONTEXT_PATH + "/data/dwjk/countyViewControl.action"
					}, {
						id : "lsbj",
						title : "历史报警信息",
						img : CONTEXT_PATH + "/images/index/left_lsbjxxgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_lsbj.jpg",*/
						url : CONTEXT_PATH + "/data/dzjg/lsbjxxgl/list.action"
					},					
					{
						id : "pzgl",
						title : "配置管理",
						img : CONTEXT_PATH + "/images/index/left_pzgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_pzgl.jpg",*/
						subMenu : [{
							id : "mrsz",
							title : "区县默认设置",
							roleId:"1",
							url : CONTEXT_PATH + "/data/xtsz/input.action"
						} /* {
							id : "dzjgsb",
							title : "电子监管设备",
							roleId:"1",
							url : CONTEXT_PATH + "/data/dzjg/jgsb/list.action"
						} ,{
							id : "ydzfzd",
							title : "移动执法终端",
							roleId:"1",
							url : CONTEXT_PATH + "/data/ydzf/list.action"
						},*/ ]
					}
					, {
							id : "sbgl",
							title : "设备管理",
							img : CONTEXT_PATH + "/images/index/left_sbgl.png",
							/*img : CONTEXT_PATH + "/images/index/erj_sbgl.jpg",*/
							subMenu : [{
								id : "dzjgsb",
								title : "电子监管设备",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/jgsb/list.action"
							} ,{
								id : "simk",
								title : "SIM卡",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/sim/list.action"
							},{
								id : "ydzfzd",
								title : "移动执法终端",
								roleId:"1",
								url : CONTEXT_PATH + "/data/sbgl/ydzf/list.action"
							},{
								id : "tjinfo",
								title : "统计信息",
								roleId:"1",
								url : CONTEXT_PATH + "/data/dzjg/sbgl/tjinfo/tjreport.jsp"
							}]
					} ]
				},
				xtgl : {
					title : "系统管理",
					img : CONTEXT_PATH + "/images/index/xt_gl_s.jpg",
					subMenu : [ {
						id : "user",
						title : "用户管理",
						img : CONTEXT_PATH + "/images/index/left_yhgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_yhgl.jpg",*/
						url : CONTEXT_PATH + "/sys/user/list.action"
					}, {
						id : "role",
						title : "角色管理",
						img : CONTEXT_PATH + "/images/index/left_jsgl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jsgl.jpg",*/
						url : CONTEXT_PATH + "/sys/role/list.action"
					}, {
						id : "log",
						title : "操作日志",
						img : CONTEXT_PATH + "/images/index/left_czrz.png",
						/*img : CONTEXT_PATH + "/images/index/erj_czrz.jpg",*/
						url : CONTEXT_PATH + "/sys/xtrz/list.action?rzlx=1"
					} ]
				}
			},
			sfs:{
				bggl : {
					title : "办公管理",
					img : CONTEXT_PATH + "/images/index/bg_gl_s.jpg",
					subMenu : [ {
						id : "org",
						title : "矫正机构管理",
						url : CONTEXT_PATH + "/data/jggl/list.action",
						img : CONTEXT_PATH + "/images/index/left_jzjggl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_jzjg.jpg"*/
					}, {
						id : "people",
						title : "人员管理",
						url : CONTEXT_PATH + "/data/rygl/list.action",
						img : CONTEXT_PATH + "/images/index/left_rygl.png",
						/*img : CONTEXT_PATH + "/images/index/erj_rygl.jpg"*/
					},{
						id : "notice",
						title : "通知管理",
						/*img : CONTEXT_PATH + "/images/index/erj_tzgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_tzgl.png",
						subMenu : [  {
							id : "tzck",
							title : "通知查看",
							url : CONTEXT_PATH + "/data/bggl/tzgl/tzView.jsp"
						} ]
					}, {
						id : "dtxx",
						title : "动态信息管理",
						/*img : CONTEXT_PATH + "/images/index/erj_dtxx.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_dtxxgl.png",
						subMenu : [ {
							id : "dtxxsb",
							title : "动态信息上报",
							url : CONTEXT_PATH + "/data/bggl/dtgl/dtglsb.jsp",
							permission : {
								orgType : "qxsfj|sfs"
							}
						} ]
					}, {
						id : "xzyd",
						title : "下载园地",
						/*img : CONTEXT_PATH + "/images/index/erj_xzyd.jpg",*/
						img : CONTEXT_PATH + "/images/index/icon5.png",
						subMenu : [ {
							id : "upload",
							title : "资料上传",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlsc.jsp"
						}, {
							id : "download",
							title : "资料下载",
							url : CONTEXT_PATH + "/data/bggl/xzyd/zlxz.jsp"
						} ]
					} ]
				},
				sqjz : {
					title : "矫正管理",
					img : CONTEXT_PATH + "/images/index/sq_jz_s.jpg",
					subMenu : [ {
						id : "jzgl",
						title : "日常管理",
						/*img : CONTEXT_PATH + "/images/index/erj_jzgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_rcgl.png",
						url : CONTEXT_PATH + "/data/jzgl/dagl/zjry.jsp"
					}, {
						id : "bbgl",
						title : "报表管理",
						/*img : CONTEXT_PATH + "/images/index/erj_bbgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_bbgl.png",
						subMenu : [ {
							id : "workReport",
							title : "工作统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/jzgztj/list.jsp"
						}, {
							id : "fxryReport",
							title : "人员情况统计表",
							url : CONTEXT_PATH + "/data/jzgl/bbgl/list.jsp"
						} ]
					}, {
						id : "cxtj",
						title : "数据统计",
						/*img : CONTEXT_PATH + "/images/index/erj_cxtj.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_cxtj.png",
						subMenu : [ /*{
							id : "dacx",
							title : "人员档案查询",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/fxrycx.jsp"
						},*/ {
							id : "sjfx",
							title : "数据统计",
							url : CONTEXT_PATH + "/data/jzgl/cxtj/sjfx/report.jsp"
						} ]
					},
					{
						id : "tgry",
						title : "特管人员",
						/*img : CONTEXT_PATH + "/images/index/erj_tgry.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_tgry.png",
						url : CONTEXT_PATH + "/data/jzgl/dagl/sry.jsp?"
					}]
				},
				dzjg : {
					title : "电子监管",
					img : CONTEXT_PATH + "/images/index/dz_jg_s.jpg",
					subMenu : [ {
						id : "dwjk",
						title : "定位监控",
						/*img : CONTEXT_PATH + "/images/index/erj_dwjk.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_dwjk.png",
						url : CONTEXT_PATH + "/data/dwjk/locationDetail.action?orgId="+ORG_ID
					}, {
						id : "lsbj",
						title : "历史报警信息",
						/*img : CONTEXT_PATH + "/images/index/erj_lsbj.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_lsbjxxgl.png",
						url : CONTEXT_PATH + "/data/dzjg/lsbjxxgl/list.action"
					}, 
					 {
						id : "pzgl",
						title : "配置管理",
						/*img : CONTEXT_PATH + "/images/index/erj_pzgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_pzgl.png",
						subMenu : [ /*{
							id : "bjpcsz",
							title : "聚集报警排除",
							roleId:"1",
							url : CONTEXT_PATH + "/data/xtsz/sfsjjbjsz.action"
						},*/ {
							id : "fxrysz",
							title : "服刑人员设置",
							roleId:"1",
							url : CONTEXT_PATH + "/data/xtsz/fxryList.action"
						} ]
					} ]
				},
				xtgl : {
					title : "系统管理",
					img : CONTEXT_PATH + "/images/index/dz_jg_s.jpg",
					subMenu : [ {
						id : "user",
						title : "用户管理",
						/*img : CONTEXT_PATH + "/images/index/erj_yhgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_yhgl.png",
						url : CONTEXT_PATH + "/sys/user/list.action"
					}, {
						id : "role",
						title : "角色管理",
						/*img : CONTEXT_PATH + "/images/index/erj_jsgl.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_jsgl.png",
						url : CONTEXT_PATH + "/sys/role/list.action"
					}, {
						id : "log",
						title : "操作日志",
						/*img : CONTEXT_PATH + "/images/index/erj_czrz.jpg",*/
						img : CONTEXT_PATH + "/images/index/left_czrz.png",
						url : CONTEXT_PATH + "/sys/xtrz/list.action?rzlx=1"
					} ]
				}
			},
			others:{}
		},
		init : function(container, options,isws) {
			var me = this;
			var opts = $.extend(me.defaultConfig, options || {});
			var myMenu=me.menuList[ORG_TYPE];
			if (opts.first) {
				me.createMenu(myMenu[opts.first], container, opts,isws);
				$("#myMenuNew li").removeClass("navnew-c");
				$("#"+opts.first).addClass("navnew-c");
			
				$("#"+options.second+"ext").css({background:"#008af0"});
				//alert(options.second);
			}
		},
		createMenu:function(menu,container,options,isws){
			var me=this;
			var table=$('<table cellpadding="0" cellspacing="0" class="left" width="100%" ></table>');
			$(container).append(table);
			if(menu){
				//$(table).append($('<tr><th height="37"><img src="'+menu.img+'"/></th></tr>'));
				var tr=$('<tr></tr>');
				$(table).append(tr);
				var td=$('<td></td>');
				$(tr).append(td);
				$.each(menu.subMenu, function(i, item){
					var div=$('<div style="height:36px" class="123" id="'+item.id+'ext"></div>');
					var thirdMenu="";
					var hashMenu=false;
					if(item.subMenu){//绘制三级菜单
						$.each(item.subMenu, function(i, thirdItem){
							var permissioned=true;
							if(thirdItem.roleId&&thirdItem.roleId!==ROLE_ID){
								permissioned=false;
							}
							if(permissioned){
								hashMenu=true;
								if(thirdMenu==""){
									thirdMenu='<div class="sanji_wai" id="'+item.id+'" style="background:#1f9bf7"><div id="nav2" ><ul>';
								}
								var itemclass="";
								if(options.third===thirdItem.id){
									itemclass="menucurrent";
								}
								var li='<li><a href="'+thirdItem.url
									+'" class="'+itemclass
									+'"><span style="background:#1f9bf7;color:white"><div  style="margin-left:25px;"><img style="margin-right:15px;" src="/sqjz/images/index/icon6.png"></img>'
									+thirdItem.title+'</div></span></a></li>';
								thirdMenu=thirdMenu+li;
							}
						});
					}
					if(hashMenu){//有三级菜单
						thirdMenu=thirdMenu+'<div class="clear"></div></ul></div></div>';
						$(td).append(div);
						$(td).append($(thirdMenu));
						$(div).append($('<div  style="line-height:36px;height:36px;text-align:left"><img  style="padding-left:10px;" src="'+item.img+'"/><span  style="cursor:default;padding-left:10px;vertical-align:middle;color:#002b5f">'+item.title+'</span></div>'));
						if(options.second!==item.id){
							$("#"+item.id).css({display:"none"}); 
						}
						/*$(div).bind("click", function(){
							  $(".sanji_wai").css({display:"none"}); 
							  $("#"+item.id).css({display:"block"}); 
						}); */
					}else if(item.url){//无三级菜单
						$(td).append(div);
						$(div).append('<a  href="'+item.url+'"><div style="line-height:36px;text-align:left"><img  style="padding-left:10px;"  src="'+item.img+'" /><span  style="padding-left:10px;vertical-align:middle;color:#002b5f">'+item.title+'</span></div></a>')
						//$(div).append($('<a><div style="line-height:36px;height:36px;text-align:left" ><a  href="'+item.url+'"><img  style="padding-left:10px;" src="'+item.img+'" /><span  style="padding-left:10px;vertical-align:middle;color:#002b5f">'+item.title+'</span></a></div></a>'));
					}
					/*$(div).hover(function(){
					$(this).css({background:"#008af0"});
					},function(){
						$(this).css({background:"#1fa8f7"});
					})*/
					/*$(div).toggle(function(){
							$(".123").css({background:"#1fa8f7"});
							$(this).css({background:"#008af0"});
							  $(".sanji_wai").css({display:"none"}); 
							  $("#"+item.id).css({display:"block"}); 
						},function(){
							$(".123").css({background:"#1fa8f7"});
							$(this).css({background:"#1fa8f7"});
							$("#"+item.id).css({display:"none"});
						})*/
					
					$(div).bind("click", function(){
						$(this).unbind("hover");
						//alert($(this).attr("id"));
						$(".123").css({background:"#1fa8f7"});
						$(this).css({background:"#008af0"});
						$(".sanji_wai").css({display:"none"}); 
						$("#"+item.id).css({display:"block"}); 
				}); 
				});
			}
		}
	});
})(jQuery);