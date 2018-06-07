package cn.grid.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    
    public DataServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");//取得当前页数,这是json自身的参数
		String rows = request.getParameter("rows");//取得每页显示行数，这是json自身的参数
		System.out.println(page);
		System.out.println(rows);
		int totalRecord = 80;//总记录数（应根据数据库取得，此处仅为模拟）
		int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? totalRecord
					/ Integer.parseInt(rows) : totalRecord / Integer.parseInt(rows)
					+1;//计算总页数
		try {
			int index = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);//开始记录数 
			int pageSize = Integer.parseInt(rows);
			//下面模拟构造JSON数据对象
			String json = "{\"total\":\"" + totalPage + "\",\"page\":\"" + page + "\",\"records\":\"" + totalRecord + "\",\"rows\":[" ;
			for (int i = index; i < pageSize + index && i < totalRecord; i++){
				json += "{\"cell\":[\"NO " + i + "\",\"name" + i + "\",\"PHONE " + i + "\"]}";
				if (i != pageSize + index - 1 && i != totalRecord - 1){
					json += ",";
				}
			}
			json += "]}";
			response.getWriter().write(json);//将json数据返回页面
			request.getSession().setAttribute("tf", json);
		} catch (Exception ex){
			
		}
	}

}
