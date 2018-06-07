package day03;
/*°ü×°Àà
 * 
 */
public class IntegerDemo1 {
	public static void main(String[] args){
	Integer i=new Integer(1);
	System.out.println(i);
	Integer i1=new Integer(1);
	Integer i2=Integer.valueOf(1);
	Integer i3=Integer.valueOf(1);
	System.out.println(i1==i);//false
	System.out.println(i2==i3);//true
	int i4=i.intValue();
	Integer i5=8;
	int a=i5;
	Integer aa=1;
	}
}
