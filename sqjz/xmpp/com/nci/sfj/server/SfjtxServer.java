package com.nci.sfj.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.sfj.common.util.Config;
import com.nci.sfj.xmpp.session.SessionManager;

public class SfjtxServer {
	private static final Log log = LogFactory.getLog(SfjtxServer.class);

	private static SfjtxServer instance;
	private String serverName;
	private String serverHomeDir;
	private boolean shuttingDown;

	public static SfjtxServer getInstance() {
		// return instance;
		if (instance == null) {
			synchronized (SfjtxServer.class) {
				instance = new SfjtxServer();
			}
		}
		return instance;
	}
	/**
	 * Constructor. Creates a server and starts it.
	 */
	public SfjtxServer() {
		if (instance != null) {
			throw new IllegalStateException("A server is already running");
		}
		instance = this;
		start();
	}

	/**
	 * Returns if the server is running in standalone mode.
	 * 
	 * @return true if the server is running in standalone mode, false
	 *         otherwise.
	 */
	public boolean isStandAlone() {
		boolean standalone;
		try {
			standalone = Class
					.forName("org.androidpn.server.starter.ServerStarter") != null;
		} catch (ClassNotFoundException e) {
			standalone = false;
		}
		return false;
	}

	private void shutdownServer() {
		shuttingDown = true;
		// Close all connections
		SessionManager.getInstance().closeAllSessions();
		log.info("TxServer stopped");
	}

	private class ShutdownHookThread extends Thread {
		public void run() {
			shutdownServer();
			log.info("Server halted");
			System.err.println("Server halted");
		}
	}

	/**
	 * Description:
	 * 
	 * @deprecated
	 */
	private void locateServer() throws FileNotFoundException {
		String baseDir = System.getProperty("base.dir", ".");
		log.debug("base.dir=" + baseDir);
		if (serverHomeDir == null) {
			try {
				File confDir = new File(baseDir, "conf");
				if (confDir.exists()) {
					serverHomeDir = confDir.getParentFile().getCanonicalPath();
				}
			} catch (FileNotFoundException fe) {
				// Ignore
			} catch (IOException ie) {
				// Ignore
			}
		}

		if (serverHomeDir == null) {
			System.err.println("Could not locate home.");
			throw new FileNotFoundException();
		}
	}

	/**
	 * Starts the server using Spring configuration.
	 */
	public void start() {
		try {
			if (isStandAlone()) {
				Runtime.getRuntime().addShutdownHook(new ShutdownHookThread());
			}
			// locateServer();
			serverName = Config.getString("xmpp.domain", "127.0.0.1")
					.toLowerCase();
			log.info("Spring配置文件加载完毕。");
		} catch (Exception e) {
			log.error("启动XMPP通讯服务失败", e);
			shutdownServer();
		}
	}

	private class ShutdownThread extends Thread {
		public void run() {
			try {
				Thread.sleep(5000);
				System.exit(0);
			} catch (InterruptedException e) {
				// Ignore
			}
		}
	}

	/**
	 * Stops the server.
	 */
	public void stop() {
		shutdownServer();
		Thread shutdownThread = new ShutdownThread();
		shutdownThread.setDaemon(true);
		shutdownThread.start();
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public static void main(String[] args) {
		new SfjtxServer();
	}
}
