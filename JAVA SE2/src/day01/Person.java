package day01;

import javax.management.RuntimeErrorException;

public class Person {
	int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age>100){
			throw new  RuntimeException("年龄不合法");
		}
		this.age = age;
	}
	public static void main(String[] args) {
		Person p=new Person();
		try{
			p.setAge(1000);
		}catch(Exception e){
			System.out.println(e.getMessage());//输出异常的堆栈信息，或者异常
			e.printStackTrace();//自己主动输出
			System.out.println(e.getMessage().toString());
		}
		
	}
}
