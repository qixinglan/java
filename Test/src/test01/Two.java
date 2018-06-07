package test01;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Two {
	public static void main(String[] args) {
		try {
			InputStream in=new FileInputStream("F:/KuGou/3.wav");
			AudioStream as=new AudioStream(in);
			//AudioPlayer.player.start(as);
			//AudioPlayer.player.start(as);
			/*
			 * Ñ­»·²¥·Å
			 */
			AudioData data = as.getData();
			ContinuousAudioDataStream gg= new ContinuousAudioDataStream (data);
			AudioPlayer.player.start(gg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
