package day01;
/**
 * finally
 * @author asus
 *
 */
public class FinallyDemo {
	public static void main(String[] args) {
		String str=null;
		str="";
		try{
			System.out.println(str.length());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{//����ִ��
			System.out.println("finally�����е�����");
		}
		System.out.println("�������");
	}
}
