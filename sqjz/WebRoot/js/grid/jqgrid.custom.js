 /*
   **
   *************************************************************************
   * Date: 2011-7-7
   * modifier: shenlj
   * reason: 增加方法：saveAllData : function(url, succesfunc, postData)保存jqgrid发生变化的数据，用于jqgrid填报提交
   *         增加方法：arrayToJson(o)，字符串或object数组转成json串
   **************************************************************************
   * Date: 2011-11-1
   * modifier: zhougz
   * reason: 
   * a. 调用接口由loadComplete变为afterLoad
   * b. afterLoad中增加模糊匹配功能 
   ************************************************************************** 
   * Date: 2011-12-02
   * modifier: zhougz
   * reason: 
   * 初始化时修改多选框状态 add by zhougz 2011-12-02
   * ************************************************************************** 
   * Date: 2012-06-07
   * modifier: zhougz
   * reason: 
   * 点击行时。如果多选框是disable则没有点击效果 add by zhougz 2012-06-07
  **/

$.jgrid = $.jgrid || {};

jQuery.extend(jQuery.jgrid.defaults, {
      prmNames : {
    	prefix: 'jqgrid.',
        page : "pageStart",
        rows : "pageSize",
        sort : "sortCol",
        order : "sortOrder",
        search : "search",
        nd : "nd",
        id : "id",
        oper : "oper",
        editoper : "edit",
        addoper : "add",
        deloper : "del",
        subgridid : "id",
        npage : null,
        totalrows : "totalrows"
      },
      altRows : true,
      datatype : "json",
      
      mtype : "POST",
      rowNum : 20,
      rowList : [10,20,50],
      height: -1,
      viewrecords : true,
	  autowidth : true,
	  shrinkToFit: true,
	  rownumbers : true,
	  multiselect : true,
      expectHeightEl : false,
      userDataClass : false,//userData数据行table样式，
	  rowDataCache	: false,//表格行数据是否缓存，如果是行编辑表格的不要设置该属性
      serializeGridData : function(postData, prm) {  	  
        var cols = "";
        for (var i = 0; i < this.p.colModel.length; i++) {
          var _colName = this.p.colModel[i].name;
          if (_colName !== "cb" && _colName !== "subgrid"
              && _colName !== "rn") {
            cols += ',' + _colName;
          }
        }
        if (this.p.appendDataCol) {
          cols = this.p.appendDataCol + cols;
        } else {
          cols = cols.substring(1);
        }
    	
        if (typeof postData === "string") {
			// TODO 还没有处理已经有cols的场景。
        	postData += "&cols=" + cols;
        	if (prm) {
        		$.each(prm, function(name, value) {
        			postData += "&" + name + "=" + value;
        		});
        	}
        } else {
        	postData = postData || {};
        	if (!postData.cols) {
        		postData['cols'] = cols;
	        }
        }
        var prefix = this.p.prmNames.prefix;
        if (prefix){
        	var realPosted = {};
        	for (var name in postData){
        		realPosted[prefix + name] = postData[name];
        	}
        	return realPosted;
        }
        return postData;
      },
      beforeSelectRow : function(a,e){
      	// 如果点击这行存在  disabled的 checkbox那么将不能被选中
      	if($('td[aria-describedby$=_cb] :checkbox:disabled',$(e.target).parent('tr')).length >0){
      		return false;
      	}else{
      		return true;
      	}
      },
      onSelectRow : function(id, status) {
        // 当多选被激活，同时配置要求单选，那么在当前行被选中时，把其他选择的给去掉。
        if (this.p.multiselect === true
            && this.p.selectOnlyOne === true && status === true) {

          var _arrSelected = $(this).getGridParam("selarrrow");
          for (var i = _arrSelected.length - 1; i >= 0; i--) {
            if (_arrSelected[i] !== id) {
              $(this).jqGrid('setSelection', _arrSelected[i],
                  false);
            }
          }
        }
        if (this.p.afterSelectRow) {
          this.p.afterSelectRow.call(this, id, status);
        }
      },

      loadComplete: function(jsonData) {
    	  
      	/** add by 周营昭， 当json数据报错时，需要给出提示。 begin */
      	if (jsonData.success === false) {
      		$.alert(jsonData.msg, false, false, function() {
      			// 配合sessionError.jsp页面给出的数据，这个可以确定是session访问过期造成的错误。
      			if(jsonData.error == "session-expire") {
      				top.location.href = $.ctx + "/login.jsp";
      			}
      		});
      		return;
      	}
      	/** add by 周营昭， 当json数据报错时，需要给出提示。 end */
      
		/** 初始化时修改多选框状态 add by zhougz 2011-12-02 begin 
		$.each(this.p.colModel, function(_num,_o){
      		var col = this;
      		if($.isFunction(col.inlineCheckbox)){
      			$.each(jsonData.rows,function(i,obj){
      				var checkboxObj = $("#jqg_"+$(self).attr('id')+"_"+obj.id)[0];
      				col.inlineCheckbox.call(checkboxObj,obj[_o.index],obj)
      			});
      		}
		});
		 初始化时修改多选框状态 add by zhougz 2011-12-02 end **/
		
		
      	var $grid = $(this);
      	/**新增设置userdata数据行table样式配置，add by zhaomd*/
		if(this.p.userDataClass){
			$grid.parents(".ui-jqgrid").find(".ui-jqgrid-sdiv table:first").addClass(this.p.userDataClass);
		}
      	/**add end*/
		
      	var $gridDiv = $grid.parents(".ui-jqgrid-bdiv");
      	if(this.p.height == -1 && $.isFunction($gridDiv.getElFitHeight)){//表格高度自适应 add by zhaomd
      		var pageHeight = 10;
      		if(!$.isNull(this.p.pager)){
      			pageHeight += $(this.p.pager).outerHeight();
      		}
      		function onResize(){
      			if($grid.is(":hidden")){
      				return;
      			}
      			var height =  $gridDiv.getElFitHeight($grid[0].p.expectHeightEl)-$gridDiv.nextAll(".ui-jqgrid-sdiv").height()-pageHeight;
      		
      			if($grid.outerHeight()>height){
      				/*当高度超过grid的高度是滚动条出现 add by qiuj 2012-08-22 end */
      				$gridDiv.css("overflow-y","auto"); 
      				$gridDiv.height(height);
      			}else{
      				$gridDiv.height('100%');
      				/** 出现多余滚动条 add by qiuj 2012-06-26 end **/
      				//$gridDiv.css("overflow-y","hidden");
      			}
      		}
      		onResize(); 
      		$(".content").resize(function(){
				onResize(); 
      			resizeGridWidth();
      		});
      		$(window).resize(function(){
				onResize(); 
      			resizeGridWidth();
      		});
      	}else{
      		window.onresize = resizeGridWidth;
      	}
      
       if(isNaN(this.p.height) && this.p.hscroll === true) {
          $gridDiv.height($grid.height() + 18);
        }

        if(this.p.autowidth) {
          	$grid.jqGrid('setGridWidth', ($grid.parents(".ui-jqgrid").parent().width()-4) );
        }
      },


      jsonReader : {
        repeatitems : false
      }
    });

