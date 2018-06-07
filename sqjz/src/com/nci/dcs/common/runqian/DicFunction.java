package com.nci.dcs.common.runqian;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nci.dcs.system.model.DictionaryKeyValue;
import com.runqian.base4.resources.EngineMessage;
import com.runqian.base4.resources.MessageManager;
import com.runqian.base4.util.ReportError;
import com.runqian.report4.usermodel.Context;

public class DicFunction extends RunqianFunction {
	
	@Override
	public Object calculate(Context ctx, boolean isInput) {
		// 判断参数个数
		if (this.paramList.size() < 2) {
			MessageManager mm = EngineMessage.get();
			throw new ReportError("DicFunction:"
					+ mm.getMessage("function.missingParam"));
		}
		String strCodes = getStringParam(0, this.paramList, ctx, isInput,true);
		if (StringUtils.isEmpty(strCodes))
			return null;
		String type = getStringParam(1, this.paramList, ctx, isInput,true);
		String[] codes = strCodes.split(",");
		for (int i = codes.length; i-- > 0;) {
			codes[i] = getDicValue(type, codes[i]);
		}
		return StringUtils.join(codes, ",");
	}

	private static String getDicValue(String type, String code) {
		if (type == null){
			return code;
		}
		List<DictionaryKeyValue> items = getRunqianUtilsContext()
				.getDictionaryInfoService().findItems(type);
		if (items == null){
			return code;
		}
		for (DictionaryKeyValue item : items) {
			if (item.isUsing() && item.getCode() != null
					&& item.getCode().equals(code))
				return item.getName();
		}
		return null;
	}
}
