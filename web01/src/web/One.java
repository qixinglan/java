package web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class One {
	int x=10;
	static int y[]=new int[3];
	static int y1;
public static void main(String[] args) {
	System.out.println(2<<3);
	System.out.println(y1);
	System.out.println(y[0]);
	System.out.println(2.0-1.1);
	System.out.println(1.0-0.1);
	List<String> a=new ArrayList<String>();
	a.add("a");
	a.add("b");
	System.out.println(a);
	String []b={"a","b","c"};
	System.out.println(Arrays.toString(b));
	One one=new One();
}
  One(){
	  print();
	  x=30;
			  }
  void print(){
	  System.out.println(x);
  }
  static void aa(){
	  
  }
  final void bb(){
	  
  }
  
}
class b extends One{
	static void aa(){
		System.out.println("aa");  
	  }
	final void bb(int a){
		  
	  }
}
