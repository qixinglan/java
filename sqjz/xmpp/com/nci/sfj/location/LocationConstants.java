package com.nci.sfj.location;

/**
 * Description:主动定位中的常量
 * 
 * @author Shuzz
 * @since 2014年9月9日上午11:53:56
 * 
 */
public class LocationConstants {
	/**
	 * Description:基本消息长度
	 */
	public static final int MESSAGE_LENGTH = 10;
	// ---------消息类型---------
	/**
	 * Description:1数据
	 */
	public static final byte TYPE_DATA = (byte) 1;
	/**
	 * Description:2数据接收确认
	 */
	public static final byte TYPE_DATA_ACK = (byte) 2;
	/**
	 * Description:3心跳探测
	 */
	public static final byte TYPE_HEART = (byte) 3;
	/**
	 * Description:4心跳回应
	 */
	public static final byte TYPE_HEART_ACK = (byte) 4;
	/**
	 * Description:5异常或错误
	 */
	public static final byte TYPE_ERROR = (byte) 5;
}
