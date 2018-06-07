package com.nci.dcs.jzgl.education.action;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.jzgl.education.model.EducationInfo;
import com.nci.dcs.jzgl.education.service.EducationInfoService;
import com.nci.dcs.system.model.User;

public class EducationInfoAction extends BaseAction<EducationInfo>{

	private static final long serialVersionUID = 3162969649970950452L;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	@Autowired
	private EducationInfoService service;
	
	@Override
	public String list() throws Throwable {
		Log.info("");
		return SUCCESS;
	}
	
	public String getEducationsInitData() {
		User user = getUser();
		this.entity.setTime(new Date());
		this.entity.setPersonName(user.getName());
		this.entity.setCreateTime(DateTimeFmtSpec.getDateFormat().format(new Date()));
		return SUCCESS;
	}
	
	
	/**
	 * @name 获取列表信息
	 * @return
	 * @author caolj
	 * @date 2015年4月1日 下午4:23:20
	 * @message:
	 */
	public String getData() {
		this.getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", request.getParameter("fxryId")));
		service.findPaged(page);
		return SUCCESS;
	}
	
	/**
	 * 查看
	 */
	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		this.entity = service.get(id);
		return SUCCESS;
	}
	
	/**
	 * 保存
	 */
	@Override
	public String add() throws Throwable {
		String id = entity.getId();
		if(id == null || "".equals(id)){
			entity.setId(null);
		}
		entity.setCreater(getUserId());
		entity.setCreatedate(new Date());
		entity.setOrgId(getCurOrgId());
		service.create(entity);
		ajaxFormResult = AjaxFormResult.success(entity.getId());
		return SUCCESS;
	}

	public String addMore() throws Throwable {
		try {
			String ids = request.getParameter("ids");
			if (!CommonUtils.isNull(ids)) {
				String[] fxryIds = ids.split(",");
				Date time = entity.getTime();
				String bz = entity.getBz();
				String type = entity.getType();
				String sfcy = entity.getSfcy();
				String org = getCurOrgId();
				String user = getUserId();
				Date now = new Date();
				for (String fxryId : fxryIds) {
					EducationInfo temp= new EducationInfo();
					temp.setTime(time);
					temp.setType(type);
					temp.setSfcy(sfcy);
					temp.setBz(bz);
					temp.setCreater(user);
					temp.setCreatedate(now);
					temp.setOrgId(org);
					temp.setFxryId(fxryId);
					service.create(temp);
				}
				ajaxFormResult = AjaxFormResult.success("success");
			} else {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行集中教育！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 */
	@Override
	public String delete() throws Throwable {
		String id = request.getParameter("id");
		service.delete(id);
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
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
