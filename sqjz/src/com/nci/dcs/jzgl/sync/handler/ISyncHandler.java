package com.nci.dcs.jzgl.sync.handler;

import com.nci.dcs.jzgl.sync.utils.SyncException;

public interface ISyncHandler {
	public String getName();
	public String getEncoding();
	public void sendRequest(String xml) throws SyncException;//同步内容输入到此输出流
	public void sync() throws SyncException;//执行同步代码
	public String readResponse() throws SyncException;//返回结果从此流读出,如果返回值为null,则不处理结果
	public void close();//清理操作
}
