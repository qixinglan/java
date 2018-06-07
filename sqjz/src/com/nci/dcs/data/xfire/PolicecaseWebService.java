package com.nci.dcs.data.xfire;
public interface PolicecaseWebService {

	/**
	 * 获取案件统计数据
	 * @param unitId
	 * @param caseTypeName
	 * @param startDate
	 * @param endDate
	 * @param mode   取值范围(1，2，3)  1：统计本单位  ，2：统计直属下级单位		3：统计本单位以及所有下级单位
	 * @return
	 */
	public String stat(String unitId,String caseTypeCode,String startDate,String endDate,String mode);
	
	/**
	 * 列出多个单位的指定案件
	 * @param unitIds
	 * @param caseTypeCode
	 * @param startDate
	 * @param endDate
	 * @param mode
	 * @return
	 */
	public String stat(String[] unitIds,String caseTypeCode,String startDate,String endDate,String mode);
	
	/**
	 * 获取案件个数
	 * @param unitId
	 * @param caseTypeName
	 * @param startDate
	 * @param endDate
	 * @param mode   取值范围(1，2，3)  1：统计本单位  ，2：统计直属下级单位		3：统计本单位以及所有下级单位
	 * @return
	 */
	public Integer getPoliceCaseCount(String unitId,String caseTypeCode,String startDate,String endDate,String mode);
	
	/**
	 * 获取单位列表
	 * @return
	 */
	public String getAllUnitListAsXml();
	
	/**
	 * 根据用户id得到单位Id
	 * @param userId
	 * @return
	 */
	public String getUnitIdByUserId(String userId);
	
	/**
	 * 获取单位的案件类别
	 * @param unitId
	 * @return
	 */
	public String getAllCaseTypeOfUnitAsXml(String unitId);
	
	/**
	 * 获得单位的案件树（携带数字字段）
	 * @param unitId
	 * @return
	 */
	public String getAllCaseTypeWithNumberTypeItemOfUnitAsXml(String unitId);
	
	
}
