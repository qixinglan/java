package com.nci.sfj.transmit.model;

public enum QueryType {
	/**
	 * @Description 机构信息
	 */
	JGXX, 
	/**
	 * @Description 待办事项
	 */
	DBSX,
	/**
	 * @Description 在矫人员列表
	 */
	ZJRYLIST,
	/**
	 * @Description 电子监管统计信息
	 */
	DZJGTJXX,
	/**
	 * @Description 电子监管人员列表
	 */
	DZJGLIST,
	/**
	 * @Description 在矫人员基本信息
	 */
	ZJRYJBXX,
	/**
	 * @Description 在矫人员法律文书信息
	 */
	ZJRYFLWS,
	/**
	 * @Description 在矫人员刑罚执行信息
	 */
	ZJRYXFZX,
	/**
	 * @Description 在矫人员矫正成员信息列表
	 */
	ZJRYJZCYLIST,
	/**
	 * @Description 在矫人员当月报到记录
	 */
	ZJRYDYBDLIST,
	/**
	 * @Description 在矫人员电话报到记录
	 */
	ZJRYDHBDLIST,
	/**
	 * @Description 在矫人员暂监外病检记录
	 */
	ZJRYJWBJLIST,
	/**
	 * @Description 在矫人员社区服务记录
	 */
	ZJRYSQFWLIST,
	/**
	 * @Description 在矫人员每月走访记录
	 */
	ZJRYMYZFLIST,
	/**
	 * @Description 在矫人员每月教育记录
	 */
	ZJRYMYJYLIST,
	/**
	 * @Description 在矫人员集中教育记录
	 */
	ZJRYJZJYLIST,
	/**
	 * @Description 在矫人员请假记录
	 */
	ZJRYQJJLLIST,
	/**
	 * @Description 在矫人员矫正转移信息
	 */
	ZJRYJZZYLIST,
	/**
	 * @Description 在矫人员矫正托管信息
	 */
	ZJRYJZTGLIST,
	/**
	 * @Description 在矫人员解除矫正信息
	 */
	ZJRYJCJZ,
	/**
	 * @Description 在矫人员奖惩信息
	 */
	ZJRYJCXXLIST,
	/**
	 * @Description 待办事项通知信息
	 */
	DBSXTZXXLIST,
	/**
	 * @Description 待办事项动态信息
	 */
	DBSXDTXXLIST,
	/**
	 * @Description 待办事项人员未接收
	 */
	DBSXWJSLIST,
	/**
	 * @Description 待办事项人员已接收
	 */
	DBSXYJSLIST,
	/**
	 * @Description 待办事项待转入人员
	 */
	DBSXDZRLIST,
	/**
	 * @Description 待办事项暂监外病检
	 */
	DBSXJWBJLIST,
	/**
	 * @Description 待办事项预解矫提醒
	 */
	DBSXYJJTXLIST,
	/**
	 * @Description 待办事项电话报告
	 */
	DBSXDHBDLIST,
	/**
	 * @Description 待办事项当月报告
	 */
	DBSXDYBDLIST,
	/**
	 * @Description 待办事项教育提醒
	 */
	DBSXJYTXLIST,
	/**
	 * @Description 待办事项走访提醒
	 */
	DBSXZFTXLIST,
	/**
	 * @Description 待办事项社区服务
	 */
	DBSXSQFWLIST,
	/**
	 * @Description 待办事项人员接收
	 */
	DBSXRYJSLIST,
	/**
	 * @Description 待办事项销假提醒 
	 */
	DBSXXJTXLIST,
	/**
	 * @Description 待办事项集中初始教育
	 */
	DBSXJZCSJYLIST,
	/**
	 * @Description 待办事项集中解矫前教育
	 */
	DBSXJZJJQJYLIST,
	/**
	 * @Description 待办事项历史报警信息
	 */
	DBSXBJXXLIST,
	/**
	 * @Description 电子监管历史轨迹信息
	 */
	DZJGLSGJLIST,
	/**
	 * @Description 矫正管理报到登记信息
	 */
    JZGLBDDJXX,
	/**
	 * @Description 强制退出
	 */
	FORCEQUIT,
	/**
	 * @Description 执法督察信息
	 */
	ZFDCXX,
	/**
	 * @Description 执法督察类型统计信息
	 */
	ZFDCLXTJXX,
	/**
	 * @Description 执法督察单位统计信息
	 */
	ZFDCBMTJXX,
	/**
	 * @Description 待办事项通知信息操作
	 */
	DBSXCZTZXX,
	/**
	 * @Description 待办事项动态信息操作
	 */
	DBSXCZDTXX,
	/**
	 * @Description 待办事项待转入人员操作
	 */
	DBSXCZDZRRY,
	/**
	 * @Description 待办事项暂监外病检操作
	 */
	DBSXCZZJWBJ,
	/**
	 * @Description 待办事项报警信息处理
	 */
	DBSXCZBJXXCL,
	/**
	 * @Description 待办事项销假提醒处理
	 */
	DBSXCZXJTXCL,
	/**
	 * @Description 待办事项电话报告处理
	 */
	DBSXCZDHBG,
	/**
	 * @Description 待办事项当月报告处理
	 */
	DBSXCZDYBG,
	/**
	 * @Description 待办事项教育提醒处理
	 */
	DBSXCZJYTX,
	/**
	 * @Description 待办事项走访提醒处理
	 */
	DBSXCZZFTX,
	/**
	 * @Description 待办事项社区服务处理
	 */
	DBSXCZSQFW,
	/**
	 * @Description 待办事项初始教育处理
	 */
	DBSXCZCSJY,
	/**
	 * @Description 待办事项解矫前教育处理
	 */
	DBSXCZJJQJY,
	/**
	 * @Description 执法终端修改密码
	 */
	ZFZDXGMM,
	/**
	 * @Description 执法终端定位设备编号
	 */
	ZFZDDWSBBH,
	/**
	 * @Description 执法督察信息列表，按类型、单位显示人员列表
	 */
	ZFDCXXJZRYLIST

}
