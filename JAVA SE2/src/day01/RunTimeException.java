package day01;
/**
 *�׳� RunTimeException��������
 *����Ҫ�ڸ÷���������throws
 * @author asus
 *
 */
public class RunTimeException {
	public static void main(String[] args) {
		connectionDB();
	}
	public static void connectionDB(){
		throw new RuntimeException("����");
	}
}
