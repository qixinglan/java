package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.AddressSelect;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYTransferInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryReadyReleaseInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryTempoutprionExtendService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.rcbdgl.service.ViewCCFxryReportInfoService;
import com.nci.dcs.jzgl.rcbdgl.service.ViewFxryVacateinfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

/**
 * Description:日常工作提醒中获取各项提醒数量的action
 *
 * @author Shuzz
 * @since 2014年10月22日上午9:41:59
 */
@SuppressWarnings("serial")
public class DailyWorkRemindAction extends BaseAction<AddressSelect> {
	@Autowired
	private ViewFXRYExecuteInfoService viewFXRYExecuteInfoService;
	@Autowired
	private ViewFXRYTransferInfoService viewFXRYTransferInfoService;
	@Autowired
	private ViewFxryTempoutprionExtendService viewFxryTempoutprionExtendService;
	@Autowired
	private ViewFxryReadyReleaseInfoService viewFxryReadyReleaseInfoService;
	@Autowired
	private ViewFxryVacateinfoService viewFxryVacateinfoService;
	@Autowired
	private ViewCCFxryReportInfoService viewCCFxryReportInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	private Map<String, Integer> result;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void calculateCount(String type, List<String> ids, Bmb org) {
		Page p = new Page(5);
		List c = new ArrayList();
		p.setCriterions(c);
		if ("xbdry".equals(type)) {
			if (org.isQxsfj()) {
				p.getCriterions().add(Restrictions.in("responseOrg", ids));
			} else if (org.isSfs()) {
				// 司法所按负责单位查询
				p.getCriterions().add(
						Restrictions.eq("responseOrg", org.getBm()));
			}
			p.getCriterions().add(Restrictions.ne("state", FXRYState.JJ));
			p.getCriterions().add(Restrictions.isNull("excuteId"));
			viewFXRYExecuteInfoService.findPaged(p);
		} else if ("dzrry".equals(type)) {
			p.getCriterions().add(Restrictions.eq("transtatus", 0));
			p.getCriterions().add(
					Restrictions.eq("receiveOrgId", getCurOrgId()));
			p.getCriterions().add(Restrictions.eq("state", FXRYState.ZC));
			viewFXRYTransferInfoService.findPaged(p);
		} else if ("zjwxbry".equals(type)) {
			if (org.isSfs()) {
				p.getCriterions().add(
						Restrictions.eq("responseOrg", org.getBm()));
			} else if (org.isQxsfj()) {
				p.getCriterions().add(Restrictions.eq("supOrgId", org.getBm()));
			}
			viewFxryTempoutprionExtendService.findPaged(p);
		} else if ("yjjry".equals(type)) {
			if (org.isSfs()) {
				p.getCriterions().add(
						Restrictions.eq("responseOrg", org.getBm()));
			} else if (org.isQxsfj()) {
				p.getCriterions().add(Restrictions.eq("supOrgId", org.getBm()));
			}
			viewFxryReadyReleaseInfoService.findPaged(p);
		} else if ("bdtxry".equals(type)) {
			if (org.isQxsfj()) {
				p.getCriterions().add(Restrictions.in("orgid", ids));
			} else if (org.isSfs()) {
				p.getCriterions().add(Restrictions.eq("orgid", org.getBm()));
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 3);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			p.getCriterions().add(
					Restrictions.or(Restrictions.isNull("reportdate"),
							Restrictions.le("reportdate", calendar.getTime())));
			viewCCFxryReportInfoService.findPaged(p);
		} else if ("xjtxry".equals(type)) {
			if (org.isQxsfj()) {
				p.getCriterions().add(Restrictions.in("recordOrgId", ids));
			} else if (org.isSfs()) {
				// 司法所按负责单位查询
				p.getCriterions().add(
						Restrictions.eq("recordOrgId", org.getBm()));
			}
			p.getCriterions().add(Restrictions.isNull("reportDate"));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			p.getCriterions().add(
					Restrictions.le("endDate", calendar.getTime()));
			viewFxryVacateinfoService.findPaged(p);
		}
		result.put(type, p.getTotalCount());
	}

	public String getCount() throws Throwable {
		try{
			String types = request.getParameter("type");
			if (result == null) {
				result = new HashMap<String, Integer>();
			}
			Bmb org = getCurOrg();
			List<String> ids = new ArrayList<String>();
			if (org.isQxsfj()) {
				OrganizationInfo curOrg = organizationInfoService.get(org.getBm());
				Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
				for (OrganizationInfo item : suborgs) {
					ids.add(item.getOrgId());
				}
				ids.add(curOrg.getOrgId());
			}
			for (String type : types.split(",")) {
				if (!CommonUtils.isNull(type)) {
					calculateCount(type, ids, org);
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
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

	public Map<String, Integer> getResult() {
		return result;
	}

	public void setResult(Map<String, Integer> result) {
		this.result = result;
	}

}
