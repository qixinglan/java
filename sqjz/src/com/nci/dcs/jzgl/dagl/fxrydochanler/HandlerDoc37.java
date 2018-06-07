package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc36;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc37 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name","remove_Date", "houseAddress"};
	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc36 t where t.id=?";
		String[] values = { fxry_Id };
		List<ViewCcDoc36> result = fxryDocExportService
				.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc36 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc36 doc36 = result.get(0);
			//脱矫日期
			if(doc36.getRemoveDate()!=null){
				doc36.setRemove_Date(Encapsulation.dataToChinese(doc36.getRemoveDate()));
			}
			mapViewCcDoc36 = Encapsulation.beanToMap(doc36);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc36.get(s) == null ? "" : mapViewCcDoc36
						.get(s).toString());
			}
		}
		return root;
	}
}