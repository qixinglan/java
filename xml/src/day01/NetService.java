package day01;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class NetService {
	 public void send(File file,OutputStream out) throws Exception{
		 String header="file,"+file.getName()+","+file.length();
		 byte[] bytes =header.getBytes("utf-8");
		 bytes=Arrays.copyOf(bytes, 100);
		 out.write(bytes);
		 InputStream in=new FileInputStream(file);
		 int b;
		 while((b=in.read())!=-1){
			 out.write(b);
		 }
		 out.flush();
		 in.close();
	 }
	 public void receive(InputStream in) throws Exception{
		 byte[] buf=new byte[100];
		 in.read(buf);
		 String header=new String(buf,"buf-8").trim();
		 String[] date=header.split(",");
		 String type=date[0];
		 String filename=date[1];
		 long length=Long.parseLong(date[3]);
		 BufferedOutputStream fout=new BufferedOutputStream(new FileOutputStream("_"+filename+"."+type));
		 for(long j=1;j<=length;j++){
			int b=in.read();
			fout.write(b);
		 }
		 fout.flush();
		 in.close();
		
	 }
}
