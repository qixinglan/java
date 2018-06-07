package day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 获取线程安全的集合
 * @author asus
 *
 */
public class SyncAPIDemo {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("three");
		/**
		 * 转换为线程安全的
		 * 所有的集合都支持一个 以参数为：Collection的构造方法
		 * 这个构造方法的作用可以再创建当前集合的基础上，
		 * 将给定的集合元素装入其中
		 * 这个构造方法通常习惯叫它为：集合的复制构造器
		 * 
		 */
		List<String> vector=new Vector<String>(list);
		System.out.println(vector);
		Set<String> hashset=new HashSet<String>(list);
		System.out.println(hashset);//无序，不重复
		
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three",3);
		
		/**
		 * 使用Collection的方法，可以方便的将一个集合或map变成线程安全的
		 * 返回一个线程安全的集合/map
		 */
		System.out.println(Collections.synchronizedList(list));
		System.out.println(Collections.synchronizedSet(hashset));
		System.out.println(Collections.synchronizedMap(map));
	}
}
