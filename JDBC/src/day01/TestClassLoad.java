package day01;

public class TestClassLoad {
	//��Foo��װ�ص��ڴ��У���������
	//����һ�����󣨶ѣ�
	Foo foo=new Foo();
	//��Fooװ�ص��ڴ棬���������󣬻����о�̬��
}
class Foo{
	static {//ֻҪ���ص��ڴ������
		System.out.println("load...");
	}
	Foo(){
		System.out.println("Foo()..");
	}
}