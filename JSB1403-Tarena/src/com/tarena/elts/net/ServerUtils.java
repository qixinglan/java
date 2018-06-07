package com.tarena.elts.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * ���紫�乤��
 * @author asus
 *
 */
public class ServerUtils {
	//Զ�̷�������,���ͻ��˴���diaoyong
	public static Response remoteCall(String host,int port,Request req){
		try{
		//���ӷ�����
		Socket s=new Socket(host,port);
		//��������
		ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
		out.writeObject(req);
		out.flush();
		//���ܻ�Ӧ
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
	 * ����ͻ��˵����󣬷���һ����Ӧ����������
	 * ����Req ����req�����method���ԣ���ÿͻ�������ʲôҵ��
	 * ����parameterType���飬���ҵ���е���Ҫ�Ĳ�������
	 * ����parameters���飬���ҵ�����������ĵĲ�������
	 * ��ͻ�����login���͵���ExamService��Login �߼�
	 * �ڵ���login�߼���ʱ����Ҫ�Ĳ������ͺͲ������ݾ���������������
	 */
	public static Response call(Object examService,Request req){
		Response res=new Response();
		try {
			//�Ȱ�req���method parameterType parameters��ԭ��ҵ���߼�
			Class cls=examService.getClass();
			//����ҵ���߼����һ��ҵ�񷽷���login��int ID��String pwd��
			Method md=cls.getMethod(req.getMethod(), req.getParameterTypes());
			Object obj=md.invoke(examService, req.getParameters());
			
			res.setValue(obj);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				res.setE(e);//��½ʧ��ʱ���Զ����쳣
			}
			return res;
	}
}
