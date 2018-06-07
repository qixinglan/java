package day06;
/**
 * 模板模式
 * @author asus
 *
 */
public abstract class  Person {
	//介绍自己，打招呼
	public void sayHello(){
		//打招呼
		System.out.println("大家好");
		//介绍自己名字
		System.out.println("我叫"+getName());
		//介绍自己情况
		System.out.println(getInfo());
		//说拜拜
		System.out.println();
	}
	/**
	 * 获取自己名字
	 */
	public abstract String getName();
		
	
	/**
	 * 获取自己情况
	 */
	public abstract String getInfo();
		
	
	/**
	 * 说再见
	 */
	public abstract String sayGoodBye();
		
	
}
