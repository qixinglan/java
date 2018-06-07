package com.nci.sfj.location.codec;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.nci.sfj.common.util.Charsets;
import com.nci.sfj.location.LocationConstants;
import com.nci.sfj.location.LocationMessage;

/**
 * Description:
 * 
 * @author Shuzz
 * @since 2014年9月4日上午10:36:15
 */
public class LocationDecoder implements MessageDecoder {

	private static final Logger logger = Logger
			.getLogger(LocationDecoder.class);

	/**
	 * Description:根据通信的端口号来进行消息是否能解析的判断
	 * 
	 * @author Shuzz
	 * @since 2014年9月4日上午10:36:15
	 * 
	 * @see org.apache.mina.filter.codec.demux.MessageDecoder#decodable(org.apache
	 *      .mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer)
	 */
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		if (in.remaining() < LocationConstants.MESSAGE_LENGTH) {
			return MessageDecoderResult.NEED_DATA;
		}
		in.get();
		in.getInt();
		in.get();
		int messageLen = in.getInt();
		try {
			if (in.remaining() < messageLen) {
				return MessageDecoderResult.NEED_DATA;
			}
			return MessageDecoderResult.OK;
		} catch (Exception e) {
			return MessageDecoderResult.NOT_OK;
		}
	}

	/**
	 * Description:解析消息
	 * 
	 * @author Shuzz
	 * @since 2014年9月4日上午10:36:15
	 * @param in
	 * @return
	 * @throws Exception
	 */
	private LocationMessage decodeMessage(IoBuffer in) throws Exception {
		try {
			byte type = in.get();
			int sequence = in.getInt();
			LocationMessage message = new LocationMessage(type, sequence);
			message.setPacketType(in.get());
			int messageLen = in.getInt();
			message.setMessageLength(messageLen);
			if (messageLen > 0) {
				byte[] content = new byte[messageLen];
				in.get(content);
				Charset charset = Charsets.UTF8;
				message.setContent(new String(content, charset));
			}
			return message;
		} catch (Exception e) {
			logger.error(null, e);
			return null;
		}
	}

	/**
	 * Description:解析消息
	 * 
	 * @author Shuzz
	 * @since 2014年9月4日上午10:36:15
	 * 
	 * @see org.apache.mina.filter.codec.demux.MessageDecoder#decode(org.apache.mina
	 *      .core.session.IoSession, org.apache.mina.core.buffer.IoBuffer,
	 *      org.apache.mina.filter.codec.ProtocolDecoderOutput)
	 */
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		LocationMessage m = decodeMessage(in);
		// Return NEED_DATA if the body is not fully read.
		if (m == null) {
			return MessageDecoderResult.NEED_DATA;
		}
		out.write(m);
		return MessageDecoderResult.OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}
}