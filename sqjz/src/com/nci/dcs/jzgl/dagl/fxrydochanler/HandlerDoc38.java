package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc38;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc38 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name", "sex", "birth_Date",
			"identityCard", "accusation", "oriPunishment", "dateS_Ori",
			"dateE_Ori", "trialUnit", "houseAddress", "homeAddress",
			"adjustType", "dateS_Adjust", "dateE_Adjust", "date_Judgment",
			"writNumber", "writType", "remove_Date" };

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc38 t where t.id=?";
		String[] values = { fxry_Id };
		List<ViewCcDoc38> result = fxryDocExportService
				.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc38 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc38 doc38 = result.get(0);
			// 出生日期
			if (doc38.getBirthdate() != null) {
				doc38.setBirth_Date(Encapsulation.dataToChinese(doc38
						.getBirthdate()));
			}
			// 原判刑期起止时间
			if (doc38.getDateSOri() != null) {
				doc38.setDateS_Ori(Encapsulation.dataToChinese(doc38
						.getDateSOri()));
			}
			if (doc38.getDateEOri() != null) {
				doc38.setDateE_Ori(Encapsulation.dataToChinese(doc38
						.getDateEOri()));
			}
			// 矫正起止时间
			if (doc38.getDateSAdjust() != null) {
				doc38.setDateS_Adjust(Encapsulation.dataToChinese(doc38
						.getDateSAdjust()));
			}
			if (doc38.getDateEAdjust() != null) {
				doc38.setDateE_Adjust(Encapsulation.dataToChinese(doc38
						.getDateEAdjust()));
			}
			// 审判时间
			if (doc38.getDateJudgment() != null) {
				doc38.setDate_Judgment(Encapsulation.dataToChinese(doc38
						.getDateJudgment()));
			}
			// 脱矫时间
			if (doc38.getRemoveDate() != null) {
				doc38.setRemove_Date(Encapsulation.dataToChinese(doc38
						.getRemoveDate()));
			}
			// 具体罪名
			doc38.setAccusation(Encapsulation.getValueToName(
					doc38.getAccusation(), "accusation"));
			mapViewCcDoc38 = Encapsulation.beanToMap(doc38);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc38.get(s) == null ? "" : mapViewCcDoc38
						.get(s).toString());
			}
		}
		return root;
	}
}