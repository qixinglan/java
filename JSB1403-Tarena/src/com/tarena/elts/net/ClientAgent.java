package com.tarena.elts.net;

import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.IdOrPwdException;
import com.tarena.elts.util.Config;
/**
 * ���Է���ͻ��˴���
 * @author asus
 *
 */
public class ClientAgent implements ExamService {
	private Config config;
	
	public void setConfig(Config config) {
		this.config = config;
	}
	/**
	 * ���ܿͻ��˿�����������������
	 * ���з�װ
	 * ���ӷ�����
	 * �ѷ�װ�õ����ݴ���������
	 * ���ط��������͵�����
	 * ���з��ദ�����շ���User���󹩿���������
	 */
	public User login(int id, String pwd) throws IdOrPwdException {
		//��װ����
		Request req=new Request
		("login",new Class[]{Integer.TYPE,String.class},new Object[]{id,pwd});
		//�ѷ�װ�õ����ݴ���������
		Response res=ServerUtils.remoteCall(config.getString("ServerIP"),config.getInt("ServerPort"),req);
		//���ദ����
		User u=(User)res.getValue();
		if(u==null){//�׳��쳣
			throw new RuntimeException (res.getE());
		}
		return u;
	}
	public QuestionInfo getQuestionInfoByIndex(int index) {
		Request req=new Request("getQuestionInfoByIndex",new Class[]{Integer.TYPE},new Object[]{index});
		Response res=ServerUtils.remoteCall(config.getString("ServerIP"), config.getInt("ServerPort"), req);
		QuestionInfo qi=(QuestionInfo)res.getValue();
		if(){
		}
		return null;
	}
	public String getRule() {
		return null;
	}
	public int getScore() {
		return 0;
	}
	public int over() {
		
		return 0;
	}
	public void saveUserAnswer(int questionIndex, List<Integer> userAnswer) {
		Request req=new Request("saveUserAnswer",new Class[]{Integer.TYPE,List.class},new Object[]{questionIndex,userAnswer});
		
	}
	public void setEntityContext(EntityContext entityContext) {
	}
	public ExamInfo start()throws Exception{
		Request req=new Request("start",new Class[]{},new Object[]{});
		Response res=ServerUtils.remoteCall(config.getString("ServerIP"), config.getInt("ServerPort"), req);
		ExamInfo examInfo=(ExamInfo)res.getValue();
		if(examInfo==null){
			throw new RuntimeException(res.getE());
		}
		return examInfo;
	}
}
