package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试使用List添加自定义类型元素
 * @author asus
 *
 */
public class ArrayListDemo2 {
	public static void main(String[] args){
		List list=new ArrayList();//创建集合
		list.add(new Point(1,2));
		list.add(new Point(3,4));
		list.add(new Point(5,6));
		System.out.println(list);//
		Point p=new Point(1,2);
		/**
		 * 包含的比较式基于eauals
		 */
		if(list.contains(p)){
			System.out.println("包含");
			/**
			 * remove方法删除集合中第一个与给定对象p形同的元素
			 * 从第一个开始找，equals是true的第一个元素
			 */
			list.remove(p);
		}else{
			System.out.println("不包含");
		}
		System.out.println(list);
	}
}
