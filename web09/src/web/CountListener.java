package web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//����������ʱ��ᴴ��������ʵ��,ֻ��һ��ʵ�� 
public class CountListener implements HttpSessionListener{
	private int count=0;
	/*
	 * HttpSessionEvent��һ���¼�����
	 * ͨ�������ǿ��Ի���¼�����ʱ�ĸ�����Ϣ
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("session  crate");
		count++;
		HttpSession session=arg0.getSession();
		session.getServletContext().setAttribute("count", count);
		System.out.println("count"+count);
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("session delete");
		count--;
	}

	public int getCount() {
		return count;
	}
	

}
