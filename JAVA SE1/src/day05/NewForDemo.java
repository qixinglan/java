package day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 新循环
 * @author asus
 *
 */
public class NewForDemo {
	public static void main(String[] args){
		int[] array={1,2,3,4,5,6,7,8};
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]+1);
		}
		//新循环for遍历数组
		for(int i:array){
			System.out.println(i+1);
		}
		/**
		 * 新循环遍历集合
		 */
		Collection<String> list=new ArrayList<String>();
		list.add("一");
		list.add("二");
		list.add("三");
		list.add("四");
		list.add("五");
		for(String str:list){
			System.out.println(str);
			//list.remove(str);运行异常
		}
	}
}
