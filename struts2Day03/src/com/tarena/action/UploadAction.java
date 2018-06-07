package com.tarena.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.tarena.util.FileUtil;

public class UploadAction {
	private File some;
	private String someFileName;
	public String execute(){
		if(some==null){
			return "error";
		}
		String path=
				ServletActionContext.getServletContext().
				getRealPath("WEB-INF/upload/"+someFileName);
		FileUtil fu=new FileUtil();
		fu.copy(some, new File(path));
		return "ok";
	}
	public File getSome() {
		return some;
	}
	public void setSome(File some) {
		this.some = some;
	}
	public String getSomeFileName() {
		return someFileName;
	}
	public void setSomeFileName(String someFileName) {
		this.someFileName = someFileName;
	}
	
}
//package com.tarena.action;
//
//import java.io.File;
//import java.io.Serializable;
//
//import org.apache.struts2.ServletActionContext;
//
//import com.tarena.util.FileUtil;
//
//public class UploadAction implements Serializable {
//
//	//接收表单传入的文件，要与表单中的
//	//文件框的名称一致。该数据实际上是
//	//由拦截器传入的临时文件。
//	private File some;
//	
//	//拦截器检测到符合规则的String类型的
//	//变量后，会将原始文件名注入。
//	private String someFileName;
//	
//	public String execute() {
//		if(some == null) {
//			return "error";
//		}
//		//d:/czh/apache-tomcat-7.0.54/webapps/StrutsDay04/WEB-INF/upload/
//		//相对于部署项目的路径
//		String path = "WEB-INF/upload/" + someFileName;
//		//计算出完整路径
//		path = ServletActionContext
//			.getServletContext().getRealPath(path);
//		//文件拷贝
//		FileUtil.copy(some, new File(path));
//		
//		return "ok";
//	}
//
//	public File getSome() {
//		return some;
//	}
//
//	public void setSome(File some) {
//		this.some = some;
//	}
//
//	public String getSomeFileName() {
//		return someFileName;
//	}
//
//	public void setSomeFileName(String someFileName) {
//		this.someFileName = someFileName;
//	}
//	
//}