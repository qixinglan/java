package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.AdminiDao;
import dao.Cart;
import dao.UserShopR;
import dao.bookdao;
import dao.datecartdao;
import dao.shoprecorddao;
import dao.userdao;
import entity.Administrator;
import entity.Book;
import entity.DateCart;
import entity.ShopRecord;
import entity.User;

public class actions extends HttpServlet{

	Administrator ad=null;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		System.out.println(action);
		if("main".equals(action)){
			response.setContentType("text/html;charset=utf-8");
			//request.getSession().removeAttribute("cart");
			response.sendRedirect("http://127.0.0.1:8081/bookWeb/main/main.jsp");
			
		}
		if("introduce1".equals(action)){
			int bid=Integer.parseInt(request.getParameter("bid"));
			bookdao bookdao=new bookdao();
			System.out.println(bid);
			try {
				Book book=bookdao.findById(bid);
				request.setAttribute("book",book);
				request.getRequestDispatcher("introduce/introduce1.jsp?bid="+bid).forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("introduce2".equals(action)){
			String type=request.getParameter("type");
			int currpage=1;
			try{
			currpage=Integer.parseInt(request.getParameter("currpage"));
			}catch(Exception e){
			currpage=1;	
			}
			type=new String(type.getBytes("ISO-8859-1"),"utf-8");
			bookdao bookdao=new bookdao();
			try {
				if("全部".equals(type)){
					List<Book> list=bookdao.findAll();
					request.setAttribute("book",list);
					request.getRequestDispatcher("introduce/introduce2.jsp?type="+type+"&currpage="+currpage).forward(request, response);
				}
				else{
				List<Book> list=bookdao.findByType(type);
				request.setAttribute("book",list);
				request.getRequestDispatcher("introduce/introduce2.jsp?type="+type+"&currpage="+currpage).forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("code".equals(action)){//产生校验码程序
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
			String temp="03456789qwertyuiopasdfghjkxcvbnmQWERTYUOPASDFGHJKLXCVBNM";
			String number="";
			for(int i=0;i<5;i++){
				number+=temp.charAt(r.nextInt(55));
			}
			HttpSession session=request.getSession();
			session.setAttribute("yanzhengma", number);
			System.out.println(number);
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.setFont(new Font("宋体",Font.BOLD|Font.ITALIC , 22));
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
		if("regist".equals(action)){//注册
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			String email=request.getParameter("email");
			String number=request.getParameter("codema");
			HttpSession session=request.getSession();
			System.out.println(number);
			if(number.equalsIgnoreCase((String)session.getAttribute("yanzhengma"))){//验证码检验
				try {
					userdao userdao=new userdao();
					List<User> list=userdao.findAll();
					boolean bool=true;
					    if(list!=null){
							for(User u:list){
								if(u.getUname().equals(username)){//用户名检验
									bool=false;
									response.sendRedirect("login/usernameerror.jsp");
								}
							}
					    }
						if(bool){
							User user=new User();
							user.setUname(username);
							user.setEmail(email);
							user.setPassword(pwd);
							System.out.println("1");
							System.out.println(user.toString());
							userdao.add(user);
							System.out.println("2");
							response.sendRedirect("login/registsuccess.html");
							
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				
			}
			else{
//				request.setAttribute("codemaerror", "验证码错误");
//				request.getRequestDispatcher("login/codemaerror.jsp").forward(request, response);
				response.sendRedirect("login/codemaerror.jsp");
			}
		}
		if("fixuser".equals(action)){//用户信息修改
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			String email=request.getParameter("email");
			String number=request.getParameter("codema");
			HttpSession session=request.getSession();
			System.out.println(number);
			if(number.equalsIgnoreCase(((String)session.getAttribute("yanzhengma")))){//验证码检验
				
				try {
					userdao userdao=new userdao();
					List<User> list=userdao.findAll();
					boolean bool=true;
					    if(list!=null){
							for(User u:list){
								if(u.getUname().equals(username)){//用户名检验
									bool=false;
									response.sendRedirect("http://127.0.0.1:8081/bookWeb/user/fixusererror.jsp");
								}
							}
					    }
						if(bool){
							User ouser=(User)session.getAttribute("user");
							int u_id=ouser.getUid();//获得原来用户的id
							User user=new User();
							user.setUname(username);
							user.setEmail(email);
							user.setPassword(pwd);
							user.setUid(u_id);//给新修改的用户同样的id
							userdao.updateByU_id(user);//修改用户信息
							session.removeAttribute("user");//用户成功修改个人信息后，要注销登录
							session.removeAttribute("cart");
							response.sendRedirect("http://127.0.0.1:8081/bookWeb/user/fixusersuccess.html");
							
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				
			}
			else{
//				request.setAttribute("codemaerror", "验证码错误");
//				request.getRequestDispatcher("login/codemaerror.jsp").forward(request, response);
				response.sendRedirect("user/codemaerror.jsp");
			}
		}
		if("login".equals(action)){//登录检验
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			userdao userdao=new userdao();
			try {
				List<User> list=userdao.findAll();
				boolean bool=true;
				if(list!=null){
					for(User u:list){
						System.out.println(username+password);
						if(u.getUname().equals(username)&&u.getPassword().equals(password)&&u.getStatus()==1){//登录成功
							HttpSession session=request.getSession();
							session.setAttribute("user",u);
							datecartdao datecartdao=new  datecartdao();//获取用户在数据库的购物车
							List<DateCart> cartlist=datecartdao.findByU_id(u.getUid());
							Cart cart=new Cart();//创建新的购物车用来存放用户在数据库的购物车，session的购物车将被覆盖
							if(cartlist!=null){
								
								for(DateCart e:cartlist){
									bookdao bookdao=new bookdao();
									Book book=bookdao.findById(e.getBid());
									cart.add(book);
								}
								session.setAttribute("cart", cart);
							}
							else{
								session.setAttribute("cart", cart);//如果用户数据库购物车本身为空，session的 购物车置空。
							}
							bool=false;
							response.sendRedirect("../main/main.jsp");
							
						}
					}
			    }
				if(bool){
					response.sendRedirect("../login/loginerror.html");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("logout".equals(action)){//注销
			String p=request.getParameter("p");
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("user");
			session.removeAttribute("user");//注销用户
			/*
			 * 将新的购物车存入该用户数据库
			 */
//			Cart cart=(Cart)session.getAttribute("cart");//获取当前购物车
//			List<Book> list=cart.getItems();
//			datecartdao datecartdao=new datecartdao();
//			try {
//				datecartdao.delete(user.getUid());//删除原该用户数据库购物车
//				for(Book e:list){//向该用户插入新的购物车
//					datecartdao.add(user.getUid(), e.getBid());
//				}
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			session.removeAttribute("cart");//注销购物车
			if("main".equals(p)){
			response.sendRedirect("main/main.jsp");
			}
			if("introduce1".equals(p)){
				String bid=request.getParameter("bid");
				response.sendRedirect("introduce1.do?bid="+bid);
			}
			if("introduce2".equals(p)){
				String currpage=request.getParameter("currpage");
				String type=request.getParameter("type");
				response.sendRedirect("introduce2.do?currpage="+currpage+"&type="+type);
			}
			if("introduce3".equals(p)){
				String currpage=request.getParameter("currpage");
				String keyword=request.getParameter("keyword");
				response.sendRedirect("search.do?currpage="+currpage+"&keyword="+keyword);
			}
			if("introduce4".equals(p)){
				
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/introduce/introduce4.jsp");
			}
		}
		if("search".equals(action)){
			String keyword=request.getParameter("keyword");
			keyword=new String(keyword.getBytes("ISO-8859-1"),"utf-8");
			int currpage=1;
			try{
			currpage=Integer.parseInt(request.getParameter("currpage"));
			}catch(Exception e){
			currpage=1;	
			}
			bookdao bookdao=new bookdao();
			try {
				List<Book> list=bookdao.findByKeyword(keyword);
				if(list!=null){
				request.setAttribute("book",list);
				request.getRequestDispatcher("introduce/introduce3.jsp?currpage="+currpage+"&keyword="+keyword).forward(request, response);
				}
				else{
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/introduce/introduce4.jsp");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("lookcart".equals(action)){//查看购物车，先检查是否登录，没有登录，要先登录
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/shop/cart.jsp");
			}
		}
		
		if("add".equals(action)){//购物车添加书籍
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
				int bid=Integer.parseInt(request.getParameter("bid"));
				Cart cart=null;
				
				cart=(Cart)session.getAttribute("cart");
				
				bookdao bookdao=new bookdao();
				User user=(User)session.getAttribute("user");
				datecartdao datecartdao=new datecartdao();
				
				try {
					Book book = bookdao.findById(bid);
					boolean sucessfalse=cart.add(book);
					//检查新添加的是否重复，若不重复，才向数据库的购物车添加数据
					if(sucessfalse){
						datecartdao.add(user.getUid(), bid);
					}
					session.setAttribute("cart",cart);
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/introduce1.do?bid="+bid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		if("delete".equals(action)){//购物车删除书籍
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
				User user=(User)session.getAttribute("user");
				datecartdao datecartdao=new datecartdao();
				int bid=Integer.parseInt(request.getParameter("bid"));
				try {
					datecartdao.deleteByUidBid(user.getUid(), bid);
					session=request.getSession();
					Cart cart=(Cart)session.getAttribute("cart");
					cart.delete(bid);
					session.setAttribute("cart", cart);
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/shop/cart.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if("deleteall".equals(action)){//购物车清空书籍
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
				try {
					User user=(User)session.getAttribute("user");
					int uid=user.getUid();
					datecartdao datecartdao=new datecartdao();
					session=request.getSession();
					Cart cart=(Cart)session.getAttribute("cart");
					List<Book> list=cart.getItems();
					for(Book e:list){
						datecartdao.deleteByUidBid(uid, e.getBid());
					}
					cart.clear();
					session.setAttribute("cart", cart);
					System.out.println(cart.getItems().size());
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/shop/cart.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if("buy".equals(action)){//支付操作
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
				Cart cart=(Cart)session.getAttribute("cart");
				List<Book> list=cart.getItems();//获取购物车的书籍
				User user=(User)session.getAttribute("user");
				int uid=user.getUid();
				datecartdao datecartdao=new datecartdao();
				try {
					datecartdao.delete(user.getUid());//清空数据库该用户的购物车
					shoprecorddao shoprecorddao=new shoprecorddao();
					for(Book e:list){//添加都数据库购买记录
						shoprecorddao.insert(uid,e.getBid());
					}
					cart.clear();//清空前台购物车
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/lookcart.do");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		}
		if("mybook".equals(action)){
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
			int u_id=Integer.parseInt(request.getParameter("u_id"));
			int currpage=1;
			try{
			currpage=Integer.parseInt(request.getParameter("currpage"));
			}catch(Exception e){
			currpage=1;	
			}
			shoprecorddao shoprecorddao=new shoprecorddao();
			try {
				
				List<ShopRecord> list=shoprecorddao.findByU_id(u_id);
				List<Book> listbook=new ArrayList();
				if(list!=null){
					for(ShopRecord e:list){
						bookdao bookdao=new bookdao();
						Book book=bookdao.findById(e.getBid());
						listbook.add(book);
					}
					request.setAttribute("book",listbook);
					request.getRequestDispatcher("user/mybook.jsp?currpage="+currpage).forward(request, response);
				}
				else{
					response.sendRedirect("http://127.0.0.1:8081/bookWeb/user/emptymybook.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("read".equals(action)){
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/login/login.html");
			}
			else{
			int u_id=Integer.parseInt(request.getParameter("u_id"));
			int bid=Integer.parseInt(request.getParameter("bid"));
			shoprecorddao shoprecorddao=new shoprecorddao();
			bookdao bookdao=new bookdao();
			try {
				Book book=bookdao.findById(bid);
				ShopRecord shoprecord=shoprecorddao.findChapter(u_id, bid);
				request.setAttribute("book", book);
				System.out.println(book.getTotalchapter());
				request.getRequestDispatcher("read/read2.jsp?currchapter="+shoprecord.getChapter()+"&u_id="+u_id+"&bid"+bid).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		if("savechapter".equals(action)){
			HttpSession session=request.getSession();
			if(session.getAttribute("user")==null){
				PrintWriter out=response.getWriter();
				out.print("用户已注销");
			}
			else{
			System.out.println("修改");
			int chapter=Integer.parseInt(request.getParameter("chapter"));
			int u_id=Integer.parseInt(request.getParameter("u_id"));
			int bid=Integer.parseInt(request.getParameter("bid"));
			shoprecorddao shoprecorddao=new shoprecorddao();
			System.out.println("修改");
			try {
				shoprecorddao.updateChapter(chapter, u_id, bid);//修改阅读记录
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		
		
		
		if("adlogin".equals(action)){//管理员登陆
			String aname=request.getParameter("aname");
			String apwd=request.getParameter("apwd");
			//keyword=new String(aname.getBytes("ISO-8859-1"),"utf-8");
			AdminiDao adminidao=new AdminiDao();
			boolean bool=true;
			try {
				List<Administrator> list=adminidao.findAll();
				for(Administrator e:list){
					if(e.getAname().equals(aname)&&e.getApwd().equals(apwd)){
						ad=e;
						HttpSession session=request.getSession();
						session.setAttribute("admin", e);
						bool=false;
					}
					
				}
				if(bool){
					response.setCharacterEncoding("utf-8");
					PrintWriter out=response.getWriter();
					out.print("用户名或密码错误");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if("a".equals(action)){//管理员成功登陆
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}
			else{
			request.setAttribute("ad", ad);
			request.getRequestDispatcher("/WEB-INF/admanage/manager.jsp").forward(request, response);
			}
		}
		if("admanage".equals(action)){//管理员修改查看界面
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			AdminiDao adminidao=new AdminiDao();
			try {
				List<Administrator> list=adminidao.findByPage(page);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/admanage/admanage.jsp?page="+page).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("addelete".equals(action)){//删除管理员
			String aname=request.getParameter("aname");
			AdminiDao adminidao=new AdminiDao();
			try {
				adminidao.deleteByAname(aname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("adupdate".equals(action)){//修改管理员
			String aname=request.getParameter("aname");
			String apwd=request.getParameter("apwd");
			String atype=request.getParameter("atype");
			String yaname=request.getParameter("yaname");
			AdminiDao adminidao=new AdminiDao();
			try {
				List<Administrator> list=adminidao.findAll();
				boolean b=true;
				for(Administrator e:list){
					if(e.getAname().equals(aname)){
						b=false;
						PrintWriter out=response.getWriter();
						out.print("管理员用户名重复");
					}
				}
				if(b){
					adminidao.updateByAname(aname, apwd, atype, yaname);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("adadd".equals(action)){//添加管理员
			String aname=request.getParameter("aname");
			String apwd=request.getParameter("apwd");
			int atype=Integer.parseInt(request.getParameter("atype"));
			AdminiDao adminidao=new AdminiDao();
			try {
				List<Administrator> list=adminidao.findAll();
				boolean b=true;
				for(Administrator e:list){
					if(e.getAname().equals(aname)){
						b=false;
						PrintWriter out=response.getWriter();
						out.print("管理员用户名重复");
					}
				}
				if(b){
					adminidao.insert(aname, apwd, atype);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if("bookmanage".equals(action)){//电子书修改查看界面
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			bookdao bookdao=new bookdao();
			try {
				List<Book> list=bookdao.findByPage(page);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/admanage/bookmanage.jsp?page="+page).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("bookdelete".equals(action)){//电子书删除
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			int bid=Integer.parseInt(request.getParameter("bid"));
			bookdao bookdao=new bookdao();
			try {
				bookdao.deleteByBid(bid);
				List<Book> list=bookdao.findByPage(page);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/admanage/bookmanage.jsp?page="+page).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("bookupdate".equals(action)){//电子书修改
			int bid=Integer.parseInt(request.getParameter("bid"));
			String bname=request.getParameter("bname");
			String author=request.getParameter("author");
			String type=request.getParameter("type");
			String introduce=request.getParameter("introduce");
			String imgadd=request.getParameter("imgadd");
			String bookadd=request.getParameter("bookadd");
			int totalchapter=Integer.parseInt(request.getParameter("totalchapter"));
			double price=Double.parseDouble(request.getParameter("price"));
			bookdao bookdao=new bookdao();
			try {
				bookdao.updateByBid(bname, author, type, introduce, imgadd, bookadd, price, totalchapter, bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("bookadd".equals(action)){//电子书插入
			String bname=request.getParameter("bname");
			String author=request.getParameter("author");
			String type=request.getParameter("type");
			String introduce=request.getParameter("introduce");
			String imgadd=request.getParameter("imgadd");
			String bookadd=request.getParameter("bookadd");
			int totalchapter=Integer.parseInt(request.getParameter("totalchapter"));
			double price=Double.parseDouble(request.getParameter("price"));
			bookdao bookdao=new bookdao();
			try {
				bookdao.insert(bname, author, type, introduce, imgadd, bookadd, price, totalchapter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("subpicture".equals(action)){
			int bid=Integer.parseInt(request.getParameter("bid"));
			int page=Integer.parseInt(request.getParameter("page"));
			FileItemFactory factory=new DiskFileItemFactory();//为解析器提供默认配置
			ServletFileUpload sfu=new ServletFileUpload(factory);//创建解析器
			try {
				//解析器将解析的结果封装到FileItem对象上
				//,一个表单域对应一个FileItem对象。
				//只需要调用FileItem对象的提供的方法即可
				//获得相应表单域的数据。
				List<FileItem> items=sfu.parseRequest(request);
				for(FileItem f:items){
					if(f.isFormField()){//普通的表单域
						System.out.println(f.getString());//读取数据
					}
					else{//上传文件域
						ServletContext context=getServletContext();
						String path=context.getRealPath("images/bookimages");//依据逻辑路径获得实际部署时的物理路径
						//获得文件名
						String filename=f.getName();
						File file=new File(path+"/"+filename);
						f.write(file);
						bookdao bookdao=new bookdao();
						String path2="http://127.0.0.1:8081/bookWeb/images/bookimages/"+filename;
						bookdao.updateimgaddByBid(path2, bid);//修改电子书数据库图片路径
						response.sendRedirect("bookmanage.do?page="+page);
						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		if("usermanage".equals(action)){// 用户修改查看界面
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			userdao userdao=new userdao();
			try {
				List<User> list=userdao.findByPage(page);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/admanage/usermanage.jsp?page="+page).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("userdelete".equals(action)){// 用户删除  还要删除用户的购物车和购买记录
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			userdao userdao=new userdao();
			datecartdao datecartdao=new datecartdao();
			shoprecorddao shoprecorddao=new shoprecorddao();
			
			try {
				userdao.deleteByUid(uid);
				datecartdao.delete(uid);
				shoprecorddao.deleteByUid(uid);
				List<User> list=userdao.findByPage(page);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/admanage/usermanage.jsp?page="+page).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("userupstate".equals(action)){// 用户 状态修改界面
			int uid=Integer.parseInt(request.getParameter("uid"));
			userdao userdao=new userdao();
			try {
				List<User> list=userdao.findByUid(uid);
				User user=list.get(0);//只有一个用户
				if(user.getStatus()==0){
					userdao.upStatueByUid(1, uid);
				}
				else{
					userdao.upStatueByUid(0, uid);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		if("usercartmanage".equals(action)){// 用户购物车修改查看界面
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			datecartdao datecartdao=new datecartdao();
			bookdao bookdao=new bookdao();
			try {
				List<DateCart> list=datecartdao.findByUidPage(uid, page);
				Map<Integer,String> map=new HashMap<Integer,String>();
				if(list!=null){
					for(DateCart e:list){
						Book book=bookdao.findById(e.getBid());
						map.put(e.getBid(), book.getBname());
					}
				}
				else{
					map=null;
				}
				
				request.setAttribute("map", map);
				request.getRequestDispatcher("/WEB-INF/admanage/usercartmanage.jsp?page="+page+"&uid"+uid).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		if("usercartdelete".equals(action)){// 用户购物车通过（加入购买记录）
			int bid=Integer.parseInt(request.getParameter("bid"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			datecartdao datecartdao=new datecartdao();
			shoprecorddao shoprecorddao=new shoprecorddao();
			try {
				
				datecartdao.deleteByUidBid(uid, bid);
				shoprecorddao.insert(uid, bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("usercartdelete2".equals(action)){// 用户购物车整页通过（加入购买记录）
			int page=Integer.parseInt(request.getParameter("page"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			datecartdao datecartdao=new datecartdao();
			shoprecorddao shoprecorddao=new shoprecorddao();
			try {
				List<DateCart> list=datecartdao.findByUidPage(uid, page);
				if(list!=null){
					for(DateCart e:list){
						datecartdao.deleteByUidBid(uid, e.getBid());
						shoprecorddao.insert(uid, e.getBid());
					}
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		if("usershoprecordmanage".equals(action)){// 用户购买记录查询修改界面
			HttpSession session=request.getSession();
			if(session.getAttribute("admin")==null){
				response.sendRedirect("http://127.0.0.1:8081/bookWeb/Administrator/administrator.html");
			}else{
			int page=Integer.parseInt(request.getParameter("page"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			shoprecorddao shoprecorddao=new shoprecorddao();
			bookdao bookdao=new bookdao();
			try {
				List<ShopRecord> list=shoprecorddao.findByUidPage(uid, page);
				List<UserShopR> list2=new ArrayList();
				if(list!=null){
					for(ShopRecord e:list){
						UserShopR usershopr=new UserShopR();
						usershopr.setBid(e.getBid());
						usershopr.setBname(bookdao.findById(e.getBid()).getBname());
						usershopr.setChapter(e.getChapter());
						list2.add(usershopr);
					}	
				}
				else{
					list2=null;
				}
				request.setAttribute("list", list2);
				request.getRequestDispatcher("/WEB-INF/admanage/usershoprecordmanage.jsp?page="+page+"&uid"+uid).forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

		if("shoprecorddelete".equals(action)){// 用户购买记录删除
			int bid=Integer.parseInt(request.getParameter("bid"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			shoprecorddao shoprecorddao=new shoprecorddao();
			try {
				shoprecorddao.deleteByUidBid(uid, bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("shoprecordadd".equals(action)){// 用户购买记录添加
			String bname=request.getParameter("bname");
			int uid=Integer.parseInt(request.getParameter("uid"));
			bookdao bookdao=new bookdao();
			shoprecorddao shoprecorddao=new shoprecorddao();
			try {
				Book book=bookdao.findByBname(bname).get(0);
				int bid=book.getBid();
				shoprecorddao.insert(uid, bid);
			} catch (Exception e) {
				PrintWriter out=response.getWriter();
				out.print("书名错误或书名不存在");// ajax返回非“”
			}
		}
		
		
		
		
	}

}
