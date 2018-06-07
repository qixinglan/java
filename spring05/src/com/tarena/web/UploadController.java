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
	//显示上传表单
	@RequestMapping("/upload.form")
	public String uploadForm(){
		return "upload";
	}
	//处理上传请求
	@RequestMapping("/file-upload.form")
	public String fileUpload(MultipartFile image,String author,
			ModelMap map,HttpServletRequest req)throws Exception{
//		image.getOriginalFilename();//获取上传文件的文件名
//		image.getName();//获取name的属性名称
//		image.getBytes();//上载的全部byte数据
//		image.getInputStream();//获取上传文件的一个byte数据
//		image.getContentType();//获取上传文件的类型png图片返回————“image/png”
		//创建目标文件夹
		String path="/WEB-INF/images";
		path=req.getSession().getServletContext().getRealPath(path);//获得实际路径
		System.out.println(path);
		File dir=new File(path);
		if(!dir.exists()){
			dir.mkdir();
		}
		String uuid=UUID.randomUUID().toString();//产生永远不重复的字符串
		String Ofilename=image.getOriginalFilename();
		String img=uuid+Ofilename.substring(Ofilename.lastIndexOf("."));//文件名
		String txt=uuid+".txt";
		//写出文件
		FileOutputStream imgOut=new FileOutputStream(new File(dir,img));
		imgOut.write(image.getBytes());
		imgOut.close();
		//写出元数据文本文件
		PrintWriter out=new PrintWriter(new File(dir,txt));
		out.println("原始文件名："+image.getOriginalFilename());
		out.println("作者："+author);
		out.println("ContentType："+image.getContentType());
		map.addAttribute("msg","成功上载"+Ofilename);
		out.close();
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
