package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Two {
	public static void main(String[] args)  {
		Thread t1=new Thread(){public void run(){
			while(true){
			try {
				Socket s = new Socket("127.0.0.1",8088);
				OutputStream w=s.getOutputStream();
				PrintWriter w2=new PrintWriter(w,true);
				Scanner in=new Scanner(System.in);
				String str=in.nextLine();
				w2.println("¿Í»§¶Ë1£º"+str);
				InputStream i=s.getInputStream();
				BufferedReader r=new BufferedReader(new InputStreamReader(i));
				System.out.println(r.readLine());

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			}
		};
		
		
		t1.start();
		
		
	}
}
