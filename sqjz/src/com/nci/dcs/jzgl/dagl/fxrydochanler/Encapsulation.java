package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtilsBean;

public class Encapsulation {

	public static final String[] sansType = { " ", "涉毒", "涉黑", "涉枪" };

	public static final String[] addpunishment = { " ", "罚金", "剥夺政治权利", "没收财产",
			"驱逐出境" };
	// 是否四史
	public static final String[] sisType = { " ", "吸毒史", "脱逃史", "自杀史", "袭警史" };
	// 三类犯罪
	public static final String[] slfzType = { " ", "职务犯罪", "金融犯罪 ", "涉黑" };
	// 纠正成员最信息
	public static final String[] groupInfo = { " ", "司法所工作人员", "社会工作者", "志愿者",
			"村（居）委会人员", "所在单位人员", "就读学校人员", "家庭成员或监护人", "保证人", "其他", "公安民警" };
	// 具体罪名
	public static final Map<String, Map<String, String>> ACCUSATION = new HashMap<String, Map<String, String>>();

	public static final Map<String, String[]> dirt = new HashMap<String, String[]>();

	public static Map<String, String> docMapNames = new HashMap<String, String>();
	static {
		dirt.put("sansType", sansType);
		dirt.put("addpunishment", addpunishment);
		dirt.put("sisType", sisType);
		dirt.put("slfzType", slfzType);
		dirt.put("groupInfo", groupInfo);
	}

	// value 从数据库中查询出的字典值，例如：1,2,3
	// mc-字典名称，只有以下几个参数：
	// sansType 三涉
	// addpunishment，附加罪
	// sisType，是否四史
	// slfzType，三类犯罪
	// groupInfo， 纠正成员最信息
	// accusation 具体罪名
	public static String getValueToName(String value, String mc) {
		String result = "";
		if (value != null && value.length() > 0) {
			String[] array = value.split(",");
			if ("accusation".equalsIgnoreCase(mc)) {
				Map<String, String> dir = ACCUSATION.get("JTZM");
				for (String s : array) {
					result += dir.get(s) + " ";
				}
			} else {
				String[] value_array = dirt.get(mc);
				for (String s : array) {
					result += value_array[new Integer(s)] + " ";
				}
			}
		}
		return result;
	}

	/**
	 * date日期转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dataToChinese(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			return sdf.format(date).toString();
		}
		return "";
	}

	/**
	 * date日期转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dataToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date).toString();
		}
		return "";
	}

	/**
	 * date日期转换成字符串数组
	 * 
	 * @param date
	 * @return
	 */
	public static String[] dataToArray(Date date) {
		String[] dateArray = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateTemp = sdf.format(date).toString();
			dateArray = dateTemp.split("-");
		}
		return dateArray;
	}

	// 实现将javabean转成Map
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean
					.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".endsWith(name)) {
					params.put(name,
							propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	public static void PropertiesToMap()  {
		InputStream in = null;
		try {
			Properties properties = new Properties();

			try {
				in = new BufferedInputStream(new FileInputStream(
						Encapsulation.class.getResource("/").getPath()
								+ "docNames.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			docMapNames = new HashMap<String, String>((Map) properties);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
