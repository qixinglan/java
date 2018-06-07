package day05;

import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
	public static void main(String[]args){
		Deque<String> stack=new LinkedList<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		System.out.println(stack);
		System.out.println(stack.peek());
		String str=null;
		while(stack.peek()!=null){
			str=stack.pop();
			System.out.println(str);
		}
	}
}
