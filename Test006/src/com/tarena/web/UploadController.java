package com.tarena.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/upload")
public class UploadController {
	//显示上传表单
		@RequestMapping("/upload.form")
		public String uploadForm(){
			return "upload";
		}
	@RequestMapping("/file-upload.form")
	public String upload(MultipartFile file) throws IOException{
		System.out.println("upload");
		String fileType=file.getContentType();
		System.out.println(fileType);
		String fileName=file.getOriginalFilename();
		System.out.println(fileName);
		String type=fileName.substring(fileName.lastIndexOf(".")+1);
		FileOutputStream fos=new FileOutputStream
				(new File("C:/Users/Administrator/Desktop/upload."+type));
		byte b[]=file.getBytes();
		fos.write(b);
		return "success";
	}
	@ExceptionHandler
	public String execute(HttpServletRequest req,Exception e)throws Exception{
		if(e instanceof MaxUploadSizeExceededException){
			req.setAttribute("error", "上载文件过大！");
			return "upload";
		}
		throw e;
	}
}
