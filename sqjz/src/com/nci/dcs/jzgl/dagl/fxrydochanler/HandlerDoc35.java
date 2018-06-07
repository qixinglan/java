package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc35;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc35 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name", "writNumber", "writType",
			"adjustType", "dateS_Adjust", "dateE_Adjust", "opinion",
			"adjustCode"};
	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		String sql = "from ViewCcDoc35 t where t.id=?";
		String[] values = { fxry_Id };
		List<ViewCcDoc35> result = fxryDocExportService
				.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc35 = new HashMap<String, Object>();
		if (result != null && result.size() > 0) {
			ViewCcDoc35 doc35 = result.get(0);
			// 矫正起止时间
			if (doc35.getDateSAdjust() != null) {
				doc35.setDateS_Adjust(Encapsulation.dataToChinese(doc35
						.getDateSAdjust()));
			}
			if (doc35.getDateEAdjust() != null) {
				doc35.setDateE_Adjust(Encapsulation.dataToChinese(doc35
						.getDateEAdjust()));
			}
			//矫正类型
			if(doc35.getAdjustCode()!=null){
				String code=doc35.getAdjustCode();
				if("1".equalsIgnoreCase(code)){
					doc35.setAdjustCode("管制期满，依法解除管制");
				}else if("2".equalsIgnoreCase(code)){
					doc35.setAdjustCode("缓刑考验期满，原判刑罚不再执行");
				}else if("3".equalsIgnoreCase(code)){
					doc35.setAdjustCode("假释考验期满，原判刑罚执行完毕");
				}
			}
			mapViewCcDoc35 = Encapsulation.beanToMap(doc35);
			for (String s : CONTEXT) {
				root.put(s, mapViewCcDoc35.get(s) == null ? "" : mapViewCcDoc35
						.get(s).toString());
			}
		}
		return root;
	}
}