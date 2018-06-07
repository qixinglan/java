package com.nci.dcs.jzgl.bbgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcStatisticsFxry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cc_statistics_fxry_month", schema = "SQJZ")
public class CcStatisticsFxryPerMonth implements java.io.Serializable {

	// Fields

	private String id;
	private String orgid;
	private Integer year;
	private Integer month;
	private Integer syzcrs;
	private Integer bdqkByzj;
	private Integer bdqkByjsQmjc;
	private Integer bdqkByjsCxhx;
	private Integer bdqkByjsCxjs;
	private Integer bdqkByjsSjzx;
	private Integer bdqkByjsQt;
	private Integer xflbGzZs;
	private Integer xflbGzXgjzl;
	private Integer xflbHxZs;
	private Integer xflbHxXgjzl;
	private Integer xflbJs;
	private Integer xflbZyjwzx;
	private Integer xflbBdzzql;
	private Integer fzlxWhgjaq;
	private Integer fzlxWhggaq;
	private Integer fzlxPhjjzx;
	private Integer fzlxQfgm;
	private Integer fzlxQfcc;
	private Integer fzlxFhsh;
	private Integer fzlxWhgfly;
	private Integer fzlxTwsh;
	private Integer fzlxDz;
	private Integer fzlxQt;
	private Integer hjCz;
	private Integer hjNc;
	private Integer hjGat;
	private Integer hjWg;
	private Integer hjQt;
	private Integer xbN;
	private Integer xbV;
	private Integer mzH;
	private Integer mzSs;
	private Integer nl18;
	private Integer nl1845;
	private Integer nl4660;
	private Integer nl61;
	private Integer whcdDzys;
	private Integer whcdGz;
	private Integer whcdCzyx;
	private Integer jyjxJy;
	private Integer jyjxJx;
	private Integer jyjxWn;
	private Integer jyjxWy;
	private Integer byJg;
	private Integer byZacf;
	private Integer byCdjx;
	private Integer byJzdbg;
	private Integer byJwzx;
	private Integer ljxmCxhx;
	private Integer ljxmCxjs;
	private Integer ljxmCxjw;
	private Integer ljxmJg;
	private Integer ljxmZacf;
	private Integer ljxmJzdbg;
	private Integer ljxmCdjx;
	private Integer ljxmXgjzl;
	private Integer ljxmJcjzl;
	private Integer ljxmJsjzry;
	private Integer ljxmJcjzry;
	private String superOrg;
	private Integer bdqkByjsSjQt;
	private Integer bdqkByjsSwZc;
	private Integer bdqkByjsSwFzc;
	private Integer bdqkByjsJzdbg;
	private String status;
	private Date reporttime;
	private String reason;
	private Integer ljxmZfz;
	private Integer byZfz;
	private Integer byLtg; 

