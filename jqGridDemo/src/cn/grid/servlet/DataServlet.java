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
		String page = request.getParameter("page");//ȡ�õ�ǰҳ��,����json����Ĳ���
		String rows = request.getParameter("rows");//ȡ��ÿҳ��ʾ����������json����Ĳ���
		System.out.println(page);
		System.out.println(rows);
		int totalRecord = 80;//�ܼ�¼����Ӧ�������ݿ�ȡ�ã��˴���Ϊģ�⣩
		int totalPage = totalRecord % Integer.parseInt(rows) == 0 ? totalRecord
					/ Integer.parseInt(rows) : totalRecord / Integer.parseInt(rows)
					+1;//������ҳ��
		try {
			int index = (Integer.parseInt(page) - 1) * Integer.parseInt(rows);//��ʼ��¼�� 
			int pageSize = Integer.parseInt(rows);
			//����ģ�⹹��JSON���ݶ���
			String json = "{\"total\":\"" + totalPage + "\",\"page\":\"" + page + "\",\"records\":\"" + totalRecord + "\",\"rows\":[" ;
			for (int i = index; i < pageSize + index && i < totalRecord; i++){
				json += "{\"cell\":[\"NO " + i + "\",\"name" + i + "\",\"PHONE " + i + "\"]}";
				if (i != pageSize + index - 1 && i != totalRecord - 1){
					json += ",";
				}
			}
			json += "]}";
			response.getWriter().write(json);//��json���ݷ���ҳ��
			request.getSession().setAttribute("tf", json);
		} catch (Exception ex){
			
		}
	}

}
