package day04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ��������������
 * @author asus
 *
 */
public class IteratorDemo {
	public static void main(String[] args){
		List list=new ArrayList();
		list.add("1");
		list.add("#");
		list.add("2");
		list.add("#");
		list.add("3");
		list.add("#");
		list.add("4");
		//list.add("5");
		
		//��ȡ���ڱ������ϵĵ�����
		Iterator it=list.listIterator();
		while(it.hasNext()){
			//String element=(String)it.next();
			//System.out.println(element);
			if("#".equals((String)it.next())){
				it.remove();
			}
		}
			System.out.println(list);	
	}
}
