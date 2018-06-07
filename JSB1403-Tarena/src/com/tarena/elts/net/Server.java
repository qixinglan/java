package com.tarena.elts.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.ExamServiceImpl;
import com.tarena.elts.util.Config;

/**
 * 考试服务端
 * @author asus
 *
 */
public class Server {
	private Config config;
	
	public void setConfig(Config config) {
		this.config = config;
	}

	//供创建一个新服务（ExamServiceImpl）的时候 
	private EntityContext entityContext;
	
	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}

	//首先定义一个服务端列表,一个客户端（sessionID）对应一个服务（ExamServiceImpl）
	Map<String,ExamService> serviceMap=new HashMap();
	public void startServer(){
		try{
			ServerSocket server=new ServerSocket(config.getInt("ServerPort"));
		
			while(true){
			//等待客户端
			Socket ss=server.accept();
			//启动线程
			new Thread(new Service(ss)).start();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	class Service implements Runnable{
		private Socket ss;
		public Service(Socket ss){
			this.ss=ss;
		}
		public void run(){
			try {
				//接受请求
				ObjectInputStream in = new ObjectInputStream(ss
						.getInputStream());
				Request req = (Request) in.readObject();
				//处理请求
				//调用封装好的处理逻辑，每个请求有不同的客户端
				//不同客户端对应不同的ExamServiceImpl
				//把请求的数据，调用ExamServiceImpl里的某一逻辑，返回结果
				//有客户端的时候传客户端对应的service没有客户端的时候创建新的service
				//创建一个ExamService
				ExamService service = getService(req);
				Response res = ServerUtils.call(service, req);
				//发送响应
				ObjectOutputStream out = new ObjectOutputStream(ss
				.getOutputStream());
				out.writeObject(res);
				ss.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	//根据不同的客户端，返回和客户端相对应的服务（ExamServiceImpl）
	//根据req的sessioioID来辨别不同的客户端
	private ExamService getService(Request req){
		String sessionID=req.getSessionID();
		ExamService examService=serviceMap.get(sessionID);
		if(examService==null){//以前没有该客户端
			examService=new ExamServiceImpl();
			
			ser.setEntityContext(entityContext);
			//此时ExamServiceImpl的实现类不能工作，必须注入数据管理层 对象
			serviceMap.put(sessionID, examService);
		}
		serviceMap.put(sessionID, examService);
		return examService;
	}
	
	public static void main(String[] args) {
		Config config=new Config("client.properties");
		EntityContext entityContext=new EntityContext(config);
		Server ser=new Server();
		ser.setConfig(config);
		new Server().startServer();
	}
}
