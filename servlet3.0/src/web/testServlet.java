package web;
/**
 * 注解式Servlet
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(description="",
displayName="",
initParams={@WebInitParam(name="username",value="Tom"),@WebInitParam(name="age",value="18")},
loadOnStartup=1,
name="testServlet",
value={"/testServlet","/helloServlet"} //urlPatterns={"/testServlet","/helloServlet"}
)
/*
 * name ==<serlvet-name>
 * value/urlPatterns两个只能写一个==<url-pattern>   //urlPatterns={"/testServlet","/helloServlet"},
 * loadOnStartup==<load-on-startup>
 * initParams==  <init-param>
 * description==<description>
 * displayName==<display-name>
 */
@MultipartConfig(
		
		)
/*
 * 该注解主要是为了辅助 Servlet 3.0 中 HttpServletRequest 提供的对上传文件的支持。该注解标注在 Servlet 上面，以表示该 Servlet 希望处理的请求的 MIME 类型是 multipart/form-data。另外，它还提供了若干属性用于简化对上传文件的处理。具体如下：

表 5. @MultipartConfig 的常用属性
属性名 类型 是否可选 描述 
fileSizeThreshold  int  是 当数据量大于该值时，内容将被写入文件。 
location  String  是 存放生成的文件地址。 
maxFileSize  long  是 允许上传的文件最大值。默认值为 -1，表示没有限制。 
maxRequestSize  long  是 针对该 multipart/form-data 请求的最大数量，默认值为 -1，表示没有限制。 

 */
public class testServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("");
	}
}
