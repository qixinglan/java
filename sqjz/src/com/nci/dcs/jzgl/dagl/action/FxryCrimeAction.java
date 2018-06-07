package com.nci.dcs.jzgl.dagl.action;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.FxryCrime;
import com.nci.dcs.jzgl.dagl.service.FxryCrimeService;

@SuppressWarnings("serial")
public class FxryCrimeAction extends BaseAction<FxryCrime> {
	@Autowired
	private FxryCrimeService fxryCrimeService;
	private AjaxFormResult ajaxFormResult;

	@Override
	@SuppressWarnings("unchecked")
	public String list() throws Throwable {
		String fxryId = request.getParameter("fxryId");
		Page<FxryCrime> page = getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryid", fxryId));
		fxryCrimeService.findPaged(page);
		return SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		try{
			if (!StringUtils.isBlank(id)){
				this.entity = fxryCrimeService.get(id);
			}
			return SUCCESS;
		}
		catch (Exception e){
			return NONE;
		}
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		int deleted = 0;
		String lastId = "";
		try {
			String idsParam = this.request.getParameter("ids");
			if (idsParam != null && !idsParam.isEmpty()) {
				String ids[] = idsParam.split(",");
				for (String id : ids) {
					lastId = id;
					fxryCrimeService.delete(id.trim());
					++deleted;
				}
				ajaxFormResult = AjaxFormResult
						.success(String.valueOf(deleted));
			} else {
				ajaxFormResult = AjaxFormResult.error("无删除对象！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(lastId);
		}
		return SUCCESS;
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
		try {
			entity.setRecordOrgId(getCurOrgId());
			if (StringUtils.isBlank(entity.getId())) {
				entity.setId(null);
			}
			fxryCrimeService.update(entity);
			ajaxFormResult = AjaxFormResult.success(entity.getId());
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
