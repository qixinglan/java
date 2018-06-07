package test05;

import java.util.HashSet;
import java.util.Set;

public class Two {
	public static void main(String[] args) {
		Set set=new HashSet();
		Point p1=new Point(1,1);
		Point p2=new Point(1,1);
		set.add(p1);
		set.add(p2);
		System.out.println(p1.equals(p2));
		System.out.println(set);
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
	
}