//update by xiajb 2013-05-20 修改公共处理列表中有多个码表值显示需要转换问题
jQuery.extend($.fn.fmatter, {

  translateCode : function(cellvalue, options, rowdata) {
    if (options.colModel.formatoptions && options.colModel.formatoptions.codeClass) {
      var codeMap = $("body").data("codesMap") ? $("body").data("codesMap")[options.colModel.formatoptions]:null;
      var texts = [];
      var clscfg = options.colModel.formatoptions.clscfg;
      
      // 如果没有缓存的数据，并且有构建缓存数据的条件，则构建转义基础数据。
      if((!codeMap) && $.codeClass && $.codeClass[options.colModel.formatoptions.codeClass]){
      	  codeMap = {};
	      $.each($.codeClass[options.colModel.formatoptions.codeClass],function(){
	      	codeMap[this.code]=this.codeDesc;
	      });
	      
	      // 缓存起来。
	      var cachedData = $("body").data("codesMap");
	      if (!cachedData) {
	    	  cachedData = {};
	    	  $("body").data("codesMap", cachedData);
	      }
	      cachedData[options.colModel.formatoptions.codeClass] = codeMap;
      }
      
      var appendResult = "";
      if(codeMap && cellvalue && cellvalue!=""){
      	$.each(cellvalue.split(","),function(){
      		texts.push(codeMap[this]);
      	});
      } 
      
      if (clscfg && clscfg[cellvalue]) {
		  appendResult += "class='" + clscfg[cellvalue] +"'";
	  }
      if ((!codeMap) && cellvalue) {
    	  appendResult += " codeClass='" + options.colModel.formatoptions.codeClass +"'";
      }
	      
      return "<span orig='"+ cellvalue + "' "+ appendResult +">" + texts.join("，") + "</span>";
    } else {
      return "没有指定转义的codeClass";
    }
  }
});

$.fn.fmatter.translateCode.unformat = function(text, options, cellval) {
   return $("span[orig]", cellval).attr("orig");
};


