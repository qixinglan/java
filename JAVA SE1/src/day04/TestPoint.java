package day04;

/**
 * 
 * @author asus
 * 
 */
public class TestPoint {
	public static void main(String[] args) {
		Point<Integer, String> p = new Point<Integer, String>(2, "qwer");
		Point<Double, Integer> p1 = new Point<Double, Integer>(3.5, 5);
		//p.setX(3);
		//p.setY("qw");
		set(p);
		int x=p.getX();
		//int y=p.getY();
	}
	public static void set(Point p1){
		p1.setX(3);
		p1.setY(3.1);
	}
}
