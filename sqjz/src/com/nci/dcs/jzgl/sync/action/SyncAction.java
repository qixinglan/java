package com.nci.dcs.jzgl.sync.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.jzgl.sync.handler.HttpResponseHandler;
import com.nci.dcs.jzgl.sync.handler.ISyncHandler;
import com.nci.dcs.jzgl.sync.handler.MultipleHandler;
import com.nci.dcs.jzgl.sync.service.SyncService;
import com.nci.dcs.jzgl.sync.utils.SyncHelper;
import com.nci.dcs.jzgl.sync.utils.SyncException;
import com.nci.dcs.jzgl.sync.utils.XMLUtil;
import com.nci.dcs.jzgl.sync.xmlmodels.Result;

public class SyncAction implements ServletRequestAware, ServletResponseAware  {
	@Autowired
	private SyncService service;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void syncPerson() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createPersonHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncPerson(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}
	
	public void syncFalv() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createFalvHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncFalv(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}

	public void syncXingFa() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createXingFaHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncXingFa(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}
	
	public void syncJianDing() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createJianDingHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncJianDing(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}
	public void syncJianLi() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createJianLiHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncJianLi(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}
	public void syncGuanXi() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			ISyncHandler httpHandler = new HttpResponseHandler(response, "gb2312");
			ISyncHandler urlHandler = SyncHelper.createGuanXiHandler();
			MultipleHandler handler = new MultipleHandler(urlHandler);
			handler.addHandler(httpHandler);
			Result r = service.syncGuanXi(fxryId, handler);
			httpHandler.sendRequest(XMLUtil.format(r));
		}
	}
	public void syncAll() throws SyncException{
		String fxryId = request.getParameter("fxryId");
		if (!StringUtils.isBlank(fxryId)){
			response.setContentType("text/plain");
			service.syncAll(fxryId, false);
		}
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
