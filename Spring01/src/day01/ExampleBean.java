package day01;

import java.io.Serializable;


public class ExampleBean implements Serializable{
	String n;
	
	public ExampleBean(){
		n="Tom";
		System.out.println("Call ExampleBean");
	}
	public String GetName(){
		return n;
	}
	public void setName(String name){
		this.n=name;
	}
	@Override
	public String toString() {
		return "ExampleBean [n=" + n + "]";
	}
	public void init(){
		System.out.println("init");
	}
	public void destroy(){
		System.out.println("destrory");
	}
}
