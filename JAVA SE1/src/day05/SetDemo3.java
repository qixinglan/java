package day05;

import java.util.HashSet;
import java.util.Set;

/**
 * hashset的添加元素
 * @author asus
 *
 */
public class SetDemo3 {
	public static void main(String[] args) {
		Set<String>set=new HashSet<String>();
		set.add("三");
		set.add("四");
		set.add("五");
		set.add("一");
		set.add("二");
		System.out.println(set);
		//set因为无序，所以没有get方法
	}
}
