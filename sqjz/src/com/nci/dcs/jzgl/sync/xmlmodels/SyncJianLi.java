package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianLi;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//H、	个人简历
@XStreamAlias("List")
public class SyncJianLi extends SyncOthers{
	@XStreamImplicit(itemFieldName="JianLi")
	private List<JianLi> jianli = new ArrayList<JianLi>();

	public List<JianLi> getJianli() {
		return jianli;
	}

	public void setJianli(List<JianLi> jianli) {
		this.jianli = jianli;
	}
}
