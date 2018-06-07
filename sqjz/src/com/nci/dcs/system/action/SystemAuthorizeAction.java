package com.nci.dcs.system.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.EncodingUtil;
import com.nci.dcs.system.model.ServerLicense;
import com.nci.dcs.system.service.ServerLicenseService;
import com.nci.dcs.system.utils.SystemLicenseiUtil;

public class SystemAuthorizeAction extends BaseAction<ServerLicense> {

	private static final long serialVersionUID = 3487059109350376751L;

	@Autowired
	private ServerLicenseService serverLicenseService;

	private AjaxFormResult ajaxFormResult;
	private Map<String, Object> info;
	private String authoriseCode;

	public String sysInfo() {
		try {
			info = new HashMap<String, Object>();
			info.put("os", SystemLicenseiUtil.sysName());
			info.put("hasLicense", SystemLicenseiUtil.hasLicense());
			info.put("timeOut", SystemLicenseiUtil.licenseTimeOut());
			info.put("dbTimeOut", SystemLicenseiUtil.dataBaseTimeOut());
			String baseBoardSN = SystemLicenseiUtil.baseBoardSN();
			if (CommonUtils.isNull(baseBoardSN)) {
				baseBoardSN = "无法获取特征码，不支持该机型或操作系统";
			} else {
				baseBoardSN = EncodingUtil.aesEncrypt(baseBoardSN,
						EncodingUtil.encryptKey, EncodingUtil.ivKey);
			}
			info.put("board", baseBoardSN);
			String cpuSN = SystemLicenseiUtil.cpuSN();
			if (CommonUtils.isNull(cpuSN)) {
				cpuSN = "无法获取特征码，不支持该机型或操作系统";
			} else {
				cpuSN = EncodingUtil.aesEncrypt(cpuSN, EncodingUtil.encryptKey,
						EncodingUtil.ivKey);
			}
			info.put("cpu", cpuSN);
			info.put("version", SystemLicenseiUtil.versionName());
		} catch (Exception e) {
			logger.error("获取系统特征码失败", e);
		}
		return SUCCESS;
	}

	public String authorise() {
		if (CommonUtils.isNull(authoriseCode)) {
			ajaxFormResult = AjaxFormResult.error("授权码为空");
		} else {
			Map<String, String> map = SystemLicenseiUtil
					.licenseDecode(authoriseCode);
			if (map.containsValue(SystemLicenseiUtil.getSyscode())) {
				serverLicenseService.addLicense(
						map.get(SystemLicenseiUtil.SYS_CODE),
						map.get(SystemLicenseiUtil.START_TIME),
						map.get(SystemLicenseiUtil.END_TIME));
				ajaxFormResult = AjaxFormResult.success("授权成功");
				SystemLicenseiUtil.getLicense();
			} else {
				ajaxFormResult = AjaxFormResult.error("授权码错误");
			}

		}
		return SUCCESS;
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

	public String getAuthoriseCode() {
		return authoriseCode;
	}

	public void setAuthoriseCode(String authoriseCode) {
		this.authoriseCode = authoriseCode;
	}

}
