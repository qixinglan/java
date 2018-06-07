package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

public class ViewFXRYExecuteInfoAction extends BaseAction<ViewFXRYExecuteInfo>{
	@Autowired
	protected  ViewFXRYExecuteInfoService service;
	
	@Autowired
	private OrganizationInfoService organizationInfoService;
	
	private void orgCriterions(Page<ViewFXRYExecuteInfo> page,Boolean isTgry) {
		Bmb org = getCurOrg();
		Criterion crit = null;
		if (org.isQxsfj()) {
			OrganizationInfo curOrg = organizationInfoService.get(org.getBm());
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			crit = Restrictions.in("responseOrg", ids);
		} else if (org.isSfs()) {
			// 司法所按负责单位查询
			crit = Restrictions.eq("responseOrg", org.getBm());
		}
		if (crit != null) {
			page.getCriterions().add(crit);
		}
		if(!isTgry){
			page.getCriterions().add(Restrictions.ne("state", FXRYState.JJ));
			page.getCriterions().add(Restrictions.ne("state", FXRYState.LJ));
		}
	}

	@Override
	public String list() throws Throwable {
		String deviceCode = null;
		String supOrg=null;
		String filters = this.getJqgrid().getFilters();
		if (null != filters && filters.contains("\"field\":\"deviceCode\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"deviceCode\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				deviceCode = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(deviceCode)) {
					int end=0;
					if(tempFilters[1].indexOf("{")>-1){
						end=tempFilters[1].indexOf("{");
					}else{
						end=tempFilters[1].indexOf("}")+1;
					}
					String newFilters = tempFilters[0].substring(0,tempFilters[0].length()-1)
							+ tempFilters[1].substring(end);
					this.getJqgrid().setFilters(newFilters);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		filters = this.getJqgrid().getFilters();
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"responseOrg\"");
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
		Page<ViewFXRYExecuteInfo> page = this.getRequestPage();
		String showTgry=request.getParameter("showTgry");
		if("true".equals(showTgry)&&(Constants.IS.equals(getUser().getIsws())||"3".equals(getUser().getIsws()))){
			//是否查看特管人员并检查用户是否有权限，"3"代表更高级权限，新加的数值，（拥有查看并且赋予其他人查看权限）
			page.getCriterions().add(Restrictions.eq("isTgry",Constants.IS));
			orgCriterions(page, true);
		}else{
			page.getCriterions().add(Restrictions.eq("isTgry",Constants.FS));
			orgCriterions(page, false);
		}
		if (!CommonUtils.isNull(deviceCode)) {
			if ("2".equals(deviceCode)) {
				page.getCriterions().add(Restrictions.isNull("deviceCode"));
			} else {
				page.getCriterions().add(Restrictions.isNotNull("deviceCode"));
			}
		}
		if (!CommonUtils.isNull(supOrg)) {
			OrganizationInfo curOrg = organizationInfoService.get(supOrg);
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			page.getCriterions().add(Restrictions.in("responseOrg", ids));
		}
		try {
			service.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String xbdryList()throws Exception {
		Page<ViewFXRYExecuteInfo> page = this.getRequestPage();
		orgCriterions(page,false);
		//检查用户是否有查看特管人员权限
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		page.getCriterions().add(Restrictions.isNull("excuteId"));
		try {
			service.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
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
		return "add";
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

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return "success";
	}
}
