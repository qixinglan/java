package day05;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端应用程序
 * @author asus
 *
 */
public class Client {
	private Socket socket;
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client=new Client();
		/**
		 * 实例化Socket，尝试链接指定的服务器端
		 */
		client.socket=new Socket("localhost",8088);//一new就已经链接了
		/**
		 * 用Socket建立输出流，用于向服务器发送信息
		 */
		OutputStream out=client.socket.getOutputStream();
		/**
		 *  将输出流转换为PrientWriter，可以方便的发送字符串
		 */
		PrintWriter writer=new PrintWriter(out, true);
		writer.println("客户端：你好!! 我是谷亮");
	}
}
