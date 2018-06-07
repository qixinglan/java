package day04;

import java.util.ArrayList;
import java.util.List;

/*
 * ArrayList
 * List的子类实现
 * 由数组实现可重复的有序集
 */
public class ArrayListDemo1 {
	public static void main(String[] args){
		List list=new ArrayList();
		/*
		 * 因为list是有序集，所以add方法顺序向集合末尾添加元素
		 */
		list.add("一");
		list.add("二");
		list.add("三");
		System.out.println("集合元素数目"+list.size());//3
		System.out.println(list);//[一，二，三]
		list.clear();//清空集合
		System.out.println("集合元素数目"+list.size());//0
		System.out.println("是否为空集合"+list.isEmpty());
		//List list1=null;
		//System.out.println(list1.isEmpty());//抛异常
	}
}
