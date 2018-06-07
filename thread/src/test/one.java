package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




public class one {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(8088);
		System.out.println("开启服务器");
		while(true){
			Socket ss=server.accept();
			System.out.println("谷亮已连接");
			InputStream r=ss.getInputStream();
			InputStreamReader in=new InputStreamReader(r);
			BufferedReader re=new BufferedReader(in);
			OutputStream out=ss.getOutputStream();
			PrintWriter pw=new PrintWriter(out,true);
			String s=re.readLine();
			System.out.println(s);
			pw.println(s);
		}
		
	}
}
