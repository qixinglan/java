package day04;

import java.util.ArrayList;
import java.util.List;

/*
 * ArrayList
 * List������ʵ��
 * ������ʵ�ֿ��ظ�������
 */
public class ArrayListDemo1 {
	public static void main(String[] args){
		List list=new ArrayList();
		/*
		 * ��Ϊlist�����򼯣�����add����˳���򼯺�ĩβ���Ԫ��
		 */
		list.add("һ");
		list.add("��");
		list.add("��");
		System.out.println("����Ԫ����Ŀ"+list.size());//3
		System.out.println(list);//[һ��������]
		list.clear();//��ռ���
		System.out.println("����Ԫ����Ŀ"+list.size());//0
		System.out.println("�Ƿ�Ϊ�ռ���"+list.isEmpty());
		//List list1=null;
		//System.out.println(list1.isEmpty());//���쳣
	}
}
