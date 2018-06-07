package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.userdao;
import entity.user;

public class login extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String url=request.getRequestURI();
		String location=url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
		if(location.equals("/login")){
			response.setContentType("text/html;charset=utf-8");
			String name=request.getParameter("name");
			String pwd=request.getParameter("pwd");
			userdao udao=new userdao();
			user user1;
			try {
				user1 = udao.findByname(name);
				System.out.println(name);
				System.out.println(user1.getPwd());
				if(user1!=null &&pwd.equals(user1.getPwd())){
					request.getSession().setAttribute("user", user1);
					response.sendRedirect("main.jsp");
				}
				else{
					request.setAttribute("login", "用户名或密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
		else if(location.equals("/image")){
			int width=80;
			int height=30;
			BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			Graphics g=image.getGraphics();
			Random r=new Random();
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.fillRect(0, 0, width, height);
//			String number="";
//			for(int i=0;i<5;i++){
//				String s[]={(char)(48+r.nextInt(10))+"",(char)(65+r.nextInt(26))+"",(char)(97+r.nextInt(26))+""};
//				number+=s[r.nextInt(3)];
//			}
			String temp="0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
			String number="";
			for(int i=0;i<5;i++){
				number+=temp.charAt(r.nextInt(62));
			}
			HttpSession session=request.getSession();
			session.setAttribute("yanzhengma", number);
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.setFont(new Font("宋体",Font.BOLD|Font.ITALIC , 18));
			g.drawString(number, 5,20);
			for(int i=0;i<5;i++){
				g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				g.drawLine(r.nextInt(width), r.nextInt(height),r.nextInt(width) , r.nextInt(height));
			}
			response.setContentType("image/jpeg");
			OutputStream ops=response.getOutputStream();
			ImageIO.write(image, "jpeg",ops);
			ops.close();
		}
		else if(location.equals("/regist")){
			response.setContentType("text/html;charset=utf-8");
			String number=request.getParameter("number");
			HttpSession session=request.getSession();
			String s=(String)session.getAttribute("yanzhengma");
			System.out.println(s);
			userdao dao=new userdao();
			String username=request.getParameter("username");
			try {
				if(number.equalsIgnoreCase(s)&&dao.newuser(username)){
					user u=new user();
					String pwd=request.getParameter("pwd");
					int age=Integer.parseInt(request.getParameter("age"));
					u.setAge(age);
					u.setName(username);
					u.setPwd(pwd);
					System.out.println(age+username+pwd);
					
					dao.insert(u);
					session.setAttribute("user", u);//记录已登陆状态
					response.sendRedirect("main.jsp");
					}
				else{
					response.sendRedirect("regist.jsp");
					
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
	

}
