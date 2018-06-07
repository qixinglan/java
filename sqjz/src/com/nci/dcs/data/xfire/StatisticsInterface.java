package com.nci.dcs.data.xfire;

import java.util.Date;

public interface StatisticsInterface {
	
	public String getDutyList(String unitId,Date startTime,Date endTime);

	public String getDynaData(String unitId,Date startTime,Date endTime,int mode);
	
	public String getDynaList(String unitId,Date startTime,Date endTime,int firstPosition,int pageSize,int mode);
	
	public String getPoliceCaseCount(String caseType,String unitId,Date startTime,Date endTime);
	
	public String getPoliceCasePersonCount(String caseType,String unitId,Date startTime,Date endTime,String code);
	
	public String getPoliceCaseList(String caseType,String unitId,Date startTime,Date endTime,int pegesize,int pageno);
	
	public String getPoliceCaseListForComprehensiveDataServer(String caseType,String[] unitIds,Date startTime,Date endTime,int pegesize,int pageno);
	
	public String getPoliceCaseListCountForComprehensiveDataServer(String caseType,String[] unitIds,Date startTime,Date endTime);
	
	public String getArmyDynamicInfoServer(String code,String unitId);
	
	public String getDynNumInfo(String code, String unitId);
	
	public String getXingShiZhiAnCount(String unitId,String caseTypeCode,Date startDate,Date endDate);
	
	public String getBorderPeron(String code,String unitId);
}
