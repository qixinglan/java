package com.tarena.elts.net;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.ExamServiceImpl;
import com.tarena.elts.ui.ClientContext;
import com.tarena.elts.ui.ExamFrame;
import com.tarena.elts.ui.LoginFrame;
import com.tarena.elts.ui.MenuFrame;
import com.tarena.elts.ui.WelcomeFrame;
import com.tarena.elts.util.Config;

public class Start {
	public static void main(String[] args) {
		WelcomeFrame welcomeFrame=new WelcomeFrame();
		LoginFrame loginFrame=new LoginFrame();
		MenuFrame menuFrame=new MenuFrame();
		ExamFrame examFrame=new ExamFrame();
		ClientContext clientContext=new ClientContext();
//		ClientAgent clientAgent=new ClientAgent();
//		ExamService examService=new ExamService() {
//			
//			@Override
//			public User login(int id, String pwd) throws IdOrPwdException {
//				// TODO Auto-generated method stub
//				if(id==1000&&pwd.equals("1234")){
//					return new User("����",1000,"1234");
//				}
//				//�׳�����ʱ�쳣
//				throw new IdOrPwdException("�û������������");
//			}
//		};//����EntityContext����
		Config config=new Config("client.properties");
		EntityContext entityContext=new EntityContext(config);
		ExamServiceImpl examService=new ExamServiceImpl();
		//�Ѷ���ע������
		clientAgent.setConfig(config);
		loginFrame.setClientContext(clientContext);
		menuFrame.setClientContext(clientContext);
		clientContext.setLoginFrame(loginFrame);
		clientContext.setMenuFrame(menuFrame);
		//clientContext.setExamService(examService);
		//����һ���ͻ��˴���
		ExamService clientAgent=new ClientAgent();
		clientContext.setExamService(clientAgent);
//		clientContext.setExamFrame(examFrame);
		clientContext.setWelcomeFrame(welcomeFrame);
		examService.setEntityContext(entityContext);
		examFrame.setClientContext(clientContext);
		clientContext.show();
		//loginFrame.setVisible(true);
	}			
}
