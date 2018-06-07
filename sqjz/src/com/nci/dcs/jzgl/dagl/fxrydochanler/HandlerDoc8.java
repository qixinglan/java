package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc8;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;


public class HandlerDoc8 implements IFxryDocHandler {
	public static final String[] CONTEXT = { "name", "cname", "address","person", "phone"};
	@Override
	public Map<String, String> Execute(String fxry_Id,FXRYDocExportService fxryDocExportService) {
		String sql="from ViewCcDoc8 t where t.fxryId=?";
		String[] values={fxry_Id};
		List<ViewCcDoc8> result =fxryDocExportService.getByFxryId(sql, values);
		Map<String, String> root = new HashMap<String, String>();
		Map<String, Object> mapViewCcDoc8 = new HashMap<String, Object>();
		if(result!=null&&result.size()>0){
			ViewCcDoc8 doc8=result.get(0);
			DateObject dateObject=new DateObject();
			//转换报道时间
			dateObject.get_ReportTime(doc8.getReportTime());
			mapViewCcDoc8 = Encapsulation.beanToMap(doc8);
			for(String s:CONTEXT){
				root.put(s, mapViewCcDoc8.get(s) == null ? "" : mapViewCcDoc8
						.get(s).toString());
			}
			DateObject.getMapRoot(root, dateObject);
			
		}
		return root;
	}
}