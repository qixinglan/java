package com.nci.dcs.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.nci.dcs.data.model.Jx;
import com.nci.dcs.data.model.Zw;
import com.nci.dcs.official.model.Persons;

@Entity
@Table(name = "CC_USER_INFO")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2531673725606707746L;
	private String id;
	private String name;
	private String userName;
	private String passWord;
	private Set<Role> roles = new HashSet<Role>(0);
	private Date cjsj;// 创建时间
	private String cjr;// 创建人
	private Date xgsj;// 修改时间
	private String xgr;// 修改人
	private Integer sex;// 性别
	private String idcard;// 身份证号
	// private String dunit;//编制单位
	// private String wunit;//工作单位
	// private String duty;//职务
	// private String title;//警衔
	private Bmb dunit;
	private Bmb wunit;
	private Zw duty;
	private Jx title;
	private Persons person;
	private String isvalid;
	private String roleNames;
	private String isws;//是否有查看特管人员权限
	@Column(name = "ISWS", length = 30)
	public String getIsws() {
		return isws;
	}

	public void setIsws(String isws) {
		this.isws = isws;
	}
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
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

	@ManyToMany
	@JoinTable(name = "CC_PERSONROLES", joinColumns = @JoinColumn(name = "USERID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "CJSJ")
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	@Column(name = "CJR")
	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	@Column(name = "XGSJ")
	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	@Column(name = "XGR")
	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	@Column(name = "SEX")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "IDCARD")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DUNIT", nullable = true)
	public Bmb getDunit() {
		return dunit;
	}

	public void setDunit(Bmb dunit) {
		this.dunit = dunit;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WUNIT", nullable = true)
	public Bmb getWunit() {
		return wunit;
	}

	public void setWunit(Bmb wunit) {
		this.wunit = wunit;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DUTY", nullable = true)
	public Zw getDuty() {
		return duty;
	}

	public void setDuty(Zw duty) {
		this.duty = duty;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TITLE", nullable = true)
	public Jx getTitle() {
		return title;
	}

	public void setTitle(Jx title) {
		this.title = title;
	}

	@OneToOne()
	@JoinColumn(name = "PERSON_ID", insertable = true, unique = true)
	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	@Column(name = "ISVALID")
	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	@Transient
	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

}