package day05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 利用set集合不重复性，生成1——100之间不重复随机数
 * @author asus
 *
 */
public class SetDemo2 {
	public static void main(String[]args){
		Set<Integer> set=new HashSet<Integer>();
		Random r=new Random();
		int index=0;
		while(set.size()<20){
			set.add(r.nextInt(100)+1);
			index++;
		}
		System.out.println(set);
		System.out.println(index);
	}
}
