package com.nci.dcs.jzgl.bbgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcZtzjStatisticMonth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ZTZJ_STATISTIC_MONTH", schema = "SQJZ")
public class CcZtzjStatisticMonth implements java.io.Serializable {

	// Fields

	private String id;
	private String orgId;
	private Integer month;
	private Integer year;
	private Integer yjjsJzmj;
	private Integer yjjsSyb;
	private Integer yjjsJftr;
	private Integer yjjsClqk;
	private Integer yjjsGljd;
	private Integer gzdwGzry;
	private Integer gzdwZzZs;
	private Integer gzdwZzSyb;
	private Integer gzdwZzHtz;
	private Integer gzdwZzDz;
	private Integer gzdwZzPx;
	private Integer gzdwCdgjZs;
	private Integer gzdwCdgjPx;
	private Integer gzdwZyzZs;
	private Integer gzdwZyzPx;
	private Integer gnkzJzRs;
	private Integer gnkzJzCs;
	private Integer gnkzShzdRs;
	private Integer gnkzShzdTtcs;
	private Integer gnkzShzdGrcs;
	private Integer gnkzXlzxRs;
	private Integer gnkzXlzxTtcs;
	private Integer gnkzXlzxGrcs;
	private Integer gnkzJybzJnpx;
	private Integer gnkzJybzJyzd;
	private Integer gnkzJybzJyxx;
	private Integer gnkzGyldCs;
	private Integer gnkzGyldRs;
	private Integer gnkzSwry;
	private Integer gnkzQtkz;
	private Integer rcglZklh;
	private Integer rcglMtbd;
	private Integer rcglRyjl;
	private Integer rcglRycl;
	private Integer rcglDwjl;
	private Integer gzdwFzgzry;
	private String supOrgId;
	private String reason;
	private String status;
	private Date reporttime;
	private String reportperson;

	// Constructors

	/** default constructor */
	public CcZtzjStatisticMonth() {
	}

	/** minimal constructor */
	public CcZtzjStatisticMonth(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcZtzjStatisticMonth(String id,
			Integer yjjsJzmj, Integer yjjsSyb, Integer yjjsJftr,
			Integer yjjsClqk, Integer yjjsGljd, Integer gzdwGzry,
			Integer gzdwZzZs, Integer gzdwZzSyb, Integer gzdwZzHtz,
			Integer gzdwZzDz, Integer gzdwZzPx, Integer gzdwCdgjZs,
			Integer gzdwCdgjPx, Integer gzdwZyzZs, Integer gzdwZyzPx,
			Integer gnkzJzRs, Integer gnkzJzCs, Integer gnkzShzdRs,
			Integer gnkzShzdTtcs, Integer gnkzShzdGrcs,
			Integer gnkzXlzxRs, Integer gnkzXlzxTtcs,
			Integer gnkzXlzxGrcs, Integer gnkzJybzJnpx,
			Integer gnkzJybzJyzd, Integer gnkzJybzJyxx,
			Integer gnkzGyldCs, Integer gnkzGyldRs, Integer gnkzSwry,
			Integer gnkzQtkz, Integer rcglZklh, Integer rcglMtbd,
			Integer rcglRyjl, Integer rcglRycl, Integer rcglDwjl,
			Integer gzdwFzgzry, String supOrgId,String status,String reason) {
		this.id = id;
		this.yjjsJzmj = yjjsJzmj;
		this.yjjsSyb = yjjsSyb;
		this.yjjsJftr = yjjsJftr;
		this.yjjsClqk = yjjsClqk;
		this.yjjsGljd = yjjsGljd;
		this.gzdwGzry = gzdwGzry;
		this.gzdwZzZs = gzdwZzZs;
		this.gzdwZzSyb = gzdwZzSyb;
		this.gzdwZzHtz = gzdwZzHtz;
		this.gzdwZzDz = gzdwZzDz;
		this.gzdwZzPx = gzdwZzPx;
		this.gzdwCdgjZs = gzdwCdgjZs;
		this.gzdwCdgjPx = gzdwCdgjPx;
		this.gzdwZyzZs = gzdwZyzZs;
		this.gzdwZyzPx = gzdwZyzPx;
		this.gnkzJzRs = gnkzJzRs;
		this.gnkzJzCs = gnkzJzCs;
		this.gnkzShzdRs = gnkzShzdRs;
		this.gnkzShzdTtcs = gnkzShzdTtcs;
		this.gnkzShzdGrcs = gnkzShzdGrcs;
		this.gnkzXlzxRs = gnkzXlzxRs;
		this.gnkzXlzxTtcs = gnkzXlzxTtcs;
		this.gnkzXlzxGrcs = gnkzXlzxGrcs;
		this.gnkzJybzJnpx = gnkzJybzJnpx;
		this.gnkzJybzJyzd = gnkzJybzJyzd;
		this.gnkzJybzJyxx = gnkzJybzJyxx;
		this.gnkzGyldCs = gnkzGyldCs;
		this.gnkzGyldRs = gnkzGyldRs;
		this.gnkzSwry = gnkzSwry;
		this.gnkzQtkz = gnkzQtkz;
		this.rcglZklh = rcglZklh;
		this.rcglMtbd = rcglMtbd;
		this.rcglRyjl = rcglRyjl;
		this.rcglRycl = rcglRycl;
		this.rcglDwjl = rcglDwjl;
		this.gzdwFzgzry = gzdwFzgzry;
		this.supOrgId = supOrgId;
		this.status = status;
		this.reason = reason;
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

	@Column(name = "ORG_ID", nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "MONTH", nullable = false, precision = 22, scale = 0)
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "YEAR", nullable = false, precision = 22, scale = 0)
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "YJJS_JZMJ", precision = 22, scale = 0)
	public Integer getYjjsJzmj() {
		return this.yjjsJzmj;
	}

