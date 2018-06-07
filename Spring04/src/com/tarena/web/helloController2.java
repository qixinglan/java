package com.tarena.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 另一种，带参数注解方式
 * @author Administrator
 *
 */
@Controller
@RequestMapping("hello2")
public class helloController2 {
	@RequestMapping(params="method=addUser")//请求路径http://127.0.0.1:8081/Spring04/hello2.form?method=addUser
	public String addUser(String method){
		System.out.println(method);
		return "success";
	}
	
	@RequestMapping(value="/test0")//默认为get方式
	public String test0(){
		System.out.println("访问路径：test/test0.form");
		return "success";
	}
//这个需要自己写个form发送post请求，自己手写写在浏览器地址栏的为get请求，在这报错
	@RequestMapping(value="/test1",method=RequestMethod.POST)
	public String test1(String sex){
		System.out.println(sex);
		System.out.println("访问路径为：test/test1.form,而且是get方式______"+sex);
		return "success";
	}
	
	@RequestMapping(value="/test2",method=RequestMethod.GET,params="param=test2")
	public String test2(){
		System.out.println("访问路径为：test/test1.form?param=test2,而且是get方式");
		return "success";
	}
	
	//REST风格的参数
	@RequestMapping(value="/test3/{name}")
	public String test3(@PathVariable String name){//形参和接收参数的名称一致
		System.out.println(name);
		System.out.println("访问路径为：test/test3/zhangsan.form");
		System.out.println("看这里的访问路径，直接就将‘zhangsan’这个参数值就传递给了@RequestMapping(value=\"/test3/{name}\")中的name参数名，可随意命名参数名称，");
		return "success";
	}
	
	@RequestMapping(value="/test5/{name}")
	public String test5(@PathVariable("name")String rename){//形参和接收参数的名称不一致
		System.out.println(rename);
		System.out.println("访问路径为：test/test5/zhangsan.form");
		System.out.println("看这里的访问路径，直接就将‘zhangsan’这个参数值就传递给了@RequestMapping(value=\"/test5/{name}\")中的name参数名，可随意命名参数名称，" +
				"然后后面的@PathVariable(\"name\")中的name名称要和上面的那个name保持一致，然后把此name绑定到形参rename");
		return "success";
	}
	
	@RequestMapping(value="/test4/{sex}/{sno}")
	//这里我写成@RequestMapping(value="test4/{sex}/{sno}")也是可以滴
	public String test4(@PathVariable("sex")String sex,@PathVariable("sno")String sno){
		System.out.println(sex);
		System.err.println(sno);
		System.out.println("访问路径：test/test4/male/10506.form");
		return "success";
	}
	
	//支持正则
	@RequestMapping(value="/test6/{textualPart:[a-z-]+}.{numericPart:[\\d]+}")
	public String test6(@PathVariable String textualPart,@PathVariable String numericPart){
		System.out.println(textualPart);//输出sometxt
		System.out.println(numericPart);//输出123
		System.out.println("访问路径：test/test6/sometxt.123.form");
		return "success";
	}
	
	//访问WEB-INF下的views文件中的不同目录的页面，这里访问的是user目录下的，不知道其他有什么好的办法没
	@RequestMapping(value="/test7")
	public String test7(){
		System.out.println("访问路径：test/test7.form");
		return "user/success";
	}

}
