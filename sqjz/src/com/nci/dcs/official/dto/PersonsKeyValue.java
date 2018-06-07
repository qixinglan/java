package com.nci.dcs.official.dto;

import com.nci.dcs.official.model.Persons;

public class PersonsKeyValue {
	private String name;
	private String code;
	private boolean using;
	private String phone;
   
	public PersonsKeyValue(){}
	
	public PersonsKeyValue(Persons per) {
		this.code = per.getId();
		this.name = per.getName();
		if(null==per.getSfzg()){
			this.using=true;
		}else if("1".equals(per.getSfzg())){
			this.using=true;
		}else{
			this.using=false;
		}
		this.phone=per.getPhone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}