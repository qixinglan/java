package day05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������Ӧ�ó���
 * @author asus
 *
 */
public class Server {
	private ServerSocket srerve;
	public static void main(String[] args) throws Exception{
		//ʵ������������ʵ��
		Server server=new Server();
		//1 ����ʼ��ServerSocket
		//  ��ʼ����ʱ��Ҫָ���������˿ں�
		server.srerve=new ServerSocket(8088);
		/**
		 * 2 ������8088�˿ڣ��ȴ��ͻ��˵�����
		 * 		���ظո����ӵĿͻ���Socket
		 */
		System.out.println("����������ˣ��ȴ��ͻ������ӡ�����");
		Socket socket=server.srerve.accept();//�Ǹ�����������һֱ���пͻ�������
		System.out.println("һ���ͻ��������˹����ķ�����");
		/**
		 * ͨ���ͻ��˵�Socket��ȡһ�������������ڶ�ȡ�ͻ��˷������� ��Ϣ
		 */
		InputStream in=socket.getInputStream();
		/**
		 * ���ܵ���
		 */
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		System.out.println(br.readLine());
	}
}
