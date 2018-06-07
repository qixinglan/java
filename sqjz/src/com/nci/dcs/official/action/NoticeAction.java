package com.nci.dcs.official.action;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.official.model.CcNoticereceive;
import com.nci.dcs.official.model.CcWeeknotice;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.official.service.NoticeReceiveServie;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.official.service.PersonsService;
import com.nci.dcs.official.service.WeekNoticeService;
import com.nci.dcs.webservices.dxpt.service.DXPTService;

public class NoticeAction extends BaseAction<CcWeeknotice> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7816860670601463741L;
	private AjaxFormResult ajaxFormResult;
	@Autowired
	private OrganizationInfoService orgService;

	@Autowired
	private NoticeReceiveServie noticeServie;

	@Autowired
	private WeekNoticeService weekNoticeService;

	@Autowired
	private DXPTService dxptService;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PersonsService personsService;

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @name 保存
	 * @return
	 * @author clj
	 * @date 2014年9月11日 上午10:29:40
	 * @message：
	 */
	public String postData() throws Throwable {
		String jgIds = request.getParameter("jgIds");
		String id = request.getParameter("id");
		if (id != null && !("").equals(id)) {
			weekNoticeService.delete(id);
		}
		id = CommonUtils.uuid();
		entity.setId(id);
		if (jgIds != null && !jgIds.isEmpty()) {
			Set<CcNoticereceive> ccNoticeReceive = new HashSet<CcNoticereceive>();
			String[] arrJg = jgIds.split(",");
			CcNoticereceive item;
			for (int i = 0; i < arrJg.length; i++) {
				item = new CcNoticereceive();
				item.setId(CommonUtils.uuid());
				item.setName(entity.getTitle());
				item.setNoticeid(entity.getId());
				item.setOrgId(arrJg[i]);
				item.setCcWeeknotice(entity);
				item.setStatus("2");// 1：已签收；2：待签收
				ccNoticeReceive.add(item);
			}
			entity.setCcNoticereceives(ccNoticeReceive);
		}
		entity.setCreatedate(new Date());
		entity.setCreater(getUser().getName());
		entity.setRecordOrgId(getUser().getWunit().getBm());
		weekNoticeService.create(entity);
		ajaxFormResult = new AjaxFormResult(true, id);
		return SUCCESS;
	}

	/**
	 * @name 查询通知信息
	 * @return
	 * @author clj
	 * @date 2014年9月11日 上午10:25:35
	 * @message：
	 */
	public String getData() {
		getRequestPage();
		String oper = request.getParameter("oper");
		String status = request.getParameter("status");
		String orgId = getUser().getWunit().getBm();
		weekNoticeService.findPaged(page, orgId, oper,status);
		return SUCCESS;
	}
	/**
	 * @name 查询通知详细信息
	 * @return
	 * @author clj
	 * @date 2014年9月11日 上午10:25:35
	 * @message：
	 */
	public String search() {
		getRequestPage();
		String id = request.getParameter("id");
		this.entity = weekNoticeService.get(id);
		Set<CcNoticereceive> notice = entity.getCcNoticereceives();
		String orgids = "";
		String tempId = "";
		String status = "";
		String orgId = getUser().getWunit().getBm();
		String oper = request.getParameter("oper");
		for (CcNoticereceive temp : notice) {
			tempId = temp.getOrgId();
			status = temp.getStatus();

			// 通知查看进入，将机构通知状态改为签收
			if (orgId.equals(tempId)
					&& (oper != null && ("tzView").equals(oper))
					&& (status != null && ("2").equals(status))) {
				temp.setReceivetime(new Date());
				temp.setStatus("1");// 1：已签收；2：待签收
				temp.setReceiver(getUser());
				temp.setReceivername(getUser().getName());
				noticeServie.create(temp);
			}
			if (oper != null && ("view").equals(oper)) {// 拟制查看时《通知单位》为机构名称
				orgids = orgids + getOrgName(tempId) + ",";
			} else if (oper != null && ("edit").equals(oper)) {// 拟制编辑时《通知单位》为机构代码
				orgids = orgids + tempId + ",";
			}
		}
		String orgidStr = orgids.equals("") ? "" : orgids.substring(0,
				orgids.lastIndexOf(","));
		if (oper != null && ("tzView").equals(oper)) {// 通知查看进入《通知单位》为当前登陆机关名称
			orgidStr = getUser().getWunit().getMc();
		}
		// else if(oper != null && ("view").equals(oper)){//拟制查看时《拟制人》为当前登录人
		// entity.setCreater(getUser().getName());
		// }
		request.setAttribute("orgids", orgidStr);

		entity.setJgIds(orgidStr);
		return SUCCESS;
	}

	/**
	 * @name 删除
	 * @author clj
	 * @throws Throwable
	 * @date 2014年9月11日 上午10:25:16
	 * @message：
	 */
	public void delNotice() throws Throwable {
		String id = request.getParameter("id");
		weekNoticeService.delete(id);
		// 删除附件信息
		String affixId = request.getParameter("affixId");
		if (affixId != null && !("").equals(affixId)) {
			delFile(affixId);
		}
	}

	/**
	 * @name 删除附件信息
	 * @param affixId
	 * @throws Throwable
	 * @author caolj
	 * @date 2014年9月23日 上午9:46:11
	 * @message:
	 */
	public void delFile(String affixId) throws Throwable {
		String realPath = getRealPath("tzgl/upload", affixId);
		FileUtils.deleteDirectory(new File(realPath));
	}

	public static String getRealPath(String path, String id) {
		String realPath = PathUtils.getRealPath() + File.separator + "upload"
				+ File.separator + FilenameUtils.normalize(path);
		realPath = realPath + File.separator + id.substring(0, 2)
				+ File.separator + id.substring(2, 4) + File.separator + id;
		return realPath;
	}

	/**
	 * @name 下发
	 * @author clj
	 * @date 2014年9月11日 上午10:25:16
	 * @message：
	 */
	public String xfNotice() {
		String id = (String) request.getAttribute("id");
		CcWeeknotice weekNotice = weekNoticeService.get(id);
		weekNotice.setStatus("1");// 已下发
		weekNotice.setSendtime(new Date());
		weekNoticeService.update(weekNotice);
		// 市局向区县司法局下发通知通告时，系统通过市局短信平台向接收单位负责人（区县矫正科科长）发送短信提醒
		dxptSendmessage(weekNotice);
		return SUCCESS;
	}

	/**
	 * @name 短信下发通知通告
	 * @author clj
	 * @date 2014年9月15日 上午11:43:02
	 * @message：
	 */
	public void dxptSendmessage(CcWeeknotice weekNotice) {
		Set<CcNoticereceive> notice = weekNotice.getCcNoticereceives();
		String orgId = "";
		String content = "";
		String phone = "";
		String name = "";
		String orgTypeNow = getUser().getWunit().getOrgType();
		String duty = "";// 职务职级
		String orgType = "8";// 机关级别-矫正帮教科
		List<Persons> persons = null;
		String tzjb = "区县";
		try {
			// 仅市局下发通知时发送短信
			if (orgTypeNow != null && ("1").equals(orgTypeNow)) {
				tzjb = "市局";
				duty = weekNotice.getReceiveType();
				for (CcNoticereceive temp : notice) {
					orgId = temp.getOrgId();
					// 获取接收单位负责人（区县矫正科科长）
					persons = personsService.getPersonsBySupOrg(orgId, orgType,
							duty);
					if (persons != null && persons.size() > 0) {
						if (duty == null || ("").equals(duty)) {// 无职务职级
							for (Persons person : persons) {
								phone = person.getPhone();
								if (phone != null && !("").equals(phone)) {
									name = person.getName();
									break;
								}
							}
						} else {
							phone = persons.get(0).getPhone();
							name = persons.get(0).getName();
						}
						// 发送短信
						if (phone != null && !("").equals(phone)) {
							content += name + "您好，" + tzjb + "下发通知通告："
									+ temp.getName() + "。请注意签收！";
							dxptService.sendPlatformMessage(phone, name,
									content);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * @name 查看签收情况
	 * @return
	 * @author clj
	 * @date 2014年9月12日 上午10:07:34
	 * @message：
	 */
	public String qsqkView() {
		return SUCCESS;
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

	public String addNotice() {
		return SUCCESS;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return SUCCESS;
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}
}
