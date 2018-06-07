package day05;

import java.util.ArrayList;
import java.util.List;

/**
 * 取子集
 * @author asus
 *
 */
public class SubListDemo {
	public static void main(String[] args){
		List<Integer>list=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		System.out.println(list);
		List<Integer> sublist=list.subList(3, 8);//含头不含尾
		System.out.println(sublist);
		//将子集扩大十倍
		for(int i=0;i<sublist.size();i++){
			sublist.set(i, sublist.get(i)*10);
		}
		System.out.println(sublist);
		System.out.println(list);//对子集的修改会影响原集合
	}
}
