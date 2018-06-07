package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:服刑人员佩戴腕表的历史记录实体类
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_DEVICE", schema = "SQJZ")
public class FxryDevice implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 * Description:腕表实体类
	 */
	private SdeviceXmpp superviseDevice;
	/**
	 * Description:服刑人员主键
	 */
	private String personId;
	/**
	 * Description:开始佩戴时间
	 */
	private Date startTime;
	/**
	 * Description:结束佩戴时间
	 */
	private Date endTime;
	/**
	 * Description:佩戴状态(1:正在佩戴;0:结束佩戴)
	 */
	private int userStatus;

	// Constructors

	/** default constructor */

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PERSON_ID", nullable = false, length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID", nullable = false, insertable = false, updatable = false)
	public SdeviceXmpp getSuperviseDevice() {
		return superviseDevice;
	}

	public void setSuperviseDevice(SdeviceXmpp superviseDevice) {
		this.superviseDevice = superviseDevice;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_TIME", length = 7)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "USER_STATUS")
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
}