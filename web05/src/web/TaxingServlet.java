package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaxingServlet extends HttpServlet {
	// String a[]=new String[]{"q"};
	// String b[]=new String[1];
	// String c[]={"asd"};
	private HashMap<String, Double> taxes = new HashMap<String, Double>();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String city = request.getParameter("city");
		double basePrice = Double
				.parseDouble(request.getParameter("baseprice"));
		PrintWriter out = response.getWriter();
		double price = basePrice * taxes.get(city);
		out.println("<h1>出售价格:" + price + "</h1>");
		out.close();
	}

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		String taxInfo = config.getInitParameter("taxRate");
		String[] tax1 = taxInfo.split(":");
		for (int i = 0; i < tax1.length; i++) {
			String[] tax2 = tax1[i].split(",");
			taxes.put(tax2[0], Double.parseDouble(tax2[1]));
		}
	}
}
