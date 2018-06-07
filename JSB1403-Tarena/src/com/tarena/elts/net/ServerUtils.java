package com.tarena.elts.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 网络传输工具
 * @author asus
 *
 */
public class ServerUtils {
	//远程方法调用,供客户端代理diaoyong
	public static Response remoteCall(String host,int port,Request req){
		try{
		//连接服务器
		Socket s=new Socket(host,port);
		//发送求情
		ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
		out.writeObject(req);
		out.flush();
		//接受回应
		ObjectInputStream in=new ObjectInputStream(s.getInputStream());
		Response res=(Response)in.readObject();
		s.close();
		return res;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/**
	 * 处理客户端的请求，返回一个回应供服务器用
	 * 分析Req 根据req里面的method属性，获得客户端请求什么业务
	 * 根据parameterType数组，获得业务当中的需要的参数类型
	 * 根据parameters数组，获得业务当中所传来的的参数数据
	 * 如客户端在login，就调用ExamService的Login 逻辑
	 * 在调用login逻辑的时候，需要的参数类型和参数内容均来自那两个数组
	 */
	public static Response call(Object examService,Request req){
		Response res=new Response();
		try {
			//先把req里的method parameterType parameters还原成业务逻辑
			Class cls=examService.getClass();
			//返回业务逻辑里的一个业务方法如login（int ID，String pwd）
			Method md=cls.getMethod(req.getMethod(), req.getParameterTypes());
			Object obj=md.invoke(examService, req.getParameters());
			
			res.setValue(obj);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				res.setE(e);//登陆失败时的自定义异常
			}
			return res;
	}
}
