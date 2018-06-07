package day05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端应用程序
 * @author asus
 *
 */
public class Server {
	private ServerSocket srerve;
	public static void main(String[] args) throws Exception{
		//实例化服务器端实例
		Server server=new Server();
		//1 。初始化ServerSocket
		//  初始化的时候要指定服务器端口号
		server.srerve=new ServerSocket(8088);
		/**
		 * 2 。监听8088端口，等待客户端的链接
		 * 		返回刚刚链接的客户端Socket
		 */
		System.out.println("服务端启动了，等待客户端链接。。。");
		Socket socket=server.srerve.accept();//是个阻塞方法，一直到有客户端链接
		System.out.println("一个客户端链接了谷亮的服务器");
		/**
		 * 通过客户端的Socket获取一个输入流，用于读取客户端发送来的 信息
		 */
		InputStream in=socket.getInputStream();
		/**
		 * 将受到的
		 */
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		System.out.println(br.readLine());
	}
}
