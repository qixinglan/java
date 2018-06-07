package day06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * 聊天室客户端程序
 * @author asus
 *
 */
public class Client4{
	 //用于连接服务器的socket
	private Socket socket;
	public Client4(){
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
		try {
			//1
			getServerInfoHander handler=new getServerInfoHander();
			Thread t=new Thread(handler);
			t.start();
			//2
			Scanner scanner=new Scanner(System.in);//
			//将对应服务器输出流包装为PrintWreter
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			String str=null;
			while(true){
				str=scanner.nextLine();
				out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("服务器连接异常");
		}finally{//若与服务器发生关闭socket
			try {
				socket.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	//该线程用于循坏读取服务循环发送过来的信息并输出到控制台
	public class getServerInfoHander implements Runnable{
		public void run(){
			try{
				//通过Socket获取输入流，读取服务器发送过来的信息
				BufferedReader reader=new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				//循环读服务器发送过来的信息
				String str=null;
				while(true){
					str=reader.readLine();
					System.out.println(str);
				}
			}catch(Exception e){
				System.out.println("与服务器连接失败");
			}
		}
		
	}
	public static void main(String[] args){
		Client4 client=new Client4();
		client.start();
	}
}