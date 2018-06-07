package com.nci.sfj.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:移动执法终端授权码表
 * 
 * @author Shuzz
 * @since 2015年8月11日上午10:14:16
 * 
 */
@Entity
@Table(name = "CC_XMPP_JUSTICE")
public class XmppJustice {
	/**
	 * Description:主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * Description:移动执法终端IMEI号
	 */
	@Column(name = "A")
	private String imei;

	/**
	 * Description:是否授权
	 */
	@Column(name = "B")
	private String validate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

}
