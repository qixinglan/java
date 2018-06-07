package com.nci.dcs.jzgl.sync.xmlmodels;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.PersonId;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public abstract class SyncOthers  extends SyncObject{
	@XStreamAlias("Person")
	private PersonId personId = new PersonId();
	
	@Override
	public void setPersonId(String id){
		this.personId.setID(id);
	}
	
	@Override
	public String getPersonId(){
		return this.personId.getID();
	}
}
