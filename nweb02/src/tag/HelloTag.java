package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport{
	private String info;
	private int qty;
	
	

	public void setQty(int qty) {
		this.qty = qty;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public void doTag() throws JspException, IOException {
		PageContext ctx=(PageContext)getJspContext();//ͨ��SimpleTagSupport���PageContext
		JspWriter out=ctx.getOut();//�� ͨ��PageContext��������˸����ζ���
		for(int i=0;i<qty;i++){
		    out.println(info+i+"<br/>"); }
	}
	
}
