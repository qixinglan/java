package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Date extends SimpleTagSupport{
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext)getJspContext();
		JspWriter out=pc.getOut();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy:MM:dd");
		java.util.Date date=new java.util.Date();
		out.println(sdf.format(date));
	}

}
