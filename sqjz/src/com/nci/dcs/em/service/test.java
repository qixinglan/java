package com.nci.dcs.em.service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.nci.dcs.base.xmpp.XmppCallback;

public class test {
	private static final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);
	public static String sendXmpp(final XmppCallback<String> callback){
		String id = UUID.randomUUID().toString();
		service.schedule(new Runnable(){
			private String id;
			private Random random = new Random();
			@Override
			public void run() {
				try {
					if(random.nextBoolean() && random.nextBoolean() && random.nextBoolean()){
						callback.finish(getId(), 1, 1, "成功。", null);
					} else {
						callback.process(getId(), 1, 1, "成功。", null);
						service.schedule(this, random.nextInt(4), TimeUnit.SECONDS);
					}
				} catch (Throwable t){
					t.printStackTrace();
				}
			}
			public Runnable setId(String id){
				this.id = id;
				return this;
			}
			public String getId(){
				return id;
			}
		}.setId(id), 3, TimeUnit.SECONDS);
		return id;
	}
}
