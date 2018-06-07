package com.nci.dcs.jzgl.sync.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.nci.dcs.jzgl.sync.utils.SyncException;

public class HttpResponseHandler implements ISyncHandler{
	private HttpServletResponse response;
	private String encoding;
	public HttpResponseHandler(HttpServletResponse response, String encoding){
		this.response = response;
		this.encoding = encoding;
		this.response.setCharacterEncoding(encoding);
	}
	@Override
	public String getName() {
		return null;
	}

	@Override
	public void sendRequest(String xml) throws SyncException {
		try{
			this.response.getWriter().write(xml);
		}
		catch(IOException e){
			throw new SyncException("HttpServletResponse获取写入xml失败");
		}
	}

	@Override
	public void sync() throws SyncException {	
	}

	@Override
	public String readResponse() throws SyncException {
		return null;
	}

	@Override
	public void close() {	
	}
	@Override
	public String getEncoding() {
		return this.encoding;
	}
}
