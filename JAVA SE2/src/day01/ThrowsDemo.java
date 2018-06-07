package day01;
/**
 * throws
 * @author asus
 *
 */
public class ThrowsDemo {
	public static void main(String[] args) {
		try{
		connectionDB("192.168.1.");// 连接数据库
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("保存数据");//保存数据
		System.out.println("注册成功");//通知用户
	}
	public static void connectionDB(String url)throws Exception{//可以throws多个异常，用“，”隔开
		if("192.168.1.1".equals(url)){
			System.out.println("数据库连接成功");
		}else{
			throw new Exception("连接数据库异常");//连接失败，模拟报错
		}
	}
}
