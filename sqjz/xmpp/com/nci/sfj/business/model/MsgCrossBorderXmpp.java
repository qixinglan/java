package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录是否越界的实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_MSG_CROSSBORDER", schema = "SQJZ")
public class MsgCrossBorderXmpp {

	/**
	 * Description:主键
	 */
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private String id;

	/**
	 * Description:经度
	 */
	@Column(name = "LONGITUDE")
	private Double gis_x;

	/**
	 * Description:纬度
	 */
	@Column(name = "LATITUDE")
	private Double gis_y;

	/**
	 * Description:对应的服刑人员主键
	 */
	@Column(name = "FXRY_ID")
	private String fxryId;

	/**
	 * Description:定位时间
	 */
	@Column(name = "TIME")
	private Date time;

	/**
	 * Description:是否越界(1:越界;2:未越界)
	 */
	@Column(name = "ISCROSS")
	private String isCross;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getGis_x() {
		return gis_x;
	}

	public void setGis_x(Double gis_x) {
		this.gis_x = gis_x;
	}

	public Double getGis_y() {
		return gis_y;
	}

	public void setGis_y(Double gis_y) {
		this.gis_y = gis_y;
	}

	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIsCross() {
		return isCross;
	}

	public void setIsCross(String isCross) {
		this.isCross = isCross;
	}

}