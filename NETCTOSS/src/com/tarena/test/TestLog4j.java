package com.tarena.test;
import org.apache.log4j.Logger;
public class TestLog4j {	
	private static Logger logger = Logger.getLogger(TestLog4j.class);
	public static void main(String[] args) {
		logger.debug("调试");
		logger.info("普通");
		logger.warn("警告");
		logger.error("错误");
		logger.fatal("严重");
	}
}
