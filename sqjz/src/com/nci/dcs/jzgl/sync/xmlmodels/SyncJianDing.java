package com.nci.dcs.jzgl.sync.xmlmodels;

import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Addr;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianDing;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.XSJJ;
import com.thoughtworks.xstream.annotations.XStreamAlias;

//D、	期满鉴定信息同步 --解矫信息
@XStreamAlias("List")
public class SyncJianDing extends SyncOthers{
	@XStreamAlias("JianDing")
	private JianDing jianDing = new JianDing();
	
	@XStreamAlias("XSJJ")
	private XSJJ xsjj = new XSJJ();
	
	@XStreamAlias("Addr")
	private Addr addr = new Addr();

	public JianDing getJianDing() {
		return jianDing;
	}

	public void setJianDing(JianDing jianDing) {
		this.jianDing = jianDing;
	}

	public XSJJ getXsjj() {
		return xsjj;
	}

	public void setXsjj(XSJJ xsjj) {
		this.xsjj = xsjj;
	}

	public Addr getAddr() {
		return addr;
	}

	public void setAddr(Addr addr) {
		this.addr = addr;
	}
}
