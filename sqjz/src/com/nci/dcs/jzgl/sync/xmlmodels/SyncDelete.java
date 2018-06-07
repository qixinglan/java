package com.nci.dcs.jzgl.sync.xmlmodels;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//E、	信息删除
@XStreamAlias("List")
public class SyncDelete extends SyncObject{
	public enum DeleteType{
		ALL("pri"), //人员全部信息
		JIANLI( "JianLi"),//简历
		GUANXI( "GuanXi"),//家庭成员
		GONGTONG( "GongTong"),//共同犯罪
		YASONG( "YaSong"),//押送
		TUOGUAN( "TuoGuan"),//脱管
		JIANDING( "JianDing"),//鉴定 or 解矫
		BIANDONG( "BianDong"),//刑期变动
		YUZUI( "YuZui"),//余罪
		JIBENG("JiBeng");//基本
		private String value;
		private DeleteType(String v){
			this.value = v;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	private String Type;
	private String ID;
	private String ListID;
	public String getType() {
		return Type;
	}
	public void setType(DeleteType type) {
		Type = type.getValue();
	}
	@Override
	public void setPersonId(String id){
		this.ID = id;
	}
	
	@Override
	public String getPersonId(){
		return this.ID;
	}
	
	public String getListID() {
		return ListID;
	}
	public void setListID(String listID) {
		ListID = listID;
	}
}
