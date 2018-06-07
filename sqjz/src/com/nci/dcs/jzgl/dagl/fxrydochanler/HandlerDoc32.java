package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc32;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public class HandlerDoc32  implements IFxryDocHandler {
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> Execute(String fxryId,FXRYDocExportService fxryDocExportService) {
		Map<String, String> root = new HashMap<String, String>();
		String sql = "From ViewCcDoc32 where id='"+fxryId+"'";
		List<ViewCcDoc32> resList = fxryDocExportService.getByFxryId(sql);
		ViewCcDoc32 info = resList.get(0);
		String[] bda = Encapsulation.dataToArray(info.getBirthdate());//生日
		String[] sda = Encapsulation.dataToArray(info.getDateJudgment());//判决日期
		
		//获取罪名
		String accusation = Encapsulation.getValueToName(info.getAccusation(), "accusation");
		String addpunishment = Encapsulation.getValueToName(info.getAddpunishment(), "addpunishment");
		String p = info.getProvince()==null?"":info.getProvince();
		String c = info.getCity()==null?"":info.getCity();
		String city = p;
		if(!p.equals(c)){
			city = p+c;
		}
		String rmfy = info.getTrialUnit()==null?"":info.getTrialUnit();
		if(rmfy.endsWith("人民法院")){
			rmfy = rmfy.substring(0,rmfy.indexOf("人民法院"));
		}
		root.put("name", info.getName());
		root.put("sex", info.getSex()==null?"":info.getSex());
		root.put("nation", info.getNation());
		root.put("bdaYear", bda==null?"":bda[0]);
		root.put("bdaMonth", bda==null?"":bda[1]);
		root.put("bdaDay", bda==null?"":bda[2]);
		root.put("city", city);//省市
		root.put("county", info.getCounty()==null?"":info.getCounty());
		root.put("educationDegree", info.getEducationDegree());
		root.put("homeAddress", info.getHomeAddress());
		root.put("houseAddress", info.getHouseAddress());
		root.put("accusation", accusation);
		root.put("rmfy", rmfy);//人民法院
		root.put("sdaYear", sda==null?"":sda[0]);
		root.put("sdaMonth", sda==null?"":sda[1]);
		root.put("sdaDay", sda==null?"":sda[2]);
		root.put("judgmentNumber", info.getJudgmentNumber()==null?"":info.getJudgmentNumber());//判决书编号
		root.put("oriPunishment", info.getOriPunishment()==null?"":info.getOriPunishment());
		root.put("addpunishment", addpunishment);
		root.put("jxcs", "  ");
		root.put("jxYear", "  ");
		root.put("jxMonth", "  ");
		root.put("sjxYear", "  ");
		root.put("sjxMonth", "  ");
		root.put("sjxDay", "  ");
		root.put("ejxYear", "  ");
		root.put("ejxMonth", "  ");
		root.put("ejxDay", "  ");
		root.put("zjw", "  ");
		root.put("zjwYear", "  ");
		root.put("zjwMonth", "  ");
		root.put("zjwDay", "  ");
		root.put("szjwYear", "  ");
		root.put("szjwMonth", "  ");
		root.put("szjwDay", "  ");
		root.put("ezjwYear", "  ");
		root.put("ezjwMonth", "  ");
		root.put("ezjwDay", "  ");
		root.put("reason", "  ");
		root.put("jtss", "  ");
		root.put("ssxzT", "  ");
		root.put("ssxzX", "  ");
		root.put("jyzxd", "  ");
		root.put("stgYear", "  ");
		root.put("stgMonth", "  ");
		root.put("stgDay", "  ");
		root.put("etgYear", "  ");
		root.put("etgMonth", "  ");
		root.put("etgDay", "  ");
		root.put("xfT", "  ");
		root.put("ssfT", "  ");
		root.put("ssfK", "  ");
		root.put("nowDate", Encapsulation.dataToChinese(new Date()));
		root.put("clJ", "  ");
		root.put("clC", "  ");
		root.put("clY", "  ");
		root.put("wsbh", info.getWsbh()==null?"（    ）字第   号":info.getWsbh());
		return root;
	}
}
