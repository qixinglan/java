package com.nci.dcs.jzgl.sync.utils;

import com.nci.dcs.jzgl.sync.handler.URLSyncHandler;

public class SyncHelper {
	private static final String ENCODING = "gb2312";
	private static final String URL_PREFIX = "http://test.zhonghezhiyuan.com/sqjz";
	private static final String URL_PERSON = URL_PREFIX + "/post_api/sqjz_person.php";
	private static final String URL_FALV = URL_PREFIX + "/post_api/sqjz_falv.php";
	private static final String URL_XINGFA = URL_PREFIX + "/post_api/sqjz_xingfa.php";
	private static final String URL_JIANDING = URL_PREFIX + "/post_api/sqjz_jiechu.php";
	private static final String URL_DELETE = URL_PREFIX + "/post_api/delete.php";
	private static final String URL_GUANXI = URL_PREFIX + "/post_api/sqjz_guanxi.php";
	private static final String URL_JIANLI = URL_PREFIX + "/post_api/sqjz_jianli.php";
	private static final String URL_TUOGUAN = URL_PREFIX + "/post_api/sqjz_tuoguan.php";
	private static final String URL_YUZUI = URL_PREFIX + "/post_api/sqjz_yuzui.php";
	private static final String URL_BIANDONG = URL_PREFIX + "/post_api/sqjz_biandong.php";
	
	public static URLSyncHandler createPersonHandler() throws SyncException{
		return new URLSyncHandler("上报人员基本信息", URL_PERSON, ENCODING);
	}
	
	public static URLSyncHandler createFalvHandler() throws SyncException{
		return new URLSyncHandler("上报法律文书信息", URL_FALV, ENCODING);
	}
	
	public static URLSyncHandler createXingFaHandler() throws SyncException{
		return new URLSyncHandler("上报刑罚执行信息", URL_XINGFA, ENCODING);
	}
	
	public static URLSyncHandler createJianDingHandler() throws SyncException{
		return new URLSyncHandler("上报期满鉴定（解矫）信息", URL_JIANDING, ENCODING);
	}
	
	public static URLSyncHandler createDeleteHandler() throws SyncException{
		return new URLSyncHandler("删除信息", URL_DELETE, ENCODING);
	}
	
	public static URLSyncHandler createGuanXiHandler() throws SyncException{
		return new URLSyncHandler("上报人员社会关系", URL_GUANXI, ENCODING);
	}
	
	public static URLSyncHandler createJianLiHandler() throws SyncException{
		return new URLSyncHandler("上报人员简历", URL_JIANLI, ENCODING);
	}
	
	public static URLSyncHandler createTuoGuanHandler() throws SyncException{
		return new URLSyncHandler("上报脱管信息", URL_TUOGUAN, ENCODING);
	}
	
	public URLSyncHandler createYuZuiHandler() throws SyncException{
		return new URLSyncHandler("上报余罪或再罪有关情况", URL_YUZUI, ENCODING);
	}
	
	public URLSyncHandler createBianDongHandler() throws SyncException{
		return new URLSyncHandler("上报刑期变动情况", URL_BIANDONG, ENCODING);
	}
}
