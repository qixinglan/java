package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.misc.BASE64Encoder;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc7;
import com.nci.dcs.jzgl.dagl.model.FXRYResumeInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYSocialRelations;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc7 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "currentOrg", "code", "tbrq",
			"name", "userdName", "identityCard", "sex", "nation", "birth_Date",
			"educationDegree", "health", "politicsStatusOriginal",
			"maritalState", "picture", "houseAddress", "homeAddress", "workNuit",
			"workNuitPhone", "phoneNumber", "accusation", "oriPunishment", "oriPeriod",
			"decideUnit", "orgdetentionAddress", "forbidden",
			"adjustType", "adjustPeriod","wssdsj", "writType",
			"receiveType", "report_Time", "reportCode_1", "reportCode_2",
			"reportCode_3", "majorCrime", "criminalPunshment" };

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc7 t where t.id=?";
		String[] valuses = { fxry_Id };
		Map<String, String> root = new HashMap<String, String>();
		// 查询服刑人员简历
		String sql_Resume = "from FXRYResumeInfo t where t.fxryId=? order by t.startTime desc";
		List<FXRYResumeInfo> listResume = fxryDocExportService.getByFxryId(sql_Resume, valuses);
		if (listResume != null && listResume.size() > 0) {
			for (int i = 0; i < listResume.size(); i++) {
				Date ds=listResume.get(i).getStartTime();
				Date de=listResume.get(i).getEndTime();
				String workUnit=listResume.get(i).getWorkUnit();
				String job=listResume.get(i).getJob();
				root.put("resume" + i + "_stime", (ds==null?"":ds)+"");
				root.put("resume" + i + "_etime", (de==null?"":de)+"");
				root.put("resume" + i + "_unit", (workUnit==null||"".equalsIgnoreCase(workUnit)?"":workUnit));
				root.put("resume" + i + "_job", (job==null||"".equalsIgnoreCase(job)?"":job));
			}
		}
		// 查询服刑人员家庭成员和社会关系
		String sql_Social = "from FXRYSocialRelations t where t.fxryId=?";
		List<FXRYSocialRelations> listSocial = fxryDocExportService
				.getByFxryId(sql_Social, valuses);
		if (listSocial != null && listSocial.size() > 0) {
			for (int i = 0; i < listSocial.size(); i++) {
				String name=listSocial.get(i).getName();
				String relation=listSocial.get(i).getRelation();
				String address=listSocial.get(i).getAddress();
				String phoneNumber=listSocial.get(i).getPhoneNumber();
				root.put("social" + i + "_name", (name==null||"".equalsIgnoreCase(name)?"":name));
				root.put("social" + i + "_relation", (relation==null||"".equalsIgnoreCase(relation)?"":relation));
				root.put("social" + i + "_address", (address==null||"".equalsIgnoreCase(address)?"":address));
				root.put("social" + i + "_phoneNumber", (phoneNumber==null||"".equalsIgnoreCase(phoneNumber)?"":phoneNumber));
			}
		}
		// 查询服刑人员信息
		List<ViewCcDoc7> result = fxryDocExportService
				.getByFxryId(sql, valuses);
		Map<String, Object> mapViewCcDoc7 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc7 doc7 = result.get(0);
			//填表日期
			doc7.setTbrq(Encapsulation.dataToChinese(new Date()));
			String reportInfo = doc7.getReportCode();
			if (reportInfo != null && reportInfo.length() > 0) {
				if ("1".equalsIgnoreCase(reportInfo)) {
					doc7.setReportCode_1("是");
					doc7.setReportCode_2("否");
					doc7.setReportCode_3("否");
				} else if ("2".equalsIgnoreCase(reportInfo)) {
					doc7.setReportCode_1("否");
					doc7.setReportCode_2("是");
					doc7.setReportCode_3("否");
				} else if ("3".equalsIgnoreCase(reportInfo)) {
					doc7.setReportCode_1("否");
					doc7.setReportCode_2("否");
					doc7.setReportCode_3("是");
				}
			}

			DateObject dateObject=new DateObject();
			// 矫正起止时间
			dateObject.getAdjustFromToTime(doc7.getDateSAdjust(), doc7.getDateEAdjust());
			// 禁令起止时间
			dateObject.getForbiddenFromToTime(doc7.getDateSForbidden(), doc7.getDateEForbidden());
			// 出生日期
			if (doc7.getBirthdate() != null) {
				doc7.setBirth_Date(Encapsulation.dataToChinese(doc7
						.getBirthdate()));
			}
			// 报道时间
			if (doc7.getReportTime() != null) {
				doc7.setReport_Time(Encapsulation.dataToChinese(doc7
						.getReportTime()));
			}
			// 具体罪名
			doc7.setAccusation(Encapsulation.getValueToName(
					doc7.getAccusation(), "accusation"));

			mapViewCcDoc7 = Encapsulation.beanToMap(doc7);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc7.get(s) == null ? "" : mapViewCcDoc7
						.get(s).toString());
			}
			DateObject.getMapRoot(root, dateObject);
			//读取照片
			if(doc7.getPicture()!=null){
				BASE64Encoder encoder = new BASE64Encoder();
				root.put("picture", encoder.encode(doc7.getPicture()));
			}
		}
		return root;
	}
}
