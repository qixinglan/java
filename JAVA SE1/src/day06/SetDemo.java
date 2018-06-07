package day06;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
	public static void main(String[] args) {
		Set<Point>set=new HashSet<Point>();
		set.add(new Point(1,2));
		System.out.println(set.size());
		set.add(new Point(3,6));
		System.out.println(set.size());
		System.out.println(set);
	}
}
