package day01;

public class TestClassLoad {
	//把Foo类装载到内存中（方法区）
	//创建一个对象（堆）
	Foo foo=new Foo();
	//把Foo装载到内存，不创建对象，会运行静态块
}
class Foo{
	static {//只要加载到内存就运行
		System.out.println("load...");
	}
	Foo(){
		System.out.println("Foo()..");
	}
}