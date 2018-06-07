package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合中的泛型
 * 用于约束集合中存放元素类型
 * @author asus
 *
 */
public class ArrayListDemo5 {
	public static void main(String[]args){
		List<String> list=new ArrayList<String>();
		/**
		 * 泛型约束了add方法的参数类型
		 */
		list.add("1");
		list.add("2");
		list.add("3");
		
	}
}
