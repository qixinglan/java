package com.nci.dcs.jzgl.fingerprint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcFingerprintMachine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FINGERPRINT_MACHINE", schema = "SQJZ")
public class CcFingerprintMachine implements java.io.Serializable {

	// Fields

	private String id;
	private String machineId;
	private String machineIp;
	private String orgId;
	private String status;

	// Constructors

	/** default constructor */
	public CcFingerprintMachine() {
	}

	/** minimal constructor */
	public CcFingerprintMachine(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcFingerprintMachine(String id, String machineId, String orgId,
			String status,String machineIp) {
		this.id = id;
		this.machineId = machineId;
		this.orgId = orgId;
		this.status = status;
		this.machineIp = machineIp;
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

	@Column(name = "MACHINE_ID", length = 32)
	public String getMachineId() {
		return this.machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "STATUS", precision = 2, scale = 0)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MACHINE_IP", length = 32)
	public String getMachineIp() {
		return this.machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

}