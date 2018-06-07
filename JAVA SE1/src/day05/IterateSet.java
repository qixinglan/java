package day05;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * set集合是无序的，所以没有get方法
 * 只能通过迭代器的方式获取元素
 * @author asus
 *
 */
public class IterateSet {
	public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		set.add("one");
		set.add("two");
		set.add("three");
		System.out.println(set);
		Iterator<String> it=set.iterator();
		while(it.hasNext()){//迭代器遍历
			String str=it.next();
			System.out.println(str);
		}
		for(String str:set){//增强for循环遍历
			System.out.println(str);
		}
	}
}
