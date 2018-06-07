package com.nci.dcs.jzgl.dagl.fxrydochanler;

public class FxryDocHandlerFactory {
	public static IFxryDocHandler getFxryDocHandlerInstance(String fxry_dabh) {
		try {
			if (fxry_dabh != null && fxry_dabh != "") {
				Object o = Class.forName(
						"com.nci.dcs.jzgl.dagl.fxrydochanler.HandlerDoc"
								+ fxry_dabh).newInstance();
				IFxryDocHandler docHandler = ((IFxryDocHandler) o);
				return docHandler;
			}
		} catch (Exception e) {
			try {
					Object o = Class.forName(
							"com.nci.dcs.jzgl.dagl.fxrydochanler.HandlerDocDefault").newInstance();
					IFxryDocHandler docHandler = ((IFxryDocHandler) o);
					return docHandler;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}
