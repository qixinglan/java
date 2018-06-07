
package day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author asus
 *
 */
public class SortList {
	public static void main(String[] args){
		List<Point> list=new ArrayList<Point>();
		list.add(new Point(1,5));
		list.add(new Point(3,4));
		list.add(new Point(2,2));
//		List<String>list=new ArrayList<String>(0);
//		list.add("able");
//		list.add("adam");
//		list.add("BOSS");
//		list.add("killer");
//		list.add("TOM");
//		
		System.out.println(list);
		Collections.sort(list);//×ÔÈ»ÅÅĞò
		System.out.println(list);
		System.out.println(new Point(1,1).compareTo(new Point (2,2)));
	}
}
