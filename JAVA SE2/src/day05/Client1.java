package day05;

import java.net.Socket;

/**
 * �ͻ���Ӧ�ó���
 * @author asus
 *
 */
public class Client1 {
	private Socket socket;
	public Client1(){
		try {
			/**
			 * ʵ����Socket,��������ָ���ķ�������
			 */
			socket =new Socket("localhost",8088);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����쳣");
			e.printStackTrace();
		}
	}
	/**
	 * �÷���������������˽���ͨ��
	 */
	public void start(){
		try {
			/**
			 * �����̣߳����ܷ���������������Ϣ
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * �ͻ��˴������̣߳����ڽ��ܷ���������������Ϣ
	 */
}
