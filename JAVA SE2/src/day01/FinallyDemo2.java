package day01;
/**
 * finally��;
 * @author asus
 *
 */
public class FinallyDemo2 {
	public static void main(String[] args) {
		try{
			String age="12";
			age="ABC";
			System.out.println("�������ݿ�");
			System.out.println("��������"+Integer.parseInt(age));
			//System.out.println("�ر����ݿ�");
		}catch(Exception e){
			System.out.println("���ִ���");
		}
		finally{
			System.out.println("�ر����ݿ�");
		}
		System.out.println("�����˳�");
	}
}
