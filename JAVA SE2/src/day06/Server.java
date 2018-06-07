package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 聊天室客户端程序
 * @author asus
 *
 */
public class Server {
	/**
	 * 服务端Socket
	 */
	private ServerSocket server;
	//服务器响应配置信息
	private Config config;
	//存放所有客户端的输出流的集合
	private Vector<PrintWriter> allOut;
	/**
	 * 消息队列
	 * 作用：当每一个线程收到一个客户端发送过来的信息时
	 * 我们将信息放入队列、
	 * 转发信息的线程会从队列中顺序的取出信息，做转发
	 * 
	 * 使用双缓冲队列
	 * 在同步的基础上允许两个线程做存取操作即
	 * 一个线程存元素时，允许另一个线程同时取元素
	 * 提高了同步时的存取效率
	 */
	private BlockingQueue<String> messageQuene;
	private ExecutorService threadPool;
	/**
	 * 服务端构造方法
	 * 用于初始化服务端必要的内容
	 */
	
	public Server(){
		try {
			//初始化线程池
			threadPool=Executors.newCachedThreadPool();
			//初始化消息队列
			messageQuene=new LinkedBlockingDeque<String>();
			//初始化集合
			allOut=new Vector<PrintWriter>();
			//读取配置文件
			config=new Config("Server_config.properties");
			//用配置文件中配置的端口号创建ServerSocket
			server=new ServerSocket(config.getIntValue("port"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("服务器启动失败");
			e.printStackTrace();
		}
	}
	//向共享集合中装入一个客户端的输出流
	public synchronized void addOut(PrintWriter out){
		allOut.add(out);
	}
	//将给定的消息发送给所有客户端
	public synchronized void sendMessageToAllClient(String message){
		 /*
		  * 在迭代几何元素时，不能对元素进行增删操作，否则出现异常
		  * 
		  * Vector虽然是同步的，但仅仅是对于元素进行增删操作可以做到同步
		  * 遍历元素使用的迭代依然不允许在迭代中删除元素
		  * 所以迭代元素的操作要与 增删元素的操作互斥
		  */
		for(PrintWriter writer : allOut){
			writer.println(message);
		}
	}
	//将给定的输出流从集合中消除
	//当客户端与服务器断开后，该集合中就不再需要保存这个客户端的输出流了
	public synchronized void removeout(PrintWriter out){
		allOut.remove(out);
	}
	//启动服务器，开始接受客户端信息
	public void start(){
		
		try {
			//启动转发线程
			SendMessageToAllClientHandle sendMessageHandler=
				new SendMessageToAllClientHandle();
			Thread sendThread=new Thread(sendMessageHandler);
			sendThread.setDaemon(true);//后台线程
			sendThread.start();
			//监听端口，等待客户端连接
			while(true){
				Socket socket=server.accept();
				//启动一个线程，用于处理该客户端的交互
				System.out.println("连接服务器成功");
				GetClientInfoHandler handler=new GetClientInfoHandler(socket);
				threadPool.execute(handler);//有一个任务就就加一个线
//				Thread t=new Thread(handler);
//				t.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//创建内部类,用于处理与客户端交互的线程体
	private class GetClientInfoHandler implements Runnable{
		//当前线程用于交互的客户端Socket
		private Socket client;
		//构造方法用于创建线程时将需要加交互的客户端Socket传入
		public GetClientInfoHandler(Socket client){
			this.client=client;
		}
		public void run(){
			PrintWriter out=null;//对应客户端的输出流
			try{
				//输出一下当前客户端IP
				//InetAddress描述的是互联网协议中的IP
				InetAddress address=client.getInetAddress();//
//				System.out.println("客户端Ip："+address.getHostAddress());//
//				System.out.println("客户端主机名："+address.getHostName());
				//在该线程启动后，先将客户端的输出流放入共享集合
				out=new PrintWriter(client.getOutputStream(),true);
				addOut(out);
				System.out.println("当前在线人数："+allOut.size());
				InputStream in=client.getInputStream();
				//将输入流包装为缓冲字符输入流
				InputStreamReader reader=new InputStreamReader(in);
				BufferedReader br=new BufferedReader(reader);
				String str=null;
				//并发运行   循环读取客户端发送的信息
			while(true){
				//读取客户端发送的一行字符串
				str=br.readLine();
				if(str==null){
					throw new RuntimeException("用户输入异常");
				}
//				
//				//读取一句话以后，应该转发给所有客户端
//				sendMessageToAllClient(str);
				//当读取到一个信息后，我们将客户端发来的信息放入消息队列，等待转发
				messageQuene.offer(str,1,TimeUnit.MINUTES);
				
			}
			}catch(Exception e){
				System.out.println("客户端连接异常");
				//e.printStackTrace();
			}finally{
				try {
					//关闭连接前，先将输出流从共享集合中删除
					removeout(out);
					System.out.println(client.getInetAddress().getHostAddress()+"下线了");
					System.out.println("当前在线人数:"+allOut.size());
					client.close();//断开与服务器的连接(无论是否正常结束），关闭也意味着输入和输出流同时关闭
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//循环从消息队列中获取消息，并转发给所有客户端
	public class SendMessageToAllClientHandle implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String str=null;
			while(true){
				//取出队首元素
				str=messageQuene.poll();
				if(str!=null){
					//将消息转发给所有客户端
					sendMessageToAllClient(str);
				}else{
					//若队列空了，就休息一会，减少不必要的性能开销
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
	}
	
}
