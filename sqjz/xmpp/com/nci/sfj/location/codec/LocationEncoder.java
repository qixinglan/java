package com.nci.sfj.location.codec;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.nci.sfj.location.LocationMessage;

/**
 * Description:小灵通短信编码器
 *
 * @author Shuzz
 * @since 2014-1-10上午10:29:47
 */
public class LocationEncoder implements MessageEncoder<LocationMessage> {
	private static final Logger log = Logger.getLogger(LocationEncoder.class);
	@Override
	public void encode(IoSession session, LocationMessage message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = codeMessage(message);
		out.write(buf);
	}

	public IoBuffer codeMessage(LocationMessage message) {
		IoBuffer buf = IoBuffer.allocate(256);
		buf.setAutoExpand(true);
		try {
			buf.put(message.enCoder());
		} catch (Exception ex) {
			log.error("小灵通短信编码失败", ex);
		}
		buf.flip();
		return buf;
	}
}
