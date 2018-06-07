package day05;

import java.net.Socket;

/**
 * 客户端应用程序
 * @author asus
 *
 */
public class Client1 {
	private Socket socket;
	public Client1(){
		try {
			/**
			 * 实例化Socket,尝试连接指定的服务器端
			 */
			socket =new Socket("localhost",8088);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接异常");
			e.printStackTrace();
		}
	}
	/**
	 * 该方法用于与服务器端进行通信
	 */
	public void start(){
		try {
			/**
			 * 启动线程，接受服务器发送来的信息
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 客户端创建的线程，用于接受服务器发送来的信息
	 */
}
