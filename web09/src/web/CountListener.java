package web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//容器启动的时候会创建监听器实例,只有一个实例 
public class CountListener implements HttpSessionListener{
	private int count=0;
	/*
	 * HttpSessionEvent是一个事件对象，
	 * 通过它我们可以获得事件发生时的各种信息
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
