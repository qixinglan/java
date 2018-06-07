package com.nci.dcs.common.runqian;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.dcs.official.model.OrganizationInfo;
import com.runqian.base4.resources.EngineMessage;
import com.runqian.base4.resources.MessageManager;
import com.runqian.base4.util.ReportError;
import com.runqian.report4.usermodel.Context;

public  class OrgFunction extends RunqianFunction{
	private static Logger logger = LoggerFactory.getLogger(OrgFunction.class);
	@Override
	public Object calculate(Context ctx, boolean isInput) {
		// 判断参数个数
		if (this.paramList.size() < 2) {
			MessageManager mm = EngineMessage.get();
			throw new ReportError("OrgNameFunction:"
					+ mm.getMessage("function.missingParam"));
		}
		String org = getStringParam(0, this.paramList, ctx, isInput,true);
		String field = getStringParam(1, this.paramList, ctx, isInput,false);
		return getOrgField(org,field);

	}

	private String getOrgField(String orgId,String field) {
		if (null == orgId){
			return null;
		}
		OrganizationInfo organizationInfo = getRunqianUtilsContext().getOrganizationInfoService().get(
				orgId);
		if (organizationInfo != null)
			try {
				Object obj = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(organizationInfo, field);
				return obj == null ? null : obj.toString();
			} catch (Exception e) {
				logger.error("获取机构" + orgId +"属性" + field +"异常。",e);
			}
		return null;
	}
}