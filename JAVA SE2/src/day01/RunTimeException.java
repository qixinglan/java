package day01;
/**
 *抛出 RunTimeException及其子类
 *不需要在该方法中声明throws
 * @author asus
 *
 */
public class RunTimeException {
	public static void main(String[] args) {
		connectionDB();
	}
	public static void connectionDB(){
		throw new RuntimeException("出错");
	}
}
