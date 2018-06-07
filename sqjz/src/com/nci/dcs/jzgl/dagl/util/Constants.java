package com.nci.dcs.jzgl.dagl.util;

public interface Constants {
	public interface DeviceStatus{
		public static Long USING = 1L;
		public static Long UNUSE = 0L;
	}
	public enum FXRYStateChangeType {
		NONE,
		SAME_ORG,//要求服刑人员与操作人员相同机构，才可以状态变更
		CHANGE_ORG //要求服刑人员状态变更后,修改其矫正负责单位
	}
}
