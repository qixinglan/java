package com.nci.sfj.xmpp.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmpp.packet.JID;
import org.xmpp.packet.Packet;

import com.nci.sfj.server.SfjtxServer;
import com.nci.sfj.xmpp.exception.ConnectionException;
import com.nci.sfj.xmpp.session.SessionManager;

@Service
public class XmppSenderService {
	@Autowired
	private XmppSender sender;
	private SessionManager sessionManager = SessionManager.getInstance();

	private void handlePacket(Packet packet, String userJid) {
		packet.setFrom(sessionManager.getUserJID(userJid));
		String userName = userJid.split("@")[0];
		String resource = userJid.split("@")[1];
		packet.setTo(new JID(userName, SfjtxServer.getInstance()
				.getServerName(), resource));
	}

	public void addAlarm(Packet packet, String userJid) {
		handlePacket(packet, userJid);
		sender.addAlarmMessage(packet);
		sender.notifySend();
	}

	public void addSetting(Packet packet, String userJid) {
		handlePacket(packet, userJid);
		sender.addSettingMessage(packet);
		sender.notifySend();
	}

	public void addlocation(Packet packet, String userJid) {
		handlePacket(packet, userJid);
		sender.addLocationMessage(packet);
		sender.notifySend();
	}

	public void sendWithoutQueuing(Packet packet) throws ConnectionException {
		sender.sendWithoutQueuing(packet);
	}

	public XmppSender getSender() {
		return sender;
	}

	public void setSender(XmppSender sender) {
		this.sender = sender;
	}
}