	// Constructors
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "REPORTTIME")
	public Date getReporttime() {
		return reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	/** default constructor */
	public CcStatisticsFxryPerMonth() {
	}

	/** minimal constructor */
	public CcStatisticsFxryPerMonth(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcStatisticsFxryPerMonth(String id, String orgid, Integer year, Integer month,
			Integer syzcrs, Integer bdqkByzj, Integer bdqkByjsQmjc,
			Integer bdqkByjsCxhx, Integer bdqkByjsCxjs,
			Integer bdqkByjsSjzx, Integer bdqkByjsQt,
			Integer xflbGzZs, Integer xflbGzXgjzl, Integer xflbHxZs,
			Integer xflbHxXgjzl, Integer xflbJs, Integer xflbZyjwzx,
			Integer xflbBdzzql, Integer fzlxWhgjaq,
			Integer fzlxWhggaq, Integer fzlxPhjjzx, Integer fzlxQfgm,
			Integer fzlxQfcc, Integer fzlxFhsh, Integer fzlxWhgfly,
			Integer fzlxTwsh, Integer fzlxDz, Integer fzlxQt,
			Integer hjCz, Integer hjNc, Integer hjGat,
			Integer hjWg, Integer hjQt, Integer xbN, Integer xbV,
			Integer mzH, Integer mzSs, Integer nl18,
			Integer nl1845, Integer nl4660, Integer nl61,
			Integer whcdDzys, Integer whcdGz, Integer whcdCzyx,
			Integer jyjxJy, Integer jyjxJx, Integer jyjxWn,
			Integer jyjxWy, Integer byJg, Integer byZacf,
			Integer byCdjx, Integer byJzdbg, Integer byJwzx,
			Integer ljxmCxhx, Integer ljxmCxjs, Integer ljxmCxjw,
			Integer ljxmJg, Integer ljxmZacf, Integer ljxmJzdbg,
			Integer ljxmCdjx, Integer ljxmXgjzl, Integer ljxmJcjzl,
			Integer ljxmJsjzry, Integer ljxmJcjzry, String superOrg,
			Integer bdqkByjsSjQt, Integer bdqkByjsSwZc,
			Integer bdqkByjsSwFzc, Integer bdqkByjsJzdbg,String status,Date reporttime) {
		this.id = id;
		this.orgid = orgid;
		this.year = year;
		this.month = month;
		this.syzcrs = syzcrs;
		this.bdqkByzj = bdqkByzj;
		this.bdqkByjsQmjc = bdqkByjsQmjc;
		this.bdqkByjsCxhx = bdqkByjsCxhx;
		this.bdqkByjsCxjs = bdqkByjsCxjs;
		this.bdqkByjsSjzx = bdqkByjsSjzx;
		this.bdqkByjsQt = bdqkByjsQt;
		this.xflbGzZs = xflbGzZs;
		this.xflbGzXgjzl = xflbGzXgjzl;
		this.xflbHxZs = xflbHxZs;
		this.xflbHxXgjzl = xflbHxXgjzl;
		this.xflbJs = xflbJs;
		this.xflbZyjwzx = xflbZyjwzx;
		this.xflbBdzzql = xflbBdzzql;
		this.fzlxWhgjaq = fzlxWhgjaq;
		this.fzlxWhggaq = fzlxWhggaq;
		this.fzlxPhjjzx = fzlxPhjjzx;
		this.fzlxQfgm = fzlxQfgm;
		this.fzlxQfcc = fzlxQfcc;
		this.fzlxFhsh = fzlxFhsh;
		this.fzlxWhgfly = fzlxWhgfly;
		this.fzlxTwsh = fzlxTwsh;
		this.fzlxDz = fzlxDz;
		this.fzlxQt = fzlxQt;
		this.hjCz = hjCz;
		this.hjNc = hjNc;
		this.hjGat = hjGat;
		this.hjWg = hjWg;
		this.hjQt = hjQt;
		this.xbN = xbN;
		this.xbV = xbV;
		this.mzH = mzH;
		this.mzSs = mzSs;
		this.nl18 = nl18;
		this.nl1845 = nl1845;
		this.nl4660 = nl4660;
		this.nl61 = nl61;
		this.whcdDzys = whcdDzys;
		this.whcdGz = whcdGz;
		this.whcdCzyx = whcdCzyx;
		this.jyjxJy = jyjxJy;
		this.jyjxJx = jyjxJx;
		this.jyjxWn = jyjxWn;
		this.jyjxWy = jyjxWy;
		this.byJg = byJg;
		this.byZacf = byZacf;
		this.byCdjx = byCdjx;
		this.byJzdbg = byJzdbg;
		this.byJwzx = byJwzx;
		this.ljxmCxhx = ljxmCxhx;
		this.ljxmCxjs = ljxmCxjs;
		this.ljxmCxjw = ljxmCxjw;
		this.ljxmJg = ljxmJg;
		this.ljxmZacf = ljxmZacf;
		this.ljxmJzdbg = ljxmJzdbg;
		this.ljxmCdjx = ljxmCdjx;
		this.ljxmXgjzl = ljxmXgjzl;
		this.ljxmJcjzl = ljxmJcjzl;
		this.ljxmJsjzry = ljxmJsjzry;
		this.ljxmJcjzry = ljxmJcjzry;
		this.superOrg = superOrg;
		this.bdqkByjsSjQt = bdqkByjsSjQt;
		this.bdqkByjsSwZc = bdqkByjsSwZc;
		this.bdqkByjsSwFzc = bdqkByjsSwFzc;
		this.bdqkByjsJzdbg = bdqkByjsJzdbg;
		this.status = status;
		this.reporttime = reporttime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ORGID", length = 32)
	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Column(name = "YEAR", precision = 4, scale = 0)
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "MONTH", precision = 2, scale = 0)
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "SYZCRS", precision = 22, scale = 0)
	public Integer getSyzcrs() {
		return this.syzcrs;
	}

	public void setSyzcrs(Integer syzcrs) {
		this.syzcrs = syzcrs;
	}

	@Column(name = "BDQK_BYZJ", precision = 22, scale = 0)
	public Integer getBdqkByzj() {
		return this.bdqkByzj;
	}

	public void setBdqkByzj(Integer bdqkByzj) {
		this.bdqkByzj = bdqkByzj;
	}

	@Column(name = "BDQK_BYJS_QMJC", precision = 22, scale = 0)
	public Integer getBdqkByjsQmjc() {
		return this.bdqkByjsQmjc;
	}

	public void setBdqkByjsQmjc(Integer bdqkByjsQmjc) {
		this.bdqkByjsQmjc = bdqkByjsQmjc;
	}

	@Column(name = "BDQK_BYJS_CXHX", precision = 22, scale = 0)
	public Integer getBdqkByjsCxhx() {
		return this.bdqkByjsCxhx;
	}

	public void setBdqkByjsCxhx(Integer bdqkByjsCxhx) {
		this.bdqkByjsCxhx = bdqkByjsCxhx;
	}

	@Column(name = "BDQK_BYJS_CXJS", precision = 22, scale = 0)
	public Integer getBdqkByjsCxjs() {
		return this.bdqkByjsCxjs;
	}

	public void setBdqkByjsCxjs(Integer bdqkByjsCxjs) {
		this.bdqkByjsCxjs = bdqkByjsCxjs;
	}

	@Column(name = "BDQK_BYJS_SJZX", precision = 22, scale = 0)
	public Integer getBdqkByjsSjzx() {
		return this.bdqkByjsSjzx;
	}

	public void setBdqkByjsSjzx(Integer bdqkByjsSjzx) {
		this.bdqkByjsSjzx = bdqkByjsSjzx;
	}

	@Column(name = "BDQK_BYJS_QT", precision = 22, scale = 0)
	public Integer getBdqkByjsQt() {
		return this.bdqkByjsQt;
	}

	public void setBdqkByjsQt(Integer bdqkByjsQt) {
		this.bdqkByjsQt = bdqkByjsQt;
	}

	@Column(name = "XFLB_GZ_ZS", precision = 22, scale = 0)
	public Integer getXflbGzZs() {
		return this.xflbGzZs;
	}

	public void setXflbGzZs(Integer xflbGzZs) {
		this.xflbGzZs = xflbGzZs;
	}

	@Column(name = "XFLB_GZ_XGJZL", precision = 22, scale = 0)
	public Integer getXflbGzXgjzl() {
		return this.xflbGzXgjzl;
	}

	public void setXflbGzXgjzl(Integer xflbGzXgjzl) {
		this.xflbGzXgjzl = xflbGzXgjzl;
	}

	@Column(name = "XFLB_HX_ZS", precision = 22, scale = 0)
	public Integer getXflbHxZs() {
		return this.xflbHxZs;
	}

	public void setXflbHxZs(Integer xflbHxZs) {
		this.xflbHxZs = xflbHxZs;
	}

	@Column(name = "XFLB_HX_XGJZL", precision = 22, scale = 0)
	public Integer getXflbHxXgjzl() {
		return this.xflbHxXgjzl;
	}

	public void setXflbHxXgjzl(Integer xflbHxXgjzl) {
		this.xflbHxXgjzl = xflbHxXgjzl;
	}

	@Column(name = "XFLB_JS", precision = 22, scale = 0)
	public Integer getXflbJs() {
		return this.xflbJs;
	}

	public void setXflbJs(Integer xflbJs) {
		this.xflbJs = xflbJs;
	}

	@Column(name = "XFLB_ZYJWZX", precision = 22, scale = 0)
	public Integer getXflbZyjwzx() {
		return this.xflbZyjwzx;
	}

	public void setXflbZyjwzx(Integer xflbZyjwzx) {
		this.xflbZyjwzx = xflbZyjwzx;
	}

	@Column(name = "XFLB_BDZZQL", precision = 22, scale = 0)
	public Integer getXflbBdzzql() {
		return this.xflbBdzzql;
	}

	public void setXflbBdzzql(Integer xflbBdzzql) {
		this.xflbBdzzql = xflbBdzzql;
	}

	@Column(name = "FZLX_WHGJAQ", precision = 22, scale = 0)
	public Integer getFzlxWhgjaq() {
		return this.fzlxWhgjaq;
	}

	public void setFzlxWhgjaq(Integer fzlxWhgjaq) {
		this.fzlxWhgjaq = fzlxWhgjaq;
	}

	@Column(name = "FZLX_WHGGAQ", precision = 22, scale = 0)
	public Integer getFzlxWhggaq() {
		return this.fzlxWhggaq;
	}

	public void setFzlxWhggaq(Integer fzlxWhggaq) {
		this.fzlxWhggaq = fzlxWhggaq;
	}

	@Column(name = "FZLX_PHJJZX", precision = 22, scale = 0)
	public Integer getFzlxPhjjzx() {
		return this.fzlxPhjjzx;
	}

	public void setFzlxPhjjzx(Integer fzlxPhjjzx) {
		this.fzlxPhjjzx = fzlxPhjjzx;
	}

	@Column(name = "FZLX_QFGM", precision = 22, scale = 0)
	public Integer getFzlxQfgm() {
		return this.fzlxQfgm;
	}

	public void setFzlxQfgm(Integer fzlxQfgm) {
		this.fzlxQfgm = fzlxQfgm;
	}

	@Column(name = "FZLX_QFCC", precision = 22, scale = 0)
	public Integer getFzlxQfcc() {
		return this.fzlxQfcc;
	}

	public void setFzlxQfcc(Integer fzlxQfcc) {
		this.fzlxQfcc = fzlxQfcc;
	}

	@Column(name = "FZLX_FHSH", precision = 22, scale = 0)
	public Integer getFzlxFhsh() {
		return this.fzlxFhsh;
	}

	public void setFzlxFhsh(Integer fzlxFhsh) {
		this.fzlxFhsh = fzlxFhsh;
	}

	@Column(name = "FZLX_WHGFLY", precision = 22, scale = 0)
	public Integer getFzlxWhgfly() {
		return this.fzlxWhgfly;
	}

	public void setFzlxWhgfly(Integer fzlxWhgfly) {
		this.fzlxWhgfly = fzlxWhgfly;
	}

	@Column(name = "FZLX_TWSH", precision = 22, scale = 0)
	public Integer getFzlxTwsh() {
		return this.fzlxTwsh;
	}

	public void setFzlxTwsh(Integer fzlxTwsh) {
		this.fzlxTwsh = fzlxTwsh;
	}

	@Column(name = "FZLX_DZ", precision = 22, scale = 0)
	public Integer getFzlxDz() {
		return this.fzlxDz;
	}

	public void setFzlxDz(Integer fzlxDz) {
		this.fzlxDz = fzlxDz;
	}

	@Column(name = "FZLX_QT", precision = 22, scale = 0)
	public Integer getFzlxQt() {
		return this.fzlxQt;
	}

	public void setFzlxQt(Integer fzlxQt) {
		this.fzlxQt = fzlxQt;
	}

	@Column(name = "HJ_CZ", precision = 22, scale = 0)
	public Integer getHjCz() {
		return this.hjCz;
	}

	public void setHjCz(Integer hjCz) {
		this.hjCz = hjCz;
	}

	@Column(name = "HJ_NC", precision = 22, scale = 0)
	public Integer getHjNc() {
		return this.hjNc;
	}

	public void setHjNc(Integer hjNc) {
		this.hjNc = hjNc;
	}

	@Column(name = "HJ_GAT", precision = 22, scale = 0)
	public Integer getHjGat() {
		return this.hjGat;
	}

	public void setHjGat(Integer hjGat) {
		this.hjGat = hjGat;
	}

	@Column(name = "HJ_WG", precision = 22, scale = 0)
	public Integer getHjWg() {
		return this.hjWg;
	}

	public void setHjWg(Integer hjWg) {
		this.hjWg = hjWg;
	}

	@Column(name = "HJ_QT", precision = 22, scale = 0)
	public Integer getHjQt() {
		return this.hjQt;
	}

	public void setHjQt(Integer hjQt) {
		this.hjQt = hjQt;
	}

	@Column(name = "XB_N", precision = 22, scale = 0)
	public Integer getXbN() {
		return this.xbN;
	}

	public void setXbN(Integer xbN) {
		this.xbN = xbN;
	}

	@Column(name = "XB_V", precision = 22, scale = 0)
	public Integer getXbV() {
		return this.xbV;
	}

	public void setXbV(Integer xbV) {
		this.xbV = xbV;
	}

	@Column(name = "MZ_H", precision = 22, scale = 0)
	public Integer getMzH() {
		return this.mzH;
	}

	public void setMzH(Integer mzH) {
		this.mzH = mzH;
	}

	@Column(name = "MZ_SS", precision = 22, scale = 0)
	public Integer getMzSs() {
		return this.mzSs;
	}

	public void setMzSs(Integer mzSs) {
		this.mzSs = mzSs;
	}

	@Column(name = "NL_18", precision = 22, scale = 0)
	public Integer getNl18() {
		return this.nl18;
	}

	public void setNl18(Integer nl18) {
		this.nl18 = nl18;
	}

	@Column(name = "NL_18_45", precision = 22, scale = 0)
	public Integer getNl1845() {
		return this.nl1845;
	}

	public void setNl1845(Integer nl1845) {
		this.nl1845 = nl1845;
	}

	@Column(name = "NL_46_60", precision = 22, scale = 0)
	public Integer getNl4660() {
		return this.nl4660;
	}

	public void setNl4660(Integer nl4660) {
		this.nl4660 = nl4660;
	}

	@Column(name = "NL_61", precision = 22, scale = 0)
	public Integer getNl61() {
		return this.nl61;
	}

	public void setNl61(Integer nl61) {
		this.nl61 = nl61;
	}

	@Column(name = "WHCD_DZYS", precision = 22, scale = 0)
	public Integer getWhcdDzys() {
		return this.whcdDzys;
	}

	public void setWhcdDzys(Integer whcdDzys) {
		this.whcdDzys = whcdDzys;
	}

	@Column(name = "WHCD_GZ", precision = 22, scale = 0)
	public Integer getWhcdGz() {
		return this.whcdGz;
	}

	public void setWhcdGz(Integer whcdGz) {
		this.whcdGz = whcdGz;
	}

	@Column(name = "WHCD_CZYX", precision = 22, scale = 0)
	public Integer getWhcdCzyx() {
		return this.whcdCzyx;
	}

	public void setWhcdCzyx(Integer whcdCzyx) {
		this.whcdCzyx = whcdCzyx;
	}

	@Column(name = "JYJX_JY", precision = 22, scale = 0)
	public Integer getJyjxJy() {
		return this.jyjxJy;
	}

	public void setJyjxJy(Integer jyjxJy) {
		this.jyjxJy = jyjxJy;
	}

	@Column(name = "JYJX_JX", precision = 22, scale = 0)
	public Integer getJyjxJx() {
		return this.jyjxJx;
	}

	public void setJyjxJx(Integer jyjxJx) {
		this.jyjxJx = jyjxJx;
	}

	@Column(name = "JYJX_WN", precision = 22, scale = 0)
	public Integer getJyjxWn() {
		return this.jyjxWn;
	}

	public void setJyjxWn(Integer jyjxWn) {
		this.jyjxWn = jyjxWn;
	}

	@Column(name = "JYJX_WY", precision = 22, scale = 0)
	public Integer getJyjxWy() {
		return this.jyjxWy;
	}

	public void setJyjxWy(Integer jyjxWy) {
		this.jyjxWy = jyjxWy;
	}

	@Column(name = "BY_JG", precision = 22, scale = 0)
	public Integer getByJg() {
		return this.byJg;
	}

	public void setByJg(Integer byJg) {
		this.byJg = byJg;
	}

	@Column(name = "BY_ZACF", precision = 22, scale = 0)
	public Integer getByZacf() {
		return this.byZacf;
	}

	public void setByZacf(Integer byZacf) {
		this.byZacf = byZacf;
	}

	@Column(name = "BY_CDJX", precision = 22, scale = 0)
	public Integer getByCdjx() {
		return this.byCdjx;
	}

	public void setByCdjx(Integer byCdjx) {
		this.byCdjx = byCdjx;
	}

	@Column(name = "BY_JZDBG", precision = 22, scale = 0)
	public Integer getByJzdbg() {
		return this.byJzdbg;
	}

	public void setByJzdbg(Integer byJzdbg) {
		this.byJzdbg = byJzdbg;
	}

	@Column(name = "BY_JWZX", precision = 22, scale = 0)
	public Integer getByJwzx() {
		return this.byJwzx;
	}

	public void setByJwzx(Integer byJwzx) {
		this.byJwzx = byJwzx;
	}

	@Column(name = "LJXM_CXHX", precision = 22, scale = 0)
	public Integer getLjxmCxhx() {
		return this.ljxmCxhx;
	}

	public void setLjxmCxhx(Integer ljxmCxhx) {
		this.ljxmCxhx = ljxmCxhx;
	}

	@Column(name = "LJXM_CXJS", precision = 22, scale = 0)
	public Integer getLjxmCxjs() {
		return this.ljxmCxjs;
	}

	public void setLjxmCxjs(Integer ljxmCxjs) {
		this.ljxmCxjs = ljxmCxjs;
	}

	@Column(name = "LJXM_CXJW", precision = 22, scale = 0)
	public Integer getLjxmCxjw() {
		return this.ljxmCxjw;
	}

	public void setLjxmCxjw(Integer ljxmCxjw) {
		this.ljxmCxjw = ljxmCxjw;
	}

	@Column(name = "LJXM_JG", precision = 22, scale = 0)
	public Integer getLjxmJg() {
		return this.ljxmJg;
	}

	public void setLjxmJg(Integer ljxmJg) {
		this.ljxmJg = ljxmJg;
	}

	@Column(name = "LJXM_ZACF", precision = 22, scale = 0)
	public Integer getLjxmZacf() {
		return this.ljxmZacf;
	}

	public void setLjxmZacf(Integer ljxmZacf) {
		this.ljxmZacf = ljxmZacf;
	}

	@Column(name = "LJXM_JZDBG", precision = 22, scale = 0)
	public Integer getLjxmJzdbg() {
		return this.ljxmJzdbg;
	}

	public void setLjxmJzdbg(Integer ljxmJzdbg) {
		this.ljxmJzdbg = ljxmJzdbg;
	}

	@Column(name = "LJXM_CDJX", precision = 22, scale = 0)
	public Integer getLjxmCdjx() {
		return this.ljxmCdjx;
	}

	public void setLjxmCdjx(Integer ljxmCdjx) {
		this.ljxmCdjx = ljxmCdjx;
	}

	@Column(name = "LJXM_XGJZL", precision = 22, scale = 0)
	public Integer getLjxmXgjzl() {
		return this.ljxmXgjzl;
	}

	public void setLjxmXgjzl(Integer ljxmXgjzl) {
		this.ljxmXgjzl = ljxmXgjzl;
	}

	@Column(name = "LJXM_JCJZL", precision = 22, scale = 0)
	public Integer getLjxmJcjzl() {
		return this.ljxmJcjzl;
	}

	public void setLjxmJcjzl(Integer ljxmJcjzl) {
		this.ljxmJcjzl = ljxmJcjzl;
	}

	@Column(name = "LJXM_JSJZRY", precision = 22, scale = 0)
	public Integer getLjxmJsjzry() {
		return this.ljxmJsjzry;
	}

	public void setLjxmJsjzry(Integer ljxmJsjzry) {
		this.ljxmJsjzry = ljxmJsjzry;
	}

	@Column(name = "LJXM_JCJZRY", precision = 22, scale = 0)
	public Integer getLjxmJcjzry() {
		return this.ljxmJcjzry;
	}

	public void setLjxmJcjzry(Integer ljxmJcjzry) {
		this.ljxmJcjzry = ljxmJcjzry;
	}

	@Column(name = "SUPER_ORG", length = 32)
	public String getSuperOrg() {
		return this.superOrg;
	}

	public void setSuperOrg(String superOrg) {
		this.superOrg = superOrg;
	}

	@Column(name = "BDQK_BYJS_SJ_QT", precision = 22, scale = 0)
	public Integer getBdqkByjsSjQt() {
		return this.bdqkByjsSjQt;
	}

	public void setBdqkByjsSjQt(Integer bdqkByjsSjQt) {
		this.bdqkByjsSjQt = bdqkByjsSjQt;
	}

	@Column(name = "BDQK_BYJS_SW_ZC", precision = 22, scale = 0)
	public Integer getBdqkByjsSwZc() {
		return this.bdqkByjsSwZc;
	}

	public void setBdqkByjsSwZc(Integer bdqkByjsSwZc) {
		this.bdqkByjsSwZc = bdqkByjsSwZc;
	}

	@Column(name = "BDQK_BYJS_SW_FZC", precision = 22, scale = 0)
	public Integer getBdqkByjsSwFzc() {
		return this.bdqkByjsSwFzc;
	}

	public void setBdqkByjsSwFzc(Integer bdqkByjsSwFzc) {
		this.bdqkByjsSwFzc = bdqkByjsSwFzc;
	}

	@Column(name = "BDQK_BYJS_JZDBG", precision = 22, scale = 0)
	public Integer getBdqkByjsJzdbg() {
		return this.bdqkByjsJzdbg;
	}

	public void setBdqkByjsJzdbg(Integer bdqkByjsJzdbg) {
		this.bdqkByjsJzdbg = bdqkByjsJzdbg;
	}
		
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "LJXM_ZFZ", length = 1)
	public Integer getLjxmZfz() {
		return ljxmZfz;
	}

	public void setLjxmZfz(Integer ljxmZfz) {
		this.ljxmZfz = ljxmZfz;
	}
	@Column(name = "BY_ZFZ", length = 1)
	public Integer getByZfz() {
		return byZfz;
	}

	public void setByZfz(Integer byZfz) {
		this.byZfz = byZfz;
	}
	@Column(name = "BY_LTG", length = 1)
	public Integer getByLtg() {
		return byLtg;
	}

	public void setByLtg(Integer byLtg) {
		this.byLtg = byLtg;
	}
	
}