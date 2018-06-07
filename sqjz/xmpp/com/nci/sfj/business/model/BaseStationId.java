package com.nci.sfj.business.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Description:基站信息的联合主键实体类
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class BaseStationId implements java.io.Serializable {

	// Fields
	@Column(name = "MCC")
	private Long mcc;
	@Column(name = "MNC")
	private Long mnc;
	@Column(name = "LAC")
	private Long lac;
	@Column(name = "CELL")
	private Long cell;
	@Column(name = "LNG")
	private Long lng;
	@Column(name = "LAT")
	private Long lat;

	public Long getMcc() {
		return mcc;
	}

	public void setMcc(Long mcc) {
		this.mcc = mcc;
	}

	public Long getMnc() {
		return mnc;
	}

	public void setMnc(Long mnc) {
		this.mnc = mnc;
	}

	public Long getLac() {
		return lac;
	}

	public void setLac(Long lac) {
		this.lac = lac;
	}

	public Long getCell() {
		return cell;
	}

	public void setCell(Long cell) {
		this.cell = cell;
	}

	public Long getLng() {
		return lng;
	}

	public void setLng(Long lng) {
		this.lng = lng;
	}

	public Long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((lac == null) ? 0 : lac.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseStationId other = (BaseStationId) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (lac == null) {
			if (other.lac != null)
				return false;
		} else if (!lac.equals(other.lac))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc))
			return false;
		return true;
	}

}