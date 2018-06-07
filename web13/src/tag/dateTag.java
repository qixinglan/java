package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class dateTag extends SimpleTagSupport{
	private String pattern;

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		Date date=new Date();
		SimpleDateFormat adf=new SimpleDateFormat(pattern);
		PageContext ctx=(PageContext)this.getJspContext();
		JspWriter out=ctx.getOut();
		out.println(adf.format(date));
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
}
