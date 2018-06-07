package day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Test05 {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);
		list=Collections.synchronizedList(list);
		Set <String>set=new HashSet<String>(list);
		System.out.println(set);
		Vector <String>vector=new Vector<String>(list);
		System.out.println(vector);
	}
}
