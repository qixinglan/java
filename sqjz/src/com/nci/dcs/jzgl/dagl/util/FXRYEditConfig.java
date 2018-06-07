package com.nci.dcs.jzgl.dagl.util;

public class FXRYEditConfig {
	private boolean moreinfo = false;
	private boolean legal = false;
	private boolean executeinfo = false;
	private boolean team = false;
	private boolean release = false;
	
	public FXRYEditConfig(){}
	public FXRYEditConfig(boolean same){
		this.moreinfo = same;
		this.legal = same;
		this.executeinfo = same;
		this.release = same;
		this.team = same;
	}
	public boolean isMoreinfo() {
		return moreinfo;
	}
	public void setMoreinfo(boolean moreinfo) {
		this.moreinfo = moreinfo;
	}
	public boolean isLegal() {
		return legal;
	}
	public void setLegal(boolean legal) {
		this.legal = legal;
	}
	public boolean isExecuteinfo() {
		return executeinfo;
	}
	public void setExecuteinfo(boolean executeinfo) {
		this.executeinfo = executeinfo;
	}
	public boolean isRelease() {
		return release;
	}
	public void setRelease(boolean release) {
		this.release = release;
	}
	public boolean isTeam() {
		return team;
	}
	public void setTeam(boolean team) {
		this.team = team;
	}
}
