package day05;

import java.util.ArrayList;
import java.util.List;

/**
 * ȡ�Ӽ�
 * @author asus
 *
 */
public class SubListDemo {
	public static void main(String[] args){
		List<Integer>list=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		System.out.println(list);
		List<Integer> sublist=list.subList(3, 8);//��ͷ����β
		System.out.println(sublist);
		//���Ӽ�����ʮ��
		for(int i=0;i<sublist.size();i++){
			sublist.set(i, sublist.get(i)*10);
		}
		System.out.println(sublist);
		System.out.println(list);//���Ӽ����޸Ļ�Ӱ��ԭ����
	}
}
