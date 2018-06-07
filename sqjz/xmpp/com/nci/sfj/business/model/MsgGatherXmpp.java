package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录聚集消息的实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_MSG_GATHER", schema = "SQJZ")
public class MsgGatherXmpp {

	/**
	 * Description:
	 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	@Column(name = "FXRY1_ID")
	private String fxry1;
	@Column(name = "FXRY2_ID")
	private String fxry2;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME")
	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFxry1() {
		return fxry1;
	}

	public void setFxry1(String fxry1) {
		this.fxry1 = fxry1;
	}

	public String getFxry2() {
		return fxry2;
	}

	public void setFxry2(String fxry2) {
		this.fxry2 = fxry2;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}