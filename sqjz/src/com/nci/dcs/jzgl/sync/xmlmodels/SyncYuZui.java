package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YuZui;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//J、余罪或再罪有关情况
@XStreamAlias("List")
public class SyncYuZui extends SyncOthers {
	@XStreamImplicit(itemFieldName="YuZui")
	private List<YuZui> yuzui = new ArrayList<YuZui>();

	public List<YuZui> getYuzui() {
		return yuzui;
	}

	public void setYuzui(List<YuZui> yuzui) {
		this.yuzui = yuzui;
	}
}
