package day01;
/**
 * try catch
 * @author asus
 *
 */
public class TryCatchDemo {
	public static void main(String[] args){
		try{
			//String str=null;
			String str="abc";
			//��jvmִ�е�str��length����ʱ����str��null���ᴴ��һ��Nullpointexceptionʵ��������������Ϣ���õ����ʵ���У����׳�
			System.out.println(str.length());//�쳣�������䲻ִ�У�ֱ��ȥ��catch��䣬��catch��俪ʼִ��
			System.out.println(str.charAt(0));
			int num=Integer.parseInt(str);//NumFormatException
			System.out.println("δִ��");
			
		}catch(NullPointerException e){
			System.out.println("�����и���ָ��");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("�ַ����±�Խ���쳣");
		}catch(Exception e){
			System.out.println("����δ֪�쳣");
		}
		/**
		 * �����쳣Ӧ���մӾ��嵽����ԭ����Ϊһ����������catch��佫��ִ��
		 */
		
		System.out.println("���������");
	}
}
