package test05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Six {
	static String s="123456";
	static int []a=new int[]{1,2,3,4,5,6};
	static int []b=new int[6];
	public static void main(String[] args) {
//		String str = "xafdvs";
//		char[] arr1 = str.toCharArray();
//		char[] arr2 = Arrays.copyOf(arr1,arr1.length);
//		for(int i=0;i<arr1.length-1;i++)
//		{
//		for(int j = i+1;j<arr2.length;j++){
//		System.out.println(arr1[i] + "," + arr2[j]);
//		}
//		}
//		List list=new ArrayList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		list.add(6);
		
		for(int i=0;i<a.length;i++){
			b[i]=fun(6-i);
			System.out.println(Arrays.toString(a));
		}
		System.out.println(Arrays.toString(b));
	}
	static int  fun(int k){
		int r=(int)(Math.random()*k);
		int z=a[r];
		System.out.println(z);
		for(int i=0;i<a.length-1;i++){
			for(int t=1;t<7;t++){
				if(z!=t){
					a[i]=t;
				}
			}
		}
		return z;
	}
}
