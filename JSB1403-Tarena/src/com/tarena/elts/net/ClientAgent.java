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
 * 考试服务客户端代理
 * @author asus
 *
 */
public class ClientAgent implements ExamService {
	private Config config;
	
	public void setConfig(Config config) {
		this.config = config;
	}
	/**
	 * 接受客户端控制器发过来的数据
	 * 进行封装
	 * 连接服务器
	 * 把封装好的数据传给服务器
	 * 返回服务器发送的数据
	 * 进行分类处理，最终返回User对象供控制器调用
	 */
	public User login(int id, String pwd) throws IdOrPwdException {
		//封装请求
		Request req=new Request
		("login",new Class[]{Integer.TYPE,String.class},new Object[]{id,pwd});
		//把封装好的数据传给服务器
		Response res=ServerUtils.remoteCall(config.getString("ServerIP"),config.getInt("ServerPort"),req);
		//分类处理结果
		User u=(User)res.getValue();
		if(u==null){//抛出异常
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
