package day05;
/**
 * 可比较的点
 * @author asus
 *
 */
public class Point implements Comparable<Point>{
	private int x;
	private int y;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public String toString(){
		return x+","+y;
	}
	public int compareTo(Point o){//定义比较规则
		/**
		 * 我们定义点之间的比较规则为
		 * 点到远点的距离
		 */
		//计算当前对象的长度
		int len=x*x+y*y;
		//计算给定参数点的长度
		int arglen=(int)(Math.pow(o.x, 2)+Math.pow(o.y, 2));
		return len-arglen;//返回值不关心具体的值，只关心取值范围
		//>0当前值比给定对象大
		//<0当前值比给定对象小
		//=0当前值与给定对象相等
	}
	/**
	 * 这里compareTo方法的返回值应该与equals方法的返回值具有一致性
	 * 即当两个对象equals为true时，compareTo方法返回值为0
	 */
}