function resizeGridWidth(){

	jQuery(".ui-jqgrid").each(function(){
	    var id = $(this).attr("id");
	    //熊猫追加--处理多次遍历JQGRID
	    if(id){
	    	id = id.substring(5);
    	  	var w = $(this).parent().width() - 4;
		    var aw = jQuery("#" + id).jqGrid('getGridParam', 'autowidth');
		  
		    if (w > 0 && aw === true) {
		    	jQuery("#" + id).jqGrid('setGridWidth', w );
		    }
	    }
	});
}

/* 行合并。索引从0开始，包含隐藏列，注意jqgrid的自动序号列也是一列。
使用方法：
$("#jqGridId").tuiTableRowSpan("3, 4, 8");
该方法暂时由王硕维护，若存在不满足需求的情况或使用上的问题请找王硕解决
*/
jQuery.fn.tuiTableRowSpan = function(colIndexs) {

	//去掉鼠标浮动样式、点击样式、各行样式。 add by zhougz 20111219 begin
	$(this).unbind('mouseover').bind('click.unbindClick',function(e){
		$(e.target).closest("tr.ui-state-highlight").removeClass('ui-state-highlight');
	}).find('tr.ui-priority-secondary').removeClass('ui-priority-secondary');
	// end

    return this.each(function() {
        var indexs = eval("([" + colIndexs + "])");
        for (var i = 0; i < indexs.length; i++) {
            var colIdx = indexs[i];
            var that;
            //var $preThat;
            $('tbody tr', this).each(function() {
            	var row = this;
                $('td:eq(' + colIdx + ')', this).filter(':visible').each(function() {
                    /**修改合并行规则为后一列的合并依赖于前一列的合并情况 edit by zhaomd*/
                    if (that != null && $(this).text() == $(that).text()
                    	&& (i==0 || $('td:eq(' + indexs[i-1] + ')', row).attr("merge"))) {
                        //alert($(this).html()+",index["+i+"]="+indexs[i]);
                        rowspan = $(that).attr("rowSpan");
                        if (rowspan == undefined) {

                            $(that).attr("rowSpan", 1);
                            rowspan = $(that).attr("rowSpan");
                        }
                        rowspan = Number(rowspan) + 1;
                        $(that).attr("rowSpan", rowspan); // do your action for the colSpan cell here
                        $(this).hide(); //原为remove，修改为hide，解决合并行后getRowData取值错位问题 --edit by wangs
                    	$(this).attr("merge",true);
                    } else {
                        that = this;
                        //$preThat = $('td:eq(' + indexs[i-1] + ')', row);
                    }
                    /**edit end*/
                    // that = (that == null) ? this : that; // set the that if not already set
                });

            });
        }
    });
};

