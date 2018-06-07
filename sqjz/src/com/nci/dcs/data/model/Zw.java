package com.nci.dcs.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 干部职务表
 * @author zjc
 *
 */
@Entity
@Table(name = "D_ZW")
public class Zw implements Serializable{
	private String dm;//代码
	private String mc;//名称

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="g")
	@SequenceGenerator(name="g",sequenceName="SEQ_D_ZW")
	@Column(name = "DM", unique = true,nullable = false)
	public String getDm(){
		return dm;
	}
	public void setDm(String dm){
		this.dm = dm;
	}
	
	@Column(name = "MC")
	public String getMc(){
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
}
