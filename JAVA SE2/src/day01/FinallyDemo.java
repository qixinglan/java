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
		finally{//必须执行
			System.out.println("finally语句块中的内容");
		}
		System.out.println("程序结束");
	}
}
