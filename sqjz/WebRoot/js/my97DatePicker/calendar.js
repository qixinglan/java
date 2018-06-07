/*
 * My97 DatePicker 4.7 Release
 * License: http://www.my97.net/dp/license.asp
 */
var $c;
if ($FF) {
	Event.prototype.__defineSetter__("returnValue", function($) {
		if (!$) {
			this.preventDefault();
		}
		return $;
	});
	Event.prototype.__defineGetter__("srcElement", function() {
		var $ = this.target;
		while ($.nodeType != 1) {
			$ = $.parentNode;
		}
		return $;
	});
	HTMLElement.prototype.attachEvent = function(_, A) {
		var $ = _.replace(/on/, "");
		A._ieEmuEventHandler = function($) {
			window.event = $;
			return A();
		};
		this.addEventListener($, A._ieEmuEventHandler, false);
	};
}
function My97DP() {
	$c = this;
	this.QS = [];
	$d = document.createElement("div");
	$d.className = "WdateDiv";
	$d.innerHTML = '<div id=dpTitle><div class="navImg NavImgll"><a href="###"></a></div><div class="navImg NavImgl"><a href="###"></a></div><div style="float:left"><div class="menuSel MMenu"></div><input class=yminput></div><div style="float:left"><div class="menuSel YMenu"></div><input class=yminput></div><div class="navImg NavImgrr"><a href="###"></a></div><div class="navImg NavImgr"><a href="###"></a></div><div style="float:right"></div></div><div style="position:absolute;overflow:hidden"></div><div></div><div id=dpTime><div class="menuSel hhMenu"></div><div class="menuSel mmMenu"></div><div class="menuSel ssMenu"></div><table cellspacing=0 cellpadding=0 border=0><tr><td rowspan=2><span id=dpTimeStr></span>&nbsp;<input class=tB maxlength=2><input value=":" class=tm readonly><input class=tE maxlength=2><input value=":" class=tm readonly><input class=tE maxlength=2></td><td><button id=dpTimeUp></button></td></tr><tr><td><button id=dpTimeDown></button></td></tr></table></div><div id=dpQS></div><div id=dpControl><input class=dpButton id=dpClearInput type=button><input class=dpButton id=dpTodayInput type=button><input class=dpButton id=dpOkInput type=button></div>';
	attachTabEvent($d, function() {
		hideSel();
	});
	$();
	$dp.focusArr = [
	        document, $d.MI, $d.yI, $d.HI, $d.mI, $d.sI, $d.clearI, $d.todayI,
	        $d.okI
	];
	for (var A = 0; A < $dp.focusArr.length; A++) {
		var B = $dp.focusArr[A];
		B.nextCtrl = A == $dp.focusArr.length - 1
		        ? $dp.focusArr[1]
		        : $dp.focusArr[A + 1];
		$dp.attachEvent(B, "onkeydown", _tab);
	}
	this.init();
	_();
	_inputBindEvent("y,M,H,m,s");
	$d.upButton.onclick = function() {
		updownEvent(1);
	};
	$d.downButton.onclick = function() {
		updownEvent(-1);
	};
	$d.qsDiv.onclick = function() {
		if ($d.qsDivSel.style.display != "block") {
			$c._fillQS();
			showB($d.qsDivSel);
		} else {
			hide($d.qsDivSel);
		}
	};
	document.body.appendChild($d);
	function $() {
		var _ = $("a");
		divs = $("div"), ipts = $("input"), btns = $("button"), spans = $("span");
		$d.navLeftImg = _[0];
		$d.leftImg = _[1];
		$d.rightImg = _[3];
		$d.navRightImg = _[2];
		$d.rMD = divs[9];
		$d.MI = ipts[0];
		$d.yI = ipts[1];
		$d.titleDiv = divs[0];
		$d.MD = divs[4];
		$d.yD = divs[6];
		$d.qsDivSel = divs[10];
		$d.dDiv = divs[11];
		$d.tDiv = divs[12];
		$d.HD = divs[13];
		$d.mD = divs[14];
		$d.sD = divs[15];
		$d.qsDiv = divs[16];
		$d.bDiv = divs[17];
		$d.HI = ipts[2];
		$d.mI = ipts[4];
		$d.sI = ipts[6];
		$d.clearI = ipts[7];
		$d.todayI = ipts[8];
		$d.okI = ipts[9];
		$d.upButton = btns[0];
		$d.downButton = btns[1];
		$d.timeSpan = spans[0];
		function $($) {
			return $d.getElementsByTagName($);
		}
	}
	function _() {
		$d.navLeftImg.onclick = function() {
			$ny = $ny <= 0 ? $ny - 1 : -1;
			if ($ny % 5 == 0) {
				$d.yI.focus();
				return;
			}
			$d.yI.value = $dt.y - 1;
			$d.yI.onblur();
		};
		$d.leftImg.onclick = function() {
			$dt.attr("M", -1);
			$d.MI.onblur();
		};
		$d.rightImg.onclick = function() {
			$dt.attr("M", 1);
			$d.MI.onblur();
		};
		$d.navRightImg.onclick = function() {
			$ny = $ny >= 0 ? $ny + 1 : 1;
			if ($ny % 5 == 0) {
				$d.yI.focus();
				return;
			}
			$d.yI.value = $dt.y + 1;
			$d.yI.onblur();
		};
	}
}
My97DP.prototype = {
	init	         : function() {
		$ny = 0;
		$dp.cal = this;
		if ($dp.readOnly && $dp.el.readOnly != null) {
			$dp.el.readOnly = true;
			$dp.el.blur();
		}
		$();
		this._dealFmt();
		$dt = this.newdate = new DPDate();
		$tdt = new DPDate();
		$sdt = this.date = new DPDate();
		this.dateFmt = this.doExp($dp.dateFmt);
		this.autoPickDate = $dp.autoPickDate == null ? ($dp.has.st
		        && $dp.has.st ? false : true) : $dp.autoPickDate;
		$dp.autoUpdateOnChanged = $dp.autoUpdateOnChanged == null
		        ? ($dp.isShowOK && $dp.has.d ? false : true)
		        : $dp.autoUpdateOnChanged;
		this.ddateRe = this._initRe("disabledDates");
		this.ddayRe = this._initRe("disabledDays");
		this.sdateRe = this._initRe("specialDates");
		this.sdayRe = this._initRe("specialDays");
		this.minDate = this.doCustomDate($dp.minDate,
		        $dp.minDate != $dp.defMinDate ? $dp.realFmt : $dp.realFullFmt,
		        $dp.defMinDate
		);
		this.maxDate = this.doCustomDate($dp.maxDate,
		        $dp.maxDate != $dp.defMaxDate ? $dp.realFmt : $dp.realFullFmt,
		        $dp.defMaxDate
		);
		if (this.minDate.compareWith(this.maxDate) > 0) {
			$dp.errMsg = $lang.err_1;
		}
		if (this.loadDate()) {
			this._makeDateInRange();
			this.oldValue = $dp.el[$dp.elProp];
		} else {
			this.mark(false, 2);
		}
		_setAll($dt);
		$d.timeSpan.innerHTML = $lang.timeStr;
		$d.clearI.value = $lang.clearStr;
		$d.todayI.value = $lang.todayStr;
		$d.okI.value = $lang.okStr;
		$d.okI.disabled = !$c.checkValid($sdt);
		this.initShowAndHide();
		this.initBtn();
		if ($dp.errMsg) {
			alert($dp.errMsg);
		}
		this.draw();
		if ($dp.el.nodeType == 1 && $dp.el.My97Mark === undefined) {
			$dp.attachEvent($dp.el, "onkeydown", _tab);
			$dp.attachEvent($dp.el, "onblur", function() {
				if ($dp.dd.style.display == "none") {
					$c.close();
				}
			});
		}
		$c.currFocus = $dp.el;
		hideSel();
		function $() {
			var _, $;
			for (_ = 0; ($ = document.getElementsByTagName("link")[_]); _++) {
				if ($.rel.indexOf("style") != -1 && $.title) {
					$.disabled = true;
					if ($.title == $dp.skin) {
						$.disabled = false;
					}
				}
			}
		}
	},
	_makeDateInRange	: function() {
		var _ = this.checkRange();
		if (_ != 0) {
			var $;
			if (_ > 0) {
				$ = this.maxDate;
			} else {
				$ = this.minDate;
			}
			if ($dp.has.sd) {
				$dt.y = $.y;
				$dt.M = $.M;
				$dt.d = $.d;
			}
			if ($dp.has.st) {
				$dt.H = $.H;
				$dt.m = $.m;
				$dt.s = $.s;
			}
		}
	},
	splitDate	     : function(M, F, O, _, D, B, A, N, G) {
		var E;
		if (M && M.loadDate) {
			E = M;
		} else {
			E = new DPDate();
			if (M != "") {
				F = F || $dp.dateFmt;
				var K, P = 0, J, C = /yyyy|yyy|yy|y|MMMM|MMM|MM|M|dd|d|%ld|HH|H|mm|m|ss|s|DD|D|WW|W|w/g, Q = F
				        .match(C);
				C.lastIndex = 0;
				if (G) {
					J = M.split(/\W+/);
				} else {
					var $ = 0, H = "^";
					while ((J = C.exec(F)) !== null) {
						if ($ >= 0) {
							H += F.substring($, J.index);
						}
						$ = C.lastIndex;
						switch (J[0]) {
							case "yyyy" :
								H += "(\\d{4})";
								break;
							case "yyy" :
								H += "(\\d{3})";
								break;
							case "MMMM" :
							case "MMM" :
							case "DD" :
							case "D" :
								H += "(\\D+)";
								break;
							default :
								H += "(\\d\\d?)";
								break;
						}
					}
					H += ".*$";
					J = new RegExp(H).exec(M);
					P = 1;
				}
				if (J) {
					for (K = 0; K < Q.length; K++) {
						var L = J[K + P];
						if (L) {
							switch (Q[K]) {
								case "MMMM" :
								case "MMM" :
									E.M = I(Q[K], L);
									break;
								case "y" :
								case "yy" :
									L = pInt2(L, 0);
									if (L < 50) {
										L += 2000;
									} else {
										L += 1900;
									}
									E.y = L;
									break;
								case "yyy" :
									E.y = pInt2(L, 0) + $dp.yearOffset;
									break;
								default :
									E[Q[K].slice(-1)] = L;
									break;
							}
						}
					}
				} else {
					E.d = 32;
				}
			}
		}
		E.coverDate(O, _, D, B, A, N);
		return E;
		function I($, A) {
			var B = $ == "MMMM" ? $lang.aLongMonStr : $lang.aMonStr;
			for (var _ = 0; _ < 12; _++) {
				if (B[_].toLowerCase() == A.substr(0, B[_].length)
				        .toLowerCase()) {
					return _ + 1;
				}
			}
			return -1;
		}
	},
	_initRe	         : function(B) {
		var A, _ = $dp[B], $ = "(?:";
		if (_) {
			for (A = 0; A < _.length; A++) {
				$ += this.doExp(_[A]);
				if (A != _.length - 1) {
					$ += "|";
				}
			}
			$ = new RegExp($ + ")");
		} else {
			$ = null;
		}
		return $;
	},
	update	         : function() {
		var $ = this.getNewDateStr();
		if ($dp.el[$dp.elProp] != $) {
			$dp.el[$dp.elProp] = $;
		}
		this.setRealValue();
	},
	setRealValue	 : function($) {
		var _ = $dp.$($dp.vel), $ = rtn($, this.getNewDateStr($dp.realFmt));
		if (_) {
			_.value = $;
		}
		$dp.el.realValue = $;
	},
	doExp	         : function(s) {
		var ps = "yMdHms", arr, tmpEval, re = /#?\{(.*?)\}/;
		s = s + "";
		for (var i = 0; i < ps.length; i++) {
			s = s.replace("%" + ps.charAt(i), this.getP(ps.charAt(i), null,
			                $tdt
			        ));
		}
		if (s.substring(0, 3) == "#F{") {
			s = s.substring(3, s.length - 1);
			if (s.indexOf("return ") < 0) {
				s = "return " + s;
			}
			s = $dp.win.eval('new Function("' + s + '");');
			s = s();
		} else {
			while ((arr = re.exec(s)) != null) {
				arr.lastIndex = arr.index + arr[1].length + arr[0].length
				        - arr[1].length - 1;
				tmpEval = pInt(eval(arr[1]));
				if (tmpEval < 0) {
					tmpEval = "9700" + (-tmpEval);
				}
				s = s.substring(0, arr.index) + tmpEval
				        + s.substring(arr.lastIndex + 1);
			}
		}
		return s;
	},
	doCustomDate	 : function($, A, B) {
		var _;
		$ = this.doExp($);
		if (!$ || $ == "") {
			$ = B;
		}
		if (typeof $ == "object") {
			_ = $;
		} else {
			_ = this.splitDate($, A, null, null, 1, 0, 0, 0, true);
			_.y = ("" + _.y).replace(/^9700/, "-");
			_.M = ("" + _.M).replace(/^9700/, "-");
			_.d = ("" + _.d).replace(/^9700/, "-");
			_.H = ("" + _.H).replace(/^9700/, "-");
			_.m = ("" + _.m).replace(/^9700/, "-");
			_.s = ("" + _.s).replace(/^9700/, "-");
			if ($.indexOf("%ld") >= 0) {
				$ = $.replace(/%ld/g, "0");
				_.d = 0;
				_.M = pInt(_.M) + 1;
			}
			_.refresh();
		}
		return _;
	},
	loadDate	     : function() {
		var A, _;
		if ($dp.alwaysUseStartDate
		        || ($dp.startDate != "" && $dp.el[$dp.elProp] == "")) {
			A = this.doExp($dp.startDate);
			_ = $dp.realFmt;
		} else {
			A = $dp.el[$dp.elProp];
			_ = this.dateFmt;
		}
		$dt.loadFromDate(this.splitDate(A, _));
		if (A != "") {
			var $ = 1;
			if ($dp.has.sd && !this.isDate($dt)) {
				$dt.y = $tdt.y;
				$dt.M = $tdt.M;
				$dt.d = $tdt.d;
				$ = 0;
			}
			if ($dp.has.st && !this.isTime($dt)) {
				$dt.H = $tdt.H;
				$dt.m = $tdt.m;
				$dt.s = $tdt.s;
				$ = 0;
			}
			return $ && this.checkValid($dt);
		}
		return 1;
	},
	isDate	         : function($) {
		if ($.y != null) {
			$ = doStr($.y, 4) + "-" + $.M + "-" + $.d;
		}
		return $
		        .match(/^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/);
	},
	isTime	         : function($) {
		if ($.H != null) {
			$ = $.H + ":" + $.m + ":" + $.s;
		}
		return $
		        .match(/^([0-9]|([0-1][0-9])|([2][0-3])):([0-9]|([0-5][0-9])):([0-9]|([0-5][0-9]))$/);
	},
	checkRange	     : function(_, $) {
		_ = _ || $dt;
		var A = _.compareWith(this.minDate, $);
		if (A > 0) {
			A = _.compareWith(this.maxDate, $);
			if (A < 0) {
				A = 0;
			}
		}
		return A;
	},
	checkValid	     : function(A, $, _) {
		$ = $ || $dp.has.minUnit;
		var B = this.checkRange(A, $);
		if (B == 0) {
			B = 1;
			if ($ == "d" && _ == null) {
				_ = new Date(A.y, A.M - 1, A.d).getDay();
			}
			B = !this.testDisDay(_) && !this.testDisDate(A, $);
		} else {
			B = 0;
		}
		return B;
	},
	checkAndUpdate	 : function() {
		var A = $dp.el, $ = this, _ = $dp.el[$dp.elProp];
		if (_ != null) {
			if (_ != "" && !$dp.readOnly) {
				$.date.loadFromDate($.splitDate(_, $.dateFmt));
			}
			if (_ == ""
			        || ($.isDate($.date) && $.isTime($.date) && $
			                .checkValid($.date))) {
				if (_ != "") {
					$.newdate.loadFromDate($.date);
					$.update();
				} else {
					$.setRealValue("");
				}
			} else {
				return false;
			}
		}
		return true;
	},
	close	         : function($) {
		hideSel();
		if (this.checkAndUpdate()) {
			this.mark(true);
			$dp.hide();
		} else {
			if ($) {
				_cancelKey($);
				this.mark(false, 2);
			} else {
				this.mark(false);
			}
			$dp.show();
		}
	},
	_fd	             : function() {
		var _, F, $, J, C, G = new sb(), A = $lang.aWeekStr, B = $dp.firstDayOfWeek, H = "", E = "", K = new DPDate(
		        $dt.y, $dt.M, $dt.d, 0, 0, 0
		), I = K.y, D = K.M;
		C = 1 - new Date(I, D - 1, 1).getDay() + B;
		if (C > 1) {
			C -= 7;
		}
		G
		        .a("<table class=WdayTable width=100% border=0 cellspacing=0 cellpadding=0>");
		G.a("<tr class=MTitle align=center>");
		if ($dp.isShowWeek) {
			G.a("<td>" + A[0] + "</td>");
		}
		for (_ = 0; _ < 7; _++) {
			G.a("<td>" + A[(B + _) % 7 + 1] + "</td>");
		}
		G.a("</tr>");
		for (_ = 1, F = C; _ < 7; _++) {
			G.a("<tr>");
			for ($ = 0; $ < 7; $++) {
				K.loadDate(I, D, F++);
				K.refresh();
				if (K.M == D) {
					J = true;
					if (K.compareWith($sdt, "d") == 0) {
						H = "Wselday";
					} else {
						if (K.compareWith($tdt, "d") == 0) {
							H = "Wtoday";
						} else {
							H = ($dp.highLineWeekDay
							        && (0 == (B + $) % 7 || 6 == (B + $) % 7)
							        ? "Wwday"
							        : "Wday");
						}
					}
					E = ($dp.highLineWeekDay
					        && (0 == (B + $) % 7 || 6 == (B + $) % 7)
					        ? "WwdayOn"
					        : "WdayOn");
				} else {
					if ($dp.isShowOthers) {
						J = true;
						H = "WotherDay";
						E = "WotherDayOn";
					} else {
						J = false;
					}
				}
				if ($dp.isShowWeek && $ == 0 && (_ < 4 || J)) {
					G.a("<td class=Wweek>"
					        + getWeek(K, $dp.firstDayOfWeek == 0 ? 1 : 0)
					        + "</td>");
				}
				G.a("<td ");
				if (J) {
					if (this.checkValid(K, "d", $)) {
						if (this.testSpeDay(new Date(K.y, K.M - 1, K.d)
						        .getDay())
						        || this.testSpeDate(K)) {
							H = "WspecialDay";
						}
						G.a('onclick="day_Click(' + K.y + "," + K.M + "," + K.d
						        + ');" ');
						G.a("onmouseover=\"this.className='" + E + "'\" ");
						G.a("onmouseout=\"this.className='" + H + "'\" ");
					} else {
						H = "WinvalidDay";
					}
					G.a("class=" + H);
					G.a(">" + K.d + "</td>");
				} else {
					G.a("></td>");
				}
			}
			G.a("</tr>");
		}
		G.a("</table>");
		return G.j();
	},
	testDisDate	     : function(A, $) {
		var _ = this.testDate(A, this.ddateRe, $);
		return (this.ddateRe && $dp.opposite) ? !_ : _;
	},
	testDisDay	     : function($) {
		return this.testDay($, this.ddayRe);
	},
	testSpeDate	     : function($) {
		return this.testDate($, this.sdateRe);
	},
	testSpeDay	     : function($) {
		return this.testDay($, this.sdayRe);
	},
	testDate	     : function(A, _, $) {
		var B = $ == "d" ? $dp.realDateFmt : $dp.realFmt;
		return _ ? _.test(this.getDateStr(B, A)) : 0;
	},
	testDay	         : function(_, $) {
		return $ ? $.test(_) : 0;
	},
	_f	             : function(p, c, r, e, isR) {
		var s = new sb(), fp = isR ? "r" + p : p;
		bak = $dt[p];
		s.a("<table cellspacing=0 cellpadding=3 border=0");
		for (var i = 0; i < r; i++) {
			s.a('<tr nowrap="nowrap">');
			for (var j = 0; j < c; j++) {
				s.a("<td nowrap ");
				$dt[p] = eval(e);
				if (($dp.opposite && this.checkRange($dt, p) == 0)
				        || this.checkValid($dt, p)) {
					s
					        .a("class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown=\"");
					s.a("hide($d." + p + "D);$d." + fp + "I.value=" + $dt[p]
					        + ";$d." + fp + 'I.blur();"');
				} else {
					s.a("class='invalidMenu'");
				}
				s.a(">" + (p == "M" ? $lang.aMonStr[$dt[p] - 1] : $dt[p])
				        + "</td>");
			}
			s.a("</tr>");
		}
		s.a("</table>");
		$dt[p] = bak;
		return s.j();
	},
	_fMyPos	         : function(_, A) {
		if (_) {
			var $ = _.offsetLeft;
			if ($IE) {
				$ = _.getBoundingClientRect().left;
			}
			A.style.left = $;
		}
	},
	_fM	             : function($) {
		this._fMyPos($, $d.MD);
		$d.MD.innerHTML = this._f("M", 2, 6, "i+j*6+1", $ == $d.rMI);
	},
	_fy	             : function(A, $) {
		var _ = new sb();
		$ = rtn($, $dt.y - 5);
		_.a(this._f("y", 2, 5, $ + "+i+j*5", A == $d.ryI));
		_
		        .a("<table cellspacing=0 cellpadding=3 border=0 align=center><tr><td ");
		_
		        .a(this.minDate.y < $
		                ? "class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown='if(event.preventDefault)event.preventDefault();event.cancelBubble=true;$c._fy(0,"
		                        + ($ - 10) + ")'"
		                : "class='invalidMenu'");
		_
		        .a(">\u2190</td><td class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown=\"hide($d.yD);$d.yI.blur();\">\xd7</td><td ");
		_
		        .a(this.maxDate.y > $ + 10
		                ? "class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onmousedown='if(event.preventDefault)event.preventDefault();event.cancelBubble=true;$c._fy(0,"
		                        + ($ + 10) + ")'"
		                : "class='invalidMenu'");
		_.a(">\u2192</td></tr></table>");
		this._fMyPos(A, $d.yD);
		$d.yD.innerHTML = _.j();
	},
	_fHMS	         : function($, A, _) {
		$d[$ + "D"].innerHTML = this._f($, 6, A, _);
	},
	_fH	             : function() {
		this._fHMS("H", 4, "i * 6 + j");
	},
	_fm	             : function() {
		this._fHMS("m", 2, "i * 30 + j * 5");
	},
	_fs	             : function() {
		this._fHMS("s", 1, "j * 10");
	},
	_fillQS	         : function($) {
		this.initQS();
		var C = this.QS, B = C.style, A = new sb();
		A
		        .a("<table class=WdayTable width=100% height=100% border=0 cellspacing=0 cellpadding=0>");
		A.a('<tr class=MTitle><td><div style="float:left">' + $lang.quickStr
		        + "</div>");
		if (!$) {
			A
			        .a('<div style="float:right;cursor:pointer" onclick="hide($d.qsDivSel);">\xd7</div>');
		}
		A.a("</td></tr>");
		for (var _ = 0; _ < C.length; _++) {
			if (C[_]) {
				A
				        .a("<tr><td style='text-align:left' nowrap='nowrap' class='menu' onmouseover=\"this.className='menuOn'\" onmouseout=\"this.className='menu'\" onclick=\"");
				A.a("day_Click(" + C[_].y + ", " + C[_].M + ", " + C[_].d + ","
				        + C[_].H + "," + C[_].m + "," + C[_].s + ');">');
				A.a("&nbsp;" + this.getDateStr(null, C[_]));
				A.a("</td></tr>");
			} else {
				A.a("<tr><td class='menu'>&nbsp;</td></tr>");
			}
		}
		A.a("</table>");
		$d.qsDivSel.innerHTML = A.j();
	},
	_dealFmt	     : function() {
		$(/w/);
		$(/WW|W/);
		$(/DD|D/);
		$(/yyyy|yyy|yy|y/);
		$(/MMMM|MMM|MM|M/);
		$(/dd|d/);
		$(/HH|H/);
		$(/mm|m/);
		$(/ss|s/);
		$dp.has.sd = ($dp.has.y || $dp.has.M || $dp.has.d) ? true : false;
		$dp.has.st = ($dp.has.H || $dp.has.m || $dp.has.s) ? true : false;
		$dp.realFullFmt = $dp.realFullFmt.replace(/%Date/, $dp.realDateFmt)
		        .replace(/%Time/, $dp.realTimeFmt);
		if ($dp.has.sd) {
			if ($dp.has.st) {
				$dp.realFmt = $dp.realFullFmt;
			} else {
				$dp.realFmt = $dp.realDateFmt;
			}
		} else {
			$dp.realFmt = $dp.realTimeFmt;
		}
		function $(_) {
			var $ = (_ + "").slice(1, 2);
			$dp.has[$] = _.exec($dp.dateFmt)
			        ? ($dp.has.minUnit = $, true)
			        : false;
		}
	},
	initShowAndHide	 : function() {
		var $ = 0;
		$dp.has.y ? ($ = 1, show($d.yI, $d.navLeftImg, $d.navRightImg)) : hide(
		        $d.yI, $d.navLeftImg, $d.navRightImg
		);
		$dp.has.M ? ($ = 1, show($d.MI, $d.leftImg, $d.rightImg)) : hide($d.MI,
		        $d.leftImg, $d.rightImg
		);
		$ ? show($d.titleDiv) : hide($d.titleDiv);
		if ($dp.has.st) {
			show($d.tDiv);
			disHMS($d.HI, $dp.has.H);
			disHMS($d.mI, $dp.has.m);
			disHMS($d.sI, $dp.has.s);
		} else {
			hide($d.tDiv);
		}
		shorH($d.clearI, $dp.isShowClear);
		shorH($d.todayI, $dp.isShowToday);
		shorH($d.okI, $dp.isShowOK);
		shorH($d.qsDiv, !$dp.doubleCalendar && $dp.has.d && $dp.qsEnabled);
		if ($dp.eCont || !($dp.isShowClear || $dp.isShowToday || $dp.isShowOK)) {
			hide($d.bDiv);
		} else {
			show($d.bDiv);
		}
	},
	mark	         : function(B, $) {
		var _ = $dp.el, D = $FF ? "class" : "className";
		if (B) {
			C(_);
		} else {
			if ($ == null) {
				$ = $dp.errDealMode;
			}
			switch ($) {
				case 0 :
					if (confirm($lang.errAlertMsg)) {
						_[$dp.elProp] = this.oldValue;
						C(_);
					} else {
						A(_);
					}
					break;
				case 1 :
					_[$dp.elProp] = this.oldValue;
					C(_);
					break;
				case 2 :
					A(_);
					break;
			}
		}
		function C($) {
			var A = $.className;
			if (A) {
				var _ = A.replace(/WdateFmtErr/g, "");
				if (A != _) {
					$.setAttribute(D, _);
				}
			}
		}
		function A($) {
			$.setAttribute(D, $.className + " WdateFmtErr");
		}
	},
	getP	         : function($, G, E) {
		E = E || $sdt;
		var H, F = [
		        $ + $, $
		], _, C = E[$], A = function($) {
			return doStr(C, $.length);
		};
		switch ($) {
			case "w" :
				C = getDay(E);
				break;
			case "D" :
				var B = getDay(E) + 1;
				A = function($) {
					return $.length == 2
					        ? $lang.aLongWeekStr[B]
					        : $lang.aWeekStr[B];
				};
				break;
			case "W" :
				C = getWeek(E);
				break;
			case "y" :
				F = [
				        "yyyy", "yyy", "yy", "y"
				];
				G = G || F[0];
				A = function($) {
					return doStr(
					        ($.length < 4) ? ($.length < 3 ? E.y % 100 : (E.y
					                + 2000 - $dp.yearOffset)
					                % 1000) : C, $.length
					);
				};
				break;
			case "M" :
				F = [
				        "MMMM", "MMM", "MM", "M"
				];
				A = function($) {
					return ($.length == 4)
					        ? $lang.aLongMonStr[C - 1]
					        : ($.length == 3) ? $lang.aMonStr[C - 1] : doStr(C,
					                $.length
					        );
				};
				break;
		}
		G = G || $ + $;
		if ("yMdHms".indexOf($) > -1 && $ != "y" && !$dp.has[$]) {
			if ("Hms".indexOf($) > -1) {
				C = 0;
			} else {
				C = 1;
			}
		}
		var D = [];
		for (H = 0; H < F.length; H++) {
			_ = F[H];
			if (G.indexOf(_) >= 0) {
				D[H] = A(_);
				G = G.replace(_, "{" + H + "}");
			}
		}
		for (H = 0; H < D.length; H++) {
			G = G.replace(new RegExp("\\{" + H + "\\}", "g"), D[H]);
		}
		return G;
	},
	getDateStr	     : function(D, B) {
		B = B || this.splitDate($dp.el[$dp.elProp], this.dateFmt) || $sdt;
		D = D || this.dateFmt;
		if (D.indexOf("%ld") >= 0) {
			var _ = new DPDate();
			_.loadFromDate(B);
			_.d = 0;
			_.M = pInt(_.M) + 1;
			_.refresh();
			D = D.replace(/%ld/g, _.d);
		}
		var A = "ydHmswW";
		for (var $ = 0; $ < A.length; $++) {
			var C = A.charAt($);
			D = this.getP(C, D, B);
		}
		if ($dp.has.D) {
			D = D.replace(/DD/g, "%dd").replace(/D/g, "%d");
			D = this.getP("M", D, B);
			D = D.replace(/\%dd/g, this.getP("D", "DD")).replace(/\%d/g,
			        this.getP("D", "D")
			);
		} else {
			D = this.getP("M", D, B);
		}
		return D;
	},
	getNewP	         : function(_, $) {
		return this.getP(_, $, $dt);
	},
	getNewDateStr	 : function($) {
		return this.getDateStr($, $dt);
	},
	draw	         : function() {
		$d.rMD.innerHTML = "";
		if ($dp.doubleCalendar) {
			$c.autoPickDate = true;
			$dp.isShowOthers = false;
			$d.className = "WdateDiv WdateDiv2";
			var $ = new sb();
			$
			        .a("<table class=WdayTable2 width=100% cellspacing=0 cellpadding=0 border=1><tr><td valign=top>");
			$.a(this._fd());
			$.a("</td><td valign=top>");
			$dt.attr("M", 1);
			$.a(this._fd());
			$d.rMI = $d.MI.cloneNode(true);
			$d.ryI = $d.yI.cloneNode(true);
			$d.rMD.appendChild($d.rMI);
			$d.rMD.appendChild($d.ryI);
			$d.rMI.value = $lang.aMonStr[$dt.M - 1];
			$d.rMI.realValue = $dt.M;
			$d.ryI.value = $dt.y;
			_inputBindEvent("rM,ry");
			$d.rMI.className = $d.ryI.className = "yminput";
			$dt.attr("M", -1);
			$.a("</td></tr></table>");
			$d.dDiv.innerHTML = $.j();
		} else {
			$d.className = "WdateDiv";
			$d.dDiv.innerHTML = this._fd();
		}
		if (!$dp.has.d || $dp.autoShowQS) {
			this._fillQS(true);
			showB($d.qsDivSel);
		} else {
			hide($d.qsDivSel);
		}
		this.autoSize();
	},
	autoSize	     : function() {
		var C = parent.document.getElementsByTagName("iframe");
		for (var B = 0; B < C.length; B++) {
			var A = $d.style.height;
			$d.style.height = "";
			var $ = $d.offsetHeight;
			if (C[B].contentWindow == window && $) {
				C[B].style.width = $d.offsetWidth + "px";
				var _ = $d.tDiv.offsetHeight;
				if (_ && $d.bDiv.style.display == "none"
				        && $d.tDiv.style.display != "none"
				        && document.body.scrollHeight - $ >= _) {
					$ += _;
					$d.style.height = $;
				} else {
					$d.style.height = A;
				}
				C[B].style.height = Math.max($, $d.offsetHeight) + "px";
			}
		}
		$d.qsDivSel.style.width = $d.dDiv.offsetWidth;
		$d.qsDivSel.style.height = $d.dDiv.offsetHeight;
	},
	pickDate	     : function() {
		$dt.d = Math.min(new Date($dt.y, $dt.M, 0).getDate(), $dt.d);
		$sdt.loadFromDate($dt);
		this.update();
		if (!$dp.eCont) {
			if (this.checkValid($dt)) {
				$c.mark(true);
				elFocus();
				hide($dp.dd);
			} else {
				$c.mark(false);
			}
		}
		if ($dp.onpicked) {
			callFunc("onpicked");
		} else {
			if (this.oldValue != $dp.el[$dp.elProp] && $dp.el.onchange) {
				fireEvent($dp.el, "change");
			}
		}
	},
	initBtn	         : function() {
		$d.clearI.onclick = function() {
			if (!callFunc("onclearing")) {
				$dp.el[$dp.elProp] = "";
				$c.setRealValue("");
				elFocus();
				hide($dp.dd);
				if ($dp.oncleared) {
					callFunc("oncleared");
				} else {
					if ($c.oldValue != $dp.el[$dp.elProp] && $dp.el.onchange) {
						fireEvent($dp.el, "change");
					}
				}
			}
		};
		$d.okI.onclick = function() {
			day_Click();
		};
		if (this.checkValid($tdt)) {
			$d.todayI.disabled = false;
			$d.todayI.onclick = function() {
				$dt.loadFromDate($tdt);
				day_Click();
			};
		} else {
			$d.todayI.disabled = true;
		}
	},
	initQS	         : function() {
		var H, B, C, A, F = [], E = 5, _ = $dp.quickSel.length, G = $dp.has.minUnit;
		if (_ > E) {
			_ = E;
		} else {
			if (G == "m" || G == "s") {
				F = [
				        0, 15, 30, 45, 59, -60, -45, -30, -15, -1
				];
			} else {
				for (H = 0; H < E * 2; H++) {
					F[H] = $dt[G] - E + 1 + H;
				}
			}
		}
		for (H = B = 0; H < _; H++) {
			C = this.doCustomDate($dp.quickSel[H]);
			if (this.checkValid(C)) {
				this.QS[B++] = C;
			}
		}
		var D = "yMdHms", $ = [
		        1, 1, 1, 0, 0, 0
		];
		for (H = 0; H <= D.indexOf(G); H++) {
			$[H] = $dt[D.charAt(H)];
		}
		for (H = 0; B < E; H++) {
			if (H < F.length) {
				C = new DPDate($[0], $[1], $[2], $[3], $[4], $[5]);
				C[G] = F[H];
				C.refresh();
				if (this.checkValid(C)) {
					this.QS[B++] = C;
				}
			} else {
				this.QS[B++] = null;
			}
		}
	}
};
function elFocus() {
	var _ = $dp.el;
	try {
		if (_.style.display != "none"
		        && _.type != "hidden"
		        && (_.nodeName.toLowerCase() == "input" || _.nodeName
		                .toLowerCase() == "textarea")) {
			if ($dp.srcEl == _) {
				$dp.el.My97Mark = true;
			}
			$dp.el.focus();
			return;
		}
	} catch ($) {
	}
	_.My97Mark = false;
}
function sb() {
	this.s = new Array();
	this.i = 0;
	this.a = function($) {
		this.s[this.i++] = $;
	};
	this.j = function() {
		return this.s.join("");
	};
}
function getWeek(A, _) {
	_ = _ || 0;
	var B = new Date(A.y, A.M - 1, A.d + _);
	B.setDate(B.getDate() - (B.getDay() + 6) % 7 + $dp.whichDayIsfirstWeek - 1);
	var $ = B.valueOf();
	B.setMonth(0);
	B.setDate(4);
	return Math.round(($ - B.valueOf()) / (7 * 86400000)) + 1;
}
function getDay($) {
	var _ = new Date($.y, $.M - 1, $.d);
	return _.getDay();
}
function show() {
	setDisp(arguments, "");
}
function showB() {
	setDisp(arguments, "block");
}
function hide() {
	setDisp(arguments, "none");
}
function setDisp(_, $) {
	for (i = 0; i < _.length; i++) {
		_[i].style.display = $;
	}
}
function shorH(_, $) {
	$ ? show(_) : hide(_);
}
function disHMS(_, $) {
	if ($) {
		_.disabled = false;
	} else {
		_.disabled = true;
		_.value = "00";
	}
}
function c(p, pv) {
	if (p == "M") {
		pv = makeInRange(pv, 1, 12);
	} else {
		if (p == "H") {
			pv = makeInRange(pv, 0, 23);
		} else {
			if ("ms".indexOf(p) >= 0) {
				pv = makeInRange(pv, 0, 59);
			}
		}
	}
	if ($sdt[p] != pv && !callFunc(p + "changing")) {
		var func = 'sv("' + p + '",' + pv + ")", rv = $c.checkRange();
		if (rv == 0) {
			eval(func);
		} else {
			if (rv < 0) {
				_setFrom($c.minDate);
			} else {
				if (rv > 0) {
					_setFrom($c.maxDate);
				}
			}
		}
		$d.okI.disabled = !$c.checkValid($sdt);
		if ("yMd".indexOf(p) >= 0) {
			$c.draw();
		}
		callFunc(p + "changed");
	}
	function _setFrom($) {
		_setAll($c.checkValid($) ? $ : $sdt);
	}
}
function _setAll($) {
	sv("y", $.y);
	sv("M", $.M);
	sv("d", $.d);
	sv("H", $.H);
	sv("m", $.m);
	sv("s", $.s);
}
function day_Click(A, D, F, $, E, B) {
	var C = new DPDate($dt.y, $dt.M, $dt.d, $dt.H, $dt.m, $dt.s);
	$dt.loadDate(A, D, F, $, E, B);
	if (!callFunc("onpicking")) {
		var _ = C.y == A && C.M == D && C.d == F;
		if (!_ && arguments.length != 0) {
			c("y", A);
			c("M", D);
			c("d", F);
			$c.currFocus = $dp.el;
			if ($dp.autoUpdateOnChanged) {
				$c.update();
			}
		}
		if ($c.autoPickDate || _ || arguments.length == 0) {
			$c.pickDate();
		}
	} else {
		$dt = C;
	}
}
function callFunc($) {
	var _;
	if ($dp[$]) {
		_ = $dp[$].call($dp.el, $dp);
	}
	return _;
}
function sv(_, $) {
	if ($ == null) {
		$ = $dt[_];
	}
	$sdt[_] = $dt[_] = $;
	if ("yHms".indexOf(_) >= 0) {
		$d[_ + "I"].value = $;
	}
	if (_ == "M") {
		$d.MI.realValue = $;
		$d.MI.value = $lang.aMonStr[$ - 1];
	}
}
function makeInRange(A, _, $) {
	if (A < _) {
		A = _;
	} else {
		if (A > $) {
			A = $;
		}
	}
	return A;
}
function attachTabEvent($, _) {
	$.attachEvent("onkeydown", function() {
		var A = event, $ = (A.which == undefined) ? A.keyCode : A.which;
		if ($ == 9) {
			_();
		}
	});
}
function doStr($, _) {
	$ = $ + "";
	while ($.length < _) {
		$ = "0" + $;
	}
	return $;
}
function hideSel() {
	hide($d.yD, $d.MD, $d.HD, $d.mD, $d.sD);
}
function updownEvent($) {
	if ($c.currFocus == undefined) {
		$c.currFocus = $d.HI;
	}
	switch ($c.currFocus) {
		case $d.HI :
			c("H", $dt.H + $);
			break;
		case $d.mI :
			c("m", $dt.m + $);
			break;
		case $d.sI :
			c("s", $dt.s + $);
			break;
	}
	if ($dp.autoUpdateOnChanged) {
		$c.update();
	}
}
function DPDate($, _, B, C, A, D) {
	this.loadDate($, _, B, C, A, D);
}
DPDate.prototype = {
	loadDate	 : function(_, C, E, $, D, A) {
		var B = new Date();
		this.y = pInt3(_, this.y, B.getFullYear());
		this.M = pInt3(C, this.M, B.getMonth() + 1);
		this.d = $dp.has.d ? pInt3(E, this.d, B.getDate()) : 1;
		this.H = pInt3($, this.H, B.getHours());
		this.m = pInt3(D, this.m, B.getMinutes());
		this.s = pInt3(A, this.s, B.getSeconds());
	},
	loadFromDate	: function($) {
		if ($) {
			this.loadDate($.y, $.M, $.d, $.H, $.m, $.s);
		}
	},
	coverDate	 : function(_, C, E, $, D, A) {
		var B = new Date();
		this.y = pInt3(this.y, _, B.getFullYear());
		this.M = pInt3(this.M, C, B.getMonth() + 1);
		this.d = $dp.has.d ? pInt3(this.d, E, B.getDate()) : 1;
		this.H = pInt3(this.H, $, B.getHours());
		this.m = pInt3(this.m, D, B.getMinutes());
		this.s = pInt3(this.s, A, B.getSeconds());
	},
	compareWith	 : function(B, C) {
		var _ = "yMdHms", D, A;
		C = _.indexOf(C);
		C = C >= 0 ? C : 5;
		for (var $ = 0; $ <= C; $++) {
			A = _.charAt($);
			D = this[A] - B[A];
			if (D > 0) {
				return 1;
			} else {
				if (D < 0) {
					return -1;
				}
			}
		}
		return 0;
	},
	refresh	     : function() {
		var $ = new Date(this.y, this.M - 1, this.d, this.H, this.m, this.s);
		this.y = $.getFullYear();
		this.M = $.getMonth() + 1;
		this.d = $.getDate();
		this.H = $.getHours();
		this.m = $.getMinutes();
		this.s = $.getSeconds();
		return !isNaN(this.y);
	},
	attr	     : function(A, _) {
		if ("yMdHms".indexOf(A) >= 0) {
			var $ = this.d;
			if (A == "M") {
				this.d = 1;
			}
			this[A] += _;
			this.refresh();
			this.d = $;
		}
	}
};
function pInt($) {
	return parseInt($, 10);
}
function pInt2($, _) {
	return rtn(pInt($), _);
}
function pInt3(_, $, A) {
	return pInt2(_, rtn($, A));
}
function rtn($, _) {
	return $ == null || isNaN($) ? _ : $;
}
function fireEvent($, _) {
	if ($IE) {
		$.fireEvent("on" + _);
	} else {
		var A = document.createEvent("HTMLEvents");
		A.initEvent(_, true, true);
		$.dispatchEvent(A);
	}
}
function _foundInput(A) {
	var $, _, B = "y,M,H,m,s,ry,rM".split(",");
	for (_ = 0; _ < B.length; _++) {
		$ = B[_];
		if ($d[$ + "I"] == A) {
			return $.slice($.length - 1, $.length);
		}
	}
	return 0;
}
function _focus($) {
	var _ = _foundInput(this);
	if (!_) {
		return;
	}
	$c.currFocus = this;
	if (_ == "y") {
		this.className = "yminputfocus";
	} else {
		if (_ == "M") {
			this.className = "yminputfocus";
			this.value = this["realValue"];
		}
	}
	this.select();
	$c["_f" + _](this);
	showB($d[_ + "D"]);
}
function _blur(showDiv) {
	var p = _foundInput(this), isR, mStr, v = this.value, oldv = $dt[p];
	if (p == 0) {
		return;
	}
	$dt[p] = Number(v) >= 0 ? Number(v) : $dt[p];
	if (p == "y") {
		isR = this == $d.ryI;
		if (isR && $dt.M == 12) {
			$dt.y -= 1;
		}
	} else {
		if (p == "M") {
			isR = this == $d.rMI;
			if (isR) {
				mStr = $lang.aMonStr[$dt[p] - 1];
				if (oldv == 12) {
					$dt.y += 1;
				}
				$dt.attr("M", -1);
			}
			if ($sdt.M == $dt.M) {
				this.value = mStr || $lang.aMonStr[$dt[p] - 1];
			}
			if (($sdt.y != $dt.y)) {
				c("y", $dt.y);
			}
		}
	}
	eval('c("' + p + '",' + $dt[p] + ")");
	if (showDiv !== true) {
		if (p == "y" || p == "M") {
			this.className = "yminput";
		}
		hide($d[p + "D"]);
	}
	if ($dp.autoUpdateOnChanged) {
		$c.update();
	}
}
function _cancelKey($) {
	if ($.preventDefault) {
		$.preventDefault();
		$.stopPropagation();
	} else {
		$.cancelBubble = true;
		$.returnValue = false;
	}
	if ($OPERA) {
		$.keyCode = 0;
	}
}
function _inputBindEvent(A) {
	var $ = A.split(",");
	for (var _ = 0; _ < $.length; _++) {
		var B = $[_] + "I";
		$d[B].onfocus = _focus;
		$d[B].onblur = _blur;
	}
}
function _tab(G) {
	var J = G.srcElement || G.target, Q = G.which || G.keyCode;
	isShow = $dp.dd.style.display != "none";
	if (Q > 57) {
		Q -= 48;
	}
	if ($dp.enableKeyboard && isShow) {
		if (!J.nextCtrl) {
			J.nextCtrl = $dp.focusArr[1];
			$c.currFocus = $dp.el;
		}
		if (J == $dp.el) {
			$c.currFocus = $dp.el;
		}
		if (Q == 27) {
			if (J == $dp.el) {
				$c.close();
				return;
			} else {
				$dp.el.focus();
			}
		}
		if (Q >= 37 && Q <= 40) {
			var H;
			if ($c.currFocus == $dp.el || $c.currFocus == $d.okI) {
				if ($dp.has.d) {
					U = "d";
					if (Q == 38) {
						$dt[U] -= 7;
					} else {
						if (Q == 39) {
							$dt[U] += 1;
						} else {
							if (Q == 37) {
								$dt[U] -= 1;
							} else {
								$dt[U] += 7;
							}
						}
					}
					$dt.refresh();
					c("y", $dt.y);
					c("M", $dt.M);
					c("d", $dt[U]);
					_cancelKey(G);
					return;
				} else {
					U = $dp.has.minUnit;
					$d[U + "I"].focus();
				}
			}
			U = U || _foundInput($c.currFocus);
			if (U) {
				if (Q == 38 || Q == 39) {
					$dt[U] += 1;
				} else {
					$dt[U] -= 1;
				}
				$dt.refresh();
				$c.currFocus.value = $dt[U];
				_blur.call($c.currFocus, true);
				$c.currFocus.select();
			}
		} else {
			if (Q == 9) {
				var $ = J.nextCtrl;
				for (var T = 0; T < $dp.focusArr.length; T++) {
					if ($.disabled == true || $.offsetHeight == 0) {
						$ = $.nextCtrl;
					} else {
						break;
					}
				}
				if ($c.currFocus != $) {
					$c.currFocus = $;
					$.focus();
				}
			} else {
				if (Q == 13) {
					_blur.call($c.currFocus);
					if ($c.currFocus.type == "button") {
						$c.currFocus.click();
					} else {
						$c.pickDate();
					}
					$c.currFocus = $dp.el;
				}
			}
		}
	} else {
		if (Q == 9 && J == $dp.el) {
			$c.close();
		}
	}
	if ($dp.enableInputMask && !$OPERA && !$dp.readOnly
	        && $c.currFocus == $dp.el && (Q >= 48 && Q <= 57)) {
		var N = $dp.el, S = N.value, A = _(N), K = {
			str	: "",
			arr	: []
		}, T = 0, M, H = 0, W = 0, I = 0, L, V = /yyyy|yyy|yy|y|MM|M|dd|d|%ld|HH|H|mm|m|ss|s|WW|W|w/g, F = $dp.dateFmt
		        .match(V), D, C, X, P, O, B, L = 0;
		if (S != "") {
			I = S.match(/[0-9]/g);
			I = I == null ? 0 : I.length;
			for (T = 0; T < F.length; T++) {
				I -= Math.max(F[T].length, 2);
			}
			I = I >= 0 ? 1 : 0;
			if (I == 1 && A >= S.length) {
				A = S.length - 1;
			}
		}
		S = S.substring(0, A) + String.fromCharCode(Q) + S.substring(A + I);
		A++;
		for (T = 0; T < S.length; T++) {
			var E = S.charAt(T);
			if (E >= 0 && E <= 9) {
				K.str += E;
			} else {
				K.arr[K.str.length] = 1;
			}
		}
		S = "";
		V.lastIndex = 0;
		while ((M = V.exec($dp.dateFmt)) !== null) {
			W = M.index - (M[0] == "%ld" ? 1 : 0);
			if (H >= 0) {
				S += $dp.dateFmt.substring(H, W);
				if (A >= H + L && A <= W + L) {
					A += W - H;
				}
			}
			H = V.lastIndex;
			B = H - W;
			D = K.str.substring(0, B);
			C = M[0].charAt(0);
			X = pInt(D.charAt(0));
			if (K.str.length > 1) {
				P = K.str.charAt(1);
				O = X * 10 + pInt(P);
			} else {
				P = "";
				O = X;
			}
			if (K.arr[W] || C == "M" && O > 12 || C == "d" && O > 31
			        || C == "H" && O > 23 || "ms".indexOf(C) >= 0 && O > 59) {
				if (M[0].length == 2) {
					D = "0" + X;
					B = 1;
				} else {
					D = X;
				}
				A++;
			} else {
				if (B == 1) {
					D += P;
					L++;
				}
			}
			S += D;
			K.str = K.str.substring(B);
			if (K.str == "") {
				break;
			}
		}
		N.value = S;
		R(N, A);
		_cancelKey(G);
	}
	if (isShow && $c.currFocus != $dp.el
	        && !((Q >= 48 && Q <= 57) || Q == 8 || Q == 46)) {
		_cancelKey(G);
	}
	function _($) {
		var A = 0;
		if (document.selection) {
			$.focus();
			var _ = document.selection.createRange();
			_.moveStart("character", -$.value.length);
			A = _.text.length;
		} else {
			if ($.selectionStart || $.selectionStart == "0") {
				A = $.selectionStart;
			}
		}
		return (A);
	}
	function R(A, $) {
		if (A.setSelectionRange) {
			A.focus();
			A.setSelectionRange($, $);
		} else {
			if (A.createTextRange) {
				var _ = A.createTextRange();
				_.collapse(true);
				_.moveEnd("character", $);
				_.moveStart("character", $);
				_.select();
			}
		}
	}
}