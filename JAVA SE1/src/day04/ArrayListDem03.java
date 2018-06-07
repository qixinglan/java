package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合中的批量操作
 * @author asus
 *
 */
public class ArrayListDem03 {
	public static void main(String[] args){
		List list1=new ArrayList();
		List list2=new ArrayList();
		List list3=new ArrayList();
		//向第一个集合中添加元素
		list1.add("1");
		list1.add("2");
		list1.add("3");
		//向集合二中添加元素
		list2.add("4");
		list2.add("5");
		//向集合三中添加元素
		list3.add("1");
		list3.add("2");
		//将集合二中的元素存入集合一
		list1.addAll(list2);
		System.out.println(list1);
		//将集合一中与集合三中相同的元素的删除
		list1.removeAll(list3);
		System.out.println(list1);
		list1.retainAll(list2);
		//只保留集合一中与集合二相同的元素
		System.out.println(list1);
		//以上判断元素是否相同，都是用equals
		}
}
