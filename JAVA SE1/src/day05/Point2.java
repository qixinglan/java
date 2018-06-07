package day05;

public class Point2 {
	public static void main(String[] args) {
		A a=new A(1,2);
		
	}
}
class A{
	private int x;
	private int y;
	public A(int x,int y){
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return x+","+y;
	}
}