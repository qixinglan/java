package test01;

import java.util.Arrays;

public class Seven {
	public static void main(String[] args) {
		int [] array1=new int[3];
		int [] array2=new int[]{1,2,3};
		int [] array3={5,6,7};
		System.out.println(array2==array3);
		array1=array2;
		System.out.println(Arrays.toString(array1));
		array2[0]=4;
		System.out.println(Arrays.toString(array1));
		System.arraycopy(array1, 0, array3, 1, 1);
		System.out.println(Arrays.toString(array3));
		int array4[]=Arrays.copyOf(array3,array3.length+1);
		array4[3]=8;
		System.out.println(Arrays.toString(array4));
	}
}
