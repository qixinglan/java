package test01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Eleven {
	public static void main(String[] args) {
		List list1=new ArrayList();
		list1.add(1);
		list1.remove(0);
		System.out.println(list1);
		List list2=new ArrayList();
		list2.add(1);
		Iterator it=list2.iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}
		System.out.println(list2);
		List list3=new ArrayList();
		list3.add(1);
		list3.add(2);
		System.out.println(list3.get(0));
		System.out.println(list3.set(1, 3));
		System.out.println(list3);
		list3.add(1,4);
		System.out.println(list3);
		System.out.println(list3.indexOf(3));
		System.out.println(list3.lastIndexOf(3));
		List list4=list3.subList(0,2);
		System.out.println(list4);
		list4.remove(0);
		System.out.println(list4);
		System.out.println(list3);
		List<Integer> list5=new ArrayList<Integer>();
		list5.add(1);
		list5.add(2);
		list5.add(3);
		list5.add(4);
		for(int i:list5){
			//list5.remove(i);//新循环不能修改删除
			System.out.println(i);
		}
		System.out.println(list5);
		Queue<Integer> queue1=new LinkedList<Integer>();
		queue1.add(1);
		queue1.offer(2);
		System.out.println(queue1);
		System.out.println(queue1.peek());
		Iterator it1=queue1.iterator();
		while(it1.hasNext()){
			System.out.println(it1.next());
		}
		for(Integer i:queue1){
			System.out.println(i);
		}
		Set set1=new HashSet();
		set1.add(1);
		set1.add(2);
		System.out.println(set1.add(1));
		System.out.println(set1);
		Set<Point> set2=new HashSet<Point>();
		set2.add(new Point(1,1));
		set2.add(new Point(1,1));
		Point p1=new Point(3, 3);
		set2.add(p1);
		p1.setX(4);
		set2.add(p1);
		p1.setX(5);
		System.out.println(set2);
		for(Point p:set2){
			System.out.println(p);
		}
		//遍历Map
		Map<Point,Integer> map1=new HashMap<Point,Integer>();
		map1.put(new Point(1,1),1);
		map1.put(new Point(2,2),2);
		map1.put(new Point(3,3),3);
		map1.put(new Point(1,1),1);
		System.out.println(map1);
		Set<Point> set3=map1.keySet();
		for(Point s:set3){
			System.out.println(map1.get(s));
		}
		Iterator it2=set3.iterator();
		while(it2.hasNext()){
			System.out.println(map1.get(it2.next()));
		}
		Set<Entry<Point,Integer>> set4=map1.entrySet();
		for(Entry s:set4){
			System.out.println(s.getValue());
		}
		Collection<Integer> c=map1.values();
		for(Integer i:c){
			System.out.println(i);
		}
	}
	public void fun(){
		throw new RuntimeException();
	}
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
