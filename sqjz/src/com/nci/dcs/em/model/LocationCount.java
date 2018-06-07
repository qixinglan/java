package com.nci.dcs.em.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//add by notebook 
@Entity
@Table(name = "CC_LOCATION_COUNT", schema = "SQJZ")
public class LocationCount implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	@Column(name = "ORG_ID", length = 32)
	private String orgId;
	@Column(name = "FXRY_ID", length = 32)
	private String fxryId;
	@Column(name = "LCOUNT", precision = 22, scale = 0)
	private BigDecimal count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}
}