package day05;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * set����������ģ�����û��get����
 * ֻ��ͨ���������ķ�ʽ��ȡԪ��
 * @author asus
 *
 */
public class IterateSet {
	public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		set.add("one");
		set.add("two");
		set.add("three");
		System.out.println(set);
		Iterator<String> it=set.iterator();
		while(it.hasNext()){//����������
			String str=it.next();
			System.out.println(str);
		}
		for(String str:set){//��ǿforѭ������
			System.out.println(str);
		}
	}
}
