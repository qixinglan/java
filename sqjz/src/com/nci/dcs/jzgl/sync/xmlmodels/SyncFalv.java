package com.nci.dcs.jzgl.sync.xmlmodels;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Falv;
import com.thoughtworks.xstream.annotations.XStreamAlias;

//B、	法律文书信息同步
@XStreamAlias("List")
public class SyncFalv extends SyncOthers{
	@XStreamAlias("FaLv")
	private Falv falv = new Falv();
	public Falv getFalv() {
		return falv;
	}
	public void setFalv(Falv falv) {
		this.falv = falv;
	}
}
