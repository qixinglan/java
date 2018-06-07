package com.nci.dcs.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.common.exceptions.InsertException;
import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.data.model.Jx;
import com.nci.dcs.data.model.Zw;
import com.nci.dcs.system.model.DictionaryInfo;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.nci.dcs.system.service.UserService;
import com.opensymphony.xwork2.Action;

import com.nci.dcs.data.service.JxService;
import com.nci.dcs.data.service.ZwService;

public class DictionaryInfoAction extends BaseAction<DictionaryInfo>{
	
	@Autowired
	private DictionaryInfoService service;
	
	private List<DictionaryKeyValue> result;
	
	public String json(){
		Date ifModified = HttpUtil.ifModified2Date(this.request.getHeader("If-Modified-Since"));
		if (ifModified.before(service.getLastModified())){
			try {
				Map<String, List<DictionaryKeyValue>> dicts = service.getDict();
				this.response.setHeader("Last-Modified", HttpUtil.date2LastModified(service.getLastModified()));
				this.response.setHeader("Content-Type", "text/json;charset=utf-8");
				this.response.setHeader("Cache-Control", "max-age=0");
				JSONObject result = JSONObject.fromObject(dicts);
				this.response.getWriter().write(result.toString());
			} catch (IOException e) {
			}
		}
		else{
			this.response.setStatus(304);
		}
		return "none";
	}
	
	public String search(){
		render();
		List<DictionaryKeyValue> temp = new ArrayList();
		for (DictionaryKeyValue keyvalue: result){
			if (keyvalue.isUsing()){
				temp.add(keyvalue);
			}
		}
		result = temp;
		return "success";
	}
	
	public String render(){
		result = null;
		String code = this.request.getParameter("code");
		if (code != null && !code.isEmpty()){
			result = service.findItems(code);
		}
		if (result == null){
			result = new ArrayList();
		}
		return "success";
	}
	
	public String reload(){
		service.loadCache(true);
		return "success";
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

	public List<DictionaryKeyValue> getResult() {
		return result;
	}

	public void setResult(List<DictionaryKeyValue> result) {
		this.result = result;
	}

}
