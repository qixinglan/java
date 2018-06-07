package com.nci.dcs.common.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.json.JSONArray;

import com.nci.dcs.common.utils.MD5Util;


@SuppressWarnings("unchecked")
public class DataAccessImp implements DataAccessInterface {

    private static final String _NO_OPERATOR_METHOD = "The endpoint reference (EPR) for the Operation not found";

    private static final String _NO_OPERATOR_METHOD_MEAN = "视图不提供该方法";

    private static final String _CONNECTION_REASON = "Connection refused: connect";

    private static final String _CONNECTION_REASON_MEAN = "无法连接服务";

    private static final String _ENDPOINT_NO_FOUND = "The service cannot be found for the endpoint reference";

    private static final String _ENDPOINT_NO_FOUND_MEAN = "服务不存在";
    
    /** 过滤系统定义8种特殊字符 */
    private static final String REGEX = "[',\"()&%\\\\]";

    private String wsdlURL = "";

    private String userName = "";

    private String passWord = "";

    private String tableName = "";

    private Options options = null;

    private RPCServiceClient serviceClient;

    /**
     * DataAccessImp 构造器
     * 
     * @param wsdlURL
     *            服务地址
     * @param userName
     *            数据访问用户名
     * @param passWord
     *            数据访问密码
     * @param tableName
     *            操作表名称
     * @throws AxisFault
     */
    public DataAccessImp(final String wsdlURL, final String userName, final String passWord, final String tableName) throws AxisFault {
        // 创建RPCServiceClient对象
        serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        options = serviceClient.getOptions();

        this.wsdlURL = wsdlURL;
        this.userName = userName.replaceAll(REGEX, "");
        this.passWord = passWord;
        this.tableName = tableName;
    }

    /**
     * DataAccessImp 构造器
     * 
     * @param wsdlURL
     *            服务地址
     * @param userName
     *            数据访问用户名
     * @param passWord
     *            数据访问密码
     * @throws AxisFault
     */
    public DataAccessImp(final String wsdlURL, final String userName, final String passWord) throws AxisFault {
        // 创建RPCServiceClient对象
        serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        options = serviceClient.getOptions();
        this.wsdlURL = wsdlURL;
        this.userName = userName.replaceAll(REGEX, "");
        this.passWord = passWord;
    }

    /**
     * 删除数据
     * 
     * @param whereInfo
     *            过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String delete(String whereInfo) throws AxisFault {

        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // options = serviceClient.getOptions();

        options.setAction("delete");

        // 创建EndpointReference对象，并设置webservice地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 where条件语句 数据访问用户名 表名称
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { whereInfo, userName, tableName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "delete");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;

    }

    /**
     * 获取记录数
     * 
     * @param whereInfo
     * @return String 记录总数
     * @throws AxisFault
     */
    public String getRecordCount(String whereInfo) throws AxisFault {
        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient1 = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // Options options1 = serviceClient.getOptions();

        options.setAction("getCount");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR1 = new EndpointReference(wsdlURL);
        options.setTo(targetEPR1);
        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 where条件语句
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { userName, tableName, whereInfo, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry1 = new QName("http://ws.apache.org/axis2", "getCount");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String)
        // serviceClient1.invokeBlocking(opAddEntry1, opAddEntryArgs,
        // classes)[0];
        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry1, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
//        serviceClient.cleanup();
//        serviceClient = null;

        return strResult;
    }

    /**
     * 插入数据
     * 
     * @param columnMap
     *            存储数据信息
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String insert(Map < String, Object > columnMap) throws AxisFault {
        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // Options options = serviceClient.getOptions();

        options.setAction("insert");
    	
//    	options.setAction("insertBatch");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * 准备批量数据 Insert方法每次可以执行N条插入语句 每一条插入语句用Map对象保存 然后再放到List对象里
         */
        List < Map < String, Object > > list = new ArrayList < Map < String, Object > >();
        list.add(columnMap);
        JSONArray ja = new JSONArray(list);

