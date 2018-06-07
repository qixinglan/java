package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc7;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDocBaseInfo implements IFxryDocHandler {
	public static final String[] CONTEXT = { "currentOrg", "code", "tbrq",
			"name", "userdName", "identityCard", "sex", "nation", "birth_Date",
			"educationDegree", "health", "politicsStatusOriginal",
			"maritalState", "picture", "houseAddress", "homeAddress",
			"workNuit", "workNuitPhone", "phoneNumber", "accusation",
			"oriPunishment", "oriPeriod", "addpunishment","decideUnit", "orgdetentionAddress",
			"forbidden", "adjustType", "adjustPeriod", "wssdsj", "writType",
			"groupInfo", "receiveType", "report_Time", "reportCode_1",
			"reportCode_2", "reportCode_3", "majorCrime", "criminalPunshment",
			"responseOrg" };

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc7 t where t.id=?";
//		fxry_Id="4028839a4244768b014447c7cc1c03c1";
		String[] values = { fxry_Id };
		List<ViewCcDoc7> result = fxryDocExportService.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc7 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc7 doc7 = result.get(0);
			// 出生日期
			if (doc7.getBirthdate() != null) {
				doc7.setBirth_Date(Encapsulation.dataToString(doc7
						.getBirthdate()));
			}
			DateObject dateObject = new DateObject();
			// 禁令时间(_年_月_日)
			dateObject.getForbiddenTime(doc7.getDateSForbidden(),
					doc7.getDateEForbidden());
			// 禁令起止日(自*年*月*日 至 *年*月*日)
			dateObject.getForbiddenFromToTime(doc7.getDateSForbidden(),
					doc7.getDateEForbidden());
			// 矫正时间(_年_月_日)
			dateObject.getAdjustTime(doc7.getDateSAdjust(),
					doc7.getDateEAdjust());
			// 矫正起止日(自*年*月*日 至 *年*月*日)
			dateObject.getAdjustFromToTime(doc7.getDateSAdjust(),
					doc7.getDateEAdjust());
			// 纠正组成员信息
			doc7.setGroupInfo(Encapsulation.getValueToName(doc7.getGroupInfo(),
					"groupInfo"));
			// 具体罪名
			doc7.setAccusation(Encapsulation.getValueToName(
					doc7.getAccusation(), "accusation"));
			mapViewCcDoc7 = Encapsulation.beanToMap(doc7);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc7.get(s) == null ? "" : mapViewCcDoc7
						.get(s).toString());
			}
			root = DateObject.getMapRoot(root, dateObject);
		}
		return root;
	}
}