	public void setYjjsJzmj(Integer yjjsJzmj) {
		this.yjjsJzmj = yjjsJzmj;
	}

	@Column(name = "YJJS_SYB", precision = 22, scale = 0)
	public Integer getYjjsSyb() {
		return this.yjjsSyb;
	}

	public void setYjjsSyb(Integer yjjsSyb) {
		this.yjjsSyb = yjjsSyb;
	}

	@Column(name = "YJJS_JFTR", precision = 22, scale = 0)
	public Integer getYjjsJftr() {
		return this.yjjsJftr;
	}

	public void setYjjsJftr(Integer yjjsJftr) {
		this.yjjsJftr = yjjsJftr;
	}

	@Column(name = "YJJS_CLQK", precision = 22, scale = 0)
	public Integer getYjjsClqk() {
		return this.yjjsClqk;
	}

	public void setYjjsClqk(Integer yjjsClqk) {
		this.yjjsClqk = yjjsClqk;
	}

	@Column(name = "YJJS_GLJD", precision = 22, scale = 0)
	public Integer getYjjsGljd() {
		return this.yjjsGljd;
	}

	public void setYjjsGljd(Integer yjjsGljd) {
		this.yjjsGljd = yjjsGljd;
	}

	@Column(name = "GZDW_GZRY", precision = 22, scale = 0)
	public Integer getGzdwGzry() {
		return this.gzdwGzry;
	}

	public void setGzdwGzry(Integer gzdwGzry) {
		this.gzdwGzry = gzdwGzry;
	}

	@Column(name = "GZDW_ZZ_ZS", precision = 22, scale = 0)
	public Integer getGzdwZzZs() {
		return this.gzdwZzZs;
	}

	public void setGzdwZzZs(Integer gzdwZzZs) {
		this.gzdwZzZs = gzdwZzZs;
	}

	@Column(name = "GZDW_ZZ_SYB", precision = 22, scale = 0)
	public Integer getGzdwZzSyb() {
		return this.gzdwZzSyb;
	}

	public void setGzdwZzSyb(Integer gzdwZzSyb) {
		this.gzdwZzSyb = gzdwZzSyb;
	}

	@Column(name = "GZDW_ZZ_HTZ", precision = 22, scale = 0)
	public Integer getGzdwZzHtz() {
		return this.gzdwZzHtz;
	}

	public void setGzdwZzHtz(Integer gzdwZzHtz) {
		this.gzdwZzHtz = gzdwZzHtz;
	}

	@Column(name = "GZDW_ZZ_DZ", precision = 22, scale = 0)
	public Integer getGzdwZzDz() {
		return this.gzdwZzDz;
	}

	public void setGzdwZzDz(Integer gzdwZzDz) {
		this.gzdwZzDz = gzdwZzDz;
	}

