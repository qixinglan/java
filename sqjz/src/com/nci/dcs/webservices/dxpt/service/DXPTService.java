package com.nci.dcs.webservices.dxpt.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang.xwork.StringUtils;
import org.directwebremoting.util.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.webservices.dxpt.DXPTSendException;
import com.nci.dcs.webservices.dxpt.wsdl.MainActionProxy;

/**
 * @author xxz
 *
 */
/**
 * @author xxz
 *
 */
@Service
public class DXPTService {
	private Logger logger = Logger.getLogger(DXPTService.class);
	private String wsdl;
	private String requestId;
	private String defaultSenderName;
	private String defaultSenderId;
	
	public DXPTService(){
		try {
			InputStream source = getClass().getClassLoader().getResourceAsStream("/dxpt.properties");
			Properties config = new Properties();
			config.load(new InputStreamReader(source, "utf-8"));
			wsdl = config.getProperty("dxpt.wsdl");
			Assert.hasText(wsdl, "wsdl不能为空!");
			requestId = config.getProperty("dxpt.requestId");
			Assert.hasText(requestId, "requestId不能为空");
			defaultSenderName = config.getProperty("dxpt.defaultSenderName");
			defaultSenderId = config.getProperty("dxpt.defaultSenderId");
		} catch (Exception e) {
			logger.error("加载短信平台配置dxpt.properties出错:", e);
		}
	}
	
	private void sendMessage(String phoneNumber, String receivePerson, String content, 
						String isDingShi, Date sendDate, String sendPerson, String sendpersonid) throws DXPTSendException{
		try{
			String sendDateStr = "";
			if (sendDate != null){
				sendDateStr = "定时" + DateTimeFmtSpec.getMinutesFormat().format(sendDate);
			}
			logger.info(String.format("发送%s短信【%s】:%s", sendDateStr, phoneNumber, content));
			MainActionProxy proxy = new MainActionProxy(wsdl);
			String result = proxy.sendMessage(phoneNumber, receivePerson, content, requestId, isDingShi, sendDateStr, sendPerson, sendpersonid);
			if (StringUtils.isEmpty(result) || "Ok".equalsIgnoreCase(result)){
				return;
			}
			String msg = "";
			if ("Norequestid".equals(result)){
				msg = "没有对应的服务号";
			}
			else{
				msg = String.format("参数格式错误,请检查参数【发送号码=%s】【接收人=%s】【内容=%s】【是否定时=%s,%s】【发送人=%s】【发送人ID=%s】", 
						phoneNumber, receivePerson, content, isDingShi, sendDateStr, sendPerson, sendpersonid);
			}
			logger.error(msg);
			throw new DXPTSendException(msg);
		}
		catch (RemoteException e){
			String msg = "网络故障，无法连接到短信平台";
			logger.error(msg);
			throw new DXPTSendException(msg);
		}
		catch (DXPTSendException e){
			throw e;
		}
		catch (Exception e){
			String msg = "调用错误,传入错误的参数";
			logger.error(msg);
			throw new DXPTSendException(msg);
		}
	}
	
	/**
	 * @param phoneNumber 接收短信的电话号码,多个号码以英文逗号分割
	 * @param receivePerson 接收人姓名,多个人名以英文逗号分割
	 * @param content 短信内容
	 * @param sendPerson 发送人的姓名
	 * @param sendpersonid 发送人的登录帐号,ca帐号
	 * @throws DXPTSendException
	 */
	public void sendUserMessage(String phoneNumber, String receivePerson, String content, 
			  String sendPerson, String sendpersonid) throws DXPTSendException{
		sendMessage(phoneNumber, receivePerson, content, "no", null, sendPerson, sendpersonid);
	}
	
	
	/**
	 * @param phoneNumber 接收短信的电话号码,多个号码以英文逗号分割
	 * @param receivePerson 接收人姓名,多个人名以英文逗号分割
	 * @param content 短信内容
	 * @param sendDate 定时发送时间,使用格式为yyyy-MM-dd HH:mm
	 * @param sendPerson 发送人的姓名
	 * @param sendpersonid 发送人的登录帐号,ca帐号
	 * @throws DXPTSendException
	 */
	public void sendUserMessage(String phoneNumber, String receivePerson, String content, Date sendDate, 
			  String sendPerson, String sendpersonid) throws DXPTSendException{
		sendMessage(phoneNumber, receivePerson, content, "yes", sendDate, sendPerson, sendpersonid);
	}
	
	private void sendPlatformMessage(String phoneNumber, String receivePerson, String content, 
			String isDingShi, Date sendDate) throws DXPTSendException{
		if (!StringUtils.isEmpty(defaultSenderName) && !StringUtils.isEmpty(defaultSenderId)){
			sendMessage(phoneNumber, receivePerson, content, isDingShi, sendDate, defaultSenderName, defaultSenderId);
		}
		else{
			String msg = "没有配置默认的defaultSenderName或defaultSenderId, 必须显示设置发送者";
			logger.warn(msg);
			throw new DXPTSendException(msg);
		}
	}
	
	
	/**
	 * @param phoneNumber 接收短信的电话号码,多个号码以英文逗号分割
	 * @param receivePerson 接收人姓名,多个人名以英文逗号分割
	 * @param content 短信内容
	 * @throws DXPTSendException
	 */
	public void sendPlatformMessage(String phoneNumber, String receivePerson, String content) throws DXPTSendException{
		sendPlatformMessage(phoneNumber, receivePerson, content, "no", null);
	}
	
	/**
	 * @param phoneNumber 接收短信的电话号码,多个号码以英文逗号分割
	 * @param receivePerson 接收人姓名,多个人名以英文逗号分割
	 * @param content 短信内容
	 * @param sendDate 定时发送时间,使用格式为yyyy-MM-dd HH:mm
	 * @throws DXPTSendException
	 */
	public void sendPlatformMessage(String phoneNumber, String receivePerson, String content, Date sendDate) throws DXPTSendException{
		sendPlatformMessage(phoneNumber, receivePerson, content, "yes", sendDate);
	}
}
