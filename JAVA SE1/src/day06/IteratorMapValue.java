package day06;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ����/��ȡ����value
 * @author asus
 *
 */
public class IteratorMapValue {
	public static void main(String[] args) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("k1", 1);
		map.put("k2", 2);
		map.put("k3", 3);
		map.put("k4", 4);
		map.put("k5", 5);
		Collection<Integer> values=map.values();//����values����
		for(Integer value:values){
			System.out.println("value:"+value);
		}
	}
}