        // 组织Webservice调用参数
        Object[] opAddEntryArgs = new Object[] { ja.toString(), userName, tableName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "insertBatch");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
//        serviceClient.cleanup();
//        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }
    
    public String insertBatch(List < Map < String, Object > > columnList) throws AxisFault {
        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // Options options = serviceClient.getOptions();

        options.setAction("insertBatch");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * 准备批量数据 Insert方法每次可以执行N条插入语句 每一条插入语句用Map对象保存 然后再放到List对象里
         */
        JSONArray ja = new JSONArray(columnList);

        // 组织Webservice调用参数
        Object[] opAddEntryArgs = new Object[] { ja.toString(), userName, tableName,
                MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "insertBatch");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
//        serviceClient.cleanup();
//        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    /**
     * 查询数据
     * 
     * @param columnList
     *            存储查询字段名称
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页记录数
     * @param whereInfo
     *            过滤条件
     * @return String 记录
     * @throws AxisFault
     * 
     */
    public String selectPage(List < String > columnList, int pageNo, int pageSize, String whereInfo, String order) throws AxisFault {
        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient2 = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // Options options2 = serviceClient.getOptions();

        options.setAction("selectpages");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR2 = new EndpointReference(wsdlURL);
        options.setTo(targetEPR2);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 数据访问用户密码
         * where条件语句 每页条数（返回记录的条数） 从第几条开始返回
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { convertArrayList(columnList), userName, tableName, MD5Util.MD5Encode(passWord), whereInfo, order, String.valueOf(pageSize), String.valueOf(pageNo) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry2 = new QName("http://ws.apache.org/axis2", "selectpages");
        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry2, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由结果集与执行信息，通过符号“，”分割 结果集是由字段名称及字段数值组成，通过符号“：”分割 执行信息： 0：对表没有操作权限
         * 1：对表没有查询权限 2：数据访问用户权限已经过期 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    /**
     * 更新数据
     * 
     * @param columnMap
     *            存储数据信息
     * @param whereInfo
     *            过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String update(Map < String, Object > columnMap, String whereInfo) throws AxisFault {
        // 创建RPCServiceClient对象
        // RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        // Options options = serviceClient.getOptions();

        options.setAction("update");

        // 创建EndpointReference对象，并设置webservice地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * 准备批量数据 Insert方法每次可以执行N条插入语句 每一条插入语句用Map对象保存 然后再放到List对象里
         */

        List < Map < String, Object > > list = new ArrayList < Map < String, Object > >();
        list.add(columnMap);
        JSONArray ja = new JSONArray(list);
        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 where条件语句 数据访问用户名 表名称
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { ja.toString(), new String[] { whereInfo }, userName, tableName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "update");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;

    }

    private String[] convertArrayList(List < String > list) {
        String[] s = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
        return s;
    }

    /**
     * 删除数据
     * 
     * @param whereInfo
     *            过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String deleteWithoutTableName(String whereInfo) throws AxisFault {
        options.setAction("deleteWithNotTableName");

        // 创建EndpointReference对象，并设置webservice地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 where条件语句 数据访问用户名 表名称
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { whereInfo, userName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "deleteWithNotTableName");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;

    }

    /**
     * 获取记录数
     * 
     * @param whereInfo
     * @return String 记录总数
     * @throws AxisFault
     */
    public String getRecordCountWithoutTableName(String whereInfo) throws AxisFault {
        options.setAction("getCountWithNotTableName");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR1 = new EndpointReference(wsdlURL);
        options.setTo(targetEPR1);
        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 where条件语句
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { userName, whereInfo, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry1 = new QName("http://ws.apache.org/axis2", "getCountWithNotTableName");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry1,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry1, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        return strResult;
    }

    /**
     * 插入数据
     * 
     * @param columnMap
     *            存储数据信息
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String insertWithoutTableName(Map < String, Object > columnMap) throws AxisFault {
        options.setAction("insertBatchWithNotTableName");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * 准备批量数据 Insert方法每次可以执行N条插入语句 每一条插入语句用Map对象保存 然后再放到List对象里
         */
        List < Map < String, Object > > list = new ArrayList < Map < String, Object > >();
        list.add(columnMap);
        JSONArray ja = new JSONArray(list);

        // 组织Webservice调用参数
        Object[] opAddEntryArgs = new Object[] { ja.toString(), userName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "insertBatchWithNotTableName");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    /**
     * 查询数据
     * 
     * @param columnList
     *            存储查询字段名称
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页记录数
     * @param whereInfo
     *            过滤条件
     * @return String 记录
     * @throws AxisFault
     */
    public String selectPageWithoutTableName(List < String > columnList, int pageNo, int pageSize, String whereInfo, String order) throws AxisFault {
        options.setAction("selectpagesWithNotTableName");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR2 = new EndpointReference(wsdlURL);
        options.setTo(targetEPR2);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 数据访问用户密码
         * where条件语句 每页条数（返回记录的条数） 从第几条开始返回
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { convertArrayList(columnList), userName, MD5Util.MD5Encode(passWord), whereInfo, order, String.valueOf(pageSize), String.valueOf(pageNo) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry2 = new QName("http://ws.apache.org/axis2", "selectpagesWithNotTableName");
        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry2,
        // opAddEntryArgs, classes)[0];
        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry2, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由结果集与执行信息，通过符号“，”分割 结果集是由字段名称及字段数值组成，通过符号“：”分割 执行信息： 0：对表没有操作权限
         * 1：对表没有查询权限 2：数据访问用户权限已经过期 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    /**
     * 更新数据
     * 
     * @param columnMap
     *            存储数据信息
     * @param whereInfo
     *            过滤条件
     * @return String 影响记录数
     * @throws AxisFault
     */
    public String updateWithoutTableName(Map < String, Object > columnMap, String whereInfo) throws AxisFault {
        options.setAction("updateWithNotTableName");

        // 创建EndpointReference对象，并设置webservice地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * 准备批量数据 Insert方法每次可以执行N条插入语句 每一条插入语句用Map对象保存 然后再放到List对象里
         */

        List < Map < String, Object > > list = new ArrayList < Map < String, Object > >();
        list.add(columnMap);
        JSONArray ja = new JSONArray(list);
        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 where条件语句 数据访问用户名 表名称
         * 数据访问用户密码
         */
        // 组织Web service调用参数
        Object[] opAddEntryArgs = new Object[] { ja.toString(), new String[] { whereInfo }, userName, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "updateWithNotTableName");

        options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(1000 * 60 * 60));
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(1000 * 60 * 60));
        // String strResult = (String) serviceClient.invokeBlocking(opAddEntry,
        // opAddEntryArgs, classes)[0];

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由执行信息及影响行数组成，通过符号“：”分割 执行信息： 0：对表没有操作权限 1：对表没有查询权限 2：数据访问用户权限已经过期
         * 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    /**
     * 如果服务是视图的话，返回不支持信息
     * 
     * @return
     */
    private String getUnSupportMessage() {
        JSONArray list = new JSONArray();
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("info", "7:" + _NO_OPERATOR_METHOD_MEAN);
        list.put(map);
        return list.toString();
    }

    /**
     * @return
     */
    private String getNoConnectionMessage() {
        JSONArray list = new JSONArray();
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("info", "7:" + _CONNECTION_REASON_MEAN);
        list.put(map);
        return list.toString();
    }

    /**
     * @return
     */
    private String getEndpointNotFoundMessage() {
        JSONArray list = new JSONArray();
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("info", "7:" + _ENDPOINT_NO_FOUND_MEAN);
        list.put(map);
        return list.toString();
    }

    private String processAxisFault(AxisFault e) throws AxisFault {
        String strResult = null;
        if (e.getMessage().contains(_NO_OPERATOR_METHOD)) {
            strResult = getUnSupportMessage();
        } else if (e.getMessage().contains(_CONNECTION_REASON)) {
            strResult = getNoConnectionMessage();
        } else if (e.getMessage().contains(_ENDPOINT_NO_FOUND)) {
            strResult = getEndpointNotFoundMessage();
        } else {
            throw e;
        }
        return strResult;
    }

    public String select(List < String > columnList, String whereInfo) throws AxisFault {
        // 创建RPCServiceClient对象
        RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        Options options = serviceClient.getOptions();

        options.setAction("select");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 数据访问用户密码
         * where条件语句 每页条数（返回记录的条数） 从第几条开始返回
         */
        // 组织Web service调用参数
        // whereInfo = "ID >= 3";
        Object[] opAddEntryArgs = new Object[] { convertArrayList(columnList), tableName, userName, whereInfo, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry2 = new QName("http://ws.apache.org/axis2", "select");

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry2, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由结果集与执行信息，通过符号“，”分割 结果集是由字段名称及字段数值组成，通过符号“：”分割 执行信息： 0：对表没有操作权限
         * 1：对表没有查询权限 2：数据访问用户权限已经过期 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    public String selectOrder(List < String > columnList, String whereInfo, String order) throws AxisFault { // 创建RPCServiceClient对象
        RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        Options options = serviceClient.getOptions();

        options.setAction("selectByOrder");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 数据访问用户密码
         * where条件语句 每页条数（返回记录的条数） 从第几条开始返回
         */
        // 组织Web service调用参数
        // whereInfo = "ID >= 3";
        Object[] opAddEntryArgs = new Object[] { convertArrayList(columnList), userName, whereInfo, MD5Util.MD5Encode(passWord), order };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry2 = new QName("http://ws.apache.org/axis2", "selectByOrder");

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry2, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由结果集与执行信息，通过符号“，”分割 结果集是由字段名称及字段数值组成，通过符号“：”分割 执行信息： 0：对表没有操作权限
         * 1：对表没有查询权限 2：数据访问用户权限已经过期 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }

    public String selectWithoutTableName(List < String > columnList, String whereInfo) throws AxisFault {
        // 创建RPCServiceClient对象
        RPCServiceClient serviceClient = new RPCServiceClient();

        // 通过RPCServiceClient对象，获取Options对象
        Options options = serviceClient.getOptions();

        options.setAction("select");

        // 创建EndpointReference对象，并设置web service地址
        EndpointReference targetEPR = new EndpointReference(wsdlURL);
        options.setTo(targetEPR);

        /*
         * Web service参数顺序不可改变 参看对应WSDL文件 所有参数均为String类型 数据访问用户名 表名称 数据访问用户密码
         * where条件语句 每页条数（返回记录的条数） 从第几条开始返回
         */
        // 组织Web service调用参数
        // whereInfo = "ID >= 3";
        Object[] opAddEntryArgs = new Object[] { convertArrayList(columnList), tableName, userName, whereInfo, MD5Util.MD5Encode(passWord) };

        // 声明返回值类型
        Class[] classes = new Class[] { String.class };

        // 指定要调用Web service方法及WSDL文件的命名空间
        QName opAddEntry2 = new QName("http://ws.apache.org/axis2", "select");

        String strResult = null;
        try {
            strResult = (String) serviceClient.invokeBlocking(opAddEntry2, opAddEntryArgs, classes)[0];
        } catch (AxisFault e) {
            strResult = processAxisFault(e);
        }

        // 关闭服务对象
        serviceClient.cleanup();
        serviceClient = null;

        /*
         * 返回值由结果集与执行信息，通过符号“，”分割 结果集是由字段名称及字段数值组成，通过符号“：”分割 执行信息： 0：对表没有操作权限
         * 1：对表没有查询权限 2：数据访问用户权限已经过期 3：对表的某个字段没有查询权限 4：成功执行 5：数据访问用户密码不正确
         */
        return strResult;
    }
}
