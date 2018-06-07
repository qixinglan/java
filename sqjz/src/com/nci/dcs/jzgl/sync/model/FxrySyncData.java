package com.nci.dcs.jzgl.sync.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * CcFxrySyncDate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_SYNC_DATA", schema = "SQJZ")
public class FxrySyncData implements java.io.Serializable {
	// Fields
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	@Column(name = "FXRY_ID", length = 32)
	private String fxryId;
	@Column(name = "DATA_TYPE", length = 30)
	private String dataType;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_TIME", length = 7)
	private Date dataTime;
	@Lob
	@Column(name = "XML_DATA")
	private byte[] xmlData;
	@Column(name = "DATA_STATE", length = 30)
	private String dataState;

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public byte[] getXmlData() {
		return xmlData;
	}

	public void setXmlData(byte[] xmlData) {
		this.xmlData = xmlData;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

}
