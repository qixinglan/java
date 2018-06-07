package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc7;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc14 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name", "accusation",
			"adjustType", "adjustPeriod", "responseOrg", "supResponseOrg" };

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc7 t where t.id=?";
//		fxry_Id="4028839a404caf6b01405c96d0484d3b";
		String[] values = { fxry_Id };
		List<ViewCcDoc7> result = fxryDocExportService.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc14 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc7 doc14 = result.get(0);
			// 具体罪名
			doc14.setAccusation(Encapsulation.getValueToName(doc14.getAccusation(), "accusation"));
			mapViewCcDoc14 = Encapsulation.beanToMap(doc14);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc14.get(s) == null ? "" : mapViewCcDoc14
						.get(s).toString());
			}
			if(!"3".equalsIgnoreCase(doc14.getOrgType())){
				//说明服刑人员所在的机构是 区县局或者市局
				//此时只显示区县局或者市局，上级机构不显示
				root.put("supResponseOrg", "");
			}
			DateObject dateObject=new DateObject();
			dateObject.get_YearTime();
			DateObject.getMapRoot(root, dateObject);
		}	
		return root;
	}
}