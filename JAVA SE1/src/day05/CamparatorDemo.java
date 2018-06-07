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
		Collections.sort(list);//����Ĭ�ϱȽϹ���
		System.out.println(list);
		Collections.sort(list,new MyComparator());//�������Զ���Ƚ���
		System.out.println(list);
		//ͨ�����ǲ���������ķ�ʽ�����Զ���Ƚ���
		Comparator<String> comparator=new Comparator<String>(){
			public int compare(String o1,String o2){
				return o2.length()-o1.length();
			}
		};
		Collections.sort(list, comparator);
		System.out.println(list);
	}
}

//�Զ���Ƚ���
class MyComparator implements Comparator<String>{
	/**
	 * �ȽϹ���
	 * ������ֵ>0:o1>02
	 * ������ֵ<0:o1<o2
	 * ������ֵ=0:o1=o2
	 */
	public int compare (String o1,String o2){
		return o1.length()-o2.length();
	}
	
}
