package tag;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Date2 extends SimpleTagSupport{
	//d1为指定日期
	//d2为指定格式
	//默认为当前日期，默认格式为yyyy-MM-dd
	private String d1;
	private String d2;
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext)getJspContext();
		JspWriter out=pc.getOut();
		if(d1==null&&d2==null){//都不指定
			java.util.Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			out.print(sdf.format(date));
		}
		if(d1==null&&d2!=null){//指定格式
			java.util.Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(d2);
			out.print(sdf.format(date));
		}
		if(d1!=null&&d2==null){//指定日期
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date;
			try {
				date = sdf.parse(d1);
				out.print(sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(d1!=null&&d2!=null){//都指定
			SimpleDateFormat sdf=new SimpleDateFormat(d2);
			java.util.Date date;
			try {
				date = sdf.parse(d1);
				out.print(sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
