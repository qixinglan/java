package com.nci.dcs.jzgl.sync.xmlmodels;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GongTong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.XingFa;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YaSong;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//C、	刑罚信息同步
@XStreamAlias("List")
public class SyncXingfa extends SyncOthers {
	@XStreamAlias("XingFa")
	private XingFa xingfa = new XingFa();
	
	@XStreamImplicit(itemFieldName="YaSong")
	private List<YaSong> yasong = new ArrayList<YaSong>();
	
	@XStreamImplicit(itemFieldName="GongTong")
	private List<GongTong> gongtong = new ArrayList<GongTong>();

	public XingFa getXingfa() {
		return xingfa;
	}

	public void setXingfa(XingFa xingfa) {
		this.xingfa = xingfa;
	}

	public List<YaSong> getYasong() {
		return yasong;
	}

	public void setYasong(List<YaSong> yasong) {
		this.yasong = yasong;
	}

	public List<GongTong> getGongtong() {
		return gongtong;
	}

	public void setGongtong(List<GongTong> gongtong) {
		this.gongtong = gongtong;
	}
}
