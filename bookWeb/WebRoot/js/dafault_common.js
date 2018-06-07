function getCartsNum() {
	$.ajax({
		url : "${ctx}/api/shop/cart!getCartsNum.do?ajax=yes",
		data : "",
		dataType : "json",
		success : function(a) {
			if (a.result == 1) {
				$("#cartsNum").text(a.data.num)
			} else {
				$.dialog.tips("获取购物车件数失败")
			}
		},
		error : function(a) {
			$.dialog.tips("出错了:(")
		}
	})
}
function checkPageSize(b, a) {
	if (!/^\d+$/.test(b)) {
		return 1
	}
	if (b > a) {
		return a
	}
	if (b < a) {
		return b
	}
	return b
}
nextAuthors = 1;
authorStatus = 0;
pubPageNo = 0;
maxFlag = false;
function changeAuthors() {
	$.ajax({
		url : "author!change.do?ajax=yes&pageNumber=" + nextAuthors
				+ "&status=" + authorStatus,
		dataType : "json",
		success : function(a) {
			var d = "";
			var b = a.data.result;
			if (b.length > 0) {
				for ( var c = 0; c < b.length; c++) {
					d += '<li class="mr_16">';
					d += '<a title="' + b[c].intro + '" href="zuozhe'
							+ b[c].name + '.html" target="_blank">';
					d += '<img alt="' + b[c].name
							+ '" style="width: 100px; height: 125px;" src="'
							+ b[c].photo + '" />';
					d += '<div class="authorName"><p>' + b[c].name
							+ "</p></div>";
					d += "</a>";
					d += "</li>"
				}
				$("#authorListUL").html(d)
			}
			++nextAuthors;
			if (nextAuthors > a.data.totalPageCount) {
				nextAuthors = 1;
				if (authorStatus == 0) {
					authorStatus = 1
				} else {
					authorStatus = 0
				}
			}
		},
		error : function() {
			$.dialog.tips("数据加载错误")
		}
	})
}
function changePublisher(c) {
	var b;
	var a = 1;
	if (c == "pre") {
		--pubPageNo;
		if (pubPageNo < 0) {
			pubPageNo = 0;
			return false
		} else {
			if (pubPageNo == 0) {
				b = 1;
				a = 1
			} else {
				b = pubPageNo;
				a = 0
			}
		}
	}
	if (c == "next") {
		if (maxFlag == true) {
			b = 1;
			a = 1;
			pubPageNo = 0
		} else {
			++pubPageNo;
			b = pubPageNo;
			a = 0
		}
	}
	$.ajax({
		url : "publisher!change.do?ajax=yes&pageNumber=" + b + "&status=" + a,
		dataType : "json",
		success : function(d) {
			var f = "";
			var g = d.data.result;
			if (g.length > 0) {
				for ( var e = 0; e < g.length; e++) {
					f += "<li>";
					f += '<a title="' + g[e].intro + '" href="chubanshe'
							+ g[e].pub_name + '.html" target="_blank">';
					f += '<img alt="' + g[e].pub_name
							+ '" style="width: 110px; height: 70px;" src="'
							+ g[e].pub_img + '" />';
					f += "</a>";
					f += "</li>"
				}
				$("#ISL_Cont_1").html(f)
			}
			if (c == "next") {
				if (pubPageNo + 1 > d.data.totalPageCount) {
					maxFlag = true
				} else {
					maxFlag = false
				}
			}
			if (c == "pre") {
				maxFlag = false
			}
		},
		error : function() {
			$.dialog.tips("数据加载错误")
		}
	})
}
$(function() {
	if ($("#totop") != undefined) {
		$("#totop").hide();
		$(function() {
			$(window).scroll(function() {
				if ($(window).scrollTop() > 100) {
					$("#totop").fadeIn()
				} else {
					$("#totop").fadeOut()
				}
			});
			$("#totop").click(function() {
				$("body,html").animate({
					scrollTop : 0
				}, 500);
				return false
			})
		})
	}
});
(function(a) {
	a.fn.jbtn = function(b) {
		return this.each(function() {
			a(this).unbind("hover").hover(function() {
				var c = a(this);
				c.addClass("hover")
			}, function() {
				var c = a(this);
				c.removeClass("hover")
			})
		})
	};
	a.Loading = a.Loading || {};
	a.Loading.show = function(b) {
		a.blockUI({
			css : {
				top : "10px"
			},
			message : b,
			showOverlay : false
		})
	};
	a.Loading.hide = function() {
		a.unblockUI()
	};
	a.alert = function(b) {
		a.dialog.alert(b)
	};
	a.confirm = function(d, c, b) {
		a.dialog.confirm(d, c, b)
	}
})(jQuery);
$(function() {
	CartBar.init()
});
var CartBar = {
	init : function() {
		this.loadNum()
	},
	deleteItem : function(a) {
	},
	loadNum : function() {
		var a = this;
		$.ajax({
			url : ctx + "/api/shop/cart!getCartData.do?ajax=yes",
			dataType : "json",
			cache : false,
			success : function(b) {
				if (b.result == 1) {
					$("#cartsNum").text(b.data.count)
				}
			}
		})
	},
	loadNumByMb : function() {
		var a = this;
		$.ajax({
			url : ctx + "/api/shop/cart!getCartData.do?ajax=yes",
			dataType : "json",
			cache : false,
			success : function(b) {
				if (b.result == 1) {
					if (b.data.count + "" == "0") {
						$("#cartsNum").attr("style", "display: none;")
					} else {
						$("#cartsNum").removeAttr("style");
						$("#cartsNum").text(b.data.count)
					}
				}
			}
		})
	}
};
(function(f) {
	var b = {
		title : "登陆",
		modal : true,
		draggable : true,
		fixed : false,
		afterHide : function() {
			this.unload()
		},
		onLoadComplete : c,
		parent : null
	};
	var d;
	var g;
	var e;
	f.LoginDialog = f.LoginDialog || {};
	f.LoginDialog.open = function(i) {
		var h = "login.html?widgetid=login&ajax=yes&action=dialog";
		i = f.extend({}, b, i || {});
		g = i.loginSuccess;
		e = i.loginError;
		d = f.dialog({
			title : "会员登录",
			width : 475,
			height : 312,
			lock : true,
			min : false,
			max : false
		});
		f.ajax({
			url : h,
			success : function(j) {
				d.content(j);
				c()
			},
			error : function() {
				f.alert("登录页面获取出错")
			},
			cache : false
		});
		return false
	};
	f.LoginDialog.close = function() {
		d.cancel()
	};
	function a() {
		f("#widget_login_bar").html("加载中...");
		f("#widget_login_bar").load(
				"login_bar.html?widgetid=/login_bar.html&ajax=yes")
	}
	function c(h) {
		f("#login_dialog_wrapper .loginbtn").click(function() {
			f.Loading.show("正在登录，请稍候...");
			var i = {
				url : "widget.do?type=member_login&ajax=yes&action=ajaxlogin",
				type : "POST",
				dataType : "json",
				success : function(j) {
					f.Loading.hide();
					if (j.result == 1) {
						isLogin = true;
						if (d) {
							a();
							d.close();
							if (g && typeof (g) == "function") {
								g(j)
							}
						}
					} else {
						if (j.result == -1) {
							f.alert("验证码验入错误");
							if (e && typeof (e) == "function") {
								e(j)
							}
						} else {
							if (j.result == 0) {
								f.alert("用户名或密码输入错误");
								if (e && typeof (e) == "function") {
									e(j)
								}
							}
						}
					}
				},
				error : function(j) {
					f.Loading.hide();
					f.alert("出现错误 ，请重试")
				}
			};
			f("#login_dialog_wrapper form").ajaxSubmit(i)
		});
		f("#login_dialog_wrapper .btn").jbtn();
		f("#verifyCodebox a").click(
				function() {
					f("#verifyCodebox img").attr(
							"src",
							"validcode.do?vtype=memberlogin&r="
									+ new Date().getTime())
				})
	}
})(jQuery);