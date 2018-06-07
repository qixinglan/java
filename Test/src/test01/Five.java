package test01;
 class S{
	int age;
	static int salary;
	static {
		salary=3;
		System.out.println("aaa");
	}
	{
		System.out.println("bbb");
		age=4;
	}
	public S(){
		System.out.println("ccc");
	}
	public  void out(){
		System.out.println(salary);
		{
			System.out.println("ddd");
			age=4;
		}
		
	}
	
}
class Five{
	public static void main(String[] args) {
		S s=new S();
		s.out();
	}
}