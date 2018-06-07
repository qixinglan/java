package com.nci.sfj.location.connect;

public interface QueuableMessageInspector {
	int getQPriority(Object message);
	String getMsgUnicode(Object message);
}
