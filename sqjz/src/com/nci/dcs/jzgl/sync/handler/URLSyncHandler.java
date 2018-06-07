package com.nci.dcs.jzgl.sync.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.nci.dcs.jzgl.sync.utils.SyncException;

public class URLSyncHandler implements ISyncHandler{
	private String name;
	private String encoding;
	private URL url;
	private String urlStr;
	private URLConnection conn;
	private URLConnection getConnection() throws IOException{
		if (conn == null){
			conn = url.openConnection();
	        conn.setDoOutput(true);//POST方式
	        conn.setDoInput(true);
		}
		return conn;
	}
	public URLSyncHandler(String name, String urlStr, String encoding) throws SyncException{
		try {
			this.name = name;
			this.encoding = encoding;
			this.urlStr = urlStr;
			this.url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new SyncException("URL格式不正确");
		}
	}
	@Override
	public void sync() throws SyncException {
	}

	private OutputStreamWriter writer = null;
	private OutputStreamWriter getWriter() throws UnsupportedEncodingException, IOException{
		if (writer == null){
			writer = new OutputStreamWriter(getConnection().getOutputStream(), getEncoding());
		}
		return writer;
	}
	
	@Override
	public void sendRequest(String xml) throws SyncException {
		try {
			getWriter().write(xml);
		} catch (UnsupportedEncodingException e){
			throw new SyncException("不支持的编码集" + urlStr);
		}catch (IOException e) {
			throw new SyncException("网络错误，无法连接" + urlStr);
		}
	}

	@Override
	public String readResponse() throws SyncException {
		InputStreamReader in = null;
		try{
			writer.flush();
			writer.close();
			in = new InputStreamReader(getConnection().getInputStream(), encoding);
			BufferedReader reader = new BufferedReader(in);
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			if (line != null){
				sb.append(line);
				line = reader.readLine();
			}
			return sb.toString();
		}
		catch (UnsupportedEncodingException e){
			throw new SyncException("不支持的编码集" + urlStr);
		}
	    catch (IOException e) {
	    	throw new SyncException("网络错误，远端无响应" + urlStr);
	    }
		finally{
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
	@Override
	public void close() {
		try {
			if (writer != null){
				writer.close();
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getEncoding() {
		return this.encoding;
	}
}
