package com.nci.sfj.location.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.nci.sfj.location.LocationMessage;

public class LocationCodecFactory extends DemuxingProtocolCodecFactory {

	public LocationCodecFactory() {
		addMessageDecoder(new LocationDecoder());
		addMessageEncoder(LocationMessage.class, new LocationEncoder());
	}
}
