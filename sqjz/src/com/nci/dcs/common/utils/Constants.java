package com.nci.dcs.common.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 常量定义
 * 
 */
public class Constants {
	// 人员状态
	public static final String STAFF_ENABLED = "ENABLED"; // 有效
	public static final String STAFF_DISABLED = "DISABLED"; // 无效
	public static final String STAFF_LOCKED = "LOCKED"; // 锁定
	
	//实体对象标记
	public static final int FLAG_NORMAL = 0;		//正常状态
	public static final int FLAG_DELETEED = 1;		//删除状态
	public static final int FLAG_AUDITING = 2;		//审核中状态
	public static final int FLAG_FAILURE = 4;		//审核失败状态
	public static final int FLAG_CONVERTING = 8;		//审核失败状态
	public static final int FLAG_ALL = 64;		//非删除状态
	
	//数据查询方式
	public static final byte SELECT_TYPE_SQL=0;			//数据查询方式，使用SQL语句查询数据
	public static final byte SELECT_TYPE_SERVICE=1;		//数据查询方式，使用服务方式查询数据

	//节点类型
	public static final byte NODE_LEAF = 1;
	public static final byte NODE_FOLDER = 0;
	

	//排序类型
	public static final int ORDER_DAY = 0;
	public static final int ORDER_WEEK = 1;
	public static final int ORDER_MONTH = 2;
	
	//日志类型
	public static final int LOG_LOGIN = 0;	//登录日志
	public static final int LOG_OPER = 1;	//操作日志
	
	
	//操作日志类型
	public static final int LOG_OPER_ADD = 0;
	public static final int LOG_OPER_DELETE = 1;
	public static final int LOG_OPER_MODIFY = 2;
	public static final int LOG_OPER_SELECT = 4;

	//图片上传路径
	public static final String SEPARATOR = File.separator;	
	public static final String IMAGEPATH = "upload" + SEPARATOR + "images";	//上传图片的存放路径
	
	//文件上传路径
    
	public static final String FILEPATH = "upload" + SEPARATOR + "files";	//上传文件的存放路径
	public static final String YSFILEPATH = FILEPATH + SEPARATOR + "ys";	//上传预算文件的存放路径
	public static final String JHFILEPATH = FILEPATH + SEPARATOR + "jh";	//上传计划文件的存放路径
	public static final String ZXFILEPATH = FILEPATH + SEPARATOR + "zx";	//上传执行文件的存放路径
	
	//文件读取缓冲池大小(字节)
	public static final int BUFFERSIZE = 2048;
	
	//默认分页大小
	public static final int PAGESIZE = 15;
	
	//导出数据时每个sheet的数据量
	public static double SHEETSIZE = 10000.0;
	
	//发布信息类型
	public static final int TZGG_GZDT = 0;  //工作动态
	public static final int TZGG_ZBGG = 1;  //招标公告,14/01/23删除
	public static final int TZGG_ZBGS = 2;  //中标公示,14/01/23删除
	public static final int TZGG_FBGG = 3;  //废标公告,14/01/23删除
	public static final int TZGG_GZGG = 4;  //更正公告,14/01/23删除
	public static final int TZGG_FLFG = 5;  //政策法规
	public static final int TZGG_TZTG = 6;  //通知通告
	public static final int TZGG_JNHB = 7;  //节能环保标志清单
	public static final int TZGG_ZLXZ = 8;  //资料下载
	public static final int TZGG_ZZJG = 9;  //组织机构
	public static final int TZGG_BSZN = 10;  //办事指南
	
	//发布类型
	public static final int WFB = 0;        //未发布
	public static final int YFB = 1;        //已发布
	
	//待办事宜状态
	public static final int DBSY_WCK = 0;	//未查看
	public static final int DBSY_YCK = 1;	//已查看
	
	//待办事宜紧急程度
	public static final int DBSY_JJ = 0;	//紧急
	public static final int DBSY_YB = 1;	//一般
	
	//首页参数
	public static final int GYSLINE = 14;
	
	//采购过程状态
	public static final int CGGC_WKS = 0;	//未开始
	public static final int CGGC_JXZ = 1;	//进行中
	public static final int CGGC_YWC = 2;	//已完成
	
	//采购上报状态
	public static final int SBZT_WSB = 0;	//未上报
	public static final int SBZT_YSB = 3;	//已上报
	
	//采购计划
	//报送状态
	public static final int BSZT_WBS = 0;//未报送
	public static final int BSZT_YBS = 1;//已报送
	
	//审核状态
	public static final int SHZT_WSH = 0;//未审核
	public static final int SHZT_YTG = 1;//已通过
	public static final int SHZT_WTG = 2;//未通过
	
	//定标状态
	public static final int DBZT_DD = 0;//等待定标
	public static final int DBZT_WZ = 1;//未中
	public static final int DBZT_YZ = 2;//已中
	
	//废止状态
	public static final int FZZT_WFZ = 0; //未废止
	public static final int FZZT_YFZ = 1; //已废止
	
	//采购方式
	public static final int CGFS_GK = 0; //公开
	public static final int CGFS_YQ = 1; //邀请
	public static final int CGFS_JZ = 2; //竞争
	public static final int CGFS_XJ = 3; //询价
	public static final int CGFS_DY = 4; //单一来源
	public static final int CGFS_XY = 5; //协议供货
	public static final int CGFS_JJ = 6; //网上竞价
	public static final int CGFS_DD = 7; //定点采购
	
	//抽取专家确认状态
	public static final int QRZT_DQR = 0;//待确认
	public static final int QRZT_CG = 1;//确认成功
	public static final int QRZT_SB = 2;//确认失败
	
	//分数类型
	public static final int FSLX_GDLX = 0;//固定类型
	public static final int FSLX_FWLX = 1;//范围类型
	
	//进口情况
	public static final int JKQK_FJK = 0; //非进口
	public static final int JKQK_JK = 1; //进口
	
	//供应商是否中标状态
	public static final int SFZB_DDDB=0;//等待定标
	public static final int SFZB_ZB=1;//中标
	public static final int SFZB_WZB=2;//未中标
}
