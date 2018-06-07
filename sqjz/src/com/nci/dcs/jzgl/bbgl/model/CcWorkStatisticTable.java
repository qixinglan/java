package com.nci.dcs.jzgl.bbgl.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcWorkStatisticTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_WORK_STATISTIC_TABLE", schema = "SQJZ")
public class CcWorkStatisticTable implements java.io.Serializable {

	// Fields

	private String id;
	private String orgid;
	private Integer month;
	private Integer year;
	private BigDecimal kzdqDsj;
	private BigDecimal kzdqQxj;
	private BigDecimal kzdqXz;
	private BigDecimal jgqkDs;
	private BigDecimal jgqkQx;
	private BigDecimal jgqkSfs;
	private BigDecimal jgqkJzxz;
	private BigDecimal dwqkSs;
	private BigDecimal dwqkDs;
	private BigDecimal dwqkZyzf;
	private BigDecimal dwqkDfxz;
	private BigDecimal dwqkSyb;
	private BigDecimal dwqkQt;
	private BigDecimal dwqkShgz;
	private BigDecimal dwqkZyz;
	private BigDecimal jfbzSj;
	private BigDecimal jfbzDs;
	private BigDecimal jfbzQx;
	private BigDecimal lsqkZjjz;
	private BigDecimal lsqkGbth;
	private BigDecimal lsqkXlfd;
	private BigDecimal lsqkSqfw;
	private BigDecimal lsqkLsdb;
	private BigDecimal lsqkLscbt;
	private BigDecimal lsqkJnbx;
	private BigDecimal lsqkJyjx;
	private BigDecimal lsqkQt;
	private BigDecimal jdjsJy;
	private BigDecimal jdjsJx;
	private BigDecimal jdjsSqfw;
	private BigDecimal jdjsGljy;
	private BigDecimal xxhDs;
	private BigDecimal xxhQx;
	private BigDecimal xxhSj;
	private BigDecimal dcpg;
	private String status;
	private String superOrg;
	private String reason;

