package day06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * �����ҿͻ��˳���
 * @author asus
 *
 */
public class Client4{
	 //�������ӷ�������socket
	private Socket socket;
	public Client4(){
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
		try {
			//1
			getServerInfoHander handler=new getServerInfoHander();
			Thread t=new Thread(handler);
			t.start();
			//2
			Scanner scanner=new Scanner(System.in);//
			//����Ӧ�������������װΪPrintWreter
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			String str=null;
			while(true){
				str=scanner.nextLine();
				out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����������쳣");
		}finally{//��������������ر�socket
			try {
				socket.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	//���߳�����ѭ����ȡ����ѭ�����͹�������Ϣ�����������̨
	public class getServerInfoHander implements Runnable{
		public void run(){
			try{
				//ͨ��Socket��ȡ����������ȡ���������͹�������Ϣ
				BufferedReader reader=new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				//ѭ�������������͹�������Ϣ
				String str=null;
				while(true){
					str=reader.readLine();
					System.out.println(str);
				}
			}catch(Exception e){
				System.out.println("�����������ʧ��");
			}
		}
		
	}
	public static void main(String[] args){
		Client4 client=new Client4();
		client.start();
	}
}