package com.tarena.train;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	public static void main(String[] args) {
		final WelcomFrame welcome=new WelcomFrame();
		welcome.setVisible(true);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//��ʱ��
		//timer.schedule(TimeTask,time1,time2)��λ����
		
		//timer.schedule(TimerTask,time)
		final Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				welcome.setVisible(false);
				timer.cancel();
				new TrainGame().start();
			}
		}, 2000);
	}
}












