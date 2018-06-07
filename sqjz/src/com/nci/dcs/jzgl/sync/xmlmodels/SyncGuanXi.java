package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GuanXi;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//G、家庭成员及主要社会关系
@XStreamAlias("List")
public class SyncGuanXi extends SyncOthers {
	@XStreamImplicit(itemFieldName="GuanXi")
	private List<GuanXi> guanxi = new ArrayList<GuanXi>();

	public List<GuanXi> getGuanxi() {
		return guanxi;
	}

	public void setGuanxi(List<GuanXi> guanxi) {
		this.guanxi = guanxi;
	}
}
