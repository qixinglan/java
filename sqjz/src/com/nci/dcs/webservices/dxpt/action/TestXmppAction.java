package com.nci.dcs.webservices.dxpt.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageResponse;
import com.nci.dcs.webservices.dxpt.dto.XmppSession;
import com.nci.dcs.webservices.dxpt.service.XmppService;
import com.nci.sfj.business.model.XmppDeviceInfo;
import com.nci.sfj.business.model.XmppLog;
import com.nci.sfj.client.XmppClientManage;
import com.nci.sfj.client.XmppClientSession;
import com.nci.sfj.common.util.ConfigManager;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.sender.EmMessageSendService;
import com.nci.sfj.xmpp.session.ClientSession;
import com.nci.sfj.xmpp.session.SessionManager;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Description:系统默认设置action
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
public class TestXmppAction extends BaseAction<XmppLog> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4354720374451912863L;
	@Autowired
	private EmMessageSendService emMessageSendService;
	@Autowired
	private XmppService xmppService;

	private Page<XmppDeviceInfo> xmppPage = new Page(15);
	private Page<XmppSession> page1 = new Page(15);

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String configReload(){
		ConfigManager.getInstance().loadConfig();
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String input() throws Exception {
		String type = request.getParameter("type");
		if (type.equals("1")) {
			List<String> telPhones = new ArrayList<String>();
			telPhones.addAll(Arrays.asList(entity.getTelPhones().split(",")));
			emMessageSendService.setTelPhonesByDevice(telPhones,
					entity.getZjnum());
		} else if (type.equals("2")) {
			emMessageSendService.pairDevice(entity.getZjnum(),
					entity.getWbnum());
		} else if (type.equals("3")) {
			emMessageSendService.powerOff(entity.getZjnum());
		} else if (type.equals("4")) {
			emMessageSendService.running(false, entity.getZjnum());
		} else if (type.equals("5")) {
			emMessageSendService.running(true, entity.getZjnum());
		} else if (type.equals("6")) {
			emMessageSendService.setSilentListByOrg(entity.getSilent(),
					rootOrgId);
		} else if (type.equals("7")) {
			emMessageSendService.setVoltageValByOrg(entity.getVoltage(),
					rootOrgId);
		} else if (type.equals("8")) {
			emMessageSendService.getDiskInfo(entity.getZjnum());
		} else if (type.equals("9")) {
			emMessageSendService.getEQuantityInfo(entity.getZjnum(), "phone");
		} else if (type.equals("10")) {
			emMessageSendService.getEQuantityInfo(entity.getZjnum(), "watch");
		} else if (type.equals("11")) {
			emMessageSendService.getCPUInfo(entity.getZjnum());
		} else if (type.equals("12")) {
			emMessageSendService.getMemoryInfo(entity.getZjnum());
		} else if (type.equals("13")) {
			emMessageSendService.shakeTest(entity.getZjnum());
		} else if (type.equals("14")) {
			emMessageSendService.getSIMInfo(entity.getZjnum());
		} else if (type.equals("15")) {
			emMessageSendService.getWatchCloseState(entity.getZjnum());
		} else if (type.equals("16")) {
			emMessageSendService.getWorkState(entity.getZjnum(), "phone");
		} else if (type.equals("17")) {
			emMessageSendService.getWorkState(entity.getZjnum(), "watch");
		} else if (type.equals("18")) {
			emMessageSendService.getLocation(entity.getZjnum());
		}else if (type.equals("19")) {
			emMessageSendService.powerReset(entity.getZjnum());
		}
		return NONE;
	}

	public String search() throws Throwable {
		try {
			List c = new ArrayList();
			xmppPage.setCriterions(c);
			if (jqgrid != null) {
				this.xmppPage.setPageNo(jqgrid.getPageStart());
				this.xmppPage.setPageSize(jqgrid.getLimit());
				this.xmppPage.setOrderBy(jqgrid.getSortCol());
				this.xmppPage.setOrder(jqgrid.getSortOrder());
				this.xmppPage.setAutoCount(true);
				List criter = jqgrid.getCriterions(XmppDeviceInfo.class);
				if (criter == null) {
					criter = new ArrayList();
				}
				criter.add(Restrictions.isNotNull("loginTime"));
				this.xmppPage.setCriterions(criter);
			}
			xmppService.findInfoPaged(xmppPage);
			setJqgridPage(new JQGridPageResponse(this.xmppPage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String sessionSearch() throws Throwable {
		try {
			List c = new ArrayList();
			page1.setCriterions(c);
			Map<Long, XmppClientSession> map = XmppClientManage.getInstance()
					.getClientSessions();
			Collection<ClientSession> css = SessionManager.getInstance()
					.getPreAuthSessions().values();
			List<XmppSession> list = new ArrayList<XmppSession>();
			for (XmppClientSession session : map.values()) {
				XmppSession s = new XmppSession();
				s.setId(session.getSession().getId());
				s.setContent(session.getSession().toString());
				s.setConnected(session.getSession().isConnected());
				s.setClosed(session.getSession().isClosing());
				s.setKeyCount(0);
				for (ClientSession cs : css) {
					if (null != cs.getConnection()
							&& session.getId().equals(
									cs.getConnection().getConnectionId())) {
						Map<String, AuthToken> users=cs.getUsers();
						s.setKeyCount(users.size());
						StringBuffer sb=new StringBuffer();
						for(String user:users.keySet()){
							sb.append(user.split("@")[0]);
							sb.append(",");
						}
						if(sb.length()>0){
							s.setUsers(sb.substring(0,sb.length()-1));
						}
						break;
					}
				}
				list.add(s);
			}
			setJqgridPage(new JQGridPageResponse(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String detail() throws Throwable {
		try {
			String deviceNumber = request.getParameter("num");
			String type = request.getParameter("type");
			page = this.getRequestPage();
			page.getCriterions().add(
					Restrictions.eq("deviceNumber", deviceNumber));
			xmppService.findLogPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public Page<XmppDeviceInfo> getXmppPage() {
		return xmppPage;
	}

	public void setXmppPage(Page<XmppDeviceInfo> xmppPage) {
		this.xmppPage = xmppPage;
	}

}