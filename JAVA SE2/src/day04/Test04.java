package day04;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test04 {
	public static void main(String[] args) {
		show();
//		Calendar c=Calendar.getInstance();
//		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
//		while(true){
//			c.add(Calendar.SECOND, 1);
//			System.out.println(sdf.format(c.getTime()));
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	static void show(){
		
		final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				Date date=new Date();
				// TODO Auto-generated method stub
				System.out.println(sdf.format(date));
			}
		}, 1000,1000);
	}
}
