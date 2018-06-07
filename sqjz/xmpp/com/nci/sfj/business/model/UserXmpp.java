package com.nci.sfj.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Description:XMPP通信模块中用户信息实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_USER_INFO")
public class UserXmpp {

	/**
	 * Description:主键
	 */
	private String id;

	/**
	 * Description:姓名
	 */
	private String name;

	/**
	 * Description:用户名
	 */
	private String userName;

	/**
	 * Description:密码
	 */
	private String passWord;

	/**
	 * Description:所属机构
	 */
	private OrgXmpp wunit;

	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USER_MANE")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WUNIT", nullable = true)
	public OrgXmpp getWunit() {
		return wunit;
	}

	public void setWunit(OrgXmpp wunit) {
		this.wunit = wunit;
	}
}