	// Constructors
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "SUPER_ORG", length = 32)
	public String getSuperOrg() {
		return superOrg;
	}

	public void setSuperOrg(String superOrg) {
		this.superOrg = superOrg;
	}

	/** default constructor */
	public CcWorkStatisticTable() {
	}

	/** minimal constructor */
	public CcWorkStatisticTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcWorkStatisticTable(String id, String orgid, Integer month,
			Integer year, BigDecimal kzdqDsj, BigDecimal kzdqQxj,
			BigDecimal kzdqXz, BigDecimal jgqkDs, BigDecimal jgqkQx,
			BigDecimal jgqkSfs, BigDecimal jgqkJzxz, BigDecimal dwqkSs,
			BigDecimal dwqkDs, BigDecimal dwqkZyzf, BigDecimal dwqkDfxz,
			BigDecimal dwqkSyb, BigDecimal dwqkQt, BigDecimal dwqkShgz,
			BigDecimal dwqkZyz, BigDecimal jfbzSj, BigDecimal jfbzDs,
			BigDecimal jfbzQx, BigDecimal lsqkZjjz, BigDecimal lsqkGbth,
			BigDecimal lsqkXlfd, BigDecimal lsqkSqfw, BigDecimal lsqkLsdb,
			BigDecimal lsqkLscbt, BigDecimal lsqkJnbx, BigDecimal lsqkJyjx,
			BigDecimal lsqkQt, BigDecimal jdjsJy, BigDecimal jdjsJx,
			BigDecimal jdjsSqfw, BigDecimal jdjsGljy, BigDecimal xxhDs,
			BigDecimal xxhQx, BigDecimal xxhSj, BigDecimal dcpg, String status) {
		this.id = id;
		this.orgid = orgid;
		this.month = month;
		this.year = year;
		this.kzdqDsj = kzdqDsj;
		this.kzdqQxj = kzdqQxj;
		this.kzdqXz = kzdqXz;
		this.jgqkDs = jgqkDs;
		this.jgqkQx = jgqkQx;
		this.jgqkSfs = jgqkSfs;
		this.jgqkJzxz = jgqkJzxz;
		this.dwqkSs = dwqkSs;
		this.dwqkDs = dwqkDs;
		this.dwqkZyzf = dwqkZyzf;
		this.dwqkDfxz = dwqkDfxz;
		this.dwqkSyb = dwqkSyb;
		this.dwqkQt = dwqkQt;
		this.dwqkShgz = dwqkShgz;
		this.dwqkZyz = dwqkZyz;
		this.jfbzSj = jfbzSj;
		this.jfbzDs = jfbzDs;
		this.jfbzQx = jfbzQx;
		this.lsqkZjjz = lsqkZjjz;
		this.lsqkGbth = lsqkGbth;
		this.lsqkXlfd = lsqkXlfd;
		this.lsqkSqfw = lsqkSqfw;
		this.lsqkLsdb = lsqkLsdb;
		this.lsqkLscbt = lsqkLscbt;
		this.lsqkJnbx = lsqkJnbx;
		this.lsqkJyjx = lsqkJyjx;
		this.lsqkQt = lsqkQt;
		this.jdjsJy = jdjsJy;
		this.jdjsJx = jdjsJx;
		this.jdjsSqfw = jdjsSqfw;
		this.jdjsGljy = jdjsGljy;
		this.xxhDs = xxhDs;
		this.xxhQx = xxhQx;
		this.xxhSj = xxhSj;
		this.dcpg = dcpg;
		this.status = status;
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

	@Column(name = "MONTH", length = 2)
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "YEAR", length = 4)
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "KZDQ_DSJ", precision = 22, scale = 0)
	public BigDecimal getKzdqDsj() {
		return this.kzdqDsj;
	}

	public void setKzdqDsj(BigDecimal kzdqDsj) {
		this.kzdqDsj = kzdqDsj;
	}

	@Column(name = "KZDQ_QXJ", precision = 22, scale = 0)
	public BigDecimal getKzdqQxj() {
		return this.kzdqQxj;
	}

	public void setKzdqQxj(BigDecimal kzdqQxj) {
		this.kzdqQxj = kzdqQxj;
	}

	@Column(name = "KZDQ_XZ", precision = 22, scale = 0)
	public BigDecimal getKzdqXz() {
		return this.kzdqXz;
	}

	public void setKzdqXz(BigDecimal kzdqXz) {
		this.kzdqXz = kzdqXz;
	}

	@Column(name = "JGQK_DS", precision = 22, scale = 0)
	public BigDecimal getJgqkDs() {
		return this.jgqkDs;
	}

	public void setJgqkDs(BigDecimal jgqkDs) {
		this.jgqkDs = jgqkDs;
	}

	@Column(name = "JGQK_QX", precision = 22, scale = 0)
	public BigDecimal getJgqkQx() {
		return this.jgqkQx;
	}

	public void setJgqkQx(BigDecimal jgqkQx) {
		this.jgqkQx = jgqkQx;
	}

	@Column(name = "JGQK_SFS", precision = 22, scale = 0)
	public BigDecimal getJgqkSfs() {
		return this.jgqkSfs;
	}

	public void setJgqkSfs(BigDecimal jgqkSfs) {
		this.jgqkSfs = jgqkSfs;
	}

	@Column(name = "JGQK_JZXZ", precision = 22, scale = 0)
	public BigDecimal getJgqkJzxz() {
		return this.jgqkJzxz;
	}

	public void setJgqkJzxz(BigDecimal jgqkJzxz) {
		this.jgqkJzxz = jgqkJzxz;
	}

	@Column(name = "DWQK_SS", precision = 22, scale = 0)
	public BigDecimal getDwqkSs() {
		return this.dwqkSs;
	}

	public void setDwqkSs(BigDecimal dwqkSs) {
		this.dwqkSs = dwqkSs;
	}

	@Column(name = "DWQK_DS", precision = 22, scale = 0)
	public BigDecimal getDwqkDs() {
		return this.dwqkDs;
	}

	public void setDwqkDs(BigDecimal dwqkDs) {
		this.dwqkDs = dwqkDs;
	}

	@Column(name = "DWQK_ZYZF", precision = 22, scale = 0)
	public BigDecimal getDwqkZyzf() {
		return this.dwqkZyzf;
	}

	public void setDwqkZyzf(BigDecimal dwqkZyzf) {
		this.dwqkZyzf = dwqkZyzf;
	}

	@Column(name = "DWQK_DFXZ", precision = 22, scale = 0)
	public BigDecimal getDwqkDfxz() {
		return this.dwqkDfxz;
	}

	public void setDwqkDfxz(BigDecimal dwqkDfxz) {
		this.dwqkDfxz = dwqkDfxz;
	}

	@Column(name = "DWQK_SYB", precision = 22, scale = 0)
	public BigDecimal getDwqkSyb() {
		return this.dwqkSyb;
	}

	public void setDwqkSyb(BigDecimal dwqkSyb) {
		this.dwqkSyb = dwqkSyb;
	}

	@Column(name = "DWQK_QT", precision = 22, scale = 0)
	public BigDecimal getDwqkQt() {
		return this.dwqkQt;
	}

	public void setDwqkQt(BigDecimal dwqkQt) {
		this.dwqkQt = dwqkQt;
	}

	@Column(name = "DWQK_SHGZ", precision = 22, scale = 0)
	public BigDecimal getDwqkShgz() {
		return this.dwqkShgz;
	}

	public void setDwqkShgz(BigDecimal dwqkShgz) {
		this.dwqkShgz = dwqkShgz;
	}

	@Column(name = "DWQK_ZYZ", precision = 22, scale = 0)
	public BigDecimal getDwqkZyz() {
		return this.dwqkZyz;
	}

	public void setDwqkZyz(BigDecimal dwqkZyz) {
		this.dwqkZyz = dwqkZyz;
	}

	@Column(name = "JFBZ_SJ", precision = 22, scale = 0)
	public BigDecimal getJfbzSj() {
		return this.jfbzSj;
	}

	public void setJfbzSj(BigDecimal jfbzSj) {
		this.jfbzSj = jfbzSj;
	}

	@Column(name = "JFBZ_DS", precision = 22, scale = 0)
	public BigDecimal getJfbzDs() {
		return this.jfbzDs;
	}

	public void setJfbzDs(BigDecimal jfbzDs) {
		this.jfbzDs = jfbzDs;
	}

	@Column(name = "JFBZ_QX", precision = 22, scale = 0)
	public BigDecimal getJfbzQx() {
		return this.jfbzQx;
	}

	public void setJfbzQx(BigDecimal jfbzQx) {
		this.jfbzQx = jfbzQx;
	}

	@Column(name = "LSQK_ZJJZ", precision = 22, scale = 0)
	public BigDecimal getLsqkZjjz() {
		return this.lsqkZjjz;
	}

	public void setLsqkZjjz(BigDecimal lsqkZjjz) {
		this.lsqkZjjz = lsqkZjjz;
	}

	@Column(name = "LSQK_GBTH", precision = 22, scale = 0)
	public BigDecimal getLsqkGbth() {
		return this.lsqkGbth;
	}

	public void setLsqkGbth(BigDecimal lsqkGbth) {
		this.lsqkGbth = lsqkGbth;
	}

	@Column(name = "LSQK_XLFD", precision = 22, scale = 0)
	public BigDecimal getLsqkXlfd() {
		return this.lsqkXlfd;
	}

	public void setLsqkXlfd(BigDecimal lsqkXlfd) {
		this.lsqkXlfd = lsqkXlfd;
	}

	@Column(name = "LSQK_SQFW", precision = 22, scale = 0)
	public BigDecimal getLsqkSqfw() {
		return this.lsqkSqfw;
	}

	public void setLsqkSqfw(BigDecimal lsqkSqfw) {
		this.lsqkSqfw = lsqkSqfw;
	}

	@Column(name = "LSQK_LSDB", precision = 22, scale = 0)
	public BigDecimal getLsqkLsdb() {
		return this.lsqkLsdb;
	}

	public void setLsqkLsdb(BigDecimal lsqkLsdb) {
		this.lsqkLsdb = lsqkLsdb;
	}

	@Column(name = "LSQK_LSCBT", precision = 22, scale = 0)
	public BigDecimal getLsqkLscbt() {
		return this.lsqkLscbt;
	}

	public void setLsqkLscbt(BigDecimal lsqkLscbt) {
		this.lsqkLscbt = lsqkLscbt;
	}

	@Column(name = "LSQK_JNBX", precision = 22, scale = 0)
	public BigDecimal getLsqkJnbx() {
		return this.lsqkJnbx;
	}

	public void setLsqkJnbx(BigDecimal lsqkJnbx) {
		this.lsqkJnbx = lsqkJnbx;
	}

	@Column(name = "LSQK_JYJX", precision = 22, scale = 0)
	public BigDecimal getLsqkJyjx() {
		return this.lsqkJyjx;
	}

	public void setLsqkJyjx(BigDecimal lsqkJyjx) {
		this.lsqkJyjx = lsqkJyjx;
	}

	@Column(name = "LSQK_QT", precision = 22, scale = 0)
	public BigDecimal getLsqkQt() {
		return this.lsqkQt;
	}

	public void setLsqkQt(BigDecimal lsqkQt) {
		this.lsqkQt = lsqkQt;
	}

	@Column(name = "JDJS_JY", precision = 22, scale = 0)
	public BigDecimal getJdjsJy() {
		return this.jdjsJy;
	}

	public void setJdjsJy(BigDecimal jdjsJy) {
		this.jdjsJy = jdjsJy;
	}

	@Column(name = "JDJS_JX", precision = 22, scale = 0)
	public BigDecimal getJdjsJx() {
		return this.jdjsJx;
	}

	public void setJdjsJx(BigDecimal jdjsJx) {
		this.jdjsJx = jdjsJx;
	}

	@Column(name = "JDJS_SQFW", precision = 22, scale = 0)
	public BigDecimal getJdjsSqfw() {
		return this.jdjsSqfw;
	}

	public void setJdjsSqfw(BigDecimal jdjsSqfw) {
		this.jdjsSqfw = jdjsSqfw;
	}

	@Column(name = "JDJS_GLJY", precision = 22, scale = 0)
	public BigDecimal getJdjsGljy() {
		return this.jdjsGljy;
	}

	public void setJdjsGljy(BigDecimal jdjsGljy) {
		this.jdjsGljy = jdjsGljy;
	}

	@Column(name = "XXH_DS", precision = 22, scale = 0)
	public BigDecimal getXxhDs() {
		return this.xxhDs;
	}

	public void setXxhDs(BigDecimal xxhDs) {
		this.xxhDs = xxhDs;
	}

	@Column(name = "XXH_QX", precision = 22, scale = 0)
	public BigDecimal getXxhQx() {
		return this.xxhQx;
	}

	public void setXxhQx(BigDecimal xxhQx) {
		this.xxhQx = xxhQx;
	}

	@Column(name = "XXH_SJ", precision = 22, scale = 0)
	public BigDecimal getXxhSj() {
		return this.xxhSj;
	}

	public void setXxhSj(BigDecimal xxhSj) {
		this.xxhSj = xxhSj;
	}

	@Column(name = "DCPG", precision = 22, scale = 0)
	public BigDecimal getDcpg() {
		return this.dcpg;
	}

	public void setDcpg(BigDecimal dcpg) {
		this.dcpg = dcpg;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}