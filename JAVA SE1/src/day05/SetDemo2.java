package day05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * ����set���ϲ��ظ��ԣ�����1����100֮�䲻�ظ������
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
