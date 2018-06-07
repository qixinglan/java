package com.nci.dcs.common.runqian;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runqian.base4.resources.EngineMessage;
import com.runqian.base4.resources.MessageManager;
import com.runqian.base4.util.ReportError;
import com.runqian.report4.usermodel.Context;

public  class PadStringIfNull extends RunqianFunction{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(PadStringIfNull.class);
	@Override
	public Object calculate(Context ctx, boolean isInput) {
		// 判断参数个数
		if (this.paramList.size() < 3) {
			MessageManager mm = EngineMessage.get();
			throw new ReportError("OrgNameFunction:"
					+ mm.getMessage("function.missingParam"));
		}
		String str = getStringParam(0, this.paramList, ctx, isInput,true);

		Integer length = getIntegerParam(1, this.paramList, ctx, isInput, false);
		String padding = getStringParam(2, this.paramList, ctx, isInput,true);
		if (StringUtils.isEmpty(padding)){
			padding = " ";
		}
		if (!StringUtils.isEmpty(str)) {
			int len = 0;
			for (int i = 0; i < str.length(); i++)
				len+=(str.codePointAt(i) > 127 ? 2 : 1);
			if (len >= length)
				return str;
//			char[] leftPadding = getPaddingStr(" ", (length - len) >> 1);
//			char[] rightPadding = getPaddingStr(" ", length - (length - len) >> 1);
			//return new StringBuffer().append(leftPadding).append(str).append(rightPadding).toString();
			return new StringBuffer().append(str).toString();
		}
		return new String(getPaddingStr(padding, length));

	}
	
	public char[] getPaddingStr(String padding, int length){
		char[] chars = new char[length];
		char[] paddings = padding.toCharArray();
		for (int i = 0,j=0; i < length; i++,j=(j+1)%paddings.length){
			if (Character.codePointAt(paddings, j) > 127)
				length--;
			chars[i] = paddings[j];
		}
		if (length < chars.length)
			chars = Arrays.copyOfRange(chars, 0, length);
		return chars;
	}
}