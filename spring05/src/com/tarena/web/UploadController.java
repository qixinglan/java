package com.tarena.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	//��ʾ�ϴ���
	@RequestMapping("/upload.form")
	public String uploadForm(){
		return "upload";
	}
	//�����ϴ�����
	@RequestMapping("/file-upload.form")
	public String fileUpload(MultipartFile image,String author,
			ModelMap map,HttpServletRequest req)throws Exception{
//		image.getOriginalFilename();//��ȡ�ϴ��ļ����ļ���
//		image.getName();//��ȡname����������
//		image.getBytes();//���ص�ȫ��byte����
//		image.getInputStream();//��ȡ�ϴ��ļ���һ��byte����
//		image.getContentType();//��ȡ�ϴ��ļ�������pngͼƬ���ء���������image/png��
		//����Ŀ���ļ���
		String path="/WEB-INF/images";
		path=req.getSession().getServletContext().getRealPath(path);//���ʵ��·��
		System.out.println(path);
		File dir=new File(path);
		if(!dir.exists()){
			dir.mkdir();
		}
		String uuid=UUID.randomUUID().toString();//������Զ���ظ����ַ���
		String Ofilename=image.getOriginalFilename();
		String img=uuid+Ofilename.substring(Ofilename.lastIndexOf("."));//�ļ���
		String txt=uuid+".txt";
		//д���ļ�
		FileOutputStream imgOut=new FileOutputStream(new File(dir,img));
		imgOut.write(image.getBytes());
		imgOut.close();
		//д��Ԫ�����ı��ļ�
		PrintWriter out=new PrintWriter(new File(dir,txt));
		out.println("ԭʼ�ļ�����"+image.getOriginalFilename());
		out.println("���ߣ�"+author);
		out.println("ContentType��"+image.getContentType());
		map.addAttribute("msg","�ɹ�����"+Ofilename);
		out.close();
		return "success";
	}
	@ExceptionHandler
	public String execute(HttpServletRequest req,Exception e)throws Exception{
		if(e instanceof MaxUploadSizeExceededException){
			req.setAttribute("error", "�����ļ�����");
			return "upload";
		}
		throw e;
		
	}
}
