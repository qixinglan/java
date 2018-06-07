package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Person;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//A、	人员基本信息同步s
@XStreamAlias("List")
public class SyncPerson extends SyncObject{
	@XStreamAlias("Person")
	private Person person = new Person();
	@XStreamImplicit(itemFieldName="JianLi")
	private List<JianLi> jianli = new ArrayList<JianLi>();
	@XStreamImplicit(itemFieldName="GuanXi")
	private List<GuanXi> guanxi = new ArrayList<GuanXi>();
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<JianLi> getJianli() {
		return jianli;
	}
	public void setJianli(List<JianLi> jianli) {
		this.jianli = jianli;
	}
	public List<GuanXi> getGuanxi() {
		return guanxi;
	}
	public void setGuanxi(List<GuanXi> guanxi) {
		this.guanxi = guanxi;
	}
	
	@Override
	public void setPersonId(String id){
		this.person.setID(id);
	}
	
	@Override
	public String getPersonId(){
		return this.person.getID();
	}
}
