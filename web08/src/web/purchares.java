package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.cart;
import bean.cartItem;

import dao.produceDAO;
import entity.produce;

public class purchares extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		produceDAO dao=new produceDAO();
		
		if(action.equals("/list")){
			
			try {
				List<produce> list=dao.findall();
				request.setAttribute("producelist", list);
				request.getRequestDispatcher("computerlist.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("/buy")){
			try {
				
				int id=Integer.parseInt(request.getParameter("id"));
				produce p=dao.findByid(id);
				cartItem c=new cartItem(p, 1);
				cart o=(cart)session.getAttribute("che");
				if(o==null){
					cart cart1=new cart();
					cart1.add(c);
					session.setAttribute("che", cart1);
				}
				else{
					o.add(c);
					session.setAttribute("che",o);
				}
				response.sendRedirect("list.do");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(action.equals("/cart")){
			request.setAttribute("cardlist",session.getAttribute("che"));
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
		else if(action.equals("/update")){
			int id=Integer.parseInt(request.getParameter("id"));
			int qty=Integer.parseInt(request.getParameter("qty"));
			cart c=(cart)session.getAttribute("che");
			c.modify(id, qty);
			response.sendRedirect("cart.do");
		}
		else if(action.equals("/delete")){
			int id=Integer.parseInt(request.getParameter("id"));
			cart c=(cart)session.getAttribute("che");
			c.delete(id);
			response.sendRedirect("cart.do");
		}
		else if(action.equals("/clear")){
			cart c=(cart)session.getAttribute("che");
			c.clear();
			response.sendRedirect("cart.do");
		}
		out.close();
	}

}
