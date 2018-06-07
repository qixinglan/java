package com.tarena.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.tarena.dao.AdminDaoImp;
import com.tarena.entity.Admin;
import com.tarena.util.ImageUtil;

public class LoginAction {
	InputStream imageStream;
	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	String admin_code;
	String password;
	String msg;
	String code;
	
	public String toLogin(){
		return "ok";
	}
	public String login(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		AdminDaoImp adminDaoImp=new AdminDaoImp();
		Admin admin=adminDaoImp.findByAdmin_code(admin_code);
		String num=(String)session.getAttribute("code");
		if(num==null||!num.equalsIgnoreCase(code)){
			msg="验证码错误";
			return "fail";
		}
		if(admin==null){
			msg="用户名不存在";
			return "fail";
		}
		if(admin.getPassword().equals(password)){
			session.setAttribute("admin", admin);
			return "success";
		}else{
			msg="密码错误";
			return "fail";
		}
	}
	public String createImage() throws IOException{
		Map<String, BufferedImage> map=ImageUtil.createImage();
		String code = map.keySet().iterator().next();
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("code",code);
		BufferedImage image = map.get(code);
		imageStream=ImageUtil.getInputStream(image);
		return "image";
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
