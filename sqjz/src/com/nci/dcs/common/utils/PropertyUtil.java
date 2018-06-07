package com.nci.dcs.common.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtil {
	
	//yp 
	public static String getConfigInfomations(String configFile, String itemIndex) {
		try {
			ResourceBundle resource = ResourceBundle.getBundle(configFile, Locale.getDefault());
			String attr = resource.getString(itemIndex);
			return attr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
