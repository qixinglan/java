package day01;
/**
 * finally用途
 * @author asus
 *
 */
public class FinallyDemo2 {
	public static void main(String[] args) {
		try{
			String age="12";
			age="ABC";
			System.out.println("连接数据库");
			System.out.println("保存数据"+Integer.parseInt(age));
			//System.out.println("关闭数据库");
		}catch(Exception e){
			System.out.println("出现错误");
		}
		finally{
			System.out.println("关闭数据库");
		}
		System.out.println("程序退出");
	}
}