	@Column(name = "GZDW_ZZ_PX", precision = 22, scale = 0)
	public Integer getGzdwZzPx() {
		return this.gzdwZzPx;
	}

	public void setGzdwZzPx(Integer gzdwZzPx) {
		this.gzdwZzPx = gzdwZzPx;
	}

	@Column(name = "GZDW_CDGJ_ZS", precision = 22, scale = 0)
	public Integer getGzdwCdgjZs() {
		return this.gzdwCdgjZs;
	}

	public void setGzdwCdgjZs(Integer gzdwCdgjZs) {
		this.gzdwCdgjZs = gzdwCdgjZs;
	}

	@Column(name = "GZDW_CDGJ_PX", precision = 22, scale = 0)
	public Integer getGzdwCdgjPx() {
		return this.gzdwCdgjPx;
	}

	public void setGzdwCdgjPx(Integer gzdwCdgjPx) {
		this.gzdwCdgjPx = gzdwCdgjPx;
	}

	@Column(name = "GZDW_ZYZ_ZS", precision = 22, scale = 0)
	public Integer getGzdwZyzZs() {
		return this.gzdwZyzZs;
	}

	public void setGzdwZyzZs(Integer gzdwZyzZs) {
		this.gzdwZyzZs = gzdwZyzZs;
	}

	@Column(name = "GZDW_ZYZ_PX", precision = 22, scale = 0)
	public Integer getGzdwZyzPx() {
		return this.gzdwZyzPx;
	}

	public void setGzdwZyzPx(Integer gzdwZyzPx) {
		this.gzdwZyzPx = gzdwZyzPx;
	}

	@Column(name = "GNKZ_JZJY_JJ_RS", precision = 22, scale = 0)
	public Integer getGnkzJzRs() {
		return this.gnkzJzRs;
	}

	public void setGnkzJzRs(Integer gnkzJzRs) {
		this.gnkzJzRs = gnkzJzRs;
	}

	@Column(name = "GNKZ_JZJY_JJ_CS", precision = 22, scale = 0)
	public Integer getGnkzJzCs() {
		return this.gnkzJzCs;
	}

	public void setGnkzJzCs(Integer gnkzJzCs) {
		this.gnkzJzCs = gnkzJzCs;
	}

	@Column(name = "GNKZ_SHZD_RS", precision = 22, scale = 0)
	public Integer getGnkzShzdRs() {
		return this.gnkzShzdRs;
	}

	public void setGnkzShzdRs(Integer gnkzShzdRs) {
		this.gnkzShzdRs = gnkzShzdRs;
	}

	@Column(name = "GNKZ_SHZD_TTCS", precision = 22, scale = 0)
	public Integer getGnkzShzdTtcs() {
		return this.gnkzShzdTtcs;
	}

	public void setGnkzShzdTtcs(Integer gnkzShzdTtcs) {
		this.gnkzShzdTtcs = gnkzShzdTtcs;
	}

	@Column(name = "GNKZ_SHZD_GRCS", precision = 22, scale = 0)
	public Integer getGnkzShzdGrcs() {
		return this.gnkzShzdGrcs;
	}

	public void setGnkzShzdGrcs(Integer gnkzShzdGrcs) {
		this.gnkzShzdGrcs = gnkzShzdGrcs;
	}

	@Column(name = "GNKZ_XLZX_RS", precision = 22, scale = 0)
	public Integer getGnkzXlzxRs() {
		return this.gnkzXlzxRs;
	}

	public void setGnkzXlzxRs(Integer gnkzXlzxRs) {
		this.gnkzXlzxRs = gnkzXlzxRs;
	}

	@Column(name = "GNKZ_XLZX_TTCS", precision = 22, scale = 0)
	public Integer getGnkzXlzxTtcs() {
		return this.gnkzXlzxTtcs;
	}

	public void setGnkzXlzxTtcs(Integer gnkzXlzxTtcs) {
		this.gnkzXlzxTtcs = gnkzXlzxTtcs;
	}

	@Column(name = "GNKZ_XLZX_GRCS", precision = 22, scale = 0)
	public Integer getGnkzXlzxGrcs() {
		return this.gnkzXlzxGrcs;
	}

	public void setGnkzXlzxGrcs(Integer gnkzXlzxGrcs) {
		this.gnkzXlzxGrcs = gnkzXlzxGrcs;
	}

