package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 如果jsp标签有属性，则属性名称必须与
 * 标签类的属性名称一致，而且类型要匹配。
 *此外，标签类的属性必须要有对应的set方法。
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
	 * override SimpleTagSupport类的doTag方法，
	 * 将处理逻辑写在这儿。
	 */
  public void doTag() throws JspException, IOException {
  	System.out.println("doTag...");
  	/*
  	 * 使用SimpleTagSupport类提供的getJspContext
  	 * 方法获得PageContext，PageContext提供了方法
  	 * 来找到其它八个隐含对象。
  	 */
  	PageContext ctx = (PageContext)getJspContext();
  	JspWriter out = ctx.getOut();
  	for(int i=0;i<qty;i++){
			out.println(info + "<br/>");
		}
  }
	
}
