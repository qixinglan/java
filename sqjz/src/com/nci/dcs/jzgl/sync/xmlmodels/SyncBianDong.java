package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.BianDong;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//k、	刑期变动情况
@XStreamAlias("List")
public class SyncBianDong  extends SyncOthers {
	@XStreamImplicit(itemFieldName="BianDong")
	private List<BianDong> biandong = new ArrayList<BianDong>();

	public List<BianDong> getBiandong() {
		return biandong;
	}

	public void setBiandong(List<BianDong> biandong) {
		this.biandong = biandong;
	}
}
