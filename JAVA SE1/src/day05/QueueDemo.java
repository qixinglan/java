package day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ����
 * ��ȡһ�����ݣ���ȡ���ݱ����Ƚ��ȳ���ԭ��
 * @author asus
 *
 */
public class QueueDemo {
	public static void main(String[]args){
		Queue<String> queue=new LinkedList<String>();
		queue.offer("A");
		queue.offer("B");
		queue.offer("C");
		System.out.println(queue);
		System.out.println(queue.peek());//�鿴����Ԫ��
		//System.out.println(queue.poll());
		/**
		 * ���еı�����һ���Ե� 
		 * ֻ��ȡ������Ԫ�أ����ܻ�ȡ
		 */
		String str=null;
		while((str=queue.poll())!=null){
			System.out.println(str);
		}
	}
}
