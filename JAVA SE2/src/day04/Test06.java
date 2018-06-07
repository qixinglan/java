package day04;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Test06 {
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		for(Object o:list){
			System.out.println(o);
		}
		Iterator i=list.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
			
		}
		for(Object o:list){
			System.out.println(o+"aa");
		}
		Set set=new HashSet();
		set.add(1);
		set.add(2);
		set.add(3);
		for(Object o:set){
			System.out.println(o);
		}
		Iterator i1=set.iterator();
		while(i1.hasNext()){
			System.out.println(i1.next());
			i1.remove();
		}
		Map map=new HashMap();
		map.put(1, "11");
		map.put(2, "22");
		map.put(3, "33");
		Set set1=map.keySet();
		for(Object o:set1){
			System.out.println(map.get(o));
		}
		Queue queue=new LinkedList();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue.peek());
		for(Object o:queue){
			System.out.println(o);
		}
		Iterator i2=queue.iterator();
		while(i2.hasNext()){
			System.out.println(i2.next()+"ss");
		}
		Object o;
		while((o=queue.poll())!=null){
			System.out.println(o);
		}
		System.out.println(queue);
		Deque deque=new LinkedList();
		deque.push(1);
		deque.push(2);
		deque.push(3);
		System.out.println(deque);
		System.out.println(deque.pop());
		System.out.println(deque);
		
	}
}
