package day06;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * �����ҿͻ��˳���
 * @author asus
 *
 */
public class Client {
	 //�������ӷ�������socket
	private Socket socket;
	public Client(){
		try {
			//��ȡ�ͻ��˵������ļ�
			Config config=new Config("Client_config.properties");
			socket=new Socket(config.getStringValue("ip"),config.getIntValue("port"));
			System.out.println("���ӷ������ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ӷ�����ʧ��");
			e.printStackTrace();
		}
	}
	//�ͻ��˿�ʼ����  ��Ҫ����������
	//1.����һ���̣߳�����ѭ����ȡ���������͹�������Ϣ
	//2.ѭ����ȡ����������ַ��������͸�������
	public void start(){
		try{
			//�����̣߳����ܷ���������������Ϣ
			GetServerinfoHandler handle=new GetServerinfoHandler(socket);
			Thread t=new Thread(handle);
			t.start();
			//ͨ��Socket�����������������������˷�����Ϣ
			OutputStream out= socket.getOutputStream();
			//�������ת��ΪPrintWriter�����Է���ķ����ַ���
			PrintWriter pw=new PrintWriter(out,true);
			//�����̵���������ɻ����ַ�����������ڶ�ȡ�û����������
			BufferedReader SysInReader=new BufferedReader(
					new InputStreamReader(System.in));
			String str=null;
			while(true){
				//��ȡ���������һ���ַ���
				str=SysInReader.readLine();
				//�������������Ϣ
				pw.println(str);
			}
			
//			//ͨ��Socket��ȡ�����������ڶ�ȡ�������˷��͵���Ϣ
//			InputStream in=socket.getInputStream();
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) {
		Client client=new Client();
		client.start();
	}
	//�ͻ��˴������̣߳����ڽ��ܷ���������������Ϣ�������������̨
	public class GetServerinfoHandler implements Runnable{
		private Socket socket;
		public GetServerinfoHandler(Socket socket){
			this.socket=socket;
		}
		public void run(){
			try {
				//�ͻ������ͨ����������ѭ����ȡ����������������Ϣ
				BufferedReader reader=new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String str=null;
				while(true){
					//��ȡ���������͹�����һ����Ϣ
					str=reader.readLine();
					//���������̨
					System.out.println(str);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
