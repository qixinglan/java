package test01;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class One {
	public static void main(String[] args) {
		URL url;
		try {
			
			url = new URL("file:F:/KuGou/3.wav");
			final AudioClip ac=Applet.newAudioClip(url);
			ac.play();
			//Thread.sleep(5000);
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ac.stop();
				}
			}, 5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
