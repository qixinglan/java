package com.nci.dcs.system.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.EncodingUtil;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.system.service.ServerLicenseService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

public class SystemLicenseiUtil {

	public final static String SYS_CODE = "sysCode";
	public final static String START_TIME = "startTime";
	public final static String END_TIME = "endTime";
	private static Logger logger = LoggerFactory
			.getLogger(SystemLicenseiUtil.class);

	/**
	 * 系统版本:地市版
	 */
	private static String version = "CITY";

	private static Map<String, String> versions = new HashMap<String, String>();
	/**
	 * 
	 */
	private static String syscode = null;
	/**
	 * 授权开始时间
	 */
	private static Date startTime = null;
	/**
	 * 授权结束时间
	 */
	private static Date endTime = null;
	/**
	 * 是否授权
	 */
	private static boolean hasLicense = false;

	private static List<String> linuxInfo = null;
	private static Date linuxInfoModifyDate = null;

	private static boolean initFlag = false;

	public static synchronized void init() {
		if (!initFlag) {
			syscode = systemCode();
			versions.put("PROVINCE", "全省级版本");
			versions.put("CITY", "地市级版本");
			versions.put("COUNTY", "区县级版本");
			getLicense();
			initFlag = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getLicense() {
		try {
			ServerLicenseService serverLicenseService = (ServerLicenseService) SpringContextUtil
					.getBean("serverLicenseService");
			List<Date> dates = serverLicenseService.serverAuthenticate(syscode);
			if (CommonUtils.isNotNull(dates)) {
				hasLicense = true;
				startTime = dates.get(0);
				endTime = dates.get(1);
			}
		} catch (Exception e) {
			logger.error("获取授权信息失败,认为未授权", e);
		}
	}

	/**
	 * Description:windows下获取主板序列号
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月25日下午2:01:29
	 */
	private static String winBaseBoardSN() {
		String result = "";
		try {
			Process process = Runtime.getRuntime()
					.exec(new String[] { "wmic", "baseboard", "get",
							"SerialNumber" });
			process.getOutputStream().close();
			Scanner scanner = new Scanner(process.getInputStream());
			scanner.next();
			result = scanner.next();
			scanner.close();
		} catch (IOException e) {
			logger.error("获取Windows主板序列号命令出错：", e);
		}
		return result;
	}

	/**
	 * Description:windows下获取CPU列号
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月25日下午2:01:48
	 */
	private static String winCpuSN() {
		String result = "";
		try {
			Process process = Runtime.getRuntime().exec(
					new String[] { "wmic", "cpu", "get", "ProcessorId" });
			process.getOutputStream().close();
			Scanner scanner = new Scanner(process.getInputStream());
			scanner.next();
			result = scanner.next();
			scanner.close();
		} catch (IOException e) {
			logger.error("获取WindowsCPU序列号命令出错：", e);
		}
		return result;
	}

	/**
	 * Description:windows下获取硬盘序列号(xp下无法获取)
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月25日下午2:02:53
	 */
	private static String winDiskDriveSN() {
		String result = "";
		try {
			Process process = Runtime.getRuntime().exec(
					new String[] { "wmic", "diskdrive", "get", "SeriaNumber" });
			process.getOutputStream().close();
			Scanner scanner = new Scanner(process.getInputStream());
			scanner.next();
			result = scanner.next();
			scanner.close();
		} catch (IOException e) {
			logger.error("获取Windows硬盘序列号命令出错：", e);
		}
		return result;
	}

	private static List<String> linuxCpuInfo() {
		LineInputStream lineInputStream = null;
		List<String> list = new ArrayList<String>();
		Process process;
		try {
			process = Runtime.getRuntime().exec("cat /proc/cpuinfo");
			process.getOutputStream().close();
			lineInputStream = new LineInputStream(process.getInputStream());
			while (lineInputStream.available() > 0) {
				list.add(lineInputStream.readLine());
			}
			linuxInfoModifyDate = new Date();
			while (process.waitFor() != 0) {
				Thread.sleep(50);
			}
			process.destroy();
		} catch (IOException e) {
			list = new ArrayList<String>();
			logger.error("获取LinuxCPU信息命令出错：", e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != lineInputStream) {
				try {
					lineInputStream.close();
				} catch (IOException e) {
					logger.error("LinuxCPU信息流关闭出错：", e);
				}
			}
		}
		return list;
	}

	private static synchronized List<String> getLinuxCpuInfo() {
		if (CommonUtils.isNull(linuxInfo)) {
			linuxInfo = linuxCpuInfo();
		} else if (System.currentTimeMillis() - linuxInfoModifyDate.getTime() > 10 * 60 * 1000) {
			linuxInfo = linuxCpuInfo();
		}
		return linuxInfo;
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月25日下午2:04:45
	 */
	private static String linuxBaseBoardSN() {
		StringBuffer buffer = new StringBuffer();
		List<String> contexts = getLinuxCpuInfo();
		for (String context : contexts) {
			buffer.append(context);
			buffer.append("\r\n");
		}
		return EncodingUtil.md5Base64(buffer.toString());
	}

	private static String linuxCpuSN() {
		List<String> contexts = getLinuxCpuInfo();
		return Integer.toString(contexts.hashCode());
	}

	private static String systemCode() {
		String result = null;
		try {
			result = "B:" + baseBoardSN() + "\r\n" + "C:" + cpuSN()
					+ "\r\nVersion:" + version;
			if (!CommonUtils.isNull(result)) {
				result = EncodingUtil.aesEncrypt(result,
						EncodingUtil.encryptKey, EncodingUtil.ivKey);
			}
		} catch (Exception e) {
			logger.error("获取系统特征码失败", e);
		}
		return result;
	}

	public static String getSyscode() {
		return syscode;
	}

	/**
	 * Description:获取操作系统信息
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月26日下午3:32:26
	 */
	public static String sysName() {
		return System.getProperty("os.name");
	}

	/**
	 * Description:获取应用软件版本信息
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月26日下午3:32:39
	 */
	public static String versionName() {
		return versions.get(version);
	}

	/**
	 * Description:查看系统是否已经授权
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月26日下午3:32:03
	 */
	public static boolean hasLicense() {
		return hasLicense;
	}

	/**
	 * Description:查看系统授权是否过期
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月26日下午3:32:15
	 */
	public static boolean licenseTimeOut() {
		if (null != startTime && null != endTime) {
			Date now = new Date();
			if (startTime.before(now) && endTime.after(now)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Description:判断是否与数据库
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月28日上午9:40:40
	 */
	public static boolean dataBaseTimeOut() {
		ServerLicenseService serverLicenseService = (ServerLicenseService) SpringContextUtil
				.getBean("serverLicenseService");
		Date date = serverLicenseService.getDataBaseTime();
		if (Math.abs(System.currentTimeMillis() - date.getTime()) > 24 * 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	public static String baseBoardSN() {
		String sysName = sysName();
		String result = "";
		if (sysName.contains("Windows")) {
			result = winBaseBoardSN();
			logger.error("WinBaseBoard:" + result);
		} else if (sysName.contains("Linux")) {
			result = linuxBaseBoardSN();
			logger.error("LinuxBaseBoard:" + result);
		}
		return result;
	}

	public static String cpuSN() {
		String sysName = sysName();
		String result = "";
		if (sysName.contains("Windows")) {
			result = winCpuSN();
			logger.error("WinCPU:" + result);
		} else if (sysName.contains("Linux")) {
			result = linuxCpuSN();
			logger.error("LinuxCPU:" + result);
		}
		return result;
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月27日下午2:06:16
	 */
	public static String licenseCreate(String baseBoardCode, String cpuCode,
			String version, Date start, Date end) {
		String result = null;
		try {
			String baseBoard = EncodingUtil.aesDecrypt(baseBoardCode,
					EncodingUtil.encryptKey, EncodingUtil.ivKey);
			String cpu = EncodingUtil.aesDecrypt(cpuCode,
					EncodingUtil.encryptKey, EncodingUtil.ivKey);
			result = "B:" + baseBoard + "\r\n" + "C:" + cpu + "\r\nVersion:"
					+ version;
			if (!CommonUtils.isNull(result)) {
				result = EncodingUtil.aesEncrypt(result,
						EncodingUtil.encryptKey, EncodingUtil.ivKey);
				result = result + "\r\nStart:"
						+ DateTimeFmtSpec.getDateFormat().format(start)
						+ "\r\nEnd:"
						+ DateTimeFmtSpec.getDateFormat().format(end);
				result = EncodingUtil.aesEncrypt(result,
						EncodingUtil.encryptKey, EncodingUtil.ivKey);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	public static Map<String, String> licenseDecode(String authoriseCode) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String temp = EncodingUtil.aesDecrypt(authoriseCode,
					EncodingUtil.encryptKey, EncodingUtil.ivKey);
			if (temp.contains("\r\nStart:") && temp.contains("\r\nEnd:")) {
				String key = temp.substring(0, temp.indexOf("\r\nStart:"));
				String start = temp.substring(temp.indexOf("\r\nStart:") + 8,
						temp.indexOf("\r\nEnd:"));
				String end = temp.substring(temp.indexOf("\r\nEnd:") + 6);
				map.put(SYS_CODE, key);
				Date startTime = DateTimeFmtSpec.getDateFormat().parse(start);
				Date endTime = DateTimeFmtSpec.getDateFormat().parse(end);
				map.put(START_TIME, EncodingUtil.aesEncrypt(DateTimeFmtSpec
						.getDateFormat().format(startTime),
						EncodingUtil.encryptKey, EncodingUtil.ivKey));
				map.put(END_TIME, EncodingUtil.aesEncrypt(DateTimeFmtSpec
						.getDateFormat().format(endTime),
						EncodingUtil.encryptKey, EncodingUtil.ivKey));
			}
		} catch (Exception e) {
			map = new HashMap<String, String>();
			logger.error("解析授权码失败", e);
		}
		return map;
	}

	public static void main(String[] args) {
		String start = "2015-08-27", end = "2016-12-31";
		try {
			String ss = licenseCreate(
					"cOAuFxcWf7k7sEjdDJBu1MJuji/teB8GClXu4O3IEwI=",
					"4yUYlu7tqlg2z7zDoUgb4OBQx8rXT0LKzzTUHj/ROwY=", "CITY", DateTimeFmtSpec
							.getDateFormat().parse(start), DateTimeFmtSpec
							.getDateFormat().parse(end));
			System.out.println(ss);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
