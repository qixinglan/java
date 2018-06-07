package com.tarena.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.dao.Admin;
import com.tarena.dao.AdminMapper;
import com.tarena.dao.Module;
import com.tarena.util.ImageUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final static String admin_code_error="1";
	private final static String password_error="2";
	private final static String code_error="3";
	private final static String success="4";
	@Resource
	AdminMapper adminMapper;
	@RequestMapping("login.do")
	public String login(){
		return "/main/login";
	}
	@RequestMapping("loginSuccess.do")
	public String loginSuccess(){
		return "/main/index";
	}
	@ResponseBody
	@RequestMapping("adminlogin.do")
	public String adminlogin(String admin_code,String password,String code,HttpSession session){
		if(!code.equalsIgnoreCase((String)session.getAttribute("code"))){
			return code_error;
		}
		Admin admin=adminMapper.findByAdmin_code(admin_code);
		if(admin==null){
			return admin_code_error;
		}else if(!password.equals(admin.getPassword())){
			return password_error;
		}else{
			session.setAttribute("admin", admin);
			List<Module> list=adminMapper.findModulesByAdmin_id(admin.getAdmin_id());
			session.setAttribute("adminModules", list);
			return success;
		}
		
	}
	@RequestMapping("createImage.do")
	public void createImage(HttpSession session,HttpServletResponse response) throws IOException{
		response.setContentType("image/jpeg");
		Map<String, BufferedImage> map=ImageUtil.createImage();
		Set <Entry<String,BufferedImage>> set=map.entrySet();
		String code = map.keySet().iterator().next();
		session.setAttribute("code",code);
		BufferedImage image = map.get(code);
		ImageUtil.getInputStream(image);
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	@RequestMapping("nopower.do")
	public String nopower(){
		return "/main/nopower";
	}
}
