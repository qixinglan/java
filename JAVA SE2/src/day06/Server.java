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
 * �����ҿͻ��˳���
 * @author asus
 *
 */
public class Server {
	/**
	 * �����Socket
	 */
	private ServerSocket server;
	//��������Ӧ������Ϣ
	private Config config;
	//������пͻ��˵�������ļ���
	private Vector<PrintWriter> allOut;
	/**
	 * ��Ϣ����
	 * ���ã���ÿһ���߳��յ�һ���ͻ��˷��͹�������Ϣʱ
	 * ���ǽ���Ϣ������С�
	 * ת����Ϣ���̻߳�Ӷ�����˳���ȡ����Ϣ����ת��
	 * 
	 * ʹ��˫�������
	 * ��ͬ���Ļ��������������߳�����ȡ������
	 * һ���̴߳�Ԫ��ʱ��������һ���߳�ͬʱȡԪ��
	 * �����ͬ��ʱ�Ĵ�ȡЧ��
	 */
	private BlockingQueue<String> messageQuene;
	private ExecutorService threadPool;
	/**
	 * ����˹��췽��
	 * ���ڳ�ʼ������˱�Ҫ������
	 */
	
	public Server(){
		try {
			//��ʼ���̳߳�
			threadPool=Executors.newCachedThreadPool();
			//��ʼ����Ϣ����
			messageQuene=new LinkedBlockingDeque<String>();
			//��ʼ������
			allOut=new Vector<PrintWriter>();
			//��ȡ�����ļ�
			config=new Config("Server_config.properties");
			//�������ļ������õĶ˿ںŴ���ServerSocket
			server=new ServerSocket(config.getIntValue("port"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����������ʧ��");
			e.printStackTrace();
		}
	}
	//��������װ��һ���ͻ��˵������
	public synchronized void addOut(PrintWriter out){
		allOut.add(out);
	}
	//����������Ϣ���͸����пͻ���
	public synchronized void sendMessageToAllClient(String message){
		 /*
		  * �ڵ�������Ԫ��ʱ�����ܶ�Ԫ�ؽ�����ɾ��������������쳣
		  * 
		  * Vector��Ȼ��ͬ���ģ��������Ƕ���Ԫ�ؽ�����ɾ������������ͬ��
		  * ����Ԫ��ʹ�õĵ�����Ȼ�������ڵ�����ɾ��Ԫ��
		  * ���Ե���Ԫ�صĲ���Ҫ�� ��ɾԪ�صĲ�������
		  */
		for(PrintWriter writer : allOut){
			writer.println(message);
		}
	}
	//��������������Ӽ���������
	//���ͻ�����������Ͽ��󣬸ü����оͲ�����Ҫ��������ͻ��˵��������
	public synchronized void removeout(PrintWriter out){
		allOut.remove(out);
	}
	//��������������ʼ���ܿͻ�����Ϣ
	public void start(){
		
		try {
			//����ת���߳�
			SendMessageToAllClientHandle sendMessageHandler=
				new SendMessageToAllClientHandle();
			Thread sendThread=new Thread(sendMessageHandler);
			sendThread.setDaemon(true);//��̨�߳�
			sendThread.start();
			//�����˿ڣ��ȴ��ͻ�������
			while(true){
				Socket socket=server.accept();
				//����һ���̣߳����ڴ���ÿͻ��˵Ľ���
				System.out.println("���ӷ������ɹ�");
				GetClientInfoHandler handler=new GetClientInfoHandler(socket);
				threadPool.execute(handler);//��һ������;ͼ�һ����
//				Thread t=new Thread(handler);
//				t.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//�����ڲ���,���ڴ�����ͻ��˽������߳���
	private class GetClientInfoHandler implements Runnable{
		//��ǰ�߳����ڽ����Ŀͻ���Socket
		private Socket client;
		//���췽�����ڴ����߳�ʱ����Ҫ�ӽ����Ŀͻ���Socket����
		public GetClientInfoHandler(Socket client){
			this.client=client;
		}
		public void run(){
			PrintWriter out=null;//��Ӧ�ͻ��˵������
			try{
				//���һ�µ�ǰ�ͻ���IP
				//InetAddress�������ǻ�����Э���е�IP
				InetAddress address=client.getInetAddress();//
//				System.out.println("�ͻ���Ip��"+address.getHostAddress());//
//				System.out.println("�ͻ�����������"+address.getHostName());
				//�ڸ��߳��������Ƚ��ͻ��˵���������빲����
				out=new PrintWriter(client.getOutputStream(),true);
				addOut(out);
				System.out.println("��ǰ����������"+allOut.size());
				InputStream in=client.getInputStream();
				//����������װΪ�����ַ�������
				InputStreamReader reader=new InputStreamReader(in);
				BufferedReader br=new BufferedReader(reader);
				String str=null;
				//��������   ѭ����ȡ�ͻ��˷��͵���Ϣ
			while(true){
				//��ȡ�ͻ��˷��͵�һ���ַ���
				str=br.readLine();
				if(str==null){
					throw new RuntimeException("�û������쳣");
				}
//				
//				//��ȡһ�仰�Ժ�Ӧ��ת�������пͻ���
//				sendMessageToAllClient(str);
				//����ȡ��һ����Ϣ�����ǽ��ͻ��˷�������Ϣ������Ϣ���У��ȴ�ת��
				messageQuene.offer(str,1,TimeUnit.MINUTES);
				
			}
			}catch(Exception e){
				System.out.println("�ͻ��������쳣");
				//e.printStackTrace();
			}finally{
				try {
					//�ر�����ǰ���Ƚ�������ӹ�������ɾ��
					removeout(out);
					System.out.println(client.getInetAddress().getHostAddress()+"������");
					System.out.println("��ǰ��������:"+allOut.size());
					client.close();//�Ͽ��������������(�����Ƿ��������������ر�Ҳ��ζ������������ͬʱ�ر�
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//ѭ������Ϣ�����л�ȡ��Ϣ����ת�������пͻ���
	public class SendMessageToAllClientHandle implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String str=null;
			while(true){
				//ȡ������Ԫ��
				str=messageQuene.poll();
				if(str!=null){
					//����Ϣת�������пͻ���
					sendMessageToAllClient(str);
				}else{
					//�����п��ˣ�����Ϣһ�ᣬ���ٲ���Ҫ�����ܿ���
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
