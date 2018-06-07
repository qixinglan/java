package day01;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DemocServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server=new ServerSocket(8088);
		Socket s=server.accept();
		InputStream in=s.getInputStream();
		int filenumber=2;
		receive(s,filenumber);
		s.close();
		
	}	
//		byte[] bytes=new byte[100];
//		
//		
//		
//		in.read(bytes);
//		String t1=new String(bytes,"utf-8").trim();
//		String[] data=t1.split(",");
//		String type=data[0];
//		String filename=data[1];
//		Long length=Long.parseLong(data[2]);
//		BufferedOutputStream fout=new BufferedOutputStream(new FileOutputStream("_"+filename+"."+type));
//		for(long i=1;i<=length;i++){
//			int b=in.read();
//			fout.write(b);
//		}
//		fout.flush();
//		
//		
//		in.read(bytes);
//		t1=new String(bytes,"utf-8").trim();
//		data=t1.split(",");
//		type=data[0];
//		filename=data[1];
//		length=Long.parseLong(data[2]);
//		fout=new BufferedOutputStream(new FileOutputStream("_"+filename+"."+type));
//		for(long i=1;i<=length;i++){
//			int b=in.read();
//			fout.write(b);
//		}
//		fout.flush();
//		
//		
//		fout.close();
//		s.close();
//	}
	public static void receive(Socket s,int filenumber) throws Exception{
		for(int i=1;i<=filenumber;i++){
			InputStream in=s.getInputStream();
			byte[] bytes=new byte[100];
	
			in.read(bytes);
			String t1=new String(bytes,"utf-8").trim();
			String[] data=t1.split(",");
			String type=data[0];
			String filename=data[1];
			Long length=Long.parseLong(data[2]);
			BufferedOutputStream fout=new BufferedOutputStream(new FileOutputStream("_"+filename+"."+type));
			for(long j=1;j<=length;j++){
				int b=in.read();
				fout.write(b);
			}
			fout.flush();
			if(i==filenumber){
				fout.close();
			}
		}
		
		
		
	}
}
