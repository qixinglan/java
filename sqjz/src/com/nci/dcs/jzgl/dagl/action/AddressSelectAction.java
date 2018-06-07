package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageRequest;
import com.nci.dcs.jzgl.dagl.model.Address;
import com.nci.dcs.jzgl.dagl.model.AddressSelect;
import com.nci.dcs.jzgl.dagl.service.AddressService;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.AddressSelectService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;


public class AddressSelectAction extends BaseAction<AddressSelect>{
	
	@Autowired
	AddressSelectService service;
	
	@Autowired
	AddressService addressService;
	
	private AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	List<AddressSelect> result;
	
	@Override
	public String list() throws Throwable {
		String pid = request.getParameter("pid");
		String level = request.getParameter("level");
		if (!StringUtils.isBlank(level) && level.equals("2")){
			//如果获取市数据
			AddressSelect parent = service.get(pid);
			if (parent != null && parent.getZxs() != null && parent.getZxs().intValue() == 1){
				result = new ArrayList<AddressSelect>();
				result.add(parent);
			}
		}
		if (!StringUtils.isBlank(level) && level.equals("3")){
			//如果获取县数据
			AddressSelect parent = service.get(pid);
			if (parent != null && parent.getZgx() != null && parent.getZgx().intValue() == 1){
				result = new ArrayList<AddressSelect>();
				result.add(parent);
			}
		}
		if (result == null){
			result = service.getByParentId(pid);
		}
		return "success";
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

	public List<AddressSelect> getResult() {
		return result;
	}

}
