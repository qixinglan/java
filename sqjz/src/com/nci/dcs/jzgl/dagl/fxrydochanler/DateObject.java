package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateObject {
	public static final String[] TIME_CONTENT = { "dateS_Forbidden_Y",
			"dateS_Forbidden_M", "dateS_Forbidden_D", "dateE_Forbidden_Y",
			"dateE_Forbidden_M", "dateE_Forbidden_D", "dateS_Adjust_Y",
			"dateS_Adjust_M", "dateS_Adjust_D", "dateE_Adjust_Y",
			"dateE_Adjust_M", "dateE_Adjust_D", "reportTime_Y", "reportTime_M",
			"reportTime_D", "adjustFromTo" ,"forbiddenFromTo","year"};
	public static final String[] YEAR = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};

	// 矫正起止日
	private String adjustFromTo;
	// 矫正起(自 *年*月*日)
	private String adjustFrom;
	// 矫正止(至 *年*月*日)
	private String adjustTo;

	// 禁令起止日
	private String forbiddenFromTo;
	// 禁令起
	private String forbiddenFrom;
	// 禁令止
	private String forbiddenTo;

	// 禁令开始时间-年
	private String dateS_Forbidden_Y;
	// 禁令开始时间-月
	private String dateS_Forbidden_M;
	// 禁令开始时间-日
	private String dateS_Forbidden_D;
	// 禁令结束时间-年
	private String dateE_Forbidden_Y;
	// 禁令结束时间-月
	private String dateE_Forbidden_M;
	// 禁令结束时间-日
	private String dateE_Forbidden_D;
	// 矫正开始时间-年
	private String dateS_Adjust_Y;
	// 矫正开始时间-月
	private String dateS_Adjust_M;
	// 矫正开始时间-日
	private String dateS_Adjust_D;
	// 矫正结束时间-年
	private String dateE_Adjust_Y;
	// 矫正结束时间-月
	private String dateE_Adjust_M;
	// 矫正结束时间-日
	private String dateE_Adjust_D;
	// 报道时间-年
	private String reportTime_Y;
	// 报道时间-月
	private String reportTime_M;
	// 报道时间-日
	private String reportTime_D;
	
	//年份
	private String year;


	// 获取禁令开始--结束时间
	public DateObject getForbiddenTime(Date dateSForbidden, Date dateEForbidden) {
		if (dateSForbidden != null) {
			String[] array = Encapsulation.dataToArray(dateSForbidden);
			this.setDateS_Forbidden_Y(array[0]);
			this.setDateS_Forbidden_M(array[1]);
			this.setDateS_Forbidden_D(array[2]);
		} else {
			this.setDateS_Forbidden_Y("");
			this.setDateS_Forbidden_M("");
			this.setDateS_Forbidden_D("");

		}
		// 禁令结束时间
		if (dateEForbidden != null) {
			String[] array = Encapsulation.dataToArray(dateEForbidden);
			this.setDateE_Forbidden_Y(array[0]);
			this.setDateE_Forbidden_M(array[1]);
			this.setDateE_Forbidden_D(array[2]);
		} else {
			this.setDateE_Forbidden_Y("");
			this.setDateE_Forbidden_M("");
			this.setDateE_Forbidden_D("");

		}
		return this;

	}

	// 获取矫正开始--结束时间
	public DateObject getAdjustTime(Date dateSAdjust, Date dateEAdjust) {
		if (dateSAdjust != null) {
			String[] array = Encapsulation.dataToArray(dateSAdjust);
			this.setDateS_Adjust_Y(array[0]);
			this.setDateS_Adjust_M(array[1]);
			this.setDateS_Adjust_D(array[2]);

		} else {
			this.setDateS_Adjust_Y("");
			this.setDateS_Adjust_M("");
			this.setDateS_Adjust_D("");

		}
		// 矫正结束时间
		if (dateEAdjust != null) {
			String[] array = Encapsulation.dataToArray(dateEAdjust);
			this.setDateE_Adjust_Y(array[0]);
			this.setDateE_Adjust_M(array[1]);
			this.setDateE_Adjust_D(array[2]);

		} else {
			this.setDateE_Adjust_Y("");
			this.setDateE_Adjust_M("");
			this.setDateE_Adjust_D("");

		}
		return this;

	}

	// 获取禁令起止日
	public DateObject getForbiddenFromToTime(Date dateSForbidden,
			Date dateEForbidden) {
		if (dateSForbidden != null) {
			this.setForbiddenFrom("自 "+ Encapsulation.dataToChinese(dateSForbidden) + " ");
		} else {
			this.setForbiddenFrom("");
		}

		if (dateEForbidden != null) {
			this.setForbiddenTo("至 "
					+ Encapsulation.dataToChinese(dateEForbidden));
		} else {
			this.setForbiddenTo("");
		}
		this.setForbiddenFromTo(this.getForbiddenFrom() + this.getForbiddenTo());
		return this;

	}

	public DateObject getAdjustFromToTime(Date dateSAdjust, Date dateEAdjust) {
		// 矫正起止日
		if (dateSAdjust != null) {
			this.setAdjustFrom("自 " + Encapsulation.dataToChinese(dateSAdjust)+ " ");
		} else {
			this.setAdjustFrom("");
			;
		}

		if (dateEAdjust != null) {
			this.setAdjustTo("至 " + Encapsulation.dataToChinese(dateEAdjust));
		} else {
			this.setAdjustTo("");
		}
		this.setAdjustFromTo(this.getAdjustFrom() + this.getAdjustTo());
		return this;

	}

	// 获取报道时间
	public DateObject get_ReportTime(Date reportTime) {
		if (reportTime != null) {
			String[] array = Encapsulation.dataToArray(reportTime);
			this.setReportTime_Y(array[0]);
			this.setReportTime_M(array[1]);
			this.setReportTime_D(array[2]);

		} else {
			this.setReportTime_Y("");
			this.setReportTime_M("");
			this.setReportTime_D("");
		}
		return this;

	}
	
	// 获取年度
		public DateObject get_YearTime() {
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			String s=sdf.format(new Date()).toString();
			char[] array=s.toCharArray();
			if(array!=null&&array.length>=2){
				char c1=array[0];
				char c2=array[1];
				this.setYear(YEAR[new Integer(new Character(c1).toString())]+YEAR[new Integer(new Character(c2).toString())]);
			}
			return this;

		}

	// 实现将dateObject中的时间对象赋值到root中
	public static Map<String, String> getMapRoot(Map<String, String> root,
			DateObject dateObject) {
		Map<String, Object> mapObject = Encapsulation.beanToMap(dateObject);
		for (String s : TIME_CONTENT) {
			root.put(s, mapObject.get(s) == null ? "" : mapObject.get(s)
					.toString());
		}
		return root;
	}

	public String getDateS_Forbidden_Y() {
		return dateS_Forbidden_Y;
	}

	public void setDateS_Forbidden_Y(String dateS_Forbidden_Y) {
		this.dateS_Forbidden_Y = dateS_Forbidden_Y;
	}

	public String getDateS_Forbidden_M() {
		return dateS_Forbidden_M;
	}

	public void setDateS_Forbidden_M(String dateS_Forbidden_M) {
		this.dateS_Forbidden_M = dateS_Forbidden_M;
	}

	public String getDateS_Forbidden_D() {
		return dateS_Forbidden_D;
	}

	public void setDateS_Forbidden_D(String dateS_Forbidden_D) {
		this.dateS_Forbidden_D = dateS_Forbidden_D;
	}

	public String getDateE_Forbidden_Y() {
		return dateE_Forbidden_Y;
	}

	public void setDateE_Forbidden_Y(String dateE_Forbidden_Y) {
		this.dateE_Forbidden_Y = dateE_Forbidden_Y;
	}

	public String getDateE_Forbidden_M() {
		return dateE_Forbidden_M;
	}

	public void setDateE_Forbidden_M(String dateE_Forbidden_M) {
		this.dateE_Forbidden_M = dateE_Forbidden_M;
	}

	public String getDateE_Forbidden_D() {
		return dateE_Forbidden_D;
	}

	public void setDateE_Forbidden_D(String dateE_Forbidden_D) {
		this.dateE_Forbidden_D = dateE_Forbidden_D;
	}

	public String getDateS_Adjust_Y() {
		return dateS_Adjust_Y;
	}

	public void setDateS_Adjust_Y(String dateS_Adjust_Y) {
		this.dateS_Adjust_Y = dateS_Adjust_Y;
	}

	public String getDateS_Adjust_M() {
		return dateS_Adjust_M;
	}

	public void setDateS_Adjust_M(String dateS_Adjust_M) {
		this.dateS_Adjust_M = dateS_Adjust_M;
	}

	public String getDateS_Adjust_D() {
		return dateS_Adjust_D;
	}

	public void setDateS_Adjust_D(String dateS_Adjust_D) {
		this.dateS_Adjust_D = dateS_Adjust_D;
	}

	public String getDateE_Adjust_Y() {
		return dateE_Adjust_Y;
	}

	public void setDateE_Adjust_Y(String dateE_Adjust_Y) {
		this.dateE_Adjust_Y = dateE_Adjust_Y;
	}

	public String getDateE_Adjust_M() {
		return dateE_Adjust_M;
	}

	public void setDateE_Adjust_M(String dateE_Adjust_M) {
		this.dateE_Adjust_M = dateE_Adjust_M;
	}

	public String getDateE_Adjust_D() {
		return dateE_Adjust_D;
	}

	public void setDateE_Adjust_D(String dateE_Adjust_D) {
		this.dateE_Adjust_D = dateE_Adjust_D;
	}

	public String getReportTime_Y() {
		return reportTime_Y;
	}

	public void setReportTime_Y(String reportTime_Y) {
		this.reportTime_Y = reportTime_Y;
	}

	public String getReportTime_M() {
		return reportTime_M;
	}

	public void setReportTime_M(String reportTime_M) {
		this.reportTime_M = reportTime_M;
	}

	public String getReportTime_D() {
		return reportTime_D;
	}

	public void setReportTime_D(String reportTime_D) {
		this.reportTime_D = reportTime_D;
	}

	public String getAdjustFrom() {
		return adjustFrom;
	}

	public void setAdjustFrom(String adjustFrom) {
		this.adjustFrom = adjustFrom;
	}

	public String getAdjustTo() {
		return adjustTo;
	}

	public void setAdjustTo(String adjustTo) {
		this.adjustTo = adjustTo;
	}

	public String getAdjustFromTo() {
		return adjustFromTo;
	}

	public void setAdjustFromTo(String adjustFromTo) {
		this.adjustFromTo = adjustFromTo;
	}

	public String getForbiddenFromTo() {
		return forbiddenFromTo;
	}

	public void setForbiddenFromTo(String forbiddenFromTo) {
		this.forbiddenFromTo = forbiddenFromTo;
	}

	public String getForbiddenFrom() {
		return forbiddenFrom;
	}

	public void setForbiddenFrom(String forbiddenFrom) {
		this.forbiddenFrom = forbiddenFrom;
	}

	public String getForbiddenTo() {
		return forbiddenTo;
	}

	public void setForbiddenTo(String forbiddenTo) {
		this.forbiddenTo = forbiddenTo;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}

// public Date getReportTime() {
// return reportTime;
// }
//
// public void setReportTime(Date reportTime) {
// this.reportTime = reportTime;
// }

// 禁令开始时间
// private Date dateSForbidden;
// 禁令结束时间
// private Date dateEForbidden;
// 矫正开始时间
// private Date dateSAdjust;
// 矫正结束时间
// private Date dateEAdjust;
// 报道时间
// private Date reportTime;

// public Date getDateSForbidden() {
// return dateSForbidden;
// }
//
// public void setDateSForbidden(Date dateSForbidden) {
// this.dateSForbidden = dateSForbidden;
// }
//
// public Date getDateEForbidden() {
// return dateEForbidden;
// }
//
// public void setDateEForbidden(Date dateEForbidden) {
// this.dateEForbidden = dateEForbidden;
// }
//
// public Date getDateSAdjust() {
// return dateSAdjust;
// }
//
// public void setDateSAdjust(Date dateSAdjust) {
// this.dateSAdjust = dateSAdjust;
// }
//
// public Date getDateEAdjust() {
// return dateEAdjust;
// }
//
// public void setDateEAdjust(Date dateEAdjust) {
// this.dateEAdjust = dateEAdjust;
// }