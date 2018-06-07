package test01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;
/**
 * 
 * @author Administrator
 *
 */
/*
 * 
 */


public class Six {
	static String s;
	public static void main(String[] args) {
	int[] a=new int[1];
	System.out.println(a[0]);
	System.out.println(s);
	int[] ary=new int[]{1,2,3};
	int[] ary1=new int[3];
	int[] ary2;
	System.out.println();
	switch('a'){
	case('a'):
		System.out.println(1);
		break;
	case(2):
		System.out.println(2);
		break;
	default:
		System.out.println("null");
	}
	byte c=1;
	byte b=2;
	c+=c+b;
	//c=c+b;
	int d1=3;
	System.out.println((double)d1/2);
	System.out.println((double)(d1/2));
	System.out.println(16>>1);
	String s="abcde";
	System.out.println(s.length());
	System.out.println(s.charAt(0));
	O o=new O() {
		
		@Override
		public void fun() {
			// TODO Auto-generated method stub
			
		}
	};
	Timer timer=new Timer();
	timer.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("3");
		}
	}, 3000);
	timer.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("aaa");
		}
	}, 3000, 1000);
	Calendar cc=Calendar.getInstance();
	cc.set(2015, 6, 7, 14,55,30);
	Date date=cc.getTime();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String ss=sdf.format(date);
	System.out.println(ss);
	timer.schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("ss");
		}
	}, date);
	Properties p=new Properties();
	FileInputStream in;
	try {
		in = new FileInputStream("a1.properties");
		p.load(in);
		System.out.println(p.getProperty("name"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
interface O{
	void fun();
}
abstract class F implements O{
	abstract void fun1();
	public void fun(){
		System.out.println("aa");
	}
}
class D implements O{
	@Override
	public void fun() {
		// TODO Auto-generated method stub
	}
}
class E extends F{

	@Override
	void fun1() {
		// TODO Auto-generated method stub
	}

}
