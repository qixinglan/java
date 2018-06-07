package com.nci.dcs.jzgl.sync;

import com.nci.dcs.jzgl.sync.utils.XMLUtil;
import com.nci.dcs.jzgl.sync.xmlmodels.Result;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncBianDong;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncDelete;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncFalv;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncGuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianDing;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncPerson;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncTuoGuan;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncXingfa;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncYuZui;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.BianDong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GongTong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YaSong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YuZui;

public class demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testSyncPerson();
		//testSyncFalv();
		//testSyncXingfa();
		//testSyncJianDing();
		//testSyncDelete();
		//testSyncGuanXi();
		//testSyncJianLi();
		//testSyncTuoGuan();
		//testSyncYuZui();
		//testSyncBianDong();
		testParseResult();
	}

	public static void testSyncPerson(){
		//A、	人员基本信息同步
		SyncPerson syncPerson = new SyncPerson();
		syncPerson.getPerson().setUsedName("测试姓名");
		System.out.println(XMLUtil.format(syncPerson));
	}
	
	public static void testSyncFalv(){
		//B、	法律文书信息同步
		SyncFalv syncFalv = new SyncFalv();
		syncFalv.getFalv().setDaiBu("XXDJFDKSFDSFDSFDS");
		System.out.println(XMLUtil.format(syncFalv));
	}
	
	public static void testSyncXingfa(){
		//C、	刑罚信息同步
		SyncXingfa syncXingfa = new SyncXingfa();
		syncXingfa.getXingfa().setRemark("我有罪");
		syncXingfa.getGongtong().add(new GongTong());
		syncXingfa.getYasong().add(new YaSong());
		System.out.println(XMLUtil.format(syncXingfa));
	}
	
	public static void testSyncJianDing(){
		//D、	期满鉴定信息同步 --解矫信息
		SyncJianDing syncJianDing = new SyncJianDing();
		System.out.println(XMLUtil.format(syncJianDing));
	}
	
	public static void testSyncDelete(){
		//E、	信息删除
		SyncDelete syncDelete = new SyncDelete();
		syncDelete.setType(SyncDelete.DeleteType.JIANLI);
		System.out.println(XMLUtil.format(syncDelete));
	}
	
	public static void testSyncGuanXi(){
		//G、家庭成员及主要社会关系
		SyncGuanXi syncGuanXi = new SyncGuanXi();
		syncGuanXi.getGuanxi().add(new GuanXi());
		System.out.println(XMLUtil.format(syncGuanXi));
	}
	public static void testSyncJianLi(){
		//H、	个人简历
		SyncJianLi syncJianLi = new SyncJianLi();
		syncJianLi.getJianli().add(new JianLi());
		System.out.println(XMLUtil.format(syncJianLi));
	}
	
	public static void testSyncTuoGuan(){
		//I、矫正脱管
		SyncTuoGuan syncTuoGuan = new SyncTuoGuan();
		System.out.println(XMLUtil.format(syncTuoGuan));
	}
	
	public static void testSyncYuZui(){
		//J、余罪或再罪有关情况
		SyncYuZui syncYuZui = new SyncYuZui();
		syncYuZui.getYuzui().add(new YuZui());
		System.out.println(XMLUtil.format(syncYuZui));
	}
	
	public static void testSyncBianDong(){
		//k、	刑期变动情况
		SyncBianDong syncBianDong = new SyncBianDong();
		syncBianDong.getBiandong().add(new BianDong());
		System.out.println(XMLUtil.format(syncBianDong));
	}
	
	public static void testParseResult(){
		try{
			String result = "<?xml version=\"1.0\" encoding=\"gb2312\" ?><Result><State>error</State><ID></ID><Desc>数据不符合协议规定的xml格式</Desc></Result>";
			Result r = (Result)XMLUtil.parse(result);
			System.out.println(r.getDesc());
			testSyncBianDong();
		}
		catch (Exception e){
			System.out.println("Exception:" + e.getMessage());
		}
	}
}

