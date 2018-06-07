package test;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.service.ExamServiceImpl;
import com.tarena.elts.ui.ClientContext;
import com.tarena.elts.ui.ExamFrame;
import com.tarena.elts.ui.LoginFrame;
import com.tarena.elts.ui.MenuFrame;
import com.tarena.elts.ui.WelcomeFrame;
import com.tarena.elts.util.Config;

public class TestLoginFrame {
	public static void main(String[] args) {
		WelcomeFrame welcomFrame=new WelcomeFrame();
		LoginFrame loginFrame=new LoginFrame();
		MenuFrame menuFrame=new MenuFrame();
		ExamFrame examFrame=new ExamFrame();
		ClientContext clientContext=new ClientContext();
		Config config=new Config("client.properties");
		EntityContext entityContext=new EntityContext(config);
		ExamServiceImpl examService=new ExamServiceImpl();
		//把对象注入属性
		loginFrame.setClientContext(clientContext);
		menuFrame.setClientContext(clientContext);
		clientContext.setLoginFrame(loginFrame);
		clientContext.setExamFrame(examFrame);
		clientContext.setMenuFrame(menuFrame);
		clientContext.setWelcomFrame(welcomFrame);
		clientContext.setExamService(examService);
		examService.setEntityContext(entityContext);
		examFrame.setClientContext(clientContext);
		clientContext.show();
		
	}
}
