package day06;
/**
 * ����ģʽ
 * ��ǰ���ʵ��ֻ��һ��
 * @author asus
 *
 */
public class SingletonDemo {
	private int width;
	private int height;
	private static SingletonDemo obj=new SingletonDemo();//����һ��˽�е�ǰ������ԣ�ȫ��Ψһʵ����
	private SingletonDemo() {//˽�л����췽������ֹ���newʵ��
		super();
	}
	public static SingletonDemo getInstance(){//����һ�����õľ�̬�����������ſ���ͨ���������ã�������ʵ��
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
