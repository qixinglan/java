package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * get������ȡ����Ԫ��
 * 
 * @author asus
 *
 */
public class ArrayListDemo4 {
	public static void main(String[] args){
		List list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		/**
		 * ��ȡ����ʱ����object��ȡ�ģ�����Ҫת��
		 */
		String element=(String)list.get(2);
		System.out.println(element);//2
		for(int i=0;i<list.size();i++){//����
			System.out.println(list.get(i));//1 2 3  object����
			
		}
		/**
		 * ��Ԫ�ء�2"�滻Ϊ������
		 * ����ֵ���ܳ���size����-1
		 */
		Object old=list.set(1, "��");
		System.out.println(list);
		System.out.println(old);//���滻����Ԫ��
		/**
		 * ָ��λ�ò������Ԫ��
		 * ԭ����Ԫ��˳������ƶ�
		 * size()+1
		 * list�ڶ���λ�ò��롱һ��
		 */
		list.add(1,"һ");
		System.out.println(list);
		/**
		 * ɾ��������Ԫ��* 
		 * ע��Խ������
		 */
		list.remove(2);//�з���ֵ�����ر�ɾ����Ԫ��
		System.out.println(list);
		/**
		 * �鿴��3���ڼ����е�λ��
		 */
		long index=list.indexOf("3");
		System.out.println(index);
	}
}
