package day04;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * ������ת��Ϊָ������
 * @author asus
 *
 */
public class ToArrayDemo {
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add("one");
		list.add("two");
		list.add("three");
		Object[] obj=list.toArray();
		String[] str={"1"};
		String[] strArray=(String[])list.toArray(str);
		System.out.println(Arrays.toString(strArray));
	}
	
}
