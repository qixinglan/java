package day01;
/**
 * throws
 * @author asus
 *
 */
public class ThrowsDemo {
	public static void main(String[] args) {
		try{
		connectionDB("192.168.1.");// �������ݿ�
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("��������");//��������
		System.out.println("ע��ɹ�");//֪ͨ�û�
	}
	public static void connectionDB(String url)throws Exception{//����throws����쳣���á���������
		if("192.168.1.1".equals(url)){
			System.out.println("���ݿ����ӳɹ�");
		}else{
			throw new Exception("�������ݿ��쳣");//����ʧ�ܣ�ģ�ⱨ��
		}
	}
}
