package day06;

public class TestSingletDemo {
	public static void main(String[] args) {
		SingletonDemo o1=SingletonDemo.getInstance();
		o1.setHeight(200);
		o1.setWidth(400);
		SingletonDemo o2=SingletonDemo.getInstance();
		System.out.println(o2.getHeight()+","+o2.getWidth());
		
	}
}
