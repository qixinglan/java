package day05;

import java.util.HashSet;
import java.util.Set;

/**
 * hashset������Ԫ��
 * @author asus
 *
 */
public class SetDemo3 {
	public static void main(String[] args) {
		Set<String>set=new HashSet<String>();
		set.add("��");
		set.add("��");
		set.add("��");
		set.add("һ");
		set.add("��");
		System.out.println(set);
		//set��Ϊ��������û��get����
	}
}