package com.nci.dcs.em.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.em.model.CcDeviceFxry;
import com.nci.dcs.em.service.DeviceFxryService;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;

public class DeviceFxryAction extends BaseAction<CcDeviceFxry>{
	@Autowired
	private DeviceFxryService deviceFxryService;
	@Autowired
	protected FXRYBaseInfoService FXRYBaseInfoservice;
	@Autowired
	protected OrganizationInfoService organizationInfoService;
	//佩戴人员分页显示，某设备历史佩戴人员统计
	public String pdrytj() {
		String deviceNumber = request.getParameter("deviceNumber");
		getRequestPage();
		page.getCriterions().add(Restrictions.eq("deviceNumber", deviceNumber));
		deviceFxryService.searchHistory(page);
		return SUCCESS;
	}
	//佩戴人员分页显示，全局设备历史佩戴人员统计
	public String fxrytj() {
		String indexNumber=request.getParameter("indexNumber");
		String useUnit=request.getParameter("useUnit");
		String useSfsUnit=request.getParameter("useSfsUnit");
		String startTime=request.getParameter("startTime");
		String overTime=request.getParameter("overTime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date overDate=null;
		try {
			if(startTime==null||"null".equals(startTime)||"".equals(startTime)){
				//起始时间为空给它一个较早时间
				startDate=sdf.parse("1980-01-01");
			}else{
				startDate=sdf.parse(startTime);
			};
			if(overTime==null||"null".equals(overTime)||"".equals(overTime)){
				overDate=new Date();
			}else{
				overDate=sdf.parse(overTime);
			};
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 实现搜索单位为区县司法局展示结果为该司法局下的所有单位
		 */
		String supOrg=null;
		String filters = this.getJqgrid().getFilters();
		if (null != filters && filters.contains("\"field\":\"useSfsUnit\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"useSfsUnit\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				supOrg = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(supOrg)) {
					OrganizationInfo org = organizationInfoService.get(supOrg);
					if (org != null && "2".equals(org.getOrgType())) {
						int end=0;
						if(tempFilters[1].indexOf("{")>-1){
							end=tempFilters[1].indexOf("{");
						}else{
							end=tempFilters[1].indexOf("}")+1;
						}
						String newFilters = tempFilters[0].substring(0,tempFilters[0].length()-1)
								+ tempFilters[1].substring(end);
						this.getJqgrid().setFilters(newFilters);
					}else{
						supOrg=null;
					}
				}
			} catch (Exception e) {

			}
		}
		getRequestPage();
		if (!CommonUtils.isNull(supOrg)) {
			OrganizationInfo curOrg = organizationInfoService.get(supOrg);
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			page.getCriterions().add(Restrictions.in("useSfsUnit", ids));
		}
		//实现搜索单位功能结束结束
		if(getCurOrgId().equals(rootOrgId)){
			if(indexNumber.equals("0")){
				page.getCriterions().add(Restrictions.and(Restrictions.le("wearTime",overDate),Restrictions.ge("wearTime", startDate)));
			}
			if(indexNumber.equals("1")){
				page.getCriterions().add(Restrictions.and(Restrictions.le("releaseTime",overDate),Restrictions.ge("releaseTime",startDate)));
			}
			if(useUnit.matches("[0-9]+")){
				page.getCriterions().add(Restrictions.eq("useUnit",useUnit));
			}
			if(useSfsUnit.matches("[0-9]+")){
				page.getCriterions().add(Restrictions.eq("useSfsUnit",useSfsUnit));
			}
		}else{
			if(indexNumber.equals("0")){
				page.getCriterions().add(Restrictions.and(Restrictions.le("wearTime",overDate),Restrictions.ge("wearTime", startDate)));
			}
			if(indexNumber.equals("1")){
				page.getCriterions().add(Restrictions.and(Restrictions.le("releaseTime",overDate),Restrictions.ge("releaseTime", startDate)));
			}
			page.getCriterions().add(Restrictions.eq("useUnit",getCurOrgId()));
			if(useSfsUnit.matches("[0-9]+")){
				page.getCriterions().add(Restrictions.eq("useSfsUnit",useSfsUnit));
			}
		}
		deviceFxryService.searchHistory(page);
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
