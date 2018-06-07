package day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * ��ȡ�̰߳�ȫ�ļ���
 * @author asus
 *
 */
public class SyncAPIDemo {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("three");
		/**
		 * ת��Ϊ�̰߳�ȫ��
		 * ���еļ��϶�֧��һ�� �Բ���Ϊ��Collection�Ĺ��췽��
		 * ������췽�������ÿ����ٴ�����ǰ���ϵĻ����ϣ�
		 * �������ļ���Ԫ��װ������
		 * ������췽��ͨ��ϰ�߽���Ϊ�����ϵĸ��ƹ�����
		 * 
		 */
		List<String> vector=new Vector<String>(list);
		System.out.println(vector);
		Set<String> hashset=new HashSet<String>(list);
		System.out.println(hashset);//���򣬲��ظ�
		
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three",3);
		
		/**
		 * ʹ��Collection�ķ��������Է���Ľ�һ�����ϻ�map����̰߳�ȫ��
		 * ����һ���̰߳�ȫ�ļ���/map
		 */
		System.out.println(Collections.synchronizedList(list));
		System.out.println(Collections.synchronizedSet(hashset));
		System.out.println(Collections.synchronizedMap(map));
	}
}
