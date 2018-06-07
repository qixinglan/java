package com.nci.dcs.jzgl.sync.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.sync.model.ViewCcFxrySyncInfo;
import com.nci.dcs.jzgl.sync.service.ViewCcFxrySyncinfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;

@SuppressWarnings("serial")
public class ViewSyncAction extends BaseAction<ViewCcFxrySyncInfo> {
	@Autowired
	private ViewCcFxrySyncinfoService viewCcFxrySyncinfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private AjaxFormResult ajaxFormResult;

	@Override
	public String list() throws Throwable {
		String filters = this.getJqgrid().getFilters();
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				String orgId = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(orgId)) {
					OrganizationInfo org = organizationInfoService.get(orgId);
					if (org != null && "2".equals(org.getOrgType())) {
						String newFilters = tempFilters[0]
								+ "\"field\":\"supOrgId\"" + tempFilters[1];
						this.getJqgrid().setFilters(newFilters);
					}
				}
			} catch (Exception e) {

			}
		}
		viewCcFxrySyncinfoService.findPaged(getRequestPage());
		return SUCCESS;
	}

	public String fxrysSync() throws Throwable {
		try {
			viewCcFxrySyncinfoService.fxrySync();
			ajaxFormResult = AjaxFormResult.success("");
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error("");
		}
		return SUCCESS;
	}
	public String fxrySync() throws Throwable {
		try {
			String fxryId=request.getParameter("fxryId");
			String fxryIds=request.getParameter("fxryIds");
			if(!CommonUtils.isNull(fxryId)){
				viewCcFxrySyncinfoService.fxrySync(fxryId);
				ajaxFormResult = AjaxFormResult.success("");
			}if(!CommonUtils.isNull(fxryIds)){
				for(String fxry:fxryIds.split(",")){
					viewCcFxrySyncinfoService.fxrySync(fxry);
				}
				ajaxFormResult = AjaxFormResult.success("");
			}else {
				ajaxFormResult = AjaxFormResult.error("未获取对应的人员ID");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error("");
		}
		return SUCCESS;
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
