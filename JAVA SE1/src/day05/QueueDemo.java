package day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列
 * 存取一组数据，存取数据本着先进先出的原则
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
		System.out.println(queue.peek());//查看队首元素
		//System.out.println(queue.poll());
		/**
		 * 队列的遍历是一次性的 
		 * 只有取出队首元素，才能获取
		 */
		String str=null;
		while((str=queue.poll())!=null){
			System.out.println(str);
		}
	}
}
