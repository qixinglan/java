package com.tarena.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ��һ�֣�������ע�ⷽʽ
 * @author Administrator
 *
 */
@Controller
@RequestMapping("hello2")
public class helloController2 {
	@RequestMapping(params="method=addUser")//����·��http://127.0.0.1:8081/Spring04/hello2.form?method=addUser
	public String addUser(String method){
		System.out.println(method);
		return "success";
	}
	
	@RequestMapping(value="/test0")//Ĭ��Ϊget��ʽ
	public String test0(){
		System.out.println("����·����test/test0.form");
		return "success";
	}
//�����Ҫ�Լ�д��form����post�����Լ���дд���������ַ����Ϊget�������ⱨ��
	@RequestMapping(value="/test1",method=RequestMethod.POST)
	public String test1(String sex){
		System.out.println(sex);
		System.out.println("����·��Ϊ��test/test1.form,������get��ʽ______"+sex);
		return "success";
	}
	
	@RequestMapping(value="/test2",method=RequestMethod.GET,params="param=test2")
	public String test2(){
		System.out.println("����·��Ϊ��test/test1.form?param=test2,������get��ʽ");
		return "success";
	}
	
	//REST���Ĳ���
	@RequestMapping(value="/test3/{name}")
	public String test3(@PathVariable String name){//�βκͽ��ղ���������һ��
		System.out.println(name);
		System.out.println("����·��Ϊ��test/test3/zhangsan.form");
		System.out.println("������ķ���·����ֱ�Ӿͽ���zhangsan���������ֵ�ʹ��ݸ���@RequestMapping(value=\"/test3/{name}\")�е�name�������������������������ƣ�");
		return "success";
	}
	
	@RequestMapping(value="/test5/{name}")
	public String test5(@PathVariable("name")String rename){//�βκͽ��ղ��������Ʋ�һ��
		System.out.println(rename);
		System.out.println("����·��Ϊ��test/test5/zhangsan.form");
		System.out.println("������ķ���·����ֱ�Ӿͽ���zhangsan���������ֵ�ʹ��ݸ���@RequestMapping(value=\"/test5/{name}\")�е�name�������������������������ƣ�" +
				"Ȼ������@PathVariable(\"name\")�е�name����Ҫ��������Ǹ�name����һ�£�Ȼ��Ѵ�name�󶨵��β�rename");
		return "success";
	}
	
	@RequestMapping(value="/test4/{sex}/{sno}")
	//������д��@RequestMapping(value="test4/{sex}/{sno}")Ҳ�ǿ��Ե�
	public String test4(@PathVariable("sex")String sex,@PathVariable("sno")String sno){
		System.out.println(sex);
		System.err.println(sno);
		System.out.println("����·����test/test4/male/10506.form");
		return "success";
	}
	
	//֧������
	@RequestMapping(value="/test6/{textualPart:[a-z-]+}.{numericPart:[\\d]+}")
	public String test6(@PathVariable String textualPart,@PathVariable String numericPart){
		System.out.println(textualPart);//���sometxt
		System.out.println(numericPart);//���123
		System.out.println("����·����test/test6/sometxt.123.form");
		return "success";
	}
	
	//����WEB-INF�µ�views�ļ��еĲ�ͬĿ¼��ҳ�棬������ʵ���userĿ¼�µģ���֪��������ʲô�õİ취û
	@RequestMapping(value="/test7")
	public String test7(){
		System.out.println("����·����test/test7.form");
		return "user/success";
	}

}
