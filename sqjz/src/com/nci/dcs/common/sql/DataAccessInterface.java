package com.nci.dcs.common.sql;

import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

public interface DataAccessInterface {
    
    /**
     * 插入数据
     * @param columnMap 存储数据信息
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String insert(final Map < String, Object > columnMap) throws AxisFault;
    
    public String insertBatch(List < Map < String, Object > > columnList) throws AxisFault;
    /**
     * 插入数据
     * @param columnMap 存储数据信息
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String insertWithoutTableName(final Map < String, Object > columnMap) throws AxisFault;
    
    /**
     * 更新数据
     * @param columnMap 存储数据信息
     * @param whereInfo 过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String update(final Map < String, Object > columnMap, final String whereInfo) throws AxisFault;
    
    /**
     * 更新数据
     * @param columnMap 存储数据信息
     * @param whereInfo 过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String updateWithoutTableName(final Map < String, Object > columnMap, final String whereInfo) throws AxisFault;
    
    /**
     * 删除数据
     * @param whereInfo 过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String delete(final String whereInfo) throws AxisFault;
    
    /**
     * 删除数据
     * @param whereInfo 过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String deleteWithoutTableName(final String whereInfo) throws AxisFault;
    
    /**
     * 获取记录条数
     * @param whereInfo 过滤条件
     * @return 记录总数
     * @throws AxisFault
     */
    public String getRecordCount(final String whereInfo) throws AxisFault;
    
    /**
     * 获取记录数
     * @param whereInfo
     * @return String 记录总数
     * @throws AxisFault
     */
    public String getRecordCountWithoutTableName(final String whereInfo) throws AxisFault;
    
    /**
     * 查询数据
     * @param columnList 存储查询字段名称
     * @param pageNo 页码
     * @param pageSize 每页记录数
     * @param whereInfo 过滤条件
     * @return String 记录
     * @throws AxisFault
     * 
	 * 返回值由结果集与执行信息
	 * 通过符号“，”分割 结果集是由字段名称及字段数值组成
	 * 通过符号“：”分割 执行信息：
	 * 0：对表没有操作权限 
	 * 1：对表没有查询权限 
	 * 2：数据访问用户权限已经过期 
	 * 3：对表的某个字段没有查询权限 
	 * 4：成功执行
	 * 5：数据访问用户密码不正确
	 * 6:用户不存在
     */
    public String selectPage(final List < String > columnList, final int pageNo, final int pageSize, final String whereInfo, final String order) throws AxisFault;
    
    /**
     * 查询数据
     * @param columnList 存储查询字段名称
     * @param pageNo 页码
     * @param pageSize 每页记录数
     * @param whereInfo 过滤条件
     * @return String 记录
     * @throws AxisFault
     */
    public String selectPageWithoutTableName(final List < String > columnList, final int pageNo, final int pageSize, final String whereInfo, final String order) throws AxisFault;
    
    /**
     * 查询数据
     * @param columnList 存储查询字段名称
     * @param whereInfo 过滤条件
     * @return String 记录
     * @throws AxisFault
     */
    public String select(final List < String > columnList, final String whereInfo) throws AxisFault;
    
    /**
     * 查询数据
     * @param columnList 存储查询字段名称
     * @param whereInfo 过滤条件
     * @return String 记录
     * @throws AxisFault
     */
    public String selectWithoutTableName(final List < String > columnList, final String whereInfo) throws AxisFault;
    
    /**
     * @param columnList 存储查询字段名称
     * @param whereInfo 过滤条件
     * @param order 排序条件
     * @return String 记录
     * @throws AxisFault
     */
    public String selectOrder(List < String > columnList, String whereInfo, String order) throws AxisFault;
}
