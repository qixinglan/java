package day05;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���Ӧ�ó���
 * @author asus
 *
 */
public class Client {
	private Socket socket;
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client=new Client();
		/**
		 * ʵ����Socket����������ָ���ķ�������
		 */
		client.socket=new Socket("localhost",8088);//һnew���Ѿ�������
		/**
		 * ��Socket����������������������������Ϣ
		 */
		OutputStream out=client.socket.getOutputStream();
		/**
		 *  �������ת��ΪPrientWriter�����Է���ķ����ַ���
		 */
		PrintWriter writer=new PrintWriter(out, true);
		writer.println("�ͻ��ˣ����!! ���ǹ���");
	}
}
