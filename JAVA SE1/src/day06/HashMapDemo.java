package day06;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
	public static void main(String[] args) {
		Map<String,Point> points=new HashMap<String,Point>();
		points.put("1,2", new Point(1,2));//map���Ԫ��
		points.put("3,4", new Point(3,4));//map���Ԫ��
		points.put("5,6", new Point(5,6));//map���Ԫ��
		//��ȡ5,6�����
		System.out.println(points.get("5,6").getX()+","+points.get("5,6").getY());
	}
	
}
