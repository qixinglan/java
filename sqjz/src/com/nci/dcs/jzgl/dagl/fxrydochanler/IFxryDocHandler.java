package com.nci.dcs.jzgl.dagl.fxrydochanler;

import java.util.Map;

import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;

public interface IFxryDocHandler {
	public Map<String,String> Execute(String fxry_Id,FXRYDocExportService fxryDocExportService);
}
