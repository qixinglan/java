package com.nci.dcs.data.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.page.JsonTree;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.data.model.CcGatherAlarmIgnore;
import com.nci.dcs.data.model.CcIgnoreOrg;
import com.nci.dcs.data.model.CcIgnorePerson;
import com.nci.dcs.data.service.DzwlService;
import com.nci.dcs.data.service.JjszSevice;
import com.nci.dcs.official.dto.PersonsKeyValue;
import com.nci.dcs.official.service.OrganizationInfoService;

public class JjszAction extends BaseAction<CcGatherAlarmIgnore> {

	public String result;
	private String JgMc;

	public List<CcGatherAlarmIgnore> list;
	@Autowired
	public JjszSevice jjszSevice;
	@Autowired
	public DzwlService dzwlService;
	@Autowired
	public OrganizationInfoService OrgService;

	public List datas = new ArrayList();

	public String getJgMc() {
		return JgMc;
	}

	public void setJgMc(String jgMc) {
		JgMc = jgMc;
	}

	public String jjbjsz() throws Throwable {
		setJgMc(getCurOrg().getMc());
		request.setAttribute("qxj", getCurOrg().isQxsfj());
		return "success";
	}

	public String qxxz() {

		return SUCCESS;
	}

	public String getData() {

		this.list = jjszSevice.FindByJgId(getCurOrgId());
		return SUCCESS;
	}

