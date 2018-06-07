package com.nci.dcs.data.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.nci.dcs.common.utils.CommonUtils;

/**
 * 一些常用的工具方法
 * 
 * @author tzhli
 * 
 */
public class DateUtils {

	public static final String YYYYMMDD = "yyyy-MM-dd";
	private static DateUtils CU;

	public static DateUtils getInstance() {
		return CU;
	}

	/**
	 * 日期转换为字符串，格式为：yyyy-MM
	 * 
	 * @param date
	 * @return String
	 * @throws Exception
	 */
	public static String FullDateToString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			return sdf.format(date);
		}
		return "";
	}

	public static String timeInMillisToString(Long mills) {
		if (mills != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
			return sdf.format(new Date(mills));
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
			SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
			return sdf.format(date).toString();
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
		return StringToDate(str, "yyyy-MM");
	}

	/**
	 * 将字符串转成日期
	 * 
	 * @param str
	 * @param pattern
	 * @return Date
	 */
	public static Date StringToDate(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (!CommonUtils.isNull(str)) {
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public static int getDaysBetween(Date beginDate, Date endDate) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(beginDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(endDate);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);

		}
		return days;
	}

	public static void clearHours(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	public static Date getMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		clearHours(calendar);
		if (date.before(calendar.getTime())) {
			calendar.add(Calendar.DAY_OF_YEAR, -7);
		}
		return calendar.getTime();
	}

	public static Date getLastMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getMonday(date));
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		return calendar.getTime();
	}

	public static Date getFirstDayForMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		clearHours(calendar);
		return calendar.getTime();
	}

	public static Date getFirstDayForLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFirstDayForMonth(date));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
}
