package com.tarena.elts.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tarena.elts.util.Config;

/**
 * 数据管理  实体对象和数据库之间   值对象和项目之间 上下文关系
 * @author asus
 *里面存储着数据
 */
public class EntityContext {
	private Config config;
	//用户的实例集合，Map集合（ID=用户）
	private HashMap<Integer,User> users=new HashMap();
	//所有考题的实例集合
	private List<Question> questions=new ArrayList<Question>();
	public EntityContext(Config config){
		this.config=config;
		//加载用户
		loadUsers(config.getString("UserFile"));
		//加载考题
		loadQuestions(config.getString("QuestionFile"));
	}
	//加载考题
	private void loadQuestions(String fileName){
		try {
			BufferedReader in=new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),"utf-8"));
			String line =null;
			while((line=in.readLine())!=null){
				if(line.startsWith("#")||line.equals("")){
					continue;
				}
			//分析数据 转换为Question
			Question q=paseQuestion(line,in);
			//把Question给集合question
			questions.add(q);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private Question paseQuestion(String line, BufferedReader in) throws IOException {
		// TODO Auto-generated method stub
		//把line分析转换为正确答案和难度
		String[] temp=line.split("[@,][a-z]+=");
		String[] i=temp[1].split("/");
		List<Integer> answer=new ArrayList<Integer>();
		for(int j=0;j<i.length;j++){
			answer.add(Integer.parseInt(i[j]));
		}
		//在读取一行，转换为题干
		String title=in.readLine();
		//在读取四行转换为选项集合
		List<String> options=new ArrayList<String>();
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		//创建Question对象
		Question q=new Question();
		q.setLevel(Integer.parseInt(temp[2]));
		q.setAnswer(answer);
		q.setTitle(title);
		q.setOptions(options);
		return q;
	}
	//加载用户   从数据文件加载用户
	//根据文件名找到文件
	//创建文件输入流（输入到系统）
	//读取每一行数据
	//分析数据
	//根据数据创建User
	//把User给users
	private void loadUsers(String fileName){
		try {
			BufferedReader in=new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),"utf-8"));
			String line=null;
			while((line=in.readLine())!=null){
				line=line.trim();//去除左右两边的空格
				if(line.startsWith("#")||line.equals("")){
					continue;//忽略空行和注释
				}
				//分析line，转换为Users
				User u=paseUser(line);
				//
				users.put(u.getId(), u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private User paseUser(String line) {
		String[] data=line.split(":");
		//
		User user=new User();
		user.setId(Integer.parseInt(data[0]));
		user.setName(data[1]);
		user.setPassword(data[2]);
		user.setPhonenumber(data[3]);
		user.setEmail(data[4]);
		return user;
	}
	//根据ID返回一个user
	public User getUserById(int id){
		return users.get(id);
	}
	public List<Question> getQuestions() {
		return questions;
	}
	//获取考试信息的一系列属性
	public String getExamTitle(){
		return config.getString("ExamTitle");
	}
	public int gettotalScore(){
		return config.getInt("TotalScore");
	}
	public int getTimeLimit(){
		return config.getInt("TimeLimit");
	}
	public int getQuestionCount(){
		return config.getInt("QuestionCount");
	}
	public String getRule(){
		return config.getString("ExamRule");
	}
}

