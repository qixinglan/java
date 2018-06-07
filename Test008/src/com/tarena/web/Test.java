package com.tarena.web;



public class Test {
	 int a=10;
	void show(){
		System.out.println(a);
	}
	public static void main(String[] args) {
		A a=new A();
		a.show();
		Test t=new A();
		System.out.println(t.a);
	}
}
class A extends Test{
	int a=18;
	void show(){
		super.show();
		System.out.println(a);
		System.out.println(super.a);
	}
}
 