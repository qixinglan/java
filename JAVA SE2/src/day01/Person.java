package day01;

import javax.management.RuntimeErrorException;

public class Person {
	int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age>100){
			throw new  RuntimeException("���䲻�Ϸ�");
		}
		this.age = age;
	}
	public static void main(String[] args) {
		Person p=new Person();
		try{
			p.setAge(1000);
		}catch(Exception e){
			System.out.println(e.getMessage());//����쳣�Ķ�ջ��Ϣ�������쳣
			e.printStackTrace();//�Լ��������
			System.out.println(e.getMessage().toString());
		}
		
	}
}
