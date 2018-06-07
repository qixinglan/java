package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class form extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FileItemFactory factory=new DiskFileItemFactory();//为解析器提供默认配置
		ServletFileUpload sfu=new ServletFileUpload(factory);//创建解析器
		try {
			//解析器将解析的结果封装到FileItem对象上
			//,一个表单域对应一个FileItem对象。
			//只需要调用FileItem对象的提供的方法即可
			//获得相应表单域的数据。
			List<FileItem> items=sfu.parseRequest(request);
			for(FileItem f:items){
				if(f.isFormField()){//普通的表单域
					System.out.println(f.getString());//读取数据
				}
				else{//上传文件域
					ServletContext context=getServletContext();
					String path=context.getRealPath("upload");//依据逻辑路径获得实际部署时的物理路径
					//获得文件名
					String filename=f.getName();
					File file=new File(path+"/"+filename);
					System.out.println(path);
					f.write(file);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
