(function ($) {
"use strict";
$.monitortabpage = $.monitortabpage || {};
$.extend($.monitortabpage,{
	defaultConfig:{
		server:{
		},
		network:{
		},
		dataBase:{
		},
		lbsServer:{
		}
	},
	init: function(action, options){
		var me = this;
		me.config = $.extend({}, me.defaultConfig[action] || {});
		me.config = $.extend(me.config, options || {});
		me.config.baseurl = me.config.baseurl || CONTEXT_PATH + "/data/monitor/";
		me[action].call(me);
	},
	server: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'searchServer.action',
			colModel: [{
				        name	: 'createTime',
				        index	: 'createTime',
				        label	: '监控时间',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'cpuRatio',
				        index	: 'cpuRatio',
				        label	: 'CPU使用率',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }, {
				        name	: 'wlncRatio',
				        index	: 'wlncRatio',
				        label	: '物理内存使用率',
				        sortable: true,
				        width	: 120,
				        align	: 'center'
			        }, {
				        name	: 'totalMemorySize',
				        index	: 'totalMemorySize',
				        label	: '总物理内存',
				        width	: 100,
				        sortable: true,
				        align	: 'center'
			        }, {
				        name	: 'usedMemory',
				        index	: 'usedMemory',
				        label	: '已用物理内存',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }, {
				        name	: 'freePhysicalMemorySize',
				        index	: 'freePhysicalMemorySize',
				        label	: '剩余物理内存',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }, {
				        name	: 'maxMemory',
				        index	: 'maxMemory',
				        label	: '最大可用内存',
				        sortable: true,
				        width	: 150,
				        align	: 'center'
			        }, {
				        name	: 'totalMemory',
				        index	: 'totalMemory',
				        label	: '可用内存',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }, {
				        name	: 'freeMemory',
				        index	: 'freeMemory',
				        label	: '剩余内存',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }, {
				        name	: 'totalThread',
				        index	: 'totalThread',
				        label	: '线程总数',
				        sortable: true,
				        width	: 100,
				        align	: 'center'
			        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'createTime',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	},
	network: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'searchNetwork.action',
			colModel: [{
		        name	: 'createTime',
		        index	: 'createTime',
		        label	: '监控时间',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'ip',
		        index	: 'ip',
		        label	: '网络通讯地址',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'lossRatio',
		        index	: 'lossRatio',
		        label	: '网络通讯失败率',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'sent',
		        index	: 'sent',
		        label	: '网络通讯发送次数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'received',
		        index	: 'received',
		        label	: '网络通讯成功次数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'lost',
		        index	: 'lost',
		        label	: '网络通讯失败次数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }],
			multiselect : false,
			rownumbers	: true,
			sortname: 'createTime',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	},
	dataBase: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'searchDataBase.action',
			colModel: [{
		        name	: 'createTime',
		        index	: 'createTime',
		        label	: '监控时间',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'portStatus',
		        index	: 'portStatus',
		        label	: '连接状态',
		        formatter : function(value, opts, rwdat) {
					if(value=='1'){
						return "正常";	
					}else{
						return "不正常";
					}
		        },
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'nowLink',
		        index	: 'nowLink',
		        label	: '当前连接数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'idleLink',
		        index	: 'idleLink',
		        label	: '空闲连接数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'sumLink',
		        index	: 'sumLink',
		        label	: '总连接数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'maxLink',
		        index	: 'maxLink',
		        label	: '最大连接数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }],
			multiselect : false,
			rownumbers	: true,
			sortname: 'createTime',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	},
	lbsServer: function(){
		var me = this;
		$("#mainGrid").jqGrid({
			url		    : me.config.baseurl + 'searchLbsServer.action',
			colModel: [{
		        name	: 'createTime',
		        index	: 'createTime',
		        label	: '监控时间',
		        sortable: true,
		        width	: 130,
		        align	: 'center'
	        }, {
		        name	: 'status',
		        index	: 'status',
		        label	: 'LBS状态',
		        formatter : function(value, opts, rwdat) {
					if(value=='1'){
						return "正常";	
					}else{
						return "不正常";
					}
		        },
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'ratio',
		        index	: 'ratio',
		        label	: '定位成功率',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'success',
		        index	: 'success',
		        label	: '定位成功次数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'fail',
		        index	: 'fail',
		        label	: '定位失败次数',
		        sortable: true,
		        width	: 100,
		        align	: 'center'
	        }, {
		        name	: 'sent',
		        index	: 'sent',
		        label	: '网络通讯发送次数',
		        sortable: true,
		        width	: 110,
		        align	: 'center'
	        }, {
		        name	: 'received',
		        index	: 'received',
		        label	: '网络通讯成功次数',
		        sortable: true,
		        width	: 110,
		        align	: 'center'
	        }, {
		        name	: 'lost',
		        index	: 'lost',
		        label	: '网络通讯失败次数',
		        sortable: true,
		        width	: 110,
		        align	: 'center'
	        }, {
		        name	: 'lossRatio',
		        index	: 'lossRatio',
		        label	: '网络通讯失败率',
		        sortable: true,
		        width	: 110,
		        align	: 'center'
	        }
			],
			multiselect : false,
			rownumbers	: true,
			sortname: 'createTime',
			sortorder: "desc",
			pager: '#mainGridPager'
		});
		$("#mainGrid").bindSearchForm("#searchDiv", "#searchBtn", "#resetBtn");
	}
});
})(jQuery);