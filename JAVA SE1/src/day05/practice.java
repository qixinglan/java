package day05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class practice {
	public static void main(String[] args) {
		Set<Integer> set=new HashSet<Integer>();
		while(set.size()<7){
			set.add(new Random().nextInt(37));
		}
		System.out.println(set);
	}
}
