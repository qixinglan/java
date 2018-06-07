package com.nci.dcs.jzgl.sync.xmlmodels;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.TuoGuan;
import com.thoughtworks.xstream.annotations.XStreamAlias;

//I、矫正脱管
@XStreamAlias("List")
public class SyncTuoGuan extends SyncOthers {
	@XStreamAlias("TuoGuan")
	private TuoGuan tuoguan = new TuoGuan();

	public TuoGuan getTuoguan() {
		return tuoguan;
	}

	public void setTuoguan(TuoGuan tuoguan) {
		this.tuoguan = tuoguan;
	}
}
