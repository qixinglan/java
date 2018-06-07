package day06;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 遍历所有key
 * @author asus
 *
 */
public class IterateMapKey {
	public static void main(String[] args) {
		Map<String,Integer>map=new HashMap<String,Integer>();
		//添加五个元素
		map.put("k1", 1);
		map.put("k2", 2);
		map.put("k3", 3);
		map.put("k4", 4);
		map.put("k5", 5);
		Set<String> keys=map.keySet();//返回一个set集合
		for(String key:keys){
			System.out.println(key);
			System.out.println(map.get(key));
		}
		for(Iterator<String> it=keys.iterator();it.hasNext();){
			String key=it.next();
			System.out.println(key);
			System.out.println(map.get(key));
		}
	}
}
