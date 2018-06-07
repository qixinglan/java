package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc7;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDocDefault implements IFxryDocHandler {

	@Override
	public Map<String, String> Execute(String fxry_Id,
			FXRYDocExportService fxryDocExportService) {
		//默认handler只是查询服刑人员姓名，然后经空word下载下来
		Map<String, String> root = new HashMap<String, String>();
		String sql = "from ViewCcDoc7 t where t.id=?";
		String[] valuses = { fxry_Id };
		List<ViewCcDoc7> result = fxryDocExportService.getByFxryId(sql, valuses);
		if(result!=null&&result.size()>0){
			ViewCcDoc7 entity=result.get(0);
			root.put("name", entity.getName()==null?"":entity.getName());
		}
		return root;
	}

}
