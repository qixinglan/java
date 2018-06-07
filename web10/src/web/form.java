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
		
		FileItemFactory factory=new DiskFileItemFactory();//Ϊ�������ṩĬ������
		ServletFileUpload sfu=new ServletFileUpload(factory);//����������
		try {
			//�������������Ľ����װ��FileItem������
			//,һ�������Ӧһ��FileItem����
			//ֻ��Ҫ����FileItem������ṩ�ķ�������
			//�����Ӧ��������ݡ�
			List<FileItem> items=sfu.parseRequest(request);
			for(FileItem f:items){
				if(f.isFormField()){//��ͨ�ı���
					System.out.println(f.getString());//��ȡ����
				}
				else{//�ϴ��ļ���
					ServletContext context=getServletContext();
					String path=context.getRealPath("upload");//�����߼�·�����ʵ�ʲ���ʱ������·��
					//����ļ���
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
