package day06;
/**
 * 单例模式
 * 当前类的实例只有一个
 * @author asus
 *
 */
public class SingletonDemo {
	private int width;
	private int height;
	private static SingletonDemo obj=new SingletonDemo();//定义一个私有当前类的属性（全局唯一实例）
	private SingletonDemo() {//私有化构造方法，阻止外界new实例
		super();
	}
	public static SingletonDemo getInstance(){//定义一个公用的静态方法（这样才可以通过类名调用）来创建实例
		return obj;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
