package day05;
/**
 * �ɱȽϵĵ�
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
	public int compareTo(Point o){//����ȽϹ���
		/**
		 * ���Ƕ����֮��ıȽϹ���Ϊ
		 * �㵽Զ��ľ���
		 */
		//���㵱ǰ����ĳ���
		int len=x*x+y*y;
		//�������������ĳ���
		int arglen=(int)(Math.pow(o.x, 2)+Math.pow(o.y, 2));
		return len-arglen;//����ֵ�����ľ����ֵ��ֻ����ȡֵ��Χ
		//>0��ǰֵ�ȸ��������
		//<0��ǰֵ�ȸ�������С
		//=0��ǰֵ������������
	}
	/**
	 * ����compareTo�����ķ���ֵӦ����equals�����ķ���ֵ����һ����
	 * ������������equalsΪtrueʱ��compareTo��������ֵΪ0
	 */
}
