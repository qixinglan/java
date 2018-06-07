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
//	//���ձ�������ļ���Ҫ����е�
//	//�ļ��������һ�¡�������ʵ������
//	//���������������ʱ�ļ���
//	private File some;
//	
//	//��������⵽���Ϲ����String���͵�
//	//�����󣬻Ὣԭʼ�ļ���ע�롣
//	private String someFileName;
//	
//	public String execute() {
//		if(some == null) {
//			return "error";
//		}
//		//d:/czh/apache-tomcat-7.0.54/webapps/StrutsDay04/WEB-INF/upload/
//		//����ڲ�����Ŀ��·��
//		String path = "WEB-INF/upload/" + someFileName;
//		//���������·��
//		path = ServletActionContext
//			.getServletContext().getRealPath(path);
//		//�ļ�����
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