package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * ���jsp��ǩ�����ԣ����������Ʊ�����
 * ��ǩ�����������һ�£���������Ҫƥ�䡣
 *���⣬��ǩ������Ա���Ҫ�ж�Ӧ��set������
 */
public class HelloTag extends SimpleTagSupport{
	private String info;
	private int qty;
	
	public HelloTag() {
	  System.out.println("HelloTag's constructor...");
  }

	public void setInfo(String info) {
		System.out.println("setInfo..." + info);
  	this.info = info;
  }

	public void setQty(int qty) {
		System.out.println("setQty..." + qty);
  	this.qty = qty;
  }

	/**
	 * override SimpleTagSupport���doTag������
	 * �������߼�д�������
	 */
  public void doTag() throws JspException, IOException {
  	System.out.println("doTag...");
  	/*
  	 * ʹ��SimpleTagSupport���ṩ��getJspContext
  	 * �������PageContext��PageContext�ṩ�˷���
  	 * ���ҵ������˸���������
  	 */
  	PageContext ctx = (PageContext)getJspContext();
  	JspWriter out = ctx.getOut();
  	for(int i=0;i<qty;i++){
			out.println(info + "<br/>");
		}
  }
	
}
