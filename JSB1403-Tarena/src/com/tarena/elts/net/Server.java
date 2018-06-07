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
 * ���Է����
 * @author asus
 *
 */
public class Server {
	private Config config;
	
	public void setConfig(Config config) {
		this.config = config;
	}

	//������һ���·���ExamServiceImpl����ʱ�� 
	private EntityContext entityContext;
	
	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}

	//���ȶ���һ��������б�,һ���ͻ��ˣ�sessionID����Ӧһ������ExamServiceImpl��
	Map<String,ExamService> serviceMap=new HashMap();
	public void startServer(){
		try{
			ServerSocket server=new ServerSocket(config.getInt("ServerPort"));
		
			while(true){
			//�ȴ��ͻ���
			Socket ss=server.accept();
			//�����߳�
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
				//��������
				ObjectInputStream in = new ObjectInputStream(ss
						.getInputStream());
				Request req = (Request) in.readObject();
				//��������
				//���÷�װ�õĴ����߼���ÿ�������в�ͬ�Ŀͻ���
				//��ͬ�ͻ��˶�Ӧ��ͬ��ExamServiceImpl
				//����������ݣ�����ExamServiceImpl���ĳһ�߼������ؽ��
				//�пͻ��˵�ʱ�򴫿ͻ��˶�Ӧ��serviceû�пͻ��˵�ʱ�򴴽��µ�service
				//����һ��ExamService
				ExamService service = getService(req);
				Response res = ServerUtils.call(service, req);
				//������Ӧ
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
	//���ݲ�ͬ�Ŀͻ��ˣ����غͿͻ������Ӧ�ķ���ExamServiceImpl��
	//����req��sessioioID�����ͬ�Ŀͻ���
	private ExamService getService(Request req){
		String sessionID=req.getSessionID();
		ExamService examService=serviceMap.get(sessionID);
		if(examService==null){//��ǰû�иÿͻ���
			examService=new ExamServiceImpl();
			
			ser.setEntityContext(entityContext);
			//��ʱExamServiceImpl��ʵ���಻�ܹ���������ע�����ݹ���� ����
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
