package day06;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * ������ȡkey-value
 * @author asus
 *
 */
public class IteratorMapEntry {
	public static void main(String[] args) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("k1", 1);
		map.put("k2", 2);
		map.put("k3", 3);
		map.put("k4", 4);
		map.put("k5", 5);
		Set<Entry<String,Integer>> entries=map.entrySet();//����entryset
		Iterator<Entry<String,Integer>> it=entries.iterator();//������
		while(it.hasNext()){//���������
			Entry<String,Integer> entry=it.next();
			System.out.println(entry.getKey()+","+entry.getValue());
		}
		
	}
	
}
