package com.tarena.test;
import org.apache.log4j.Logger;
public class TestLog4j {	
	private static Logger logger = Logger.getLogger(TestLog4j.class);
	public static void main(String[] args) {
		logger.debug("����");
		logger.info("��ͨ");
		logger.warn("����");
		logger.error("����");
		logger.fatal("����");
	}
}
