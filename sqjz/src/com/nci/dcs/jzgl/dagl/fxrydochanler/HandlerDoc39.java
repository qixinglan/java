package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc34;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc39 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name", "sex", "birth_Date",
			"identityCard", "houseAddress", "homeAddress",
			"accusation", "oriPunishment", "dateS_Ori","dateE_Ori",
			"adjustType","adjustPeriod","responseOrg","death_Date","deathReason",
			"date_Judgment","trialUnit"};

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc34 t where t.fxryId=?";
		String[] values = { fxry_Id };
		List<ViewCcDoc34> result = fxryDocExportService
				.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcdoc39 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc34 doc39 = result.get(0);
			// 出生日期
			if (doc39.getBirthdate() != null) {
				doc39.setBirth_Date(Encapsulation.dataToChinese(doc39
						.getBirthdate()));
			}
			// 原判刑期起止时间
			if (doc39.getDateSOri() != null) {
				doc39.setDateS_Ori(Encapsulation.dataToChinese(doc39
						.getDateSOri()));
			}
			if (doc39.getDateEOri() != null) {
				doc39.setDateE_Ori(Encapsulation.dataToChinese(doc39
						.getDateEOri()));
			}
			// 矫正起止时间
			if (doc39.getDateSAdjust() != null) {
				doc39.setDateS_Adjust(Encapsulation.dataToChinese(doc39
						.getDateSAdjust()));
			}
			if (doc39.getDateEAdjust() != null) {
				doc39.setDateE_Adjust(Encapsulation.dataToChinese(doc39
						.getDateEAdjust()));
			}
			// 审判时间
			if (doc39.getDateJudgment() != null) {
				doc39.setDate_Judgment(Encapsulation.dataToChinese(doc39
						.getDateJudgment()));
			}
			// 死亡日期 
			if (doc39.getDeathDate() != null) {
				doc39.setDeath_Date(Encapsulation.dataToChinese(doc39
						.getDeathDate()));
			}
			// 具体罪名
			doc39.setAccusation(Encapsulation.getValueToName(
					doc39.getAccusation(), "accusation"));
			mapViewCcdoc39 = Encapsulation.beanToMap(doc39);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcdoc39.get(s) == null ? "" : mapViewCcdoc39
						.get(s).toString());
			}
		}
		return root;
	}
}