package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * �����е���������
 * @author asus
 *
 */
public class ArrayListDem03 {
	public static void main(String[] args){
		List list1=new ArrayList();
		List list2=new ArrayList();
		List list3=new ArrayList();
		//���һ�����������Ԫ��
		list1.add("1");
		list1.add("2");
		list1.add("3");
		//�򼯺϶������Ԫ��
		list2.add("4");
		list2.add("5");
		//�򼯺��������Ԫ��
		list3.add("1");
		list3.add("2");
		//�����϶��е�Ԫ�ش��뼯��һ
		list1.addAll(list2);
		System.out.println(list1);
		//������һ���뼯��������ͬ��Ԫ�ص�ɾ��
		list1.removeAll(list3);
		System.out.println(list1);
		list1.retainAll(list2);
		//ֻ��������һ���뼯�϶���ͬ��Ԫ��
		System.out.println(list1);
		//�����ж�Ԫ���Ƿ���ͬ��������equals
		}
}
