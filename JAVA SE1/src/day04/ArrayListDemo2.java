package day04;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ʹ��List����Զ�������Ԫ��
 * @author asus
 *
 */
public class ArrayListDemo2 {
	public static void main(String[] args){
		List list=new ArrayList();//��������
		list.add(new Point(1,2));
		list.add(new Point(3,4));
		list.add(new Point(5,6));
		System.out.println(list);//
		Point p=new Point(1,2);
		/**
		 * �����ıȽ�ʽ����eauals
		 */
		if(list.contains(p)){
			System.out.println("����");
			/**
			 * remove����ɾ�������е�һ�����������p��ͬ��Ԫ��
			 * �ӵ�һ����ʼ�ң�equals��true�ĵ�һ��Ԫ��
			 */
			list.remove(p);
		}else{
			System.out.println("������");
		}
		System.out.println(list);
	}
}
