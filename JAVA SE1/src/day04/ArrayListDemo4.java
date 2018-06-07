package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * get方法获取集合元素
 * 
 * @author asus
 *
 */
public class ArrayListDemo4 {
	public static void main(String[] args){
		List list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		/**
		 * 获取回来时是以object获取的，所以要转型
		 */
		String element=(String)list.get(2);
		System.out.println(element);//2
		for(int i=0;i<list.size();i++){//遍历
			System.out.println(list.get(i));//1 2 3  object类型
			
		}
		/**
		 * 将元素“2"替换为”二“
		 * 索引值不能超过size（）-1
		 */
		Object old=list.set(1, "二");
		System.out.println(list);
		System.out.println(old);//被替换掉的元素
		/**
		 * 指定位置插入给定元素
		 * 原来的元素顺序向后移动
		 * size()+1
		 * list第二个位置插入”一“
		 */
		list.add(1,"一");
		System.out.println(list);
		/**
		 * 删除第三个元素* 
		 * 注意越界问题
		 */
		list.remove(2);//有返回值，返回被删除的元素
		System.out.println(list);
		/**
		 * 查看”3“在集合中的位置
		 */
		long index=list.indexOf("3");
		System.out.println(index);
	}
}