/* 列表头合并。
索引从0开始，包含隐藏列，注意jqgrid的自动序号列也是一列。
   
使用方法：
$("#jqGridId").tuiJqgridColSpan({ 
    cols: [
        { indexes: "3, 4", title: "合并后的大标题" },
        { indexes: "6, 7", title: "合并后的大标题" },
        { indexes: "11, 12, 13", title: "合并后的大标题" }
    ]
});
注意事项： 
1.不支持列宽的resize，最好将shrinkToFit设置为false；    
2.jqgrid的table表头必须有aria-labelledby='gbox_tableid' 这样的属性；
3.只适用于jqgrid；
*/
var tuiJqgridColSpanInit_kkccddqq = false;
jQuery.fn.tuiJqgridColSpan = function(options) {

    options = $.extend({}, { UnbindDragEvent: true, cols: null }, options);

    if (tuiJqgridColSpanInit_kkccddqq) {
        return;
    }
	
    // 验证参数
    if (options.cols == null || options.cols.length == 0) {
        alert("cols参数必须设置");
        return;
    }

    // 传入的列参数必须是顺序列，由小到大排列，如3,4,5
    var error = false;
    for (var i = 0; i < options.cols.length; i++) {
        var colIndexs = eval("([" + options.cols[i].indexes + "])");

        for (var j = 0; j < colIndexs.length; j++) {
            if (j == colIndexs.length - 1) break;

            if (colIndexs[j] != colIndexs[j + 1] - 1) {
                error = true;
                break;
            }
        }

        if (error) break;
    }

    if (error) {
        alert("传入的列参数必须是顺序列，如：3,4,5");
        return;
    }

    // 下面是对jqgrid的表头进行改造
    var oldTr,    // 被合并的第一行
        oldThs;   // 被合并的第一行的th
    var tableId = $(this).attr("id");
    // thead
    var jqHead = $("table[aria-labelledby='gbox_" + tableId + "']");
    var jqDiv = $("div#gbox_" + tableId);

    oldTr = $("thead tr:first", jqHead);
    if (oldTr.height() < 5) {
            oldTr = $("thead tr:eq(1)", jqHead);
            oldThs = $("th", oldTr);
    } else {
            // 增加第一行
            oldThs = $("th", oldTr);
            var ftr = $("<tr/>").css("height", "auto").addClass("ui-jqgrid-labels").attr("role", "rowheader").insertBefore(oldTr);
            oldThs.each(function(index) {
	            var cth = $(this);
	            var cH = cth.css("height"), cW = 130, fth = $("<th/>").css("height", 0);
	            if(index == 0){
	            	cW = 25;
	            }
	            // 在IE8或firefox下面，会出现多一条边线，因此要去掉。
	            if (($.browser.msie && $.browser.version == "8.0") || $.browser.mozilla) {
	                fth.css({ "border-top": "solid 0px #fff", "border-bottom": "solid 0px #fff" });
	            }
	
	            if (cth.css("display") == "none") {
	                fth.css({ "display": "none", "white-space": "nowrap", "width": 0 });
	            }
	            else {
	                fth.css("width", cW);
	            }
	            // 增加第一行
	            fth.addClass(cth.attr("class")).attr("role", "columnheader").appendTo(ftr);
	        });
    }
    
    var ntr = $("<tr/>").addClass("ui-jqgrid-labels").attr("role", "rowheader").insertAfter(oldTr);

    oldThs.each(function(index) {
        var cth = $(this);
        var cH = cth.css("height"), cW = cth.css("width"),
        nth = $("<th/>").css("height", cH);

        if (cth.css("display") == "none") {
            nth.css({ "display": "none", "white-space": "nowrap", "width": 0 });
        }
        else {
            nth.css("width", cW);
        }
        
        // 增加第三行
        if (cth.children().length > 0) {
        	cth.children().clone().appendTo(nth);
        } else {
        	nth.html(cth.text());
        }
        
        nth.addClass(cth.attr("class"))
           .attr("role", "columnheader")
           .attr("rowSpan", cth.attr("rowSpan"))
           .attr("colSpan", cth.attr("colSpan"))
           .appendTo(ntr);
        
        if (cth.css("border-bottom-style") == "solid") {
        	nth.css("border-bottom", "1px solid #D9D9D9");
        }
        
        cth.attr("rowSpan", 1);
    });

    // 列合并。注意：这里不放在上面的循环中处理，因为每个遍历都要执行下面的操作。
    var toDelete = [];
    for (var i = 0; i < options.cols.length; i++) {
        var colIndexs = eval("([" + options.cols[i].indexes + "])");
        var colTitle = options.cols[i].title;
        //传入html begin add by zhougz 2011-12-07
		var colTitleHTML = options.cols[i].titleHTML || false;
		
        var isrowSpan = false;
        var mergedTH;
        
        for (var j = 0; j < colIndexs.length; j++) {
            // 把被合并的列隐藏，不能remove，这样jqgrid的排序功能会错位。
            if (j != 0) {
                var curColspan = mergedTH.attr("colSpan") || 1;
                var appendColspan = oldThs.eq(colIndexs[j]).attr("colSpan") || 1;
                mergedTH.attr("colSpan", 0 + parseInt(curColspan) +parseInt( appendColspan)) ;
                
                oldThs.eq(colIndexs[j]).attr("colSpan", "1").hide();
                toDelete.push(oldThs.eq(colIndexs[j]));
            } else {

                    mergedTH = oldThs.eq(colIndexs[j]);
                    mergedTH.html(colTitle);
                    //传入html begin add by zhougz 2011-12-07
                    if(colTitleHTML) mergedTH.html(colTitleHTML);
                    
                    mergedTH.css("border-bottom", "1px solid #D9D9D9");
            }

            // 标记删除clone后多余的th
            $("th", ntr).eq(colIndexs[j]).attr("tuidel", "false");
        }
    }
    for (var i = 0; i < toDelete.length; i++) {
        toDelete[i].remove();
    }
    
    $("th", ntr).each(function(index) {
        var $th = $(this);
            
        if ($th.attr("tuidel") != 'false') {
            oldThs.eq(index).attr("rowSpan", 0 + parseInt((oldThs.eq(index).attr("rowSpan") || 1)) + parseInt(($th.attr("rowSpan") || 1)));
            $th.remove();
        } 
    });

	$(window).trigger('resize');
};
$(function(){
	
	$(".content").resize(function(){
      	resizeGridWidth();
     });
})