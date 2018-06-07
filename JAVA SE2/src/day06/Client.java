package day06;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 聊天室客户端程序
 * @author asus
 *
 */
public class Client {
	 //用于连接服务器的socket
	private Socket socket;
	public Client(){
		try {
			//读取客户端的配置文件
			Config config=new Config("Client_config.properties");
			socket=new Socket(config.getStringValue("ip"),config.getIntValue("port"));
			System.out.println("连接服务器成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接服务器失败");
			e.printStackTrace();
		}
	}
	//客户端开始工作  主要工作有两个
	//1.启动一个线程，用于循环读取服务器发送过来的信息
	//2.循环读取键盘输入的字符串并发送给服务器
	public void start(){
		try{
			//启动线程，接受服务器发送来的信息
			GetServerinfoHandler handle=new GetServerinfoHandler(socket);
			Thread t=new Thread(handle);
			t.start();
			//通过Socket创建输出流，用于向服务器端发送信息
			OutputStream out= socket.getOutputStream();
			//将输出流转化为PrintWriter，可以方便的发送字符串
			PrintWriter pw=new PrintWriter(out,true);
			//将键盘的输入流变成缓冲字符输出流，用于读取用户输入的内容
			BufferedReader SysInReader=new BufferedReader(
					new InputStreamReader(System.in));
			String str=null;
			while(true){
				//获取键盘输入的一行字符串
				str=SysInReader.readLine();
				//向服务器发送信息
				pw.println(str);
			}
			
//			//通过Socket获取输入流，用于读取服务器端发送的信息
//			InputStream in=socket.getInputStream();
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) {
		Client client=new Client();
		client.start();
	}
	//客户端创建的线程，用于接受服务器发送来的信息，并输出到控制台
	public class GetServerinfoHandler implements Runnable{
		private Socket socket;
		public GetServerinfoHandler(Socket socket){
			this.socket=socket;
		}
		public void run(){
			try {
				//客户端这边通过输入流，循环读取服务器发送来的信息
				BufferedReader reader=new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String str=null;
				while(true){
					//读取服务器发送过来的一条信息
					str=reader.readLine();
					//输出到控制台
					System.out.println(str);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
