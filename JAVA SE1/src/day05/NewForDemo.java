package day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ��ѭ��
 * @author asus
 *
 */
public class NewForDemo {
	public static void main(String[] args){
		int[] array={1,2,3,4,5,6,7,8};
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]+1);
		}
		//��ѭ��for��������
		for(int i:array){
			System.out.println(i+1);
		}
		/**
		 * ��ѭ����������
		 */
		Collection<String> list=new ArrayList<String>();
		list.add("һ");
		list.add("��");
		list.add("��");
		list.add("��");
		list.add("��");
		for(String str:list){
			System.out.println(str);
			//list.remove(str);�����쳣
		}
	}
}
