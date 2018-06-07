package test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Four{
	public static void main(String[] args){
		List<User> list2=new ArrayList();
		list2.add(new User(2));
		list2.add(new User(5));
		list2.add(new User(1));
		list2.add(new User(3));
		list2.add(new User(4)); 
		Collections.sort(list2);
		System.out.println(list2.toString());
		
		
		List<User2> list4=new ArrayList();
		list4.add(new User2(2));
		list4.add(new User2(5));
		list4.add(new User2(1));
		list4.add(new User2(3));
		list4.add(new User2(4)); 
		Comparator<User2> com1=new Comparator<User2>() {
			
			@Override
			public int compare(User2 o1, User2 o2) {
				// TODO Auto-generated method stub
				return o2.getAge()-o1.getAge();
			}
		};
		Collections.sort(list4, com1);
		System.out.println(list4.toString());
	}

	
}
class User implements Comparable{
	private int age;
	public User(int age) {
		super();
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((User)o).getAge()-this.age;
	}
	@Override
	public String toString() {
		return "User [age=" + age + "]";
	}
}

class User2 {
	private int age;
	
	public User2(int age){
		super();
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User2 [age=" + age + "]";
	}
}
