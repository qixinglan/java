package day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author asus
 *
 */
public class CamparatorDemo {
	public static void main(String[] args) {
		List<String>list=new ArrayList<String>(0);
		list.add("able");
		list.add("adam");
		list.add("BOSS");
		list.add("killer");
		list.add("TOM");
		System.out.println(list);
		Collections.sort(list);//调用默认比较规则
		System.out.println(list);
		Collections.sort(list,new MyComparator());//调用用自定义比较器
		System.out.println(list);
		//通常我们采用匿名类的方式创建自定义比较器
		Comparator<String> comparator=new Comparator<String>(){
			public int compare(String o1,String o2){
				return o2.length()-o1.length();
			}
		};
		Collections.sort(list, comparator);
		System.out.println(list);
	}
}

//自定义比较器
class MyComparator implements Comparator<String>{
	/**
	 * 比较规则
	 * 当返回值>0:o1>02
	 * 当返回值<0:o1<o2
	 * 当返回值=0:o1=o2
	 */
	public int compare (String o1,String o2){
		return o1.length()-o2.length();
	}
	
}
