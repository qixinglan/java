package com.nci.dcs.jzgl.sync.xmlmodels;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public abstract class SyncObject {
	@XStreamAlias("User")
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public abstract void setPersonId(String id);
	public abstract String getPersonId();
}