	public String getdzwl() {
		String parms = request.getQueryString();
		String fxryid;
		try {
			fxryid = URLDecoder.decode(parms.split("=")[1], "UTF-8");
			result = dzwlService.GetDZWL(fxryid);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	public String deleteData() {
		String ignoreId = request.getParameter("ignoreId");
		try {
			jjszSevice.delete(ignoreId);
			this.result = "success";

		} catch (Exception e) {
			this.result = ERROR + ":" + e.getMessage();
		}
		return SUCCESS;
	}

	public String postData() {
		String jgIds = request.getParameter("JgIds");
		String beginDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String jgMc = request.getParameter("jgMc");
		String ignoreId = request.getParameter("ignoreId");
		if (ignoreId != null && !ignoreId.isEmpty()) {
			jjszSevice.delete(ignoreId);
			// entity=new CcGatherAlarmIgnore();
		}

		try {
			entity.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(beginDate));
			entity.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(endDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setOrgId(getCurOrg().getBm());
		entity.setOrgName(getCurOrg().getMc());
		// this.getCurOrg().getMc()
		entity.setIgnoreId(CommonUtils.uuid());
		if (jgIds != null && !jgIds.isEmpty()) {
			Set<CcIgnoreOrg> ccIgnoreOrgs = new HashSet<CcIgnoreOrg>();
			String[] arrJg = jgIds.split(",");
			String[] arrJgmc = jgMc.split(",");
			CcIgnoreOrg org;
			for (int i = 0; i < arrJg.length; i++) {
				org = new CcIgnoreOrg();
				org.setId(CommonUtils.uuid());
				org.setIgnoreId(entity.getIgnoreId());
				org.setOrgId(arrJg[i]);
				org.setOrgName(arrJgmc[i]);
				org.setCcGatherAlarmIgnore(entity);
				ccIgnoreOrgs.add(org);

			}
			entity.setCcIgnoreOrgs(ccIgnoreOrgs);
		}
		try {
			jjszSevice.create(entity);
			this.result = "success:" + entity.getIgnoreId();

		} catch (Exception e) {
			this.result = "failure&" + e.getMessage();
		}

		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public String getfxryList() throws Throwable {

		String cCode = this.getCurOrgId();
		String type = request.getParameter("type");
		if (type.equalsIgnoreCase("3")) {
			datas = this.jjszSevice.getfxryData(cCode);
		} else {
			datas = this.OrgService.findJsonByParentId(cCode);
			for(int i=0;i<datas.size();i++){
				JsonTree d=(JsonTree) datas.get(i);
				if(!("3".equals(d.getAttr().getDm())||
						"2".equals(d.getAttr().getDm()))){
					datas.remove(i);
					i--;
				}
			}
		}
		return "success";
	}

	public String getfxryCmbox() {
		try {

			String cCode = this.getCurOrgId();
			datas = this.jjszSevice.getfxryData(cCode);
			List<PersonsKeyValue> lst = new ArrayList<PersonsKeyValue>();
			if (datas != null && datas.size() > 0) {
				for (Object item : datas) {
					PersonsKeyValue p = new PersonsKeyValue();
					Object[] arr = (Object[]) item;
					p.setCode((String) arr[0]);
					p.setName((String) arr[1]);
					p.setUsing(true);
					lst.add(p);
				}
			}
			Map<String, List<PersonsKeyValue>> map = new HashMap<String, List<PersonsKeyValue>>();
			map.put("0", lst);
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(map);
			this.response.getWriter().write(result.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "none";
	}
	public String deletefxry() {
		String gid=request.getParameter("gid");
		try {
			
		//fxryid = URLDecoder.decode(parms.split("=")[1], "UTF-8");
		   result=dzwlService.DeleteFxry(gid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getfxrypolyon() {
		
		String fxryid=request.getParameter("fxryid");
		try {
			
		//fxryid = URLDecoder.decode(parms.split("=")[1], "UTF-8");
		   result=dzwlService.GetFXRYXX(fxryid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getfxrydzwl() {
			
		String fxryid=request.getParameter("fxryid");
		try {
			result=dzwlService.GetFxrydzwl(fxryid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String convertpoint() {
		String x=request.getParameter("x");
		String y=request.getParameter("y");
		result=dzwlService.ConvertPoint(x, y);
		return SUCCESS;
	}
	public String Convert54ToWgs84() {
		String x=request.getParameter("x");
		String y=request.getParameter("y");
		result=dzwlService.Convert54ToWgs84(x, y);
		return SUCCESS;
	}
	
	public String convertpoints() {
		String strpoints=request.getParameter("points");
		result=dzwlService.ConvertPoints(strpoints);
		return SUCCESS;
	}
	public String savefxry() {
		String fxryid=request.getParameter("fxryid");
		String type=request.getParameter("type");
		String strPoints=request.getParameter("points");
		String[] points=strPoints.split(",");
		double[] coordinates=new double[points.length];
		for (int i = 0; i < points.length; i++) {
			coordinates[i]=Double.parseDouble(points[i]);
		}
		try {
			boolean r=dzwlService.SaveFxry(fxryid, type, coordinates);
			result="true";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="false";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="false";
		}
		return SUCCESS;
	}
	public String addfxry() {
		String personIds = request.getParameter("personIds");
		String startTime = request.getParameter("startDate");
		String endTime = request.getParameter("endDate");
		String personNames = request.getParameter("personNames");
		String ignoreId = request.getParameter("ignoreId");
		if (ignoreId != null && !ignoreId.isEmpty()) {
			jjszSevice.delete(ignoreId);
			// entity=new CcGatherAlarmIgnore();
		}
		jjszSevice.find();
		try {
			entity.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(startTime));
			entity.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm")
					.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setOrgId(getCurOrg().getBm());
		entity.setOrgName(getCurOrg().getMc());
		entity.setIgnoreId(UUID.randomUUID().toString().replace("-", ""));
		if (personIds != null && !personIds.isEmpty()) {
			Set<CcIgnorePerson> ccIgnorePersons = new HashSet<CcIgnorePerson>();
			String[] arrPerson = personIds.split(",");
			String[] arrPersonName = personNames.split(",");
			CcIgnorePerson fxry;
			for (int i = 0; i < arrPerson.length; i++) {
				fxry = new CcIgnorePerson();
				fxry.setIgnoreId(entity.getIgnoreId());
				fxry.setPersonId(arrPerson[i]);
				fxry.setPersonName(arrPersonName[i]);
				fxry.setCcGatherAlarmIgnore(entity);
				ccIgnorePersons.add(fxry);
			}
			entity.setCcIgnorePersons(ccIgnorePersons);
		}
		try {
			jjszSevice.create(entity);
			this.result = "success:" + entity.getIgnoreId();

		} catch (Exception e) {
			this.result = "failure&" + e.getMessage();
		}
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}
}
