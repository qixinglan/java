package com.nci.sfj.location;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.sfj.common.util.Charsets;
import com.nci.sfj.location.codec.LocationEncoder;

/**
 * Description:主动定位请求消息实体
 * 
 * @author Shuzz
 * @since 2014年9月9日上午11:54:57
 * 
 */
public class LocationMessage {

	/**
	 * 数据包类型，说明数据包的用途:1数据，2数据接收确认，3心跳探测，4心跳回应，5异常或错误
	 * */
	private byte type;
	/**
	 * 数据包序列号
	 * */
	private int sequenceId;
	/**
	 * 数据类型:1字符串，2文件
	 * */
	private byte packetType;
	/**
	 * 数据内容长度:无符号的32位整型，采用大端对齐的字节顺序
	 * */
	private int messageLength;
	/**
	 * 数据内容:消息采用UTF8进行编码
	 * */
	private String content;

	private static int sequence = 0;
	private static int Min_Seq = 0;// 第三部分最小值
	private static int Max_Seq = Integer.MAX_VALUE;// 第三部分最大值

	public IoBuffer getIoBuffer() {
		LocationEncoder encoder = new LocationEncoder();
		return encoder.codeMessage(this);
	}

	/**
	 * Description:序列号自增长用
	 * 
	 * @author Shuzz
	 * @since 2014-1-10上午10:26:04
	 */
	private synchronized void computeSequence() {
		if (sequence == Max_Seq)
			sequence = Min_Seq;
		else {
			sequence += 1;
		}
		sequenceId = sequence;
	}

	/**
	 * Description:用于我方主动发送时序列自增长
	 * 
	 * @author Shuzz
	 * @param type
	 */
	public LocationMessage(byte type) {
		this.type = type;
		this.packetType = (byte) 1;
		this.computeSequence();
	}

	/**
	 * Description:用于回复消息
	 * 
	 * @author Shuzz
	 * @param sequence
	 */
	public LocationMessage(byte type, int sequence) {
		this.type = type;
		this.sequenceId = sequence;
		this.packetType = (byte) 1;
	}

	public ByteBuffer enCoder() {
		ByteBuffer buf = ByteBuffer.allocate(LocationConstants.MESSAGE_LENGTH
				+ messageLength);
		buf.put(type);
		buf.putInt(sequenceId);
		buf.put(packetType);
		buf.putInt(messageLength);
		if (messageLength != 0 && !CommonUtils.isNull(content)) {
			Charset charset = Charsets.UTF8;
			byte[] abyte = content.getBytes(charset);
			buf.put(abyte);
		}
		buf.flip();
		return buf;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public byte getPacketType() {
		return packetType;
	}

	public void setPacketType(byte packetType) {
		this.packetType = packetType;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		Charset charset = Charsets.UTF8;
		byte[] abyte = content.getBytes(charset);
		this.messageLength = abyte.length;
	}

	@Override
	public String toString() {
		return "主动定位[数据包类型:" + type + ";序列号:" + sequenceId + ";数据类型:"
				+ packetType + ";内容长度:" + messageLength + ";数据内容:" + content
				+ "]";
	}

}
