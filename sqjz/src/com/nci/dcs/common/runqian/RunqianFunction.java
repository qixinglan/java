package com.nci.dcs.common.runqian;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.official.service.PersonsService;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.runqian.base4.resources.EngineMessage;
import com.runqian.base4.resources.MessageManager;
import com.runqian.base4.util.ReportError;
import com.runqian.report4.model.expression.Expression;
import com.runqian.report4.model.expression.Function;
import com.runqian.report4.model.expression.Variant2;
import com.runqian.report4.usermodel.Context;

public abstract class RunqianFunction extends Function {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(RunqianFunction.class);

	protected static Object getParam(int i,
			@SuppressWarnings("rawtypes") ArrayList paramList, Context ctx,
			boolean isInput) {
		if (paramList.size() <= i) {
			return null;
		}
		Expression param = (Expression) paramList.get(i);
		if (param == null) {
			return null;
		}
		return Variant2.getValue(param.calculate(ctx, isInput), false, isInput);
	}

	protected static String getStringParam(int i,
			@SuppressWarnings("rawtypes") ArrayList paramList, Context ctx,
			boolean isInput, boolean optional) {
		Object param = getParam(i, paramList, ctx, isInput);
		if (param instanceof String)
			return (String) param;
		else if (optional){
			return null;
		}
		MessageManager mm = EngineMessage.get();
		throw new ReportError("encrypt:"
				+ mm.getMessage("function.missingParam"));
	}
	
	protected static Integer getIntegerParam(int i,
			@SuppressWarnings("rawtypes") ArrayList paramList, Context ctx,
			boolean isInput, boolean optional) {
		Object param = getParam(i, paramList, ctx, isInput);
		if (param instanceof Integer)
			return (Integer) param;
		else if (optional){
			return null;
		}
		MessageManager mm = EngineMessage.get();
		throw new ReportError("encrypt:"
				+ mm.getMessage("function.missingParam"));
	}

	/**
	 * Referencing services in project.
	 */
	private static RunqianUtilsContext runqianUtilsContext = null;

	protected static RunqianUtilsContext getRunqianUtilsContext() {
		if (runqianUtilsContext == null)
			runqianUtilsContext = (RunqianUtilsContext) SpringContextUtil
					.getBean("runqianUtilsContext");
		return runqianUtilsContext;
	}

	/**
	 * Hold referencing services in project.
	 */
	@Component("runqianUtilsContext")
	public static class RunqianUtilsContext {
		@Autowired
		private DictionaryInfoService dictionaryInfoService = null;
		@Autowired
		private OrganizationInfoService organizationInfoService = null;
		@Autowired
		private PersonsService personsService = null;

		public DictionaryInfoService getDictionaryInfoService() {
			return dictionaryInfoService;
		}

		public void setDictionaryInfoService(
				DictionaryInfoService dictionaryInfoService) {
			this.dictionaryInfoService = dictionaryInfoService;
		}

		public OrganizationInfoService getOrganizationInfoService() {
			return organizationInfoService;
		}

		public void setOrganizationInfoService(
				OrganizationInfoService organizationInfoService) {
			this.organizationInfoService = organizationInfoService;
		}

		public PersonsService getPersonsService() {
			return personsService;
		}

		public void setPersonsService(PersonsService personsService) {
			this.personsService = personsService;
		}
	}
}