	@Column(name = "GNKZ_JYBZ_JNPX", precision = 22, scale = 0)
	public Integer getGnkzJybzJnpx() {
		return this.gnkzJybzJnpx;
	}

	public void setGnkzJybzJnpx(Integer gnkzJybzJnpx) {
		this.gnkzJybzJnpx = gnkzJybzJnpx;
	}

	@Column(name = "GNKZ_JYBZ_JYZD", precision = 22, scale = 0)
	public Integer getGnkzJybzJyzd() {
		return this.gnkzJybzJyzd;
	}

	public void setGnkzJybzJyzd(Integer gnkzJybzJyzd) {
		this.gnkzJybzJyzd = gnkzJybzJyzd;
	}

	@Column(name = "GNKZ_JYBZ_JYXX", precision = 22, scale = 0)
	public Integer getGnkzJybzJyxx() {
		return this.gnkzJybzJyxx;
	}

	public void setGnkzJybzJyxx(Integer gnkzJybzJyxx) {
		this.gnkzJybzJyxx = gnkzJybzJyxx;
	}

	@Column(name = "GNKZ_GYLD_CS", precision = 22, scale = 0)
	public Integer getGnkzGyldCs() {
		return this.gnkzGyldCs;
	}

	public void setGnkzGyldCs(Integer gnkzGyldCs) {
		this.gnkzGyldCs = gnkzGyldCs;
	}

	@Column(name = "GNKZ_GYLD_RS", precision = 22, scale = 0)
	public Integer getGnkzGyldRs() {
		return this.gnkzGyldRs;
	}

	public void setGnkzGyldRs(Integer gnkzGyldRs) {
		this.gnkzGyldRs = gnkzGyldRs;
	}

	@Column(name = "GNKZ_SWRY", precision = 22, scale = 0)
	public Integer getGnkzSwry() {
		return this.gnkzSwry;
	}

	public void setGnkzSwry(Integer gnkzSwry) {
		this.gnkzSwry = gnkzSwry;
	}

	@Column(name = "GNKZ_QTKZ", precision = 22, scale = 0)
	public Integer getGnkzQtkz() {
		return this.gnkzQtkz;
	}

	public void setGnkzQtkz(Integer gnkzQtkz) {
		this.gnkzQtkz = gnkzQtkz;
	}

	@Column(name = "RCGL_ZKLH", precision = 22, scale = 0)
	public Integer getRcglZklh() {
		return this.rcglZklh;
	}

	public void setRcglZklh(Integer rcglZklh) {
		this.rcglZklh = rcglZklh;
	}

	@Column(name = "RCGL_MTBD", precision = 22, scale = 0)
	public Integer getRcglMtbd() {
		return this.rcglMtbd;
	}

	public void setRcglMtbd(Integer rcglMtbd) {
		this.rcglMtbd = rcglMtbd;
	}

	@Column(name = "RCGL_RYJL", precision = 22, scale = 0)
	public Integer getRcglRyjl() {
		return this.rcglRyjl;
	}

	public void setRcglRyjl(Integer rcglRyjl) {
		this.rcglRyjl = rcglRyjl;
	}

	@Column(name = "RCGL_RYCL", precision = 22, scale = 0)
	public Integer getRcglRycl() {
		return this.rcglRycl;
	}

	public void setRcglRycl(Integer rcglRycl) {
		this.rcglRycl = rcglRycl;
	}

	@Column(name = "RCGL_DWJL", precision = 22, scale = 0)
	public Integer getRcglDwjl() {
		return this.rcglDwjl;
	}

	public void setRcglDwjl(Integer rcglDwjl) {
		this.rcglDwjl = rcglDwjl;
	}

	@Column(name = "GZDW_FZGZRY", precision = 22, scale = 0)
	public Integer getGzdwFzgzry() {
		return this.gzdwFzgzry;
	}

	public void setGzdwFzgzry(Integer gzdwFzgzry) {
		this.gzdwFzgzry = gzdwFzgzry;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return this.supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}
	@Column(name = "REASON", length = 1000)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "STATUS", length = 32)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "REPORTTIME")
	public Date getReporttime() {
		return reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}
	@Column(name = "REPORTPERSON", length = 32)
	public String getReportperson() {
		return reportperson;
	}

	public void setReportperson(String reportperson) {
		this.reportperson = reportperson;
	}
}