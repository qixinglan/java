package com.nci.dcs.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.id.Hex;
import org.apache.commons.id.uuid.UUID;
import org.apache.struts2.ServletActionContext;

/**
 * 一些常用的工具方法
 * 
 * @author tzhli
 * 
 */
public class CommonUtils {
	private static Properties PROP;
	private static CommonUtils CU;

	private CommonUtils() {
		InputStream is = this.getClass().getResourceAsStream(
				"/system.properties");
		PROP = new Properties();
		try {
			PROP.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		if (null == PROP) {
			CU = new CommonUtils();
		}
		String value = (String) PROP.get(key);
		return value;
	}

	public static void setProperty(String key, String value) {
		try {
			if (null == PROP) {
				CU = new CommonUtils();
			}
			FileOutputStream fos = new FileOutputStream(ServletActionContext
					.getServletContext().getRealPath("/WEB-INF/classes/system.properties"));
			PROP.setProperty(key, value);
			PROP.store(fos, "system");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static CommonUtils getInstance() {
		return CU;
	}

	/**
	 * 获得主键uuid
	 * 
	 * @return
	 */
	public static String uuid() {
		return new String(Hex.encodeHex(UUID.randomUUID().getRawBytes()));
	}
	

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return boolean true：为空；false：不为空
	 */
	public static boolean isNull(String str) {
		if (null != str && !"".equals(str) && !"".equals(str.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断map是否为空
	 * 
	 * @author Jennie
	 * @since 2012-7-30
	 * @param map
	 * @return boolean true：为空；false：不为空
	 */
	public static boolean isEmptyMap(Map<String, Object> map){
		boolean r = true;
		if(null != map){
			for(Entry entry : map.entrySet()){
				if(null != entry.getValue() && !"".equals(entry.getValue().toString().trim()) && !"0".equals(entry.getValue().toString().trim())){
					r = false;
					break;
				}
			}
		}
		
		return r;
	}
	
	public static boolean isNull(List list){
		return (list == null || list.size() == 0);
	}
	
	public static boolean isNotNull(List list){
		return (list != null && list.size() > 0);
	}
	
	public static boolean isNotNull(Set set){
		return (set != null && set.size() > 0);
	}
	
	public static boolean isNotNull(Map map){
		return (map != null && map.size() > 0);
	}

	/**
	 * 日期转换为字符串，格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 * @throws Exception
	 */
	public static String FullDateToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转换为字符串，格式为：yyyy-MM-dd
	 * 
	 * @param date
	 * @return String
	 * @throws Exception
	 */
	public static String DateToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return "";
	}

	public static String TimeToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 将字符串转成日期
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date StringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!isNull(str)) {
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 将字符串转成日期(年月日)
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date StringToShortDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!isNull(str)) {
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	private static String formatDate(String date) {
		if (!CommonUtils.isNull(date)) {
			String format = "";
			String[] str = date.split("-");
			if (str.length > 0) {
				for (String s : str) {
					if (!CommonUtils.isNull(format)) {
						format += "-";
					}
					format += (s.length() > 1 ? s : ("0" + s));
				}
				return format;
			}
		}
		return null;
	}

	public static Date StringToDate(String str, Integer lx) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat fullsdf = new SimpleDateFormat("yy-MM-dd HH:mm");
		if (!CommonUtils.isNull(str)) {
			if (lx == 4) {
				try {
					return sdf.parse(formatDate(str.replace("/", "-")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (lx == 5) {
				String[] date = str.replace("/", "-").split(" ");
				String format = formatDate(date[0]);
				if (date.length == 2) {
					format += " " + date[1];
				} else {
					format += " 00:00:00";
				}

				try {
					return fullsdf.parse(format);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files.clone().length != 0) {
					for (File f : files) {
						deleteFile(f);
					}
					file.delete();
				} else {
					file.delete();
				}
			} else {
				file.delete();
			}
		}
	}

	/**
	 * 获取路径
	 * 
	 * @return String
	 */
	public static String getRealPath() {
		String spath = Thread.currentThread().getContextClassLoader()
				.getResource("//").toString();
		String s = spath.substring(6);
		return s;
	}

	/**
	 * 将单引号替换成两个单引号
	 * 
	 * @param str
	 * @return String
	 */
	public static String replaceMarks(String str) {

		String newstr = str.replace("\'", "\'\'");
		newstr = newstr.replace("_", "!_");
		return newstr;
	}
	
	/**
	 * 将双引号替换成单引号
	 * 
	 * @param str
	 * @return String
	 */
	public static String replaceMarksdan(String str) {

		String newstr = str.replace("\"", "\'");
		return newstr;
	}

	/**
	 * 将字符串做 SHA1摘要
	 * 
	 * @param message
	 * @return 摘要值十六进制转换后的字符串
	 */
	public static String getPassword(String message) {
		if (null == message) {
			return null;
		}
		java.security.MessageDigest alga = null;
		try {
			alga = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
		alga.update(message.getBytes());
		// 计算SHA-1值
		byte[] digesta = alga.digest();

		// 将字节数组转换成十六进制数 Hex 类在 commons-codec-1.3.jar中
		Hex hex = new Hex();
		// System.out.println(digesta);
		String mm = new String(hex.encodeHex(digesta));
		// System.out.println(mm);
		return mm;
//		return mm.substring(0,35);
	}

	public static void main(String[] args) {
		System.out.print(CommonUtils.getProperty("dept"));
		System.out.println(CommonUtils.getProperty("dept"));
	}

	/**
	 * 转换操作类型
	 * 
	 * @param type
	 * @return
	 */
	public static String getOperType(int type) {
		String str = "";
		switch (type) {
		case Constants.LOG_OPER_ADD:
			str = "新增";
			break;
		case Constants.LOG_OPER_MODIFY:
			str = "更新";
			break;
		case Constants.LOG_OPER_DELETE:
			str = "删除";
			break;
		case Constants.LOG_OPER_SELECT:
			str = "查询";
			break;
		}
		return str;
	}
	
	/**
	 * 验证字符串中是否包含汉字
	 * @param str
	 * @return
	 */
	public static boolean regexCC(String str){
		String regex = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regex);
		return p.matcher(str).find();
	}
	/**
	 * 将Java bean 中String类型的字段的值进行trim去空格
	 * 
	 * @param obj
	 */
	public static void qckg(Object obj) {
		Field[] fs = obj.getClass().getDeclaredFields();
		for (Field f : fs) {
			if (f.getType().getName().equals("java.lang.String")) {
				try {
					Method m = obj.getClass().getMethod(
							"get" + f.getName().substring(0, 1).toUpperCase()
									+ f.getName().substring(1));
					Object o = m.invoke(obj);
					if (o != null) {
						Method m1 = obj.getClass().getMethod(
								"set"
										+ f.getName().substring(0, 1)
												.toUpperCase()
										+ f.getName().substring(1),
								new Class[] { o.getClass() });
						m1.invoke(obj, o.toString().trim());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void copy(Object obj, Object object) throws Exception {
		Field[] fieldobject = object.getClass().getDeclaredFields();
		Field[] fieldob = obj.getClass().getDeclaredFields();
		fieldobject[0].getType();
		for (int i = 0; i < fieldobject.length; i++) {
			String objectname = fieldobject[i].getName();
			String objDataType = (fieldobject[i].getGenericType()).toString();
			for (int j = 0; j < fieldob.length; j++) {
				String objname = fieldob[j].getName();
				String objectDataType = (fieldob[j].getGenericType())
						.toString();
				if (objname.equals(objectname)
						&& objDataType.equals(objectDataType)) {
					if(!Modifier.isFinal(fieldobject[i].getModifiers())){
						fieldobject[i].setAccessible(true);
						fieldob[j].setAccessible(true);
						Object objs = fieldobject[i].get(object);
						fieldob[j].set(obj, objs);
					}
				}
			}
		}
	}

	/**
	 * java bean 之间的赋值拷贝不拷贝null值
	 * 
	 * @param obj
	 *            目标bean
	 * @param object
	 *            源bean
	 * */
	public static void copyWithOutNull(Object obj, Object object)
			throws Exception {
		Field[] fieldobject = object.getClass().getDeclaredFields();
		Field[] fieldob = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fieldobject.length; i++) {
			//排除final类型的属性,无法赋值会报错
			if(!Modifier.isFinal(fieldobject[i].getModifiers())){
				String objectname = fieldobject[i].getName();
				String objDataType = (fieldobject[i].getGenericType()).toString();
				for (int j = 0; j < fieldob.length; j++) {
					String objname = fieldob[j].getName();
					String objectDataType = (fieldob[j].getGenericType())
							.toString();
					if (objname.equals(objectname)
							&& objDataType.equals(objectDataType)) {
						fieldobject[i].setAccessible(true);
						fieldob[j].setAccessible(true);
						Object objs = fieldobject[i].get(object);
						if (objs != null) {
							fieldob[j].set(obj, objs);
						}
					}
				}
			}
		}
	}

	/**
	 * java bean 内属性赋值为null或者0
	 * 
	 * @param obj
	 *            目标bean
	 * */
	public static void setNull(Object obj) throws Exception {
		Field[] fieldob = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fieldob.length; j++) {
			String objectDataType = (fieldob[j].getGenericType()).toString();
			fieldob[j].setAccessible(true);
			if ("int".equals(objectDataType) || "double".equals(objectDataType)
					|| "float".equals(objectDataType)) {
				fieldob[j].set(obj, 0);
			} else {
				fieldob[j].set(obj, null);
			}
		}
	}
}
